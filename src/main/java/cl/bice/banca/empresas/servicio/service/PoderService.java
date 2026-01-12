package cl.bice.banca.empresas.servicio.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.operaciones.RespuestaAprob;
import cl.bice.banca.empresas.servicio.model.request.ms.poderes.PoderRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.response.ms.poderes.PoderResponse;
import cl.bice.banca.empresas.servicio.util.TefMasivaUtil;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


/**
 * Clase para gestionar validacion de poderes en Fisban
 * 
 * @author Marco Bello
 *
 */
@Service
public class PoderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PoderService.class);


	@Autowired
	Properties propiedadesExterna;

	@Autowired
	ValoresCampoOperacionesService valoresCampoOperacionesService;
	
	@Autowired
	OperacionesEmpresaPortalService operacionesEmpresaPortalService;

	@Autowired
	RestTemplate restTemplate;
	
	/**
	 * Inicializa el objeto de la clase PoderRequest.
	 * 
	 * @param aprobarOperacionesPortalRequest
	 * @param nroOperacion
	 * @return
	 * @throws RequestInvalidoException 
	 */
	public PoderRequest getPoderRequest(AprobarOperacionesPortalRequest aprobarOperacionesPortalRequest,
			String numOperProg, MapOperaciones mapOperaciones) throws RequestInvalidoException {
		
		String numCuentaCargo;
		String monto;
		try {
			numCuentaCargo = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones,
					numOperProg, "NroCuentaCargo", true);
			monto = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, numOperProg,
					"Monto", true);
		} catch (NoEncontradoException e) {
			throw new RequestInvalidoException(e);
		}

		if (null != numCuentaCargo) {
			numCuentaCargo = numCuentaCargo.replaceFirst("^0+(?!$)", "");
		}
		
		
		PoderRequest poderRequest = new PoderRequest();
		poderRequest.setCodigoMoneda(getCodMonedaIsoOperProg(Integer.parseInt(aprobarOperacionesPortalRequest.getCodigoServicio()))); 
		poderRequest.setCodigoServicio(TefMasivaUtil.isOperacionSOMA(mapOperaciones, numOperProg) ? Constantes.SERVICIO_OPERACION_EMPRESA_COD_LBTR_SOMA : 
			aprobarOperacionesPortalRequest.getCodigoServicio());
		poderRequest.setDatoFirma("");
		poderRequest.setCodigoFacultad("");
		poderRequest.setMonto(monto);
		poderRequest.setNumOperProg(numOperProg);
		poderRequest.setNumeroCuenta(numCuentaCargo);
		poderRequest.setRutApoderado(aprobarOperacionesPortalRequest.getRutApoderado());
		poderRequest.setRutCliente(aprobarOperacionesPortalRequest.getRutEmpresa());
		
		return poderRequest;
	}	
	

	/**
	 * Realiza validacion de Poderes en Legal Suite.
	 * Se ejecuta microservicio ms-poderes-validacion
	 * 
	 * @param ValidaPoderRequest
	 * @param MapOperaciones
	 * @return 50: FIRMA COMPLETA <br>
	 *         40: FIRMA PARCIAL <br>
	 *         30: FIRMA NO REALIZADA
	 */
	public RespuestaAprob validaPoder(PoderRequest request, String maskedData) {
		LOGGER.info("Inicio validacion de poderes desde ms - rutEmpresa[{}] rutApoderado[{}] numOperProg[{}] "
				+ " codigoServicio[{}] monto[{}] numeroCuenta[{}] codMoneda [{}] ",
				request.getRutCliente(), request.getRutApoderado(), request.getNumOperProg(), request.getCodigoServicio(),
				request.getMonto(), request.getNumeroCuenta(), request.getCodigoMoneda());
		
		RespuestaAprob respuestaAprob = this.evaluarRespuestaPoder(this.execValidacionPoder(request, maskedData));
		
		if (respuestaAprob != null) {
			LOGGER.info("Fin validacion de poderes desde ms (ejecucion ok) - rutEmpresa[{}] rutApoderado[{}] numOperProg[{}] monto[{}] numeroCuenta[{}] codEstado[{}] glosa[{}] ",
				request.getRutCliente(), request.getRutApoderado(), request.getNumOperProg(), request.getMonto(), request.getNumeroCuenta(),
				respuestaAprob.getCodEstado(), respuestaAprob.getGlsMensaje());
		}
		else {
			respuestaAprob = new RespuestaAprob();
			respuestaAprob.setCodEstado(30);
			respuestaAprob.setGlsMensaje(Constantes.GLS_ERROR_SRV_FISBAN);
			LOGGER.info("Fin validacion de poderes desde ms (ejecucion con error) - rutEmpresa[{}] rutApoderado[{}] numOperProg[{}] monto[{}] numeroCuenta[{}] codEstado[{}] glosa[{}] ",
					request.getRutCliente(), request.getRutApoderado(), request.getNumOperProg(), request.getMonto(), request.getNumeroCuenta(),
					"30", Constantes.GLS_ERROR_SRV_FISBAN);
		}

		return respuestaAprob;
	}
	
	

	/**
	 * Ejecuta llamada a microservicio de validacion de poderes <br>
	 * ms-poderes-validacion
	 * 
	 * @param MovCartolaRequest
	 * @return PoderResponse
	 */	
	private PoderResponse execValidacionPoder(PoderRequest poderRequest, String maskedData) {
		PoderResponse poderResponse = null;
		
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("x-rut-cliente", poderRequest.getRutCliente());
			headers.set("x-rut-usuario", poderRequest.getRutApoderado());
			
			Map<String, String> params = new HashMap<>();
		    params.put("maskedData", maskedData);
		    params.put("codigoServicio", poderRequest.getCodigoServicio());
		    params.put("monto", poderRequest.getMonto());
		    params.put("codMoneda", poderRequest.getCodigoMoneda());
		    
	        ResponseEntity<PoderResponse> entityPoderResponse = restTemplate.exchange(
	        		propiedadesExterna.getProperty("url.ms.poderes"),
	        		HttpMethod.GET,
	        		new HttpEntity<String>(headers),
	        		PoderResponse.class,
	        		params);
			
			if (entityPoderResponse != null && entityPoderResponse.getStatusCodeValue() == HttpStatus.OK.value() ) {
				LOGGER.info("Respuesta ms poderes -  rutCliente [{}] rutApoderado [{}] numOperProg [{}] monto [{}] cuenta [{}] codigoSalida [{}] " +
						"glosaSalida [{}] httpStatus [{}]",
						poderRequest.getRutCliente(), poderRequest.getRutApoderado(), poderRequest.getNumOperProg(),
						poderRequest.getMonto(), poderRequest.getNumeroCuenta(),
						entityPoderResponse.getBody().getCodigo(), entityPoderResponse.getBody().getGlosa(), entityPoderResponse.getStatusCodeValue());
				poderResponse = entityPoderResponse.getBody();
			}

		} 
		catch (HttpClientErrorException ex1) {
			LOGGER.error("[HttpClientErrorException] rutCliente [{}] rutApoderado [{}] numOperProg [{}] cuenta [{}] error[{}] ResponseBodyAsString [{}] StatusCode [{}]", 
					poderRequest.getRutCliente(), poderRequest.getRutApoderado(), poderRequest.getNumOperProg(), poderRequest.getNumeroCuenta(), 
					ex1.getMessage(), ex1.getResponseBodyAsString(), ex1.getStatusCode());
		}
		catch (RestClientException ex2) {
			LOGGER.error("[RestClientException] rutCliente [{}] rutApoderado [{}] numOperProg [{}] cuenta [{}] error[{}] ", 
					poderRequest.getRutCliente(), poderRequest.getRutApoderado(), poderRequest.getNumOperProg(), poderRequest.getNumeroCuenta(), ex2.getMessage());
		}
		catch (Exception ex3) {
			LOGGER.error("[Exception] rutCliente [{}] rutApoderado [{}] numOperProg [{}] cuenta [{}] error[{}] ", 
					poderRequest.getRutCliente(), poderRequest.getRutApoderado(), poderRequest.getNumOperProg(), poderRequest.getNumeroCuenta(), ex3.getMessage());
		}
		
		return poderResponse;
	}
	
	
	/**
	 * Realiza la evaluacion de la respuesta de Poderes <br>
	 * Estados y Glosa que entrega el microservicio <br>
	 * codigoSalida | glosaSalida: <br> 
	 * 50 | Firma Completa  <br>
	 * 40 | Firma Parcial   <br>
	 * 30 | Firma ya Existe <br>
	 * 20 | Firma Invalida  <br>
	 * 10 | Error Firmando  <br>
	 * 00 | Error Generico  <br>
	 *  
	 * @param MovCartolaResponse
	 * @return
	 */
	private RespuestaAprob evaluarRespuestaPoder(PoderResponse poderResponse) {
		int respuesta = 30;
		RespuestaAprob respuestaAprob = null;
		
		try {
			if (poderResponse != null) {
				respuesta = poderResponse.getCodigo() != null && TefMasivaUtil.esNumero(poderResponse.getCodigo()) ? Integer.parseInt(poderResponse.getCodigo()) : 0; 
				if (respuesta != 30 && respuesta != 40 && respuesta != 50 ) {
					respuesta = 30;
				}
				respuestaAprob = new RespuestaAprob();
				respuestaAprob.setCodEstado(respuesta);
				respuestaAprob.setGlsMensaje(poderResponse.getGlosa());
			}
		}
		catch (Exception e) {
			LOGGER.error("Exception - Error: [{}]",e.getMessage());
		}
		
		return respuestaAprob;
	}
	
	
	private String getCodMonedaIsoOperProg(int codServicio) {
		String codMoneda = "";
		if (codServicio == 935) {
			codMoneda = "CLP";
		}
		
		return codMoneda;
		
	}
	

	

}
