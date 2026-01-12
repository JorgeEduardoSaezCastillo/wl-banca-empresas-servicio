package cl.bice.banca.empresas.servicio.model.response.empresas;

import cl.bice.banca.empresas.servicio.model.response.saldo.SaldoResp;

/**
 * Cuenta de una empresa con saldo
 * 
 * @author Cristian Pais
 *
 */
public class CuentaSaldo extends Cuenta {
	/**
	 * saldo
	 */
	private SaldoResp saldo;

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
