package cl.bice.banca.empresas.servicio.model.request.empresas;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

/**
 * Clase que contiene par&aacute;metros de consulta para obtener el saldo de una
 * cuenta
 * 
 * @author Cristian Pais
 *
 */
public class SaldoRequest extends BaseRequestEmpresa {

	/**
	 * Código de producto
	 */
	private String codigoProducto;
	/**
	 * Cuenta
	 */
	private String cuenta;
	/**
	 * Moneda
	 */
	private String moneda;
	/**
	 * Bia
	 */
	private String bia;

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the bia
	 */
	public boolean isBia() {
		return (bia != null && "true".equalsIgnoreCase(bia.trim()));
	}

	/**
	 * @param bia the bia to set
	 */
	public void setBia(String bia) {
		this.bia = bia;
	}

}
