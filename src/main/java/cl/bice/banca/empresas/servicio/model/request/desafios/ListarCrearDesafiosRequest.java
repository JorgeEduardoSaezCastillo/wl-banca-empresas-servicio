package cl.bice.banca.empresas.servicio.model.request.desafios;

import java.util.List;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

/**
 * Request del servicio de listarCrear desafíos.
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class ListarCrearDesafiosRequest extends BaseRequestEmpresa {

	/**
	 * C&oacute;digo servicio
	 */
	private String codigoServicio;
	/**
	 * Email usuario
	 */
	private String emailUsuario;
	/**
	 * Lista que contiene n&uacute;meros de operaciones
	 */
	private List<String> listaOperaciones;
	/**
	 * Fecha desde rango consulta operaciones
	 */
	private String fechaDesde;
	/**
	 * Fecha hasta rango consulta operaciones
	 */
	private String fechaHasta;

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
	 * @return the emailUsuario
	 */
	public String getEmailUsuario() {
		return emailUsuario;
	}

	/**
	 * @param emailUsuario the emailUsuario to set
	 */
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
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

}
