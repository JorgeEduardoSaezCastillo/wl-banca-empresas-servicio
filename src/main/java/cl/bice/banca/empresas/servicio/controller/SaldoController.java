package cl.bice.banca.empresas.servicio.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.IlegalException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.request.empresas.CuentaSaldoRequest;
import cl.bice.banca.empresas.servicio.model.request.empresas.SaldoRequest;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.empresas.CuentaSaldo;
import cl.bice.banca.empresas.servicio.model.response.empresas.CuentasSaldoPorPerfilResponse;
import cl.bice.banca.empresas.servicio.model.response.empresas.SaldoResponse;
import cl.bice.banca.empresas.servicio.model.response.saldo.SaldoResp;
import cl.bice.banca.empresas.servicio.service.CuentaService;
import cl.bice.banca.empresas.servicio.service.EstadoService;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;

@RestController
@EnableCircuitBreaker
public class SaldoController extends BaseControllerEmpresa {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(SaldoController.class);

	@Autowired
	CuentaService cuentaService;

	@Autowired
	EstadoService estadoService;

	/**
	 * Retorna el saldo de una cuenta.
	 * 
	 * @param request
	 * @return Saldo de una cuenta.
	 */
	@PostMapping(value = "/saldo/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SaldoResponse> obtenerSaldo(@RequestBody SaldoRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		String monedaFormateada = String.format("%03d", Integer.valueOf(request.getMoneda()));

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		SaldoResp saldoResp = null;

		if (estado.isEXITO()) {
			if (StringUtils.isBlank(request.getCodigoProducto())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
						EstadoService.GLOSA_ERROR_REQUEST_CAMPO_CODIGO_PRODUCTO);
			} else if (StringUtils.isBlank(request.getCuenta())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
						EstadoService.GLOSA_ERROR_REQUEST_CAMPO_CUENTA);
			} else if (StringUtils.isBlank(request.getMoneda())) {
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
						EstadoService.GLOSA_ERROR_REQUEST_CAMPO_MONEDA);
			} else {
				try {
					cuentaService.validarProductoCliente(request.getRutEmpresa(), request.getCuenta(),
							request.getCodigoProducto());
				} catch (IlegalException e) {
					LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
					estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
							EstadoService.GLOSA_ERROR_REQUEST_CAMPO_CODIGO_PRODUCTO);
				}
			}
		}

		if (estado.isEXITO()) {
			if (!cuentaService.saldoControllerUsuarioPuedeVerCuentaEmpresa(request.getRut(), request.getRutEmpresa(),
					request.getCuenta())) {
				LOGGER.error("El usuario con rut {} no tiene permiso para ver la cuenta {} de la empresa {}",
						request.getRut(), request.getRutEmpresa(), request.getCuenta());
				estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_SALDO, estado);
			} else {
				saldoResp = cuentaService.obtenerSaldo(monedaFormateada, request, estado);
			}
		}

		if (saldoResp == null)
			saldoResp = new SaldoResp();

		SaldoResponse respuesta = new SaldoResponse();

		respuesta.setEstatus(estado);
		respuesta.setSaldo(saldoResp);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());

		return ResponseEntity.ok(respuesta);
	}

	/**
	 * Retorna las cuentas que se permiten ver según el perfil del usuario.
	 * 
	 * @param request
	 * @return Detalle de cuentas con saldo.
	 * @throws BancaEmpresasException
	 */
	@PostMapping(value = "/empresas/cuentas/listadoConSaldo", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CuentasSaldoPorPerfilResponse> saldoControllerObtenerCuentasSaldoPorPerfil(
			@RequestBody CuentaSaldoRequest request) {

		this.cargarRequest(request);
		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_INICIO, request.getSessionID(), request.getRut());

		Estado estado = estadoService.inicializarEstado();

		reqEmpresasService.requestEmpresasUtilValidarRequestBaseEmpresa(request, estado);

		if (estado.isEXITO() && request.getMoneda() == null) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					" en el campo moneda");
		}

		if (estado.isEXITO() && null != request.getCodigoProducto()) {
			try {
				Integer.parseInt(request.getCodigoProducto());
			} catch (NumberFormatException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
						" en el campo codigoProducto");
			}
		} else if (null == request.getCodigoProducto()) {
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_REQUEST_INVALIDO, estado,
					" en el campo codigoProducto");
		}

		List<CuentaSaldo> listaCuentas = new ArrayList<>();
		if (estado.isEXITO()) {
			try {
				List<Map<String, Object>> salida = cuentaService.obtenerCuentasPorPerfil(request.getRut(),
						request.getRutEmpresa());
				listaCuentas = cuentaService.saldoControllerGenerarListaCuentasSaldo(salida, request);
			} catch (NoEncontradoException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E003_EMPRESAS_LISTA_CUENTAS_SALDO_PERFIL, estado);
			} catch (BancaEmpresasException e) {
				LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
				estadoService.setErrorEstado(EstadoEnum.ERROR_E002_EMPRESAS_LISTA_CUENTAS_SALDO_PERFIL, estado);
			}

		}

		CuentasSaldoPorPerfilResponse resp = new CuentasSaldoPorPerfilResponse();

		resp.setListaCuentas(listaCuentas);
		resp.setEstatus(estado);

		LOGGER.info(Constantes.PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR, request.getSessionID(), request.getRut());

		return ResponseEntity.ok(resp);
	}

}
