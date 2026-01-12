package cl.bice.banca.empresas.servicio.model.request.mobisigner;

/**
 * Clase que contiene los datos del request para invocar al metodo que valida el
 * estado del desaf&iacute;o FDA del servicio mobisigner.
 * 
 * @author Cristian Pais
 *
 */
public class ValidarStatusOperacionRequest {

	/**
	 * Identificador transacci&oacute;n
	 */
	private String transactionId;

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

}
