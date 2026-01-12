package cl.bice.banca.empresas.servicio.soap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Service;


import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.util.FormateadorUtil;
import cl.bice.banca.empresas.servicio.util.UtilPrintLog;
import cl.bice.nominas.ws.ConsultaApoOpIn;
import cl.bice.nominas.ws.ConsultaApoOpOut;
import cl.bice.nominas.ws.ConsultaParamNominas;
import cl.bice.nominas.ws.ConsultaParamNominasResponse;
import cl.bice.nominas.ws.ConsultaParametrosIn;
import cl.bice.nominas.ws.ConsultaParametrosOut;
import cl.bice.nominas.ws.EliminaApoOpIn;
import cl.bice.nominas.ws.EliminaApoOpOut;
import cl.bice.nominas.ws.EliminaFirmaOp;
import cl.bice.nominas.ws.FirmaOpVo;
import cl.bice.nominas.ws.OperaCargoOnlineIn;
import cl.bice.nominas.ws.OperaCargoOnlineOut;
import cl.bice.nominas.ws.ActualizaDetCampIn;
import cl.bice.nominas.ws.ActualizaDetCampOut;
import cl.bice.nominas.ws.ActualizaNominaIn;
import cl.bice.nominas.ws.ActualizaNominaOut;
import cl.bice.nominas.ws.ActualizaOpIn;
import cl.bice.nominas.ws.ActualizaOpOut;
import cl.bice.nominas.ws.ApliCargoOnlineIn;
import cl.bice.nominas.ws.AplicaCargoOnline;
import cl.bice.nominas.ws.CargoOnlineOut;
import cl.bice.nominas.ws.RegistraApoOpIn;
import cl.bice.nominas.ws.RegistraApoOpOut;

/**
 * Cliente del servicio de nominas.
 * 
 * @author Cristian Pais (TINET)
 *
 */
