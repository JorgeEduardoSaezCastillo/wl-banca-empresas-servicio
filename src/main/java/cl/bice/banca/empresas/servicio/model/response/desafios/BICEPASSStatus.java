package cl.bice.banca.empresas.servicio.model.response.desafios;

public enum BICEPASSStatus {
	/**
	 * Validaci&oacute;n exitosa
	 */
	CONFIRM("CONFIRM"),
	/**
	 * Cliente presiona cancelar
	 */
	CANCEL("CANCEL"),
	/**
	 * Dudosa
	 */
	CONCERN("CONCERN"),
	/**
	 * Cliente a&uacute;n no termina de validar
	 */
	NOT_FOUND("NOT_FOUND");

	/**
	 * id status BICEPASS
	 */
	private String id;

	/**
	 * 
	 * @param id
	 */
	BICEPASSStatus(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return id status BICEPASS
	 */
	public String id() {
		return id;
	}

}
