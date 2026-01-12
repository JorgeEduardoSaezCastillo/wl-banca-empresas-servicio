package cl.bice.banca.empresas.servicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by lbasso on 01-06-17.
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error en la Aplicacion")
public class BancaEmpresasException extends Exception {

	/**
	 * Serie de la clase.
	 */
	private static final long serialVersionUID = -2023371162303285936L;

	/**
	 * Indica si la expcecion envia correo o no.
	 */
	private final boolean envioEmail;

	/**
	 * Mensaje que se muestra en el envio de correo.
	 */
	private final String mensajeCorreo;

	/**
	 * Constructor por defecto.
	 *
	 * @param e excepcion que origina la excepcion.
	 */
	public BancaEmpresasException(Throwable e) {
		super(e);
		envioEmail = false;
		mensajeCorreo = "";
	}

	/**
	 * Constructor por defecto.
	 *
	 * @param e excepcion que origina la excepcion.
	 */
	public BancaEmpresasException(String e) {
		super(e);
		envioEmail = false;
		mensajeCorreo = "";
	}

	/**
	 * Constructor por defecto.
	 *
	 * @param e          excepcion que genera la excepcion
	 * @param envioEmail boolean que indica si se debe o no enviar correo.
	 */
	public BancaEmpresasException(Throwable e, Boolean envioEmail) {
		super(e);
		this.envioEmail = envioEmail;
		mensajeCorreo = "";
	}

	/**
	 * Constructor cuando la excepcion nace de una regla de negocio.
	 */
	public BancaEmpresasException() {
		super();
		envioEmail = false;
		mensajeCorreo = "";
	}

	/**
	 * Constructor por defecto.
	 *
	 * @param mensaje    de la excepcion.
	 * @param envioEmail boolean que indica si se debe o no enviar correo.
	 */
	public BancaEmpresasException(String mensaje, boolean envioEmail) {
		super(mensaje);
		this.envioEmail = envioEmail;
		mensajeCorreo = mensaje;
	}

	/**
	 * Permite obtener el valor del atributo envioEmail.
	 *
	 * @return el valor envioEmail.
	 */
	public boolean isEnvioEmail() {
		return this.envioEmail;
	}

	/**
	 * Permite obtener el valor del atributo mensajeCorreo.
	 *
	 * @return el valor mensajeCorreo.
	 */
	public String getMensajeCorreo() {
		return this.mensajeCorreo;
	}

}
