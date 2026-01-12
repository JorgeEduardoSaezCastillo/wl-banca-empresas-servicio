package cl.bice.banca.empresas.servicio.model.response.empresas;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.bice.banca.empresas.servicio.model.response.Estado;

/**
 * Operaciones pendientes de aprobar o liberar
 * 
 * @author Cristian Pais
 *
 */
public class OperacionesAprobarLiberarResponse {
	/**
	 * Estatus
	 */
	@JsonProperty("estado")
	private Estado estatus;
	/**
	 * Operaciones para aprobar/liberar
	 */
	@JsonProperty("respuesta")
	private OperacionesAprobarLiberar operaciones;

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
	 * @return the operaciones
	 */
	public OperacionesAprobarLiberar getOperaciones() {
		return operaciones;
	}

	/**
	 * @param operaciones the operaciones to set
	 */
	public void setOperaciones(OperacionesAprobarLiberar operaciones) {
		this.operaciones = operaciones;
	}

}
