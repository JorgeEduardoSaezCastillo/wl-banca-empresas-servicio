package cl.bice.banca.empresas.servicio.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.WebServiceException;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.IlegalException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.request.TarjetasRequest;
import cl.bice.banca.empresas.servicio.model.request.bia.BiaRequest;
import cl.bice.banca.empresas.servicio.model.request.empresas.CuentaSaldoRequest;
import cl.bice.banca.empresas.servicio.model.request.empresas.SaldoRequest;
import cl.bice.banca.empresas.servicio.model.request.saldo.ConsultaSaldosReq;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.Tarjeta;
import cl.bice.banca.empresas.servicio.model.response.TarjetasResponse;
import cl.bice.banca.empresas.servicio.model.response.bia.Total;
import cl.bice.banca.empresas.servicio.model.response.empresas.CuentaSaldo;
import cl.bice.banca.empresas.servicio.model.response.saldo.DetalleResp;
import cl.bice.banca.empresas.servicio.model.response.saldo.DetalleSaldoResp;
import cl.bice.banca.empresas.servicio.model.response.saldo.SaldoResp;
import cl.bice.banca.empresas.servicio.repository.CifRepository;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.banca.empresas.servicio.util.FormateadorUtil;
import cl.bice.banca.empresas.servicio.util.MapperUtil;

/**
 * Clase que agrupa métodos asociados a las cuentas de empresas.
 * 
 * @author Fibacache
 *
 */
