package cl.bice.banca.empresas.servicio.model.response.empresas;

/**
 * Cuenta de una empresa
 * 
 * @author Cristian Pais
 *
 */
public class Cuenta {
	/**
	 * número de cuenta
	 */
	private String numCuenta;
	
	/**
	 * número de cuenta original
	 */
	private String numCuentaBase;
	
	/**
	 * rut empresa
	 */
	private String rutEmpresa;
	/**
	 * rut usuario
	 */
	private String rutUsuario;
	/**
	 * alias
	 */
	private String alias;
	/**
	 * flag apoderado
	 */
	private String flgApoderado;
	/**
	 * flag adm delegado
	 */
	private String flgAdmDelegado;

	/**
	 * @return the numCuenta
	 */
	public String getNumCuenta() {
		return numCuenta;
	}

	/**
	 * @param numCuenta the numCuenta to set
	 */
	public void setNumCuenta(String numCuenta) {
		this.numCuenta = numCuenta;
	}
	
	/**
	 * @return the numCuentaBase
	 */
	public String getNumCuentaBase() {
		return numCuentaBase;
	}

	/**
	 * @param numCuenta the numCuenta to set
	 */
	public void setNumCuentaBase(String numCuentaBase) {
		this.numCuentaBase = numCuentaBase;
	}

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
	 * @return the rutUsuario
	 */
	public String getRutUsuario() {
		return rutUsuario;
	}

	/**
	 * @param rutUsuario the rutUsuario to set
	 */
	public void setRutUsuario(String rutUsuario) {
		this.rutUsuario = rutUsuario;
	}

	/**
	 * @return the alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * @param alias the alias to set
	 */
	public void setAlias(String alias) {
		this.alias = alias;
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
