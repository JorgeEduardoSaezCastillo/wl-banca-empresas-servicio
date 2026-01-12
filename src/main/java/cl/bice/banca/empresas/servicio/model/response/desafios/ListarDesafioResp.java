package cl.bice.banca.empresas.servicio.model.response.desafios;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.bice.banca.empresas.servicio.model.common.EstadoResp;

/**
 * Clase que contiene datos de la respuesta del servicio que lista
 * desaf&iacute;s
 * 
 * @author Cristian Pais
 *
 */
public class ListarDesafioResp {
	/**
	 * Estado
	 */
	@JsonProperty("estado")
	EstadoResp estado;
	/**
	 * Factor de seguridad desaf&iacute;o
	 */
	@JsonProperty("factorAuten")
	List<FactorSeguridadDesafio> factorSeguridadDesafio;

	/**
	 * @return the estado
	 */
	public EstadoResp getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(EstadoResp estado) {
		this.estado = estado;
	}

	/**
	 * @return the factorSeguridadDesafio
	 */
	public List<FactorSeguridadDesafio> getFactorSeguridadDesafio() {
		return factorSeguridadDesafio;
	}

	/**
	 * @param factorSeguridadDesafio the factorSeguridadDesafio to set
	 */
	public void setFactorSeguridadDesafio(List<FactorSeguridadDesafio> factorSeguridadDesafio) {
		this.factorSeguridadDesafio = factorSeguridadDesafio;
	}
}
