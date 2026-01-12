package cl.bice.banca.empresas.servicio.model.cartolaprovisoria;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response para el servicio de movimientos de cartola provisoria
 * @author Marco Bello
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)

public class MovCartolaResponse {


	@JsonProperty("STATUS")
	private String status;
	
	@JsonProperty("STATUS_DES")
	private String statusDes;
	
	@JsonProperty("INSTANCIA")
	private String instancia;

	@JsonProperty("MASPAGINAS")
	private String masPaginas;

	@JsonProperty("TOTALREGISTROS")
	private String totalRegistros;

	@JsonProperty("REGISTROSPAGINA")
	private String registrosPagina;

	@JsonProperty("ULTIMOREGISTROPAG")
	private String ultimoRegistroPag;

	@JsonProperty("MOVIMIENTOS")
	private MovCartola movimientos;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDes() {
		return statusDes;
	}

	public void setStatusDes(String statusDes) {
		this.statusDes = statusDes;
	}

	public String getInstancia() {
		return instancia;
	}

	public void setInstancia(String instancia) {
		this.instancia = instancia;
	}

	public String getMasPaginas() {
		return masPaginas;
	}

	public void setMasPaginas(String masPaginas) {
		this.masPaginas = masPaginas;
	}

	public String getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(String totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public String getRegistrosPagina() {
		return registrosPagina;
	}

	public void setRegistrosPagina(String registrosPagina) {
		this.registrosPagina = registrosPagina;
	}

	public String getUltimoRegistroPag() {
		return ultimoRegistroPag;
	}

	public void setUltimoRegistroPag(String ultimoRegistroPag) {
		this.ultimoRegistroPag = ultimoRegistroPag;
	}

	public MovCartola getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(MovCartola movimientos) {
		this.movimientos = movimientos;
	}
	

}
