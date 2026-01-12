package cl.bice.banca.empresas.servicio.model.common;

public class ValidaPoderesResult {
	private int codValidaPoderes;
	private int numTipoPersona;
	private String codTipoUsuario;
	private int flgRegistro;
	private int flgFirma;
	private int flgValidaLs;

	/**
	 * @return the codValidaPoderes
	 */
	public int getCodValidaPoderes() {
		return codValidaPoderes;
	}

	/**
	 * @param codValidaPoderes the codValidaPoderes to set
	 */
	public void setCodValidaPoderes(int codValidaPoderes) {
		this.codValidaPoderes = codValidaPoderes;
	}

	/**
	 * @return the numTipoPersona
	 */
	public int getNumTipoPersona() {
		return numTipoPersona;
	}

	/**
	 * @param numTipoPersona the numTipoPersona to set
	 */
	public void setNumTipoPersona(int numTipoPersona) {
		this.numTipoPersona = numTipoPersona;
	}

	/**
	 * @return the codTipoUsuario
	 */
	public String getCodTipoUsuario() {
		return codTipoUsuario;
	}

	/**
	 * @param codTipoUsuario the codTipoUsuario to set
	 */
	public void setCodTipoUsuario(String codTipoUsuario) {
		this.codTipoUsuario = codTipoUsuario;
	}

	/**
	 * @return the flgRegistro
	 */
	public int getFlgRegistro() {
		return flgRegistro;
	}

	/**
	 * @param flgRegistro the flgRegistro to set
	 */
	public void setFlgRegistro(int flgRegistro) {
		this.flgRegistro = flgRegistro;
	}

	/**
	 * @return the flgFirma
	 */
	public int getFlgFirma() {
		return flgFirma;
	}

	/**
	 * @param flgFirma the flgFirma to set
	 */
	public void setFlgFirma(int flgFirma) {
		this.flgFirma = flgFirma;
	}

	/**
	 * @return the flgValidaLs
	 */
	public int getFlgValidaLs() {
		return flgValidaLs;
	}

	/**
	 * @param flgValidaLs the flgValidaLs to set
	 */
	public void setFlgValidaLs(int flgValidaLs) {
		this.flgValidaLs = flgValidaLs;
	}
}
