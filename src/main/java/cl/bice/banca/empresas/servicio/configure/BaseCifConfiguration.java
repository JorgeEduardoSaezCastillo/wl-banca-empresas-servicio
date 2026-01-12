package cl.bice.banca.empresas.servicio.configure;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

/**
 * Created by lbasso on 23-05-17.
 */
public class BaseCifConfiguration extends ConfigurationBase {

	@Value(value = "classpath:cl/bice/banca/empresas/servicio/mapper/cif/CifMapper.xml")
	protected Resource cif;

	/**
	 * Metodo encagado de generar una fabrica de sessiones.
	 *
	 * @param dataSource Data Source a utilizar.
	 * @return Fabrica de sessiones.
	 * @throws Exception En caso de que exista un problema con la BD.
	 */
	@Bean(name = "sqlSessionFactoryCif")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourceCif") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		Resource[] mapperLocations = new Resource[1];
		mapperLocations[0] = cif;
		sessionFactory.setMapperLocations(mapperLocations);
		return sessionFactory.getObject();
	}

	/**
	 * Metodo encagado de generar el Template que utilizada MyBatis.
	 *
	 * @param sqlSessionFactory Fabrica de sessiones.
	 * @return Template.
	 */
	@Bean(name = "sqlSessionTemplateCif", destroyMethod = "clearCache")
	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("sqlSessionFactoryCif") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
