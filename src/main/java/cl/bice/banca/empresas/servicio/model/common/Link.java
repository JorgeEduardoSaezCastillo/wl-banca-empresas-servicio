package cl.bice.banca.empresas.servicio.model.common;

/**
 * Clase con la informacion del link.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class Link {

	/**
	 * Url de la tarjeta.
	 */
	private String url;

	/**
	 * texto de la tarjeta.
	 */
	private String texto;

	/**
	 * Codigo del servicio.
	 */
	private String codServicio;

	/**
	 * Permite obtener el valor del atributo url.
	 *
	 * @return retorna el valor del atributo url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Permite setear el valor del atributo url.
	 *
	 * @param url nuevo valor para el atributo url.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Permite obtener el valor del atributo texto.
	 *
	 * @return retorna el valor del atributo texto.
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * Permite setear el valor del atributo texto.
	 *
	 * @param texto nuevo valor para el atributo texto.
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * Permite obtener el valor del atributo codServicio.
	 *
	 * @return retorna el valor del atributo codServicio.
	 */
	public String getCodServicio() {
		return codServicio;
	}

	/**
	 * Permite setear el valor del atributo codServicio.
	 *
	 * @param codServicio nuevo valor para el atributo codServicio.
	 */
	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}

}
