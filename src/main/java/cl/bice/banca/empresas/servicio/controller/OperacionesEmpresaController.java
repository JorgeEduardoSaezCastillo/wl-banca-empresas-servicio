package cl.bice.banca.empresas.servicio.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.WebServiceException;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.request.ListaOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.ValidaPoderRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.base.ResponseBase;
import cl.bice.banca.empresas.servicio.model.response.base.Respuesta;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesPortalResp;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesPortalResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.LiberarOperacionesResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.LiberarOperacionesPortalResponse;
import cl.bice.banca.empresas.servicio.model.response.operaciones.ValidacionPoderResponse;
import cl.bice.banca.empresas.servicio.service.BiceComexService;
import cl.bice.banca.empresas.servicio.service.DesafiosServices;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.service.MailService;
import cl.bice.banca.empresas.servicio.service.NominasDiferidasService;
import cl.bice.banca.empresas.servicio.service.NominasEnLineaService;
import cl.bice.banca.empresas.servicio.service.OperacionesEmpresaService;
import cl.bice.banca.empresas.servicio.service.RequestEmpresasService;
import cl.bice.banca.empresas.servicio.service.OperacionesEmpresaPortalService;
import cl.bice.banca.empresas.servicio.service.ValidacionService;
import cl.bice.banca.empresas.servicio.soap.ClienteOperacionNomina;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;

/**
 * Controller para obtencion de nominas de pago
 * * @author TInet
 *
 */
@RestController
@RequestMapping("/operaciones")
public class OperacionesEmpresaController extends BaseControllerEmpresa {

