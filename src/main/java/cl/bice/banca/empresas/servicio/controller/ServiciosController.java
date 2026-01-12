package cl.bice.banca.empresas.servicio.controller;

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

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.base.ResponseBase;
import cl.bice.banca.empresas.servicio.model.response.base.Respuesta;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.service.ServiciosService;
import cl.bice.banca.empresas.servicio.soap.ClienteOperacionNomina;

@RestController
@RequestMapping("/servicios")
public class ServiciosController extends BaseControllerEmpresa {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiciosController.class);
	/**
	 * Cliente para operaciones del ws NominasPagosWs
	 */
	@Autowired
	@Qualifier("ClienteOperacionNomina")
	ClienteOperacionNomina clienteOperacionNomina;

	@Autowired
	ServiciosService serviciosService;

	@Autowired
	EstadoService estadoService;

	@PostMapping(value = "/listaServicios", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseBase> listaServicios(@RequestBody BaseRequestEmpresa request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		ResponseBase response = null;
		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		if (estado.isEXITO()) {
			response = serviciosService.obtenerListaServicios(estado);
		} else {
			response = new ResponseBase();
			response.setEstado(estado);
			Respuesta respuesta = new Respuesta();
			response.setRespuesta(respuesta);
		}

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(response);
	}
}
