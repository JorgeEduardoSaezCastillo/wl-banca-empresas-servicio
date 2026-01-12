package cl.bice.banca.empresas.servicio.model.nominas;

import java.util.List;

/**
 * Clase que representa a la nomina en linea.
 *
 * @author Cristian Pais (TINet)
 * @version 1.0
 */
public class NominaEnLinea {

	/**
	 * Identificador de la nomina.
	 */
	private int codNomina;

	/**
	 * Identificador del tipo de nomina.
	 */
	private int codTipoNomina;

	/**
	 * Glosa del tipo de nomina.
	 */
	private String tipoNomina;

	/**
	 * Cuenta a la que se realizara el cargo.
	 */
	private long cuentaCargo;

	/**
	 * Fecha de carga del archivo.
	 */
	private String fechaCarga;

	/**
	 * Fecha programada para el pago.
	 */
	private String fechaPago;

	/**
	 * Monto total de la nomina.
	 */
	private long monto;

	/**
	 * Nombre del archivo.
	 */
	private String nomArchivo;

	/**
	 * Descripcion.
	 */
	private String referencia;

	/**
	 * Rut de la empresa.
	 */
	private String rutEmpresa;

	/**
	 * Nombre de la empresa.
	 */
	private String nombreEmpresa;

	/**
	 * Secuencia usada para el nombre del archivo a enviar a CCA.
	 */
	private String secuenciaCCA;

	/**
	 * Estado actual de la nomina.
	 */
	private EstadoNomina estado;

	/**
	 * Lista del resumen de medios de pago.
	 */
	private List resumenMediosPago;

	/**
	 * Total de pagos a realizar.
	 */
	private int totalPagos;

	/**
	 * Identificador de la operacion en progreso.
	 */
	private long numOperProg;

	/**
	 * Nombre de la empresa.
	 */
	private String mailOrdenante;

	/**
	 * Historico de estados de la nomina.
	 */
	private List estados;

	/**
	 * Permite obtener el valor del atributo estado.
	 *
	 * @return retorna el valor del atributo estado.
	 */
	public EstadoNomina getEstado() {
		return estado;
	}

	/**
	 * Permite setear el valor del atributo estado.
	 *
	 * @param estado nuevo valor para el atributo estado.
	 */
	public void setEstado(final EstadoNomina estado) {
		this.estado = estado;
	}

	/**
	 * Permite obtener el valor del atributo codNomina.
	 *
	 * @return retorna el valor del atributo codNomina.
	 */
	public int getCodNomina() {
		return codNomina;
	}

	/**
	 * Permite setear el valor del atributo codNomina.
	 *
	 * @param codNomina nuevo valor para el atributo codNomina.
	 */
	public void setCodNomina(final int codNomina) {
		this.codNomina = codNomina;
	}

	/**
	 * Permite obtener el valor del atributo codTipoNomina.
	 *
	 * @return retorna el valor del atributo codTipoNomina.
	 */
	public int getCodTipoNomina() {
		return codTipoNomina;
	}

	/**
	 * Permite setear el valor del atributo codTipoNomina.
	 *
	 * @param codTipoNomina nuevo valor para el atributo codTipoNomina.
	 */
	public void setCodTipoNomina(final int codTipoNomina) {
		this.codTipoNomina = codTipoNomina;
	}

	/**
	 * Permite obtener el valor del atributo cuentaCargo.
	 *
	 * @return retorna el valor del atributo cuentaCargo.
	 */
	public long getCuentaCargo() {
		return cuentaCargo;
	}

	/**
	 * Permite setear el valor del atributo cuentaCargo.
	 *
	 * @param cuentaCargo nuevo valor para el atributo cuentaCargo.
	 */
	public void setCuentaCargo(final long cuentaCargo) {
		this.cuentaCargo = cuentaCargo;
	}

	/**
	 * Permite obtener el valor del atributo fechaCarga.
	 *
	 * @return retorna el valor del atributo fechaCarga.
	 */
	public String getFechaCarga() {
		return fechaCarga;
	}

	/**
	 * Permite setear el valor del atributo fechaCarga.
	 *
	 * @param fechaCarga nuevo valor para el atributo fechaCarga.
	 */
	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	/**
	 * Permite obtener el valor del atributo fechaPago.
	 *
	 * @return retorna el valor del atributo fechaPago.
	 */
	public String getFechaPago() {
		return fechaPago;
	}

	/**
	 * Permite setear el valor del atributo fechaPago.
	 *
	 * @param fechaPago nuevo valor para el atributo fechaPago.
	 */
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	/**
	 * Permite obtener el valor del atributo monto.
	 *
	 * @return retorna el valor del atributo monto.
	 */
	public long getMonto() {
		return monto;
	}

	/**
	 * Permite setear el valor del atributo monto.
	 *
	 * @param monto nuevo valor para el atributo monto.
	 */
	public void setMonto(final long monto) {
		this.monto = monto;
	}

	/**
	 * Permite obtener el valor del atributo nomArchivo.
	 *
	 * @return retorna el valor del atributo nomArchivo.
	 */
	public String getNomArchivo() {
		return nomArchivo;
	}