	@Autowired
	public OperacionesEmpresaController(EstadoService estadoService,
										RequestEmpresasService reqEmpresasService,
										OperacionesEmpresaPortalService operacionesEmpresaPortalService,
										OperacionesEmpresaService operacionesEmpresaService,
										NominasEnLineaService nominasEnLineaService,
										HttpServletRequest httpServletRequest) {
		this.estadoService = estadoService;
		this.reqEmpresasService = reqEmpresasService;
		this.operacionesEmpresaPortalService = operacionesEmpresaPortalService;
		this.operacionesEmpresaService = operacionesEmpresaService;
		this.nominasEnLineaService = nominasEnLineaService;
		this.httpServletRequest = httpServletRequest;
	}

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionesEmpresaController.class);

	private HttpServletRequest httpServletRequest;

	/**
	 * Servicio consulta
	 */
	@Autowired
	private OperacionesEmpresaService operacionesEmpresaService;

	@Autowired
	private OperacionesEmpresaPortalService operacionesEmpresaPortalService;

	@Autowired
	private NominasDiferidasService nominasDiferidasService;

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
	ValidacionService validacionService;

	@Autowired
	NominasEnLineaService nominasEnLineaService;

	@Autowired
	DesafiosServices desafiosServices;

	@Autowired
	BiceComexService biceComexService;


	/**
	 * Obtiene las operaciones por día de la empresa.
	 * * @param request
	 * @return detalle de la nomina
	 */
	@PostMapping(value = "/listaOperaciones", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBase> listaOperaciones(@RequestBody ListaOperacionesRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		ResponseBase response = null;
		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		if (StringUtils.isBlank(request.getCodigoServicio())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_CODIGO_SERVICIO);
		}

		if (estado.isEXITO()) {
			response = operacionesEmpresaService.obtieneListaOperaciones(request, estado);
		} else {
			response = new ResponseBase();
			response.setEstado(estado);
			Respuesta respuesta = new Respuesta();
			response.setRespuesta(respuesta);
		}

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(response);
	}

	/**
	 * Valida los poderes de la empresa. Verifica primero si la empresa tiene
	 * facultad, si tiene valida poderes contra BD mediante SP sinó valida contra
	 * tpag2000.
	 * * @param request
	 * @return 50 firma completa, 40 firma parcial, 30 sin firmar
	 */
	@PostMapping(value = "/validaPoderEmpresa", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ValidacionPoderResponse> validaPoderEmpresa(@RequestBody ValidaPoderRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);
		if (StringUtils.isBlank(request.getCodigoServicio())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_CODIGO_SERVICIO);
		} else if (StringUtils.isBlank(request.getNumeroOperacion())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_NUMERO_OPERACION);
		}

		ValidacionPoderResponse response = operacionesEmpresaService.validaPoderEmpresa(request, estado);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(response);
	}

	/**
	 * Aprueba operaciones.
	 * * @param request
	 * @return operaciones no aprobadas y cantidad de operaciones no aprobadas.
	 */
	@PostMapping(value = "/aprobarOperaciones", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AprobarOperacionesResponse> aprobarOperaciones(
			@RequestBody AprobarOperacionesRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);
		if (StringUtils.isBlank(request.getCodigoServicio())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_CODIGO_SERVICIO);
		} else if (request.getListaOperaciones() == null || request.getListaOperaciones().isEmpty()) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_LISTA_OPERACIONES);
		} else if (StringUtils.isBlank(request.getNombreApoderado())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_NOMBRE_APODERADO);
		} else if (StringUtils.isBlank(request.getIdTransaccion())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_ID_TRANSACCION);
		}

		if ("1".equals(propiedadesExterna.getProperty("validarFirmaOperacionesAprobarOperaciones"))
				&& Constantes.SERVICIO_LISTAR_CREAR_DESAFIOS_DESAFIO_FDA.equals(request.getDispositivoFirma())) {
			try {
				if (estado.isEXITO() && !desafiosServices.isFirmaCorrectaOperaciones(request)) {
					estadoService.setErrorEstado(EstadoEnum.ERROR_E004_EMPRESAS_APROBAR_OPERACIONES, estado);
				}
			} catch (WebServiceException | BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES, estado);
			}
		}

		AprobarOperacionesResponse response = null;

		if (Constantes.CODIGO_SERVICIO_NOMINAS_EN_LINEA.equals(request.getCodigoServicio())) {
			response = nominasEnLineaService.aprobarNominasEnLinea(request, estado);
		} else if (Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_DIVIDENDO_ACCIONES
				.equals(request.getCodigoServicio())
				|| Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_PROVEEDORES.equals(request.getCodigoServicio())
				|| Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES
				.equals(request.getCodigoServicio())) {
			if (StringUtils.isBlank(request.getDispositivoFirma())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
						EstadoService.GLOSA_ERROR_REQUEST_CAMPO_DISP_FIRMA);
			}
			response = nominasDiferidasService.aprobarNominasDiferidas(request, estado);
		} else if (Constantes.CODIGO_SERVICIO_BICECOMEX.equals(request.getCodigoServicio())) {
			if (StringUtils.isBlank(request.getDispositivoFirma())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
						EstadoService.GLOSA_ERROR_REQUEST_CAMPO_DISP_FIRMA);
			}
			response = biceComexService.aprobarBiceComex(request, estado);
		} else {
			response = operacionesEmpresaService.aprobarOperaciones(request, estado);
		}

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(response);
	}

	/**
	 * Libera operaciones.
	 * * @param request
	 * @return lista de operaciones liberadas.
	 */
	@PostMapping(value = "/liberarOperaciones", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LiberarOperacionesResponse> liberarOperaciones(
			@RequestBody LiberarOperacionesRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);
		if (StringUtils.isBlank(request.getCodigoServicio())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_CODIGO_SERVICIO);
		} else if (request.getListaOperaciones() == null || request.getListaOperaciones().isEmpty()) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_LISTA_OPERACIONES);
		} else if (StringUtils.isBlank(request.getNombreApoderado())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_NOMBRE_APODERADO);
		} else if (StringUtils.isBlank(request.getGlsTipoDisp())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_GLS_TIPO_DISP);
		} else if (StringUtils.isBlank(request.getGlsDesafio())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_GLS_DESAFIO);
		} else if (StringUtils.isBlank(request.getGlsTipocliente())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_GLS_TIPO_CLIENTE);
		}

		if (Constantes.CODIGO_SERVICIO_LBTR.equals(request.getCodigoServicio())) {
			if (StringUtils.isBlank(request.getTipo())) { // Nota: getTipo() deducido del contexto, validación de imagen
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
						EstadoService.GLOSA_ERROR_REQUEST_CAMPO_TIPO);
			}
		}

		LiberarOperacionesResponse response = null;

		validacionService.validarClaveUsuario(request, estado);

		if (Constantes.CODIGO_SERVICIO_NOMINAS_EN_LINEA.equals(request.getCodigoServicio())) {
			response = nominasEnLineaService.liberarNominasEnLinea(request, estado);
		} else {
			response = operacionesEmpresaService.liberarOperaciones(request, estado);
		}

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(response);
	}

	/**
	 * Permite aprobar operaciones en nuevo modelo asincrono desde Portal Privado.
	 * * * @param AprobarOperacionesPortalRequest
	 * @return operaciones no aprobadas y cantidad de operaciones no aprobadas.
	 */
	@PostMapping(value = "/aprobarOperacionesMasivas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AprobarOperacionesPortalResponse> aprobarOperacionesMasivas(
			@RequestBody AprobarOperacionesPortalRequest request) {

		AprobarOperacionesPortalResponse response = null;
		String listaOp = request.getListaOperaciones() == null ? "" : request.getListaOperaciones().toString();

		LOGGER.info("rutApoderado [{}] rutEmpresa [{}] canal:[{}] codigoServicio:[{}] ListaOperaciones:[{}]",
				request.getRutApoderado(), request.getRutEmpresa(), request.getCanal(), request.getCodigoServicio(), listaOp );

		Estado estado = estadoService.inicializarEstado();

		try {
			reqEmpresasService.validaRequestEmpresaPortal(request, estado);
		} catch (RequestInvalidoException e) {
			LOGGER.info("Error al validar request");
		}

		if (estado.isEXITO()) {
			response = operacionesEmpresaPortalService.aprobarOperacionesMasivas(request, estado);
		} else {
			AprobarOperacionesPortalResp aprobarOperacionesResp = new AprobarOperacionesPortalResp();
			aprobarOperacionesResp.setOperacionesNoAprobadas(Collections.emptyList());
			aprobarOperacionesResp.setCantOperacionesAprobadas(0);
			aprobarOperacionesResp.setCantOperacionesNoAprobadas(0);
			aprobarOperacionesResp.setOperacionesConFirmaParcial(Collections.emptyList());
			aprobarOperacionesResp.setOperacionesConFirmaCompleta(Collections.emptyList());
			response = new AprobarOperacionesPortalResponse();
			response.setEstado(estado);
			response.setRespuesta(aprobarOperacionesResp);
		}

		return ResponseEntity.ok(response);
	}

	/**
	 * Permite liberar operaciones en nuevo modelo asincrono desde Portal Privado.
	 * * * @param request
	 * @return lista de operaciones liberadas.
	 */
	@PostMapping(value = "/liberarOperacionesMasivas", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LiberarOperacionesPortalResponse> liberarOperacionesMasivas(
			@RequestBody LiberarOperacionesPortalRequest request) {

		String listaOp = request.getListaOperaciones() == null ? "" : request.getListaOperaciones().toString();

		LOGGER.info("OperacionesEmpresaController.liberarOperacionesMasivas rutApoderado [{}] rutEmpresa [{}] canal:[{}] codigoServicio:[{}] ListaOperaciones:[{}]",
				request.getRutApoderado(), request.getRutEmpresa(), request.getCanal(), request.getCodigoServicio(), listaOp );

		Estado estado = estadoService.inicializarEstado();
		LiberarOperacionesPortalResponse response = null;

		try {
			reqEmpresasService.validaRequestEmpresaPortalLiberacion(request, estado);
			if (!estado.isEXITO()) {
				response = operacionesEmpresaPortalService.setearRespuestaRequestInvalido(estado);
			} else {
				response = operacionesEmpresaPortalService.liberarOperacionesPortal(request, estado);
			}
		} catch (RequestInvalidoException e) {
			LOGGER.info("Error al validar request");
		}

		return ResponseEntity.ok(response);
	}
}