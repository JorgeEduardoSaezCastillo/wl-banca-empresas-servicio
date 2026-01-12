package cl.bice.banca.empresas.servicio.soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
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
import cl.bice.banca.empresas.servicio.model.common.BaseRequest;
import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.util.UtilPrintLog;
import cl.bice.genericoperprog.ws.ActualizaDetCampIn;
import cl.bice.genericoperprog.ws.ActualizaDetCampOut;
import cl.bice.genericoperprog.ws.ActualizaOpIn;
import cl.bice.genericoperprog.ws.ActualizaOpOut;
import cl.bice.genericoperprog.ws.ConsultaApoOpIn;
import cl.bice.genericoperprog.ws.ConsultaApoOpOut;
import cl.bice.genericoperprog.ws.FirmaOpDto;
import cl.bice.genericoperprog.ws.GenericOperProgWS;
import cl.bice.genericoperprog.ws.GenericOperProgWsService;
import cl.bice.genericoperprog.ws.ObjectFactory;

/**
 * Cliente para consultar el servicio GenericOperProgWS.
 * 
 * @author Cristian Pais (TINET)
 *
 */
@Service("ClienteGenericOperProg")
public class ClienteGenericOperProg {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteGenericOperProg.class);

	private URL url;

	GenericOperProgWsService service;
	private GenericOperProgWS impl;
	ObjectFactory objectFactory;

	@Autowired
	Properties propiedadesExterna;

	/**
	 * Inicializa el cliente del servicio.
	 */
	private void firstcall() {
		try {
			if (null == url) {
				this.url = new URL(propiedadesExterna.getProperty("servicios.url.operprog"));
				service = new GenericOperProgWsService(url);
				impl = service.getGenericOperProgWSPort();
				objectFactory = new ObjectFactory();
				Map<String, Object> requestContext = ((BindingProvider) impl).getRequestContext();
				requestContext.put("com.sun.xml.internal.ws.request.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.operprog.request-timeout")));
				requestContext.put("com.sun.xml.internal.ws.connect.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.operprog.connect-timeout")));
			}
		} catch (MalformedURLException e) {
			LOGGER.error("Exception {} {} ", ClienteGenericOperProg.class.getSimpleName(), e);
		}
	}

	/**
	 * Consulta las firmas de una operación.
	 * 
	 * @param numOperProg
	 * @return
	 * @throws BancaEmpresasException
	 */
	public ConsultaApoOpOut consultarFirmasOperacion(String numOperProg) throws BancaEmpresasException {
		try {
			firstcall();

			ConsultaApoOpIn consultaFirmaApo = objectFactory.createConsultaApoOpIn();
			consultaFirmaApo.setINNUMOPERPROG(numOperProg);

			UtilPrintLog.printXML(consultaFirmaApo, ConsultaApoOpIn.class, "REQUEST ConsultaApoOpIn");

			ConsultaApoOpOut consultaApoOpOut = impl.consultaFirmaOp(consultaFirmaApo);

			UtilPrintLog.printXML(consultaApoOpOut, ConsultaApoOpOut.class, "RESPONSE ConsultaApoOpOut");

			return consultaApoOpOut;
		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteGenericOperProg consultarFirmasOperacion: {}", e);
			throw new BancaEmpresasException();
		}
	}
	
	public ActualizaOpOut actualizarData(Map<String, String> mapData) {
//		boolean respuesta = false;
		ActualizaOpOut respuesta = new ActualizaOpOut();
		try {
			firstcall();
			LOGGER.info("ACTUALIZAR DATA DE OPERACIÓN EN PROGRESO NUM_OPER_PROG: [{}]", mapData.get(Constantes.NUM_OPER_PROG));

			ActualizaOpIn request = objectFactory.createActualizaOpIn();

			request.setINNUMOPERPROG((String) mapData.get(Constantes.NUM_OPER_PROG));
			if(mapData.get(Constantes.INESTADO_SOAP) != null )
				request.setINESTADO((String) mapData.get(Constantes.INESTADO_SOAP));
			if(mapData.get(Constantes.INNUMOPERTRF_SOAP) != null )
				request.setINNUMOPERTRF(mapData.get(Constantes.INNUMOPERTRF_SOAP));

			UtilPrintLog.printXML(request, ActualizaOpIn.class, "REQUEST ACTUALIZAR OPERACIONES EN PROCESO");

			ActualizaOpOut response = impl.actualizaOperProg(request);

//			mapData.put(Constantes.RESPONSE, response);

			UtilPrintLog.printXML(response, ActualizaOpOut.class, "RESPONSE ACTUALIZAR OPERACIONES EN PROCESO");

//			respuesta = true;
			respuesta = response;
		} catch (RuntimeException e) {
			if ("java.net.SocketTimeoutException: Read timed out".equalsIgnoreCase(e.getMessage())) {
				LOGGER.info("ERROR WS TIME OUT AL ACTUALIZAR OPERACIONES TBL_OPER_PROG, ERROR: {}", e);
			}
			else
				LOGGER.info("ERROR WS AL ACTUALIZAR OPERACIONES TBL_OPER_PROG, ERROR: {}", e);
		}
		return respuesta;
	}

	public ActualizaDetCampOut actualizarDetalle(String numOperProg, String inDetalle) throws BancaEmpresasException{
//		boolean respuesta = false;
		ActualizaDetCampOut respuesta = new ActualizaDetCampOut();
		try {
			firstcall();

			LOGGER.info("ACTUALIZAR DETALLE DE OPERACIONES EN PROGRESO: NUM_OPER_PROG: [{}]", numOperProg);
			ActualizaDetCampIn request = objectFactory.createActualizaDetCampIn();

			request.setINNUMOPERPROG(numOperProg);
			request.setINDETALLE(inDetalle);

			UtilPrintLog.printXML(request, ActualizaDetCampIn.class,
					"REQUEST ACTUALIZAR DETALLE OPERACIONES EN PROCESO");

			ActualizaDetCampOut response = impl.actualizaDetCamp(request);

//			variablesYErrores.put(Constantes.RESPONSE, response);

			UtilPrintLog.printXML(response, ActualizaDetCampOut.class,
					"RESPONSE ACTUALIZAR DETALLE OPERACIONES EN PROCESO");

			respuesta = response;
		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("[{}] ERROR SOAP  [{}]", numOperProg, e);
			throw new BancaEmpresasException();
		}
		return respuesta;

	}
	
	/**
	 * Valida si ya existe Firma del Apoderado en proceso de Aprobacion
	 * 
	 * @param numOperProg
	 * @param rutApoderado
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean existeFirma(String numOperProg, String rutApoderado) throws BancaEmpresasException {
		boolean existe = false;

		ConsultaApoOpOut consultaApoOpOut = consultarFirmasOperacion(numOperProg);

		if (consultaApoOpOut.getCodEstado() > 0) {

			List<FirmaOpDto> lista = consultaApoOpOut.getFirmaOpDto();
			for (FirmaOpDto firmaOp : lista) {
				if (firmaOp.getRutApodeado().equals(rutApoderado)) {
					existe = true;
					break;
				}
			}
		}

		return existe;
	}	

}
