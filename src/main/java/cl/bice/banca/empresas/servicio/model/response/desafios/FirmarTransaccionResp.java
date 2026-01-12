package cl.bice.banca.empresas.servicio.model.response.desafios;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.bice.banca.empresas.servicio.model.common.EstadoResp;

/**
 * Datos de la respuesta del servicio FirmarTransaccion.
 * 
 * @author Cristian Pais
 *
 */
public class FirmarTransaccionResp {
	/**
	 * Estado
	 */
	@JsonProperty("estado")
	private EstadoResp estado;
	/**
	 * Desaf&iacute;o respuesta
	 */
	@JsonProperty("desafioRespuesta")
	private DesafioRespuesta desafioRespuesta;

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
	 * @return the desafioRespuesta
	 */
	public DesafioRespuesta getDesafioRespuesta() {
		return desafioRespuesta;
	}

	/**
	 * @param desafioRespuesta the desafioRespuesta to set
	 */
	public void setDesafioRespuesta(DesafioRespuesta desafioRespuesta) {
		this.desafioRespuesta = desafioRespuesta;
	}

}
