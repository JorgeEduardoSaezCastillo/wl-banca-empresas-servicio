package cl.bice.banca.empresas.servicio.model.response.saldo;

import java.util.List;

import cl.bice.banca.empresas.servicio.model.common.AccesoDirecto;

/**
 * Clase que contiene los datos del detalle de la facturacion de la tdc para
 * moneda nacional o internacional.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
public class DetalleFacturacionResp {

	/**
	 * Etiqueta para la deuda facturada.
	 */
	private String descripcionDeudaFacturada;

	/**
	 * Monto deuda facturada.
	 */
	private String deudaFacturada;

	/**
	 * Monto deuda facturada formateada.
	 */
	private String deudaFacturadaFormateada;

	/**
	 * Etiqueta para la deuda actual.
	 */
	private String descripcionDeudaActual;

	/**
	 * Monto deuda actual.
	 */
	private String deudaActual;

	/**
	 * Monto deuda actual fotmateada.
	 */
	private String deudaActualFormateada;

	/**
	 * Etiqueta para la deuda actual.
	 */
	private String descripcionPagoMinimo;

	/**
	 * Monto deuda actual.
	 */
	private String pagoMinimo;

	/**
	 * Monto deuda actual fotmateada.
	 */
	private String pagoMinimoFormateada;

	private List<AccesoDirecto> links;

	/**
	 * Permite obtener el valor del atributo descripcionPagoMinimo.
	 *
	 * @return retorna el valor del atributo descripcionPagoMinimo.
	 */
	public String getDescripcionPagoMinimo() {
		return descripcionPagoMinimo;
	}

	/**
	 * Permite setear el valor del atributo descripcionPagoMinimo.
	 *
	 * @param descripcionPagoMinimo nuevo valor para el atributo
	 *                              descripcionPagoMinimo.
	 */
	public void setDescripcionPagoMinimo(String descripcionPagoMinimo) {
		this.descripcionPagoMinimo = descripcionPagoMinimo;
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
	 * Permite obtener el valor del atributo pagoMinimoFormateada.
	 *
	 * @return retorna el valor del atributo pagoMinimoFormateada.
	 */
	public String getPagoMinimoFormateada() {
		return pagoMinimoFormateada;
	}

	/**
	 * Permite setear el valor del atributo pagoMinimoFormateada.
	 *
	 * @param pagoMinimoFormateada nuevo valor para el atributo
	 *                             pagoMinimoFormateada.
	 */
	public void setPagoMinimoFormateada(String pagoMinimoFormateada) {
		this.pagoMinimoFormateada = pagoMinimoFormateada;
	}

	/**
	 * Permite obtener el valor del atributo descripcionDeudaFacturada.
	 *
	 * @return retorna el valor del atributo descripcionDeudaFacturada.
	 */
	public String getDescripcionDeudaFacturada() {
		return descripcionDeudaFacturada;
	}

	/**
	 * Permite setear el valor del atributo descripcionDeudaFacturada.
	 *
	 * @param newdescripcionDeudaFacturada nuevo valor para el atributo
	 *                                     descripcionDeudaFacturada.
	 */
	public void setDescripcionDeudaFacturada(final String newdescripcionDeudaFacturada) {
		this.descripcionDeudaFacturada = newdescripcionDeudaFacturada;
	}

	/**
	 * Permite obtener el valor del atributo deudaFacturada.
	 *
	 * @return retorna el valor del atributo deudaFacturada.
	 */
	public String getDeudaFacturada() {
		return deudaFacturada;
	}

	/**
	 * Permite setear el valor del atributo deudaFacturada.
	 *
	 * @param newdeudaFacturada nuevo valor para el atributo deudaFacturada.
	 */
	public void setDeudaFacturada(final String newdeudaFacturada) {
		this.deudaFacturada = newdeudaFacturada;
	}

	/**
	 * Permite obtener el valor del atributo deudaFacturadaFormateada.
	 *
	 * @return retorna el valor del atributo deudaFacturadaFormateada.
	 */
	public String getDeudaFacturadaFormateada() {
		return deudaFacturadaFormateada;
	}

	/**
	 * Permite setear el valor del atributo deudaFacturadaFormateada.
	 *
	 * @param newdeudaFacturadaFormateada nuevo valor para el atributo
	 *                                    deudaFacturadaFormateada.
	 */
	public void setDeudaFacturadaFormateada(final String newdeudaFacturadaFormateada) {
		this.deudaFacturadaFormateada = newdeudaFacturadaFormateada;
	}

	/**
	 * Permite obtener el valor del atributo descripcionDeudaActual.
	 *
	 * @return retorna el valor del atributo descripcionDeudaActual.
	 */
	public String getDescripcionDeudaActual() {
		return descripcionDeudaActual;
	}

	/**
	 * Permite setear el valor del atributo descripcionDeudaActual.
	 *
	 * @param newdescripcionDeudaActual nuevo valor para el atributo
	 *                                  descripcionDeudaActual.
	 */
	public void setDescripcionDeudaActual(final String newdescripcionDeudaActual) {
		this.descripcionDeudaActual = newdescripcionDeudaActual;
	}

	/**
	 * Permite obtener el valor del atributo deudaActual.
	 *
	 * @return retorna el valor del atributo deudaActual.
	 */
	public String getDeudaActual() {
		return deudaActual;
	}

	/**
	 * Permite setear el valor del atributo deudaActual.
	 *
	 * @param newdeudaActual nuevo valor para el atributo deudaActual.
	 */
	public void setDeudaActual(final String newdeudaActual) {
		this.deudaActual = newdeudaActual;
	}

	/**
	 * Permite obtener el valor del atributo deudaActualFormateada.
	 *
	 * @return retorna el valor del atributo deudaActualFormateada.
	 */
	public String getDeudaActualFormateada() {
		return deudaActualFormateada;
	}

	/**
	 * Permite setear el valor del atributo deudaActualFormateada.
	 *
	 * @param newdeudaActualFormateada nuevo valor para el atributo
	 *                                 deudaActualFormateada.
	 */
	public void setDeudaActualFormateada(final String newdeudaActualFormateada) {
		this.deudaActualFormateada = newdeudaActualFormateada;
	}

	/**
	 * Permite obtener el valor del atributo link.
	 *
	 * @return retorna el valor del atributo link.
	 */
	public List<AccesoDirecto> getLinks() {
		return links;
	}

	/**
	 * Permite setear el valor del atributo link.
	 *
	 * @param link nuevo valor para el atributo link.
	 */
	public void setLinks(List<AccesoDirecto> links) {
		this.links = links;
	}

}
