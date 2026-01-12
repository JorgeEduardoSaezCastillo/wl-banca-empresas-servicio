package cl.bice.banca.empresas.servicio.model.common;

/**
 * Clase con el detalle de la tarjeta.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class DetalleTarjeta {

	/**
	 * Fecha del detalle DD MMM YYYY.
	 */
	private String fecha;

	/**
	 * Fecha Filter MM/DD/YYYY.
	 */
	private String fechaFiltro;

	/**
	 * Fecha orden en segundos.
	 */
	private String fechaOrden;

	/**
	 * Descripcion del detalle.
	 */
	private String descripcion;

	/**
	 * Tipo de operacion.
	 */
	private String tipoOperacion;

	/**
	 * lugar donde se efectua la operacion (Pais internacion, ciudad nacional)
	 */
	private String lugar;

	/**
	 * Cargo realizado a la cuenta (Con signo).
	 */
	private String cargo;

	/**
	 * Abono realizado a la cuenta (Con signo).
	 */
	private String abono;

	/**
	 * Abono neto realizado a la cuenta (Sin signo).
	 */
	private String abonoNeto;

	/**
	 * Cargo Neto realizado a la cuenta (Sin signo).
	 */
	private String cargoNeto;

	/**
	 * Permite obtener el valor del atributo fecha.
	 *
	 * @return retorna el valor del atributo fecha.
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Permite setear el valor del atributo fecha.
	 *
	 * @param fecha nuevo valor para el atributo fecha.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Permite obtener el valor del atributo descripcion.
	 *
	 * @return retorna el valor del atributo descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Permite setear el valor del atributo descripcion.
	 *
	 * @param descripcion nuevo valor para el atributo descripcion.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Permite obtener el valor del atributo tipoOperacion.
	 *
	 * @return retorna el valor del atributo tipoOperacion.
	 */
	public String getTipoOperacion() {
		return tipoOperacion;
	}

	/**
	 * Permite setear el valor del atributo tipoOperacion.
	 *
	 * @param tipoOperacion nuevo valor para el atributo tipoOperacion.
	 */
	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	/**
	 * Permite obtener el valor del atributo lugar.
	 *
	 * @return retorna el valor del atributo lugar.
	 */
	public String getLugar() {
		return lugar;
	}

	/**
	 * Permite setear el valor del atributo lugar.
	 *
	 * @param lugar nuevo valor para el atributo lugar.
	 */
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	/**
	 * Permite obtener el valor del atributo cargo.
	 *
	 * @return retorna el valor del atributo cargo.
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * Permite setear el valor del atributo cargo.
	 *
	 * @param cargo nuevo valor para el atributo cargo.
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * Permite obtener el valor del atributo abono.
	 *
	 * @return retorna el valor del atributo abono.
	 */
	public String getAbono() {
		return abono;
	}

	/**
	 * Permite setear el valor del atributo abono.
	 *
	 * @param abono nuevo valor para el atributo abono.
	 */
	public void setAbono(String abono) {
		this.abono = abono;
	}

	/**
	 * Permite obtener el valor del atributo fechaFiltro.
	 *
	 * @return retorna el valor del atributo fechaFiltro.
	 */
	public String getFechaFiltro() {
		return fechaFiltro;
	}

	/**
	 * Permite setear el valor del atributo fechaFiltro.
	 *
	 * @param fechaFiltro nuevo valor para el atributo fechaFiltro.
	 */
	public void setFechaFiltro(String fechaFiltro) {
		this.fechaFiltro = fechaFiltro;
	}

	/**
	 * Permite obtener el valor del atributo fechaOrden.
	 *
	 * @return retorna el valor del atributo fechaOrden.
	 */
	public String getFechaOrden() {
		return fechaOrden;
	}

	/**
	 * Permite setear el valor del atributo fechaOrden.
	 *
	 * @param fechaOrden nuevo valor para el atributo fechaOrden.
	 */
	public void setFechaOrden(String fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	/**
	 * Permite obtener el valor del atributo abonoNeto.
	 *
	 * @return retorna el valor del atributo abonoNeto.
	 */
	public String getAbonoNeto() {
		return abonoNeto;
	}

	/**
	 * Permite setear el valor del atributo abonoNeto.
	 *
	 * @param abonoNeto nuevo valor para el atributo abonoNeto.
	 */
	public void setAbonoNeto(String abonoNeto) {
		this.abonoNeto = abonoNeto;
	}

	/**
	 * Permite obtener el valor del atributo cargoNeto.
	 *
	 * @return retorna el valor del atributo cargoNeto.
	 */
	public String getCargoNeto() {
		return cargoNeto;
	}

	/**
	 * Permite setear el valor del atributo cargoNeto.
	 *
	 * @param cargoNeto nuevo valor para el atributo cargoNeto.
	 */
	public void setCargoNeto(String cargoNeto) {
		this.cargoNeto = cargoNeto;
	}
}
