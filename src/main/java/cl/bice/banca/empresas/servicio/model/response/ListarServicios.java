package cl.bice.banca.empresas.servicio.model.response;

import java.util.List;

import cl.bice.banca.empresas.servicio.model.common.Servicio;

/**
 * Response del servicio listar servicios
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class ListarServicios {
	/**
	 * Lista de servicios
	 */
	private List<Servicio> servicios;

	/**
	 * @return the servicios
	 */
	public List<Servicio> getServicios() {
		return servicios;
	}

	/**
	 * @param servicios the servicios to set
	 */
	public void setServicios(List<Servicio> servicios) {
		this.servicios = servicios;
	}
}
