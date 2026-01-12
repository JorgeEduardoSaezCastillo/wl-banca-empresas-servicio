package cl.bice.banca.empresas.servicio.model.request.desafios;

import java.util.Map;

import cl.bice.banca.empresas.servicio.model.common.BaseRequest;

/**
 * Request para invocar al servicio que crea desafío BICEPASS.
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class FirmarTransaccionRequest extends BaseRequest {
	/**
	 * Tipo cliente
	 */
	private String tipoCliente;
	/**
	 * IP cliente
	 */
	private String iPCliente;
	/**
	 * Canal
	 */
	private String canal;
	/**
	 * ID transacción
	 */
	private String idTransaccion;
	/**
	 * Datos transacción
	 */
	private Map<String, String> datosTransaccion;

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

	/**
	 * @return the iPCliente
	 */
	public String getiPCliente() {
		return iPCliente;
	}

	/**
	 * @param iPCliente the iPCliente to set
	 */
	public void setiPCliente(String iPCliente) {
		this.iPCliente = iPCliente;
	}

	/**
	 * @return the canal
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * @param canal the canal to set
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

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
	 * @return the datosTransaccion
	 */
	public Map<String, String> getDatosTransaccion() {
		return datosTransaccion;
	}

	/**
	 * @param datosTransaccion the datosTransaccion to set
	 */
	public void setDatosTransaccion(Map<String, String> datosTransaccion) {
		this.datosTransaccion = datosTransaccion;
	}

}
