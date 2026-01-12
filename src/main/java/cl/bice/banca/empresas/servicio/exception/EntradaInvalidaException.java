package cl.bice.banca.empresas.servicio.exception;

/**
 * Excepcion lanzada cuando en un método se recibe una entrada no válida.
 * Created by lbasso on 24-05-17.
 */
public class EntradaInvalidaException extends BancaEmpresasException {

	/**
	 * Serie de la clase.
	 */
	private static final long serialVersionUID = 8127236802049526967L;

	/**
	 * Constructor cuando la excepcion nace de otra.
	 *
	 * @param e excepcion que origina la excepcion.
	 */
	public EntradaInvalidaException(Throwable e) {
		super(e);
	}

	/**
	 * Constructor cuando la excepcion nace de una regla de negocio.
	 */
	public EntradaInvalidaException() {
		super();
	}

}
