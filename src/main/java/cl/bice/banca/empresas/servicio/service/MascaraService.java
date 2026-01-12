package cl.bice.banca.empresas.servicio.service;

import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.response.ms.mascara.EnmascararSalidaTO;
import org.codehaus.jackson.map.ObjectMapper;
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
 * Clase para gestionar llamada al microservicio de mascara
 * 
 */
@Service
public class MascaraService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MascaraService.class);
	private final Properties propiedadesExterna;
	private final RestTemplate restTemplate;
	private final ValoresCampoOperacionesService valoresCampoOperacionesService;
	
	@Autowired
	public MascaraService(RestTemplate restTemplate, Properties propiedadesExterna, ValoresCampoOperacionesService valoresCampoOperacionesService ) {
		this.valoresCampoOperacionesService = valoresCampoOperacionesService;
		this.restTemplate = restTemplate;
		this.propiedadesExterna = propiedadesExterna;
	}
	

	/**
	 * Ejecuta llamada a microservicio de mascara para encriptar el parametro recibido <br>
	 * srv-mascara
	 * @param Dato a encriptar
	 * @return Dato encriptado
	 */
	@Retryable(value = {}, maxAttempts = 3, backoff = @Backoff(delay = 2000))
	public String encriptarDato(String dato, String rutApoderado) throws BancaEmpresasException {
		String datoEncriptado = "";
		String aplicaReintentoForzado = "";
		String urlServicioOk = "";
		String urlServicioReintento = "";
		String urlServicioFinal = "";
		int retryCount = 0;
		LOGGER.info("dato para encriptar: [{}] rutApoderado [{}]", dato, rutApoderado);

		try {
			aplicaReintentoForzado = propiedadesExterna.getProperty("flag.error.reintento");
			urlServicioOk = propiedadesExterna.getProperty("url.ms.mascara");
			urlServicioReintento = propiedadesExterna.getProperty("url.ms.mascara.reintento");
			urlServicioFinal = "S".equals(aplicaReintentoForzado) ? urlServicioReintento : urlServicioOk;
			retryCount = RetrySynchronizationManager.getContext().getRetryCount();
			
			LOGGER.info("aplicaReintentoForzado: [{}] urlServicioOk [{}] urlServicioReintento [{}] urlServicioFinal [{}]",
					aplicaReintentoForzado, urlServicioOk, urlServicioReintento, urlServicioFinal);	
			
			if ("S".equals(aplicaReintentoForzado) && retryCount == 2) {
				urlServicioFinal = urlServicioOk;
			}
			
			LOGGER.info("dato: [{}] rutUsuario [{}] retryCount [{}] urlServicioAutenticacion [{}]",
					dato, rutApoderado, retryCount, urlServicioFinal);

			ObjectMapper mapper = new ObjectMapper();
			String requestJson = mapper.writeValueAsString(dato);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(requestJson, headers);
			
			ResponseEntity<EnmascararSalidaTO> response = restTemplate.exchange(urlServicioFinal, HttpMethod.POST, request, EnmascararSalidaTO.class);

			if (response.getStatusCodeValue() == HttpStatus.OK.value()) {
				EnmascararSalidaTO enmascararSalidaTO = response.getBody();
				if (enmascararSalidaTO.getCodigoSalida().equals("200")) {
					datoEncriptado = enmascararSalidaTO.getMascara();
					LOGGER.info("ms mascara ejecutado correctamente, datoInput [{}] dato encriptado [{}] rutApoderado [{}]", dato, datoEncriptado, rutApoderado);
				} 
			}
			else {
				LOGGER.info("ms mascara ejecutado con error, datoInput [{}] dato encriptado [{}] rutApoderado [{}] response.getStatusCodeValue [{}]", 
						dato, datoEncriptado, rutApoderado, response.getStatusCodeValue());
			}

		} catch (Exception ex) {
			LOGGER.error("ms mascara Exception - datoInput [{}] rutApoderado [{}] error: [{}]", dato, rutApoderado, ex.getMessage());
			throw new BancaEmpresasException(ex.getMessage());
		}
		
		return datoEncriptado;
	} 
	



	public String getDataRequest(MapOperaciones mapOperaciones, String numOperProg) {
		String dataRequest = "";
		try {
			dataRequest = Long.parseLong(valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, numOperProg, "NroCuentaCargo", true)) + "," + numOperProg;
		}
		catch (Exception ne) {
			dataRequest = "";
		}
		
		return dataRequest;
	}
	
	

}
