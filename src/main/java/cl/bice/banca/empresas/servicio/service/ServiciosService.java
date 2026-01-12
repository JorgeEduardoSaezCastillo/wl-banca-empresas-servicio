package cl.bice.banca.empresas.servicio.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.model.common.Servicio;
import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.model.response.ListarServicios;
import cl.bice.banca.empresas.servicio.model.response.base.ResponseBase;
import cl.bice.banca.empresas.servicio.model.response.base.Respuesta;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;
import cl.bice.nominas.ws.ParametrosVo;

/**
 * Clase para consultar servicios (nro servicio, glosa servicio) a través del
 * servicio de nominas.
 * 
 * @author Fibacache
 *
 */
@Service
public class ServiciosService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiciosService.class);

	/**
	 * Propiedades Externas.
	 */
	@Autowired
	Properties propiedadesExterna;

	@Autowired
	ConsultarParametrosService consultarParametrosService;

	@Autowired
	EstadoService estadoService;

	/**
	 * Obtiene lista de servicios.
	 * 
	 * @param estado
	 * @return
	 */
	public ResponseBase obtenerListaServicios(Estado estado) {
		ResponseBase response = new ResponseBase();
		response.setEstado(estado);

		ListarServicios listarServicios = new ListarServicios();
		List<Servicio> servicios = new ArrayList<>();

		try {
			List<ParametrosVo> listaParametrosVo = consultarParametrosService.consultaParametro("BANCAMOBILEEMPRESA",
					"SERVICIOS");
			boolean nominasDiferidasAgrupadas = false;

			for (ParametrosVo paramsVo : listaParametrosVo) {
				Servicio servicio = new Servicio();

				// Agrupo las tres posibles glosas y codigo de servicios de nominas diferidas en
				// un solo elemento
				if ((Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_DIVIDENDO_ACCIONES
						.equals(paramsVo.getValParametro())
						|| Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_PROVEEDORES
								.equals(paramsVo.getValParametro())
						|| Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES
								.equals(paramsVo.getValParametro()))) {

					if (!nominasDiferidasAgrupadas) {
						String glosaCodigoServicio = propiedadesExterna.getProperty(
								"servicios.resumen.operaciones.glosa.cod.servicio." + paramsVo.getValParametro());
						servicio.setGlosaServicio(glosaCodigoServicio);
						servicio.setCodigoServicio("200");
						servicios.add(servicio);
						nominasDiferidasAgrupadas = true;
					}
				} else {
					servicio.setCodigoServicio(paramsVo.getValParametro());
					servicio.setGlosaServicio(paramsVo.getValParametro2());
					servicios.add(servicio);
				}
			}

		} catch (BancaEmpresasException e) {
			LOGGER.error(Constantes.PARAMETRO_EMPRESAS_LOG_ERROR, e);
			estadoService.setErrorEstado(EstadoEnum.ERROR_E001_EMPRESAS_LISTAR_SERVICIOS, estado);
		}

		listarServicios.setServicios(servicios);
		Respuesta respuesta = new Respuesta();
		respuesta.setAdditionalProperty("Servicios", listarServicios);
		response.setRespuesta(respuesta);

		return response;
	}

	/**
	 * Como nominas diferidas tiene 3 codigo de servicio distintos se optó por
	 * manjearse solo con uno de ellos. Este método recibe un código de servicio y
	 * si es uno de los 3 códigos de servicios posibles de de nominas diferidas y
	 * retorna siempre el mismo: 200 En caso de recibir un código de servicio
	 * distinto de nominas diferidas retorna el mismo código de servicio que
	 * recibió.
	 * 
	 * @param codServicio
	 * @return
	 */
	public String filtrarCodigoServicio(String codServicio) {
		if (Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_DIVIDENDO_ACCIONES.equals(codServicio)
				|| Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_PROVEEDORES.equals(codServicio)
				|| Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES.equals(codServicio)) {
			return Constantes.CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES;
		}
		return codServicio;
	}
}
