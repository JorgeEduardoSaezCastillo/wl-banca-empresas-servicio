
package cl.bice.banca.empresas.servicio.model.response.bia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "totalActivo", "totalPasivo", "patrimonio" })
public class Total {

	@JsonProperty("totalActivo")
	private String totalActivo;
	@JsonProperty("totalPasivo")
	private String totalPasivo;
	@JsonProperty("patrimonio")
	private String patrimonio;

	@JsonProperty("totalActivo")
	public String getTotalActivo() {
		return totalActivo;
	}

	@JsonProperty("totalActivo")
	public void setTotalActivo(String totalActivo) {
		this.totalActivo = totalActivo;
	}

	@JsonProperty("totalPasivo")
	public String getTotalPasivo() {
		return totalPasivo;
	}

	@JsonProperty("totalPasivo")
	public void setTotalPasivo(String totalPasivo) {
		this.totalPasivo = totalPasivo;
	}

	@JsonProperty("patrimonio")
	public String getPatrimonio() {
		return patrimonio;
	}

	@JsonProperty("patrimonio")
	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

}
