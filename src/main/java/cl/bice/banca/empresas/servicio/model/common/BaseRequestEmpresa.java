package cl.bice.banca.empresas.servicio.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Cristian Pais on 27-02-19.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseRequestEmpresa extends BaseRequest {

	/**
	 * Canal.
	 */
	private String canal;

	/**
	 * Rut empresa.
	 */
	private String rutEmpresa;

	/**
	 * Constructor Base.
	 */
	public BaseRequestEmpresa() {
		super();
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseRequestEmpresa [token=");
		builder.append(this.getToken());
		builder.append(", rut=");
		builder.append(this.getRut());
		builder.append(", dispositivo=");
		builder.append(this.getDispositivo());
		builder.append(", canal=");
		builder.append(canal);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Permite obtener el valor del atributo canal.
	 *
	 * @return retorna el valor del atributo canal.
	 */
	public String getCanal() {
		return canal;
	}

	/**
	 * Permite setear el valor del atributo canal.
	 *
	 * @param canal nuevo valor para el atributo canal.
	 */
	public void setCanal(String canal) {
		this.canal = canal;
	}

	/**
	 * Permite obtener el valor del atributo rutEmpresa
	 * 
	 * @return retorna el valor del atributo rutEmpresa
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}

	/**
	 * Permite setear el valor del atributo rutEmpresa
	 * 
	 * @param rutEmpresa nuevo valor para el atributo rutEmpresa
	 */
	public void setRutEmpresa(String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

}
