package cl.bice.banca.empresas.servicio.model.response.empresas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.bice.banca.empresas.servicio.model.response.Estado;

/**
 * Cuentas que puede ver un usuario con un perfil determinado
 * 
 * @author Cristian Pais
 *
 */
public class CuentasSaldoPorPerfilResponse {
	/**
	 * Estatus.
	 */
	@JsonProperty("estado")
	private Estado estatus;
	/**
	 * Resumen de operaciones.
	 */
	@JsonProperty("cuentas")
	private List<CuentaSaldo> listaCuentas;

	/**
	 * @return the estatus
	 */
	public Estado getEstatus() {
		return estatus;
	}

	/**
	 * @param estatus the estatus to set
	 */
	public void setEstatus(Estado estatus) {
		this.estatus = estatus;
	}

	/**
	 * @return the listaCuentas
	 */
	public List<CuentaSaldo> getListaCuentas() {
		return listaCuentas;
	}

	/**
	 * @param listaCuentas the listaCuentas to set
	 */
	public void setListaCuentas(List<CuentaSaldo> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}

}
