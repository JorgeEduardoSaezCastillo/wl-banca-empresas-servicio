package cl.bice.banca.empresas.servicio.model.response.empresas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.bice.banca.empresas.servicio.model.common.Empresa;
import cl.bice.banca.empresas.servicio.model.response.Estado;

/**
 * Clase que contiene una lista de empresas
 * 
 * @author Cristian Pais
 *
 */
public class EmpresasResponse {

	/**
	 * Estatus.
	 */
	@JsonProperty("estado")
	private Estado estatus;

	@JsonProperty("listaEmpresa")
	private List<Empresa> empresas;

	/**
	 * Permite obtener el valor del atributo estatus.
	 *
	 * @return retorna el valor del atributo estatus.
	 */
	public Estado getEstatus() {
		return estatus;
	}

	/**
	 * Permite setear el valor del atributo estatus.
	 *
	 * @param estatus nuevo valor para el atributo estatus.
	 */
	public void setEstatus(Estado estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the empresas
	 */
	public List<Empresa> getEmpresas() {
		return empresas;
	}

	/**
	 * @param empresas the empresas to set
	 */
	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

}
