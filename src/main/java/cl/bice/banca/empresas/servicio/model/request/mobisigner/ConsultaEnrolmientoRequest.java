package cl.bice.banca.empresas.servicio.model.request.mobisigner;

/**
 * Clase para invocar el metodo que consulta el enrolamiento de un cliente en
 * mobisigner.
 * 
 * @author Cristian Pais
 *
 */
public class ConsultaEnrolmientoRequest {
	/**
	 * Código de Rut empresa
	 */
	private String userId;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		if (userId.charAt(0) == '0')
			this.userId = userId.substring(1, userId.length());
		else
			this.userId = userId;
	}

	@Override
	public String toString() {
		return "ConsultaEnrolmientoRequest [userId=" + userId + "]";
	}

}
