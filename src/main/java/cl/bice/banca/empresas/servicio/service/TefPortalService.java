package cl.bice.banca.empresas.servicio.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesPortalRequest;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.soap.ClienteGFS;
import cl.bice.banca.empresas.servicio.soap.ClienteGenericOperProg;
import cl.bice.genericoperprog.ws.ActualizaDetCampOut;
import cl.bice.gfs.ws.GENERICFINANCIALSERVICEOUT;
import cl.bice.nominas.ws.ParametrosVo;
import cl.bice.banca.empresas.servicio.exception.BussinesException;


/**
 * Servicios GFS
 * 
 * 
 */
@Service
public class TefPortalService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TefPortalService.class);
	
	@Autowired
	OperacionesEmpresaPortalService operacionesEmpresaPortalService;
	
	@Autowired
	Properties propiedadesExterna;
	
	@Autowired
	PortalOrawRepository portalOrawRepository;
	
	@Autowired
	@Qualifier("ClienteGenericOperProg")
	ClienteGenericOperProg clienteGenericOperProg;

	@Autowired
	EmpresasService empresasService;

	@Autowired
	@Qualifier("ClienteGFS")
	ClienteGFS clienteGFS;
	
	@Autowired
	ConsultarParametrosService consultarParametrosService;
	

		
	
	/**
	 * LLama al servicio GFS y sigue los posibles flujos Ok y NOK
	 * 
	 * @param paramsGfs Map<String, Object> Data necesaria para llamar al
	 * servicio GFS
	 */
	public Map<String, Object> execGFS(LiberarOperacionesPortalRequest request, Map<String, Object> 
			mapOperacionSp, boolean esSoloCargo) throws BancaEmpresasException {
		
		Map<String, Object> paramsGfs = this.setearDatosGfs(mapOperacionSp, esSoloCargo, Constantes.MODOTRX_DIRECTA);
		if (clienteGFS.call(paramsGfs)) {
			this.respuestaOKPortalGFS(paramsGfs, esSoloCargo, request);
		} else {
			this.respuestaNOKPortalGfs(paramsGfs);
		}

		return paramsGfs;
	}
	
	
	/**
	 * Liberaciones Masivas <br>
	 * Flujo OK al llamar a GFS. Se procede a analizar respuesta de GFS, en caso de
	 * que sea la correcta se llama al servicio de registrar operaciones y al
	 * servicio de mensajeria. en caso de ser erronea se llama al servicio de
	 * registrar operaciones y si es necesario se efectua una reversa a GFS.
	 * 
	 * @param variablesYErrores Map<String, Object> posee la data necesaria para
	 *                          llamar al servicio de registrar operaciones .
	 * @return
	 * @throws BussinesException
	 */
	private Map<String, Object> respuestaOKPortalGFS(Map<String, Object> variablesYErrores, boolean esSoloCargo, LiberarOperacionesPortalRequest request) throws BancaEmpresasException{
		GENERICFINANCIALSERVICEOUT resp = (GENERICFINANCIALSERVICEOUT) variablesYErrores.get(Constantes.RESPONSE);
		
		//valores iniciales
		variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, false);
		variablesYErrores.put(Constantes.COD_ESTADO_LIBERACION, Constantes.COD_ESTADO_REVERSA);
		variablesYErrores.put(Constantes.ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
		variablesYErrores.put(Constantes.GLOSA_ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SISTEMA));
		
		this.flujosRespuestaOKPortalGFS(variablesYErrores, esSoloCargo, request, resp);
		
		if((boolean)variablesYErrores.get(Constantes.FLAG_REVERSA_GFS)) { 
			this.reversaPortalGfs(variablesYErrores);
			operacionesEmpresaPortalService.actualizaEstadoOperProg((String)variablesYErrores.get(Constantes.NUM_OPER_PROG), Integer.parseInt(Constantes.COD_ESTADO_REVERSA));
			this.execRollbackDetalleCampLiberar((String)variablesYErrores.get(Constantes.NUM_OPER_PROG));
		}
		else if(!resp.getESTADO().equals(Constantes.CONSTANTE_CERO)) {//si no hay reversa pero hubo error en gfs, se deben actualizar los datos en tbl_detalle_camp
			operacionesEmpresaPortalService.actualizaEstadoOperProg((String)variablesYErrores.get(Constantes.NUM_OPER_PROG), Integer.parseInt(Constantes.COD_ESTADO_REVERSA));
			this.execRollbackDetalleCampLiberar((String)variablesYErrores.get(Constantes.NUM_OPER_PROG));
		}
				
		return variablesYErrores;
	}
	
	
	public void execRollbackDetalleCampLiberar(String numOperProg) {
		this.actualizaDatosDetalleCamp(numOperProg, Constantes.COD_CAMPO_ESTADO_PROCESO, Constantes.VAL_CAMPO_ESTADO_NO_PROCESADA);
		this.actualizaDatosDetalleCamp(numOperProg, Constantes.COD_CAMPO_RUT_LIBERADOR, Constantes.VALOR_DEFAULT_DET_CAMP);
		this.actualizaDatosDetalleCamp(numOperProg, Constantes.COD_CAMPO_NOM_LIBERADOR, "");
		this.actualizaDatosDetalleCamp(numOperProg, Constantes.COD_CAMPO_FECHA_HORA_LIBERACION, "");
		this.actualizaDatosDetalleCamp(numOperProg, Constantes.COD_CAMPO_CANAL_LIBERACION_PORTAL, "");
	}
	
	
	private boolean actualizaDatosDetalleCamp(String numeroOperProg, String codCampo, String valCampo) {
		boolean resultado = false;
		Map<String, Object> parametros = new HashMap<>();
		parametros.put(Constantes.PARAMETRO_EMPRESAS_V_VAL_CAMPO, valCampo);
		parametros.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPER_PROG, numeroOperProg);
		parametros.put(Constantes.PARAMETRO_EMPRESAS_V_COD_CAMPO, codCampo);
		try {
			portalOrawRepository.actualizaDetalleCampPortal(parametros);
			resultado = true;
		}
		catch (Exception e) {
			LOGGER.info("TefPortalService.actualizaDatosDetalleCamp - Error en actualización tbl_detalle_camp. numOperProg: [{}] codCampo:[{}] valCampo:[{}] Error:[{}]",
					numeroOperProg, codCampo, valCampo, e.getMessage());
		}
		
		return resultado;
				
	}	
	
	

	/**
	 * Liberaciones Masivas <br>
	 * Revisa posibles flujos de la respuesta OK del GFS y determina acciones a seguir
	 * 
	 * @param variablesYErrores Map<String, Object> posee la data necesaria para
	 *                          llamar al servicio de registrar operaciones .
	 * @param esSoloCargo
	 * @param request
	 * @param resp
	 * @return
	 * @throws BussinesException
	 */
	private void flujosRespuestaOKPortalGFS(Map<String, Object> variablesYErrores, boolean esSoloCargo, LiberarOperacionesPortalRequest request, GENERICFINANCIALSERVICEOUT resp) 
			throws BancaEmpresasException{
		
		if (resp.getESTADO().equals(Constantes.CONSTANTE_CERO)){
			if(null != resp.getIDOPERACIONFCC() && null != resp.getFECHACONTABLEOUT()
				&& null != resp.getNROREFERENCIA() && null != resp.getFECHA()) {
				
				this.flujosTodosLosTagsPortal(variablesYErrores, esSoloCargo, request, resp);
				
			}else {
				LOGGER.info("Error GFS (no trae todos los tags)");
				variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, true);
			}
			
		}
		else { 
			if(resp.getESTADO().equals("-1") || resp.getESTADO().equals("2033") ||  (resp.getESTADO() != null && isCodigoReversaGfs(resp.getESTADO().trim()))  ) {
				LOGGER.error("Error GFS, rutCliente: [{}] rutUsuario: [{}] numOperProg [{}] numCtaCargo [{}] monto [{}] codError: [{}] glosaError: [{}]", 
						request.getRutEmpresa(), request.getRutApoderado(), variablesYErrores.get("NUM_OPER_PROG"), variablesYErrores.get("CUENTACARGO_SOAP"), 
						variablesYErrores.get("MONTOCARGO_SOAP"), resp.getESTADO() , resp.getDESCRIPCION());
				variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, true);
			}
			else { 
				variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, false);
			}
				
			if(resp.getDESCRIPCION().toUpperCase().contains("SALDO")) {
				variablesYErrores.put(Constantes.GLOSA_ESTADO_LIBERACION, resp.getDESCRIPCION());
			}
				
		}
	}
	

	/**
	 * Flujo a seguir cuando respuesta GFS contiene todos los tags
	 * 
	 * @param variablesYErrores Map<String, Object> posee la data necesaria para
	 *                          llamar al servicio de registrar operaciones .
	 * @param esSoloCargo
	 * @param request
	 * @param resp
	 * @return
	 * @throws BussinesException
	 */
	private void flujosTodosLosTagsPortal(Map<String, Object> varErrores, boolean isSoloCargo, LiberarOperacionesPortalRequest request, 
			GENERICFINANCIALSERVICEOUT resp) {

		String estadoOperProg = "";
		String numOperProg = "";
		estadoOperProg = (isSoloCargo ? Constantes.ESTATUS_LIBERADA_160 : Constantes.COD_ESTADO_PROCESADO);
		
		
		try {
			numOperProg = (String)varErrores.get(Constantes.NUM_OPER_PROG); 
			if (this.actualizaDatosOperProg(numOperProg, resp.getIDOPERACIONFCC(), Integer.parseInt(estadoOperProg))) {
				String respAct = this.actualizaDetalle(isSoloCargo, request, resp, numOperProg);
				
				//variables a retornar para operación de liberación exitosa
				varErrores.put(Constantes.COD_ESTADO_LIBERACION, estadoOperProg);
				varErrores.put(Constantes.ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_OK));
				varErrores.put(Constantes.GLOSA_ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_OK));
				if (!Constantes.GLS_OK.equals(respAct)) {
					varErrores.put(Constantes.FLAG_REVERSA_GFS, true);
					varErrores.put(Constantes.FLAG_ERROR_UPD_OPER_PROG, Constantes.GLS_OPC_SI);
					varErrores.put(Constantes.FLAG_ERROR_UPD_DET_CAMP, Constantes.GLS_OPC_SI);
				}
				else {
					varErrores.put(Constantes.FLAG_ERROR_UPD_OPER_PROG, Constantes.GLS_OPC_NO);
				}
				
			}else {//falla actualización tbl_oper_prog
				LOGGER.error("Error al actualizar tbl_oper_prog, NUM_OPER_PROG: [{}]", numOperProg);
				varErrores.put(Constantes.FLAG_REVERSA_GFS, true);
				varErrores.put(Constantes.FLAG_ERROR_UPD_OPER_PROG, Constantes.GLS_OPC_SI);
				varErrores.put(Constantes.FLAG_ERROR_UPD_DET_CAMP, Constantes.GLS_OPC_NO);
			}
			
			
		}catch(RuntimeException e) {
			LOGGER.error("Error al actualizar tbl_oper_prog, NUM_OPER_PROG: [{}], glosa error: [{}]", numOperProg, e.getMessage());
			varErrores.put(Constantes.FLAG_REVERSA_GFS, true);
		}
	}
	
	private boolean actualizaDatosOperProg(String numOperProg, String numOperacionTrf, int codEstado) {
		String resp1 = operacionesEmpresaPortalService.actualizaEstadoOperProg(numOperProg, codEstado);
		String resp2 = operacionesEmpresaPortalService.actualizaNumOperacionTrfOperProg(numOperProg, numOperacionTrf);
		
		return (Constantes.GLS_OK.equals(resp1) && Constantes.GLS_OK.equals(resp2));
	}
	
	private String actualizaDetalle(boolean isSoloCargo, LiberarOperacionesPortalRequest request, GENERICFINANCIALSERVICEOUT resp, String numOperProg) {
		int codEstado = -1;
		String msgStatus = "";
		String respuesta = "";
		
		try {
			String detalleInSoapAct = Constantes.COD_CAMPO_RUT_LIBERADOR + "=" + request.getRutApoderado() + ";";
			detalleInSoapAct = detalleInSoapAct + Constantes.COD_CAMPO_NOM_LIBERADOR + "=" +  operacionesEmpresaPortalService.getNombreApoderado(request.getRutApoderado()) + ";";
			String fechaSistema = empresasService.fechaHoy(Constantes.FORMAT_DD_MM_YYYY_HHMMSS);
			detalleInSoapAct = detalleInSoapAct + Constantes.COD_CAMPO_FECHA_HORA_LIBERACION + "=" + fechaSistema + ";";
			detalleInSoapAct = detalleInSoapAct + Constantes.COD_CAMPO_ESTADO_PROCESO + "=" + Constantes.VAL_CAMPO_ESTADO_PROCESADA + ";";
			
			detalleInSoapAct = detalleInSoapAct + Constantes.COD_CAMPO_CARGO_EN_LINEA + "=" + Constantes.CONSTANTE_UNO + ";";
			
			if(!isSoloCargo) {//si es cargo y abono
				detalleInSoapAct = detalleInSoapAct + Constantes.COD_CAMPO_ABONO_EN_LINEA + "=" + Constantes.CONSTANTE_UNO + ";";
				detalleInSoapAct = detalleInSoapAct + Constantes.COD_CAMPO_GLOSA_ABONO_EN_LINEA + "=" + "Cod "+ resp.getESTADO() + " Desc " + resp.getDESCRIPCION() + ";";
			}else {
				detalleInSoapAct = detalleInSoapAct + Constantes.COD_CAMPO_ABONO_EN_LINEA + "=" + Constantes.CONSTANTE_CERO + ";";
			}
			
			ActualizaDetCampOut respDetalleAct = clienteGenericOperProg.actualizarDetalle(numOperProg, detalleInSoapAct);
			
			if(respDetalleAct != null && Constantes.NUMERIC_CERO.equals(respDetalleAct.getCodEstado())){
				respuesta = Constantes.GLS_OK;
				LOGGER.info("Actualización tbl_detalle_camp exitosa, numero de operación: [{}]", numOperProg);
			}else {
				respuesta = Constantes.GLS_ERROR_GENERICO;
				codEstado = respDetalleAct == null ? -1 : respDetalleAct.getCodEstado();
				msgStatus = respDetalleAct == null ? "" : respDetalleAct.getMsgStatus();
				LOGGER.info("Error al actualizar tbl_detalle_camp, numero de operación: [{}], cod estado: [{}], glosa estado: [{}]", numOperProg,  codEstado, msgStatus);
			}
			
		}catch(Exception e) {
			respuesta = Constantes.GLS_ERROR_GENERICO;
			LOGGER.error("Error al actualizar tbl_detalle_camp, NUM_OPER_PROG: [{}], glosa error: [{}]", numOperProg, e.getMessage());
		}
		
		return respuesta;
		
	}
	
	
	public Map<String, Object> setearDatosGfs( Map<String, Object> mapOperacionSpLib, boolean esSoloCargo, String modoTrx) {
		
		Map<String, Object> paramsGfsLib = new HashMap<>();
		String modoInvocacionGfs = "";
		String paramIdServicioGfs = "";
		String referenciaLib ="";
		String paramReferenciaLib = "";
		
		if(esSoloCargo) {
			modoInvocacionGfs = Constantes.MODO_INVOCACION_SIMPLE;
			paramIdServicioGfs = "LBTRCARGOONLINEBO";
			paramReferenciaLib = "VALOR15"; //banco beneficiario
		}
		else {
			modoInvocacionGfs = Constantes.MODO_INVOCACION_MULTIPLE;
			paramIdServicioGfs = "LBTRCARGOONLINEBB";
			paramReferenciaLib = "VALOR14"; //nombre beneficiario
		}
		
		if((String)mapOperacionSpLib.get(Constantes.VALOR2) != null && !((String)mapOperacionSpLib.get(Constantes.VALOR2)).equals(""))
			referenciaLib = referenciaLib + (String)mapOperacionSpLib.get(paramReferenciaLib) + " Ref: " + (String)mapOperacionSpLib.get(Constantes.VALOR2);
		else
			referenciaLib = referenciaLib + (String)mapOperacionSpLib.get(paramReferenciaLib) + " Ref: " + mapOperacionSpLib.get(Constantes.NUM_OPER_PROG).toString();
		
		paramsGfsLib.put(Constantes.RUTCLIENTE_SOAP, mapOperacionSpLib.get("VALOR7").toString());
		paramsGfsLib.put(Constantes.NUM_OPER_PROG, mapOperacionSpLib.get(Constantes.NUM_OPER_PROG).toString());
		
		paramsGfsLib.put(Constantes.CANAL_SOAP, Constantes.CANAL_INTERNET);
		paramsGfsLib.put(Constantes.SUCURSAL_SOAP, Constantes.SUCURSAL);
		paramsGfsLib.put(Constantes.MODOTRX_SOAP, modoTrx);
		paramsGfsLib.put(Constantes.MODOINVOCACION_SOAP, modoInvocacionGfs);
		
		String fechaHoy =  empresasService.fechaHoy(Constantes.FORMAT_YYYYMMDDHHMMSS);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FORMAT_YYYYMMDDHHMMSS);
		DateTimeFormatter formatterContable = DateTimeFormatter.ofPattern(Constantes.FORMAT_YYMMDDHHMMSS);
		
		LocalDateTime fechaHoyLocalDate = LocalDateTime.parse(fechaHoy, formatter);
		
		Integer milisegundos = LocalTime.now().getNano();
		String traceNum = fechaHoy.concat(milisegundos.toString().substring(0, 3)) + mapOperacionSpLib.get(Constantes.NUM_OPER_PROG).toString();
		paramsGfsLib.put(Constantes.TRACENUMBER_SOAP, traceNum);
		
		String fechaContable = empresasService.fechaContable(Constantes.FORMAT_YYYYMMDD, "LBTRCARGOONLINE", fechaHoyLocalDate.format(formatterContable));
		paramsGfsLib.put(Constantes.FECHACONTABLE_SOAP, fechaContable);

		paramsGfsLib.put(Constantes.MONEDACARGO_SOAP, Constantes.MONEDACLP);
		paramsGfsLib.put(Constantes.BANCOCARGO_SOAP, Constantes.COD_BANCO_BICE_028);
		paramsGfsLib.put(Constantes.CUENTACARGO_SOAP, ((String)mapOperacionSpLib.get("VALOR9")).replaceFirst("^0+(?!$)", "")); // le quito los ceros a la izquierda
		paramsGfsLib.put(Constantes.TIPOCUENTACARGO_SOAP, Constantes.COD_TIPO_CTA);
		paramsGfsLib.put(Constantes.MONTOCARGO_SOAP, mapOperacionSpLib.get("VALOR1").toString());
		
		if(esSoloCargo) {
			paramsGfsLib.put(Constantes.RUTABONO_SOAP, "");
			paramsGfsLib.put(Constantes.NOMBREABONO_SOAP, "");
			paramsGfsLib.put(Constantes.MONEDAABONO_SOAP, "");
			paramsGfsLib.put(Constantes.CUENTABONO_SOAP, "");
			paramsGfsLib.put(Constantes.TIPOCUENTABONO_SOAP, "");
		}else {
			paramsGfsLib.put(Constantes.RUTABONO_SOAP, mapOperacionSpLib.get("VALOR13").toString());
			paramsGfsLib.put(Constantes.NOMBREABONO_SOAP, mapOperacionSpLib.get("VALOR14").toString());
			paramsGfsLib.put(Constantes.MONEDAABONO_SOAP, Constantes.MONEDACLP);
			paramsGfsLib.put(Constantes.CUENTABONO_SOAP, ((String)mapOperacionSpLib.get("VALOR17")).replaceFirst("^0+(?!$)", "")); // le quito los ceros a la izquierda
			paramsGfsLib.put(Constantes.TIPOCUENTABONO_SOAP, Constantes.COD_TIPO_CTA);
		}

		String idServicio = "";
		List<ParametrosVo> idServicioParams;
		try {
			idServicioParams = consultarParametrosService.consultaParametro("SERVICIOSLBTR", paramIdServicioGfs);
			if (idServicioParams != null && !idServicioParams.isEmpty()) {
				idServicio = idServicioParams.get(0).getValParametro() != null ? idServicioParams.get(0).getValParametro().trim()  : "";
			}
		} catch (BancaEmpresasException e) {
			LOGGER.error("Error al obtener idServicio desde ws consulta parametros tbl_validacion, error: [{}]", e.getMessage());
		}

		paramsGfsLib.put(Constantes.IDSERVICIO_SOAP, idServicio);
		
		paramsGfsLib.put(Constantes.REFERENCIA_SOAP, referenciaLib);
		paramsGfsLib.put(Constantes.DOCCARGO_SOAP, mapOperacionSpLib.get(Constantes.NUM_OPER_PROG).toString());
		paramsGfsLib.put(Constantes.DOCABONO_SOAP, mapOperacionSpLib.get(Constantes.NUM_OPER_PROG).toString());
		
		return paramsGfsLib;
	}
	
	
	/**
	 * Flujo NOK al llamar a GFS. Se procede a reversa la cual maneja actualizaciones en operaciones y detalle operaciones
	 * 
	 * 
	 * @param variablesYErrores Map<String, Object> posee la data necesaria para
	 *                          llamar al servicio de registrar operaciones y los
	 *                          errores.
	 * @return
	 * @throws BancaEmpresasException 
	 * @throws BussinesException
	 */
	private Map<String, Object> respuestaNOKPortalGfs(Map<String, Object> variablesYErrores) throws BancaEmpresasException {
		variablesYErrores.put(Constantes.COD_ESTADO_LIBERACION, Constantes.COD_ESTADO_REVERSA);
		variablesYErrores.put(Constantes.ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
		variablesYErrores.put(Constantes.GLOSA_ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SISTEMA));
		reversaPortalGfs(variablesYErrores);
		
		return variablesYErrores;
	}
	
	
	/**
	 * LLama al servicio GFS (reversa) y sigue los posibles flujos Ok y NOK
	 * 
	 * @param variablesYErrores Map<String, Object> Data necesaria para llamar al
	 *                          servicio GFS
	 */
	public Map<String, Object> reversaPortalGfs(Map<String, Object> requestGfs) throws BancaEmpresasException {
		int codEstado = -1;
		String msgStatus = "";
		
		try {
			requestGfs.put(Constantes.MODOTRX_SOAP, Constantes.MODOTRX_REVERSA);
			
			if (clienteGFS.call(requestGfs)) {
				GENERICFINANCIALSERVICEOUT resp = (GENERICFINANCIALSERVICEOUT) requestGfs.get(Constantes.RESPONSE);
				if(!resp.getESTADO().equals(Constantes.CONSTANTE_MIL)) {
					requestGfs.put(Constantes.RESPONSE_STATUS, resp.getESTADO());
					requestGfs.put(Constantes.RESPONSE_GLOSA, resp.getDESCRIPCION());
				}			
			} 
		} catch (Exception e) {
			LOGGER.error("Error llamando reversa GFS, error: [{}]. NUM_OPER_PROG: [{}]", e.getMessage(), requestGfs.get(Constantes.NUM_OPER_PROG));
		}

		String detalleInSoap = "";
		detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_ESTADO_PROCESO + "=" + Constantes.VAL_CAMPO_ESTADO_NO_PROCESADA + ";";
		
		ActualizaDetCampOut respDetalle = clienteGenericOperProg.actualizarDetalle((String)requestGfs.get(Constantes.NUM_OPER_PROG), detalleInSoap);
		if(respDetalle != null && Constantes.NUMERIC_CERO.equals(respDetalle.getCodEstado())) {
				LOGGER.info("Actualización tbl_detalle_camp exitosa. NUM_OPER_PROG: [{}]. Cod estado: [{}], glosa estado [{}]", (String)requestGfs.get(Constantes.NUM_OPER_PROG), respDetalle.getCodEstado(), respDetalle.getMsgStatus());
		}
		else {
			codEstado = respDetalle == null ? -1 : respDetalle.getCodEstado();
			msgStatus = respDetalle == null ? "" : respDetalle.getMsgStatus();
			LOGGER.info("Error al actualizar tbl_detalle_camp, numero de operación: [{}], cod estado: [{}], glosa estado: [{}]", 
					(String)requestGfs.get(Constantes.NUM_OPER_PROG),  codEstado, msgStatus);			
			
		}
		return requestGfs;
	}	
	
	
	/**
	 * Dado un código de respuesta GFS verifica si se debe ejecutar o no reversa
	 * 
	 * @param rutEmpresa
	 * @return boolean false si es N o no está en la lista de reversa y true si es S
	 * @throws BancaEmpresasException
	 */
	public boolean isCodigoReversaGfs(String codErrorGfs) throws ErrorStoredProcedureException {
		LOGGER.info("TefPortalService isCodigoReversaGfs: codError[{}]", codErrorGfs);
		boolean resultado = false;

		String salidaSPGfs = "";
		Map<String, Object> params = new HashMap<>();
		params.put("v_COD_ERROR", codErrorGfs);
	
		try {
			portalOrawRepository.obtenerCodigosErrorGfs(params);
			salidaSPGfs = (String)params.get("v_SALIDA");
			if (salidaSPGfs != null && !salidaSPGfs.isEmpty() && salidaSPGfs.contains("S") ) {
				resultado = true;
			}
			
		} catch (Exception e) {
			throw new ErrorStoredProcedureException(e);
		}
	
		return resultado;
	}	
	
}
