package cl.bice.banca.empresas.servicio.mapper.cif;

import java.util.Map;

/**
 * Mapper que contiene los metodos del package banca digital
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public interface CifMapper {
	/**
	 * metodo encargado de validar un producto del cliente.
	 *
	 * @param con los parametros de entrada.
	 * @return hashmap con la respuesta.
	 */
	void validaProductoPKG(Map<String, Object> params);
}
