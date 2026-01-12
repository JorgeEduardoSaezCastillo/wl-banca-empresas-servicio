package cl.bice.banca.empresas.servicio.model.response.empresas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que tiene la finalidad de representar en json un listado de operaciones
 * a liberar
 * 
 * @author Cristian Pais
 *
 */
public class TiposOperacionesLiberar {
	/**
	 * Tipo de operaci&oacute;n
	 */
	@JsonProperty("tipo")
	private String tipo;
	/**
	 * Representa los separadores (desde donde hasta donde) que separan los pares
	 * campo-valor de la clase DetalleCampoValorTipoOperacion de la lista detalle a
	 * que campo-valor
	 */
	@JsonProperty("arraySeparadores")
	private List<List<Integer>> arraySeparadores;
	/**
	 * Lista de detalles de operaciones por liberar
	 */
	@JsonProperty("detalleOperaciones")
	private List<DetalleCampoValorTipoOperacion> detalle;

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the detalle
	 */
	public List<DetalleCampoValorTipoOperacion> getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(List<DetalleCampoValorTipoOperacion> detalle) {
		this.detalle = detalle;
	}

	/**
	 * @return the arraySeparadores
	 */
	public List<List<Integer>> getArraySeparadores() {
		return arraySeparadores;
	}

	/**
	 * @param arraySeparadores the arraySeparadores to set
	 */
	public void setArraySeparadores(List<List<Integer>> arraySeparadores) {
		this.arraySeparadores = arraySeparadores;
	}

}
