package cl.bice.banca.empresas.servicio.model.request.mobisigner;

import java.util.List;
import java.util.Map;

/**
 * Clase que contiene los datos del request para invocar al metodo
 * insertTransaction del servicio mobisigner.
 * 
 * @author Cristian Pais
 *
 */
public class TransactionRequest {

	/**
	 * Tipo documento
	 */
	private int docType;
	/**
	 * Nombre
	 */
	private String name;
	/**
	 * Datos transacci&oacute;n
	 */
	private String transactionData;
	/**
	 * Firmantes
	 */
	private List<SignerTO> signers;
	/**
	 * Metadata
	 */
	private Map<String, String> metadata;

	/**
	 * @return the docType
	 */
	public int getDocType() {
		return docType;
	}

	/**
	 * @param docType the docType to set
	 */
	public void setDocType(int docType) {
		this.docType = docType;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the transactionData
	 */
	public String getTransactionData() {
		return transactionData;
	}

	/**
	 * @param transactionData the transactionData to set
	 */
	public void setTransactionData(String transactionData) {
		this.transactionData = transactionData;
	}

	/**
	 * @return the signers
	 */
	public List<SignerTO> getSigners() {
		return signers;
	}

	/**
	 * @param signers the signers to set
	 */
	public void setSigners(List<SignerTO> signers) {
		this.signers = signers;
	}

	/**
	 * @return the metadata
	 */
	public Map<String, String> getMetadata() {
		return metadata;
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

}
