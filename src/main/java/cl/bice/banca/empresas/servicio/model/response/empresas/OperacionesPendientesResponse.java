package cl.bice.banca.empresas.servicio.model.response.empresas;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.bice.banca.empresas.servicio.model.response.Estado;

/**
 * Contiene un resumen de operaciones pendientes de aprobar y liberar
 * 
 * @author Cristian Pais
 *
 */
public class OperacionesPendientesResponse {
	/**
	 * Estatus.
	 */
	@JsonProperty("estado")
	private Estado estatus;
	/**
	 * Resumen de operaciones.
	 */
	@JsonProperty("resumenOperaciones")
	private ResumenOperaciones resumenOperaciones;

	/**
	 * @return the estatus
	 */
	public Estado getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(Estado estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the resumenOperaciones
	 */
	public ResumenOperaciones getResumenOperaciones() {
		return resumenOperaciones;
	}

	/**
	 * @param resumenOperaciones the resumenOperaciones to set
	 */
	public void setResumenOperaciones(ResumenOperaciones resumenOperaciones) {
		this.resumenOperaciones = resumenOperaciones;
	}
}
