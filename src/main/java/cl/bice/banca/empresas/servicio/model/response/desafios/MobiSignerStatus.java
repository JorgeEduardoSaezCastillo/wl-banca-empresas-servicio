package cl.bice.banca.empresas.servicio.model.response.desafios;

public enum MobiSignerStatus {
	/**
	 * Firma pendiente
	 */
	FIRMA_PENDIENTE(1),
	/**
	 * Firma completada
	 */
	FIRMA_COMPLETADA(2),
	/**
	 * Firma rechazada
	 */
	FIRMA_RECHAZADA(3),
	/**
	 * Firma caducada
	 */
	FIRMA_CADUCADA(4);
	/**
	 * id status mobisigner
	 */
	private int id;

	/**
	 * 
	 * @param id
	 */
	MobiSignerStatus(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return id status mobisigner
	 */
	public int id() {
		return id;
	}

}
