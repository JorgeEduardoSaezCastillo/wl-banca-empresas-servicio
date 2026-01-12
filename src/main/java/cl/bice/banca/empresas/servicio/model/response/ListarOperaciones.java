package cl.bice.banca.empresas.servicio.model.response;

import java.util.List;

import cl.bice.banca.empresas.servicio.model.response.base.ResponseBase;

/**
 * Response del servicio
 * 
 * @author Tinet
 *
 */
public class ListarOperaciones extends ResponseBase {
	/**
	 * Lista de operaciones
	 */
	private List<Operacion> operaciones;
	/**
	 * Filtros
	 */
	private List<List<Integer>> filtros;

	/**
	 * 
	 * @return operaciones
	 */
	public List<Operacion> getOperaciones() {
		return operaciones;
	}

	/**
	 * 
	 * @param operaciones
	 */
	public void setOperaciones(List<Operacion> operaciones) {
		this.operaciones = operaciones;
	}

	/**
	 * 
	 * @return filtros
	 */
	public List<List<Integer>> getFiltros() {
		return filtros;
	}

	/**
	 * 
	 * @param filtros
	 */
	public void setFiltros(List<List<Integer>> filtros) {
		this.filtros = filtros;
	}

}
