package cl.bice.banca.empresas.servicio.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.apache.axis2.AxisFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.model.request.operaciones.LiberarOperacionesRequest;
import cl.bice.banca.empresas.servicio.soap.ClienteOperacionNomina;
import cl.bice.banca.empresas.servicio.util.EmailUtil;
import cl.bice.nominas.ws.ActualizaOpIn;
import cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub;
import cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessRequest;
import cl.bice.wsc.tefws.ESB_TefBICE_ServiceStub.TefBICEProcessResponse;

@Service
public class LbtrMXservice {

	private static final Logger LOGGER = LoggerFactory.getLogger(LbtrMXservice.class);

	@Autowired
	EmpresasService empresasService;

	@Autowired
	OperacionesEmpresaService operacionesEmpresaService;
	
	@Autowired
	@Qualifier("ClienteOperacionNomina")
	ClienteOperacionNomina clienteOperacionNomina;

	@Autowired
	CorrelativoService correlativoService;

	@Autowired
	Properties propiedadesExterna;

	@Autowired
	EmailUtil emailUtil;

	private final static String COD_TRX_OK = "0";
	private final static String COD_TRX_2033 = "2033";
	private final static int COD_RETORNO_APROBACION_OK = 0;
	private static final String GLS_TRANSFERENCIA_REGISTRO_CORRELATIVO = "Transferencias a Terceros Disponibles LBTR";

	public static final DecimalFormat FORMA_MONTO_MX = new DecimalFormat("###,###,###,###,##0.00");
	public static final DecimalFormat FORMA_MONTO_MN = new DecimalFormat("###,###,###,###,##0");

	/**
	 * Obtiene y registra el id correlativo.
	 * 
	 * @param request
	 * @return id correlativo
	 */
	public String iniciarControlCorrelativo(LiberarOperacionesRequest request) {		
		try {
			String idCorrelativo = correlativoService.idCorrelativo();

			if (!idCorrelativo.equals("")) {
				correlativoService.registrarCorrelativo(idCorrelativo,
						request.getCodigoServicio(), request.getRutEmpresa(), request.getRut(),
						GLS_TRANSFERENCIA_REGISTRO_CORRELATIVO);
			}

			return idCorrelativo;
		} catch (Exception e) {
			LOGGER.info("TefMXservice iniciarControlCorrelativo excepcion: {}",e);
			return "";
		}		
	}
	
