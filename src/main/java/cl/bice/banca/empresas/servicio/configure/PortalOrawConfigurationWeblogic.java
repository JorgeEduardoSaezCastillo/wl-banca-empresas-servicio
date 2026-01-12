package cl.bice.banca.empresas.servicio.configure;

import javax.naming.NamingException;
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
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 * Created by Cristian Pais on 01-03-19.
 */

@Configuration
@Profile("weblogic")
@ConfigurationProperties("portalOraw")
@MapperScan("cl.bice.banca.empresas.servicio.mapper.portaloraw")
public class PortalOrawConfigurationWeblogic {

	@Value(value = "classpath:cl/bice/banca/empresas/servicio/mapper/portaloraw/EmpresasMapper.xml")
	private Resource portaloraw;
	/**
	 * username.
	 */
	private String jndi;

	/**
	 * Constructor por defecto.
	 */
	public PortalOrawConfigurationWeblogic() {
		super();
	}

	/**
	 * Metodo datasource.
	 *
	 * @return datasource.
	 * @throws NamingException en caso de que exista un problema con el jdni.
	 */
	@Bean(name = "dataSourcePortalOraw", destroyMethod = "")
	public DataSource dataSource() throws NamingException {
		final JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		final String lJdni = this.jndi;
		jndiObjectFactoryBean.setJndiName(lJdni);
		jndiObjectFactoryBean.afterPropertiesSet();
		return (DataSource) jndiObjectFactoryBean.getObject();
	}

	/**
	 * Metodo encagado de generar una fabrica de sessiones.
	 *
	 * @param dataSource Data Source a utilizar.
	 * @return Fabrica de sessiones.
	 * @throws Exception En caso de que exista un problema con la BD.
	 */
	@Bean(name = "sqlSessionFactoryPortalOraw")
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourcePortalOraw") DataSource dataSourcePortalOraw)
			throws Exception {
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

	/**
	 * Permite obtener el valor del atributo jndi.
	 *
	 * @return el valor jndi.
	 */
	public String getJndi() {
		return this.jndi;
	}

	/**
	 * Permite settear el valor del atributo jndi.
	 *
	 * @param jndi nuevo valor para el atributo jndi.
	 */
	public void setJndi(String jndi) {
		this.jndi = jndi;
	}

}
