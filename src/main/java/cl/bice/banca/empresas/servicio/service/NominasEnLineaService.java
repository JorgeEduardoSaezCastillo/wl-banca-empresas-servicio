package cl.bice.banca.empresas.servicio.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.xml.ws.WebServiceException;

import cl.bice.banca.empresas.servicio.controller.TransaccionesController;
import cl.bice.banca.empresas.servicio.model.request.operaciones.TransactionRequest;
import cl.bice.banca.empresas.servicio.model.response.operaciones.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.EntradaInvalidaException;
import cl.bice.banca.empresas.servicio.exception.ErrorBaseDatosException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.BaseRequest;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.common.ValidadorUtil;
import cl.bice.banca.empresas.servicio.model.nominas.EstadoNomina;
import cl.bice.banca.empresas.servicio.model.nominas.EstadosNomLin;
import cl.bice.banca.empresas.servicio.model.nominas.NominaEnLinea;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.ValidaPoderRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.soap.ClienteGFS;
import cl.bice.banca.empresas.servicio.soap.ClienteGenericOperProg;
import cl.bice.banca.empresas.servicio.soap.ClienteNominasEnLinea;
import cl.bice.banca.empresas.servicio.soap.ClienteOperacionNomina;
import cl.bice.banca.empresas.servicio.soap.ClienteTEL23300;
import cl.bice.banca.empresas.servicio.util.BancaUtil;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.banca.empresas.servicio.util.FormateadorUtil;
import cl.bice.banca.empresas.servicio.util.MapperUtil;
import cl.bice.banca.empresas.servicio.util.OperacionesEmpresaUtil;
import cl.bice.genericoperprog.ws.ConsultaApoOpOut;
import cl.bice.genericoperprog.ws.FirmaOpDto;
import cl.bice.gfs.ws.GENERICFINANCIALSERVICEIN;
import cl.bice.gfs.ws.GENERICFINANCIALSERVICEOUT;
import cl.bice.gfs.ws.ObjectFactory;
import cl.bice.nominasenlinea.ws.ActEstadoNomOut;
import cl.bice.nominasenlinea.ws.RevAprobarNomLinOut;

