package cl.bice.nominas.parametros.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase para almacenar en caché los datos de la tabla de parámetros.
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class CacheParametros {

	/**
	 * Map con los datos de la tabla de parámetros
	 */
	private Map<String, Object> mapCache;

	/**
	 * Retorna el map con los datos de la tabla de parámetros
	 * 
	 * @return
	 */
	public Map<String, Object> getMapCache() {
		if (mapCache == null) {
			mapCache = new HashMap<>();
		}
		return mapCache;
	}

	/**
	 * Limpia el caché
	 */
	public void clearMap() {
		if (mapCache == null) {
			mapCache = new HashMap<>();
		}
		mapCache.clear();
	}

}