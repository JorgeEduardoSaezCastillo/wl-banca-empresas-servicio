package cl.bice.banca.empresas.servicio.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import cl.bice.banca.empresas.servicio.controller.SaldoController;
import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.EntradaInvalidaException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.operaciones.AprobarOp;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.operaciones.RespuestaAprob;
import cl.bice.banca.empresas.servicio.model.request.empresas.SaldoRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.empresas.DetalleCampoValorTipoOperacion;
import cl.bice.banca.empresas.servicio.model.response.empresas.OperacionesAprobar;
import cl.bice.banca.empresas.servicio.model.response.ms.autenticacionoper.AutenticacionSalidaTO;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesPortalResp;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesPortalResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.LiberarOperacionesPortalResp;
import cl.bice.banca.empresas.servicio.model.response.operaciones.LiberarOperacionesPortalResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.OperacionAprobLib;
import cl.bice.banca.empresas.servicio.model.response.saldo.DetalleSaldoResp;
import cl.bice.banca.empresas.servicio.model.response.saldo.SaldoResp;
import cl.bice.banca.empresas.servicio.repository.BiceValidaPoderRepository;
import cl.bice.banca.empresas.servicio.repository.MdpRepository;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.soap.ClienteGenericOperProg;
import cl.bice.banca.empresas.servicio.soap.ClienteNominasEnLinea;
import cl.bice.banca.empresas.servicio.soap.ClienteTefWs;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.banca.empresas.servicio.util.MapperUtil;
import cl.bice.banca.empresas.servicio.util.OperacionesEmpresaUtil;
import cl.bice.banca.empresas.servicio.util.RequestUtil;
import cl.bice.genericoperprog.ws.ActualizaDetCampOut;
import cl.bice.genericoperprog.ws.ActualizaOpOut;
import cl.bice.gfs.ws.GENERICFINANCIALSERVICEOUT;
import cl.bice.nominas.ws.ParametrosVo;
import cl.bice.banca.empresas.servicio.util.TefMasivaUtil;


/**
 * Clase con métodos para manejar las operaciones de una empresa.
 * 
 * @author Marco Bello
 */
