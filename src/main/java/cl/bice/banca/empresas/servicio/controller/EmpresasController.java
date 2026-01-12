package cl.bice.banca.empresas.servicio.controller;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.ws.WebServiceException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.model.bicecomex.ConsultaGeneral;
import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.common.Empresa;
import cl.bice.banca.empresas.servicio.model.request.empresas.OperacionesAprobarLiberarRequest;
import cl.bice.banca.empresas.servicio.model.request.empresas.ResumenMovimientoRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.empresas.Cuenta;
import cl.bice.banca.empresas.servicio.model.response.empresas.CuentasPorPerfilResponse;
import cl.bice.banca.empresas.servicio.model.response.empresas.DetalleCampoValorTipoOperacion;
import cl.bice.banca.empresas.servicio.model.response.empresas.EmpresasResponse;
import cl.bice.banca.empresas.servicio.model.response.empresas.IOperacionesAprobarLiberar;
import cl.bice.banca.empresas.servicio.model.response.empresas.MovimientosResponse;
import cl.bice.banca.empresas.servicio.model.response.empresas.OperacionesAprobarLiberar;
import cl.bice.banca.empresas.servicio.model.response.empresas.OperacionesAprobarLiberarResponse;
import cl.bice.banca.empresas.servicio.model.response.empresas.OperacionesLiberar;
import cl.bice.banca.empresas.servicio.model.response.empresas.OperacionesPendientesResponse;
import cl.bice.banca.empresas.servicio.model.response.empresas.ResumenMovimiento;
import cl.bice.banca.empresas.servicio.model.response.empresas.ResumenOperaciones;
import cl.bice.banca.empresas.servicio.model.response.empresas.TiposOperacionesLiberar;
import cl.bice.banca.empresas.servicio.service.BiceComexService;
import cl.bice.banca.empresas.servicio.service.ConsultarParametrosService;
import cl.bice.banca.empresas.servicio.service.CuentaService;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.service.OperacionesEmpresaService;
import cl.bice.banca.empresas.servicio.soap.cartola.MOVIMIENTO;
import cl.bice.banca.empresas.servicio.soap.cartola.TCMW0025OUT;
import cl.bice.banca.empresas.servicio.soap.cartola.cliente.CartolaCliente;
import cl.bice.banca.empresas.servicio.util.FechaUtil;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.banca.empresas.servicio.util.FormateadorUtil;
import cl.bice.banca.empresas.servicio.util.MapperUtil;
import cl.bice.banca.empresas.servicio.util.OperacionesEmpresaUtil;
import cl.bice.nominas.ws.ParametrosVo;

/**
 * Controlador que maneja la informacion para empresas.
 *
 * @author Cristian Pais (TINet)
 * @version 1.0
 */
