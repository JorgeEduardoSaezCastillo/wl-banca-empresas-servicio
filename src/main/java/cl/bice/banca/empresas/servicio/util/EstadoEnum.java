package cl.bice.banca.empresas.servicio.util;

/**
 * Enum para obtener cÃ³digos de estado y parÃ¡metro para obtener desde el archivo
 * properties la glosa de estado.
 * 
 * @author Fibacache
 *
 */
public enum EstadoEnum {
	EXITO("1", "texto.exito"),

	ERROR_E001_EMPRESAS_REQUEST_INVALIDO("E001", "texto.errorE001.empresas.request.invalido"),
	ERROR_E002_EMPRESAS_REQUEST_INVALIDO("E002", "texto.errorE002.empresas.request.invalido"),
	ERROR_E003_EMPRESAS_REQUEST_INVALIDO("E003", "texto.errorE003.empresas.request.invalido"),
	ERROR_E004_EMPRESAS_REQUEST_INVALIDO("E004", "texto.errorE004.empresas.request.invalido"),
	ERROR_E005_EMPRESAS_REQUEST_INVALIDO("E005", "texto.errorE005.empresas.request.invalido"),
	ERROR_E006_EMPRESAS_REQUEST_INVALIDO("E006", "texto.errorE006.empresas.request.invalido"),
	ERROR_E007_EMPRESAS_REQUEST_INVALIDO("E007", "texto.errorE007.empresas.request.invalido"),
	ERROR_E008_EMPRESAS_REQUEST_INVALIDO("E008", "texto.errorE008.empresas.request.invalido"),
	ERROR_E009_EMPRESAS_REQUEST_INVALIDO("E009", "texto.errorE009.empresas.request.invalido"),
	ERROR_E010_EMPRESAS_REQUEST_INVALIDO("E010", "texto.errorE010.empresas.request.invalido"),
	ERROR_E011_EMPRESAS_REQUEST_INVALIDO("E011", "texto.errorE011.empresas.request.invalido"),
	ERROR_E012_EMPRESAS_REQUEST_INVALIDO("E012", "texto.errorE012.empresas.request.invalido"),
	ERROR_E013_EMPRESAS_REQUEST_INVALIDO("E013", "texto.errorE013.empresas.request.invalido"),
	ERROR_E014_EMPRESAS_REQUEST_INVALIDO("E014", "texto.errorE014.empresas.request.invalido"),
	ERROR_E015_EMPRESAS_REQUEST_INVALIDO("E015", "texto.errorE015.empresas.request.invalido"),
	ERROR_E016_EMPRESAS_REQUEST_INVALIDO("E016", "texto.errorE016.empresas.request.invalido"),
	ERROR_E017_EMPRESAS_REQUEST_INVALIDO("E017", "texto.errorE017.empresas.request.invalido"),
	ERROR_E018_EMPRESAS_REQUEST_INVALIDO("E018", "texto.errorE018.empresas.request.invalido"),
	ERROR_E019_EMPRESAS_REQUEST_INVALIDO("E019", "texto.errorE019.empresas.request.invalido"),
	
	ERROR_E001_EMPRESAS_OBTENER_EMPRESAS("E001", "texto.errorE001.empresas.obtener.empresas"),
	ERROR_E002_EMPRESAS_OBTENER_EMPRESAS("E002", "texto.errorE002.empresas.obtener.empresas"),
	ERROR_E003_EMPRESAS_OBTENER_EMPRESAS("E003", "texto.errorE003.empresas.obtener.empresas"),

	ERROR_E001_EMPRESAS_RESUMEN_OPERACIONES("E001", "texto.errorE001.empresas.resumen.operaciones"),
	ERROR_E002_EMPRESAS_RESUMEN_OPERACIONES("E002", "texto.errorE002.empresas.resumen.operaciones"),
	ERROR_E003_EMPRESAS_RESUMEN_OPERACIONES("E003", "texto.errorE003.empresas.resumen.operaciones"),

