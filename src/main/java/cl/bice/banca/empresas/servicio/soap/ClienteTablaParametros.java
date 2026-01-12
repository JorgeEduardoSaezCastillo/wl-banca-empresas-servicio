package cl.bice.banca.empresas.servicio.soap;

import java.util.Map;

import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.util.UtilPrintLog;
import cl.bice.nominas.ws.ConsultaParametrosIn;
import cl.bice.nominas.ws.ConsultaParametrosOut;

/**
 * Cliente para consultar la tabla de parametros.
 * 
 * @author Fibacache
 *
 */
@Service("ClienteTablaParametros")
public class ClienteTablaParametros extends BaseClienteServicioNominas {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteTablaParametros.class);
	public static final String NOMPARAM = "NOMPARAM";
	public static final String NOMTIPO = "NOMTIPO";

	/**
	 * Recibe nombre y tipo parámetro y consulta la tabla parámetros.
	 * 
	 * @param input
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean call(Map<String, Object> input) throws BancaEmpresasException {
		try {

			firstcall();

			ConsultaParametrosIn request = objectFactory.createConsultaParametrosIn();

			request.setINNOMPARAM((String) input.get(NOMPARAM));
			request.setINNOMTIPO((String) input.get(NOMTIPO));

			UtilPrintLog.printXML(request, ConsultaParametrosIn.class, "REQUEST TABLA PARAMETROS");

			ConsultaParametrosOut response = impl.consultaParamValidacion(request);
			input.put("respuesta", response);
			UtilPrintLog.printXML(response, ConsultaParametrosOut.class, "RESPONSE TABLA PARAMETROS");
			return true;
		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error servicio TablaParametros : {}", e);
			throw new BancaEmpresasException();
		}
	}

	public boolean call2() {
		return false;
	}

}
