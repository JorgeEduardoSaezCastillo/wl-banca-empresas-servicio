package cl.bice.banca.empresas.servicio.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.bice.nominas.bancos.model.CacheBancos;
import cl.bice.nominas.parametros.model.CacheParametros;

@RestController
@RequestMapping("/util")
public class UtilController extends BaseControllerEmpresa {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilController.class);
	/**
	 * Caché de parámetros
	 */
	@Autowired
	@Qualifier("MapCache")
	CacheParametros cacheParametros;
	
	/**
	 * Caché bancos
	 */
	@Autowired
	@Qualifier("MapCacheBancos")
	CacheBancos cacheBancos;

	/**
	 * Interfaz GET que se encarga de refrescar el cache local
	 * 
	 * @return respuesta HTTP OK
	 */
	@GetMapping("refreshCache")
	public ResponseEntity<Object> reloadParametros() {
		LOGGER.info("LIMPIANDO CACHE DE PARAMETROS");
		cacheParametros.clearMap();
		LOGGER.info("CACHE DE PARAMETROS LIMPIADO");
		
		LOGGER.info("LIMPIANDO CACHE DE BANCOS");
		cacheBancos.clearMap();
		LOGGER.info("CACHE DE BANCOS LIMPIADO");
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
