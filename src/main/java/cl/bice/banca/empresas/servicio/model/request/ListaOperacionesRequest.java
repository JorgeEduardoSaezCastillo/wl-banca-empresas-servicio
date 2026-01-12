package cl.bice.banca.empresas.servicio.model.request;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

/**
 * Request Para consultar las operciones por dia
 * 
 * @author Tinet
 *
 */
public class ListaOperacionesRequest extends BaseRequestEmpresa {

	private String codigoServicio;

	public ListaOperacionesRequest() {
		super();
	}

	public String getCodigoServicio() {
		return codigoServicio;
	}

	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	@Override
	public String toString() {
		return "ListaOperacionesRequest [codigoServicio=" + codigoServicio + ", getCodigoServicio()="
				+ getCodigoServicio() + ", getToken()=" + getToken() + ", getRutEmpresa()=" + getRutEmpresa()
				+ ", getDispositivo()=" + getDispositivo() + ", getRut()=" + getRut() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
