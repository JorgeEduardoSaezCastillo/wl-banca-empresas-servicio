package cl.bice.banca.empresas.servicio.model.response;

import java.io.Serializable;

/**
 * Clase Estatus del servicio tarjeta.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class Estatus implements Serializable {
	/**
	 * Serie de la clase.
	 */
	private static final long serialVersionUID = 8120890118207172560L;

	/**
	 * Codigo Estado.
	 */
	private String codigoEstado;

	/**
	 * Glosa Estado.
	 */
	private String glosaEstado;

	/**
	 * Permite obtener el valor del atributo codigoEstado.
	 *
	 * @return retorna el valor del atributo codigoEstado.
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * Permite setear el valor del atributo codigoEstado.
	 *
	 * @param codigoEstado nuevo valor para el atributo codigoEstado.
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	/**
	 * Permite obtener el valor del atributo glosaEstado.
	 *
	 * @return retorna el valor del atributo glosaEstado.
	 */
	public String getGlosaEstado() {
		return glosaEstado;
	}

	/**
	 * Permite setear el valor del atributo glosaEstado.
	 *
	 * @param glosaEstado nuevo valor para el atributo glosaEstado.
	 */
	public void setGlosaEstado(String glosaEstado) {
		this.glosaEstado = glosaEstado;
	}

}