@Service("ClienteOperacionNomina")
public class ClienteOperacionNomina extends BaseClienteServicioNominas {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClienteOperacionNomina.class);

	public static final String ACTUALIZAR_DETALLE_OPERACION_DETALLE_PROCESANDO = "2=PROCESANDO;";
	public static final String ACTUALIZAR_DETALLE_OPERACION_DETALLE_NO_PROCESANDO = "2= ;";

	/**
	 * Actualiza detalle de la operación en tbl_detalle_camp
	 * 
	 * @param inDetalle
	 * @param inNumOperProg
	 * @return
	 * @throws BancaEmpresasException
	 */
	@SuppressWarnings("rawtypes")
	public ActualizaDetCampOut actualizarDetalleOperacion(String inDetalle, String inNumOperProg)
			throws BancaEmpresasException {
		LOGGER.info("ClienteOperacionNomina actualizarDetalleOperacion: inDetalle[{}] inNumOperProg[{}]", inDetalle,
				inNumOperProg);
		try {
			firstcall();

			ActualizaDetCampIn actualizaDetCampIn = objectFactory.createActualizaDetCampIn();
			actualizaDetCampIn.setINDETALLE(inDetalle);
			actualizaDetCampIn.setINNUMOPERPROG(inNumOperProg);

			UtilPrintLog.printXML(actualizaDetCampIn, ActualizaDetCampIn.class, "REQUEST ActualizaDetCampIn");

			ActualizaDetCampOut response = impl.actualizaDetCamp(actualizaDetCampIn);

			UtilPrintLog.printXML(response, ActualizaDetCampOut.class, "RESPONSE ActualizaDetCampOut");

			if (response != null && response.getCodEstado() == 0) {
				return response;
			} else {
				throw new BancaEmpresasException();
			}

		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteOperacionNomina actualizarDetalleOperacion: {}", e);
			throw new BancaEmpresasException();
		}
	}

	/**
	 * Actualiza estado de las firmas de la operación en tabla tbl_oper_prog.
	 *
	 * @param actualizaOpIn
	 * @return
	 * @throws BancaEmpresasException
	 */
	@SuppressWarnings("rawtypes")
	public ActualizaOpOut tblOperacionProg(ActualizaOpIn actualizaOpIn) throws BancaEmpresasException {
		LOGGER.info("ClienteOperacionNomina tblOperacionProg");
		try {
			firstcall();

			UtilPrintLog.printXML(actualizaOpIn, ActualizaOpIn.class, "REQUEST ActualizaOpIn");

			ActualizaOpOut response = impl.actualizaOperProg(actualizaOpIn);

			UtilPrintLog.printXML(response, ActualizaOpOut.class, "RESPONSE ActualizaOpOut");

			if (response != null && response.getCodEstado() == 0) {
				return response;
			} else {
				throw new BancaEmpresasException();
			}

		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteOperacionNomina tblOperacionProg: {}", e);
			throw new BancaEmpresasException();
		}
	}

	/**
	 * Actualiza estado de las firmas de la operación en tabla tbl_oper_prog.
	 * 
	 * @param intEstado     30 - sin firmar, 40 - firma parcial, 50 - firma completa
	 * @param inNumOperProg
	 * @return
	 * @throws BancaEmpresasException
	 */
	@SuppressWarnings("rawtypes")
	public ActualizaOpOut tblOperacionProg(String intEstado, String inNumOperProg) throws BancaEmpresasException {
		ActualizaOpIn actualizaOpIn = objectFactory.createActualizaOpIn();
		actualizaOpIn.setINESTADO(intEstado);
		actualizaOpIn.setINNUMOPERPROG(inNumOperProg);

		return tblOperacionProg(actualizaOpIn);
	}

	/**
	 * Actualiza en tbl_apo_op el registro de la operación con nuevo apoderado.
	 * 
	 * @param inDatoFirma
	 * @param inNomApoderado
	 * @param inNumOperProg
	 * @param inRutApoderado
	 * @return
	 * @throws BancaEmpresasException
	 */
	@SuppressWarnings("rawtypes")
	public RegistraApoOpOut registraFirma(String inDatoFirma, String inNomApoderado, String inNumOperProg,
			String inRutApoderado) throws BancaEmpresasException {
		LOGGER.info("ClienteOperacionNomina registraFirma: inDatoFirma[{}] inNomApoderado[{}] inNumOperProg[{}]",
				inDatoFirma, inNomApoderado, inNumOperProg);
		try {
			firstcall();
			RegistraApoOpIn registraApoOpIn = objectFactory.createRegistraApoOpIn();
			registraApoOpIn.setINDATOFIRMA(inDatoFirma);
			registraApoOpIn.setINNOMAPODERADO(inNomApoderado);
			registraApoOpIn.setINNUMOPERPROG(inNumOperProg);
			registraApoOpIn.setINRUTAPODERADO(inRutApoderado);

			UtilPrintLog.printXML(registraApoOpIn, RegistraApoOpIn.class, "REQUEST RegistraApoOpIn");

			RegistraApoOpOut response = impl.registraFirmaOp(registraApoOpIn);

			UtilPrintLog.printXML(response, RegistraApoOpOut.class, "RESPONSE RegistraApoOpOut");

			if (response != null && response.getCodEstado() == 0) {
				return response;
			} else {
				throw new BancaEmpresasException();
			}

		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteOperacionNomina registraFirmaOp: {}", e);
			throw new BancaEmpresasException();
		}
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

			List<FirmaOpVo> lista = consultaApoOpOut.getFirmaOpDto();
			for (FirmaOpVo firmaOp : lista) {
				if (firmaOp.getRutApoderado().equalsIgnoreCase(rutApoderado)) {
					existe = true;
					break;
				}
			}
		}

		return existe;
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
			LOGGER.error("Error en ClienteOperacionNomina consultarFirmasOperacion: {}", e);
			throw new BancaEmpresasException();
		}
	}

	/**
	 * Metodo que valida si Rut cliente y cta cargo, estan habilitados para operar
	 * con Cargo Online.
	 * 
	 * @param rutCliente
	 * @param numCtaCargo
	 * @return boolean
	 * @throws Exception
	 */
	public boolean operaCargoOnline(String rutCliente, String numCtaCargo) throws BancaEmpresasException {
		boolean operaOnline = false;
		try {
			firstcall();

			OperaCargoOnlineIn operaCargoOnlineRequest = objectFactory.createOperaCargoOnlineIn();
			operaCargoOnlineRequest.setRutCliente(rutCliente);
			operaCargoOnlineRequest.setNumCtaCargo(numCtaCargo);

			UtilPrintLog.printXML(operaCargoOnlineRequest, OperaCargoOnlineIn.class, "REQUEST OperaCargoOnlineIn");

			OperaCargoOnlineOut operaCargoOnlineResponse = impl.operaOnline(operaCargoOnlineRequest);

			UtilPrintLog.printXML(operaCargoOnlineResponse, OperaCargoOnlineOut.class, "RESPONSE OperaCargoOnlineOut");

			if (operaCargoOnlineResponse.getCodResult() == 1) {
				operaOnline = true;
			} else if (operaCargoOnlineResponse.getCodResult() == -1) {
				operaOnline = false;
				LOGGER.info(
						"ClienteOperacionNomina.operaCargoOnline Response: " + operaCargoOnlineResponse.getMsgResult());
			} else {
				throw new BancaEmpresasException("ClienteOperacionNomina.operaCargoOnline Response Error: "
						+ operaCargoOnlineResponse.getMsgResult());
			}

			return operaOnline;

		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteOperacionNomina consultarFirmasOperacion: {}", e);
			throw new BancaEmpresasException();
		}

	}

	/**
	 * Actualiza en tbl_apo_op el registro de la operación con nuevo apoderado.
	 * 
	 * @param inDatoFirma
	 * @param inNomApoderado
	 * @param inNumOperProg
	 * @param inRutApoderado
	 * @return
	 * @throws BancaEmpresasException
	 */
	@SuppressWarnings("rawtypes")
	public EliminaApoOpOut eliminaFirma(String inNumOperProg, String inRutApoderado) throws BancaEmpresasException {
		LOGGER.info("ClienteOperacionNomina eliminaFirma: inNumOperProg[{}], inRutApoderado[{}]", inNumOperProg,
				inRutApoderado);
		try {
			firstcall();
			EliminaApoOpIn eliminaApoOpIn = objectFactory.createEliminaApoOpIn();
			eliminaApoOpIn.setINNUMOPERPROG(inNumOperProg);
			eliminaApoOpIn.setINRUTAPODERADO(inRutApoderado);

			UtilPrintLog.printXML(eliminaApoOpIn, EliminaApoOpIn.class, "REQUEST EliminaApoOpIn");

			EliminaApoOpOut response = impl.eliminaFirmaOp(eliminaApoOpIn);

			UtilPrintLog.printXML(response, EliminaApoOpOut.class, "RESPONSE EliminaApoOpOut");

			if (response != null && response.getCodEstado() == 0) {
				return response;
			} else {
				throw new BancaEmpresasException();
			}

		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteOperacionNomina eliminaFirma: {}", e);
			throw new BancaEmpresasException();
		}
	}

	/**
	 * Consultar parámetros nóminas
	 * 
	 * @param nomParam
	 * @param nomTipo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ConsultaParametrosOut consultaParamNomina(String nomParam, String nomTipo) throws BancaEmpresasException {

		LOGGER.info("ClienteOperacionNomina consultaParamNomina: nomParam[{}], nomTipo[{}]", nomParam, nomTipo);

		try {
			firstcall();

			ConsultaParametrosIn consultaParametrosIn = objectFactory.createConsultaParametrosIn();

			consultaParametrosIn.setINNOMPARAM(nomParam);
			consultaParametrosIn.setINNOMTIPO(nomTipo);

			UtilPrintLog.printXML(consultaParametrosIn, ConsultaParametrosIn.class, "REQUEST ConsultaParametrosIn");

			ConsultaParametrosOut response = impl.consultaParamNominas(consultaParametrosIn);

			UtilPrintLog.printXML(response, ConsultaParametrosOut.class, "RESPONSE ConsultaParametrosOut");

			if (response != null && response.getCodEstado() != 0) {
				return response;
			} else {
				throw new BancaEmpresasException();
			}

		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteOperacionNomina consultaParamNomina: {}", e);
			throw new BancaEmpresasException();
		}
	}

	/**
	 * Consultar parámetros validación
	 * 
	 * @param nomParam
	 * @param nomTipo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ConsultaParametrosOut consultaParamValidacion(String nomParam, String nomTipo)
			throws BancaEmpresasException {

		LOGGER.info("ClienteOperacionNomina consultaParamValidacion: nomParam[{}], nomTipo[{}]", nomParam, nomTipo);

		try {
			firstcall();

			ConsultaParametrosIn consultaParametrosIn = objectFactory.createConsultaParametrosIn();

			consultaParametrosIn.setINNOMPARAM(nomParam);
			consultaParametrosIn.setINNOMTIPO(nomTipo);

			UtilPrintLog.printXML(consultaParametrosIn, ConsultaParametrosIn.class, "REQUEST ConsultaParametrosIn");

			ConsultaParametrosOut response = impl.consultaParamValidacion(consultaParametrosIn);

			UtilPrintLog.printXML(response, ConsultaParametrosOut.class, "RESPONSE ConsultaParametrosOut");

			if (response != null && response.getCodEstado() != 0) {
				return response;
			} else {
				throw new BancaEmpresasException();
			}

		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteOperacionNomina consultaParamValidacion: {}", e);
			throw new BancaEmpresasException();
		}
	}

	/**
	 * Aplicar cargo online a una nómina
	 * 
	 * @param nomParam
	 * @param nomTipo
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public CargoOnlineOut aplicaCargoOnline(String cuentaCargo, String numNomina, String rutCliente, String saldoDisp)
			throws BancaEmpresasException {

		LOGGER.info("ClienteOperacionNomina aplicaCargoOnline: cuentaCargo[{}], numNomina[{}], rutCliente[{}]",
				cuentaCargo, numNomina, rutCliente);

		try {
			firstcall();

			ApliCargoOnlineIn aplicaCargoOnline = objectFactory.createApliCargoOnlineIn();

			aplicaCargoOnline.setNumCtaCargo(cuentaCargo);
			aplicaCargoOnline.setNumNomina(numNomina);
			aplicaCargoOnline.setRutCliente(rutCliente);
			aplicaCargoOnline.setSaldoDisp(saldoDisp);

			UtilPrintLog.printXML(aplicaCargoOnline, ApliCargoOnlineIn.class, "REQUEST ApliCargoOnlineIn");

			CargoOnlineOut response = impl.aplicaCargoOnline(aplicaCargoOnline);

			UtilPrintLog.printXML(response, CargoOnlineOut.class, "RESPONSE CargoOnlineOut");

			if (response != null) {
				return response;
			} else {
				throw new BancaEmpresasException();
			}

		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteOperacionNomina aplicaCargoOnline: {}", e);
			throw new BancaEmpresasException();
		}
	}

	/**
	 * Actualiza estado y accion de la tbl_nominasenviadas y tbl_oper_prog.
	 * 
	 * @param id_sesion
	 * @param num_nomina
	 * @param cod_estado
	 * @param cod_accion
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public boolean actualizaNominaEnv(String num_nomina, String cod_estado, String cod_accion)
			throws BancaEmpresasException {

		boolean status = true;
		LOGGER.info("ClienteOperacionNomina.actualizaNominaEnv Ini : num_nomina " + num_nomina + " cod_estado:"
				+ cod_estado + " cod_accion:" + cod_accion);

		try {
			firstcall();

			ActualizaNominaIn actualizaNominaIn = objectFactory.createActualizaNominaIn();

			actualizaNominaIn.setINNUMNOMINA(num_nomina);
			actualizaNominaIn.setINCODESTADO(cod_estado);
			actualizaNominaIn.setINCODACCION(cod_accion);

			UtilPrintLog.printXML(actualizaNominaIn, ActualizaNominaIn.class, "REQUEST ActualizaNominaIn");

			ActualizaNominaOut response = impl.actualizaNomina(actualizaNominaIn);

			UtilPrintLog.printXML(response, ActualizaNominaOut.class, "RESPONSE ActualizaNominaOut");

			if (response == null || response.getCodEstado() != 0) {
				status = false;
				LOGGER.warn("ClienteOperacionNomina.actualizaNominaEnv Mensaje error: [{}]", response.getMsgStatus());
				throw new BancaEmpresasException();
			}

		} catch (WebServiceException | CannotGetJdbcConnectionException e) {
			LOGGER.error("Error en ClienteOperacionNomina actualizaNominaEnv: [{}]", e);
			status = false;
			throw new BancaEmpresasException();
		}
		return status;

	}

	/**
	 * Genera una lista de ruts para firmar una operación: ruts que ya han firmado +
	 * rut que va a firmar
	 * 
	 * @param numOperProg
	 * @param rutFirmante
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String obtenerRutsFirmantes(String numOperProg, String rutFirmante) throws BancaEmpresasException {
		StringBuilder resultado = new StringBuilder();

		String separador = "";
		ConsultaApoOpOut consultaApoOpOut = consultarFirmasOperacion(numOperProg);

		if (consultaApoOpOut.getCodEstado() > 0) {

			List<FirmaOpVo> lista = consultaApoOpOut.getFirmaOpDto();
			for (FirmaOpVo firmaOp : lista) {
				String rutApoderado = FormateadorUtil.rutSinCero(firmaOp.getRutApoderado());
				if (!rutFirmante.trim().equals(rutApoderado.trim())) {
					resultado.append(separador).append(FormateadorUtil.rutSinCero(firmaOp.getRutApoderado()));
					separador = ",";
				}
			}
		}

		if ("".equals(resultado.toString().trim()))
			resultado.append(rutFirmante);
		else
			resultado.append(",").append(rutFirmante);

		return resultado.toString();
	}

}
