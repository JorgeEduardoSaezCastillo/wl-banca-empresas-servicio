package cl.bice.banca.empresas.servicio.model.response.empresas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Resumen de operaciones
 * 
 * @author Cristian Pais
 *
 */
public class ResumenOperaciones {
	/**
	 * el total de operaciones
	 */
	@JsonProperty("totalOperaciones")
	private int resumenTotalOperaciones;
	/**
	 * el total de aprobaciones
	 */
	@JsonProperty("aprobaciones")
	private int resumenAprobaciones;
	/**
	 * el total de liberaciones
	 */
	@JsonProperty("liberaciones")
	private int resumenLiberaciones;
	/**
	 * la cantidad total de empresas
	 */
	@JsonProperty("totalEmpresas")
	private int resumenTotalEmpresas;
	/**
	 * tipo de operaciones
	 */
	@JsonProperty("tipoOperaciones")
	private List<TipoOperaciones> tipoOperaciones;

	/**
	 * @return the totalOperaciones
	 */
	public int getResumenTotalOperaciones() {
		return resumenTotalOperaciones;
	}

	/**
	 * @param totalOperaciones the totalOperaciones to set
	 */
	public void setResumenTotalOperaciones(int totalOperaciones) {
		this.resumenTotalOperaciones = totalOperaciones;
	}

	/**
	 * @return the aprobaciones
	 */
	public int getResumenAprobaciones() {
		return resumenAprobaciones;
	}

	/**
	 * @param aprobaciones the aprobaciones to set
	 */
	public void setResumenAprobaciones(int aprobaciones) {
		this.resumenAprobaciones = aprobaciones;
	}

	/**
	 * @return the liberaciones
	 */
	public int getResumenLiberaciones() {
		return resumenLiberaciones;
	}

	/**
	 * @param liberaciones the liberaciones to set
	 */
	public void setResumenLiberaciones(int liberaciones) {
		this.resumenLiberaciones = liberaciones;
	}

	/**
	 * @return the totalEmpresas
	 */
	public int getResumenTotalEmpresas() {
		return resumenTotalEmpresas;
	}

	/**
	 * @param totalEmpresas the totalEmpresas to set
	 */
	public void setResumenTotalEmpresas(int totalEmpresas) {
		this.resumenTotalEmpresas = totalEmpresas;
	}

	/**
	 * @return the tipoOperaciones
	 */
	public List<TipoOperaciones> getTipoOperaciones() {
		return tipoOperaciones;
	}

	/**
	 * @param tipoOperaciones the tipoOperaciones to set
	 */
	public void setTipoOperaciones(List<TipoOperaciones> tipoOperaciones) {
		this.tipoOperaciones = tipoOperaciones;

		this.resumenAprobaciones = 0;
		this.resumenLiberaciones = 0;
		this.resumenTotalEmpresas = 0;

		if (this.tipoOperaciones != null) {
			for (TipoOperaciones tipoOp : tipoOperaciones) {
				this.resumenTotalOperaciones += tipoOp.getTotalOperaciones();
				this.resumenAprobaciones += tipoOp.getAprobaciones();
				this.resumenLiberaciones += tipoOp.getLiberaciones();
				this.resumenTotalEmpresas += tipoOp.getTotalEmpresas();
			}
		}
	}
}
