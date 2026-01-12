package cl.bice.banca.empresas.servicio.model.request.desafios;

import java.util.List;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

/**
 * Request del servicio validarEstadoDesafio
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class ValidarEstadoConfirmacionRequest extends BaseRequestEmpresa {
	/**
	 * Código servicio
	 */
	private String codigoServicio;
	/**
	 * Lista de operaciones
	 */
	private List<String> listaOperaciones;
	/**
	 * ID transaccion
	 */
	private String idTransaccion;
	/**
	 * Tipo desafío
	 */
	private String tipoDesafio;
	/**
	 * Fecha desde rango consulta operaciones
	 */
	private String fechaDesde;
	/**
	 * Fecha hasta rango consulta operaciones
	 */
	private String fechaHasta;
	
	/**
	 * Tipo Cliente
	 */
	private String tipoCliente;

	/**
	 * @return the codigoServicio
	 */
	public String getCodigoServicio() {
		return codigoServicio;
	}

	/**
	 * @param codigoServicio the codigoServicio to set
	 */
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	/**
	 * @return the listaOperaciones
	 */
	public List<String> getListaOperaciones() {
		return listaOperaciones;
	}

	/**
	 * @param listaOperaciones the listaOperaciones to set
	 */
	public void setListaOperaciones(List<String> listaOperaciones) {
		this.listaOperaciones = listaOperaciones;
	}

	/**
	 * @return the idTransaccion
	 */
	public String getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * @param idTransaccion the idTransaccion to set
	 */
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * @return the tipoDesafio
	 */
	public String getTipoDesafio() {
		return tipoDesafio;
	}

	/**
	 * @param tipoDesafio the tipoDesafio to set
	 */
	public void setTipoDesafio(String tipoDesafio) {
		this.tipoDesafio = tipoDesafio;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	
	

}
