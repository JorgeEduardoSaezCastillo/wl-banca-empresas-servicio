//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación
// de la referencia de enlace (JAXB) XML v2.2.11
// Visite <a
// href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve
// a compilar el esquema de origen.
// Generado el: 2017.09.27 a las 09:26:39 AM CLST
//

package cl.bice.banca.empresas.servicio.model.response.empresas;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Resumen de movimientos
 * 
 * @author Cristian Pais
 *
 */
public class ResumenMovimiento {

	/**
	 * glosa de resumen movimiento
	 */
	@JsonProperty("glosa")
	private String glosa;
	/**
	 * monto
	 */
	@JsonProperty("monto")
	private String monto;
	/**
	 * monto formateado
	 */
	@JsonProperty("montoFormateado")
	private String montoFormateado;
	/**
	 * fecha
	 */
	@JsonProperty("fecha")
	private String fecha;
	
	/**
	 * detalle de resumen movimiento
	 */
	@JsonProperty("detalle")
	private String detalle;
	
	/**
	 * tipo movimiento
	 */
	@JsonProperty("tipo")
	private String tipo;


	/**
	 * 
	 * @return la glosa
	 */
	public String getGlosa() {
		return glosa;
	}

	/**
	 * 
	 * @param glosa la glosa a setear
	 */
	public void setGlosa(String glosa) {
		this.glosa = glosa;
	}

	/**
	 * 
	 * @return el monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * 
	 * @param monto el monto a setear
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * 
	 * @return el monto formateado
	 */
	public String getMontoFormateado() {
		return montoFormateado;
	}

	/**
	 * 
	 * @param montoFormateado el monto formateado a setear
	 */
	public void setMontoFormateado(String montoFormateado) {
		this.montoFormateado = montoFormateado;
	}

	/**
	 * 
	 * @return la fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * 
	 * @param fecha la fecha a setear
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	/**
	 * 
	 * @return  detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * 
	 * @param glosa la glosa a setear
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	/**
	 * 
	 * @return  tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * 
	 * @param tipo a setear
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
