package cl.bice.banca.empresas.servicio.model.request.operaciones;

import java.util.List;

import cl.bice.banca.empresas.servicio.model.request.ValidarDesafiosRequest;

/**
 * Request del servicio aprobarOperaciones
 * 
 * @author Cristian Pais (TINET)
 *
 */
public class AprobarOperacionesRequest extends ValidarDesafiosRequest {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1125835827555376600L;
	/**
	 * Código servicio
	 */
	private String codigoServicio;
	/**
	 * Nombre apoderado
	 */
	private String nombreApoderado;
	/**
	 * Dispositivo firma
	 */
	private String dispositivoFirma;
	/**
	 * Lista de operaciones
	 */
	private List<String> listaOperaciones;

	/**
	 * ID transaccion
	 */
	private String idTransaccion;
	
	/**
	 * ID Sesion
	 */
	private String idSesion;

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
	 * @return the idTransaccion
	 */
	public String getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * @param idTransaccion the idTransaccion to set
	 */
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * @return the idSesion
	 */
	public String getIdSesion() {
		return idSesion;
	}

	/**
	 * @param idSesion the idSesion to set
	 */
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

}
