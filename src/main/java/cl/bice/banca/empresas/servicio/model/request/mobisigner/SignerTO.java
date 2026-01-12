package cl.bice.banca.empresas.servicio.model.request.mobisigner;

/**
 * Firmantes, parte de la data para invocar al servicio que crea desaf&iacute;o
 * FDA del servicio mobisigner.
 * 
 * @author Cristian Pais
 *
 */
public class SignerTO {
	/**
	 * Email
	 */
	private String email;
	/**
	 * Id de usuario = rut usuario
	 */
	private String userId;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
