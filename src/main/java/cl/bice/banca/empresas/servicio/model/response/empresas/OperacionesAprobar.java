package cl.bice.banca.empresas.servicio.model.response.empresas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Operaciones por aprobar
 * 
 * @author Cristian Pais
 *
 */
public class OperacionesAprobar implements IOperacionesAprobarLiberar {
	/**
	 * Representa los separadores (desde donde hasta donde) que separan los pares
	 * campo-valor de la clase DetalleCampoValorTipoOperacion de la lista detalle a
	 * que campo-valor
	 */
	@JsonProperty("arraySeparadores")
	private List<List<Integer>> arraySeparadores;
	/**
	 * Lista de detalles de operaciones por aprobar
	 */
	private List<DetalleCampoValorTipoOperacion> detalle;

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
