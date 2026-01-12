package cl.bice.banca.empresas.servicio.model.nominas;

import java.io.Serializable;

/**
 * Clase que representa al estado de la nomina.
 * 
 * @author Elio Diaz. (TINet)
 * @version 1.0
 *
 */
public class EstadoNomina implements Serializable {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 2481635308906059779L;

	/**
	 * Estado de la nomina.
	 */
	private int estado;

	/**
	 * Glosa del estado.
	 */
	private String glosa;

	/**
	 * Fecha de cambio del estado.
	 */
	private String fecha;
	/**
	 * RUT del usuario que realizo el cambio del estado.
	 */
	private String rutUsuario;

	/**
	 * Nombre del usuario que realizo el cambio de estado.
	 */
	private String nombreUsuario;

	/**
	 * Permite obtener el valor del atributo estado.
	 *
	 * @return retorna el valor del atributo estado.
	 */
	public int getEstado() {
		return estado;
	}

	/**
	 * Permite setear el valor del atributo estado.
	 *
	 * @param estado nuevo valor para el atributo estado.
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}

	/**
	 * Permite obtener el valor del atributo fecha.
	 *
	 * @return retorna el valor del atributo fecha.
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Permite setear el valor del atributo fecha.
	 *
	 * @param fecha nuevo valor para el atributo fecha.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Permite obtener el valor del atributo rutUsuario.
	 *
	 * @return retorna el valor del atributo rutUsuario.
	 */
	public String getRutUsuario() {
		return rutUsuario;
	}

	/**
	 * Permite setear el valor del atributo rutUsuario.
	 *
	 * @param rutUsuario nuevo valor para el atributo rutUsuario.
	 */
	public void setRutUsuario(String rutUsuario) {
		this.rutUsuario = rutUsuario;
	}

	/**
	 * Permite obtener el valor del atributo glosa.
	 *
	 * @return retorna el valor del atributo glosa.
	 */
	public String getGlosa() {
		return glosa;
	}

	/**
	 * Permite setear el valor del atributo glosa.
	 *
	 * @param glosa nuevo valor para el atributo glosa.
	 */
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	/**
	 * Permite obtener el valor del atributo nombreUsuario.
	 *
	 * @return retorna el valor del atributo nombreUsuario.
	 */
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	/**
	 * Permite setear el valor del atributo nombreUsuario.
	 *
	 * @param nombreUsuario nuevo valor para el atributo nombreUsuario.
	 */
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return "Estado: " + this.getEstado() + " Usuario: " + this.getRutUsuario();
	}
}
