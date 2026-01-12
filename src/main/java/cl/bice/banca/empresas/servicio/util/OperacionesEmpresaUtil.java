package cl.bice.banca.empresas.servicio.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase con métodos últiles para las operaciones de una empresa.
 *
 * @author Cristian Pais (TINet)
 * @version 1.0
 */
public class OperacionesEmpresaUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(OperacionesEmpresaUtil.class);
	private static final int CANTIDAD_DE_NUMEROS_SEPARADOR = 2;

	private static final Map<Integer, Integer> mapEstadoNomLinOperProg = new HashMap<Integer, Integer>() {
		{
			put(0, 10);
			put(1, 30);
			put(2, 40);
			put(3, 50);
			put(4, 160);
			put(5, 150);
			put(6, 60);
			put(7, 65);
			put(8, 110);
		}
	};

	/**
	 * Contructor por defecto.
	 */
	public OperacionesEmpresaUtil() {
		super();
	}

	/**
	 * Genero una lista de dos dimensiones que contiene separadores para los pares
	 * campo-valor de las clases DetalleCampoValorTipoOperacion y Operacion.
	 * 
	 * @param tipo de operaci&oacute;n
	 * @return Lista de separadores para la clase DetalleCampoValorTipoOperacion.
	 */
	public List<List<Integer>> obtenerArraySeparadores(String separadores) {
		List<List<Integer>> respuesta = new ArrayList<>();
		List<Integer> rango = new ArrayList<>();

		if (separadores != null) {
			String[] parts = separadores.split(",");
			int cont = 0;
			for (String num : parts) {
				cont++;
				rango.add(Integer.parseInt(num));
				if (cont == CANTIDAD_DE_NUMEROS_SEPARADOR) {
					respuesta.add(rango);
					cont = 0;
					rango = new ArrayList<>();
				}
			}
		}
		LOGGER.info("Respuesta de rangos = {}", respuesta);
		return respuesta;
	}

	public static int obtenerEstadoOperProgNomLin(int estadoNomLin) {
		return mapEstadoNomLinOperProg.get(new Integer(estadoNomLin)).intValue();
	}

}
