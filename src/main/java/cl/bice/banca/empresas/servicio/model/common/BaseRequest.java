package cl.bice.banca.empresas.servicio.model.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by lbasso on 01-06-17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseRequest {

	/**
	 * Token.
	 */
	private String token;

	/**
	 * Rut.
	 */
	private String rut;

	/**
	 * Dispositivo.
	 */
	private String dispositivo;

	/**
	 * Direccion IP desde donde se origina la llamada.
	 */
	@JsonIgnore
	private String ip;

	/**
	 * Session ID.
	 */
	@JsonIgnore
	private String sessionID;

	/**
	 * Origen de la llamada.
	 */
	@JsonIgnore
	private String origenLlamada;
	
	@JsonIgnore
	private String serverName;

	/**
	 * Constructor Base.
	 */
	public BaseRequest() {
		super();
	}

    public BaseRequest(String token, String rut, String dispositivo) {
		super();
		this.token = token;
		this.rut = rut;
		this.dispositivo = dispositivo;
	}
    
	/**
	 * Permite obtener el valor del atributo token.
	 *
	 * @return el valor token.
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * Permite settear el valor del atributo token.
	 *
	 * @param token nuevo valor para el atributo token.
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Permite obtener el valor del atributo rut.
	 *
	 * @return el valor rut.
	 */
	public String getRut() {
		return this.rut;
	}

	/**
	 * Permite settear el valor del atributo rut.
	 *
	 * @param rut nuevo valor para el atributo rut.
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}

	/**
	 * Permite obtener el valor del atributo dispositivo.
	 *
	 * @return el valor dispositivo.
	 */
	public String getDispositivo() {
		return this.dispositivo;
	}

	/**
	 * Permite settear el valor del atributo dispositivo.
	 *
	 * @param dispositivo nuevo valor para el atributo dispositivo.
	 */
	public void setDispositivo(String dispositivo) {
		this.dispositivo = dispositivo;
	}

	/**
	 * Permite obtener el valor del atributo ip.
	 *
	 * @return retorna el valor del atributo ip.
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Permite setear el valor del atributo ip.
	 *
	 * @param ip nuevo valor para el atributo ip.
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * Permite obtener el valor del atributo sessionID.
	 *
	 * @return retorna el valor del atributo sessionID.
	 */
	public String getSessionID() {
		return sessionID;
	}

	/**
	 * Permite setear el valor del atributo sessionID.
	 *
	 * @param sessionID nuevo valor para el atributo sessionID.
	 */
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BaseRequest [token=");
		builder.append(token);
		builder.append(", rut=");
		builder.append(rut);
		builder.append(", dispositivo=");
		builder.append(dispositivo);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Permite obtener el valor del atributo origenLlamada.
	 *
	 * @return retorna el valor del atributo origenLlamada.
	 */
	public String getOrigenLlamada() {
		return origenLlamada;
	}

	/**
	 * Permite setear el valor del atributo origenLlamada.
	 *
	 * @param origenLlamada nuevo valor para el atributo origenLlamada.
	 */
	public void setOrigenLlamada(String origenLlamada) {
		this.origenLlamada = origenLlamada;
	}

	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * @param serverName the serverName to set
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

}
