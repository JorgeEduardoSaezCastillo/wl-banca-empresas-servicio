package cl.bice.banca.empresas.servicio.configure;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import oracle.jdbc.pool.OracleDataSource;

/**
 * Created by Cristian Pais on 01-03-19.
 */

@Configuration
@Profile("local")
@ConfigurationProperties("portalOraw")
@MapperScan("cl.bice.banca.empresas.servicio.mapper.portaloraw")
public class PortalOrawConfiguration extends ConfigurationBase {

	@Value(value = "classpath:cl/bice/banca/empresas/servicio/mapper/portaloraw/EmpresasMapper.xml")
	private Resource portaloraw;

	/**
	 * Constructor por defecto.
	 */
	public PortalOrawConfiguration() {
		super();
	}

	/**
	 * Metodo datasource.
	 *
	 * @return datasource.
	 * @throws SQLException en caso de error.
	 */
	@Bean(name = "dataSourcePortalOraw", destroyMethod = "close")
	public DataSource dataSourcePortalOraw() throws SQLException {
		final OracleDataSource dataSourcePortalOraw = new OracleDataSource();
		dataSourcePortalOraw.setUser(this.getUsername());
		dataSourcePortalOraw.setPassword(this.getPassword());
		dataSourcePortalOraw.setURL(this.getUrl());
		dataSourcePortalOraw.setImplicitCachingEnabled(true);
		dataSourcePortalOraw.setFastConnectionFailoverEnabled(true);
		return dataSourcePortalOraw;
	}

	/**
	 * Metodo encagado de generar una fabrica de sessiones.
	 *
	 * @param dataSource Data Source a utilizar.
	 * @return Fabrica de sessiones.
	 * @throws Exception En caso de que exista un problema con la BD.
	 */
	@Bean(name = "sqlSessionFactoryPortalOraw")
	public SqlSessionFactory sqlSessionFactoryPortalOraw(
			@Qualifier("dataSourcePortalOraw") DataSource dataSourcePortalOraw) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSourcePortalOraw);
		Resource[] mapperLocations = new Resource[1];
		mapperLocations[0] = portaloraw;
		sessionFactory.setMapperLocations(mapperLocations);
		return sessionFactory.getObject();
	}

	/**
	 * Metodo encagado de generar el Template que utilizada MyBatis.
	 *
	 * @param sqlSessionFactory Fabrica de sessiones.
	 * @return Template.
	 */
	@Bean(name = "sqlSessionTemplatePortalOraw", destroyMethod = "clearCache")
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("sqlSessionFactoryPortalOraw") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
