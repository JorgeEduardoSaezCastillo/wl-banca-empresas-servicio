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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorBaseDatosException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.banca.empresas.servicio.util.MapperUtil;


/**
 * Clase destinada a manejar datos de transaccion asociado a un numOperProg
 * 
 *
 */
@Service
public class OperProgService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OperProgService.class);
	private static final String PROP_CANT_VALORES = "servicios.sp.operacionesAprobar.cantidad.valores";
	private static final String STR_NUM_OPER_PROG = "NUM_OPER_PROG";
	private static final String VALOR = "VALOR";
	private static final String V_SALIDA = "v_SALIDA";

	
	@Autowired
	PortalOrawRepository portalOrawRepository;
	
	@Autowired
	Properties propiedadesExterna;
	

	@Transactional
	public String validaProcesoOperProg(String numOperProg, String rutUsuario) {
		String resultado = "";
		
		try {
			if (this.isProcesandoOperacion(numOperProg) ) {
				LOGGER.info("Valida si Operacion esta bloqueada primer intento numOperProg [{}] rutUsuario [{}] estadoCampo2 [{}]", numOperProg, rutUsuario, "BLOQUEADA");
				LOGGER.info("espera de x segundos numOperProg [{}] rutUsuario [{}] ", numOperProg, rutUsuario);
				Thread.sleep(Long.parseLong(propiedadesExterna.getProperty("cant_segundos_espera"))*1000);
				LOGGER.info("despues espera de x segundos numOperProg [{}] rutUsuario [{}] ", numOperProg, rutUsuario);
				if (this.isProcesandoOperacion(numOperProg)) {
					LOGGER.info("operacion se encuentra bloqueada despues espera de x segundos - numOperProg [{}] rutUsuario [{}] ", numOperProg, rutUsuario);
					resultado = Constantes.GLS_ERROR_DATA_DETALLE;
				}
				else {
					LOGGER.info("Valida si Operacion esta bloqueada segundo intento numOperProg [{}] rutUsuario [{}] estadoCampo2 [{}]", numOperProg, rutUsuario, "NO_BLOQUEADA");
					resultado = this.bloquearOperacion(numOperProg);
				}
			}
			else { //bloquear operacion
				LOGGER.info("Valida si Operacion esta bloqueada numOperProg [{}] rutUsuario [{}] estadoCampo2 [{}]", numOperProg, rutUsuario, "NO_BLOQUEADA");
				resultado = this.bloquearOperacion(numOperProg);
				
			}
		}
		catch (Exception e) {
			resultado = Constantes.GLS_ERROR_GENERICO;
		}
		
		return resultado;
	}
	
	private String bloquearOperacion(String numOperProg) {
		String resultado = "";
		
		try {
			String updDetalle = this.actualizaDetalleCampPortal(numOperProg, 2, Constantes.GLS_EN_PROCESO);
			if (Constantes.GLS_ERROR_GENERICO.equals(updDetalle)) {
				resultado = Constantes.GLS_ERROR_UPD_DETALLE_CAMP;
			}
			else {
				resultado = Constantes.GLS_OK;
			}
		}
		catch (Exception e) {
			resultado = Constantes.GLS_ERROR_GENERICO;
		}
		return resultado;
		
	}
	
	
	private String actualizaDetalleCampPortal(String numOperProgPortal, int codigoCampo, String valorCampo) {
		String respuesta = "";
		Map<String, Object> paramsPortal = new HashMap<>();
		paramsPortal.put(Constantes.PARAMETRO_EMPRESAS_V_VAL_CAMPO, valorCampo);
		paramsPortal.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPER_PROG, numOperProgPortal);
		paramsPortal.put(Constantes.PARAMETRO_EMPRESAS_V_COD_CAMPO, codigoCampo);
		try {
			portalOrawRepository.actualizaDetalleCampPortal(paramsPortal);
			respuesta = Constantes.GLS_OK;
		}
		catch (Exception e) {
			LOGGER.error(Constantes.GLS_LOG_ERROR,e.getMessage());
			respuesta = Constantes.GLS_ERROR_GENERICO;
		}
		
		return respuesta;
				
	}

	
	
	/**
	 * Consulta si una operación está siendo procesada para aprobacion
	 * .
	 * 
	 * @param nroOperacion
	 * @return
	 * @throws BancaEmpresasException
	 */
	private boolean isProcesandoOperacion(String nroOperacion) throws BancaEmpresasException {
		try {
			String estadoOperacion = this.consultarDetalleOperProg(nroOperacion);

			if (null == estadoOperacion) {
				throw new BancaEmpresasException();
			}

			return (!Constantes.VAL_CAMPO_NO_BLOQUEADO.equalsIgnoreCase(estadoOperacion.trim().substring(0,1)));

		} catch (NoEncontradoException | ErrorStoredProcedureException e) {
			throw new BancaEmpresasException(e);
		}
	}
	
	
	public String consultarDetalleOperProg(String nroOperacion) throws BancaEmpresasException {
		String resultado;
		
		Map<String, Object> paramsOper = new HashMap<>();
		paramsOper.put(Constantes.PARAM_V_NUM_OPER_PROG, nroOperacion);
		paramsOper.put(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT, null);
		paramsOper.put("v_OUT_MSG_RESULT", null);
		paramsOper.put(V_SALIDA, null);

		try {
			portalOrawRepository.consultarDetalleOperacion(paramsOper);
			resultado = (String) paramsOper.get(V_SALIDA);
			String codResult = (String) paramsOper.get(Constantes.PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT);

			if (!MapperUtil.isCodResultSPValido(codResult)) {
				throw new NoEncontradoException();
			} else if (null == resultado) {
				throw new ErrorStoredProcedureException();
			}
		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return resultado;
	}
	
	/**
	 * Consulta si una operación está siendo procesada.
	 * 
	 * @param nroOperacion
	 * @return
	 * @throws 
	 */
	public String obtenerProcesandoOperacion(String nroOperacion)  {
		String estadoOperacion = "";
		try {
			estadoOperacion = this.consultarDetalleOperProg(nroOperacion);

			if (null == estadoOperacion) {
				estadoOperacion = Constantes.GLS_ERROR_GENERICO;
			}

		} catch (BancaEmpresasException e) {
			estadoOperacion = Constantes.GLS_ERROR_GENERICO;
		}
		
		return estadoOperacion;
	}

	
	
	/**
	 * Método encargado de obtener los datos de las operaciones y guardarlos en un
	 * map. Se utiliza para el servicio de Aprobaciones y Liberaciones Masivas.
	 * 
	 * @param rutUsuario
	 * @param rutEmpresa
	 * @param codServicio
	 * @param canal
	 * @throws BancaEmpresasException
	 */
	public MapOperaciones obtenerMapOperacionesPortal(String rutUsuario, String rutEmpresa, String codServicio, String canal, String listaOperProg, boolean isListaAprobar)
			throws BancaEmpresasException {
		LOGGER.info("ValoresCampoOperacionesService obtenerMapOperaciones: rutUsuario[{}] rutEmpresa[{}] codServicio[{}]", rutUsuario,rutEmpresa, codServicio);

		MapOperaciones mapOperaciones = null;
		List<Map<String, Object>> listValoresSP = new ArrayList<>();
		try {
			
			listValoresSP = this.obtenerDetalleOperacionesAprobarLiberarPortal(rutUsuario, rutEmpresa, codServicio, isListaAprobar, canal, listaOperProg);

			mapOperaciones = new MapOperaciones();
			mapOperaciones.setMapOutputSP(listValoresSP);

			int cantidadValores = Integer
					.parseInt(propiedadesExterna.getProperty(PROP_CANT_VALORES));
			for (Map<String, Object> mapa : listValoresSP) {
				for (int i = 1; i <= cantidadValores; i++) {
					String clave = VALOR + String.valueOf(i);
					String valor = MapperUtil.validaRespuesta(String.valueOf(mapa.get(clave)), false);
					valor = "null".equalsIgnoreCase(valor.trim()) ? "" : valor;
					this.putValorCampoOperacionPortal(mapOperaciones, MapperUtil.validaRespuesta(mapa.get(STR_NUM_OPER_PROG), false), valor, String.valueOf(i));
				}
			}
		} catch (NoEncontradoException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new NoEncontradoException();
		} catch (ErrorStoredProcedureException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new ErrorStoredProcedureException();
		}

		return mapOperaciones;

	}
	
	
	/**
	 * Agrega un valor al map asociado a un determinado número de operación.
	 * 
	 * @param numeroOperacion
	 * @param valor
	 * @param numValor
	 */
	private void putValorCampoOperacionPortal(MapOperaciones mapOperacionesPortal, String numeroOperacionPortal, String valorOper,
			String numValorOper) {
		if (null != mapOperacionesPortal.getMapOperaciones().get(numeroOperacionPortal)) {
			mapOperacionesPortal.getMapOperaciones().get(numeroOperacionPortal).put(VALOR + numValorOper, valorOper);
		} else {
			Map<String, String> valorCampo = new HashMap<>();
			valorCampo.put(VALOR + numValorOper, valorOper);
			mapOperacionesPortal.getMapOperaciones().put(numeroOperacionPortal, valorCampo);
		}
	}
	
	
	/**
	 * Ejecuta consulta via SQL mybatis para obtener el detalle de las operaciones para aprobar/liberar.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param codigoServicio
	 * @param isListaAprobar
	 * @param canal
	 * @return Operaciones por aprobar/liberar.
	 * @throws SQLException
	 * @throws NoEncontradoException
	 */
	public List<Map<String, Object>> obtenerDetalleOperacionesAprobarLiberarPortal(String rutUsuario, String rutEmpresa,
			String codigoServicio, boolean isListaAprobar, String canal, String listaOperProg) throws BancaEmpresasException {
		LOGGER.info("rutUsuario[{}] rutEmpresa[{}] codigoServicio[{}] isListaAprobar[{}] listaOperProg[{}]",
				rutUsuario, rutEmpresa, codigoServicio, isListaAprobar, listaOperProg);
		List<Map<String, Object>> salida = null;
		
		String strEstados = "";
		int codServicioPerfil;
		
		if (isListaAprobar) {
			strEstados = Constantes.TEF_LBTR_ESTADOS_APROBAR;
			codServicioPerfil = Constantes.TEF_LBTR_SRV_PERFIL_APROBAR;
		}
		else {
			strEstados = Constantes.TEF_LBTR_ESTADOS_LIBERAR;
			codServicioPerfil = Constantes.TEF_LBTR_SRV_PERFIL_LIBERAR;
		}

		Map<String, Object> params = new HashMap<>();
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_USU, rutUsuario);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_RUT_CLI, rutEmpresa);
		params.put(Constantes.PARAMETRO_EMPRESAS_SP_V_COD_SERVICIO, codigoServicio);
		params.put(Constantes.PARAMETRO_EMPRESAS_V_LISTA_OPER_PROG, listaOperProg);
		params.put(Constantes.PARAMETRO_EMPRESAS_V_ESTADOS_APROB_LIB, strEstados);
		params.put(Constantes.PARAMETRO_EMPRESAS_V_COD_SERVICIO_PERFIL, codServicioPerfil);

		try {
			if ((null != canal) && Constantes.CANAL_PORTAL.equalsIgnoreCase(canal) && (Constantes.CODIGO_SERVICIO_LBTR.equals(codigoServicio))) {
				salida = portalOrawRepository.obtenerListaOperacionesAprobarLiberarPortal(params);
			}

		} catch (SQLException e) {
			throw new ErrorBaseDatosException(e);
		}
		
		if (salida == null) {
			throw new NoEncontradoException();
		}

		return salida;
	}
	
	
	
	
}
