
package cl.bice.banca.empresas.servicio.model.response.dolares;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "fechaDesde", "fechaHasta", "movimientos", "totales", "certificados", "vencimientos" })
public class CartolaDolaresBice {

	@JsonProperty("fechaDesde")
	private String fechaDesde;
	@JsonProperty("fechaHasta")
	private String fechaHasta;
	@JsonProperty("movimientos")
	private List<Movimiento> movimientos = null;
	@JsonProperty("totales")
	private Totales totales;
	@JsonProperty("certificados")
	private List<Object> certificados = null;
	@JsonProperty("vencimientos")
	private List<Object> vencimientos = null;

	@JsonProperty("fechaDesde")
	public String getFechaDesde() {
		return fechaDesde;
	}

	@JsonProperty("fechaDesde")
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	@JsonProperty("fechaHasta")
	public String getFechaHasta() {
		return fechaHasta;
	}

	@JsonProperty("fechaHasta")
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	@JsonProperty("movimientos")
	public List<Movimiento> getMovimientos() {
		return movimientos;
	}

	@JsonProperty("movimientos")
	public void setMovimientos(List<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	@JsonProperty("totales")
	public Totales getTotales() {
		return totales;
	}

	@JsonProperty("totales")
	public void setTotales(Totales totales) {
		this.totales = totales;
	}

	@JsonProperty("certificados")
	public List<Object> getCertificados() {
		return certificados;
	}

	@JsonProperty("certificados")
	public void setCertificados(List<Object> certificados) {
		this.certificados = certificados;
	}

	@JsonProperty("vencimientos")
	public List<Object> getVencimientos() {
		return vencimientos;
	}

	@JsonProperty("vencimientos")
	public void setVencimientos(List<Object> vencimientos) {
		this.vencimientos = vencimientos;
	}

}
