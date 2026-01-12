package cl.bice.banca.empresas.servicio.mapper.bicecomex;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;

/**
 * Mapper que contiene los metodos de la BD SQLServer db_legal
 *
 * @author (TINet)
 * @version 1.0
 */
@Primary
public interface BiceComexMapper {

	/**
	 * metodo encargado de consultar operaciones biceComex
	 *
	 * @param params mapa con parametros de entrada y salida.
	 */
	void consultarOperaciones(Map<String, Object> params) throws SQLException;

	List<Map<String, Object>>  contarOperaciones(Map<String, Object> params) throws SQLException;
	
}
