
package cl.bice.banca.empresas.servicio.model.response.bia;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import cl.bice.banca.empresas.servicio.model.response.Estado;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "estado", "sobreGiro" })
public class SobreGiroResponse {

	@JsonProperty("estado")
	private Estado estado;
	@JsonProperty("sobreGiro")
	private SobreGiro sobreGiro;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("estado")
	public Estado getEstado() {
		return estado;
	}

	@JsonProperty("estado")
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@JsonProperty("sobreGiro")
	public SobreGiro getSobreGiro() {
		return sobreGiro;
	}

	@JsonProperty("sobreGiro")
	public void setSobreGiro(SobreGiro sobreGiro) {
		this.sobreGiro = sobreGiro;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
