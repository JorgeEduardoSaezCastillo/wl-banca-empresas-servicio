package cl.bice.banca.empresas.servicio.model.response.operaciones;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Respuesta del servicio de aprobar operaciones masivas.
 * 
 * @author Marco Bello
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cantidadOperacionesAprobadas", "cantidadOperacionesNoAprobadas" })
public class AprobarOperacionesPortalResp {

	/**
	 * Cantidad de operaciones que se pudieron aprobar.
	 */
	@JsonProperty("cantidadOperacionesAprobadas")
	private Integer cantOperacionesAprobadas;
	
	
	/**
	 * Cantidad de operaciones que no se pudieron aprobar.
	 */
	@JsonProperty("cantidadOperacionesNoAprobadas")
	private Integer cantOperacionesNoAprobadas;
	
	
	/**
	 * Operaciones que no se pudieron aprobar.
	 */
	@JsonProperty("operacionesNoAprobadas")
	private List<OperacionAprobLib> operacionesNoAprobadas;

	
	/**
	 * Operaciones que se aprobaron pero que solo tienen firma parcial.
	 */
	@JsonProperty("operacionesConFirmaParcial")
	private List<OperacionAprobLib> operacionesConFirmaParcial;
	
	
	/**
	 * Operaciones que se aprobaron con firma completa.
	 */
	@JsonProperty("operacionesConFirmaCompleta")
	private List<OperacionAprobLib> operacionesConFirmaCompleta;
	

	/**
	 * @return the operacionesNoAprobadas
	 */
	public List<OperacionAprobLib> getOperacionesNoAprobadas() {
		return operacionesNoAprobadas;
	}

	/**
	 * @param operacionesNoAprobadas the operacionesNoAprobadas to set
	 */
	public void setOperacionesNoAprobadas(List<OperacionAprobLib> operacionesNoAprobadas) {
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
	

	public Integer getCantOperacionesAprobadas() {
		return cantOperacionesAprobadas;
	}

	public void setCantOperacionesAprobadas(Integer cantOperacionesAprobadas) {
		this.cantOperacionesAprobadas = cantOperacionesAprobadas;
	}

	/**
	 * @return the operacionesConFirmaParcial
	 */
	public List<OperacionAprobLib> getOperacionesConFirmaParcial() {
		return operacionesConFirmaParcial;
	}

	/**
	 * @param operacionesConFirmaParcial the operacionesConFirmaParcial to set
	 */
	public void setOperacionesConFirmaParcial(List<OperacionAprobLib> operacionesConFirmaParcial) {
		this.operacionesConFirmaParcial = operacionesConFirmaParcial;
	}

	public List<OperacionAprobLib> getOperacionesConFirmaCompleta() {
		return operacionesConFirmaCompleta;
	}

	public void setOperacionesConFirmaCompleta(List<OperacionAprobLib> operacionesConFirmaCompleta) {
		this.operacionesConFirmaCompleta = operacionesConFirmaCompleta;
	}
	
	
	
}
