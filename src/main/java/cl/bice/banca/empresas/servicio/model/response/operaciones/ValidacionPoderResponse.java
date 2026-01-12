package cl.bice.banca.empresas.servicio.model.response.operaciones;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.bice.banca.empresas.servicio.model.response.Estado;

/**
 * Clase que contiene los datos del request para el servicio ValidaPoderEmpresa.
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class ValidacionPoderResponse {
	/**
	 * Estado.
	 */
	@JsonProperty("estado")
	private Estado estado;
	/**
	 * Respuesta a retornar.
	 */
	@JsonProperty("respuesta")
	private ValidacionPoderResp respuesta;

	/**
	 * @return the estado
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * @return the respuesta
	 */
	public ValidacionPoderResp getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(ValidacionPoderResp respuesta) {
		this.respuesta = respuesta;
	}

}
