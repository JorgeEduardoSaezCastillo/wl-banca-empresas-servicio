package cl.bice.banca.empresas.servicio.configure;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import cl.bice.nominas.bancos.model.CacheBancos;
import cl.bice.nominas.parametros.model.CacheParametros;

@Configuration
public class ConfigCache {

	@Bean(name = "MapCache")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public CacheParametros getCacheParametros() {
		return new CacheParametros();
	}

	@Bean(name = "MapCacheBancos")
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public CacheBancos getCacheBancos() {
		return new CacheBancos();
	}
}
