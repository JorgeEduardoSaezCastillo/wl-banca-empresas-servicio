package cl.bice.banca.empresas.servicio.service;

import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.request.mobisigner.ConsultaEnrolmientoRequest;
import cl.bice.banca.empresas.servicio.model.request.mobisigner.TransactionRequest;
import cl.bice.banca.empresas.servicio.model.request.mobisigner.ValidarEstadoOperacionResponse;
import cl.bice.banca.empresas.servicio.model.request.mobisigner.ValidarStatusOperacionRequest;
import cl.bice.banca.empresas.servicio.model.response.mobisigner.EnrolamientoResponse;
import cl.bice.banca.empresas.servicio.model.response.mobisigner.TransactionResponse;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.util.BancaUtil;
import cl.bice.banca.empresas.servicio.util.FormateadorUtil;

/**
 * Cliente para consultar el servicio mobisigner.
 * 
 * @author Cristian Pais
 *
 */
@Service
public class MobisignerService {
	
	@Autowired
	private PortalOrawRepository portalOrawRepository;
	
	/**
	 * Rest Template encargado de realizar las llamadas.
	 */
	private final RestTemplate restTemplate;

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MobisignerService.class);

	/**
	 * Nombre de la clave que contiene el apikey en la cabecera cada vez que se
	 * invoca a un metodo del servicio mobisigner.
	 */
	private static final String MOBISIGNER_APIKEY_HEADER_KEY = "Authorization";
	/**
	 * Nombre del par&aacute;metro que contiene el apikey en el archivo properties.
	 */
	private static final String PARAM_SERVICIOS_MOBISIGNER_API_KEY = "servicios.mobisigner.apikey";

	/**
	 * Propiedades Externas.
	 */
	@Autowired
	private Properties propiedadesExterna;

	/**
	 * Constructor de la clase.
	 * 
	 * @param rest
	 */
	public MobisignerService(RestTemplate rest) {
		this.restTemplate = rest;
	}

	/**
	 * Este metodo invoca a UserExists de ESIGN.
	 *
	 * @param userId
	 * @return Si el cliente est&aacute; enrolado o no.
	 *         Posibles codigos de salida:
	 *         0: Cliente enrolado.
	 *         1: Rut no es valido.
	 *         2: Excepcion al consultar servicio enrolamiento en ESIGN.
	 *         3: Respuesta nula servicio enrolamiento en ESIGN.
	 *         4: Error general en metodo de cosulta de enrolamiento.
	 *         5: Usuario excepcionado, no se consultara si se encuentra enrolado en ESIGN.
	 *         201: Usuario no puede firmar.
	 *         202: Usuarion no existe.
	 * @throws BancaEmpresasException
	 */
	public EnrolamientoResponse consultaClienteEnrolado(String userId) {
		LOGGER.info("userId [{}]", userId);

		EnrolamientoResponse respClienteEnrolado = null;
		try {

			int codRespuesta = verificarExcepcionConsultaEnrolamiento(userId);
			LOGGER.info("codRespuesta: [{}]", codRespuesta);

			if (codRespuesta == Constantes.CODIGO_RESP_NO_CONS_ENROLAMIENTO) {
				respClienteEnrolado = generarRespuesta(Constantes.GLS_USUARIO_NO_VALIDA_ENROLAMIENTO,
						Constantes.USUARIO_NO_VALIDA_ENROLAMIENTO);
				LOGGER.info("respClienteEnrolado [{}]", respClienteEnrolado);
				return respClienteEnrolado;
			}

			ConsultaEnrolmientoRequest consultaEnrolamientoRequest = new ConsultaEnrolmientoRequest();
			consultaEnrolamientoRequest.setUserId(FormateadorUtil.rutSinCero(userId));

			String consultaEnrolamientoRequestJson = BancaUtil.objectToJson(consultaEnrolamientoRequest);
			LOGGER.info("consultaEnrolamientoRequestJson [{}]",
					consultaEnrolamientoRequestJson);

			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set(MOBISIGNER_APIKEY_HEADER_KEY,
					propiedadesExterna.getProperty(PARAM_SERVICIOS_MOBISIGNER_API_KEY));
			HttpEntity<ConsultaEnrolmientoRequest> request = new HttpEntity<>(consultaEnrolamientoRequest,
					requestHeaders);

			URI uri = URI.create(propiedadesExterna.getProperty("servicios.url.mobisigner.users.exists"));
			respClienteEnrolado = restTemplate.postForObject(uri, request, EnrolamientoResponse.class);

			String respClienteEnroladoJson = BancaUtil.objectToJson(respClienteEnrolado);
			LOGGER.info("respClienteEnroladoJson [{}]", respClienteEnroladoJson);

			if (null == respClienteEnrolado)
				throw new BancaEmpresasException();

		} catch (RestClientException e) {
			respClienteEnrolado = generarRespuesta(e.getMessage(), Constantes.ERROR_CONSULTA_SRV_ENROLAMIENTO);
			LOGGER.error("RestClientException: respClienteEnrolado [{}]", respClienteEnrolado);
			LOGGER.error("RestClientException: e [{}]", e);
		} catch (BancaEmpresasException e) {
			respClienteEnrolado = generarRespuesta(e.getMessage(), Constantes.ERROR_RESPUESTA_SRV_ENROLAMIENTO);
			LOGGER.error("BancaEmpresasException: respClienteEnrolado [{}]", respClienteEnrolado);
			LOGGER.error("BancaEmpresasException: e [{}]", e);
		} catch (Exception e) {
			respClienteEnrolado = generarRespuesta(e.getMessage(), Constantes.ERROR_CONSULTA_ENROLAMIENTO);
			LOGGER.error("Exception: respClienteEnrolado [{}]", respClienteEnrolado);
			LOGGER.error("Exception: e [{}]", e);
		} finally {
			LOGGER.info("finally: respClienteEnrolado [{}]", respClienteEnrolado);
			return respClienteEnrolado;
		} 

		}

	/**
	 * Entrega objeto con respuesta. 
	 * @param glsEstado
	 * @param codEstado
	 * @return respClienteEnrolado que contiene codigo y glosa de respuesta.
	 */
	private EnrolamientoResponse generarRespuesta(String glsEstado, int codEstado) {
		EnrolamientoResponse respClienteEnrolado;
		respClienteEnrolado = new EnrolamientoResponse();
		respClienteEnrolado.setStatusCode(codEstado);
		respClienteEnrolado.setStatusDescription(glsEstado);
		return respClienteEnrolado;
	}

	/**
	 * Realiza la llamada a sp que valida si usuario se encuentra excepcionado para
	 * no consultar su enrolamiento en ESIGN.
	 * 
	 * @param userId
	 * @return
	 * @throws BancaEmpresasException
	 */
	private int verificarExcepcionConsultaEnrolamiento(String userId)
			throws BancaEmpresasException {

		int codRespuesta;
		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.VAL_EXCEP_CONS_ENROLAMIENTO_SP_PI_RUT, userId);

		try {

			portalOrawRepository.consultarUsuarioExcepcionado(params);
			LOGGER.info("COD RESPUESTA [{}], GLS RESPUESTA [{}]",
					params.get(Constantes.VAL_EXCEP_CONS_ENROLAMIENTO_SP_PO_STS),
					params.get(Constantes.VAL_EXCEP_CONS_ENROLAMIENTO_SP_PO_MSG));
			codRespuesta = (int) params.get(Constantes.VAL_EXCEP_CONS_ENROLAMIENTO_SP_PO_STS);

		} catch (SQLException e) {
			LOGGER.error("SQLException: e [{}]", e);
			throw new ErrorStoredProcedureException(e);
		}

		if (codRespuesta < 0) {
			throw new NoEncontradoException();
		}

		return codRespuesta;

	}

	/**
	 * Crea el desaf&iacute;o FDA.
	 * 
	 * @param transactionReq
	 * @return Objeto con los datos de la creaci&oacute;n del desaf&iacute;o FDA
	 * @throws BancaEmpresasException
	 */
	public TransactionResponse insertTransaction(TransactionRequest transactionReq) throws BancaEmpresasException {
		LOGGER.info("MobisignerService insertTransaction");
		String transactionReqJson = BancaUtil.objectToJson(transactionReq);
		LOGGER.info("MobisignerService insertTransaction TransactionRequest: {}", transactionReqJson);

		TransactionResponse respTransaction = null;
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set(MOBISIGNER_APIKEY_HEADER_KEY,
					propiedadesExterna.getProperty(PARAM_SERVICIOS_MOBISIGNER_API_KEY));
			HttpEntity<TransactionRequest> request = new HttpEntity<>(transactionReq, requestHeaders);
			URI uri = URI.create(propiedadesExterna.getProperty("servicios.url.mobisigner.transaction.insert"));

			respTransaction = restTemplate.postForObject(uri, request, TransactionResponse.class);

			String respTransactionJson = BancaUtil.objectToJson(respTransaction);
			LOGGER.debug("MobisignerService insertTransaction TransactionResponse {}", respTransactionJson);

			if (null == respTransaction || 0 != respTransaction.getStatusCode())
				throw new BancaEmpresasException();

		} catch (RestClientException e) {
			LOGGER.error("Error insertTransaction MobisignerService: {}", e);
			throw new BancaEmpresasException();
		}

		return respTransaction;
	}

	/**
	 * Valida el estado del desaf&iacute;o FDA.
	 * 
	 * @param validarStatusOperacionRequest
	 * @return Estado de la firma (puede ser: completada, pendiente, rechazada o
	 *         caducada).
	 * @throws BancaEmpresasException
	 */
	public ValidarEstadoOperacionResponse validarStatusOperacion(
			ValidarStatusOperacionRequest validarStatusOperacionRequest) throws BancaEmpresasException {
		LOGGER.info("MobisignerService validarStatusOperacion");
		String validarStatusOperacionRequestJson = BancaUtil.objectToJson(validarStatusOperacionRequest);
		LOGGER.info("MobisignerService validarStatusOperacion ValidarStatusOperacionRequest: {}",
				validarStatusOperacionRequestJson);

		ValidarEstadoOperacionResponse respEstadoOperacion = null;
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set(MOBISIGNER_APIKEY_HEADER_KEY,
					propiedadesExterna.getProperty(PARAM_SERVICIOS_MOBISIGNER_API_KEY));
			HttpEntity<ValidarStatusOperacionRequest> request = new HttpEntity<>(validarStatusOperacionRequest,
					requestHeaders);
			URI uri = URI.create(propiedadesExterna.getProperty("servicios.url.mobisigner.transaction.status"));
			respEstadoOperacion = restTemplate.postForObject(uri, request, ValidarEstadoOperacionResponse.class);

			String respEstadoOperacionJson = BancaUtil.objectToJson(respEstadoOperacion);
			LOGGER.debug("MobisignerService validarStatusOperacion ValidarEstadoOperacionResponse {}",
					respEstadoOperacionJson);

			if (null == respEstadoOperacion || 0 != respEstadoOperacion.getStatusCode())
				throw new BancaEmpresasException();

		} catch (RestClientException e) {
			LOGGER.error("Error validarStatusOperación MobisignerService: {}", e);
			throw new BancaEmpresasException();
		}

		return respEstadoOperacion;
	}

}
