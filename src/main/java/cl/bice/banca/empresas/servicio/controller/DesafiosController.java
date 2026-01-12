package cl.bice.banca.empresas.servicio.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.WebServiceException;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.EntradaInvalidaException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.MontoInvalidoException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.nominas.NominaEnLinea;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.request.desafios.EstadoValidacionMobileSignerRequest;
import cl.bice.banca.empresas.servicio.model.request.desafios.ListarCrearDesafiosRequest;
import cl.bice.banca.empresas.servicio.model.request.desafios.ValidarEstadoConfirmacionRequest;
import cl.bice.banca.empresas.servicio.model.request.desafios.ValidarMobileSignerRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.base.ResponseBase;
import cl.bice.banca.empresas.servicio.model.response.base.Respuesta;
import cl.bice.banca.empresas.servicio.model.response.desafios.ListarCrearDesafiosResp;
import cl.bice.banca.empresas.servicio.model.response.desafios.ListarCrearDesafiosResponse;
import cl.bice.banca.empresas.servicio.model.response.desafios.ValidarEstadoConfirmacionResp;
import cl.bice.banca.empresas.servicio.model.response.desafios.ValidarEstadoConfirmacionResponse;
import cl.bice.banca.empresas.servicio.model.response.desafios.ValidarMobileSignerResp;
import cl.bice.banca.empresas.servicio.service.BiceComexService;
import cl.bice.banca.empresas.servicio.service.DesafiosServices;
import cl.bice.banca.empresas.servicio.service.EmpresasService;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.service.NominasEnLineaService;
import cl.bice.banca.empresas.servicio.service.OperacionesEmpresaService;
import cl.bice.banca.empresas.servicio.service.ValidacionService;
import cl.bice.banca.empresas.servicio.service.ValoresCampoOperacionesService;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;

/**
 * Controlador que maneja la informacion de desaf&iacute;os.
 *
 * @author Cristian Pais (TINet)
 * @version 1.0
 */
