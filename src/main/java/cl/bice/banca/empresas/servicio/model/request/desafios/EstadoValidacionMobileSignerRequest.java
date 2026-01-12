package cl.bice.banca.empresas.servicio.model.request.desafios;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

/**
 * Request del servicio verEstadoValidacionMobileSigner.
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class EstadoValidacionMobileSignerRequest extends BaseRequestEmpresa {
	/**
	 * ID transaccion
	 */
	private String idTransaccion;

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
}
