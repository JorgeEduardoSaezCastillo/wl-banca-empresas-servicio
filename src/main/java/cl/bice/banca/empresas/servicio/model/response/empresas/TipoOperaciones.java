package cl.bice.banca.empresas.servicio.model.response.empresas;

/**
 * Tipo de operaciones
 * 
 * @author Cristian Pais
 *
 */
public class TipoOperaciones {
	/**
	 * c&oacute;digo de servicio
	 */
	private String codServicio;
	/**
	 * Glosa de c&oacute;digo de servicio
	 */
	private String glosaCodigoServicio;
	/**
	 * cantidad de operaciones
	 */
	private int totalOperaciones;
	/**
	 * cantidad de aprobaciones
	 */
	private int aprobaciones;
	/**
	 * cantidad de liberaciones
	 */
	private int liberaciones;
	/**
	 * cantidad de empresas
	 */
	private int totalEmpresas;

	/**
	 * @return the codServicio
	 */
	public String getCodServicio() {
		return codServicio;
	}

	/**
	 * @param codServicio the codServicio to set
	 */
	public void setCodServicio(String codServicio) {
		this.codServicio = codServicio;
	}

	/**
	 * @return the glosaCodigoServicio
	 */
	public String getGlosaCodigoServicio() {
		return glosaCodigoServicio;
	}

	/**
	 * @param glosaCodigoServicio the glosaCodigoServicio to set
	 */
	public void setGlosaCodigoServicio(String glosaCodigoServicio) {
		this.glosaCodigoServicio = glosaCodigoServicio;
	}

	/**
	 * @return the totalOperaciones
	 */
	public int getTotalOperaciones() {
		return totalOperaciones;
	}

	/**
	 * @param totalOperaciones the totalOperaciones to set
	 */
	public void setTotalOperaciones(int totalOperaciones) {
		this.totalOperaciones = totalOperaciones;
	}

	/**
	 * @return the aprobaciones
	 */
	public int getAprobaciones() {
		return aprobaciones;
	}

	/**
	 * @param aprobaciones the aprobaciones to set
	 */
	public void setAprobaciones(int aprobaciones) {
		this.aprobaciones = aprobaciones;
	}

	/**
	 * @return the liberaciones
	 */
	public int getLiberaciones() {
		return liberaciones;
	}

	/**
	 * @param liberaciones the liberaciones to set
	 */
	public void setLiberaciones(int liberaciones) {
		this.liberaciones = liberaciones;
	}

	/**
	 * @return the totalEmpresas
	 */
	public int getTotalEmpresas() {
		return totalEmpresas;
	}

	/**
	 * @param totalEmpresas the totalEmpresas to set
	 */
	public void setTotalEmpresas(int totalEmpresas) {
		this.totalEmpresas = totalEmpresas;
	}

}
