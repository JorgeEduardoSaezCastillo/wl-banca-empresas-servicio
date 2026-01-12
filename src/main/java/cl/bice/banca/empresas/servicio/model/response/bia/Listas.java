
package cl.bice.banca.empresas.servicio.model.response.bia;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "detallePortafolio", "total", "portafolioDiversificado" })
public class Listas {

	@JsonProperty("detallePortafolio")
	private List<DetallePortafolio> detallePortafolio = null;
	@JsonProperty("total")
	private List<Total> total = null;
	@JsonProperty("portafolioDiversificado")
	private String portafolioDiversificado;

	@JsonProperty("detallePortafolio")
	public List<DetallePortafolio> getDetallePortafolio() {
		return detallePortafolio;
	}

	@JsonProperty("detallePortafolio")
	public void setDetallePortafolio(List<DetallePortafolio> detallePortafolio) {
		this.detallePortafolio = detallePortafolio;
	}

	@JsonProperty("total")
	public List<Total> getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(List<Total> total) {
		this.total = total;
	}

	@JsonProperty("portafolioDiversificado")
	public String getPortafolioDiversificado() {
		return portafolioDiversificado;
	}

	@JsonProperty("portafolioDiversificado")
	public void setPortafolioDiversificado(String portafolioDiversificado) {
		this.portafolioDiversificado = portafolioDiversificado;
	}

}
