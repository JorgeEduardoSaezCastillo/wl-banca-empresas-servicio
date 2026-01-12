package cl.bice.banca.empresas.servicio.model.common;

/**
 * Clase que contiene los datos del estado de la peticion.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
public class EstadoResp {

	/**
	 * Codigo del estado.
	 */
	private String codigoEstado;

	/**
	 * Detalle del estado.
	 */
	private String glosaEstado;

	/**
	 * Permite obtener el valor del atributo codigoEstado.
	 *
	 * @return retorna el valor del atributo codigoEstado.
	 */
	public final String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * Permite setear el valor del atributo codigoEstado.
	 *
	 * @param newCodigoEstado nuevo valor para el atributo codigoEstado.
	 */
	public final void setCodigoEstado(final String newCodigoEstado) {
		this.codigoEstado = newCodigoEstado;
	}

	/**
	 * Permite obtener el valor del atributo glosaEstado.
	 *
	 * @return retorna el valor del atributo glosaEstado.
	 */
	public final String getGlosaEstado() {
		return glosaEstado;
	}

	/**
	 * Permite setear el valor del atributo glosaEstado.
	 *
	 * @param newGlosaEstado nuevo valor para el atributo glosaEstado.
	 */
	public final void setGlosaEstado(final String newGlosaEstado) {
		this.glosaEstado = newGlosaEstado;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "EstadoResp [codigoEstado=" + codigoEstado + ", glosaEstado=" + glosaEstado + "]";
	}

}
