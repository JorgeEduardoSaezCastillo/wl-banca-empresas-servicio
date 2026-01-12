package cl.bice.banca.empresas.servicio.configure;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by lbasso on 06-06-17.
 */
@Component
public class SimpleCORSFilter implements Filter {

	/**
	 * Logger de la clase.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCORSFilter.class);

	/**
	 * Constructor por defecto.
	 */
	public SimpleCORSFilter() {
		LOGGER.info("[Sistema] Inicio");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;
		String header = response.getHeader("Access-Control-Allow-Origin");
		if (header == null || header.isEmpty()) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		response.addHeader("Access-Control-Allow-Methods", "HEAD, GET, PUT, POST, DELETE, PATCH");
		response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
		response.addHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Expose-Headers", "Location");

		chain.doFilter(req, res);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(FilterConfig filterConfig) {
		LOGGER.info("[Sistema] Metodo Sin uso");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void destroy() {
		LOGGER.info("[Sistema] Metodo Sin uso");
	}

}
