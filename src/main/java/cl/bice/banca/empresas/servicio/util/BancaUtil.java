package cl.bice.banca.empresas.servicio.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.bice.banca.empresas.servicio.model.response.saldo.DetalleResp;
import cl.bice.banca.empresas.servicio.model.response.saldo.DetalleSaldoResp;
import cl.bice.banca.empresas.servicio.model.response.saldo.SaldoResp;
import cl.bice.banca.empresas.servicio.soap.balance.CuentaType;

/**
 * Clase utilitaria de banca empresas.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class BancaUtil {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BancaUtil.class);

	/**
	 * Contructor por defecto.
	 */
	private BancaUtil() {
		super();
	}

	/**
	 * Convierte el string recibido en un objeto Date.
	 *
	 * @param fecha  Fecha.
	 * @param format Formato de la fecha.
	 * @return Objeto tipo Date.
	 */
	public static Date parseDate(final String fecha, final String format) {

		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date fechaSalida = null;
		if (fecha != null) {
			try {
				fechaSalida = formatter.parse(fecha);
			} catch (ParseException e) {
				fechaSalida = null;
			}
		}
		return fechaSalida;
	}

	public static void cargarCuenta(SaldoResp saldoResp, CuentaType cuenta) {

		DetalleSaldoResp detalleNuevo = new DetalleSaldoResp();
		DetalleResp informacion = new DetalleResp();
		informacion.setDescripcion("En Pesos");
		informacion.setCodigoMoneda("000");
		informacion.setMonto(
				FormateadorUtil.formatearMonto(String.valueOf(cuenta.getDolar()), informacion.getCodigoMoneda()));
		detalleNuevo.getSaldos().add(informacion);
		informacion = new DetalleResp();
		informacion.setDescripcion("En Dólares");
		informacion.setCodigoMoneda("013");
		informacion.setMonto(
				FormateadorUtil.formatearMonto(String.valueOf(cuenta.getDolar()), informacion.getCodigoMoneda()));
		detalleNuevo.getSaldos().add(informacion);
		informacion = new DetalleResp();
		informacion.setDescripcion("En Euros");
		informacion.setCodigoMoneda("142");
		informacion.setMonto(
				FormateadorUtil.formatearMonto(String.valueOf(cuenta.getDolar()), informacion.getCodigoMoneda()));
		detalleNuevo.getSaldos().add(informacion);
		saldoResp.getDetalle().add(detalleNuevo);
	}

	/**
	 * Convierte un objecto a un String en formato json.
	 * 
	 * @param obj
	 * @return
	 */
	public static String objectToJson(Object obj) {
		String resp = "";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			resp = objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			LOGGER.info("Error al convertir objecto a json: ", e);
		}
		return resp;
	}

	/**
	 * Convierte una lista a String cuyos elementos están separados con el separador
	 * recibido por parámetro
	 * 
	 * @param list
	 * @param separator
	 * @return
	 */
	public static String listToString(List<String> list, String separator) {
		String result = "";
		if (null != list && !list.isEmpty()) {
			result = String.join(separator, list);
			if (result.charAt(result.length() - 1) == separator.charAt(0)) {
				result = result.substring(0, result.length() - 1);
			}
		}
		return result;
	}

	/**
	 * Cambia de formato una fecha
	 * 
	 * @param fecha
	 * @param formatoOriginal
	 * @param formatoNuevo
	 * @return
	 * @throws ParseException
	 */
	public static String cambiarFormatoFecha(String fecha, String formatoOriginal, String formatoNuevo)
			throws ParseException {
		return (new SimpleDateFormat(formatoOriginal)).format((new SimpleDateFormat(formatoNuevo)).parse(fecha));
	}

	/**
	 * Pasa los atributos y respectivos valores de un objeto a un map
	 * 
	 * @param o
	 * @return
	 */
	public static Map<String, String> object2Map(Object o, List<String> attrExclude) {
		Class co = o.getClass();
		Field[] cfields = co.getDeclaredFields();
		Map<String, String> ret = new HashMap<>();
		for (Field f : cfields) {
			String attributeName = f.getName();
			if (null == attrExclude || !attrExclude.contains(attributeName)) {
				String getterMethodName = "get" + attributeName.substring(0, 1).toUpperCase()
						+ attributeName.substring(1, attributeName.length());
				Method m = null;
				try {
					m = co.getMethod(getterMethodName);
					Object valObject = m.invoke(o);
					ret.put(attributeName, String.valueOf(valObject));
				} catch (Exception e) {
					continue;
				}
			}
		}
		return ret;
	}
	
	/**
	 * Agrega dias a un date
	 * 
	 * @param o
	 * @return
	 */
	public static Date agregaDias(Date fecha, int dias) {
		Calendar cal = Calendar.getInstance();
		  cal.setTime(fecha);
		  cal.add(Calendar.DATE, dias);
		  fecha = cal.getTime();
		return fecha;
	}

}
