package cl.bice.banca.empresas.servicio.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.EntradaInvalidaException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.request.empresas.SaldoRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.ValidaPoderRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.empresas.DetalleCampoValorTipoOperacion;
import cl.bice.banca.empresas.servicio.model.response.empresas.OperacionesAprobar;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesResp;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesResponse;
import cl.bice.banca.empresas.servicio.model.response.saldo.SaldoResp;
import cl.bice.banca.empresas.servicio.repository.BiceValidaPoderRepository;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.soap.ClienteOperacionNomina;
import cl.bice.banca.empresas.servicio.util.BancaUtil;
import cl.bice.banca.empresas.servicio.util.EmailUtil;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.banca.empresas.servicio.util.FormateadorUtil;
import cl.bice.banca.empresas.servicio.util.MapperUtil;
import cl.bice.banca.empresas.servicio.util.OperacionesEmpresaUtil;
import cl.bice.nominas.ws.CargoOnlineOut;
import cl.bice.nominas.ws.ConsultaApoOpOut;
import cl.bice.nominas.ws.ConsultaParametrosOut;
import cl.bice.nominas.ws.FirmaOpVo;
import cl.bice.nominas.ws.ParametrosVo;
import cl.bice.nominas.ws.RegNomiCargoLineaVo;
import cl.bice.nominas.ws.RegistraApoOpOut;
import cl.bice.nominasenlinea.ws.RevAprobarNomLinOut;

/**
 * Clase con métodos para manejar las operaciones de una empresa.
 * 
 * @author Tinet
 */
@Service
public class NominasDiferidasService {
	private static final Logger LOGGER = LoggerFactory.getLogger(NominasDiferidasService.class);
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
	FacultadService facultadService;

	@Autowired
	ValidacionService validacionService;
	
	@Autowired
	EmpresasService empresasService;
	
	@Autowired
	ConsultarParametrosService consultarParametrosService;
	
	@Autowired
	OperacionesEmpresaService operacionesEmpresaService;

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
	CuentaService cuentaService;
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	NominasEnLineaService nominasEnLineaService;
	
	@Autowired
	ParametrosValidacionService parametrosValidacionService;


	private static final String NUM_OPER_PROG = "NUM_OPER_PROG";
	private static final String SP_VALIDA_PODER_FLAG_MANDATARIOS_FACULTADES_Y_MONTOS = "3";
	private static final String TIPO_ERROR_MAIL = "APPEMPRESA-ERR-ACTUALIZAAPROB";

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
	public int tienePoderEmpresa(ValidaPoderRequest request,String nroCuentaCargo, String monto)
			throws BancaEmpresasException {
		LOGGER.info("OperacionesEmpresaService tienePoderEmpresa: rut[{}] rutEmpresa[{}] numeroOperacion[{}]",
				request.getRut(), request.getRutEmpresa(), request.getNumeroOperacion());
		int resultado;

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
			resultado = operacionesEmpresaService.validaPoderSPPRValidacionPoderes(request, codigoFacultad);
		} else {
			resultado = operacionesEmpresaService.validaPoderTPAG2000(request);
		}

		return resultado;
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
		ConsultaApoOpOut consultaApoOpOut = clienteOperacionNomina.consultarFirmasOperacion(numOperProg);
		List<FirmaOpVo> listaFirmasOpDto = consultaApoOpOut.getFirmaOpDto();

