package cl.bice.banca.empresas.servicio.model.response.movimientos;

/**
 * Clase que representa los movimientos no facturados de la tarjeta de credito.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
public class MovimientosNoFacturadosResp {

	/**
	 * Fecha de la transaccion.
	 */
	private String fecha;

	/**
	 * Descripcion.
	 */
	private String glosa;

	/**
	 * Detalle.
	 */
	private String detalle;

	/**
	 * Monto.
	 */
	private String monto;

	/**
	 * Monto formateado.
	 */
	private String montoFormato;

	/**
	 * Tipo de transaccion.
	 */
	private String tipoTransaccion;

	/**
	 * Pais de origen de la transaccion.
	 */
	private String pais;

	/**
	 * Ciudad de origen de la transaccion.
	 */
	private String ciudad;

	/**
	 * Permite obtener el valor del atributo fecha.
	 *
	 * @return retorna el valor del atributo fecha.
	 */
	public final String getFecha() {
		return fecha;
	}

	/**
	 * Permite setear el valor del atributo fecha.
	 *
	 * @param newFecha nuevo valor para el atributo fecha.
	 */
	public final void setFecha(final String newFecha) {
		this.fecha = newFecha;
	}

	/**
	 * Permite obtener el valor del atributo glosa.
	 *
	 * @return retorna el valor del atributo glosa.
	 */
	public final String getGlosa() {
		return glosa;
	}

	/**
	 * Permite setear el valor del atributo glosa.
	 *
	 * @param newGlosa nuevo valor para el atributo glosa.
	 */
	public final void setGlosa(final String newGlosa) {
		this.glosa = newGlosa;
	}

	/**
	 * Permite obtener el valor del atributo detalle.
	 *
	 * @return retorna el valor del atributo detalle.
	 */
	public final String getDetalle() {
		return detalle;
	}

	/**
	 * Permite setear el valor del atributo detalle.
	 *
	 * @param newDetalle nuevo valor para el atributo detalle.
	 */
	public final void setDetalle(final String newDetalle) {
		this.detalle = newDetalle;
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
	 * @param newMonto nuevo valor para el atributo monto.
	 */
	public final void setMonto(final String newMonto) {
		this.monto = newMonto;
	}

	/**
	 * Permite obtener el valor del atributo tipoTransaccion.
	 *
	 * @return retorna el valor del atributo tipoTransaccion.
	 */
	public final String getTipoTransaccion() {
		return tipoTransaccion;
	}

	/**
	 * Permite setear el valor del atributo tipoTransaccion.
	 *
	 * @param newTipoTransaccion nuevo valor para el atributo tipoTransaccion.
	 */
	public final void setTipoTransaccion(final String newTipoTransaccion) {
		this.tipoTransaccion = newTipoTransaccion;
	}

	/**
	 * Permite obtener el valor del atributo pais.
	 *
	 * @return retorna el valor del atributo pais.
	 */
	public final String getPais() {
		return pais;
	}

	/**
	 * Permite setear el valor del atributo pais.
	 *
	 * @param newpais nuevo valor para el atributo pais.
	 */
	public final void setPais(final String newpais) {
		this.pais = newpais;
	}

	/**
	 * Permite obtener el valor del atributo ciudad.
	 *
	 * @return retorna el valor del atributo ciudad.
	 */
	public final String getCiudad() {
		return ciudad;
	}

	/**
	 * Permite setear el valor del atributo ciudad.
	 *
	 * @param newciudad nuevo valor para el atributo ciudad.
	 */
	public final void setCiudad(final String newciudad) {
		this.ciudad = newciudad;
	}

	/**
	 * Permite obtener el valor del atributo montoFormato.
	 *
	 * @return retorna el valor del atributo montoFormato.
	 */
	public String getMontoFormato() {
		return montoFormato;
	}

	/**
	 * Permite setear el valor del atributo montoFormato.
	 *
	 * @param newmontoFormato nuevo valor para el atributo montoFormato.
	 */
	public void setMontoFormato(final String newmontoFormato) {
		this.montoFormato = newmontoFormato;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "MovimientosNoFacturadosResp [fecha=" + fecha + ", glosa=" + glosa + ", detalle=" + detalle + ", monto="
				+ monto + ", montoFormato=" + montoFormato + ", tipoTransaccion=" + tipoTransaccion + ", pais=" + pais
				+ ", ciudad=" + ciudad + "]";
	}

}
