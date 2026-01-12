package cl.bice.banca.empresas.servicio.model.response;

import cl.bice.banca.empresas.servicio.model.response.empresas.DetalleCampoValorTipoOperacion;

/**
 * 
 * Clase encargada de recibir cada movimiento desde la consulta del SP de
 * movimientos diarios
 *
 * @author Fabian Urra C. (TINet).
 * @version 1.0
 */
public class Operacion extends DetalleCampoValorTipoOperacion {

	/**
	 * C&oacute;digo servicio
	 */
	private String codigoServicio;

	/**
	 * 
	 * @return codigoServicio
	 */
	public String getCodigoServicio() {
		return codigoServicio;
	}

	/**
	 * 
	 * @param codigoServicio
	 */
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

}
