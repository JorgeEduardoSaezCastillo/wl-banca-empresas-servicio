/**
 * 
 */
package cl.bice.banca.empresas.servicio.model.operaciones;

/**Clase generada para representar operaciones del tipo LBTR
 * @author Alexis Ganga SM
 *
 */
public class OperacionLbtr {
	
//	private String monto;
//	private String referencia;
//	private String fechaValuta;
//	private String tipoIngreso;
//	private String estado;
//	private String clienteOrdenante;
//	private String comprasAvancesEnCuotasNoFacturados;
//	private String totalFacturado;
//	private String mesVencimientoUno;
//	private String mesVencimientoDos;
//	private String mesVencimientoTres;
//	private String mesVencimientoCuatro;
//	private String cuotaMesUno;
//	private String cuotaMesDos;
//	private String cuotaMesTres;
//	private String cuotaMesCuatro;
//	private String fechaInicioPeriodo;
//	private String fechaFinPeriodo;
//	
//	public String getTotalFacturado() {
//		return totalFacturado;
//	}
//	public void setTotalFacturado(String totalFacturado) {
//		this.totalFacturado = totalFacturado;
//	}
//	public String getMesVencimientoUno() {
//		return mesVencimientoUno;
//	}
//	public void setMesVencimientoUno(String mesVencimientoUno) {
//		this.mesVencimientoUno = mesVencimientoUno;
//	}
//	public String getMesVencimientoDos() {
//		return mesVencimientoDos;
//	}
//	public void setMesVencimientoDos(String mesVencimientoDos) {
//		this.mesVencimientoDos = mesVencimientoDos;
//	}
//	public String getMesVencimientoTres() {
//		return mesVencimientoTres;
//	}
//	public void setMesVencimientoTres(String mesVencimientoTres) {
//		this.mesVencimientoTres = mesVencimientoTres;
//	}
//	public String getMesVencimientoCuatro() {
//		return mesVencimientoCuatro;
//	}
//	public void setMesVencimientoCuatro(String mesVencimientoCuatro) {
//		this.mesVencimientoCuatro = mesVencimientoCuatro;
//	}
//	public String getCuotaMesUno() {
//		return cuotaMesUno;
//	}
//	public void setCuotaMesUno(String cuotaMesUno) {
//		this.cuotaMesUno = cuotaMesUno;
//	}
//	public String getCuotaMesDos() {
//		return cuotaMesDos;
//	}
//	public void setCuotaMesDos(String cuotaMesDos) {
//		this.cuotaMesDos = cuotaMesDos;
//	}
//	public String getCuotaMesTres() {
//		return cuotaMesTres;
//	}
//	public void setCuotaMesTres(String cuotaMesTres) {
//		this.cuotaMesTres = cuotaMesTres;
//	}
//	public String getCuotaMesCuatro() {
//		return cuotaMesCuatro;
//	}
//	public void setCuotaMesCuatro(String cuotaMesCuatro) {
//		this.cuotaMesCuatro = cuotaMesCuatro;
//	}
//	public String getSaldoAdeudadoInicioPeriodo() {
//		return saldoAdeudadoInicioPeriodo;
//	}
//	public void setSaldoAdeudadoInicioPeriodo(String saldoAdeudadoInicioPeriodo) {
//		this.saldoAdeudadoInicioPeriodo = saldoAdeudadoInicioPeriodo;
//	}
//	public String getAbonosPagos() {
//		return abonosPagos;
//	}
//	public void setAbonosPagos(String abonosPagos) {
//		this.abonosPagos = abonosPagos;
//	}
//	public String getComprasAvancesPat() {
//		return comprasAvancesPat;
//	}
//	public void setComprasAvancesPat(String comprasAvancesPat) {
//		this.comprasAvancesPat = comprasAvancesPat;
//	}
//	public String getComprasAvancesEnCuotas() {
//		return comprasAvancesEnCuotas;
//	}
//	public void setComprasAvancesEnCuotas(String comprasAvancesEnCuotas) {
//		this.comprasAvancesEnCuotas = comprasAvancesEnCuotas;
//	}
//	public String getInteresesComisionesImpuestos() {
//		return interesesComisionesImpuestos;
//	}
//	public void setInteresesComisionesImpuestos(String interesesComisionesImpuestos) {
//		this.interesesComisionesImpuestos = interesesComisionesImpuestos;
//	}
//	public String getOtrosCargos() {
//		return otrosCargos;
//	}
//	public void setOtrosCargos(String otrosCargos) {
//		this.otrosCargos = otrosCargos;
//	}
//	public String getComprasAvancesEnCuotasNoFacturados() {
//		return comprasAvancesEnCuotasNoFacturados;
//	}
//	public void setComprasAvancesEnCuotasNoFacturados(String comprasAvancesEnCuotasNoFacturados) {
//		this.comprasAvancesEnCuotasNoFacturados = comprasAvancesEnCuotasNoFacturados;
//	}
//	public String getFechaInicioPeriodo() {
//		return fechaInicioPeriodo;
//	}
//	public void setFechaInicioPeriodo(String fechaInicioPeriodo) {
//		this.fechaInicioPeriodo = fechaInicioPeriodo;
//	}
//	public String getFechaFinPeriodo() {
//		return fechaFinPeriodo;
//	}
//	public void setFechaFinPeriodo(String fechaFinPeriodo) {
//		this.fechaFinPeriodo = fechaFinPeriodo;
//	}
//	@Override
//	public String toString() {
//		return "DetalleNacional [saldoAdeudadoInicioPeriodo=" + saldoAdeudadoInicioPeriodo + ", abonosPagos="
//				+ abonosPagos + ", comprasAvancesPat=" + comprasAvancesPat + ", comprasAvancesEnCuotas="
//				+ comprasAvancesEnCuotas + ", interesesComisionesImpuestos=" + interesesComisionesImpuestos
//				+ ", otrosCargos=" + otrosCargos + ", comprasAvancesEnCuotasNoFacturados="
//				+ comprasAvancesEnCuotasNoFacturados + ", totalFacturado=" + totalFacturado + ", mesVencimientoUno="
//				+ mesVencimientoUno + ", mesVencimientoDos=" + mesVencimientoDos + ", mesVencimientoTres="
//				+ mesVencimientoTres + ", mesVencimientoCuatro=" + mesVencimientoCuatro + ", cuotaMesUno=" + cuotaMesUno
//				+ ", cuotaMesDos=" + cuotaMesDos + ", cuotaMesTres=" + cuotaMesTres + ", cuotaMesCuatro="
//				+ cuotaMesCuatro + ", fechaInicioPeriodo=" + fechaInicioPeriodo + ", fechaFinPeriodo=" + fechaFinPeriodo
//				+ ", getTotalFacturado()=" + getTotalFacturado() + ", getMesVencimientoUno()=" + getMesVencimientoUno()
//				+ ", getMesVencimientoDos()=" + getMesVencimientoDos() + ", getMesVencimientoTres()="
//				+ getMesVencimientoTres() + ", getMesVencimientoCuatro()=" + getMesVencimientoCuatro()
//				+ ", getCuotaMesUno()=" + getCuotaMesUno() + ", getCuotaMesDos()=" + getCuotaMesDos()
//				+ ", getCuotaMesTres()=" + getCuotaMesTres() + ", getCuotaMesCuatro()=" + getCuotaMesCuatro()
//				+ ", getSaldoAdeudadoInicioPeriodo()=" + getSaldoAdeudadoInicioPeriodo() + ", getAbonosPagos()="
//				+ getAbonosPagos() + ", getComprasAvancesPat()=" + getComprasAvancesPat()
//				+ ", getComprasAvancesEnCuotas()=" + getComprasAvancesEnCuotas()
//				+ ", getInteresesComisionesImpuestos()=" + getInteresesComisionesImpuestos() + ", getOtrosCargos()="
//				+ getOtrosCargos() + ", getComprasAvancesEnCuotasNoFacturados()="
//				+ getComprasAvancesEnCuotasNoFacturados() + ", getFechaInicioPeriodo()=" + getFechaInicioPeriodo()
//				+ ", getFechaFinPeriodo()=" + getFechaFinPeriodo() + ", getClass()=" + getClass() + ", hashCode()="
//				+ hashCode() + ", toString()=" + super.toString() + "]";
//	}

}
