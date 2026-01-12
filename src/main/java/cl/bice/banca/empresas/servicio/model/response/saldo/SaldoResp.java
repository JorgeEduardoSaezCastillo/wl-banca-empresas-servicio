package cl.bice.banca.empresas.servicio.model.response.saldo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import cl.bice.banca.empresas.servicio.model.common.AccesoDirecto;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.soap.balance.CuentaType;
import cl.bice.banca.empresas.servicio.util.FormateadorUtil;

/**
 * Clase que contiene datos de respuesta del servicio rest.
 *
 * @author Elio Diaz. (TINet)
 * @version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaldoResp {

	/**
	 * Titulo a mostrar en el bloque principal.
	 */
	private String titulo;

	/**
	 * Monto resumen.
	 */
	private String monto;

	/**
	 * Monto formateado.
	 */
	private String montoFormateado;

	/**
	 * Detalle del monto disponible para avance MN.
	 */
	private DetalleAvanceNacionalResp detalleAvanceNacional;

	/**
	 * Detalle de la facturacion para TDC.
	 */
	private FacturacionResp detalleFacturacion;

	/**
	 * Lista de saldos del producto.
	 */
	private List<DetalleSaldoResp> detalle = new ArrayList<>();

	/**
	 * Texto informativo.
	 */
	private String textoInfo;

	/**
	 * Accesos directo del producto.
	 */
	private List<AccesoDirecto> accesosDirectos;

	/**
	 * Fecha de la BD.
	 */
	private String fecha;

	/**
	 * Fecha de la Retenciones del Día.
	 */
	private String retencionesMismaPlaza;

	/**
	 * Fecha de la Retenciones del Día Formateado.
	 */
	private String retencionesMismaPlazaFormateado;

	/**
	 * Fecha de la Retenciones Anteriores.
	 */
	private String retencionesOtrasPlazas;

	/**
	 * Fecha de la Retenciones Anteriores Formateado.
	 */
	private String retencionesOtrasPlazasFormateado;

	public SaldoResp() {
		super();

	}

	/**
	 * Contructor que utiliza como base una cuenta de Balance de inversion.
	 *
	 * @param cuenta de origen.
	 * @param titulo del saldo.
	 */
	public SaldoResp(CuentaType cuenta) {
		super();
		DetalleSaldoResp detalleNuevo = new DetalleSaldoResp();
		if ("Operaciones En Tránsito".equalsIgnoreCase(cuenta.getTipo())) {
			cargarDetalle(cuenta.getPesos(), detalleNuevo, "Operaciones en Tránsito en Pesos",
					Constantes.CODIGO_MONEDA_PESOS);
			cargarDetalle(cuenta.getDolar(), detalleNuevo, "Operaciones en Tránsito en Dólares",
					Constantes.CODIGO_MONEDA_USD);
			cargarDetalle(cuenta.getEuro(), detalleNuevo, "Operaciones en Tránsito en Euros",
					Constantes.CODIGO_MONEDA_EURO);
		} else if ("Simultánea".equalsIgnoreCase(cuenta.getTipo())) {
			cargarDetalle(cuenta.getPesos(), detalleNuevo, "Pasivo en Simultáneas", Constantes.CODIGO_MONEDA_PESOS);
		} else {
			cargarDetalle(cuenta.getPesos(), detalleNuevo, "En Pesos", Constantes.CODIGO_MONEDA_PESOS);
			cargarDetalle(cuenta.getDolar(), detalleNuevo, "En Dólares", Constantes.CODIGO_MONEDA_USD);
			cargarDetalle(cuenta.getEuro(), detalleNuevo, "En Euros", Constantes.CODIGO_MONEDA_EURO);
		}
		this.detalle.add(detalleNuevo);

	}

	private void cargarDetalle(double monto, DetalleSaldoResp detalleNuevo, String descripcion, String moneda) {
		DetalleResp informacion = new DetalleResp();
		informacion.setDescripcion(descripcion);
		informacion.setCodigoMoneda(moneda);
		informacion.setMontoNumerico((float) monto);
		informacion.setMonto(FormateadorUtil.formatearMonto(String.valueOf(monto), informacion.getCodigoMoneda()));
		detalleNuevo.getSaldos().add(informacion);
	}

	/**
	 * Permite obtener el valor del atributo detalle.
	 *
	 * @return retorna el valor del atributo detalle.
	 */
	public final List<DetalleSaldoResp> getDetalle() {
		return detalle;
	}

	/**
	 * Permite setear el valor del atributo detalle.
	 *
	 * @param newdetalle nuevo valor para el atributo detalle.
	 */
	public final void setDetalle(final List<DetalleSaldoResp> newdetalle) {
		this.detalle = newdetalle;
	}

	/**
	 * Permite obtener el valor del atributo titulo.
	 *
	 * @return retorna el valor del atributo titulo.
	 */
	public final String getTitulo() {
		return titulo;
	}

	/**
	 * Permite setear el valor del atributo titulo.
	 *
	 * @param newtitulo nuevo valor para el atributo titulo.
	 */
	public final void setTitulo(final String newtitulo) {
		this.titulo = newtitulo;
	}

	/**
	 * Permite obtener el valor del atributo monto.
	 *
	 * @return retorna el valor del atributo monto.
	 */
	public final String getMonto() {
		return monto;
	}

	/**
	 * Permite setear el valor del atributo monto.
	 *
	 * @param newmonto nuevo valor para el atributo monto.
	 */
	public final void setMonto(final String newmonto) {
		this.monto = newmonto;
	}

	/**
	 * Permite obtener el valor del atributo textoInfo.
	 *
	 * @return retorna el valor del atributo textoInfo.
	 */
	public final String getTextoInfo() {
		return textoInfo;
	}

	/**
	 * Permite setear el valor del atributo textoInfo.
	 *
	 * @param newtextoInfo nuevo valor para el atributo textoInfo.
	 */
	public final void setTextoInfo(final String newtextoInfo) {
		this.textoInfo = newtextoInfo;
	}

	/**
	 * Permite obtener el valor del atributo montoFormateado.
	 *
	 * @return retorna el valor del atributo montoFormateado.
	 */
	public final String getMontoFormateado() {
		return montoFormateado;
	}

	/**
	 * Permite setear el valor del atributo montoFormateado.
	 *
	 * @param newmontoFormateado nuevo valor para el atributo montoFormateado.
	 */
	public final void setMontoFormateado(final String newmontoFormateado) {
		this.montoFormateado = newmontoFormateado;
	}

	/**
	 * Permite obtener el valor del atributo detalleAvanceNacional.
	 *
	 * @return retorna el valor del atributo detalleAvanceNacional.
	 */
	public DetalleAvanceNacionalResp getDetalleAvanceNacional() {
		return detalleAvanceNacional;
	}

	/**
	 * Permite setear el valor del atributo detalleAvanceNacional.
	 *
	 * @param newdetalleAvanceNacional nuevo valor para el atributo
	 *                                 detalleAvanceNacional.
	 */
	public void setDetalleAvanceNacional(final DetalleAvanceNacionalResp newdetalleAvanceNacional) {
		this.detalleAvanceNacional = newdetalleAvanceNacional;
	}

	/**
	 * Permite obtener el valor del atributo detalleFacturacion.
	 *
	 * @return retorna el valor del atributo detalleFacturacion.
	 */
	public FacturacionResp getDetalleFacturacion() {
		return detalleFacturacion;
	}

	/**
	 * Permite setear el valor del atributo detalleFacturacion.
	 *
	 * @param newdetalleFacturacion nuevo valor para el atributo detalleFacturacion.
	 */
	public void setDetalleFacturacion(final FacturacionResp newdetalleFacturacion) {
		this.detalleFacturacion = newdetalleFacturacion;
	}

	/**
	 * Permite obtener el valor del atributo accesosDirectos.
	 *
	 * @return retorna el valor del atributo accesosDirectos.
	 */
	public List<AccesoDirecto> getAccesosDirectos() {
		return accesosDirectos;
	}

	/**
	 * Permite setear el valor del atributo accesosDirectos.
	 *
	 * @param accesosDirectos nuevo valor para el atributo accesosDirectos.
	 */
	public void setAccesosDirectos(List<AccesoDirecto> accesosDirectos) {
		this.accesosDirectos = accesosDirectos;
	}

	/**
	 * Permite obtener el valor del atributo fecha.
	 *
	 * @return retorna el valor del atributo fecha.
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Permite setear el valor del atributo fecha.
	 *
	 * @param fecha nuevo valor para el atributo fecha.
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the retencionesMismaPlaza
	 */
	public String getRetencionesMismaPlaza() {
		return retencionesMismaPlaza;
	}

	/**
	 * @param retencionesMismaPlaza the retencionesMismaPlaza to set
	 */
	public void setRetencionesMismaPlaza(String retencionesMismaPlaza) {
		this.retencionesMismaPlaza = retencionesMismaPlaza;
	}

	/**
	 * @return the retencionesMismaPlazaFormateado
	 */
	public String getRetencionesMismaPlazaFormateado() {
		return retencionesMismaPlazaFormateado;
	}

	/**
	 * @param retencionesMismaPlazaFormateado the retencionesMismaPlazaFormateado to
	 *                                        set
	 */
	public void setRetencionesMismaPlazaFormateado(String retencionesMismaPlazaFormateado) {
		this.retencionesMismaPlazaFormateado = retencionesMismaPlazaFormateado;
	}

	/**
	 * @return the retencionesOtrasPlazas
	 */
	public String getRetencionesOtrasPlazas() {
		return retencionesOtrasPlazas;
	}

	/**
	 * @param retencionesOtrasPlazas the retencionesOtrasPlazas to set
	 */
	public void setRetencionesOtrasPlazas(String retencionesOtrasPlazas) {
		this.retencionesOtrasPlazas = retencionesOtrasPlazas;
	}

	/**
	 * @return the retencionesOtrasPlazasFormateado
	 */
	public String getRetencionesOtrasPlazasFormateado() {
		return retencionesOtrasPlazasFormateado;
	}

	/**
	 * @param retencionesOtrasPlazasFormateado the retencionesOtrasPlazasFormateado
	 *                                         to set
	 */
	public void setRetencionesOtrasPlazasFormateado(String retencionesOtrasPlazasFormateado) {
		this.retencionesOtrasPlazasFormateado = retencionesOtrasPlazasFormateado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SaldoResp [titulo=" + titulo + ", monto=" + monto + ", montoFormateado=" + montoFormateado
				+ ", detalleAvanceNacional=" + detalleAvanceNacional + ", detalleFacturacion=" + detalleFacturacion
				+ ", detalle=" + detalle + ", textoInfo=" + textoInfo + ", accesosDirectos=" + accesosDirectos
				+ ", fecha=" + fecha + ", retencionesMismaPlaza=" + retencionesMismaPlaza
				+ ", retencionesMismaPlazaFormateado=" + retencionesMismaPlazaFormateado + ", retencionesOtrasPlazas="
				+ retencionesOtrasPlazas + ", retencionesOtrasPlazasFormateado=" + retencionesOtrasPlazasFormateado
				+ "]";
	}

}