package cl.bice.banca.empresas.servicio.model.response.empresas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Operaciones por liberar
 * 
 * @author Cristian Pais
 *
 */
public class OperacionesLiberar implements IOperacionesAprobarLiberar {
	/**
	 * Map para agrupar operaciones por tipo de operaci&oacute;n
	 */
	@JsonIgnore
	private Map<String, TiposOperacionesLiberar> mapTiposOpLiberar;
	/**
	 * Lista de tipos de operaci&oacute;n
	 */
	@JsonProperty("arrayTipos")
	private List<String> arrayTipos;
	/**
	 * Lista de operaciones ya agrupadas por tipo de operaci&oacute;n
	 */
	@JsonProperty("operacionesTipo")
	private List<TiposOperacionesLiberar> lista;

	/**
	 * @return the mapTiposOpLiberar
	 */
	public Map<String, TiposOperacionesLiberar> getMapTiposOpLiberar() {
		return mapTiposOpLiberar;
	}

	/**
	 * @param mapTiposOpLiberar the mapTiposOpLiberar to set
	 */
	public void setMapTiposOpLiberar(Map<String, TiposOperacionesLiberar> mapTiposOpLiberar) {
		this.mapTiposOpLiberar = mapTiposOpLiberar;
		this.lista = new ArrayList<>(mapTiposOpLiberar.values());
	}

	/**
	 * @return the lista
	 */
	public List<TiposOperacionesLiberar> getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(List<TiposOperacionesLiberar> lista) {
		this.lista = lista;
	}

	/**
	 * @return the arrayTipos
	 */
	public List<String> getArrayTipos() {
		return arrayTipos;
	}

	/**
	 * @param arrayTipos the arrayTipos to set
	 */
	public void setArrayTipos(List<String> arrayTipos) {
		this.arrayTipos = arrayTipos;
	}

}
