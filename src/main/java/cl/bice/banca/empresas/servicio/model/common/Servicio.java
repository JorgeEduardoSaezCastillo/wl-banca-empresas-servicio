package cl.bice.banca.empresas.servicio.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "codigoServicio" })
public class Servicio {
	@JsonProperty("codigoServicio")
	private String codigoServicio;
	@JsonProperty("glosaServicio")
	private String glosaServicio;

	/**
	 * @return the codigoServicio
	 */
	public String getCodigoServicio() {
		return codigoServicio;
	}

	/**
	 * @param codigoServicio the codigoServicio to set
	 */
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	/**
	 * @return the glosaServicio
	 */
	public String getGlosaServicio() {
		return glosaServicio;
	}

	/**
	 * @param glosaServicio the glosaServicio to set
	 */
	public void setGlosaServicio(String glosaServicio) {
		this.glosaServicio = glosaServicio;
	}
}
