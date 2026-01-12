package cl.bice.banca.empresas.servicio.model.response.ms.mascara;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)

public class EnmascararSalidaTO {

	/**
	 * Codigo de respuesta.
	 */
	@JsonProperty("codigoSalida")
	private String codigoSalida;
	
	/**
	 * Glosa respuesta.
	 */
	@JsonProperty("glosaSalida")
	private String glosaSalida;
	
	/**
	 * Dato encriptado
	 */
	@JsonProperty("mascara")
	private String mascara;	

	
	public String getCodigoSalida() {
		return codigoSalida;
	}
	
	public void setCodigoSalida(String codigoSalida) {
		this.codigoSalida = codigoSalida;
	}
	
	public String getGlosaSalida() {
		return glosaSalida;
	}
	
	public void setGlosaSalida(String glosaSalida) {
		this.glosaSalida = glosaSalida;
	}
	
	public String getMascara() {
		return mascara;
	}
	
	public void setMascara(String mascara) {
		this.mascara = mascara;
	}
	

}