@RestController
public class EmpresasController extends BaseControllerEmpresa {
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpresasController.class);

	@Autowired
	CartolaCliente cartolaCliente;

	@Autowired
	CuentaService cuentasService;

	@Autowired
	OperacionesEmpresaService operacionesEmpresaService;

	@Autowired
	EstadoService estadoService;
	
	@Autowired
	BiceComexService biceComexService;
	
	@Autowired
	ConsultarParametrosService consultarParametrosService;

	/**
	 * Constante para invocar el web service de consulta de cartola provisoria.
	 */
	private static final String TIPO_MOVIMIENTO = "C";

	/**
	 * Constante para invocar el web service de consulta de cartola provisoria.
	 */
	private static final BigInteger INFO_CHEQUES = BigInteger.ONE;

	private static final String STATUS_OK = "1000";

	private static final String MAS_PAGINAS_PENDIENTES = "Y";

	private static final int LARGO_CUENTA = 18;

	/**
	 * Constante para invocar el web service de consulta de cartola provisoria.
	 */
	private static final String CANAL_INTERNET = "INTERNET";

	/**
	 * Retorna un listado de empresas.
	 * 
	 * @param request
	 * @return Lista de empresas.
	 * @throws BancaEmpresasException
	 */
	@PostMapping(value = "/empresas/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmpresasResponse> empresasControllerObtenerEmpresas(@RequestBody BaseRequestEmpresa request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		List<Empresa> listaEmpresas = new ArrayList<>();
		if (estado.isEXITO()) {
			Map<String, Object> params = new HashMap<>();
			params.put("rut_usu", request.getRut());
			params.put("rut_cli", request.getRutEmpresa());
			params.put(Constantes.PARAMETRO_EMPRESAS_SP_SALIDA, null);

			try {
				portalOrawRepository.obtenerListaEmpresas(params);
				List<Map<String, Object>> salida = (List<Map<String, Object>>) params
						.get(Constantes.PARAMETRO_EMPRESAS_SP_SALIDA);
				if (!MapperUtil.isSalidaSPValida(salida)) {
					estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_OBTENER_EMPRESAS, estado);
				} else if (params.get(Constantes.PARAMETRO_EMPRESAS_SP_OUT_COD_RESULT) == null
						|| !"0".equals(params.get(Constantes.PARAMETRO_EMPRESAS_SP_OUT_COD_RESULT).toString().trim())) {
					throw new BancaEmpresasException();
				} else {
					listaEmpresas = empresasControllerGenerarListaEmpresa(salida);
				}
			} catch (BancaEmpresasException | SQLException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_OBTENER_EMPRESAS, estado);
			}
		}

		EmpresasResponse resp = new EmpresasResponse();

		resp.setEmpresas(listaEmpresas);
		resp.setEstatus(estado);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());

		return ResponseEntity.ok(resp);
	}

	/**
	 * Retorna un resumen de operaciones.
	 * 
	 * @param request
	 * @return Resumen de operaciones.
	 * @throws BancaEmpresasException
	 */
	@PostMapping(value = "/empresas/operaciones/resumen", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OperacionesPendientesResponse> empresasControllerObtenerResumenOperaciones(
			@RequestBody BaseRequestEmpresa request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		ResumenOperaciones resumenOperaciones = new ResumenOperaciones();
		resumenOperaciones.setTipoOperaciones(new ArrayList<>());
		if (estado.isEXITO()) {
			Map<String, Object> params = new HashMap<>();
			params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU, request.getRut());
			params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_CLI, request.getRutEmpresa());
			params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA, null);

			try {
				portalOrawRepository.obtenerResumenOperaciones(params);
				List<Map<String, Object>> salida = (List<Map<String, Object>>) params
						.get(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA);
				
				//se agregan operaciones BiceComex, cod. servicio 1271
				List<Map<String, Object>> salidaSqlServer = biceComexService.obtenerResumenOperacionesBiceComexV2(request.getRut(), request.getRutEmpresa());
				
				if (!MapperUtil.isSalidaSPValida(salida) && !MapperUtil.isSalidaSPValida(salidaSqlServer)) {
					estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_RESUMEN_OPERACIONES, estado);
				} else {
					if(MapperUtil.isSalidaSPValida(salida)) {
						if(MapperUtil.isSalidaSPValida(salidaSqlServer)) {
							salida.addAll(salidaSqlServer);
						}
					}else
						salida = salidaSqlServer;
					resumenOperaciones = operacionesEmpresaService.generarResumenOperaciones(salida);
				}
			} catch (Exception e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_RESUMEN_OPERACIONES, estado);
			}
		}

		OperacionesPendientesResponse resp = new OperacionesPendientesResponse();

		resp.setResumenOperaciones(resumenOperaciones);
		resp.setEstatus(estado);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());

		return ResponseEntity.ok(resp);
	}

	
	
	

	/**
	 * Retorna un resumen de operaciones (deprecado).
	 * 
	 * @param request
	 * @return Resumen de operaciones.
	 * @throws BancaEmpresasException
	 */
	@PostMapping(value = "/empresas/operaciones/resumenOld", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OperacionesPendientesResponse> empresasControllerObtenerResumenOperacionesOld(
			@RequestBody BaseRequestEmpresa request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		ResumenOperaciones resumenOperaciones = new ResumenOperaciones();
		resumenOperaciones.setTipoOperaciones(new ArrayList<>());
		if (estado.isEXITO()) {
			Map<String, Object> params = new HashMap<>();
			params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU, request.getRut());
			params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_CLI, request.getRutEmpresa());
			params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA, null);

			try {
				portalOrawRepository.obtenerResumenOperaciones(params);
				List<Map<String, Object>> salida = (List<Map<String, Object>>) params
						.get(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA);
				
				//se agregan operaciones BiceComex, cod. servicio 1271
				List<Map<String, Object>> salidaSqlServer = biceComexService.obtenerResumenOperacionesBiceComex(request.getRut(), request.getRutEmpresa());
				
				if (!MapperUtil.isSalidaSPValida(salida) && !MapperUtil.isSalidaSPValida(salidaSqlServer)) {
					estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_RESUMEN_OPERACIONES, estado);
				} else {
					if(MapperUtil.isSalidaSPValida(salida)) {
						if(MapperUtil.isSalidaSPValida(salidaSqlServer)) {
							salida.addAll(salidaSqlServer);
						}
					}else
						salida = salidaSqlServer;
					resumenOperaciones = operacionesEmpresaService.generarResumenOperaciones(salida);
				}
			} catch (Exception e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_RESUMEN_OPERACIONES, estado);
			}
		}

		OperacionesPendientesResponse resp = new OperacionesPendientesResponse();

		resp.setResumenOperaciones(resumenOperaciones);
		resp.setEstatus(estado);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());

		return ResponseEntity.ok(resp);
	}

	
	/**
	 * Retorna las cuentas que se permiten ver según el perfil del usuario.
	 * 
	 * @param request
	 * @return Listado de cuentas.
	 * @throws BancaEmpresasException
	 */
	@PostMapping(value = "/empresas/cuentas/listado", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CuentasPorPerfilResponse> empresasControllerObtenerCuentasPorPerfil(
			@RequestBody BaseRequestEmpresa request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		List<Cuenta> listaCuentas = new ArrayList<>();
		if (estado.isEXITO()) {
			try {
				List<Map<String, Object>> salida = cuentasService.obtenerCuentasPorPerfil(request.getRut(),
						request.getRutEmpresa());
				listaCuentas = empresasControllerGenerarListaCuentas(salida);
			} catch (NoEncontradoException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_LISTA_CUENTAS_PERFIL, estado);
			} catch (BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_LISTA_CUENTAS_PERFIL, estado);
			}

		}

		CuentasPorPerfilResponse resp = new CuentasPorPerfilResponse();

		resp.setListaCuentas(listaCuentas);
		resp.setEstatus(estado);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());

		return ResponseEntity.ok(resp);
	}

	/**
	 * Retorna una lista con los datos de los últimos 30 movimientos.
	 * 
	 * @param request
	 * @return Listado de &uacute;ltimos movimientos.
	 * @throws BancaEmpresasException
	 */
	@PostMapping(value = "/empresas/operaciones/listaResumenMovimiento", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MovimientosResponse> listaResumenMovimiento(@RequestBody ResumenMovimientoRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		List<ResumenMovimiento> listadoMovimiento = null;
		if (estado.isEXITO()) {
			StringBuilder sb = new StringBuilder(request.getCuenta());
			while (sb.length() < LARGO_CUENTA) {
				sb.insert(0, "0");
			}

			listadoMovimiento = obtenerListadoMovimientos(sb.toString(), estado);
		}

		if (listadoMovimiento == null)
			listadoMovimiento = new ArrayList<>();

		MovimientosResponse respMovimiento = new MovimientosResponse();

		respMovimiento.setEstatus(estado);
		respMovimiento.setResumenMovimiento(listadoMovimiento);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());
		return ResponseEntity.ok(respMovimiento);
	}

	/**
	 * Retorna un listado de operaciones para aprobar o liberar.
	 * 
	 * @param request
	 * @return Lista de operaciones por aprobar o liberar.
	 * @throws BancaEmpresasException
	 */
	@PostMapping(value = { "/empresas/operaciones/listaAprobar",
			"/empresas/operaciones/listaLiberar" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OperacionesAprobarLiberarResponse> empresasControllerListaOperacionesAprobarLiberar(
			HttpServletRequest httpRequest, @RequestBody OperacionesAprobarLiberarRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());
		LOGGER.info("Filtros de fechas: FechaDesde[{}] FechaHasta[{}]", request.getFechaDesde(), request.getFechaHasta());

		final String requestMapping = (String) httpRequest.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		final boolean isListaAprobar = requestMapping.contains("/listaAprobar");

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		if (estado.isEXITO() && StringUtils.isBlank(request.getCodigoServicio())) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					EstadoService.GLOSA_ERROR_REQUEST_CAMPO_CODIGO_SERVICIO);
		}

		//Inicio validacion de parametros de fechas
		if (request.getCodigoServicio().equals("810")) {
			FechaUtil fechaUtil = new FechaUtil();
			if (!fechaUtil.isFechaValida(request.getFechaDesde()) ) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
						EstadoService.GLOSA_ERROR_REQUEST_CAMPO_FEC_DESDE);
			}
			else {
				if (!fechaUtil.isFechaValida(request.getFechaHasta()) ) {
					estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
							EstadoService.GLOSA_ERROR_REQUEST_CAMPO_FEC_HASTA);
				}
				else {
					String validaFechas = fechaUtil.validaFechas(request.getFechaDesde(), request.getFechaHasta());
					if (!validaFechas.equals("OK")) {
						fechaUtil.setGlosaError(estadoService, estado, validaFechas);
					}
				}
			}
		}
		//Fin validacion de parametros de fechas

		OperacionesAprobarLiberar operacionesAprobarLiberar = new OperacionesAprobarLiberar();
		if (estado.isEXITO()) {
			Map<String, Object> params = new HashMap<>();
			if(!request.getCodigoServicio().equals(Constantes.CODIGO_SERVICIO_BICECOMEX)) {
				params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU, request.getRut());
				params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_CLI, request.getRutEmpresa());
				params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_COD_SERVICIO, request.getCodigoServicio());
				params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_ESTADO, isListaAprobar ? "A" : "L");
				params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_FEC_DESDE, request.getFechaDesde());
				params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_FEC_HASTA, request.getFechaHasta());
				params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA, null);

				try {
					portalOrawRepository.obtenerOperacionesAprobarLiberar(params);
					List<Map<String, Object>> salida = (List<Map<String, Object>>) params
							.get(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA);
					if (!MapperUtil.isSalidaSPValida(salida)) {
						estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_OPERACIONES_LISTAR_APROBAR_LIBERAR,
								estado);
					} else if (params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT) == null || !"0"
							.equals(params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT).toString().trim())) {
						throw new BancaEmpresasException();
					} else {
						operacionesAprobarLiberar = empresasControllerGenerarOperacionesAprobarLiberar(isListaAprobar,
								request.getCodigoServicio(), salida);
					}
				} catch (BancaEmpresasException | SQLException e) {
					LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
					estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_OPERACIONES_LISTAR_APROBAR_LIBERAR, estado);
				}
			}else { //Para BICECOMEX (cod. 1271) se obtienen las operaciones desde sql server

				List<Map<String, Object>> salida= new ArrayList<>();
				try {
					if(isListaAprobar) {
						List<ConsultaGeneral> listaOperaciones = biceComexService.consultarOperacionesBiceComex(request.getRut(), request.getRutEmpresa(), Constantes.BICECOMEX_ESTADO_CON_TODOS, request.getFechaDesde(), request.getFechaHasta());
						salida = biceComexService.generarSalidaTipoSp(listaOperaciones);
					}
					
					if (salida == null || salida.isEmpty()) {
						estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_OPERACIONES_LISTAR_APROBAR_LIBERAR,
								estado);
					} else {
						operacionesAprobarLiberar = empresasControllerGenerarOperacionesAprobarLiberar(isListaAprobar,
								request.getCodigoServicio(), salida);
					}
				} catch (Exception e) {
					LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
					estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_OPERACIONES_LISTAR_APROBAR_LIBERAR, estado);
				}
			}

		}

		OperacionesAprobarLiberarResponse resp = new OperacionesAprobarLiberarResponse();

		resp.setOperaciones(operacionesAprobarLiberar);
		resp.setEstatus(estado);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());

		return ResponseEntity.ok(resp);
	}

	/**
	 * Genera una lista de resumenes de movimientos a partir de un n&uacute;mero de
	 * cuenta.
	 * 
	 * @param cuenta
	 * @param estado
	 * @return Lista de resumenes de movimientos de una cuenta.
	 */
	private List<ResumenMovimiento> obtenerListadoMovimientos(String cuenta, Estado estado) {

		List<ResumenMovimiento> respuesta = new ArrayList<>();
		LOGGER.info("consultado cuenta:"+cuenta+  " | BIG:"+new BigInteger(cuenta).toString());
		try {
			long registro = 0;
			List<MOVIMIENTO> respuestaWs = new ArrayList<>();
			TCMW0025OUT respServicio;
			
			do {
				respServicio = cartolaCliente.obtenerCartolaProvisoria(cuenta, CANAL_INTERNET, TIPO_MOVIMIENTO,
						INFO_CHEQUES, BigInteger.valueOf(registro + 1));

				if (respServicio == null || respServicio.getSTATUS() == null) {
					throw new BancaEmpresasException();
				}

				if (!respServicio.getSTATUS().equals(STATUS_OK)) {
					throw new NoEncontradoException();
				}
				

				if (respServicio.getMOVIMIENTOS() != null) {
					respuestaWs.addAll(respServicio.getMOVIMIENTOS().getMOVIMIENTO());
					registro = Long.parseLong(respServicio.getULTIMOREGISTROPAG());
				}

			} while (respServicio.getMASPAGINAS().equals(MAS_PAGINAS_PENDIENTES));

			if (!respuestaWs.isEmpty()) {
				String moneda = respServicio.getINFOCUENTA().getMONEDA() != null? respServicio.getINFOCUENTA().getMONEDA(): "CLP";
		
				if(moneda.equals("CLP"))
					respuesta = obtenerResumenMovimiento(respuestaWs,Constantes.CODIGO_MONEDA_PESOS);
				else if(moneda.equals("USD"))
					respuesta = obtenerResumenMovimiento(respuestaWs,Constantes.CODIGO_MONEDA_USD);
				else if(moneda.equals("EUR"))
					respuesta = obtenerResumenMovimiento(respuestaWs,Constantes.CODIGO_MONEDA_EURO);
				
			} else {
				throw new NoEncontradoException();
			}

		} catch (NoEncontradoException e) {
			LOGGER.warn(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_LISTA_RESUMEN_MOVIMIENTOS, estado);
		} catch (WebServiceException | BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_LISTA_RESUMEN_MOVIMIENTOS, estado);
		}

		return respuesta;
	}

	/**
	 * Genero clase (OperacionesAprobarLiberar) que contiene: 1) Una lista de
	 * operaciones por aprobar o liberar seg&uacute;n el par&aacute;metro booleano
	 * isAprobar (true=generar lista de operaciones por aprobar, false= generar
	 * lista de operaciones por liberar) 2) Cabecera del listado de operaciones (por
	 * aprobar o liberar) que se mostrar&aacute; en el front.
	 * 
	 * 
	 * @param isAprobar
	 * @param codigoServicio
	 * @param listado
	 * @return Clase (OperacionesAprobarLiberar) que contiene lista de operaciones
	 *         por aprobar o liberar y cabecera del listado a mostrar en el front.
	 */
	private OperacionesAprobarLiberar empresasControllerGenerarOperacionesAprobarLiberar(boolean isAprobar,
			String codigoServicio, List<Map<String, Object>> listado) {

		if (codigoServicio.equals(Constantes.CODIGO_SERVICIO_TEF_MX)
				|| codigoServicio.equals(Constantes.CODIGO_SERVICIO_SPOTWEB)) {
			operacionesEmpresaService.actualizarFormatoMontoOperacionListaMapOperacionesTefMxSpotWeb(codigoServicio, listado);
		}
		
		OperacionesAprobarLiberar opAprobarLiberar = new OperacionesAprobarLiberar();

		opAprobarLiberar.setCodigoServicio(codigoServicio);

		opAprobarLiberar
				.setOperaciones(empresasControllerGenerarListaOperacionesLiberar(isAprobar, listado, codigoServicio));

		if (isAprobar) {
			opAprobarLiberar.setCabecera(
					propiedadesExterna.getProperty("servicios.cabecera.operaciones." + codigoServicio + ".aprobar"));
		} else {
			opAprobarLiberar.setCabecera(
					propiedadesExterna.getProperty("servicios.cabecera.operaciones." + codigoServicio + ".liberar"));
		}

		return opAprobarLiberar;
	}

	/**
	 * Genero una lista de operaciones por aprobar o liberar seg&uacute;n el
	 * par&aacute;metro booleano isAprobar (true=generar lista de operaciones por
	 * aprobar, false= generar lista de operaciones por liberar).
	 * 
	 * @param isAprobar
	 * @param listado
	 * @return Lista de operaciones por aprobar o liberar.
	 */
	private IOperacionesAprobarLiberar empresasControllerGenerarListaOperacionesLiberar(boolean isAprobar,
			List<Map<String, Object>> listado, String codigoServicio) {

		IOperacionesAprobarLiberar respuesta;

		if (isAprobar) {
			respuesta = operacionesEmpresaService.generarListaOperacionesAprobar(listado, codigoServicio);
		} else {
			respuesta = empresasControllerGenerarListaOperacionesLiberar(listado, codigoServicio);
		}

		return respuesta;
	}

	/**
	 * Genera partir de la salida del SP una clase (OperacionesLiberar) que contiene
	 * una lista de operaciones por liberar.
	 * 
	 * @param listado
	 * @return Clase (OperacionesLiberar) que contiene una lista de operaciones por
	 *         liberar.
	 */
	private OperacionesLiberar empresasControllerGenerarListaOperacionesLiberar(List<Map<String, Object>> listado,
			String codigoServicio) {

		OperacionesLiberar operacionesLiberar = new OperacionesLiberar();
		Map<String, TiposOperacionesLiberar> operacionesLiberarMap = new HashMap<>();
		ArrayList<String> arrayTipos = new ArrayList<>();

		for (Map<String, Object> mapa : listado) {
			String tipo = MapperUtil.validaRespuesta(mapa.get("TIPO"), false);
			if (!arrayTipos.contains(tipo))
				arrayTipos.add(tipo);
			if (operacionesLiberarMap.get(tipo) == null) {
				TiposOperacionesLiberar tiposOpLiberar = new TiposOperacionesLiberar();
				List<DetalleCampoValorTipoOperacion> listaDetalleCampoValorTipoOp = new ArrayList<>();
				listaDetalleCampoValorTipoOp.add(operacionesEmpresaService.obtenerDetalleCampoValorTipoOperacion(mapa));
				tiposOpLiberar.setTipo(tipo);
				tiposOpLiberar.setDetalle(listaDetalleCampoValorTipoOp);
				OperacionesEmpresaUtil operacionesEmpresaUtil = new OperacionesEmpresaUtil();

				String parametroSeparadores = null;
				if (Constantes.CODIGO_SERVICIO_LBTR.equals(codigoServicio)) {
					parametroSeparadores = propiedadesExterna
							.getProperty("servicios.separadores.operacion." + codigoServicio + ".tipo." + tipo);
				} else {
					parametroSeparadores = propiedadesExterna
							.getProperty("servicios.separadores.operacion." + codigoServicio + ".tipo.liberar");
				}
				List<List<Integer>> arraySeparadores = operacionesEmpresaUtil
						.obtenerArraySeparadores(parametroSeparadores);

				tiposOpLiberar.setArraySeparadores(arraySeparadores);
				operacionesLiberarMap.put(tipo, tiposOpLiberar);
			} else {
				operacionesLiberarMap.get(tipo).getDetalle()
						.add(operacionesEmpresaService.obtenerDetalleCampoValorTipoOperacion(mapa));
			}
		}
		operacionesLiberar.setArrayTipos(arrayTipos);
		operacionesLiberar.setMapTiposOpLiberar(operacionesLiberarMap);

		return operacionesLiberar;
	}

	/**
	 * Generao una lista de empresas a partir de la salida del SP.
	 * 
	 * @param listado
	 * @return Lista de empresas.
	 */
	private List<Empresa> empresasControllerGenerarListaEmpresa(List<Map<String, Object>> listado) {

		List<Empresa> respuesta = new ArrayList<>();
		for (Map<String, Object> mapa : listado) {
			Empresa empresa = new Empresa();
			empresa.setRutEmpresa(
					MapperUtil.validaRespuesta(mapa.get(Constantes.PARAMETRO_EMPRESAS_SP_RUT_CLIENTE), false));
			empresa.setNombreEmpresa(
					MapperUtil.validaRespuesta(mapa.get(Constantes.PARAMETRO_EMPRESAS_SP_NOMBRES), false));
			empresa.setFlgApoderado(
					MapperUtil.validaRespuesta(mapa.get(Constantes.PARAMETRO_EMPRESAS_SP_FLG_APODERADO), false));
			empresa.setFlgPreferido(
					MapperUtil.validaRespuesta(mapa.get(Constantes.PARAMETRO_EMPRESAS_SP_FLG_INICIO), false));
			empresa.setFlgAdmDelegado(
					MapperUtil.validaRespuesta(mapa.get(Constantes.PARAMETRO_EMPRESAS_SP_FLG_ADM_DELEGADO), false));
			respuesta.add(empresa);
		}
		return respuesta;
	}

	/**
	 * Genero una lista de cuentas a partir de la salida del SP que devuelve las
	 * cuentas por perfil.
	 * 
	 * @param listado
	 * @return Lista de cuentas.
	 */
	private List<Cuenta> empresasControllerGenerarListaCuentas(List<Map<String, Object>> listado) {

		List<Cuenta> respuesta = new ArrayList<>();
		for (Map<String, Object> mapa : listado) {
			Cuenta cuenta = new Cuenta();

			cuenta.setRutEmpresa(
					MapperUtil.validaRespuesta(mapa.get(Constantes.PARAMETRO_EMPRESAS_SP_RUT_CLIENTE), false));
			cuenta.setRutUsuario(MapperUtil.validaRespuesta(mapa.get("RUT_USUARIO"), false));
			cuenta.setNumCuenta(MapperUtil.validaRespuesta(mapa.get("NUM_CUENTA"), false));
			cuenta.setNumCuentaBase(mapa.get("NUM_CUENTA").toString());
			cuenta.setAlias(MapperUtil.validaRespuesta(mapa.get("GLS_ALIAS"), false));
			cuenta.setFlgApoderado(
					MapperUtil.validaRespuesta(mapa.get(Constantes.PARAMETRO_EMPRESAS_SP_FLG_APODERADO), false));
			cuenta.setFlgAdmDelegado(
					MapperUtil.validaRespuesta(mapa.get(Constantes.PARAMETRO_EMPRESAS_SP_FLG_ADM_DELEGADO), false));

			respuesta.add(cuenta);
		}
		return respuesta;
	}

	/**
	 * Genera una lista de resumenes de movimientos.
	 * 
	 * @param lista de movimientos
	 * @return Lista de resumenes de movimientos.
	 */
	private List<ResumenMovimiento> obtenerResumenMovimiento(final List<MOVIMIENTO> wsResp, String tipoMoneda) {

		List<ResumenMovimiento> listadoMovimiento;
		if (!wsResp.isEmpty()) {
			listadoMovimiento = new ArrayList<>();
			
			int maximoMovimientos = obtenerMaxMovimientosCartola();
			LOGGER.info("maximo movimientos:"+maximoMovimientos);

			wsResp.stream().sorted(Comparator.comparing(MOVIMIENTO::getFECHA,Comparator.reverseOrder()))
					.limit(maximoMovimientos)
					.forEachOrdered(item -> {
						ResumenMovimiento resumenMovimiento = new ResumenMovimiento();
						resumenMovimiento.setFecha(item.getFECHA());
						resumenMovimiento.setGlosa(item.getNARRATIVA());
						resumenMovimiento.setMonto(item.getMONTO());
						resumenMovimiento.setMontoFormateado(item.getMONTO() != null
								? FormateadorUtil.formatearMonto(item.getMONTO(), tipoMoneda)
								: item.getMONTO());
						resumenMovimiento.setDetalle(item.getREMARKS());
						resumenMovimiento.setTipo(item.getTIPOMOV());
						listadoMovimiento.add(resumenMovimiento);
					});

		} else {
			listadoMovimiento = new ArrayList<>();
			ResumenMovimiento resumenMovimiento = new ResumenMovimiento();
			resumenMovimiento.setFecha("");
			resumenMovimiento.setGlosa("");
			resumenMovimiento.setMonto("0");
			resumenMovimiento.setMontoFormateado("0");
			listadoMovimiento.add(resumenMovimiento);
		}
		return listadoMovimiento;
	}
	
	/**
	 * 
	 * @return maximo movimiento cartola
	 */
	private int obtenerMaxMovimientosCartola() {

		int max = Integer.parseInt(propiedadesExterna.getProperty("cantidad.movimientos.maximo"));
		
		try {
			
			List<ParametrosVo> parametrosDB = consultarParametrosService.consultaParametro("BANCAMOBILEEMPRESA", "MAXMOVCARTOLA");
			
			if (parametrosDB != null && !parametrosDB.isEmpty()) {
				max = parametrosDB.get(0).getValParametro() != null
						? Integer.parseInt(parametrosDB.get(0).getValParametro().trim())
						: Integer.parseInt(propiedadesExterna.getProperty("cantidad.movimientos.maximo"));
			}

		} catch (Exception e) {
			LOGGER.warn(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			
		}

		return max;
	}

}