	ERROR_E001_EMPRESAS_LISTA_CUENTAS_PERFIL("E001", "texto.errorE001.empresas.lista.cuentas.perfil"),
	ERROR_E002_EMPRESAS_LISTA_CUENTAS_PERFIL("E002", "texto.errorE002.empresas.lista.cuentas.perfil"),
	ERROR_E003_EMPRESAS_LISTA_CUENTAS_PERFIL("E003", "texto.errorE003.empresas.lista.cuentas.perfil"),

	ERROR_E001_EMPRESAS_LISTA_CUENTAS_SALDO_PERFIL("E001", "texto.errorE001.empresas.lista.cuentas.saldo.perfil"),
	ERROR_E002_EMPRESAS_LISTA_CUENTAS_SALDO_PERFIL("E002", "texto.errorE002.empresas.lista.cuentas.saldo.perfil"),
	ERROR_E003_EMPRESAS_LISTA_CUENTAS_SALDO_PERFIL("E003", "texto.errorE003.empresas.lista.cuentas.saldo.perfil"),

	ERROR_E001_EMPRESAS_SALDO("E001", "texto.errorE001.empresas.saldo"),
	ERROR_E002_EMPRESAS_SALDO("E002", "texto.errorE002.empresas.saldo"),
	ERROR_E003_EMPRESAS_SALDO("E003", "texto.errorE003.empresas.saldo"),

	ERROR_E001_EMPRESAS_LISTA_RESUMEN_MOVIMIENTOS("E001", "texto.errorE001.empresas.lista.resumen.movimientos"),
	ERROR_E002_EMPRESAS_LISTA_RESUMEN_MOVIMIENTOS("E002", "texto.errorE002.empresas.lista.resumen.movimientos"),
	ERROR_E003_EMPRESAS_LISTA_RESUMEN_MOVIMIENTOS("E003", "texto.errorE003.empresas.lista.resumen.movimientos"),

	ERROR_E001_EMPRESAS_OPERACIONES_LISTAR_APROBAR_LIBERAR("E001",
			"texto.errorE001.empresas.operaciones.listar.aprobar.liberar"),
	ERROR_E002_EMPRESAS_OPERACIONES_LISTAR_APROBAR_LIBERAR("E002",
			"texto.errorE002.empresas.operaciones.listar.aprobar.liberar"),
	ERROR_E003_EMPRESAS_OPERACIONES_LISTAR_APROBAR_LIBERAR("E003",
			"texto.errorE003.empresas.operaciones.listar.aprobar.liberar"),

	ERROR_E001_EMPRESAS_LISTAR_CREAR_DESAFIOS("E001", "texto.errorE001.empresas.listar.crear.desafios"),
	ERROR_E002_EMPRESAS_LISTAR_CREAR_DESAFIOS("E002", "texto.errorE002.empresas.listar.crear.desafios"),
	ERROR_E003_EMPRESAS_LISTAR_CREAR_DESAFIOS("E003", "texto.errorE003.empresas.listar.crear.desafios"),
	ERROR_E004_EMPRESAS_LISTAR_CREAR_DESAFIOS("E004", "texto.errorE004.empresas.listar.crear.desafios"),

	ERROR_E001_EMPRESAS_VALIDAR_ESTADO_DESAFIO("E001", "texto.errorE001.empresas.validar.estado.desafio"),
	ERROR_E002_EMPRESAS_VALIDAR_ESTADO_DESAFIO("E002", "texto.errorE002.empresas.validar.estado.desafio"),
	ERROR_E003_EMPRESAS_VALIDAR_ESTADO_DESAFIO("E003", "texto.errorE003.empresas.validar.estado.desafio"),

	ERROR_E001_EMPRESAS_LISTA_OPERACIONES_DIA("E001", "texto.errorE001.empresas.lista.operaciones.dia"),
	ERROR_E002_EMPRESAS_LISTA_OPERACIONES_DIA("E002", "texto.errorE002.empresas.lista.operaciones.dia"),
	ERROR_E003_EMPRESAS_LISTA_OPERACIONES_DIA("E003", "texto.errorE003.empresas.lista.operaciones.dia"),

