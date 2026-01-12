package cl.bice.banca.empresas.servicio.soap.cartola.cliente;

import java.math.BigInteger;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import cl.bice.banca.empresas.servicio.soap.cartola.TCMW0025IN;
import cl.bice.banca.empresas.servicio.soap.cartola.TCMW0025OUT;

/**
 * Cliente soap para llamar a los servicios de acciones.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
@Service
public class CartolaCliente extends WebServiceGatewaySupport {

	public TCMW0025OUT obtenerCartolaProvisoria(String cuenta, String canal, String tipoMovimiento,
			BigInteger infoCheque, BigInteger registro) {
		TCMW0025IN request = new TCMW0025IN();

		request.setCANAL(canal);
		request.setCUENTAIN(new BigInteger(cuenta));
		request.setTIPOMOVIN(tipoMovimiento);
		request.setINFOCHEQUESIN(infoCheque);
		request.setREGISTRO(registro);
		return (TCMW0025OUT) getWebServiceTemplate().marshalSendAndReceive(request, new SoapActionCallback(
				"http://www.bice.cl/wsdl/CartolaProvisoria_MN_MSPortType/CartolaProvisoria_MN_Input"));
	}

}