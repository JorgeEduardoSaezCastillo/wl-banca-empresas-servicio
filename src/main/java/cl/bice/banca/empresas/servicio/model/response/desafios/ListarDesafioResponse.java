package cl.bice.banca.empresas.servicio.model.response.desafios;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Respuesta del servicio de listarDesafios.
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class ListarDesafioResponse {

	/**
	 * Respuesta al listar desaf&iacute;os
	 */
	@JsonProperty("respuesta")
	private ListarDesafioResp listarDesafioResp;

	/**
	 * @return the listarDesafioResp
	 */
	public ListarDesafioResp getListarDesafioResp() {
		return listarDesafioResp;
	}

	/**
	 * @param listarDesafioResp the listarDesafioResp to set
	 */
	public void setListarDesafioResp(ListarDesafioResp listarDesafioResp) {
		this.listarDesafioResp = listarDesafioResp;
	}
}
