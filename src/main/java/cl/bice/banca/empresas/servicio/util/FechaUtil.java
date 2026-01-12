package cl.bice.banca.empresas.servicio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.log.SysoCounter;

import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.service.EstadoService;

/**
 * Clase Utilitaria para validaciones de fechas.
 *
 * @version 1.0
 */

public class FechaUtil {
	
	public FechaUtil() {
		super();
	}

	/**
	 * Valida Fecha formato yyyymmdd, si viene nula no se realiza validación
	 * 
	 * @param fecha
	 * @return
	 * @throws ParseException
	 */
	public boolean isFechaValida(String fecha) {
		boolean fechaOk = false;

	    try {
			if (fecha == null || fecha.equals("")) {
				fechaOk = true;
			}
			else {
				if (fecha.length() < 8 || fecha.length() > 8 || !esNumero(fecha) ) {
					fechaOk = false;
				}
				else {
			        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			        formatoFecha.setLenient(false);
			        formatoFecha.parse(fecha.substring(6,8) + "/" + fecha.substring(4,6) + "/" + fecha.substring(0,4));
			        fechaOk = true;
				}
			}
	    } 
	    catch (Exception e1) {
	        fechaOk = false;
	    }

	    return fechaOk;	
		
	}
	
	private boolean esNumero(String valor) {
		return valor.matches("[0-9]*");
	}
	
	public String validaFechas(String fechaDesde, String fechaHasta) {
		String fechasOk = "";
		
		try { 
			if ( isFechasNulas(fechaDesde,fechaHasta) || isFechasBlanco(fechaDesde, fechaHasta)  ) {
				fechasOk = "OK";
			}
			else {
				fechasOk = validaFechasBlanco(fechaDesde,fechaHasta);
				if (fechasOk.equals("OK")) {
					fechasOk = validaFechasNumericas(fechaDesde, fechaHasta);
					if (fechasOk.equals("OK")) {
						fechasOk = validaRangoFechas(fechaDesde, fechaHasta);
					}
				}
			}
		}
		catch (Exception e) {
			
		}
		
		return fechasOk;
	}
	
	private String validaRangoFechas(String fechaDesde, String fechaHasta) {
		String fechasOk = "OK";
		
		if (!esFechaMenor(fechaDesde,fechaHasta)) {
			fechasOk = "GLOSA_ERROR_REQUEST_CAMPO_FECHAS_1";
		}
		else {
			if (!esFechaMayor(fechaDesde,fechaHasta)) {
				fechasOk = "GLOSA_ERROR_REQUEST_CAMPO_FECHAS_2";
			}
			else {
		        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		        Date fecSistema = new Date();
		        if (Integer.parseInt(fechaDesde) < Integer.parseInt(dateFormat.format(fecSistema))) {
		        	fechasOk = "GLOSA_ERROR_REQUEST_CAMPO_FECHAS_6";
		        }
				
			}
		}
		
		return fechasOk;
		
	}
	
	
	private String validaFechasNumericas(String fechaDesde, String fechaHasta) {
		String fechasOk = "";
		
		try {
			if ( !esNumero(fechaDesde) && esNumero(fechaHasta) ) {
				fechasOk = "GLOSA_ERROR_REQUEST_CAMPO_FECHAS_3";
			}
			else {
				if ( esNumero(fechaDesde) && !esNumero(fechaHasta) ) {
					fechasOk = "GLOSA_ERROR_REQUEST_CAMPO_FECHAS_4";
				}
				else {
					if ( !esNumero(fechaDesde) && !esNumero(fechaHasta) ) {
						fechasOk = "GLOSA_ERROR_REQUEST_CAMPO_FECHAS_5";
					}
					else {
						fechasOk = "OK";
					}
				}
			}
		}
		catch (Exception e) {
			
		}
		return fechasOk;

	} 
	
	private String validaFechasBlanco(String fechaDesde, String fechaHasta) {
		String fechasOk = "";
		
		try {
			if (fechaDesde.equals("") && !fechaHasta.equals("")) {
				fechasOk = "GLOSA_ERROR_REQUEST_CAMPO_FEC_DESDE_VAL";
			}
			else {
				if (!fechaDesde.equals("") && fechaHasta.equals("")) {
					fechasOk = "GLOSA_ERROR_REQUEST_CAMPO_FEC_HASTA_VAL";
				}
				else {
					fechasOk = "OK";
				}
			}
		}
		catch (Exception e) {
			
		}
		return fechasOk;
	} 
	
	private boolean isFechasBlanco(String fecha1, String fecha2) {
		try {
			return (fecha1.equals("") && fecha2.equals("") );
		}
		catch (Exception e) {
			
		}
		return false;
	} 
	
	private boolean isFechasNulas(String fecha1, String fecha2) {
		try {
			return (fecha1 == null && fecha2 == null );
		}
		catch (Exception e) {
			
		}
		return false;
	}	
	
	public boolean esFechaMenor(String fechaDesde, String fechaHasta) {
		boolean esMenor = false;
		
		try {
			if (Integer.parseInt(fechaDesde) <= Integer.parseInt(fechaHasta) ) {
				esMenor = true;
			}
		}
		catch (Exception e) {
			
		}
		
		return esMenor;
	}

	public boolean esFechaMayor(String fechaDesde, String fechaHasta) {
		boolean esMayor = false;
		
		try {
			if (Integer.parseInt(fechaHasta) >= Integer.parseInt(fechaDesde) ) {
				esMayor = true;
			}
		}
		catch (Exception e) {
			
		}
		
		return esMayor;
	}
	
	public void setGlosaError(EstadoService estadoService, Estado estado, String tipoError) {
		switch (tipoError) {
		case "GLOSA_ERROR_REQUEST_CAMPO_FEC_DESDE_VAL":
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,EstadoService.GLOSA_ERROR_REQUEST_CAMPO_FEC_DESDE_VAL);
			break;
		case "GLOSA_ERROR_REQUEST_CAMPO_FEC_HASTA_VAL":
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,EstadoService.GLOSA_ERROR_REQUEST_CAMPO_FEC_HASTA_VAL);
			break;
		case "GLOSA_ERROR_REQUEST_CAMPO_FECHAS_1":
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,EstadoService.GLOSA_ERROR_REQUEST_CAMPO_FECHAS_1);
			break;
		case "GLOSA_ERROR_REQUEST_CAMPO_FECHAS_2":
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,EstadoService.GLOSA_ERROR_REQUEST_CAMPO_FECHAS_2);
			break;
		case "GLOSA_ERROR_REQUEST_CAMPO_FECHAS_6":
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,EstadoService.GLOSA_ERROR_REQUEST_CAMPO_FECHAS_6);
			break;

		default:
			break;
		}
		
	}
	

}
