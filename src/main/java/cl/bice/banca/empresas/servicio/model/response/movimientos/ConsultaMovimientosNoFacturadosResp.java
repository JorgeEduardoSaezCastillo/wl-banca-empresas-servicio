package cl.bice.banca.empresas.servicio.model.response.movimientos;

import java.util.List;

import cl.bice.banca.empresas.servicio.model.common.EstadoResp;

/**
 * Clase que contiene los datos de la respuesta del servicio rest de consulta de
 * movimientos no facturados.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
public class ConsultaMovimientosNoFacturadosResp {

	/**
	 * Estado de la solicitud.
	 */
	private EstadoResp estado;

	/**
	 * Lista de movimientos nacionales.
	 */
	private List<MovimientosNoFacturadosResp> movimientosNoFacturadosNacionales;

	/**
	 * Lista de movimientos extranjeros.
	 */
	private List<MovimientosNoFacturadosResp> movimientosNoFacturadosExtranjeros;

	/**
	 * Permite obtener el valor del atributo estado.
	 *
	 * @return retorna el valor del atributo estado.
	 */
	public final EstadoResp getEstado() {
		return estado;
	}

	/**
	 * Permite setear el valor del atributo estado.
	 *
	 * @param newEstado nuevo valor para el atributo estado.
	 */
	public final void setEstado(final EstadoResp newEstado) {
		this.estado = newEstado;
	}

	/**
	 * Permite obtener el valor del atributo movimientosNoFacturadosNacionales.
	 *
	 * @return retorna el valor del atributo movimientosNoFacturadosNacionales.
	 */
	public final List<MovimientosNoFacturadosResp> getMovimientosNoFacturadosNacionales() {
		return movimientosNoFacturadosNacionales;
	}

	/**
	 * Permite setear el valor del atributo movimientosNoFacturadosNacionales.
	 *
	 * @param newmovimientosNoFacturadosNacionales nuevo valor para el atributo
	 *                                             movimientosNoFacturadosNacionales.
	 */
	public final void setMovimientosNoFacturadosNacionales(
			final List<MovimientosNoFacturadosResp> newmovimientosNoFacturadosNacionales) {
		this.movimientosNoFacturadosNacionales = newmovimientosNoFacturadosNacionales;
	}

	/**
	 * Permite obtener el valor del atributo movimientosNoFacturadosExtranjeros.
	 *
	 * @return retorna el valor del atributo movimientosNoFacturadosExtranjeros.
	 */
	public final List<MovimientosNoFacturadosResp> getMovimientosNoFacturadosExtranjeros() {
		return movimientosNoFacturadosExtranjeros;
	}

	/**
	 * Permite setear el valor del atributo movimientosNoFacturadosExtranjeros.
	 *
	 * @param newmovimientosNoFacturadosExtranjeros nuevo valor para el atributo
	 *                                              movimientosNoFacturadosExtranjeros.
	 */
	public final void setMovimientosNoFacturadosExtranjeros(
			final List<MovimientosNoFacturadosResp> newmovimientosNoFacturadosExtranjeros) {
		this.movimientosNoFacturadosExtranjeros = newmovimientosNoFacturadosExtranjeros;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "ConsultaMovimientosNoFacturadosResp [estado=" + estado + ", movimientosNoFacturadosNacionales="
				+ movimientosNoFacturadosNacionales + ", movimientosNoFacturadosExtranjeros="
				+ movimientosNoFacturadosExtranjeros + "]";
	}

}
