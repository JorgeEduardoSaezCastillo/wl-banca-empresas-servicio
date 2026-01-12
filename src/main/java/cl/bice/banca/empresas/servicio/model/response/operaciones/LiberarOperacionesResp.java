package cl.bice.banca.empresas.servicio.model.response.operaciones;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Respuesta del servicio de liberar operaciones.
 * 
 * @author Alexis Ganga (TINET)
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "operacionesNoLiberadas", "cantidadOperacionesLiberadas" })
public class LiberarOperacionesResp {
	/**
	 * Operaciones que no se pudieron liberar.
	 */
	@JsonProperty("operacionesNoLiberadas")
	private Map<String, Map<String, String>> operacionesNoLiberadas;
	
	/**
	 * Operaciones liberadas.
	 */
	@JsonProperty("operacionesLiberadas")
	private Map<String, Map<String, String>> operacionesLiberadas;
	
	/**
	 * Cantidad de operaciones que no se pudieron liberar.
	 */
	@JsonProperty("cantidadOperacionesNoLiberadas")
	private Integer cantOperacionesNoLiberadas;
	/**
	 * Operaciones que se liberaron pero que solo tienen firma parcial.
	 */
	@JsonProperty("operacionesConFirmaParcial")
	private List<String> operacionesConFirmaParcial;
	
	/**
	 * Cantidad de operaciones que se liberarion
	 */
	@JsonProperty("cantidadOperacionesLiberadas")
	private Integer cantOperacionesLiberadas;

	/**
	 * @return the operacionesNoLiberadas
	 */
	public Map<String, Map<String, String>> getOperacionesNoLiberadas() {
		return operacionesNoLiberadas;
	}

	/**
	 * @param operacionesNoLiberadas the operacionesNoLiberadas to set
	 */
	public void setOperacionesNoLiberadas(Map<String, Map<String, String>> operacionesNoLiberadas) {
		this.operacionesNoLiberadas = operacionesNoLiberadas;
	}

	/**
	 * @return the cantOperacionesNoLiberadas
	 */
	public Integer getCantOperacionesNoLiberadas() {
		return cantOperacionesNoLiberadas;
	}

	/**
	 * @param cantOperacionesNoLiberadas the cantOperacionesNoLiberadas to set
	 */
	public void setCantOperacionesNoLiberadas(Integer cantOperacionesNoLiberadas) {
		this.cantOperacionesNoLiberadas = cantOperacionesNoLiberadas;
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

	public Integer getCantOperacionesLiberadas() {
		return cantOperacionesLiberadas;
	}

	public void setCantOperacionesLiberadas(Integer cantOperacionesLiberadas) {
		this.cantOperacionesLiberadas = cantOperacionesLiberadas;
	}

	public Map<String, Map<String, String>> getOperacionesLiberadas() {
		return operacionesLiberadas;
	}

	public void setOperacionesLiberadas(Map<String, Map<String, String>> operacionesLiberadas) {
		this.operacionesLiberadas = operacionesLiberadas;
	}
}
