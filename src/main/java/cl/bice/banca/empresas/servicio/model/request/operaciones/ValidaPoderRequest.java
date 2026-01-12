package cl.bice.banca.empresas.servicio.model.request.operaciones;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import cl.bice.banca.empresas.servicio.model.common.BaseRequestEmpresa;

/**
 * Request del servicio validaPoderEmpresa
 * 
 * @author Cristian Pais (TINET)
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidaPoderRequest extends BaseRequestEmpresa {
	/**
	 * C&oacute;digo de servicio
	 */
	private String codigoServicio;
	/**
	 * numero operacion
	 */
	private String numeroOperacion;
	/**
	 * Rut de mandatarios
	 */
	@JsonIgnore
	private String mandatarios;
	/**
	 * Monto
	 */
	@JsonIgnore
	private String monto;
	/**
	 * Nro cuenta cargo
	 */
	@JsonIgnore
	private String nroCuentaCargo;

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
	 * @return the numeroOperacion
	 */
	public String getNumeroOperacion() {
		return numeroOperacion;
	}

	/**
	 * @param numeroOperacion the numeroOperacion to set
	 */
	public void setNumeroOperacion(String numeroOperacion) {
		this.numeroOperacion = numeroOperacion;
	}

	/**
	 * @return the mandatarios
	 */
	public String getMandatarios() {
		return mandatarios;
	}

	/**
	 * @param mandatarios the mandatarios to set
	 */
	public void setMandatarios(String mandatarios) {
		this.mandatarios = mandatarios;
	}

	/**
	 * @return the monto
	 */
	public String getMonto() {
		return monto;
	}

	/**
	 * @param monto the monto to set
	 */
	public void setMonto(String monto) {
		this.monto = monto;
	}

	/**
	 * @return the nroCuentaCargo
	 */
	public String getNroCuentaCargo() {
		return nroCuentaCargo;
	}

	/**
	 * @param nroCuentaCargo the nroCuentaCargo to set
	 */
	public void setNroCuentaCargo(String nroCuentaCargo) {
		this.nroCuentaCargo = nroCuentaCargo;
	}

}
