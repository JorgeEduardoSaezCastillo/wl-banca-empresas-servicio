package cl.bice.banca.empresas.servicio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;

/**
 * Clase que contiene metodos utilitarios que utilizaran los mapper.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public final class MapperUtil {

	/** The Constant YYYYMMDD. Formato: yyyyMMdd */
	public static final String YYYYMMDD = "yyyyMMdd";

	/**
	 * Parametro de salida del paquete integracion canales. Mensaje del la respuesta
	 * de la BD.
	 */
	private static final String PARAMETRO_MSG_RESULTADO = "O_MSG_RESULT";

	/**
	 * Parametro de salida del paquete integracion canales. Codigo de la respuesta
	 * de la BD.
	 */
	private static final String PARAMETRO_COD_RESULTADO = "O_COD_RESULT";

	/**
	 * Contructor privado ya que es una clase statica.
	 */
	private MapperUtil() {
		super();
	}

	/**
	 * Metodo encargado de generar un mapa basico para la llamadas al paquete de
	 * integracion canales.
	 *
	 * @return mapa con los resultado por defecto.
	 */
	public static Map<String, Object> generarMapa() {
		Map<String, Object> mapa = new HashMap<>();
		mapa.put(PARAMETRO_COD_RESULTADO, null);
		mapa.put(PARAMETRO_MSG_RESULTADO, null);
		return mapa;
	}

	public static String validaRespuesta(Object objeto, boolean vacio) {
		if (objeto != null) {
			return String.valueOf(objeto);
		} else if (vacio) {
			return null;
		}
		return "";
	}

	/**
	 * Metodo encargado de generar un mapa basico para la llamadas al paquete de
	 * Banca Empresas.
	 *
	 * @return mapa con los resultado por defecto.
	 */
	public static Map<String, Object> generarMapaBancaEmpresas() {
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("cod_estado", null);
		mapa.put("gls_estado", null);
		return mapa;
	}

	/**
	 * Metodo encargado de generar un mapa basico para la llamadas al paquete de
	 * Banca Empresas.
	 *
	 * @return mapa con los resultado por defecto.
	 */
	public static Map<String, Object> generarMapaBancaDigitalCif() {
		Map<String, Object> mapa = new HashMap<>();
		mapa.put("p_cod_estado", null);
		mapa.put("p_gls_estado", null);
		return mapa;
	}

	/**
	 * Metodo encargado de limpiar los 0 de la izquierda del rut.
	 *
	 * @param rut con cero.
	 * @return rut sin cero.
	 */
	public static String rutSinCeros(String rut) {
		return rut.replaceFirst("^0+(?!$)", "");
	}

	public static String formatearFecha(String fecha, String parse, String formato) throws BancaEmpresasException {
		SimpleDateFormat dateParse = new SimpleDateFormat(parse);
		try {
			return formatearFecha(dateParse.parse(fecha), formato);
		} catch (ParseException e) {
			throw new BancaEmpresasException("Error al formatear fecha");
		}
	}

	public static Date parseFecha(String fecha, String parse) throws BancaEmpresasException {
		SimpleDateFormat dateParse = new SimpleDateFormat(parse);
		try {
			return dateParse.parse(fecha);
		} catch (ParseException e) {
			throw new BancaEmpresasException("Error al formatear fecha");
		}
	}

	public static String formatearFecha(Object fecha, String formato) {
		if (fecha instanceof Date) {
			return new SimpleDateFormat(formato).format(fecha);
		}
		return null;
	}

	public static int validarNumero(Object object) throws BancaEmpresasException {
		if (object != null) {
			try {
				return Integer.parseInt(object.toString());
			} catch (NumberFormatException e) {
				throw new BancaEmpresasException(e);
			}
		}
		return 0;
	}

	/**
	 * Valida que la salida del SP sea v&aacute;lida
	 * 
	 * @param salidaSP
	 * @return
	 */
	public static boolean isSalidaSPValida(List<Map<String, Object>> salida) {
		if (salida == null || salida.isEmpty())
			return false;
		else {
			return (salida.get(0) != null && !salida.get(0).isEmpty() && salida.get(0).get("-1") == null);
		}
	}

	/**
	 * Valida que codResult del SP sea v&aacute;lido
	 * 
	 * @param salidaSP
	 * @return
	 */
	public static boolean isCodResultSPValido(String codResult) {
		return (null != codResult && !"-1".equals(codResult.trim()));
	}
}
