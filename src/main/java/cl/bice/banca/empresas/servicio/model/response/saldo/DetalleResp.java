package cl.bice.banca.empresas.servicio.model.response.saldo;

/**
 * Clase que contiene el detalle del saldo.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
public class DetalleResp {

	/**
	 * Descripcion.
	 */
	private String descripcion;

	/**
	 * Monto.
	 */
	private String monto;

	/**
	 * Monto Numero.
	 */
	private double montoNumerico;

	/**
	 * Codigo de la moneda.
	 */
	private String codigoMoneda;

	/**
	 * Permite obtener el valor del atributo descripcion.
	 *
	 * @return retorna el valor del atributo descripcion.
	 */
	public final String getDescripcion() {
		return descripcion;
	}

	/**
	 * Permite setear el valor del atributo descripcion.
	 *
	 * @param newdescripcion nuevo valor para el atributo descripcion.
	 */
	public final void setDescripcion(final String newdescripcion) {
		this.descripcion = newdescripcion;
	}

	/**
	 * Permite obtener el valor del atributo monto.
	 *
	 * @return retorna el valor del atributo monto.
	 */
	public final String getMonto() {
		return monto;
	}

	/**
	 * Permite setear el valor del atributo monto.
	 *
	 * @param newmonto nuevo valor para el atributo monto.
	 */
	public final void setMonto(final String newmonto) {
		this.monto = newmonto;
	}

	/**
	 * Permite obtener el valor del atributo codigoMoneda.
	 *
	 * @return retorna el valor del atributo codigoMoneda.
	 */
	public final String getCodigoMoneda() {
		return codigoMoneda;
	}

	/**
	 * Permite setear el valor del atributo codigoMoneda.
	 *
	 * @param newcodigoMoneda nuevo valor para el atributo codigoMoneda.
	 */
	public final void setCodigoMoneda(final String newcodigoMoneda) {
		this.codigoMoneda = newcodigoMoneda;
	}

	/**
	 * Permite obtener el valor del atributo montoNumerico.
	 *
	 * @return retorna el valor del atributo montoNumerico.
	 */
	public double getMontoNumerico() {
		return montoNumerico;
	}

	/**
	 * Permite setear el valor del atributo montoNumerico.
	 *
	 * @param montoNumerico nuevo valor para el atributo montoNumerico.
	 */
	public void setMontoNumerico(double montoNumerico) {
		this.montoNumerico = montoNumerico;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "DetalleResp [descripcion=" + descripcion + ", monto=" + monto + ", montoNumerico=" + montoNumerico
				+ ", codigoMoneda=" + codigoMoneda + "]";
	}

}
