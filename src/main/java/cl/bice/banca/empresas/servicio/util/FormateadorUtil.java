package cl.bice.banca.empresas.servicio.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.bice.banca.empresas.servicio.model.common.Constantes;

/**
 * Clase encargada de Formatear.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class FormateadorUtil {

	/**
	 * Contructor por defecto.
	 */
	private FormateadorUtil() {
		super();
	}

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FormateadorUtil.class);

	public static final DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.getDefault());

	public static final String FORMAT_DECIMAL_PATTERN_DOLAR_EURO = "###,###.###";

	public static final String FORMAT_DECIMAL_PATTERN_UFCUATRO = "###,###.####";

	public static final String FORMAT_DECIMAL_PATTERN_PESOS = "###,###";
	
	public static final String EURO_SYMBOL_UTF = "\u20AC";

	public static final String formatoProducto(String cuenta, final int codProd) {
		if (cuenta != null) {
			String cuentaConFormato = "";
			StringBuilder sb = new StringBuilder(cuenta);
			while (sb.length() < 18) {
				sb.insert(0, "0");
			}
			String cuentaAux = sb.toString();
			String lngCuenta = String.valueOf(Long.parseLong(cuentaAux));
			if (lngCuenta.length() != 11 || !"200".equalsIgnoreCase(cuentaAux.substring(7, 10))
					&& !"213".equalsIgnoreCase(cuentaAux.substring(7, 10))) {
				switch (codProd) {
				case 100:
					cuentaConFormato = cuentaAux.substring(10, 12) + "-" + cuentaAux.substring(12, 17) + "-"
							+ cuentaAux.substring(17, 18);
					break;
				case 101:
					cuentaConFormato = cuentaAux.substring(7, 10) + "-" + cuentaAux.substring(10, 12) + "-"
							+ cuentaAux.substring(12, 17) + "-" + cuentaAux.substring(17, 18);
					break;
				case 110:
					cuentaConFormato = cuentaAux.substring(10, 12) + "-" + cuentaAux.substring(12, 17) + "-"
							+ cuentaAux.substring(17, 18);
					break;

				case 140:
					cuentaConFormato = cuentaAux.substring(7, 10) + "-" + cuentaAux.substring(10, 12) + "-"
							+ cuentaAux.substring(13, 17) + "-" + cuentaAux.substring(17, 18);
					break;
				case 120:
				case 250:
					cuentaConFormato = cuentaAux.substring(9, 11) + "-" + cuentaAux.substring(11, 16) + "-"
							+ cuentaAux.substring(16, 18);
					break;
				default:
					cuentaConFormato = cuentaAux;
					break;
				}
			} else {
				cuentaConFormato = cuentaAux.substring(7, 10) + "-" + cuentaAux.substring(10, 12) + "-"
						+ cuentaAux.substring(12, 17) + "-" + cuentaAux.substring(17, 18);
			}
			return cuentaConFormato;
		}
		return "";
	}

	public static String formatearMonto(String monto, String codigoMoneda) {
		DecimalFormat formatea;
		DecimalFormatSymbols decimalSymbols = new DecimalFormatSymbols(Locale.getDefault());
		decimalSymbols.setDecimalSeparator(',');
		decimalSymbols.setGroupingSeparator('.');

		switch (codigoMoneda) {
		case Constantes.CODIGO_MONEDA_PESOS:
			formatea = new DecimalFormat(FORMAT_DECIMAL_PATTERN_PESOS, decimalSymbols);
			return "$ " + formatea.format(Double.valueOf(monto));
		case Constantes.CODIGO_MONEDA_USD:
			formatea = new DecimalFormat(FORMAT_DECIMAL_PATTERN_DOLAR_EURO, decimalSymbols);
			return "US$ " + formatea.format(Double.valueOf(monto));
		case Constantes.CODIGO_MONEDA_EURO:
			formatea = new DecimalFormat(FORMAT_DECIMAL_PATTERN_DOLAR_EURO, decimalSymbols);
			return EURO_SYMBOL_UTF + " " + formatea.format(Double.valueOf(monto));
		case Constantes.CODIGO_MONEDA_UF:
			formatea = new DecimalFormat(FORMAT_DECIMAL_PATTERN_UFCUATRO, decimalSymbols);
			return "UF " + formatea.format(Double.valueOf(monto));
		default:
			return null;
		}
	}

	public static String formatearMontoOperacionMultiMoneda(String monto, String codigoMoneda) {
		String montoTemp = String.valueOf(formatBigDecimalfromString(monto)).replace('.', ',');

		switch (codigoMoneda) {
		case Constantes.CODIGO_MONEDA_USD:
			return "US$ " + montoTemp;
		case Constantes.CODIGO_MONEDA_EURO:
			return EURO_SYMBOL_UTF + " " + montoTemp;
		default:
			return null;
		}
	}

	/**
	 * Rellena con 0 a la izquiera hasta completar el largo.
	 *
	 * @param texto de origen.
	 * @param largo que debe tener el texto.
	 * @return texto con 0 a la izquierada.
	 */
	public static String rellenarCero(String texto, int largo) {
		StringBuilder sb = new StringBuilder(texto);
		while (sb.length() < largo) {
			sb.insert(0, "0");
		}
		return sb.toString();
	}

	public static String formatearMonto(double monto, String moneda) {
		return formatearMonto(String.valueOf(monto), moneda);
	}

	public static String rutSinCero(String rut) {
		if (rut != null) {
			return rut.replaceFirst("^0+(?!$)", "");
		} else {
			return null;
		}

	}

	/**
	 * Rut Sin ceros y con guion.
	 *
	 * @param rut Rut a limpiar.
	 * @return Rut sin ceros y con guion.
	 */
	public static String rutSinCeroCGuion(String rut) {
		String rutAux = rutSinCero(rut);
		if (rutAux != null && rutAux.length() > 2) {
			String dv = rutAux.substring(rutAux.length() - 1);
			return rutAux.substring(0, rutAux.length() - 1) + "-" + dv;
		}
		return null;
	}

	/**
	 * Metodo para formatear rut xx.xxx.xxx-0
	 * 
	 * @param rut
	 * @return
	 */
	public static String formatoRut(String rut) {

		String rutDigits;
		String checkDigit;
		String rutValue;
		String rutx = "";

		rut = rut.replace("-", "");
		rutValue = rut.trim();

		rutDigits = rutValue.substring(0, rutValue.length() - 1);
		checkDigit = rutValue.substring(rutValue.length() - 1);

		DecimalFormat dc = new DecimalFormat("###,###,###");
		rutx = dc.format(new Long(rutDigits));
		rutx = rutx.replace(',', '.');
		rutx = rutx + "-" + checkDigit;
		rutx = rutx.trim();

		return rutx;
	}

	/**
	 * @param monto
	 * @return
	 */
	public static String formatlongAMonto(long monto) {
		return formatBigDecimal(BigDecimal.valueOf(monto), "###,###,###,###,###");
	}

	/**
	 * Retorna un dato de tipo string formateado dada la mascara mask
	 * 
	 * @param amount
	 * @param mask
	 * @return
	 */
	public static String formatBigDecimal(BigDecimal amount, String mask) {
		DecimalFormat df = new DecimalFormat(mask);
		DecimalFormatSymbols dfs = df.getDecimalFormatSymbols();
		dfs.setDecimalSeparator(',');
		dfs.setGroupingSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		return df.format(amount.doubleValue());
	}

	/**
	 * Retorna un dato de tipo bigDecimal cuando los ultimos dos dígitos son los
	 * decimales
	 * 
	 * @param amount
	 * @param mask
	 * @return
	 */
	public static BigDecimal formatBigDecimalfromString(String amount) {
		try {
			if (amount != null && !amount.isEmpty() && amount.trim().length() > 2) {
				BigDecimal temp = new BigDecimal(amount.trim());
				return temp.divide(new BigDecimal("100"));
			} else {
				LOGGER.warn("No se pudo formatear el monto: [{}]", amount);
				return new BigDecimal("0");
			}
		} catch (NumberFormatException e) {
			LOGGER.warn("[{}] No se pudo formatear el monto: [{}]", e, amount);
			return new BigDecimal("0");
		}
	}
	
	public static String formatearMontoOperacionBiceComex(String monto, String codigoMoneda) {
		String montoTemp = monto.replace('.', ',');

		switch (codigoMoneda) {
		case Constantes.CODIGO_MONEDA_USD:
			return "US$ " + montoTemp;
		case Constantes.CODIGO_MONEDA_EURO:
			return EURO_SYMBOL_UTF + " " + montoTemp;
		default:
			return null;
		}
	}

}
