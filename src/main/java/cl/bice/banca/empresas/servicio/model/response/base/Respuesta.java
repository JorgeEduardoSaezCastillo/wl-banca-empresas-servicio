package cl.bice.banca.empresas.servicio.model.response.base;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Respuest general.
 *
 * @author Francisco Mendoza.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Respuesta {
	/**
	 * Propiedades adicionales.
	 */
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<>();

	/**
	 * Retorna el valor del atributo.
	 *
	 * @return Propiedades adicionales.
	 */
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	/**
	 * Asigna un valor al atributo.
	 * 
	 * @param name  Atributo nombre.
	 * @param value Atributo valor.
	 */
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Respuesta [");
		builder.append("additionalProperties=");
		builder.append(additionalProperties);
		builder.append("]");
		return builder.toString();
	}

}
