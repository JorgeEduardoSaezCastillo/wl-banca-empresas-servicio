package cl.bice.banca.empresas.servicio.model.response.desafios;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.bice.banca.empresas.servicio.model.response.Estado;

/**
 * Respuesta del servicio ListarCrearDesafios
 * 
 * @author Cristian Pais
 *
 */
public class ListarCrearDesafiosResponse {
	/**
	 * Estatus.
	 */
	@JsonProperty("estado")
	private Estado estatus;
	/**
	 * Datos de la respuesta
	 */
	@JsonProperty("respuesta")
	private ListarCrearDesafiosResp respuesta;

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
	public ListarCrearDesafiosResp getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(ListarCrearDesafiosResp respuesta) {
		this.respuesta = respuesta;
	}

}
