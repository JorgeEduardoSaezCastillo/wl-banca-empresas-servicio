package cl.bice.banca.empresas.servicio.model.response.mobisigner;

/**
 * Clase para la respuesta del metodo insertTransaction del servicio mobisigner.
 * 
 * @author Cristian Pais
 *
 */
public class TransactionResponse {
	/**
	 * C&oacute;digo de estado.
	 */
	private int statusCode;
	/**
	 * Descripci&oacute; de estado.
	 */
	private String statusDescription;
	/**
	 * Identificador de la transacci&oacute;n.
	 */
	private String transactionId;
	/**
	 * Token.
	 */
	private String token;

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusDescription
	 */
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * @param statusDescription the statusDescription to set
	 */
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Los atributos transactionId y token no van porque podr&iacute;an contener
	 * informaci&oacute;n cr&iacute;tica del usuario
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransactionResponse [statusCode=" + statusCode + ", statusDescription=" + statusDescription + "]";
	}

}
