package cl.bice.banca.empresas.servicio.mapper.poradmin;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Primary;

/**
 * Mapper que contiene los metodos del package banca digital
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
@Primary
public interface BancaDigitalMapper {

	/**
	 * Metodo encargado de obtener la Fecha desde la BD.
	 * 
	 * @param params mapa con parametros de entrada y salida.
	 */
	void obtenerFechaPKG(Map<String, Date> params);
	
	/**
	 * Metodo encargado de obtener la Fecha Contable desde la BD.
	 * 
	 * @param params mapa con parametros de entrada y salida.
	 */
	void obtenerFechaContable(Map<String, Object> params);
	
	/**
	 * Metodo encargado de obtener los bancos.
	 * 
	 * @param params mapa con parametros de entrada y salida.
	 */
	void obtenerBancos(Map<String, Object> params);
}
