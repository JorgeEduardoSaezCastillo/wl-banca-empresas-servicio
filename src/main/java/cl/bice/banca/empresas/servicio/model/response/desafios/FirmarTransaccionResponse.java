package cl.bice.banca.empresas.servicio.model.response.desafios;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Respuesta del servicio de firmar transacci&oacute;n
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class FirmarTransaccionResponse {
	/**
	 * Datos de la respuesta
	 */
	@JsonProperty("respuesta")
	private FirmarTransaccionResp firmarTransaccionResp;
	
	@JsonProperty("tipoCliente")
	private String tipoCliente;

	/**
	 * @return the firmarTransaccionResp
	 */
	public FirmarTransaccionResp getFirmarTransaccionResp() {
		return firmarTransaccionResp;
	}

	/**
	 * @param firmarTransaccionResp the firmarTransaccionResp to set
	 */
	public void setFirmarTransaccionResp(FirmarTransaccionResp firmarTransaccionResp) {
		this.firmarTransaccionResp = firmarTransaccionResp;
	}
	
	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

}
