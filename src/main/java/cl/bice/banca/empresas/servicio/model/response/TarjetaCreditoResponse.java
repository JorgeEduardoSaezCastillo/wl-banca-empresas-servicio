package cl.bice.banca.empresas.servicio.model.response;

import java.util.List;

import cl.bice.banca.empresas.servicio.model.common.InfoTarjeta;

/**
 * Informacion que tiene toda la informacion de la tarjeta de credito.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public class TarjetaCreditoResponse {

	private List<InfoTarjeta> tarjetas;

	/**
	 * Permite obtener el valor del atributo tarjetas.
	 *
	 * @return retorna el valor del atributo tarjetas.
	 */
	public List<InfoTarjeta> getTarjetas() {
		return tarjetas;
	}

	/**
	 * Permite setear el valor del atributo tarjetas.
	 *
	 * @param tarjetas nuevo valor para el atributo tarjetas.
	 */
	public void setTarjetas(List<InfoTarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

}
