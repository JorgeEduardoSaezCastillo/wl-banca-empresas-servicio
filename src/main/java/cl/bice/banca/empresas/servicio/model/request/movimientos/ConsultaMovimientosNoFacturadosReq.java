package cl.bice.banca.empresas.servicio.model.request.movimientos;

/**
 * Clase que contiene los datos de entrada del servicio rest para la consulta de
 * movimientos no facturados.
 * 
 * @author tinet
 *
 */
public class ConsultaMovimientosNoFacturadosReq {

	/**
	 * Codigo del dispositivo.
	 */
	private String dispositivo;

	/**
	 * Token de la sesion.
	 */
	private String token;

	/**
	 * Rut del cliente.
	 */
	private String rut;

	/**
	 * Numero de cuenta.
	 */
	private String cuenta;

	/**
	 * Permite obtener el valor del atributo dispositivo.
	 *
	 * @return retorna el valor del atributo dispositivo.
	 */
	public final String getDispositivo() {
		return dispositivo;
	}

	/**
	 * Permite setear el valor del atributo dispositivo.
	 *
	 * @param newDispositivo nuevo valor para el atributo dispositivo.
	 */
	public final void setDispositivo(final String newDispositivo) {
		this.dispositivo = newDispositivo;
	}

	/**
	 * Permite obtener el valor del atributo token.
	 *
	 * @return retorna el valor del atributo token.
	 */
	public final String getToken() {
		return token;
	}

	/**
	 * Permite setear el valor del atributo token.
	 *
	 * @param newToken nuevo valor para el atributo token.
	 */
	public final void setToken(final String newToken) {
		this.token = newToken;
	}

	/**
	 * Permite obtener el valor del atributo rut.
	 *
	 * @return retorna el valor del atributo rut.
	 */
	public final String getRut() {
		return rut;
	}

	/**
	 * Permite setear el valor del atributo rut.
	 *
	 * @param newRut newRut valor para el atributo rut.
	 */
	public final void setRut(final String newRut) {
		this.rut = newRut;
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
	 * @param newCuenta nuevo valor para el atributo cuenta.
	 */
	public final void setCuenta(final String newCuenta) {
		this.cuenta = newCuenta;
	}
}