@Service
public class NominasEnLineaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(NominasEnLineaService.class);

	/**
	 * Propiedades Externas.
	 */
	@Autowired
	Properties propiedadesExterna;

	@Autowired
	PortalOrawRepository portalOrawRepository;

	@Autowired
	@Qualifier("ClienteGenericOperProg")
	ClienteGenericOperProg clienteGenericOperProg;

	@Autowired
	@Qualifier("ClienteNominasEnLinea")
	ClienteNominasEnLinea clienteNominasEnLinea;

	@Autowired
	ValidacionService validacionService;

	@Autowired
	OperacionesEmpresaService operacionesEmpresaService;

	@Autowired
	EstadoService estadoService;

	@Autowired
	EmpresasService empresasService;

	@Autowired
	@Qualifier("ClienteTEL23300")
	ClienteTEL23300 clienteTEL23300;

	@Autowired
	@Qualifier("ClienteGFS")
	ClienteGFS clienteGFS;

	@Autowired
	@Qualifier("ClienteOperacionNomina")
	ClienteOperacionNomina clienteOperacionNomina;
	
	@Autowired
	CorrelativoService correlativoService;

	@Autowired
	LbtrMXservice lbtrService;

	@Autowired
	TransaccionesController transaccionesController;

	/**
	 * Hashmap con el detalle de las nominas a liberar.
	 */
	private HashMap detalleNominasLiberar = null;

	/**
	 * Hashmap con los montos totales por cuenta.
	 */
	private HashMap montosNominaCuentas = new HashMap();

	/**
	 * Hashmap con los saldos de las cuentas.
	 */
	private HashMap saldosCuentas = new HashMap();

	/**
	 * Hashmap con la cuenta y si tiene saldo suficiente.
	 */
	private HashMap saldosSuficientes = new HashMap();

	private static final String NUM_OPER_PROG = "NUM_OPER_PROG";

	private static final String GLS_TRANSFERENCIA_REGISTRO_CORRELATIVO = "Liberacion de Nominas en Linea";

	public static final String COD_SERVICIO_CONTROL_CORRELATIVO = "5116";

	/**
	 * Metodo utilizado para validar hora de registro para el flujo de segunda firma
	 * en el servicio aprobar nomina -1 : error al ejecutar ws 0 : se ejecuta solo
	 * una firma 1 : error firmas simultaneas
	 * 
	 * @param id_sesion
	 * @param operacion
	 * @param firmante
	 * @return
	 * @throws BancaEmpresasException
	 */
	public int checkearFirmasSimultaneas(String operacion, String firmante) throws BancaEmpresasException {
		LOGGER.info("OperacionesEmpresaService checkearFirmasSimultaneas");

		long num;
		int resultado = 0;
		List<String> horaOtrasFirmas = new ArrayList();
		String fechahoraFirmaApoderado = "";
		String hora = "";
		String apoderado = "";

		ConsultaApoOpOut consultaApoOpOut = clienteGenericOperProg.consultarFirmasOperacion(operacion);
		List<FirmaOpDto> listaFirmasOpDto = consultaApoOpOut.getFirmaOpDto();
		LOGGER.info("OperacionesEmpresaService checkearFirmasSimultaneas: Rut_firmante[{}]", firmante);
		LOGGER.info("OperacionesEmpresaService checkearFirmasSimultaneas: Cantidad de firmas[{}] operacion[{}]",
				listaFirmasOpDto.size(), operacion);

		// 1.- Obtiene hora de cada firma asociada a la nomina
		if (!listaFirmasOpDto.isEmpty()) {
			for (FirmaOpDto firmaOpDto : listaFirmasOpDto) {

				apoderado = firmaOpDto.getRutApodeado();
				hora = firmaOpDto.getFecFirma();

				if (apoderado.equalsIgnoreCase(firmante)) {
					fechahoraFirmaApoderado = hora;
				} else {
					horaOtrasFirmas.add(hora);
				}
			}
		} else {
			resultado = -1;
		}

		if (-1 != resultado) {
			LOGGER.info("OperacionesEmpresaService checkearFirmasSimultaneas: fechahoraFirmaApoderado[{}]",
					fechahoraFirmaApoderado);
			// 3.- Inicia validacion entre firma actual y firmas anteriores - Comparar hora
			// de firmas
			for (String registroFirma : horaOtrasFirmas) {
				LOGGER.info("OperacionesEmpresaService checkearFirmasSimultaneas: Comparar firma Registrada[{}]",
						registroFirma);
				// 4.- Obtiene Differencia en segundos entre la firma actual y la firma anterior
				num = diferenciaEnSegundosFechasFirmasOperaciones(fechahoraFirmaApoderado, registroFirma);
				LOGGER.info("OperacionesEmpresaService checkearFirmasSimultaneas: Resultado diferenciaEnSegundos[{}]",
						num);
				// 5.- la diferencia entre la hora de registro de cada firma , debe ser mayor a
				// 5 segundos
				if (num < 5) {
					LOGGER.info(
							"OperacionesEmpresaService checkearFirmasSimultaneas: Registro invalido - Dos apoderados registran firma en el mismo segundo");
					return 1;
				}
			}
		}

		return resultado;
	}

	/**
	 * Metodo que calcula diferencia en segundos segun fechas entregas desde la
	 * TBL_APO_OP.
	 * 
	 * @param id_sesion
	 * @param firmaActual
	 * @param firma_op
	 * @return tiempo en segundos
	 */
	private long diferenciaEnSegundosFechasFirmasOperaciones(String fechaFirmaActual, String fechaFirmaOperacion) {

		try {

			long firmaA = Long.parseLong(
					BancaUtil.cambiarFormatoFecha(fechaFirmaActual, "yyyyMMddHHmmss", "dd-MM-yyyy HH:mm:ss"));
			long firmaOp = Long.parseLong(
					BancaUtil.cambiarFormatoFecha(fechaFirmaOperacion, "yyyyMMddHHmmss", "dd-MM-yyyy HH:mm:ss"));
			LOGGER.info(
					"OperacionesEmpresaService diferenciaEnSegundosFechasFirmasOperaciones: fechaFirmaActual[{}] fechaFirmaOperacion[{}]",
					fechaFirmaActual, fechaFirmaOperacion);
			long segundos = firmaA - firmaOp;
			return Math.abs(segundos);

		} catch (ParseException e) {
			LOGGER.error("OperacionesEmpresaService diferenciaEnSegundosFechasFirmasOperaciones: [{}]", e);
			return -1;
		}

	}

	/**
	 * Retorna las firmas de una nomina.
	 * 
	 * @param codNomina
	 * @return
	 * @throws BancaEmpresasException
	 */
	public List<Map<String, Object>> obtenerFirmasNominaEnLinea(String codNomina) throws BancaEmpresasException {
		List<Map<String, Object>> salida;

		Map<String, Object> params = new HashMap<>();
		params.put("P_COD_NOMINA", codNomina);
		params.put("P_FIRMAS", null);

		try {
			portalOrawRepository.obtenerFirmasNominaEnLinea(params);
			salida = (List<Map<String, Object>>) params.get("P_FIRMAS");

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return salida;
	}

	/**
	 * Retorna el nombre del tipo de nomina.
	 * 
	 * @param codNomina
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String obtenerNombreTipoNomina(int codNomina) throws BancaEmpresasException {
		String salida;

		Map<String, Object> params = new HashMap<>();
		params.put("P_COD_TIPONOMINAPERFIL", String.valueOf(codNomina));
		params.put("P_NOM_SERVICIO", null);

		try {
			portalOrawRepository.obtenerNombreTipoNomina(params);
			salida = (String) params.get("P_NOM_SERVICIO");

			if (null == salida) {
				throw new ErrorStoredProcedureException();
			}

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return salida;
	}

	/**
	 * Retorna los ruts que han firmado la nomina en linea.
	 * 
	 * @param codNomina
	 * @param rutFirmante
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String obtenerRutsFirmantesNominaEnLinea(String codNomina, String rutFirmante)
			throws BancaEmpresasException {
		try {
			StringBuilder resultado = new StringBuilder();
			String separador = "";
			List<Map<String, Object>> salidaSP = obtenerFirmasNominaEnLinea(codNomina);

			for (Map<String, Object> mapa : salidaSP) {
				String rutApoderado = MapperUtil.validaRespuesta(mapa.get("RUT_APODERADO"), false);
				rutApoderado = FormateadorUtil.rutSinCero(rutApoderado);
				if (!rutFirmante.trim().equals(rutApoderado.trim())) {
					resultado.append(separador).append(rutApoderado);
					separador = ",";
				}
			}

			if ("".equals(resultado.toString().trim()))
				resultado.append(rutFirmante);
			else
				resultado.append(",").append(rutFirmante);

			return resultado.toString();
		} catch (ErrorStoredProcedureException e) {
			throw new ErrorBaseDatosException(e);
		}
	}

	/**
	 * Recibe una lista de n&uacute;meros de nominas y retorna una lista de objetos
	 * de la clase NominaEnLinea.
	 * 
	 * @param listaCodNominas
	 * @return
	 * @throws BancaEmpresasException
	 */
	public List<NominaEnLinea> obtenerListaNominasEnLinea(List<String> listaCodNominas) throws BancaEmpresasException {
		List<NominaEnLinea> resultado = new ArrayList<>();
		for (String codNominaStr : listaCodNominas) {
			NominaEnLinea nomina = obtenerDetalleNomina(Integer.parseInt(codNominaStr));
			resultado.add(nomina);
		}
		return resultado;
	}

	/**
	 * Recibe un n&uacute;mero de n&oacute;mina y retorna un objeto de la clase
	 * NominaEnLinea.
	 * 
	 * @param codNomina
	 * @return
	 * @throws BancaEmpresasException
	 */
	public NominaEnLinea obtenerDetalleNomina(int codNomina) throws BancaEmpresasException {
		LOGGER.info("NominasEnLineaService obtenerDetalleNomina: [{}]", codNomina);
		NominaEnLinea nomina = null;

		Map<String, Object> params = new HashMap<>();
		params.put("P_COD_NOMINA", String.valueOf(codNomina));
		params.put("P_NOMINA", null);
		params.put("P_ESTADOS", null);

		try {
			portalOrawRepository.obtenerNominaEnLinea(params);
			List<Map<String, Object>> salidaNominas = (List<Map<String, Object>>) params.get("P_NOMINA");
			List<Map<String, Object>> salidaEstados = (List<Map<String, Object>>) params.get("P_ESTADOS");

			if ((null == salidaNominas) || (null == salidaEstados)) {
				LOGGER.error("NominasEnLineaService obtenerDetalleNomina: salidaNominas o salidaEstados null");
				throw new ErrorStoredProcedureException();
			} else if (salidaNominas.isEmpty() || salidaEstados.isEmpty()) {
				LOGGER.error("NominasEnLineaService obtenerDetalleNomina: salidaNominas o salidaEstados vacío");
				throw new NoEncontradoException();
			} else {
				Map<String, Object> map = salidaNominas.get(0);
				if (null != map) {
					nomina = new NominaEnLinea();
					nomina.setCodNomina(codNomina);
					nomina.setCodTipoNomina(
							Integer.parseInt(MapperUtil.validaRespuesta(map.get("COD_TIPONOMINA"), false)));
					nomina.setRutEmpresa(MapperUtil.validaRespuesta(map.get("RUT_EMPRESA"), false));
					nomina.setNombreEmpresa(MapperUtil.validaRespuesta(map.get("NOM_EMPRESA"), false));
					nomina.setReferencia(MapperUtil.validaRespuesta(map.get("GLS_REFERENCIA"), false));
					nomina.setCuentaCargo(
							Long.parseLong(MapperUtil.validaRespuesta(map.get("NUM_CUENTA_CARGO"), false)));
					nomina.setMonto(Long.parseLong(MapperUtil.validaRespuesta(map.get("MTO_NOMINA"), false)));
					nomina.setTotalPagos(Integer.parseInt(MapperUtil.validaRespuesta(map.get("NUM_REGISTROS"), false)));
					nomina.setFechaCarga(MapperUtil.validaRespuesta(map.get("FEC_CARGA"), false));
					nomina.setFechaPago(MapperUtil.validaRespuesta(map.get("FEC_PAGO"), false));
					nomina.setNumOperProg(Long.parseLong(MapperUtil.validaRespuesta(map.get(NUM_OPER_PROG), false)));

					nomina.setEstados(new ArrayList());
					for (Map<String, Object> mapEstado : salidaEstados) {
						EstadoNomina estado = new EstadoNomina();
						estado.setRutUsuario(MapperUtil.validaRespuesta(mapEstado.get("RUT_USUARIO"), false));
						estado.setEstado(
								Integer.parseInt(MapperUtil.validaRespuesta(mapEstado.get("COD_ESTADO"), false)));
						estado.setGlosa(MapperUtil.validaRespuesta(mapEstado.get("GLS_ESTADO"), false));
						estado.setNombreUsuario(MapperUtil.validaRespuesta(mapEstado.get("NOM_USUARIO"), false));
						estado.setFecha(MapperUtil.validaRespuesta(mapEstado.get("FEC_ESTADO"), false));
						int actual = Integer
								.parseInt(MapperUtil.validaRespuesta(mapEstado.get("FLG_ESTADO_ACTUAL"), false));
						if (actual == 1) {
							nomina.setEstado(estado);
						} else {
							nomina.getEstados().add(estado);
						}
					}
					nomina.setTipoNomina(obtenerNombreTipoNomina(nomina.getCodTipoNomina()));
				}
			}

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return nomina;
	}

	/**
	 * Aprueba nominas en linea.
	 * 
	 * @param request
	 * @param estado
	 * @return
	 */
	public AprobarOperacionesResponse aprobarNominasEnLinea(AprobarOperacionesRequest request, Estado estado) {
		AprobarOperacionesResp resultado = null;

		if (estado.isEXITO()) {
			try {

				List<NominaEnLinea> listNominas = obtenerListaNominasEnLinea(request.getListaOperaciones());
				if (!validacionService.isPertenenciaNominaValida(request, request.getCodigoServicio(), listNominas))
					throw new RequestInvalidoException();

				resultado = actualizaEstadoNominas(request.getRut(), request.getRutEmpresa(),
						request.getCodigoServicio(), listNominas, request.getIp(), operacionesEmpresaService
								.obtenerInDatoFirma(request.getDispositivoFirma(), request.getCanal(), request.getIp()),
						request.getCanal());

			} catch (RequestInvalidoException | EntradaInvalidaException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado);
			} catch (ErrorBaseDatosException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_APROBAR_NOMINAS_EN_LINEA, estado);
			} catch (BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_NOMINAS_EN_LINEA, estado);
			}
		}

		AprobarOperacionesResponse response = new AprobarOperacionesResponse();
		response.setEstado(estado);
		response.setRespuesta(resultado);

		return response;
	}

	/**
	 * Aprueba nominas en linea actualizando sus estados.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param codigoServicio
	 * @param listNominas
	 * @param ipCliente
	 * @param datoFirma
	 * @return
	 * @throws BancaEmpresasException
	 */
	public AprobarOperacionesResp actualizaEstadoNominas(String rut, String rutEmpresa, String codigoServicio,
			List<NominaEnLinea> listNominas, String ipCliente, String datoFirma, String canal)
			throws BancaEmpresasException {

		List<String> operacionesNoAprobadas = new ArrayList<>();
		List<String> operacionesConFirmaParcial = new ArrayList<>();

		String srvBice = (ipCliente == null
				? propiedadesExterna.getProperty("servicios.nominas.registra.firma.ipdefault")
				: ipCliente);

		for (NominaEnLinea nomina : listNominas) {

			try {

				int estadoNomina = Constantes.NOMINA_EN_LINEA_ESTADO_APROBADA;
				String nombreUsuario = empresasService.obtenerNombreDelegado(rut);

				// obtener la lista de firmantes
				String firmas = obtenerRutsFirmantesNominaEnLinea(String.valueOf(nomina.getCodNomina()), rut);

				nomina.getEstado().setEstado(estadoNomina);
				nomina.getEstado().setNombreUsuario(nombreUsuario);
				nomina.getEstado().setRutUsuario(rut);

				// Valida firma - Control para validar si la nomina ya contiene una firma del
				// apoderado
				if (clienteGenericOperProg.existeFirma(String.valueOf(nomina.getNumOperProg()),
						nomina.getEstado().getRutUsuario())) {
					LOGGER.info("actualizaEstadoNominas(): El usuario {} ya firmo la nomina #{}", rut,
							nomina.getCodNomina());
					operacionesNoAprobadas.add(String.valueOf(nomina.getCodNomina()));
				} else {
					// Valida rut asociado a nomina - debe ser igual al rut de la empresa que se
					// encuentra operando
					if (nomina.getRutEmpresa().equals(rutEmpresa)) {
						estadoNomina = validarPoderesNominasEnLinea(nomina, rutEmpresa, codigoServicio, firmas);
						actualizarEstadoNominaEnLinea(estadoNomina, nomina, operacionesConFirmaParcial,
								operacionesNoAprobadas, srvBice, ipCliente, datoFirma);

					} else {
						// Nomina no pertenece al cliente actual
						LOGGER.info("actualizaEstadoNominas(): Nomina = {} no pertenece a la empresa {}",
								nomina.getCodNomina(), rutEmpresa);
						operacionesNoAprobadas.add(String.valueOf(nomina.getCodNomina()));
					}

				}

			} catch (BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				operacionesNoAprobadas.add(String.valueOf(nomina.getCodNomina()));
			}

		}

		Integer cantidadOperacionesNoAprobadas = 0;
		if (null != operacionesNoAprobadas && !operacionesNoAprobadas.isEmpty()) {
			cantidadOperacionesNoAprobadas = operacionesNoAprobadas.size();
		}

		AprobarOperacionesResp aprobarOperacionesResp = new AprobarOperacionesResp();
		aprobarOperacionesResp.setOperacionesNoAprobadas(operacionesNoAprobadas);
		aprobarOperacionesResp.setCantOperacionesNoAprobadas(cantidadOperacionesNoAprobadas);
		aprobarOperacionesResp.setOperacionesConFirmaParcial(operacionesConFirmaParcial);

		return aprobarOperacionesResp;
	}

	/**
	 * Valida poderes contra TPAG2000 para aprobar nominas en linea.
	 * 
	 * @param nomina
	 * @param rutEmpresa
	 * @param codigoServicio
	 * @param firmas
	 * @return
	 * @throws BancaEmpresasException
	 */
	private int validarPoderesNominasEnLinea(NominaEnLinea nomina, String rutEmpresa, String codigoServicio,
			String firmas) throws BancaEmpresasException {
		LOGGER.info("OperacionesEmpresaService validarPoderesNominasEnLinea");
		int estadoNomina = -1;

		ValidaPoderRequest validaPoderReq = new ValidaPoderRequest();
		validaPoderReq.setRutEmpresa(rutEmpresa);
		validaPoderReq.setCodigoServicio(codigoServicio);
		validaPoderReq.setMandatarios(firmas);
		validaPoderReq.setMonto(String.valueOf(nomina.getMonto()));
		validaPoderReq.setNroCuentaCargo(String.valueOf(nomina.getCuentaCargo()));
		validaPoderReq.setNumeroOperacion(String.valueOf(nomina.getNumOperProg()));

		int resultadoValidarPoder = operacionesEmpresaService.validaPoderTPAG2000(validaPoderReq);

		switch (resultadoValidarPoder) {
		case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR:
			estadoNomina = -1;
			break;
		case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL:
			estadoNomina = Constantes.NOMINA_EN_LINEA_ESTADO_APROBADA_PARCIAL;
			break;
		default:
			estadoNomina = Constantes.NOMINA_EN_LINEA_ESTADO_APROBADA;
			LOGGER.debug("OperacionesEmpresaService validarPoderesNominasEnLinea codNomina [{}] con firma completa",
					nomina.getCodNomina());
			break;
		}
		LOGGER.info(
				"OperacionesEmpresaService validarPoderesNominasEnLinea:  codNomina[{}] numOperProg[{}] estadoNomina[{}]",
				nomina.getCodNomina(), nomina.getNumOperProg(), estadoNomina);
		return estadoNomina;
	}

	/**
	 * Encargado de actualizar el estado de las nominas en linea en el proceso de
	 * aprobaci&oacute;n.
	 * 
	 * @param estadoNomina
	 * @param nomina
	 * @param operacionesConFirmaParcial
	 * @param operacionesNoAprobadas
	 * @param srvBice
	 * @param ipCliente
	 * @param datoFirma
	 * @throws BancaEmpresasException
	 */
	private void actualizarEstadoNominaEnLinea(int estadoNomina, NominaEnLinea nomina,
			List<String> operacionesConFirmaParcial, List<String> operacionesNoAprobadas, String srvBice,
			String ipCliente, String datoFirma) throws BancaEmpresasException {
		if (estadoNomina != -1) {
			nomina.getEstado().setEstado(estadoNomina);

			ActEstadoNomOut actEstadoNomOut = clienteNominasEnLinea.actualizarNominaEnLinea(nomina.getCodNomina(),
					nomina.getEstado().getRutUsuario(), nomina.getEstado().getNombreUsuario(), estadoNomina,
					Constantes.NOMINAS_EN_LINEA_NO_REVERSA_ESTADO, srvBice, ipCliente, datoFirma);

			if (1 != actEstadoNomOut.getCodEstado()) {
				operacionesNoAprobadas.add(String.valueOf(nomina.getCodNomina()));
			} else {
				if (Constantes.NOMINA_EN_LINEA_ESTADO_APROBADA_PARCIAL == estadoNomina) {
					operacionesConFirmaParcial.add(String.valueOf(nomina.getCodNomina()));
				} else if (Constantes.NOMINA_EN_LINEA_ESTADO_APROBADA == estadoNomina) {
					LOGGER.info("actualizaEstadoNominas(): usuario {} Nomina #{} resultado: Firma completa",
							nomina.getEstado().getRutUsuario(), nomina.getCodNomina());
				}
				// Valida hora de firma - Control firma simultanea
				int resultadoFirmaSimultanea = checkearFirmasSimultaneas(String.valueOf(nomina.getNumOperProg()),
						nomina.getEstado().getRutUsuario());
				LOGGER.info("actualizaEstadoNominas(): checkearFirmasSimultaneas - usuario {} Nomina #{} resultado: {}",
						nomina.getEstado().getRutUsuario(), nomina.getCodNomina(), resultadoFirmaSimultanea);

				if (resultadoFirmaSimultanea == 1) {
					RevAprobarNomLinOut revAprobarNomLinOut = clienteNominasEnLinea.reversaAprobacionNominaEnLinea(
							nomina.getCodNomina(), nomina.getEstado().getRutUsuario(), nomina.getEstado().getEstado());

					LOGGER.info("actualizaEstadoNominas(): Reversa - Nomina #{} estadonominareversa: {}",
							nomina.getCodNomina(), (1 != revAprobarNomLinOut.getCodEstado()));
				}
			}
		} else {
			operacionesNoAprobadas.add(String.valueOf(nomina.getCodNomina()));
		}
	}

	/**
	 * Recibe una lista de n&uacute;eros de n&oacute;minas en linea y retorna una
	 * lista que contiene los numOperProg correspondientes a cada n&uacute;ero de
	 * n&oacute;mina.
	 * 
	 * @param listCodNominas
	 * @return
	 * @throws BancaEmpresasException
	 */
	public List<String> obtenerListNumOperProgNominas(List<String> listCodNominas) throws BancaEmpresasException {
		List<String> resultado = null;
		List<NominaEnLinea> listNominas = obtenerListaNominasEnLinea(listCodNominas);
		if (null != listNominas && !listNominas.isEmpty()) {
			resultado = new ArrayList<>();
			for (NominaEnLinea nomina : listNominas) {
				resultado.add(String.valueOf(nomina.getNumOperProg()));
			}
		}
		return resultado;
	}

	/**
	 * Obtiene el monto total de todas las nominas de la lista que recibe este
	 * metodo.
	 * 
	 * @param listNominas
	 * @return Monto total de nominas.
	 */
	public long obtenerMontoTotalNominas(List<NominaEnLinea> listNominas) throws BancaEmpresasException {
		long resultado = 0;
		try {
			for (NominaEnLinea nomina : listNominas) {
				resultado = resultado + nomina.getMonto();
			}
		} catch (NumberFormatException e) {
			throw new BancaEmpresasException(e.getMessage());
		}

		return resultado;
	}

	/**
	 * Obtiene los saldos de las cuentas y los montos totales por cuenta.
	 * 
	 * @param listCodNominas Codigos de nominas a liberar.
	 * @throws BancaEmpresasException
	 */
	private void obtenerSaldosYMontosCuentas(List<String> listCodNominas) throws BancaEmpresasException {

		detalleNominasLiberar = new HashMap();
		for (String codNominaStr : listCodNominas) {
			int codNomina = Integer.parseInt(codNominaStr);

			NominaEnLinea nomina = null;
			try {
				nomina = obtenerDetalleNomina(codNomina);
			} catch (ErrorStoredProcedureException | NoEncontradoException e) {
				throw new ErrorBaseDatosException();
			}

			detalleNominasLiberar.put(String.valueOf(nomina.getCodNomina()), nomina);

			// Totaliza los montos por cuenta
			if (montosNominaCuentas.containsKey(String.valueOf(nomina.getCuentaCargo()))) {
				Long suma = (Long) montosNominaCuentas.get(String.valueOf(nomina.getCuentaCargo()));
				suma = new Long(suma.longValue() + nomina.getMonto());
				montosNominaCuentas.put(String.valueOf(nomina.getCuentaCargo()), suma);
			} else {
				montosNominaCuentas.put(String.valueOf(nomina.getCuentaCargo()), new Long(nomina.getMonto()));
			}
			// Obtiene los saldos de las cuentas
			if (!saldosCuentas.containsKey(String.valueOf(nomina.getCuentaCargo()))) {
				long saldoCuenta = -1;
				try {
					saldoCuenta = clienteTEL23300.obtenerSaldoCuentaNomina(String.valueOf(nomina.getCuentaCargo()));
				} catch (BancaEmpresasException e) {
					LOGGER.error("ERROR. No se pudo obtener el saldo de la cuenta [{}] para la nómina en linea [{}]: ",
							nomina.getCuentaCargo(), nomina.getCodNomina(), e);
				}

				saldosCuentas.put(String.valueOf(nomina.getCuentaCargo()), new Long(saldoCuenta));
			}
		}

		Set cuentas = saldosCuentas.keySet();
		Iterator iterator = cuentas.iterator();
		while (iterator.hasNext()) {
			String cuenta = (String) iterator.next();
			Long saldo = (Long) saldosCuentas.get(cuenta);
			Long montoTotal = (Long) montosNominaCuentas.get(cuenta);

			if (saldo.longValue() >= montoTotal.longValue()) {
				saldosSuficientes.put(cuenta, Boolean.TRUE);
			} else {
				saldosSuficientes.put(cuenta, Boolean.FALSE);
			}
		}
	}

	/**
	 * Elimina en la base de datos el registro de apoderado al liberar nomina en
	 * linea.
	 * 
	 * @param nomina Nomina en proceso.
	 * @return Boolean que indica si se elimina registro.
	 */
	public boolean eliminarEstadoNominaEnLinea(int codNomina, int estado, String rutUsuario, int flgActual)
			throws BancaEmpresasException {
		LOGGER.info("NominasEnLineaService eliminarEstadoNominaEnLinea: codNomina[{}] estado[{}]", codNomina, estado);
		String resultado = null;

		Map<String, Object> params = new HashMap<>();
		params.put("P_COD_NOM", String.valueOf(codNomina));
		params.put("P_COD_ESTADO", String.valueOf(estado));
		params.put("P_RUT_USUARIO", rutUsuario);
		params.put("P_FLG_ESTADO", String.valueOf(flgActual)); // 1:Actual - 0:Anterior
		params.put("P_RESULTADO", null);
		params.put("P_MSG_RESULT", null);

		try {
			portalOrawRepository.eliminarEstadoNominaEnLinea(params);

			resultado = (String) params.get("P_RESULTADO");

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if (null == resultado) {
			throw new NoEncontradoException();
		}

		return ("1".equals(resultado));
	}

	/**
	 * Registra en la base de datos el cargo a la cuenta de la nomina
	 * 
	 * @param nomina
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean registrarCargoNominasEnLinea(NominaEnLinea nomina) throws BancaEmpresasException {
		LOGGER.info("NominasEnLineaService registrarCargoNominasEnLinea: codNomina[{}] rutEmpresa[{}]",
				nomina.getCodNomina(), nomina.getRutEmpresa());
		String resultado = null;

		Map<String, Object> params = new HashMap<>();
		params.put("P_COD_NOMINA", String.valueOf(nomina.getCodNomina()));
		params.put("P_COD_REGISTRO", String.valueOf(0));
		params.put("P_TITULAR", nomina.getNombreEmpresa());
		params.put("RUT_TITULAR", nomina.getRutEmpresa());
		params.put("P_CUENTA", String.valueOf(nomina.getCuentaCargo()));
		params.put("P_MONTO", String.valueOf(nomina.getMonto()));
		params.put("P_TIPO_TRX", String.valueOf(Constantes.NOMINAS_EN_LINEA_TIPO_OPERACION_CARGO));
		params.put("P_ESTADO_TRX", "1");
		params.put("P_COD_BANCO", Constantes.COD_BANCO_BICE_28);
		params.put("P_RESULTADO", null);

		try {
			portalOrawRepository.registrarCargoNominasEnLinea(params);

			resultado = (String) params.get("P_RESULTADO");

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if (null == resultado) {
			throw new NoEncontradoException();
		}

		return ("1".equals(resultado));
	}

	public List<EstadosNomLin> obtenerEstadosNominasEnLinea(int codNomina, int estado) throws BancaEmpresasException {
		LOGGER.info("NominasEnLineaService obtenerEstadosNominasEnLinea: codNomina[{}] estado[{}]", codNomina, estado);
		List<Map<String, Object>> salidaSP;

		Map<String, Object> params = new HashMap<>();
		params.put("P_COD_NOMINA", String.valueOf(codNomina));
		params.put("P_ESTADO", String.valueOf(estado));
		params.put("v_COD_RESULT", null);
		params.put("v_MSG_RESULT", null);
		params.put("v_result", null);

		try {
			portalOrawRepository.obtenerEstadosNominasEnLinea(params);

			salidaSP = (List<Map<String, Object>>) params.get("v_result");

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if (!MapperUtil.isSalidaSPValida(salidaSP)) {
			throw new NoEncontradoException();
		}

		return generarListaEstadosNominasEnLinea(salidaSP);
	}

	private List<EstadosNomLin> generarListaEstadosNominasEnLinea(List<Map<String, Object>> lista) {
		List<EstadosNomLin> resultado = new ArrayList<>();
		for (Map<String, Object> mapa : lista) {
			EstadosNomLin estadoNomLin = new EstadosNomLin();
			estadoNomLin.setCodEstado(MapperUtil.validaRespuesta(mapa.get("COD_ESTADO"), false));
			estadoNomLin.setCodNomina(MapperUtil.validaRespuesta(mapa.get("COD_NOMINA"), false));
			estadoNomLin.setFecEstado(MapperUtil.validaRespuesta(mapa.get("FEC_ESTADO"), false));
			estadoNomLin.setFlgEstadoActual(MapperUtil.validaRespuesta(mapa.get("FLG_ESTADO_ACTUAL"), false));
			estadoNomLin.setNomUsuario(MapperUtil.validaRespuesta(mapa.get("NOM_USUARIO"), false));
			estadoNomLin.setRutUsuario(MapperUtil.validaRespuesta(mapa.get("RUT_USUARIO"), false));

			resultado.add(estadoNomLin);
		}

		return resultado;
	}

	/**
	 * Valida estado liberado de nomina y verifica doble ejecucion.
	 * 
	 * @param nomina Nomina a ser liberada
	 * @return Boolean que indica si la nomina se proceso en doble instancia.
	 * @throws BancaEmpresasException
	 */
	private int validaEstadoLiberar(NominaEnLinea nomina) throws BancaEmpresasException {
		LOGGER.info("NominasEnLineaService validaEstadoLiberar");
		int controlLiberar = -1; // 0: OK ; 1: Doble Liberar ; -1 Error Consulta
		int cont = 0;
		int flgEstado = 0;
		String usuario = nomina.getEstado().getRutUsuario();
		// 1.- Obtengo registros EDSNOM, estado 4
		List<EstadosNomLin> listEstados = obtenerEstadosNominasEnLinea(nomina.getCodNomina(),
				Constantes.NOMINA_EN_LINEA_ESTADO_LIBERADA);

		if (!listEstados.isEmpty()) {
			for (EstadosNomLin estado : listEstados) {
				LOGGER.info("NominasEnLineaService validaEstadoLiberar: codNomina[{}] rutUsuario[{}] fecEstado[{}]",
						estado.getCodNomina(), estado.getRutUsuario(), estado.getFecEstado());
				// Obtiene flg de estado asociado al rut usuario
				if (usuario.equals(estado.getRutUsuario())) {
					flgEstado = Integer.parseInt(estado.getFlgEstadoActual());
				}
				cont++;
			}
			// 2.- Si retorna mas de un registro, se valida campo FEC_ESTADO y obtener
			// diferencias de registros
			if (cont > 1) {
				if (flgEstado == 1) {
					// 3.- Si tiene el estado mas reciente, se elimina registro
					if (eliminarEstadoNominaEnLinea(nomina.getCodNomina(), Constantes.NOMINA_EN_LINEA_ESTADO_LIBERADA,
							usuario, 1)) {
						controlLiberar = 1;
					} else {
						controlLiberar = -1;
					}
				}
			} else {
				controlLiberar = 0;
			}
		}

		return controlLiberar;
	}

	public boolean validarMaxFechaLiberarNomLin(int codNomina) throws BancaEmpresasException {
		LOGGER.info("NominasEnLineaService validarMaxFechaLiberarNomLin: codNomina[{}]", codNomina);
		String codResult = null;

		Map<String, Object> params = new HashMap<>();
		params.put("I_NUM_NOMINA", String.valueOf(codNomina));
		params.put("O_COD_RESULT", null);

		try {
			portalOrawRepository.validarMaxFechaLiberarNomLin(params);

			codResult = (String) params.get("O_COD_RESULT");

		} catch (SQLException e) {
			LOGGER.info(
					"NominasEnLineaService validarMaxFechaLiberarNomLin: Error al validar fecha para liberar nomina");
			throw new ErrorStoredProcedureException(e);
		}

		if (null == codResult) {
			LOGGER.info(
					"NominasEnLineaService validarMaxFechaLiberarNomLin: Error al validar fecha para liberar nomina");
			throw new NoEncontradoException();
		}

		return ("0".equals(codResult));
	}

	public String obtenerFechaContableNomLin(final String vTRX) throws BancaEmpresasException {
		LOGGER.info("NominasEnLineaService validarMaxFechaLiberarNomLin");
		Date fechaContable = null;
		String fecha;

		Map<String, Object> params = new HashMap<>();
		params.put("P_TRX", vTRX);
		params.put("P_FECHA", null);

		try {
			portalOrawRepository.obtenerFechaContableNomLin(params);

			fechaContable = (Date) params.get("P_FECHA");

			if (null == fechaContable) {
				throw new NoEncontradoException();
			}

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			fecha = df.format(fechaContable);

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return fecha;
	}

	public Properties obtenerParametrosNomLin() throws BancaEmpresasException {
		LOGGER.info("NominasEnLineaService obtenerParametrosNomLin");
		Properties parametros = new Properties();
		List<Map<String, Object>> salidaSP = null;

		Map<String, Object> params = new HashMap<>();
		params.put("P_PARAMETROS", null);

		try {
			portalOrawRepository.obtenerParametrosNomLin(params);

			salidaSP = (List<Map<String, Object>>) params.get("P_PARAMETROS");

			if (null == salidaSP) {
				throw new NoEncontradoException();
			} else {
				for (Map<String, Object> map : salidaSP) {
					parametros.put(map.get("NOM_PARAMETRO"), map.get("VAL_PARAMETRO"));
				}
			}

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return parametros;
	}

	/**
	 * Realiza el cargo a la cuenta de la nomina.
	 * 
	 * @param nomina Nomina a ser liberada
	 * @return Boolean que indica si se realizo correctamente el cargo en GFS.
	 */
	private boolean realizarCargoCuentaNomina(NominaEnLinea nomina, BaseRequest base) {

		LOGGER.info("Realizando el cargo a la cuenta {} por el monto de {}", nomina.getCuentaCargo(),
				nomina.getMonto());
		boolean cargoCuenta = false;
		ObjectFactory objectFactoryGFS = new ObjectFactory();
		GENERICFINANCIALSERVICEIN in = objectFactoryGFS.createGENERICFINANCIALSERVICEIN();
		try {
			Properties parametrosNomLin = obtenerParametrosNomLin();

			in.setCANAL(Constantes.CANAL_INTERNET);
			in.setSUCURSAL(Constantes.SUCURSAL);
			in.setMODOTRX(Constantes.MODOTRX_DIRECTA);
			in.setMODOINVOCACION(Constantes.MODO_INVOCACION_SIMPLE);
			in.setRUTCLIENTE(nomina.getRutEmpresa());

			String traceNumber = System.currentTimeMillis() + (nomina.getCodNomina() + "");
			in.setTRACENUMBER(traceNumber + nomina.getCodNomina());

			String vTRX = parametrosNomLin.getProperty(Constantes.GFS_PROPERTY_FECHA_CONTABLE_VTRX);

			if (Boolean.valueOf(propiedadesExterna.getProperty("flag.fechacontable"))) {
				in.setFECHACONTABLE(propiedadesExterna.getProperty("flag.fechacontable.fecha"));
			} else {
				in.setFECHACONTABLE(obtenerFechaContableNomLin(vTRX));
			}
			in.setMONEDACARGO(Constantes.MONEDACLP);
			in.setBANCOCARGO(Constantes.COD_BANCO_BICE_028);
			in.setCUENTACARGO(String.valueOf(nomina.getCuentaCargo()));
			in.setTIPOCUENTACARGO(Constantes.COD_TIPO_CTA);
			in.setMONTOCARGO(String.valueOf(nomina.getMonto()));
			in.setRUTABONO("");
			in.setNOMBREABONO("");
			in.setMONEDAABONO("");
			in.setCUENTABONO("");
			in.setTIPOCUENTABONO("");
			in.setIDSERVICIO(parametrosNomLin.getProperty(Constantes.GFS_PROPERTY_ID_SERVICIO_CARGO));

			String referencia = "";
			String refAux = nomina.getReferencia();
			String fechaHoy = empresasService.fechaHoy("dd-MM-yyyy HH:mm:ss");
			if (refAux != null && !refAux.equals("")) {
				referencia = " (REF:" + refAux + ", " + fechaHoy + ")";
			}

			in.setREFERENCIA(referencia);
			in.setDOCCARGO(nomina.getCodNomina() + "");
			in.setDOCABONO(nomina.getCodNomina() + "");

			GENERICFINANCIALSERVICEOUT respGFS = null;
			ValidadorUtil validadorUtil = clienteGFS.call(in, base);
			if (validadorUtil.isResultado()) {
				respGFS = (GENERICFINANCIALSERVICEOUT) validadorUtil.getObjetoRespuesta();
				LOGGER.info("Respuesta de GFS: {}", respGFS.getESTADO());
				if (Constantes.GFS_CODIGO_RETORNO_APROBACION_OK.equals(respGFS.getESTADO())) {
					cargoCuenta = true;
				} else {
					in.setMODOTRX(Constantes.MODOTRX_REVERSA);
					validadorUtil = clienteGFS.call(in, base);
					if (validadorUtil.isResultado()) {
						respGFS = (GENERICFINANCIALSERVICEOUT) validadorUtil.getObjetoRespuesta();
						LOGGER.info("Respuesta reversa de GFS: {}", respGFS.getESTADO());
					} else {
						LOGGER.info("Error en reversa de GFS: codigoEstado[{}] glosaEstado[{}]",
								validadorUtil.getEstado().getCodigoEstado(),
								validadorUtil.getEstado().getGlosaEstado());
					}

				}
			} else {
				LOGGER.info("Error GFS: codigoEstado[{}] glosaEstado[{}]", validadorUtil.getEstado().getCodigoEstado(),
						validadorUtil.getEstado().getGlosaEstado());
			}
		} catch (Exception e) {
			LOGGER.info("Exception Error invocando a GFS al realizar el cargo" + " de la nomina "
					+ nomina.getCodNomina() + " por " + nomina.getMonto());
			in.setMODOTRX(Constantes.MODOTRX_REVERSA);
			ValidadorUtil validadorUtil = clienteGFS.call(in, base);
			if (validadorUtil.isResultado()) {
				GENERICFINANCIALSERVICEOUT respGFS = (GENERICFINANCIALSERVICEOUT) validadorUtil.getObjetoRespuesta();
				LOGGER.info("Respuesta reversa de GFS: {}", respGFS.getESTADO());
			} else {
				LOGGER.info("Error en reversa de GFS: codigoEstado[{}] glosaEstado[{}]",
						validadorUtil.getEstado().getCodigoEstado(), validadorUtil.getEstado().getGlosaEstado());
			}
		}

		return cargoCuenta;
	}

	public LiberarOperacionesResponse liberarNominasEnLinea(LiberarOperacionesRequest request, Estado estado) {
		LiberarOperacionesResponse response = new LiberarOperacionesResponse();
		LiberarOperacionesResp resp = null;
		if (estado.isEXITO()) {
			try {
				String idCorrelativo = correlativoService.controlCorrelativo(request.getRutEmpresa(), request.getRut(), COD_SERVICIO_CONTROL_CORRELATIVO, GLS_TRANSFERENCIA_REGISTRO_CORRELATIVO);

				if (correlativoService.isControlCorrelativoCorrecto(idCorrelativo, COD_SERVICIO_CONTROL_CORRELATIVO)) {
					correlativoService.actualizarCorrelativo(idCorrelativo, COD_SERVICIO_CONTROL_CORRELATIVO,
							request.getListaOperaciones());

					obtenerParametrosNomLin();

					EstadoNomina estadoNomina = new EstadoNomina();
					estadoNomina.setRutUsuario(request.getRut());
					estadoNomina.setNombreUsuario(request.getNombreApoderado());
					estadoNomina.setEstado(Constantes.NOMINA_EN_LINEA_ESTADO_LIBERADA);

					obtenerSaldosYMontosCuentas(request.getListaOperaciones());
					String inDatoFirma = operacionesEmpresaService.obtenerInDatoFirma(request.getDispositivoFirma(),
							request.getCanal(), request.getIp());

					resp = actualizarEstadoNominasLiberar(estadoNomina, request, inDatoFirma);
				} else {
					LOGGER.error("liberarNominasEnLinea: control correlativo incorrecto");
					throw new BancaEmpresasException();
				}

			} catch (ErrorBaseDatosException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_LIBERAR_NOMINAS_EN_LINEA, estado);
			} catch (WebServiceException | BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_LIBERAR_NOMINAS_EN_LINEA, estado);
			}
		}

		response.setEstado(estado);
		response.setRespuesta(resp);

		return response;
	}

	private LiberarOperacionesResp actualizarEstadoNominasLiberar(EstadoNomina estado,
			LiberarOperacionesRequest request, String datoFirma) throws BancaEmpresasException {

		Map<String, Map<String, String>> operacionesNoLiberadas = new HashMap<>();
		Map<String, Map<String, String>> operacionesLiberadas = new HashMap<>();
		List controlLiberar = new ArrayList<>();

		String srvBice = (request.getIp() == null
				? propiedadesExterna.getProperty("servicios.nominas.registra.firma.ipdefault")
				: request.getIp());

		for (String codNomina : request.getListaOperaciones()) {
			NominaEnLinea nomina = (NominaEnLinea) detalleNominasLiberar.get(codNomina);
			boolean estadoActualizacion = false;
			boolean estadoCargo = false;
			boolean errorSaldo = false;
			boolean flujoOK = false;
			boolean reversarEstadoNomina = false;
			int resControlLiberar = -1;
			// Primero, bloqueamos la transacción de la nómina
			LOGGER.info("Se validará bloqueo de transacción");
			boolean lockTransaction = lockTransaction(nomina);
			if (!lockTransaction) {
				LOGGER.warn("Ya existe otra instancia de liberación de la nómina: [{}]", codNomina);
				throw new BancaEmpresasException("Ya existe otra instancia de liberación de la nómina");				
			}

			try {
				validarCampo2(String.valueOf(nomina.getNumOperProg()));
				try {
				String inDatoFirmaLiberar = operacionesEmpresaService.obtenerInDatoFirma("", request.getCanal(),
						request.getIp());
				String detalleInSoapCanal = Constantes.COD_CAMPO_CANAL_LIBERACION + "=" + inDatoFirmaLiberar + ";";
				clienteOperacionNomina.actualizarDetalleOperacion(detalleInSoapCanal,
						String.valueOf(nomina.getNumOperProg()));
			} catch (BancaEmpresasException e) {
				LOGGER.warn("Error al actualizar canal: [{}], nroOperacion: [{}]", request.getCanal(),
						nomina.getNumOperProg());
			}

			LOGGER.info(
					"NominasEnLineaService actualizarEstadoNominasLiberar: nomina.getRutEmpresa() = {}  - rutEmpresa = {}",
					nomina.getRutEmpresa(), request.getRutEmpresa());

			if (nomina.getEstado().getEstado() == Constantes.NOMINA_EN_LINEA_ESTADO_APROBADA
					&& nomina.getRutEmpresa().equals(request.getRutEmpresa())) {
				nomina.setEstado(estado);
				if (validarMaxFechaLiberarNomLin(nomina.getCodNomina())) {
					Boolean tieneSaldo = (Boolean) saldosSuficientes.get(String.valueOf(nomina.getCuentaCargo()));
					if (tieneSaldo.booleanValue()) {
						estadoActualizacion = actualizarEstadoNomLinLiberar(nomina, srvBice, request.getIp(), datoFirma,
								false);
						resControlLiberar = validarEstadoLiberarNomina(nomina); // 0: OK ; 1: Doble Liberar ; -1 Error
																				// Consulta

						if (estadoActualizacion && resControlLiberar == 0) {
							estadoCargo = realizarCargoCuentaNomina(nomina, request);
							// Valida respuesta GFS (Cargo)
							if (!estadoCargo) {
								// reversa estado nomina liberado a firmado
								reversarEstadoNomina = true;
								flujoOK = false;
								LOGGER.info(
										"NominasEnLineaService actualizarEstadoNominasLiberar: Reversa Cargo y Estado - nomina = {}",
										nomina.getCodNomina());
							} else {
								try {
									registrarCargoNominasEnLinea(nomina);
								} catch (BancaEmpresasException e) {
									LOGGER.info(
											"NominasEnLineaService actualizarEstadoNominasLiberar: error al invocar registrarCargoNominasEnLinea()");
								}
								flujoOK = true;
								LOGGER.info(
										"NominasEnLineaService actualizarEstadoNominasLiberar: Cargo y Nuevo estado OK - nomina = {}",
										nomina.getCodNomina());
							}
						} else {
							flujoOK = false;
							if (resControlLiberar == 1) {
								LOGGER.info(
										"NominasEnLineaService actualizarEstadoNominasLiberar: Control liberar simultanea de nomina = "
												+ nomina.getCodNomina());
								controlLiberar.add(nomina);
							} else if (resControlLiberar == -1) {
								LOGGER.info(
										"NominasEnLineaService actualizarEstadoNominasLiberar: Error en control liberar simultanea - se reversa estado de nomina = "
												+ nomina.getCodNomina());
								reversarEstadoNomina = true;

							} else {
								LOGGER.info(
										"NominasEnLineaService actualizarEstadoNominasLiberar: No se logra actualizar estado de nomina = {}",
										nomina.getCodNomina());
							}
						}
					} else {
						errorSaldo = true;
						flujoOK = false;
						// No tiene saldo
						LOGGER.info(
								"NominasEnLineaService actualizarEstadoNominasLiberar: No tiene saldo - rutEmpresa[{}] cuenta[{}]",
								nomina.getRutEmpresa(), nomina.getCuentaCargo());
					}
				} else {
					// No se pudo hacer el cargo a la cuenta
					flujoOK = false;
					LOGGER.info("NominasEnLineaService actualizarEstadoNominasLiberar: fecha no válida");
				}
			} else {
				flujoOK = false;
				LOGGER.info(
						"NominasEnLineaService actualizarEstadoNominasLiberar: Nomina no se encuentra disponible para liberar.");
			}

			if (reversarEstadoNomina) {
				// reversa estado nomina liberado a firmado
				estadoActualizacion = actualizarEstadoNomLinLiberar(nomina, srvBice, request.getIp(), datoFirma, true);
			}

			actualizarObjRespuesta(flujoOK, nomina, operacionesLiberadas, operacionesNoLiberadas, errorSaldo);

			} finally {
				// Desbloqueamos la nómina
				unLockTransaction(nomina);
			}
		}
		LOGGER.info("NominasEnLineaService actualizarEstadoNominasLiberar controlLiberar: {}", controlLiberar);

		LiberarOperacionesResp liberarOperacionesResp = new LiberarOperacionesResp();
		liberarOperacionesResp.setOperacionesLiberadas(operacionesLiberadas);
		liberarOperacionesResp.setOperacionesNoLiberadas(operacionesNoLiberadas);
		liberarOperacionesResp.setCantOperacionesLiberadas(operacionesLiberadas.size());
		liberarOperacionesResp.setCantOperacionesNoLiberadas(operacionesNoLiberadas.size());

		return liberarOperacionesResp;
	}

	public boolean lockTransaction(NominaEnLinea nomina) {
		TransactionRequest trxRequest = new TransactionRequest();
        trxRequest.setAction("Liberando nomina en linea");
		trxRequest.setRutEnterprise(nomina.getRutEmpresa());
		trxRequest.setIdTrx(String.valueOf(nomina.getCodNomina()));
		ResponseEntity<TransactionResponse> response = transaccionesController.empresasControllerTransactionLock(trxRequest);
		LOGGER.info("Resultado al bloquear la trx: [{}]", response.getBody());
		if (response.getStatusCode() == HttpStatus.OK) {
			return Objects.requireNonNull(response.getBody()).isSuccess();
		} else {
			return false;
		}
	}

	public boolean unLockTransaction(NominaEnLinea nomina) {
		TransactionRequest trxRequest = new TransactionRequest();
		trxRequest.setRutEnterprise(nomina.getRutEmpresa());
		trxRequest.setIdTrx(String.valueOf(nomina.getCodNomina()));
		ResponseEntity<TransactionResponse> response = transaccionesController.empresasControllerTransactionUnLock(trxRequest);
		LOGGER.info("Resultado al desbloquear la trx: [{}]", response.getBody());
		if (response.getStatusCode() == HttpStatus.OK) {
			return Objects.requireNonNull(response.getBody()).isSuccess();
		} else {
			return false;
		}
	}

	public void actualizarObjRespuesta(boolean flujoOK, NominaEnLinea nomina,
			Map<String, Map<String, String>> operacionesLiberadas,
			Map<String, Map<String, String>> operacionesNoLiberadas, boolean errorSaldo) {

		Map<String, String> mapOperacionResp = new HashMap<>();
		if (!flujoOK) {
			mapOperacionResp.put(Constantes.COD_ESTADO_LIBERACION,
					propiedadesExterna.getProperty(Constantes.COD_ESTADO_LIBERACION_NOK));
			mapOperacionResp.put(Constantes.ESTADO_LIBERACION,
					propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));

			if (errorSaldo) {
				mapOperacionResp.put(Constantes.GLOSA_ESTADO_LIBERACION,
						propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SALDO1));
			} else {
				mapOperacionResp.put(Constantes.GLOSA_ESTADO_LIBERACION,
						propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SISTEMA));
			}

			operacionesNoLiberadas.put(String.valueOf(nomina.getCodNomina()), mapOperacionResp);
		} else {
			// variables a retornar para operación de liberación exitosa
			mapOperacionResp.put(Constantes.COD_ESTADO_LIBERACION,
					String.valueOf(OperacionesEmpresaUtil.obtenerEstadoOperProgNomLin(nomina.getEstado().getEstado())));
			mapOperacionResp.put(Constantes.ESTADO_LIBERACION,
					propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_OK));
			mapOperacionResp.put(Constantes.GLOSA_ESTADO_LIBERACION,
					propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_OK));

			operacionesLiberadas.put(String.valueOf(nomina.getCodNomina()), mapOperacionResp);
		}
	}

	public boolean actualizarEstadoNomLinLiberar(NominaEnLinea nomina, String srvBice, String ipCliente,
			String datoFirma, boolean isReversarEstado) {
		LOGGER.info("NominasEnLineaService actualizarEstadoNomLinLiberar: codNomina[{}]", nomina.getCodNomina());
		boolean resp = false;
		String strLogError = "NominasEnLineaService actualizarEstadoNomLinLiberar: Error, no se logra actualizar estado de nomina: {}";
		int reversa = Constantes.NOMINAS_EN_LINEA_NO_REVERSA_ESTADO;
		if (isReversarEstado) {
			reversa = Constantes.NOMINAS_EN_LINEA_REVERSAR_LIBERAR;
			strLogError = "NominasEnLineaService actualizarEstadoNomLinLiberar: Error en reversa estado nomina de liberado a firmado: {}";
		}

		try {
			ActEstadoNomOut actEstadoNomOut = clienteNominasEnLinea.actualizarNominaEnLinea(nomina.getCodNomina(),
					nomina.getEstado().getRutUsuario(), nomina.getEstado().getNombreUsuario(),
					nomina.getEstado().getEstado(), reversa, srvBice, ipCliente, datoFirma);

			resp = (1 == actEstadoNomOut.getCodEstado());
		} catch (BancaEmpresasException e) {
			LOGGER.info(strLogError, e);
		}

		return resp;
	}

	public int validarEstadoLiberarNomina(NominaEnLinea nomina) {
		int resp = -1;
		try {
			resp = validaEstadoLiberar(nomina);
		} catch (BancaEmpresasException e) {
			LOGGER.info("NominasEnLineaService validarEstadoLiberarNomina: Error al validar estado nomina: ", e);
		}

		return resp;
	}

	/**
	 * Verifica si el campo 2 no existe o es nulo
	 * Si se cumple, entonces guarda valor "Nº" en el mismo
	 *
	 * @param numOperProg identificador de la tabla tbl_detalle_camp
	 */
	public void validarCampo2(String numOperProg) {
		LOGGER.info("NominasEnLineaService validarCampo2");
		if (!lbtrService.existeCampo2(numOperProg)) {
			operacionesEmpresaService.actualizarDetalleOperacion("2", "Nº", numOperProg, false);
		}
	}


}
