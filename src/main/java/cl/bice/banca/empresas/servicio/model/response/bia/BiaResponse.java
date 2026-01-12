
package cl.bice.banca.empresas.servicio.model.response.bia;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import cl.bice.banca.empresas.servicio.model.response.Estado;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "estado", "listas" })
public class BiaResponse {

	@JsonProperty("estado")
	private Estado estado;
	@JsonProperty("listas")
	private Listas listas;

	@JsonProperty("estado")
	public Estado getEstado() {
		return estado;
	}

	@JsonProperty("estado")
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@JsonProperty("listas")
	public Listas getListas() {
		return listas;
	}

	@JsonProperty("listas")
	public void setListas(Listas listas) {
		this.listas = listas;
	}

}
