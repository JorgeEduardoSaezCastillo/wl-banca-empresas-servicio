package cl.bice.banca.empresas.servicio.model.request;

import java.io.Serializable;

/**
 * Request para el servicio de Tarjetas de Credito.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class TarjetasRequest implements Serializable {

	/**
	 * Serie de la clase.
	 */
	private static final long serialVersionUID = 5067777171714292560L;

	/**
	 * Rut del cliente.
	 */
	private String rut;

	/**
	 * Numero de la tarjeta.
	 */
	private String numeroTarjeta;

	/**
	 * Permite obtener el valor del atributo rut.
	 *
	 * @return retorna el valor del atributo rut.
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * Permite setear el valor del atributo rut.
	 *
	 * @param rut nuevo valor para el atributo rut.
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * Permite obtener el valor del atributo numeroTarjeta.
	 *
	 * @return retorna el valor del atributo numeroTarjeta.
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * Permite setear el valor del atributo numeroTarjeta.
	 *
	 * @param numeroTarjeta nuevo valor para el atributo numeroTarjeta.
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

}
