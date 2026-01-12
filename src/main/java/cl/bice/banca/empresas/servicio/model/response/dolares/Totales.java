
package cl.bice.banca.empresas.servicio.model.response.dolares;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "saldo" })
public class Totales {

	@JsonProperty("saldo")
	private String saldo;

	@JsonProperty("saldo")
	public String getSaldo() {
		return saldo;
	}

	@JsonProperty("saldo")
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

}