@Service
public class OperacionesEmpresaPortalService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionesEmpresaPortalService.class);
	
	
	@Autowired
	public OperacionesEmpresaPortalService(PortalOrawRepository portalOrawRepository, ValoresCampoOperacionesService valoresCampoOperacionesService,
			ValidacionPortalService validacionPortalService, EstadoService estadoService, OperProgService operProgService) {
		this.portalOrawRepository = portalOrawRepository;
		this.valoresCampoOperacionesService = valoresCampoOperacionesService;
		this.validacionPortalService = validacionPortalService;
		this.estadoService = estadoService;
		this.operProgService = operProgService;
	}


	/**
	 * Propiedades Externas.
	 */
	@Autowired
	Properties propiedadesExterna;
	
	@Autowired
	MdpRepository mdpRepository;
	
	@Autowired
	BiceComexService biceComexService;	
	
	@Autowired
	@Qualifier("ClienteGenericOperProg")
	ClienteGenericOperProg clienteGenericOperProg;

	@Autowired
	ConsultarParametrosService consultarParametrosService;

	@Autowired
	FacultadService facultadService;
	
	@Autowired
	TefPortalService tefPortalService;
	
	@Autowired
	@Qualifier("ClienteNominasEnLinea")
	ClienteNominasEnLinea clienteNominasEnLinea;


	@Autowired
	CuentaService cuentaService;
	
	@Autowired
	SaldoController saldoController;

	@Autowired
	ServiciosService serviciosService;
	

	//@Autowired
	ValidacionPortalService validacionPortalService;

	@Autowired
	EmpresasService empresasService;

	
	//@Autowired
	PortalOrawRepository portalOrawRepository;

	@Autowired
	BiceValidaPoderRepository biceValidaPoderRepository;
	
	//@Autowired
	ValoresCampoOperacionesService valoresCampoOperacionesService;
	
	RequestEmpresasService requestEmpresasService;
	
	@Autowired
	MailService mailService;
	
	
	@Autowired
	LbtrMXservice lbtrMXservice;	

	//@Autowired
	EstadoService estadoService;

	
	@Autowired
	PoderService poderService;
	
	@Autowired
	OperacionesEmpresaService operacionesEmpresaService;

	@Autowired
	RestTemplate restTemplate;

	
	@Autowired
	@Qualifier("ClienteTefWs")
	ClienteTefWs clienteTefWs;
	
	@Autowired
	OperProgService operProgService;
	
	@Autowired
	MascaraService mascaraService;
	
	@Autowired
	AutorizarService autorizarService;
	
	private static final String V_SALIDA = "v_SALIDA";
	private static final String DISPOSITIVO_FIRMA_ESIGN = "ESIGN";
	private static final String FIRMA_FDA = "FDA";


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
			listaDetalleCampoValorTipoOperacion.add(operacionesEmpresaService.obtenerDetalleCampoValorTipoOperacion(mapa));
		}

		OperacionesEmpresaUtil operacionesEmpresaUtil = new OperacionesEmpresaUtil();
		List<List<Integer>> arraySeparadores = operacionesEmpresaUtil.obtenerArraySeparadores(
				propiedadesExterna.getProperty("servicios.separadores.operacion." + codigoServicio + ".tipo.aprobar"));

		operacionesAprobar.setArraySeparadores(arraySeparadores);
		operacionesAprobar.setDetalle(listaDetalleCampoValorTipoOperacion);

		return operacionesAprobar;
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
	public AprobarOp aprobarOperacion(String nroOperacion, AprobarOperacionesPortalRequest request, MapOperaciones mapOperaciones, 
			String dispositivoFirma, String nomApoderado) {
		//se asigna el estado inicial que tiene la operacion antes de procesarla
		int codEstadoInicial = this.getCodEstadoOperProg(nroOperacion, mapOperaciones, true);
		AprobarOp aprobarOp = new AprobarOp();
		AprobarOp aprobarOpAux;
		RespuestaAprob respuestaAprob = null;
		String procesoOperProg = "";
		String maskedDataPoderes = "";
		
		try {
			procesoOperProg = operProgService.validaProcesoOperProg(nroOperacion, request.getRutApoderado());
			LOGGER.info("resultado validaProcesoOperProg numOperProg [{}] rutUsuario [{}] resultado [{}] ", nroOperacion, request.getRutApoderado(), procesoOperProg);
			if (!Constantes.GLS_OK.equals(procesoOperProg)) {
				aprobarOp.setCodAprobacion(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR);
				aprobarOp.setGlsMensaje(procesoOperProg);
				return aprobarOp;
			}
			
			this.validaCampoDos(mapOperaciones, nroOperacion);

			if (this.existeFirmaPrevia(nroOperacion, request.getRutApoderado())) {
				aprobarOp.setCodAprobacion(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR);
				aprobarOp.setGlsMensaje(Constantes.FIRMA_YA_EXISTE);
				this.actualizaDetalleCamp(nroOperacion, 2, Constantes.ACTUALIZAR_DETALLE_OPERACION_SIN_PROCESAR);
				return aprobarOp;
			}
			if (!this.validaEstadoOperProg(nroOperacion, mapOperaciones, true)) {
				aprobarOp.setCodAprobacion(-1);
				aprobarOp.setGlsMensaje(Constantes.GLS_ERROR_ESTADO_APROB);
				this.actualizaDetalleCamp(nroOperacion, 2, Constantes.ACTUALIZAR_DETALLE_OPERACION_SIN_PROCESAR);
				return aprobarOp;
			}
			maskedDataPoderes = this.execMascaraEncriptar(mascaraService.getDataRequest(mapOperaciones, nroOperacion), request.getRutApoderado());
			if ("".equals(maskedDataPoderes)) {
				aprobarOp.setCodAprobacion(Constantes.COD_ESTADO_ERROR_EXEC_MASCARA);
				aprobarOp.setGlsMensaje(Constantes.GLS_ERROR_ESTADO_APROB);
				this.actualizaDetalleCamp(nroOperacion, 2, Constantes.ACTUALIZAR_DETALLE_OPERACION_SIN_PROCESAR);
				return aprobarOp;
			}
		} catch (Exception e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			aprobarOp.setCodAprobacion(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR);
			aprobarOp.setGlsMensaje("");
			return aprobarOp;
		}
		
		try {
			respuestaAprob =  poderService.validaPoder(poderService.getPoderRequest(request, nroOperacion, mapOperaciones), maskedDataPoderes);
			
		} catch (BancaEmpresasException e) {
			LOGGER.error("Error al ejecutar servicio de poderes [{}]", e.getMessage());
			aprobarOp.setCodAprobacion(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR);
			aprobarOp.setGlsMensaje(Constantes.GLS_ERROR_EXEC_SRV_FISBAN);
			this.actualizaDetalleCamp(nroOperacion, 2, Constantes.ACTUALIZAR_DETALLE_OPERACION_SIN_PROCESAR);
			return aprobarOp;
		}
		
		aprobarOpAux = this.execActualizaDataAprobacion(nroOperacion, respuestaAprob.getCodEstado(), request.getRutApoderado(), dispositivoFirma, 
				codEstadoInicial, respuestaAprob.getGlsMensaje() == null ? "" : respuestaAprob.getGlsMensaje(), nomApoderado );

		aprobarOp.setCodAprobacion(aprobarOpAux.getCodAprobacion());
		aprobarOp.setGlsMensaje(aprobarOpAux.getGlsMensaje());
		return aprobarOp;

			
	}
	
	private AprobarOp execActualizaDataAprobacion(String numOperProg, int codEstadoOperProg, String rutApoderado, String dispositivoFirma, 
			int codEstadoInicial, String msgResulFisban, String nomApoderado) {
		AprobarOp aprobarOp = new AprobarOp();
		String updOperProg = "";
		boolean resultOperProg = false;
		boolean resultFirma = false;
		
		if (Constantes.GLS_ERROR_GENERICO.equals(this.actualizaDetalleCamp(numOperProg, 2, Constantes.ACTUALIZAR_DETALLE_OPERACION_SIN_PROCESAR))) {
			this.actualizaDetalleCamp(numOperProg, 2, Constantes.ACTUALIZAR_DETALLE_OPERACION_SIN_PROCESAR);
		}		
		
		if (Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR != codEstadoOperProg) {
			updOperProg = this.actualizaEstadoOperProg(numOperProg, codEstadoOperProg);
			
			if (Constantes.GLS_OK.equals(updOperProg)) {
				resultOperProg = true;	
				resultFirma = this.registraFirma(this.obtenerInDatoFirma(dispositivoFirma, Constantes.CANAL_PORTAL_EMPRESA_TEF_MASIVA, ""),
						nomApoderado, numOperProg, rutApoderado);
			}
			
			if ( !(resultOperProg && resultFirma)) {
				this.execReversaActualizaOperProgFirma(resultOperProg, resultFirma, numOperProg, codEstadoInicial, rutApoderado);
				this.actualizaDetalleCamp(numOperProg, 2, Constantes.ACTUALIZAR_DETALLE_OPERACION_SIN_PROCESAR);
				this.eliminaFirmaApoderado(numOperProg, rutApoderado);
				aprobarOp.setCodAprobacion(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR);
				aprobarOp.setGlsMensaje(TefMasivaUtil.getMensajeErrorAprobar(resultOperProg, resultFirma));
				return aprobarOp;
			}
			else {
				aprobarOp.setCodAprobacion(codEstadoOperProg);
				aprobarOp.setGlsMensaje("");
			}
		}
		else {
			aprobarOp.setCodAprobacion(Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR);
			aprobarOp.setGlsMensaje(msgResulFisban);
		}
		
		return aprobarOp;
		
	}
	
	
	private boolean validaEstadoOperProg(String numOperProg, MapOperaciones mapOperaciones, boolean isAprobar) {
		boolean validacion = false;
		String glsEstado = "";
		
		try {
			glsEstado = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, numOperProg, "Estado", isAprobar);
			if (glsEstado.equalsIgnoreCase(Constantes.GLS_ESTADO_FIRMA_PENDIENTE) || glsEstado.equalsIgnoreCase(Constantes.GLS_ESTADO_FIRMA_PARCIAL) ) {
				validacion = true;
			}
		}
		catch (NoEncontradoException e) {
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
		}
		
		return validacion;
		
	}
	
	public String actualizaDetalleCamp(String numOperProg, int codCampo, String valCampo) {
		String respuesta = "";
		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_EMPRESAS_V_VAL_CAMPO, valCampo);
		params.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPER_PROG, numOperProg);
		params.put(Constantes.PARAMETRO_EMPRESAS_V_COD_CAMPO, codCampo);
		try {
			portalOrawRepository.actualizaDetalleCampPortal(params);
			respuesta = Constantes.GLS_OK;
		}
		catch (Exception e) {
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
			respuesta = Constantes.GLS_ERROR_GENERICO;
		}
		
		return respuesta;
				
	}
	
	public String actualizaEstadoOperProg(String numOperProg, int codEstado) {
		String respuesta = "";
		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPER_PROG, numOperProg);
		params.put(Constantes.PARAMETRO_EMPRESAS_V_COD_ESTADO, codEstado);
		try {
			portalOrawRepository.actualizaEstadoOperProg(params);
			respuesta = Constantes.GLS_OK;
		}
		catch (Exception e) {
			respuesta = Constantes.GLS_ERROR_GENERICO;
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
		}
		
		return respuesta;
				
	}
	
	public String actualizaNumOperacionTrfOperProg(String numOperProg, String numOperacionTrf) {
		String resp = "";
		Map<String, Object> parametros = new HashMap<>();
		parametros.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPER_PROG, numOperProg);
		parametros.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPERACION_TRF, numOperacionTrf);
		try {
			portalOrawRepository.actualizaNumOperacionTrfOperProg(parametros);
			resp = Constantes.GLS_OK;
		}
		catch (Exception e) {
			resp = Constantes.GLS_ERROR_GENERICO;
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
		}
		
		return resp;
				
	}	
	public String getNombreApoderado(String rutApoderado) {
		String nomApoderado = "";
		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU, rutApoderado);

		try {
			nomApoderado = portalOrawRepository.getNombreApoderadoClaveAcceso(params);
			if (nomApoderado == null || "".equals(nomApoderado)) {
				params = new HashMap<>();
				params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU, getRutSinCeros(rutApoderado));
				nomApoderado = portalOrawRepository.getNombreCliente(params);
			}
		}
		catch (Exception e) {
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
		}
		
		return nomApoderado;
				
	}
	
	private String getRutSinCeros(String rut) {
		return Integer.parseInt(rut.substring(0, rut.length()-1)) + rut.substring(rut.length()-1, rut.length());
	} 

	

	/**
	 * Genera InDatoFirma que equivale a: [ID DISPOSITIVO] + [_] + [CANAL] + [|] +
	 * [NOMBRE-PORTAL-9i]
	 * 
	 * @param dispositivoFirma
	 * @param canal
	 * @param nombrePortal
	 * @return
	 */
	public String obtenerInDatoFirma(String dispositivoFirma, String canal, String nombrePortal) {
		StringBuilder str = new StringBuilder();
		if (dispositivoFirma != null && !dispositivoFirma.isEmpty()) {
			str.append((FIRMA_FDA.equalsIgnoreCase(dispositivoFirma.trim()) ? DISPOSITIVO_FIRMA_ESIGN
					: dispositivoFirma.toUpperCase()));
			str.append("|");
		}
		str.append(canal);
		str.append(( (nombrePortal == null || "".equals(nombrePortal)) ? "" : "|" +nombrePortal));

		return str.toString();
	}

	/**
	 * Registra firma de un apoderado
	 * 
	 * @param inDatoFirma
	 * @param inNomApoderado
	 * @param inNumOperProg
	 * @param inRutApoderado
	 */
	private boolean registraFirma(String inDatoFirma, String inNomApoderado, String inNumOperProg, String inRutApoderado) {
		boolean resultadoOk = false;
		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put(Constantes.PARAMETRO_EMPRESAS_V_DATO_FIRMA, inDatoFirma);
		parametros.put(Constantes.PARAMETRO_EMPRESAS_V_NOM_APODERADO, inNomApoderado);
		parametros.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPER_PROG, inNumOperProg);
		parametros.put(Constantes.PARAMETRO_EMPRESAS_V_RUT_APODERADO, inRutApoderado);
		
		try {
			portalOrawRepository.registraFirmaApoderado(parametros);
			resultadoOk = true;
		} catch (Exception e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e.getMessage());
		}
		return resultadoOk;
	}




	/**
	 * Recibe una lista de operaciones y las aprueba. Este método controla posibles
	 * errores al intentar realizar las operaciones para aprobar para luego
	 * enviarselas al controller y este al front.
	 * 
	 * @param request
	 * @param estado
	 * @return operaciones no aprobadas, cantidad operaciones no aprobadas y
	 *         operaciones con firma parcial.
	 */
	@SuppressWarnings("unchecked")
	public AprobarOperacionesPortalResponse aprobarOperacionesMasivas(AprobarOperacionesPortalRequest request, Estado estado) {
		List<OperacionAprobLib> operacionesNoAprobadas = new ArrayList<>();
		List<OperacionAprobLib> operacionesConFirmaParcial = new ArrayList<>();
		List<OperacionAprobLib> operacionesConFirmaCompleta = new ArrayList<>();
		List<String> listaOperPertenencia = null;

		if (estado.isEXITO()) {
			try {

				MapOperaciones mapOperaciones = operProgService.obtenerMapOperacionesPortal(request.getRutApoderado(),
						request.getRutEmpresa(), request.getCodigoServicio(), request.getCanal(), convertListaOperProg(request.getListaOperaciones()), true);
				
				
				if (this.existenOperProg(mapOperaciones)) {
					listaOperPertenencia = validacionPortalService.isPertenenciaValida(request, request.getCodigoServicio(),request.getListaOperaciones(), mapOperaciones, true);
					if (listaOperPertenencia == null || listaOperPertenencia.isEmpty()) {
						estadoService.setErrorEstado(EstadoEnum.ERROR_E019_EMPRESAS_REQUEST_INVALIDO, estado);
					}
					else {
						List<Object> listaOper 	    = this.procesarAprobaciones(request, request.getRutEmpresa().toUpperCase(), request.getRutApoderado().toUpperCase(), 
								mapOperaciones, listaOperPertenencia);
						operacionesNoAprobadas 	    = (List<OperacionAprobLib>)listaOper.get(0);
						operacionesConFirmaParcial  = (List<OperacionAprobLib>)listaOper.get(1);
						operacionesConFirmaCompleta = (List<OperacionAprobLib>)listaOper.get(2);
						mapOperaciones.clearMap();
					}

				}
				else {
					estadoService.setErrorEstado(EstadoEnum.ERROR_E015_EMPRESAS_REQUEST_INVALIDO, estado);
				}
				
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
		
		int cantOperAprobadas = this.getCantOpAprobadas(operacionesConFirmaParcial, operacionesConFirmaCompleta);
		int cantOperNoAprobadas = this.getCantOpNoAprobadas(operacionesNoAprobadas);
		AprobarOperacionesPortalResp aprobarOperacionesResp = this.setearAprobarOperacionesResp(cantOperAprobadas, cantOperNoAprobadas, 
				operacionesNoAprobadas, operacionesConFirmaParcial, operacionesConFirmaCompleta);
		AprobarOperacionesPortalResponse response = new AprobarOperacionesPortalResponse();
		response.setEstado(estado);
		response.setRespuesta(aprobarOperacionesResp);

		return response;
	}
	
	
	private AprobarOperacionesPortalResp setearAprobarOperacionesResp(Integer cantOperAprobadas, Integer cantOperNoAprobadas,
			List<OperacionAprobLib> operacionesNoAprobadas, 
			List<OperacionAprobLib> operacionesConFirmaParcial,
			List<OperacionAprobLib> operacionesConFirmaCompleta) {
		
		AprobarOperacionesPortalResp aprobarOperacionesResp = new AprobarOperacionesPortalResp();
		aprobarOperacionesResp.setCantOperacionesAprobadas(cantOperAprobadas);
		aprobarOperacionesResp.setCantOperacionesNoAprobadas(cantOperNoAprobadas);
		aprobarOperacionesResp.setOperacionesConFirmaCompleta(operacionesConFirmaCompleta);
		aprobarOperacionesResp.setOperacionesConFirmaParcial(operacionesConFirmaParcial);
		aprobarOperacionesResp.setOperacionesNoAprobadas(operacionesNoAprobadas);
		
		return aprobarOperacionesResp;
	}
	
	private int getCantOpNoAprobadas(List<OperacionAprobLib> operacionesNoAprobadas) {
		int cantidadOperacionesNoAprobadas = 0;
		if (null != operacionesNoAprobadas && !operacionesNoAprobadas.isEmpty()) {
			cantidadOperacionesNoAprobadas = operacionesNoAprobadas.size();
		}
		
		return cantidadOperacionesNoAprobadas;
	}
	
	private int getCantOpAprobadas(List<OperacionAprobLib> operacionesConFirmaParcial, List<OperacionAprobLib> operacionesConFirmaCompleta ) {
		int cantOperAprobadas = 0;
		if (null != operacionesConFirmaParcial && !operacionesConFirmaParcial.isEmpty()) {
			cantOperAprobadas = operacionesConFirmaParcial.size();
		}
		if (null != operacionesConFirmaCompleta && !operacionesConFirmaCompleta.isEmpty()) {
			cantOperAprobadas = cantOperAprobadas + operacionesConFirmaCompleta.size();
		}
		
		return cantOperAprobadas;
	}	
	
	
	private boolean existenOperProg(MapOperaciones mapOperaciones) {
		return (mapOperaciones.getMapOperaciones().size() == 0 ? Boolean.FALSE : Boolean.TRUE);
	}

	
	
	public LiberarOperacionesPortalResponse setearRespuestaRequestInvalido(Estado estado) { 
		List<OperacionAprobLib> operacionesNoLiberadas = new ArrayList<>();
		List<OperacionAprobLib> operacionesLiberadas = new ArrayList<>();
		LiberarOperacionesPortalResp liberarOperacionesResp = new LiberarOperacionesPortalResp();
		
		liberarOperacionesResp.setCantOperacionesNoLiberadas(getCantidadOper(operacionesNoLiberadas));
		liberarOperacionesResp.setCantOperacionesLiberadas(getCantidadOper(operacionesLiberadas));
		liberarOperacionesResp.setOperacionesNoLiberadas(operacionesNoLiberadas);
		liberarOperacionesResp.setOperacionesLiberadas(operacionesLiberadas);
		
		LiberarOperacionesPortalResponse response = new LiberarOperacionesPortalResponse();
		response.setEstado(estado);
		response.setRespuesta(liberarOperacionesResp);

		return response;		
		
	}

	/**
	 * Recibe una lista de operaciones y las libera.
	 * Se utiliza en el proceso de Liberaciones Masivas
	 * 
	 * @param request
	 * @param estado
	 * @return operaciones liberadas con su estado y glosa de liberación
	 *         (liberada/rechazada)
	 * 
	 */
	@SuppressWarnings({ "unchecked" })
	public LiberarOperacionesPortalResponse liberarOperacionesPortal(LiberarOperacionesPortalRequest request, Estado estado) { 
		List<OperacionAprobLib> operacionesNoLiberadas = new ArrayList<>();
		List<OperacionAprobLib> operacionesLiberadas = new ArrayList<>();
		LiberarOperacionesPortalResp liberarOperacionesResp = new LiberarOperacionesPortalResp();
		List<Map<String, Object>> listaParametros = this.obtenerParametrosValidacion(Constantes.VALOR_NOM_PARAM);
			
		try {
			
			MapOperaciones mapOperaciones = operProgService.obtenerMapOperacionesPortal(request.getRutApoderado(), 
					request.getRutEmpresa(), request.getCodigoServicio(), request.getCanal(), convertListaOperProg(request.getListaOperaciones()), false);
			
			if (this.existenOperProg(mapOperaciones)) {
				List<Object> listaOper 	   = this.procesarLiberaciones(request, request.getRutEmpresa().toUpperCase(), request.getRutApoderado().toUpperCase(), 
						mapOperaciones, listaParametros);
				operacionesNoLiberadas 	   = (List<OperacionAprobLib>)listaOper.get(0);
				operacionesLiberadas 	   = (List<OperacionAprobLib>)listaOper.get(1);

				mapOperaciones.clearMap();
				if (operacionesLiberadas.isEmpty()) {
					estadoService.setErrorEstado(EstadoEnum.ERROR_E102_EMPRESAS_GENERIC_APROB_LIB, estado);
				}
			}
			else {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E015_EMPRESAS_REQUEST_INVALIDO, estado);
			}
			

		} catch (RequestInvalidoException | EntradaInvalidaException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado);
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES, estado);
		}


		liberarOperacionesResp.setCantOperacionesNoLiberadas(getCantidadOper(operacionesNoLiberadas));
		liberarOperacionesResp.setCantOperacionesLiberadas(getCantidadOper(operacionesLiberadas));
		liberarOperacionesResp.setOperacionesNoLiberadas(operacionesNoLiberadas);
		liberarOperacionesResp.setOperacionesLiberadas(operacionesLiberadas);
		
		LiberarOperacionesPortalResponse response = new LiberarOperacionesPortalResponse();
		response.setEstado(estado);
		response.setRespuesta(liberarOperacionesResp);

		return response;
	}
	
	private Integer getCantidadOper(List<OperacionAprobLib> listaOperaciones) {
		Integer cantidad = 0;
		if (null != listaOperaciones && !listaOperaciones.isEmpty()) {
			cantidad = listaOperaciones.size();
		}
		
		return cantidad;
		
	}
	

	/**
	 * Libera operaciones por tipo Actualiza datos operacion en progreso y detalle
	 * de operaciones
	 * 
	 * @param nroOperacion
	 * @param request
	 * @return
	 */
	public Map<String, String> liberarOperacion(String nroOperacion, LiberarOperacionesPortalRequest request, MapOperaciones mapOperaciones, String cargoOnlineTodos) {
		
		Map<String, String> resultado = new HashMap<>();
		String isOpLib = this.isOperProgLiberada(nroOperacion);
		
		if ( "SI".equals(isOpLib)) {
        	resultado.put(Constantes.COD_RESULTADO, String.valueOf(Constantes.COD_ESTADO_OPERPROG_YA_LIBERADA));
            resultado.put(Constantes.GLS_RESULTADO, Constantes.GLS_OPER_PROG_YA_LIBERADA);
            return resultado;
		}
		if ( Constantes.GLS_ERROR_GENERICO.equals(isOpLib)) {
        	resultado.put(Constantes.COD_RESULTADO, String.valueOf(Constantes.COD_ESTADO_OPERPROG_ERROR_LIBERADA));
            resultado.put(Constantes.GLS_RESULTADO, Constantes.GLS_OPER_PROG_ERROR_LIBERADA);
            return resultado;
		}
		if ( Constantes.GLS_ESTADO_LIB_INVALIDO.equals(isOpLib)) {
        	resultado.put(Constantes.COD_RESULTADO, String.valueOf(Constantes.COD_ESTADO_OPERPROG_ERROR_PEND_LIBERAR));
            resultado.put(Constantes.GLS_RESULTADO, Constantes.GLS_OPER_PROG_ERROR_EST_LIBERADA);
            return resultado;
		}
		
		return this.liberarOperacionLbtrPortal(nroOperacion, request, mapOperaciones, cargoOnlineTodos);

	}
	
	private String isOperProgLiberada(String numOperProg) {
		String liberada = "NO";
		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPER_PROG, numOperProg);
		
		try {
			Map<String, Object> mapOperProg = portalOrawRepository.getDatosOperProg(params);
			if (mapOperProg.get("COD_ESTADO").toString().equals(Constantes.COD_ESTADO_LIBERADA)) {
				liberada = "SI";
			}
			else {
				if (!mapOperProg.get("COD_ESTADO").toString().equals(Constantes.TEF_LBTR_ESTADOS_LIBERAR)) {
					liberada = "ESTADO_LIB_INVALIDO";
				}
			} 
		}
		catch (Exception e) {
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
			liberada = Constantes.GLS_ERROR_GENERICO;
		}
		
		return liberada;
	}

	/**
	 * Libera operaciones del tipo LBTR Actualiza datos operacion en progreso y
	 * detalle de operaciones
	 * 
	 * @param nroOperacion
	 * @param request
	 * @return
	 */
	private Map<String, String> liberarOperacionLbtrPortal(String nroOperacion, LiberarOperacionesPortalRequest request,
			MapOperaciones mapOperacionesParam, String cargoOnlineTodos)  {
        int codResultado = -1 ;
        String glsResultado = "";
		Map<String, String> resultado = new HashMap<>();
		
		// se obtienen valores operacion campo-valor de la salida de la query via mybatis
		List<Map<String, Object>> mapOperacionesBD = mapOperacionesParam.getMapOutputSP();
		Map<String, Object> mapOperProgSp = new HashMap<>();

		for (Map<String, Object> map : mapOperacionesBD) {
			if (map.get(Constantes.NUM_OPER_PROG).toString().trim().equals(nroOperacion)) {
				mapOperProgSp = map;
				break;
			}
		}
		
		Map<String, String> mapOperacionResult = new HashMap<>();// inicializamos mapa de operacion a devolver
		mapOperacionesParam.getMapOperaciones().put(nroOperacion, mapOperacionResult);
		mapOperacionesParam.getMapOperaciones().get(nroOperacion).put(Constantes.COD_ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.COD_ESTADO_LIBERACION_NOK));
		mapOperacionesParam.getMapOperaciones().get(nroOperacion).put(Constantes.ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
		mapOperacionesParam.getMapOperaciones().get(nroOperacion).put(Constantes.GLOSA_ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SISTEMA));

		if (mapOperProgSp.isEmpty()) {
			LOGGER.info("No se pudo liberar operacion - Operprog: [{}] no pertenece a lista por liberar", nroOperacion);
        	resultado.put(Constantes.COD_RESULTADO, String.valueOf(codResultado));
            resultado.put(Constantes.GLS_RESULTADO, glsResultado);  
			return resultado;
			
		}

		String strTieneCargoEnLinea = this.validaParametroTieneCargoEnLinea(request.getRutEmpresa(), cargoOnlineTodos);
		String strTieneCuentaParagua = this.validaParametroTieneCuentaParagua(mapOperProgSp, request.getRutEmpresa());
		String strIsFechaValutaHoy = this.validaParametroFechaValutaHoy((String) mapOperProgSp.get("VALOR3"));
		
		if (!this.isOkLecturaParametros(strTieneCargoEnLinea, strTieneCuentaParagua, strIsFechaValutaHoy)) {
        	resultado.put(Constantes.COD_RESULTADO, String.valueOf(Constantes.COD_ESTADO_ERROR_GET_PARAM_CARGO_LINEA));
            resultado.put(Constantes.GLS_RESULTADO, Constantes.GLS_ERROR_GET_PARAM_CARGO_LINEA);  
			return resultado;
		}
		
		boolean tieneCargoEnLinea = Constantes.GLS_OPC_SI.equals(strTieneCargoEnLinea) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue();
		boolean tieneCuentaParagua = Constantes.GLS_OPC_SI.equals(strTieneCuentaParagua) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue();
		boolean isFechaValutaHoy = Constantes.GLS_OPC_SI.equals(strIsFechaValutaHoy) ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue();
		
		LOGGER.info("Operacion N°: [{}] rutCliente [{}] rutUsuario [{}] esCargoEnLinea [{}] esCuentaParagua [{}] esFechaValutaHoy [{}] ",
				nroOperacion, request.getRutEmpresa(), request.getRutApoderado(), strTieneCargoEnLinea, strTieneCuentaParagua, strIsFechaValutaHoy);
		
		if(isFechaValutaHoy && !operacionesEmpresaService.esHoraValida()) {
			LOGGER.info("No se pudo liberar operación. Operacion N°: [{}] tiene fecha de valuta de hoy y no puede ser liberada a esta hora. rutCliente [{}] rutUsuario [{}]", 
					nroOperacion, request.getRutEmpresa(), request.getRutApoderado());
			mapOperacionResult.put(Constantes.GLOSA_ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_HORA_LIMITE));
        	resultado.put(Constantes.COD_RESULTADO, String.valueOf(Constantes.COD_ESTADO_ERROR_LIB_FECHA_VALUTA));
            resultado.put(Constantes.GLS_RESULTADO, Constantes.GLS_ERROR_LIB_FECHA_VALUTA);  
			return resultado;
		}

		if (tieneCargoEnLinea && !tieneCuentaParagua && isFechaValutaHoy) { //FLUJO CON CARGO EN LINEA
			//0: OK| -1:Error
			resultado = this.execFlujoCargoEnLinea(mapOperProgSp, nroOperacion, request);
			
		} else {// FLUJO SIN CARGO EN LINEA
			//0: OK| -1:Error
			resultado = this.execFlujoSinCargoEnLinea(mapOperProgSp, tieneCuentaParagua, request, nroOperacion);
		}

		return resultado;
	}
	
	private String validaParametroTieneCargoEnLinea(String rutCliente, String cargoOnlineTodos) {
		String respuesta = "";
		try {
			switch (cargoOnlineTodos) {
			case Constantes.GLS_OPC_SI:
				respuesta = Constantes.GLS_OPC_SI;
				break;
			case Constantes.GLS_OPC_NO:
				respuesta = validacionPortalService.empresaTieneCargoEnLineaLbtr(rutCliente);
				break;
			case Constantes.GLS_ERROR_GENERICO:
				respuesta = Constantes.GLS_ERROR_GENERICO;
				break;

			default:
				break;
			}
		}
		catch (Exception e) {
			respuesta = Constantes.GLS_ERROR_GENERICO;
		}
		
		return respuesta;
	}

	private String validaParametroTieneCuentaParagua(Map<String, Object> mapOperProgSp, String rutCliente) {
		String respuesta = "";
		try {
			respuesta = this.isCtaParagua(mapOperProgSp, rutCliente);
		}
		catch (Exception e) {
			respuesta = Constantes.GLS_ERROR_GENERICO;
		}
		
		return respuesta;
	}
	
	private String validaParametroFechaValutaHoy(String fechaValuta) {
		String respuesta = "";
		try {
			respuesta = this.isFechaValutaLbtrHoy(fechaValuta);
		}
		catch (Exception e) {
			respuesta = Constantes.GLS_ERROR_GENERICO;
		}
		
		return respuesta;
	}
	
	private boolean isOkLecturaParametros(String tieneCargoLinea, String tieneCtaParagua, String esFechaValutaHoy) {
		return (!Constantes.GLS_ERROR_GENERICO.equals(tieneCargoLinea) && 
				!Constantes.GLS_ERROR_GENERICO.equals(tieneCtaParagua) &&
				!Constantes.GLS_ERROR_GENERICO.equals(esFechaValutaHoy));
	}
	


	/**
	 * Obtiene saldo disponible de una cuenta
	 * 
	 * @param request, cuentaCargo, estado @return BigDecimal @throws
	 */
	public BigDecimal obtenerSaldoCuenta(LiberarOperacionesPortalRequest request, String nroCuenta) {
		BigDecimal saldoDisponibleCta = new BigDecimal(0);
		SaldoRequest saldoReq = new SaldoRequest();
		saldoReq.setCuenta(nroCuenta);
		saldoReq.setBia("false");
		saldoReq.setCanal(request.getCanal());
		saldoReq.setCodigoProducto(Constantes.COD_TIPO_CTA);
		saldoReq.setMoneda(Constantes.CONSTANTE_CERO);
		saldoReq.setRut(request.getRutApoderado());
		saldoReq.setRutEmpresa(request.getRutEmpresa());
		saldoReq.setDispositivo(request.getRutApoderado());
		saldoReq.setToken(request.getRutApoderado());
		
		String monedaFormat = String.format("%03d", Integer.valueOf(saldoReq.getMoneda()));
		Estado estadoTmp = new Estado(EstadoEnum.EXITO.getCodigoEstado(), EstadoEnum.EXITO.getParametroGlosaEstado());
		SaldoResp saldoResponse = cuentaService.obtenerSaldo(monedaFormat, saldoReq, estadoTmp);
		if (saldoResponse != null && estadoTmp.isEXITO() && saldoResponse.getMonto() != null) {
			saldoDisponibleCta = saldoDisponibleCta.add(new BigDecimal(saldoResponse.getMonto().trim()));
		}

		return saldoDisponibleCta;
	}

	/**
	 * Obtiene objeto de saldos para una cuenta
	 * 
	 * @param request, cuentaCargo, estado @return SaldoResp @throws
	 */
	public SaldoResp obtenerSaldos(LiberarOperacionesPortalRequest request, String nroCuenta) {
		SaldoRequest saldoRequest = new SaldoRequest();
		saldoRequest.setCuenta(nroCuenta);
		saldoRequest.setBia("false");
		saldoRequest.setCanal(request.getCanal());
		saldoRequest.setCodigoProducto(Constantes.COD_TIPO_CTA);
		saldoRequest.setMoneda(Constantes.CONSTANTE_CERO);
		saldoRequest.setRut(request.getRutApoderado());
		saldoRequest.setRutEmpresa(request.getRutEmpresa());

		String monedaFormateada = String.format("%03d", Integer.valueOf(saldoRequest.getMoneda()));
		Estado estadoTemp = new Estado(EstadoEnum.EXITO.getCodigoEstado(), EstadoEnum.EXITO.getParametroGlosaEstado());
		return cuentaService.obtenerSaldo(monedaFormateada, saldoRequest, estadoTemp);

	}

	/**
	 * Obtiene el saldo de las cuentas asociadas a modalidad paragua dada una cuenta
	 * 
	 * @param request, cuentaCargo, estado @return bigDecimal @throws
	 */
	public BigDecimal obtenerSaldoCuentaParagua(LiberarOperacionesPortalRequest request, String cuentaCargo, Estado estado) {
		BigDecimal saldoDisponible = new BigDecimal(0);

		List<Map<String, Object>> listaCuentasColaborativas = empresasService.obtenerCuentasColaborativas(cuentaCargo);

		if (listaCuentasColaborativas != null && !listaCuentasColaborativas.isEmpty()) {

			for (Map<String, Object> cuentaColaborativa : listaCuentasColaborativas) {
				SaldoResp saldos = obtenerSaldos(request, (String) cuentaColaborativa.get("CUENTA"));
				if (saldos != null && estado.isEXITO()) {
					List<DetalleSaldoResp> detalleSaldoList = saldos.getDetalle();
					
					saldoDisponible = this.calcularSaldoCtaMadre((BigDecimal) cuentaColaborativa.get("COD_TIPO_CUENTA"), 
							detalleSaldoList, saldos, (String) cuentaColaborativa.get("COD_LSOBREGIRO"));
					
				} else {
					LOGGER.error("No se pudo obtener el saldo de la cuenta asociada a cuenta paragua, cuenta nro: [{}] rutCliente [{}] rutUsuario [{}] ",
							(String) cuentaColaborativa.get("CUENTA"), request.getRutEmpresa(), request.getRutApoderado());
					estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);
				}
			}

		} else {
			LOGGER.error("Error al obtener cuentas colaborativas (modalidad cuenta paragua)");
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);
		}

		LOGGER.info("Saldo cuenta paragua: [{}], cuenta nro: [{}] rutCliente [{}] rutUsuario [{}] ", saldoDisponible, cuentaCargo, request.getRutEmpresa(), request.getRutApoderado());
		return saldoDisponible;
	}
	
	
	private BigDecimal calcularSaldoCtaMadre(BigDecimal codTipoCuenta, List<DetalleSaldoResp> detalleSaldoList, SaldoResp saldos, String cuentaColaborativa) {
		
		BigDecimal saldoDisponible = new BigDecimal(0);
		
		// Calculo saldo cuenta madre
		if (codTipoCuenta.compareTo(BigDecimal.ZERO) == 0) {
			if (detalleSaldoList.get(0).getSaldos().get(0).getMonto() != null) {
				DetalleSaldoResp detalleSaldo = detalleSaldoList.get(0);// cuenta cte
				BigDecimal saldoDispCuentaMadre = new BigDecimal(detalleSaldo.getSaldos().get(0).getMonto()
						.trim().replace("$ ", "").replace(".", ""));
				// Para la cuenta madre se considera el monto sólo si es mayor a cero
				if (saldoDispCuentaMadre.compareTo(BigDecimal.ZERO) > 0) {
					saldoDisponible = saldoDisponible.add(saldoDispCuentaMadre);
				}
			}

		} else {// si no es cuenta madre, se calcula directamente el monto
			if (saldos.getMonto() != null)
				saldoDisponible = saldoDisponible.add(new BigDecimal(saldos.getMonto().trim()));
		}
		// Si tiene línea de sobregiro se considera en el monto final
		if (cuentaColaborativa.equalsIgnoreCase("S")) {
			saldoDisponible = this.calcularSaldoCtaMadreLS(detalleSaldoList, saldoDisponible);
		}
		
		return saldoDisponible;
		
	}
	
	private BigDecimal calcularSaldoCtaMadreLS(List<DetalleSaldoResp> detalleSaldoList, BigDecimal saldoDisponibleParam) {
		BigDecimal saldoDisponible = new BigDecimal(0);
		boolean existeDato = false;
		
		if (detalleSaldoList != null) {
			for (DetalleSaldoResp detalleSaldo : detalleSaldoList) {
				if (detalleSaldo.getNombre() != null
						&& detalleSaldo.getNombre().toUpperCase().contains("SOBREGIRO")
						&& detalleSaldo.getSaldos().get(0).getMonto() != null) {
					BigDecimal saldoDispLineaSobregiro = new BigDecimal(detalleSaldo.getSaldos().get(0)
							.getMonto().trim().replace("$ ", "").replace(".", ""));
					saldoDisponible = saldoDisponible.add(saldoDispLineaSobregiro);
					existeDato = true;
				}
			}
		}
		
		if (!existeDato) {
			saldoDisponible = saldoDisponibleParam;
		}
		
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
			numeroRut = Constantes.VALOR_CON_CEROS + rut.trim();
			numeroRut = numeroRut.substring(numeroRut.length() - 11);
			numeroCta = Constantes.VALOR_CON_CEROS + cuenta.trim();
			numeroCta = numeroCta.substring(numeroCta.length() - 14);

			Map<String, Object> paramsObtenerAbonoCclv = new HashMap<>();

			paramsObtenerAbonoCclv.put("Cuenta_rec", numeroCta);
			paramsObtenerAbonoCclv.put("rut_rec", numeroRut);
			paramsObtenerAbonoCclv.put("result2_msg", 0);

			LOGGER.info("OperacionesEmpresaPortalService obtenerAbonosCclv: rutEmpresa[{}], cuenta[{}]", rut, cuenta);

			mdpRepository.obtenerAbonosCclv(paramsObtenerAbonoCclv);

			if (paramsObtenerAbonoCclv.get(Constantes.STR_RETURN_VALUE) != null)
				abonosCclv = abonosCclv.add(new BigDecimal((Integer) paramsObtenerAbonoCclv.get(Constantes.STR_RETURN_VALUE)));
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
	 * Obtiene datos empresa cclv
	 * 
	 * @param rutEmpresa
	 * @return params
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> obtenerDatosCclv(String rutEmpresa) {
		Map<String, Object> params = new HashMap<>();
		params.put("v_RUT_EMPRESA", rutEmpresa);
		params.put(Constantes.STR_V_SALIDA, null);

		List<Map<String, Object>> salida = new ArrayList<>();

		try {
			portalOrawRepository.obtenerDatosCclv(params);
			salida = (List<Map<String, Object>>) params.get(Constantes.STR_V_SALIDA);
			if (!MapperUtil.isSalidaSPValida(salida)) {
				LOGGER.error("Error al obtener datos cclv para empresa rut: [{}]", rutEmpresa);
			} else if (params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT) == null
					|| !"0".equals(params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT).toString().trim())) {
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
	public BigDecimal calcularSaldoCclv(LiberarOperacionesPortalRequest request, String cuentaCargo, Estado estado) {
		BigDecimal saldoCclv = new BigDecimal(0);
		String rutEmpresa = request.getRutEmpresa();
		try {
			BigDecimal minimo1 = new BigDecimal(0);
			BigDecimal minimo2 = new BigDecimal(0);
			BigDecimal cupoTefCclv;
			BigDecimal saldoOv;
			BigDecimal abonosCclv = new BigDecimal(0);
			BigDecimal pagadoCclv = new BigDecimal(0);
			BigDecimal saldoTotalCuenta = new BigDecimal(0);

			Map<String, Object> datosCclv = obtenerDatosCclv(rutEmpresa) != null && !obtenerDatosCclv(rutEmpresa).isEmpty()
					? obtenerDatosCclv(rutEmpresa).get(0)
					: null;
			if (datosCclv == null) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);
				return saldoCclv;
			}

			cupoTefCclv = new BigDecimal(datosCclv.get("MONTO_DIARIO").toString());
			saldoOv = new BigDecimal(datosCclv.get("SALDO_DISP_OV").toString());
			abonosCclv = abonosCclv.add(obtenerAbonosCclv(rutEmpresa, cuentaCargo, estado));
			pagadoCclv = pagadoCclv.add(operacionesEmpresaService.obtenerPagosCclv(rutEmpresa, cuentaCargo, estado));
			saldoTotalCuenta = saldoTotalCuenta.add(obtenerSaldoCuenta(request, cuentaCargo));

			minimo1 = minimo1.add(cupoTefCclv).add(saldoOv).add(abonosCclv).subtract(pagadoCclv);
			minimo2 = minimo2.add(saldoTotalCuenta);

			if (minimo1.compareTo(minimo2) < 0)
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
	 * Retorna un valor del detalle de una operacion. El detalle de una operacion se
	 * obtiene de la tabla TBL_DETALLE_CAMP.
	 * 
	 * @param numOperProg
	 * @param codCampo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String obtenerValorCampoOperacion(String numOperProg, String codCampo) {
		LOGGER.info("numOperProg[{}], codCampo[{}]", numOperProg,
				codCampo);
		String result = null;
		Map<String, Object> hashParams = new HashMap<>();
		hashParams.put("V_NUM_OPER_PROG", numOperProg);
		hashParams.put(Constantes.STR_V_COD_RESULT, null);
		hashParams.put("v_result", null);

		List<Map<String, Object>> salidaSpOperProg = new ArrayList<>();

		try {
			portalOrawRepository.obtenerDetalleOperacion(hashParams);
			salidaSpOperProg = (List<Map<String, Object>>) hashParams.get("v_result");
			if (!MapperUtil.isSalidaSPValida(salidaSpOperProg)) {
				LOGGER.error("Error al obtener el detalle de la operacion [{}]", numOperProg);
			} else if (hashParams.get(Constantes.STR_V_COD_RESULT) == null
					|| !"1".equals(hashParams.get("v_COD_RESULT").toString().trim())) {
				LOGGER.error("Error al obtener el detalle de la operacion [{}] v_COD_RESULT [{}]", numOperProg,
						hashParams.get(Constantes.STR_V_COD_RESULT));
				throw new BancaEmpresasException();
			} else {
				for (Map<String, Object> map : salidaSpOperProg) {
					if (null != map.get("COD_CAMPO")
							&& codCampo.trim().equals(String.valueOf(map.get("COD_CAMPO")).trim())) {
						result = String.valueOf(map.get("VAL_CAMPO"));
						break;
					}
				}
			}
		} catch (BancaEmpresasException | SQLException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
		}
		LOGGER.info("numOperProg [{}] codCampo [{}] resultado[{}]", numOperProg, codCampo, result);
		return result;
	}


	
	/**
	 * Convierte una lista de numOperprog en una cadena de numeros separados por coma
	 * 
	 * @param List<String>
	 * @return String
	 */	
	private String convertListaOperProg(List<String> listaOperProg) {
		return listaOperProg.stream()
			     .map(i -> i)
			     .collect(Collectors.joining(", "));
		
	} 
	

	/**
	 * Realiza el proceso de aprobacion de un listado de operaciones
	 * 
	 * @param AprobarOperacionesPortalRequest
	 * @param rutCliente
	 * @param rutUsuario
	 * @return List<Object>
	 */
	private List<Object> procesarAprobaciones(AprobarOperacionesPortalRequest request, String rutCliente, String rutUsuario, MapOperaciones mapOperaciones,
			List<String> listaOperPertenencia) {
		List<OperacionAprobLib> operacionesNoAprobadas = new ArrayList<>();
		List<OperacionAprobLib> operacionesConFirmaParcial = new ArrayList<>();
		List<OperacionAprobLib> operacionesConFirmaCompleta = new ArrayList<>();
		List<Object> operaciones = new ArrayList<>();
		AutenticacionSalidaTO autenticacionSalidaTO = null;
		OperacionAprobLib operacionAprobLib = null;
		List<Map<String, Object>> listaParametros = this.obtenerParametrosValidacion(Constantes.VALOR_NOM_PARAM);
		List<Map<String, Object>> listaParamSis = this.obtenerParametrosValidacion(Constantes.VALOR_NOM_PARAM_TEF_SIS);
		List<Map<String, Object>> listaCCLV = this.obtenerParametrosValidacion(Constantes.VALOR_NOM_PARAM_CCLV);
		HashMap<String, String> datosValidaNegocio;
		int codEstadoAprob;
		boolean isEstadoOkAprob = false;
		boolean cumplePertenencia = false;
		String rutCCLV = TefMasivaUtil.obtenerValorParametro(listaCCLV, Constantes.VALOR_NOM_TIPO_RUT_CCLV);
		String nomApoderado = this.getNombreApoderado(rutUsuario);
		
		for (String nroOperacion : request.getListaOperaciones()) {
				LOGGER.info("Inicio proceso aprobacion Tef LBTR MN: rutCliente [{}] rutUsuario [{}] numOperProg[{}] ", 
						request.getRutEmpresa(), request.getRutApoderado(), nroOperacion);
				
				try {
					autenticacionSalidaTO = autorizarService.execAutorizar(nroOperacion, rutCliente, rutUsuario, Constantes.MS_AUTENTICACION_OPER_TIPO_OPERACION_APROBAR);
				} catch (BancaEmpresasException e) {
					autenticacionSalidaTO = new AutenticacionSalidaTO();
					autenticacionSalidaTO.setGlsError(Constantes.GLS_ERROR_SRV_AUTENTICACION);
				}
				
				isEstadoOkAprob   = this.estadoOkAprobar(nroOperacion, mapOperaciones, true);
				cumplePertenencia = TefMasivaUtil.cumplePertenencia(nroOperacion, listaOperPertenencia);
				
				if (this.existeTipoDesafio(autenticacionSalidaTO) && isEstadoOkAprob && cumplePertenencia ) {
					datosValidaNegocio = this.validacionesNegocio(rutCliente, nroOperacion, mapOperaciones, listaParametros, 
							autenticacionSalidaTO.getTipoDesafio(), listaParamSis, rutCCLV);
					if (!Constantes.COD_RESULTADO_VALIDA_OK.equals(datosValidaNegocio.get(Constantes.COD_RESULTADO))) {
						operacionAprobLib = new OperacionAprobLib();
						operacionAprobLib.setNumOperProg(nroOperacion);
						operacionAprobLib.setGlosaEstado(datosValidaNegocio.get(Constantes.GLS_RESULTADO));
						operacionesNoAprobadas.add(operacionAprobLib);
						codEstadoAprob = 30;
					}
					else {
						operacionAprobLib = new OperacionAprobLib();
						operacionAprobLib.setNumOperProg(nroOperacion);
						AprobarOp aprobarOp = this.aprobarOperacion(nroOperacion, request, mapOperaciones, autenticacionSalidaTO.getTipoDesafio(), nomApoderado);

						codEstadoAprob = aprobarOp.getCodAprobacion();
		
						switch (codEstadoAprob) {
						case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR:
							operacionAprobLib.setGlosaEstado(Constantes.TEF_LBTR_GLS_NOK_APROBAR + this.getMensajeAprob(aprobarOp.getGlsMensaje()));
							operacionesNoAprobadas.add(operacionAprobLib);
							break;
						case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL:
							operacionAprobLib.setGlosaEstado(Constantes.GLS_ESTADO_FIRMA_PARCIAL);
							operacionesConFirmaParcial.add(operacionAprobLib);
							break;
						case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_FIRMA_COMPLETA:
							operacionAprobLib.setGlosaEstado(Constantes.TEF_LBTR_GLS_OK_APROBAR);
							operacionesConFirmaCompleta.add(operacionAprobLib);
							break;
						case Constantes.COD_ESTADO_FIRMA_YA_REGISTRADA:
							operacionAprobLib.setGlosaEstado(Constantes.GLS_FIRMA_YA_REGISTRADA);
							operacionesNoAprobadas.add(operacionAprobLib);
							break;
						case Constantes.COD_ESTADO_ERROR_EXEC_MASCARA:
							operacionAprobLib.setGlosaEstado(Constantes.GLS_ERROR_SRV_MASCARA);
							operacionesNoAprobadas.add(operacionAprobLib);
							break;
						default:
							operacionAprobLib.setGlosaEstado("Firma no realizada");
							operacionesNoAprobadas.add(operacionAprobLib);
							break;
						}
					}
				}
				else {
					operacionAprobLib = new OperacionAprobLib();
					operacionAprobLib.setNumOperProg(nroOperacion);
					operacionAprobLib.setGlosaEstado(this.getGlosaEstadoErrorAprob(autenticacionSalidaTO, isEstadoOkAprob, cumplePertenencia));
					operacionesNoAprobadas.add(operacionAprobLib);
					codEstadoAprob = 30;
				}
				LOGGER.info("Fin proceso aprobacion Tef LBTR MN:    rutCliente [{}] rutUsuario [{}] numOperProg[{}] codigoEstado[{}] glosaEstado[{}]", 
						request.getRutEmpresa(), request.getRutApoderado(), nroOperacion, codEstadoAprob, operacionAprobLib.getGlosaEstado());
				

		} //for
		
		operaciones.add(operacionesNoAprobadas);
		operaciones.add(operacionesConFirmaParcial);
		operaciones.add(operacionesConFirmaCompleta);
		
		return operaciones;
		
	}
	
	
	private List<Object> procesarLiberaciones(LiberarOperacionesPortalRequest request, String rutCliente, String rutUsuario, 
			MapOperaciones mapOperaciones, List<Map<String, Object>> listaParametros) {
		AutenticacionSalidaTO autenticacionSalidaTO = null;
		List<OperacionAprobLib> operacionesNoLiberadas = new ArrayList<>();
		List<OperacionAprobLib> operacionesLiberadas = new ArrayList<>();
		List<Object> operaciones = new ArrayList<>();
		OperacionAprobLib operacionAprobLib = null;
		boolean validaOpLib;
		Map<String, String> resultadoLiberar;
		String codEstadoLiberar = "";
		String cargoOnlineTodos = TefMasivaUtil.aplicaCargoOnlineTodos(listaParametros);
		boolean isEstadoOkLiberar = false;
		
		for (String nroOperacion : request.getListaOperaciones()) {
			LOGGER.info("Inicio proceso liberacion Tef LBTR MN: rutCliente [{}] rutUsuario [{}] numOperProg[{}]", 
					request.getRutEmpresa(), request.getRutApoderado(), nroOperacion);
			
			try {
				autenticacionSalidaTO = autorizarService.execAutorizar(nroOperacion, rutCliente, rutUsuario, Constantes.MS_AUTENTICACION_OPER_TIPO_OPERACION_LIBERAR);
			} catch (BancaEmpresasException e1) {
				autenticacionSalidaTO = new AutenticacionSalidaTO();
				autenticacionSalidaTO.setGlsError(Constantes.GLS_ERROR_SRV_AUTENTICACION);
			}
			
			validaOpLib = this.validaOperProgLiberar(nroOperacion, mapOperaciones, Constantes.CAMPO_NUM_OPER_PROG);
			isEstadoOkLiberar = this.estadoOkLiberar(nroOperacion, mapOperaciones);

			if (this.existeTipoDesafio(autenticacionSalidaTO) && isEstadoOkLiberar && validaOpLib) {
				operacionAprobLib = new OperacionAprobLib();
				operacionAprobLib.setNumOperProg(nroOperacion);

				try {
					resultadoLiberar = this.liberarOperacion(nroOperacion, request, mapOperaciones, cargoOnlineTodos);
					operacionAprobLib.setGlosaEstado(resultadoLiberar.get(Constantes.GLS_RESULTADO));
					codEstadoLiberar = resultadoLiberar.get(Constantes.COD_RESULTADO);
					if (Constantes.COD_RESULTADO_EXITOSO.equals(codEstadoLiberar)) {
						operacionesLiberadas.add(operacionAprobLib);
					}
					else {
						operacionesNoLiberadas.add(operacionAprobLib);
					}
				}
				catch (Exception e) {
					LOGGER.info("Error al liberar operacion Tef LBTR MN: rutCliente [{}] rutUsuario [{}] numOperProg[{}] Error[{}]",
							rutCliente, rutUsuario, nroOperacion, e.getMessage());
					codEstadoLiberar = Constantes.COD_ESTADO_REVERSA;
				}

			}
			else {
				operacionAprobLib = new OperacionAprobLib();
				operacionAprobLib.setNumOperProg(nroOperacion);
				operacionAprobLib.setGlosaEstado(this.getGlosaEstadoErrorLib(autenticacionSalidaTO, isEstadoOkLiberar, validaOpLib));
				operacionesNoLiberadas.add(operacionAprobLib);
				codEstadoLiberar = Constantes.COD_ESTADO_REVERSA;
			}

			LOGGER.info("Fin proceso liberacion Tef LBTR MN: rutCliente [{}] rutUsuario [{}] numOperProg[{}] codigoEstado[{}]", 
					request.getRutEmpresa(), request.getRutApoderado(), nroOperacion, codEstadoLiberar);

		} //for
		
		operaciones.add(operacionesNoLiberadas);
		operaciones.add(operacionesLiberadas);
		
		return operaciones;
		
		
	}
	

	private int validaSaldoCtaParaguaCCLV(boolean esCuentaParagua, String tipoOperacion, BigDecimal saldoDisponible, BigDecimal saldoLiberar) {
		// se valida saldo sólo en caso de cuenta PARAGUA o CCLV (no aplica para SIMPLE ni DVP)
		if (esCuentaParagua || tipoOperacion.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_CCLV)) {
			if (saldoDisponible.compareTo(saldoLiberar) < 0) {
				return -1;
			} else {
				return 0;
			}
		}
		
		return 0;
		
	}
	
	private List<String> getDatosErrorLib(String glsEstado) {
		List<String> datos = new ArrayList<>();
		switch (glsEstado) {
		case Constantes.GLS_ERROR_GENERICO:
			datos = this.getMsgGenerico();
			break;
		case Constantes.VAL_CAMPO_ESTADO_PROCESADA:
			datos.add(String.valueOf(Constantes.COD_ESTADO_ERROR_OPER_PROCESADA));
			datos.add(Constantes.GLS_OPER_PROG_YA_LIBERADA);
			break;
		case Constantes.GLOSA_EXEC_GFS:
			datos.add(String.valueOf(Constantes.COD_ESTADO_ERROR_GFS_EJECUTANDOSE));
			datos.add(Constantes.GLS_ERROR_OPER_EXEC_GFS);
			break;

		default:
			datos = this.getMsgGenerico();
			break;
		}
		
		return datos;
	}
	
	private List<String> getMsgGenerico() {
		List<String> datos = new ArrayList<>();
		datos.add(Constantes.COD_RESULTADO_VALIDA_ERROR);
		datos.add(Constantes.GLS_ERROR_GET_PARAM_CAMPO_2);
		
		return datos;
	}
	
	private boolean isEstadoOkCampoDos(String glsEstado) {
		return (glsEstado.startsWith("N") ? Boolean.TRUE.booleanValue() : Boolean.FALSE.booleanValue());
	}
	
	private Map<String, String> execFlujoCargoEnLinea(Map<String, Object> mapOperacionSp, String nroOperacion, LiberarOperacionesPortalRequest request) {
        int codResultado = -1 ;
        String glsResultado = "";
		Map<String, String> resultado = new HashMap<>();
		Map<String, String> resultadoActDet = new HashMap<>();
		
		boolean esSoloCargo = operacionesEmpresaService.esSoloCargo((String) mapOperacionSp.get("VALOR15"),((String) mapOperacionSp.get("TIPO")).trim());
		
		try {
			String numCuentaCargo = ((String)mapOperacionSp.get("VALOR9")).replaceFirst("^0+(?!$)", "");
			String montoOperacion = mapOperacionSp.get("VALOR1").toString();
			String estadoOperacion = operProgService.obtenerProcesandoOperacion(nroOperacion);

			if (!this.isEstadoOkCampoDos(estadoOperacion)) {
				LOGGER.info("No se pudo liberar operación. Operación N°: [{}] tiene estado [{}] rutCliente [{}] rutUsuario [{}] ", 
						nroOperacion, estadoOperacion, request.getRutEmpresa(), request.getRutApoderado());
				List<String> datos = this.getDatosErrorLib(estadoOperacion);
				codResultado = Integer.parseInt(datos.get(0));
				glsResultado = datos.get(1);
			} else if (estadoOperacion.startsWith("N")) {
				resultadoActDet = this.validaActualizaDetalle(nroOperacion, Integer.parseInt(Constantes.COD_CAMPO_ESTADO_PROCESO), Constantes.GLOSA_EXEC_GFS);

				if (!resultadoActDet.get(Constantes.COD_RESULTADO).equals("0")) {
					LOGGER.info("No se pudo actualizar la operación en la tbl_detalle_camp, rutCliente [{}] rutUsuario [{}] num_oper_prog: [{}] cod estado: {}, glosa error: {}",
							request.getRutEmpresa(), request.getRutApoderado(), nroOperacion, resultadoActDet.get(Constantes.COD_RESULTADO), 
							resultadoActDet.get(Constantes.GLS_RESULTADO));
					codResultado = Constantes.COD_ESTADO_ERROR_UPD_DETALLE_CAMP;
					glsResultado = Constantes.GLS_ERROR_UPD_DETALLE_CAMP;
					this.actualizaEstadoOperProg(nroOperacion, Integer.parseInt(Constantes.COD_ESTADO_REVERSA));
					tefPortalService.execRollbackDetalleCampLiberar(nroOperacion);
				} else {
					LOGGER.info("Actualización exitosa en tbl_detalle_camp, rutCliente [{}] rutUsuario [{}] num_oper_prog: [{}] cod estado: {}, glosa error: {}",
							request.getRutEmpresa(), request.getRutApoderado(), nroOperacion, resultadoActDet.get(Constantes.COD_RESULTADO), 
							resultadoActDet.get(Constantes.GLS_RESULTADO));
					Map<String, String> datosLib = this.execLiberacionCartolaGfs(request, numCuentaCargo, nroOperacion, montoOperacion, esSoloCargo, mapOperacionSp);
					codResultado = Integer.parseInt(datosLib.get(Constantes.COD_RESULTADO));
					glsResultado = datosLib.get(Constantes.GLS_RESULTADO);
				}
			}
		} catch (Exception e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			codResultado = -1;
			glsResultado = Constantes.GLS_ERROR_GENERICO_LIBERADA;
			this.actualizaEstadoOperProg(nroOperacion, Integer.parseInt(Constantes.COD_ESTADO_REVERSA));
			tefPortalService.execRollbackDetalleCampLiberar(nroOperacion);
		}
		
        finally {
        	if (codResultado != 0) {
        		this.actualizaDetalleCamp(nroOperacion, Integer.parseInt(Constantes.COD_CAMPO_ESTADO_PROCESO), Constantes.VAL_CAMPO_ESTADO_NO_PROCESADA);
        	}
        	resultado.put(Constantes.COD_RESULTADO, String.valueOf(codResultado));
            resultado.put(Constantes.GLS_RESULTADO, glsResultado);        	
        }
		
		return resultado;
		
	}
	
	private Map<String, String> validaActualizaDetalle(String nroOperacion, int codCampo, String valCampo) {
		Map<String, String> resultado = new HashMap<>();
		int codResultado = -1;
		String glsResultado = "";
		
		try {
			String act = this.actualizaDetalleCamp(nroOperacion, codCampo, valCampo);
			if (Constantes.GLS_OK.equals(act)) {
		       	codResultado = Integer.parseInt(Constantes.COD_RESULTADO_EXITOSO);
	            glsResultado = "";   
			}
			else {
				codResultado = Constantes.COD_ESTADO_ERROR_UPD_DETALLE_CAMP;
				glsResultado = Constantes.GLS_ERROR_UPD_DETALLE_CAMP;  
			}
		}
		catch (Exception e) {
			codResultado = -1;
		}
		
		finally {
	       	resultado.put(Constantes.COD_RESULTADO, String.valueOf(codResultado));
            resultado.put(Constantes.GLS_RESULTADO, glsResultado);   
		}
		
		return resultado;
	}
	
	
	private Map<String, String> execLiberacionCartolaGfs(LiberarOperacionesPortalRequest request, String numCuentaCargo, String numOperProg, 
			String montoOperacion, boolean esSoloCargo, Map<String, Object> mapOperacionSp) {
		
		Map<String, String> resultado = new HashMap<>();
        int codResultado = -1 ;
        String glsResultado = "";
        String glsErrorGfs = "";
        String codLiberacion = Constantes.COD_ESTADO_REVERSA;
        String errorUpdateOperProg = "";
        String errorUpdateDet = "";
        
        try {
        
			String validaCargo = this.validaCargoCartolaProvisoria(numCuentaCargo, request.getRutEmpresa(), request.getRutApoderado(), numOperProg, montoOperacion);
			
			LOGGER.info("resultado valida cargo cartola provisoria, validaCargo [{}] rutCliente [{}] rutUsuario [{}] numeroCuenta [{}] numOperProg [{}] monto [{}]",
					validaCargo, request.getRutEmpresa(), request.getRutApoderado(), numCuentaCargo, numOperProg, montoOperacion);
			
			if (Constantes.CARGO_NO_EXISTE.equals(validaCargo) || Constantes.SIN_MOVIMIENTOS.equals(validaCargo) ) {
				Map<String, Object> respGfs = tefPortalService.execGFS(request, mapOperacionSp, esSoloCargo);
				codLiberacion = (String) respGfs.get(Constantes.COD_ESTADO_LIBERACION);
				errorUpdateOperProg = (String) respGfs.get(Constantes.FLAG_ERROR_UPD_OPER_PROG);
				errorUpdateDet = (String) respGfs.get(Constantes.FLAG_ERROR_UPD_DET_CAMP);
				GENERICFINANCIALSERVICEOUT resp = (GENERICFINANCIALSERVICEOUT) respGfs.get(Constantes.RESPONSE);
				glsErrorGfs = "estado:"+resp.getESTADO() + " desc:"+resp.getDESCRIPCION();
			}
			else {
				this.actualizaDetalleCamp(numOperProg, Integer.parseInt(Constantes.COD_CAMPO_ESTADO_PROCESO), Constantes.VAL_CAMPO_ESTADO_NO_PROCESADA);
				codResultado = Constantes.COD_ESTADO_ERROR_EXEC_CARTOLA;
				glsResultado = Constantes.GLS_ERROR_EXEC_CARTOLA;
			}
			
			LOGGER.info("rutCliente [{}] rutUsuario [{}] numOperProg [{}] codLiberacion [{}]",request.getRutEmpresa(), request.getRutApoderado(), numOperProg, codLiberacion);
			
			if (codLiberacion.equals(Constantes.ESTATUS_LIBERADA_160) || codLiberacion.equals(Constantes.COD_ESTADO_PROCESADO)) {
				this.actualizaDetalleCamp(numOperProg, Integer.parseInt(Constantes.COD_CAMPO_CANAL_LIBERACION_PORTAL), Constantes.CANAL_PORTAL_EMPRESA_TEF_MASIVA); 
				codResultado = Integer.parseInt(Constantes.COD_RESULTADO_EXITOSO);
				glsResultado = Constantes.TEF_LBTR_GLS_OK_LIBERAR;
			}
			else {
				List<String> datos = this.getMsgErrorExecLiberacion(errorUpdateOperProg, errorUpdateDet, glsErrorGfs, codResultado);
				codResultado = Integer.parseInt(datos.get(0));
				glsResultado = datos.get(1);
			}
        }
        catch (Exception e) {
        	codResultado = -1;
        	glsResultado = Constantes.GLS_ERROR_EXEC_GFS;
			this.actualizaEstadoOperProg(numOperProg, Integer.parseInt(Constantes.COD_ESTADO_REVERSA));
			tefPortalService.execRollbackDetalleCampLiberar(numOperProg);
			LOGGER.info("Error al ejecutar liberacion cartola y gfs (Exception) - rutCliente [{}] rutUsuario [{}] numeroCuenta [{}] numOperProg [{}] monto [{}] Error [{}]",
					request.getRutEmpresa(), request.getRutApoderado(), numCuentaCargo, numOperProg, montoOperacion, e.getMessage());
        }
        
        finally {
        	resultado.put(Constantes.COD_RESULTADO, String.valueOf(codResultado));
            resultado.put(Constantes.GLS_RESULTADO, glsResultado);        	
        }
        
        return resultado;
	}
	
	private List<String> getMsgErrorExecLiberacion(String errorUpdateOperProg, String errorUpdateDet, String glsErrorGfs, int codResultado) {
		List<String> datos = new ArrayList<>();
		glsErrorGfs = glsErrorGfs != null && !"".equals(glsErrorGfs) ? " ("+ glsErrorGfs + ")" : "";
		
		if (Constantes.COD_ESTADO_ERROR_EXEC_CARTOLA == codResultado) {
			datos.add(String.valueOf(codResultado));
			datos.add(Constantes.GLS_ERROR_EXEC_CARTOLA);
		}
		else {
			if (Constantes.GLS_OPC_SI.equals(errorUpdateOperProg)) {
				datos.add(String.valueOf(Constantes.COD_ESTADO_ERROR_UPD_OPER_PROG));
				datos.add(Constantes.GLS_ERROR_UPD_OPER_PROG);
			}
			else {
				if (Constantes.GLS_OPC_SI.equals(errorUpdateDet)) {
					datos.add(String.valueOf(Constantes.COD_ESTADO_ERROR_UPD_DETALLE_CAMP));
					datos.add(Constantes.GLS_ERROR_UPD_DETALLE_CAMP);
				}
				else {
					datos.add(String.valueOf(Constantes.COD_ESTADO_ERROR_EXEC_GFS));
					datos.add(Constantes.GLS_ERROR_EXEC_GFS + glsErrorGfs);
				}
			}
		}
		
		return datos;
	}
	
	
	private String validaCargoCartolaProvisoria(String numCuentaCte, String rutCliente, String rutUsuario, String numOperProg, String montoOperacion) {
		LOGGER.info("numCuentaCte [{}] rutCliente [{}] rutUsuario [{}] numOperProg [{}] montoOperacion [{}]", 
				numCuentaCte, rutCliente, rutUsuario, numOperProg, montoOperacion);
		return Constantes.CARGO_NO_EXISTE;
	}	
	
	
	private  Map<String, String> execFlujoSinCargoEnLinea(Map<String, Object> mapOperacionSp, boolean esCuentaParagua, LiberarOperacionesPortalRequest request, String nroOperacion) {
        int codResultado = -1 ;
        String glsResultado = "";
        Map<String, String> resultado = new HashMap<>();
		
		try {
			Estado estadoTemp = new Estado(EstadoEnum.EXITO.getCodigoEstado(),EstadoEnum.EXITO.getParametroGlosaEstado());
			String cuentaCargo = (String) mapOperacionSp.get("VALOR9");
			BigDecimal saldoLiberar = new BigDecimal((String) mapOperacionSp.get("VALOR1"));
			BigDecimal saldoDisponible;
			
			
			String strSaldo = this.getSaldoDisponibleLbtr(mapOperacionSp, esCuentaParagua, request, cuentaCargo, estadoTemp);
			if (strSaldo.equals(Constantes.GLS_ERROR_SALDO_CTA)) {
				resultado.put(Constantes.COD_RESULTADO, String.valueOf(Constantes.COD_ESTADO_ERROR_LIB_SALDO_CTA));
		        resultado.put(Constantes.GLS_RESULTADO, Constantes.GLS_ERROR_LIB_SALDO_CTA);
				return resultado;
			}
			else {
				saldoDisponible = BigDecimal.valueOf(Long.parseLong(strSaldo));
			}
			
			
			int validaSaldoCCLV = this.validaSaldoCtaParaguaCCLV(esCuentaParagua, ((String)mapOperacionSp.get("TIPO")).trim(), saldoDisponible, saldoLiberar);
			if (validaSaldoCCLV < 0) {
				resultado.put(Constantes.COD_RESULTADO, String.valueOf(Constantes.COD_ESTADO_ERROR_LIB_SALDO_CCLV));
		        resultado.put(Constantes.GLS_RESULTADO, Constantes.GLS_ERROR_LIB_SALDO_CCLV);
				return resultado;
			}
	
			LOGGER.info("Saldo disponible: [{}], saldo a liberar: [{}], cuenta nro: [{}] rutCliente [{}] rutUsuario [{}]", 
					saldoDisponible,saldoLiberar, cuentaCargo, request.getRutEmpresa(), request.getRutApoderado());
	
			Map<String, String> mapOperacionRequest = new HashMap<>();
			mapOperacionRequest.put(Constantes.NUM_OPER_PROG, nroOperacion);
			mapOperacionRequest.put(Constantes.INESTADO_SOAP, Constantes.ESTATUS_LIBERADA_160);
			// se actualiza en tbl_oper_prog
			
			
			ActualizaOpOut respOperacion = clienteGenericOperProg.actualizarData(mapOperacionRequest);
			if (Constantes.VALOR_CERO != respOperacion.getCodEstado()) {
				LOGGER.info("No se pudo actualizar la operación en la tbl_oper_prog, rutCliente [{}] rutUsuario [{}] num_oper_prog: [{}] cod estado: {}, glosa error: {}",
						request.getRutEmpresa(), request.getRutApoderado(), nroOperacion, respOperacion.getCodEstado(), respOperacion.getMsgResult());
				codResultado = Constantes.COD_ESTADO_ERROR_UPD_OPER_PROG;
		        glsResultado = Constantes.GLS_ERROR_UPD_OPER_PROG;
			} else {
				LOGGER.info("Actualización exitosa en tbl_oper_prog, rutCliente [{}] rutUsuario [{}] num_oper_prog: [{}], cod estado: {}, glosa estado: {}",
						request.getRutEmpresa(), request.getRutApoderado(), nroOperacion, respOperacion.getCodEstado(), respOperacion.getMsgResult());
	
				String detalleInSoap = Constantes.COD_CAMPO_RUT_LIBERADOR + "=" + request.getRutApoderado() + ";";
				detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_NOM_LIBERADOR + "=" + this.getNombreApoderado(request.getRutApoderado()) + ";";
				String fechaHoy = empresasService.fechaHoy(Constantes.FORMAT_DD_MM_YYYY_HHMMSS);
				detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_CANAL_LIBERACION_PORTAL + "=" + Constantes.CANAL_PORTAL_EMPRESA + ";";
				detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_FECHA_HORA_LIBERACION + "=" + fechaHoy + ";";
				ActualizaDetCampOut resActualizaDetalleOp = clienteGenericOperProg.actualizarDetalle(nroOperacion, detalleInSoap);
				if (resActualizaDetalleOp.getCodEstado() != 0) {
					codResultado = Constantes.COD_ESTADO_ERROR_UPD_DETALLE_CAMP;
			        glsResultado = Constantes.GLS_ERROR_UPD_DETALLE_CAMP;
					this.actualizaEstadoOperProg(nroOperacion, Integer.parseInt(Constantes.COD_ESTADO_REVERSA));
					LOGGER.info("No se pudo actualizar la operación en la tbl_detalle_camp, rutCliente [{}] rutUsuario [{}] num_oper_prog: [{}] cod estado: {}, glosa error: {}",
							request.getRutEmpresa(), request.getRutApoderado(), nroOperacion, resActualizaDetalleOp.getCodEstado(), resActualizaDetalleOp.getMsgStatus());
				}
				else {
					codResultado = Integer.parseInt(Constantes.COD_RESULTADO_EXITOSO);
			        glsResultado = Constantes.TEF_LBTR_GLS_OK_LIBERAR;
					LOGGER.info(
							"Actualización exitosa en tbl_detalle_camp, rutCliente [{}] rutUsuario [{}] num_oper_prog: [{}] cod estado: {}, glosa : {}",
							request.getRutEmpresa(), request.getRutApoderado(), nroOperacion, resActualizaDetalleOp.getCodEstado(), resActualizaDetalleOp.getMsgStatus());
				}
				
			}
		}
		catch (BancaEmpresasException e) {
			LOGGER.info("Error al ejecutar flujo sinCargoEnLinea - rutCliente [{}] rutUsuario [{}] num_oper_prog: [{}] Error: [{}]", 
					request.getRutEmpresa(), request.getRutApoderado(), nroOperacion, e.getMessage());
			this.actualizaEstadoOperProg(nroOperacion, Integer.parseInt(Constantes.COD_ESTADO_REVERSA));
			tefPortalService.execRollbackDetalleCampLiberar(nroOperacion);
			codResultado = -1;
		}
		
		
		resultado.put(Constantes.COD_RESULTADO, String.valueOf(codResultado));
        resultado.put(Constantes.GLS_RESULTADO, glsResultado);

		return resultado;
		
	}
	
	private String getSaldoDisponibleLbtr(Map<String, Object> mapOperacionSp, boolean esCuentaParagua, LiberarOperacionesPortalRequest request, 
			String cuentaCargo, Estado estadoTemp) {
		
		BigDecimal saldoDisponible = new BigDecimal(0);
		String strResultado = "";
		
		try {
			if (esCuentaParagua) {
				saldoDisponible = saldoDisponible.add(obtenerSaldoCuentaParagua(request, cuentaCargo, estadoTemp));
				if (!estadoTemp.isEXITO()) {
					strResultado = Constantes.GLS_ERROR_SALDO_CTA;
				}
	
			} else if (((String) mapOperacionSp.get("TIPO")).trim()
					.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_SIMPLE)
					|| ((String) mapOperacionSp.get("TIPO")).trim()
							.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_DVP)) {
				saldoDisponible = saldoDisponible.add(obtenerSaldoCuenta(request, cuentaCargo));
				if (!estadoTemp.isEXITO()) {
					strResultado = Constantes.GLS_ERROR_SALDO_CTA;
				}
	
			} else if (((String) mapOperacionSp.get("TIPO")).trim()
					.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_CCLV)) {
				saldoDisponible = saldoDisponible.add(calcularSaldoCclv(request, cuentaCargo, estadoTemp));
				if (!estadoTemp.isEXITO())
					strResultado = Constantes.GLS_ERROR_SALDO_CTA;
			}
			
			if (!strResultado.equals(Constantes.GLS_ERROR_SALDO_CTA)) {
				strResultado = String.valueOf(saldoDisponible.longValue());
			}

		}
		catch (Exception e) {
			strResultado = Constantes.GLS_ERROR_SALDO_CTA;
		}
		
		return strResultado;
		
	}
	
	
	private String isCtaParagua(Map<String, Object> mapOperacionSp, String rutEmpresa) {
		boolean esCuentaParagua = false;
		String respuesta = "";
		
		try {
			if (((String) mapOperacionSp.get("TIPO")).trim()
					.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_SIMPLE)
					|| ((String) mapOperacionSp.get("TIPO")).trim()
							.equalsIgnoreCase(Constantes.SERVICIO_OPERACION_EMPRESA_TIPO_CCLV)) {
				esCuentaParagua = validacionPortalService.empresaOperaCuentaParaguaLbtr(rutEmpresa);
				respuesta = (esCuentaParagua ? Constantes.GLS_OPC_SI : Constantes.GLS_OPC_NO);
			}
		}
		catch (BancaEmpresasException e) {
			LOGGER.info("OperacionesEmpresaPortalService.isCtaParagua - Error: [{}]",e.getMessage());
			respuesta = Constantes.GLS_ERROR_GENERICO;
		}
		
		return respuesta;
	}
	
	private String getMensajeAprob(String mensaje) {
		return "".equals(mensaje) ? mensaje : ", " +mensaje;
			
	}
	
	
	private void execReversaActualizaOperProgFirma(boolean resultOperProg, boolean resultFirma, String numOperProg, int codEstado, String rutApoderado) {
		if (resultOperProg && !resultFirma) {
			//actualizar operprog a estado inicial
			this.actualizaEstadoOperProg(numOperProg, codEstado);
		}
		if (!resultOperProg && resultFirma) {
			//eliminar firma
			try {
				this.eliminaFirmaApoderado(numOperProg, rutApoderado);
			}
			catch (Exception e) {
				LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
			}
		}
		
	}
	
	private int getCodEstadoOperProg(String numOperProg, MapOperaciones mapOperaciones, boolean isAprobar) {
		int codEstado = 30;
		String glsEstado = "";
		
		try {
			glsEstado = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, numOperProg, Constantes.NOM_CAMPO_ESTADO, isAprobar);
			
			switch (glsEstado) {
			case Constantes.GLS_ESTADO_FIRMA_PENDIENTE:
				codEstado = 30;
				break;
			case Constantes.GLS_ESTADO_FIRMA_PARCIAL:
				codEstado = 40;
				break;
			case Constantes.GLS_ESTADO_FIRMADO:
				codEstado = 50;
				break;

			default:
				break;
			}
		}
		catch (NoEncontradoException e) {
			LOGGER.error("OperacionesEmpresaPortalService.getCodEstadoOperProg - Error: [{}]",e.getMessage());
		}
		
		return codEstado;
		
	}
	
	private boolean estadoOkAprobar(String numOperProg, MapOperaciones mapOperaciones, boolean isAprobar) {
		int codEstado = getCodEstadoOperProg(numOperProg, mapOperaciones, isAprobar);
		return (codEstado == Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR || codEstado == Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL);
	}

	private String getGlosaEstadoErrorAprob(AutenticacionSalidaTO autenticacionSalidaTO, boolean isEstadoOkAprob, boolean cumplePertenencia) {
		String salida = "";
		if (!isEstadoOkAprob) {
			salida = Constantes.GLS_OPER_PROG_ERROR_APROBADA;
		}
		else {
			if (!cumplePertenencia) {
				salida = Constantes.GLS_ERROR_OPER_SIN_PERTENENCIA;
			}
			else {
				salida = autenticacionSalidaTO == null ? Constantes.GLS_OPER_SIN_SFA : Constantes.GLS_ERROR_SRV_AUTENTICACION;
			}
		}
		return salida;		
	}
	
	private boolean estadoOkLiberar(String numOperProg, MapOperaciones mapOperaciones) {
		int codEstado = this.getCodEstadoOperProg(numOperProg, mapOperaciones, true);
		return (codEstado == Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_FIRMA_COMPLETA);
	}
	
	private String getGlosaEstadoErrorLib(AutenticacionSalidaTO autenticacionSalidaTO, boolean isEstadoOkLiberar, boolean validaOpLib) {
		String glsError = "";
		if (autenticacionSalidaTO == null) {
			glsError = Constantes.GLS_OPER_SIN_PIN_BICE; 
		}
		else {
			if (!isEstadoOkLiberar) {
				glsError = Constantes.GLS_OPER_PROG_ERROR_EST_LIBERADA;
			}
			else {
				if (!"".equals(autenticacionSalidaTO.getGlsError()) ) {
					glsError = autenticacionSalidaTO.getGlsError();
				}
				else {
					glsError = this.getEstadoLib(validaOpLib);
				}
			}
		}
		
		return glsError;
				
	}
	
	private String getEstadoLib(boolean validaOpLib) {
		return (validaOpLib == Boolean.TRUE.booleanValue() ? Constantes.GLS_OPER_PROG_ERROR_EST_LIBERADA : Constantes.GLS_OPER_PROG_ERROR_EST_LIBERADA_INC);
	}

	
	private boolean validaOperProgLiberar(String numOperProg, MapOperaciones mapOperaciones, String nombreCampo) {
		return validacionPortalService.validaOperProgLib(numOperProg, mapOperaciones, nombreCampo);
	}
	
	
	/*
	 * Realiza las siguientes validaciones: 
	 * 		a) Valida horario tope de aprobacion cuando la fecha de curse es igual al dia actual
	 * 		b) Valida Monto maximo permitido para aprobar segun tipo de bicepass
	 * 		c) Valida si el tipo de Tef LBTR esta habilitada para Aprobacion
	 */
	private HashMap<String, String> validacionesNegocio(String rutCliente, String numOperProg, MapOperaciones mapOperaciones, List<Map<String, Object>> listaParametros, 
			String tipoSFA, List<Map<String, Object>> listaParamSis, String rutCCLV) {
		HashMap<String, String> resultado = new HashMap<>();
		HashMap<String, String> datosValida = new HashMap<>();
		String codResultado = Constantes.COD_RESULTADO_VALIDA_OK;
		String glsResultado = Constantes.GLS_RESULTADO_VALIDA_OK;
		String horarioTopeAprob = "";
		String tipoLbtrExcluidas = "";
		
		try {
			if (TefMasivaUtil.isListasOk(listaParametros, listaParamSis)) {
				horarioTopeAprob = this.getValorParametro(listaParametros, Constantes.NOM_TIPO_HORA_LIM_INGRESO);
				tipoLbtrExcluidas = this.getValorParametro(listaParametros, Constantes.TIPO_LBTR_EXCLUIDA);
				
				if (this.isTefLbtrExcluida(rutCliente, numOperProg, mapOperaciones, tipoLbtrExcluidas, rutCCLV, listaParamSis)) {
					codResultado = Constantes.COD_RESULTADO_VALIDA_ERROR;
					glsResultado = Constantes.GLS_ERROR_VALIDA_TEF_EXCLUIDA;
				}
				else {
					datosValida = this.validaHorarioTopeAprobacion(numOperProg, mapOperaciones, horarioTopeAprob);
					LOGGER.info("validaHorarioTopeAprobacion numOperProg [{}]  horarioTopeAprob [{}] codResultadoValidacion [{}] glsResultadoValidacion [{}]", 
							numOperProg, horarioTopeAprob, datosValida.get(Constantes.COD_RESULTADO), datosValida.get(Constantes.GLS_RESULTADO));
					
					if (!Constantes.COD_RESULTADO_VALIDA_OK.equals(datosValida.get(Constantes.COD_RESULTADO))) {
						codResultado = Constantes.COD_RESULTADO_VALIDA_ERROR;
						glsResultado =  datosValida.get(Constantes.GLS_RESULTADO);
					}
					else {
						datosValida = this.validaMontoTefLbtrFda(numOperProg, mapOperaciones, listaParametros, tipoSFA);
						LOGGER.info("validaMontoTefLbtrFda --> numOperProg [{}] codResultadoValidacion [{}] glsResultadoValidacion [{}]", 
								numOperProg, datosValida.get(Constantes.COD_RESULTADO), datosValida.get(Constantes.GLS_RESULTADO));
						
						if (!Constantes.COD_RESULTADO_VALIDA_OK.equals(datosValida.get(Constantes.COD_RESULTADO))) {
							codResultado = Constantes.COD_RESULTADO_VALIDA_ERROR;
							glsResultado =  datosValida.get(Constantes.GLS_RESULTADO);
						}
					}
				}
			}
			else {
				codResultado = Constantes.COD_RESULTADO_VALIDA_ERROR;
				glsResultado = Constantes.GLS_RESULTADO_VALIDA_ERROR;
			}
		}
		catch (Exception e) {
			LOGGER.error("Error (Exception): [{}]",e.getMessage());
			codResultado = Constantes.COD_RESULTADO_VALIDA_ERROR;
			glsResultado = Constantes.GLS_RESULTADO_VALIDA_ERROR;
		}
		
		finally {
			resultado.put(Constantes.COD_RESULTADO, codResultado);
	        resultado.put(Constantes.GLS_RESULTADO, glsResultado);
		}
		
		return resultado;

	}
	
	private HashMap<String, String> validaMontoTefLbtrFda(String numOperProg, MapOperaciones mapOperaciones, List<Map<String, Object>> listaParametros, String tipoSFA) {
		String montoOp = "";
		String montoMaximoSinFda = "";
		HashMap<String, String> resultado = new HashMap<>();
		String codResultado = Constantes.COD_RESULTADO_VALIDA_OK;
		String glsResultado = Constantes.GLS_RESULTADO_VALIDA_OK;
		
		try {
			montoOp = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, numOperProg, Constantes.NOM_CAMPO_MONTO, true);
			montoMaximoSinFda = this.getValorParametro(listaParametros, Constantes.NOM_TIPO_MONTO_FDA);
			if ( !"".equals(montoMaximoSinFda) &&  (Long.parseLong(montoOp) > Long.parseLong(montoMaximoSinFda)) && !this.firmadoFda(tipoSFA)  ) {
				codResultado = Constantes.COD_RESULTADO_VALIDA_ERROR;
				glsResultado = this.getMensajeMontoFda(Constantes.MSG_EXCEDE_MONTO_SIN_FDA, montoMaximoSinFda);
			}
		}
		catch (Exception e) {
			codResultado = Constantes.COD_RESULTADO_VALIDA_ERROR;
			glsResultado = Constantes.GLS_RESULTADO_VALIDA_ERROR;
			LOGGER.error("OperacionesEmpresaPortalService.validaMontoTefLbtrFda - Error: [{}]",e.getMessage());
		}
		
		finally {
		   	resultado.put(Constantes.COD_RESULTADO, codResultado);
	        resultado.put(Constantes.GLS_RESULTADO, glsResultado);       
		}
		
		return resultado;
		
	}
	
	private String getMensajeMontoFda(String msg, String monto) {
		monto = RequestUtil.agregaPuntos(Long.parseLong(monto));
		return msg.replace("&MONTO", monto);
	}
	
	private boolean firmadoFda(String tipoSFA) {
		return (Constantes.SFA_FDA_ESIGN.equals(tipoSFA) || Constantes.SFA_FDA_CERTINET.equals(tipoSFA));
		
	}
	
	public List<Map<String, Object>> obtenerParametrosValidacion(String nomParametro) {
		List<Map<String, Object>> salida = null;
		
		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_NOM_PARAM, nomParametro);

		try {
			salida = portalOrawRepository.getParametrosValidacion(params);
			
		} catch (SQLException e) {
			LOGGER.error("OperacionesEmpresaPortalService.obtenerParametrosValidacion - Error: [{}]",e.getMessage());
		}

		return salida;
	}
	
	private String getValorParametro(List<Map<String, Object>> listaParametros, String nomTipo) {
		String valor = "";
		
		if (listaParametros != null) {
			for (Map<String, Object> parametro : listaParametros) {
				if (nomTipo.equals(parametro.get("NOM_TIPO"))) {
					valor = (String)parametro.get("VAL_PARAMETRO");
					break;
				}
			}
		}
		
		return valor;
	}
	
	private boolean existeTipoDesafio(AutenticacionSalidaTO autenticacionSalidaTO) {
		return autenticacionSalidaTO != null &&  !"".equals(autenticacionSalidaTO.getTipoDesafio()) && !Constantes.GLS_ERROR_SRV_AUTENTICACION.equals(autenticacionSalidaTO.getGlsError());
	}
	
	
	public List<Map<String, Object>> obtenerFirmasOperProg(String numOperProg) {
		List<Map<String, Object>> salida = null;
		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPER_PROG, numOperProg);

		try {
			salida = portalOrawRepository.getFirmasOperProg(params);
		} catch (SQLException e) {
			LOGGER.error("Error al obtener firmas - numOperProg [{}] Error: [{}]", numOperProg, e.getMessage());
		}

		return salida;
		
	}
	
	
	private boolean existeFirmaPrevia(String numOperProg, String rutUsuario) {
		boolean existe = false;
		List<Map<String, Object>> listaFirma = this.obtenerFirmasOperProg(numOperProg);
		
		try {
			if (listaFirma != null && !listaFirma.isEmpty()) {
				for (Map<String, Object> parametro : listaFirma) {
					if (rutUsuario.equals(parametro.get("RUT_APODERADO"))) {
						existe = true;
						break;
					}
				}
			}
		}
		catch (Exception e) {
			existe = true;
		}
		
		return existe;

	}
	
	public List<Map<String, Object>> obtenerDatosUsuarioCliente(String rutCliente, String rutUsuario) {
		List<Map<String, Object>> salida = null;
		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_CLI, rutCliente);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU, rutUsuario);
		
		try {
			salida = portalOrawRepository.getDatosUsuarioCliente(params);
		} catch (SQLException e) {
			LOGGER.error("Error al obtener datos usuario cliente - rutCliente [{}] rutUsuario [{}] Error: [{}]", rutCliente, rutUsuario, e.getMessage());
		}

		return salida;
		
	}
	
	
	/**
	 * Valida si la fecha de valuta corresponde al dia actual
	 * 
	 * @param fechaValuta (string dd-mm-YYYY)
	 * @return String
	 * @throws 
	 */
	public String isFechaValutaLbtrHoy(String fechaValuta) {
		String respuesta = "";

		try {
			String fechaActual = empresasService.fechaHoy(Constantes.FORMAT_YYYYMMDDHHMMSS);

			DateTimeFormatter formatterFecHoy = DateTimeFormatter.ofPattern(Constantes.FORMAT_YYYYMMDDHHMMSS);
			DateTimeFormatter formatterFecValuta = DateTimeFormatter.ofPattern(Constantes.FORMAT_DD_MM_YYYY);
			LocalDate fechaHoyLocalDate = LocalDate.parse(fechaActual, formatterFecHoy);
			LocalDate fechaValutaLbtrLocalDate = LocalDate.parse(fechaValuta, formatterFecValuta);

			String horaHoy = fechaActual.substring(8, 12);
			String horaCorteLib = "";

			List<ParametrosVo> horaCorteParametros = consultarParametrosService.consultaParametro("HORACORTETEF", "LBTRCARGOONLINE");
			if (horaCorteParametros != null && !horaCorteParametros.isEmpty()) {
				horaCorteLib = horaCorteParametros.get(0).getValParametro() != null ? horaCorteParametros.get(0).getValParametro().trim() : "";
				
				if (fechaValutaLbtrLocalDate.isEqual(fechaHoyLocalDate)
						&& Integer.valueOf(horaHoy).compareTo(Integer.valueOf(horaCorteLib)) < 0) {
					respuesta = Constantes.GLS_OPC_SI;
				}
				else {
					respuesta = Constantes.GLS_OPC_NO;
				}
			}


		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			respuesta = Constantes.GLS_ERROR_GENERICO;
		}

		return respuesta;

	}
	
	private HashMap<String, String> validaHorarioTopeAprobacion(String numOperProg, MapOperaciones mapOperaciones, String horarioTope) {
		String fechaValutaOper = "";
		String fechaHoraSistema = "";
		String fechaSistema = "";
		String horaSistema = "0";
		HashMap<String, String> resultado = new HashMap<>();
		String codResultado = Constantes.COD_RESULTADO_VALIDA_OK;
		String glsResultado = Constantes.GLS_RESULTADO_VALIDA_OK;
		
		try {
			if (!"".equals(horarioTope)) {
				fechaHoraSistema = empresasService.fechaHoy(Constantes.FORMAT_YYYYMMDDHHMMSS);
				fechaSistema = fechaHoraSistema.substring(0,8);
				horaSistema  = fechaHoraSistema.substring(8, 12);
				
				fechaValutaOper = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, numOperProg, Constantes.NOM_CAMPO_FECHA_VALUTA, true);
				fechaValutaOper = fechaValutaOper.replace("/", "");
				fechaValutaOper = fechaValutaOper.replace("-", "");
				fechaValutaOper = fechaValutaOper.substring(4,8) + fechaValutaOper.substring(2,4) + fechaValutaOper.substring(0,2);
				
				LOGGER.info("numOperProg [{}] fechaHoraSistema [{}] fechaSistema [{}] horaSistema [{}] horarioTope [{}] fechaValutaOper [{}]", 
						numOperProg, fechaHoraSistema, fechaSistema, horaSistema, horarioTope, fechaValutaOper);

				
				if (fechaValutaOper.equals(fechaSistema) && Integer.parseInt(horaSistema) > Integer.parseInt(horarioTope) ) {
					codResultado = Constantes.COD_RESULTADO_VALIDA_ERROR;
					glsResultado = Constantes.MSG_EXCEDE_HORARIO_LIMITE_APROB.replace("&HORARIO_LIMITE", TefMasivaUtil.getHoraFormatoFront(horarioTope));
				}
			}
		}
		catch (Exception e) {
			codResultado = Constantes.COD_RESULTADO_VALIDA_ERROR;
			glsResultado = Constantes.GLS_ERROR_VALIDA_HORARIO_APROB;
			LOGGER.error("OperacionesEmpresaPortalService.validaHorarioTopeAprobacion - Error: [{}]",e.getMessage());
		}
		
		finally {
		   	resultado.put(Constantes.COD_RESULTADO, codResultado);
	        resultado.put(Constantes.GLS_RESULTADO, glsResultado);       
		}
		
		return resultado;
		
	}
	
	
	public void eliminaFirmaApoderado(String numOperProg, String rutApoderado) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPER_PROG, numOperProg);
		parametros.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU, rutApoderado);

		try {
			portalOrawRepository.eliminaFirmaApoderado(parametros);
		}
		catch (Exception e) {
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
		}
				
	}
	
	
	private boolean isTefLbtrExcluida(String rutCliente, String numOperProg, MapOperaciones mapOperaciones, String tipoLbtrExcluidas, String rutCCLV, 
			List<Map<String, Object>> listaParamSis) {
		boolean isExcluida = false;
		String[] arrayTipoLbtrExcluida = null;
		String tipoLbtrParam = "";
		
		try {
			tipoLbtrParam = this.getTipoLbtr(rutCliente, numOperProg, mapOperaciones, rutCCLV, listaParamSis);
			arrayTipoLbtrExcluida = tipoLbtrExcluidas.split("-");
			if (arrayTipoLbtrExcluida != null) {
				for (int i = 0; i < arrayTipoLbtrExcluida.length; i++) {
					if (tipoLbtrParam.equals(arrayTipoLbtrExcluida[i])) {
						isExcluida = true;
						break;
					}
				}
			}
		}
		catch (Exception e) {
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
		}
		
		return isExcluida;
	}
	
	private String getTipoLbtr(String rutCliente, String numOperProgParam, MapOperaciones mapOperaciones, String rutCCLV, List<Map<String, Object>> listaParamSis) {
		String tipoLbtr = "";
		String codServicioBD = "";
		String numOperProgBD = "";
		String codServicio = "";
		String metodoPago = "";
		String rutAbono = "";
		String numCuentaAbono = "";
		String nombreBancoAbono = "";
		String referencia = "";
		List<String> listaCiaSeguro = null; 
		List<String> listaTipoPago = null;
		List<String> listaNumeroContrato = null;
		List<String> datos = new ArrayList<>();
		
		try {
			rutAbono 	     = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, numOperProgParam, Constantes.NOM_CAMPO_RUT_ABONO, true);
			numCuentaAbono   = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, numOperProgParam, Constantes.NOM_CAMPO_CTA_ABONO, true);
			nombreBancoAbono = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, numOperProgParam, Constantes.NOM_CAMPO_BANCO_BENEF, true);
			referencia       = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, numOperProgParam, Constantes.NOM_CAMPO_REFERENCIA, true);
		}
		catch (NoEncontradoException ne) {
			LOGGER.error(Constantes.GLS_LOG_ERROR,ne.getMessage());
		}
		
		try {
			listaCiaSeguro 		= TefMasivaUtil.getListaValoresParametroSis(listaParamSis, Constantes.VALOR_NOM_TIPO_RUT_CIA);
			listaTipoPago  		= TefMasivaUtil.getListaValoresParametroSis(listaParamSis, Constantes.VALOR_NOM_TIPO_TIPO_PAGO);
			listaNumeroContrato = TefMasivaUtil.getListaValoresParametroSis(listaParamSis, Constantes.VALOR_NOM_TIPO_NUM_CONTRATO);
			
			for (Map<String, Object> mapa : mapOperaciones.getMapOutputSP()) {
				numOperProgBD = mapa.get("NUM_OPER_PROG").toString();
				codServicioBD = mapa.get("COD_SERVICIO").toString();
				metodoPago    = mapa.get("TIPO").toString();
				if (numOperProgParam.equals(numOperProgBD)) {
					codServicio = codServicioBD;
					break;
				}
			}
			
			datos.add(rutCliente);
			datos.add(codServicio);
			datos.add(referencia);
			datos.add(metodoPago);
			datos.add(rutAbono);
			datos.add(numCuentaAbono);
			datos.add(nombreBancoAbono);
			datos.add(rutCCLV);
			tipoLbtr = TefMasivaUtil.getGlosaTipoLbtr(datos, listaCiaSeguro, listaTipoPago, listaNumeroContrato);
			
		}
		catch (Exception e) {
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
		}
		
		return tipoLbtr;
	}
	
	
	private void validaCampoDos(MapOperaciones mapOperaciones, String numOperProg) {
		if (Constantes.SERVICIO_OPERACION_EMPRESA_COD_LBTR_SOMA.equals(TefMasivaUtil.getCodServicioOperProgDesdeBD(mapOperaciones, numOperProg)) && 
				Constantes.GLS_OPC_NO.equals(this.existeCampoDos(numOperProg))) {
			this.agregaCampoDetalleCamp(numOperProg, Constantes.COD_CAMPO_ESTADO_PROCESO, Constantes.VAL_CAMPO_ESTADO_NO_PROCESADA);
		}
	}
	
	private String existeCampoDos(String numOperProg) {
		String salida = "";

		try {
			salida = this.existeDetalleCampoDos(numOperProg);
		} catch (Exception e) {
			salida = Constantes.GLS_ERROR_GENERICO;
		}
		
		return salida;
	}
	
	
	private String agregaCampoDetalleCamp(String numOperProg, String codCampo, String valCampo) {
		String respuesta = "";
		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_EMPRESAS_V_VAL_CAMPO, valCampo);
		params.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPER_PROG, numOperProg);
		params.put(Constantes.PARAMETRO_EMPRESAS_V_COD_CAMPO, codCampo);
		try {
			portalOrawRepository.agregaCampoDetalleCampPortal(params);
			respuesta = Constantes.GLS_OK;
		}
		catch (Exception e) {
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
			respuesta = Constantes.GLS_ERROR_GENERICO;
		}
		
		return respuesta;
				
	}
	
	private String existeDetalleCampoDos(String numOperProg)  {
		String salida = Constantes.GLS_ERROR_GENERICO;
		
		Map<String, Object> parametrosOper = new HashMap<>();
		parametrosOper.put("V_NUM_OPER_PROG", numOperProg);
		parametrosOper.put(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT, null);
		parametrosOper.put("v_OUT_MSG_RESULT", null);
		parametrosOper.put(V_SALIDA, null);

		try {
			portalOrawRepository.consultarDetalleOperacion(parametrosOper);
			salida = (String) parametrosOper.get(V_SALIDA);
			String codResult = (String) parametrosOper.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT);
			
			if (codResult != null && "-1".equals(codResult) && "".equals(TefMasivaUtil.eliminaEspacios(salida)) ) { //no encontro datos
				salida = "NO";
			}
			else {
				if (codResult != null && !"-1".equals(codResult)) { //error al ejecutar query
					salida = Constantes.GLS_ERROR_GENERICO;
				}
				else {
					if (codResult != null && "0".equals(codResult)) { //si encontro datos
						salida = "SI";
					}
					
				}
			}
			
		} catch (SQLException e) {
			salida = Constantes.GLS_ERROR_GENERICO;
		}

		return salida;
	}
	
	
	private String execMascaraEncriptar(String dato, String rutApoderado) {
		String resp = "";
		
		try {
			resp = mascaraService.encriptarDato(dato, rutApoderado);
		}
		catch (Exception e) {
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
		}
		
		return resp;
	}
	
	
	
	


}
