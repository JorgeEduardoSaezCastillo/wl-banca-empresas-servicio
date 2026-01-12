
package cl.bice.banca.empresas.servicio.model.response.bia;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "interesUsoLinea", "fechaSaldo" })
public class SobreGiro {

	@JsonProperty("interesUsoLinea")
	private String interesUsoLinea;
	@JsonProperty("fechaSaldo")
	private String fechaSaldo;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	@JsonProperty("interesUsoLinea")
	public String getInteresUsoLinea() {
		return interesUsoLinea;
	}

	@JsonProperty("interesUsoLinea")
	public void setInteresUsoLinea(String interesUsoLinea) {
		this.interesUsoLinea = interesUsoLinea;
	}

	@JsonProperty("fechaSaldo")
	public String getFechaSaldo() {
		return fechaSaldo;
	}

	@JsonProperty("fechaSaldo")
	public void setFechaSaldo(String fechaSaldo) {
		this.fechaSaldo = fechaSaldo;
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
