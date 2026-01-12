package cl.bice.banca.empresas.servicio.model.operaciones;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase para contar monto total y cantidad de operaciones por moneda.
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class ResumenOperacionesMoneda {

	private String monedaOperaciones;
	private String codigoMonedaOperaciones;
	private BigDecimal montoTotalOperaciones;
	private int cantidadTotalOperaciones;
	private List<String> listNumOperaciones;

	/**
	 * @return the monedaOperaciones
	 */
	public String getMonedaOperaciones() {
		return monedaOperaciones;
	}

	/**
	 * @param monedaOperaciones the monedaOperaciones to set
	 */
	public void setMonedaOperaciones(String monedaOperaciones) {
		this.monedaOperaciones = monedaOperaciones;
	}

	/**
	 * @return the codigoMonedaOperaciones
	 */
	public String getCodigoMonedaOperaciones() {
		return codigoMonedaOperaciones;
	}

	/**
	 * @param codigoMonedaOperaciones the codigoMonedaOperaciones to set
	 */
	public void setCodigoMonedaOperaciones(String codigoMonedaOperaciones) {
		this.codigoMonedaOperaciones = codigoMonedaOperaciones;
	}

	/**
	 * @return the montoTotalOperaciones
	 */
	public BigDecimal getMontoTotalOperaciones() {
		return montoTotalOperaciones;
	}
	
	/**
	 * @return monto total en formato String
	 */
	public String getMontoTotalOperacionesFormat() {
		return String.valueOf(montoTotalOperaciones).replace('.', ',');
	}

	/**
	 * @param montoTotalOperaciones the montoTotalOperaciones to set
	 */
	public void setMontoTotalOperaciones(BigDecimal montoTotalOperaciones) {
		this.montoTotalOperaciones = montoTotalOperaciones;
	}

	/**
	 * @return the cantidadTotalOperaciones
	 */
	public int getCantidadTotalOperaciones() {
		return cantidadTotalOperaciones;
	}

	/**
	 * @param cantidadTotalOperaciones the cantidadTotalOperaciones to set
	 */
	public void setCantidadTotalOperaciones(int cantidadTotalOperaciones) {
		this.cantidadTotalOperaciones = cantidadTotalOperaciones;
	}

	/**
	 * @return the listNumOperaciones
	 */
	public List<String> getListNumOperaciones() {
		return listNumOperaciones;
	}

	/**
	 * @param listNumOperaciones the listNumOperaciones to set
	 */
	public void setListNumOperaciones(List<String> listNumOperaciones) {
		this.listNumOperaciones = listNumOperaciones;
	}

}
