package cl.bice.banca.empresas.servicio.model.operaciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase para almacenar datos de operaciones.
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class MapOperaciones {
	/**
	 * Map para guardar datos de operaciones.
	 */
	private Map<String, Map<String, String>> operaciones;

	/**
	 * Contiene el output original del SP
	 */
	private List<Map<String, Object>> outputSP;

	/**
	 * Inicializa el map para guardar los datos de operaciones.
	 * 
	 * @return
	 */
	public Map<String, Map<String, String>> getMapOperaciones() {
		if (operaciones == null) {
			operaciones = new HashMap<>();
		}
		return operaciones;
	}

	public void setMapOperaciones (Map<String, Map<String, String>> operaciones) {
		this.operaciones = operaciones;
	}

	/**
	 * Limpia el map
	 */
	public void clearMap() {
		if (operaciones != null) {
			operaciones.clear();
			operaciones = null;
		}

		if (outputSP != null) {
			outputSP.clear();
			outputSP = null;
		}
	}

	/**
	 * @return the mapOutputSP
	 */
	public List<Map<String, Object>> getMapOutputSP() {
		return outputSP;
	}

	/**
	 * @param mapOutputSP the mapOutputSP to set
	 */
	public void setMapOutputSP(List<Map<String, Object>> mapOutputSP) {
		this.outputSP = mapOutputSP;
	}

}