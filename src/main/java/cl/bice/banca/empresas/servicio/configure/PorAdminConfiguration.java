package cl.bice.banca.empresas.servicio.configure;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Created by lbasso on 23-05-17.
 */

@Configuration
@Profile("local")
@ConfigurationProperties("porAdmin")
@MapperScan("cl.bice.banca.empresas.servicio.mapper.poradmin")
public class PorAdminConfiguration extends ConfigurationBase {

	/**
	 * Constructor por defecto.
	 */
	public PorAdminConfiguration() {
		super();
	}

	/**
	 * Metodo datasource.
	 *
	 * @return datasource.
	 * @throws SQLException en caso de error.
	 */
	@Bean(name = "dataSourcePorAdmin", destroyMethod = "close")
	@Primary
	public DataSource dataSourcePorAdmin() throws SQLException {
		final OracleDataSource dataSourcePorAdmin = new OracleDataSource();
		dataSourcePorAdmin.setUser(this.getUsername());
		dataSourcePorAdmin.setPassword(this.getPassword());
		dataSourcePorAdmin.setURL(this.getUrl());
		dataSourcePorAdmin.setImplicitCachingEnabled(true);
		dataSourcePorAdmin.setFastConnectionFailoverEnabled(true);
		return dataSourcePorAdmin;
	}

	/**
	 * Metodo encagado de generar una fabrica de sessiones.
	 *
	 * @param dataSource Data Source a utilizar.
	 * @return Fabrica de sessiones.
	 * @throws Exception En caso de que exista un problema con la BD.
	 */
	@Bean(name = "sqlSessionFactoryPorAdmin")
	@Primary
	public SqlSessionFactory sqlSessionFactoryPorAdmin(@Qualifier("dataSourcePorAdmin") DataSource dataSourcePorAdmin)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSourcePorAdmin);
		return sessionFactory.getObject();
	}

	/**
	 * Metodo encagado de generar el Template que utilizada MyBatis.
	 *
	 * @param sqlSessionFactory Fabrica de sessiones.
	 * @return Template.
	 */
	@Bean(name = "sqlSessionTemplatePorAdmin", destroyMethod = "clearCache")
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("sqlSessionFactoryPorAdmin") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
