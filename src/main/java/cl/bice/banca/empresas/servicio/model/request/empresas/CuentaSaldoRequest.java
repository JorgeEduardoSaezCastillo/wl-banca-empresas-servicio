package cl.bice.banca.empresas.servicio.model.request.empresas;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

public class CuentaSaldoRequest extends BaseRequestEmpresa {
	/**
	 * Código de producto
	 */
	private String codigoProducto;
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
