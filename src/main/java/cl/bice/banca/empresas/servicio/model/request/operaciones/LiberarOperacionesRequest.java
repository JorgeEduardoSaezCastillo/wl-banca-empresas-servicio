package cl.bice.banca.empresas.servicio.model.request.operaciones;

import java.util.List;

import cl.bice.banca.empresas.servicio.model.request.ValidarDesafiosRequest;

/**
 * Request del servicio liberarOperaciones
 * 
 * @author Alexis Ganga (TINET)
 *
 */
public class LiberarOperacionesRequest extends ValidarDesafiosRequest {
	/**
	 * Código servicio
	 */
	private String codigoServicio;
	/**
	 * Tipo de las operaciones
	 */
	private String tipo;	
	/**
	 * Nombre apoderado
	 */
	private String nombreApoderado;
	/**
	 * Lista de operaciones
	 */
	private List<String> listaOperaciones;

	/**
	 * Dispositivo firma
	 */
	private String dispositivoFirma;

	/**
	 * @return the codigoServicio
	 */
	public String getCodigoServicio() {
		return codigoServicio;
	}

	/**
	 * @param codigoServicio the codigoServicio to set
	 */
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the nombreApoderado
	 */
	public String getNombreApoderado() {
		return nombreApoderado;
	}

	/**
	 * @param nombreApoderado the nombreApoderado to set
	 */
	public void setNombreApoderado(String nombreApoderado) {
		this.nombreApoderado = nombreApoderado;
	}

	/**
	 * @return the listaOperaciones
	 */
	public List<String> getListaOperaciones() {
		return listaOperaciones;
	}

	/**
	 * @param listaOperaciones the listaOperaciones to set
	 */
	public void setListaOperaciones(List<String> listaOperaciones) {
		this.listaOperaciones = listaOperaciones;
	}

	/**
	 * @return the dispositivoFirma
	 */
	public String getDispositivoFirma() {
		return dispositivoFirma;
	}

	/**
	 * @param dispositivoFirma the dispositivoFirma to set
	 */
	public void setDispositivoFirma(String dispositivoFirma) {
		this.dispositivoFirma = dispositivoFirma;
	}

}
