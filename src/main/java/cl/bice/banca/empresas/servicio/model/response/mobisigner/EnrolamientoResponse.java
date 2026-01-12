package cl.bice.banca.empresas.servicio.model.response.mobisigner;

/**
 * Clase de respuesta del metodo UserExists del servicio mobisigner
 * 
 * @author Cristian Pais
 *
 */
public class EnrolamientoResponse {
	/**
	 * C&oacute;digo estado
	 */
	private int statusCode;
	/**
	 * Descripci&oacute;n estado.
	 */
	private String statusDescription;

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

	@Override
	public String toString() {
		return "Enrolamiento [statusCode=" + statusCode + ", statusDescription=" + statusDescription + "]";
	}

}
