package cl.bice.banca.empresas.servicio.model.common;

public class UsuarioCliente {	
	private String rutCliente;
	private String rutUsuario;
	private String flgPrototipo;
	private String flgAdmDelegado;
	private String flgAdmBanco;
	private String flgSuperAdmBanco;
	private String flgActivo;
	private String flgInicio;
	private String flgAdmBancoSucursales;
	private String flgProduccion;
	private String flgDesarrollo;
	private String flgAdmDelegadoPersona;
	private String flgApoderado;
	
	/**
	 * @return the rutCliente
	 */
	public String getRutCliente() {
		return rutCliente;
	}
	/**
	 * @param rutCliente the rutCliente to set
	 */
	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
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
	 * @return the flgPrototipo
	 */
	public String getFlgPrototipo() {
		return flgPrototipo;
	}
	/**
	 * @param flgPrototipo the flgPrototipo to set
	 */
	public void setFlgPrototipo(String flgPrototipo) {
		this.flgPrototipo = flgPrototipo;
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
	/**
	 * @return the flgAdmBanco
	 */
	public String getFlgAdmBanco() {
		return flgAdmBanco;
	}
	/**
	 * @param flgAdmBanco the flgAdmBanco to set
	 */
	public void setFlgAdmBanco(String flgAdmBanco) {
		this.flgAdmBanco = flgAdmBanco;
	}
	/**
	 * @return the flgSuperAdmBanco
	 */
	public String getFlgSuperAdmBanco() {
		return flgSuperAdmBanco;
	}
	/**
	 * @param flgSuperAdmBanco the flgSuperAdmBanco to set
	 */
	public void setFlgSuperAdmBanco(String flgSuperAdmBanco) {
		this.flgSuperAdmBanco = flgSuperAdmBanco;
	}
	/**
	 * @return the flgActivo
	 */
	public String getFlgActivo() {
		return flgActivo;
	}
	/**
	 * @param flgActivo the flgActivo to set
	 */
	public void setFlgActivo(String flgActivo) {
		this.flgActivo = flgActivo;
	}
	/**
	 * @return the flgInicio
	 */
	public String getFlgInicio() {
		return flgInicio;
	}
	/**
	 * @param flgInicio the flgInicio to set
	 */
	public void setFlgInicio(String flgInicio) {
		this.flgInicio = flgInicio;
	}
	/**
	 * @return the flgAdmBancoSucursales
	 */
	public String getFlgAdmBancoSucursales() {
		return flgAdmBancoSucursales;
	}
	/**
	 * @param flgAdmBancoSucursales the flgAdmBancoSucursales to set
	 */
	public void setFlgAdmBancoSucursales(String flgAdmBancoSucursales) {
		this.flgAdmBancoSucursales = flgAdmBancoSucursales;
	}
	/**
	 * @return the flgProduccion
	 */
	public String getFlgProduccion() {
		return flgProduccion;
	}
	/**
	 * @param flgProduccion the flgProduccion to set
	 */
	public void setFlgProduccion(String flgProduccion) {
		this.flgProduccion = flgProduccion;
	}
	/**
	 * @return the flgDesarrollo
	 */
	public String getFlgDesarrollo() {
		return flgDesarrollo;
	}
	/**
	 * @param flgDesarrollo the flgDesarrollo to set
	 */
	public void setFlgDesarrollo(String flgDesarrollo) {
		this.flgDesarrollo = flgDesarrollo;
	}
	/**
	 * @return the flgAdmDelegadoPersona
	 */
	public String getFlgAdmDelegadoPersona() {
		return flgAdmDelegadoPersona;
	}
	/**
	 * @param flgAdmDelegadoPersona the flgAdmDelegadoPersona to set
	 */
	public void setFlgAdmDelegadoPersona(String flgAdmDelegadoPersona) {
		this.flgAdmDelegadoPersona = flgAdmDelegadoPersona;
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
	
	public boolean isApoderado() {
		return (null != this.flgApoderado && this.flgApoderado.equals("1"));
	}
}