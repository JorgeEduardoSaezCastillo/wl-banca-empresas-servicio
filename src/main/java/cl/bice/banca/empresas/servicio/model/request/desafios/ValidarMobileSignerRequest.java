package cl.bice.banca.empresas.servicio.model.request.desafios;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

/**
 * Request del servicio validarMobileSigner.
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class ValidarMobileSignerRequest extends BaseRequestEmpresa {
	/**
	 * Email usuario
	 */
	private String emailUsuario;

	/**
	 * @return the emailUsuario
	 */
	public String getEmailUsuario() {
		return emailUsuario;
	}

	/**
	 * @param emailUsuario the emailUsuario to set
	 */
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
}
