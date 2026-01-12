package cl.bice.banca.empresas.servicio.configure;

import javax.naming.NamingException;
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
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 * Created by lbasso on 23-05-17.
 */

@Configuration
@Profile("weblogic")
@ConfigurationProperties("porAdmin")
@MapperScan("cl.bice.banca.empresas.servicio.mapper.poradmin")
public class PorAdminConfigurationWeblogic {

	/**
	 * username.
	 */
	private String jndi;

	/**
	 * Constructor por defecto.
	 */
	public PorAdminConfigurationWeblogic() {
		super();
	}

	/**
	 * Metodo datasource.
	 *
	 * @return datasource.
	 * @throws NamingException en caso de que exista un problema con el jdni.
	 */
	@Bean(name = "dataSourcePorAdmin", destroyMethod = "")
	@Primary
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
	@Bean(name = "sqlSessionFactoryPorAdmin")
	@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSourcePorAdmin") DataSource dataSourcePorAdmin)
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