	ERROR_E001_EMPRESAS_APROBAR_OPERACIONES("E001", "texto.errorE001.empresas.aprobar.operaciones"),
	ERROR_E002_EMPRESAS_APROBAR_OPERACIONES("E002", "texto.errorE002.empresas.aprobar.operaciones"),
	ERROR_E003_EMPRESAS_APROBAR_OPERACIONES("E003", "texto.errorE003.empresas.aprobar.operaciones"),
	ERROR_E004_EMPRESAS_APROBAR_OPERACIONES("E004", "texto.errorE004.empresas.aprobar.operaciones"),
	ERROR_E005_EMPRESAS_APROBAR_OPERACIONES("E005", "texto.errorE005.empresas.aprobar.operaciones"),

	ERROR_E001_EMPRESAS_VALIDA_PODER_EMPRESA("E001", "texto.errorE001.empresas.valida.poder.empresa"),
	ERROR_E002_EMPRESAS_VALIDA_PODER_EMPRESA("E002", "texto.errorE002.empresas.valida.poder.empresa"),
	ERROR_E003_EMPRESAS_VALIDA_PODER_EMPRESA("E003", "texto.errorE003.empresas.valida.poder.empresa"),

	ERROR_E001_EMPRESAS_LISTAR_SERVICIOS("E001", "texto.errorE001.empresas.listar.servicios"),

	ERROR_E001_EMPRESAS_DETALLE_AUTORIZACION("E001", "texto.errorE001.empresas.detalle.autorizacion"),
	ERROR_E002_EMPRESAS_DETALLE_AUTORIZACION("E002", "texto.errorE002.empresas.detalle.autorizacion"),
	ERROR_E003_EMPRESAS_DETALLE_AUTORIZACION("E003", "texto.errorE003.empresas.detalle.autorizacion"),
	
	ERROR_E001_EMPRESAS_CLIENTE_MOBISIGNER("E001", "texto.errorE001.empresas.cliente.mobisigner"),
	
	
	ERROR_E001_EMPRESAS_VALIDAR_MOBILE_SIGNER("E001", "texto.errorE001.empresas.validar.mobile.signer"),
	
	ERROR_E001_EMPRESAS_ESTADO_VALIDACION_MOBILE_SIGNER("E001",
			"texto.errorE001.empresas.estado.validacion.mobile.signer"),

	ERROR_E001_EMPRESAS_APROBAR_NOMINAS_EN_LINEA("E001", "texto.errorE001.empresas.aprobar.nominas.en.linea"),
	ERROR_E002_EMPRESAS_APROBAR_NOMINAS_EN_LINEA("E002", "texto.errorE002.empresas.aprobar.nominas.en.linea"),
	ERROR_E003_EMPRESAS_APROBAR_NOMINAS_EN_LINEA("E003", "texto.errorE003.empresas.aprobar.nominas.en.linea"),
	
	ERROR_E001_EMPRESAS_LIBERAR_OPERACIONES("E001", "texto.errorE001.empresas.liberar.operaciones"),
	ERROR_E006_EMPRESAS_LIBERAR_OPERACIONES("E006", "texto.errorE006.empresas.liberar.operaciones"),

	ERROR_E001_EMPRESAS_LIBERAR_NOMINAS_EN_LINEA("E001", "texto.errorE001.empresas.liberar.nominas.en.linea"),
	ERROR_E002_EMPRESAS_LIBERAR_NOMINAS_EN_LINEA("E002", "texto.errorE002.empresas.liberar.nominas.en.linea"),
	ERROR_E003_EMPRESAS_LIBERAR_NOMINAS_EN_LINEA("E003", "texto.errorE003.empresas.liberar.nominas.en.linea"),

	ERROR_E101_EMPRESAS_GENERIC_APROB_LIB("E101", "texto.errorE101.empresas.generic.aprob.lib"),
	ERROR_E102_EMPRESAS_GENERIC_APROB_LIB("E102", "texto.errorE102.empresas.generic.aprob.lib")
	;

	private String codigoEstado;
	private String parametroGlosaEstado;

	private EstadoEnum(String codigoEstado, String parametroGlosaEstado) {
		this.parametroGlosaEstado = parametroGlosaEstado;
		this.codigoEstado = codigoEstado;
	}

	/**
	 * @return the codigoEstado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * @return the glosaEstado
	 */
	public String getParametroGlosaEstado() {
		return parametroGlosaEstado;
	}
}