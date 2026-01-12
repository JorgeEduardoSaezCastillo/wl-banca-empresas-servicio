package cl.bice.banca.empresas.servicio.model.response.base;

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
public class ResponseBase {
	/**
	 * Estado.
	 */
	@JsonProperty("estado")
	private Estado estado;
	/**
	 * Respuesta a retornar.
	 */
	@JsonProperty("respuesta")
	private Respuesta respuesta;

	/**
	 * @return the estado
	 */
	@JsonProperty("estado")
	public Estado getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	@JsonProperty("estado")
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	/**
	 * Retorma el valor del atributo.
	 *
	 * @return Respuesta con los estados.
	 */
	@JsonProperty("respuesta")
	public Respuesta getRespuesta() {
		return respuesta;
	}

	/**
	 * Asigna un valor al atributo.
	 *
	 * @param respuesta Respuesta a asignar.
	 */
	@JsonProperty("respuesta")
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public String toString() {
		return "ResponseBase [respuesta=" + respuesta + ", getRespuesta()=" + getRespuesta() + "]";
	}

}
