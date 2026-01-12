package cl.bice.banca.empresas.servicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.WebApplicationInitializer;

/**
 * Clase Base de la aplicacion.
 */
@SpringBootApplication
@EnableRetry
public class BancaEmpresasServicioApplication extends SpringBootServletInitializer implements WebApplicationInitializer {

	/**
	 * Constructor por defecto de la clase.
	 */
	public BancaEmpresasServicioApplication() {
		super();
	}

	/**
	 * Main que se ejecuta al inciar la aplicacion.
	 *
	 * @param args argumentos.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BancaEmpresasServicioApplication.class, args);
	}

}
