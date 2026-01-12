package cl.bice.banca.empresas.servicio.model.common;

/**
 * Clase para guardar datos de bancos
 * 
 * @author Fibacache
 *
 */
public class Banco {

	private String codBanco;
	private String codSwift;
	private String glsBanco;
	private String glsNomBanco;
	private String nomBanco;
	private String rutBanco;

	/**
	 * @return the codBanco
	 */
	public String getCodBanco() {
		return codBanco;
	}

	/**
	 * @param codBanco the codBanco to set
	 */
	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}

	/**
	 * @return the codSwift
	 */
	public String getCodSwift() {
		return codSwift;
	}

	/**
	 * @param codSwift the codSwift to set
	 */
	public void setCodSwift(String codSwift) {
		this.codSwift = codSwift;
	}

	/**
	 * @return the glsBanco
	 */
	public String getGlsBanco() {
		return glsBanco;
	}

	/**
	 * @param glsBanco the glsBanco to set
	 */
	public void setGlsBanco(String glsBanco) {
		this.glsBanco = glsBanco;
	}

	/**
	 * @return the glsNomBanco
	 */
	public String getGlsNomBanco() {
		return glsNomBanco;
	}

	/**
	 * @param glsNomBanco the glsNomBanco to set
	 */
	public void setGlsNomBanco(String glsNomBanco) {
		this.glsNomBanco = glsNomBanco;
	}

	/**
	 * @return the nomBanco
	 */
	public String getNomBanco() {
		return nomBanco;
	}

	/**
	 * @param nomBanco the nomBanco to set
	 */
	public void setNomBanco(String nomBanco) {
		this.nomBanco = nomBanco;
	}

	/**
	 * @return the rutBanco
	 */
	public String getRutBanco() {
		return rutBanco;
	}

	/**
	 * @param rutBanco the rutBanco to set
	 */
	public void setRutBanco(String rutBanco) {
		this.rutBanco = rutBanco;
	}
}
