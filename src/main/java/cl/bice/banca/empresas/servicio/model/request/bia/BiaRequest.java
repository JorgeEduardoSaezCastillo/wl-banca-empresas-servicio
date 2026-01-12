package cl.bice.banca.empresas.servicio.model.request.bia;

import cl.bice.banca.empresas.servicio.model.common.BaseRequest;

public class BiaRequest extends BaseRequest {

	private String idCuenta;

	/**
	 * Permite obtener el valor del atributo idCuenta.
	 *
	 * @return retorna el valor del atributo idCuenta.
	 */
	public String getIdCuenta() {
		return idCuenta;
	}

	/**
	 * Permite setear el valor del atributo idCuenta.
	 *
	 * @param idCuenta nuevo valor para el atributo idCuenta.
	 */
	public void setIdCuenta(String idCuenta) {
		this.idCuenta = idCuenta;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "BiaRequest [idCuenta=" + idCuenta + ", toString()=" + super.toString() + "]";
	}

}
