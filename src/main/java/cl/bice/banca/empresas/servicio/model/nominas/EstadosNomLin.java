package cl.bice.banca.empresas.servicio.model.nominas;

/**
 * Clase para almacenar los registros obtenidos desde el SP
 * POR_PKG_NOMINA_EN_LINEA.POR_SP_CONS_ESTADOS_NOMLIN
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class EstadosNomLin {
	private String codNomina;
	private String rutUsuario;
	private String codEstado;
	private String fecEstado;
	private String flgEstadoActual;
	private String nomUsuario;

	/**
	 * @return the codNomina
	 */
	public String getCodNomina() {
		return codNomina;
	}

	/**
	 * @param codNomina the codNomina to set
	 */
	public void setCodNomina(String codNomina) {
		this.codNomina = codNomina;
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
	 * @return the codEstado
	 */
	public String getCodEstado() {
		return codEstado;
	}

	/**
	 * @param codEstado the codEstado to set
	 */
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}

	/**
	 * @return the fecEstado
	 */
	public String getFecEstado() {
		return fecEstado;
	}

	/**
	 * @param fecEstado the fecEstado to set
	 */
	public void setFecEstado(String fecEstado) {
		this.fecEstado = fecEstado;
	}

	/**
	 * @return the flgEstadoActual
	 */
	public String getFlgEstadoActual() {
		return flgEstadoActual;
	}

	/**
	 * @param flgEstadoActual the flgEstadoActual to set
	 */
	public void setFlgEstadoActual(String flgEstadoActual) {
		this.flgEstadoActual = flgEstadoActual;
	}

	/**
	 * @return the nomUsuario
	 */
	public String getNomUsuario() {
		return nomUsuario;
	}

	/**
	 * @param nomUsuario the nomUsuario to set
	 */
	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}
}
