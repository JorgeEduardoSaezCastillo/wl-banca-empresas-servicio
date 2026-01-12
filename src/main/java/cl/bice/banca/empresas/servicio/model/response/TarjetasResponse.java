package cl.bice.banca.empresas.servicio.model.response;

import java.io.Serializable;

/**
 * Response Servicio Tarjeta.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class TarjetasResponse implements Serializable {

	/**
	 * Seria de la clase.
	 */
	private static final long serialVersionUID = -4865695520220241686L;

	/**
	 * Estatus.
	 */
	private Estatus estatus;

	/**
	 * Respuesta.
	 */
	private RespuestaTarjeta respuesta;

	/**
	 * Permite obtener el valor del atributo estatus.
	 *
	 * @return retorna el valor del atributo estatus.
	 */
	public Estatus getEstatus() {
		return estatus;
	}

	/**
	 * Permite setear el valor del atributo estatus.
	 *
	 * @param estatus nuevo valor para el atributo estatus.
	 */
	public void setEstatus(Estatus estatus) {
		this.estatus = estatus;
	}

	/**
	 * Permite obtener el valor del atributo respuesta.
	 *
	 * @return retorna el valor del atributo respuesta.
	 */
	public RespuestaTarjeta getRespuesta() {
		return respuesta;
	}

	/**
	 * Permite setear el valor del atributo respuesta.
	 *
	 * @param respuesta nuevo valor para el atributo respuesta.
	 */
	public void setRespuesta(RespuestaTarjeta respuesta) {
		this.respuesta = respuesta;
	}

}