@Service
public class CuentaService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CuentaService.class);
	/**
	 * Propiedades Externas.
	 */
	@Autowired
	protected Properties propiedadesExterna;

	/**
	 * Repositoria del schema cif.
	 */
	@Autowired
	protected CifRepository cifRepository;

	@Autowired
	PortalOrawRepository portalOrawRepository;

	/**
	 * Servicio encargado de las tarjetas.
	 */
	@Autowired
	private TarjetaService tarjetaService;

	@Autowired
	EstadoService estadoService;

	@Autowired
	ProductosService productosService;
	
	
	private static final String NUM_CUENTA = "NUM_CUENTA";
	
	/**
	 * 
	 * @param rut             rut del cliente
	 * @param numeroOperacion número de la operación
	 * @param tipo            tipo de operación
	 * @throws IlegalException
	 */
	public void validarProductoCliente(String rut, String numeroOperacion, String tipo) throws IlegalException {
		switch (tipo) {
		case "TC":
		case "210":
			RestTemplate rest = new RestTemplate();
			TarjetasRequest request = new TarjetasRequest();
			request.setRut(MapperUtil.rutSinCeros(rut));
			request.setNumeroTarjeta("");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);

			HttpEntity<TarjetasRequest> entity = new HttpEntity<>(request, headers);
			TarjetasResponse response = rest.postForObject(
					propiedadesExterna.getProperty(Constantes.NOTIFICACIONES_TARJETAS_URL), entity,
					TarjetasResponse.class);
			if (response != null && response.getRespuesta() != null && response.getRespuesta().getTarjeta() != null) {
				for (Tarjeta tarjeta : response.getRespuesta().getTarjeta()) {
					if (numeroOperacion.equalsIgnoreCase(tarjeta.getNumPanTarjeta())) {
						return;
					}

				}
			}
			LOGGER.error("Se intenta ingresar con informacion incorrecta rut:{} producto:{} tipo:{}", rut,
					numeroOperacion, tipo);
			throw new IlegalException();
		case "100":
		case "101":
			HashMap<String, Object> params = new HashMap<>();
			params.put("pi_rut", MapperUtil.rutSinCeros(rut));
			params.put("pi_numero_operacion", numeroOperacion);
			cifRepository.validaProductoPKG(params);
			if (params.get("p_existe") == null || "0".equals(params.get("p_existe").toString())) {
				LOGGER.error("Se intenta ingresar con informacion incorrecta rut:{} producto:{} tipo:{}", rut,
						numeroOperacion, tipo);
				throw new IlegalException();
			}
			break;
		default:
			LOGGER.error("No se valida rut:{} producto:{} tipo:{}", rut, numeroOperacion, tipo);
			break;
		}

	}

	/**
	 * Obtiene desde SP las cuentas que se permiten ver seg&uacute;n el perfil del
	 * usuario.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @return cuentas permitidas seg&uacute;n perfil usuario
	 * @throws SQLException
	 * @throws BancaEmpresasException
	 */
	public List<Map<String, Object>> obtenerCuentasPorPerfil(String rut, String rutEmpresa)
			throws BancaEmpresasException {
		LOGGER.info("CuentaService obtenerCuentasPorPerfil: rut[{}] rutEmpresa[{}]", rut, rutEmpresa);
		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU, rut);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_CLI, rutEmpresa);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA, null);

		try {
			LOGGER.info(
					"CuentaService obtenerCuentasPorPerfil: ejecuta POR_PKG_BANCADIGITAL_EMPRESAS.POR_SP_CON_PERFIL_USUARIO");
			portalOrawRepository.obtenerCuentasPorPerfil(params);
		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		List<Map<String, Object>> salida = (List<Map<String, Object>>) params
				.get(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA);

		if (!MapperUtil.isSalidaSPValida(salida)) {
			LOGGER.info("CuentaService obtenerCuentasPorPerfil: ERROR salida SP no valida");
			throw new NoEncontradoException();
		} else if (params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT) == null
				|| !"0".equals(params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT).toString().trim())) {
			LOGGER.info("CuentaService obtenerCuentasPorPerfil: ERROR {} = {}",
					Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT,
					params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT));
			throw new BancaEmpresasException();
		}

		return salida;
	}

	/**
	 * Genera (a partir de la salida del SP) una lista de cuentas que incluye
	 * también el saldo de la misma.
	 * 
	 * @param listado
	 * @param request
	 * @return Lista de cuentas con saldo.
	 */
	public List<CuentaSaldo> saldoControllerGenerarListaCuentasSaldo(List<Map<String, Object>> listado,
			CuentaSaldoRequest request) {

		String monedaFormateada = String.format("%03d", Integer.valueOf(request.getMoneda()));
		
		HashMap<String, Object> listadoProductosCC = productosService.obtenerProductosCCWS(request.getRutEmpresa(), request.getDispositivo(), request.getCanal(), request.getToken());
		

		List<CuentaSaldo> respuesta = new ArrayList<>();
		for (Map<String, Object> mapa : listado) {
			CuentaSaldo cuentaSaldo = new CuentaSaldo();

			cuentaSaldo.setRutEmpresa(
					MapperUtil.validaRespuesta(mapa.get(Constantes.PARAMETRO_EMPRESAS_SP_RUT_CLIENTE), false));
			cuentaSaldo.setRutUsuario(MapperUtil.validaRespuesta(mapa.get("RUT_USUARIO"), false));

			int codigoProducto = Integer.parseInt(request.getCodigoProducto());
			String nroCuenta = MapperUtil.validaRespuesta(mapa.get(NUM_CUENTA), true);
			cuentaSaldo.setNumCuenta(FormateadorUtil.formatoProducto(nroCuenta, codigoProducto));
			cuentaSaldo.setNumCuentaBase(nroCuenta);


			cuentaSaldo.setAlias(MapperUtil.validaRespuesta(mapa.get("GLS_ALIAS"), false));
			cuentaSaldo.setFlgApoderado(
					MapperUtil.validaRespuesta(mapa.get(Constantes.PARAMETRO_EMPRESAS_SP_FLG_APODERADO), false));
			cuentaSaldo.setFlgAdmDelegado(
					MapperUtil.validaRespuesta(mapa.get(Constantes.PARAMETRO_EMPRESAS_SP_FLG_ADM_DELEGADO), false));

			SaldoResp saldo;
			try {
				HashMap<String, Object> productoCC = productosService.obtenerProductoCC(listadoProductosCC, nroCuenta);

				if(productoCC != null) {
					LOGGER.info("entro en productoCC, "+productoCC.get("numeroOperacion"));
					request.setCodigoProducto(productoCC.get("codigoProducto").toString());
					saldo = obtenerSaldo(productoCC.get("codigoMoneda").toString(), nroCuenta, request);
				}
				else {
					saldo = obtenerSaldo(monedaFormateada, nroCuenta, request);
				}
				
			} catch (BancaEmpresasException e) {
				LOGGER.warn("ERROR al obtener saldo de la cuenta [{}]: {}", cuentaSaldo.getNumCuenta(), e);
				saldo = new SaldoResp();
			}
			cuentaSaldo.setSaldo(saldo);

			respuesta.add(cuentaSaldo);
		}
		return respuesta;
	}

	/**
	 * Retorna el saldo de una cuenta.
	 * 
	 * @param monedaFormateada moneda formateada
	 * @param request          par&aacute;metros de consulta de saldo
	 * @param estado           estado
	 * @return Saldo de la cuenta.
	 */
	private SaldoResp obtenerSaldo(String monedaFormateada, String cuenta, CuentaSaldoRequest request)
			throws BancaEmpresasException {
		LOGGER.info("[{}][{}] Se llama servico obtener Saldo", request.getSessionID(), request.getRut());
		SaldoResp saldoResp = tarjetaService.obtenerSaldo(new ConsultaSaldosReq(request.getCodigoProducto(), cuenta,
				monedaFormateada, request.getDispositivo(), request.getToken(), request.getRutEmpresa())).getSaldo();
		LOGGER.info("[{}][{}] Retorno obtener Saldo", request.getSessionID(), request.getRut());

		if (request.isBia() && !"210".equalsIgnoreCase(request.getCodigoProducto())) {
			BiaRequest reqBia = new BiaRequest();
			reqBia.setDispositivo(request.getDispositivo());
			reqBia.setIdCuenta(cuenta);
			reqBia.setRut(request.getRutEmpresa());
			reqBia.setToken(request.getToken());
			LOGGER.info("[{}][{}] Llamada Obtener Bia", request.getSessionID(), request.getRut());
			List<Total> lista = tarjetaService.obtenerBia(reqBia);
			LOGGER.info("[{}][{}] Retorno Obtener Bia", request.getSessionID(), request.getRut());
			addDetalleSaldoResp(saldoResp, monedaFormateada, lista.get(0));
		}

		return saldoResp;
	}

	/**
	 * Agrego detalles al objeto de la clase SaldoResp.
	 * 
	 * @param saldoResp
	 * @param monedaFormateada
	 * @param total
	 */
	private void addDetalleSaldoResp(SaldoResp saldoResp, String monedaFormateada, Total total) {
		DetalleSaldoResp detalle = new DetalleSaldoResp();
		detalle.setNombre("BIA");
		DetalleResp linea = new DetalleResp();
		linea.setCodigoMoneda(monedaFormateada);
		linea.setDescripcion(obtenerDescripcion("total.activos", ""));
		linea.setMonto(FormateadorUtil.formatearMonto(total.getTotalActivo().replace(".", "").replace(",", "."),
				monedaFormateada));
		detalle.getSaldos().add(linea);
		linea = new DetalleResp();
		linea.setCodigoMoneda(monedaFormateada);
		linea.setDescripcion(obtenerDescripcion("total.pasivos", ""));
		linea.setMonto(FormateadorUtil.formatearMonto(total.getTotalPasivo().replace(".", "").replace(",", "."),
				monedaFormateada));
		detalle.getSaldos().add(linea);
		linea = new DetalleResp();
		linea.setCodigoMoneda(monedaFormateada);
		linea.setDescripcion(obtenerDescripcion("total.patrimonio", ""));
		linea.setMonto(FormateadorUtil.formatearMonto(total.getPatrimonio().replace(".", "").replace(",", "."),
				monedaFormateada));
		detalle.getSaldos().add(linea);
		saldoResp.getDetalle().add(detalle);
	}

	/**
	 * Obtiene desripción
	 * 
	 * @param tipo
	 * @param defecto
	 * @return
	 */
	private String obtenerDescripcion(String tipo, String defecto) {
		return obtenerPropiedad("texto." + tipo.replace(" ", ".").replace("_", ".").toLowerCase(), defecto);
	}

	/**
	 * Obtiene propiedad.
	 * 
	 * @param texto
	 * @param defecto
	 * @return
	 */
	private String obtenerPropiedad(String texto, String defecto) {
		return propiedadesExterna.getProperty(texto, defecto);
	}

	/**
	 * Retorna el saldo de una cuenta.
	 * 
	 * @param onedaFormateada moneda formateada
	 * @param request         par&aacute;metros de consulta de saldo
	 * @param estado          estado
	 * @return Saldo de la cuenta.
	 */
	public SaldoResp obtenerSaldo(String monedaFormateada, SaldoRequest request, Estado estado) {
		try {
			LOGGER.info("[{}][{}] Se llama servico obtener Saldo", request.getSessionID(), request.getRut());
			
			HashMap<String, Object> listadoProductosCC = productosService.obtenerProductosCCWS(request.getRutEmpresa(), request.getDispositivo(), request.getCanal(), request.getToken());
			HashMap<String, Object> productoCC = productosService.obtenerProductoCC(listadoProductosCC, request.getCuenta());
			
			SaldoResp saldoResp;
			if(productoCC != null) {
				LOGGER.info("entro en productoCC, "+productoCC.get("numeroOperacion"));
				saldoResp = tarjetaService
						.obtenerSaldo(new ConsultaSaldosReq(productoCC.get("codigoProducto").toString(), request.getCuenta(),
								productoCC.get("codigoMoneda").toString(), request.getDispositivo(), request.getToken(), request.getRutEmpresa()))
						.getSaldo();
			}
			else {
				saldoResp = tarjetaService
						.obtenerSaldo(new ConsultaSaldosReq(request.getCodigoProducto(), request.getCuenta(),
								monedaFormateada, request.getDispositivo(), request.getToken(), request.getRutEmpresa()))
						.getSaldo();
			}
			
			LOGGER.info("[{}][{}] Retorno obtener Saldo", request.getSessionID(), request.getRut());

			if (request.isBia() && !"210".equalsIgnoreCase(request.getCodigoProducto())) {
				BiaRequest reqBia = new BiaRequest();
				reqBia.setDispositivo(request.getDispositivo());
				reqBia.setIdCuenta(request.getCuenta());
				reqBia.setRut(request.getRutEmpresa());
				reqBia.setToken(request.getToken());
				LOGGER.info("[{}][{}] Llamada Obtener Bia", request.getSessionID(), request.getRut());
				List<Total> lista = tarjetaService.obtenerBia(reqBia);
				LOGGER.info("[{}][{}] Retorno Obtener Bia", request.getSessionID(), request.getRut());
				addDetalleSaldoResp(saldoResp, monedaFormateada, lista.get(0));
			}

			return saldoResp;
		} catch (WebServiceException | BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_SALDO, estado);

			return new SaldoResp();
		}
	}

	/**
	 * Valida un numero de cuenta pertenece a un rut usuario y rut empresa.
	 * 
	 * @param nroCuenta
	 * @param rut
	 * @param rutEmpresa
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean validaPertenenciaCuentaRutUsuarioRutEmpresa(String nroCuenta, String rut, String rutEmpresa)
			throws BancaEmpresasException {
		LOGGER.info("CuentaService validaPertenenciaCuentaRutUsuarioRutEmpresa: nroCuenta[{}] rut[{}] rutEmpresa[{}]",
				nroCuenta, rut, rutEmpresa);
		boolean resultado = false;
		List<Map<String, Object>> salida = obtenerCuentasPorPerfil(rut, rutEmpresa);
		for (Map<String, Object> mapa : salida) {
			String nCuenta = MapperUtil.validaRespuesta(mapa.get(NUM_CUENTA), false);
			if (nroCuenta.equals(nCuenta.trim())) {
				resultado = true;
				break;
			}
		}
		LOGGER.info("CuentaService validaPertenenciaCuentaRutUsuarioRutEmpresa: resultado[{}]", resultado);
		return resultado;
	}

	/**
	 * Consulta si el rut usuario tiene permiso para ver una cuenta de una empresa.
	 * 
	 * @param rutUsu     rut del usuario
	 * @param rutEmpresa rut de la empresa
	 * @param numCuenta  número de cuenta
	 * @return Retorna verdadero si el usuario tiene permiso para ver la cuenta de
	 *         la empresa.
	 */
	public boolean saldoControllerUsuarioPuedeVerCuentaEmpresa(String rutUsu, String rutEmpresa, String numCuenta) {
		Map<String, Object> params = new HashMap<>();
		params.put("v_rut_usu", rutUsu);
		params.put("v_rut_cli", rutEmpresa);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA, null);
		List<Map<String, Object>> listado;
		try {
			portalOrawRepository.obtenerCuentasPorPerfil(params);
			listado = (List<Map<String, Object>>) params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_SALIDA);
		} catch (SQLException e) {
			LOGGER.error("ERROR: {}", e);
			listado = null;
		}

		boolean respuesta = false;
		for (Map<String, Object> mapa : listado) {
			String numCuentaStr = String.valueOf(mapa.get(NUM_CUENTA));
			respuesta = ((numCuentaStr != null) && numCuentaStr.trim().equalsIgnoreCase(numCuenta));
			if (respuesta)
				break;
		}
		return respuesta;
	}
}
