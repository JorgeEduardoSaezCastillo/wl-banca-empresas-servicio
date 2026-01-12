package cl.bice.banca.empresas.servicio.configure;

/**
 * Configuration base
 * 
 * @author Tinet
 *
 */
public class ConfigurationBase {
	/**
	 * username.
	 */
	protected String username;

	/**
	 * password.
	 */
	protected String password;

	/**
	 * url.
	 */
	protected String url;

	/**
	 * Permite obtener el valor del atributo username.
	 *
	 * @return the username value.
	 */
	public final String getUsername() {
		return this.username;
	}

	/**
	 * Permite establecer el valor del atributo username.
	 *
	 * @param username new value for username attribute.
	 */
	public final void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Permite obtener el valor del atributo password.
	 *
	 * @return the password value.
	 */
	public final String getPassword() {
		return this.password;
	}

	/**
	 * Permite establecer el valor del atributo password.
	 *
	 * @param password new value for password attribute.
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Permite obtener el valor del atributo url.
	 *
	 * @return the url value.
	 */
	public final String getUrl() {
		return this.url;
	}

	/**
	 * Permite establecer el valor del atributo url.
	 *
	 * @param url new value for url attribute.
	 */
	public final void setUrl(String url) {
		this.url = url;
	}

}
