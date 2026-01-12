
package cl.bice.banca.empresas.servicio.model.response.bia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "glosaActivoPasivo", "activoPasivo", "annoActual", "porcAnnoActual", "ytd", "porcYtd" })
public class DetallePortafolio {

	@JsonProperty("glosaActivoPasivo")
	private String glosaActivoPasivo;
	@JsonProperty("activoPasivo")
	private String activoPasivo;
	@JsonProperty("annoActual")
	private String annoActual;
	@JsonProperty("porcAnnoActual")
	private String porcAnnoActual;
	@JsonProperty("ytd")
	private String ytd;
	@JsonProperty("porcYtd")
	private String porcYtd;

	@JsonProperty("glosaActivoPasivo")
	public String getGlosaActivoPasivo() {
		return glosaActivoPasivo;
	}

	@JsonProperty("glosaActivoPasivo")
	public void setGlosaActivoPasivo(String glosaActivoPasivo) {
		this.glosaActivoPasivo = glosaActivoPasivo;
	}

	@JsonProperty("activoPasivo")
	public String getActivoPasivo() {
		return activoPasivo;
	}

	@JsonProperty("activoPasivo")
	public void setActivoPasivo(String activoPasivo) {
		this.activoPasivo = activoPasivo;
	}

	@JsonProperty("annoActual")
	public String getAnnoActual() {
		return annoActual;
	}

	@JsonProperty("annoActual")
	public void setAnnoActual(String annoActual) {
		this.annoActual = annoActual;
	}

	@JsonProperty("porcAnnoActual")
	public String getPorcAnnoActual() {
		return porcAnnoActual;
	}

	@JsonProperty("porcAnnoActual")
	public void setPorcAnnoActual(String porcAnnoActual) {
		this.porcAnnoActual = porcAnnoActual;
	}

	@JsonProperty("ytd")
	public String getYtd() {
		return ytd;
	}

	@JsonProperty("ytd")
	public void setYtd(String ytd) {
		this.ytd = ytd;
	}

	@JsonProperty("porcYtd")
	public String getPorcYtd() {
		return porcYtd;
	}

	@JsonProperty("porcYtd")
	public void setPorcYtd(String porcYtd) {
		this.porcYtd = porcYtd;
	}

}
