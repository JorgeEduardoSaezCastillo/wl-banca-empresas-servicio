package cl.bice.banca.empresas.servicio.configure;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Created by lbasso on 23-05-17.
 */

@Configuration
@Profile("local")
@ConfigurationProperties("cif")
@MapperScan("cl.bice.banca.empresas.servicio.mapper.cif")
public class CifConfiguration extends BaseCifConfiguration {

	/**
	 * Constructor por defecto.
	 */
	public CifConfiguration() {
		super();
	}

	/**
	 * Metodo datasource.
	 *
	 * @return datasource.
	 * @throws SQLException en caso de error.
	 */
	@Bean(name = "dataSourceCif", destroyMethod = "close")
	public DataSource dataSourceCif() throws SQLException {
		final OracleDataSource dataSource = new OracleDataSource();
		dataSource.setUser(this.getUsername());
		dataSource.setPassword(this.getPassword());
		dataSource.setURL(this.getUrl());
		dataSource.setImplicitCachingEnabled(true);
		dataSource.setFastConnectionFailoverEnabled(true);
		return dataSource;
	}

}
