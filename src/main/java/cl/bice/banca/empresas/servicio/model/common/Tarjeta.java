package cl.bice.banca.empresas.servicio.model.common;

import java.util.List;

/**
 * Informacion de la tarjeta.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class Tarjeta {
	/**
	 * Dinero disponible para la tarjeta.
	 */
	private String disponible;

	/**
	 * Dinero Utilizado.
	 */
	private String utilizado;

	/**
	 * Dinero para avance disponible Solo Nacional.
	 */
	private String avance;

	/**
	 * Fecha de la ultima facturacion.
	 */
	private String fechaUltimaFacturacion;

	/**
	 * Fecha vencimiento de la cuenta.
	 */
	private String fechaVencimiento;

	/**
	 * Pago total del mes.
	 */
	private String pagoTotal;

	/**
	 * Pago minimo del mes.
	 */
	private String pagoMinimo;

	/**
	 * Texto del pago automatico de la tarjeta.
	 */
	private String pat;

	/**
	 * Link Principal.
	 */
	private Link link;

	/**
	 * Links que contiene la tarjeta.
	 */
	private List<Link> links;

	/**
	 * Permite obtener el valor del atributo link.
	 *
	 * @return retorna el valor del atributo link.
	 */
	public Link getLink() {
		return link;
	}

	/**
	 * Permite setear el valor del atributo link.
	 *
	 * @param link nuevo valor para el atributo link.
	 */
	public void setLink(Link link) {
		this.link = link;
	}

	/**
	 * Permite obtener el valor del atributo links.
	 *
	 * @return retorna el valor del atributo links.
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * Permite setear el valor del atributo links.
	 *
	 * @param links nuevo valor para el atributo links.
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	/**
	 * Permite obtener el valor del atributo disponible.
	 *
	 * @return retorna el valor del atributo disponible.
	 */
	public String getDisponible() {
		return disponible;
	}

	/**
	 * Permite setear el valor del atributo disponible.
	 *
	 * @param disponible nuevo valor para el atributo disponible.
	 */
	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	/**
	 * Permite obtener el valor del atributo utilizado.
	 *
	 * @return retorna el valor del atributo utilizado.
	 */
	public String getUtilizado() {
		return utilizado;
	}

	/**
	 * Permite setear el valor del atributo utilizado.
	 *
	 * @param utilizado nuevo valor para el atributo utilizado.
	 */
	public void setUtilizado(String utilizado) {
		this.utilizado = utilizado;
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
	 * @param fechaUltimaFacturacion nuevo valor para el atributo
	 *                               fechaUltimaFacturacion.
	 */
	public void setFechaUltimaFacturacion(String fechaUltimaFacturacion) {
		this.fechaUltimaFacturacion = fechaUltimaFacturacion;
	}

	/**
	 * Permite obtener el valor del atributo fechaVencimiento.
	 *
	 * @return retorna el valor del atributo fechaVencimiento.
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Permite setear el valor del atributo fechaVencimiento.
	 *
	 * @param fechaVencimiento nuevo valor para el atributo fechaVencimiento.
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Permite obtener el valor del atributo pagoTotal.
	 *
	 * @return retorna el valor del atributo pagoTotal.
	 */
	public String getPagoTotal() {
		return pagoTotal;
	}

	/**
	 * Permite setear el valor del atributo pagoTotal.
	 *
	 * @param pagoTotal nuevo valor para el atributo pagoTotal.
	 */
	public void setPagoTotal(String pagoTotal) {
		this.pagoTotal = pagoTotal;
	}

	/**
	 * Permite obtener el valor del atributo pagoMinimo.
	 *
	 * @return retorna el valor del atributo pagoMinimo.
	 */
	public String getPagoMinimo() {
		return pagoMinimo;
	}

	/**
	 * Permite setear el valor del atributo pagoMinimo.
	 *
	 * @param pagoMinimo nuevo valor para el atributo pagoMinimo.
	 */
	public void setPagoMinimo(String pagoMinimo) {
		this.pagoMinimo = pagoMinimo;
	}

	/**
	 * Permite obtener el valor del atributo pat.
	 *
	 * @return retorna el valor del atributo pat.
	 */
	public String getPat() {
		return pat;
	}

	/**
	 * Permite setear el valor del atributo pat.
	 *
	 * @param pat nuevo valor para el atributo pat.
	 */
	public void setPat(String pat) {
		this.pat = pat;
	}

	/**
	 * Permite obtener el valor del atributo avance.
	 *
	 * @return retorna el valor del atributo avance.
	 */
	public String getAvance() {
		return avance;
	}

	/**
	 * Permite setear el valor del atributo avance.
	 *
	 * @param avance nuevo valor para el atributo avance.
	 */
	public void setAvance(String avance) {
		this.avance = avance;
	}

}
