package cl.bice.banca.empresas.servicio.model.response.desafios;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.bice.banca.empresas.servicio.model.response.Estado;

/**
 * Respuesta del servicio ValidarEstadoConfirmacion
 * 
 * @author Cristian Pais
 *
 */
public class ValidarEstadoConfirmacionResponse {
	/**
	 * Estatus.
	 */
	@JsonProperty("estado")
	private Estado estatus;
	/**
	 * Datos de la respuesta
	 */
	@JsonProperty("respuesta")
	private ValidarEstadoConfirmacionResp respuesta;

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
	 * @return the respuesta
	 */
	public ValidarEstadoConfirmacionResp getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(ValidarEstadoConfirmacionResp respuesta) {
		this.respuesta = respuesta;
	}

}
