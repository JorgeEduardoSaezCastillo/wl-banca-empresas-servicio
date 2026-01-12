package cl.bice.banca.empresas.servicio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.response.ParametrosValidacion;
import cl.bice.banca.empresas.servicio.soap.ClienteOperacionNomina;
import cl.bice.nominas.ws.ConsultaParametrosOut;
import cl.bice.nominas.ws.ParametrosVo;

@Service
public class ParametrosValidacionService {

    /**
     * Logger.
     */
    private transient Logger log = LoggerFactory.getLogger(ParametrosValidacionService.class);

    /**
     * Propiedades Externas.
     */
    @Autowired
    private Properties propiedadesExterna;
    
    /**
	 * Cliente para operaciones del ws NominasPagosWs
	 */
	@Autowired
	@Qualifier("ClienteOperacionNomina")
	ClienteOperacionNomina clienteOperacionNomina;

    /**
     * Obtiene parametros de validacion.
     */
    public List<ParametrosValidacion> obtienePrametrosValidacion(String parametro, String tipoParametro, String rut) {

        log.info("[INI][{}] parametro: {}, tipo parametro: {}", rut, parametro, tipoParametro);

        RestTemplate restTemplate = new RestTemplate();

        String urlServicio = propiedadesExterna.getProperty("servicios.url.parametros.validacion");

        StringBuilder parametros = new StringBuilder();
        parametros.append("dispositivo=0");
        parametros.append("&rut=" + rut);
        parametros.append("&token=0");
        parametros.append("&parametro=" + parametro + "&tipo=" + tipoParametro + "&estado=1");

        urlServicio = urlServicio + parametros.toString();

        List<ParametrosValidacion> listaParametros = null;

        try {
            ResponseEntity<List<ParametrosValidacion>> response = restTemplate.exchange(urlServicio, HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<ParametrosValidacion>>() {
                    });

            listaParametros = response.getBody();
            
            log.info("[{}] listaParametros: {}", rut, listaParametros);

        } catch (Exception ex) {
            log.error("[FIN][Exception][{}]parametro: {}, tipo parametro: {}, exception: {}", rut, parametro,
                    tipoParametro, ex);
            return null;
        }

        log.info("[FIN][{}] parametro: {}, tipo parametro: {}", rut, parametro, tipoParametro);

        return listaParametros;
    }
    
    /**
     * Retorna objeto parametros validación con los para metros de la tbl_validacion dado un parametro y su tipo
     * 
     * @param rut
     * @return
     */
    public  ParametrosValidacion obtenerParametroValidacion(String rut, String parametro, String tipoParametro) {
        log.info("[INI][{}]", rut);
        
        ParametrosValidacion parametroValidacion = new ParametrosValidacion();
        List<ParametrosValidacion> pv = obtienePrametrosValidacion(parametro, tipoParametro, rut);
        if(pv != null && !pv.isEmpty()) {
        	parametroValidacion = pv.get(0); 
        }

        log.info("[FIN][{}] Parámetros validación: {}", rut, pv) ;

        return parametroValidacion;
    }
    
	/**
	 * @param data
	 * @return
	 */
	public String getParamValidacion(String nomParam,String nomTipo, String valParametro) throws BancaEmpresasException{
		
		log.info("ParametrosValidacionService.getParamValidacion nomParam: ["+ nomParam + "] nomTipo ["+nomTipo+"]");
		String retorno = "";
		try{		
			ConsultaParametrosOut consultaParametrosNomina = clienteOperacionNomina.consultaParamValidacion(nomParam, nomTipo);
			List<ParametrosVo> parametrosNomina = consultaParametrosNomina.getParametrosVo();
			if(!parametrosNomina.isEmpty()){
				ParametrosVo parametrosVo = parametrosNomina.get(0);
				
				switch(valParametro) {
				case Constantes.VAL_PARAMETRO:
					retorno = retorno + parametrosVo.getValParametro();
					break;
				case Constantes.VAL_PARAMETRO2:
					retorno = retorno + parametrosVo.getValParametro2();
					break;
				case Constantes.VAL_PARAMETRO3:
					retorno = retorno + parametrosVo.getValParametro3();
					break;
				case Constantes.VAL_PARAMETRO4:
					retorno = retorno + parametrosVo.getValParametro4();
					break;
				}
			}
		}catch(BancaEmpresasException e){
			e.printStackTrace();
		}
		log.info("ParametrosValidacionService.getParamValidacion [{}]: [{}]", valParametro, retorno);
		return retorno;
	}
	
	/**
	 * @param data
	 * @return
	 */
	public List<ParametrosVo> getParametrosValidacion(String nomParam,String nomTipo) throws BancaEmpresasException{
		
		log.info("ParametrosValidacionService.getParamValidacion nomParam: ["+ nomParam + "] nomTipo ["+nomTipo+"]");
		List<ParametrosVo> parametrosNomina = new ArrayList<>();
		try{		
			ConsultaParametrosOut consultaParametrosNomina = clienteOperacionNomina.consultaParamValidacion(nomParam, nomTipo);
			parametrosNomina = consultaParametrosNomina.getParametrosVo();
		}catch(BancaEmpresasException e){
			e.printStackTrace();
		}
		log.info("ParametrosValidacionService.getParamValidacion [{}]: ", parametrosNomina);
		return parametrosNomina;
	}

}
