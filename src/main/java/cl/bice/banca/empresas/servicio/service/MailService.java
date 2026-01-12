package cl.bice.banca.empresas.servicio.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;

/**
 * Clase para envío de mails.
 * 
 * @author Cristian Pais (TINET)
 *
 */
@Service
public class MailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
	@Autowired
	PortalOrawRepository portalOrawRepository;

	/**
	 * Envía mail con código y glosa de error a través del SP SP
	 * POR_PKG_BANCADIGITAL_EMPRESAS.POR_FUN_LIS_TEF_MX_MAIL_REMESA
	 * 
	 * @param nroOperacion
	 * @param codError
	 * @param glosaError
	 * @param tipo
	 * @throws BancaEmpresasException
	 */
	public void enviarMailCodGlsError(String nroOperacion, String codError, String glosaError, String tipo)
			throws BancaEmpresasException {
		LOGGER.info("MailService enviarMailCodGlsError: nroOperacion[{}] codError[{}] glosaError[{}] tipo[{}]",
				nroOperacion, codError, glosaError, tipo);
		String salida = null;

		Map<String, Object> params = new HashMap<>();
		params.put("V_NUM_OPER_PROG", nroOperacion);
		params.put("V_COD_ERROR", codError);
		params.put("V_GLS_ERROR", glosaError);
		params.put("V_TIPO", tipo);

		try {
			portalOrawRepository.enviarMailCodGlsError(params);
			salida = (String) params.get("RESULT");
		} catch (SQLException | UncategorizedSQLException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new ErrorStoredProcedureException();
		}

		if (null == salida || "-1".equals(salida)) {
			throw new BancaEmpresasException();
		}
	}
}
