package cl.bice.banca.empresas.servicio.model.common;

import java.util.List;

/**
 * Clase con la informacion de la tarjeta.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class InfoTarjeta {

	/**
	 * Dolares Bice de la tarjeta.
	 */
	private String dolaresBice;

	/**
	 * Alias de la tarjeta.
	 */
	private String aliasTarjeta;

	/**
	 * Estado de la tarjeta.
	 */
	private String estado;

	/**
	 * TipoTarjeta.
	 */
	private String origenTarjeta;

	/**
	 * Informacion nacional de la tarjeta.
	 */
	private Tarjeta nacional;

	/**
	 * Informacion internacional de la tarjeta.
	 */
	private Tarjeta internacional;

	/**
	 * Detalle Nacional de la tarjeta.
	 */
	private List<DetalleTarjeta> detalleNacional;

	/**
	 * Detalle intarnacional de la tarjeta.
	 */
	private List<DetalleTarjeta> detalleInternacional;

	/**
	 * Permite obtener el valor del atributo dolaresBice.
	 *
	 * @return retorna el valor del atributo dolaresBice.
	 */
	public String getDolaresBice() {
		return dolaresBice;
	}

	/**
	 * Permite setear el valor del atributo dolaresBice.
	 *
	 * @param dolaresBice nuevo valor para el atributo dolaresBice.
	 */
	public void setDolaresBice(String dolaresBice) {
		this.dolaresBice = dolaresBice;
	}

	/**
	 * Permite obtener el valor del atributo aliasTarjeta.
	 *
	 * @return retorna el valor del atributo aliasTarjeta.
	 */
	public String getAliasTarjeta() {
		return aliasTarjeta;
	}

	/**
	 * Permite setear el valor del atributo aliasTarjeta.
	 *
	 * @param aliasTarjeta nuevo valor para el atributo aliasTarjeta.
	 */
	public void setAliasTarjeta(String aliasTarjeta) {
		this.aliasTarjeta = aliasTarjeta;
	}

	/**
	 * Permite obtener el valor del atributo nacional.
	 *
	 * @return retorna el valor del atributo nacional.
	 */
	public Tarjeta getNacional() {
		return nacional;
	}

	/**
	 * Permite setear el valor del atributo nacional.
	 *
	 * @param nacional nuevo valor para el atributo nacional.
	 */
	public void setNacional(Tarjeta nacional) {
		this.nacional = nacional;
	}

	/**
	 * Permite obtener el valor del atributo internacional.
	 *
	 * @return retorna el valor del atributo internacional.
	 */
	public Tarjeta getInternacional() {
		return internacional;
	}

	/**
	 * Permite setear el valor del atributo internacional.
	 *
	 * @param internacional nuevo valor para el atributo internacional.
	 */
	public void setInternacional(Tarjeta internacional) {
		this.internacional = internacional;
	}

	/**
	 * Permite obtener el valor del atributo detalleNacional.
	 *
	 * @return retorna el valor del atributo detalleNacional.
	 */
	public List<DetalleTarjeta> getDetalleNacional() {
		return detalleNacional;
	}

	/**
	 * Permite setear el valor del atributo detalleNacional.
	 *
	 * @param detalleNacional nuevo valor para el atributo detalleNacional.
	 */
	public void setDetalleNacional(List<DetalleTarjeta> detalleNacional) {
		this.detalleNacional = detalleNacional;
	}

	/**
	 * Permite obtener el valor del atributo detalleInternacional.
	 *
	 * @return retorna el valor del atributo detalleInternacional.
	 */
	public List<DetalleTarjeta> getDetalleInternacional() {
		return detalleInternacional;
	}

	/**
	 * Permite setear el valor del atributo detalleInternacional.
	 *
	 * @param detalleInternacional nuevo valor para el atributo
	 *                             detalleInternacional.
	 */
	public void setDetalleInternacional(List<DetalleTarjeta> detalleInternacional) {
		this.detalleInternacional = detalleInternacional;
	}

	/**
	 * Permite obtener el valor del atributo estado.
	 *
	 * @return retorna el valor del atributo estado.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Permite setear el valor del atributo estado.
	 *
	 * @param estado nuevo valor para el atributo estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Permite obtener el valor del atributo origenTarjeta.
	 *
	 * @return retorna el valor del atributo origenTarjeta.
	 */
	public String getOrigenTarjeta() {
		return origenTarjeta;
	}

	/**
	 * Permite setear el valor del atributo origenTarjeta.
	 *
	 * @param origenTarjeta nuevo valor para el atributo origenTarjeta.
	 */
	public void setOrigenTarjeta(String origenTarjeta) {
		this.origenTarjeta = origenTarjeta;
	}

}
