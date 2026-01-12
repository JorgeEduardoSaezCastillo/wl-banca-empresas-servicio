package cl.bice.banca.empresas.servicio.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cl.bice.nominas.bancos.model.CacheBancos;
import cl.bice.nominas.parametros.model.CacheParametros;

@Component
public class ScheduledTasks {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledTasks.class);

	@Autowired
	@Qualifier("MapCache")
	CacheParametros cacheParametros;
	
	@Autowired
	@Qualifier("MapCacheBancos")
	CacheBancos cacheBancos;

	@Scheduled(cron = "${cache.refresh}")
	public void taskVentaXEncargo() {
		LOGGER.info("LIMPIANDO CACHE DE PARAMETROS");
		cacheParametros.clearMap();
		LOGGER.info("CACHE DE PARAMETROS LIMPIO");
		
		LOGGER.info("LIMPIANDO CACHE DE BANCOS");
		cacheBancos.clearMap();
		LOGGER.info("CACHE DE BANCOS LIMPIO");
	}

}