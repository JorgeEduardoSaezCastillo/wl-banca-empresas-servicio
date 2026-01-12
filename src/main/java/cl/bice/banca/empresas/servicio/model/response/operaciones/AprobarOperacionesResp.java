package cl.bice.banca.empresas.servicio.model.response.operaciones;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Respuesta del servicio de aprobar operaciones.
 * 
 * @author Cristian Pais (TINET)
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "operacionesNoAprobadas", "cantidadOperacionesNoAprobadas" })
public class AprobarOperacionesResp {
	/**
	 * Operaciones que no se pudieron aprobar.
	 */
	@JsonProperty("operacionesNoAprobadas")
	private List<String> operacionesNoAprobadas;
	/**
	 * Cantidad de operaciones que no se pudieron aprobar.
	 */
	@JsonProperty("cantidadOperacionesNoAprobadas")
	private Integer cantOperacionesNoAprobadas;
	/**
	 * Operaciones que se aprobaron pero que solo tienen firma parcial.
	 */
	@JsonProperty("operacionesConFirmaParcial")
	private List<String> operacionesConFirmaParcial;

	/**
	 * @return the operacionesNoAprobadas
	 */
	public List<String> getOperacionesNoAprobadas() {
		return operacionesNoAprobadas;
	}

	/**
	 * @param operacionesNoAprobadas the operacionesNoAprobadas to set
	 */
	public void setOperacionesNoAprobadas(List<String> operacionesNoAprobadas) {
		this.operacionesNoAprobadas = operacionesNoAprobadas;
	}

	/**
	 * @return the cantOperacionesNoAprobadas
	 */
	public Integer getCantOperacionesNoAprobadas() {
		return cantOperacionesNoAprobadas;
	}

	/**
	 * @param cantOperacionesNoAprobadas the cantOperacionesNoAprobadas to set
	 */
	public void setCantOperacionesNoAprobadas(Integer cantOperacionesNoAprobadas) {
		this.cantOperacionesNoAprobadas = cantOperacionesNoAprobadas;
	}

	/**
	 * @return the operacionesConFirmaParcial
	 */
	public List<String> getOperacionesConFirmaParcial() {
		return operacionesConFirmaParcial;
	}

	/**
	 * @param operacionesConFirmaParcial the operacionesConFirmaParcial to set
	 */
	public void setOperacionesConFirmaParcial(List<String> operacionesConFirmaParcial) {
		this.operacionesConFirmaParcial = operacionesConFirmaParcial;
	}
}
