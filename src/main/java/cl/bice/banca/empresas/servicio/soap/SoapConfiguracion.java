package cl.bice.banca.empresas.servicio.soap;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import cl.bice.banca.empresas.servicio.soap.balance.cliente.BalanceCliente;
import cl.bice.banca.empresas.servicio.soap.cartola.cliente.CartolaCliente;

@Configuration
public class SoapConfiguracion {

	/**
	 * Propiedades Externas.
	 */
	@Autowired
	private Properties propiedadesExterna;

	@Bean
	public Jaxb2Marshaller marshaller() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setCheckForXmlRootElement(false);
		marshaller.setContextPaths("cl.bice.banca.empresas.servicio.soap.balance");
		return marshaller;
	}

	@Bean
	public Jaxb2Marshaller marshaller2() {
		Jaxb2Marshaller marshaller2 = new Jaxb2Marshaller();
		marshaller2.setCheckForXmlRootElement(false);
		marshaller2.setContextPaths("cl.bice.banca.empresas.servicio.soap.cartola");
		return marshaller2;
	}

	private WebServiceMessageSender messageSender() {
		HttpComponentsMessageSender sender = new HttpComponentsMessageSender();
		sender.setConnectionTimeout(Integer.valueOf(propiedadesExterna.getProperty("timeout.connection", "1200000")));
		sender.setReadTimeout(Integer.valueOf(propiedadesExterna.getProperty("timeout.read", "1200000")));
		return sender;
	}

	@Bean
	public BalanceCliente balanceCliente(Jaxb2Marshaller marshaller) {
		BalanceCliente cliente = new BalanceCliente();
		cliente.getWebServiceTemplate().setCheckConnectionForFault(false);
		cliente.setDefaultUri(propiedadesExterna.getProperty("servicios.url.balance"));
		cliente.setMarshaller(marshaller);
		cliente.setUnmarshaller(marshaller);
		cliente.setMessageSender(messageSender());
		return cliente;
	}

	@Bean
	public CartolaCliente cartolaCliente(Jaxb2Marshaller marshaller2) {
		CartolaCliente client = new CartolaCliente();
		client.setDefaultUri(propiedadesExterna.getProperty("servicios.url.cartola.provisoria"));
		client.setMarshaller(marshaller2());
		client.setUnmarshaller(marshaller2());
		client.setMessageSender(messageSender());
		return client;
	}

}
