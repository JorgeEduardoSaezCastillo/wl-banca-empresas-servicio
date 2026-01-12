package cl.bice.banca.empresas.servicio.soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;
import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.util.UtilPrintLog;
import cl.bice.tel23300.ws.ObjectFactory;
import cl.bice.tel23300.ws.TEL23300;
import cl.bice.tel23300.ws.TEL23300Service;
import cl.bice.tel23300.ws.Tel23300Out;

/**
 * Cliente para consultar el servicio TEL23300.
 * 
 * @author Cristian Pais (TINET)
 *
 */
@Service("ClienteTEL23300")
public class ClienteTEL23300 {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteTEL23300.class);

	private URL url;

	TEL23300Service service;
	private TEL23300 impl;
	ObjectFactory objectFactory;

	@Autowired
	Properties propiedadesExterna;

	/**
	 * Canal para invocar al servicio TEL23300
	 */
	private static final String CANAL_TEL23300 = "IP";

	/**
	 * Inicializa el cliente del servicio.
	 */
	private void firstcall() {
		try {
			if (null == url) {
				this.url = new URL(propiedadesExterna.getProperty("servicios.url.TEL23300"));
				service = new TEL23300Service(url);
				impl = service.getTEL23300Port();
				objectFactory = new ObjectFactory();
				Map<String, Object> requestContext = ((BindingProvider) impl).getRequestContext();
				requestContext.put("com.sun.xml.internal.ws.request.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.tel23300.request-timeout")));
				requestContext.put("com.sun.xml.internal.ws.connect.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.tel23300.connect-timeout")));
			}
		} catch (MalformedURLException e) {
			LOGGER.error("Exception {} {} ", ClienteTEL23300.class.getSimpleName(), e);
		}
	}

	/**
	 * Execute
	 * 
	 * @param canal
	 * @param moneda
	 * @param cuenta
	 * @return
	 * @throws BancaEmpresasException
	 */
	public Tel23300Out execute(String canal, String moneda, String cuenta) throws BancaEmpresasException {
		LOGGER.info("ClienteTEL23300 execute: canal[{}] moneda[{}] cuenta[{}]", canal, moneda, cuenta);
		try {
			firstcall();
			Tel23300Out tel23300Out = impl.execute(canal, moneda, cuenta);
			UtilPrintLog.printXML(tel23300Out, Tel23300Out.class, "RESPONSE Tel23300Out");

			return tel23300Out;
		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteTEL23300 execute: {}", e);
			throw new BancaEmpresasException();
		}

	}

	/**
	 * Obtiene el saldo de la cuenta de la nomina.
	 * 
	 * @param nroCuenta
	 * @return
	 * @throws BancaEmpresasException
	 */
	public long obtenerSaldoCuentaNomina(String nroCuenta) throws BancaEmpresasException {
		LOGGER.info("ClienteTEL23300 obtenerSaldoCuentaNomina: nroCuenta[{}]", nroCuenta);
		long monto = 0;
		Tel23300Out tel23300Out;
		try {
			tel23300Out = execute(CANAL_TEL23300,
					propiedadesExterna.getProperty("servicios.nominasenlinea.codigo.moneda.nomina"), nroCuenta);

			if (null == tel23300Out || null == tel23300Out.getStatus()) {
				throw new BancaEmpresasException();
			}

			monto = determinaMonto(tel23300Out.getStatus(), tel23300Out.getSaldoDisponible(),
					tel23300Out.getMontoDisponibleLineaSobregiro());
			
		} catch (NumberFormatException e) {
			LOGGER.error("Error en ClienteTEL23300 obtenerSaldoCuentaNomina: {}", e);
			throw new BancaEmpresasException();
		}

		return monto;
	}
	
	/**
	 * Determina el monto saldo de la cuenta de la nomina.
	 * 
	 * @param int status, String saldo, String saldoLC
	 * @return long monto
	 * @throws BancaEmpresasException
	 */
	private long determinaMonto(int status, String saldo, String saldoLC) throws BancaEmpresasException {
		long monto = 0;
		if (1 == status) {
			if (saldo != null && saldoLC != null) {

				long montoDisponible = Long.parseLong(saldo);
				long montoDisponibleLC = Long.parseLong(saldoLC);

				if (montoDisponible <= 0 && montoDisponibleLC < 0) {
					monto = 0;
				} else if (montoDisponible <= 0 && montoDisponibleLC >= 0) {
					monto = montoDisponibleLC;
				} else {
					monto = montoDisponible + montoDisponibleLC;
				}
			} else {
				monto = -1;
			}
		} else {
			monto = -1;
		}
		return monto;
	}
}
