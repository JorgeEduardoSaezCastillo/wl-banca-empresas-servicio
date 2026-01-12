package cl.bice.banca.empresas.servicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepcion de encontrar una accion no permitida.
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Esta realizando acciones no permitidas")
public class IlegalException extends BancaEmpresasException {

	/**
	 * SerialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase.
	 * 
	 * @param message Mensaje a enviar.
	 */
	public IlegalException(String message) {
		super(message);
	}

	/**
	 * Contructor por defecto.
	 */
	public IlegalException() {
		super();
	}

}