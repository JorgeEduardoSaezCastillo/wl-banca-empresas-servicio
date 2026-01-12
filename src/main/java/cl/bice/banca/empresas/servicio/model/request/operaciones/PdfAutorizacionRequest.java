package cl.bice.banca.empresas.servicio.model.request.operaciones;

import java.util.List;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

/**
 * Request del servicio generarDocDetalleAutorizacion
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class PdfAutorizacionRequest extends BaseRequestEmpresa {
	/**
	 * Código de servicio
	 */
	private String codigoServicio;
	/**
	 * Lista de operaciones
	 */
	private List<String> listaOperaciones;

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
}
