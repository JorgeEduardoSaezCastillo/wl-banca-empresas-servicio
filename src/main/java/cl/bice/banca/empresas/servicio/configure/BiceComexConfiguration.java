package cl.bice.banca.empresas.servicio.configure;

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
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by lbasso on 23-05-17.
 */

@Configuration
@Profile("local")
@ConfigurationProperties("biceComex")
@MapperScan("cl.bice.banca.empresas.servicio.mapper.bicecomex")
public class BiceComexConfiguration extends ConfigurationBase {

	@Value(value = "classpath:cl/bice/banca/empresas/servicio/mapper/bicecomex/BiceComexMapper.xml")
	private Resource bicecomex;

	/**
	 * Constructor por defecto.
	 */
	public BiceComexConfiguration() {
		super();
	}

	@Bean(name = "dataSourceBiceComexMapper")
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("net.sourceforge.jtds.jdbc.Driver");
		dataSource.setUrl(this.getUrl());
		dataSource.setUsername(this.getUsername());
		dataSource.setPassword(this.getPassword());
		return dataSource;
	}

	/**
	 * Metodo encagado de generar una fabrica de sessiones.
	 *
	 * @param dataSource Data Source a utilizar.
	 * @return Fabrica de sessiones.
	 * @throws Exception En caso de que exista un problema con la BD.
	 */
	@Bean(name = "sqlSessionFactoryBiceComexMapper")
	public SqlSessionFactory sqlSessionFactoryPorAdmin(
			@Qualifier("dataSourceBiceComexMapper") DataSource datasourseSqlServer) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(datasourseSqlServer);
		Resource[] mapperLocations = new Resource[1];
		mapperLocations[0] = bicecomex;
		sessionFactory.setMapperLocations(mapperLocations);
		return sessionFactory.getObject();
	}

	/**
	 * Metodo encagado de generar el Template que utilizada MyBatis.
	 *
	 * @param sqlSessionFactory Fabrica de sessiones.
	 * @return Template.
	 */
	@Bean(name = "sqlSessionTemplateBiceComexMapper", destroyMethod = "clearCache")
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("sqlSessionFactoryBiceComexMapper") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}