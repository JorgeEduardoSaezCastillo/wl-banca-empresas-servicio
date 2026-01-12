package cl.bice.banca.empresas.servicio.model.common;

/**
 * clase que representa una empresa
 * 
 * @author Cristian Pais
 *
 */
public class Empresa {

	/**
	 * rut empresa
	 */
	private String rutEmpresa;

	/**
	 * nombre empresa
	 */
	private String nombreEmpresa;

	/**
	 * flag apoderado
	 */
	private String flgApoderado;

	/**
	 * flag preferido
	 */
	private String flgPreferido;

	/**
	 * flag adm delegado
	 */
	private String flgAdmDelegado;

	/**
	 * @return the rutEmpresa
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}

	/**
	 * @param rutEmpresa the rutEmpresa to set
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	/**
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param nombreEmpresa the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/**
	 * @return the flgApoderado
	 */
	public String getFlgApoderado() {
		return flgApoderado;
	}

	/**
	 * @param flgApoderado the flgApoderado to set
	 */
	public void setFlgApoderado(String flgApoderado) {
		this.flgApoderado = flgApoderado;
	}

	/**
	 * @return the flgPreferido
	 */
	public String getFlgPreferido() {
		return flgPreferido;
	}

	/**
	 * @param flgPreferido the flgPreferido to set
	 */
	public void setFlgPreferido(String flgPreferido) {
		this.flgPreferido = flgPreferido;
	}

	/**
	 * @return the flgAdmDelegado
	 */
	public String getFlgAdmDelegado() {
		return flgAdmDelegado;
	}

	/**
	 * @param flgAdmDelegado the flgAdmDelegado to set
	 */
	public void setFlgAdmDelegado(String flgAdmDelegado) {
		this.flgAdmDelegado = flgAdmDelegado;
	}

}
