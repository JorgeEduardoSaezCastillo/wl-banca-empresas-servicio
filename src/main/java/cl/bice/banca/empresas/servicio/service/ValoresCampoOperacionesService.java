package cl.bice.banca.empresas.servicio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.EntradaInvalidaException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.model.bicecomex.ConsultaGeneral;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.operaciones.MapOperaciones;
import cl.bice.banca.empresas.servicio.util.MapperUtil;

/**
 * Clase enargada de cargar los datos de las operaciones obtenidas desde el SP
 * POR_PKG_BANCADIGITAL_EMPRESAS.POR_SP_CON_LBTR_APROB_LIB
 * 
 * @author Cristian Pais (TINET)
 *
 */
@Service
public class ValoresCampoOperacionesService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ValoresCampoOperacionesService.class);

	/**
	 * Propiedades Externas.
	 */
	@Autowired
	Properties propiedadesExterna;
	
	@Autowired
	BiceComexService biceComexService;

	@Autowired
	OperacionesEmpresaService operacionesEmpresaService;
	
	//@Autowired
	//OperacionesEmpresaPortalService operacionesEmpresaPortalService;
	
	
	private static final String VALOR = "VALOR";
	private static final String CAMPO = "CAMPO";
	private static final String STR_NUM_OPER_PROG = "NUM_OPER_PROG";
	private static final String PROP_CANT_VALORES = "servicios.sp.operacionesAprobar.cantidad.valores";
	
		/**
	 * Método encargado de obtener los datos de las operaciones y guardarlos en un
	 * map.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param codServicio
	 * @param isListarCrearDesafio
	 * @return
	 * @throws BancaEmpresasException
	 */
	public MapOperaciones obtenerMapOperaciones(String rut, String rutEmpresa, String codServicio, boolean isListarCrearDesafio)
			throws BancaEmpresasException {
		return obtenerMapOperaciones(rut, rutEmpresa, codServicio, null, isListarCrearDesafio, null, null);

	}
	
	/**
	 * Método encargado de obtener los datos de las operaciones y guardarlos en un
	 * map.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param codServicio
	 * @return
	 * @throws BancaEmpresasException
	 */
	public MapOperaciones obtenerMapOperaciones(String rut, String rutEmpresa, String codServicio)
			throws BancaEmpresasException {
		return obtenerMapOperaciones(rut, rutEmpresa, codServicio, false);

	}

	/**
	 * Método encargado de obtener los datos de las operaciones y guardarlos en un
	 * map.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param codServicio
	 * @param canal
	 * @throws BancaEmpresasException
	 */
	public MapOperaciones obtenerMapOperaciones(String rut, String rutEmpresa, String codServicio, String canal, boolean isListarCrearDesafio, String fechaDesde, String fechaHasta)
			throws BancaEmpresasException {
		LOGGER.info("ValoresCampoOperacionesService obtenerMapOperaciones: rut[{}] rutEmpresa[{}] codServicio[{}]", rut,
				rutEmpresa, codServicio);

		MapOperaciones mapOperProg = null;
		List<Map<String, Object>> listValoresSP = new ArrayList<>();
		try {
			if (isListarCrearDesafio && (null != canal) && Constantes.CANAL_PORTAL.equalsIgnoreCase(canal)
					&& (Constantes.CODIGO_SERVICIO_SPOTWEB.equals(codServicio))) {
				listValoresSP = operacionesEmpresaService.obtenerOperacionesAprobarLiberarGenericoPortal(rut,
						rutEmpresa, codServicio, true, canal);
			} else if (isListarCrearDesafio && (null != canal) && Constantes.CANAL_PORTAL.equalsIgnoreCase(canal)
					&& (codServicio.equals(Constantes.CODIGO_SERVICIO_BICECOMEX))) {
				List<ConsultaGeneral> operacionesBicecomex = biceComexService.consultarOperacionesBiceComex(rut,
						rutEmpresa, Constantes.BICECOMEX_ESTADO_CON_TODOS, fechaDesde, fechaHasta);
				listValoresSP = biceComexService.generarSalidaTipoSp(operacionesBicecomex);
			} else if (codServicio.equals(Constantes.CODIGO_SERVICIO_BICECOMEX)) {
				List<ConsultaGeneral> operacionesBicecomex = biceComexService.consultarOperacionesBiceComex(rut,
						rutEmpresa, Constantes.BICECOMEX_ESTADO_CON_TODOS, null, null);
				listValoresSP = biceComexService.generarSalidaTipoSp(operacionesBicecomex);
			} else {
				listValoresSP = operacionesEmpresaService.obtenerOperacionesAprobarLiberar(rut, rutEmpresa, codServicio,
						true, canal);
			}
			
			mapOperProg = new MapOperaciones();
			mapOperProg.setMapOutputSP(listValoresSP);

			int cantidadValores = Integer
					.parseInt(propiedadesExterna.getProperty(PROP_CANT_VALORES));
			for (Map<String, Object> mapa : listValoresSP) {
				for (int i = 1; i <= cantidadValores; i++) {
					String clave = VALOR + String.valueOf(i);
					String valor = MapperUtil.validaRespuesta(String.valueOf(mapa.get(clave)), false);
					valor = "null".equalsIgnoreCase(valor.trim()) ? "" : valor;
					putValorCampoOperacion(mapOperProg, MapperUtil.validaRespuesta(mapa.get(STR_NUM_OPER_PROG), false),
							valor, String.valueOf(i));
				}
			}
		} catch (NoEncontradoException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new NoEncontradoException();
		} catch (ErrorStoredProcedureException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new ErrorStoredProcedureException();
		}

		return mapOperProg;

	}

	/**
	 * Agrega un valor al map asociado a un determinado número de operación.
	 * 
	 * @param numeroOperacion
	 * @param valor
	 * @param numValor
	 */
	public void putValorCampoOperacion(MapOperaciones mapOperaciones, String numeroOperacion, String valor,
			String numValor) {
		if (null != mapOperaciones.getMapOperaciones().get(numeroOperacion)) {
			mapOperaciones.getMapOperaciones().get(numeroOperacion).put(VALOR + numValor, valor);
		} else {
			Map<String, String> valorCampo = new HashMap<>();
			valorCampo.put(VALOR + numValor, valor);
			mapOperaciones.getMapOperaciones().put(numeroOperacion, valorCampo);
		}
	}

	/**
	 * Retorna un determinado valor a partir de numValor y numeroOperacion.
	 * 
	 * @param mapOperaciones
	 * @param numeroOperacion
	 * @param nombreCampo
	 * @param isTipoOpAprobar
	 * @return
	 * @throws EntradaInvalidaException
	 */
	public String getValorCampoOperacion(MapOperaciones mapOperaciones, String numeroOperacion, String nombreCampo,
			boolean isTipoOpAprobar) throws NoEncontradoException {
		LOGGER.info("ValoresCampoOperaciones getValorCampoOperacion");
		if (null != mapOperaciones.getMapOperaciones().get(numeroOperacion)) {
			return mapOperaciones.getMapOperaciones().get(numeroOperacion)
					.get(VALOR + propiedadesExterna.getProperty("servicios.sp.operaciones"
							+ (isTipoOpAprobar ? "Aprobar" : "Liberar") + "." + nombreCampo + ".numValor"));
		} else {
			throw new NoEncontradoException(); // no se encontró registro para numeroOperacion
		}
	}
	
	/**
	 * Método encargado de obtener los datos de las operaciones y guardarlos en un
	 * map.
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param codServicio
	 * @throws BancaEmpresasException
	 */
	public MapOperaciones obtenerMapOperacionesCompleto(String rut, String rutEmpresa, String codServicio, boolean isListaAprobar)
			throws BancaEmpresasException {
		LOGGER.info("ValoresCampoOperacionesService obtenerMapOperaciones: rut[{}] rutEmpresa[{}] codServicio[{}]", rut,
				rutEmpresa, codServicio);

		MapOperaciones mapOperaciones = null;
		try {
			List<Map<String, Object>> listValoresSP = operacionesEmpresaService.obtenerOperacionesAprobarLiberar(rut,
					rutEmpresa, codServicio, isListaAprobar);

			mapOperaciones = new MapOperaciones();
			mapOperaciones.setMapOutputSP(listValoresSP);

			int cantidadValores = Integer
					.parseInt(propiedadesExterna.getProperty(PROP_CANT_VALORES));
			for (Map<String, Object> mapa : listValoresSP) {
				for (int i = 1; i <= cantidadValores; i++) {
					String claveValor = VALOR + String.valueOf(i);
					String valor = MapperUtil.validaRespuesta(String.valueOf(mapa.get(claveValor)), false);
					String claveCampo = CAMPO + String.valueOf(i);
					String campo = MapperUtil.validaRespuesta(String.valueOf(mapa.get(claveCampo)), false);
					valor = "null".equalsIgnoreCase(valor.trim()) ? "" : valor;
					campo = "null".equalsIgnoreCase(campo.trim()) ? "" : campo;
					putCampoValorOperacion(mapOperaciones, MapperUtil.validaRespuesta(mapa.get(STR_NUM_OPER_PROG), false),
							campo, valor);
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
	 * Agrega un valor al map asociado a un determinado número de operación con su respectivo nombre (clave).
	 * 
	 * @param numeroOperacion
	 * @param clave, valor
	 * @param numValor
	 */
	private void putCampoValorOperacion(MapOperaciones mapOperaciones, String numeroOperacion, String campo, String valor) {
		if (null != mapOperaciones.getMapOperaciones().get(numeroOperacion)) {
			mapOperaciones.getMapOperaciones().get(numeroOperacion).put(campo, valor);
		} else {
			Map<String, String> valorCampo = new HashMap<>();
			valorCampo.put(campo, valor);
			mapOperaciones.getMapOperaciones().put(numeroOperacion, valorCampo);
		}
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
	/* MBS
	public MapOperaciones obtenerMapOperacionesPortal(String rutUsuario, String rutEmpresa, String codServicio, String canal, String listaOperProg, boolean isListaAprobar)
			throws BancaEmpresasException {
		LOGGER.info("ValoresCampoOperacionesService obtenerMapOperaciones: rutUsuario[{}] rutEmpresa[{}] codServicio[{}]", rutUsuario,rutEmpresa, codServicio);

		MapOperaciones mapOperaciones = null;
		List<Map<String, Object>> listValoresSP = new ArrayList<>();
		try {
			
			listValoresSP = operacionesEmpresaPortalService.obtenerDetalleOperacionesAprobarLiberarPortal(rutUsuario, rutEmpresa, codServicio, isListaAprobar, canal, listaOperProg);

			mapOperaciones = new MapOperaciones();
			mapOperaciones.setMapOutputSP(listValoresSP);

			int cantidadValores = Integer
					.parseInt(propiedadesExterna.getProperty(PROP_CANT_VALORES));
			for (Map<String, Object> mapa : listValoresSP) {
				for (int i = 1; i <= cantidadValores; i++) {
					String clave = VALOR + String.valueOf(i);
					String valor = MapperUtil.validaRespuesta(String.valueOf(mapa.get(clave)), false);
					valor = "null".equalsIgnoreCase(valor.trim()) ? "" : valor;
					putValorCampoOperacion(mapOperaciones, MapperUtil.validaRespuesta(mapa.get(STR_NUM_OPER_PROG), false), valor, String.valueOf(i));
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
	*/
	
	

	/**
	 * Método encargado de obtener los datos de las operaciones y guardarlos en un
	 * map. Se utiliza para el proceso de liberacion masiva
	 * 
	 * @param rut
	 * @param rutEmpresa
	 * @param codServicio
	 * @throws BancaEmpresasException
	 */
	public MapOperaciones obtenerMapOperacionesPortalLiberacion(String rutApoderado, String rutEmpresa, String codServicio, boolean isListaAprobar)
			throws BancaEmpresasException {
		LOGGER.info("ValoresCampoOperacionesService obtenerMapOperaciones: rutApoderado[{}] rutEmpresa[{}] codServicio[{}] isListaAprobar [{}]", 
				rutApoderado, rutEmpresa, codServicio, isListaAprobar);

		MapOperaciones mapOperacionesLib = null;
		try {
			List<Map<String, Object>> listValoresSPLib = operacionesEmpresaService.obtenerOperacionesAprobarLiberar(rutApoderado,
					rutEmpresa, codServicio, isListaAprobar);

			mapOperacionesLib = new MapOperaciones();
			mapOperacionesLib.setMapOutputSP(listValoresSPLib);

			int cantValores = Integer
					.parseInt(propiedadesExterna.getProperty(PROP_CANT_VALORES));
			for (Map<String, Object> mapaLib : listValoresSPLib) {
				for (int i = 1; i <= cantValores; i++) {
					String claveValor = VALOR + String.valueOf(i);
					String valor = MapperUtil.validaRespuesta(String.valueOf(mapaLib.get(claveValor)), false);
					String claveCampo = CAMPO + String.valueOf(i);
					String campo = MapperUtil.validaRespuesta(String.valueOf(mapaLib.get(claveCampo)), false);
					valor = "null".equalsIgnoreCase(valor.trim()) ? "" : valor;
					campo = "null".equalsIgnoreCase(campo.trim()) ? "" : campo;
					putCampoValorOperacion(mapOperacionesLib, MapperUtil.validaRespuesta(mapaLib.get(STR_NUM_OPER_PROG), false),
							campo, valor);
				}
			}
		} catch (NoEncontradoException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new NoEncontradoException();
		} catch (ErrorStoredProcedureException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new ErrorStoredProcedureException();
		}

		return mapOperacionesLib;

	}
	
	
}
