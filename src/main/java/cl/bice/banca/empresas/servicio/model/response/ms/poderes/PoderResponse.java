package cl.bice.banca.empresas.servicio.model.response.ms.poderes;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response para el microservicio de validacion de poderes
 * @author Marco Bello
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)

public class PoderResponse {

	/**
	 * Codigo de respuesta.
	 */
	@JsonProperty("codigo")
	private String codigo;
	
	/**
	 * Glosa respuesta.
	 */
	@JsonProperty("glosa")
	private String glosa;
	
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigoSalida(String codigo) {
		this.codigo = codigo;
	}
	
	public String getGlosa() {
		return glosa;
	}
	
	public void setGlosaSalida(String glosa) {
		this.glosa = glosa;
	}
	

}
