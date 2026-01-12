package cl.bice.banca.empresas.servicio.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.nominas.NominaEnLinea;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.request.ValidarDesafiosRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;

/**
 * Clase con métodos que realizan validaciones.
 * 
 * @author Cristian Pais (TINET)
 *
 */
@Service
public class ValidacionService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidacionService.class);

	@Autowired
	EmpresasService empresasService;

	@Autowired
	OperacionesEmpresaService operacionesEmpresaService;

	@Autowired
	ValoresCampoOperacionesService valoresCampoOperacionesService;

	@Autowired
	CuentaService cuentaService;

	@Autowired
	PortalOrawRepository portalOrawRepository;
	/**
	 * Propiedades Externas.
	 */
	@Autowired
	Properties propiedadesExterna;

	@Autowired
	EstadoService estadoService;

	/**
	 * Para una detarminada operación: 1) Valida un numero de cuenta pertenece a un
	 * rut usuario y rut empresa. 2) Valida si el rut usuario pertenece a rut
	 * empresa. 3) Valida si el nro de operación recibida pertenecen a rut empresa y
	 * c&oacute;digo de servicio.
	 * 
	 * @param requestBase
	 * @param codServicio
	 * @param nroOperacion
	 * @return true en caso de que se cumplan las 3 validaciones
	 * @throws BancaEmpresasException
	 */
	public boolean isPertenenciaValida(BaseRequestEmpresa requestBase, String codServicio, String nroOperacion,
			MapOperaciones mapOperaciones, boolean isTipoOpAprobar) throws BancaEmpresasException {
		LOGGER.info("ValidacionService isPertenenciaValida");
		String nombreCampoNroCuentaCargo;
		if (codServicio.equals(Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_DIVIDENDO_ACCIONES)
				|| codServicio.equals(Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_PROVEEDORES)
				|| codServicio.equals(Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES))
			nombreCampoNroCuentaCargo = "NomDifNroCuentaCargo";
		else if (Constantes.CODIGO_SERVICIO_SPOTWEB.equals(codServicio))
			nombreCampoNroCuentaCargo = "SpotWebNroCuentaCargo";
		else if (Constantes.CODIGO_SERVICIO_TEF_MX.equals(codServicio))
			nombreCampoNroCuentaCargo = "TefMxNroCuentaCargo";
		else
			nombreCampoNroCuentaCargo = "NroCuentaCargo";

		String nroCuentaCargo = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, nroOperacion,
				nombreCampoNroCuentaCargo, isTipoOpAprobar);

		if (null != nroCuentaCargo) {
			nroCuentaCargo = nroCuentaCargo.trim().replaceFirst("^0+(?!$)", "");// Le quito los ceros a la izquierda
		} else {
			LOGGER.error("ValidacionService isPertenenciaValida: ERROR nroCuentaCargo == null");
			throw new RequestInvalidoException();
		}

		return isCumplePertenencias(nroOperacion, nroCuentaCargo, requestBase.getRut(), requestBase.getRutEmpresa(),
				codServicio);
	}

	/**
	 * Checkea si una operaci&oacute;n de tipo nominas cumple con las pertenencias.
	 * 
	 * @param requestBase
	 * @param codServicio
	 * @param nroOperacion
	 * @param nroCuentaCargo
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean isPertenenciaNominaValida(BaseRequestEmpresa requestBase, String codServicio, String nroOperacion,
			String nroCuentaCargo) throws BancaEmpresasException {
		LOGGER.info("ValidacionService isPertenenciaNominaValida");
		if (null != nroCuentaCargo) {
			nroCuentaCargo = nroCuentaCargo.trim().replaceFirst("^0+(?!$)", "");// Le quito los ceros a la izquierda
		} else {
			LOGGER.info("ValidacionService isPertenenciaNominaValida: ERROR nroCuentaCargo == null");
			throw new RequestInvalidoException();
		}

		return isCumplePertenencias(nroOperacion, nroCuentaCargo, requestBase.getRut(), requestBase.getRutEmpresa(),
				codServicio);
	}

	/**
	 * Checkea si una operaci&oacute;n cumple con las pertenencias.
	 * 
	 * @param nroOperacion
	 * @param nroCuentaCargo
	 * @param rut
	 * @param rutEmpresa
	 * @param codServicio
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean isCumplePertenencias(String nroOperacion, String nroCuentaCargo, String rut, String rutEmpresa,
			String codServicio) throws BancaEmpresasException {
		LOGGER.info(
				"ValidacionService isCumplePertenencias: nroOperacion[{}] nroCuentaCargo[{}] rut[{}] rutEmpresa[{}] codServicio[{}]",
				nroOperacion, nroCuentaCargo, rut, rutEmpresa, codServicio);
		boolean resultado = true;

		resultado = cuentaService.validaPertenenciaCuentaRutUsuarioRutEmpresa(nroCuentaCargo, rut, rutEmpresa);
		LOGGER.info(
				"ValidacionService isCumplePertenencias: valida pertenencia cuenta cargo - rut usuario - rut empresa: resultado[{}]",
				resultado);

		if (resultado) {
			resultado = empresasService.perteneceUsuarioEmpresa(rut, rutEmpresa);
			LOGGER.info(
					"ValidacionService isCumplePertenencias: valida pertenencia rut usuario - rut empresa: resultado[{}]",
					resultado);
		}

		if (resultado) {
			resultado = operacionesEmpresaService.isPertenenciaCorrectaNumOperacionRutEmpresa(nroOperacion, codServicio,
					rutEmpresa);
			LOGGER.info(
					"ValidacionService isCumplePertenencias: valida pertenencia nro operacion - rut empresa: resultado[{}]",
					resultado);
		}

		LOGGER.info("ValidacionService isCumplePertenencias: resultado[{}]", resultado);
		return resultado;
	}

	/**
	 * Checkea si una lista de operaciones de tipo nomina cumple con las
	 * pertenencias.
	 * 
	 * @param requestBase
	 * @param codServicio
	 * @param listNominas
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean isPertenenciaNominaValida(BaseRequestEmpresa requestBase, String codServicio,
			List<NominaEnLinea> listNominas) throws BancaEmpresasException {
		LOGGER.info("ValidacionService isPertenenciaNominaValida");
		boolean resultado = true;

		for (NominaEnLinea nomina : listNominas) {
			if (!isPertenenciaNominaValida(requestBase, codServicio, String.valueOf(nomina.getNumOperProg()),
					String.valueOf(nomina.getCuentaCargo()))) {
				resultado = false;
				break;
			}
		}
		LOGGER.info("ValidacionService isPertenenciaNominaValida: resultado[{}]", resultado);
		return resultado;
	}

	/**
	 * Haca la validación de pertenencias para una lista de operaciones.
	 * 
	 * @param requestBase
	 * @param codServicio
	 * @param listOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean isPertenenciaValida(BaseRequestEmpresa requestBase, String codServicio, List<String> listOperaciones,
			MapOperaciones mapOperaciones, boolean isTipoOpAprobar) throws BancaEmpresasException {
		LOGGER.info("ValidacionService isPertenenciaValida");
		boolean resultado = true;

		for (String nroOperacion : listOperaciones) {
			if (!isPertenenciaValida(requestBase, codServicio, nroOperacion, mapOperaciones, isTipoOpAprobar)) {
				resultado = false;
				break;
			}
		}
		LOGGER.info("ValidacionService isPertenenciaValida: resultado[{}]", resultado);
		return resultado;
	}

	/**
	 * Verifica si la empresa tiene cargo en línea LBTR
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean empresaTieneCargoEnLineLbtr(String rutEmpresa) throws BancaEmpresasException {
		LOGGER.info("ValidacionService empresaTieneCargoEnLineLbtr: rutEmpresa[{}]", rutEmpresa);
		boolean resultado = true;

		String salida;

		Map<String, Object> params = new HashMap<>();
		params.put("V_RUT_CLI", rutEmpresa);

		try {
			portalOrawRepository.empresaTieneCargoEnLineaLbtr(params);
			salida = (String) params.get("RESULT");
		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if (salida == null || salida.equals("")) {
			throw new NoEncontradoException();
		}

		if (salida.equals("0"))
			resultado = false;

		return resultado;
	}

	/**
	 * Verifica si la empresa opera con modelo de cuenta paragua LBTR
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean empresaOperaCuentaParaguaLbtr(String rutEmpresa) throws BancaEmpresasException {
		LOGGER.info("ValidacionService empresaOperaCuentaParaguaLbtr: rutEmpresa[{}]", rutEmpresa);
		boolean resultado = true;

		String salida;

		Map<String, Object> params = new HashMap<>();
		params.put("V_RUT_CLI", rutEmpresa);

		try {
			portalOrawRepository.empresaTieneCuentaParaguaLbtr(params);
			salida = (String) params.get("RESULT");
		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if (salida == null || salida.equals("")) {
			throw new NoEncontradoException();
		}

		if (salida.equals("0"))
			resultado = false;

		return resultado;
	}

	/**
	 * Valida la clave ingresada por el usuario
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean validarClaveUsuario(ValidarDesafiosRequest objRequest, Estado estado) {
		LOGGER.info("ValidacionService validarClave rut usuario: [{}], ", objRequest.getRut());
		boolean retorno = false;
		if (estado.isEXITO()) {

			try {

				RestTemplate restTemplate = new RestTemplate();

				String urlServicio = propiedadesExterna.getProperty("servicios.url.validar.desafios.tef.mx");

				ObjectMapper mapper = new ObjectMapper();
				String requestJson = mapper.writeValueAsString(objRequest);

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				HttpEntity<String> request = new HttpEntity<>(requestJson, headers);

				ResponseEntity<Estado> response = restTemplate.exchange(urlServicio, HttpMethod.POST, request,
						Estado.class);

				if (response.getStatusCodeValue() == HttpStatus.OK.value()) {
					LOGGER.info("Servicio validación clave ejecutado correctamente. Rut usuario [{}]",
							objRequest.getRut());
					Estado estadoResp = response.getBody();
					if (estadoResp.getCodigoEstado().equals("000")) {
						LOGGER.error("Clave válida. Rut usuario: [{}]", objRequest.getRut());
						retorno = true;
					} else {
						LOGGER.error("Clave inválida. Rut usuario: [{}]", objRequest.getRut());
						estadoService.setErrorEstado(EstadoEnum.ERROR_E006_EMPRESAS_LIBERAR_OPERACIONES, estado);
					}
				}

			} catch (Exception ex) {
				LOGGER.error("[Exception] Error al validar clave rut usuario: [{}], error: [{}]", objRequest.getRut(),
						ex);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_LIBERAR_OPERACIONES, estado);
			}
		}

		return retorno;
	}
	
	/**
	 * Hace la validación de pertenencias para una lista de operaciones del tipo BiceComex.
	 * 
	 * @param requestBase
	 * @param codServicio
	 * @param listOperaciones
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean isPertenenciaValidaBiceComex(BaseRequestEmpresa requestBase, String codServicio, List<String> listOperaciones, boolean isTipoOpAprobar) throws BancaEmpresasException {
		LOGGER.info("ValidacionService isPertenenciaValidaBiceComex");
		boolean resultado = true;

		for (String nroOperacion : listOperaciones) {
			if (!isCumplePertenenciasBiceComex(nroOperacion, requestBase.getRut(), requestBase.getRutEmpresa(), codServicio)) {
				resultado = false;
				break;
			}
		}
		LOGGER.info("ValidacionService isPertenenciaValidaBiceComex: resultado[{}]", resultado);
		return resultado;
	}
	
	/**
	 * Checkea si una operación cumple con las pertenencias.
	 * 
	 * @param nroOperacion
	 * @param rut
	 * @param rutEmpresa
	 * @param codServicio
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean isCumplePertenenciasBiceComex(String nroOperacion, String rut, String rutEmpresa,
			String codServicio) throws BancaEmpresasException {
		LOGGER.info(
				"ValidacionService isCumplePertenenciasBiceComex: nroOperacion[{}] rut[{}] rutEmpresa[{}] codServicio[{}]",
				nroOperacion, rut, rutEmpresa, codServicio);
		boolean resultado = true;

		resultado = empresasService.perteneceUsuarioEmpresa(rut, rutEmpresa);
		LOGGER.info(
				"ValidacionService isCumplePertenenciasBiceComex: valida pertenencia rut usuario - rut empresa: resultado[{}]",
				resultado);

		LOGGER.info("ValidacionService isCumplePertenenciasBiceComex: resultado[{}]", resultado);
		return resultado;
	}
}
