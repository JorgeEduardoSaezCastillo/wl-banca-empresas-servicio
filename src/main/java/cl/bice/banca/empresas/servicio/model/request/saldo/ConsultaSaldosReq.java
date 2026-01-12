package cl.bice.banca.empresas.servicio.model.request.saldo;

import com.fasterxml.jackson.annotation.JsonInclude;

import cl.bice.banca.empresas.servicio.model.common.BaseRequest;

/**
 * Clase que contiene los parametros de entrada del servicio rest.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConsultaSaldosReq extends BaseRequest {

	/**
	 * Codigo del producto a consultar.
	 */
	private String codigoProducto;

	/**
	 * Codigo del producto a consultar.
	 */
	private String cuenta;

	/**
	 * Codigo de la moneda del producto.
	 */
	private String moneda;

	public ConsultaSaldosReq() {
		super();
	}

	public ConsultaSaldosReq(String codigoProducto, String cuenta, String moneda, String dispositivo, String token,
			String rut) {
		super();
		this.codigoProducto = codigoProducto;
		this.cuenta = cuenta;
		this.moneda = moneda;
		super.setDispositivo(dispositivo);
		super.setToken(token);
		super.setRut(rut);
	}

	/**
	 * Permite obtener el valor del atributo codigoProducto.
	 *
	 * @return retorna el valor del atributo codigoProducto.
	 */
	public final String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * Permite setear el valor del atributo codigoProducto.
	 *
	 * @param newcodigoProducto nuevo valor para el atributo codigoProducto.
	 */
	public final void setCodigoProducto(final String newcodigoProducto) {
		this.codigoProducto = newcodigoProducto;
	}

	/**
	 * Permite obtener el valor del atributo cuenta.
	 *
	 * @return retorna el valor del atributo cuenta.
	 */
	public final String getCuenta() {
		return cuenta;
	}

	/**
	 * Permite setear el valor del atributo cuenta.
	 *
	 * @param newcuenta nuevo valor para el atributo cuenta.
	 */
	public final void setCuenta(final String newcuenta) {
		this.cuenta = newcuenta;
	}

	/**
	 * Permite obtener el valor del atributo moneda.
	 *
	 * @return retorna el valor del atributo moneda.
	 */
	public final String getMoneda() {
		return moneda;
	}

	/**
	 * Permite setear el valor del atributo moneda.
	 *
	 * @param newmoneda nuevo valor para el atributo moneda.
	 */
	public final void setMoneda(final String newmoneda) {
		this.moneda = newmoneda;
	}

}
