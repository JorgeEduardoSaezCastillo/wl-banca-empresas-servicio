package cl.bice.banca.empresas.servicio.soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.util.UtilPrintLog;
import cl.bice.tef.ws.ObjectFactory;
import cl.bice.tef.ws.TefPort;
import cl.bice.tef.ws.TefPortService;
import cl.bice.tef.ws.Tpag2000RequestType;
import cl.bice.tef.ws.Tpag2000ResponseType;

/**
 * Cliente para consultar el servicio TEF.
 * 
 * @author Fibacache
 *
 */
@Service("ClienteTefWs")
public class ClienteTefWs {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteOperacionNomina.class);

	private URL url;
	TefPortService service;
	private TefPort impl;
	ObjectFactory objectFactory;

	@Autowired
	Properties propiedadesExterna;

	/**
	 * Inicializa el cliente del servicio.
	 */
	private void firstcall() {
		try {
			if (null == url) {
				this.url = new URL(propiedadesExterna.getProperty("servicios.url.empresa.tef"));
				service = new TefPortService(url);
				impl = service.getTefPortPort();
				objectFactory = new ObjectFactory();
				Map<String, Object> requestContext = ((BindingProvider) impl).getRequestContext();
				requestContext.put("com.sun.xml.internal.ws.request.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.empresa.tef.request-timeout")));
				requestContext.put("com.sun.xml.internal.ws.connect.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.empresa.tef.connect-timeout")));
			}
		} catch (MalformedURLException e) {
			LOGGER.error("Exception {} {} ", ClienteTefWs.class.getSimpleName(), e);
		}
	}

	/**
	 * Valida poderes contra TPAG2000
	 * 
	 * @param tpag2000Request
	 * @return
	 * @throws BancaEmpresasException
	 */
	public Tpag2000ResponseType validarTPAG2000(Tpag2000RequestType tpag2000Request) throws BancaEmpresasException {
		try {
			firstcall();

			UtilPrintLog.printXML(tpag2000Request, Tpag2000RequestType.class, "REQUEST Tpag2000RequestType");

			Tpag2000ResponseType response = impl.tpag2000(tpag2000Request);

			if (null == response) {
				throw new BancaEmpresasException("TPAG2000 respuesta nula");
			}

			UtilPrintLog.printXML(response, Tpag2000ResponseType.class, "RESPONSE Tpag2000ResponseType");

			return response;

		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteTefWs validarTPAG2000: {}", e);
			throw new BancaEmpresasException();
		}
	}

}
