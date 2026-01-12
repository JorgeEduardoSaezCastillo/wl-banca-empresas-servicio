package cl.bice.banca.empresas.servicio.model.response.operaciones;

import java.util.List;
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
@JsonPropertyOrder({ "cantidadOperacionesLiberadas", "cantidadOperacionesNoLiberadas" })
public class LiberarOperacionesPortalResp {
	
	/**
	 * Cantidad de operaciones que se pudieron liberar.
	 */
	@JsonProperty("cantidadOperacionesLiberadas")
	private Integer cantOperacionesLiberadas;
	
	
	/**
	 * Cantidad de operaciones que no se pudieron liberar.
	 */
	@JsonProperty("cantidadOperacionesNoLiberadas")
	private Integer cantOperacionesNoLiberadas;	

	
	
	/**
	 * Operaciones que no pudieron liberarse.
	 */
	@JsonProperty("operacionesNoLiberadas")
	private List<OperacionAprobLib> operacionesNoLiberadas;
	
	
	/**
	 * Operaciones liberadas.
	 */
	@JsonProperty("operacionesLiberadas")
	private List<OperacionAprobLib> operacionesLiberadas;
	

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


	public Integer getCantOperacionesLiberadas() {
		return cantOperacionesLiberadas;
	}

	public void setCantOperacionesLiberadas(Integer cantOperacionesLiberadas) {
		this.cantOperacionesLiberadas = cantOperacionesLiberadas;
	}

	public List<OperacionAprobLib> getOperacionesNoLiberadas() {
		return operacionesNoLiberadas;
	}

	public void setOperacionesNoLiberadas(List<OperacionAprobLib> operacionesNoLiberadas) {
		this.operacionesNoLiberadas = operacionesNoLiberadas;
	}

	public List<OperacionAprobLib> getOperacionesLiberadas() {
		return operacionesLiberadas;
	}

	public void setOperacionesLiberadas(List<OperacionAprobLib> operacionesLiberadas) {
		this.operacionesLiberadas = operacionesLiberadas;
	}
	
	

}
