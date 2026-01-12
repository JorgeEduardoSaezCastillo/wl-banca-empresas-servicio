package cl.bice.banca.empresas.servicio.model.request.empresas;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

/**
 * Clase que contiene par&aacute;metros de consulta para obtener un resumen de
 * movimientos
 * 
 * @author Cristian Pais
 *
 */
public class ResumenMovimientoRequest extends BaseRequestEmpresa {

	/**
	 * Código de servicio
	 */
	private String cuenta;

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

}
