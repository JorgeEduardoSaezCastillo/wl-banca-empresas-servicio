package cl.bice.banca.empresas.servicio.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.soap.ClienteTablaParametros;
import cl.bice.nominas.parametros.model.CacheParametros;
import cl.bice.nominas.ws.ConsultaParametrosOut;
import cl.bice.nominas.ws.ParametrosVo;

/**
 * Clase para consultar la tabla parámetros a través del cliente del servicio de
 * la tabla parámetros.
 * 
 * @author Fibacache
 *
 */
@Service
public class ConsultarParametrosService {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultarParametrosService.class);

	@Autowired
	@Qualifier("MapCache")
	CacheParametros cacheParametros;

	@Autowired
	@Qualifier("ClienteTablaParametros")
	ClienteTablaParametros tablaParametros;

	public static final String NOMPARAM = "NOMPARAM";
	public static final String NOMTIPO = "NOMTIPO";

	/**
	 * Consulta parámetro.
	 * 
	 * @param nomParam
	 * @param nomTipo
	 * @return
	 * @throws BancaEmpresasException
	 */
	@SuppressWarnings("unchecked")
	public List<ParametrosVo> consultaParametro(String nomParam, String nomTipo) throws BancaEmpresasException {
		List<ParametrosVo> listParametros = new ArrayList<>();
		String auxNomParam = nomParam;
		if (null == nomParam || "".equals(nomParam.trim()))
			auxNomParam = nomTipo;

		if (!cacheParametros.getMapCache().isEmpty() && null != cacheParametros.getMapCache().get(auxNomParam)
				&& null != ((Map<String, Object>) cacheParametros.getMapCache().get(auxNomParam)).get(nomTipo)) {

			listParametros = (List<ParametrosVo>) ((Map<String, Object>) cacheParametros.getMapCache().get(auxNomParam))
					.get(nomTipo);
		} else {

			Map<String, Object> mapData = new HashMap<>();

			mapData.put(NOMPARAM, nomParam);
			mapData.put(NOMTIPO, nomTipo);

			if (tablaParametros.call(mapData)) {
				ConsultaParametrosOut resp = (ConsultaParametrosOut) mapData.get("respuesta");

				if (null != resp && resp.getCodEstado() > 0) {
					listParametros = putCache(nomTipo, auxNomParam, resp);
				} else {
					LOGGER.error("Error general de base datos");
				}
			}
		}
		return listParametros;
	}

	public boolean validarRegla(String nomParam, String nomTipo, String dato, int posicionValidacion)
			throws BancaEmpresasException {

		List<ParametrosVo> listParametro = consultaParametro(nomParam, nomTipo);
		if (!listParametro.isEmpty()) {
			for (ParametrosVo parametro : listParametro) {
				char resp = continueSwitch(dato, posicionValidacion, parametro);
				if (resp == 0)
//					se encontro parametro y fue correcta la regla
					return true;
			}
		}
//		se retorna falso ya que la lista esta vacia o no se encontro coincidencia de parametros
		return false;
	}

	// 0= TRUE, 1= false, 2 = continuar
	private char continueSwitch(String dato, int posicionValidacion, ParametrosVo parametro) {

		switch (posicionValidacion) {
		case 3:
			if (null != parametro.getValParametro() && parametro.getValParametro().trim().equalsIgnoreCase(dato)) {
				return 0;
			}
			break;
		case 2:
			if (null != parametro.getNomTipo() && parametro.getNomTipo().trim().equalsIgnoreCase(dato)) {
				return 0;
			}
			break;
		case 1:
			if (null != parametro.getNomParam() && parametro.getNomParam().trim().equalsIgnoreCase(dato)) {
				return 0;
			}
			break;

		case 4:
			if (null != parametro.getValParametro2() && parametro.getValParametro2().trim().equalsIgnoreCase(dato)) {
				return 0;
			}
			break;

		default:
			return continueSwitch2(dato, posicionValidacion, parametro);
		}
		return 2;
	}

	// 0= TRUE, 1= false, 2 = continuar
	private char continueSwitch2(String dato, int posicionValidacion, ParametrosVo parametro) {
		switch (posicionValidacion) {
		case 5:
			if (null != parametro.getValParametro3() && parametro.getValParametro3().trim().equalsIgnoreCase(dato)) {
				return 0;
			}
			break;
		case 6:
			if (null != parametro.getValParametro4() && parametro.getValParametro4().trim().equalsIgnoreCase(dato)) {
				return 0;
			}
			break;
		default:
			return 1;
		}
		return 2;
	}

	@SuppressWarnings("unchecked")
	private List<ParametrosVo> putCache(String nomTipo, String auxNomParam, ConsultaParametrosOut resp) {
		if (null != cacheParametros.getMapCache().get(auxNomParam)) {
			((Map<String, Object>) cacheParametros.getMapCache().get(auxNomParam)).put(nomTipo, resp.getParametrosVo());
		} else {
			Map<String, Object> mapRespuesta = new HashMap<>();
			mapRespuesta.put(nomTipo, resp.getParametrosVo());
			cacheParametros.getMapCache().put(auxNomParam, mapRespuesta);
		}

		return resp.getParametrosVo();
	}

}