package cl.bice.banca.empresas.servicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepcion para indicar excepcion en validacion de monto. Created by A. Ganga
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Esta realizando acciones no permitidas")
public class MontoInvalidoException extends BancaEmpresasException {

	/**
	 * Serie de la clase.
	 */
	private static final long serialVersionUID = -8545428916409469291L;

	/**
	 * Constructor cuando la excepcion nace de otra..
	 *
	 * @param e excepcion que origina la excepcion.
	 */
	public MontoInvalidoException(Throwable e) {
		super(e);
	}

	/**
	 * Constructor cuando la excepcion nace de una regla de negocio.
	 */
	public MontoInvalidoException() {
		super();
	}

	public MontoInvalidoException(String texto) {
		super(texto);
	}

}
