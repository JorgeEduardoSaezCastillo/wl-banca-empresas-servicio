package cl.bice.banca.empresas.servicio.model.response.desafios;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que contiene datos acerca del tipo de desaf&iacute;o que tiene un
 * cliente.
 * 
 * @author Cristian Pais
 *
 */
public class FactorSeguridadDesafio {
	/**
	 * Tipo desaf&iacute;o
	 */
	@JsonProperty("tipoDesafio")
	private String tipoDesafio;
	/**
	 * Identificador
	 */
	@JsonProperty("identificador")
	private String identificador;
	/**
	 * Identificador operaci&oacute;n transacci&oacute;n
	 */
	@JsonProperty("idOperTrx")
	private String idOperTrx;

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
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * @param identificador the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	/**
	 * @return the idOperTrx
	 */
	public String getIdOperTrx() {
		return idOperTrx;
	}

	/**
	 * @param idOperTrx the idOperTrx to set
	 */
	public void setIdOperTrx(String idOperTrx) {
		this.idOperTrx = idOperTrx;
	}
}
