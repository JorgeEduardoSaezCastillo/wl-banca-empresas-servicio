package cl.bice.banca.empresas.servicio.service;

import java.math.BigDecimal;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.DocumentPdfException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.MontoInvalidoException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.nominas.NominaEnLinea;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.operaciones.ResumenOperacionesMoneda;
import cl.bice.banca.empresas.servicio.model.request.desafios.FirmarTransaccionRequest;
import cl.bice.banca.empresas.servicio.model.request.desafios.ListarCrearDesafiosRequest;
import cl.bice.banca.empresas.servicio.model.request.desafios.ListarDesafioRequest;
import cl.bice.banca.empresas.servicio.model.request.desafios.ValidarEstadoConfirmacionRequest;
import cl.bice.banca.empresas.servicio.model.request.desafios.ValidarMobileSignerRequest;
import cl.bice.banca.empresas.servicio.model.request.mobisigner.SignerTO;
import cl.bice.banca.empresas.servicio.model.request.mobisigner.TransactionRequest;
import cl.bice.banca.empresas.servicio.model.request.mobisigner.ValidarEstadoOperacionResponse;
import cl.bice.banca.empresas.servicio.model.request.mobisigner.ValidarStatusOperacionRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.response.desafios.BICEPASSStatus;
import cl.bice.banca.empresas.servicio.model.response.desafios.FirmarTransaccionResponse;
import cl.bice.banca.empresas.servicio.model.response.desafios.ListarCrearDesafiosResp;
import cl.bice.banca.empresas.servicio.model.response.desafios.ListarDesafioResponse;
import cl.bice.banca.empresas.servicio.model.response.desafios.MobiSignerStatus;
import cl.bice.banca.empresas.servicio.model.response.desafios.ValidarEstadoConfirmacionResp;
import cl.bice.banca.empresas.servicio.model.response.desafios.ValidarMobileSignerResp;
import cl.bice.banca.empresas.servicio.model.response.empresas.DetalleCampoValorTipoOperacion;
import cl.bice.banca.empresas.servicio.model.response.empresas.OperacionesAprobar;
import cl.bice.banca.empresas.servicio.model.response.mobisigner.EnrolamientoResponse;
import cl.bice.banca.empresas.servicio.model.response.mobisigner.TransactionResponse;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.util.BancaUtil;
import cl.bice.banca.empresas.servicio.util.EmailUtil;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.banca.empresas.servicio.util.FormateadorUtil;
import cl.bice.banca.empresas.servicio.util.MapperUtil;

/**
 * Cliente para invocar a los servicios que listan y crean desaf&iacute;os.
 *
 * @author Cristian Pais (TINet)
 * @version 1.0
 */
@Service
public class DesafiosServices {

	public static final String DATOS_TRANSACCION_APROBAR_CAMPO_OPERACION = "Operación:";
	public static final String DATOS_TRANSACCION_APROBAR_VALOR_OPERACION = "Aprobación Tef Alto Valor";
	public static final String DATOS_TRANSACCION_APROBAR_CAMPO_RUTFIRMANTE = "Rut Firmante:";
	public static final String DATOS_TRANSACCION_APROBAR_CAMPO_RUTEMPRESA = "Rut Empresa:";
	public static final String DATOS_TRANSACCION_APROBAR_CAMPO_CANTIDADOPERACIONES = "Cantidad Operaciones:";
	public static final String DATOS_TRANSACCION_APROBAR_CAMPO_MONTOTOTAL = "Monto Total Operaciones:";
	public static final String DATOS_TRANSACCION_APROBAR_CAMPO_FECHA = "Fecha:";
	public static final String DATOS_TRANSACCION_APROBAR_CAMPO_HORA = "Hora:";

	private static final int DESAFIO_FDA_TRANSACTION_REQUEST_TYPE_PDF = 2;

	private static final int DESAFIO_FDA_VALIDAR_MOBILE_SIGNER_REQUEST_DOC_TYPE_PDF = 2;

	private static final String XML_ETIQUETA_APERTURA_TRANSACTION_DATA = "<transactionData>";
	private static final String XML_ETIQUETA_APERTURA_TITLE = "<title>";
	private static final String XML_ETIQUETA_APERTURA_SUB_TITLE = "<subtitle>";
	private static final String XML_ETIQUETA_APERTURA_VALUE = "<value>";
	private static final String XML_ETIQUETA_APERTURA_ELEMENT = "<element>";
	private static final String XML_ETIQUETA_APERTURA_DATA = "<data>";
	private static final String XML_ETIQUETA_APERTURA_KEY = "<key>";
	private static final String XML_ETIQUETA_CIERRE_VALUE = "</value>";
	private static final String XML_ETIQUETA_CIERRE_ELEMENT = "</element>";
	private static final String XML_ETIQUETA_CIERRE_DATA = "</data>";
	private static final String XML_ETIQUETA_CIERRE_KEY = "</key>";
	private static final String XML_ETIQUETA_CIERRE_TITLE = "</title>";
	private static final String XML_ETIQUETA_CIERRE_SUB_TITLE = "</subtitle>";
	private static final String XML_ETIQUETA_CIERRE_TRANSACTION_DATA = "</transactionData>";

	private static final String TRANSACTION_DATA_TITLE = "APROBACIÓN LBTR";

	private static final String DESAFIOS_TIPO_CLIENTE = "5";
	private static final String DESAFIOS_TIPO_CLIENTE_EMPRESA = "6";
	private static final String LISTAR_DESAFIOS_REQUEST_CODIGO_SERVICIO = "20007";
	private static final int LISTAR_DESAFIOS_REQUEST_CAMPO1 = 0;
	private static final String LISTAR_DESAFIOS_REQUEST_CAMPO2 = "";
	private static final String LISTAR_DESAFIOS_REQUEST_CAMPO3 = "Consultar Desafios";
	private static final String LISTAR_DESAFIOS_REQUEST_CAMPO4 = "";
	private static final String LISTAR_DESAFIOS_REQUEST_CAMPO5 = "";

	private static final String CANAL_CREAR_DESAFIO_BICEPASS = "PERSONAS";

	private static final String PROVEEDOR_ESIGN = "ESIGN";
	private static final String PROVEEDOR_ESIGN_COD = "3";

	private static final String FDA_SEGUIMIENTO_PI_FLG_PAG1 = "1";
	private static final String FDA_SEGUIMIENTO_PI_FLG_PAG2 = "1";

	private static final String VALUE_OPERACIONES = "operaciones";

	private static final String TRANSACTION_DATA_TITLE_NOMINAS_DIF = "APROB. NÓMINAS DIFERIDAS";

	private static final String TRANSACTION_DATA_TITLE_NOMINAS_LIN = "APROB. NÓMINAS EN LINEA";

	private static final String DESAFIO_FDA_TRANSACTION_DATA_TITLE_SPOT_WEB = "APROB. SPOTWEB";

	private static final String DESAFIO_FDA_TRANSACTION_DATA_TITLE_TEF_MX = "APROB. TEF MX";
	
	private static final String DESAFIO_FDA_TRANSACTION_DATA_TITLE_BICECOMEX = "APROB. BICECOMEX";

