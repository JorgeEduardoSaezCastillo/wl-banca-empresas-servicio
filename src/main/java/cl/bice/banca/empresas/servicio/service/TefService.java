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
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesRequest;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.soap.ClienteGFS;
import cl.bice.banca.empresas.servicio.soap.ClienteGenericOperProg;
import cl.bice.genericoperprog.ws.ActualizaDetCampOut;
import cl.bice.genericoperprog.ws.ActualizaOpOut;
import cl.bice.gfs.ws.GENERICFINANCIALSERVICEOUT;
import cl.bice.nominas.ws.ParametrosVo;
import cl.bice.banca.empresas.servicio.exception.BussinesException;


/**
 * Clase con métodos para manejar las operaciones de una empresa.
 * 
 * @author Tinet
 */
@Service
public class TefService {
	private static final Logger LOGGER = LoggerFactory.getLogger(TefService.class);
	/**
	 * Propiedades Externas.
	 */
	@Autowired
	Properties propiedadesExterna;
	
	@Autowired
	@Qualifier("ClienteGFS")
	ClienteGFS clienteGFS;
	
	@Autowired
	@Qualifier("ClienteGenericOperProg")
	ClienteGenericOperProg clienteGenericOperProg;
	
	@Autowired
	EmpresasService empresasService;
	
	@Autowired
	PortalOrawRepository portalOrawRepository;
	
	@Autowired
	OperacionesEmpresaService operacionesEmpresaService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	ConsultarParametrosService consultarParametrosService;
	
	/**
	 * LLama al servicio GFS y sigue los posibles flujos Ok y NOK
	 * 
	 * @param paramsGfs Map<String, Object> Data necesaria para llamar al
	 *                          servicio GFS
	 */
	public Map<String, Object> llamarGFS(LiberarOperacionesRequest request, Map<String, Object> mapOperacionSp, boolean esSoloCargo, String tipoPago) throws BancaEmpresasException {
		Map<String, Object> paramsGfs = new HashMap<>();
		
		try {
			paramsGfs = llenarDatosGfs(request.getRut(), mapOperacionSp, esSoloCargo, tipoPago, Constantes.MODOTRX_DIRECTA);
			if (clienteGFS.call(paramsGfs)) {
				respuestaOKGFS(paramsGfs, esSoloCargo, request);
			} else {
				respuestaNOKGfs(paramsGfs);
			}
		} catch (BussinesException e) {
			LOGGER.info("ERROR CREANDO REQUEST GFS, NUM_OPER_PROG: [{}]", (String)mapOperacionSp.get(Constantes.NUM_OPER_PROG) );
			paramsGfs.put(Constantes.COD_ESTADO_LIBERACION, Constantes.COD_ESTADO_REVERSA);
			paramsGfs.put(Constantes.ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
			paramsGfs.put(Constantes.GLOSA_ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SISTEMA));
		}

		return paramsGfs;
	}

	/**
	 * Llena los datos para tef Gfs
	 * 
	 * @param nroOperacion
	 * @return
	 * @throws BancaEmpresasException
	 */
	public Map<String, Object> llenarDatosGfs(String rut, Map<String, Object> mapOperacionSp, boolean esSoloCargo, String tipoPago, String modoTrx) throws BussinesException{
		
		Map<String, Object> paramsGfs = new HashMap<>();
		String modoInvocacion = "";
		String paramIdServicio = "";
		String referencia ="";
		String paramReferencia = "";
		
		if(esSoloCargo) {
			modoInvocacion = Constantes.MODO_INVOCACION_SIMPLE;
			paramIdServicio = "LBTRCARGOONLINEBO";
			paramReferencia = "VALOR15"; //banco beneficiario
		}
		else {
			modoInvocacion = Constantes.MODO_INVOCACION_MULTIPLE;
			paramIdServicio = "LBTRCARGOONLINEBB";
			paramReferencia = "VALOR14"; //nombre beneficiario
		}
		
		if((String)mapOperacionSp.get(Constantes.VALOR2) != null && !((String)mapOperacionSp.get(Constantes.VALOR2)).equals(""))
			referencia = referencia + (String)mapOperacionSp.get(paramReferencia) + " Ref: " + (String)mapOperacionSp.get(Constantes.VALOR2);
		else
			referencia = referencia + (String)mapOperacionSp.get(paramReferencia) + " Ref: " + mapOperacionSp.get(Constantes.NUM_OPER_PROG).toString();
		
		paramsGfs.put(Constantes.RUTCLIENTE_SOAP, (String)mapOperacionSp.get("VALOR7"));
		paramsGfs.put(Constantes.NUM_OPER_PROG, mapOperacionSp.get(Constantes.NUM_OPER_PROG).toString());
		
		paramsGfs.put(Constantes.CANAL_SOAP, Constantes.CANAL_INTERNET);
		paramsGfs.put(Constantes.SUCURSAL_SOAP, Constantes.SUCURSAL);
		paramsGfs.put(Constantes.MODOTRX_SOAP, modoTrx);
		paramsGfs.put(Constantes.MODOINVOCACION_SOAP, modoInvocacion);
		
		String fechaHoy =  empresasService.fechaHoy(Constantes.FORMAT_YYYYMMDDHHMMSS);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FORMAT_YYYYMMDDHHMMSS);
		DateTimeFormatter formatterContable = DateTimeFormatter.ofPattern(Constantes.FORMAT_YYMMDDHHMMSS);
		
