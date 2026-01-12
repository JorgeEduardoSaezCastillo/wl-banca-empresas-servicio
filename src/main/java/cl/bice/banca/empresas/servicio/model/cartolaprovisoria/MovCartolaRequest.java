package cl.bice.banca.empresas.servicio.model.cartolaprovisoria;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * Request para el servicio de movimientos de cartola provisoria
 * @author Marco Bello
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovCartolaRequest  {
	
	@JsonProperty("CANAL")
	private String canal;
	
	@JsonProperty("CUENTA")
	private String numeroCuenta;
	
	@JsonProperty("TIPOMOVIMIENTO")
	private String tipoMovimiento;
	
	@JsonProperty("FECHADESDE")
	private String fechaDesde;
	
	@JsonProperty("FECHAHASTA")
	private String fechaHasta;
	
	@JsonProperty("REGISTRO")
	private long registro;
	
	
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public long getRegistro() {
		return registro;
	}
	public void setRegistro(long registro) {
		this.registro = registro;
	}

	
	
}
