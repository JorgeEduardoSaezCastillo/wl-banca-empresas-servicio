package cl.bice.banca.empresas.servicio.model.response;

import java.io.Serializable;

/**
 * Clase response del servicio tarjeta.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class Tarjeta implements Serializable {

	/**
	 * Serie de la clase.
	 */
	private static final long serialVersionUID = -6529347366375863555L;

	/**
	 * Numero de Operacion.
	 */
	private String numOperacion;

	/**
	 * Codigo Tipo Producto.
	 */
	private String codTipoProducto;

	/**
	 * Codigo Estado Producto.
	 */
	private String codEstadoProducto;

	/**
	 * Codigo Relacion Producto.
	 */
	private String codRelacionProducto;

	/**
	 * Numero Cuenta Tarjeta.
	 */
	private String numCuentaTarjeta;

	/**
	 * Numero Tarjeta.
	 */
	private String numPanTarjeta;

	/**
	 * Numero Correlativo Nacional.
	 */
	private String numCorrelativoNacional;

	/**
	 * Codigo Tipo Tarjeta.
	 */
	private String codTipoTarjeta;

	/**
	 * Monto Limite Gasto Pesos.
	 */
	private String mtoLimGastoPesos;

	/**
	 * Monto Limite Gasto Dolar.
	 */
	private String mtoLimGastoDolar;

	/**
	 * Tarjeta Formateada.
	 */
	private String tarjetaFormateada;

	/**
	 * Permite obtener el valor del atributo numOperacion.
	 *
	 * @return retorna el valor del atributo numOperacion.
	 */
	public String getNumOperacion() {
		return numOperacion;
	}

	/**
	 * Permite setear el valor del atributo numOperacion.
	 *
	 * @param numOperacion nuevo valor para el atributo numOperacion.
	 */
	public void setNumOperacion(String numOperacion) {
		this.numOperacion = numOperacion;
	}

	/**
	 * Permite obtener el valor del atributo codTipoProducto.
	 *
	 * @return retorna el valor del atributo codTipoProducto.
	 */
	public String getCodTipoProducto() {
		return codTipoProducto;
	}

	/**
	 * Permite setear el valor del atributo codTipoProducto.
	 *
	 * @param codTipoProducto nuevo valor para el atributo codTipoProducto.
	 */
	public void setCodTipoProducto(String codTipoProducto) {
		this.codTipoProducto = codTipoProducto;
	}

	/**
	 * Permite obtener el valor del atributo codEstadoProducto.
	 *
	 * @return retorna el valor del atributo codEstadoProducto.
	 */
	public String getCodEstadoProducto() {
		return codEstadoProducto;
	}

	/**
	 * Permite setear el valor del atributo codEstadoProducto.
	 *
	 * @param codEstadoProducto nuevo valor para el atributo codEstadoProducto.
	 */
	public void setCodEstadoProducto(String codEstadoProducto) {
		this.codEstadoProducto = codEstadoProducto;
	}

	/**
	 * Permite obtener el valor del atributo codRelacionProducto.
	 *
	 * @return retorna el valor del atributo codRelacionProducto.
	 */
	public String getCodRelacionProducto() {
		return codRelacionProducto;
	}

	/**
	 * Permite setear el valor del atributo codRelacionProducto.
	 *
	 * @param codRelacionProducto nuevo valor para el atributo codRelacionProducto.
	 */
	public void setCodRelacionProducto(String codRelacionProducto) {
		this.codRelacionProducto = codRelacionProducto;
	}

	/**
	 * Permite obtener el valor del atributo numCuentaTarjeta.
	 *
	 * @return retorna el valor del atributo numCuentaTarjeta.
	 */
	public String getNumCuentaTarjeta() {
		return numCuentaTarjeta;
	}

	/**
	 * Permite setear el valor del atributo numCuentaTarjeta.
	 *
	 * @param numCuentaTarjeta nuevo valor para el atributo numCuentaTarjeta.
	 */
	public void setNumCuentaTarjeta(String numCuentaTarjeta) {
		this.numCuentaTarjeta = numCuentaTarjeta;
	}

	/**
	 * Permite obtener el valor del atributo numPanTarjeta.
	 *
	 * @return retorna el valor del atributo numPanTarjeta.
	 */
	public String getNumPanTarjeta() {
		return numPanTarjeta;
	}

	/**
	 * Permite setear el valor del atributo numPanTarjeta.
	 *
	 * @param numPanTarjeta nuevo valor para el atributo numPanTarjeta.
	 */
	public void setNumPanTarjeta(String numPanTarjeta) {
		this.numPanTarjeta = numPanTarjeta;
	}

	/**
	 * Permite obtener el valor del atributo numCorrelativoNacional.
	 *
	 * @return retorna el valor del atributo numCorrelativoNacional.
	 */
	public String getNumCorrelativoNacional() {
		return numCorrelativoNacional;
	}

	/**
	 * Permite setear el valor del atributo numCorrelativoNacional.
	 *
	 * @param numCorrelativoNacional nuevo valor para el atributo
	 *                               numCorrelativoNacional.
	 */
	public void setNumCorrelativoNacional(String numCorrelativoNacional) {
		this.numCorrelativoNacional = numCorrelativoNacional;
	}

	/**
	 * Permite obtener el valor del atributo codTipoTarjeta.
	 *
	 * @return retorna el valor del atributo codTipoTarjeta.
	 */
	public String getCodTipoTarjeta() {
		return codTipoTarjeta;
	}

	/**
	 * Permite setear el valor del atributo codTipoTarjeta.
	 *
	 * @param codTipoTarjeta nuevo valor para el atributo codTipoTarjeta.
	 */
	public void setCodTipoTarjeta(String codTipoTarjeta) {
		this.codTipoTarjeta = codTipoTarjeta;
	}

	/**
	 * Permite obtener el valor del atributo mtoLimGastoPesos.
	 *
	 * @return retorna el valor del atributo mtoLimGastoPesos.
	 */
	public String getMtoLimGastoPesos() {
		return mtoLimGastoPesos;
	}

	/**
	 * Permite setear el valor del atributo mtoLimGastoPesos.
	 *
	 * @param mtoLimGastoPesos nuevo valor para el atributo mtoLimGastoPesos.
	 */
	public void setMtoLimGastoPesos(String mtoLimGastoPesos) {
		this.mtoLimGastoPesos = mtoLimGastoPesos;
	}

	/**
	 * Permite obtener el valor del atributo mtoLimGastoDolar.
	 *
	 * @return retorna el valor del atributo mtoLimGastoDolar.
	 */
	public String getMtoLimGastoDolar() {
		return mtoLimGastoDolar;
	}

	/**
	 * Permite setear el valor del atributo mtoLimGastoDolar.
	 *
	 * @param mtoLimGastoDolar nuevo valor para el atributo mtoLimGastoDolar.
	 */
	public void setMtoLimGastoDolar(String mtoLimGastoDolar) {
		this.mtoLimGastoDolar = mtoLimGastoDolar;
	}

	/**
	 * Permite obtener el valor del atributo tarjetaFormateada.
	 *
	 * @return retorna el valor del atributo tarjetaFormateada.
	 */
	public String getTarjetaFormateada() {
		return tarjetaFormateada;
	}

	/**
	 * Permite setear el valor del atributo tarjetaFormateada.
	 *
	 * @param tarjetaFormateada nuevo valor para el atributo tarjetaFormateada.
	 */
	public void setTarjetaFormateada(String tarjetaFormateada) {
		this.tarjetaFormateada = tarjetaFormateada;
	}

}
