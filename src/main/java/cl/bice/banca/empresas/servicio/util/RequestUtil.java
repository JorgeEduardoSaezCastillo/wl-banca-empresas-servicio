package cl.bice.banca.empresas.servicio.util;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.common.BaseRequest;
import cl.bice.banca.empresas.servicio.model.common.Constantes;

/**
 * Clase utilitaria encargado de validar los request.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public final class RequestUtil {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

	/**
	 * Constructor Privado.
	 */
	private RequestUtil() {
		super();
	}

	/**
	 * Metodo encargado de validar el request base.
	 *
	 * @param request a validar.
	 * @throws RequestInvalidoException en caso de que falle la validacion.
	 */
	public static void validarRequestBase(Object request) throws RequestInvalidoException {
		if (request == null) {
			LOGGER.error("[validarRequestBase] Request invalido en el campo request");
			throw new RequestInvalidoException();
		}
		if (!(request instanceof BaseRequest)) {
			LOGGER.error("[validarRequestBase] Request invalido" + " no contiene request base.");
			throw new RequestInvalidoException();
		}
		BaseRequest requestBase = (BaseRequest) request;
		validarRut(requestBase);
		if (requestBase.getToken() == null || requestBase.getToken().isEmpty()) {
			LOGGER.error("[validarRequestBase] Request invalido en el campo token");
			throw new RequestInvalidoException();
		}
		if (requestBase.getDispositivo() == null || requestBase.getDispositivo().isEmpty()) {
			LOGGER.error("[validarRequestBase] Request invalido" + " en el campo Dispositivo");
			throw new RequestInvalidoException();
		}
	}

	/**
	 * Metodo encargado de validar el rut.
	 *
	 * @param request donde viene el rut.
	 * @throws RequestInvalidoException en caso de que el rut no este correcto.
	 */
	private static void validarRut(BaseRequest request) throws RequestInvalidoException {

		if (request.getRut() == null || request.getRut().isEmpty()) {
			LOGGER.error("[validarRut] Request invalido en el campo rut");
			throw new RequestInvalidoException();
		}
		try {
			String rut = request.getRut().toUpperCase();
			if (rut.contains(".") || rut.contains("-")) {
				LOGGER.error("[ValidarRut] Rut no debe contener simbolos");
				throw new RequestInvalidoException();

			}
			if (rut.length() != Constantes.LARGO_RUT) {
				LOGGER.error("[ValidarRut]Largo del rut debe ser 10");
				throw new RequestInvalidoException();
			}

			if (requestUtilEsValidoDigitoRut(rut)) {
				return;
			}

			LOGGER.error("[ValidarRut] Digito incorrecto");
			throw new RequestInvalidoException();

		} catch (NumberFormatException e) {
			LOGGER.error("[ValidarRut]Valores no numericos");
			throw new RequestInvalidoException(e);
		}
	}

	public static boolean requestUtilEsValidoDigitoRut(String rut) {
		int rutAux = Integer.parseInt(rut.substring(0, rut.length() - 1));

		char dv = rut.charAt(rut.length() - 1);

		int m = 0;
		int s = 1;
		for (; rutAux != 0; rutAux /= Constantes.LARGO_RUT) {
			s = (s + rutAux % Constantes.LARGO_RUT
					* (Constantes.FORMULA_RUT_NUEVE - m++ % Constantes.FORMULAR_RUT_SEIS))
					% Constantes.FORMULA_RUT_ONCE;
		}
		char comparaciondv;
		if (s != 0) {
			comparaciondv = (char) (s + Constantes.CHAR_SLASH);
		} else {
			comparaciondv = (char) Constantes.CHAR_K;
		}

		return dv == comparaciondv;
	}

	public static String obtenerValorJson(String texto, String key, boolean optativo) throws BancaEmpresasException {
		try {
			JSONObject jsonObj = new JSONObject(texto);
			return String.valueOf(jsonObj.get(key));
		} catch (JSONException e) {
			LOGGER.error("ERROR PARSEO", e);
			if (!optativo) {
				throw new BancaEmpresasException(e);
			} else {
				return null;
			}
		}
	}

	public static String obtenerRutSCeroCGuion(String rut) {
		String rutAux = MapperUtil.rutSinCeros(rut);
		char dv = rut.charAt(rut.length() - 1);
		rutAux = rutAux.substring(0, rutAux.length() - 1) + "-" + dv;
		return rutAux;
	}
	
	public static boolean validaCanal(String canal) {
		canal = canal == null ? "" : canal;
		return canal.equals(Constantes.CANAL_PORTAL_EMPRESA);
	}
	
	public static boolean validaCodigoServicio(String codServicio) {
		codServicio = codServicio == null ? "" : codServicio;
		return (codServicio.equals(Constantes.CODIGO_SERVICIO_LBTR));
	}
	
	public static boolean validaListaOperaciones(List<String> operaciones) {
		boolean resultado = false;
		
		if (operaciones != null && !operaciones.isEmpty()) {
			for (int i = 0; i < operaciones.size(); i++) {
				if (!esNumero(operaciones.get(i))) {
					resultado = false;
					break;
				}
				else {
					resultado = true;
				}
				
			}
		}

		
		return resultado;
	}	

	public static boolean validaNombreApoderado(String nombreApo) {
		return (nombreApo != null && !nombreApo.equals("") );
	}
	
	
	/**
	 * Metodo encargado de validar el rut.
	 *
	 * @param request donde viene el rut.
	 * @throws RequestInvalidoException en caso de que el rut no este correcto.
	 */
	public static int validarRut(String rut)  {
		int codError = 0;

		if (rut == null || rut.isEmpty()) {
			codError = -1;
			return codError;
		}
		try {
			rut = rut.toUpperCase();
			if (rut.contains(".") || rut.contains("-")) {
				codError = -2;
			}
			if (rut.length() != Constantes.LARGO_RUT) {
				codError = -3;
			}

			if (!requestUtilEsValidoDigitoRut(rut)) {
				codError = -4;
			}
			
		} catch (Exception e) {
			codError = -5;
			return codError;
		}
		
		return codError;

	}
	
	public static boolean esNumero(String numOperProg) {
		
		try {
			Long.parseLong(numOperProg);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	public static String agregaPuntos(long numero) {
		DecimalFormat formatea = new DecimalFormat("###,###.##");
		return formatea.format(numero);
	}
	
	
	public static String validaTipoUsuario(List<Map<String, Object>> datosUsuario) {
		String resultado = Constantes.USUARIO_SIN_PERMISO_APROBAR;
		
		try {
			if (datosUsuario != null && !datosUsuario.isEmpty()) {
				for (Map<String, Object> parametro : datosUsuario) {
					if (Constantes.FLG_ES_APODERADO == Integer.parseInt(parametro.get("FLG_APODERADO").toString()) || 
						Constantes.FLG_ES_ADM_DELEGADO == Integer.parseInt(parametro.get("FLG_ADM_DELEGADO").toString()) ) {
						resultado = Constantes.USUARIO_CON_PERMISO_APROBAR;
						break;
					}
				}
			}
			else {
				resultado = Constantes.USUARIO_NO_EXISTE;
			}
		}
		catch (Exception e) {
			resultado = "ERROR";
		}
		
		return resultado;
	}
	
	public static boolean isRutSegmentoEmpresa(String rutCliente) {
		return (Integer.parseInt(rutCliente.substring(0, rutCliente.length()-1)) >= Constantes.RUT_SEGMENTO_EMPRESA );
	}
	
}