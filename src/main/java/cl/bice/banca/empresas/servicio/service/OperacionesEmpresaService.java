package cl.bice.banca.empresas.servicio.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.controller.SaldoController;
import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.EntradaInvalidaException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.bicecomex.ConsultaGeneral;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.common.UsuarioCliente;
import cl.bice.banca.empresas.servicio.model.common.ValidaPoderesResult;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.operaciones.ResumenOperacionesMoneda;
import cl.bice.banca.empresas.servicio.model.request.ListaOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.empresas.SaldoRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.ValidaPoderRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.ListarOperaciones;
import cl.bice.banca.empresas.servicio.model.response.Operacion;
import cl.bice.banca.empresas.servicio.model.response.base.ResponseBase;
import cl.bice.banca.empresas.servicio.model.response.base.Respuesta;
import cl.bice.banca.empresas.servicio.model.response.empresas.DetalleCampoValorTipoOperacion;
import cl.bice.banca.empresas.servicio.model.response.empresas.OperacionesAprobar;
import cl.bice.banca.empresas.servicio.model.response.empresas.ResumenOperaciones;
import cl.bice.banca.empresas.servicio.model.response.empresas.TipoOperaciones;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesResp;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.LiberarOperacionesResp;
import cl.bice.banca.empresas.servicio.model.response.operaciones.LiberarOperacionesResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.ValidacionPoderResp;
import cl.bice.banca.empresas.servicio.model.response.operaciones.ValidacionPoderResponse;
import cl.bice.banca.empresas.servicio.model.response.saldo.DetalleSaldoResp;
import cl.bice.banca.empresas.servicio.model.response.saldo.SaldoResp;
import cl.bice.banca.empresas.servicio.repository.BiceValidaPoderRepository;
import cl.bice.banca.empresas.servicio.repository.MdpRepository;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.soap.ClienteGenericOperProg;
import cl.bice.banca.empresas.servicio.soap.ClienteNominasEnLinea;
import cl.bice.banca.empresas.servicio.soap.ClienteOperacionNomina;
import cl.bice.banca.empresas.servicio.soap.ClienteTefWs;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.banca.empresas.servicio.util.FormateadorUtil;
import cl.bice.banca.empresas.servicio.util.MapperUtil;
import cl.bice.banca.empresas.servicio.util.OperacionesEmpresaUtil;
import cl.bice.genericoperprog.ws.ActualizaDetCampOut;
import cl.bice.genericoperprog.ws.ActualizaOpOut;
import cl.bice.genericoperprog.ws.ConsultaApoOpOut;
import cl.bice.genericoperprog.ws.FirmaOpDto;
import cl.bice.nominas.ws.ActualizaOpIn;
import cl.bice.nominas.ws.ParametrosVo;
import cl.bice.tef.ws.ObjectFactory;
import cl.bice.tef.ws.Tpag2000EntradaType;
import cl.bice.tef.ws.Tpag2000RequestType;
import cl.bice.tef.ws.Tpag2000ResponseType;

/**
 * Clase con métodos para manejar las operaciones de una empresa.
 * 
 * @author Tinet
 */
