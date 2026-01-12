package cl.bice.banca.empresas.servicio.model.request.ms.poderes;

import com.fasterxml.jackson.annotation.JsonInclude;



/**
 * Request para el microservicio de validacion de poderes
 * @author Marco Bello
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PoderRequest  {
	

	/**
	 * Codigo moneda con la cual se realiza la operacion
	 */
	private String codigoMoneda;
	
	
	/**
	 * Código servicio
	 */
	private String codigoServicio;

	
	/**
	 * Código de la facultad en Legal suite
	 */
	private String facultad;

	
	/**
	 * Monto
	 */
	private String monto;
	

	/**
	 * Numero operacion en progreso
	 */
	private String numOperProg;

	
	/**
	 * Numero cuenta corriente
	 */
	private String numeroCuenta;

	
	/**
	 * Rut apoderado
	 */
	private String rutApoderado;

	
	/**
	 * Rut cliente
	 */
	private String rutCliente;
	
	
	/**
	 * Dato firma
	 */
	private String datoFirma;	


	public String getCodigoMoneda() {
		return codigoMoneda;
	}


	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}


	public String getCodigoServicio() {
		return codigoServicio;
	}


	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}


	public String getFacultad() {
		return facultad;
	}


	public void setCodigoFacultad(String facultad) {
		this.facultad = facultad;
	}


	public String getMonto() {
		return monto;
	}


	public void setMonto(String monto) {
		this.monto = monto;
	}


	public String getNumOperProg() {
		return numOperProg;
	}


	public void setNumOperProg(String numOperProg) {
		this.numOperProg = numOperProg;
	}


	public String getNumeroCuenta() {
		return numeroCuenta;
	}


	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}


	public String getRutApoderado() {
		return rutApoderado;
	}


	public void setRutApoderado(String rutApoderado) {
		this.rutApoderado = rutApoderado;
	}


	public String getRutCliente() {
		return rutCliente;
	}


	public void setRutCliente(String rutCliente) {
		this.rutCliente = rutCliente;
	}


	public String getDatoFirma() {
		return datoFirma;
	}


	public void setDatoFirma(String datoFirma) {
		this.datoFirma = datoFirma;
	}
	
}
