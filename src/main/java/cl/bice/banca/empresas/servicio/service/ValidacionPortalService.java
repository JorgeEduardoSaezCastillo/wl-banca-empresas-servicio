package cl.bice.banca.empresas.servicio.service;

import java.sql.SQLException;
import java.util.ArrayList;
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
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.request.ValidarDesafiosRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.banca.empresas.servicio.util.TefMasivaUtil;

/**
 * Clase con métodos que realizan validaciones.
 * 
 * @author Marco Bello
 *
 */

@Service
public class ValidacionPortalService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidacionPortalService.class);
	
	
	@Autowired
	public ValidacionPortalService(ValoresCampoOperacionesService valoresCampoOperacionesService, CuentaService cuentaService, 
			EmpresasService empresasService, OperacionesEmpresaService operacionesEmpresaService, PortalOrawRepository portalOrawRepository) {
		this.valoresCampoOperacionesService = valoresCampoOperacionesService;
		this.cuentaService = cuentaService;
		this.empresasService = empresasService;
		this.operacionesEmpresaService = operacionesEmpresaService;
		this.portalOrawRepository = portalOrawRepository;
	}


	@Autowired
	EstadoService estadoService;

	CuentaService cuentaService;

	OperacionesEmpresaService operacionesEmpresaService;

	ValoresCampoOperacionesService valoresCampoOperacionesService;

	PortalOrawRepository portalOrawRepository;
	
	EmpresasService empresasService;
	
	/**
	 * Propiedades Externas.
	 */
	@Autowired
	Properties propiedadesExterna;



	/**
	 * Para una detarminada operación: 
	 * 1) Valida un numero de cuenta pertenece a un rut usuario y rut empresa. 
	 * 2) Valida si el rut usuario pertenece a rut empresa. 
	 * 3) Valida si el nro de operación recibida pertenecen a rut empresa y codigo de servicio.
	 * 
	 * @param requestBase
	 * @param codServicio
	 * @param nroOperacion
	 * @return true en caso de que se cumplan las 3 validaciones
	 * @throws BancaEmpresasException
	 */
	public boolean isPertenenciaValida(String rutApoderado, String rutEmpresa, String codServicio, String nroOperacion,
			MapOperaciones mapOperaciones, boolean isTipoOpAprobar) throws BancaEmpresasException {
		LOGGER.info("ValidacionPortalService isPertenenciaValida");
		String nombreCampoNroCuentaCargo = "NroCuentaCargo";
		String nroCuentaCargo = "";
		
		try {
			nroCuentaCargo = valoresCampoOperacionesService.getValorCampoOperacion(mapOperaciones, nroOperacion, nombreCampoNroCuentaCargo, isTipoOpAprobar);
		}
		catch (NoEncontradoException ne) {
			nroCuentaCargo = "";
		}

		if (!"".equals(nroCuentaCargo)) {
			nroCuentaCargo = nroCuentaCargo.trim().replaceFirst("^0+(?!$)", "");// Le quito los ceros a la izquierda
		} else {
			LOGGER.error("ValidacionPortalService isPertenenciaValida: ERROR nroCuentaCargo == null");
			nroCuentaCargo = "";
		}

		return this.isCumplePertenencias(nroOperacion, nroCuentaCargo, rutApoderado, rutEmpresa, codServicio);
	}


	/**
	 * Checkea si una operacion cumple con las pertenencias.
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
				"ValidacionPortalService isCumplePertenencias: nroOperacion[{}] nroCuentaCargo[{}] rut[{}] rutEmpresa[{}] codServicio[{}]",
				nroOperacion, nroCuentaCargo, rut, rutEmpresa, codServicio);
		boolean resultado = true;
		
		resultado = this.validaPertenenciaCuenta(nroCuentaCargo, rut, rutEmpresa); 
		LOGGER.info(
				"ValidacionPortalService isCumplePertenencias: valida pertenencia cuenta cargo - rut usuario - rut empresa: resultado[{}]",
				resultado);

		if (resultado) {
			resultado = empresasService.perteneceUsuarioEmpresa(rut, rutEmpresa);
			LOGGER.info(
					"ValidacionPortalService isCumplePertenencias: valida pertenencia rut usuario - rut empresa: resultado[{}]",
					resultado);
		}

		if (resultado) {
			resultado = operacionesEmpresaService.isPertenenciaCorrectaNumOperacionRutEmpresa(nroOperacion, codServicio,
					rutEmpresa);
			LOGGER.info(
					"ValidacionService isCumplePertenencias: valida pertenencia nro operacion - rut empresa: resultado[{}]",
					resultado);
		}

		LOGGER.info("ValidacionPortalService isCumplePertenencias: resultado[{}]", resultado);
		return resultado;
	}
	
	
	private boolean validaPertenenciaCuenta(String nroCuentaCargo, String rut, String rutEmpresa) {
		boolean resultado = false;
		
		try {
			resultado = cuentaService.validaPertenenciaCuentaRutUsuarioRutEmpresa(nroCuentaCargo, rut, rutEmpresa);
		}
		catch (BancaEmpresasException e) {
			LOGGER.error("BancaEmpresasException: [{}]", e.getMessage());
		}
		
		return resultado;
		
	}


	/**
	 * Realiza la validacion de pertenencias para una lista de operaciones.
	 * 
	 * @param requestBase
	 * @param codServicio
	 * @param listOperaciones
	 * @param mapOperaciones
	 * @param isTipoOpAprobar
	 * @return
	 * @throws BancaEmpresasException
	 */
	public List<String> isPertenenciaValida(AprobarOperacionesPortalRequest requestBase, String codServicio, List<String> listOperaciones,
			MapOperaciones mapOperaciones, boolean isTipoOpAprobar) throws BancaEmpresasException {
		LOGGER.info("ValidacionPortalService isPertenenciaValida");
		List<String> listaOperOk = new ArrayList<>();
		String codServicioBD = "";
		
		for (String nroOperacion : listOperaciones) {
			codServicioBD = TefMasivaUtil.getCodServicioOperProgDesdeBD(mapOperaciones, nroOperacion);
			codServicio = Constantes.SERVICIO_OPERACION_EMPRESA_COD_LBTR_SOMA.equals(codServicioBD) ? Constantes.SERVICIO_OPERACION_EMPRESA_COD_LBTR_SOMA : codServicio;
			if (this.isPertenenciaValida(requestBase.getRutApoderado(), requestBase.getRutEmpresa(), codServicio, nroOperacion, mapOperaciones, isTipoOpAprobar)) {
				listaOperOk.add(nroOperacion);
			}
		}
		
		return listaOperOk;
	}

	/**
	 * Verifica si la empresa tiene cargo en línea LBTR
	 * 
	 * @param rutEmpresa
	 * @return
	 */
	public String empresaTieneCargoEnLineaLbtr(String rutEmpresa)  {
		LOGGER.info("ValidacionPortalService empresaTieneCargoEnLineaLbtr: rutEmpresa[{}]", rutEmpresa);
		String resultado = "";

		String salida;

		Map<String, Object> params = new HashMap<>();
		params.put("V_RUT_CLI", rutEmpresa);

		try {
			portalOrawRepository.empresaTieneCargoEnLineaLbtr(params);
			salida = (String) params.get("RESULT");
			
			if (salida == null || "".equals(salida)) {
				resultado = Constantes.GLS_ERROR_GENERICO;
			}
			else {
				resultado = ("0".equals(salida) ? Constantes.GLS_OPC_NO : Constantes.GLS_OPC_SI);
			}
			
		} 
		catch (SQLException e) {
			resultado = Constantes.GLS_ERROR_GENERICO;
		}

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
		LOGGER.info("ValidacionPortalService empresaOperaCuentaParaguaLbtr: rutEmpresa[{}]", rutEmpresa);
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
		LOGGER.info("ValidacionPortalService validarClave rut usuario: [{}], ", objRequest.getRut());
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
					LOGGER.info("ValidacionPortalService - Servicio validación clave ejecutado correctamente. Rut usuario [{}]",
							objRequest.getRut());
					Estado estadoResp = response.getBody();
					if (estadoResp.getCodigoEstado().equals("000")) {
						LOGGER.info("ValidacionPortalService - Clave válida. Rut usuario: [{}]", objRequest.getRut());
						retorno = true;
					} else {
						LOGGER.error("ValidacionPortalService - Clave inválida. Rut usuario: [{}]", objRequest.getRut());
						estadoService.setErrorEstado(EstadoEnum.ERROR_E006_EMPRESAS_LIBERAR_OPERACIONES, estado);
					}
				}

			} catch (Exception ex) {
				LOGGER.error("ValidacionPortalService [Exception] Error al validar clave rut usuario: [{}], error: [{}]", objRequest.getRut(),
						ex);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_LIBERAR_OPERACIONES, estado);
			}
		}

		return retorno;
	}
	

	public boolean validaOperProgLib(String numOperProg, MapOperaciones mapOperaciones, String nombreCampo) {
		boolean existeOperProg = false;
		Map<String, Object> map;
		List<Map<String, Object>> lista = mapOperaciones.getMapOutputSP();
		for (int i = 0; i < lista.size(); i++) {
			map = lista.get(i);
			if ( ((map.get(nombreCampo)).toString()).equals(numOperProg) ) {
				existeOperProg = true;
				break;
			}
		}
		
		return existeOperProg;

	}

	
	
}
