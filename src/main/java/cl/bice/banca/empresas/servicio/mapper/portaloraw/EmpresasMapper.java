package cl.bice.banca.empresas.servicio.mapper.portaloraw;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Primary;


/**
 * Mapper que contiene los metodos del package banca empresas
 *
 * @author Cristian Pais (TINet)
 * @version 1.0
 */
@Primary
public interface EmpresasMapper {

	/**
	 * metodo encargado de obtener las operaciones pendientes de firma
	 *
	 * @param params mapa con parametros de entrada y salida.
	 */
	void obtenerOperacionesPendientesDeFirma(Map<String, Object> params) throws SQLException;

	/**
	 * obtiene la lista de empresas para las cuales el usuario está autorizado a
	 * operar, junto con la marca de empresa preferida y de apoderado activado para
	 * la empresa
	 * 
	 * @param params
	 */
	void obtenerListaEmpresas(Map<String, Object> params) throws SQLException;

	/**
	 * metodo encargado de obtener las operaciones pendientes
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerResumenOperaciones(Map<String, Object> params) throws SQLException;

	/**
	 * metodo encargado de obtener las cuentas que puede ver un apoderado o delegado
	 * de la empresa *
	 * 
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerCuentasPorPerfil(Map<String, Object> params) throws SQLException;

	/**
	 * metodo encargado de obtener operaciones para aprobar o liberar
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerOperacionesAprobarLiberar(Map<String, Object> params) throws SQLException;

	/**
	 * metodo encargado de obtener operaciones del día
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtieneListaOperacionesDia(Map<String, Object> params) throws SQLException;

	/**
	 * consulta si un rut tiene facultad especial
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void existeFacultad(Map<String, Object> params) throws SQLException;

	/**
	 * registra log facultad
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void registraLogFacultad(Map<String, Object> params) throws SQLException;

	/**
	 * valida la pertenencia de un número de operación a un rut empresa
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void validarPertenenciaNumOperacionRutEmpresa(Map<String, Object> params) throws SQLException;

	/**
	 * envía mail con datos de un error (código y glosa)
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void enviarMailCodGlsError(Map<String, Object> params) throws SQLException;

	/**
	 * Consulta detalle de operación El detalle puede ser: 1) "2=PROCESANDO;" 2) "2=
	 * ;"
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void consultarDetalleOperacion(Map<String, Object> params) throws SQLException;

	/**
	 * obtiene nombre delegado pero tambien de apoderado
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerNombreDelegado(Map<String, Object> params) throws SQLException;

	/**
	 * obtiene nombre cliente
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerNombreCliente(Map<String, Object> params) throws SQLException;

	/**
	 * registra contrato FDA
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void registraContratoFDA(Map<String, Object> params) throws SQLException;

	/**
	 * inserta datos FDA en una tabla de seguimiento
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void insertarFDASeguimiento(Map<String, Object> params) throws SQLException;

	/**
	 * metodo encargado de obtener operaciones para aprobar o liberar para el portal
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerOperacionesAprobarLiberarPortal(Map<String, Object> params) throws SQLException;

	/**
	 * metodo encargado de obtener las firmas de nominas en linea
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */

	void obtenerFirmasNominaEnLinea(Map<String, Object> params) throws SQLException;

	/**
	 * metodo encargado de obtener datos del detalle de nominas en linea
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */

	void obtenerNominaEnLinea(Map<String, Object> params) throws SQLException;

