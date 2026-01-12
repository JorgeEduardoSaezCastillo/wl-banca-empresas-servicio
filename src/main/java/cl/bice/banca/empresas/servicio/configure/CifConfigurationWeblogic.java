package cl.bice.banca.empresas.servicio.configure;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiObjectFactoryBean;

/**
 * Created by lbasso on 23-05-17.
 */

@Configuration
@Profile("weblogic")
@ConfigurationProperties("cif")
@MapperScan("cl.bice.banca.empresas.servicio.mapper.cif")
public class CifConfigurationWeblogic extends BaseCifConfiguration {

	/**
	 * username.
	 */
	private String jndi;

	/**
	 * Constructor por defecto.
	 */
	public CifConfigurationWeblogic() {
		super();
	}

	/**
	 * Metodo datasource.
	 *
	 * @return datasource.
	 * @throws NamingException en caso de que exista un problema con el jdni.
	 */
	@Bean(name = "dataSourceCif", destroyMethod = "")
	public DataSource dataSource() throws NamingException {
		final JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
		final String lJdni = this.jndi;
		jndiObjectFactoryBean.setJndiName(lJdni);
		jndiObjectFactoryBean.afterPropertiesSet();
		return (DataSource) jndiObjectFactoryBean.getObject();
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
