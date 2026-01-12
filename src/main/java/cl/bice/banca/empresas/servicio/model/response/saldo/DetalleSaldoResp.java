package cl.bice.banca.empresas.servicio.model.response.saldo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contiene los datos del detalle del saldo.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
public class DetalleSaldoResp {

	/**
	 * Descripcion.
	 */
	private String nombre;

	/**
	 * Lista de saldos.
	 */
	private List<DetalleResp> saldos = new ArrayList<>();

	/**
	 * Permite obtener el valor del atributo saldos.
	 *
	 * @return retorna el valor del atributo saldos.
	 */
	public final List<DetalleResp> getSaldos() {
		return saldos;
	}

	/**
	 * Permite setear el valor del atributo saldos.
	 *
	 * @param newsaldos nuevo valor para el atributo saldos.
	 */
	public final void setSaldos(final List<DetalleResp> newsaldos) {
		this.saldos = newsaldos;
	}

	/**
	 * Permite obtener el valor del atributo nombre.
	 *
	 * @return retorna el valor del atributo nombre.
	 */
	public final String getNombre() {
		return nombre;
	}

	/**
	 * Permite setear el valor del atributo nombre.
	 *
	 * @param newnombre nuevo valor para el atributo nombre.
	 */
	public final void setNombre(final String newnombre) {
		this.nombre = newnombre;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "DetalleSaldoResp [nombre=" + nombre + ", saldos=" + saldos + "]";
	}

}
