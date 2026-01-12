package cl.bice.banca.empresas.servicio.model.response.ms.autenticacionoper;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)

public class AutenticacionSalidaTO {
	
	/**
	 * Rut Cliente.
	 */
	@JsonProperty("rutCliente")
	private String rutCliente;
	
	/**
	 * Rut Usuario
	 */
	@JsonProperty("rutUsuario")
	private String rutUsuario;
	
	/**
	 * Tipo Cliente (BANCA_EMPRESAS)
	 */
	@JsonProperty("tipoCliente")
	private String tipoCliente;	

	/**
	 * Tipo Desafio (TOKEN|SOFTOKEN|SMS|TOKEN_SIGN_ONLINE|TOKEN_SIGN_OFFLINE|ESIGN)
	 */
	@JsonProperty("tipoDesafio")
	private String tipoDesafio;
	
	/**
	 * Nombre Portal 9i
	 */
	@JsonProperty("nombrePortal")
	private String nombrePortal;	
	
	
	/**
	 * Glosa Error
	 */
	@JsonProperty("glsError")
	private String glsError;
	

	public String getRutCliente() {
		return rutCliente;
	}

	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}

	public String getRutUsuario() {
		return rutUsuario;
	}

	public void setRutUsuario(String rutUsuario) {
		this.rutUsuario = rutUsuario;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getTipoDesafio() {
		return tipoDesafio;
	}

	public void setTipoDesafio(String tipoDesafio) {
		this.tipoDesafio = tipoDesafio;
	}

	public String getNombrePortal() {
		return nombrePortal;
	}

	public void setNombrePortal(String nombrePortal) {
		this.nombrePortal = nombrePortal;
	}

	public String getGlsError() {
		return glsError;
	}

	public void setGlsError(String glsError) {
		this.glsError = glsError;
	}	
	

}
