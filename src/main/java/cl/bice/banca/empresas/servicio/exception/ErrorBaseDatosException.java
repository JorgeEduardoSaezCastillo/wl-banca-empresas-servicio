package cl.bice.banca.empresas.servicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepcion cuando ocurra un problema con la BD. Created by lbasso on 30-05-17.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "BASE DATOS CON PROBLEMAS")
public class ErrorBaseDatosException extends BancaEmpresasException {

	/**
	 * Serie de la clase.
	 */
	private static final long serialVersionUID = -5526402660362050838L;

	/**
	 * Constructor por defecto.
	 *
	 * @param e excepcion que origina la excepcion.
	 */
	public ErrorBaseDatosException(Throwable e) {
		super(e);
	}

	/**
	 * Constructor cuando la excepcion nace de una regla de negocio.
	 */
	public ErrorBaseDatosException() {
		super();
	}

	/**
	 * Constructor cuando la excepcion nace de una regla de negocio.
	 *
	 * @param texto       a desplegar.
	 * @param enviaCorreo boolean que indica si se envia o no correo.
	 */
	public ErrorBaseDatosException(String texto, boolean enviaCorreo) {
		super(texto, enviaCorreo);
	}
}