@Service
public class OperacionesEmpresaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionesEmpresaService.class);
	/**
	 * Propiedades Externas.
	 */
	@Autowired
	Properties propiedadesExterna;

	@Autowired
	PortalOrawRepository portalOrawRepository;

	@Autowired
	BiceValidaPoderRepository biceValidaPoderRepository;

	@Autowired
	ValoresCampoOperacionesService valoresCampoOperacionesService;

	@Autowired
	@Qualifier("ClienteTefWs")
	ClienteTefWs clienteTefWs;

	@Autowired
	@Qualifier("ClienteGenericOperProg")
	ClienteGenericOperProg clienteGenericOperProg;

	@Autowired
	@Qualifier("ClienteNominasEnLinea")
	ClienteNominasEnLinea clienteNominasEnLinea;

	@Autowired
	FacultadService facultadService;

	@Autowired
	ValidacionService validacionService;

	@Autowired
	EmpresasService empresasService;

	@Autowired
	ConsultarParametrosService consultarParametrosService;

	/**
	 * Cliente para operaciones del ws NominasPagosWs
	 */
	@Autowired
	@Qualifier("ClienteOperacionNomina")
	ClienteOperacionNomina clienteOperacionNomina;

	@Autowired
	MailService mailService;

	@Autowired
	EstadoService estadoService;

	@Autowired
	TefService tefService;

	@Autowired
	SaldoController saldoController;

	@Autowired
	CuentaService cuentaService;

	@Autowired
	MdpRepository mdpRepository;

	@Autowired
	ServiciosService serviciosService;
	
	@Autowired
	BiceComexService biceComexService;
	
	@Autowired
	LbtrMXservice lbtrMXservice;

	private static final String V_SALIDA = "v_SALIDA";

	private static final String NUM_OPER_PROG = "NUM_OPER_PROG";
	private static final String SP_VALIDA_PODER_FLAG_MANDATARIOS_FACULTADES_Y_MONTOS = "3";
	private static final String TIPO_ERROR_MAIL = "APPEMPRESA-ERR-ACTUALIZAAPROB";
	private static final String REQUEST_TPAG2000_CANAL = "IE";
	private static final String DISPOSITIVO_FIRMA_ESIGN = "ESIGN";
	private static final String FIRMA_FDA = "FDA";

	/**
	 * Obtiene el listado de operaciones por dia de empresa.
	 * 
	 * @param request
	 * @return
	 */
	public ResponseBase obtieneListaOperaciones(ListaOperacionesRequest request, Estado estado) {

		ResponseBase response = inicializaResponseBase(estado);

		if(!request.getCodigoServicio().equals(Constantes.CODIGO_SERVICIO_BICECOMEX)) {
			Map<String, Object> params = new HashMap<>();
			params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU.toUpperCase(), request.getRut());
			params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_CLI.toUpperCase(), request.getRutEmpresa());
			params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_COD_SERVICO.toUpperCase(), request.getCodigoServicio());

			ListarOperaciones respuesta = new ListarOperaciones();
			List<Operacion> operaciones;

			try {
				LOGGER.info("Ejecutando llamada a SP {} ", params);
				portalOrawRepository.obtieneListaOperacionesDia(params);
				LOGGER.debug("resultado BD = {}", params);

				List<Map<String, Object>> salida = (List<Map<String, Object>>) params
						.get(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA.toUpperCase());

				if (!MapperUtil.isSalidaSPValida(salida)) {
					estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_LISTA_OPERACIONES_DIA, estado);
				} else if (params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT.toUpperCase()) == null
						|| !"0".equals(params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT.toUpperCase())
								.toString().trim())) {
					throw new BancaEmpresasException();
				} else {
					operaciones = constuyeListadoOperaciones(request, salida);
					OperacionesEmpresaUtil operacionesEmpresaUtil = new OperacionesEmpresaUtil();
					List<List<Integer>> listaRangos = operacionesEmpresaUtil
							.obtenerArraySeparadores(propiedadesExterna.getProperty(
									"servicios.separadores.operacion.tipo.operacionesdia" + request.getCodigoServicio()));

					/**
					 * llamada a metodo comentada para fase 2 de ordenamiento y segmentacion en
					 * servicio List<MovimientosSalida> aasd =
					 * obtieneRespuestaFinal(listaRangos,operaciones);
					 **/
					respuesta.setOperaciones(operaciones);
					respuesta.setFiltros(listaRangos);
					response.getRespuesta().setAdditionalProperty("Operaciones", respuesta);
				}
			} catch (BancaEmpresasException | SQLException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_LISTA_OPERACIONES_DIA, estado);
			}
		}else {//Para BICECOMEX (cod. 1271) se obtienen las operaciones desde sql server

			ListarOperaciones respuesta = new ListarOperaciones();
			List<Operacion> operaciones;

			try {
				List<ConsultaGeneral> listaOperaciones = biceComexService.consultarOperacionesBiceComex(request.getRut(), request.getRutEmpresa(), "Todos", null, null);
				List<Map<String, Object>> salida = biceComexService.generarSalidaTipoSpDia(listaOperaciones);

				if (!MapperUtil.isSalidaSPValida(salida)) {
					estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_LISTA_OPERACIONES_DIA, estado);
				} else {
					operaciones = constuyeListadoOperaciones(request, salida);
					OperacionesEmpresaUtil operacionesEmpresaUtil = new OperacionesEmpresaUtil();
					List<List<Integer>> listaRangos = operacionesEmpresaUtil
							.obtenerArraySeparadores(propiedadesExterna.getProperty(
									"servicios.separadores.operacion.tipo.operacionesdia" + request.getCodigoServicio()));

					/**
					 * llamada a metodo comentada para fase 2 de ordenamiento y segmentacion en
					 * servicio List<MovimientosSalida> aasd =
					 * obtieneRespuestaFinal(listaRangos,operaciones);
					 **/
					respuesta.setOperaciones(operaciones);
					respuesta.setFiltros(listaRangos);
					response.getRespuesta().setAdditionalProperty("Operaciones", respuesta);
				}
			} catch (Exception e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_LISTA_OPERACIONES_DIA, estado);
			}
		}
		

		return response;
	}

	/**
	 * metodo que lee el cursor desde BD y lo envia a una lista TO para ser
	 * retornada
	 * 
	 * @param parametrosBD
	 * @return
	 */
	public List<Operacion> constuyeListadoOperaciones(ListaOperacionesRequest request, List<Map<String, Object>> parametrosBD) {
		
		if (request.getCodigoServicio().equals(Constantes.CODIGO_SERVICIO_TEF_MX)
				|| request.getCodigoServicio().equals(Constantes.CODIGO_SERVICIO_SPOTWEB)) {
			actualizarFormatoMontoOperacionListaMapOperacionesTefMxSpotWeb(request.getCodigoServicio(), parametrosBD);
		}
		
		List<Operacion> operaciones = new ArrayList<>();
		for (int i = 0; i < parametrosBD.size(); i++) {
			Map<String, Object> mov = parametrosBD.get(i);
			Operacion oper = new Operacion();
			oper.setNumeroOperacion(String.valueOf(mov.get(NUM_OPER_PROG)));
			oper.setCodigoServicio(String.valueOf(mov.get("COD_SERVICIO")));
			oper.setTipo(String.valueOf(mov.get("TIPO")));
			oper.setCampo1(String.valueOf(mov.get("CAMPO1")));
			oper.setValor1(String.valueOf(mov.get("VALOR1")));
			oper.setCampo2(String.valueOf(mov.get("CAMPO2")));
			oper.setValor2(String.valueOf(mov.get("VALOR2")));
			oper.setCampo3(String.valueOf(mov.get("CAMPO3")));
			oper.setValor3(String.valueOf(mov.get("VALOR3")));
			oper.setCampo4(String.valueOf(mov.get("CAMPO4")));
			oper.setValor4(String.valueOf(mov.get("VALOR4")));
			oper.setCampo5(String.valueOf(mov.get("CAMPO5")));
			oper.setValor5(String.valueOf(mov.get("VALOR5")));
			oper.setCampo6(String.valueOf(mov.get("CAMPO6")));
			oper.setValor6(String.valueOf(mov.get("VALOR6")));
			oper.setCampo7(String.valueOf(mov.get("CAMPO7")));
			oper.setValor7(String.valueOf(mov.get("VALOR7")));
			oper.setCampo8(String.valueOf(mov.get("CAMPO8")));
			oper.setValor8(String.valueOf(mov.get("VALOR8")));
			oper.setCampo9(String.valueOf(mov.get("CAMPO9")));
			oper.setValor9(String.valueOf(mov.get("VALOR9")));
			oper.setCampo10(String.valueOf(mov.get("CAMPO10")));
			oper.setValor10(String.valueOf(mov.get("VALOR10")));
			oper.setCampo11(String.valueOf(mov.get("CAMPO11")));
			oper.setValor11(String.valueOf(mov.get("VALOR11")));
			oper.setCampo12(String.valueOf(mov.get("CAMPO12")));
			oper.setValor12(String.valueOf(mov.get("VALOR12")));
			oper.setCampo13(String.valueOf(mov.get("CAMPO13")));
			oper.setValor13(String.valueOf(mov.get("VALOR13")));
			oper.setCampo14(String.valueOf(mov.get("CAMPO14")));
			oper.setValor14(String.valueOf(mov.get("VALOR14")));
			oper.setCampo15(String.valueOf(mov.get("CAMPO15")));
			oper.setValor15(String.valueOf(mov.get("VALOR15")));
			oper.setCampo16(String.valueOf(mov.get("CAMPO16")));
			oper.setValor16(String.valueOf(mov.get("VALOR16")));
			oper.setCampo17(String.valueOf(mov.get("CAMPO17")));
			oper.setValor17(String.valueOf(mov.get("VALOR17")));
			operaciones.add(oper);
		}
		return operaciones;
	}

	/**
	 * Metodo encargado de inicializar los valores por defecto de la respuesta.
	 * 
	 * @param request ConsultaNominasRequest
	 */
	private ResponseBase inicializaResponseBase(Estado estado) {
		ResponseBase response = new ResponseBase();
		response.setEstado(estado);
		Respuesta respuesta = new Respuesta();
		response.setRespuesta(respuesta);
		return response;
	}

	/**
	 * Inicializa el objeto de la clase ValidaPoderRequest.
	 * 
	 * @param aprobarOperacionesRequest
	 * @param nroOperacion
	 * @return
	 */
	public ValidaPoderRequest crearValidaPoderRequest(AprobarOperacionesRequest aprobarOperacionesRequest,
			String nroOperacion) {
		ValidaPoderRequest validaPoderRequest = new ValidaPoderRequest();
		validaPoderRequest.setCodigoServicio(aprobarOperacionesRequest.getCodigoServicio());
		validaPoderRequest.setCanal(aprobarOperacionesRequest.getCanal());
		validaPoderRequest.setDispositivo(aprobarOperacionesRequest.getDispositivo());
		validaPoderRequest.setIp(aprobarOperacionesRequest.getIp());
		validaPoderRequest.setNumeroOperacion(nroOperacion);
		validaPoderRequest.setOrigenLlamada(aprobarOperacionesRequest.getOrigenLlamada());
		validaPoderRequest.setRut(aprobarOperacionesRequest.getRut());
		validaPoderRequest.setRutEmpresa(aprobarOperacionesRequest.getRutEmpresa());
		validaPoderRequest.setSessionID(aprobarOperacionesRequest.getSessionID());
		validaPoderRequest.setToken(aprobarOperacionesRequest.getToken());

		return validaPoderRequest;
	}

	/**
	 * Valida si el rut del apoderado y el rut de la empresa tiene facultad
	 * especial. Si tiene facultad especial valida poderes contra el SP
	 * PR_VALIDACION_PODERES, si no tiene facultad especial valida poderes contra
	 * TPAG2000.
	 * 
	 * @param request
	 * @return si retorna 50 significa que es FIRMA COMPLETA si retorna 40 significa
	 *         que es FIRMA PARCIAL si retorna 30 significa que es SIN FIRMAR
	 * @throws BancaEmpresasException
	 */
	public int tienePoderEmpresa(ValidaPoderRequest request, MapOperaciones mapOperaciones)
			throws BancaEmpresasException {
		LOGGER.info("OperacionesEmpresaService tienePoderEmpresa: rut[{}] rutEmpresa[{}] numeroOperacion[{}]",
				request.getRut(), request.getRutEmpresa(), request.getNumeroOperacion());
		int resultado;

		String nroCuentaCargo;
		String monto;
		try {
			nroCuentaCargo = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones,
					request.getNumeroOperacion(), "NroCuentaCargo", true);
			monto = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, request.getNumeroOperacion(),
					"Monto", true);
		} catch (NoEncontradoException e) {
			throw new RequestInvalidoException(e);
		}

		if (null != nroCuentaCargo)
			nroCuentaCargo = nroCuentaCargo.replaceFirst("^0+(?!$)", ""); // le quito los ceros a la izquierda

		String codigoFacultad = facultadService.obtenerCodigoFacultad(request.getCodigoServicio());

		boolean existeFacultad = false;
		if (null != codigoFacultad) {
			existeFacultad = facultadService.existeFacultad(request.getRutEmpresa(), request.getRut(), nroCuentaCargo,
					Integer.parseInt(codigoFacultad));
		}

		request.setMandatarios(
				obtenerRutsMandatarios(request.getNumeroOperacion(), FormateadorUtil.rutSinCero(request.getRut())));
		request.setMonto(monto);
		request.setNroCuentaCargo(nroCuentaCargo);

		if (existeFacultad) {
			resultado = validaPoderSPPRValidacionPoderes(request, codigoFacultad);
		} else {
			resultado = validaPoderTPAG2000(request);
		}

		return resultado;
	}

	/**
	 * Valida poder contra TPAG2000
	 * 
	 * @param request
	 * @return si retorna 50 significa que es FIRMA COMPLETA si retorna 40 significa
	 *         que es FIRMA PARCIAL si retorna 30 significa que es SIN FIRMAR
	 * @throws BancaEmpresasException
	 */
	public int validaPoderTPAG2000(ValidaPoderRequest request) throws BancaEmpresasException {
		Tpag2000ResponseType tpag2000ResponseType = validarTPAG2000(createRequestTpag2000(request));
		return obtenerResultadoTpag2000(tpag2000ResponseType);
	}

	public int obtenerResultadoTpag2000(Tpag2000ResponseType tpag2000ResponseType) {
		int resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		if (tpag2000ResponseType.getCodigo().trim().equals(Constantes.SERVICIO_TPAG200_CODIGO_FIRMA_COMPLETA)
				&& tpag2000ResponseType.getSubCodigo().trim()
						.equals(Constantes.SERVICIO_TPAG200_SUB_CODIGO_FIRMA_COMPLETA)) {
			resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_FIRMA_COMPLETA;
		} else if (tpag2000ResponseType.getCodigo().trim().equals(Constantes.SERVICIO_TPAG200_CODIGO_FIRMA_PENDIENTE)
				&& tpag2000ResponseType.getSubCodigo().trim()
						.equals(Constantes.SERVICIO_TPAG200_SUB_CODIGO_FIRMA_CORRECTA)) {
			resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL;
		}
		return resultado;
	}

	/**
	 * Valida poder contra SP PR_VALIDACION_PODERES
	 * 
	 * @param request
	 * @param codigoFacultad
	 * @return si retorna 50 significa que es FIRMA COMPLETA si retorna 40 significa
	 *         que es FIRMA PARCIAL si retorna 30 significa que es SIN FIRMAR
	 * @throws ErrorStoredProcedureException
	 */
	public int validaPoderSPPRValidacionPoderes(ValidaPoderRequest request, String codigoFacultad)
			throws ErrorStoredProcedureException {
		int resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		Map<String, Object> paramsSalidaSPValidacionPoderes = validarPoder(request, codigoFacultad);
		if (paramsSalidaSPValidacionPoderes.get(Constantes.PARAMETRO_SP_VALIDACION_PODERES_RESPUESTA)
				.equals(Constantes.SP_VALIDACION_PODERES_RESPUESTA_SI)) {
			registraLogFacultad(request, paramsSalidaSPValidacionPoderes, codigoFacultad);
			if (paramsSalidaSPValidacionPoderes.get(Constantes.SP_VALIDACION_PODERES_CODIGORECHAZO)
					.equals(Constantes.SP_VALIDACION_PODERES_FIRMA_COMPLETA)) {
				resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_FIRMA_COMPLETA;
			} else if (paramsSalidaSPValidacionPoderes.get(Constantes.SP_VALIDACION_PODERES_CODIGORECHAZO)
					.equals(Constantes.SP_VALIDACION_PODERES_FIRMA_PARCIAL)) {
				resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL;
			}
		}

		return resultado;
	}

	/**
	 * Valida poder llamando al cliente del servicio TPAG2000.
	 * 
	 * @param validarPoderRequest
	 * @return objeto con la respuesta del servicio TPAG2000
	 * @throws BancaEmpresasException
	 */
	public Tpag2000ResponseType validarTPAG2000(Tpag2000RequestType tpag2000Request) throws BancaEmpresasException {
		try {
			Tpag2000ResponseType tpag2000ResponseType = clienteTefWs.validarTPAG2000(tpag2000Request);
			LOGGER.info("OperacionesEmpresaService validarTPAG2000: codigo[{}] subcodigo[{}]",
					tpag2000ResponseType.getCodigo(), tpag2000ResponseType.getSubCodigo());

			if (null == tpag2000ResponseType.getCodigo() || null == tpag2000ResponseType.getSubCodigo()) {
				throw new BancaEmpresasException("TPAG2000 código respuesta y/o subcódigo respuesta nulo");
			}

			return tpag2000ResponseType;
		} catch (NumberFormatException | BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new BancaEmpresasException();
		}
	}

	/**
	 * Inicializa el objeto ValidaPoderRequest para llamar al servcio TPAG2000.
	 * 
	 * @param validarPoderRequest
	 * @return ValidaPoderRequest
	 * @throws BancaEmpresasException
	 */
	private Tpag2000RequestType createRequestTpag2000(ValidaPoderRequest validarPoderRequest)
			throws BancaEmpresasException {
		try {

			String[] mandatarios = validarPoderRequest.getMandatarios().split(",");

			ObjectFactory objectFactory = new ObjectFactory();
			Tpag2000RequestType tpag2000Request = objectFactory.createTpag2000RequestType();
			Tpag2000EntradaType tpag2000EntradaType = objectFactory.createTpag2000EntradaType();
			tpag2000EntradaType.setCanal(REQUEST_TPAG2000_CANAL);
			tpag2000EntradaType.setCodigoServicio(Integer.parseInt(validarPoderRequest.getCodigoServicio()));
			tpag2000EntradaType.setRutCliente(validarPoderRequest.getRutEmpresa());
			tpag2000EntradaType.setCodigoTipoCtaCargo(
					Integer.parseInt(propiedadesExterna.getProperty("servicios.lbtr.codigo.producto")));
			tpag2000EntradaType.setCuentaCargo(Long.parseLong(validarPoderRequest.getNroCuentaCargo()));
			tpag2000EntradaType.setMoneda(Constantes.CODIGO_MONEDA_PESOS);
			tpag2000EntradaType.setMonto(Long.parseLong(validarPoderRequest.getMonto()));
			tpag2000EntradaType.setNumeroOperacion(Long.parseLong(validarPoderRequest.getNumeroOperacion()));
			tpag2000EntradaType.setRutApoderado1(FormateadorUtil.rellenarCero(mandatarios[0], 10));
			if (mandatarios.length >= 2)
				tpag2000EntradaType.setRutApoderado2(FormateadorUtil.rellenarCero(mandatarios[1], 10));
			else
				tpag2000EntradaType.setRutApoderado2("");
			if (mandatarios.length >= 3)
				tpag2000EntradaType.setRutApoderado3(FormateadorUtil.rellenarCero(mandatarios[2], 10));
			else
				tpag2000EntradaType.setRutApoderado3("");
			if (mandatarios.length >= 4)
				tpag2000EntradaType.setRutApoderado4(FormateadorUtil.rellenarCero(mandatarios[3], 10));
			else
				tpag2000EntradaType.setRutApoderado4("");

			tpag2000Request.setTpag2000Entrada(tpag2000EntradaType);

			return tpag2000Request;
		} catch (NumberFormatException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new BancaEmpresasException();
		}

	}

	/**
	 * Inicializa el map con los parámetros que debe recibir el SP
	 * POR_PAC_FACULTADES.POR_SP_REG_LOG_FACULTADES
	 * 
	 * @param validarPoderRequest
	 * @param paramsSalidaSPValidacionPoderes
	 * @param codigoFacultad
	 * @return map con los parámetros que debe recibir el SP
	 *         POR_PAC_FACULTADES.POR_SP_REG_LOG_FACULTADES
	 */
	private Map<String, Object> obtenerDatosRequestRegistraLogFacultad(ValidaPoderRequest validarPoderRequest,
			Map<String, Object> paramsSalidaSPValidacionPoderes, String codigoFacultad) {

		boolean firmaCompleta = paramsSalidaSPValidacionPoderes.get(Constantes.SP_VALIDACION_PODERES_CODIGORECHAZO)
				.equals(Constantes.SP_VALIDACION_PODERES_FIRMA_COMPLETA);
		boolean firmaParcial = paramsSalidaSPValidacionPoderes.get(Constantes.SP_VALIDACION_PODERES_CODIGORECHAZO)
				.equals(Constantes.SP_VALIDACION_PODERES_FIRMA_PARCIAL);

		Map<String, Object> resultado = new HashMap<>();
		resultado.put("p_COD_SERVICIO", validarPoderRequest.getCodigoServicio());
		resultado.put("p_NOM_APP", validarPoderRequest.getCanal());
		resultado.put("p_IP_USUARIO", validarPoderRequest.getIp());
		resultado.put("p_NOM_SRV", validarPoderRequest.getCanal());
		resultado.put("p_NUM_OPER_PROG", validarPoderRequest.getNumeroOperacion());
		resultado.put("p_RUT_CLIENTE", validarPoderRequest.getRutEmpresa());
		resultado.put("p_RUT_FIRMANTES", validarPoderRequest.getMandatarios());
		resultado.put("p_MONTO", validarPoderRequest.getMonto());
		resultado.put("p_CTA_CARGO", validarPoderRequest.getNroCuentaCargo());
		resultado.put("p_MONEDA", Constantes.CODIGO_MONEDA_PESOS);
		resultado.put("p_COD_FACULTAD", codigoFacultad);
		resultado.put("p_GLS_OUT",
				paramsSalidaSPValidacionPoderes.get(Constantes.SP_VALIDACION_PODERES_DESCRIPCIONRECHAZO));
		resultado.put("p_FIR_COMPLETA", firmaCompleta ? Constantes.SP_REG_LOG_FACULTADES_FIRMA_COMPLETA_SI
				: Constantes.SP_REG_LOG_FACULTADES_FIRMA_COMPLETA_NO);
		resultado.put("p_FIR_PARCIAL", firmaParcial ? Constantes.SP_REG_LOG_FACULTADES_FIRMA_PARCIAL_SI
				: Constantes.SP_REG_LOG_FACULTADES_FIRMA_PARCIAL_NO);
		resultado.put("p_COD_ESTADO",
				paramsSalidaSPValidacionPoderes.get(Constantes.SP_VALIDACION_PODERES_CODIGORECHAZO));
		return resultado;
	}

	/**
	 * Registra log invocando al SP POR_PAC_FACULTADES.POR_SP_REG_LOG_FACULTADES.
	 * Este método se llama solo cuando se posee facultad especial.
	 * 
	 * @param validarPoderRequest
	 * @param paramsSalidaSPValidacionPoderes
	 * @param codigoFacultad
	 * @throws ErrorStoredProcedureException
	 */
	public void registraLogFacultad(ValidaPoderRequest validarPoderRequest,
			Map<String, Object> paramsSalidaSPValidacionPoderes, String codigoFacultad)
			throws ErrorStoredProcedureException {
		try {
			Map<String, Object> params = obtenerDatosRequestRegistraLogFacultad(validarPoderRequest,
					paramsSalidaSPValidacionPoderes, codigoFacultad);

			LOGGER.info("Se registra en tabla Seguimiento");
			portalOrawRepository.registraLogFacultad(params);
			String codStatus = (String) params.get("p_COD_STATUS");
			String msgStatus = (String) params.get("p_MSG_STATUS");
			LOGGER.info("OperacionesEmpresaService registraLogFacultad: codStatus[{}] msgStatus[{}]", codStatus,
					msgStatus);
		} catch (SQLException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new ErrorStoredProcedureException();
		}
	}

	/**
	 * Obtiene los ruts que ya han firmado la operacion y los concatena con el rut
	 * que va a firmar la operación para luego poder consultar si todos estos rut
	 * completan la cantidad de firmas requeridas.
	 * 
	 * @param numOperProg
	 * @param rutFirmante
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String obtenerRutsMandatarios(String numOperProg, String rutFirmante) throws BancaEmpresasException {
		StringBuilder resultado = new StringBuilder();

		String separador = "";
		ConsultaApoOpOut consultaApoOpOut = clienteGenericOperProg.consultarFirmasOperacion(numOperProg);
		List<FirmaOpDto> listaFirmasOpDto = consultaApoOpOut.getFirmaOpDto();

		for (FirmaOpDto firmaOpDto : listaFirmasOpDto) {
			String rutApoderado = FormateadorUtil.rutSinCero(firmaOpDto.getRutApodeado());
			if (!rutFirmante.trim().equals(rutApoderado.trim())) {
				resultado.append(separador).append(FormateadorUtil.rutSinCero(firmaOpDto.getRutApodeado()));
				separador = ",";
			}
		}

		if ("".equals(resultado.toString().trim()))
			resultado.append(rutFirmante);
		else
			resultado.append(",").append(rutFirmante);

		return resultado.toString();
	}

	/**
	 * Inicializa el map con los parámetros de consulta e invoca al SP SP
	 * PR_VALIDACION_PODERES.
	 * 
	 * @param validaPoderRequest
	 * @param codigoFacultad
	 * @return
	 * @throws ErrorStoredProcedureException
	 */
	public Map<String, Object> validarPoder(ValidaPoderRequest validaPoderRequest, String codigoFacultad)
			throws ErrorStoredProcedureException {
		try {

			Map<String, Object> paramsValidaPoder = new HashMap<>();
			paramsValidaPoder.put("RUTCLIENTE", FormateadorUtil.rutSinCero(validaPoderRequest.getRutEmpresa()));
			paramsValidaPoder.put("MANDATARIOS", validaPoderRequest.getMandatarios());
			paramsValidaPoder.put("FACULTADES", codigoFacultad);
			paramsValidaPoder.put("MONEDA", String.valueOf(Constantes.CODIGO_MONEDA_PESOS.charAt(0)));
			paramsValidaPoder.put("MONTO",
					String.format("%.2f", Double.valueOf(validaPoderRequest.getMonto())).replace(",", ""));
			paramsValidaPoder.put("FLAG", SP_VALIDA_PODER_FLAG_MANDATARIOS_FACULTADES_Y_MONTOS);

			paramsValidaPoder.put("RESPUESTA", null);
			paramsValidaPoder.put("CODIGORECHAZO", null);
			paramsValidaPoder.put("DESCRIPCIONRECHAZO", null);

			LOGGER.info("OperacionesEmpresaService validarPoder: rutEmpresa[{}] mandatarios[{}] monto[{}]",
					validaPoderRequest.getRutEmpresa(), validaPoderRequest.getMandatarios(),
					validaPoderRequest.getMonto());

			biceValidaPoderRepository.validaPoder(paramsValidaPoder);

			return paramsValidaPoder;

		} catch (SQLException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new ErrorStoredProcedureException();
		}

	}

	/**
	 * A partir de la salida del SP genera una clase (OperacionesAprobar) que
	 * contiene una lista de operaciones por aprobar.
	 * 
	 * @param listado
	 * @return Clase (OperacionesAprobar) que contiene una lista de operaciones por
	 *         aprobar.
	 */
	public OperacionesAprobar generarListaOperacionesAprobar(List<Map<String, Object>> listado, String codigoServicio) {

		OperacionesAprobar operacionesAprobar = new OperacionesAprobar();
		List<DetalleCampoValorTipoOperacion> listaDetalleCampoValorTipoOperacion = new ArrayList<>();

		for (Map<String, Object> mapa : listado) {
			listaDetalleCampoValorTipoOperacion.add(obtenerDetalleCampoValorTipoOperacion(mapa));
		}

		OperacionesEmpresaUtil operacionesEmpresaUtil = new OperacionesEmpresaUtil();
		List<List<Integer>> arraySeparadores = operacionesEmpresaUtil.obtenerArraySeparadores(
				propiedadesExterna.getProperty("servicios.separadores.operacion." + codigoServicio + ".tipo.aprobar"));

		operacionesAprobar.setArraySeparadores(arraySeparadores);
		operacionesAprobar.setDetalle(listaDetalleCampoValorTipoOperacion);

		return operacionesAprobar;
	}

	/**
	 * Genera el detalle de una operaci&oacute;n.
	 * 
	 * @param mapa
	 * @return Detalle de una operaci&oacute;n.
	 */
	public DetalleCampoValorTipoOperacion obtenerDetalleCampoValorTipoOperacion(Map<String, Object> mapa) {
		DetalleCampoValorTipoOperacion detalleCampoValTipoOp = new DetalleCampoValorTipoOperacion();
		detalleCampoValTipoOp.setNumeroOperacion(MapperUtil.validaRespuesta(mapa.get(NUM_OPER_PROG), false));
		detalleCampoValTipoOp.setTipo(MapperUtil.validaRespuesta(mapa.get("TIPO"), false));
		detalleCampoValTipoOp.setCampo1(MapperUtil.validaRespuesta(mapa.get("CAMPO1"), false));
		detalleCampoValTipoOp.setValor1(MapperUtil.validaRespuesta(mapa.get("VALOR1"), false));
		detalleCampoValTipoOp.setCampo2(MapperUtil.validaRespuesta(mapa.get("CAMPO2"), false));
		detalleCampoValTipoOp.setValor2(MapperUtil.validaRespuesta(mapa.get("VALOR2"), false));
		detalleCampoValTipoOp.setCampo3(MapperUtil.validaRespuesta(mapa.get("CAMPO3"), false));
		detalleCampoValTipoOp.setValor3(MapperUtil.validaRespuesta(mapa.get("VALOR3"), false));
		detalleCampoValTipoOp.setCampo4(MapperUtil.validaRespuesta(mapa.get("CAMPO4"), false));
		detalleCampoValTipoOp.setValor4(MapperUtil.validaRespuesta(mapa.get("VALOR4"), false));
		detalleCampoValTipoOp.setCampo5(MapperUtil.validaRespuesta(mapa.get("CAMPO5"), false));
		detalleCampoValTipoOp.setValor5(MapperUtil.validaRespuesta(mapa.get("VALOR5"), false));
		detalleCampoValTipoOp.setCampo6(MapperUtil.validaRespuesta(mapa.get("CAMPO6"), false));
		detalleCampoValTipoOp.setValor6(MapperUtil.validaRespuesta(mapa.get("VALOR6"), false));
		detalleCampoValTipoOp.setCampo7(MapperUtil.validaRespuesta(mapa.get("CAMPO7"), false));
		detalleCampoValTipoOp.setValor7(MapperUtil.validaRespuesta(mapa.get("VALOR7"), false));
		detalleCampoValTipoOp.setCampo8(MapperUtil.validaRespuesta(mapa.get("CAMPO8"), false));
		detalleCampoValTipoOp.setValor8(MapperUtil.validaRespuesta(mapa.get("VALOR8"), false));
		detalleCampoValTipoOp.setCampo9(MapperUtil.validaRespuesta(mapa.get("CAMPO9"), false));
		detalleCampoValTipoOp.setValor9(MapperUtil.validaRespuesta(mapa.get("VALOR9"), false));
		detalleCampoValTipoOp.setCampo10(MapperUtil.validaRespuesta(mapa.get("CAMPO10"), false));
		detalleCampoValTipoOp.setValor10(MapperUtil.validaRespuesta(mapa.get("VALOR10"), false));
		detalleCampoValTipoOp.setCampo11(MapperUtil.validaRespuesta(mapa.get("CAMPO11"), false));
		detalleCampoValTipoOp.setValor11(MapperUtil.validaRespuesta(mapa.get("VALOR11"), false));
		detalleCampoValTipoOp.setCampo12(MapperUtil.validaRespuesta(mapa.get("CAMPO12"), false));
		detalleCampoValTipoOp.setValor12(MapperUtil.validaRespuesta(mapa.get("VALOR12"), false));
		detalleCampoValTipoOp.setCampo13(MapperUtil.validaRespuesta(mapa.get("CAMPO13"), false));
		detalleCampoValTipoOp.setValor13(MapperUtil.validaRespuesta(mapa.get("VALOR13"), false));
		detalleCampoValTipoOp.setCampo14(MapperUtil.validaRespuesta(mapa.get("CAMPO14"), false));
		detalleCampoValTipoOp.setValor14(MapperUtil.validaRespuesta(mapa.get("VALOR14"), false));
		detalleCampoValTipoOp.setCampo15(MapperUtil.validaRespuesta(mapa.get("CAMPO15"), false));
		detalleCampoValTipoOp.setValor15(MapperUtil.validaRespuesta(mapa.get("VALOR15"), false));
		detalleCampoValTipoOp.setCampo16(MapperUtil.validaRespuesta(mapa.get("CAMPO16"), false));
		detalleCampoValTipoOp.setValor16(MapperUtil.validaRespuesta(mapa.get("VALOR16"), false));
		detalleCampoValTipoOp.setCampo17(MapperUtil.validaRespuesta(mapa.get("CAMPO17"), false));
		detalleCampoValTipoOp.setValor17(MapperUtil.validaRespuesta(mapa.get("VALOR17"), false));

		return detalleCampoValTipoOp;
	}

	/**
	 * Genera un objeto con las operaciones por aprobar.
	 * 
	 * @param mapOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public OperacionesAprobar generarOperacionesAprobar(MapOperaciones mapOperaciones, String codigoServicio) {
		return generarListaOperacionesAprobar(mapOperaciones.getMapOutputSP(), codigoServicio);
	}

	/**
	 * Invoca al SP que obtiene las operaciones por aprobar/liberar.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param codigoServicio
	 * @param isListaAprobar
	 * @return Operaciones por aprobar/liberar.
	 * @throws SQLException
	 * @throws NoEncontradoException
	 * @throws ErrorStoredProcedureException
	 */
	public List<Map<String, Object>> obtenerOperacionesAprobarLiberar(String rut, String rutEmpresa,
			String codigoServicio, boolean isListaAprobar) throws BancaEmpresasException {
		return obtenerOperacionesAprobarLiberar(rut, rutEmpresa, codigoServicio, isListaAprobar, null);
	}

	/**
	 * Invoca al SP que obtiene las operaciones por aprobar/liberar.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param codigoServicio
	 * @param isListaAprobar
	 * @param canal
	 * @return Operaciones por aprobar/liberar.
	 * @throws SQLException
	 * @throws NoEncontradoException
	 * @throws ErrorStoredProcedureException
	 */
	public List<Map<String, Object>> obtenerOperacionesAprobarLiberar(String rut, String rutEmpresa,
			String codigoServicio, boolean isListaAprobar, String canal) throws BancaEmpresasException {
		LOGGER.info(
				"OperacionesEmpresaService obtenerOperacionesAprobarLiberar: rut[{}] rutEmpresa[{}] codigoServicio[{}] isListaAprobar[{}]",
				rut, rutEmpresa, codigoServicio, isListaAprobar);
		List<Map<String, Object>> salida;

		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU, rut);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_CLI, rutEmpresa);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_COD_SERVICIO, codigoServicio);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_ESTADO, isListaAprobar ? "A" : "L");
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA, null);

		try {
			if ((null != canal) && Constantes.CANAL_PORTAL.equalsIgnoreCase(canal)
					&& (Constantes.CODIGO_SERVICIO_LBTR.equals(codigoServicio))) {
				portalOrawRepository.obtenerOperacionesAprobarLiberarPortal(params);
			} else {
				portalOrawRepository.obtenerOperacionesAprobarLiberar(params);
			}

			salida = (List<Map<String, Object>>) params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA);
		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if (!MapperUtil.isSalidaSPValida(salida)) {
			throw new NoEncontradoException();
		}

		return salida;
	}

	/**
	 * A partir de una lista de operaciones y una lista de n&uacute;meros de
	 * operaciones a filtrar genero una lista solo con las operaciones que el filtro
	 * me lo permita.
	 * 
	 * @param listaOperacionesFiltrar
	 * @param operacionesAprobar
	 * @return Lista de operaciones filtrada.
	 */
	public List<DetalleCampoValorTipoOperacion> obtenerListaOperaciones(List<String> listaOperacionesFiltrar,
			OperacionesAprobar operacionesAprobar) {
		List<DetalleCampoValorTipoOperacion> resultado = new ArrayList<>();
		for (DetalleCampoValorTipoOperacion detalleOp : operacionesAprobar.getDetalle()) {
			String nop = detalleOp.getNumeroOperacion().trim();
			if (listaOperacionesFiltrar.contains(nop)) {
				resultado.add(detalleOp);
			}
		}
		return resultado;
	}

	/**
	 * Obtiene el monto total de todas las operaciones de la lista que recibe este
	 * metodo.
	 * 
	 * @param listaOperacionesAprobar
	 * @return Monto total de operaciones.
	 */
	public String obtenerMontoTotalOperacionesAprobar(List<DetalleCampoValorTipoOperacion> listaOperacionesAprobar)
			throws BancaEmpresasException {
		long resultado = 0;
		try {
			for (DetalleCampoValorTipoOperacion detalle : listaOperacionesAprobar) {
				resultado = resultado + Long.parseLong(detalle.getValor1());
			}
		} catch (NumberFormatException e) {
			throw new BancaEmpresasException(e.getMessage());
		}

		return Long.toString(resultado);
	}

	/**
	 * Valida si el nro de operación recibida pertenecen a rut empresa y
	 * c&oacute;digo de servicio.
	 * 
	 * @param listaOperaciones
	 * @param codigoServicio
	 * @param rut_empresa
	 * @return true en caso de que pertenezca y false en caso contrario.
	 */
	public boolean isPertenenciaCorrectaNumOperacionRutEmpresa(String nroOperacion, String codServicio,
			String rutEmpresa) throws BancaEmpresasException {
		LOGGER.info(
				"OperacionesEmpresaService isPertenenciaCorrectaNumOperacionRutEmpresa: nroOperacion[{}] codServicio[{}] rutEmpresa[{}]",
				nroOperacion, codServicio, rutEmpresa);
		boolean resultado = false;
		String salidaSP = null;

		try {
			salidaSP = validarPertenenciaNumOperacionRutEmpresa(nroOperacion, codServicio, rutEmpresa);
			resultado = salidaSP.trim().equals(nroOperacion.trim());

		} catch (NoEncontradoException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			resultado = false;
		} catch (ErrorStoredProcedureException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new BancaEmpresasException();
		}
		return resultado;
	}

	/**
	 * Consulta contra el SP POR_PKG_BANCADIGITAL_EMPRESAS.POR_SP_CON_DETALLE_OPER
	 * el detalle de la operación para saber si está siendo procesada o no.
	 * 
	 * @param nroOperacion
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String consultarDetalleOperacion(String nroOperacion) throws BancaEmpresasException {
		String salida;

		Map<String, Object> params = new HashMap<>();
		params.put("V_NUM_OPER_PROG", nroOperacion);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT, null);
		params.put("v_OUT_MSG_RESULT", null);
		params.put(V_SALIDA, null);

		try {
			portalOrawRepository.consultarDetalleOperacion(params);
			salida = (String) params.get(V_SALIDA);
			String codResult = (String) params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT);

			if (!MapperUtil.isCodResultSPValido(codResult)) {
				throw new NoEncontradoException();
			} else if (null == salida) {
				throw new ErrorStoredProcedureException();
			}
		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return salida;
	}

	/**
	 * Llama al SP POR_PKG_BANCADIGITAL_EMPRESAS.POR_SP_CON_PERT_OPER_RUT para
	 * validar si nroOperacion pertenece a rutEmpresa. Si pertenece retorna el
	 * nroOperacion.
	 * 
	 * @param nroOperacion
	 * @param codServicio
	 * @param rutEmpresa
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String validarPertenenciaNumOperacionRutEmpresa(String nroOperacion, String codServicio, String rutEmpresa)
			throws BancaEmpresasException {
		LOGGER.info(
				"OperacionesEmpresaService validarPertenenciaNumOperacionRutEmpresa: nroOperacion[{}] codServicio[{}], rutEmpresa[{}]",
				nroOperacion, codServicio, rutEmpresa);
		String salida;

		Map<String, Object> params = new HashMap<>();
		params.put("v_RUT_CLI", rutEmpresa);
		params.put("v_COD_SERVICO", codServicio);
		params.put("v_NUM_OPER_PROG", nroOperacion);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT, null);
		params.put("v_OUT_MSG_RESULT", null);
		params.put(V_SALIDA, null);

		try {
			LOGGER.info(
					"OperacionesEmpresaService validarPertenenciaNumOperacionRutEmpresa: ejecuta POR_PKG_BANCADIGITAL_EMPRESAS.POR_SP_CON_PERT_OPER_RUT");
			portalOrawRepository.validarPertenenciaNumOperacionRutEmpresa(params);
			salida = (String) params.get(V_SALIDA);
			String codResult = (String) params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT);
			LOGGER.info("OperacionesEmpresaService validarPertenenciaNumOperacionRutEmpresa: codResult[{}]", codResult);
			if (!MapperUtil.isCodResultSPValido(codResult)) {
				LOGGER.info("OperacionesEmpresaService validarPertenenciaNumOperacionRutEmpresa: codResult no valido");
				throw new NoEncontradoException();
			} else if (null == salida) {
				LOGGER.info("OperacionesEmpresaService validarPertenenciaNumOperacionRutEmpresa: salida==NULL");
				throw new ErrorStoredProcedureException();
			}

		} catch (SQLException e) {
			LOGGER.info("OperacionesEmpresaService validarPertenenciaNumOperacionRutEmpresa: ", e);
			throw new ErrorStoredProcedureException(e);
		}

		return salida;
	}

	/**
	 * Chequea que la operación no esté siendo procesada. Si no está siendo
	 * procesada la deja en estado procesando y continua intentando firmar dicha
	 * operación. Si se firma la operación se la vuelve a actualizar en estado "no
	 * procesando" y se registra la firma en tabla tbl_apo_op a través del servicio
	 * de nominas.
	 * 
	 * @param nroOperacion
	 * @param request
	 * @return
	 */
	public int aprobarOperacion(String nroOperacion, AprobarOperacionesRequest request, MapOperaciones mapOperaciones) {
		int resultado;

		try {
			if (isProcesandoOperacion(nroOperacion)) {
				return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
			}
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		}

		String detalleInSoap = Constantes.ACTUALIZAR_DETALLE_OPERACION_DETALLE_PROCESANDO;
		boolean resActualizaDetalleOp = actualizarDetalleOperacion(detalleInSoap, nroOperacion, false);
		if (!resActualizaDetalleOp)
			return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;

		try {
			resultado = tienePoderEmpresa(crearValidaPoderRequest(request, nroOperacion), mapOperaciones);
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		}

		actualizarDetalleOperacion(Constantes.ACTUALIZAR_DETALLE_OPERACION_DETALLE_NO_PROCESANDO, nroOperacion, true);

		if (Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR != resultado) {
			actualizarTblOperacionProg(String.valueOf(resultado), nroOperacion, true);
			registraFirma(obtenerInDatoFirma(request), request.getNombreApoderado(), nroOperacion, request.getRut(),
					true);
		}

		return resultado;
	}

	/**
	 * Genera InDatoFirma que equivale a: [ID DISPOSITIVO] + [_] + [CANAL] + [|] +
	 * [IP]
	 * 
	 * @param request
	 * @return
	 */
	public String obtenerInDatoFirma(AprobarOperacionesRequest request) {

		return obtenerInDatoFirma(request.getDispositivoFirma(), request.getCanal(), request.getIp());
	}

	/**
	 * Genera InDatoFirma que equivale a: [ID DISPOSITIVO] + [_] + [CANAL] + [|] +
	 * [IP]
	 * 
	 * @param dispositivoFirma
	 * @param canal
	 * @param ip
	 * @return
	 */
	public String obtenerInDatoFirma(String dispositivoFirma, String canal, String ip) {
		StringBuilder str = new StringBuilder();
		if (dispositivoFirma != null && !dispositivoFirma.isEmpty()) {
			str.append((FIRMA_FDA.equalsIgnoreCase(dispositivoFirma.trim()) ? DISPOSITIVO_FIRMA_ESIGN
					: dispositivoFirma.toUpperCase()));
			str.append("_");
		}
		str.append(canal);
		str.append("|");
		str.append((ip == null ? propiedadesExterna.getProperty("servicios.nominas.registra.firma.ipdefault") : ip));

		return str.toString();
	}

	/**
	 * Actualiza en tbl_apo_op el registro de la operación con nuevo rut apoderado a
	 * través del servicio de nóminas. Si ocurre algún error en esta operación se
	 * intenta enviar mail con codigo y glosa del error.
	 * 
	 * @param inDatoFirma
	 * @param inNomApoderado
	 * @param inNumOperProg
	 * @param inRutApoderado
	 * @param enviarMailLogError
	 */
	private boolean registraFirma(String inDatoFirma, String inNomApoderado, String inNumOperProg,
			String inRutApoderado, boolean enviarMailLogError) {
		boolean resultadoOk = false;
		try {
			clienteOperacionNomina.registraFirma(inDatoFirma, inNomApoderado, inNumOperProg, inRutApoderado);
			resultadoOk = true;
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			if (enviarMailLogError)
				enviarMailLogError(inNumOperProg, EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES.getCodigoEstado(),
						estadoService.obtenerGlosaEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES) + " "
								+ propiedadesExterna.getProperty("texto.error.registrar.firma.aprobar.operaciones"),
						TIPO_ERROR_MAIL);
		}
		return resultadoOk;
	}

	/**
	 * Actualiza estado de las firmas de la operación (30- sin firmar, 40 - firma
	 * parcial, 50 - firma completa) en tabla tbl_oper_prog.
	 * 
	 * @param intEstado
	 * @param inNumOperProg
	 * @param enviarMailLogError
	 */
	private void actualizarTblOperacionProg(String intEstado, String inNumOperProg, boolean enviarMailLogError) {
		try {
			clienteOperacionNomina.tblOperacionProg(intEstado, inNumOperProg);
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			if (enviarMailLogError)
				enviarMailLogError(inNumOperProg, EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES.getCodigoEstado(),
						estadoService.obtenerGlosaEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES) + " "
								+ propiedadesExterna
										.getProperty("texto.error.actualizar.estado.op.aprobar.operaciones"),
						TIPO_ERROR_MAIL);
		}
	}

	/**
	 * Actualiza estado de las firmas de la operación (30- sin firmar, 40 - firma
	 * parcial, 50 - firma completa) en tabla tbl_oper_prog.
	 * 
	 * @param intEstado
	 * @param inNumOperProg
	 * @param enviarMailLogError
	 */
	private boolean actualizarTblOperacionProg(ActualizaOpIn actualizaOpIn, boolean enviarMailLogError) {
		boolean resultadoOk = false;
		try {
			resultadoOk = null != clienteOperacionNomina.tblOperacionProg(actualizaOpIn);
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			if (enviarMailLogError)
				enviarMailLogError(actualizaOpIn.getINNUMOPERPROG(),
						EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES.getCodigoEstado(),
						estadoService.obtenerGlosaEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES) + " "
								+ propiedadesExterna
										.getProperty("texto.error.actualizar.estado.op.aprobar.operaciones"),
						TIPO_ERROR_MAIL);
		}

		return resultadoOk;
	}

	/**
	 * Actualiza detalle de la operación en tbl_detalle_camp (procesando o no
	 * procesando)
	 * 
	 * @param inDetalle
	 * @param inNumOperProg
	 * @param enviarMailLogError
	 * @return
	 */
	public boolean actualizarDetalleOperacion(String inDetalle, String inNumOperProg, boolean enviarMailLogError) {
		boolean actualizacionCorrecta = false;
		try {
			clienteOperacionNomina.actualizarDetalleOperacion(inDetalle, inNumOperProg);
			actualizacionCorrecta = true;
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			if (enviarMailLogError)
				enviarMailLogError(inNumOperProg, EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES.getCodigoEstado(),
						estadoService.obtenerGlosaEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES) + " "
								+ propiedadesExterna
										.getProperty("texto.error.actualizar.detalle.op.aprobar.operaciones"),
						TIPO_ERROR_MAIL);
			actualizacionCorrecta = false;
		}
		return actualizacionCorrecta;
	}

	/**
	 * Actualiza detalle de la operación en tbl_detalle_camp (procesando o no
	 * procesando)
	 * 
	 * @param codCampo
	 * @param valCampo
	 * @param inNumOperProg
	 * @param enviarMailLogError
	 * @return
	 */
	public boolean actualizarDetalleOperacion(String codCampo, String valCampo, String inNumOperProg,
			boolean enviarMailLogError) {
		return actualizarDetalleOperacion((codCampo + "=" + valCampo + ";"), inNumOperProg, enviarMailLogError);
	}

	/**
	 * Llama al SP que se encarga de enviar mail con los datos codigo y glosa de
	 * error.
	 * 
	 * @param nroOperacion
	 * @param codError
	 * @param glosaError
	 * @param tipo
	 */
	public void enviarMailLogError(String nroOperacion, String codError, String glosaError, String tipo) {
		try {
			mailService.enviarMailCodGlsError(nroOperacion, codError, glosaError, tipo);

		} catch (BancaEmpresasException e1) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e1);
		}
	}

	/**
	 * Consulta si una operación está siendo procesada.
	 * 
	 * @param nroOperacion
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String obtenerProcesandoOperacion(String nroOperacion) throws BancaEmpresasException {
		try {
			String estadoOperacion = consultarDetalleOperacion(nroOperacion);

			if (null == estadoOperacion) {
				throw new BancaEmpresasException();
			}

			return (estadoOperacion.trim());

		} catch (NoEncontradoException | ErrorStoredProcedureException e) {
			throw new BancaEmpresasException(e);
		}
	}

	/**
	 * Recibe una lista de operaciones y las aprueba. Este método controla posibles
	 * errores al intentar realizar las operaciónes para aprobar para luego
	 * enviarselas al controller y este al front.
	 * 
	 * @param request
	 * @param estado
	 * @return operaciones no aprobadas, cantidad operaciones no aprobadas y
	 *         operaciones con firma parcial.
	 */
	public AprobarOperacionesResponse aprobarOperaciones(AprobarOperacionesRequest request, Estado estado) {
		List<String> operacionesNoAprobadas = new ArrayList<>();
		List<String> operacionesConFirmaParcial = new ArrayList<>();

		if (estado.isEXITO()) {
			try {
				MapOperaciones mapOperaciones = valoresCampoOperacionesService.obtenerMapOperaciones(request.getRut(),
						request.getRutEmpresa(), request.getCodigoServicio());

				if (!validacionService.isPertenenciaValida(request, request.getCodigoServicio(),
						request.getListaOperaciones(), mapOperaciones, true))
					throw new RequestInvalidoException();

				for (String nroOperacion : request.getListaOperaciones()) {
					int resultadoAprobar;
					if (Constantes.CODIGO_SERVICIO_TEF_MX.equals(request.getCodigoServicio())
							|| request.getCodigoServicio().equals(Constantes.CODIGO_SERVICIO_SPOTWEB)) {
						resultadoAprobar = aprobarOperacionLBTRMX(nroOperacion, request);
					} else {
						resultadoAprobar = aprobarOperacion(nroOperacion, request, mapOperaciones);
					}

					LOGGER.debug("OperacionesEmpresaService aprobarOperaciones: nroOperacion[{}] codigoEstado[{}]",
							nroOperacion, resultadoAprobar);

					switch (resultadoAprobar) {
					case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR:
						operacionesNoAprobadas.add(nroOperacion);
						break;
					case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL:
						operacionesConFirmaParcial.add(nroOperacion);
						break;
					default:
						LOGGER.debug("OperacionesEmpresaService aprobarOperaciones operacion [{}] con firma completa",
								nroOperacion);
						break;
					}

				}
				mapOperaciones.clearMap();
			} catch (RequestInvalidoException | EntradaInvalidaException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado);
				operacionesNoAprobadas = null;
			} catch (BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES, estado);
				operacionesNoAprobadas = null;
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
		AprobarOperacionesResponse response = new AprobarOperacionesResponse();
		response.setEstado(estado);
		response.setRespuesta(aprobarOperacionesResp);

		return response;
	}

	/**
	 * Valida poderes conta SP PR_VALIDACION_PODERES si rut usuario y rut empresa
	 * tienen facultad especial y sinó valida poderes contra TPAG2000. Este método
	 * controla posibles errores al intentar realizar las operaciónes para aprobar
	 * para luego enviarselas al controller y este al front.
	 * 
	 * @param request
	 * @param estado
	 * @return si retorna 50 significa que es FIRMA COMPLETA si retorna 40 significa
	 *         que es FIRMA PARCIAL si retorna 30 significa que es SIN FIRMAR
	 */
	public ValidacionPoderResponse validaPoderEmpresa(ValidaPoderRequest request, Estado estado) {
		ValidacionPoderResp validacionPoderResp = new ValidacionPoderResp();
		if (estado.isEXITO()) {
			try {

				MapOperaciones mapOperaciones = valoresCampoOperacionesService.obtenerMapOperaciones(request.getRut(),
						request.getRutEmpresa(), request.getCodigoServicio());

				if (!validacionService.isPertenenciaValida(request, request.getCodigoServicio(),
						request.getNumeroOperacion(), mapOperaciones, true)) {
					throw new RequestInvalidoException();
				}

				int codigoEstadoOperacion = tienePoderEmpresa(request, mapOperaciones);

				mapOperaciones.clearMap();

				validacionPoderResp.setCodigoEstadoOperacion(codigoEstadoOperacion);
				if (Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_FIRMA_COMPLETA == codigoEstadoOperacion)
					validacionPoderResp
							.setGlosaEstadoOperacion(Constantes.SERVICIO_OPERACION_EMPRESA_GLOSA_ESTADO_FIRMA_COMPLETA);
				else if (Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL == codigoEstadoOperacion)
					validacionPoderResp
							.setGlosaEstadoOperacion(Constantes.SERVICIO_OPERACION_EMPRESA_GLOSA_ESTADO_PARCIAL);
				else if (Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR == codigoEstadoOperacion)
					validacionPoderResp.setGlosaEstadoOperacion(
							Constantes.SERVICIO_OPERACION_EMPRESA_GLOSA_ESTADO_FIRMA_INCORRECTA);

			} catch (NoEncontradoException | ErrorStoredProcedureException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_VALIDA_PODER_EMPRESA, estado);
				validacionPoderResp
						.setCodigoEstadoOperacion(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR);
				validacionPoderResp
						.setGlosaEstadoOperacion(Constantes.SERVICIO_OPERACION_EMPRESA_GLOSA_ESTADO_FIRMA_INCORRECTA);
			} catch (RequestInvalidoException | EntradaInvalidaException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado);
				validacionPoderResp
						.setCodigoEstadoOperacion(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR);
				validacionPoderResp
						.setGlosaEstadoOperacion(Constantes.SERVICIO_OPERACION_EMPRESA_GLOSA_ESTADO_FIRMA_INCORRECTA);
			} catch (BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_VALIDA_PODER_EMPRESA, estado);
				validacionPoderResp
						.setCodigoEstadoOperacion(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR);
				validacionPoderResp
						.setGlosaEstadoOperacion(Constantes.SERVICIO_OPERACION_EMPRESA_GLOSA_ESTADO_FIRMA_INCORRECTA);
			}
		}

		ValidacionPoderResponse response = new ValidacionPoderResponse();
		response.setEstado(estado);
		response.setRespuesta(validacionPoderResp);

		return response;
	}

	/**
	 * Recibe una lista de operaciones y las libera. Este método controla posibles
	 * errores al intentar realizar las operaciónes para liberar para luego
	 * enviarselas al controller y este al front.
	 * 
	 * @param request
	 * @param estado
	 * @return operaciones liberadas con su estado y glosa de liberación
	 *         (liberada/rechazada)
	 * 
	 */
	public LiberarOperacionesResponse liberarOperaciones(LiberarOperacionesRequest request, Estado estado) {
		Map<String, Map<String, String>> operacionesNoLiberadas = new HashMap<>();
		Map<String, Map<String, String>> operacionesLiberadas = new HashMap<>();

		LiberarOperacionesResp liberarOperacionesResp = new LiberarOperacionesResp();
		if (estado.isEXITO()) {
			try {

				MapOperaciones mapOperaciones = valoresCampoOperacionesService.obtenerMapOperacionesCompleto(
						request.getRut(), request.getRutEmpresa(), request.getCodigoServicio(), false);
				
				boolean resultadoCorrelativo = false;
				if (request.getCodigoServicio().equals(Constantes.CODIGO_SERVICIO_TEF_MX)
						|| request.getCodigoServicio().equals(Constantes.CODIGO_SERVICIO_SPOTWEB)) {
					String idCorrelativo = lbtrMXservice.iniciarControlCorrelativo(request);
					resultadoCorrelativo = lbtrMXservice.controlCorrelativoCorrecto(idCorrelativo,request);
				}
				
				for (String nroOperacion : request.getListaOperaciones()) {

					int resultadoLiberar = liberarOperacion(nroOperacion, request, mapOperaciones, resultadoCorrelativo);

					LOGGER.debug("OperacionesEmpresaService liberarOperaciones: nroOperacion[{}] codigoEstado[{}]",
							nroOperacion, resultadoLiberar);

					if (resultadoLiberar != Constantes.NUMERIC_CERO) {
						operacionesNoLiberadas.put(nroOperacion, mapOperaciones.getMapOperaciones().get(nroOperacion));
					} else {
						operacionesLiberadas.put(nroOperacion, mapOperaciones.getMapOperaciones().get(nroOperacion));
					}

				}

				liberarOperacionesResp.setOperacionesNoLiberadas(operacionesNoLiberadas);
				liberarOperacionesResp.setOperacionesLiberadas(operacionesLiberadas);
				mapOperaciones.clearMap();

			} catch (RequestInvalidoException | EntradaInvalidaException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado);
				operacionesNoLiberadas = null;
			} catch (BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES, estado);
				operacionesNoLiberadas = null;
			}
		}

		Integer cantidadOperacionesNoLiberadas = 0;
		if (null != operacionesNoLiberadas && !operacionesNoLiberadas.isEmpty()) {
			cantidadOperacionesNoLiberadas = operacionesNoLiberadas.size();
		}

		Integer cantidadOperacionesLiberadas = 0;
		if (null != operacionesLiberadas && !operacionesLiberadas.isEmpty()) {
			cantidadOperacionesLiberadas = operacionesLiberadas.size();
		}

		liberarOperacionesResp.setCantOperacionesNoLiberadas(cantidadOperacionesNoLiberadas);
		liberarOperacionesResp.setCantOperacionesLiberadas(cantidadOperacionesLiberadas);
		LiberarOperacionesResponse response = new LiberarOperacionesResponse();
		response.setEstado(estado);
		response.setRespuesta(liberarOperacionesResp);

		return response;
	}
	
	/**
	 * Modifica el formato de los montos de una operación en el map de operaciones.
	 * Ej: 100, pasa a ser 1,00
	 * 
	 * @param mapOperaciones
	 * @param nroOperacion
	 */
	public void actualizarFormatoMontoOperacionMapOperacionesTefMxSpotWeb(MapOperaciones mapOperaciones,
			String nroOperacion) {
		try {
			DecimalFormat montoFormat = new DecimalFormat();
			montoFormat.applyPattern("###,###,###,###,##0.00");
			String monto = mapOperaciones.getMapOperaciones().get(nroOperacion).get("Monto");
			mapOperaciones.getMapOperaciones().get(nroOperacion).replace("Monto", monto,
					montoFormat.format(Double.parseDouble(monto) / (double) 100));
			String montoCargo = mapOperaciones.getMapOperaciones().get(nroOperacion).get("Monto Cargo");
			mapOperaciones.getMapOperaciones().get(nroOperacion).replace("Monto Cargo", montoCargo,
					montoFormat.format(Double.parseDouble(montoCargo) / (double) 100));
		} catch (NumberFormatException e) {
			LOGGER.info("Error al formatear montos: {}", e);
		}
	}

	/**
	 * Para todos los maps que contiene la lista: modifica el formato de los montos
	 * de una operación en el map que contiene los datos de una operación. Ej: 100,
	 * pasa a ser 1,00  
	 * @param codigoServicio
	 * @param listado
	 */
	public void actualizarFormatoMontoOperacionListaMapOperacionesTefMxSpotWeb(String codigoServicio,
			List<Map<String, Object>> listado) {
		try {
			DecimalFormat montoFormat = new DecimalFormat();
			montoFormat.applyPattern("###,###,###,###,##0.00");
			String nombreCampoMonto = (codigoServicio.equals(Constantes.CODIGO_SERVICIO_TEF_MX) ? "TefMx" : "SpotWeb")
					+ "Monto";
			String nombreCampoMontoCargo = (codigoServicio.equals(Constantes.CODIGO_SERVICIO_TEF_MX) ? "TefMx"
					: "SpotWeb") + "MontoCargo";
			String claveValorMonto = "VALOR" + propiedadesExterna
					.getProperty("servicios.sp.operacionesAprobar." + nombreCampoMonto + ".numValor");
			String claveValorMontoCargo = "VALOR" + propiedadesExterna
					.getProperty("servicios.sp.operacionesAprobar." + nombreCampoMontoCargo + ".numValor");

			for (Map<String, Object> map : listado) {

				String monto = (String) map.get(claveValorMonto);
				String montoCargo = (String) map.get(claveValorMontoCargo);
				if (null != monto) {
					map.replace(claveValorMonto, monto, montoFormat.format(Double.parseDouble(monto) / (double) 100));
				}
				if (null != montoCargo) {
					map.replace(claveValorMontoCargo, montoCargo,
							montoFormat.format(Double.parseDouble(montoCargo) / (double) 100));
				}
			}
		} catch (NumberFormatException e) {
			LOGGER.info("Error al formatear montos: {}", e);
		}
	}

	/**
	 * Libera operaciones por tipo Actualiza datos operacion en progreso y detalle
	 * de operaciones
	 * 
	 * @param nroOperacion
	 * @param request
	 * @return
	 */
	public int liberarOperacion(String nroOperacion, LiberarOperacionesRequest request, MapOperaciones mapOperaciones, boolean resultadoCorrelativo)
			throws BancaEmpresasException {
		int resultado = -1;

		if (request.getCodigoServicio().equals(Constantes.SERVICIO_OPERACION_EMPRESA_COD_LBTR)) {
			resultado = liberarOperacionLbtr(nroOperacion, request, mapOperaciones);
		}
		
		if (request.getCodigoServicio().equals(Constantes.CODIGO_SERVICIO_TEF_MX)) {
			if(resultadoCorrelativo) {
				resultado = lbtrMXservice.liberarTefMx(nroOperacion, request, mapOperaciones);
			}			
		}

		return resultado;
	}

	/**
	 * Libera operaciones del tipo LBTR Actualiza datos operacion en progreso y
	 * detalle de operaciones
	 * 
	 * @param nroOperacion
	 * @param request
	 * @return
	 */
	public int liberarOperacionLbtr(String nroOperacion, LiberarOperacionesRequest request,
			MapOperaciones mapOperaciones) throws BancaEmpresasException {
		int resultado = -1;

		LOGGER.info("Se inicia liberación LBTR. Se actualiza canal: [{}] en la tbl_detalle_camp, num_oper_prog: [{}]",
				nroOperacion, request.getCanal());
		String inDatoFirmaLiberar = obtenerInDatoFirma("", request.getCanal(), request.getIp());
		String detalleInSoapCanal = Constantes.COD_CAMPO_CANAL_LIBERACION + "=" + inDatoFirmaLiberar + ";";
		clienteOperacionNomina.actualizarDetalleOperacion(detalleInSoapCanal, nroOperacion);

		// se obtienen valores operacion campo-valor de la salida del sp.
		List<Map<String, Object>> mapOperacionesSp = new ArrayList<>();
		mapOperacionesSp = mapOperaciones.getMapOutputSP();
		Map<String, Object> mapOperacionSp = new HashMap<>();

		for (Map<String, Object> map : mapOperacionesSp) {
			if (map.get(Constantes.NUM_OPER_PROG).toString().trim().equals(nroOperacion)) {
				mapOperacionSp = map;
				break;
			}
		}

		Map<String, String> mapOperacionResp = new HashMap<>();// inicializamos mapa de operacion a devolver
		mapOperaciones.getMapOperaciones().put(nroOperacion, mapOperacionResp);
		mapOperaciones.getMapOperaciones().get(nroOperacion).put(Constantes.COD_ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.COD_ESTADO_LIBERACION_NOK));
		mapOperaciones.getMapOperaciones().get(nroOperacion).put(Constantes.ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
		mapOperaciones.getMapOperaciones().get(nroOperacion).put(Constantes.GLOSA_ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SISTEMA));

		if (mapOperacionSp == null || mapOperacionSp.isEmpty()) {
			LOGGER.warn("No se pudo liberar operación. Operación N°: [{}] no pertenece a lista por liberar",
					nroOperacion);
			return resultado;
		}

		boolean esCargoEnLinea = validacionService.empresaTieneCargoEnLineLbtr(request.getRutEmpresa());
		boolean esCuentaParagua = false;
		if (((String) mapOperacionSp.get("TIPO")).trim()
				.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_SIMPLE)
				|| ((String) mapOperacionSp.get("TIPO")).trim()
						.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_CCLV)) {
			esCuentaParagua = validacionService.empresaOperaCuentaParaguaLbtr(request.getRutEmpresa());
		}

		boolean esFechaValutaHoy = esFechaValutaHoy((String) mapOperacionSp.get("VALOR3"));
		
		if(esFechaValutaHoy && !esHoraValida()) {
			LOGGER.warn("No se pudo liberar operación. Operación N°: [{}] tiene fecha de valuta de hoy y no puede ser liberada a esta hora.",
					nroOperacion);
			mapOperacionResp.put(Constantes.GLOSA_ESTADO_LIBERACION,
					propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_HORA_LIMITE));
			return resultado;
		}

		if (esCargoEnLinea && !esCuentaParagua && esFechaValutaHoy) {
																		
			boolean esSoloCargo = esSoloCargo((String) mapOperacionSp.get("VALOR15"),
					((String) mapOperacionSp.get("TIPO")).trim());
			try {
				String estadoOperacion = obtenerProcesandoOperacion(nroOperacion);
				if (estadoOperacion.equalsIgnoreCase("PROCESADA")) {
					LOGGER.warn("No se pudo liberar operación. Operación N°: [{}] tiene estado PROCESADA",
							nroOperacion);
					return resultado;
				} else if (estadoOperacion.startsWith("N")) {
					String detalleInSoap = Constantes.COD_CAMPO_ESTADO_PROCESO + "=" + Constantes.GLOSA_EXEC_GFS;
					ActualizaDetCampOut resActualizaDetalleOp = clienteGenericOperProg.actualizarDetalle(nroOperacion,
							detalleInSoap);
					if (resActualizaDetalleOp.getCodEstado() != 0) {
						LOGGER.warn(
								"No se pudo actualizar la operación en la tbl_detalle_camp, num_oper_prog: [{}] cod estado: {}, glosa error: {}",
								nroOperacion, resActualizaDetalleOp.getCodEstado(),
								resActualizaDetalleOp.getMsgStatus());
						return resultado;
					} else {
						LOGGER.info(
								"Actualización exitosa en tbl_detalle_camp, num_oper_prog: [{}] cod estado: {}, glosa error: {}",
								nroOperacion, resActualizaDetalleOp.getCodEstado(),
								resActualizaDetalleOp.getMsgStatus());

						Map<String, Object> respGfs = new HashMap<>();
						respGfs = tefService.llamarGFS(request, mapOperacionSp, esSoloCargo,
								Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_SIMPLE);
						String codLiberacion = "";
						codLiberacion = (String) respGfs.get(Constantes.COD_ESTADO_LIBERACION);
						if (codLiberacion.equals(Constantes.ESTATUS_LIBERADA_160)
								|| codLiberacion.equals(Constantes.COD_ESTADO_PROCESADO))
							resultado = 0;

						mapOperacionResp.put(Constantes.COD_ESTADO_LIBERACION,
								(String) respGfs.get(Constantes.COD_ESTADO_LIBERACION));
						mapOperacionResp.put(Constantes.ESTADO_LIBERACION,
								(String) respGfs.get(Constantes.ESTADO_LIBERACION));
						mapOperacionResp.put(Constantes.GLOSA_ESTADO_LIBERACION,
								(String) respGfs.get(Constantes.GLOSA_ESTADO_LIBERACION));

					}
				}
			} catch (BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				return -1;
			}

		} else {// FLUJO SIN CARGO EN LINEA
			Estado estadoTemp = new Estado(EstadoEnum.EXITO.getCodigoEstado(),
					EstadoEnum.EXITO.getParametroGlosaEstado());
			String cuentaCargo = (String) mapOperacionSp.get("VALOR9");
			BigDecimal saldoLiberar = new BigDecimal((String) mapOperacionSp.get("VALOR1"));
			SaldoRequest saldoRequest = new SaldoRequest();
			saldoRequest.setBia("false");
			saldoRequest.setCanal(request.getCanal());
			saldoRequest.setCodigoProducto("0100");
			saldoRequest.setDispositivo(request.getDispositivo());
			saldoRequest.setMoneda(Constantes.CONSTANTE_CERO);
			saldoRequest.setRut(request.getRut());
			saldoRequest.setRutEmpresa(request.getRutEmpresa());
			saldoRequest.setToken(request.getToken());

			BigDecimal saldoDisponible = new BigDecimal(0);

			if (esCuentaParagua) {
				saldoDisponible = saldoDisponible.add(obtenerSaldoCuentaParagua(request, cuentaCargo, estadoTemp));
				if (!estadoTemp.isEXITO())
					return -1;

			} else if (((String) mapOperacionSp.get("TIPO")).trim()
					.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_SIMPLE)
					|| ((String) mapOperacionSp.get("TIPO")).trim()
							.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_DVP)) {
				saldoDisponible = saldoDisponible.add(obtenerSaldoCuenta(request, cuentaCargo, estadoTemp));

			} else if (((String) mapOperacionSp.get("TIPO")).trim()
					.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_CCLV)) {
//				saldoDisponible = saldoDisponible.add(obtenerSaldoCuenta(request, cuentaCargo, estadoTemp));
				saldoDisponible.add(calcularSaldoCclv(request, cuentaCargo, estadoTemp));
				if (!estadoTemp.isEXITO())
					return -1;
			}

			// se valida saldo sólo en en caso de cuenta PARAGUA o CCLV (no para SIMPLE ni
			// DVP)
			if (esCuentaParagua || ((String) mapOperacionSp.get("TIPO")).trim()
					.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_CCLV)) {
				if (saldoDisponible.compareTo(saldoLiberar) == -1) {
					LOGGER.error("Saldo cuenta insuficiente, cuenta nro: [{}]", cuentaCargo);
					mapOperacionResp.put(Constantes.GLOSA_ESTADO_LIBERACION,
							propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SALDO1));
					return -1;
				} else {
					LOGGER.info("Saldo suficiente, cuenta nro: [{}]", cuentaCargo);
				}
			}

			LOGGER.info("Saldo disponible: [{}], saldo a liberar: [{}], cuenta nro: [{}]", saldoDisponible,
					saldoLiberar, cuentaCargo);

			Map<String, String> mapOperacionRequest = new HashMap<>();
			mapOperacionRequest.put(Constantes.NUM_OPER_PROG, nroOperacion);
			mapOperacionRequest.put(Constantes.INESTADO_SOAP, Constantes.ESTATUS_LIBERADA_160);
			// se actualiza en tbl_oper_prog
			ActualizaOpOut respOperacion = clienteGenericOperProg.actualizarData(mapOperacionRequest);
			if (!Constantes.NUMERIC_CERO.equals(respOperacion.getCodEstado())) {
				LOGGER.warn(
						"No se pudo actualizar la operación en la tbl_oper_prog, num_oper_prog: [{}] cod estado: {}, glosa error: {}",
						nroOperacion, respOperacion.getCodEstado(), respOperacion.getMsgResult());
			} else {
				LOGGER.info(
						"Actualización exitosa en tbl_oper_prog,  num_oper_prog: [{}], cod estado: {}, glosa estado: {}",
						nroOperacion, respOperacion.getCodEstado(), respOperacion.getMsgResult());

				String detalleInSoap = Constantes.COD_CAMPO_RUT_LIBERADOR + "=" + request.getRut() + ";";
				detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_NOM_LIBERADOR + "=" + request.getNombreApoderado()
						+ ";";
				String fechaHoy = empresasService.fechaHoy(Constantes.FORMAT_DD_MM_YYYY_HHMMSS);
				detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_FECHA_HORA_LIBERACION + "=" + fechaHoy + ";";
				ActualizaDetCampOut resActualizaDetalleOp = clienteGenericOperProg.actualizarDetalle(nroOperacion,
						detalleInSoap);
				if (resActualizaDetalleOp.getCodEstado() != 0)
					LOGGER.warn(
							"No se pudo actualizar la operación en la tbl_detalle_camp, num_oper_prog: [{}] cod estado: {}, glosa error: {}",
							nroOperacion, resActualizaDetalleOp.getCodEstado(), resActualizaDetalleOp.getMsgStatus());
				else
					LOGGER.info(
							"Actualización exitosa en tbl_detalle_camp, num_oper_prog: [{}] cod estado: {}, glosa : {}",
							nroOperacion, resActualizaDetalleOp.getCodEstado(), resActualizaDetalleOp.getMsgStatus());

				mapOperacionResp.put(Constantes.COD_ESTADO_LIBERACION, Constantes.ESTATUS_LIBERADA_160);
				mapOperacionResp.put(Constantes.ESTADO_LIBERACION,
						propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_OK));
				mapOperacionResp.put(Constantes.GLOSA_ESTADO_LIBERACION,
						propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_OK));
				resultado = 0;
			}
		}

		return resultado;
	}

	/**
	 * Consulta si una operación está siendo procesada para liberación y si el campo
	 * .
	 * 
	 * @param nroOperacion
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean isProcesandoOperacion(String nroOperacion) throws BancaEmpresasException {
		try {
			String estadoOperacion = consultarDetalleOperacion(nroOperacion);

			if (null == estadoOperacion) {
				throw new BancaEmpresasException();
			}

			return ("PROCESANDO".equalsIgnoreCase(estadoOperacion.trim()));

		} catch (NoEncontradoException | ErrorStoredProcedureException e) {
			throw new BancaEmpresasException(e);
		}
	}

	public boolean isProcesandoOperacionTefMx(String nroOperacion) throws BancaEmpresasException {
		try {
			String estadoOperacion = consultarDetalleOperacion(nroOperacion);

			if (null == estadoOperacion) {
				throw new BancaEmpresasException();
			}

			return ("EXEC_GFS".equalsIgnoreCase(estadoOperacion.trim()));

		} catch (NoEncontradoException | ErrorStoredProcedureException e) {
			LOGGER.info("ERROR isProcesandoOperacionTefMx: {}", e);
			return true;
		}
	}

	/**
	 * Verifica si la modalidad de transacción es 'sólo cargo en línea' o si es
	 * 'cargo y abono'
	 * 
	 * @param nroOperacion
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean esSoloCargo(String banco, String tipoPago) {

		boolean esSoloCargo = false;// false significa que es cargo y abono
		if (banco.trim().toUpperCase().contains(Constantes.BICE)) {
			if (tipoPago.trim().equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_DVP))
				esSoloCargo = true;
			else if (tipoPago.trim().equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_SIMPLE))
				esSoloCargo = false;
		} else {
			if (tipoPago.trim().equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_SIMPLE)
					|| tipoPago.trim().equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_DVP)
					|| tipoPago.trim().equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_CCLV))
				esSoloCargo = true;
		}

		return esSoloCargo;

	}

	/**
	 * Verifica si la fecha de valuta es hoy
	 * 
	 * @param fechaValuta (string dd-mm-YYYY)
	 * @return boolean
	 * @throws BancaEmpresasException
	 */
	public boolean esFechaValutaHoy(String fechaValuta) {
		boolean esFechaValutaHoy = false;

		try {
			String fechaHoy = empresasService.fechaHoy(Constantes.FORMAT_YYYYMMDDHHMMSS);

			DateTimeFormatter formatterHoy = DateTimeFormatter.ofPattern(Constantes.FORMAT_YYYYMMDDHHMMSS);
			DateTimeFormatter formatterValuta = DateTimeFormatter.ofPattern(Constantes.FORMAT_DD_MM_YYYY);
			LocalDate fechaHoyLocalDate = LocalDate.parse(fechaHoy, formatterHoy);
			LocalDate fechaValutaLocalDate = LocalDate.parse(fechaValuta, formatterValuta);

			String horaHoy = fechaHoy.substring(8, 12);
			String horaCorteLiberacion = "";

			List<ParametrosVo> horaCorteParams = consultarParametrosService.consultaParametro("HORACORTETEF",
					"LBTRCARGOONLINE");
			if (horaCorteParams != null && !horaCorteParams.isEmpty()) {
				horaCorteLiberacion = horaCorteParams.get(0).getValParametro() != null
						? horaCorteParams.get(0).getValParametro().trim()
						: "";
			}

			if (fechaValutaLocalDate.isEqual(fechaHoyLocalDate)
					&& Integer.valueOf(horaHoy).compareTo(Integer.valueOf(horaCorteLiberacion)) < 0) {
				esFechaValutaHoy = true;
			}

		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
		}

		return esFechaValutaHoy;

	}

	/**
	 * Obtiene saldo disponible de una cuenta
	 * 
	 * @param request, cuentaCargo, estado @return BigDecimal @throws
	 */
	public BigDecimal obtenerSaldoCuenta(LiberarOperacionesRequest request, String nroCuenta, Estado estado) {
		BigDecimal saldoDisponible = new BigDecimal(0);
		SaldoRequest saldoRequest = new SaldoRequest();
		saldoRequest.setCuenta(nroCuenta);
		saldoRequest.setBia("false");
		saldoRequest.setCanal(request.getCanal());
		saldoRequest.setCodigoProducto("0100");
		saldoRequest.setDispositivo(request.getDispositivo());
		saldoRequest.setMoneda(Constantes.CONSTANTE_CERO);
		saldoRequest.setRut(request.getRut());
		saldoRequest.setRutEmpresa(request.getRutEmpresa());
		saldoRequest.setToken(request.getToken());

		SaldoResp saldoResp = new SaldoResp();
		String monedaFormateada = String.format("%03d", Integer.valueOf(saldoRequest.getMoneda()));
		Estado estadoTemp = new Estado(EstadoEnum.EXITO.getCodigoEstado(), EstadoEnum.EXITO.getParametroGlosaEstado());
		saldoResp = cuentaService.obtenerSaldo(monedaFormateada, saldoRequest, estadoTemp);
		if (saldoResp != null && estadoTemp.isEXITO())
			if (saldoResp.getMonto() != null)
				saldoDisponible = saldoDisponible.add(new BigDecimal(saldoResp.getMonto().trim()));

		return saldoDisponible;
	}

	/**
	 * Obtiene objeto de saldos para una cuenta
	 * 
	 * @param request, cuentaCargo, estado @return SaldoResp @throws
	 */
	public SaldoResp obtenerSaldos(LiberarOperacionesRequest request, String nroCuenta, Estado estado) {
		SaldoRequest saldoRequest = new SaldoRequest();
		saldoRequest.setCuenta(nroCuenta);
		saldoRequest.setBia("false");
		saldoRequest.setCanal(request.getCanal());
		saldoRequest.setCodigoProducto("0100");
		saldoRequest.setDispositivo(request.getDispositivo());
		saldoRequest.setMoneda(Constantes.CONSTANTE_CERO);
		saldoRequest.setRut(request.getRut());
		saldoRequest.setRutEmpresa(request.getRutEmpresa());
		saldoRequest.setToken(request.getToken());

		SaldoResp saldoResp = new SaldoResp();
		String monedaFormateada = String.format("%03d", Integer.valueOf(saldoRequest.getMoneda()));
		Estado estadoTemp = new Estado(EstadoEnum.EXITO.getCodigoEstado(), EstadoEnum.EXITO.getParametroGlosaEstado());
		saldoResp = cuentaService.obtenerSaldo(monedaFormateada, saldoRequest, estadoTemp);

		return saldoResp;
	}

	/**
	 * Obtiene el saldo de las cuentas asociadas a modalidad paragua dada una cuenta
	 * 
	 * @param request, cuentaCargo, estado @return bigDecimal @throws
	 */
	public BigDecimal obtenerSaldoCuentaParagua(LiberarOperacionesRequest request, String cuentaCargo, Estado estado) {
		BigDecimal saldoDisponible = new BigDecimal(0);

		List<Map<String, Object>> listaCuentasColaborativas = new ArrayList<>();
		listaCuentasColaborativas = empresasService.obtenerCuentasColaborativas(cuentaCargo);

		if (listaCuentasColaborativas != null && !listaCuentasColaborativas.isEmpty()) {

			for (Map<String, Object> cuentaColaborativa : listaCuentasColaborativas) {
				SaldoResp saldos = new SaldoResp();
				saldos = obtenerSaldos(request, (String) cuentaColaborativa.get("CUENTA"), estado);
				if (saldos != null && estado.isEXITO()) {
					List<DetalleSaldoResp> detalleSaldoList = saldos.getDetalle();
					// Calculo saldo cuenta madre
					if (((BigDecimal) cuentaColaborativa.get("COD_TIPO_CUENTA")).compareTo(BigDecimal.ZERO) == 0) {
//						for (DetalleSaldoResp detalleSaldo : detalleSaldoList) {
//							if(detalleSaldo.getNombre() != null && !detalleSaldo.getNombre().toUpperCase().contains("SOBREGIRO") && detalleSaldo.getSaldos().get(0).getMonto() != null) {
						if (detalleSaldoList.get(0).getSaldos().get(0).getMonto() != null) {
							DetalleSaldoResp detalleSaldo = detalleSaldoList.get(0);// cuenta cte
							BigDecimal saldoDispCuentaMadre = new BigDecimal(detalleSaldo.getSaldos().get(0).getMonto()
									.trim().replace("$ ", "").replace(".", ""));
							// Para la cuenta madre se considera el monto sólo si es mayor a cero
							if (saldoDispCuentaMadre != null && saldoDispCuentaMadre.compareTo(BigDecimal.ZERO) == 1) {
								saldoDisponible = saldoDisponible.add(saldoDispCuentaMadre);
							}
						}
//						}
					} else {// si no es cuenta madre, se calcula directamente el monto
						if (saldos.getMonto() != null)
							saldoDisponible = saldoDisponible.add(new BigDecimal(saldos.getMonto().trim()));
					}
					// Si tiene línea de sobregiro se considera en el monto final
					if (((String) cuentaColaborativa.get("COD_LSOBREGIRO")).equalsIgnoreCase("S")) {
						for (DetalleSaldoResp detalleSaldo : detalleSaldoList) {
							if (detalleSaldo.getNombre() != null
									&& detalleSaldo.getNombre().toUpperCase().contains("SOBREGIRO")
									&& detalleSaldo.getSaldos().get(0).getMonto() != null) {
								BigDecimal saldoDispLineaSobregiro = new BigDecimal(detalleSaldo.getSaldos().get(0)
										.getMonto().trim().replace("$ ", "").replace(".", ""));
								saldoDisponible = saldoDisponible.add(saldoDispLineaSobregiro);
							}
						}
					}
				} else {
					LOGGER.error("No se pudo obtener el saldo de la cuenta asociada a cuenta paragua, cuenta nro: [{}]",
							(String) cuentaColaborativa.get("CUENTA"));
					estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);
				}
			}

		} else {
			LOGGER.error("Error al obtener cuentas colaborativas (modalidad cuenta paragua)");
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);
		}

		LOGGER.info("Saldo cuenta paragua: [{}], cuenta nro: [{}]", saldoDisponible, cuentaCargo);
		return saldoDisponible;
	}

	/**
	 * Inicializa el map con los parámetros de consulta e invoca al SP SP y devuelve
	 * saldo SP_BICE_ABONOS_CCLV.
	 * 
	 * @param rut, cuenta, estado @param @return abonosCclv @throws
	 */
	public BigDecimal obtenerAbonosCclv(String rut, String cuenta, Estado estado) {
		BigDecimal abonosCclv = new BigDecimal(0);
		try {
			String numeroRut = "";
			String numeroCta = "";
			numeroRut = "00000000000" + rut.trim();
			numeroRut = numeroRut.substring(numeroRut.length() - 11);
			numeroCta = "00000000000" + cuenta.trim();
			numeroCta = numeroCta.substring(numeroCta.length() - 14);

			Map<String, Object> paramsObtenerAbonoCclv = new HashMap<>();

			paramsObtenerAbonoCclv.put("Cuenta_rec", numeroCta);
			paramsObtenerAbonoCclv.put("rut_rec", numeroRut);
			paramsObtenerAbonoCclv.put("result2_msg", 0);

			LOGGER.info("OperacionesEmpresaService obtenerAbonosCclv: rutEmpresa[{}], cuenta[{}]", rut, cuenta);

			mdpRepository.obtenerAbonosCclv(paramsObtenerAbonoCclv);

			if (paramsObtenerAbonoCclv.get("RETURN_VALUE") != null)
				abonosCclv = abonosCclv.add(new BigDecimal((Integer) paramsObtenerAbonoCclv.get("RETURN_VALUE")));
			else {
				LOGGER.error("Error al obtener abonos CCLV");
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);
			}

		} catch (Exception e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);
		}

		return abonosCclv;
	}

	/**
	 * Inicializa el map con los parámetros de consulta e invoca al SP SP y devuelve
	 * saldo SP_BICE_PAGOS_CCLV.
	 * 
	 * @param rut, cuenta, estado @param @return pagosCclv @throws
	 */
	public BigDecimal obtenerPagosCclv(String rut, String cuenta, Estado estado) {
		BigDecimal pagosCclv = new BigDecimal(0);
		try {
			String numeroRut = "";
			String numeroCta = "";
			numeroRut = "00000000000" + rut.trim();
			numeroRut = numeroRut.substring(numeroRut.length() - 11);
			numeroCta = "00000000000" + cuenta.trim();
			numeroCta = numeroCta.substring(numeroCta.length() - 14);

			Map<String, Object> paramsObtenerPagoCclv = new HashMap<>();

			paramsObtenerPagoCclv.put("Cuenta_rec", numeroCta);
			paramsObtenerPagoCclv.put("rut_rec", numeroRut);
			paramsObtenerPagoCclv.put("result2_msg", 0);

			LOGGER.info("OperacionesEmpresaService obtenerPagosCclv: rutEmpresa[{}], cuenta[{}]", rut, cuenta);

			mdpRepository.obtenerPagosCclv(paramsObtenerPagoCclv);

			if (paramsObtenerPagoCclv.get("RETURN_VALUE") != null)
				pagosCclv = pagosCclv.add(new BigDecimal((Integer) paramsObtenerPagoCclv.get("RETURN_VALUE")));
			else {
				LOGGER.error("Error al obtener abonos CCLV");
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);
			}

		} catch (Exception e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);
		}

		return pagosCclv;
	}

	/**
	 * Obtiene datos empresa cclv
	 * 
	 * @param rutEmpresa
	 * @return params
	 */
	public List<Map<String, Object>> obtenerDatosCclv(String rutEmpresa) {
		Map<String, Object> params = new HashMap<>();
		params.put("v_RUT_EMPRESA", rutEmpresa);
		params.put("v_SALIDA", null);

		List<Map<String, Object>> salida = new ArrayList<>();

		try {
			portalOrawRepository.obtenerDatosCclv(params);
			salida = (List<Map<String, Object>>) params.get("v_SALIDA");
			if (!MapperUtil.isSalidaSPValida(salida)) {
				LOGGER.error("Error al obtener datos cclv para empresa rut: [{}]", rutEmpresa);
			} else if (params.get("v_OUT_COD_RESULT") == null
					|| !"0".equals(params.get("v_OUT_COD_RESULT").toString().trim())) {
				throw new BancaEmpresasException();
			}
		} catch (BancaEmpresasException | SQLException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
		}
		return salida;
	}

	/**
	 * Calcula saldo disponible CCLV
	 * 
	 * @param rut, cuenta, estado @param @return saldoCclv @throws
	 */
	public BigDecimal calcularSaldoCclv(LiberarOperacionesRequest request, String cuentaCargo, Estado estado) {
		BigDecimal saldoCclv = new BigDecimal(0);
		String rutEmpresa = request.getRutEmpresa();
		try {
			BigDecimal minimo1 = new BigDecimal(0);
			BigDecimal minimo2 = new BigDecimal(0);
			BigDecimal cupoTefCclv = new BigDecimal(0);
			BigDecimal saldoOv = new BigDecimal(0);
			BigDecimal abonosCclv = new BigDecimal(0);
			BigDecimal pagadoCclv = new BigDecimal(0);
			BigDecimal saldoTotalCuenta = new BigDecimal(0);
			Map<String, Object> datosCclv = new HashMap<>();

			datosCclv = obtenerDatosCclv(rutEmpresa) != null && !obtenerDatosCclv(rutEmpresa).isEmpty()
					? obtenerDatosCclv(rutEmpresa).get(0)
					: null;
			if (datosCclv == null) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);
				return saldoCclv;
			}

			cupoTefCclv = new BigDecimal(datosCclv.get("MONTO_DIARIO").toString());
			saldoOv = new BigDecimal(datosCclv.get("SALDO_DISP_OV").toString());
			abonosCclv = abonosCclv.add(obtenerAbonosCclv(rutEmpresa, cuentaCargo, estado));
			pagadoCclv = pagadoCclv.add(obtenerPagosCclv(rutEmpresa, cuentaCargo, estado));
			saldoTotalCuenta = saldoTotalCuenta.add(obtenerSaldoCuenta(request, cuentaCargo, estado));

			minimo1 = minimo1.add(cupoTefCclv).add(saldoOv).add(abonosCclv).subtract(pagadoCclv);
			minimo2 = minimo2.add(saldoTotalCuenta);

			if (minimo1.compareTo(minimo2) == -1)
				saldoCclv = saldoCclv.add(minimo1);
			else
				saldoCclv = saldoCclv.add(minimo2);

		} catch (Exception e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);
		}

		return saldoCclv;
	}

	/**
	 * Genera un resumen de operaciones a partir de la salida del SP.
	 * 
	 * @param listado
	 * @return Resumen de operaciones.
	 */
	public ResumenOperaciones generarResumenOperaciones(List<Map<String, Object>> listado) {

		ResumenOperaciones respuesta = new ResumenOperaciones();

		Map<String, TipoOperaciones> tipoOps = new HashMap<>();
		for (Map<String, Object> mapa : listado) {
			TipoOperaciones tipoOp = new TipoOperaciones();

			tipoOp.setCodServicio(serviciosService
					.filtrarCodigoServicio((String) MapperUtil.validaRespuesta(mapa.get("COD_SERVICIO"), true)));

			String glosaCodigoServicio = propiedadesExterna
					.getProperty("servicios.resumen.operaciones.glosa.cod.servicio." + tipoOp.getCodServicio());
			tipoOp.setGlosaCodigoServicio(glosaCodigoServicio != null ? glosaCodigoServicio : "");

			try {
				tipoOp.setAprobaciones(
						Integer.parseInt(MapperUtil.validaRespuesta(mapa.get("CANTIDADAPROBAR"), false)));
			} catch (NumberFormatException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				tipoOp.setAprobaciones(0);
			}

			try {
				tipoOp.setLiberaciones(
						Integer.parseInt(MapperUtil.validaRespuesta(mapa.get("CANTIDADLIBERAR"), false)));
			} catch (NumberFormatException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				tipoOp.setLiberaciones(0);
			}

			try {
				tipoOp.setTotalOperaciones(
						Integer.parseInt(MapperUtil.validaRespuesta(mapa.get("CANTIDADTOTAL"), false)));
			} catch (NumberFormatException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				tipoOp.setTotalOperaciones(0);
			}
			try {
				tipoOp.setTotalEmpresas(
						Integer.parseInt(MapperUtil.validaRespuesta(mapa.get("CANTIDADEMPRESAS"), false)));
			} catch (NumberFormatException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				tipoOp.setTotalEmpresas(0);
			}

			if (Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES.equals(tipoOp.getCodServicio())
					&& tipoOps.get(Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES) != null) {

				TipoOperaciones tipoOpTemp = tipoOps
						.get(Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES);
				tipoOpTemp.setCodServicio(Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES);
				tipoOpTemp.setAprobaciones(tipoOpTemp.getAprobaciones() + tipoOp.getAprobaciones());
				tipoOpTemp.setLiberaciones(tipoOpTemp.getLiberaciones() + tipoOp.getLiberaciones());
				tipoOpTemp.setTotalOperaciones(tipoOpTemp.getTotalOperaciones() + tipoOp.getTotalOperaciones());
				tipoOpTemp.setTotalEmpresas(tipoOpTemp.getTotalEmpresas() + tipoOp.getTotalEmpresas());

				tipoOps.remove(Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES);
				tipoOps.put(Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES, tipoOpTemp);
			} else {
				tipoOps.put(tipoOp.getCodServicio(), tipoOp);
			}
		}

		respuesta.setTipoOperaciones(new ArrayList(tipoOps.values()));
		return respuesta;
	}

	/**
	 * Suma los montos totales de las operaciones recibidas. Estos montos totales
	 * están agrupados por moneda.
	 * 
	 * @param listaOperaciones
	 * @return
	 */
	public Map<String, ResumenOperacionesMoneda> obtenerMontosTotalesPorMoneda(
			List<DetalleCampoValorTipoOperacion> listaOperaciones, String codigoServicio) {
		Map<String, ResumenOperacionesMoneda> map = new HashMap<>();
		List<String> listNumOperaciones;

		for (DetalleCampoValorTipoOperacion detalle : listaOperaciones) {
			String moneda = detalle.getValor10();
			if (null != moneda) {
				moneda = moneda.trim();
				ResumenOperacionesMoneda resumenOpMoneda;
				if (map.containsKey(moneda)) {
					resumenOpMoneda = map.get(moneda);
					listNumOperaciones = resumenOpMoneda.getListNumOperaciones();
					listNumOperaciones.add(detalle.getNumeroOperacion());
					resumenOpMoneda.setCantidadTotalOperaciones(resumenOpMoneda.getCantidadTotalOperaciones() + 1);
					resumenOpMoneda.setMontoTotalOperaciones(resumenOpMoneda.getMontoTotalOperaciones()
							.add(FormateadorUtil.formatBigDecimalfromString(detalle.getValor11())));
					resumenOpMoneda.setListNumOperaciones(listNumOperaciones);
				} else {
					listNumOperaciones = new ArrayList<>();
					listNumOperaciones.add(detalle.getNumeroOperacion());
					resumenOpMoneda = new ResumenOperacionesMoneda();
					resumenOpMoneda.setMonedaOperaciones(moneda);
					String codigoMonedaOperacion = obtenerValorCampoOperacion(detalle.getNumeroOperacion(),
							codigoServicio.equals(Constantes.CODIGO_SERVICIO_SPOTWEB) ? "22" : "6");
					resumenOpMoneda.setCodigoMonedaOperaciones(codigoMonedaOperacion);
					resumenOpMoneda.setCantidadTotalOperaciones(1);
					resumenOpMoneda
							.setMontoTotalOperaciones(FormateadorUtil.formatBigDecimalfromString(detalle.getValor11()));
					resumenOpMoneda.setListNumOperaciones(listNumOperaciones);
				}
				map.put(moneda, resumenOpMoneda);
			}
		}
		return map;
	}

	/**
	 * Retorna un valor del detalle de una operacion. El detalle de una operacion se
	 * obtiene de la tabla TBL_DETALLE_CAMP.
	 * 
	 * @param numOperProg
	 * @param codCampo
	 * @return
	 */
	public String obtenerValorCampoOperacion(String numOperProg, String codCampo) {
		LOGGER.info("OperacionesEmpresaService obtenerValorCampoOperacion: numOperProg[{}], codCampo[{}]", numOperProg,
				codCampo);
		String resultado = null;
		Map<String, Object> params = new HashMap<>();
		params.put("V_NUM_OPER_PROG", numOperProg);
		params.put("v_COD_RESULT", null);
		params.put("v_result", null);

		List<Map<String, Object>> salidaSP = new ArrayList<>();

		try {
			LOGGER.info(
					"OperacionesEmpresaService obtenerValorCampoOperacion: ejecuta SP POR_PKG_BANCADIGITAL_EMPRESAS.POR_SP_CON_OP_DETALLE");
			portalOrawRepository.obtenerDetalleOperacion(params);
			salidaSP = (List<Map<String, Object>>) params.get("v_result");
			if (!MapperUtil.isSalidaSPValida(salidaSP)) {
				LOGGER.error("Error al obtener el detalle de la operacion [{}]", numOperProg);
			} else if (params.get("v_COD_RESULT") == null
					|| !"1".equals(params.get("v_COD_RESULT").toString().trim())) {
				LOGGER.error("Error al obtener el detalle de la operacion [{}] v_COD_RESULT [{}]", numOperProg,
						params.get("v_COD_RESULT"));
				throw new BancaEmpresasException();
			} else {
				for (Map<String, Object> map : salidaSP) {
					if (null != map.get("COD_CAMPO")
							&& codCampo.trim().equals(String.valueOf(map.get("COD_CAMPO")).trim())) {
						resultado = String.valueOf(map.get("VAL_CAMPO"));
						break;
					}
				}
			}
		} catch (BancaEmpresasException | SQLException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
		}
		LOGGER.info("OperacionesEmpresaService obtenerValorCampoOperacion: resultado[{}]", resultado);
		return resultado;
	}
	
	/**
	 * Consulta contra el SP POR_SP_VAL_BLOQUEO_OPERPROG
	 * Para saber si una operación está siendo procesada (bloqueada).
	 * 
	 * @param nroOperacion
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String validaBloqueoOperaciones(String numOperacion, String codServicio, String codCampo, String rutEmpresa) throws BancaEmpresasException {
		String salida;

		Map<String, Object> params = new HashMap<>();
		params.put("I_NUM_OPER_PROG", numOperacion);
		params.put("I_COD_SERVICIO", codServicio);
		params.put("I_COD_CAMPO", codCampo);
		params.put("I_RUT_EMPRESA", rutEmpresa);

		try {
			portalOrawRepository.validaBloqueoOperaciones(params);
			salida = (String) params.get("O_IS_BLOQUEADA");
			String codResult = (String) params.get("O_SRV_STATUS");

			if (!MapperUtil.isCodResultSPValido(codResult)) {
				throw new NoEncontradoException();
			} else if (null == salida) {
				throw new ErrorStoredProcedureException();
			}
		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return salida;
	}
	
	/**
	 * Consulta si una operación está siendo procesada para aprobación
	 * 
	 * 
	 * @param nroOperacion
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean isProcesandoOperacionSp(String numOperacion, String codServicio, String codCampo, String rutEmpresa) throws BancaEmpresasException {
		try {
			String estadoOperacion = validaBloqueoOperaciones(numOperacion, codServicio, codCampo, rutEmpresa);

			if (null == estadoOperacion) {
				throw new BancaEmpresasException();
			}

			return ("S".equalsIgnoreCase(estadoOperacion.trim()));

		} catch (NoEncontradoException | ErrorStoredProcedureException e) {
			throw new BancaEmpresasException(e);
		}
	}
	
		public boolean actualizarTblOperProgMX(AprobarOperacionesRequest request, String inEstado, String inAccion,
			String numOperProg) {
		boolean resultado = true;
		ActualizaOpIn actualizaOpIn = new ActualizaOpIn();
		actualizaOpIn.setINESTADO(inEstado);
		actualizaOpIn.setINACCION(inAccion);
		actualizaOpIn.setINNUMOPERPROG(numOperProg);

		resultado = actualizarTblOperacionProg(actualizaOpIn, false);
		resultado = registraFirma(obtenerInDatoFirma(request), request.getNombreApoderado(), numOperProg,
				request.getRut(), false);

		return resultado;
	}

	public UsuarioCliente obtenerUsuarioCliente(String rutUsuario, String rutCliente) {
		LOGGER.info("OperacionesEmpresaService obtenerUsuarioCliente: rutUsuario[{}], rutCliente[{}]", rutUsuario,
				rutCliente);
		UsuarioCliente resultado = null;
		Map<String, Object> params = new HashMap<>();
		params.put("P_RUT_USU", rutUsuario);
		params.put("P_RUT_CLI", rutCliente);
		params.put("P_OUT_COD_RESULT", null);
		params.put("P_SALIDA", null);

		List<Map<String, Object>> salidaSP = new ArrayList<>();

		try {
			LOGGER.info(
					"OperacionesEmpresaService obtenerUsuarioCliente: ejecuta SP POR_PKG_BANCADIGITAL_EMPRESAS.GET_USUARIO_CLIENTE");
			portalOrawRepository.consultarTblUsuarioCliente(params);
			salidaSP = (List<Map<String, Object>>) params.get("P_SALIDA");
			if (params.get("P_OUT_COD_RESULT") == null || salidaSP == null || salidaSP.get(0).isEmpty()
					|| !"1".equals(params.get("P_OUT_COD_RESULT").toString().trim())) {
				LOGGER.error("Error al obtener UsuarioCliente P_OUT_COD_RESULT [{}]", params.get("P_OUT_COD_RESULT"));
				throw new BancaEmpresasException();
			} else {
				resultado = new UsuarioCliente();
				resultado.setRutUsuario(MapperUtil.validaRespuesta(salidaSP.get(0).get("RUT_USUARIO"), false));
				resultado.setRutCliente(MapperUtil.validaRespuesta(salidaSP.get(0).get("RUT_CLIENTE"), false));
				resultado.setFlgActivo(MapperUtil.validaRespuesta(salidaSP.get(0).get("FLG_ACTIVO"), false));
				resultado.setFlgAdmBanco(MapperUtil.validaRespuesta(salidaSP.get(0).get("FLG_ADM_BANCO"), false));
				resultado.setFlgAdmBancoSucursales(
						MapperUtil.validaRespuesta(salidaSP.get(0).get("FLG_ADM_BANCO_SUCURSALES"), false));
				resultado.setFlgAdmDelegado(MapperUtil.validaRespuesta(salidaSP.get(0).get("FLG_ADM_DELEGADO"), false));
				resultado.setFlgAdmDelegadoPersona(
						MapperUtil.validaRespuesta(salidaSP.get(0).get("FLG_ADM_DELEGADO_PERSONA"), false));
				resultado.setFlgDesarrollo(MapperUtil.validaRespuesta(salidaSP.get(0).get("FLG_DESARROLLO"), false));
				resultado.setFlgInicio(MapperUtil.validaRespuesta(salidaSP.get(0).get("FLG_INICIO"), false));
				resultado.setFlgProduccion(MapperUtil.validaRespuesta(salidaSP.get(0).get("FLG_PRODUCCION"), false));
				resultado.setFlgPrototipo(MapperUtil.validaRespuesta(salidaSP.get(0).get("FLG_PROTOTIPO"), false));
				resultado.setFlgSuperAdmBanco(
						MapperUtil.validaRespuesta(salidaSP.get(0).get("FLG_SUPER_ADM_BANCO"), false));
				resultado.setFlgApoderado(MapperUtil.validaRespuesta(salidaSP.get(0).get("FLG_APODERADO"), false));
			}
		} catch (BancaEmpresasException | SQLException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
		}

		return resultado;
	}

	public ValidaPoderesResult consultarTblValidaPoderes(String tipoPersona, String tipoUsuario) {
		LOGGER.info("OperacionesEmpresaService consultarTblValidaPoderes: tipoPersona[{}], tipoUsuario[{}]",
				tipoPersona, tipoUsuario);
		ValidaPoderesResult resultado = null;

		Map<String, Object> params = new HashMap<>();
		params.put("v_TIPO_PERSONA", tipoPersona);
		params.put("v_TIPO_USUARIO", tipoUsuario);
		params.put("v_OUT_COD_RESULT", null);
		params.put("v_SALIDA", null);

		List<Map<String, Object>> salidaSP = new ArrayList<>();

		try {
			LOGGER.info(
					"OperacionesEmpresaService consultarTblValidaPoderes: ejecuta SP POR_PKG_BANCADIGITAL_EMPRESAS.POR_SP_CON_VALIDA_PODERES");
			portalOrawRepository.consultarTblValidaPoderes(params);
			salidaSP = (List<Map<String, Object>>) params.get("v_SALIDA");
			if (params.get("v_OUT_COD_RESULT") == null || salidaSP == null || salidaSP.get(0).isEmpty()
					|| !"0".equals(params.get("v_OUT_COD_RESULT").toString().trim())) {
				LOGGER.error("Error al consultar tbl_validapoderes v_OUT_COD_RESULT [{}]",
						params.get("v_OUT_COD_RESULT"));
				throw new BancaEmpresasException();
			} else {
				resultado = new ValidaPoderesResult();
				resultado.setCodTipoUsuario(String.valueOf(salidaSP.get(0).get("COD_TIPOUSUARIO")));
				resultado.setCodValidaPoderes(Integer.parseInt(String.valueOf(salidaSP.get(0).get("COD_VALIDAPODERES"))));
				resultado.setFlgFirma(Integer.parseInt(String.valueOf(salidaSP.get(0).get("FLG_FIRMA"))));
				resultado.setFlgRegistro(Integer.parseInt(String.valueOf(salidaSP.get(0).get("FLG_REGISTRO"))));
				resultado.setFlgValidaLs(Integer.parseInt(String.valueOf(salidaSP.get(0).get("FLG_VALIDA_LS"))));
				resultado.setNumTipoPersona(Integer.parseInt(String.valueOf(salidaSP.get(0).get("NUM_TIPOPERSONA"))));
			}
		} catch (BancaEmpresasException | SQLException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
		}

		return resultado;
	}

	/**
	 * PARA MX operBac = obtenerValorCampoOperacion(numOperProg, "830");
	 */

	/**
	 * 
	 * @param numOperProg
	 * @param rutUsuario
	 * @param rutCliente
	 * @param codigoServicio
	 * @param firmantes
	 * @return 30 40 50
	 * @throws BancaEmpresasException
	 */
	public int verificaPoderesMx(String numOperProg, String rutUsuario, String rutCliente, String codigoServicio,
			String firmantes) throws BancaEmpresasException {
		String tipoPersona;
		String tipoUsuario;
		int resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;

		String moneda = obtenerValorCampoOperacion(numOperProg, "6");
		String monto = obtenerValorCampoOperacion(numOperProg, "7");
		String ctaCargo = obtenerValorCampoOperacion(numOperProg, "4");

		UsuarioCliente usuarioCliente = obtenerUsuarioCliente(rutUsuario, rutCliente);

		tipoUsuario = (usuarioCliente.isApoderado() ? "APO" : "DLG");// LA PERSONA QUE FIRMA PUEDE SER DELEGADO O
																		// APODERADO
		tipoPersona = (rutUsuario.equals(rutCliente) ? "5" : "3");// 3=persona juridica / 5=persona natural

		// EL CLIENTE ES EL QUE ESTA FIRMANDO
		if (rutUsuario.equals(rutCliente)) {
			resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_FIRMA_COMPLETA;
		} else {// EL CLIENTE ES PERSONA JURIDICA
			ValidaPoderesResult validaPoderesRes = consultarTblValidaPoderes(tipoPersona, tipoUsuario);
			if (validaPoderesRes.getFlgFirma() == 1 && validaPoderesRes.getFlgValidaLs() == 1) {
				String[] mandatarios = firmantes.split(",");

				ObjectFactory objectFactory = new ObjectFactory();
				Tpag2000RequestType tpag2000Request = objectFactory.createTpag2000RequestType();
				Tpag2000EntradaType tpag2000EntradaType = objectFactory.createTpag2000EntradaType();
				tpag2000EntradaType.setCanal((rutUsuario.equals(rutCliente) ? "IP" : "IE"));
				tpag2000EntradaType.setRutCliente(rutCliente);
				tpag2000EntradaType.setCodigoTipoCtaCargo(100);
				tpag2000EntradaType.setCuentaCargo(Long.parseLong(ctaCargo));
				tpag2000EntradaType.setMonto(Long.parseLong(monto));
				tpag2000EntradaType.setMoneda(moneda);
				tpag2000EntradaType.setCodigoServicio(Integer.parseInt(codigoServicio));
				tpag2000EntradaType.setNumeroOperacion(Long.parseLong(numOperProg));// esto no está en el portal
				tpag2000EntradaType.setRutApoderado1(FormateadorUtil.rellenarCero(mandatarios[0], 10));
				if (mandatarios.length >= 2)
					tpag2000EntradaType.setRutApoderado2(FormateadorUtil.rellenarCero(mandatarios[1], 10));
				else
					tpag2000EntradaType.setRutApoderado2("");
				if (mandatarios.length >= 3)
					tpag2000EntradaType.setRutApoderado3(FormateadorUtil.rellenarCero(mandatarios[2], 10));
				else
					tpag2000EntradaType.setRutApoderado3("");
				if (mandatarios.length >= 4)
					tpag2000EntradaType.setRutApoderado4(FormateadorUtil.rellenarCero(mandatarios[3], 10));
				else
					tpag2000EntradaType.setRutApoderado4("");

				tpag2000Request.setTpag2000Entrada(tpag2000EntradaType);

				resultado = obtenerResultadoTpag2000(validarTPAG2000(tpag2000Request));
			}
		}

		return resultado;
	}

	public int aprobarOperacionLBTRMX(String numOperProg, AprobarOperacionesRequest request)
			throws BancaEmpresasException {
		int resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		if (!clienteOperacionNomina.existeFirma(numOperProg, request.getRut())) {
			String rutFirmantes = clienteOperacionNomina.obtenerRutsFirmantes(numOperProg, request.getRut());
			resultado = verificaPoderesMx(numOperProg, request.getRut(), request.getRutEmpresa(),
					request.getCodigoServicio(), rutFirmantes);

			if (Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_FIRMA_COMPLETA == resultado) {
				actualizarTblOperProgMX(request,
						String.valueOf(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_FIRMA_COMPLETA), "120",
						numOperProg);
			} else if (Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL == resultado) {
				actualizarTblOperProgMX(request,
						String.valueOf(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL), "30", numOperProg);
			}
		}

		return resultado;
	}
	
	public BigDecimal obtenerMontoTotalesOperacionesSpotWeb(List<String> listNumOperaciones) {
		BigDecimal montoTotal = new BigDecimal(0);
		for (String nroOperacion : listNumOperaciones) {			
			String nroOpStr = obtenerValorCampoOperacion(nroOperacion, "7");
			montoTotal = montoTotal.add(FormateadorUtil.formatBigDecimalfromString(nroOpStr));
		}
		return montoTotal;
	}
	
	/**
	 * Para listarCrear desafíos cuando el codigo servicio es 2935 (Spot Web)
	 * Invoca al SP que obtiene las operaciones por aprobar/liberar.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param codigoServicio
	 * @param isListaAprobar
	 * @param canal
	 * @return Operaciones por aprobar/liberar.
	 * @throws SQLException
	 * @throws NoEncontradoException
	 * @throws ErrorStoredProcedureException
	 */
	public List<Map<String, Object>> obtenerOperacionesAprobarLiberarGenericoPortal(String rut, String rutEmpresa,
			String codigoServicio, boolean isListaAprobar, String canal) throws BancaEmpresasException {
		LOGGER.info(
				"OperacionesEmpresaService obtenerOperacionesAprobarLiberarGenericoPortal: rut[{}] rutEmpresa[{}] codigoServicio[{}] isListaAprobar[{}]",
				rut, rutEmpresa, codigoServicio, isListaAprobar);
		List<Map<String, Object>> salida;

		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU, rut);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_CLI, rutEmpresa);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_COD_SERVICIO, codigoServicio);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_ESTADO, isListaAprobar ? "A" : "L");
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA, null);

		try {
			portalOrawRepository.obtenerOperacionesAprobarLiberarGenericoPortal(params);

			salida = (List<Map<String, Object>>) params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA);
		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if (!MapperUtil.isSalidaSPValida(salida)) {
			throw new NoEncontradoException();
		}

		return salida;
	}
	
	/**
	 * Verifica si una operación está dentro del límite de hora válido para ser liberada
	 * 
	 * @param 
	 * @return boolean
	 * @throws BancaEmpresasException
	 */
	public boolean esHoraValida() {
		boolean esHoraValida = false;

		try {
			String fechaHoy = empresasService.fechaHoy(Constantes.FORMAT_YYYYMMDDHHMMSS);

			String horaHoy = fechaHoy.substring(8, 12);
			String horaCorteLiberacion = "";

			List<ParametrosVo> horaCorteParams = consultarParametrosService.consultaParametro("SERVICIOSLBTR",
					"HORALIMITEINGRESO");
			if (horaCorteParams != null && !horaCorteParams.isEmpty()) {
				horaCorteLiberacion = horaCorteParams.get(0).getValParametro() != null
						? horaCorteParams.get(0).getValParametro().trim()
						: "";
			}
			LOGGER.debug("Hora actual: [{}], Hora corte liberacion: [{}]", horaHoy, horaCorteLiberacion);
			if (Integer.valueOf(horaHoy).compareTo(Integer.valueOf(horaCorteLiberacion)) < 0) {
				esHoraValida = true;
			}

		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
		}

		LOGGER.debug("Hora de liberacion valida: [{}]", esHoraValida);
		return esHoraValida;

	}

}
