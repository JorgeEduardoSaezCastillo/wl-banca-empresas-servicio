package cl.bice.nominas.bancos.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase para almacenar en caché los datos de bancos ej.: nombre banco, codigo banco.
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class CacheBancos {

	/**
	 * Map con los datos de la tabla de parámetros
	 */
	private Map<String, Object> mapCacheBancos;

	/**
	 * Retorna el map con los datos de la tabla de parámetros
	 * 
	 * @return
	 */
	public Map<String, Object> getMapCache() {
		if (mapCacheBancos == null) {
			mapCacheBancos = new HashMap<>();
		}
		return mapCacheBancos;
	}

	/**
	 * Limpia el caché
	 */
	public void clearMap() {
		if (mapCacheBancos == null) {
			mapCacheBancos = new HashMap<>();
		}
		mapCacheBancos.clear();
	}

}