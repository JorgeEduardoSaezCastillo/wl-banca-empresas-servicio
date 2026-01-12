package cl.bice.banca.empresas.servicio.model.response.operaciones;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Respuesta del servicio validaPoderEmpresa
 * 
 * @author Cristian Pais (TINET)
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "CodigoEstadoOperacion" })
public class ValidacionPoderResp {

	@JsonProperty("codigoEstadoOperacion")
	private int codigoEstadoOperacion;
	@JsonProperty("glosaEstadoOperacion")
	private String glosaEstadoOperacion;

	/**
	 * @return the codigoEstadoOperacion
	 */
	public int getCodigoEstadoOperacion() {
		return codigoEstadoOperacion;
	}

	/**
	 * @param codigoEstadoOperacion the codigoEstadoOperacion to set
	 */
	public void setCodigoEstadoOperacion(int codigoEstadoOperacion) {
		this.codigoEstadoOperacion = codigoEstadoOperacion;
	}

	/**
	 * @return the glosaEstadoOperacion
	 */
	public String getGlosaEstadoOperacion() {
		return glosaEstadoOperacion;
	}

	/**
	 * @param glosaEstadoOperacion the glosaEstadoOperacion to set
	 */
	public void setGlosaEstadoOperacion(String glosaEstadoOperacion) {
		this.glosaEstadoOperacion = glosaEstadoOperacion;
	}

}
