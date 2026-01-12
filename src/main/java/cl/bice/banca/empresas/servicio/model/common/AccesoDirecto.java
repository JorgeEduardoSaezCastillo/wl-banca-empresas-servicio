package cl.bice.banca.empresas.servicio.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Clase que representa link a otras paginas.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccesoDirecto {

	/**
	 * Nombre del acceso directo.
	 */
	private String nombre;

	/**
	 * Ruta del acceso directo.
	 */
	private String ruta;

	/**
	 * Codigo del servicio al cual se llama.
	 */
	private String codigoServicio;

	/**
	 * Permite obtener el valor del atributo nombre.
	 *
	 * @return retorna el valor del atributo nombre.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Permite setear el valor del atributo nombre.
	 *
	 * @param nombre nuevo valor para el atributo nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Permite obtener el valor del atributo ruta.
	 *
	 * @return retorna el valor del atributo ruta.
	 */
	public String getRuta() {
		return ruta;
	}

	/**
	 * Permite setear el valor del atributo ruta.
	 *
	 * @param ruta nuevo valor para el atributo ruta.
	 */
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	/**
	 * Permite obtener el valor del atributo codigoServicio.
	 *
	 * @return retorna el valor del atributo codigoServicio.
	 */
	public String getCodigoServicio() {
		return codigoServicio;
	}

	/**
	 * Permite setear el valor del atributo codigoServicio.
	 *
	 * @param codigoServicio nuevo valor para el atributo codigoServicio.
	 */
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

}
