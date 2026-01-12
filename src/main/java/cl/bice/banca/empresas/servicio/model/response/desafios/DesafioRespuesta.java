package cl.bice.banca.empresas.servicio.model.response.desafios;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase para la respuesta del servicio que crea desaf&iacute;os.
 * 
 * @author Cristian Pais
 *
 */
public class DesafioRespuesta {
	/**
	 * Identificador de transacci&oacute;n
	 */
	@JsonProperty("transaccionId")
	private String transaccionId;
	/**
	 * Estado de la transacci&oacute;n
	 */
	@JsonProperty("estadoTransaccion")
	private String estadoTransaccion;
	/**
	 * Dispositivos
	 */
	@JsonProperty("dispositivos")
	private String dispositivos;

	/**
	 * @return the transaccionId
	 */
	public String getTransaccionId() {
		return transaccionId;
	}

	/**
	 * @param transaccionId the transaccionId to set
	 */
	public void setTransaccionId(String transaccionId) {
		this.transaccionId = transaccionId;
	}

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

	/**
	 * @return the dispositivos
	 */
	public String getDispositivos() {
		return dispositivos;
	}

	/**
	 * @param dispositivos the dispositivos to set
	 */
	public void setDispositivos(String dispositivos) {
		this.dispositivos = dispositivos;
	}

}