@RestController
public class DesafiosController extends BaseControllerEmpresa {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DesafiosController.class);

	DesafiosServices desafiosServices;

	@Autowired
	OperacionesEmpresaService operacionesEmpresaService;

	@Autowired
	ValidacionService validacionService;

	@Autowired
	EmpresasService empresasService;

	@Autowired
	EstadoService estadoService;

	@Autowired
	ValoresCampoOperacionesService valoresCampoOperacionesService;

	@Autowired
	NominasEnLineaService nominasEnLineaService;
	
	@Autowired
	BiceComexService biceComexService;

	@Autowired
	public DesafiosController(@Lazy DesafiosServices desafiosServices) {
		this.desafiosServices = desafiosServices;
	}

	/**
	 * Consulta si el cliente tiene mobisigner y si tiene, crea el desaf&iacute;o
	 * FDA. Si el cliente no está enrolado en mobisigner, lista los desaf&iacute;os
	 * y consulta si el cliente tiene BICEPASS. Si el cliente tiene BICEPASS crea el
	 * desaf&iacute;o BICEPASS. Si no tiene ninguno de los desaf&iacute;os
	 * anteriormente mencionados simplemente retorna un objeto vac&iacute;o.
	 * 
	 * @param request
	 * @return Identificador de la transacci&oacute;n y tipo desaf&iacute;o.
	 */
	@PostMapping(value = "/desafios/listarCrear", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListarCrearDesafiosResponse> listarCrearDesafios(
			@RequestBody ListarCrearDesafiosRequest request) {
		
		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());
		
		String idSesion = "";
		idSesion = biceComexService.registroSeguimientoBiceComexDesafio(idSesion, request);

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		if (estado.isEXITO() && StringUtils.isBlank(request.getCodigoServicio())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_CODIGO_SERVICIO);
		}

		ListarCrearDesafiosResp listarCrearDesafiosResp = null;

		if (estado.isEXITO()) {
			try {
				listarCrearDesafiosResp = desafiosServices.listarCrearDesafiosPorTipoOperacion(request);
				if(request.getCodigoServicio().equals(Constantes.CODIGO_SERVICIO_BICECOMEX) && request.getCanal().equals(Constantes.CANAL_MOBILE_EMPRESA))
						listarCrearDesafiosResp.setIdSesion(idSesion);

			} catch (MontoInvalidoException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstadoExtra(EstadoEnum.ERROR_E004_EMPRESAS_LISTAR_CREAR_DESAFIOS, estado, e.getMessage());
			} catch (NoEncontradoException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_LISTAR_CREAR_DESAFIOS, estado);
			} catch (ErrorStoredProcedureException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_LISTAR_CREAR_DESAFIOS, estado);
			} catch (RequestInvalidoException | EntradaInvalidaException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado);
			} catch (WebServiceException | BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_LISTAR_CREAR_DESAFIOS, estado);
			}
		}

		ListarCrearDesafiosResponse listarCrearDesafiosResponse = new ListarCrearDesafiosResponse();
		listarCrearDesafiosResponse.setEstatus(estado);
		listarCrearDesafiosResponse.setRespuesta(listarCrearDesafiosResp);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(listarCrearDesafiosResponse);

	}

	/**
	 * Retorna el estado del desaf&iacute;o
	 * 
	 * @param request
	 * @return estado del desaf&iacute;o (&eacute;xito, pendiente, error)
	 */
	@PostMapping(value = "/desafios/validarEstadoDesafio", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ValidarEstadoConfirmacionResponse> validarEstadoDesafio(
			@RequestBody ValidarEstadoConfirmacionRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		ValidarEstadoConfirmacionResp validarEstadoDesafio = new ValidarEstadoConfirmacionResp();

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		if (estado.isEXITO() && StringUtils.isBlank(request.getCodigoServicio())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_CODIGO_SERVICIO);
		}

		if (estado.isEXITO()) {
			try {

				if (Constantes.CODIGO_SERVICIO_NOMINAS_EN_LINEA.equals(request.getCodigoServicio())) {
					List<NominaEnLinea> listNominas = nominasEnLineaService
							.obtenerListaNominasEnLinea(request.getListaOperaciones());
					if (!validacionService.isPertenenciaNominaValida(request, request.getCodigoServicio(), listNominas))
						throw new RequestInvalidoException();
				} else if (Constantes.CODIGO_SERVICIO_BICECOMEX.equals(request.getCodigoServicio())){
					if (!validacionService.isPertenenciaValidaBiceComex(request, request.getCodigoServicio(), request.getListaOperaciones(), true)) 
						throw new RequestInvalidoException();
				} else {
					MapOperaciones mapOperaciones = valoresCampoOperacionesService.obtenerMapOperaciones(
							request.getRut(), request.getRutEmpresa(), request.getCodigoServicio(), request.getCanal(),
							true, request.getFechaDesde(), request.getFechaHasta());
					if (!validacionService.isPertenenciaValida(request, request.getCodigoServicio(),
							request.getListaOperaciones(), mapOperaciones, true)) {
						mapOperaciones.clearMap();
						throw new RequestInvalidoException();
					}
				}

				validarEstadoDesafio = desafiosServices.validarEstadoDesafio(request);
				
			} catch (NoEncontradoException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_VALIDAR_ESTADO_DESAFIO, estado);
			} catch (ErrorStoredProcedureException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_VALIDAR_ESTADO_DESAFIO, estado);
			} catch (RequestInvalidoException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado);
			} catch (WebServiceException | BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_VALIDAR_ESTADO_DESAFIO, estado);
			}
		}

		ValidarEstadoConfirmacionResponse validarEstadoConfirmacionResponse = new ValidarEstadoConfirmacionResponse();
		validarEstadoConfirmacionResponse.setEstatus(estado);
		validarEstadoConfirmacionResponse.setRespuesta(validarEstadoDesafio);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(validarEstadoConfirmacionResponse);
	}

	/**
	 * Consulta al servicio user/exists de mobisigner si un rut usuario se encuentra
	 * enrolado.
	 * 
	 * @param request
	 * @return true si el rut usuario se encuentra enrolado en mobisigner, false en
	 *         caso contrario.
	 */
	@PostMapping(value = "/desafios/enroladoMobisigner", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBase> isClienteEnroladoMobisigner(@RequestBody BaseRequestEmpresa request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		ResponseBase response = new ResponseBase();
		Respuesta respuesta = new Respuesta();

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		if (estado.isEXITO()) {
			try {
				respuesta.setAdditionalProperty("clienteEnrolado",
						desafiosServices.isClienteEnrolado(request.getRut()));

			} catch (WebServiceException | BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_CLIENTE_MOBISIGNER, estado);
			}
		}
		response.setEstado(estado);
		response.setRespuesta(respuesta);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(response);
	}

	/**
	 * Checkea si el cliente está enrolado en mobisigner y si lo está crea desafío
	 * FDA y de esta manera se valida que mobilesigner está operativo.
	 * 
	 * @param request
	 * @return Identificador de la transacci&oacute;n y tipo desaf&iacute;o.
	 */
	@PostMapping(value = "/desafios/validarMobileSigner", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBase> validarMobileSigner(@RequestBody ValidarMobileSignerRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		ResponseBase response = new ResponseBase();
		Respuesta respuesta = new Respuesta();

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		ValidarMobileSignerResp validarMobileSignerResp = new ValidarMobileSignerResp();

		if (estado.isEXITO()) {
			try {
				desafiosServices.validarMobileSigner(request, validarMobileSignerResp);
			} catch (WebServiceException | BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_VALIDAR_MOBILE_SIGNER, estado);
			}
		}

		respuesta.setAdditionalProperty("respuesta", validarMobileSignerResp);
		response.setEstado(estado);
		response.setRespuesta(respuesta);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(response);

	}

	/**
	 * Retorna el estado de la validacion de mobile signer
	 * 
	 * @param request
	 * @return estado del desaf&iacute;o (&eacute;xito, pendiente, error)
	 */
	@PostMapping(value = "/desafios/verEstadoValidacionMobileSigner", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBase> estadoValidacionMobileSigner(
			@RequestBody EstadoValidacionMobileSignerRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		ResponseBase response = new ResponseBase();
		Respuesta respuesta = new Respuesta();

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		ValidarEstadoConfirmacionResp validarEstadoDesafio = new ValidarEstadoConfirmacionResp();

		if (estado.isEXITO()) {
			try {
				validarEstadoDesafio
						.setEstadoTransaccion(desafiosServices.obtenerEstadoTransaccionFDA(request.getIdTransaccion()));
				LOGGER.debug("estadoValidacionMobileSigner: Se obtuvo estado transacción FDA");
			} catch (WebServiceException | BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_ESTADO_VALIDACION_MOBILE_SIGNER, estado);
			}
		}

		respuesta.setAdditionalProperty("respuesta", validarEstadoDesafio);
		response.setEstado(estado);
		response.setRespuesta(respuesta);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(response);
	}

}