	/**
	 * metodo encargado de obtener el nombre del tipo de nomina
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerNombreTipoNomina(Map<String, Object> params) throws SQLException;

	/**
	 * verifica si la empresa tiene o no cargo en línea LBTR
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void empresaTieneCargoEnLineaLbtr(Map<String, Object> params) throws SQLException;

	/**
	 * verifica si la empresa opera con modelo cuenta paragua
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void empresaTieneCuentaParaguaLbtr(Map<String, Object> params) throws SQLException;

	/**
	 * Obtiene códigos error Gfs
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerCodigosErrorGfs(Map<String, Object> params) throws SQLException;

	/**
	 * Obtiene cuentas colaborativas de una cuenta
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerCuentasColaborativas(Map<String, Object> params) throws SQLException;

	/**
	 * Obtiene datos empresa cclv
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerDatosCclv(Map<String, Object> params) throws SQLException;
	
	/**
	 * Obtiene firmas de nóminas diferidas
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerFirmasNominaDiferida(Map<String, Object> params) throws SQLException;
	
	/**
	 * Obtiene datos de una nómina diferida
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerDatosNominaDiferida(Map<String, Object> params) throws SQLException;
	
	/**
	 * Obtiene email de ejecutivo
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerEmailEjecutivo(Map<String, Object> params) throws SQLException;

	/**
	 * metodo encargado de obtener los estados de nominas en linea
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerEstadosNominasEnLinea(Map<String, Object> params) throws SQLException;

	/**
	 * metodo para eliminar estados de nominas en linea
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void eliminarEstadoNominaEnLinea(Map<String, Object> params) throws SQLException;

	/**
	 * valida fecha máxima para liberar nominas en linea
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void validarMaxFechaLiberarNomLin(Map<String, Object> params) throws SQLException;

	/**
	 * obtiene la fecha contable de nomina en linea
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerFechaContableNomLin(Map<String, Object> params) throws SQLException;

	/**
	 * registra en la base de datos el cargo a la cuenta de la nomina.
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void registrarCargoNominasEnLinea(Map<String, Object> params) throws SQLException;

	/**
	 * obtiene parámetros requeridos por nominas en linea
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerParametrosNomLin(Map<String, Object> params) throws SQLException;

	/**
	 * Obtiene id correlativo para control liberacion nominas en linea
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerCorrelativoNomLin(Map<String, Object> params) throws SQLException;

	/**
	 * Graba correlativo para control liberacion nominas en linea
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void insertarCorrelativoNomLin(Map<String, Object> params) throws SQLException;

	/**
	 * Consulta id correlativo para control liberacion nominas en linea
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void consultarCorrelativoNomLin(Map<String, Object> params) throws SQLException;

	/**
	 * Actualiza registro correlativo para control liberacion nominas en linea
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void actualizarCorrelativoNomLin(Map<String, Object> params) throws SQLException;
	
	/**
	 * Obtiene lista resumen CC nóminas diferidas
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void getLisResumenCC(Map<String, Object> params) throws SQLException;
	
	/**
	 * Obtiene fecha de tbl_iodata
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerFechaIodata(Map<String, Object> params) throws SQLException;
	
	/**
	 * Obtiene fecha de tbl_iodata
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void validaDiaHabil(Map<String, Object> params) throws SQLException;
	
	/**
	 * Obtiene el detalle de una operación
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerDetalleOperacion(Map<String, Object> params) throws SQLException;
	
	/**
	 * Registra el seguimiento de una operación BiceComex (TBL_SEG_BICECOMEX)
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void registraSegBiceComex(Map<String, Object> params) throws SQLException;
	
	/**
	 * Verifica si una operación está siendo procesada (sirve para tablas oper prog y bicecomex)
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void validaBloqueoOperaciones(Map<String, Object> params) throws SQLException;
	
		/**
	 * Consulta la tabla TBL_USUARIOCLIENTE
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void consultarTblUsuarioCliente(Map<String, Object> params) throws SQLException;
	
	/**
	 * Consulta la tabla TBL_VALIDAPODERES
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void consultarTblValidaPoderes(Map<String, Object> params) throws SQLException;	
	
	/**
	 * metodo generico encargado de obtener operaciones para aprobar o liberar para el portal
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void obtenerOperacionesAprobarLiberarGenericoPortal(Map<String, Object> params) throws SQLException;
	
	/**
	 * registra contrato FDA dejando contrato inactivo
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void registraContratoFDADesactivado(Map<String, Object> params) throws SQLException;
	
	/**
	 * consulta contrato FDA
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	void consultaContratosFDA(Map<String, Object> params) throws SQLException;


	/**
	 * metodo encargado de obtener operaciones para aprobar o liberar para el portal.
	 * Se utiliza en el servicio de aprobaciones masivas
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	List<Map<String, Object>> obtenerListaOperacionesAprobarLiberarPortal(Map<String, Object> params) throws SQLException;

	
	/**
	 * Permite actualizar un campo de la tabla TBL_DETALLE_CAMP.<br>
	 * Se utiliza en el servicio de aprobaciones y liberaciones masivas
	 *
	 * @param mapa con parametros de entrada
	 * Parametros: valCampo, numOperProg, codCampo <br>
	 * Ejemplo: <br>
	 *  params.put(Constantes.PARAMETRO_EMPRESAS_V_VAL_CAMPO, valCampo); <br>
		params.put(Constantes.PARAMETRO_EMPRESAS_V_NUM_OPER_PROG, numOperProg); <br>
		params.put(Constantes.PARAMETRO_EMPRESAS_V_COD_CAMPO, codCampo); <br>
	 * @throws SQLException
	 */
	void actualizaDetalleCampPortal(Map<String, Object> params) throws SQLException;
	

