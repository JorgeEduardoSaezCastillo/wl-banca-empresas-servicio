package cl.bice.banca.empresas.servicio.model.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Respuesta del servicio Tarjeta.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class RespuestaTarjeta implements Serializable {

	/**
	 * Tarjeta.
	 */
	private List<Tarjeta> tarjeta = new ArrayList<>();

	/**
	 * Serie de la clase.
	 */
	private static final long serialVersionUID = 4103055305754415128L;

	/**
	 * Permite obtener el valor del atributo tarjeta.
	 *
	 * @return retorna el valor del atributo tarjeta.
	 */
	public List<Tarjeta> getTarjeta() {
		return tarjeta;
	}

	/**
	 * Permite setear el valor del atributo tarjeta.
	 *
	 * @param tarjeta nuevo valor para el atributo tarjeta.
	 */
	public void setTarjeta(List<Tarjeta> tarjeta) {
		this.tarjeta = tarjeta;
	}

}
