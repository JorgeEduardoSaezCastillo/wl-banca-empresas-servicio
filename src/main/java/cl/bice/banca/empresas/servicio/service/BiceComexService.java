package cl.bice.banca.empresas.servicio.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.exception.RequestInvalidoException;
import cl.bice.banca.empresas.servicio.model.bicecomex.AprobacionBiceComex;
import cl.bice.banca.empresas.servicio.model.bicecomex.ConsultaGeneral;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.common.TipoMoneda;
import cl.bice.banca.empresas.servicio.model.operaciones.ResumenOperacionesMoneda;
import cl.bice.banca.empresas.servicio.model.request.desafios.ListarCrearDesafiosRequest;
import cl.bice.banca.empresas.servicio.model.request.operaciones.AprobarOperacionesRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.empresas.DetalleCampoValorTipoOperacion;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesResp;
import cl.bice.banca.empresas.servicio.model.response.operaciones.AprobarOperacionesResponse;
import cl.bice.banca.empresas.servicio.repository.BiceComexRepository;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;

/**
 * Clase con métodos para manejar las operaciones de una empresa.
 * 
 * @author Tinet
 */
@Service
public class BiceComexService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BiceComexService.class);
	/**
	 * Propiedades Externas.
	 */
	@Autowired
	Properties propiedadesExterna;

	@Autowired
	PortalOrawRepository portalOrawRepository;

	@Autowired
	ValidacionService validacionService;

	@Autowired
	EmpresasService empresasService;

	@Autowired
	OperacionesEmpresaService operacionesEmpresaService;

	@Autowired
	EstadoService estadoService;

	@Autowired
	BiceComexRepository biceComexRepository;

	private static final String NUM_OPER_PROG = "NUM_OPER_PROG";

	/**
	 * Chequea que la operación no esté siendo procesada. Si no está siendo
	 * procesada la deja en estado EN_PROCESO y continua intentando firmar dicha
	 * operación. Si se firma la operación se la vuelve a actualizar en estado con
	 * el estado correspondiente en que finalizó la aprobación y se registra en tabla de seguimiento
	 * de BiceComex.
	 * 
	 * @param nroOperacion
	 * @param request
	 * @return
	 */
	public int aprobarOperacion(ConsultaGeneral operacion, AprobarOperacionesRequest request, String inDatoFirma) throws SQLTimeoutException{
		int resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		
		//Se revisa si la operación está EN_PROCESO
		try {
			if (operacionesEmpresaService.isProcesandoOperacionSp(operacion.getIdentificacionOperacion(), request.getCodigoServicio(), null,
					request.getRutEmpresa())) {
				LOGGER.warn("La operación nro: [{}] ya está siendo procesada.", operacion.getIdentificacionOperacion());
				LOGGER.info("Se actualiza operación nro: {}, estado: {}", operacion.getIdentificacionOperacion(), Constantes.BICECOMEX_ESTADO_FIRMA_SIMULTANEA_APROBACION);
				boolean resultadoRegistro = registraSegBiceComex(operacion, request.getRutEmpresa(), request.getRut(), inDatoFirma, request.getIdSesion(), Constantes.BICECOMEX_ESTADO_FIRMA_SIMULTANEA_APROBACION);
				if(!resultadoRegistro) {
					LOGGER.error("No se pudo actualizar estado: {} en tabla de seguimiento para operación BiceComex nro: {}" , Constantes.BICECOMEX_ESTADO_FIRMA_SIMULTANEA_APROBACION, operacion.getIdentificacionOperacion());
				}
				return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
			}
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		}

		//Se bloquea la operación seteando estado EN_PROCESO
		try {
			boolean resultadoRegistro = registraSegBiceComex(operacion, request.getRutEmpresa(), request.getRut(), inDatoFirma, request.getIdSesion(), Constantes.BICECOMEX_ESTADO_EN_PROCESO_APROBACION);
			if(!resultadoRegistro) {
				LOGGER.error("No se pudo actualizar estado: {} en tabla de seguimiento para operación BiceComex nro: {}" , Constantes.BICECOMEX_ESTADO_EN_PROCESO_APROBACION, operacion.getIdentificacionOperacion());
				return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
			}

		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			return Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		}

		try {
            if(operacion.getTimeStampOPRecibidas().compareToIgnoreCase("--")==0)
            	operacion.setTimeStampOPRecibidas("");
			
			biceComexRepository.AprobarOperaciones(Integer.valueOf(operacion.getIdentificacionOperacion()), Integer.valueOf(operacion.getCorrelativo()), operacion.getFechaModificacion(), operacion.getTimeStampOPRecibidas(), operacion.getProducto(), request.getRutEmpresa(), request.getRut());

			resultado = resultadoAprobar(operacion, request, inDatoFirma, resultado);
			
		} catch (SQLTimeoutException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new SQLTimeoutException();
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
		}

		//Se revisa si la operación está EN_PROCESO al finalizar el proceso de aprobación. Si por algún motivo sigue EN_PROCESO el estado se cambia para quedar disponible
		try {
			if (operacionesEmpresaService.isProcesandoOperacionSp(operacion.getIdentificacionOperacion(), request.getCodigoServicio(), null,
					request.getRutEmpresa())) {
				LOGGER.warn("La operación nro: [{}] esta EN_PROCESO, se actualiza su estado para quedar disponible de aprobación.", operacion.getIdentificacionOperacion());
				boolean resultadoRegistro = registraSegBiceComex(operacion, request.getRutEmpresa(), request.getRut(),
						inDatoFirma, request.getIdSesion(), Constantes.BICECOMEX_ESTADO_CONSULTADA);
				if (!resultadoRegistro) {
					LOGGER.error(
							"No se pudo actualizar estado: {} en tabla de seguimiento para operación BiceComex nro: {}",
							Constantes.BICECOMEX_ESTADO_CONSULTADA, operacion.getIdentificacionOperacion());
				}
			}
		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			LOGGER.error("Error en operación nro: {}", operacion.getIdentificacionOperacion());
		}
		
		return resultado;
	}

	/**
	 * Recibe una lista de operaciones y las aprueba. Este método controla posibles
	 * errores al intentar realizar las operaciónes para aprobar para luego
	 * enviarselas al controller y este al front.
	 * 
	 * @param request
	 * @param estado
	 * @return operaciones aprobadas, cantidad operaciones no aprobadas y
	 *         operaciones con firma parcial.
	 */
	public AprobarOperacionesResponse aprobarBiceComex(AprobarOperacionesRequest request, Estado estado) {
		List<String> operacionesNoAprobadas = new ArrayList<>();
		List<String> operacionesConFirmaParcial = new ArrayList<>();

		if (estado.isEXITO()) {
			try {

				if (!validacionService.isPertenenciaValidaBiceComex(request, request.getCodigoServicio(), request.getListaOperaciones(), true))
					throw new RequestInvalidoException();
				
				String inDatoFirma = operacionesEmpresaService.obtenerInDatoFirma(request.getDispositivoFirma(), request.getCanal(), request.getIp());
				List<ConsultaGeneral> listaOperacionesBiceComex = consultarOperacionesBiceComex(request.getRut(), request.getRutEmpresa(), Constantes.BICECOMEX_ESTADO_CON_TODOS, null, null);
				List<ConsultaGeneral> listaOperacionesBiceComexPendientes = new ArrayList<>();
				
				if(!listaOperacionesBiceComex.isEmpty()) {
					for (ConsultaGeneral operacion : listaOperacionesBiceComex ) {
						for (String nroOperacion : request.getListaOperaciones() ) {
							if(nroOperacion.equals(operacion.getIdentificacionOperacion())) {
								int resultadoAprobar = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
								try {
									resultadoAprobar = aprobarOperacion(operacion, request, inDatoFirma);
								} catch (SQLTimeoutException e) {
									LOGGER.info("Operación nro: {} queda en lista pendiente por SQLTimeoutException", operacion.getIdentificacionOperacion());
									listaOperacionesBiceComexPendientes.add(operacion);
									continue;
								}

								LOGGER.debug("BiceComexService aprobarBiceComex: nroOperacion[{}] codigoEstado[{}]",
										nroOperacion, resultadoAprobar);

								switch (resultadoAprobar) {
								case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR:
									operacionesNoAprobadas.add(nroOperacion);
									break;
								case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL:
									operacionesConFirmaParcial.add(nroOperacion);
									break;
								default:
									LOGGER.debug(
											"BiceComexService aprobarBiceComex operacion [{}] con firma completa",
											nroOperacion);
									break;
								}
							}
						}
					}
					
				}else {
					LOGGER.error("No se encuentran operaciones BiceComex en BD SQLServer. Rut apoderado: [{}], rut empresa:_[{}], lista operaciones a aprobar: [{}]", request.getRut(), request.getRutEmpresa(), request.getListaOperaciones());				
					estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES, estado);
					operacionesNoAprobadas = null;
				}
				
				//Se revisa estado de aprobación de operaciones que quedaron pendientes tras caer en timeout.
				if(!listaOperacionesBiceComexPendientes.isEmpty()) {
					for (ConsultaGeneral operacionPendiente : listaOperacionesBiceComexPendientes) {

						int resultadoAprobar = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
						try {
							resultadoAprobar = resultadoAprobar(operacionPendiente, request, inDatoFirma, resultadoAprobar);
						} catch (Exception e) {
							LOGGER.info("No se pudo revisar el estado de aprobación de la operación pendiente nro: {}", operacionPendiente.getIdentificacionOperacion());
						}

						LOGGER.debug("BiceComexService aprobarBiceComex: nroOperacion[{}] codigoEstado[{}]",
								operacionPendiente.getIdentificacionOperacion(), resultadoAprobar);

						switch (resultadoAprobar) {
						case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR:
							operacionesNoAprobadas.add(operacionPendiente.getIdentificacionOperacion());
							break;
						case Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL:
							operacionesConFirmaParcial.add(operacionPendiente.getIdentificacionOperacion());
							break;
						default:
							LOGGER.debug(
									"BiceComexService aprobarBiceComex operacion [{}] con firma completa",
									operacionPendiente.getIdentificacionOperacion());
							break;
						}
					}
				}
				
			} catch (Exception e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_APROBAR_OPERACIONES, estado);
				operacionesNoAprobadas = null;
			}
		}

		Integer cantidadOperacionesNoAprobadas = 0;
		if (null != operacionesNoAprobadas && !operacionesNoAprobadas.isEmpty()) {
			cantidadOperacionesNoAprobadas = operacionesNoAprobadas.size();
		}

		AprobarOperacionesResp aprobarOperacionesResp = new AprobarOperacionesResp();
		aprobarOperacionesResp.setOperacionesNoAprobadas(operacionesNoAprobadas);
		aprobarOperacionesResp.setCantOperacionesNoAprobadas(cantidadOperacionesNoAprobadas);
		aprobarOperacionesResp.setOperacionesConFirmaParcial(operacionesConFirmaParcial);
		AprobarOperacionesResponse response = new AprobarOperacionesResponse();
		response.setEstado(estado);
		response.setRespuesta(aprobarOperacionesResp);

		return response;
	}

	/**
	 * Se obtienen
	 * operaciones BiceComex
	 * 
	 * @param rutApoderado,rutEmpresa, fechaDesde fechaHasta 
	 * @return pagosCclv @throws
	 */
	public List<ConsultaGeneral> consultarOperacionesBiceComex(String rutApoderado, String rutEmpresa,
			String estado, String fechaDesde, String fechaHasta) {
		
		List<ConsultaGeneral> listaOperaciones = new ArrayList<>();
		
		try {

			LOGGER.info(
					"BiceComexService consultarOperacionesBiceComex: rutApoderado[{}], rutEmpresa[{}], fechaDesde[{}], fechaHasta[{}]",
					rutApoderado, rutEmpresa, fechaDesde, fechaHasta);

			listaOperaciones = biceComexRepository.consultaOperaciones(rutApoderado, rutEmpresa, Constantes.BICECOMEX_ESTADO_CON_TODOS, Constantes.BICECOMEX_ESTADO_CON_TODOS,
					estado, fechaDesde, fechaHasta);

			if (listaOperaciones == null || listaOperaciones.isEmpty())
				LOGGER.info("No se encontraron operaciones BiceComex");

		} catch (Exception e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
		}

		return listaOperaciones;
	}
	
	/**
	 * Genera una salida con el formato de SP que lista operaciones por aprobar/liberar (POR_SP_CON_GEN_APROB_LIB) 
	 * 
	 * @param mapa
	 * @return List<Map<String, Object>> 
	 */
	public List<Map<String, Object>> generarSalidaTipoSp(List<ConsultaGeneral> listaOperaciones) {
		
		List<Map<String, Object>> salida = new ArrayList<>();
		
		List<String> estadosPorAprobar = biceComexRepository.obtenerEstadosPorAprobar();
		if(!estadosPorAprobar.isEmpty())
			estadosPorAprobar.replaceAll(String::toUpperCase);
		
		if(listaOperaciones != null && !listaOperaciones.isEmpty()) {
			for (ConsultaGeneral operacion : listaOperaciones) {
				if((!estadosPorAprobar.isEmpty() ? estadosPorAprobar.contains(operacion.getEstado().trim().toUpperCase()) : 
					operacion.getEstado().equalsIgnoreCase(Constantes.BICECOMEX_ESTADO_EN_PROCESO)
					|| operacion.getEstado().equalsIgnoreCase(Constantes.BICECOMEX_ESTADO_PENDIENTE)
					|| operacion.getEstado().equalsIgnoreCase(Constantes.BICECOMEX_ESTADO_PENDIENTE_FIRMA))
					&& (operacion.getFirmadaPorApoderado() !=null && operacion.getFirmadaPorApoderado().toUpperCase().equals("NO"))	) {
					
					Map<String, Object> map = new HashMap<>();
					map.put(NUM_OPER_PROG, operacion.getIdentificacionOperacion());
					map.put("COD_SERVICIO", Constantes.CODIGO_SERVICIO_BICECOMEX);
					map.put("TIPO", "BiceComex");
					map.put("CAMPO1", "Monto");
					map.put("VALOR1", operacion.getMonto());
					map.put("CAMPO2", "Producto");
					map.put("VALOR2", operacion.getProducto());
					map.put("CAMPO3", "Tipo Operación");
					map.put("VALOR3", operacion.getTipoOperacion());
					map.put("CAMPO4", "Ref. Ordenante");
					map.put("VALOR4", operacion.getReferenciaOrdenante());
					map.put("CAMPO5", "Moneda");
					map.put("VALOR5", operacion.getMoneda());
					map.put("CAMPO6", "Beneficiario");
					map.put("VALOR6", operacion.getBeneficiarioTema());
					map.put("CAMPO7", "Fecha Ingreso");
					map.put("VALOR7", operacion.getFecha());
					map.put("CAMPO8", "Estado");
					map.put("VALOR8", operacion.getEstado());
					salida.add(map);
					
				}
			}
		}
		
		return salida;
	}
	
	/**
	 * Genera una salida con el formato de SP resumen operaciones (POR_SP_CON_OPER_USER_EMPRESAS)
	 * 
	 * @param mapa
	 * @return List<Map<String, Object>> 
	 */
	public List<Map<String, Object>> generarSalidaTipoSpResumen(List<ConsultaGeneral> listaOperaciones, int cantidadEmpresas) {
		
		List<Map<String, Object>> salida = new ArrayList<>();
		int cantidadAprobar = 0;
		int cantidadLiberar = 0;
		int cantidadTotal = 0;
//		int cantidadEmpresas = 1;
		
		List<String> estadosPorAprobar = biceComexRepository.obtenerEstadosPorAprobar();
		if(!estadosPorAprobar.isEmpty())
			estadosPorAprobar.replaceAll(String::toUpperCase);

		
		if(listaOperaciones != null && !listaOperaciones.isEmpty()) {
			for (ConsultaGeneral operacion : listaOperaciones) {
				
				if((!estadosPorAprobar.isEmpty() ? estadosPorAprobar.contains(operacion.getEstado().trim().toUpperCase()) : 
					operacion.getEstado().equalsIgnoreCase(Constantes.BICECOMEX_ESTADO_EN_PROCESO)
					|| operacion.getEstado().equalsIgnoreCase(Constantes.BICECOMEX_ESTADO_PENDIENTE)
					|| operacion.getEstado().equalsIgnoreCase(Constantes.BICECOMEX_ESTADO_PENDIENTE_FIRMA))
					&& (operacion.getFirmadaPorApoderado() !=null && operacion.getFirmadaPorApoderado().toUpperCase().equals("NO"))	) {
						
					cantidadAprobar++;
				}
//				else if(operacion.getEstado().equalsIgnoreCase(Constantes.BICECOMEX_ESTADO_APROBADA))  Por ahora no se consideran las operaciones a liberar (solo aprobar) 
//					cantidadLiberar++;
				
			}
			cantidadTotal = cantidadAprobar + cantidadLiberar;
			Map<String, Object> map = new HashMap<>();
			map.put("COD_SERVICIO", Constantes.CODIGO_SERVICIO_BICECOMEX);
			map.put("CANTIDADAPROBAR", cantidadAprobar);
			map.put("CANTIDADLIBERAR", cantidadLiberar);
			map.put("CANTIDADTOTAL", cantidadTotal);
			map.put("EMPRESA", cantidadEmpresas);
			salida.add(map);
		}
		
		return salida;
	}
	
	/**
	 * Genera una salida con el formato de SP que lista operaciones del día (POR_SP_CON_OPER_APROB_DIA)
	 * 
	 * @param mapa
	 * @return List<Map<String, Object>> 
	 */
	public List<Map<String, Object>> generarSalidaTipoSpDia(List<ConsultaGeneral> listaOperaciones) {
		
		List<Map<String, Object>> salida = new ArrayList<>();
		
		if(listaOperaciones != null && !listaOperaciones.isEmpty()) {
			for (ConsultaGeneral operacion : listaOperaciones) {
				Map<String, Object> map = new HashMap<>();
				map.put(NUM_OPER_PROG, operacion.getIdentificacionOperacion());
				map.put("COD_SERVICIO", Constantes.CODIGO_SERVICIO_BICECOMEX);
				map.put("TIPO", "BiceComex");
				map.put("CAMPO1", "Monto");
				map.put("VALOR1", operacion.getMonto());
				map.put("CAMPO2", "Producto");
				map.put("VALOR2", operacion.getProducto());
				map.put("CAMPO3", "Tipo Operación");
				map.put("VALOR3", operacion.getTipoOperacion());
				map.put("CAMPO4", "Ref. Ordenante");
				map.put("VALOR4", operacion.getReferenciaOrdenante());
				map.put("CAMPO5", "Moneda");
				map.put("VALOR5", operacion.getMoneda());
				map.put("CAMPO6", "Beneficiario");
				map.put("VALOR6", operacion.getBeneficiarioTema());
				map.put("CAMPO7", "Fecha Ingreso");
				map.put("VALOR7", operacion.getFecha());
				map.put("CAMPO8", "Estado");
				map.put("VALOR8", operacion.getEstado());
				salida.add(map);
			}
		}
		
		return salida;
	}

	/**
	 * Suma los montos totales de las operaciones recibidas. Estos montos totales
	 * están agrupados por moneda. Sólo para operaciones del tipo BiceComex (cod. 1271)
	 * 
	 * @param listaOperaciones
	 * @return
	 */
	public Map<String, ResumenOperacionesMoneda> obtenerMontosTotalesPorMonedaBiceComex(
			List<DetalleCampoValorTipoOperacion> listaOperaciones) {
		Map<String, ResumenOperacionesMoneda> map = new HashMap<>();
		List<String> listNumOperaciones;

		for (DetalleCampoValorTipoOperacion detalle : listaOperaciones) {
			String moneda = detalle.getValor5();
			if (null != moneda) {
				moneda = moneda.trim();
				ResumenOperacionesMoneda resumenOpMoneda;
				if (map.containsKey(moneda)) {
					resumenOpMoneda = map.get(moneda);
					listNumOperaciones = resumenOpMoneda.getListNumOperaciones();
					listNumOperaciones.add(detalle.getNumeroOperacion());
					resumenOpMoneda.setCantidadTotalOperaciones(resumenOpMoneda.getCantidadTotalOperaciones() + 1);
					resumenOpMoneda.setMontoTotalOperaciones(resumenOpMoneda.getMontoTotalOperaciones()
							.add(new BigDecimal(detalle.getValor1())));
					resumenOpMoneda.setListNumOperaciones(listNumOperaciones);
				} else {
					listNumOperaciones = new ArrayList<>();
					listNumOperaciones.add(detalle.getNumeroOperacion());
					resumenOpMoneda = new ResumenOperacionesMoneda();
					resumenOpMoneda.setMonedaOperaciones(moneda);
					String codigoMonedaOperacion = obtenerCodigoMoneda(moneda);
					resumenOpMoneda.setCodigoMonedaOperaciones(codigoMonedaOperacion);
					resumenOpMoneda.setCantidadTotalOperaciones(1);
					resumenOpMoneda
							.setMontoTotalOperaciones(new BigDecimal(detalle.getValor1()));
					resumenOpMoneda.setListNumOperaciones(listNumOperaciones);
				}
				map.put(moneda, resumenOpMoneda);
			}
		}
		return map;
	}
	
	/**
	 * Obtiene código de moneda dada una glosa
	 * 
	 * @param listaOperaciones
	 * @return
	 */
	public String obtenerCodigoMoneda(String glosaMoneda) {
		String codMoneda = "";
		try {
			codMoneda = TipoMoneda.valueOf(glosaMoneda).getCodigo();
		}catch(Exception e) {
			LOGGER.warn("No se encuentra el código de moneda para la glosa {}", glosaMoneda);
		}
		
		return codMoneda;
	}
	
	/** 
	 * Metodo encargado de registrar intenciones y actualizar resultado de aprobaciones BiceComex en BD Oracle
	 * @param datosOper
	 * @param rutCliente
	 * @param rutApoderado
	 * @param infoOp
	 * @param idSesion
	 * @param estado
	 */
	public boolean registraSegBiceComex(ConsultaGeneral datosOper, String rutCliente,String rutApoderado, String infoOp, String idSesion, String estado) throws BancaEmpresasException{
		
		LOGGER.info("BiceComexService.registraSegBiceComex INI");
		
		boolean retorno = false; 
		
	    String log = "identificador:["+datosOper.getIdentificacionOperacion()+"] rutCliente:["+rutCliente+"] rutApoderado:["+rutApoderado+"] producto:["+datosOper.getProducto()+"] tipoOperacion:["+datosOper.getTipoOperacion()+"] "
				+ "refOrdenante:["+datosOper.getReferenciaOrdenante()+"] moneda:["+datosOper.getMoneda()+"] monto:["+datosOper.getMonto()+"] beneficiario:["+datosOper.getBeneficiarioTema()+"] fechaIngreso:["+datosOper.getFecha()+"] "
						+ "estadoFirma:["+(estado != null && !estado.isEmpty() ? estado : datosOper.getEstado())+"] infoOp:["+infoOp+"]";
	    
	    LOGGER.info("BiceComexService.registraSegBiceComex parametros : " + log);
			String salida;
			
			SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat dt2= new SimpleDateFormat("dd-MMM-yy");
			
			String fechaIngreso = "";
			
			try {
				fechaIngreso = dt2.format(dt.parse(datosOper.getFecha()));
				LOGGER.info("Fecha ingreso formateada: {}", fechaIngreso);
			} catch (ParseException e1) {
				LOGGER.warn("No se pudo parsear fecha: {}", fechaIngreso);
				LOGGER.warn(e1.getMessage());
			}
			
			Map<String, Object> params = new HashMap<>();
			params.put("I_IDENTIFICADOR", datosOper.getIdentificacionOperacion());
			params.put("I_RUT_CLIENTE", rutCliente);
			params.put("I_RUT_APODERADO", rutApoderado);
			params.put("I_PRODUCTO", datosOper.getProducto());
			params.put("I_TIPO_OPERACION", datosOper.getTipoOperacion());
			params.put("I_REF_ORDENANTE",  (datosOper.getReferenciaOrdenante() != null) ? datosOper.getReferenciaOrdenante().trim() : datosOper.getReferenciaOrdenante());
			params.put("I_MONEDA", datosOper.getMoneda());
			params.put("I_MONTO", datosOper.getMonto());
			params.put("I_BENEFICIARIO", (datosOper.getBeneficiarioTema() != null) ? datosOper.getBeneficiarioTema().trim() : datosOper.getBeneficiarioTema());
			params.put("I_FECHA_INGRESO", fechaIngreso);
			params.put("I_ESTADO_FIRMA", estado != null && !estado.isEmpty() ? estado : (datosOper.getEstado() != null) ? datosOper.getEstado().trim() : datosOper.getEstado());
			params.put("I_INFO_OPERACION", infoOp);
			params.put("I_ID_SESION", idSesion);
			
			try {
				portalOrawRepository.registraSegBiceComex(params);
				salida = (String) params.get("RESULT");
			} catch (SQLException e) {
				LOGGER.info("BiceComexService.registraSegBiceComex Error : " + e.getMessage());
				throw new ErrorStoredProcedureException(e);
			}

			if (salida == null || salida.equals("")) {
				throw new NoEncontradoException();
			}
			
			LOGGER.info("Status: {}", salida);
			
			//Mayor 0 : exito , < error.
			if(Integer.valueOf(salida) > 0){
				retorno = true;
				LOGGER.info("BiceComexService.registraSegBiceComex Registro exitoso operacion: {}", datosOper.getIdentificacionOperacion());
			}else{
				LOGGER.info("BiceComexService.registraSegBiceComex NO Registro operacion: {}", datosOper.getIdentificacionOperacion());
			}

			return retorno;
	}
	
	/** 
	 * Metodo encargado de registrar consultas y resultado de aprobaciones BiceComex en BD Oracle
	 * @param listaOperaciones
	 * @param esConsulta
	 */
	public void registrarSeguimientoBiceComex(List<ConsultaGeneral> listaOperaciones, String rutCliente, String rutApoderado, String dispositivoFirma, String canal, String ip, String idSesion, String estado) throws BancaEmpresasException{
		String inDatoFirma = operacionesEmpresaService.obtenerInDatoFirma(dispositivoFirma, canal, ip);
		for (ConsultaGeneral operacion : listaOperaciones) {
			registraSegBiceComex(operacion, rutCliente, rutApoderado, inDatoFirma, idSesion, estado);
		}
	}
	
	/** 
	 * Metodo encargado de revisar el estado de aprobación de una operación BiceComex y registrar dicho resultado en tabla de seguimiento bicecomex
	 * @param listaOperaciones
	 * @param esConsulta
	 */
	public int resultadoAprobar(ConsultaGeneral operacion,  AprobarOperacionesRequest request, String inDatoFirma, int resultado) throws BancaEmpresasException{
		
		AprobacionBiceComex resultadoAprobar = biceComexRepository.AprobarOperaciones_resultado(Integer.valueOf(operacion.getIdentificacionOperacion()), Integer.valueOf(operacion.getCorrelativo()), operacion.getFechaModificacion(), operacion.getTimeStampOPRecibidas(), operacion.getProducto(), request.getRutEmpresa(), request.getRut());
		
		if (resultadoAprobar.getReturn_code() == 0) {
			operacion.setEstado("OK");
			//siempre que termine bien el proceso se deja en estado firma parcial, hasta que desde bicecomex se apruebe y deje de aparecer en la lista de operaciones a aprobar
			resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL;
		} else {
			operacion.setEstado(resultadoAprobar.getReturn_msg());
			resultado = Constantes.SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR;
		}

		boolean resultadoRegistro = registraSegBiceComex(operacion, request.getRutEmpresa(), request.getRut(), inDatoFirma, request.getIdSesion(), "");
		if(!resultadoRegistro) 
			LOGGER.error("No se pudo actualizar estado: {} en tabla de seguimiento para operación BiceComex nro: {}" , operacion.getEstado(), operacion.getIdentificacionOperacion());
		
		return resultado;
	}
	
	/** 
	 * Metodo encargado de rcrear id_sesion para actualizar tabla de seguimiento de operaciones BiceComex
	 * @param listaOperaciones
	 * @param esConsulta
	 */
	public String generarIdSesion(String rutCliente, String rutUsuario) throws BancaEmpresasException{
		String idSesion = "";
		String fechaHoy = empresasService.fechaHoy(Constantes.FORMAT_YYYYMMDDHHMMSS);
		idSesion = fechaHoy + rutCliente + rutUsuario;
		return idSesion;
	}

	/**
	 * Se obtiene lista operaciones BiceComex a partir de una lista de números de
	 * operacion
	 * 
	 * @param rutApoderado,rutEmpresa,
	 *            fechaDesde fechaHasta, operaciones
	 * @return listaOperaciones  @throws
	 */
	public List<ConsultaGeneral> obtenerListaBiceComex(String rutApoderado, String rutEmpresa,
			List<String> operaciones) {
		List<ConsultaGeneral> operacionesBiceComex = consultarOperacionesBiceComex(rutApoderado, rutEmpresa,
				Constantes.BICECOMEX_ESTADO_CON_TODOS, null, null);
		List<ConsultaGeneral> listaOperaciones = new ArrayList<>();
		for (String nroOperacion : operaciones) {
			for (ConsultaGeneral operacion : operacionesBiceComex) {
				if (nroOperacion.equals(operacion.getIdentificacionOperacion())) {
					listaOperaciones.add(operacion);
				}
			}
		}

		return listaOperaciones;
	}
	
	/**
	 * Registro en TBL_SEG_BICECOMEX (ORA) sólo para operaciones BiceComex cód. 1271 desde la app (canal ME)
	 * 
	 * 
	 * @param rutApoderado,rutEmpresa,
	 *            fechaDesde fechaHasta, operaciones
	 * @return listaOperaciones  @throws
	 */
	public String registroSeguimientoBiceComexDesafio(String idSesion, ListarCrearDesafiosRequest request) {
		try {
			if(request.getCodigoServicio().equals(Constantes.CODIGO_SERVICIO_BICECOMEX) && request.getCanal().equals(Constantes.CANAL_MOBILE_EMPRESA)) {
				List<ConsultaGeneral> listaOperaciones = obtenerListaBiceComex(request.getRut(), request.getRutEmpresa(), request.getListaOperaciones());
				idSesion = idSesion + generarIdSesion(request.getRutEmpresa(), request.getRut());
				registrarSeguimientoBiceComex(listaOperaciones, request.getRutEmpresa(), request.getRut(), "", request.getCanal(), request.getIp(), idSesion, Constantes.BICECOMEX_ESTADO_CONSULTADA);
			}
		}catch(Exception e) {
			LOGGER.error("Error al registrar en tabla seguimiento operaciones BiceComex error: {}", e.getMessage());
		}
		return idSesion;
	}
	
	/**
	 * Se obtiene resumen operaciones BiceComex a partir de un rut de apoderado y rut empresa
	 * En caso de llamar con rutEmpresa vacío o nulo, buscará todas las operaciones para todas las empresas del apoderado.
	 * 
	 * @param rutApoderado,rutEmpresa,
	 *            fechaDesde fechaHasta, operaciones
	 * @return listaOperaciones  @throws
	 */
	public List<Map<String, Object>> obtenerResumenOperacionesBiceComex(String rutApoderado, String rutEmpresa) {

		List<ConsultaGeneral> listaOperaciones = new ArrayList<>();
		List<String> empresasConOperaciones = new ArrayList<>();

		try {
			if (rutEmpresa.equals(Constantes.CONSTANTE_CERO)) {
				List<Map<String, Object>> listaEmpresas = empresasService.obtenerListaEmpresas(rutApoderado,
						rutEmpresa);
				for (Map<String, Object> empresa : listaEmpresas) {
					List<ConsultaGeneral> operacionesEmpresa = consultarOperacionesBiceComex(rutApoderado,
							(String) empresa.get("RUT_CLIENTE"), Constantes.BICECOMEX_ESTADO_CON_TODOS, null, null);

					if (!empresasConOperaciones.contains((String) empresa.get("RUT_CLIENTE"))
							&& !operacionesEmpresa.isEmpty())
						empresasConOperaciones.add((String) empresa.get("RUT_CLIENTE"));

					listaOperaciones.addAll(operacionesEmpresa);
				}

			} else {
				empresasConOperaciones.add(rutEmpresa);
				listaOperaciones = consultarOperacionesBiceComex(rutApoderado, rutEmpresa,
						Constantes.BICECOMEX_ESTADO_CON_TODOS, null, null);
			}
		} catch (ErrorStoredProcedureException e) {
			e.printStackTrace();
			LOGGER.error("Error al obtener lista empresas, rutApoderado: [{}]", rutApoderado);
		}

		return generarSalidaTipoSpResumen(listaOperaciones, empresasConOperaciones.size());

	}
	
	/**
	 * Se obtiene resumen operaciones BiceComex a partir de un rut de apoderado y rut empresa
	 * En caso de llamar con rutEmpresa vacío o nulo, buscará todas las operaciones para todas las empresas del apoderado.
	 * 
	 * @param rutApoderado,rutEmpresa,
	 *            fechaDesde fechaHasta, operaciones
	 * @return listaOperaciones  @throws
	 * @throws ParseException 
	 * @throws SQLException 
	 */
	public List<Map<String, Object>> obtenerResumenOperacionesBiceComexV2(String rutApoderado, String rutEmpresa) throws ParseException, SQLException {

		List<ConsultaGeneral> listaOperaciones = new ArrayList<>();
		List<String> empresasConOperaciones = new ArrayList<>();
		List<Map<String, Object>> resp= null;
		
		try {
			 
			 Map <String, Object> paramConsulta =  new HashMap<String, Object>();
			 
			 String fechaHoy = empresasService.fechaHoy(Constantes.FORMAT_DD_MM_YYYY2);

				SimpleDateFormat formateador = new SimpleDateFormat(Constantes.FORMAT_YYYYMMDD);
				//Date hoy = formateador.parse(fechaHoy);

	            //java.sql.Date sqlDateInicial = new java.sql.Date(hoy.getTime()); 
	     
	            //java.sql.Date sqlDateFinal = new java.sql.Date(hoy.getTime());
				
				String sqlDateInicial=fechaHoy ; 
		        paramConsulta.put("fecha", sqlDateInicial); 
		       
		        
			if (rutEmpresa.equals(Constantes.CONSTANTE_CERO)) {
				List<Map<String, Object>> listaEmpresas = empresasService.obtenerListaEmpresas(rutApoderado,
						rutEmpresa);
				 

				
			        paramConsulta.put("empresas",listaEmpresas.stream()
		                    .map(dato -> dato.get("RUT_CLIENTE"))
		                    .filter(valor -> valor != null)
		                    .map(Object::toString)
		                    .toArray(String[]::new));
			       
			        
			} else {
				 String[] empresas = {rutEmpresa};
				 paramConsulta.put("empresas",empresas);
			
			}
			 
	        LOGGER.info("Consultando si existen operaciones: {} " + paramConsulta);
	        
	        resp = biceComexRepository.contarOperaciones(paramConsulta);
	    
	        LOGGER.info("Consultando si existen operaciones: {} " + paramConsulta);
	        
		} catch (ErrorStoredProcedureException e) {
			e.printStackTrace();
			LOGGER.error("Error al obtener lista empresas, rutApoderado: [{}]", rutApoderado);
		}

		
		return resp;

	}
}
