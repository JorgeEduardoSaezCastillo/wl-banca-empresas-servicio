package cl.bice.banca.empresas.servicio.model.cartolaprovisoria;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * clase que representa el movimiento de la cartola provisoria
 * @author Marco Bello
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)

public class MovCartola {


	@JsonProperty("MOVIMIENTO")
	private List<DetalleMovimiento> listaDetalleMovimiento;

	public List<DetalleMovimiento> getListaDetalleMovimiento() {
		return listaDetalleMovimiento;
	}

	public void setListaDetalleMovimiento(List<DetalleMovimiento> listaDetalleMovimiento) {
		this.listaDetalleMovimiento = listaDetalleMovimiento;
	}

	
	

}
