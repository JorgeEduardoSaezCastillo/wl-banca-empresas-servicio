package cl.bice.banca.empresas.servicio.model.response.operaciones;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import cl.bice.banca.empresas.servicio.model.response.Estado;

/**
 * Objeto raiz de la respuesta del servicio web.
 *
 * @author Francisco Mendoza.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "estado" })
public class AprobarOperacionesResponse {
	/**
	 * Estado.
	 */
	@JsonProperty("estado")
	private Estado estado;
	/**
	 * Respuesta a retornar.
	 */
	@JsonProperty("respuesta")
	private AprobarOperacionesResp respuesta;

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
	public AprobarOperacionesResp getRespuesta() {
		return respuesta;
	}

	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(AprobarOperacionesResp respuesta) {
		this.respuesta = respuesta;
	}

}
