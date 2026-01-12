
package cl.bice.banca.empresas.servicio.model.response.dolares;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "respuesta" })
public class DolaresResponse {

	@JsonProperty("respuesta")
	private Respuesta respuesta;

	@JsonProperty("respuesta")
	public Respuesta getRespuesta() {
		return respuesta;
	}

	@JsonProperty("respuesta")
	public void setRespuesta(Respuesta respuesta) {
		this.respuesta = respuesta;
	}

}
