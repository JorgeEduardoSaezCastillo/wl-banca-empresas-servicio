package cl.bice.banca.empresas.servicio.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.soap.ClienteTablaParametros;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.banca.empresas.servicio.util.RequestUtil;
import cl.bice.nominas.parametros.model.CacheParametros;

/**
 * Clase utilitaria encargado de validar los request de empresas.
 *
 * @author Cristian Pais (TINet)
 * @version 1.0
 */
@Service
public class RequestEmpresasService {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestEmpresasService.class);

	public static final String LOG_REQUEST_INVALIDO = "[validarRequestBaseEmpresa] {}";
	public static final String COD_SERVICIO_TEF_BAJO_VALOR = "810";
	public static final String COD_SERVICIO_TEF_LBTR_MN = "935";

	/**
	 * Cliente para consultar la tabla par&aacute;metros
	 */
	@Autowired
	@Qualifier("ClienteTablaParametros")
	ClienteTablaParametros tablaParametros;

	/**
	 * Cache de la tabla par&aacute;metros
	 */
	@Autowired
	@Qualifier("MapCache")
	CacheParametros cacheParametros;

	@Autowired
	ConsultarParametrosService consultarParametrosService;

	@Autowired
	EstadoService estadoService;
	
	@Autowired
	OperacionesEmpresaPortalService operacionesEmpresaPortalService;

	/**
	 * Metodo encargado de validar el request base empresa.
	 *
	 * @param request a validar.
	 * @throws BancaEmpresasException
	 * @throws RequestInvalidoException en caso de que falle la validacion.
	 */
	public void requestEmpresasUtilValidarRequestBaseEmpresa(Object request, Estado estado) {
		if (request == null) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado);
			LOGGER.error(LOG_REQUEST_INVALIDO, estado.getGlosaEstado());
			return;
		}
		if (!(request instanceof BaseRequestEmpresa)) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado);
			LOGGER.error(LOG_REQUEST_INVALIDO, "no contiene request base.");
			return;
		}
		BaseRequestEmpresa requestBase = (BaseRequestEmpresa) request;

		try {
			requestEmpresaUtilValidarRutUsuario(requestBase);
		} catch (RequestInvalidoException e) {
			LOGGER.error(LOG_REQUEST_INVALIDO, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E004_EMPRESAS_REQUEST_INVALIDO, estado);
			return;
		}

		try {
			requestEmpresaUtilValidarRutEmpresa(requestBase);
		} catch (RequestInvalidoException e) {
			LOGGER.error(LOG_REQUEST_INVALIDO, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E005_EMPRESAS_REQUEST_INVALIDO, estado);
			return;
		}

		if (requestBase.getToken() == null || requestBase.getToken().isEmpty()) {
			LOGGER.error(LOG_REQUEST_INVALIDO, "en el campo token");
			estadoService.setErrorEstado(EstadoEnum.ERROR_E006_EMPRESAS_REQUEST_INVALIDO, estado);
			return;
		}
		if (requestBase.getDispositivo() == null || requestBase.getDispositivo().isEmpty()) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_REQUEST_INVALIDO, estado);
			LOGGER.error(LOG_REQUEST_INVALIDO, estado.getGlosaEstado());
			return;
		}

		if (requestBase.getCanal() == null || requestBase.getCanal().isEmpty()) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_REQUEST_INVALIDO, estado);
			LOGGER.error(LOG_REQUEST_INVALIDO, estado.getGlosaEstado());
		} else {
			try {
				boolean isCanalValido = consultarParametrosService.validarRegla("BANCAMOBILEEMPRESA", "CANAL",
						requestBase.getCanal(), 3);
				if (!isCanalValido) {
					estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_REQUEST_INVALIDO, estado);
					LOGGER.error(LOG_REQUEST_INVALIDO, estado.getGlosaEstado());
				}
			} catch (BancaEmpresasException e) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E007_EMPRESAS_REQUEST_INVALIDO, estado);
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			}
		}
	}

	/**
	 * Metodo encargado de validar rut usuario para request empresa.
	 *
	 * @param request
	 * @throws RequestInvalidoException en caso de que rut usuario no este correcto.
	 */
	private void requestEmpresaUtilValidarRutUsuario(BaseRequestEmpresa request) throws RequestInvalidoException {

		try {
			if (request.getRut() == null || request.getRut().isEmpty()) {
				LOGGER.error("[validarRut] Request invalido en el campo rut");
				throw new RequestInvalidoException();
			}

			String rut = request.getRut().toUpperCase();
			if (rut.contains(".") || rut.contains("-")) {
				LOGGER.error("[ValidarRut] Rut no debe contener simbolos");
				throw new RequestInvalidoException();

			}

			if (rut.length() != Constantes.LARGO_RUT) {
				LOGGER.error("[ValidarRut]Largo del rut debe ser 10");
				throw new RequestInvalidoException();
			}

			if (!RequestUtil.requestUtilEsValidoDigitoRut(rut)) {
				LOGGER.error("[ValidarRut] Rut con digito incorrecto");
				throw new RequestInvalidoException();
			}
		} catch (NumberFormatException e) {
			LOGGER.error("[ValidarRut]Valores no numericos");
			throw new RequestInvalidoException(e);
		}
	}

	/**
	 * Metodo encargado de validar rut empresa para request empresa.
	 *
	 * @param request
	 * @throws RequestInvalidoException en caso de que rut empresa no este correcto.
	 */
	private void requestEmpresaUtilValidarRutEmpresa(BaseRequestEmpresa request) throws RequestInvalidoException {

		try {

			if (request.getRutEmpresa() == null || request.getRutEmpresa().isEmpty()) {
				request.setRutEmpresa("0");
			}

			if ("".equals(request.getRutEmpresa().trim()) || "null".equalsIgnoreCase(request.getRutEmpresa().trim())
					|| "0".equals(request.getRutEmpresa().trim())) {
				request.setRutEmpresa("0");
			}

			if (!"0".equals(request.getRutEmpresa())) {

				String rutEmpresa = request.getRutEmpresa().toUpperCase();
				if (rutEmpresa.contains(".") || rutEmpresa.contains("-")) {
					LOGGER.error("[ValidarRut] Rut empresa no debe contener simbolos");
					throw new RequestInvalidoException();

				}
				if (rutEmpresa.length() != Constantes.LARGO_RUT) {
					LOGGER.error("[ValidarRut]Largo del rut empresa debe ser 10");
					throw new RequestInvalidoException();
				}

				if (!RequestUtil.requestUtilEsValidoDigitoRut(rutEmpresa)) {
					LOGGER.error("[ValidarRut] Rut empresa con digito incorrecto");
					throw new RequestInvalidoException();
				}

			}

		} catch (NumberFormatException e) {
			LOGGER.error("[ValidarRut]Valores no numericos");
			throw new RequestInvalidoException(e);
		}
	}
	
	
	/**
	 * Metodo encargado de validar el request completo de la operacion aprobarOperacionesMasivas
	 *
	 * @param request
	 * @throws RequestInvalidoException en caso de algun parametro sea incorrecto
	 */
	public void validaRequestEmpresaPortal(AprobarOperacionesPortalRequest request, Estado estado) throws RequestInvalidoException {

		try {
				
			if (!RequestUtil.validaCanal(request.getCanal())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_REQUEST_INVALIDO, estado);
				throw new RequestInvalidoException();
			}
			
			if (!RequestUtil.validaCodigoServicio(request.getCodigoServicio())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E008_EMPRESAS_REQUEST_INVALIDO, estado);
				throw new RequestInvalidoException();
			}

			if (!RequestUtil.validaListaOperaciones(request.getListaOperaciones())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E009_EMPRESAS_REQUEST_INVALIDO, estado);
				throw new RequestInvalidoException();
			}

			int codError = RequestUtil.validarRut(request.getRutApoderado());
			if (codError != 0) {
				estadoService = getGlosaErrorRut(codError, estado);
				throw new RequestInvalidoException();
			}			

			codError = RequestUtil.validarRut(request.getRutEmpresa());
			if (codError != 0) {
				codError = -5;
				estadoService = getGlosaErrorRut(codError, estado);
				throw new RequestInvalidoException();
			}
			
			if (!RequestUtil.isRutSegmentoEmpresa(request.getRutEmpresa())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E017_EMPRESAS_REQUEST_INVALIDO, estado);
				throw new RequestInvalidoException();
			}
			
			String result = RequestUtil.validaTipoUsuario(operacionesEmpresaPortalService.obtenerDatosUsuarioCliente(request.getRutEmpresa(), request.getRutApoderado()));
			if ( Constantes.USUARIO_SIN_PERMISO_APROBAR.equals(result) || Constantes.USUARIO_NO_EXISTE.equals(result)  ) {
				estadoService = getGlosaErrorTipoUsuario(result, estado);
				throw new RequestInvalidoException();
			}

		} catch (Exception e) {
			LOGGER.error(String.format("RequestEmpresasService.validaRequestEmpresaPortal - Exception: %s", e.getMessage()));
		}
	}

	
	private EstadoService getGlosaErrorRut(int codError, Estado estado) {
		switch (codError) {
		case -1:
			estadoService.setErrorEstado(EstadoEnum.ERROR_E004_EMPRESAS_REQUEST_INVALIDO, estado);
			break;
		case -2:
			estadoService.setErrorEstado(EstadoEnum.ERROR_E004_EMPRESAS_REQUEST_INVALIDO, estado);
			break;
		case -3:
			estadoService.setErrorEstado(EstadoEnum.ERROR_E011_EMPRESAS_REQUEST_INVALIDO, estado);
			break;
		case -4:
			estadoService.setErrorEstado(EstadoEnum.ERROR_E013_EMPRESAS_REQUEST_INVALIDO, estado);
			break;
		case -5:
			estadoService.setErrorEstado(EstadoEnum.ERROR_E012_EMPRESAS_REQUEST_INVALIDO, estado);
			break;
			
		default:
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado);
			break;
		}
		
		return estadoService;		
		
	}
	

	/**
	 * Metodo encargado de validar el request completo de la operacion liberarOperacionesMasivas
	 *
	 * @param request
	 * @throws RequestInvalidoException en caso de algun parametro sea incorrecto
	 */
	
	public void validaRequestEmpresaPortalLiberacion(LiberarOperacionesPortalRequest request, Estado estado) throws RequestInvalidoException {

		try {
				
			if (!RequestUtil.validaCanal(request.getCanal())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_REQUEST_INVALIDO, estado);
				throw new RequestInvalidoException();
			}
			
			if (!RequestUtil.validaCodigoServicio(request.getCodigoServicio())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E008_EMPRESAS_REQUEST_INVALIDO, estado);
				throw new RequestInvalidoException();
			}

			if (!RequestUtil.validaListaOperaciones(request.getListaOperaciones())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E009_EMPRESAS_REQUEST_INVALIDO, estado);
				throw new RequestInvalidoException();
			}

			int codError = RequestUtil.validarRut(request.getRutApoderado());
			if (codError != 0) {
				estadoService = getGlosaErrorRut(codError, estado);
				throw new RequestInvalidoException();
			}			

			codError = RequestUtil.validarRut(request.getRutEmpresa());
			if (codError != 0) {
				estadoService = getGlosaErrorRut(codError, estado);
				throw new RequestInvalidoException();
			}

		} catch (Exception e) {
			LOGGER.error(String.format("RequestEmpresasService.validaRequestEmpresaPortalLiberacion - Exception: %s", e.getMessage()));
		}
	}
	
	
	private EstadoService getGlosaErrorTipoUsuario(String glsError, Estado estado) {
		switch (glsError) {
		case Constantes.USUARIO_SIN_PERMISO_APROBAR:
			estadoService.setErrorEstado(EstadoEnum.ERROR_E016_EMPRESAS_REQUEST_INVALIDO, estado);
			break;
		case Constantes.USUARIO_NO_EXISTE:
			estadoService.setErrorEstado(EstadoEnum.ERROR_E018_EMPRESAS_REQUEST_INVALIDO, estado);
			break;
		default:
			estadoService.setErrorEstado(EstadoEnum.ERROR_E018_EMPRESAS_REQUEST_INVALIDO, estado);
			break;
		}
		
		return estadoService;		
		
	}

	
}