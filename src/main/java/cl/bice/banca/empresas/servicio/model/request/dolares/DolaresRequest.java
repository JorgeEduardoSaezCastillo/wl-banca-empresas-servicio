
package cl.bice.banca.empresas.servicio.model.request.dolares;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "dispositivo", "token", "rut" })
public class DolaresRequest {

	@JsonProperty("dispositivo")
	private String dispositivo;
	@JsonProperty("token")
	private String token;
	@JsonProperty("rut")
	private String rut;

	@JsonProperty("dispositivo")
	public String getDispositivo() {
		return dispositivo;
	}

	@JsonProperty("dispositivo")
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	@JsonProperty("token")
	public String getToken() {
		return token;
	}

	@JsonProperty("token")
	public void setToken(String token) {
		this.token = token;
	}

	@JsonProperty("rut")
	public String getRut() {
		return rut;
	}

	@JsonProperty("rut")
	public void setRut(String rut) {
		this.rut = rut;
	}

}
