package cl.bice.banca.empresas.servicio.model.cartolaprovisoria;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * clase que representa el detalle de un movimiento de la cartola provisoria
 * @author Marco Bello
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)

public class DetalleMovimiento {


	@JsonProperty("FECHA")
	private String fecha;
	
	@JsonProperty("FECHACONTABLE")
	private String fechaContable;
	
	@JsonProperty("CODTRN")
	private String codTrn;

	@JsonProperty("DESCRIPCION")
	private String descripcion;

	@JsonProperty("NARRATIVA")
	private String narrativa;

	@JsonProperty("REMARKS")
	private String remarks;

	@JsonProperty("SUCMOV")
	private String sucMov;

	@JsonProperty("NROINST")
	private String nroInst;

	@JsonProperty("TIPOMOV")
	private String tipoMov;

	@JsonProperty("MONTO")
	private String monto;

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getFechaContable() {
		return fechaContable;
	}

	public void setFechaContable(String fechaContable) {
		this.fechaContable = fechaContable;
	}

	public String getCodTrn() {
		return codTrn;
	}

	public void setCodTrn(String codTrn) {
		this.codTrn = codTrn;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNarrativa() {
		return narrativa;
	}

	public void setNarrativa(String narrativa) {
		this.narrativa = narrativa;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSucMov() {
		return sucMov;
	}

	public void setSucMov(String sucMov) {
		this.sucMov = sucMov;
	}

	public String getNroInst() {
		return nroInst;
	}

	public void setNroInst(String nroInst) {
		this.nroInst = nroInst;
	}

	public String getTipoMov() {
		return tipoMov;
	}

	public void setTipoMov(String tipoMov) {
		this.tipoMov = tipoMov;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	

}
