package cl.bice.banca.empresas.servicio.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.response.ms.autenticacionoper.AutenticacionSalidaTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetrySynchronizationManager;


/**
 * Clase para gestionar llamada al microservicio de autenticacion
 * 
 */
@Service
public class AutorizarService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutorizarService.class);
	private final Properties propiedadesExterna;
	private final RestTemplate restTemplate;


	@Autowired
	public AutorizarService(RestTemplate restTemplate, Properties propiedadesExterna) {
		this.restTemplate = restTemplate;
		this.propiedadesExterna = propiedadesExterna;
	}
	
	
	@Retryable(value = {}, maxAttempts = 3, backoff = @Backoff(delay = 2000))
	public AutenticacionSalidaTO execAutorizar(String numOperacion, String rutCliente, String rutUsuario,
			String tipoOperacion) throws BancaEmpresasException  {
		AutenticacionSalidaTO autenticacionSalidaTO = null;
		String aplicaReintentoForzado = "";
		String urlServicioOk = "";
		String urlServicioReintento = "";
		String urlServicioFinal = "";
		int retryCount = 0;

		try {
			aplicaReintentoForzado = propiedadesExterna.getProperty("flag.error.reintento");
			urlServicioOk = propiedadesExterna.getProperty("url.ms.autorizar.operaciones");
			urlServicioReintento = propiedadesExterna.getProperty("url.ms.autorizar.reintento");
			urlServicioFinal = "S".equals(aplicaReintentoForzado) ? urlServicioReintento : urlServicioOk;
			retryCount = RetrySynchronizationManager.getContext().getRetryCount();
			
			LOGGER.info("aplicaReintentoForzado: [{}] urlServicioOk [{}] urlServicioReintento [{}] urlServicioFinal [{}]",
					aplicaReintentoForzado, urlServicioOk, urlServicioReintento, urlServicioFinal);	
			
			if ("S".equals(aplicaReintentoForzado) && retryCount == 2) {
				urlServicioFinal = urlServicioOk;
			}
			
			LOGGER.info("numOperProg: [{}] rutCliente [{}] rutUsuario [{}] tipoOperacion [{}] retryCount [{}] urlServicioAutenticacion [{}]",
					numOperacion, rutCliente, rutUsuario, tipoOperacion, retryCount, urlServicioFinal);			
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("x-rut-cliente", rutCliente);
			headers.set("x-rut-usuario", rutUsuario);

			Map<String, String> params = new HashMap<>();
			params.put("idOperacion", numOperacion);
			params.put("tipoOperacion", tipoOperacion);

			ResponseEntity<AutenticacionSalidaTO> response = restTemplate.exchange(
					urlServicioFinal, HttpMethod.GET,
					new HttpEntity<String>(headers), AutenticacionSalidaTO.class, params);

			LOGGER.info("ms-autenticacion-operaciones response.getStatusCodeValue [{}] numOperProg [{}] rutCliente [{}] rutUsuario [{}]",
					response.getStatusCodeValue(), numOperacion, rutCliente, rutUsuario);


			if (response.getStatusCodeValue() == HttpStatus.OK.value()) {
				autenticacionSalidaTO = response.getBody();
				LOGGER.info(
						"ms-autenticacion-operaciones ejecutado correctamente, rutCliente [{}] rutUsuario [{}] tipoDesafio [{}] numOperacion [{}]",
						rutCliente, rutUsuario, autenticacionSalidaTO.getTipoDesafio(), numOperacion);
			}

		} catch (Exception ex) {
			autenticacionSalidaTO = new AutenticacionSalidaTO();
			autenticacionSalidaTO.setGlsError(Constantes.GLS_ERROR_SRV_AUTENTICACION);
			LOGGER.error(
					"ms-autenticacion-operaciones Exception numOperacion: [{}] rutCliente [{}] rutUsuario [{}] tipoOperacion [{}] error: [{}] nombreClase [{}]",
					numOperacion, rutCliente, rutUsuario, tipoOperacion, ex.getMessage(), ex.getClass());
			throw new BancaEmpresasException(ex.getMessage());
		}

		return autenticacionSalidaTO;
	}
	

}
