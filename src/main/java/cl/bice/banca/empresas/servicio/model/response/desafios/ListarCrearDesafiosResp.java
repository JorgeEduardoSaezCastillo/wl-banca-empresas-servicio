package cl.bice.banca.empresas.servicio.model.response.desafios;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Clase que contiene los datos de la respuesta del servicio que lista y crea
 * desaf&iacute;os.
 * 
 * @author Cristian Pais
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "idTransaccion", "tipoDesafio", "token", "idSesion", "tipoCliente" })
public class ListarCrearDesafiosResp {

	/**
	 * Id transacci&oacute;n.
	 */
	@JsonProperty("idTransaccion")
	private String idTransaccion;
	/**
	 * Tipo desaf&iacute;o (puede ser FDA o BICEPASS)
	 */
	@JsonProperty("tipoDesafio")
	private String tipoDesafio;
	/**
	 * Token FDA
	 */
	@JsonProperty("token")
	private String token;
	/**
	 * idSesion
	 */
	@JsonProperty("idSesion")
	private String idSesion;
	/**
	 * Tipo Cliente
	 */
	@JsonProperty("tipoCliente")
	private String tipoCliente;

	/**
	 * @return the idTransaccion
	 */
	public String getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * @param idTransaccion the idTransaccion to set
	 */
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * @return the tipoDesafio
	 */
	public String getTipoDesafio() {
		return tipoDesafio;
	}

	/**
	 * @param tipoDesafio the tipoDesafio to set
	 */
	public void setTipoDesafio(String tipoDesafio) {
		this.tipoDesafio = tipoDesafio;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the idSesion
	 */
	public String getIdSesion() {
		return idSesion;
	}

	/**
	 * @param idSesion the idSesion to set
	 */
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	/**
	 * @return the tipoCliente
	 */
	public String getTipoCliente() {
		return tipoCliente;
	}

	/**
	 * @param tipoCliente the tipoCliente to set
	 */
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	

}