	/**
	 * Rest Template encargado de realizar las llamadas.
	 */
	private final RestTemplate restTemplate;

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DesafiosServices.class);

	/**
	 * Propiedades Externas.
	 */
	@Autowired
	private Properties propiedadesExterna;

	/**
	 * Operaciones empresa.
	 */
	@Autowired
	private OperacionesEmpresaService operacionesEmpresaService;

	/**
	 * Empresas service
	 */
	@Autowired
	EmpresasService empresasService;

	/**
	 * Mobisigner service
	 */
	@Autowired
	MobisignerService mobisignerService;

	/**
	 * Detalle autorización service
	 */
	@Autowired
	DetalleAutorizacionService detalleAutorizacionService;

	/**
	 * Valores campos operaciones service
	 */
	@Autowired
	ValoresCampoOperacionesService valoresCampoOperacionesService;

	@Autowired
	ValidacionService validacionService;

	@Autowired
	NominasEnLineaService nominasEnLineaService;

	@Autowired
	PortalOrawRepository portalOrawRepository;
	
	@Autowired
	BiceComexService biceComexService;
	
	@Autowired
	ParametrosValidacionService parametrosValidacionService;
	
	@Autowired
	private Environment environment;

	public DesafiosServices(RestTemplate rest) {
		this.restTemplate = rest;
	}

	/**
	 * Metodo que invoca al servicio que lista desaf&iacute;os.
	 * 
	 * @param req
	 * @return Desaf&iacute;os
	 * @throws BancaEmpresasException
	 */
	public ListarDesafioResponse listarDesafio(ListarDesafioRequest req) throws BancaEmpresasException {

		ListarDesafioResponse respuesta = null;
		try {
			URI uri = URI.create(propiedadesExterna.getProperty("servicios.url.listarDesafio"));
			respuesta = this.restTemplate.postForObject(uri, req, ListarDesafioResponse.class);
			LOGGER.debug("{}", respuesta);
			if (null == respuesta || null == respuesta.getListarDesafioResp()
					|| null == respuesta.getListarDesafioResp().getFactorSeguridadDesafio())
				throw new BancaEmpresasException();
		} catch (RestClientException e) {
			LOGGER.error("Error listarDesafio: {}", e);
			throw new BancaEmpresasException();
		}
		return respuesta;
	}

	/**
	 * Metodo que invoca al servicio que crea desaf&iacute;os.
	 * 
	 * @param req
	 * @return Estado de la creaci&oacute;n del desaf&iacute;o
	 * @throws BancaEmpresasException
	 */
	public FirmarTransaccionResponse creaDesafio(FirmarTransaccionRequest req) throws BancaEmpresasException {
		FirmarTransaccionResponse respuesta = null;
		try {
			String reqJson = BancaUtil.objectToJson(req);
			LOGGER.info("crearDesafio request: {}", reqJson);

			URI uri = URI.create(propiedadesExterna.getProperty("servicios.url.crearDesafio"));
			respuesta = this.restTemplate.postForObject(uri, req, FirmarTransaccionResponse.class);
			
			if (!isRespuestaValidaCrearDesafio(respuesta)) {
				String respJson = BancaUtil.objectToJson(respuesta);
				LOGGER.info("respuesta crearDesafio respuesta: {}", respJson);
				
				if(req.getIdTransaccion() == null) {
					//REINTENTO TIPO CLIENTE PERSONAS
					req.setTipoCliente(DESAFIOS_TIPO_CLIENTE);
					LOGGER.info("reintentando crearDesafio con tipoCliente  request: {}", BancaUtil.objectToJson(req));
					respuesta = this.restTemplate.postForObject(uri, req, FirmarTransaccionResponse.class);
					if (!isRespuestaValidaCrearDesafio(respuesta)) {
						respJson = BancaUtil.objectToJson(respuesta);
						LOGGER.info("crearDesafio respuesta: {}", respJson);
					}else {
						respJson = BancaUtil.objectToJson(respuesta.getFirmarTransaccionResp());
						LOGGER.info("crearDesafio respuesta: {}", respJson);
					}
				}
				else {
					throw new BancaEmpresasException();
				}
				
			} else {
				String respJson = BancaUtil.objectToJson(respuesta.getFirmarTransaccionResp());
				LOGGER.info("crearDesafio respuesta: {}", respJson);
			}
			
			respuesta.setTipoCliente(req.getTipoCliente());
			

		} catch (RestClientException e) {
			LOGGER.error("Error consultaClienteEnrolado MobisignerService : ", e);
			throw new BancaEmpresasException();
		}
		return respuesta;
	}

	/**
	 * Verifica si la respuesta del servicio de crear desafío es válida.
	 * 
	 * @param respuesta
	 * @return
	 */
	private boolean isRespuestaValidaCrearDesafio(FirmarTransaccionResponse respuesta) {

		try {
			return ("1".equals(respuesta.getFirmarTransaccionResp().getEstado().getCodigoEstado().trim()));
		} catch (NullPointerException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			return false;
		}

	}

	/**
	 * Obtengo el estado del desaf&iacute;o.
	 * 
	 * @param req
	 * @return Estado del desaf&iacute;o.
	 * @throws BancaEmpresasException
	 */
	public String getEstadoDesafio(FirmarTransaccionRequest req) throws BancaEmpresasException {
		String respuesta = creaDesafio(req).getFirmarTransaccionResp().getDesafioRespuesta().getEstadoTransaccion();
		if (null == respuesta)
			throw new BancaEmpresasException();

		return respuesta;
	}

	/**
	 * Metodo que recibe una lista con n&uacute;meros de operaciones y obtiene datos
	 * (rut, rut empresa, cantidad de operaciones, monto total de todas las
	 * operaciones) de esas operaciones.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param listaOperacionesFiltrar
	 * @param mapOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public Map<String, String> obtenerDatosTransaccionAprobarOperacionesLBTR(String rut, String rutEmpresa,
			List<String> listaOperacionesFiltrar, String codigoServicio, MapOperaciones mapOperaciones)
			throws BancaEmpresasException {

		OperacionesAprobar operacionesAprobar = operacionesEmpresaService.generarOperacionesAprobar(mapOperaciones,
				codigoServicio);
		List<DetalleCampoValorTipoOperacion> listaOperacionesAprobar = operacionesEmpresaService
				.obtenerListaOperaciones(listaOperacionesFiltrar, operacionesAprobar);

		String montoTotal = FormateadorUtil.formatearMonto(
				operacionesEmpresaService.obtenerMontoTotalOperacionesAprobar(listaOperacionesAprobar),
				Constantes.CODIGO_MONEDA_PESOS);
		return obtenerDatosTransaccionAprobar(rut, rutEmpresa, listaOperacionesAprobar.size(), montoTotal,
				DATOS_TRANSACCION_APROBAR_VALOR_OPERACION);
	}

	/**
	 * Genera un map con los datos de las operaciones por aprobar.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param cantidadOperaciones
	 * @param montoTotal
	 * @return
	 */
	public Map<String, String> obtenerDatosTransaccionAprobar(String rut, String rutEmpresa, int cantidadOperaciones,
			String montoTotal, String titulo) {
		Map<String, String> resultado = new HashMap<>();
		resultado.put(DATOS_TRANSACCION_APROBAR_CAMPO_OPERACION, titulo);
		resultado.put(DATOS_TRANSACCION_APROBAR_CAMPO_RUTFIRMANTE,
				FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rut)));
		resultado.put(DATOS_TRANSACCION_APROBAR_CAMPO_RUTEMPRESA,
				FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutEmpresa)));
		resultado.put(DATOS_TRANSACCION_APROBAR_CAMPO_CANTIDADOPERACIONES, String.valueOf(cantidadOperaciones));
		resultado.put(DATOS_TRANSACCION_APROBAR_CAMPO_MONTOTOTAL, montoTotal);
		return resultado;
	}

	/**
	 * Obtiene los datos de las nominas por aprobar.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param listNominas
	 * @return
	 * @throws BancaEmpresasException
	 */
	public Map<String, String> obtenerDatosTransaccionAprobarNominasEnLinea(String rut, String rutEmpresa,
			List<NominaEnLinea> listNominas) throws BancaEmpresasException {
		String montoTotal = FormateadorUtil.formatearMonto(
				String.valueOf(nominasEnLineaService.obtenerMontoTotalNominas(listNominas)),
				Constantes.CODIGO_MONEDA_PESOS);
		return obtenerDatosTransaccionAprobar(rut, rutEmpresa, listNominas.size(), montoTotal,
				TRANSACTION_DATA_TITLE_NOMINAS_LIN);
	}

	/**
	 * Crea desaf&iacute;o FDA en mobisigner.
	 * 
	 * @param request
	 * @param emailCargo
	 * @param cantRegistros
	 * @param montoTotal
	 * @return Resultado de la creaci&oacute;n del desaf&iacute;o FDA.
	 * @throws BancaEmpresasException
	 */
	public TransactionResponse servCreaDesafioFDA(ListarCrearDesafiosRequest request, String emailCargo,
			String cantRegistros, String montoTotal, String docPDF, String titulo) throws BancaEmpresasException {

		TransactionResponse respInsertTransWs = null;

		TransactionRequest req = new TransactionRequest();
		req.setDocType(DESAFIO_FDA_TRANSACTION_REQUEST_TYPE_PDF);
		req.setName(titulo);
		req.setTransactionData(
				genXMLTransaccion(request.getRut(), request.getRutEmpresa(), cantRegistros, montoTotal, titulo));

		List<SignerTO> lSigners = new ArrayList<>();
		SignerTO signers = new SignerTO();
		signers.setEmail(emailCargo);
		signers.setUserId(MapperUtil.rutSinCeros(request.getRut()));
		lSigners.add(signers);
		req.setSigners(lSigners);

		Map<String, String> metadata = new HashMap<>();
		metadata.put(Constantes.SERVICIO_DETALLE_AUTORIZACION_ATRIBUTO_RESPUESTA_PDF_BASE64, docPDF);
		req.setMetadata(metadata);

		respInsertTransWs = mobisignerService.insertTransaction(req);

		return respInsertTransWs;
	}

	/**
	 * Crea desafio FDA (solo para operaciones de tipo LBTR)
	 * 
	 * @param request
	 * @param emailCargo
	 * @param cantRegistros
	 * @param montoTotal
	 * @param mapOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public TransactionResponse servCreaDesafioFDAOperacionesLBTR(ListarCrearDesafiosRequest request, String emailCargo,
			String cantRegistros, String montoTotal, MapOperaciones mapOperaciones) throws BancaEmpresasException {
		String docPDF;
		try {
			docPDF = detalleAutorizacionService.generaDocPdfOperacionesLBTR(request, request.getListaOperaciones(),
					mapOperaciones);
		} catch (DocumentPdfException e) {
			LOGGER.error("ERROR al crear el PDF con el detalle de la autorización: {}", e);
			docPDF = null;
		}

		return servCreaDesafioFDA(request, emailCargo, cantRegistros, montoTotal, docPDF, TRANSACTION_DATA_TITLE);
	}

	/**
	 * Metodo (solo para operaciones de tipo NOMINAS) que checkea que tipo de
	 * desafio (FDA o BICEPASS) tiene el cliente y crea el desafio correspondiente.
	 * 
	 * @param request
	 * @param listNominas
	 * @param emailCargo
	 * @param listarCrearDesafiosResp
	 * @throws BancaEmpresasException
	 */
	public void listarCrearDesafioFDAOpNominas(ListarCrearDesafiosRequest request, List<NominaEnLinea> listNominas,
			String emailCargo, ListarCrearDesafiosResp listarCrearDesafiosResp) throws BancaEmpresasException {

		String montoTotal = String.valueOf(nominasEnLineaService.obtenerMontoTotalNominas(listNominas));

		TransactionResponse respCrearFDA = servCreaDesafioFDANominasEnLinea(request, emailCargo, listNominas,
				montoTotal);
		LOGGER.debug("listarCrearDesafios: Desafío FDA creado");
		listarCrearDesafiosResp.setIdTransaccion(respCrearFDA.getTransactionId());
		listarCrearDesafiosResp.setTipoDesafio(Constantes.SERVICIO_LISTAR_CREAR_DESAFIOS_DESAFIO_FDA);
		listarCrearDesafiosResp.setToken(respCrearFDA.getToken());

		try {
			insertarFDASeguimiento(request, BancaUtil.listToString(request.getListaOperaciones(), ","),
					respCrearFDA.getTransactionId(), respCrearFDA.getToken(), montoTotal,
					String.valueOf(listNominas.size()));
		} catch (BancaEmpresasException e) {
			LOGGER.info("Error al invocar SP POR_PAC_FDA.OR_SP_INS_FDA_SEG_NEW: [{}]", e);
		}
	}

	/**
	 * Crea desafio FDA (solo para operaciones de tipo NOMINAS)
	 * 
	 * @param request
	 * @param emailCargo
	 * @param listNominas
	 * @param montoTotal
	 * @return
	 * @throws BancaEmpresasException
	 */
	public TransactionResponse servCreaDesafioFDANominasEnLinea(ListarCrearDesafiosRequest request, String emailCargo,
			List<NominaEnLinea> listNominas, String montoTotal) throws BancaEmpresasException {

		String docPDF;
		try {
			docPDF = detalleAutorizacionService.generaDocPdfOperacionesNominasEnLinea(request, listNominas);
		} catch (DocumentPdfException e) {
			LOGGER.error("ERROR al crear el PDF con el detalle de la autorización: {}", e);
			docPDF = null;
		}

		return servCreaDesafioFDA(request, emailCargo, String.valueOf(listNominas.size()), montoTotal, docPDF,
				TRANSACTION_DATA_TITLE_NOMINAS_LIN);
	}

	/**
	 * Genera un XML (transaction data) con los datos de la transacci&oacute;n
	 * requerido por el metodo insertTransaction del servicio mobisigner.
	 * 
	 * @param rutFirmadorParam
	 * @param rutEmpresaParam
	 * @param cantRegistrosParam
	 * @param montoTotalParam
	 * @return XML con datos de la transacci&oacute;n
	 */
	private String genXMLTransaccion(String rutFirmadorParam, String rutEmpresaParam, String cantRegistrosParam,
			String montoTotalParam, String titulo) {
		String resultado = "";

		String rutFirmador = null;
		String rutEmpresaStr = null;
		String numeroRegistros = null;
		String montoStr = null;

		if (null != rutFirmadorParam) {
			StringBuilder rutFirma = new StringBuilder();
			rutFirma.append(XML_ETIQUETA_APERTURA_DATA).append(XML_ETIQUETA_APERTURA_ELEMENT)
					.append(XML_ETIQUETA_APERTURA_KEY + "RUT FIRMADOR" + XML_ETIQUETA_CIERRE_KEY)
					.append(XML_ETIQUETA_APERTURA_VALUE).append(FormateadorUtil.formatoRut(rutFirmadorParam))
					.append(XML_ETIQUETA_CIERRE_VALUE).append(XML_ETIQUETA_CIERRE_ELEMENT);
			rutFirmador = rutFirma.toString();
		}

		if (null != rutEmpresaParam) {
			StringBuilder rutEmpresa = new StringBuilder();
			rutEmpresa.append(XML_ETIQUETA_APERTURA_ELEMENT).append(XML_ETIQUETA_APERTURA_KEY).append("RUT EMPRESA")
					.append(XML_ETIQUETA_CIERRE_KEY).append(XML_ETIQUETA_APERTURA_VALUE)
					.append(FormateadorUtil.formatoRut(rutEmpresaParam)).append(XML_ETIQUETA_CIERRE_VALUE)
					.append(XML_ETIQUETA_CIERRE_ELEMENT);
			rutEmpresaStr = rutEmpresa.toString();
		}

		if (null != cantRegistrosParam) {
			StringBuilder cantidadRegistros = new StringBuilder();
			cantidadRegistros.append(XML_ETIQUETA_APERTURA_ELEMENT).append(XML_ETIQUETA_APERTURA_KEY)
					.append("Cantidad Registros").append(XML_ETIQUETA_CIERRE_KEY).append(XML_ETIQUETA_APERTURA_VALUE)
					.append(cantRegistrosParam).append(XML_ETIQUETA_CIERRE_VALUE).append(XML_ETIQUETA_CIERRE_ELEMENT);
			numeroRegistros = cantidadRegistros.toString();
		}
		if (null != montoTotalParam) {
			StringBuilder monto = new StringBuilder();
			montoTotalParam = FormateadorUtil.formatearMonto(montoTotalParam,
					propiedadesExterna.getProperty("servicios.lbtr.codigo.moneda"));
			monto.append(XML_ETIQUETA_APERTURA_ELEMENT).append(XML_ETIQUETA_APERTURA_KEY).append("MONTO")
					.append(XML_ETIQUETA_CIERRE_KEY).append(XML_ETIQUETA_APERTURA_VALUE).append(montoTotalParam)
					.append(XML_ETIQUETA_CIERRE_VALUE).append(XML_ETIQUETA_CIERRE_ELEMENT)
					.append(XML_ETIQUETA_CIERRE_DATA);
			montoStr = monto.toString();
		}

		resultado = XML_ETIQUETA_APERTURA_TRANSACTION_DATA + XML_ETIQUETA_APERTURA_TITLE + titulo
				+ XML_ETIQUETA_CIERRE_TITLE + XML_ETIQUETA_APERTURA_SUB_TITLE + "" + XML_ETIQUETA_CIERRE_SUB_TITLE
				+ rutFirmador + rutEmpresaStr + numeroRegistros + montoStr + XML_ETIQUETA_CIERRE_TRANSACTION_DATA;

		return resultado;

	}

	/**
	 * Checkea si el cliente está enrolado en mobisigner y si lo está crea desafío
	 * FDA y de esta manera se valida que mobilesigner está operativo.
	 * 
	 * @param request
	 * @param listarCrearDesafiosResp
	 * @throws BancaEmpresasException
	 */
	public void validarMobileSigner(ValidarMobileSignerRequest request, ValidarMobileSignerResp validarMobileSignerResp)
			throws BancaEmpresasException {
		EnrolamientoResponse respEnroladoFDA = mobisignerService.consultaClienteEnrolado(request.getRut());
		if (0 == respEnroladoFDA.getStatusCode()) {
			LOGGER.debug("validarMobileSigner: Tiene FDA, crear desafío FDA");
			String emailCargo = EmailUtil.isValidEmailAddress(request.getEmailUsuario()) ? request.getEmailUsuario()
					: "";
			TransactionResponse respCrearFDA = servCreaDesafioValidarMobileSigner(request.getRut(), emailCargo);
			LOGGER.debug("validarMobileSigner: Desafío FDA creado");
			validarMobileSignerResp.setIdTransaccion(respCrearFDA.getTransactionId());
			validarMobileSignerResp.setTipoDesafio(Constantes.SERVICIO_LISTAR_CREAR_DESAFIOS_DESAFIO_FDA);
			validarMobileSignerResp.setToken(respCrearFDA.getToken());
		}
	}

	/**
	 * Crea desaf&iacute;o FDA para validar que que mobilesigner esté operativo.
	 * 
	 * @param rutFirmador
	 * @return Resultado de la creaci&oacute;n del desaf&iacute;o FDA.
	 * @throws BancaEmpresasException
	 */
	public TransactionResponse servCreaDesafioValidarMobileSigner(String rutFirmador, String emailCargo)
			throws BancaEmpresasException {

		String titulo = propiedadesExterna.getProperty("texto.servicioValidarMobileSigner.fda.xml.title");
		TransactionResponse respInsertTransWs = null;

		TransactionRequest trxReq = new TransactionRequest();
		trxReq.setDocType(DESAFIO_FDA_VALIDAR_MOBILE_SIGNER_REQUEST_DOC_TYPE_PDF);
		trxReq.setName(titulo);
		trxReq.setTransactionData(genXMLValidarMobileSigner(rutFirmador, titulo));

		List<SignerTO> lSigners = new ArrayList<>();
		SignerTO signers = new SignerTO();
		signers.setEmail(emailCargo);
		signers.setUserId(MapperUtil.rutSinCeros(rutFirmador));
		lSigners.add(signers);
		trxReq.setSigners(lSigners);
		trxReq.setMetadata(new HashMap<>());

		respInsertTransWs = mobisignerService.insertTransaction(trxReq);

		return respInsertTransWs;
	}

	/**
	 * Genera un XML requerido por el metodo insertTransaction del servicio
	 * mobisigner para validar que que mobilesigner esté operativo.
	 * 
	 * @param rutFirmadorParam
	 * @return XML
	 */
	private String genXMLValidarMobileSigner(String rutFirmadorParam, String titulo) {
		StringBuilder resultado = new StringBuilder();
		resultado.append(XML_ETIQUETA_APERTURA_TRANSACTION_DATA);
		resultado.append(XML_ETIQUETA_APERTURA_TITLE);
		resultado.append(titulo);
		resultado.append(XML_ETIQUETA_CIERRE_TITLE);
		resultado.append(XML_ETIQUETA_APERTURA_SUB_TITLE);
		resultado.append("").append(XML_ETIQUETA_CIERRE_SUB_TITLE);
		resultado.append(XML_ETIQUETA_APERTURA_DATA);
		resultado.append(XML_ETIQUETA_APERTURA_ELEMENT);
		resultado.append(XML_ETIQUETA_APERTURA_KEY + "RUT FIRMADOR" + XML_ETIQUETA_CIERRE_KEY);
		resultado.append(XML_ETIQUETA_APERTURA_VALUE);
		resultado.append(FormateadorUtil.formatoRut(rutFirmadorParam));
		resultado.append(XML_ETIQUETA_CIERRE_VALUE);
		resultado.append(XML_ETIQUETA_CIERRE_ELEMENT);
		resultado.append(XML_ETIQUETA_APERTURA_ELEMENT);
		resultado.append(XML_ETIQUETA_APERTURA_KEY);
		resultado.append("OPERACION");
		resultado.append(XML_ETIQUETA_CIERRE_KEY);
		resultado.append(XML_ETIQUETA_APERTURA_VALUE);
		resultado.append("Validar");
		resultado.append(XML_ETIQUETA_CIERRE_VALUE);
		resultado.append(XML_ETIQUETA_CIERRE_ELEMENT);
		resultado.append(XML_ETIQUETA_APERTURA_ELEMENT);
		resultado.append(XML_ETIQUETA_APERTURA_KEY);
		resultado.append("Fecha");
		resultado.append(XML_ETIQUETA_CIERRE_KEY);
		resultado.append(XML_ETIQUETA_APERTURA_VALUE);
		resultado.append(MapperUtil.formatearFecha(new Date(), "dd-MM-yyyy"));
		resultado.append(XML_ETIQUETA_CIERRE_VALUE);
		resultado.append(XML_ETIQUETA_CIERRE_ELEMENT);
		resultado.append(XML_ETIQUETA_CIERRE_DATA);
		resultado.append(XML_ETIQUETA_CIERRE_TRANSACTION_DATA);
		return resultado.toString();
	}

	/**
	 * Consulta el estado del desaf&iacute;o para cliente con FDA.
	 * 
	 * @param request
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String obtenerEstadoTransaccionFDA(ValidarEstadoConfirmacionRequest request) throws BancaEmpresasException {
		return obtenerEstadoTransaccionFDA(request.getIdTransaccion());
	}

	/**
	 * Consulta el estado del desaf&iacute;o para cliente con FDA.
	 * 
	 * @param idTransaccion
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String obtenerEstadoTransaccionFDA(String idTransaccion) throws BancaEmpresasException {
		ValidarStatusOperacionRequest validarStatusOperacionRequest = new ValidarStatusOperacionRequest();
		validarStatusOperacionRequest.setTransactionId(idTransaccion);
		ValidarEstadoOperacionResponse respSignerValidaEstado = mobisignerService
				.validarStatusOperacion(validarStatusOperacionRequest);

		if (MobiSignerStatus.FIRMA_COMPLETADA.id() == respSignerValidaEstado.getTransactionStatusCode()) {
			return Constantes.VALIDAR_DESAFIO_ESTADO_EXITO;
		} else if (MobiSignerStatus.FIRMA_PENDIENTE.id() == respSignerValidaEstado.getTransactionStatusCode()) {
			return Constantes.VALIDAR_DESAFIO_ESTADO_PENDIENTE;
		} else { // MobiSignerStatus.FIRMA_RECHAZADA o MobiSignerStatus.FIRMA_CADUCADA
			return Constantes.VALIDAR_DESAFIO_ESTADO_ERROR;
		}
	}

	/**
	 * Consulta el estado del desaf&iacute;o para el cliente con BICEPASS.
	 * 
	 * @param request
	 * @return Estado del desaf&iacute;o BICEPASS.
	 * @throws BancaEmpresasException
	 */
	public String obtenerEstadoTransaccionBICEPASS(ValidarEstadoConfirmacionRequest request)
			throws BancaEmpresasException {
		String resultado = null;
		FirmarTransaccionRequest req = new FirmarTransaccionRequest();
		req.setDatosTransaccion(null);
		req.setCanal(request.getCanal());
		req.setDispositivo(request.getDispositivo());
		req.setIdTransaccion(request.getIdTransaccion());
		req.setiPCliente(request.getIp());
		req.setToken(request.getToken());
		req.setRut(request.getRut());
		
		if(request.getTipoCliente() == null) 
			req.setTipoCliente(DESAFIOS_TIPO_CLIENTE_EMPRESA);
		else
			req.setTipoCliente(request.getTipoCliente());
		
		String estadoDesafio = getEstadoDesafio(req).trim();

		if (BICEPASSStatus.CONFIRM.id().equals(estadoDesafio)) {
			resultado = Constantes.VALIDAR_DESAFIO_ESTADO_EXITO;
		} else if (BICEPASSStatus.NOT_FOUND.id().equals(estadoDesafio)) {
			resultado = Constantes.VALIDAR_DESAFIO_ESTADO_PENDIENTE;
		} else {// BICEPASSStatus.CANCEL o BICEPASSStatus.CONCERN
			resultado = Constantes.VALIDAR_DESAFIO_ESTADO_ERROR;
		}

		return resultado;
	}

	/**
	 * Crea desaf&iacute;o BICEPASS
	 * 
	 * @param request
	 * @return Datos de la respuesta de la creaci&oacute;n del desaf&iacute;o
	 *         BICEPASS
	 * @throws BancaEmpresasException
	 * @throws SQLException
	 */
	public FirmarTransaccionResponse crearDesafioBICEPASSOperacionesLBTR(ListarCrearDesafiosRequest request,
			MapOperaciones mapOperaciones) throws BancaEmpresasException {
		return crearDesafioBICEPASS(request, obtenerDatosTransaccionAprobarOperacionesLBTR(request.getRut(),
				request.getRutEmpresa(), request.getListaOperaciones(), request.getCodigoServicio(), mapOperaciones));
	}

	/**
	 * Crea desafio BICEPASS (solo para operaciones de tipo NOMINAS).
	 * 
	 * @param request
	 * @param listNominas
	 * @return
	 * @throws BancaEmpresasException
	 */
	public FirmarTransaccionResponse crearDesafioBICEPASSNominasEnLinea(ListarCrearDesafiosRequest request,
			List<NominaEnLinea> listNominas) throws BancaEmpresasException {
		return crearDesafioBICEPASS(request,
				obtenerDatosTransaccionAprobarNominasEnLinea(request.getRut(), request.getRutEmpresa(), listNominas));
	}

	/**
	 * Crea desafio BICEPASS
	 * 
	 * @param request
	 * @param datosTransaccion
	 * @return
	 * @throws BancaEmpresasException
	 */
	public FirmarTransaccionResponse crearDesafioBICEPASS(ListarCrearDesafiosRequest request,
			Map<String, String> datosTransaccion) throws BancaEmpresasException {
		FirmarTransaccionRequest req = new FirmarTransaccionRequest();
		req.setDatosTransaccion(datosTransaccion);

		req.setCanal(CANAL_CREAR_DESAFIO_BICEPASS);
		req.setDispositivo(request.getDispositivo());
		req.setiPCliente(request.getIp());
		req.setToken(request.getToken());
		req.setRut(request.getRut());
		req.setTipoCliente(DESAFIOS_TIPO_CLIENTE_EMPRESA);
		
		return creaDesafio(req);
	}

	/**
	 * Lista desaf&iacute;os
	 * 
	 * @param request
	 * @return Lista de desaf&iacute;os
	 * @throws BancaEmpresasException
	 */
	public ListarDesafioResponse listarDesafios(ListarCrearDesafiosRequest request) throws BancaEmpresasException {
		ListarDesafioRequest listaDesafioReq = new ListarDesafioRequest();
		listaDesafioReq.setDispositivo(request.getDispositivo());
		listaDesafioReq.setToken(request.getToken());
		listaDesafioReq.setRut(request.getRut());
		listaDesafioReq.setTipoCliente(DESAFIOS_TIPO_CLIENTE);
		listaDesafioReq.setCodigoServicio(LISTAR_DESAFIOS_REQUEST_CODIGO_SERVICIO);
		listaDesafioReq.setCampo1(LISTAR_DESAFIOS_REQUEST_CAMPO1);
		listaDesafioReq.setCampo2(LISTAR_DESAFIOS_REQUEST_CAMPO2);
		listaDesafioReq.setCampo3(LISTAR_DESAFIOS_REQUEST_CAMPO3);
		listaDesafioReq.setCampo4(LISTAR_DESAFIOS_REQUEST_CAMPO4);
		listaDesafioReq.setCampo5(LISTAR_DESAFIOS_REQUEST_CAMPO5);
		listaDesafioReq.setCampo6(empresasService.fechaHoy("dd-MM-yyyy"));
		listaDesafioReq.setCampo7(empresasService.fechaHoy("HH:mm:ss"));
		listaDesafioReq.setDatosTransaccion(null);

		return listarDesafio(listaDesafioReq);
	}

	/**
	 * Metodo (solo para operaciones de tipo LBTR) que checkea que tipo de desafio
	 * (FDA o BICEPASS) tiene el cliente y crea el desafio correspondiente.
	 * 
	 * @param request
	 * @param mapOperaciones
	 * @param emailCargo
	 * @param listarCrearDesafiosResp
	 * @throws BancaEmpresasException
	 */
	public void listarCrearDesafioFDAOpLBTR(ListarCrearDesafiosRequest request, MapOperaciones mapOperaciones,
			String emailCargo, ListarCrearDesafiosResp listarCrearDesafiosResp) throws BancaEmpresasException {
		OperacionesAprobar operacionesAprobar = operacionesEmpresaService.generarOperacionesAprobar(mapOperaciones,
				request.getCodigoServicio());
		List<DetalleCampoValorTipoOperacion> listaOperacionesAprobar = operacionesEmpresaService
				.obtenerListaOperaciones(request.getListaOperaciones(), operacionesAprobar);
		String montoTotal = operacionesEmpresaService.obtenerMontoTotalOperacionesAprobar(listaOperacionesAprobar);

		String cantRegistros = String.valueOf(listaOperacionesAprobar.size());

		TransactionResponse respCrearFDA = servCreaDesafioFDAOperacionesLBTR(request, emailCargo, cantRegistros,
				montoTotal, mapOperaciones);
		LOGGER.debug("listarCrearDesafios: Desafío FDA creado");
		listarCrearDesafiosResp.setIdTransaccion(respCrearFDA.getTransactionId());
		listarCrearDesafiosResp.setTipoDesafio(Constantes.SERVICIO_LISTAR_CREAR_DESAFIOS_DESAFIO_FDA);
		listarCrearDesafiosResp.setToken(respCrearFDA.getToken());

		try {
			insertarFDASeguimiento(request, BancaUtil.listToString(request.getListaOperaciones(), ","),
					respCrearFDA.getTransactionId(), respCrearFDA.getToken(), montoTotal, cantRegistros);
		} catch (BancaEmpresasException e) {
			LOGGER.info("Error al invocar SP POR_PAC_FDA.OR_SP_INS_FDA_SEG_NEW: [{}]", e);
		}
	}

	/**
	 * Checkea si el cliente está enrolado en mobisigner y si lo está crea desafío
	 * FDA sinó checkeo si el cliente tiene BICEPASS si tiene BICEPASS crea desafío
	 * tipo BICEPASS. Si no tiene ningún desafío retornará una excepción.
	 * 
	 * @param request
	 * @param listarCrearDesafiosResp
	 * @throws BancaEmpresasException
	 */
	public void listarCrearDesafios(ListarCrearDesafiosRequest request, ListarCrearDesafiosResp listarCrearDesafiosResp,
			Object operaciones) throws BancaEmpresasException {

		boolean isFDA = false;
		String fdaActivo= parametrosValidacionService.getParamValidacion("FDA", "MOBISIGNER_PORTAL", Constantes.VAL_PARAMETRO).trim();
		LOGGER.info("DesafiosServices listarCrearDesafios consultar FDA: {}", (fdaActivo.equals("1")?"SI":"NO"));
		if("1".equals(fdaActivo)) {
			EnrolamientoResponse respEnroladoFDA = mobisignerService.consultaClienteEnrolado(request.getRut());
			if (0 == respEnroladoFDA.getStatusCode()) {
				isFDA = true;
			}
		}

		boolean validarContratosFda = validarContratosFda();
		boolean tieneContratosFDA = tieneContratosFDA(request, validarContratosFda);
		
		if (isFDA) {
			if (validarContratosFda) {
				try {
					registraContratoFDA(request.getRut(), request.getRutEmpresa(), request.getCodigoServicio());
				} catch (BancaEmpresasException e) {
					LOGGER.info("Error al registrar el contrato FDA: [{}]", e);
				}
			} else {
				LOGGER.info("validarContratosFda apagado, no se registra contrato.");
				//try {
					/* Acorde a lo revisado el dia 21-04-2020 no se requiere ingresar contrato desactivado*/
					//registraContratoFDADesactivado(request.getRut(), request.getRutEmpresa(),
							//request.getCodigoServicio());
				//} catch (BancaEmpresasException e) {
				//	LOGGER.info("Error al registrar el contrato FDA Desactivado: [{}]", e);
				//}
			}

			LOGGER.debug("listarCrearDesafios: Tiene FDA, crear desafío FDA");
			crearDesafioFDAPorTipoOperacion(request, listarCrearDesafiosResp, operaciones);

		} else if ((validarContratosFda && !tieneContratosFDA) || !validarContratosFda) {

			ListarDesafioResponse desafioListResponse = listarDesafios(request);

			if (!desafioListResponse.getListarDesafioResp().getFactorSeguridadDesafio().isEmpty()
					&& Constantes.CLIENTE_SERVICIO_DESAFIO_BICEPASS.equalsIgnoreCase(desafioListResponse
							.getListarDesafioResp().getFactorSeguridadDesafio().get(0).getTipoDesafio().trim())) {
				LOGGER.debug("listarCrearDesafios: Tiene BICEPASS, crear desafio BICEPASS");
				
				validarMontoMaximoSinFda(request.getListaOperaciones(), request.getCodigoServicio(), operaciones);

				crearDesafioBICEPASSPorTipoOperacion(request, listarCrearDesafiosResp, operaciones);

			} else {
				throw new NoEncontradoException();
			}
		} else {
			throw new NoEncontradoException();
		}
	}

	/**
	 * Crea desafío BICEPASS según el tipo de operación (que se determina por código
	 * de servicio).
	 * 
	 * @param request
	 * @param listarCrearDesafiosResp
	 * @param operaciones
	 * @throws BancaEmpresasException
	 */
	public void crearDesafioBICEPASSPorTipoOperacion(ListarCrearDesafiosRequest request,
			ListarCrearDesafiosResp listarCrearDesafiosResp, Object operaciones) throws BancaEmpresasException {
		FirmarTransaccionResponse firmarTransaccionResponse = null;
		if (Constantes.CODIGO_SERVICIO_NOMINAS_EN_LINEA.equals(request.getCodigoServicio())) {
			firmarTransaccionResponse = crearDesafioBICEPASSNominasEnLinea(request, (List<NominaEnLinea>) operaciones);
		} else if (Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_DIVIDENDO_ACCIONES
				.equals(request.getCodigoServicio())
				|| Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_PROVEEDORES.equals(request.getCodigoServicio())
				|| Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES
						.equals(request.getCodigoServicio())) {
			firmarTransaccionResponse = crearDesafioBICEPASSOperacionesNominasDiferidas(request,
					(MapOperaciones) operaciones);
		} else if (Constantes.CODIGO_SERVICIO_LBTR.equals(request.getCodigoServicio())) {
			firmarTransaccionResponse = crearDesafioBICEPASSOperacionesLBTR(request, (MapOperaciones) operaciones);
		} else if (Constantes.CODIGO_SERVICIO_SPOTWEB.equals(request.getCodigoServicio())
				|| Constantes.CODIGO_SERVICIO_TEF_MX.equals(request.getCodigoServicio())) {
			firmarTransaccionResponse = crearDesafioBICEPASSOperacionesSpotWeb(request, (MapOperaciones) operaciones);
		} else if (Constantes.CODIGO_SERVICIO_BICECOMEX.equals(request.getCodigoServicio())) {
			firmarTransaccionResponse = crearDesafioBICEPASSOperacionesBiceComex(request, (MapOperaciones) operaciones);
		}

		LOGGER.debug("listarCrearDesafios: Desafío BICEPASS creado");
		
		listarCrearDesafiosResp.setTipoCliente(firmarTransaccionResponse.getTipoCliente());
		listarCrearDesafiosResp.setIdTransaccion(
				firmarTransaccionResponse.getFirmarTransaccionResp().getDesafioRespuesta().getTransaccionId());
		listarCrearDesafiosResp.setTipoDesafio(Constantes.SERVICIO_LISTAR_CREAR_DESAFIOS_DESAFIO_BICEPASS);
	}

	/**
	 * Crea desafío FDA según el tipo de operación (que se determina por código de
	 * servicio).
	 * 
	 * @param request
	 * @param listarCrearDesafiosResp
	 * @param operaciones
	 * @throws BancaEmpresasException
	 */
	public void crearDesafioFDAPorTipoOperacion(ListarCrearDesafiosRequest request,
			ListarCrearDesafiosResp listarCrearDesafiosResp, Object operaciones) throws BancaEmpresasException {
		String emailCargo = EmailUtil.isValidEmailAddress(request.getEmailUsuario()) ? request.getEmailUsuario() : "";

		if (Constantes.CODIGO_SERVICIO_NOMINAS_EN_LINEA.equals(request.getCodigoServicio())) {
			listarCrearDesafioFDAOpNominas(request, (List<NominaEnLinea>) operaciones, emailCargo,
					listarCrearDesafiosResp);
		} else if (Constantes.CODIGO_SERVICIO_LBTR.equals(request.getCodigoServicio())) {
			listarCrearDesafioFDAOpLBTR(request, (MapOperaciones) operaciones, emailCargo, listarCrearDesafiosResp);
		} else if (Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_DIVIDENDO_ACCIONES
				.equals(request.getCodigoServicio())
				|| Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_PROVEEDORES.equals(request.getCodigoServicio())
				|| Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES
						.equals(request.getCodigoServicio())) {
			listarCrearDesafioFDAOpNominasDiferidas(request, (MapOperaciones) operaciones, emailCargo,
					listarCrearDesafiosResp);

		} else if (Constantes.CODIGO_SERVICIO_SPOTWEB.equals(request.getCodigoServicio())
				|| Constantes.CODIGO_SERVICIO_TEF_MX.equals(request.getCodigoServicio())) {
			listarCrearDesafioFDAOpMultiMoneda(request, (MapOperaciones) operaciones, emailCargo,
					listarCrearDesafiosResp);

		} else if (Constantes.CODIGO_SERVICIO_BICECOMEX.equals(request.getCodigoServicio())) {
			listarCrearDesafioFDAOpBiceComex(request, (MapOperaciones) operaciones, emailCargo,
					listarCrearDesafiosResp);
		}
	}

	/**
	 * Consulta si un rut usuario se encuentra enrolado en mobisigner
	 * 
	 * @param rut usuario
	 * @return true si rut usuario se encuentra enrolado en mobisigner, false en
	 *         caso contrario.
	 * @throws BancaEmpresasException
	 */
	public boolean isClienteEnrolado(String rut) throws BancaEmpresasException {
		boolean clienteEnrolado = false;
		EnrolamientoResponse respEnroladoFDA = mobisignerService.consultaClienteEnrolado(rut);
		if (0 == respEnroladoFDA.getStatusCode()) {
			clienteEnrolado = true;
		}
		return clienteEnrolado;
	}

	/**
	 * Registra contrato FDA
	 * 
	 * @param rut
	 * @param rutCliente
	 * @param codigoServicio
	 * @throws BancaEmpresasException
	 */
	public void registraContratoFDA(String rut, String rutCliente, String codigoServicio)
			throws BancaEmpresasException {
		LOGGER.info("DesafiosServices registraContratoFDA: rut[{}] rutCliente[{}] codigoServicio[{}]", rut, rutCliente,
				codigoServicio);

		Map<String, Object> params = new HashMap<>();
		params.put("P_RUT_CLIENTE", rutCliente);
		params.put("P_RUT_MODIFICA", rut);
		params.put("P_COD_SERVICIOS", codigoServicio);
		params.put("P_PROVEEDOR", PROVEEDOR_ESIGN_COD);

		try {
			portalOrawRepository.registraContratoFDA(params);

			LOGGER.info("DesafiosServices registraContratoFDA: P_STATUS[{}]", params.get("P_STATUS"));
			LOGGER.info("DesafiosServices registraContratoFDA: P_MSG_STATUS[{}]", params.get("P_MSG_STATUS"));

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}
	}

	/**
	 * Inserta datos FDA en una tabla de seguimiento
	 * 
	 * @param request
	 * @param listaNumOperaciones
	 * @param idTrxMSigner
	 * @param tokenRespMSigner
	 * @param montoTotal
	 * @param cantOperaciones
	 * @throws BancaEmpresasException
	 */
	public void insertarFDASeguimiento(ListarCrearDesafiosRequest request, String listaNumOperaciones,
			String idTrxMSigner, String tokenRespMSigner, String montoTotal, String cantOperaciones)
			throws BancaEmpresasException {

		insertarFDASeguimiento(request, listaNumOperaciones, idTrxMSigner, tokenRespMSigner, montoTotal,
				Constantes.CODIGO_MONEDA_PESOS, cantOperaciones);
	}

	/**
	 * Inserta datos FDA en una tabla de seguimiento
	 * 
	 * @param request
	 * @param listaNumOperaciones
	 * @param idTrxMSigner
	 * @param tokenRespMSigner
	 * @param montoTotal
	 * @param cantOperaciones
	 * @throws BancaEmpresasException
	 */
	public void insertarFDASeguimiento(ListarCrearDesafiosRequest request, String listaNumOperaciones,
			String idTrxMSigner, String tokenRespMSigner, String montoTotal, String moneda, String cantOperaciones)
			throws BancaEmpresasException {
		LOGGER.info("DesafiosServices registraContratoFDA: rut[{}] rutCliente[{}] codigoServicio[{}]", request.getRut(),
				request.getRutEmpresa(), request.getCodigoServicio());

		Map<String, Object> params = new HashMap<>();
		params.put("PI_COD_OPER", null);
		params.put("PI_RUT_CLIENTE", request.getRutEmpresa());
		params.put("PI_RUT_APODERADO", request.getRut());
		params.put("PI_RUT_FIRMA", request.getRut());
		params.put("PI_FLG_PAG1", FDA_SEGUIMIENTO_PI_FLG_PAG1);
		params.put("PI_FLG_PAG2", FDA_SEGUIMIENTO_PI_FLG_PAG2);
		params.put("PI_ORIGEN", request.getCodigoServicio());
		params.put("PI_NUM_OPER", listaNumOperaciones);
		params.put("PI_EMPRESA", PROVEEDOR_ESIGN);
		params.put("PI_ID_TRX", idTrxMSigner);
		params.put("PI_TOKEN", tokenRespMSigner);
		params.put("PI_CANAL", request.getCanal());
		params.put("PI_MONEDA", moneda);
		params.put("PI_MONTO", montoTotal);
		params.put("PI_CANT_OP", cantOperaciones);

		try {
			portalOrawRepository.insertarFDASeguimiento(params);

			LOGGER.info("DesafiosServices insertarFDASeguimiento: PO_STS[{}]", params.get("PO_STS"));
			LOGGER.info("DesafiosServices insertarFDASeguimiento: PO_MSG[{}]", params.get("PO_MSG"));

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}
	}

	/**
	 * Lista y crea desafíos según el tipo de operación. El tipo de operación se
	 * determina por código de servicio.
	 * 
	 * @param request
	 * @param listarCrearDesafiosResp
	 * @throws BancaEmpresasException
	 */
	public ListarCrearDesafiosResp listarCrearDesafiosPorTipoOperacion(ListarCrearDesafiosRequest request)
			throws BancaEmpresasException {

		ListarCrearDesafiosResp listarCrearDesafiosResp = new ListarCrearDesafiosResp();

		if (Constantes.CODIGO_SERVICIO_NOMINAS_EN_LINEA.equals(request.getCodigoServicio())) {
			List<NominaEnLinea> listNominas = nominasEnLineaService
					.obtenerListaNominasEnLinea(request.getListaOperaciones());
			if (!validacionService.isPertenenciaNominaValida(request, request.getCodigoServicio(), listNominas)) {
				throw new RequestInvalidoException();
			}
			listarCrearDesafios(request, listarCrearDesafiosResp, listNominas);
		} else {
			MapOperaciones mapOperaciones = valoresCampoOperacionesService.obtenerMapOperaciones(request.getRut(),
					request.getRutEmpresa(), request.getCodigoServicio(), request.getCanal(), true,
					request.getFechaDesde(), request.getFechaHasta());

			if (Constantes.CODIGO_SERVICIO_BICECOMEX.equals(request.getCodigoServicio())) {
				if (!validacionService.isPertenenciaValidaBiceComex(request, request.getCodigoServicio(),
						request.getListaOperaciones(), true)) {
					mapOperaciones.clearMap();
					throw new RequestInvalidoException();
				}
			} else {
				if (!validacionService.isPertenenciaValida(request, request.getCodigoServicio(),
						request.getListaOperaciones(), mapOperaciones, true)) {
					mapOperaciones.clearMap();
					throw new RequestInvalidoException();
				}
			}

			listarCrearDesafios(request, listarCrearDesafiosResp, mapOperaciones);
			mapOperaciones.clearMap();
		}

		return listarCrearDesafiosResp;
	}

	/**
	 * Metodo (solo para operaciones de tipo Nóminas Diferidas) que checkea que tipo
	 * de desafio (FDA o BICEPASS) tiene el cliente y crea el desafio
	 * correspondiente.
	 * 
	 * @param request
	 * @param mapOperaciones
	 * @param emailCargo
	 * @param listarCrearDesafiosResp
	 * @throws BancaEmpresasException
	 */
	public void listarCrearDesafioFDAOpNominasDiferidas(ListarCrearDesafiosRequest request,
			MapOperaciones mapOperaciones, String emailCargo, ListarCrearDesafiosResp listarCrearDesafiosResp)
			throws BancaEmpresasException {
		OperacionesAprobar operacionesAprobar = operacionesEmpresaService.generarOperacionesAprobar(mapOperaciones,
				request.getCodigoServicio());
		List<DetalleCampoValorTipoOperacion> listaOperacionesAprobar = operacionesEmpresaService
				.obtenerListaOperaciones(request.getListaOperaciones(), operacionesAprobar);
		String montoTotal = operacionesEmpresaService.obtenerMontoTotalOperacionesAprobar(listaOperacionesAprobar);

		String cantRegistros = String.valueOf(listaOperacionesAprobar.size());

		TransactionResponse respCrearFDA = servCreaDesafioFDAOperacionesNominasDiferidas(request, emailCargo,
				cantRegistros, montoTotal, mapOperaciones);
		LOGGER.debug("listarCrearDesafios: Desafío FDA creado");
		listarCrearDesafiosResp.setIdTransaccion(respCrearFDA.getTransactionId());
		listarCrearDesafiosResp.setTipoDesafio(Constantes.SERVICIO_LISTAR_CREAR_DESAFIOS_DESAFIO_FDA);
		listarCrearDesafiosResp.setToken(respCrearFDA.getToken());

		try {
			insertarFDASeguimiento(request, BancaUtil.listToString(request.getListaOperaciones(), ","),
					respCrearFDA.getTransactionId(), respCrearFDA.getToken(), montoTotal, cantRegistros);
		} catch (BancaEmpresasException e) {
			LOGGER.info("Error al invocar SP POR_PAC_FDA.OR_SP_INS_FDA_SEG_NEW: [{}]", e);
		}
	}

	/**
	 * Crea desafio FDA (solo para operaciones de tipo Nóminas diferidas)
	 * 
	 * @param request
	 * @param emailCargo
	 * @param cantRegistros
	 * @param montoTotal
	 * @param mapOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public TransactionResponse servCreaDesafioFDAOperacionesNominasDiferidas(ListarCrearDesafiosRequest request,
			String emailCargo, String cantRegistros, String montoTotal, MapOperaciones mapOperaciones)
			throws BancaEmpresasException {
		String docPDF;
		try {
			docPDF = detalleAutorizacionService.generaDocPdfOperacionesNominasDiferidas(request,
					request.getListaOperaciones(), mapOperaciones, request.getCodigoServicio());
		} catch (DocumentPdfException e) {
			LOGGER.error("ERROR al crear el PDF con el detalle de la autorización: {}", e);
			docPDF = null;
		}

		return servCreaDesafioFDA(request, emailCargo, cantRegistros, montoTotal, docPDF,
				TRANSACTION_DATA_TITLE_NOMINAS_DIF);
	}

	/**
	 * Crea desaf&iacute;o BICEPASS
	 * 
	 * @param request
	 * @return Datos de la respuesta de la creaci&oacute;n del desaf&iacute;o
	 *         BICEPASS
	 * @throws BancaEmpresasException
	 * @throws SQLException
	 */
	public FirmarTransaccionResponse crearDesafioBICEPASSOperacionesNominasDiferidas(ListarCrearDesafiosRequest request,
			MapOperaciones mapOperaciones) throws BancaEmpresasException {
		return crearDesafioBICEPASS(request, obtenerDatosTransaccionAprobarOperacionesNominasDiferidas(request.getRut(),
				request.getRutEmpresa(), request.getListaOperaciones(), request.getCodigoServicio(), mapOperaciones));
	}

	/**
	 * Metodo que recibe una lista con n&uacute;meros de operaciones y obtiene datos
	 * (rut, rut empresa, cantidad de operaciones, monto total de todas las
	 * operaciones) de esas operaciones.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param listaOperacionesFiltrar
	 * @param mapOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public Map<String, String> obtenerDatosTransaccionAprobarOperacionesNominasDiferidas(String rut, String rutEmpresa,
			List<String> listaOperacionesFiltrar, String codigoServicio, MapOperaciones mapOperaciones)
			throws BancaEmpresasException {

		OperacionesAprobar operacionesAprobar = operacionesEmpresaService.generarOperacionesAprobar(mapOperaciones,
				codigoServicio);
		List<DetalleCampoValorTipoOperacion> listaOperacionesAprobar = operacionesEmpresaService
				.obtenerListaOperaciones(listaOperacionesFiltrar, operacionesAprobar);

		String montoTotal = FormateadorUtil.formatearMonto(
				operacionesEmpresaService.obtenerMontoTotalOperacionesAprobar(listaOperacionesAprobar),
				Constantes.CODIGO_MONEDA_PESOS);
		return obtenerDatosTransaccionAprobar(rut, rutEmpresa, listaOperacionesAprobar.size(), montoTotal,
				TRANSACTION_DATA_TITLE_NOMINAS_DIF);
	}

	/**
	 * Genera un XML (transaction data) con los datos de la transacci&oacute;n
	 * requerido por el metodo insertTransaction del servicio mobisigner.
	 * 
	 * @param rutFirmadorParam
	 * @param rutEmpresaParam
	 * @param resumenOpsMoneda
	 * @return
	 */
	private String genXMLTransaccionMultiMoneda(String rutFirmadorParam, String rutEmpresaParam,
			Map<String, ResumenOperacionesMoneda> mapResumenOpsMoneda, String titulo) {
		String resultado = "";

		String rutFirmador = null;
		String rutEmpresaStr = null;
		String montosOperacionesMonedaStr = null;

		if (null != rutFirmadorParam) {
			StringBuilder rutFirma = new StringBuilder();
			rutFirma.append(XML_ETIQUETA_APERTURA_DATA).append(XML_ETIQUETA_APERTURA_ELEMENT)
					.append(XML_ETIQUETA_APERTURA_KEY + "RUT FIRMADOR" + XML_ETIQUETA_CIERRE_KEY)
					.append(XML_ETIQUETA_APERTURA_VALUE).append(FormateadorUtil.formatoRut(rutFirmadorParam))
					.append(XML_ETIQUETA_CIERRE_VALUE).append(XML_ETIQUETA_CIERRE_ELEMENT);
			rutFirmador = rutFirma.toString();
		}

		if (null != rutEmpresaParam) {
			StringBuilder rutEmpresa = new StringBuilder();
			rutEmpresa.append(XML_ETIQUETA_APERTURA_ELEMENT).append(XML_ETIQUETA_APERTURA_KEY).append("RUT EMPRESA")
					.append(XML_ETIQUETA_CIERRE_KEY).append(XML_ETIQUETA_APERTURA_VALUE)
					.append(FormateadorUtil.formatoRut(rutEmpresaParam)).append(XML_ETIQUETA_CIERRE_VALUE)
					.append(XML_ETIQUETA_CIERRE_ELEMENT);
			rutEmpresaStr = rutEmpresa.toString();
		}

		if (null != mapResumenOpsMoneda) {
			StringBuilder montosOperacionesMoneda = new StringBuilder();
			for (Map.Entry<String, ResumenOperacionesMoneda> entry : mapResumenOpsMoneda.entrySet()) {
				ResumenOperacionesMoneda resumenOpsMoneda = entry.getValue();
				montosOperacionesMoneda.append(XML_ETIQUETA_APERTURA_ELEMENT).append(XML_ETIQUETA_APERTURA_KEY)
						.append(resumenOpsMoneda.getMontoTotalOperacionesFormat()).append(" ")
						.append(resumenOpsMoneda.getMonedaOperaciones()).append(XML_ETIQUETA_CIERRE_KEY)
						.append(XML_ETIQUETA_APERTURA_VALUE).append(resumenOpsMoneda.getCantidadTotalOperaciones())
						.append(" ").append(VALUE_OPERACIONES).append(XML_ETIQUETA_CIERRE_VALUE)
						.append(XML_ETIQUETA_CIERRE_ELEMENT);
			}
			montosOperacionesMoneda.append(XML_ETIQUETA_CIERRE_DATA);
			montosOperacionesMonedaStr = montosOperacionesMoneda.toString();
		}

		resultado = XML_ETIQUETA_APERTURA_TRANSACTION_DATA + XML_ETIQUETA_APERTURA_TITLE + titulo
				+ XML_ETIQUETA_CIERRE_TITLE + XML_ETIQUETA_APERTURA_SUB_TITLE + "" + XML_ETIQUETA_CIERRE_SUB_TITLE
				+ rutFirmador + rutEmpresaStr + montosOperacionesMonedaStr + XML_ETIQUETA_CIERRE_TRANSACTION_DATA;

		return resultado;

	}

	/**
	 * Crea desaf&iacute;o FDA en mobisigner para operaciones Spotweb.
	 * 
	 * @param request
	 * @param emailCargo
	 * @param cantRegistros
	 * @param montoTotal
	 * @return Resultado de la creaci&oacute;n del desaf&iacute;o FDA.
	 * @throws BancaEmpresasException
	 */
	public TransactionResponse servCreaDesafioFDA(ListarCrearDesafiosRequest request, String emailCargo,
			Map<String, ResumenOperacionesMoneda> mapResumenOpsMoneda, String docPDF) throws BancaEmpresasException {

		String titulo = "";
		
		if (Constantes.CODIGO_SERVICIO_SPOTWEB.equals(request.getCodigoServicio()))
				titulo = DESAFIO_FDA_TRANSACTION_DATA_TITLE_SPOT_WEB;
		else if(Constantes.CODIGO_SERVICIO_TEF_MX.equals(request.getCodigoServicio()))
				titulo = DESAFIO_FDA_TRANSACTION_DATA_TITLE_TEF_MX;
		else
			titulo = DESAFIO_FDA_TRANSACTION_DATA_TITLE_BICECOMEX;

		TransactionResponse respInsertTransWs = null;

		TransactionRequest req = new TransactionRequest();
		req.setDocType(DESAFIO_FDA_TRANSACTION_REQUEST_TYPE_PDF);
		req.setName(titulo);
		req.setTransactionData(
				genXMLTransaccionMultiMoneda(request.getRut(), request.getRutEmpresa(), mapResumenOpsMoneda, titulo));

		List<SignerTO> lSigners = new ArrayList<>();
		SignerTO signers = new SignerTO();
		signers.setEmail(emailCargo);
		signers.setUserId(MapperUtil.rutSinCeros(request.getRut()));
		lSigners.add(signers);
		req.setSigners(lSigners);

		Map<String, String> metadata = new HashMap<>();
		metadata.put(Constantes.SERVICIO_DETALLE_AUTORIZACION_ATRIBUTO_RESPUESTA_PDF_BASE64, docPDF);
		req.setMetadata(metadata);

		respInsertTransWs = mobisignerService.insertTransaction(req);

		return respInsertTransWs;
	}

	/**
	 * Crea desafio FDA (solo para operaciones de tipo SpotWeb)
	 * 
	 * @param request
	 * @param emailCargo
	 * @param mapOperaciones
	 * @param mapResumenOpsMoneda
	 * 
	 * @return
	 * @throws BancaEmpresasException
	 */
	public TransactionResponse servCreaDesafioFDAOperacionesMultiMoneda(ListarCrearDesafiosRequest request,
			String emailCargo, MapOperaciones mapOperaciones, Map<String, ResumenOperacionesMoneda> mapResumenOpsMoneda)
			throws BancaEmpresasException {
		String docPDF;

		try {
			docPDF = detalleAutorizacionService.generaDocPdfOperacionesMultiMoneda(request,
					request.getListaOperaciones(), mapOperaciones, request.getCodigoServicio());
		} catch (DocumentPdfException e) {
			LOGGER.error("ERROR al crear el PDF con el detalle de la autorización: {}", e);
			docPDF = null;
		}

		return servCreaDesafioFDA(request, emailCargo, mapResumenOpsMoneda, docPDF);
	}

	/**
	 * Metodo para crear desafio FDA (solo para operaciones de tipo SpotWeb)
	 * 
	 * @param request
	 * @param mapOperaciones
	 * @param emailCargo
	 * @param listarCrearDesafiosResp
	 * @throws BancaEmpresasException
	 */
	public void listarCrearDesafioFDAOpMultiMoneda(ListarCrearDesafiosRequest request, MapOperaciones mapOperaciones,
			String emailCargo, ListarCrearDesafiosResp listarCrearDesafiosResp) throws BancaEmpresasException {
		OperacionesAprobar operacionesAprobar = operacionesEmpresaService.generarOperacionesAprobar(mapOperaciones,
				request.getCodigoServicio());
		List<DetalleCampoValorTipoOperacion> listaOperacionesAprobar = operacionesEmpresaService
				.obtenerListaOperaciones(request.getListaOperaciones(), operacionesAprobar);

		Map<String, ResumenOperacionesMoneda> mapResumenOpsMoneda = operacionesEmpresaService
				.obtenerMontosTotalesPorMoneda(listaOperacionesAprobar, request.getCodigoServicio());

		TransactionResponse respCrearFDA = servCreaDesafioFDAOperacionesMultiMoneda(request, emailCargo, mapOperaciones,
				mapResumenOpsMoneda);

		LOGGER.debug("listarCrearDesafioFDAOpMultiMoneda: Desafío FDA creado");
		listarCrearDesafiosResp.setIdTransaccion(respCrearFDA.getTransactionId());
		listarCrearDesafiosResp.setTipoDesafio(Constantes.SERVICIO_LISTAR_CREAR_DESAFIOS_DESAFIO_FDA);
		listarCrearDesafiosResp.setToken(respCrearFDA.getToken());

		try {
			if (Constantes.CODIGO_SERVICIO_SPOTWEB.equals(request.getCodigoServicio())) {
				String montoTotalSpotWeb = String.valueOf(
						operacionesEmpresaService.obtenerMontoTotalesOperacionesSpotWeb(request.getListaOperaciones()));
				
				try {
					insertarFDASeguimiento(request, BancaUtil.listToString(request.getListaOperaciones(), ","),
							respCrearFDA.getTransactionId(), respCrearFDA.getToken(), montoTotalSpotWeb,
							String.valueOf(request.getListaOperaciones().size()));
				}catch (BadSqlGrammarException e){
					LOGGER.error("Error al insertar en tabla seg fda, se reintenta con decimales con coma");
					insertarFDASeguimiento(request, BancaUtil.listToString(request.getListaOperaciones(), ","),
							respCrearFDA.getTransactionId(), respCrearFDA.getToken(), montoTotalSpotWeb.replace(".", ","),
							String.valueOf(request.getListaOperaciones().size()));
				}
				
			} else {
				for (Map.Entry<String, ResumenOperacionesMoneda> entry : mapResumenOpsMoneda.entrySet()) {
					ResumenOperacionesMoneda resumenOpsMoneda = entry.getValue();
					try {
						insertarFDASeguimiento(request,
								BancaUtil.listToString(resumenOpsMoneda.getListNumOperaciones(), ","),
								respCrearFDA.getTransactionId(), respCrearFDA.getToken(),
								String.valueOf(resumenOpsMoneda.getMontoTotalOperaciones()),
								resumenOpsMoneda.getCodigoMonedaOperaciones(),
								String.valueOf(resumenOpsMoneda.getCantidadTotalOperaciones()));
					}catch (BadSqlGrammarException e){
						LOGGER.error("Error al insertar en tabla seg fda, se reintenta con decimales con coma");
						insertarFDASeguimiento(request,
								BancaUtil.listToString(resumenOpsMoneda.getListNumOperaciones(), ","),
								respCrearFDA.getTransactionId(), respCrearFDA.getToken(),
								String.valueOf(resumenOpsMoneda.getMontoTotalOperaciones()).replace(".", ","),
								resumenOpsMoneda.getCodigoMonedaOperaciones(),
								String.valueOf(resumenOpsMoneda.getCantidadTotalOperaciones()));
					}
					
				}
			}
		} catch (BancaEmpresasException e) {
			LOGGER.info("Error al invocar SP POR_PAC_FDA.OR_SP_INS_FDA_SEG_NEW: [{}]", e);
		}
	}

	/**
	 * Crea desaf&iacute;o BICEPASS
	 * 
	 * @param request
	 * @return Datos de la respuesta de la creación del desafío BICEPASS
	 * @throws BancaEmpresasException
	 * @throws SQLException
	 */
	public FirmarTransaccionResponse crearDesafioBICEPASSOperacionesSpotWeb(ListarCrearDesafiosRequest request,
			MapOperaciones mapOperaciones) throws BancaEmpresasException {
		return crearDesafioBICEPASS(request, obtenerDatosTransaccionAprobarOperacionesSpotWeb(request.getRut(),
				request.getRutEmpresa(), request.getListaOperaciones(), request.getCodigoServicio(), mapOperaciones));
	}

	/**
	 * Metodo que recibe una lista con n&uacute;meros de operaciones y obtiene datos
	 * (rut, rut empresa, cantidad de operaciones, monto total de todas las
	 * operaciones) de esas operaciones.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param listaOperacionesFiltrar
	 * @param mapOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public Map<String, String> obtenerDatosTransaccionAprobarOperacionesSpotWeb(String rut, String rutEmpresa,
			List<String> listaOperacionesFiltrar, String codigoServicio, MapOperaciones mapOperaciones)
			throws BancaEmpresasException {

		OperacionesAprobar operacionesAprobar = operacionesEmpresaService.generarOperacionesAprobar(mapOperaciones,
				codigoServicio);
		List<DetalleCampoValorTipoOperacion> listaOperacionesAprobar = operacionesEmpresaService
				.obtenerListaOperaciones(listaOperacionesFiltrar, operacionesAprobar);

		String titulo = (Constantes.CODIGO_SERVICIO_SPOTWEB.equals(codigoServicio)
				? DESAFIO_FDA_TRANSACTION_DATA_TITLE_SPOT_WEB
				: DESAFIO_FDA_TRANSACTION_DATA_TITLE_TEF_MX);
		return obtenerDatosTransaccionAprobarSpotWeb(rut, rutEmpresa, listaOperacionesAprobar, titulo, codigoServicio);
	}

	/**
	 * Genera un map con los datos de las operaciones por aprobar para Spot web.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param cantidadOperaciones
	 * @param montoTotal
	 * @return
	 */
	public Map<String, String> obtenerDatosTransaccionAprobarSpotWeb(String rut, String rutEmpresa,
			List<DetalleCampoValorTipoOperacion> listaOperacionesAprobar, String titulo, String codigoServicio) {

		Map<String, String> resultado = new HashMap<>();
		resultado.put(DATOS_TRANSACCION_APROBAR_CAMPO_OPERACION, titulo);
		resultado.put(DATOS_TRANSACCION_APROBAR_CAMPO_RUTFIRMANTE,
				FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rut)));
		resultado.put(DATOS_TRANSACCION_APROBAR_CAMPO_RUTEMPRESA,
				FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutEmpresa)));

		Map<String, ResumenOperacionesMoneda> operacionesMoneda = operacionesEmpresaService
				.obtenerMontosTotalesPorMoneda(listaOperacionesAprobar, codigoServicio);
		Set<String> mapKeys = operacionesMoneda.keySet();
		for (String key : mapKeys) {
			ResumenOperacionesMoneda operacionMoneda = operacionesMoneda.get(key);
			resultado.put(
					operacionMoneda.getMonedaOperaciones() + " " + operacionMoneda.getMontoTotalOperacionesFormat(),
					String.valueOf(operacionMoneda.getCantidadTotalOperaciones()) + " op.");

		}

		return resultado;
	}

	public ValidarEstadoConfirmacionRequest crearRequestValidarEstadoConfirmacion(BaseRequestEmpresa baseRequest,
			List<String> listaOperaciones, String codigoServicio, String tipoDesafio, String idTransaccion) {
		ValidarEstadoConfirmacionRequest req = new ValidarEstadoConfirmacionRequest();
		req.setCanal(baseRequest.getCanal());
		req.setCodigoServicio(codigoServicio);
		req.setDispositivo(baseRequest.getDispositivo());
		req.setIdTransaccion(idTransaccion);
		req.setIp(baseRequest.getIp());
		req.setListaOperaciones(listaOperaciones);
		req.setOrigenLlamada(baseRequest.getOrigenLlamada());
		req.setRut(baseRequest.getRut());
		req.setRutEmpresa(baseRequest.getRutEmpresa());
		req.setSessionID(baseRequest.getSessionID());
		req.setTipoDesafio(tipoDesafio);
		req.setToken(baseRequest.getToken());
		return req;
	}

	public ValidarEstadoConfirmacionRequest crearRequestValidarEstadoConfirmacion(AprobarOperacionesRequest request) {
		return crearRequestValidarEstadoConfirmacion(request, request.getListaOperaciones(),
				request.getCodigoServicio(), request.getDispositivoFirma(), request.getIdTransaccion());
	}

	public ValidarEstadoConfirmacionResp validarEstadoDesafio(ValidarEstadoConfirmacionRequest request)
			throws BancaEmpresasException {
		ValidarEstadoConfirmacionResp validarEstadoDesafio = new ValidarEstadoConfirmacionResp();
		if (Constantes.SERVICIO_LISTAR_CREAR_DESAFIOS_DESAFIO_FDA.equals(request.getTipoDesafio())) {
			LOGGER.debug("validarEstadoDesafio: Tiene tipo desafío FDA, obtener estado transacción FDA");
			validarEstadoDesafio.setEstadoTransaccion(obtenerEstadoTransaccionFDA(request));
			LOGGER.debug("validarEstadoDesafio: Se obtuvo estado transacción FDA");
		} else {
			LOGGER.debug("validarEstadoDesafio: Tiene tipo desafío BICEPASS, obtener estado transacción BICEPASS");
			validarEstadoDesafio.setEstadoTransaccion(obtenerEstadoTransaccionBICEPASS(request));
			LOGGER.debug("validarEstadoDesafio: Se obtuvo estado transacción BICEPASS");
		}
		return validarEstadoDesafio;
	}

	public boolean isFirmaCorrectaOperaciones(AprobarOperacionesRequest request) throws BancaEmpresasException {
		ValidarEstadoConfirmacionResp resp = validarEstadoDesafio(crearRequestValidarEstadoConfirmacion(request));
		return (null != resp && Constantes.VALIDAR_DESAFIO_ESTADO_EXITO.equals(resp.getEstadoTransaccion()));
	}
	
	/**
	 * Metodo para crear desafio FDA (solo para operaciones de tipo BiceComex)
	 * 
	 * @param request
	 * @param mapOperaciones
	 * @param emailCargo
	 * @param listarCrearDesafiosResp
	 * @throws BancaEmpresasException
	 */
	public void listarCrearDesafioFDAOpBiceComex(ListarCrearDesafiosRequest request, MapOperaciones mapOperaciones,
			String emailCargo, ListarCrearDesafiosResp listarCrearDesafiosResp) throws BancaEmpresasException {
		OperacionesAprobar operacionesAprobar = operacionesEmpresaService.generarOperacionesAprobar(mapOperaciones,
				request.getCodigoServicio());
		List<DetalleCampoValorTipoOperacion> listaOperacionesAprobar = operacionesEmpresaService
				.obtenerListaOperaciones(request.getListaOperaciones(), operacionesAprobar);

		Map<String, ResumenOperacionesMoneda> mapResumenOpsMoneda = biceComexService
				.obtenerMontosTotalesPorMonedaBiceComex(listaOperacionesAprobar);

		TransactionResponse respCrearFDA = servCreaDesafioFDAOperacionesBiceComex(request, emailCargo, mapOperaciones,
				mapResumenOpsMoneda);

		LOGGER.debug("listarCrearDesafioFDAOpBiceComex: Desafío FDA creado");
		listarCrearDesafiosResp.setIdTransaccion(respCrearFDA.getTransactionId());
		listarCrearDesafiosResp.setTipoDesafio(Constantes.SERVICIO_LISTAR_CREAR_DESAFIOS_DESAFIO_FDA);
		listarCrearDesafiosResp.setToken(respCrearFDA.getToken());

		try {
			for (Map.Entry<String, ResumenOperacionesMoneda> entry : mapResumenOpsMoneda.entrySet()) {
				ResumenOperacionesMoneda resumenOpsMoneda = entry.getValue();
				try {
					insertarFDASeguimiento(request, BancaUtil.listToString(resumenOpsMoneda.getListNumOperaciones(), ","),
							respCrearFDA.getTransactionId(), respCrearFDA.getToken(),
							String.valueOf(resumenOpsMoneda.getMontoTotalOperaciones()),
							resumenOpsMoneda.getCodigoMonedaOperaciones(),
							String.valueOf(resumenOpsMoneda.getCantidadTotalOperaciones()));
				}catch (BadSqlGrammarException e){
					LOGGER.error("Error al insertar en tabla seg fda, se reintenta con decimales con coma");
					insertarFDASeguimiento(request, BancaUtil.listToString(resumenOpsMoneda.getListNumOperaciones(), ","),
							respCrearFDA.getTransactionId(), respCrearFDA.getToken(),
							String.valueOf(resumenOpsMoneda.getMontoTotalOperaciones()).replace(".", ","),
							resumenOpsMoneda.getCodigoMonedaOperaciones(),
							String.valueOf(resumenOpsMoneda.getCantidadTotalOperaciones()));
				}
				
			}
		} catch (BancaEmpresasException e) {
			LOGGER.info("Error al invocar SP POR_PAC_FDA.OR_SP_INS_FDA_SEG_NEW: [{}]", e);
		}
	}
	
	/**
	 * Crea desafio FDA (solo para operaciones de tipo BiceComex)
	 * 
	 * @param request
	 * @param emailCargo
	 * @param mapOperaciones
	 * @param mapResumenOpsMoneda
	 * 
	 * @return
	 * @throws BancaEmpresasException
	 */
	public TransactionResponse servCreaDesafioFDAOperacionesBiceComex(ListarCrearDesafiosRequest request,
			String emailCargo, MapOperaciones mapOperaciones, Map<String, ResumenOperacionesMoneda> mapResumenOpsMoneda)
			throws BancaEmpresasException {
		String docPDF;

		try {
			docPDF = detalleAutorizacionService.generaDocPdfOperacionesBiceComex(request,
					request.getListaOperaciones(), mapOperaciones, request.getCodigoServicio());
		} catch (DocumentPdfException e) {
			LOGGER.error("ERROR al crear el PDF con el detalle de la autorización: {}", e);
			docPDF = null;
		}

		return servCreaDesafioFDA(request, emailCargo, mapResumenOpsMoneda, docPDF);
	}
	
	/**
	 * Crea desaf&iacute;o BICEPASS
	 * 
	 * @param request
	 * @return Datos de la respuesta de la creación del desafío BICEPASS
	 * @throws BancaEmpresasException
	 * @throws SQLException
	 */
	public FirmarTransaccionResponse crearDesafioBICEPASSOperacionesBiceComex(ListarCrearDesafiosRequest request,
			MapOperaciones mapOperaciones) throws BancaEmpresasException {
		return crearDesafioBICEPASS(request, obtenerDatosTransaccionAprobarOperacionesBiceComex(request.getRut(),
				request.getRutEmpresa(), request.getListaOperaciones(), request.getCodigoServicio(), mapOperaciones));
	}
	
	/**
	 * Metodo que recibe una lista con n&uacute;meros de operaciones y obtiene datos
	 * (rut, rut empresa, cantidad de operaciones, monto total de todas las
	 * operaciones) de esas operaciones.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param listaOperacionesFiltrar
	 * @param mapOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public Map<String, String> obtenerDatosTransaccionAprobarOperacionesBiceComex(String rut, String rutEmpresa,
			List<String> listaOperacionesFiltrar, String codigoServicio, MapOperaciones mapOperaciones)
			throws BancaEmpresasException {

		OperacionesAprobar operacionesAprobar = operacionesEmpresaService.generarOperacionesAprobar(mapOperaciones,
				codigoServicio);
		List<DetalleCampoValorTipoOperacion> listaOperacionesAprobar = operacionesEmpresaService
				.obtenerListaOperaciones(listaOperacionesFiltrar, operacionesAprobar);

		String titulo = DESAFIO_FDA_TRANSACTION_DATA_TITLE_BICECOMEX;
		return obtenerDatosTransaccionAprobarBiceComex(rut, rutEmpresa, listaOperacionesAprobar, titulo);
	}
	
	/**
	 * Genera un map con los datos de las operaciones por aprobar para BiceComex.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param cantidadOperaciones
	 * @param montoTotal
	 * @return
	 */
	public Map<String, String> obtenerDatosTransaccionAprobarBiceComex(String rut, String rutEmpresa,
			List<DetalleCampoValorTipoOperacion> listaOperacionesAprobar, String titulo) {

		Map<String, String> resultado = new HashMap<>();
		resultado.put(DATOS_TRANSACCION_APROBAR_CAMPO_OPERACION, titulo);
		resultado.put(DATOS_TRANSACCION_APROBAR_CAMPO_RUTFIRMANTE,
				FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rut)));
		resultado.put(DATOS_TRANSACCION_APROBAR_CAMPO_RUTEMPRESA,
				FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutEmpresa)));

		Map<String, ResumenOperacionesMoneda> operacionesMoneda = biceComexService
				.obtenerMontosTotalesPorMonedaBiceComex(listaOperacionesAprobar);
		Set<String> mapKeys = operacionesMoneda.keySet();
		for (String key : mapKeys) {
			ResumenOperacionesMoneda operacionMoneda = operacionesMoneda.get(key);
			resultado.put(
					operacionMoneda.getMonedaOperaciones() + " " + operacionMoneda.getMontoTotalOperacionesFormat(),
					String.valueOf(operacionMoneda.getCantidadTotalOperaciones()) + " op.");

		}

		return resultado;
	}
	
	/**
	 * Valida monto máximo para un lote de operaciones para el flujo de desafío BICEPASS
	 * 
	 * 
	 * @param codServicio
	 * @param operacionesObj
	 * @throws BancaEmpresasException
	 */
	public void validarMontoMaximoSinFda(List<String> listaOperaciones, String codServicio, Object operacionesObj) throws BancaEmpresasException {
		LOGGER.debug("validarMontoMaximo: codServicio: [{}]", codServicio);
		
		try {
			BigDecimal montoMax = obtenerMontoMaximoSinFda(codServicio, operacionesObj);
			BigDecimal montoOperacion = new BigDecimal("0");
			
			if (Constantes.CODIGO_SERVICIO_NOMINAS_EN_LINEA.equals(codServicio)) {
				List<NominaEnLinea> operaciones = (List<NominaEnLinea>) operacionesObj;
				for (NominaEnLinea nominaEnLinea : operaciones) {
					montoOperacion = new BigDecimal(nominaEnLinea.getMonto());
					if(montoOperacion.compareTo(montoMax) == 1) {
						LOGGER.error("El monto [{}] de la operación excede el monto máximo para operar sin FDA", montoOperacion.toString());				
						throw new MontoInvalidoException(montoMax.toString()); 
					}
				}
			} else {
				MapOperaciones operaciones = (MapOperaciones) operacionesObj;
				for (String operacion : listaOperaciones) {
					String montoOperacionString = "";
					montoOperacionString = montoOperacionString + valoresCampoOperacionesService.getValorCampoOperacion(operaciones, operacion, "Monto", true);
					montoOperacion = new BigDecimal(montoOperacionString);
					if(montoOperacion.compareTo(montoMax) == 1) {
						LOGGER.error("El monto [{}] de la operación excede el monto máximo para operar sin FDA", montoOperacionString);				
						throw new MontoInvalidoException(montoMax.toString()); 
					}
				}
			}
			LOGGER.debug("validarMontoMaximo: montos válidos para operar sin FDA");
		} catch (NoEncontradoException e) {
			LOGGER.warn("validarMontoMaximo: {}, codServicio: [{}]", e.getMessage(), codServicio);
		}
		
	}
	
	/**
	 * Obtiene monto máximo a transferir sin dispositivo FDA
	 * 
	 * 
	 * @param codServicio
	 * @param operacionesObj
	 * @throws BancaEmpresasException
	 */
	public BigDecimal obtenerMontoMaximoSinFda(String codServicio, Object operacionesObj)
			throws BancaEmpresasException {
		LOGGER.debug("obtenerMontoMaximoSinFda: codServicio: [{}]", codServicio);

		String nomParam = "";
		nomParam = nomParam
				+ propiedadesExterna.getProperty(Constantes.SERVICIOS_NOM_PARAM_MTO_MAX_TEF_SIN_FDA + codServicio);
		String montoMaxString = parametrosValidacionService.getParamValidacion(nomParam,
				Constantes.SERVICIOS_NOM_TIPO_MTO_MAX_TEF_SIN_FDA, Constantes.VAL_PARAMETRO);

		BigDecimal montoMax = !montoMaxString.isEmpty() ? new BigDecimal(montoMaxString) : null;

		if (montoMax == null) {
			throw new NoEncontradoException("Parámetro inactivo o no encontrado");
		}

		LOGGER.info("obtenerMontoMaximoSinFda: monto máximo: [{}], codServicio: [{}]", montoMax.toString(), codServicio);

		return montoMax;
	}
	
	/**
	 * Registra contrato FDA desactivado (con estado flg_activo 0)
	 * 
	 * @param rut
	 * @param rutCliente
	 * @param codigoServicio
	 * @throws BancaEmpresasException
	 */
	public void registraContratoFDADesactivado(String rut, String rutCliente, String codigoServicio)
			throws BancaEmpresasException {
		LOGGER.info("DesafiosServices registraContratoFDADesactivado: rut[{}] rutCliente[{}] codigoServicio[{}]", rut, rutCliente,
				codigoServicio);

		Map<String, Object> params = new HashMap<>();
		params.put("P_RUT_CLIENTE", rutCliente);
		params.put("P_RUT_MODIFICA", rut);
		params.put("P_COD_SERVICIOS", codigoServicio);
		params.put("P_PROVEEDOR", PROVEEDOR_ESIGN_COD);

		try {
			portalOrawRepository.registraContratoFDADesactivado(params);

			LOGGER.info("DesafiosServices registraContratoFDADesactivado: P_STATUS[{}]", params.get("P_STATUS"));
			LOGGER.info("DesafiosServices registraContratoFDADesactivado: P_MSG_STATUS[{}]", params.get("P_MSG_STATUS"));

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}
	}
	
	/**
	 * Determina si se debe validar si existen contratos FDA o no
	 * 
	 * 
	 * @param codServicio
	 * @throws BancaEmpresasException
	 */
	public boolean validarContratosFda() throws BancaEmpresasException {
		LOGGER.debug("DesafiosService validarContratosFda");
		boolean resultado = false;
		String revisarContratosFdaActivos = parametrosValidacionService
				.getParamValidacion("FDA", "VALIDA_CONTRATOS", Constantes.VAL_PARAMETRO).trim();

		if (revisarContratosFdaActivos.equals(Constantes.CONSTANTE_UNO)) {
			resultado = true;

		} else {
			LOGGER.info("Parámetro validar contratos inactivo");
		}

		LOGGER.info("DesafiosService validarContratosFda: resultado: {}", resultado);
		return resultado;
	}
	
	/**
	 * Consulta si tiene contratos FDA activos
	 * 
	 * @param request
	 * @return tieneContratosFda
	 * @throws BancaEmpresasException
	 */
	public boolean tieneContratosFDA(ListarCrearDesafiosRequest request, boolean validarContratosFda) throws BancaEmpresasException {
		if(!validarContratosFda)
			return false;
		
		boolean tieneContratosFda = false;

		try {

			LOGGER.info("[{}] [{}] [{}] TIENE CONTRATOS FDA? [{}] [{}] ", request.getDispositivo(), request.getToken(),
					request.getRut(), request.getRutEmpresa(), request.getCodigoServicio());

			Map<String, Object> params = new HashMap<>();
			params.put("RUT_CLIENTE", request.getRutEmpresa());
			params.put("RUT_MODIFICA", request.getRut());
			params.put("COD_SERVICIO", request.getCodigoServicio());
			params.put("ESTADO", Constantes.CONSTANTE_UNO);
			params.put("CUR", null);

			LOGGER.info("[{}] [{}] [{}] REQUEST SP POR_PKG_ADM_CONT_FDA.POR_SP_CONS_CONT_FDA ==> [{}]",
					request.getDispositivo(), request.getToken(), request.getRut(), params);

			portalOrawRepository.consultaContratosFDA(params);

			LOGGER.info("[{}] [{}] [{}] RESPONSE POR_PKG_ADM_CONT_FDA.POR_SP_CONS_CONT_FDA ==> [{}]",
					request.getDispositivo(), request.getToken(), request.getRut(), params);

			List<Map<String, Object>> salida = (List<Map<String, Object>>) params.get("CUR");

			if (MapperUtil.isSalidaSPValida(salida)) {
				tieneContratosFda = true;
			}

		} catch (SQLException e) {
			LOGGER.info("[{}] [{}] [{}] ERROR SP POR_PKG_ADM_CONT_FDA.POR_SP_CONS_CONT_FDA", request.getDispositivo(),
					request.getToken(), request.getRut(), e);
			throw new ErrorStoredProcedureException(e);
		}

		LOGGER.info("[{}] [{}] [{}] TIENE CONTRATOS FDA? [{}] [{}] [{}] ", request.getDispositivo(), request.getToken(),
				request.getRut(), request.getRutEmpresa(), request.getCodigoServicio(), tieneContratosFda);
		return tieneContratosFda;
	}
	
}
