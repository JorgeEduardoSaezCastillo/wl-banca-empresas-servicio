package cl.bice.banca.empresas.servicio.model.response.saldo;

/**
 * Clase que contiene los datos de la facturacion de la tarjeta de credito.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
public class FacturacionResp {

	/**
	 * Etiqueta para la fecha de ultima factuacion.
	 */
	private String descripcionUltimaFacturacion;

	/**
	 * Fecha de la ultima facturacion.
	 */
	private String fechaUltimaFacturacion;

	/**
	 * Etiqueta para la fecha del tope de pago.
	 */
	private String descripcionFechaTopePago;

	/**
	 * Fecha tope de pago.
	 */
	private String fechaTopePago;

	/**
	 * Detalle de la facturacion MN.
	 */
	private DetalleFacturacionResp nacional;

	/**
	 * Detalle de la facturacion MX.
	 */
	private DetalleFacturacionResp internacional;

	/**
	 * Permite obtener el valor del atributo descripcionUltimaFacturacion.
	 *
	 * @return retorna el valor del atributo descripcionUltimaFacturacion.
	 */
	public String getDescripcionUltimaFacturacion() {
		return descripcionUltimaFacturacion;
	}

	/**
	 * Permite setear el valor del atributo descripcionUltimaFacturacion.
	 *
	 * @param newdescripcionUltimaFacturacion nuevo valor para el atributo
	 *                                        descripcionUltimaFacturacion.
	 */
	public void setDescripcionUltimaFacturacion(final String newdescripcionUltimaFacturacion) {
		this.descripcionUltimaFacturacion = newdescripcionUltimaFacturacion;
	}

	/**
	 * Permite obtener el valor del atributo fechaUltimaFacturacion.
	 *
	 * @return retorna el valor del atributo fechaUltimaFacturacion.
	 */
	public String getFechaUltimaFacturacion() {
		return fechaUltimaFacturacion;
	}

	/**
	 * Permite setear el valor del atributo fechaUltimaFacturacion.
	 *
	 * @param newfechaUltimaFacturacion nuevo valor para el atributo
	 *                                  fechaUltimaFacturacion.
	 */
	public void setFechaUltimaFacturacion(final String newfechaUltimaFacturacion) {
		this.fechaUltimaFacturacion = newfechaUltimaFacturacion;
	}

	/**
	 * Permite obtener el valor del atributo descripcionFechaTopePago.
	 *
	 * @return retorna el valor del atributo descripcionFechaTopePago.
	 */
	public String getDescripcionFechaTopePago() {
		return descripcionFechaTopePago;
	}

	/**
	 * Permite setear el valor del atributo descripcionFechaTopePago.
	 *
	 * @param newdescripcionFechaTopePago nuevo valor para el atributo
	 *                                    descripcionFechaTopePago.
	 */
	public void setDescripcionFechaTopePago(final String newdescripcionFechaTopePago) {
		this.descripcionFechaTopePago = newdescripcionFechaTopePago;
	}

	/**
	 * Permite obtener el valor del atributo fechaTopePago.
	 *
	 * @return retorna el valor del atributo fechaTopePago.
	 */
	public String getFechaTopePago() {
		return fechaTopePago;
	}

	/**
	 * Permite setear el valor del atributo fechaTopePago.
	 *
	 * @param newfechaTopePago nuevo valor para el atributo fechaTopePago.
	 */
	public void setFechaTopePago(final String newfechaTopePago) {
		this.fechaTopePago = newfechaTopePago;
	}

	/**
	 * Permite obtener el valor del atributo nacional.
	 *
	 * @return retorna el valor del atributo nacional.
	 */
	public DetalleFacturacionResp getNacional() {
		return nacional;
	}

	/**
	 * Permite setear el valor del atributo nacional.
	 *
	 * @param newnacional nuevo valor para el atributo nacional.
	 */
	public void setNacional(final DetalleFacturacionResp newnacional) {
		this.nacional = newnacional;
	}

	/**
	 * Permite obtener el valor del atributo internacional.
	 *
	 * @return retorna el valor del atributo internacional.
	 */
	public DetalleFacturacionResp getInternacional() {
		return internacional;
	}

	/**
	 * Permite setear el valor del atributo intenacional.
	 *
	 * @param newinternacional nuevo valor para el atributo intenacional.
	 */
	public void setInternacional(final DetalleFacturacionResp newinternacional) {
		this.internacional = newinternacional;
	}
}
