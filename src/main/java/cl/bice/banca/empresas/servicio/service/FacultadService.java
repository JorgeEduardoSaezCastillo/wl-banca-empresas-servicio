package cl.bice.banca.empresas.servicio.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;
import cl.bice.nominas.ws.ParametrosVo;

/**
 * Clase para el manejo de facultades.
 * 
 * @author Cristian Pais
 *
 */
@Service
public class FacultadService {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FacultadService.class);

	@Autowired
	PortalOrawRepository portalOrawRepository;

	/**
	 * Propiedades Externas.
	 */
	@Autowired
	private Properties propiedadesExterna;

	@Autowired
	ConsultarParametrosService consultarParametrosService;

	/**
	 * Obtiene el código de facultad.
	 * 
	 * @param codServicio
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String obtenerCodigoFacultad(String codServicio) throws BancaEmpresasException {
		String resultado = null;

		List<ParametrosVo> listParametro = consultarParametrosService.consultaParametro(
				propiedadesExterna.getProperty(Constantes.TBL_PARAM_COD_FACULTAD_NOMBRE),
				propiedadesExterna.getProperty(Constantes.TBL_PARAM_COD_FACULTAD_TIPO));

		ParametrosVo parametrosVo = listParametro.stream().filter(param -> param.getValParametro2().equals(codServicio))
				.findAny().orElse(null);

		if (null != parametrosVo)
			resultado = parametrosVo.getValParametro();

		return resultado;
	}

	/**
	 * SP que comprueba si existe facultad.
	 * 
	 * Por seguridad no se expone en el log el numero de cuenta.
	 * 
	 * @param rutCliente
	 * @param rutApoderado
	 * @param numCuenta
	 * @param codigoFacultad
	 * @return
	 * @throws ErrorStoredProcedureException
	 */
	public boolean existeFacultad(String rutCliente, String rutApoderado, String numCuenta, int codigoFacultad)
			throws ErrorStoredProcedureException {
		LOGGER.info("FacultadService existeFacultad: rutCliente[{}] rutApoderado[{}] codigoFacultad[{}]", rutCliente,
				rutApoderado, codigoFacultad);
		Map<String, Object> params = new HashMap<>();

		LOGGER.info("Validación General Validar Poderes Facultad ");

		params.put("p_COD_SERVICIO", codigoFacultad);
		params.put("p_RUT_CLIENTE", rutCliente != null ? rutCliente : "0");
		params.put("p_RUT_APODERADO", rutApoderado != null ? rutApoderado : "0");
		params.put("p_NUM_CTA", numCuenta != null ? numCuenta : "0");

		try {
			portalOrawRepository.existeFacultad(params);

			String codStatusFacultad = (String) params.get(Constantes.PARAMETRO_EXISTE_FACULTAD_SP_OUT_COD_STATUS);
			String codRetornoExisteFacultad = (String) params.get(Constantes.PARAMETRO_EXISTE_FACULTAD_SP_OUT_EXISTE);
			String glsRetornoExisteFacultad = (String) params
					.get(Constantes.PARAMETRO_EXISTE_FACULTAD_SP_OUT_MSG_STATUS);

			LOGGER.info("FacultadService existeFacultad: status[{}] existe[{}] glosa[{}]", codStatusFacultad,
					codRetornoExisteFacultad, glsRetornoExisteFacultad);

			return (codRetornoExisteFacultad != null
					&& Constantes.GLOSA_EXISTE_FACULTAD_SP_EXISTE_SI.equalsIgnoreCase(codRetornoExisteFacultad));

		} catch (SQLException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			throw new ErrorStoredProcedureException();
		}
	}

}
