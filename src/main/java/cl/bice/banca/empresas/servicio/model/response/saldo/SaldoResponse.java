package cl.bice.banca.empresas.servicio.model.response.saldo;

/**
 * Respuesta del servicio Saldo.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class SaldoResponse {

	/**
	 * Respuesta.
	 */
	private ConsultaSaldosResp respuesta;

	/**
	 * Permite obtener el valor del atributo respuesta.
	 *
	 * @return retorna el valor del atributo respuesta.
	 */
	public ConsultaSaldosResp getRespuesta() {
		return respuesta;
	}

	/**
	 * Permite setear el valor del atributo respuesta.
	 *
	 * @param respuesta nuevo valor para el atributo respuesta.
	 */
	public void setRespuesta(ConsultaSaldosResp respuesta) {
		this.respuesta = respuesta;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "SaldoResponse [respuesta=" + respuesta + "]";
	}

}