	/**
	 * Permite setear el valor del atributo nomArchivo.
	 *
	 * @param nomArchivo nuevo valor para el atributo nomArchivo.
	 */
	public void setNomArchivo(final String nomArchivo) {
		this.nomArchivo = nomArchivo;
	}

	/**
	 * Permite obtener el valor del atributo referencia.
	 *
	 * @return retorna el valor del atributo referencia.
	 */
	public String getReferencia() {
		return referencia;
	}

	/**
	 * Permite setear el valor del atributo referencia.
	 *
	 * @param referencia nuevo valor para el atributo referencia.
	 */
	public void setReferencia(final String referencia) {
		this.referencia = referencia;
	}

	/**
	 * Permite obtener el valor del atributo rutEmpresa.
	 *
	 * @return retorna el valor del atributo rutEmpresa.
	 */
	public String getRutEmpresa() {
		return rutEmpresa;
	}

	/**
	 * Permite setear el valor del atributo rutEmpresa.
	 *
	 * @param rutEmpresa nuevo valor para el atributo rutEmpresa.
	 */
	public void setRutEmpresa(final String rutEmpresa) {
		this.rutEmpresa = rutEmpresa;
	}

	/**
	 * Permite obtener el valor del atributo secuenciaCCA.
	 *
	 * @return retorna el valor del atributo secuenciaCCA.
	 */
	public String getSecuenciaCCA() {
		return secuenciaCCA;
	}

	/**
	 * Permite setear el valor del atributo secuenciaCCA.
	 *
	 * @param secuenciaCCA nuevo valor para el atributo secuenciaCCA.
	 */
	public void setSecuenciaCCA(final String secuenciaCCA) {
		this.secuenciaCCA = secuenciaCCA;
	}

	/**
	 * Permite obtener el valor del atributo resumenMediosPago.
	 *
	 * @return retorna el valor del atributo resumenMediosPago.
	 */
	public List getResumenMediosPago() {
		return resumenMediosPago;
	}

	/**
	 * Permite setear el valor del atributo resumenMediosPago.
	 *
	 * @param resumenMediosPago nuevo valor para el atributo resumenMediosPago.
	 */
	public void setResumenMediosPago(List resumenMediosPago) {
		this.resumenMediosPago = resumenMediosPago;
	}

	/**
	 * Permite obtener el valor del atributo tipoNomina.
	 *
	 * @return retorna el valor del atributo tipoNomina.
	 */
	public String getTipoNomina() {
		return tipoNomina;
	}

	/**
	 * Permite setear el valor del atributo tipoNomina.
	 *
	 * @param tipoNomina nuevo valor para el atributo tipoNomina.
	 */
	public void setTipoNomina(final String tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	/**
	 * Permite obtener el valor del atributo totalPagos.
	 *
	 * @return retorna el valor del atributo totalPagos.
	 */
	public int getTotalPagos() {
		return totalPagos;
	}

	/**
	 * Permite setear el valor del atributo totalPagos.
	 *
	 * @param totalPagos nuevo valor para el atributo totalPagos.
	 */
	public void setTotalPagos(final int totalPagos) {
		this.totalPagos = totalPagos;
	}

	/**
	 * Permite obtener el valor del atributo estados.
	 *
	 * @return retorna el valor del atributo estados.
	 */
	public List getEstados() {
		return estados;
	}

	/**
	 * Permite setear el valor del atributo estados.
	 *
	 * @param estados nuevo valor para el atributo estados.
	 */
	public void setEstados(List estados) {
		this.estados = estados;
	}

	/**
	 * Permite obtener el valor del atributo nombreEmpresa.
	 *
	 * @return retorna el valor del atributo nombreEmpresa.
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * Permite setear el valor del atributo nombreEmpresa.
	 *
	 * @param nombreEmpresa nuevo valor para el atributo nombreEmpresa.
	 */
	public void setNombreEmpresa(final String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	/** {@inheritDoc} */
	public String toString() {
		return "Codigo: " + getCodNomina() + " Tipo nomina: " + getCodTipoNomina() + " Cuenta: " + getCuentaCargo()
				+ " Fecha carga: " + getFechaCarga() + " Fecha Pago: " + getFechaPago() + " RUT Empresa: "
				+ getRutEmpresa() + " Referencia: " + getReferencia() + " NumOperProg: " + getNumOperProg();
	}

	/** {@inheritDoc} */
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj != null && obj instanceof NominaEnLinea) {
			NominaEnLinea nomina = (NominaEnLinea) obj;
			if (nomina.getCodNomina() == getCodNomina()) {
				equals = true;
			}
		}
		return equals;
	}

	/** {@inheritDoc} */
	public int hashCode() {
		return getCodNomina();
	}

	/**
	 * Permite obtener el valor del atributo numOperProg.
	 *
	 * @return retorna el valor del atributo numOperProg.
	 */
	public long getNumOperProg() {
		return numOperProg;
	}

	/**
	 * Permite setear el valor del atributo numOperProg.
	 *
	 * @param numOperProg nuevo valor para el atributo numOperProg.
	 */
	public void setNumOperProg(long numOperProg) {
		this.numOperProg = numOperProg;
	}

	public String getMailOrdenante() {
		return mailOrdenante;
	}

	public void setMailOrdenante(String mailOrdenante) {
		this.mailOrdenante = mailOrdenante;
	}
}
