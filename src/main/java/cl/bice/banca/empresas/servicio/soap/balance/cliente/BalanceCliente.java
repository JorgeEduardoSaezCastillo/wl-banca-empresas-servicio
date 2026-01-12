package cl.bice.banca.empresas.servicio.soap.balance.cliente;

import javax.xml.bind.JAXBElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import cl.bice.banca.empresas.servicio.soap.balance.BalanceProcessRequestType;
import cl.bice.banca.empresas.servicio.soap.balance.BalanceProcessResponseType;

/**
 * Cliente soap para llamar a los servicios de acciones.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
@Service
public class BalanceCliente extends WebServiceGatewaySupport {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER_BALANCE_CLIENTE = LoggerFactory.getLogger(BalanceCliente.class);

	@Autowired
	private Environment environment;

	public BalanceProcessResponseType obtenerBalance(String rut) {
		try {
			BalanceProcessRequestType request = new BalanceProcessRequestType();
			request.setRutCliente(rut);
			if (environment.acceptsProfiles("local")) {
				@SuppressWarnings("unchecked")
				JAXBElement<BalanceProcessResponseType> respuesta = (JAXBElement<BalanceProcessResponseType>) getWebServiceTemplate()
						.marshalSendAndReceive(request, new SoapActionCallback("BalanceEnLinea"));
				return respuesta.getValue();
			} else {
				return (BalanceProcessResponseType) getWebServiceTemplate().marshalSendAndReceive(request,
						new SoapActionCallback("BalanceEnLinea"));
			}
		} catch (Exception e) {
			LOGGER_BALANCE_CLIENTE.error("[{}] Error al llamar al servicio:", rut);
			LOGGER_BALANCE_CLIENTE.error("Traza:", e);
			return new BalanceProcessResponseType();
		}
	}

}
