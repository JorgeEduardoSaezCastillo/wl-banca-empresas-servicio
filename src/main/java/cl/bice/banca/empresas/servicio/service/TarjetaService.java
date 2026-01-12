package cl.bice.banca.empresas.servicio.service;

import java.net.URI;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorServicioException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.model.request.bia.BiaRequest;
import cl.bice.banca.empresas.servicio.model.request.dolares.DolaresRequest;
import cl.bice.banca.empresas.servicio.model.request.movimientos.ConsultaMovimientosNoFacturadosReq;
import cl.bice.banca.empresas.servicio.model.request.saldo.ConsultaSaldosReq;
import cl.bice.banca.empresas.servicio.model.response.bia.BiaResponse;
import cl.bice.banca.empresas.servicio.model.response.bia.SobreGiro;
import cl.bice.banca.empresas.servicio.model.response.bia.SobreGiroResponse;
import cl.bice.banca.empresas.servicio.model.response.bia.Total;
import cl.bice.banca.empresas.servicio.model.response.dolares.DolaresResponse;
import cl.bice.banca.empresas.servicio.model.response.movimientos.MovimientosResponse;
import cl.bice.banca.empresas.servicio.model.response.saldo.ConsultaSaldosResp;
import cl.bice.banca.empresas.servicio.model.response.saldo.SaldoResponse;
import cl.bice.banca.empresas.servicio.util.MapperUtil;

/**
 * Servicio encargado de obtener la informacion de las tarjetas.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
@Service
public class TarjetaService {

	/**
	 * Rest Template encargado de realizar las llamadas.
	 */
	private final RestTemplate restTemplate;

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaService.class);

	/**
	 * Propiedades Externas.
	 */
	@Autowired
	private Properties propiedadesExterna;

	public TarjetaService(RestTemplate rest) {
		this.restTemplate = rest;
	}

	/**
	 * Retorna el saldo de una cuenta.
	 * 
	 * @param req
	 * @return
	 * @throws BancaEmpresasException
	 */
	public ConsultaSaldosResp obtenerSaldo(ConsultaSaldosReq req) throws BancaEmpresasException {

		URI uri = URI.create(propiedadesExterna.getProperty("servicios.url.saldo"));
		LOGGER.debug("obtener saldo, cuenta:"+req.getCuenta()+" | "+req.getCodigoProducto()+" | :"+req.getMoneda());
		SaldoResponse respuesta = this.restTemplate.postForObject(uri, req, SaldoResponse.class);
		LOGGER.debug("{}", respuesta);
		if ("1".equalsIgnoreCase(respuesta.getRespuesta().getEstado().getCodigoEstado())) {
			LOGGER.debug("{}", respuesta);
			return respuesta.getRespuesta();
		} else {
			throw new NoEncontradoException();
		}
	}

	/**
	 * Obtiene bia.
	 * 
	 * @param req
	 * @return
	 * @throws NoEncontradoException
	 */
	public List<Total> obtenerBia(BiaRequest req) throws NoEncontradoException {
		URI uri = URI.create(propiedadesExterna.getProperty("servicios.url.tarjeta.bia"));
		BiaRequest reqFinal = new BiaRequest();
		reqFinal.setDispositivo(req.getDispositivo());
		reqFinal.setRut(MapperUtil.rutSinCeros(req.getRut()));
		reqFinal.setToken(req.getToken());
		reqFinal.setIdCuenta(req.getIdCuenta());
		BiaResponse respuesta = this.restTemplate.postForObject(uri, reqFinal, BiaResponse.class);
		if ("0".equalsIgnoreCase(respuesta.getEstado().getCodigoEstado())) {
			return respuesta.getListas().getTotal();
		} else {
			throw new NoEncontradoException();
		}
	}

	/**
	 * Consulta movimientos no facturados.
	 * 
	 * @param req
	 * @param request
	 * @return
	 * @throws ErrorServicioException
	 */
	public MovimientosResponse obtenerNoFacturado(ConsultaMovimientosNoFacturadosReq req, ConsultaSaldosReq request)
			throws ErrorServicioException {

		LOGGER.info("[{}][{}] INICIO", request.getSessionID(), request.getRut());
		URI uri = URI.create(propiedadesExterna.getProperty("servicios.url.tarjeta.no.facturado"));

		LOGGER.info("[{}][{}] Uri a utilizar: {}", request.getSessionID(), request.getRut(), uri.getPath());
		MovimientosResponse respuesta = this.restTemplate.postForObject(uri, req, MovimientosResponse.class);
		LOGGER.debug("[{}][{}] Respuesta: {}", request.getSessionID(), request.getRut(), respuesta);
		if ("1".equalsIgnoreCase(respuesta.getRespuesta().getEstado().getCodigoEstado())) {
			return respuesta;
		} else {
			throw new ErrorServicioException();
		}
	}

	/**
	 * Retorna cartola en dolares.
	 * 
	 * @param req
	 * @param request
	 * @return
	 * @throws ErrorServicioException
	 */
	public DolaresResponse obtenerDolares(DolaresRequest req, ConsultaSaldosReq request) throws ErrorServicioException {
		LOGGER.info("[{}][{}] INICIO", request.getSessionID(), request.getRut());
		URI uri = URI.create(propiedadesExterna.getProperty("servicios.url.dolares"));
		LOGGER.info("[{}][{}] Uri a utilizar: {}", request.getSessionID(), request.getRut(), uri.getPath());
		DolaresResponse respuesta = this.restTemplate.postForObject(uri, req, DolaresResponse.class);
		LOGGER.debug("[{}][{}] Respuesta: {}", request.getSessionID(), request.getRut(), respuesta);
		if ("1".equalsIgnoreCase(respuesta.getRespuesta().getEstado().getCodigoEstado())) {
			return respuesta;
		} else {
			throw new ErrorServicioException();
		}
	}

	public SobreGiroResponse obtenerSobreGiro(BiaRequest req) {
		LOGGER.info("[{}] Se inicia metodo", req.getRut());
		LOGGER.debug("[{}] Req: {}", req.getRut(), req);
		SobreGiroResponse respuesta = new SobreGiroResponse();
		String url = propiedadesExterna.getProperty("servicios.url.tarjeta.bia.sobregiro");
		if (url == null) {
			return sobreGiroCero(respuesta);
		}
		try {
			URI uri = URI.create(url);
			respuesta = this.restTemplate.postForObject(uri, req, SobreGiroResponse.class);
			LOGGER.debug("[{}] Respuesta: {}", req.getRut(), respuesta);
		} catch (Exception e) {
			LOGGER.error("[{}] Error al obtener Respuesta desde {} con request {}: {}", req.getRut(), url, req, e);
			return sobreGiroCero(respuesta);
		}

		LOGGER.info("[{}] Antes de retornar", req.getRut());
		return respuesta;

	}

	/**
	 * Retorna información de sobregiro.
	 * 
	 * @param respuesta
	 * @return
	 */
	private SobreGiroResponse sobreGiroCero(SobreGiroResponse respuesta) {
		SobreGiro sobreGiro = new SobreGiro();
		sobreGiro.setInteresUsoLinea("0");
		respuesta.setSobreGiro(sobreGiro);
		return respuesta;
	}
}
