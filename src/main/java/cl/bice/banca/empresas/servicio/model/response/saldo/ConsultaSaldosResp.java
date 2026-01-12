package cl.bice.banca.empresas.servicio.model.response.saldo;

import cl.bice.banca.empresas.servicio.model.common.EstadoResp;

/**
 * Clase que contiene los datos de respuesta del servicio rest.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
public class ConsultaSaldosResp {

	/**
	 * Estado de la consulta.
	 */
	private EstadoResp estado;

	/**
	 * Detalle de los saldos.
	 */
	private SaldoResp saldo;

	/**
	 * Permite obtener el valor del atributo estado.
	 *
	 * @return retorna el valor del atributo estado.
	 */
	public final EstadoResp getEstado() {
		return estado;
	}

	/**
	 * Permite setear el valor del atributo estado.
	 *
	 * @param newestado nuevo valor para el atributo estado.
	 */
	public final void setEstado(final EstadoResp newestado) {
		this.estado = newestado;
	}

	/**
	 * Permite obtener el valor del atributo saldo.
	 *
	 * @return retorna el valor del atributo saldo.
	 */
	public final SaldoResp getSaldo() {
		return saldo;
	}

	/**
	 * Permite setear el valor del atributo saldo.
	 *
	 * @param newsaldo nuevo valor para el atributo saldo.
	 */
	public final void setSaldo(final SaldoResp newsaldo) {
		this.saldo = newsaldo;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "ConsultaSaldosResp [estado=" + estado + ", saldo=" + saldo + "]";
	}

}
