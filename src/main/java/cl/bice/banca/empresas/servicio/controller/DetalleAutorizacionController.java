package cl.bice.banca.empresas.servicio.controller;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.request.operaciones.PdfAutorizacionRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.base.ResponseBase;
import cl.bice.banca.empresas.servicio.model.response.base.Respuesta;
import cl.bice.banca.empresas.servicio.service.DetalleAutorizacionService;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;

@RestController
@RequestMapping("/autorizacion")
public class DetalleAutorizacionController extends BaseControllerEmpresa {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DetalleAutorizacionController.class);

	@Autowired
	DetalleAutorizacionService detalleAutorizacionService;

	@Autowired
	EstadoService estadoService;

	/**
	 * Recibe una lista de operaciones y genera un documento PDF con el detalle de
	 * autorizaci&oacute;n.
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping(value = "/generarDocDetalleAutorizacion", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBase> obtenerPdfAutorizacion(@RequestBody PdfAutorizacionRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		Estado estado = estadoService.inicializarEstado();

		ResponseBase response = new ResponseBase();
		response.setEstado(estado);
		Respuesta respuesta = new Respuesta();
		response.setRespuesta(respuesta);

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, response.getEstado());

		if (estado.isEXITO() && request.getListaOperaciones() == null) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_LISTA_OPERACIONES);
		}
		if (estado.isEXITO() && StringUtils.isBlank(request.getCodigoServicio())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_CODIGO_SERVICIO);
		}

		if (estado.isEXITO()) {
			response.getRespuesta().setAdditionalProperty(
					Constantes.SERVICIO_DETALLE_AUTORIZACION_ATRIBUTO_RESPUESTA_PDF_BASE64,
					detalleAutorizacionService.obtenerPDFDetalleAutorizacion(request, request.getCodigoServicio(),
							request.getListaOperaciones(), estado));
		}

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(response);

	}
}