		LocalDateTime fechaHoyLocalDate = LocalDateTime.parse(fechaHoy, formatter);
		
		Integer milisegundos = LocalTime.now().getNano();
		String traceNum = fechaHoy.concat(milisegundos.toString().substring(0, 3)) + mapOperacionSp.get(Constantes.NUM_OPER_PROG).toString();
		paramsGfs.put(Constantes.TRACENUMBER_SOAP, traceNum);
		
		String fechaContable = empresasService.fechaContable(Constantes.FORMAT_YYYYMMDD, "LBTRCARGOONLINE", fechaHoyLocalDate.format(formatterContable));
		paramsGfs.put(Constantes.FECHACONTABLE_SOAP, fechaContable);

		paramsGfs.put(Constantes.MONEDACARGO_SOAP, Constantes.MONEDACLP);
		paramsGfs.put(Constantes.BANCOCARGO_SOAP, Constantes.COD_BANCO_BICE_028);
		paramsGfs.put(Constantes.CUENTACARGO_SOAP, ((String)mapOperacionSp.get("VALOR9")).replaceFirst("^0+(?!$)", "")); // le quito los ceros a la izquierda
		paramsGfs.put(Constantes.TIPOCUENTACARGO_SOAP, Constantes.COD_TIPO_CTA);
		paramsGfs.put(Constantes.MONTOCARGO_SOAP, (String)mapOperacionSp.get("VALOR1"));
		
		if(esSoloCargo) {
			paramsGfs.put(Constantes.RUTABONO_SOAP, "");
			paramsGfs.put(Constantes.NOMBREABONO_SOAP, "");
			paramsGfs.put(Constantes.MONEDAABONO_SOAP, "");
			paramsGfs.put(Constantes.CUENTABONO_SOAP, "");
			paramsGfs.put(Constantes.TIPOCUENTABONO_SOAP, "");
		}else {
			paramsGfs.put(Constantes.RUTABONO_SOAP, (String)mapOperacionSp.get("VALOR13"));
			paramsGfs.put(Constantes.NOMBREABONO_SOAP, (String)mapOperacionSp.get("VALOR14"));
			paramsGfs.put(Constantes.MONEDAABONO_SOAP, Constantes.MONEDACLP);
			paramsGfs.put(Constantes.CUENTABONO_SOAP, ((String)mapOperacionSp.get("VALOR17")).replaceFirst("^0+(?!$)", "")); // le quito los ceros a la izquierda
			paramsGfs.put(Constantes.TIPOCUENTABONO_SOAP, Constantes.COD_TIPO_CTA);
		}

		String idServicio = "";
		List<ParametrosVo> idServicioParams;
		try {
			idServicioParams = consultarParametrosService.consultaParametro("SERVICIOSLBTR", paramIdServicio);
			if (idServicioParams != null && !idServicioParams.isEmpty()) {
				idServicio = idServicioParams.get(0).getValParametro() != null ? idServicioParams.get(0).getValParametro().trim()  : "";
			}
		} catch (BancaEmpresasException e) {
			LOGGER.warn("Error al obtener idServicio desde ws consulta parametros tbl_validacion, error: [{}]", e);
		}

		paramsGfs.put(Constantes.IDSERVICIO_SOAP, idServicio);
		
		paramsGfs.put(Constantes.REFERENCIA_SOAP, referencia);
		paramsGfs.put(Constantes.DOCCARGO_SOAP, mapOperacionSp.get(Constantes.NUM_OPER_PROG).toString());
		paramsGfs.put(Constantes.DOCABONO_SOAP, mapOperacionSp.get(Constantes.NUM_OPER_PROG).toString());
		
