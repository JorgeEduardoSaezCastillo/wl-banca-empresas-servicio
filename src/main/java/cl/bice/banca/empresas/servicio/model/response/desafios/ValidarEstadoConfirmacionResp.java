package cl.bice.banca.empresas.servicio.model.response.desafios;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que contiene los datos de la respuesta del servicio que consulta estado
 * del desaf&iacute;o.
 * 
 * @author Cristian Pais
 *
 */
public class ValidarEstadoConfirmacionResp {

	/**
	 * Estado de confirmaci&oacute;n
	 */
	@JsonProperty("estadoTransaccion")
	private String estadoTransaccion;

	/**
	 * @return the estadoTransaccion
	 */
	public String getEstadoTransaccion() {
		return estadoTransaccion;
	}

	/**
	 * @param estadoTransaccion the estadoTransaccion to set
	 */
	public void setEstadoTransaccion(String estadoTransaccion) {
		this.estadoTransaccion = estadoTransaccion;
	}

}
