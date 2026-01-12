package cl.bice.banca.empresas.servicio.soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cl.bice.nominas.ws.NominasPagosWs;
import cl.bice.nominas.ws.NominasPagosWsService;
import cl.bice.nominas.ws.ObjectFactory;

/**
 * Superclase del cliente del servicio de nominas.
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class BaseClienteServicioNominas {
	private static final Logger BASE_LOGGER = LoggerFactory.getLogger(BaseClienteServicioNominas.class);
	protected URL url;
	protected NominasPagosWsService service;
	protected NominasPagosWs impl;
	protected ObjectFactory objectFactory;

	@Autowired
	protected Properties propiedadesExterna;

	/**
	 * Inicializa la clase.
	 */
	protected void firstcall() {
		try {
			if (null == url) {
				this.url = new URL(propiedadesExterna.getProperty("servicios.url.nominas.pagos"));
				service = new NominasPagosWsService(url);
				impl = service.getNominasPagosWsPort();
				objectFactory = new ObjectFactory();
				Map<String, Object> requestContext = ((BindingProvider) impl).getRequestContext();
				requestContext.put("com.sun.xml.internal.ws.request.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.nominas.request-timeout")));
				requestContext.put("com.sun.xml.internal.ws.connect.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.nominas.connect-timeout")));
			}
		} catch (MalformedURLException e) {
			BASE_LOGGER.error("Exception {} {} ", BaseClienteServicioNominas.class.getSimpleName(), e);
		}
	}
}
