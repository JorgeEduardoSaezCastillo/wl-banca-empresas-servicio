package cl.bice.banca.empresas.servicio.model.request.empresas;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

public class OperacionesAprobarLiberarRequest extends BaseRequestEmpresa {
	/**
	 * C&oacute;digo de servicio
	 */
	private String codigoServicio;
	
	/**
	 * Fecha desde rango consulta operaciones
	 */
	private String fechaDesde;
	
	/**
	 * Fecha hasta rango consulta operaciones
	 */
	private String fechaHasta;

	/**
	 * @return the codigoServicio
	 */
	public String getCodigoServicio() {
		return codigoServicio;
	}

	/**
	 * @param codigoServicio the codigoServicio to set
	 */
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}
