package cl.bice.banca.empresas.servicio.model.common;

import cl.bice.banca.empresas.servicio.model.response.Estado;

public class ValidadorUtil {

	private Estado estado;

	private boolean resultado;

	private Object objetoRespuesta;

	public ValidadorUtil(boolean resultado) {
		super();
		this.resultado = resultado;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public boolean isResultado() {
		return resultado;
	}

	public void setResultado(boolean resultado) {
		this.resultado = resultado;
	}

	public Object getObjetoRespuesta() {
		return objetoRespuesta;
	}

	public void setObjetoRespuesta(Object objetoRespuesta) {
		this.objetoRespuesta = objetoRespuesta;
	}

	@Override
	public String toString() {
		return "ValidadorUtil [estado=" + estado + ", resultado=" + resultado + ", objetoRespuesta=" + objetoRespuesta
				+ "]";
	}

}
