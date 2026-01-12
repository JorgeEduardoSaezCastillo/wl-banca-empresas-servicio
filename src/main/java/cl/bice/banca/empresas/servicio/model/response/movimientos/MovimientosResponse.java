
package cl.bice.banca.empresas.servicio.model.response.movimientos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "respuesta" })
public class MovimientosResponse {

	@JsonProperty("respuesta")
	private ConsultaMovimientosNoFacturadosResp respuesta;

	@JsonProperty("respuesta")
	public ConsultaMovimientosNoFacturadosResp getRespuesta() {
		return respuesta;
	}

	@JsonProperty("respuesta")
	public void setRespuesta(ConsultaMovimientosNoFacturadosResp respuesta) {
		this.respuesta = respuesta;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "MovimientosResponse [respuesta=" + respuesta + "]";
	}

}
