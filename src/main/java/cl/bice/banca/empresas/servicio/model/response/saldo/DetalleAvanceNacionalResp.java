package cl.bice.banca.empresas.servicio.model.response.saldo;

/**
 * Contiene los datos del cupo disponible para avance en moneda nacional.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
public class DetalleAvanceNacionalResp {

	/**
	 * Cupo disponible para avance sin formatear.
	 */
	private String cupoDisponible;

	/**
	 * Cupo disponible para avence formateado.
	 */
	private String cupoDisponibleFormateado;

	/**
	 * Permite obtener el valor del atributo cupoDisponible.
	 *
	 * @return retorna el valor del atributo cupoDisponible.
	 */
	public String getCupoDisponible() {
		return cupoDisponible;
	}

	/**
	 * Permite setear el valor del atributo cupoDisponible.
	 *
	 * @param newcupoDisponible nuevo valor para el atributo cupoDisponible.
	 */
	public void setCupoDisponible(final String newcupoDisponible) {
		this.cupoDisponible = newcupoDisponible;
	}

	/**
	 * Permite obtener el valor del atributo cupoDisponibleFormateado.
	 *
	 * @return retorna el valor del atributo cupoDisponibleFormateado.
	 */
	public String getCupoDisponibleFormateado() {
		return cupoDisponibleFormateado;
	}

	/**
	 * Permite setear el valor del atributo cupoDisponibleFormateado.
	 *
	 * @param newcupoDisponibleFormateado nuevo valor para el atributo
	 *                                    cupoDisponibleFormateado.
	 */
	public void setCupoDisponibleFormateado(final String newcupoDisponibleFormateado) {
		this.cupoDisponibleFormateado = newcupoDisponibleFormateado;
	}
}
