package cl.bice.banca.empresas.servicio.model.response.empresas;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Superclase que representa tanto a operaciones por aprobar como a operaciones
 * por liberar
 * 
 * @author Cristian Pais
 *
 */
public class OperacionesAprobarLiberar {
	/**
	 * c&oacute;digo de servicio
	 */
	@JsonProperty("codigoServicio")
	private String codigoServicio;
	/**
	 * cabecera a mostrar en el front para el listado de operaciones
	 */
	@JsonProperty("cabecera")
	private String cabecera;
	/**
	 * interface que puede adoptar la implementaci&oacute;n ya sea para mostrar
	 * operaciones por aprobar o por liberar
	 */
	private IOperacionesAprobarLiberar detalle;

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
	 * @return the operaciones
	 */
	public IOperacionesAprobarLiberar getOperaciones() {
		return detalle;
	}

	/**
	 * @param operaciones the operaciones to set
	 */
	public void setOperaciones(IOperacionesAprobarLiberar operaciones) {
		this.detalle = operaciones;
	}

	public String getCabecera() {
		return cabecera;
	}

	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

}
