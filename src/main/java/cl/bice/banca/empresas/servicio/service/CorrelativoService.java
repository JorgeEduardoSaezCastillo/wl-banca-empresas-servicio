package cl.bice.banca.empresas.servicio.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;

@Service
public class CorrelativoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CorrelativoService.class);

	@Autowired
	PortalOrawRepository portalOrawRepository;

	/**
	 * Retorna idCorrelativo para control de liberacion
	 * 
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String idCorrelativo() throws BancaEmpresasException {
		LOGGER.info("CorrelativoService idCorrelativo");
		String resultado = null;

		Map<String, Object> params = new HashMap<>();
		try {
			portalOrawRepository.obtenerCorrelativoNomLin(params);

			resultado = (String) params.get("P_RESULT");

			if ((null == resultado) || ("".equals(resultado))) {
				throw new NoEncontradoException();
			}

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return resultado;
	}

	public String controlCorrelativo(String rutCliente, String rutUsuario, String codServicio, String glsTransferencia)
			throws BancaEmpresasException {
		String idCorrelativo = idCorrelativo();

		boolean resultRegistrarCorrelativo = registrarCorrelativo(idCorrelativo, codServicio, rutCliente, rutUsuario,
				glsTransferencia);

		if (resultRegistrarCorrelativo) {
			LOGGER.info("CorrelativoService controlCorrelativo resultRegistrarCorrelativo[{}]",
					resultRegistrarCorrelativo);
		}

		return idCorrelativo;
	}

	/**
	 * Registra en la base de datos el idCorrelativo
	 * 
	 * @param idCorrelativo
	 * @param codServicio
	 * @param rutCliente
	 * @param rutUsuario
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean registrarCorrelativo(String idCorrelativo, String codServicio, String rutCliente, String rutUsuario,
			String glsTransferencia) throws BancaEmpresasException {
		LOGGER.info(
				"CorrelativoService registrarCorrelativo: codCorrelativo[{}] codServicio[{}] rutCliente[{}] rutUsuario[{}]",
				idCorrelativo, codServicio, rutCliente, rutUsuario);
		String resultado = null;

		Map<String, Object> params = new HashMap<>();
		params.put("P_COD_CONTROLTRANSF", idCorrelativo);
		params.put("P_COD_SERVICIO", codServicio);
		params.put("P_RUT_CLIENTE", rutCliente);
		params.put("P_RUT_USUARIO", rutUsuario);
		params.put("P_GLS_TRANSFERENCIA", glsTransferencia);
		params.put("P_RESULTADO", null);

		try {
			portalOrawRepository.insertarCorrelativoNomLin(params);

			resultado = (String) params.get("P_RESULTADO");

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if (null == resultado) {
			throw new NoEncontradoException();
		}

		return ("1".equals(resultado));
	}

	/**
	 * Valida que el control correlativo ha sido correcto.
	 * 
	 * @param idCorrelativo
	 * @param codServicio
	 * @return
	 * @throws BancaEmpresasException
	 */
	public boolean isControlCorrelativoCorrecto(String idCorrelativo, String codServicio)
			throws BancaEmpresasException {
		LOGGER.info("CorrelativoService isControlCorrelativoCorrecto: idCorrelativo[{}] codServicio[{}]", idCorrelativo,
				codServicio);
		String resultado = null;

		Map<String, Object> params = new HashMap<>();
		params.put("P_COD_CONTROLTRANSF", idCorrelativo);
		params.put("P_COD_SERVICIO", codServicio);
		params.put("v_COD_RESULT", null);

		try {
			portalOrawRepository.consultarCorrelativoNomLin(params);

			resultado = (String) params.get("v_COD_RESULT");

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		return ((null != resultado) && ("1".equals(resultado)));
	}
	
	/**
	 * Actualiza registro correlativo para control liberacion
	 * 
	 * @param idCorrelativo
	 * @param codServicio
	 * @param listNominasSeleccionadas
	 * @throws BancaEmpresasException
	 */
	public void actualizarCorrelativo(String idCorrelativo, String codServicio,
			List<String> listOperacionesSeleccionadas) throws BancaEmpresasException {
		LOGGER.info("CorrelativoService actualizarCorrelativo: codCorrelativo[{}] codServicio[{}]",
				idCorrelativo, codServicio);
		String resultado = null;

		String ultimaOp = obtenerMaxNumOperacion(listOperacionesSeleccionadas);

		Map<String, Object> params = new HashMap<>();
		params.put("P_COD_CONTROLTRANSF", idCorrelativo);
		params.put("P_COD_SERVICIO", codServicio);
		params.put("P_NUM_OPER_PROG", ultimaOp);
		params.put("P_RESULTADO", null);

		try {
			portalOrawRepository.actualizarCorrelativoNomLin(params);

			resultado = (String) params.get("P_RESULTADO");

		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if ((null == resultado) || (!"1".equals(resultado))) {
			throw new BancaEmpresasException();
		}

	}
	
	/**
	 * Recibe una lista de numeros de operaciones y retorna el mayor
	 * 
	 * @param listNumOperaciones
	 * @return
	 */
	private String obtenerMaxNumOperacion(List<String> listNumOperaciones) {
		int max = Integer.parseInt(listNumOperaciones.get(0));
		for (String numStr : listNumOperaciones) {
			int num = Integer.parseInt(numStr);
			max = num > max ? num : max;
		}
		return String.valueOf(max);
	}
}