		return paramsGfs;
	}
	
	/**
	 * Flujo OK al llamar a GFS. Se procede a analizar respuesta de GFS, en caso de
	 * que sea la correcta se llama al servicio de registrar operaciones y al
	 * servicio de mensajeria. en caso de ser erronea se llama al servicio de
	 * registrar operaciones y si es necesario se efectua una reversa a GFS
	 * 
	 * @param variablesYErrores Map<String, Object> posee la data necesaria para
	 *                          llamar al servicio de registrar operaciones .
	 * @return
	 * @throws BussinesException
	 */
	private Map<String, Object> respuestaOKGFS(Map<String, Object> variablesYErrores, boolean esSoloCargo, LiberarOperacionesRequest request) throws BancaEmpresasException{
		GENERICFINANCIALSERVICEOUT resp = (GENERICFINANCIALSERVICEOUT) variablesYErrores.get(Constantes.RESPONSE);
		
		variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, false);
		
		variablesYErrores.put(Constantes.COD_ESTADO_LIBERACION, Constantes.COD_ESTADO_REVERSA);
		variablesYErrores.put(Constantes.ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
		variablesYErrores.put(Constantes.GLOSA_ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SISTEMA));
		
		flujosRespuestaOKGFS(variablesYErrores, esSoloCargo, request, resp);
		
		if((boolean)variablesYErrores.get(Constantes.FLAG_REVERSA_GFS)) 
			reversaGfs(variablesYErrores);
		else if(!resp.getESTADO().equals(Constantes.CONSTANTE_CERO)) {//si no hay reversa pero hubo error en gfs, se debe actualizar a N° el estado de la tbl_detalle_camp
			String detalleInSoap = "";
			detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_ESTADO_PROCESO + "=" + Constantes.VAL_CAMPO_ESTADO_NO_PROCESADA + ";";
			
			ActualizaDetCampOut respDetalle = clienteGenericOperProg.actualizarDetalle((String)variablesYErrores.get(Constantes.NUM_OPER_PROG), detalleInSoap);
			if(respDetalle != null && Constantes.NUMERIC_CERO.equals(respDetalle.getCodEstado()))
					LOGGER.info("Actualización tbl_detalle_camp exitosa. NUM_OPER_PROG: [{}]. Cod estado: [{}], glosa estado [{}]", (String)variablesYErrores.get(Constantes.NUM_OPER_PROG), respDetalle.getCodEstado(), respDetalle.getMsgStatus());
			else
				LOGGER.warn("Error en actualización tbl_detalle_camp. NUM_OPER_PROG: [{}. Cod estado: [{}], glosa estado [{}]", (String)variablesYErrores.get(Constantes.NUM_OPER_PROG), respDetalle.getCodEstado(), respDetalle.getMsgStatus());
		}
				
		return variablesYErrores;
	}
	
	
	/**
	 * Dado un código de respuesta GFS verifica si se debe ejecutar o no reversa
	 * 
	 * @param rutEmpresa
	 * @return boolean false si es N o no está en la lista de reversa y true si es S
	 * @throws BancaEmpresasException
	 */
	public boolean isCodigoReversa(String codError) throws ErrorStoredProcedureException {
		LOGGER.info("TefService isCodigoReversa: codError[{}]", codError);
		boolean resultado = false;

		String salidaSP = "";
		Map<String, Object> params = new HashMap<>();
		params.put("v_COD_ERROR", codError);
	
		try {
			portalOrawRepository.obtenerCodigosErrorGfs(params);
			salidaSP = (String)params.get("v_SALIDA");
			if (salidaSP != null && !salidaSP.isEmpty()) {
				if(salidaSP.contains("S")) 
					resultado = true;
			}
			
		} catch (Exception e) {
			throw new ErrorStoredProcedureException(e);
		}
	
		return resultado;
	}
	
	/**
	 * Flujo NOK al llamar a GFS. Se procede a reversa la cual maneja actualizaciones en operaciones y deetalle operaciones
	 * 
	 * 
	 * @param variablesYErrores Map<String, Object> posee la data necesaria para
	 *                          llamar al servicio de registrar operaciones y los
	 *                          errores.
	 * @return
	 * @throws BancaEmpresasException 
	 * @throws BussinesException
	 */
	private Map<String, Object> respuestaNOKGfs(Map<String, Object> variablesYErrores) throws BancaEmpresasException {
		variablesYErrores.put(Constantes.COD_ESTADO_LIBERACION, Constantes.COD_ESTADO_REVERSA);
		variablesYErrores.put(Constantes.ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
		variablesYErrores.put(Constantes.GLOSA_ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SISTEMA));
		reversaGfs(variablesYErrores);
		
		return variablesYErrores;
	}
	
	/**
	 * LLama al servicio GFS (reversa) y sigue los posibles flujos Ok y NOK
	 * 
	 * @param variablesYErrores Map<String, Object> Data necesaria para llamar al
	 *                          servicio GFS
	 */
	public Map<String, Object> reversaGfs(Map<String, Object> requestGfs) throws BancaEmpresasException {
//		Map<String, Object> paramsGfs = new HashMap<>();
		boolean enviarMailError = false;
		try {
			requestGfs.put(Constantes.MODOTRX_SOAP, Constantes.MODOTRX_REVERSA);
			
			if (clienteGFS.call(requestGfs)) {
				GENERICFINANCIALSERVICEOUT resp = (GENERICFINANCIALSERVICEOUT) requestGfs.get(Constantes.RESPONSE);
				if(!resp.getESTADO().equals(Constantes.CONSTANTE_MIL)) {
					enviarMailError = true;
					requestGfs.put(Constantes.RESPONSE_STATUS, resp.getESTADO());
					requestGfs.put(Constantes.RESPONSE_GLOSA, resp.getDESCRIPCION());
				}			
			} else {
				enviarMailError = true;
			}
		} catch (Exception e) {
//			paramsGfs.put(Constantes.FLAG_REVERSA_GFS, false);
			LOGGER.error("Error llamando reversa GFS, error: [{}]. NUM_OPER_PROG: [{}]", e, (String)requestGfs.get(Constantes.NUM_OPER_PROG));
			enviarMailError = true;
		}
		if(enviarMailError)
			enviarMailError((String)requestGfs.get(Constantes.NUM_OPER_PROG), (String)requestGfs.get(Constantes.RESPONSE_STATUS), (String)requestGfs.get(Constantes.RESPONSE_GLOSA), Constantes.CORREO_ERR_TIPO_REVERSA_GFS);

		String detalleInSoap = "";
		detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_ESTADO_PROCESO + "=" + Constantes.VAL_CAMPO_ESTADO_NO_PROCESADA + ";";
		
		ActualizaDetCampOut respDetalle = clienteGenericOperProg.actualizarDetalle((String)requestGfs.get(Constantes.NUM_OPER_PROG), detalleInSoap);
		if(respDetalle != null && Constantes.NUMERIC_CERO.equals(respDetalle.getCodEstado()))
				LOGGER.info("Actualización tbl_detalle_camp exitosa. NUM_OPER_PROG: [{}]. Cod estado: [{}], glosa estado [{}]", (String)requestGfs.get(Constantes.NUM_OPER_PROG), respDetalle.getCodEstado(), respDetalle.getMsgStatus());
		
		else
			LOGGER.warn("Error en actualización tbl_detalle_camp. NUM_OPER_PROG: [{}. Cod estado: [{}], glosa estado [{}]", (String)requestGfs.get(Constantes.NUM_OPER_PROG), respDetalle.getCodEstado(), respDetalle.getMsgStatus());
		
		return requestGfs;
	}
	
	/**
	 * Llama al SP que se encarga de enviar mail con los datos codigo y glosa de
	 * error.
	 * 
	 * @param nroOperacion
	 * @param codError
	 * @param glosaError
	 * @param tipo
	 */
	private void enviarMailError(String nroOperacion, String codError, String glosaError, String tipo) {
		try {
			mailService.enviarMailCodGlsError(nroOperacion, codError, glosaError, tipo);

		} catch (BancaEmpresasException e1) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e1);
		}
	}
	
	/**
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
	private void flujosRespuestaOKGFS(Map<String, Object> variablesYErrores, boolean esSoloCargo, LiberarOperacionesRequest request, GENERICFINANCIALSERVICEOUT resp) throws BancaEmpresasException{
		if (resp.getESTADO().equals(Constantes.CONSTANTE_CERO)){
			if(null != resp.getIDOPERACIONFCC() && null != resp.getFECHACONTABLEOUT()
				&& null != resp.getNROREFERENCIA() && null != resp.getFECHA()) {
				
				flujosTodosLosTags(variablesYErrores, esSoloCargo, request, resp);
				
			}else {
				LOGGER.warn("Error GFS (no trae todos los tags)");
				variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, true);
			}
			
		}else if(resp.getESTADO().equals("-1") || resp.getESTADO().equals("2033")) {
			LOGGER.error("Error GFS, cod error: [{}], glosa error: [{}]", resp.getESTADO() , resp.getDESCRIPCION());
			variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, true);
		}else if(resp.getESTADO() != null) {
				boolean esCodigoReversa = isCodigoReversa(resp.getESTADO().trim());
				if(esCodigoReversa) 
					variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, true);
				else
					variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, false);
				
				if(resp.getDESCRIPCION().toUpperCase().contains("SALDO"))
					variablesYErrores.put(Constantes.GLOSA_ESTADO_LIBERACION, resp.getDESCRIPCION());
				
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
	private void flujosTodosLosTags(Map<String, Object> variablesYErrores, boolean esSoloCargo, LiberarOperacionesRequest request, GENERICFINANCIALSERVICEOUT resp) throws BancaEmpresasException{
		Map<String, String> paramsOperProg = new HashMap<>();
		String estadoOperacion = "";
		
		if(esSoloCargo)
			estadoOperacion = Constantes.ESTATUS_LIBERADA_160;
		else
			estadoOperacion = Constantes.COD_ESTADO_PROCESADO;//70
		
		paramsOperProg.put(Constantes.NUM_OPER_PROG, (String)variablesYErrores.get(Constantes.NUM_OPER_PROG));
		paramsOperProg.put(Constantes.INESTADO_SOAP, estadoOperacion);
		paramsOperProg.put(Constantes.INNUMOPERTRF_SOAP, resp.getIDOPERACIONFCC());
		
		try {
			ActualizaOpOut respOperProg = clienteGenericOperProg.actualizarData(paramsOperProg);
			if(respOperProg != null && Constantes.NUMERIC_CERO.equals(respOperProg.getCodEstado()) && respOperProg.getMsgResult() != null) {
				try {
					String detalleInSoap = Constantes.COD_CAMPO_RUT_LIBERADOR + "=" + request.getRut() + ";";
					detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_NOM_LIBERADOR + "=" + request.getNombreApoderado() + ";";
					String fechaHoy = empresasService.fechaHoy(Constantes.FORMAT_DD_MM_YYYY_HHMMSS);
					detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_FECHA_HORA_LIBERACION + "=" + fechaHoy + ";";
					detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_ESTADO_PROCESO + "=" + Constantes.VAL_CAMPO_ESTADO_PROCESADA + ";";
					
					detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_CARGO_EN_LINEA + "=" + Constantes.CONSTANTE_UNO + ";";
					
					if(!esSoloCargo) {//si es cargo y abono
						detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_ABONO_EN_LINEA + "=" + Constantes.CONSTANTE_UNO + ";";
						detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_GLOSA_ABONO_EN_LINEA + "=" + "Cod "+ resp.getESTADO() + " Desc " + resp.getDESCRIPCION() + ";";
					}else
						detalleInSoap = detalleInSoap + Constantes.COD_CAMPO_ABONO_EN_LINEA + "=" + Constantes.CONSTANTE_CERO + ";";
					
					ActualizaDetCampOut respDetalle = clienteGenericOperProg.actualizarDetalle(paramsOperProg.get(Constantes.NUM_OPER_PROG), detalleInSoap);
					
					if(respDetalle != null && Constantes.NUMERIC_CERO.equals(respDetalle.getCodEstado())){
						LOGGER.info("Actualización tbl_detalle_camp exitosa, numero de operación: [{}]", paramsOperProg.get(Constantes.NUM_OPER_PROG));
					}else {
						LOGGER.warn("Error al actualizar tbl_detalle_camp, numero de operación: [{}], cod estado: [{}], glosa estado: [{}]", paramsOperProg.get(Constantes.NUM_OPER_PROG), respDetalle.getCodEstado(), respDetalle.getMsgStatus());
					}
					
				}catch(Exception e) {
					LOGGER.error("Error al actualizar tbl_detalle_camp, NUM_OPER_PROG: [{}], glosa error: [{}]", paramsOperProg.get(Constantes.NUM_OPER_PROG), e);
				}
				
				//variables a retornar para operación de liberación exitosa
				variablesYErrores.put(Constantes.COD_ESTADO_LIBERACION, estadoOperacion);
				variablesYErrores.put(Constantes.ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_OK));
				variablesYErrores.put(Constantes.GLOSA_ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_OK));
				
				
			}else {//falla actualización tbl_oper_prog
				LOGGER.error("Error al actualizar tbl_oper_prog, NUM_OPER_PROG: [{}], cod estado [{}], glosa estado: [{}]", paramsOperProg.get(Constantes.NUM_OPER_PROG), respOperProg.getCodEstado(),  respOperProg.getMsgResult());
				variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, true);
			}
			
			
		}catch(RuntimeException e) {
			LOGGER.error("Error al actualizar tbl_oper_prog, NUM_OPER_PROG: [{}], glosa error: [{}]", paramsOperProg.get(Constantes.NUM_OPER_PROG), e);
			variablesYErrores.put(Constantes.FLAG_REVERSA_GFS, true);
		}
	}
}
