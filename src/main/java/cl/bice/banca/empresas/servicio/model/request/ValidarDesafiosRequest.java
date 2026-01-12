package cl.bice.banca.empresas.servicio.model.request;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidarDesafiosRequest extends BaseRequestEmpresa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6709722025537669111L;

	private String glsTipoDisp;

	private String glsDesafio;

	private String glsTipocliente;
	
    private Map<String, String> mapaDesafio;

	public String getGlsTipoDisp() {
		return glsTipoDisp;
	}

	public void setGlsTipoDisp(String glsTipoDisp) {
		this.glsTipoDisp = glsTipoDisp;
	}

	public String getGlsDesafio() {
		return glsDesafio;
	}

	public void setGlsDesafio(String glsDesafio) {
		this.glsDesafio = glsDesafio;
	}

	public String getGlsTipocliente() {
		return glsTipocliente;
	}

	public void setGlsTipocliente(String glsTipocliente) {
		this.glsTipocliente = glsTipocliente;
	}

	public Map<String, String> getMapaDesafio() {
		return mapaDesafio;
	}

	public void setMapaDesafio(Map<String, String> mapaDesafio) {
		this.mapaDesafio = mapaDesafio;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValidarDesafiosRequest [glsTipoDisp=").append(glsTipoDisp).append(", glsDesafio=")
				.append(glsDesafio).append(", glsTipocliente=").append(glsTipocliente).append(", mapaDesafio=")
				.append(mapaDesafio).append("]");
		return builder.toString();
	}

}