	/**
	 * Verifica que el id correlativo se ha registrado correctamente y asocia ese id
	 * correlativo a la lista de operaciones que se van a liberar.
	 * 
	 * @param idCorrelativo
	 * @param request
	 * @return true si el id correlativo está registrado correctamente y si se lo
	 *         logra asociar a la lista de operaciones por liberar, false en caso
	 *         contrario.
	 */
	public boolean controlCorrelativoCorrecto(String idCorrelativo, LiberarOperacionesRequest request) {
		try {
			if ("".equals(idCorrelativo))
				return false;

			if (correlativoService.isControlCorrelativoCorrecto(idCorrelativo, request.getCodigoServicio())) {
				correlativoService.actualizarCorrelativo(idCorrelativo, request.getCodigoServicio(),
						request.getListaOperaciones());
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			LOGGER.info("TefMXservice controlCorrelativoCorrecto excepcion: {}",e);
			return false;
		}
	}
	
	/**
	 * Libera una operación TEF MX
	 * 
	 * @param numOperProg
	 * @param request
	 * @return
	 */
	public int liberarTefMx(String numOperProg, LiberarOperacionesRequest request, MapOperaciones mapOperaciones) {
		LOGGER.info("TefMXservice liberarTefMx");
		int result = -1;
		Map<String, String> mapOperacionResp = new HashMap<>();// inicializamos mapa de operacion a devolver
		mapOperaciones.getMapOperaciones().put(numOperProg, mapOperacionResp);
		mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.COD_ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.COD_ESTADO_LIBERACION_NOK));
		mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
		mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.GLOSA_ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SISTEMA));
		try {
			validarCampo2(numOperProg);

			try {
				if (operacionesEmpresaService.isProcesandoOperacionTefMx(numOperProg)) {
					return -1;
				}
			} catch (BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				return -1;
			}

			boolean resActualizaDetalleOp = false;
			String estadoCampo2 = getEstadoOperCampo2(numOperProg);

			if (estadoCampo2.equals("PROCESADA") || estadoCampo2.equals("ERROR")) {
				return -1;
			}
			if (estadoCampo2.substring(0, 1).equals("N")) {
				resActualizaDetalleOp = operacionesEmpresaService.actualizarDetalleOperacion("2", "EXEC_GFS",
						numOperProg, false);
			}

			String fechaHoy = empresasService.fechaHoy(Constantes.FORMAT_YYYYMMDDHHMMSS);

			if (!resActualizaDetalleOp)
				return -1;
			else {
				result = enviaTefMXMismaMonedaComex(numOperProg, request.getRut(), request.getRutEmpresa(), fechaHoy, mapOperaciones);
			}

			if (0 == result) {
				operacionesEmpresaService.actualizarDetalleOperacion("2", "PROCESADA", numOperProg, false);
				registrarDatosLiberacionOperacionLiberada(request.getRut(), request.getRutEmpresa(), numOperProg,
						request.getNombreApoderado(), fechaHoy);
			}
		} catch (Exception e) {
			LOGGER.error("TefMXservice liberarTefMx error: {}", e);			
			result = -1;
		}

		return result;
	}
	
	/**
	 * Carga en un Hastable datos de una determinada TEF MX.
	 * 
	 * @param numOperProg
	 * @param rutCliente
	 * @return datos de tef mx
	 */
	private Hashtable<String, String> cargarDatosTefMx(String numOperProg, String rutCliente) {
		Hashtable<String, String> tefMx = new Hashtable<String, String>();
		tefMx.put("CUENTAABONO", operacionesEmpresaService.obtenerValorCampoOperacion(numOperProg, "5"));
		tefMx.put("NUMOPERPROG", numOperProg);
		tefMx.put("BENEFICIARIO", operacionesEmpresaService.obtenerValorCampoOperacion(numOperProg, "527"));
		tefMx.put("RUTBENEFICIARIO", operacionesEmpresaService.obtenerValorCampoOperacion(numOperProg, "19"));
		tefMx.put("CUENTACARGO", operacionesEmpresaService.obtenerValorCampoOperacion(numOperProg, "4"));
		tefMx.put("MONTOCARGO", operacionesEmpresaService.obtenerValorCampoOperacion(numOperProg, "7"));
		tefMx.put("RUTCLIENTE", rutCliente);
		tefMx.put("MONTOABONO", operacionesEmpresaService.obtenerValorCampoOperacion(numOperProg, "821"));
		tefMx.put("REFERENCIA", operacionesEmpresaService.obtenerValorCampoOperacion(numOperProg, "637"));
		tefMx.put("TIPOTEF", operacionesEmpresaService.obtenerValorCampoOperacion(numOperProg, "230"));
		tefMx.put("MONEDACARGO", operacionesEmpresaService.obtenerValorCampoOperacion(numOperProg, "6"));
		tefMx.put("MONEDAABONO", operacionesEmpresaService.obtenerValorCampoOperacion(numOperProg, "823"));

		return tefMx;
	}
	
	/**
	 * Realiza TEF MX BICE-BICE misma moneda comex
	 * 
	 * @param numOperProg
	 * @param rut
	 * @param rutCliente
	 * @param fechaHoy
	 * @return 0 si el flujo finaliza correctamente, -1 en caso contrario
	 * @throws Exception
	 */
	public int enviaTefMXMismaMonedaComex(String numOperProg, String rut, String rutCliente, String fechaHoy, MapOperaciones mapOperaciones)
			throws Exception {
		LOGGER.info("TefMXservice enviaTefMXMismaMonedaComex");
		int result = -1;
		Hashtable<String, String> tefMx = cargarDatosTefMx(numOperProg, rutCliente);
		
		Map<String, String> mapOperacionResp = new HashMap<>();// inicializamos mapa de operacion a devolver
		mapOperaciones.getMapOperaciones().put(numOperProg, mapOperacionResp);
		mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.COD_ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.COD_ESTADO_LIBERACION_NOK));
		mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
		mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.GLOSA_ESTADO_LIBERACION,
				propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_NOK_SISTEMA));

		if (tefMx.get("TIPOTEF").equals("BICE")) {
			LOGGER.info("TefMXservice enviaTefMXMismaMonedaComex TIPOTEF = BICE");
			final String COD_ESTADO_REVERSA = "50";
			final String COD_ESTADO_PROCESADO = "70";
			final String COD_ESTADO_RECHAZADA = "80";
			final String COD_BANCO_BICE = "028";
			final String COD_TIPO_CTA = "0100";
			final String CANAL = "INTERNET";
			final String IDSERVICIO = "TEFBB";
			final String MODOTRX_DIRECTA = "D";
			final String MODOTRX_REVERSA = "R";
			final String MODOINVOCACION_MULTIPLE = "M"; // si es cargo y abono
			final String MONEDAUSD = "USD";
			final String MONEDAEUR = "EUR";
			final String SUCURSAL = "001";

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.FORMAT_YYYYMMDDHHMMSS);
			DateTimeFormatter formatterContable = DateTimeFormatter.ofPattern(Constantes.FORMAT_YYMMDDHHMMSS);
			LocalDateTime fechaHoyLocalDate = LocalDateTime.parse(fechaHoy, formatter);

			tefMx.put("CANAL", CANAL);
			tefMx.put("SUCURSAL", SUCURSAL);
			tefMx.put("IDSERVICIO", IDSERVICIO);
			tefMx.put("MODOTRX", MODOTRX_DIRECTA);
			tefMx.put("MODOINVOCACION", MODOINVOCACION_MULTIPLE);
			
			Date fechaSYS  = Calendar.getInstance().getTime();
			SimpleDateFormat dfDateTraceNumber = new SimpleDateFormat("yyyyMMddHHmm");
			tefMx.put("TRACENUMBER", (dfDateTraceNumber.format(fechaSYS) + String.valueOf(tefMx.get("NUMOPERPROG"))));
			
			String fechaCurse = empresasService.fechaContable(Constantes.FORMAT_YYYYMMDD, "TEFUSD_BB",
					fechaHoyLocalDate.format(formatterContable));

			tefMx.put("FECHACONTABLE", fechaCurse);
			tefMx.put("BANCOCARGO", COD_BANCO_BICE);

			// La moneda debe ser dólar, para el servicio ESB se hace la conversion de
			// cod_moneda 13 a USD
			if (tefMx.get("MONEDACARGO").equals("013"))
				tefMx.put("GLSMONEDACARGO", MONEDAUSD);
			if (tefMx.get("MONEDACARGO").equals("142"))
				tefMx.put("GLSMONEDACARGO", MONEDAEUR);
			if (tefMx.get("MONEDAABONO").equals("013"))
				tefMx.put("GLSMONEDAABONO", MONEDAUSD);
			if (tefMx.get("MONEDAABONO").equals("142"))
				tefMx.put("GLSMONEDAABONO", MONEDAEUR);

			// La cuenta debe ser Mx, pero para el servicio se reasigna a cta cte
			tefMx.put("TIPOCTAWS", COD_TIPO_CTA);

			DecimalFormat miSaldo = new DecimalFormat();
			miSaldo.applyPattern("###,###,###,###,##0.00");

			tefMx.put("REFWS",
					(String) tefMx.get("REFERENCIA") + " "
							+ miSaldo.format(Double.parseDouble((String) tefMx.get("MONTOABONO")) / (double) 100)
							+ " Ref : " + (String) tefMx.get("NUMOPERPROG") + " ");

			Hashtable<String, String> respServicio = enviaTefMXComex(tefMx);
			LOGGER.info("TefMXservice enviaTefMXMismaMonedaComex: numOper[{}] estadoBpel[{}] desc[{}]",
					tefMx.get("NUMOPERPROG"), respServicio.get("estado"), respServicio.get("descripcion"));

			// segun la respuesta del ESB actualiza estado de la operacion
			String fecCurseActOp = fechaCurse.substring(6, 8) + "-" + fechaCurse.substring(4, 6) + "-"
					+ fechaCurse.substring(0, 4);
			if (Integer.parseInt(respServicio.get("estado").toString()) == COD_RETORNO_APROBACION_OK) {
				boolean resActualizacion = actualizarOperacionTefMX((String) tefMx.get("NUMOPERPROG"),
						respServicio.get("idoperacionfcc").toString(), respServicio.get("nroreferencia").toString(),
						COD_ESTADO_PROCESADO, "Cod " + respServicio.get("estado").toString() + " Desc "
								+ respServicio.get("descripcion").toString(),
						fecCurseActOp);

				if (resActualizacion) {
					operacionesEmpresaService.actualizarDetalleOperacion("514", fecCurseActOp,
							(String) tefMx.get("NUMOPERPROG"), false);
					
					mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.COD_ESTADO_LIBERACION, COD_ESTADO_PROCESADO);
					mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_OK));
					mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.GLOSA_ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.GLOSA_ESTADO_LIBERACION_OK));

					result = 0;
				} else {
					// Reversa
					tefMx.put("MODOTRX", MODOTRX_REVERSA);
					Hashtable<String, String> respServicior = enviaTefMXComex(tefMx);

					LOGGER.info("TefMXservice enviaTefMXMismaMonedaComex: numOper[{}] estadoBpel[{}] desc[{}]",
							tefMx.get("NUMOPERPROG"), respServicior.get("estado"), respServicior.get("descripcion"));

					actualizarOperacionTefMX((String) tefMx.get("NUMOPERPROG"), "", "", COD_ESTADO_REVERSA,
							"Cod " + respServicio.get("estado").toString() + " Desc "
									+ respServicio.get("descripcion").toString(),
							fecCurseActOp);
					
					mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.COD_ESTADO_LIBERACION, COD_ESTADO_REVERSA);
					mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
					mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.GLOSA_ESTADO_LIBERACION, respServicio.get("descripcion").toString());
				}
			} else if (respServicio.get("estado").equals("-1")) {
				// Reversa
				tefMx.put("MODOTRX", MODOTRX_REVERSA);
				Hashtable<String, String> respServicior = enviaTefMXComex(tefMx);

				LOGGER.info("TefMXservice enviaTefMXMismaMonedaComex: numOper[{}] estadoBpel[{}] desc[{}]",
						tefMx.get("NUMOPERPROG"), respServicior.get("estado"), respServicior.get("descripcion"));

				actualizarOperacionTefMX((String) tefMx.get("NUMOPERPROG"), "", "", COD_ESTADO_REVERSA, "Cod "
						+ respServicio.get("estado").toString() + " Desc " + respServicio.get("descripcion").toString(),
						fecCurseActOp);
				
				mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.COD_ESTADO_LIBERACION, COD_ESTADO_REVERSA);
				mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
				mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.GLOSA_ESTADO_LIBERACION, respServicio.get("descripcion").toString());
			} else {
				actualizarOperacionTefMX((String) tefMx.get("NUMOPERPROG"), "", "", COD_ESTADO_RECHAZADA, "Cod "
						+ respServicio.get("estado").toString() + " Desc " + respServicio.get("descripcion").toString(),
						fecCurseActOp);
				
				mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.COD_ESTADO_LIBERACION, COD_ESTADO_REVERSA);
				mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.ESTADO_LIBERACION, propiedadesExterna.getProperty(Constantes.ESTADO_LIBERACION_NOK));
				mapOperaciones.getMapOperaciones().get(numOperProg).put(Constantes.GLOSA_ESTADO_LIBERACION, respServicio.get("descripcion").toString());
			}
		}

		return result;
	}

	/**
	 * Realiza TEF MX BICE-BICE mediante ESB-WS
	 * @param reg
	 * @return
	 * @throws Exception
	 */
	public Hashtable<String, String> enviaTefMXComex(Hashtable<String, String> reg) throws Exception{
		LOGGER.info("TefMXservice enviaTefMXComex:INI");
		 Hashtable<String, String> regs 		= null;
		 
		 if(reg == null)
		 {
			 LOGGER.error("TefMXservice enviaTefMXComex reg con parametros de entrada es NULO"); 
			 throw new Exception("TefMXservice enviaTefMXComex reg con parametros de entrada es NULO");
		 }
		 try 
		 {
		 ESB_TefBICE_ServiceStub stub = new ESB_TefBICE_ServiceStub(propiedadesExterna.getProperty("servicios.url.tefBICE"));
		 
		 ESB_TefBICE_ServiceStub.CONCEPTO_type0 concep = new ESB_TefBICE_ServiceStub.CONCEPTO_type0();
		 concep.setCANAL(((String)reg.get("CANAL") != null)? (String)reg.get("CANAL"):"");
		 concep.setIDSERVICIO(((String)reg.get("IDSERVICIO")!=null)?(String)reg.get("IDSERVICIO"):"");
		 concep.setSUCURSAL(((String)reg.get("SUCURSAL")!=null)?(String)reg.get("SUCURSAL"):"");		 
		 
		 ESB_TefBICE_ServiceStub.CONTEXTO_type0 contex = new ESB_TefBICE_ServiceStub.CONTEXTO_type0();
		 contex.setFECHACONTABLE(((String)reg.get("FECHACONTABLE")!=null)?(String)reg.get("FECHACONTABLE"):"");
		 contex.setMODOINVOCACION(((String)reg.get("MODOINVOCACION")!=null)?(String)reg.get("MODOINVOCACION"):"");
		 contex.setMODOTRX(((String)reg.get("MODOTRX")!=null)?(String)reg.get("MODOTRX"):"");		 
		 contex.setREFERENCIA(((String)reg.get("REFWS")!=null)?(String)reg.get("REFWS"):"");
		 contex.setTRACENUMBER(((String)reg.get("TRACENUMBER")!=null)?(String)reg.get("TRACENUMBER"):"");
				 
		 ESB_TefBICE_ServiceStub.DATOS_ABONO_type0 dabono = new ESB_TefBICE_ServiceStub.DATOS_ABONO_type0();
		 dabono.setCUENTABONO(((String)reg.get("CUENTAABONO")!=null)?(String)reg.get("CUENTAABONO"):"");
		 dabono.setDOCABONO(((String)reg.get("NUMOPERPROG")!=null)?(String)reg.get("NUMOPERPROG"):"");
		 dabono.setMONEDAABONO(((String)reg.get("GLSMONEDAABONO")!=null)?(String)reg.get("GLSMONEDAABONO"):"");
		 dabono.setNOMBREABONO(((String)reg.get("BENEFICIARIO")!=null)?(String)reg.get("BENEFICIARIO"):"");
		 dabono.setRUTABONO(((String)reg.get("RUTBENEFICIARIO")!=null)?(String)reg.get("RUTBENEFICIARIO"):"");
		 dabono.setTIPOCUENTABONO(((String)reg.get("TIPOCTAWS")!=null)?(String)reg.get("TIPOCTAWS"):"");
		
		 ESB_TefBICE_ServiceStub.DATOS_CARGO_type0 dcargo = new ESB_TefBICE_ServiceStub.DATOS_CARGO_type0();
		 dcargo.setBANCOCARGO(((String)reg.get("BANCOCARGO")!=null)?(String)reg.get("BANCOCARGO"):"");
		 dcargo.setCUENTACARGO(((String)reg.get("CUENTACARGO")!=null)?(String)reg.get("CUENTACARGO"):"");
		 dcargo.setDOCCARGO(((String)reg.get("NUMOPERPROG")!=null)?(String)reg.get("NUMOPERPROG"):"");
		 dcargo.setMONEDACARGO(((String)reg.get("GLSMONEDACARGO")!=null)?(String)reg.get("GLSMONEDACARGO"):"");
		 dcargo.setMONTOCARGO(((String)reg.get("MONTOCARGO")==null)?"":String.valueOf(Double.parseDouble(((String)reg.get("MONTOCARGO").toString()))/100));
		 dcargo.setRUTCLIENTE(((String)reg.get("RUTCLIENTE")!=null)?(String)reg.get("RUTCLIENTE"):"");
		 dcargo.setTIPOCUENTACARGO(((String)reg.get("TIPOCTAWS")!=null)?(String)reg.get("TIPOCTAWS"):"");
		 
		 LOGGER.info("TefMXservice enviaTefMXComex:getTRACENUMBER	={}",contex.getTRACENUMBER());
		 LOGGER.info("TefMXservice enviaTefMXComex:getFECHACONTABLE={}",contex.getFECHACONTABLE());
		 LOGGER.info("TefMXservice enviaTefMXComex:getCUENTACARGO	={}",dcargo.getCUENTACARGO());
		 LOGGER.info("TefMXservice enviaTefMXComex:getMONTOCARGO	={}",dcargo.getMONTOCARGO());
		 LOGGER.info("TefMXservice enviaTefMXComex:getDOCCARGO		={}",dcargo.getDOCCARGO());
		 LOGGER.info("TefMXservice enviaTefMXComex:getRUTCLIENTE	={}",dcargo.getRUTCLIENTE());
		 
		 TefBICEProcessRequest req = new TefBICEProcessRequest();
		 req.setCONCEPTO(concep);
		 req.setCONTEXTO(contex);
		 ESB_TefBICE_ServiceStub.DATOS_ABONO_type0[] array_dabono = {dabono};
		 req.setDATOS_ABONO(array_dabono);
		 
		 
		 ESB_TefBICE_ServiceStub.DATOS_CARGO_type0[] array_dcargo = {dcargo};
		 req.setDATOS_CARGO(array_dcargo);
		 
		 TefBICEProcessResponse resp = stub.process(req);
		 
		 LOGGER.info("TefMXservice enviaTefMXComex: resp.estado=[{}] resp.idoperacionfcc=[{}]", resp.getESTADO(), resp.getIDOPERACIONFCC());		 
		 regs = new Hashtable<String, String>();
		 regs.put("descripcion", (resp.getDESCRIPCION()!=null)? resp.getDESCRIPCION():"");
		 regs.put("estado", (resp.getESTADO()!=null)? resp.getESTADO():"");
		 
		 if(resp.getESTADO().equals(COD_TRX_OK) && !resp.getIDOPERACIONFCC().equals("") ){
			 regs.put("duracion", (resp.getDURACION()!=null)? resp.getDURACION():"");		 
			 regs.put("fecha", (resp.getFECHA()!=null)? resp.getFECHA():"");
			 regs.put("fecha_in", (resp.getFECHA_IN()!=null)? resp.getFECHA_IN():"");
			 regs.put("fecha_out", (resp.getFECHA_OUT()!=null)? resp.getFECHA_OUT():"");
			 regs.put("fechacontable_out", (resp.getFECHACONTABLE_OUT()!=null)? resp.getFECHACONTABLE_OUT():"");
			 regs.put("idoperacionfcc", (resp.getIDOPERACIONFCC()!=null)? resp.getIDOPERACIONFCC():"");
			 regs.put("nroreferencia", (resp.getNROREFERENCIA()!=null)? resp.getNROREFERENCIA():"");
		 }else{
			 regs.put("descripcion", resp.getDESCRIPCION());			 
			 regs.put("estado", "-1");
		 }
		 if ( resp.getESTADO().equals(COD_TRX_2033) )
		 {
			 regs.put("descripcion", resp.getDESCRIPCION());			 
			 regs.put("estado", "-1");
		 }
		 
		 }catch(AxisFault e1){
			 regs = new Hashtable<String, String>();

			 LOGGER.error("TefMXservice enviaTefMX :e1.toString()={}",e1.toString());
			 LOGGER.error("TefMXservice enviaTefMX :e1.getMessage={}",e1.getMessage());
			 LOGGER.error("TefMXservice enviaTefMX :e1.getReason()={}",e1.getReason());
			 LOGGER.error("TefMXservice enviaTefMX :e1.getCause()={}",e1.getCause());
			 //Read timed out
			 if ( e1.getMessage().equals("Read timed out"))
			 {
				 regs.put("descripcion", e1.getMessage());			 
				 regs.put("estado", "-1");
			 }
			 else
			 {
				 regs.put("descripcion", "Error "+e1);			 
				 regs.put("estado", "1");
			 }			
		 }
		 return regs;
	 }
	


	/**
	 * Actualiza el estado y detalles de la operación TEF MX
	 * 
	 * @param numOperProg
	 * @param numOperTRF
	 * @param nroRefFcc
	 * @param estado
	 * @param glsRech
	 * @param fecCurse
	 * @return true en caso de que la actualización haya sido exitosa, false en caso
	 *         contrario
	 */
	public boolean actualizarOperacionTefMX(String numOperProg, String numOperTRF, String nroRefFcc, String estado,
			String glsRech, String fecCurse) {
		LOGGER.info(
				"TefMXservice actualizarOperacionTefMX: numOperProg[{}] numOperTRF[{}] estado[{}] glsRech[{}] fecCurse[{}]",
				numOperProg, numOperTRF, estado, glsRech, fecCurse);
		boolean resultado = true;

		ActualizaOpIn actualizaOpIn = new ActualizaOpIn();
		actualizaOpIn.setINNUMOPERPROG(numOperProg);
		actualizaOpIn.setINESTADO(estado);
		actualizaOpIn.setINNUMOPERTRF(numOperTRF);

		if (!actualizarTblOperacionProg(actualizaOpIn)) {
			LOGGER.info("TefMXservice actualizarOperacionTefMX: resultado[false]");
			return false;
		}

		if (nroRefFcc != null) {
			if (!operacionesEmpresaService.actualizarDetalleOperacion("635", "NroRefFcc " + nroRefFcc, numOperProg,
					false)) {
				LOGGER.info("TefMXservice actualizarOperacionTefMX: resultado[false]");
				return false;
			}
		}
		if (numOperTRF != null) {
			if (!operacionesEmpresaService.actualizarDetalleOperacion("2", "N° " + numOperTRF, numOperProg, false)) {
				LOGGER.info("TefMXservice actualizarOperacionTefMX: resultado[false]");
				return false;
			}
			if (!operacionesEmpresaService.actualizarDetalleOperacion("27", numOperTRF, numOperProg, false)) {
				LOGGER.info("TefMXservice actualizarOperacionTefMX: resultado[false]");
				return false;
			}
		}
		if (glsRech != null) {
			if (!operacionesEmpresaService.actualizarDetalleOperacion("635", glsRech, numOperProg, false)) {
				LOGGER.info("TefMXservice actualizarOperacionTefMX: resultado[false]");
				return false;
			}
		}
		if (glsRech != null) {
			if (!operacionesEmpresaService.actualizarDetalleOperacion("535", fecCurse, numOperProg, false)) {
				LOGGER.info("TefMXservice actualizarOperacionTefMX: resultado[false]");
				return false;
			}
		}
		
		LOGGER.info("TefMXservice actualizarOperacionTefMX: resultado[{}]", resultado);
		return resultado;
	}

	/**
	 * Registra nombre y rut del apoderado responsable de la liberación y la fecha
	 * de liberación. En caso de que ocurra algún error durante la actualización se
	 * envía un mail de alerta con el detalle del error ocurrido.
	 * 
	 * @param rut
	 * @param rutCliente
	 * @param numOperProg
	 * @param nombreApoderado
	 * @param fecFirma
	 * @return true si no ha habido errores durante el flujo, false en caso
	 *         contrario.
	 */
	private boolean registrarDatosLiberacionOperacionLiberada(String rut, String rutCliente, String numOperProg,
			String nombreApoderado, String fecFirma) {

		LOGGER.info(
				"TefMXservice registrarDatosLiberacionOperacionLiberada: rut[{}] rutCliente[{}] numOperProg[{}] nombreApoderado[{}] fecFirma[{}]",
				rut, rutCliente, numOperProg, nombreApoderado, fecFirma);
		boolean existeError = false;
		String datosError = "";

		ActualizaOpIn actualizaOpIn = new ActualizaOpIn();
		actualizaOpIn.setINACCION("110");
		actualizaOpIn.setINNUMOPERPROG(numOperProg);

		if (!actualizarTblOperacionProg(actualizaOpIn)) {
			LOGGER.info(
					"TefMXservice registrarDatosLiberacionOperacionLiberada: No fue posible actualizar la tabla tbl_oper_prog ");
			existeError = true;
		}

		if (!existeError && !operacionesEmpresaService.actualizarDetalleOperacion("200", rut, numOperProg,
				false)) {/* Rut apoderado */
			LOGGER.info(
					"TefMXservice registrarDatosLiberacionOperacionLiberada: No fue posible actualizar la tabla update_tbl_detalle_camp ");
			existeError = true;
		}

		String fechaHoy = fecFirma.substring(6, 8) + "-" + fecFirma.substring(4, 6) + "-" + fecFirma.substring(0, 4)
				+ " " + fecFirma.substring(8, 10) + ":" + fecFirma.substring(10, 12) + ":" + fecFirma.substring(12, 14);

		if (!existeError
				&& !operacionesEmpresaService.actualizarDetalleOperacion("535", fechaHoy, numOperProg, false)) {
			LOGGER.info(
					"TefMXservice registrarDatosLiberacionOperacionLiberada: No fue posible actualizar la tabla update_tbl_detalle_camp ");
			existeError = true;
		}

		if (!existeError
				&& !operacionesEmpresaService.actualizarDetalleOperacion("631", nombreApoderado, numOperProg, false)) {
			LOGGER.info(
					"TefMXservice registrarDatosLiberacionOperacionLiberada: No fue posible actualizar la tabla update_tbl_detalle_camp ");
			existeError = true;
		}

		if (existeError) {
			String nombreEmpresa;
			try {
				nombreEmpresa = empresasService.obtenerNombreCliente(rutCliente);
			} catch (BancaEmpresasException e) {
				LOGGER.info(
						"TefMXservice registrarDatosLiberacionOperacionLiberada: ERROR al obtener nombreEmpresa: {}",
						e);
				nombreEmpresa = "";
			}
			LOGGER.info("TefMXservice registrarDatosLiberacionOperacionLiberada - existeError:{}", existeError);
			if (existeError) {
				// Si existe error en la actualizacion de los datos, enviar un correo al
				// administrador
				datosError = "Se ha producido un error en la actualización de datos al momento de LIBERAR la operación.\n\n";
				datosError += "Datos Operación TEF Disponible:\n";
				datosError += "RUT EMPRESA       : " + rutCliente + "\n";
				datosError += "NOMBRE EMPRESA    : " + nombreEmpresa + "\n";
				datosError += "RUT APODERADO     : " + rut + "\n";
				datosError += "NOMBRE APODERADO  : " + nombreApoderado + "\n";
				datosError += "NUM. OPERACIÓN    : " + numOperProg + "\n\n";

				LOGGER.info("TefMXservice registrarDatosLiberacionOperacionLiberada: datos para envio de mail:{}", datosError);

				emailUtil.enviaCorreo(propiedadesExterna.getProperty("monitor.servicios.alertaTEF.to"),
						"Error en Liberación de Operaciones TEF Disponible MX", datosError, propiedadesExterna,
						propiedadesExterna.getProperty("monitor.servicios.alertaTEF.from"));
			}
		}

		return true;

	}

	/**
	 * Actualiza datos de una operación en la tabla tbl_oper_prog
	 * 
	 * @param actualizaOpIn
	 * @return true si la actualización ha sido correcta, false en caso contrario
	 */
	private boolean actualizarTblOperacionProg(ActualizaOpIn actualizaOpIn) {
		LOGGER.info("TefMXservice actualizarTblOperacionProg");
		boolean resultadoOk;
		try {
			resultadoOk = (null != clienteOperacionNomina.tblOperacionProg(actualizaOpIn));
		} catch (BancaEmpresasException e) {
			LOGGER.info("TefMXservice actualizarTblOperacionProg ERROR: {}", e);
			resultadoOk = false;
		}
		return resultadoOk;
	}

	/**
	 * Obtiene el valor del campo 2 del detalle de la operación. Si el valor es ""
	 * devolverá el valor "Nº"
	 * 
	 * @param numOperProg
	 * @return valor campo 2
	 */
	public String getEstadoOperCampo2(String numOperProg) {
		LOGGER.info("TefMXservice getEstadoOperCampo2");
		String estadoCampo2;

		try {
			estadoCampo2 = operacionesEmpresaService.consultarDetalleOperacion(numOperProg);
			if (estadoCampo2.equals("")) {
				estadoCampo2 = "Nº";
			}
			LOGGER.info("TefMXservice getEstadoOperCampo2: numOperProg: " + numOperProg + "  estadoCampo2=["
					+ estadoCampo2 + "]");
		} catch (Exception e) {
			LOGGER.error("TefMXservice getEstadoOperCampo2: Exception.e=" + String.valueOf(e) + "  numOperProg: "
					+ numOperProg);
			estadoCampo2 = "ERROR";
		}

		return estadoCampo2;
	}

	/**
	 * Verifica si el campo 2 tiene algún valor distinto de nulo y si es así guarda
	 * el valor "Nº" en el mismo
	 * 
	 * @param numOperProg
	 */
	public void validarCampo2(String numOperProg) {
		LOGGER.info("LbtrMXservice validarCampo2");
		if (existeCampo2(numOperProg)) {
			operacionesEmpresaService.actualizarDetalleOperacion("2", "Nº", numOperProg, false);
		}
	}

	/**
	 * Verifica si el campo 2 del detalle de la operación no es nulo y el valor
	 * tiene largo > 0
	 * 
	 * @param numOperProg
	 * @return true si campo 2 tiene algún valor distinto de null, false en caso
	 *         contrario
	 */
	public boolean existeCampo2(String numOperProg) {
		LOGGER.info("LbtrMXservice existeCampo2");
		String resp;
		try {
			resp = operacionesEmpresaService.consultarDetalleOperacion(numOperProg);
		} catch (BancaEmpresasException e) {
			LOGGER.info("TefMXservice existeCampo2 ERROR: {}", e);
			return false;
		}
		LOGGER.info("TefMXservice existeCampo2 valor: {}", resp);
		return (resp != null && resp.trim().length() > 0);
	}
}