	/**
	 * Permite actualizar el estado de una operacion en la tabla TBL_OPER_PROG <br>
	 * Se utiliza en el servicio de aprobaciones y liberaciones masivas
	 *
	 * @param mapa con parametros de entrada
	 * Parametros: numOperProg, codEstado
	 * @throws SQLException
	 */
	void actualizaEstadoOperProg(Map<String, Object> params) throws SQLException;
	

	/**
	 * Permite obtener el nombre de un apoderado desde tabla TBL_CLAVEACCESO 
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	String getNombreApoderadoClaveAcceso(Map<String, Object> params) throws SQLException;
	

	/**
	 * Permite obtener el nombre de un apoderado desde vista POR_VIEW_CIF_CLIENTES 
	 *
	 * @param params mapa con parametros de entrada y salida.
	 * @throws SQLException
	 */
	String getNombreCliente(Map<String, Object> params) throws SQLException;
	
	
	/**
	 * Permite obtener el detalle de datos asociado a una operacion en progreso
	 * @param mapa con parametros de entrada
	 * Parametros: numOperProg
	 * @throws SQLException
	 */
    Map<String, Object> getDatosOperProg(Map<String, Object> params) throws SQLException;
    
    
	/**
	 * Permite obtener un listado de parametros de validación
	 * @param mapa con parametros de entrada
	 * Parametros: NOM_PARAMETRO y NOM_TIPO
	 * @throws SQLException
	 */
    List<Map<String, Object>> getParametrosValidacion(Map<String, Object> params) throws SQLException;

    
	/**
	 * Permite obtener un listado con las firmas que tiene una operacion en progreso
	 * @param mapa con parametros de entrada
	 * Parametros: numOperProg
	 * @throws SQLException
	 */
    List<Map<String, Object>> getFirmasOperProg(Map<String, Object> params) throws SQLException;
    

	/**
	 * Permite obtener información del rut cliente y rut usuario
	 * @param mapa con parametros de entrada
	 * Parametros: rutCliente, rutUsuario
	 * @throws SQLException
	 */
    List<Map<String, Object>> getDatosUsuarioCliente(Map<String, Object> params) throws SQLException;
    

	/**
	 * Permite actualizar el campo NUM_OPERACION _TRF en la tabla TBL_OPER_PROG <br>
	 * Se utiliza en el servicio de aprobaciones y liberaciones masivas
	 *
	 * @param mapa con parametros de entrada
	 * Parametros: numOperProg, numOperacionTrf
	 * @throws SQLException
	 */
	void actualizaNumOperacionTrfOperProg(Map<String, Object> params) throws SQLException;
    
    
    
	/**
	 *
	 * @param params Parámetros correspondientes a la transaccion que se desea bloquear
	 */
   void lockTransaction(Map<String, Object> params) throws SQLException;

	/**
	 *
	 * @param params Parámetros correspondientes a la transaccion que se desea des-bloquear
	 */
	void unLockTransaction(Map<String, Object> params) throws SQLException;
	
	
	/**
	 * Permite eliminar una firma de apoderado en la tabla TBL_APO_OP para una operacion en particular <br>
	 * Se utiliza en el servicio de aprobaciones y liberaciones masivas
	 *
	 * @param mapa con parametros de entrada
	 * Parametros: numOperProg, rutApoderado
	 * @throws SQLException
	 */
	void eliminaFirmaApoderado(Map<String, Object> params) throws SQLException;
	

	/**
	 * Permite agregar un campo en la tabla TBL_DETALLE_CAMP para una operacion en particular <br>
	 * Se utiliza en el servicio de aprobaciones y liberaciones masivas
	 *
	 * @param mapa con parametros de entrada
	 * Parametros: numOperProg, rutApoderado
	 * @throws SQLException
	 */
	void agregaCampoDetalleCampPortal(Map<String, Object> params) throws SQLException;
	
	
	/**
	 * Registra Firma de un apoderado en la tabla TBL_APO_OP <br>
	 * Se utiliza en el servicio de aprobaciones y liberaciones masivas
	 *
	 * @param mapa con parametros de entrada
	 * Parametros: datoFirma, nomApoderado, numOperProg, rutApoderado
	 * @throws SQLException
	 */
	void registraFirmaApoderado(Map<String, Object> params) throws SQLException;
	
	/**
	 * 
	 * @param params
	 * @throws SQLException
	 */
	void consultarUsuarioExcepcionado(Map<String, Object> params)  throws SQLException;
	

}