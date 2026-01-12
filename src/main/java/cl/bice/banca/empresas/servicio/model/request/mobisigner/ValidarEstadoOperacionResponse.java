package cl.bice.banca.empresas.servicio.model.request.mobisigner;

/**
 * Clase que contiene los datos de la respuesta del metodo del servicio
 * mobisigner que consulta el estado del desaf&iacute;o
 * 
 * @author Cristian Pais
 *
 */
public class ValidarEstadoOperacionResponse {

	/**
	 * C&oacute;digo estado.
	 */
	private int statusCode;
	/**
	 * Descripci&oacute;n estado.
	 */
	private String statusDescription;
	/**
	 * C&oacute;digo estado transacci&oacute;n.
	 */
	private int transactionStatusCode;
	/**
	 * Descripci&oacute;n estado transacci&oacute;n.
	 */
	private String transactionStatusDescription;

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
	 * @return the transactionStatusCode
	 */
	public int getTransactionStatusCode() {
		return transactionStatusCode;
	}

	/**
	 * @param transactionStatusCode the transactionStatusCode to set
	 */
	public void setTransactionStatusCode(int transactionStatusCode) {
		this.transactionStatusCode = transactionStatusCode;
	}

	/**
	 * @return the transactionStatusDescription
	 */
	public String getTransactionStatusDescription() {
		return transactionStatusDescription;
	}

	/**
	 * @param transactionStatusDescription the transactionStatusDescription to set
	 */
	public void setTransactionStatusDescription(String transactionStatusDescription) {
		this.transactionStatusDescription = transactionStatusDescription;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ValidarEstadoOperacionResponse [statusCode=" + statusCode + ", statusDescription=" + statusDescription
				+ ", transactionStatusCode=" + transactionStatusCode + ", transactionStatusDescription="
				+ transactionStatusDescription + "]";
	}

}
