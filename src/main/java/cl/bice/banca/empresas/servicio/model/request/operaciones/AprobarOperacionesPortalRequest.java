package cl.bice.banca.empresas.servicio.model.request.operaciones;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;



/**
 * Request del servicio aprobarOperacionesMasivas
 * @author Marco Bello
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AprobarOperacionesPortalRequest  {

	/**
	 * Canal de origen
	 */
	private String canal;
	
	
	/**
	 * Código servicio
	 */
	private String codigoServicio;

	
	/**
	 * Lista de operaciones
	 */
	private List<String> listaOperaciones;
	

	/**
	 * Rut apoderado
	 */
	private String rutApoderado;
	
	
	/**
	 * Rut Empresa
	 */
	private String rutEmpresa;
	

	
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

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public String getRutApoderado() {
		return rutApoderado;
	}

	public void setRutApoderado(String rutApoderado) {
		this.rutApoderado = rutApoderado;
	}

	public String getRutEmpresa() {
		return rutEmpresa;
	}

	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}
	
	
	

	

}
