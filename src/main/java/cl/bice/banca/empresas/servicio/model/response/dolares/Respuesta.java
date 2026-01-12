
package cl.bice.banca.empresas.servicio.model.response.dolares;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import cl.bice.banca.empresas.servicio.model.response.Estado;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "estado", "cartolaDolaresBice" })
public class Respuesta {

	@JsonProperty("estado")
	private Estado estado;
	@JsonProperty("cartolaDolaresBice")
	private CartolaDolaresBice cartolaDolaresBice;

	@JsonProperty("estado")
	public Estado getEstado() {
		return estado;
	}

	@JsonProperty("estado")
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@JsonProperty("cartolaDolaresBice")
	public CartolaDolaresBice getCartolaDolaresBice() {
		return cartolaDolaresBice;
	}

	@JsonProperty("cartolaDolaresBice")
	public void setCartolaDolaresBice(CartolaDolaresBice cartolaDolaresBice) {
		this.cartolaDolaresBice = cartolaDolaresBice;
	}

}
