package cl.bice.banca.empresas.servicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepcion cuando ocurra un problema con el SP.
 * 
 * @author Cristian Pais
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "STORED PROCEDURE CON PROBLEMAS")
public class ErrorStoredProcedureException extends BancaEmpresasException {

	/**
	 * Serie de la clase.
	 */
	private static final long serialVersionUID = -67273624590085305L;

	/**
	 * Constructor por defecto.
	 *
	 * @param e excepcion que origina la excepcion.
	 */
	public ErrorStoredProcedureException(Throwable e) {
		super(e);
	}

	/**
	 * Constructor cuando la excepcion nace de una regla de negocio.
	 */
	public ErrorStoredProcedureException() {
		super();
	}

	/**
	 * Constructor cuando la excepcion nace de una regla de negocio.
	 *
	 * @param texto       a desplegar.
	 * @param enviaCorreo boolean que indica si se envia o no correo.
	 */
	public ErrorStoredProcedureException(String texto, boolean enviaCorreo) {
		super(texto, enviaCorreo);
	}
}
