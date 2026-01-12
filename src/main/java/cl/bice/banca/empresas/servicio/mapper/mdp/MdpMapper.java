package cl.bice.banca.empresas.servicio.mapper.mdp;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.context.annotation.Primary;

/**
 * Mapper que contiene los metodos de la BD SQLServer db_legal
 *
 * @author (TINet)
 * @version 1.0
 */
@Primary
public interface MdpMapper {

	/**
	 * metodo encargado de obtener el alias de un servicio.
	 *
	 * @param params mapa con parametros de entrada y salida.
	 */
	void validaPoder(Map<String, Object> params) throws SQLException;
	
	/**
	 * metodo encargado de obtener abonos cclv
	 *
	 * @param params mapa con parametros de entrada y salida.
	 */
	void obtenerAbonosCclv(Map<String, Object> params) throws SQLException;
	
	/**
	 * metodo encargado de obtener pagos cclv
	 *
	 * @param params mapa con parametros de entrada y salida.
	 */
	void obtenerPagosCclv(Map<String, Object> params) throws SQLException;

}
