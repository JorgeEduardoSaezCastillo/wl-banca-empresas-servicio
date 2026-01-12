package cl.bice.banca.empresas.servicio.controller;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.service.RequestEmpresasService;

/**
 * Controlador Base.
 *
 * @author Cristian Pais.
 */
public class BaseControllerEmpresa {

	@Autowired(required = true)
	private HttpServletRequest requestHttp;

	@Autowired
	PortalOrawRepository portalOrawRepository;

	@Autowired
	Properties propiedadesExterna;

	@Autowired
	RequestEmpresasService reqEmpresasService;

	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		return builder.build();
	}

	protected void cargarRequest(BaseRequestEmpresa request) {
		request.setIp(requestHttp.getRemoteAddr());
		request.setOrigenLlamada(requestHttp.getHeader("referer"));
		request.setSessionID(requestHttp.getSession().getId());
		request.setServerName(requestHttp.getServerName());
	}

}
