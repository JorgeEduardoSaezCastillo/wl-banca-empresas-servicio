package cl.bice.banca.empresas.servicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lbasso on 24-05-17.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No es posible encontrar informacion")
public class NoEncontradoException extends BancaEmpresasException {

	/**
	 * Serie de la clase.
	 */
	private static final long serialVersionUID = -8545428916409469291L;

	/**
	 * Constructor cuando la excepcion nace de otra..
	 *
	 * @param e excepcion que origina la excepcion.
	 */
	public NoEncontradoException(Throwable e) {
		super(e);
	}

	/**
	 * Constructor cuando la excepcion nace de una regla de negocio.
	 */
	public NoEncontradoException() {
		super();
	}

	public NoEncontradoException(String texto) {
		super(texto);
	}

}
