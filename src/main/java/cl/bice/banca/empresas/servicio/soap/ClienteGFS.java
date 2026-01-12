package cl.bice.banca.empresas.servicio.soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import javax.xml.ws.BindingProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.model.common.BaseRequest;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.common.ValidadorUtil;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.util.UtilPrintLog;
import cl.bice.gfs.genericfinancialservice.ws.FaultMessage;
import cl.bice.gfs.genericfinancialservice.ws.GenericFinancialService;
import cl.bice.gfs.genericfinancialservice.ws.GenericFinancialService_Service;
import cl.bice.gfs.ws.GENERICFINANCIALSERVICEIN;
import cl.bice.gfs.ws.GENERICFINANCIALSERVICEOUT;
import cl.bice.gfs.ws.ObjectFactory;

@Service("ClienteGFS")
public class ClienteGFS {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteGFS.class);
	private URL url;
	GenericFinancialService_Service service;
	private GenericFinancialService impl;
	private ObjectFactory objectFactory;

	@Autowired
	Properties propiedadesExterna;

	private void firstcall(BaseRequest base) {
		try {
			if (null == url || null == impl || null ==service) {
				url = new URL(propiedadesExterna.getProperty("servicios.url.gfs"));
				service = new GenericFinancialService_Service(url);
				impl = service.getGenericFinancialServicePt();
				objectFactory = new ObjectFactory();
				Map<String, Object> requestContext = ((BindingProvider) impl).getRequestContext();
				requestContext.put("com.sun.xml.internal.ws.request.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.gfs.request-timeout")));
				requestContext.put("com.sun.xml.internal.ws.connect.timeout",
						Integer.valueOf(propiedadesExterna.getProperty("servicios.gfs.connect-timeout")));
			}
		} catch (RuntimeException | MalformedURLException e) {

			LOGGER.info("[{}] [{}] [{}] ERROR CREANDO CLIENTE GFS[{}]", base.getDispositivo(), base.getToken(),
					base.getRut(), ClienteGFS.class.getSimpleName(), e);
		}
	}

	public boolean call(Map<String, Object> variablesYErrores) {

		boolean respuesta = false;
		BaseRequest base = new BaseRequest((String) variablesYErrores.get(Constantes.TOKEN),
				(String) variablesYErrores.get(Constantes.RUT), (String) variablesYErrores.get(Constantes.DISPOSITIVO));
		try {
			firstcall(base);

			GENERICFINANCIALSERVICEIN request = objectFactory.createGENERICFINANCIALSERVICEIN();

			request.setCANAL((String) variablesYErrores.get(Constantes.CANAL_SOAP));
			request.setSUCURSAL((String) variablesYErrores.get(Constantes.SUCURSAL_SOAP));
			request.setMODOTRX((String) variablesYErrores.get(Constantes.MODOTRX_SOAP));
			request.setMODOINVOCACION((String) variablesYErrores.get(Constantes.MODOINVOCACION_SOAP));
			request.setRUTCLIENTE((String) variablesYErrores.get(Constantes.RUTCLIENTE_SOAP));
			request.setTRACENUMBER((String) variablesYErrores.get(Constantes.TRACENUMBER_SOAP));
			request.setFECHACONTABLE((String) variablesYErrores.get(Constantes.FECHACONTABLE_SOAP));

			request.setMONEDACARGO((String) variablesYErrores.get(Constantes.MONEDACARGO_SOAP));
			request.setBANCOCARGO((String) variablesYErrores.get(Constantes.BANCOCARGO_SOAP));
			request.setCUENTACARGO((String) variablesYErrores.get(Constantes.CUENTACARGO_SOAP));
			request.setTIPOCUENTACARGO((String) variablesYErrores.get(Constantes.TIPOCUENTACARGO_SOAP));
			request.setMONTOCARGO((String) variablesYErrores.get(Constantes.MONTOCARGO_SOAP));

			request.setRUTABONO((String) variablesYErrores.get(Constantes.RUTABONO_SOAP));
			request.setNOMBREABONO((String) variablesYErrores.get(Constantes.NOMBREABONO_SOAP));
			request.setMONEDAABONO((String) variablesYErrores.get(Constantes.MONEDAABONO_SOAP));
			request.setCUENTABONO((String) variablesYErrores.get(Constantes.CUENTABONO_SOAP));
			request.setTIPOCUENTABONO((String) variablesYErrores.get(Constantes.TIPOCUENTABONO_SOAP));

			request.setIDSERVICIO((String) variablesYErrores.get(Constantes.IDSERVICIO_SOAP));
			request.setREFERENCIA((String) variablesYErrores.get(Constantes.REFERENCIA_SOAP));
			request.setDOCCARGO((String) variablesYErrores.get(Constantes.DOCCARGO_SOAP));
			request.setDOCABONO((String) variablesYErrores.get(Constantes.DOCABONO_SOAP));

			UtilPrintLog.printXML(request, GENERICFINANCIALSERVICEIN.class, "REQUEST GFS", base);

			GENERICFINANCIALSERVICEOUT response = impl.genericFinancialService(request);
			variablesYErrores.put(Constantes.RESPONSE, response);
			UtilPrintLog.printXML(response, GENERICFINANCIALSERVICEOUT.class, "RESPONSE GFS", base);
			respuesta = true;

		} catch (Exception e) {
			variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, true);

			if ("java.net.SocketTimeoutException: Read timed out".equalsIgnoreCase(e.getMessage()))
				setDetalleError(variablesYErrores, e, Constantes.ERROR_MSJ_E004_CLIENTE_TO, Constantes.E004);
			else
				setDetalleError(variablesYErrores, e, Constantes.ERROR_MSJ_E005_CLIENTE_GFS, Constantes.E005);

		}
		return respuesta;
	}

	public ValidadorUtil call(GENERICFINANCIALSERVICEIN request, BaseRequest base) {
		ValidadorUtil validadorUtil = new ValidadorUtil(false);

		try {
			validadorUtil = callGenericFinancialService(request,base);
		} catch (Exception e) {

			if ("java.net.SocketTimeoutException: Read timed out".equalsIgnoreCase(e.getMessage()))
				validadorUtil.setEstado(new Estado(Constantes.E003,
						propiedadesExterna.getProperty(Constantes.ERROR_MSJ_E003_CLIENTE_TO),
						Constantes.ERROR_MSJ_E003_CLIENTE_TO));
			else
				validadorUtil.setEstado(new Estado(Constantes.E009,
						propiedadesExterna.getProperty(Constantes.ERROR_MSJ_E009_CLIENTE_GFS),
						Constantes.ERROR_MSJ_E009_CLIENTE_GFS));

			LOGGER.error("[{}] [{}] [{}] ERROR SOAP [{}]", base.getDispositivo(), base.getToken(), base.getRut(),
					ClienteGFS.class.getSimpleName(), e);

		}
		return validadorUtil;
	}
	
	public ValidadorUtil callGenericFinancialService(GENERICFINANCIALSERVICEIN request, BaseRequest base) throws Exception {
		ValidadorUtil validadorUtil = new ValidadorUtil(false);
		
		firstcall(base);

		UtilPrintLog.printXML(request, GENERICFINANCIALSERVICEIN.class, "REQUEST GFS", base);

		GENERICFINANCIALSERVICEOUT response = impl.genericFinancialService(request);

		validadorUtil.setObjetoRespuesta(response);

		UtilPrintLog.printXML(response, GENERICFINANCIALSERVICEOUT.class, "RESPONSE GFS", base);
		validadorUtil.setResultado(true);
		
		return validadorUtil;
	}

	private void setDetalleError(Map<String, Object> variablesYErrores, Exception e, String errorDetalle,
			String errorCode) {
		LOGGER.error("[{}] [{}] [{}] ERROR SOAP [{}]", variablesYErrores.get(Constantes.DISPOSITIVO),
				variablesYErrores.get(Constantes.TOKEN), variablesYErrores.get(Constantes.RUT),
				ClienteGFS.class.getSimpleName(), e);

		variablesYErrores.put(Constantes.RESPONSE_STATUS, errorCode);
		variablesYErrores.put(Constantes.RESPONSE_GLOSA, propiedadesExterna.getProperty(errorDetalle));
		variablesYErrores.put(Constantes.RESPONSE_GLOSA_PROPERTIES, errorDetalle);

	}
}
