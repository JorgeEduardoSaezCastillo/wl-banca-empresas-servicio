package cl.bice.banca.empresas.servicio.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import cl.bice.banca.empresas.servicio.model.response.base.Respuesta;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;

/**
 * Estado de la consulta.
 *
 * @author Francisco Mendoza.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "codigoEstado", "glosaEstado" })
public class Estado extends Respuesta {
	
	public Estado() {
		super();
	}

	public Estado(String codigoEstado, String glosaEstado) {
		super();
		this.codigoEstado = codigoEstado;
		this.glosaEstado = glosaEstado;
	}

	public Estado(String codigoEstado, String glosaEstado, String propertiesGlosa) {
		super();
		this.codigoEstado = codigoEstado;
		this.glosaEstado = glosaEstado;
		this.propertiesGlosa = propertiesGlosa;
	}

	/**
	 * Codigo del estado.
	 */
	@JsonProperty("codigoEstado")
	private String codigoEstado;

	/**
	 * Glosa del estado.
	 */
	@JsonProperty("glosaEstado")
	private String glosaEstado;

	@JsonIgnore
	private String propertiesGlosa;
	/**
	 * Retorna el valor del atributo.
	 * 
	 * @return Codigo de estado.
	 */
	@JsonProperty("codigoEstado")
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * Asigna un valor al atributo.
	 * 
	 * @param codigoEstado Valor de codigo estado.
	 */
	@JsonProperty("codigoEstado")
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	/**
	 * Retorna el valor del atributo.
	 *
	 * @return Glosa de estado.
	 */
	@JsonProperty("glosaEstado")
	public String getGlosaEstado() {
		return glosaEstado;
	}

	/**
	 * Asigna el valor del atributo.
	 * 
	 * @param glosaEstado Valor de glosa estado.
	 */
	@JsonProperty("glosaEstado")
	public void setGlosaEstado(String glosaEstado) {
		this.glosaEstado = glosaEstado;
	}
	
	public String getPropertiesGlosa() {
		return propertiesGlosa;
	}

	public void setPropertiesGlosa(String propertiesGlosa) {
		this.propertiesGlosa = propertiesGlosa;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Estado [codigoEstado=");
		builder.append(codigoEstado);
		builder.append(", glosaEstado=");
		builder.append(glosaEstado);
		builder.append(", additionalProperties=");
		builder.append(this.getAdditionalProperties());
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Método para consultar si el código de estado es 1 = EXITO
	 * 
	 * @return
	 */
	@JsonIgnore
	public boolean isEXITO() {
		return this.codigoEstado.equals(EstadoEnum.EXITO.getCodigoEstado());
	}
}