		for (FirmaOpVo firmaOpDto : listaFirmasOpDto) {
			String rutApoderado = FormateadorUtil.rutSinCero(firmaOpDto.getRutApoderado());
			if (!rutFirmante.trim().equals(rutApoderado.trim())) {
				resultado.append(separador).append(FormateadorUtil.rutSinCero(firmaOpDto.getRutApoderado()));
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
			if (operacionesEmpresaService.isProcesandoOperacion(nroOperacion)) {
				LOGGER.warn("La operación nro: [{}] ya está siendo procesada." , nroOperacion);
				return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
			}
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		}
		
		//se obtienen valores operacion campo-valor de la salida del sp.
		List<Map<String, Object>> mapOperacionesSp = new ArrayList<>(); 
		mapOperacionesSp = mapOperaciones.getMapOutputSP();
		Map<String, Object>mapOperacionSp = new HashMap<>();
		
		for (Map<String, Object> map : mapOperacionesSp) {
			if(map.get(Constantes.NUM_OPER_PROG).toString().trim().equals(nroOperacion)) {
				mapOperacionSp = map;
				break;				
			}
		}
		
		if(mapOperacionSp == null || mapOperacionSp.isEmpty()) {
			LOGGER.warn("No se pudo aprobar operación. Operación N°: [{}] no pertenece a lista por aprobar", nroOperacion);
			resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
			return resultado;
		}
		
		String detalleInSoap = Constantes.ACTUALIZAR_DETALLE_OPERACION_DETALLE_PROCESANDO;
		boolean resActualizaDetalleOp = operacionesEmpresaService.actualizarDetalleOperacion(
				detalleInSoap, nroOperacion, false);
		if (!resActualizaDetalleOp)
			return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		
		try {
			
			Map<String, String> valoresNominaDiferida = obtenerValoresNomDiferida(mapOperaciones, operacionesEmpresaService.crearValidaPoderRequest(request, nroOperacion));
			
			String cuentaCargo = valoresNominaDiferida.get(Constantes.CUENTA_CARGO);
			String monto = valoresNominaDiferida.get(Constantes.MONTO);
			String numNomina = valoresNominaDiferida.get(Constantes.NUM_NOMINA);
			
	        //valida contrato cliente para operar con Cargo En Liena, dato el Rut Cliente, Cta Cargo o IdNomina
			boolean cargoEnLinea = clienteOperacionNomina.operaCargoOnline(request.getRutEmpresa(), cuentaCargo);
//	        cargoOn = nomDif.operaConCargoOnline(id_sesion, rut_cli, null,idNom);
	        
	        if (!isFechaCaduca(request.getSessionID(), numNomina, cargoEnLinea)) {
	            LOGGER.warn("NominasDiferidasService.aprobarNomina  :  "
	            		+ "LA NOMINA NRO: [{}] NO SE PUEDE FIRMAR POR FECHA CADUCADA. CARGO EN LÍNEA: [{}]", numNomina, cargoEnLinea);
	    		operacionesEmpresaService.actualizarDetalleOperacion( Constantes.ACTUALIZAR_DETALLE_OPERACION_DETALLE_NO_PROCESANDO, nroOperacion, false);
	            return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
	        }
			
			// Valida firma - Control para validar si la nomina ya contiene una firma del apoderado
			if (clienteOperacionNomina.existeFirma(nroOperacion, request.getRut())) {
				LOGGER.warn("El usuario [{}] ya firmo la nomina nro: [{}], nroOperacion: [{}]", request.getRut(), numNomina, nroOperacion);
				resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
			} else {
				
				resultado = tienePoderEmpresa(operacionesEmpresaService.crearValidaPoderRequest(request, nroOperacion), cuentaCargo, monto);
				
				if (Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR != resultado) {
					int firmasPermitidas = 1;
					int firmas = 0;
					String codEstado;
					String codAccion;
					
					if(registraFirma(operacionesEmpresaService.obtenerInDatoFirma(request), request.getNombreApoderado(), nroOperacion, request.getRut(),	true)) {
						try {
							List<Map<String, Object>> datosNomina = obtenerDatosNominaDiferida(numNomina);
							int tipoNomina = Integer.valueOf(datosNomina.get(0).get("COD_TIPONOMINA").toString());
							int formatoNomina = Integer.valueOf(datosNomina.get(0).get("COD_FORMATO").toString());
							String nomTitCtaCargo = (String)datosNomina.get(0).get("NOM_TITCTACARGO");
							String fecCurse = (String)datosNomina.get(0).get("FEC_CURSE");
							String codEstadoOriginal = datosNomina.get(0).get("COD_ESTADO").toString();
							String codAccionOriginal = datosNomina.get(0).get("COD_ACCION").toString();
							
							//obtener cantidad firmas
							ConsultaApoOpOut consultaFirmasResponse = clienteOperacionNomina.consultarFirmasOperacion(nroOperacion);
							if(consultaFirmasResponse != null) 
								firmas = consultaFirmasResponse.getCodEstado();
							
							//si el estado de la nómina es 30, se verifica que sea la primera firma
							if(codEstadoOriginal.equals(String.valueOf(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR))) {
								if(firmas > firmasPermitidas) {
									//eliminar firma
									LOGGER.warn("Existe más de una firma, se elimina firma, rut apo: [{}], rut empresa: [{}], nro operación: [{}]", 
											request.getRut(), request.getRutEmpresa(), nroOperacion);
									resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
									clienteOperacionNomina.eliminaFirma(nroOperacion, request.getRut());
								}
								
							}else {//si el estado de la nómina es 40, Valida hora de firma - Control firma simultánea
								int resultadoFirmaSimultanea = nominasEnLineaService.checkearFirmasSimultaneas(nroOperacion, request.getRut());
								LOGGER.info("checkearFirmasSimultaneas - usuario {} Nomina #{} resultado: {}",
										request.getRut(), numNomina, resultadoFirmaSimultanea);

								if (resultadoFirmaSimultanea == 1) {
									LOGGER.warn("Firma simultánea, se elimina firma, rut apo: [{}], rut empresa: [{}], nro operación: [{}]", 
											request.getRut(), request.getRutEmpresa(), nroOperacion);
									resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
									clienteOperacionNomina.eliminaFirma(nroOperacion, request.getRut());
								}
							}
							
							if (Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_FIRMA_COMPLETA == resultado) {
								codAccion = "70";
                                codEstado = "50";
								if(clienteOperacionNomina.operaCargoOnline(request.getRutEmpresa(), cuentaCargo)){
									
                    				String inDatoFirmaLiberar = operacionesEmpresaService.obtenerInDatoFirma("", request.getCanal(), request.getIp());
                    				String detalleInSoapCanal = Constantes.COD_CAMPO_CANAL_LIBERACION + "=" + inDatoFirmaLiberar + ";";
                    				operacionesEmpresaService.actualizarDetalleOperacion(detalleInSoapCanal, nroOperacion, false);
									
                            		//Se valida que la nomina este dentro de los formatos soportadados
									ConsultaParametrosOut paramResponse = clienteOperacionNomina.consultaParamNomina("NOMINAS", "FORMATOS_CARGO_LINEA");
									if(paramResponse.getCodEstado() != 0) {
										boolean formatoNomiSoportado = formatoNomiSoportado(paramResponse.getParametrosVo(), String.valueOf(tipoNomina),  String.valueOf(formatoNomina));
										if(formatoNomiSoportado) {
											LOGGER.info("Se inicia flujo cargo en línea, rut apo: [{}], rut empresa: [{}], nro operación: [{}], nro nómina: [{}]",
                									 request.getRut(), request.getRutEmpresa(), nroOperacion, numNomina);
											//se valida si esta operativo GFS, es SI esta operativo o NO
                                			String control = valControlGfs(clienteOperacionNomina.consultaParamValidacion("GFS12C", "CONTROL"));
                                			if(!"".equals(control) || !control.isEmpty()){
                    							LOGGER.warn("[{}], se elimina firma, rut apo: [{}], rut empresa: [{}], nro operación: [{}]", 
                    									control, request.getRut(), request.getRutEmpresa(), nroOperacion);
                    							resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
                    							clienteOperacionNomina.eliminaFirma(nroOperacion, request.getRut());
                    							operacionesEmpresaService.actualizarDetalleOperacion(Constantes.ACTUALIZAR_DETALLE_OPERACION_DETALLE_NO_PROCESANDO, nroOperacion, true);
                                				return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
                                			}
                                			
                                			Estado estadoTemp =new Estado(EstadoEnum.EXITO.getCodigoEstado(),EstadoEnum.EXITO.getParametroGlosaEstado());
	                                		String saldoCtaCte = obtenerSaldoCuenta(request, cuentaCargo, estadoTemp).toString();
	                                		//se valida obtención de saldo
	                                		if(!estadoTemp.isEXITO()) {
	                  							LOGGER.warn("[{}], se elimina firma, rut apo: [{}], rut empresa: [{}], nro operación: [{}]", 
                    									control, request.getRut(), request.getRutEmpresa(), nroOperacion);
                    							resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
                    							clienteOperacionNomina.eliminaFirma(nroOperacion, request.getRut());
                    							operacionesEmpresaService.actualizarDetalleOperacion(Constantes.ACTUALIZAR_DETALLE_OPERACION_DETALLE_NO_PROCESANDO, nroOperacion, true);
                                				return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
	                                		}
                                			
	                                		String id_sesion = request.getSessionID();
	                                		Map<String, String> cargoAplicado;
											cargoAplicado = aplicaCargosNomina(id_sesion, request.getRutEmpresa(), cuentaCargo, numNomina, saldoCtaCte, nomTitCtaCargo, 
													request.getRut(), request.getNombreApoderado(), FormateadorUtil.formatlongAMonto(Long.parseLong(monto)), fecCurse , tipoNomina);
										
                            			
	                                		LOGGER.info("NominasDiferidasService.aprobarOperacion  :  cargoAplicado : "+cargoAplicado.get("info"));
	                                        
	                                        // 1 y 0 son codigo de exito
	                                        if(!("0".equals(cargoAplicado.get("estado")) || "1".equals(cargoAplicado.get("estado")))){
	                                        	LOGGER.warn(!"".equals((String)cargoAplicado.get("mensaje_error")) ? (String)cargoAplicado.get("mensaje_error")
	                                          			: "Ha ocurrido un problema en procesar esta nomina.");
	                                        	clienteOperacionNomina.eliminaFirma(nroOperacion, request.getRut());
	                                        	operacionesEmpresaService.actualizarDetalleOperacion(Constantes.ACTUALIZAR_DETALLE_OPERACION_DETALLE_NO_PROCESANDO, nroOperacion, true);
	                                        	return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
	                                        }
	                                        
	                                        LOGGER.info("Fin flujo cargo en línea, rut apo: [{}], rut empresa: [{}], nro operación: [{}], nro nómina: [{}]",
                									 request.getRut(), request.getRutEmpresa(), nroOperacion, numNomina);
	                                        
										}
										
									}
                            		
								}
								
							}else {
								codAccion = "30";
                                codEstado = "40";
							}
							
							//Se actualiza estado en tbl_num_oper_prog y tbl_nominasenviadas
							if (clienteOperacionNomina.actualizaNominaEnv(numNomina, codEstado, codAccion)) {
								LOGGER.info("Nómina diferida nro: [{}] aprobada exitosamente. Rut apo: [{}], rut empresa: [{}], nro operación: [{}]",
										numNomina, request.getRut(), request.getRutEmpresa(), nroOperacion);
							} else {
								// Revierto estado en tbl_num_oper_prog y tbl_nominasenviadas, elimino firma
								 try {
								LOGGER.warn("Nómina diferida nro: [{}]. No fue posible registrar en Operaciones en Progreso. Rut apo: [{}], rut empresa: [{}], nro operación: [{}]",
										numNomina, request.getRut(), request.getRutEmpresa(), nroOperacion);
								
								clienteOperacionNomina.actualizaNominaEnv(numNomina, codEstadoOriginal, codAccionOriginal);
								clienteOperacionNomina.eliminaFirma(nroOperacion, request.getRut());

								 } catch (BancaEmpresasException e) {
									LOGGER.error(
											"Error al revertir estado nómina diferida nro: [{}], nroOperacion: [{}]. Revierte estado ERROR : [{}]", 
											numNomina, nroOperacion, e.getMessage());
								}
							}
							
						}catch(Exception e) {
							LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
							resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
							clienteOperacionNomina.eliminaFirma(nroOperacion, request.getRut());
							throw new BancaEmpresasException();
						}
						
					}else {
						LOGGER.warn("No se pudo registrar la firma, rut apo: [{}], rut empresa: [{}], nro operación: [{}]", 
								request.getRut(), request.getRutEmpresa(), nroOperacion);
						resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
					}

				}else {
					LOGGER.warn("No tiene facultades para la firma, rut apo: [{}], rut empresa: [{}], nro operación: [{}]", 
							request.getRut(), request.getRutEmpresa(), nroOperacion);
					resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
				}
						
			}
			
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		}

		//Fin del proceso. Se deja operación con estado apta para procesamiento
		operacionesEmpresaService.actualizarDetalleOperacion(Constantes.ACTUALIZAR_DETALLE_OPERACION_DETALLE_NO_PROCESANDO, nroOperacion, true);

		return resultado;
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
	private boolean registraFirma(String inDatoFirma, String inNomApoderado, String inNumOperProg, String inRutApoderado,
			boolean enviarMailLogError) {
		boolean retorno = false;
		try {
			
			RegistraApoOpOut registraFirmaResp = clienteOperacionNomina.registraFirma(inDatoFirma, inNomApoderado, inNumOperProg, inRutApoderado);
			if(registraFirmaResp != null) 
				retorno = true;
			
			
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			if (enviarMailLogError)
				enviarMailLogError(inNumOperProg, EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES.getCodigoEstado(),
						estadoService.obtenerGlosaEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES) + " "
								+ propiedadesExterna.getProperty("texto.error.registrar.firma.aprobar.operaciones"),
						TIPO_ERROR_MAIL);
		}
		return retorno;
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
	private void enviarMailLogError(String nroOperacion, String codError, String glosaError, String tipo) {
		try {
			mailService.enviarMailCodGlsError(nroOperacion, codError, glosaError, tipo);

		} catch (BancaEmpresasException e1) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e1);
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
	public AprobarOperacionesResponse aprobarNominasDiferidas(AprobarOperacionesRequest request, Estado estado) {
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
					int resultadoAprobar = aprobarOperacion(nroOperacion, request, mapOperaciones);

					LOGGER.debug("NominasDiferidasService aprobarNominasDiferidas: nroOperacion[{}] codigoEstado[{}]",
							nroOperacion, resultadoAprobar);

					switch (resultadoAprobar) {
					case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR:
						operacionesNoAprobadas.add(nroOperacion);
						break;
					case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL:
						operacionesConFirmaParcial.add(nroOperacion);
						break;
					default:
						LOGGER.debug("NominasDiferidasService aprobarNominasDiferidas operacion [{}] con firma completa",
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
	 * Se obtienen los valores de la nómina diferida
	 * 
	 * @param mapOperaciones, request
	 * @return resultado
	 * @throws RequestInvalidoException 
	 */
	public Map<String, String> obtenerValoresNomDiferida(MapOperaciones mapOperaciones, ValidaPoderRequest request) throws RequestInvalidoException {
		Map<String, String> resultado = new HashMap<>();
		String nroCuentaCargo;
		String monto;
		String nroNomina;
		try {
			nroCuentaCargo = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones,
					request.getNumeroOperacion(), "NomDifNroCuentaCargo", true);
			monto = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, request.getNumeroOperacion(),
					"NomDifMonto", true);
			nroNomina = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, request.getNumeroOperacion(),
					"NomDifNumNomina", true);
			
			resultado.put(Constantes.CUENTA_CARGO, nroCuentaCargo != null ? nroCuentaCargo : "");
			resultado.put(Constantes.MONTO, monto != null ? monto : "");
			resultado.put(Constantes.NUM_NOMINA, nroNomina != null ? nroNomina : "");
			
			return resultado;
			
		} catch (NoEncontradoException e) {
			throw new RequestInvalidoException(e);
		}
	
	}
	
	/**
	 * @param data
	 * @return
	 */
	public boolean formatoNomiSoportado(List <ParametrosVo> parametrosNomina, String tipo, String formato){
		
		LOGGER.info("NominasDiferidasService.formatoNomiSoportado tipo: ["+ tipo + "] formato ["+formato+"]");
		String listFormat= "",listTipos="";
		boolean operaOn = true;
		try{		
			if(!parametrosNomina.isEmpty()){
				ParametrosVo parametrosVo = parametrosNomina.get(0);
				listFormat = parametrosVo.getValParametro();
				listTipos = parametrosVo.getValParametro2();
				//valida
				if(listFormat.indexOf(formato) < 0 || listTipos.indexOf(tipo) < 0){
					operaOn = false;
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		LOGGER.info("NominasDiferidasService.formatoNomiSoportado operaOn " +operaOn);
		return operaOn;
	}
	
	/**
	 * Retorna lso datos de una nomina diferida.
	 * 
	 * @param numOperProg
	 * @return
	 * @throws BancaEmpresasException
	 */
	public List<Map<String, Object>> obtenerDatosNominaDiferida(String numNomina) throws BancaEmpresasException {
		List<Map<String, Object>> salida;

		Map<String, Object> params = new HashMap<>();
		
		params.put("v_NUM_NOMINA", numNomina);
		params.put("v_SALIDA", null);

		try {
			portalOrawRepository.obtenerDatosNominaDiferida(params);
			salida = (List<Map<String, Object>>) params.get("v_SALIDA");

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return salida;
	}
	
	/**
	 * @param control
	 * @return
	 */
	public String valControlGfs(ConsultaParametrosOut control){
		String msgcontrol="";
		if(control.getCodEstado() == 0 ) {
			List <ParametrosVo> parametrosVoList = control.getParametrosVo();
			try{
			
				if(!parametrosVoList.isEmpty()){
					ParametrosVo parametrosVo = parametrosVoList.get(0);
					if(!"S".equalsIgnoreCase(parametrosVo.getValParametro())){
						msgcontrol += parametrosVo.getValParametro2()+parametrosVo.getValParametro3()+parametrosVo.getValParametro4();
					}
				}
				LOGGER.info("NominasDiferidasService.valControlGfs : ["+msgcontrol+"]");
			}catch(Exception e){
				LOGGER.error("NominasDiferidasService.valControlGfs Error al obtener Control Ejecucion GFS Nominas Diferidas");
			}
		}
		return msgcontrol;
	}
	
	/**
	 * Obtiene saldo disponible de una cuenta
	 * 
	 * @param request, cuentaCargo, estado
	 * @return BigDecimal
	 * @throws 
	 */
	public BigDecimal obtenerSaldoCuenta(AprobarOperacionesRequest request, String nroCuenta, Estado estado) {
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
		Estado estadoTemp = new Estado(EstadoEnum.EXITO.getCodigoEstado(),EstadoEnum.EXITO.getParametroGlosaEstado());
		saldoResp = cuentaService.obtenerSaldo(monedaFormateada, saldoRequest, estadoTemp);
		if(saldoResp != null && estadoTemp.isEXITO()) 
			if(saldoResp.getMonto() != null) 
				saldoDisponible = saldoDisponible.add(new BigDecimal(saldoResp.getMonto().trim()));

		return saldoDisponible;
	}
	
	
	/**
	 *  status : 0 EXITO
	 *	Error error generico: -1
	 *	Error reversa gfs: -2 Obtiene mensaje respuesta para presentar al cliente, enviar mail de alerta
	 *	Error error gfs : -3
	 *	Enviar Mail error cartola: -8 Se debe definir mensaje
	 *	Enviar mail a lista alertanomidif@bice.cl por CARGO APLICADO: -9  se debe parsear mensaje retorno, viene mensaje error
	 *	Enviar mail ejecutivo por saldo: -10
	 * @param idSesion
	 * @param rutCliente
	 * @param numCtaCArgo
	 * @param numNomina
	 * @param saldoDisp
	 * @return
	 * @throws Exception 
	 */
	public Map<String, String> aplicaCargosNomina(String idSesion,String rutCliente,String numCtaCArgo,String numNomina, String saldoDisp, String nomCli,
			String rutApoderado,String nomApoderado, String mtoNom, String fecPago, int codTipNom) throws Exception{
	
		LOGGER.info("["+idSesion+"]NominasPagosWsClient.aplicaCargosNomina INI rutCliente["+rutCliente+"] numCtaCArgo["+numCtaCArgo+"] numNomina["+numNomina+"]"
				+ "saldoDisp["+saldoDisp+"] nomCli["+nomCli+"] rutApoderado["+rutApoderado+"] nomApoderado["+nomApoderado+"] mtoNom["+mtoNom+"] fecPago ["+fecPago+"] "
						+ "codTipNom["+codTipNom+"]");
		
		Map<String, String> status = new HashMap<>();
		
		
		/* mensaje_error: contiene mensaje a presentar al cliente
		 * info : Informacion interna, impirime log
		 * estado : codigo retornado invocaicion servicio
		 * */
		
		try {
			CargoOnlineOut response = clienteOperacionNomina.aplicaCargoOnline(numCtaCArgo, numNomina, rutCliente, saldoDisp);
			
			LOGGER.info("["+idSesion+"]NominasPagosWsClient.aplicaCargosNomina response: " + response.getCodEstado() + 
					" codError["+response.getCodError()+"] Mensaje["+response.getMsgResult()+"]");
			
			List<RegNomiCargoLineaVo> data = response.getRegNominas();
			String mailFromTblValidacion = parametrosValidacionService.getParamValidacion("ENVIOMAILAVISOLBTRFROM", "", Constantes.VAL_PARAMETRO);
			String remitente = !mailFromTblValidacion.isEmpty() ? mailFromTblValidacion : propiedadesExterna.getProperty(Constantes.FROM_EMAIL, "mobile@bice.cl");
			
			switch(response.getCodEstado()){
			case 0: //Ejecucion Exitosa, antes validar si tiene Cargo en linea
				if("SIN_CARGO_ONLINE".equals(response.getCodError())){
					status.put("estado", "0"); //No tiene cargo en linea para aplicar
					status.put("info", "No tiene cargo en linea para aplicar");
				}else{
					status.put("estado", "1"); //Cargos aplicados ok
					status.put("info", "Cargos aplicados OK");
				}
				break;
			case -1: //Error generico
				status.put("estado", "-1");
				status.put("info", "Error Generico");
				status.put("mensaje_error", "Ha ocurrido un problema en procesar esta nomina.");				
				break;
			case -2: //Error Reversa GFS 
				status.put("estado", "-1");
				status.put("info", "Reversa GFS");
				String[] data2 = response.getMsgResult().split("\\|");
				status.put("mensaje_error", "Ha ocurrido un problema en procesar esta nomina.");
//				Map<String, String>  dat2 = emailUtil.preparaMsgMail(idSesion, data2[0]);
				String tipoNom = tipNomina(codTipNom);
				emailUtil.sendMailErrorReversaGFS(rutCliente, numCtaCArgo, FormateadorUtil.formatlongAMonto(Long.parseLong(data2[1].trim())), data2[0], 
						mtoNom, fecPago, tipoNom, tipAbonos(data), getParamNomina("ENVIO_MAIL", "ERROR_REVERSA_GFS", Constantes.VAL_PARAMETRO),
						nomCli, remitente);
				break;
			case -3: //Error controlado GFS obtengo mensaje correspondiente, desde tabla parametros
				status.put("estado", "-3");
				status.put("info", "Error GFS");
				status.put("mensaje_error", response.getMsgResult());
				break;
			case -4: //EDTI : -4  		 muestra mensaje y envia mail alert
				status.put("estado", "-4");
				status.put("info", "Error GFS");
				status.put("mensaje_error", response.getMsgResult());
				emailUtil.sendMailErrorEDTI(rutCliente, numCtaCArgo, numNomina, mtoNom, 
						getParamNomina("ENVIO_MAIL", "ERROR_REVERSA_GFS", Constantes.VAL_PARAMETRO), nomCli, remitente);
				break;
			case -5: //DE-TUD-037 : -5  muestra mensaje y envia mail alert
				status.put("estado", "-5");
				status.put("info", "Error GFS");
				status.put("mensaje_error", response.getMsgResult());
				emailUtil.sendMailErrorDETUD037(rutCliente, numCtaCArgo, numNomina, mtoNom, getParamNomina("ENVIO_MAIL", "ERROR_REVERSA_GFS", Constantes.VAL_PARAMETRO), nomCli, remitente);
				break;
			case -6: //AC-VAC0 : -6 	 muestra mensaje y envia mail alert ejecutivo
				status.put("estado", "-6");
				status.put("info", "Error GFS");
				status.put("mensaje_error", response.getMsgResult());
				emailUtil.sendMailErrorACVAC07(rutCliente, numCtaCArgo, numNomina, mtoNom, nomCli, remitente);
				break;
			case -8: //Error ejecucion Cartola TCMW0025
				status.put("estado", "-8");
				status.put("mensaje_error", response.getMsgResult());
				status.put("info", "Error ejecucion Cartola Provisoria");
				status.put("mensaje_error", response.getMsgResult().split("\\|")[1]);
				//Ants de preparar datos de envio mail, si parametro Lista distribucion esta inactiva No envio Mail
				if(!"".equals(getParamNomina("ENVIO_MAIL", "ENVIO_MAIL_TCMW0025", Constantes.VAL_PARAMETRO))){
					
					Map<String, String> mto8 = cargosEnLinea(data);
					
					emailUtil.sendMailErrorCartolaProv(rutCliente, numNomina ,rutApoderado, fecPago, mtoNom, 
							FormateadorUtil.formatlongAMonto(Long.parseLong(mto8.get("total").toString())),
							FormateadorUtil.formatlongAMonto(Long.parseLong(mto8.get("monto1").toString())),
							FormateadorUtil.formatlongAMonto(Long.parseLong(mto8.get("monto2").toString())),
							tipNomina(codTipNom),
							tipAbonos(data), 
							getParamNomina("ENVIO_MAIL", "ERROR_TCMW0025", Constantes.VAL_PARAMETRO),
							nomCli, remitente);
					
				}
				break;
			case -9: //Cargo Aplicado
				status.put("estado", "-9");
				String[] msg_result = response.getMsgResult().split("\\|");
				//el primer valor tiene movimiento cartola provisoria, el segundo el mensaje
				status.put("mov_cartola", msg_result[0]);
				status.put("mensaje_error", msg_result[1]);
				status.put("info", "Cargo duplicados");
				//Se debe enviar mail alerta 
				Map<String, String>  dat9 = emailUtil.preparaMsgMail(idSesion, msg_result[0]);
				
				if(!dat9.isEmpty()){
					emailUtil.sendMailCargoDuplicado(rutCliente, nomCli, rutApoderado, nomApoderado,
							(String)dat9.get("ref"), 
							mtoNom, 
							fecPago, 
							(String)dat9.get("tipNom"), 
							FormateadorUtil.formatlongAMonto(Long.parseLong((String)dat9.get("mtoCargo"))), 
							"VISTA".equals((String)dat9.get("tipAbono")) ? "Vale Vista" : "Abonos "+(String)dat9.get("tipAbono"),
							(String)msg_result[0],getParamNomina("ENVIO_MAIL", "ERROR_CARGO_APLICADO", Constantes.VAL_PARAMETRO), remitente);
				}
				break;
			case -10: //mail ejecutivo Por Saldo insuficiente
				status.put("estado", "-10");
				String[] msgSinSaldo = response.getMsgResult().split("\\|");
				status.put("mensaje_error", msgSinSaldo[1]);
				status.put("info", "Saldo insuficiente " + msgSinSaldo[0]);
				break;
			}
		} catch (Exception e) {			
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new BancaEmpresasException();
		}
		return status;
	}
	
	/*
	 * @param data
	 * @return
	 */
	public String tipAbonos(List<RegNomiCargoLineaVo> data){
		
		String tipNomCarg = "";
		try{
			
			for (int i = 0; i < data.size(); i++) {
				
				RegNomiCargoLineaVo vo = data.get(i);
				String glosa = vo.getGlsGlosa();
				tipNomCarg+= glosa.substring(glosa.indexOf("-")+1,glosa.length())+"/";
				
			}			
			tipNomCarg = tipNomCarg.endsWith("/") ? tipNomCarg.substring(0, tipNomCarg.length()-1)  : tipNomCarg;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return tipNomCarg;
	}
	
	/**
	 * @param data
	 * @return
	 */
	public String getParamNomina(String nomParam,String nomTipo, String valParametro){
		
		LOGGER.info("NominasDiferidasService.getParamNomina nomParam: ["+ nomParam + "] nomTipo ["+nomTipo+"]");
		String retorno = "";
		try{		
			ConsultaParametrosOut consultaParametrosNomina = clienteOperacionNomina.consultaParamNomina(nomParam, nomTipo);
			List<ParametrosVo> parametrosNomina = consultaParametrosNomina.getParametrosVo();
			if(!parametrosNomina.isEmpty()){
				ParametrosVo parametrosVo = parametrosNomina.get(0);
				
				switch(valParametro) {
				case Constantes.VAL_PARAMETRO:
					retorno = retorno + parametrosVo.getValParametro();
					break;
				case Constantes.VAL_PARAMETRO2:
					retorno = retorno + parametrosVo.getValParametro2();
					break;
				case Constantes.VAL_PARAMETRO3:
					retorno = retorno + parametrosVo.getValParametro3();
					break;
				case Constantes.VAL_PARAMETRO4:
					retorno = retorno + parametrosVo.getValParametro4();
					break;
				}
			}
		}catch(BancaEmpresasException e){
			e.printStackTrace();
		}
		LOGGER.info("NominasDiferidasService.getParamNomina [{}]: [{}]", valParametro, retorno);
		return retorno;
	}
	
	/**
	 * @param data
	 * @return
	 */
	public Map<String, String>  cargosEnLinea(List<RegNomiCargoLineaVo> data){
		
		Map<String, String> mto = new HashMap<>(); 
		try{
			
			for(int i = 0; i < data.size(); i++) {
				RegNomiCargoLineaVo vo = data.get(i);
				int con=1;con+=i;
				mto.put("monto"+con,vo.getMonto());
			}
			if(!mto.containsKey("monto2")){
				mto.put("monto2", "0");
			}
			long monto = Long.parseLong((String)mto.get("monto1")) + Long.parseLong((String)mto.get("monto2"));
			mto.put("total", String.valueOf(monto));		
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return mto;
	}
	
	/**
	 * @param codTipNomina
	 * @return
	 */
	public String tipNomina(int codTipNomina){
		
		String tipN = "";
		try{
			switch(codTipNomina){
			case 198:
				tipN = "Dividendos";
				break;
			case 199:
				tipN = "Proveedores";
				break;
			case 200:
				tipN = "Remuneraciones";
				break;
			default:
				tipN = String.valueOf(codTipNomina);
				break;
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return tipN;
	}
	
    /**
     * Saber si la nómina tiene fecha caducada
     * @return 
     * @throws SQLException
     * @throws Exception
     */
    public boolean isFechaCaduca(String id_sesion,String numNomina,boolean cargoEnLinea) throws BancaEmpresasException{

        LOGGER.info("["+id_sesion+"]NominasDiferidasService.isFechaCaduca Begin  Numero Nomina:"+numNomina);
        try
		{
			boolean isCCA = getNominaCCA_new(id_sesion, numNomina);
			LOGGER.info("NominasDiferidasService.isFechaCaduca NominasDiferidasService.getNominaCCA(" + numNomina + ") : "
					+ isCCA);

			String fechaHoy = empresasService.fechaHoy(Constantes.FORMAT_YYYYMMDDHHMMSS);

			String hora = fechaHoy.substring(8, 12);
			// String hora = (String) CustomFormat.getDateFormat("HHmm", fechaHr);
			LOGGER.info("[" + id_sesion + "]NominasDiferidasService.isFechaCaduca Hora : " + hora);

			String hr_Limite = "1600";

			// Si opera con cargo en linea, se toma nuevo parametro de tope Aprobación
			// Nominas
			try {
				if (cargoEnLinea) {

					// sql = "select to_number(val_parametro) val_parametro from tbl_param_nominas
					// where nom_param = 'NOMINAS' and nom_tipo = 'HORA_LIMITE_NOMINA' and
					// flag_activo = 1";
					hr_Limite = getParamNomina("NOMINAS", "HORA_LIMITE_NOMINA", Constantes.VAL_PARAMETRO);
					LOGGER.info("[" + id_sesion + "]NominasDiferidasService.isFechaCaduca  :  hr_Limite= " + hr_Limite);

				} else {

					// sql = "select to_number(val_parametro) val_parametro from tbl_validacion
					// where nom_parametro = 'PROCESOFIRMACCA'";
					hr_Limite = parametrosValidacionService.getParamValidacion("PROCESOFIRMACCA", "", Constantes.VAL_PARAMETRO);
					LOGGER.info("[" + id_sesion + "]NominasDiferidasService.isFechaCaduca  :  hr_Limite= " + hr_Limite);
				}

			} catch (BancaEmpresasException e) {
				LOGGER.info("[" + id_sesion
						+ "]NominasDiferidasService.isFechaCaduca :  NO SE PUDO RECUPERAR EL REGISTRO DEL WS  ERRROR : "
						+ e);
				throw new BancaEmpresasException();
			}

			LOGGER.info("[" + id_sesion + "]NominasDiferidasService.isFechaCaduca  :  Hora Limite= " + hr_Limite);

			String fec_iodata = getFechaIodata(numNomina);
			// sql = "SELECT TO_CHAR(MIN(fec_fecha),'yyyyMMdd') FROM TBL_IODATA WHERE
			// nom_abocar = 'C' AND num_nomina= "+numNomina;

			if (fec_iodata != null && !fec_iodata.isEmpty()) {

				LOGGER.info("[" + id_sesion + "]NominasDiferidasService.isFechaCaduca : fec_iodata =" + fec_iodata);

				// COMPARA LA FECHA DE HOY MAS UN DIA QUE SEA MENOR O IGUAL QUE LA fec_iodata
				LOGGER.info("[" + id_sesion
						+ "]NominasDiferidasService.isFechaCaduca : compara con fecha actual la minima fecha de la nomina, para deterninar si se puede firmar");

				SimpleDateFormat sdf = new SimpleDateFormat(Constantes.FORMAT_YYYYMMDDHHMMSS);
				Date fecha = sdf.parse(fechaHoy);

				// Valida si es día hábil
				if (!empresasService.isDiaHabil(fecha)) {
					do {
						fecha = BancaUtil.agregaDias(fecha, 1);
					}

					while (!empresasService.isDiaHabil(fecha));
				}
				if (!isCCA && (Integer.parseInt(hora) >= Integer.parseInt(hr_Limite))) {

					fecha = BancaUtil.agregaDias(fecha, 1);
				}

				SimpleDateFormat formateador = new SimpleDateFormat(Constantes.FORMAT_YYYYMMDD);
				String DateCurse_ = formateador.format(fecha);
				LOGGER.info("[" + id_sesion + "]NominasDiferidasService.isFechaCaduca : DateCurse_ =" + DateCurse_);

				if (!(Integer.parseInt(DateCurse_) <= Integer.parseInt(fec_iodata))) {
					LOGGER.info("[" + id_sesion
							+ "]NominasDiferidasService.isFechaCaduca : fecha eN IODATA fuera de plazo, NO SE PUEDE FIRMAR la nomina");
					return false;
				} else {
					if (isCCA && (Integer.parseInt(DateCurse_) == Integer.parseInt(fec_iodata))) {
						LOGGER.info("[" + id_sesion + "]NominasDiferidasService.isFechaCaduca : Integer.parseInt("
								+ hora + ") >= Integer.parseInt(" + hr_Limite + ") "
								+ (Integer.parseInt(hora) >= Integer.parseInt(hr_Limite)));
						if (Integer.parseInt(hora) >= Integer.parseInt(hr_Limite)) {
							LOGGER.info("[" + id_sesion
									+ "]NominasDiferidasService.isFechaCaduca : Hora fuera de plazo, NO SE PUEDE FIRMAR la nomina");
							return false;
						} else {
							LOGGER.info("[" + id_sesion
									+ "]NominasDiferidasService.isFechaCaduca : HAY DATOS EN IODATA y esta en fecha, SE PUEDE FIRMAR la nomina");
							return true;
						}
					} else {
						LOGGER.info("[" + id_sesion
								+ "]NominasDiferidasService.isFechaCaduca : HAY DATOS EN IODATA y esta en fecha, SE PUEDE FIRMAR la nomina");
						return true;
					}
				}
			} else {
				LOGGER.info("[" + id_sesion
						+ "]NominasDiferidasService.isFechaCaduca : NO HAY DATOS EN IODATA, NO SE PUEDE FIRMAR");
				return false;
			}
		}catch(ParseException | BancaEmpresasException ex){
             LOGGER.info("["+id_sesion+"]NominasDiferidasService.isFechaCaduca Error: " + ex);
             return false;
        }
    } 
    
    public boolean getNominaCCA_new(String id_sesion,String numNomina) throws BancaEmpresasException{
    	
    	LOGGER.info("["+id_sesion+"]NominasDiferidasService.getNominaCCA_new ini ");
        
        boolean opc = false;
        int cca = 0;
        int nocca = 0;
        int bice = 0;
        int vv = 0;
        int servipag = 0;
        int tmp_cca = 0;
        int tmp_nocca = 0;
        int tmp_bice = 0;
        int tmp_vv = 0;
        int tmp_servipag = 0;
        int tipoNomina = 0;

		try {
			Map<String, Object> resumenCCList = getLisResumenCC(numNomina);
			cca = Integer.valueOf(resumenCCList.get("N_CCA").toString());
			nocca = Integer.valueOf(resumenCCList.get("N_NOCCA").toString());
			vv = Integer.valueOf(resumenCCList.get("N_VV").toString());
			bice = Integer.valueOf(resumenCCList.get("N_BICE").toString());

			servipag = Integer.valueOf(resumenCCList.get("N_SERVIPAG").toString());
			tipoNomina = Integer.valueOf(resumenCCList.get("TIPONOMINA").toString());

			LOGGER.info("[" + id_sesion + "]NominasDiferidasService.getNominaCCA_new : tipoNomina = " + tipoNomina
					+ "   -   cca = " + cca + "   -   nocca = " + nocca + "   -   bice = " + bice + "   -   vv = " + vv
					+ "   -   servipag = " + servipag);

			String nomParametro = "";
			if (tipoNomina == 195 || tipoNomina == 984)
				nomParametro += "CORRIMIENTO_FECHA_CARGO_NOMINA_PROVEEDORES_CHEQUE";
			else if (tipoNomina == 196 || tipoNomina == 983)
				nomParametro += "CORRIMIENTO_FECHA_CARGO_NOMINA_DIVIDENDOS_CHEQUE";
			else if (tipoNomina == 985)
				nomParametro += "CORRIMIENTO_FECHA_CARGO_NOMINA_REMUNERACIONES_CHEQUE";
			else if (tipoNomina == 197)
				nomParametro += "CORRIMIENTO_FECHA_CARGO_NOMINA_PENSIONES_CHEQUE"; // CAMBIO 31/05 TOMAS
			else if (tipoNomina == 198)
				nomParametro += "CORRIMIENTO_FECHA_CARGO_NOMINA_DIVIDENDOS";
			else if (tipoNomina == 199)
				nomParametro += "CORRIMIENTO_FECHA_CARGO_NOMINA_PROVEEDORES";
			else if (tipoNomina == 200)
				nomParametro += "CORRIMIENTO_FECHA_CARGO_NOMINA_REMUNERACIONES";

			LOGGER.info("[" + id_sesion + "]NominasDiferidasService.getNominaCCA_new : " + nomParametro);

			// try{
			ConsultaParametrosOut consultaParametrosNomina = clienteOperacionNomina
					.consultaParamValidacion(nomParametro, "");
			List<ParametrosVo> parametrosNomina = consultaParametrosNomina.getParametrosVo();
			if (!parametrosNomina.isEmpty()) {
				for (ParametrosVo parametrosVo : parametrosNomina) {
					if (parametrosVo.getNomTipo().equalsIgnoreCase("CCA")) {
						tmp_cca = Integer.valueOf(parametrosVo.getValParametro().trim());
					}
					if (parametrosVo.getNomTipo().equalsIgnoreCase("NOCCA")) {
						tmp_nocca = Integer.valueOf(parametrosVo.getValParametro().trim());
					}
					if (parametrosVo.getNomTipo().equalsIgnoreCase("BICE")) {
						tmp_vv = Integer.valueOf(parametrosVo.getValParametro().trim());
					}
					if (parametrosVo.getNomTipo().equalsIgnoreCase("VV")) {
						tmp_bice = Integer.valueOf(parametrosVo.getValParametro().trim());
					}
					if (parametrosVo.getNomTipo().equalsIgnoreCase("SERVIPAG")) {
						tmp_servipag = Integer.valueOf(parametrosVo.getValParametro().trim());
					}
				}
			}
		} catch (WebServiceException | BancaEmpresasException e) {
			LOGGER.error("[" + id_sesion + "]NominasDiferidasService.getNominaCCA_new ERROR : " + e);
			throw new BancaEmpresasException();
		}
          
        LOGGER.info("["+id_sesion+"]NominasDiferidasService.getNominaCCA_new : tmp_cca = " + tmp_cca + "   -   tmp_nocca = " + tmp_nocca + "   -   tmp_bice = " + tmp_bice + "   -   tmp_vv = " + tmp_vv + "   -   tmp_servipag = " + tmp_servipag);
          
        if (cca > 0){
            if (nocca == 0){
                tmp_nocca = 0;
            }
            if (vv == 0){
                tmp_vv = 0;
            }
            if (bice == 0){
                tmp_bice = 0;
            }
            if (servipag == 0){
                tmp_servipag = 0;
            }
            if ((tmp_cca > tmp_nocca) && (tmp_cca > tmp_vv) && (tmp_cca > tmp_bice) && (tmp_cca > tmp_servipag)){
                opc = true;
            } else {
                opc = false;
            }
        } else {
            opc = false;
        }
        return opc;
      }
    
	/**
	 * Retorna lso datos de una nomina diferida.
	 * 
	 * @param numOperProg
	 * @return
	 * @throws BancaEmpresasException
	 */
	public Map<String, Object> getLisResumenCC(String numNomina) throws BancaEmpresasException {
		LOGGER.info("NominasDiferidasService getLisResumenCC: numNomina[{}]", numNomina);
		Map<String, Object> salida;

		Map<String, Object> params = new HashMap<>();
		
		params.put("Numero_Nomina_i", numNomina);
		params.put("cur", null);

		try {
			portalOrawRepository.getLisResumenCC(params);
			salida = (Map<String, Object>) params;

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return salida;
	}
	
	/**
	 * Metodo que obtiene fecha desde tbl_iodata
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String getFechaIodata(String numNomina) throws BancaEmpresasException {
		LOGGER.info("NominasDiferidasService getFechaIodata: numNomina[{}]", numNomina);

		String salida;

		Map<String, Object> params = new HashMap<>();
		params.put("V_NUM_NOMINA", numNomina);

		try {
			portalOrawRepository.obtenerFechaIodata(params);
			salida = (String) params.get("RESULT");
		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if (salida== null || salida.equals("")) {
			throw new NoEncontradoException();
		}
		
		return salida;
	}
	
}
