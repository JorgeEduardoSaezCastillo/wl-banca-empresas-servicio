package cl.bice.banca.empresas.servicio.model.response.empresas;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.bice.banca.empresas.servicio.model.response.Estado;

/**
 * Clase que contiene una lista de resumenes de movimientos
 * 
 * @author Cristian Pais
 *
 */
public class MovimientosResponse {

	/**
	 * Estatus.
	 */
	@JsonProperty("estado")
	private Estado estatus;

	/**
	 * Resumen de movimientos.
	 */
	@JsonProperty("resumen")
	private List<ResumenMovimiento> resumenMovimiento;

	/**
	 **
	 * Permite obtener el valor del atributo movimiento.
	 *
	 * @return retorna el valor del atributo movimiento.
	 */
	public List<ResumenMovimiento> getResumenMovimiento() {
		return resumenMovimiento;
	}

	public void setResumenMovimiento(List<ResumenMovimiento> resumenMovimiento) {
		this.resumenMovimiento = resumenMovimiento;
	}

	public Estado getEstatus() {
		return estatus;
	}

	public void setEstatus(Estado estatus) {
		this.estatus = estatus;
	}

}
