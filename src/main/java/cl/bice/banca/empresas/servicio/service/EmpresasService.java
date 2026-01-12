package cl.bice.banca.empresas.servicio.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.repository.PorAdminRepository;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.banca.empresas.servicio.util.MapperUtil;

/**
 * Clase que contiene métodos genéricos para empresas.
 * 
 * @author Cristian Pais (TINET)
 *
 */
@Service
public class EmpresasService {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmpresasService.class);
	/**
	 * Propiedades Externas.
	 */
	@Autowired
	protected Properties propiedadesExterna;

	@Autowired
	PortalOrawRepository portalOrawRepository;

	@Autowired
	PorAdminRepository porAdminRepository;

	/**
	 * Valida si el rut usuario pertenece a rut empresa.
	 * 
	 * @param rutUsuario
	 * @param rutEmpresa
	 * @return true si el rut usuario pertenece a rut empresa, false en caso
	 *         contrario.
	 * @throws SQLException
	 */
	public boolean perteneceUsuarioEmpresa(String rutUsuario, String rutEmpresa) throws ErrorStoredProcedureException {
		LOGGER.info("EmpresasService perteneceUsuarioEmpresa: rutUsuario[{}] rutEmpresa[{}]", rutUsuario, rutEmpresa);
		boolean resultado = MapperUtil.isSalidaSPValida(obtenerListaEmpresas(rutUsuario, rutEmpresa));
		LOGGER.info("EmpresasService perteneceUsuarioEmpresa: resultado[{}]", resultado);
		return resultado;
	}

	/**
	 * Obtiene una lista de empresas desde el SP
	 * 
	 * @param rutUsuario
	 * @param rutEmpresa
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> obtenerListaEmpresas(String rutUsuario, String rutEmpresa)
			throws ErrorStoredProcedureException {
		LOGGER.info("EmpresasService obtenerListaEmpresas: rutUsuario[{}] rutEmpresa[{}]", rutUsuario, rutEmpresa);
		List<Map<String, Object>> salidaSP = null;
		Map<String, Object> params = new HashMap<>();
		params.put("rut_usu", rutUsuario);
		params.put("rut_cli", rutEmpresa);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_SALIDA, null);

		try {
			LOGGER.info(
					"EmpresasService obtenerListaEmpresas: ejecuta POR_PKG_BANCADIGITAL_EMPRESAS.POR_SP_CON_USUARIO_EMPRESAS");
			portalOrawRepository.obtenerListaEmpresas(params);
			salidaSP = (List<Map<String, Object>>) params.get(Constantes.PARAMETRO_EMPRESAS_SP_SALIDA);
		} catch (SQLException e) {
			LOGGER.info("EmpresasService obtenerListaEmpresas:", e);
			throw new ErrorStoredProcedureException(e);
		}

		return salidaSP;
	}

	/**
	 * Obtiene el nombre del delegado pero tambien se puede obtener el nombre de un
	 * apoderado
	 * 
	 * @param rut
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String obtenerNombreDelegado(String rut) throws BancaEmpresasException {
		LOGGER.info("EmpresasService obtenerNombreDelegado: rut[{}]", rut);
		String salida = null;

		Map<String, Object> params = new HashMap<>();
		params.put("v_RUT_DELEGADO", rut);

		try {
			LOGGER.info(
					"EmpresasService obtenerNombreDelegado: llamando a SP POR_PKG_BANCADIGITAL_EMPRESAS.POR_SP_CON_NOM_DELEGADO");
			portalOrawRepository.obtenerNombreDelegado(params);
			salida = (String) params.get("v_SALIDA");

			if (null != params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT)
					&& "-1".equals(params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT).toString().trim())) {
				throw new NoEncontradoException();
			}

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return salida;
	}

	/**
	 * Obtiene el nombre del cliente (Empresa)
	 * 
	 * @param rut
	 * @return
	 * @throws NoEncontradoException
	 * @throws BancaEmpresasException
	 * @throws SQLException
	 */
	public String obtenerNombreCliente(String rut) throws BancaEmpresasException {
		LOGGER.info("EmpresasService obtenerNombreCliente: rut[{}]", rut);
		String salida = null;

		Map<String, Object> params = new HashMap<>();
		params.put("v_RUT_CLI", rut);

		try {
			LOGGER.info(
					"EmpresasService obtenerNombreCliente: llamando a SP POR_PKG_BANCADIGITAL_EMPRESAS.POR_SP_CON_NOM_CLIENTE");
			portalOrawRepository.obtenerNombreCliente(params);
			salida = (String) params.get("v_SALIDA");

			if (null != params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT)
					&& "-1".equals(params.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT).toString().trim())) {
				throw new NoEncontradoException();
			}

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return salida;
	}

	/**
	 * Obtiene la fecha actual desde la BD
	 * 
	 * @param formato
	 * @return Fecha actual.
	 */
	public String fechaHoy(String formato) {
		Map<String, Date> params = new HashMap<>();
		try {
		params.put("fecha", null);
		porAdminRepository.obtenerFechaPKG(params);
		} catch (RuntimeException e) {
			LOGGER.error("Error al obtener fecha actual: {}", e);
		}
		return MapperUtil.formatearFecha(params.get("fecha"), formato);
	}
	
	/**
	 * Obtiene la fecha contable desde la BD
	 * 
	 * @param formato
	 * @return Fecha contable.
	 */
	public String fechaContable(String formato, String idServicioMdw, String fechaIni) {
		Map<String, Object> paramsDate = new HashMap<>();
		if ("TRUE".equalsIgnoreCase(propiedadesExterna.getProperty(Constantes.FLAG_FECHA_CONTABLE))) 
			return propiedadesExterna.getProperty(Constantes.FLAG_FECHA_DATA);
		try {
			paramsDate.put("NUM_CONTABLE", null);
			paramsDate.put("FECHA_INI", fechaIni != null && !fechaIni.isEmpty() ? fechaIni : null);
			paramsDate.put("V_TRX", idServicioMdw);
			paramsDate.put("FCONTABLE", null);
			porAdminRepository.obtenerFechaContable(paramsDate);
		} catch (RuntimeException e) {
			LOGGER.error("Error al obtener fecha contable: {}", e);
		}
		return MapperUtil.formatearFecha(paramsDate.get("FCONTABLE"), formato);
	}
	
	/**
	 * Obtiene cuentas colaborativas de una cuenta
	 * 
	 * @param formato
	 * @return Fecha contable.
	 */
	public List<Map<String, Object>> obtenerCuentasColaborativas(String numeroCuenta) {
		Map<String, Object> params = new HashMap<>();
		params.put("v_NUM_CUENTA", numeroCuenta.replaceFirst("^0+(?!$)", ""));
		params.put("v_SALIDA", null);
		
		List<Map<String, Object>> salida = new ArrayList<>();

		try {
			portalOrawRepository.obtenerCuentasColaborativas(params);
			salida = (List<Map<String, Object>>) params
					.get("v_SALIDA");
			if (!MapperUtil.isSalidaSPValida(salida)) {
				LOGGER.error("Error al obtener cuentas colaborativas para la cuenta: [{}]", numeroCuenta);
			} else if (params.get("v_OUT_COD_RESULT") == null
					|| !"0".equals(params.get("v_OUT_COD_RESULT").toString().trim())) {
				throw new BancaEmpresasException();
			} 
		} catch (BancaEmpresasException | SQLException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
		}
		return salida;
	}
	
	/**
	 * Metodo que obtiene fecha desde tbl_iodata
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean isDiaHabil(Date fecha) throws BancaEmpresasException {
		LOGGER.info("EmpresasService isDiaHabil: fecha[{}]", fecha);

		boolean retorno = false;
		String salida;

		Map<String, Object> params = new HashMap<>();
		params.put("P_FECHA", fecha);

		try {
			portalOrawRepository.validaDiaHabil(params);
			salida = (String) params.get("RESULT");
		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if (salida== null || salida.equals("")) {
			throw new NoEncontradoException();
		}

		if("1".equals(salida)) {
			retorno = true;
		}
		
		LOGGER.info("EmpresasService isDiaHabil: resultado[{}]", retorno);
		
		return retorno;
	}

	/**
	 * Intenta obtener el nombre del apoderado consultando al SP
	 * POR_SP_CON_NOM_CLIENTE y si no lo obtiene consulta al SP
	 * POR_SP_CON_NOM_DELEGADO
	 * 
	 * @param rut
	 * @return nombre del apoderado
	 * @throws BancaEmpresasException
	 */
	public String obtenerNombreApoderado(String rut) throws BancaEmpresasException {
		LOGGER.info("EmpresasService obtenerNombreApoderado");
		String nombre;
		try {
			LOGGER.info(
					"EmpresasService obtenerNombreApoderado: intentando obtener nombre llamando a obtenerNombreCliente()");
			nombre = obtenerNombreCliente(rut);
			if ("".equals(nombre)) {
				throw new BancaEmpresasException("Nombre no válido");
			}
		} catch (BancaEmpresasException e) {
			LOGGER.info("EmpresasService obtenerNombreApoderado error: {}", e);
			try {
				LOGGER.info(
						"EmpresasService obtenerNombreApoderado: intentando obtener nombre llamando a obtenerNombreDelegado()");
				nombre = obtenerNombreDelegado(rut);
			} catch (BancaEmpresasException e2) {
				LOGGER.info(
						"EmpresasService obtenerNombreApoderado: no se pudo obtener el nombre del apoderado, error: {}",
						e2);
				nombre = "";
			}
		}

		return nombre;
	}
}
