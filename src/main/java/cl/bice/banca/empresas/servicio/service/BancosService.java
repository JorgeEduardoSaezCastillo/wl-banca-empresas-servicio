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
import cl.bice.banca.empresas.servicio.model.common.Banco;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.repository.PorAdminRepository;
import cl.bice.nominas.bancos.model.CacheBancos;

/**
 * Clase destinada a manejar datos de bancos.
 * 
 * @author Fibacache
 *
 */
@Service
public class BancosService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BancosService.class);

	@Autowired
	@Qualifier("MapCacheBancos")
	CacheBancos cacheBancos;

	@Autowired
	PorAdminRepository porAdminRepository;

	/**
	 * retorna un banco desde la caché
	 * 
	 * @param codBanco
	 * @return
	 */
	public Banco obtenerBanco(String codBanco) {
		LOGGER.info("BancosService obtenerBanco");
		if (cacheBancos.getMapCache().isEmpty()) {
			cargarBancos();
		}

		if (!cacheBancos.getMapCache().isEmpty()) {
			return (Banco) cacheBancos.getMapCache().get(codBanco);
		} else {
			LOGGER.info("ERROR: cache de bancos vacío");
			return null;
		}

	}

	/**
	 * Carga bancos desde el SP
	 */
	public void cargarBancos() {
		Map<String, Object> mapCacheBancos = new HashMap<>();

		Map<String, Object> params = new HashMap<>();
		params.put("pi_mdp", String.valueOf(1));
		params.put("cod_estado", null);
		params.put("bancos", null);

		List<Map<String, Object>> salidaSP = new ArrayList<>();

		try {
			porAdminRepository.obtenerBancos(params);
			salidaSP = (List<Map<String, Object>>) params.get("bancos");

			if (params.get("cod_estado") == null || !"0".equals(params.get("cod_estado").toString().trim())) {
				throw new BancaEmpresasException();
			} else {
				Banco banco;
				for (Map<String, Object> map : salidaSP) {
					banco = new Banco();
					String codBanco = String.valueOf(map.get("COD_BANCO"));
					banco.setCodBanco(codBanco);
					banco.setCodSwift(String.valueOf(map.get("COD_SWIFT")));
					banco.setGlsBanco(String.valueOf(map.get("GLS_BANCOCORTA")));
					banco.setGlsNomBanco(String.valueOf(map.get("GLS_NOM_BANCO")));
					banco.setNomBanco(String.valueOf(map.get("NOM_BANCO")));
					banco.setRutBanco(String.valueOf(map.get("RUT_BANCO")));

					if (cacheBancos.getMapCache().get(codBanco) == null) {
						cacheBancos.getMapCache().put(codBanco, banco);
					}
				}
			}

		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
		}
	}
}
