package cl.bice.banca.empresas.servicio.model.response.empresas;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.saldo.SaldoResp;

/**
 * Saldo de una cuenta
 * 
 * @author Cristian Pais
 *
 */
public class SaldoResponse {

	/**
	 * Estatus.
	 */
	@JsonProperty("estado")
	private Estado estatus;

	/**
	 * Resumen de movimientos.
	 */
	@JsonProperty("saldo")
	private SaldoResp saldo;

	/**
	 * @return the estatus
	 */
	public Estado getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(Estado estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the saldo
	 */
	public SaldoResp getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(SaldoResp saldo) {
		this.saldo = saldo;
	}
}
