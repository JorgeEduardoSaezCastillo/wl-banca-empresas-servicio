package cl.bice.banca.empresas.servicio.configure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by lbasso on 20-06-17.
 */

@Configuration
@ConfigurationProperties("propiedades")
public class PropertiesConfiguration {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfiguration.class);

	/**
	 * Path donde se encuentra el archivo de configuracion.
	 */
	private String archivoConfiguracion;

	/**
	 * Contructor por defecto.
	 */
	public PropertiesConfiguration() {
		super();
	}

	/**
	 * Metodo encargado de obtener las propiedades externas.
	 * 
	 * @return propiedad externas.
	 */
	@Bean
	public Properties propiedadesExterna() {
		File file = new File(archivoConfiguracion);
		try (InputStream io = new FileInputStream(file);
				Reader reader = new InputStreamReader(io, StandardCharsets.UTF_8);) {
			Properties prop = new Properties();
			prop.load(reader);
			return prop;
		} catch (IOException e) {
			LOGGER.error("[Sistema] Error", e);
		}
		return null;
	}

	/**
	 * Permite obtener el valor del atributo archivoConfiguracion.
	 *
	 * @return el valor archivoConfiguracion.
	 */
	public String getArchivoConfiguracion() {
		return this.archivoConfiguracion;
	}

	/**
	 * Permite settear el valor del atributo archivoConfiguracion.
	 *
	 * @param archivoConfiguracion nuevo valor para el atributo
	 *                             archivoConfiguracion.
	 */
	public void setArchivoConfiguracion(String archivoConfiguracion) {
		this.archivoConfiguracion = archivoConfiguracion;
	}
}
