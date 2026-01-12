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
import cl.bice.nominasenlinea.ws.ActEstadoNomIn;
import cl.bice.nominasenlinea.ws.ActEstadoNomOut;
import cl.bice.nominasenlinea.ws.GenericBancaDigital;
import cl.bice.nominasenlinea.ws.GenericBancaDigitalService;
import cl.bice.nominasenlinea.ws.ObjectFactory;
import cl.bice.nominasenlinea.ws.RevAprobarNomLinIn;
import cl.bice.nominasenlinea.ws.RevAprobarNomLinOut;

/**
 * Cliente para consultar el servicio NominasEnLinea.
 * 
 * @author Cristian Pais (TINET)
 *
 */
@Service("ClienteNominasEnLinea")
public class ClienteNominasEnLinea {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteNominasEnLinea.class);

	private URL url;

	GenericBancaDigitalService service;
	private GenericBancaDigital impl;
	ObjectFactory objectFactory;

	@Autowired
	Properties propiedadesExterna;

	/**
	 * Inicializa el cliente del servicio.
	 */
	private void firstcall() {
		try {
			if (null == url) {
				this.url = new URL(propiedadesExterna.getProperty("servicios.url.nominas.en.linea"));
				service = new GenericBancaDigitalService(url);
				impl = service.getGenericBancaDigitalPort();
				objectFactory = new ObjectFactory();
				Map<String, Object> requestContext = ((BindingProvider) impl).getRequestContext();
				requestContext.put("com.sun.xml.internal.ws.request.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.nominas.en.linea.request-timeout")));
				requestContext.put("com.sun.xml.internal.ws.connect.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.nominas.en.linea.connect-timeout")));
			}
		} catch (MalformedURLException e) {
			LOGGER.error("Exception {} {} ", ClienteNominasEnLinea.class.getSimpleName(), e);
		}
	}

	/**
	 * Reversa la aprobaci&oacute;n de una n&oacute;mina en linea.
	 *
	 * @param codNomina
	 * @param rutUsuario
	 * @param estado
	 * @return
	 * @throws BancaEmpresasException
	 */
	public RevAprobarNomLinOut reversaAprobacionNominaEnLinea(int codNomina, String rutUsuario, int estado)
			throws BancaEmpresasException {
		LOGGER.info("ClienteNominasEnLinea reversaAprobacionNominaEnLinea: codNomina[{}] rutUsuario[{}] estado[{}]",
				codNomina, rutUsuario, estado);
		try {
			firstcall();
			RevAprobarNomLinIn aprobarNomLinReversa = objectFactory.createRevAprobarNomLinIn();
			aprobarNomLinReversa.setINCODNOMINA(codNomina);
			aprobarNomLinReversa.setINRUTUSUARIO(rutUsuario);
			aprobarNomLinReversa.setINESTADO(estado);
			UtilPrintLog.printXML(aprobarNomLinReversa, RevAprobarNomLinIn.class, "REQUEST RevAprobarNomLinIn");
			RevAprobarNomLinOut revAprobarNomLinOut = impl.aprobarNomLinReversa(aprobarNomLinReversa);
			UtilPrintLog.printXML(revAprobarNomLinOut, RevAprobarNomLinOut.class, "RESPONSE RevAprobarNomLinOut");

			return revAprobarNomLinOut;
		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteNominasEnLinea reversaAprobacionNominaEnLinea: {}", e);
			throw new BancaEmpresasException();
		}

	}

	/**
	 * Actualiza el estado de una n&oacute;mina en linea.
	 * 
	 * @param codNomina
	 * @param rutUsuario
	 * @param nombreUsuario
	 * @param estado
	 * @param srvBice
	 * @param ipCliente
	 * @param datoFirma
	 * @return
	 * @throws BancaEmpresasException
	 */
	public ActEstadoNomOut actualizarNominaEnLinea(int codNomina, String rutUsuario, String nombreUsuario, int estado,
			int reversarEstado, String srvBice, String ipCliente, String datoFirma) throws BancaEmpresasException {
		LOGGER.info("ClienteNominasEnLinea actualizarNominaEnLinea: codNomina[{}] rutUsuario[{}] estado[{}]", codNomina,
				rutUsuario, estado);
		try {
			firstcall();
			ActEstadoNomIn actEstadoNomLin = objectFactory.createActEstadoNomIn();
			actEstadoNomLin.setINCODNOMINA(codNomina);
			actEstadoNomLin.setINRUTUSUARIO(rutUsuario);
			if (reversarEstado == 0) {
				actEstadoNomLin.setINESTADO(estado);
			} else {
				actEstadoNomLin.setINESTADO(reversarEstado);
			}
			actEstadoNomLin.setINNOMUSUARIO(nombreUsuario);
			actEstadoNomLin.setINSRVBICE(srvBice);
			actEstadoNomLin.setINIPCLIENTE(ipCliente);
			actEstadoNomLin.setINDATOFIRMA(datoFirma);
			UtilPrintLog.printXML(actEstadoNomLin, ActEstadoNomIn.class, "REQUEST ActEstadoNomIn");
			ActEstadoNomOut actEstadoNomOut = impl.actEstadoNomLin(actEstadoNomLin);
			UtilPrintLog.printXML(actEstadoNomOut, ActEstadoNomOut.class, "RESPONSE ActEstadoNomOut");

			return actEstadoNomOut;
		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteNominasEnLinea actualizarNominaEnLinea: {}", e);
			throw new BancaEmpresasException();
		}

	}
}
