/**
 * 
 */
package cl.bice.banca.empresas.servicio.model.request.desafios;

import java.util.Map;

import cl.bice.banca.empresas.servicio.model.common.BaseRequest;

/**
 * Request para invocar al servicio que lista desaf&iacute;os
 * 
 * @author Cristian Pais
 *
 */
public class ListarDesafioRequest extends BaseRequest {
	/**
	 * Tipo cliente
	 */
	private String tipoCliente;
	/**
	 * Código servicio
	 */
	private String codigoServicio;

	private int campo1;
	private String campo2;
	private String campo3;
	private String campo4;
	private String campo5;
	private String campo6;
	private String campo7;

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
	 * @return the codigoServicio
	 */
	public String getCodigoServicio() {
		return codigoServicio;
	}

	/**
	 * @param codigoServicio the codigoServicio to set
	 */
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	/**
	 * @return the campo1
	 */
	public int getCampo1() {
		return campo1;
	}

	/**
	 * @param campo1 the campo1 to set
	 */
	public void setCampo1(int campo1) {
		this.campo1 = campo1;
	}

	/**
	 * @return the campo2
	 */
	public String getCampo2() {
		return campo2;
	}

	/**
	 * @param campo2 the campo2 to set
	 */
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	/**
	 * @return the campo3
	 */
	public String getCampo3() {
		return campo3;
	}

	/**
	 * @param campo3 the campo3 to set
	 */
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	/**
	 * @return the campo4
	 */
	public String getCampo4() {
		return campo4;
	}

	/**
	 * @param campo4 the campo4 to set
	 */
	public void setCampo4(String campo4) {
		this.campo4 = campo4;
	}

	/**
	 * @return the campo5
	 */
	public String getCampo5() {
		return campo5;
	}

	/**
	 * @param campo5 the campo5 to set
	 */
	public void setCampo5(String campo5) {
		this.campo5 = campo5;
	}

	/**
	 * @return the campo6
	 */
	public String getCampo6() {
		return campo6;
	}

	/**
	 * @param campo6 the campo6 to set
	 */
	public void setCampo6(String campo6) {
		this.campo6 = campo6;
	}

	/**
	 * @return the campo7
	 */
	public String getCampo7() {
		return campo7;
	}

	/**
	 * @param campo7 the campo7 to set
	 */
	public void setCampo7(String campo7) {
		this.campo7 = campo7;
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
