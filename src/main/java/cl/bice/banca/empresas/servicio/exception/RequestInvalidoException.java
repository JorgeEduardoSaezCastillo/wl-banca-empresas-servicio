package cl.bice.banca.empresas.servicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepcion lanzada cuando el request no esta correctamente generado. Created
 * by lbasso on 24-05-17.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request")
public class RequestInvalidoException extends BancaEmpresasException {

	/**
	 * Serie de la clase.
	 */
	private static final long serialVersionUID = 8127236802049526967L;

	/**
	 * Constructor cuando la excepcion nace de otra.
	 *
	 * @param e excepcion que origina la excepcion.
	 */
	public RequestInvalidoException(Throwable e) {
		super(e);
	}

	/**
	 * Constructor cuando la excepcion nace de una regla de negocio.
	 */
	public RequestInvalidoException() {
		super();
	}

}
