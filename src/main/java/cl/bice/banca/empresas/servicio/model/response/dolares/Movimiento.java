
package cl.bice.banca.empresas.servicio.model.response.dolares;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "fecha", "glosa", "detalle", "monto", "estado" })
public class Movimiento {

	@JsonProperty("fecha")
	private String fecha;
	@JsonProperty("glosa")
	private String glosa;
	@JsonProperty("detalle")
	private String detalle;
	@JsonProperty("monto")
	private String monto;
	@JsonProperty("estado")
	private String estado;

	@JsonProperty("fecha")
	public String getFecha() {
		return fecha;
	}

	@JsonProperty("fecha")
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	@JsonProperty("glosa")
	public String getGlosa() {
		return glosa;
	}

	@JsonProperty("glosa")
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	@JsonProperty("detalle")
	public String getDetalle() {
		return detalle;
	}

	@JsonProperty("detalle")
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	@JsonProperty("monto")
	public String getMonto() {
		return monto;
	}

	@JsonProperty("monto")
	public void setMonto(String monto) {
		this.monto = monto;
	}

	@JsonProperty("estado")
	public String getEstado() {
		return estado;
	}

	@JsonProperty("estado")
	public void setEstado(String estado) {
		this.estado = estado;
	}

}
