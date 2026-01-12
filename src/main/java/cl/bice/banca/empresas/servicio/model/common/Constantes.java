package cl.bice.banca.empresas.servicio.model.common;

/**
 * Constantes.
 *
 * @author Luis Basso S. (TINet)
 * @version 1.0
 */
public final class Constantes {

	// PARÃMETROS PARA LOGS EN EmpresasController
	public static final String PARAMETRO_EMPRESAS_LOG_INICIO = "[{}][{}] INICIO";
	public static final String PARAMETRO_EMPRESAS_LOG_ERROR = "ERROR: {}";
	public static final String PARAMETRO_EMPRESAS_LOG_ANTES_DE_RETORNAR = "[{}][{}] Antes que retorne";
	// PARÃMETROS PARA LOS INPUTS DE LOS SP QUE SE INVOCAN DESDE EmpresasController
	public static final String PARAMETRO_EMPRESAS_SP_V_RUT_USU = "v_rut_usu";
	public static final String PARAMETRO_EMPRESAS_SP_V_RUT_CLI = "v_rut_cli";
	public static final String PARAMETRO_EMPRESAS_SP_V_COD_SERVICO = "v_COD_SERVICO";
	
	public static final String PARAMETRO_EMPRESAS_V_LISTA_OPER_PROG = "v_lista_oper_prog";
	public static final String PARAMETRO_EMPRESAS_V_ESTADOS_APROB_LIB = "v_estados";
	public static final String PARAMETRO_EMPRESAS_V_COD_SERVICIO_PERFIL = "v_cod_servicio_perfil";
	public static final String PARAMETRO_EMPRESAS_V_VAL_CAMPO = "v_val_campo";
	public static final String PARAMETRO_EMPRESAS_V_NUM_OPER_PROG = "v_num_oper_prog";
	public static final String PARAMETRO_EMPRESAS_V_COD_CAMPO = "v_cod_campo";
	public static final String PARAMETRO_EMPRESAS_V_COD_ESTADO = "v_cod_estado";
	public static final String PARAMETRO_EMPRESAS_V_NUM_OPERACION_TRF = "v_num_operacion_trf";
	public static final String ACTUALIZAR_DETALLE_OPERACION_SIN_PROCESAR = "N°";
	

	public static final String PARAMETRO_EMPRESAS_SP_RUT_CLIENTE = "RUT_CLIENTE";
	public static final String PARAMETRO_EMPRESAS_SP_NOMBRES = "NOMBRES";
	public static final String PARAMETRO_EMPRESAS_SP_FLG_APODERADO = "FLG_APODERADO";
	public static final String PARAMETRO_EMPRESAS_SP_FLG_INICIO = "FLG_INICIO";
	public static final String PARAMETRO_EMPRESAS_SP_FLG_ADM_DELEGADO = "FLG_ADM_DELEGADO";

	// PARÃMETROS PARA LOS OUTPUTS DE LOS SP QUE SE INVOCAN DESDE EmpresasController
	public static final String PARAMETRO_EMPRESAS_SP_OUT_COD_RESULT = "OUT_COD_RESULT";
	public static final String PARAMETRO_EMPRESAS_SP_V_OUT_COD_RESULT = "v_OUT_COD_RESULT";
	public static final String PARAMETRO_EMPRESAS_SP_SALIDA = "SALIDA";
	public static final String PARAMETRO_EMPRESAS_SP_V_SALIDA = "v_salida";
	public static final String PARAMETRO_EMPRESAS_SP_V_ESTADO = "v_estado";
	public static final String PARAMETRO_EMPRESAS_SP_V_COD_SERVICIO = "v_cod_servicio";
	public static final String PARAMETRO_EMPRESAS_SP_V_FEC_DESDE = "v_fec_desde";
	public static final String PARAMETRO_EMPRESAS_SP_V_FEC_HASTA = "v_fec_hasta";
	

	public static final String PARAMETRO_EXISTE_FACULTAD_SP_OUT_COD_STATUS = "p_COD_STATUS";
	public static final String PARAMETRO_EXISTE_FACULTAD_SP_OUT_EXISTE = "p_EXISTE";
	public static final String PARAMETRO_EXISTE_FACULTAD_SP_OUT_MSG_STATUS = "p_MSG_STATUS";

	public static final String PARAMETRO_SP_VALIDACION_PODERES_RESPUESTA = "RESPUESTA";
	public static final String SP_VALIDACION_PODERES_CODIGORECHAZO = "CODIGORECHAZO";
	public static final String SP_VALIDACION_PODERES_DESCRIPCIONRECHAZO = "DESCRIPCIONRECHAZO";
	public static final String SP_VALIDACION_PODERES_FIRMA_COMPLETA = "000";
	public static final String SP_VALIDACION_PODERES_FIRMA_PARCIAL = "012";
	public static final String SP_VALIDACION_PODERES_RESPUESTA_SI = "SI";
	public static final String SP_VALIDACION_PODERES_RESPUESTA_NO = "NO";

	public static final String SP_REG_LOG_FACULTADES_FIRMA_COMPLETA_SI = "1";
	public static final String SP_REG_LOG_FACULTADES_FIRMA_COMPLETA_NO = "0";
	public static final String SP_REG_LOG_FACULTADES_FIRMA_PARCIAL_SI = "1";
	public static final String SP_REG_LOG_FACULTADES_FIRMA_PARCIAL_NO = "0";

	public static final String GLOSA_EXISTE_FACULTAD_SP_EXISTE_SI = "SI";

	public static final String SERVICIO_TPAG200_CODIGO_FIRMA_COMPLETA = "1";
	public static final String SERVICIO_TPAG200_SUB_CODIGO_FIRMA_COMPLETA = "1";
	public static final String SERVICIO_TPAG200_CODIGO_FIRMA_PENDIENTE = "96";
	public static final String SERVICIO_TPAG200_SUB_CODIGO_FIRMA_CORRECTA = "10";
	public static final String SERVICIO_TPAG200_SUB_CODIGO_FIRMA_INCORRECTA = "19";

	public static final int SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_FIRMA_COMPLETA = 50;
	public static final int SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_PARCIAL = 40;
	public static final int SERVICIO_OPERACION_EMPRESA_CODIGO_ESTADO_SIN_FIRMAR = 30;

	public static final String SERVICIO_OPERACION_EMPRESA_GLOSA_ESTADO_FIRMA_COMPLETA = "Firma completa";
	public static final String SERVICIO_OPERACION_EMPRESA_GLOSA_ESTADO_PARCIAL = "Firma correcta. Faltan firmas.";
	public static final String SERVICIO_OPERACION_EMPRESA_GLOSA_ESTADO_FIRMA_INCORRECTA = "Firma incorrecta. Faltan firmas.";

	public static final String SERVICIO_OPERACION_EMPRESA_COD_LBTR_SOMA = "835";
	public static final String SERVICIO_OPERACION_EMPRESA_COD_LBTR = "935";
	public static final String SERVICIO_OPERACION_EMPRESA_TIPO_SIMPLE = "Simple";
	public static final String SERVICIO_OPERACION_EMPRESA_TIPO_CCLV = "CCLV";
	public static final String SERVICIO_OPERACION_EMPRESA_TIPO_DVP = "DVP";
	public static final String SERVICIO_OPERACION_EMPRESA_TIPO_SIS = "SIS";
	public static final String SERVICIO_OPERACION_EMPRESA_TIPO_SOMA = "SOMA";

	public static final String VALIDAR_DESAFIO_ESTADO_EXITO = "EXITO";
	public static final String VALIDAR_DESAFIO_ESTADO_ERROR = "ERROR";
	public static final String VALIDAR_DESAFIO_ESTADO_PENDIENTE = "PENDIENTE";

	public static final String DESAFIO_FDA = "FDA";
	public static final String CLIENTE_SERVICIO_DESAFIO_BICEPASS = "TOKEN_SIGN_ONLINE";

	public static final String SERVICIO_LISTAR_CREAR_DESAFIOS_DESAFIO_FDA = "FDA";
	public static final String SERVICIO_LISTAR_CREAR_DESAFIOS_DESAFIO_BICEPASS = "BICEPASS";

	public static final String TBL_PARAM_COD_FACULTAD_NOMBRE = "filtro.tabla.parametros.nombre.cod-facultad";
	public static final String TBL_PARAM_COD_FACULTAD_TIPO = "filtro.tabla.parametros.tipo.cod-facultad";

	public static final String DISPOSITIVO = "dispositivo";
	public static final String TOKEN = "token";
	public static final String RUT = "rut";

	public static final String NUM_OPER_PROG = "NUM_OPER_PROG";
	public static final String RESPONSE = "RESPONSE";
	public static final String INDETALLE_SOAP = "INDETALLE_SOAP";
	public static final String INESTADO_SOAP = "INESTADO_SOAP";
	public static final String INNUMOPERTRF_SOAP = "INNUMOPERTRF_SOAP";
	public static final String ESTATUS_LIBERADA_160 = "160";
	public static final String COD_ESTADO_LIBERACION = "codLiberacion";
	public static final String ESTADO_LIBERACION = "estadoLiberacion";
	public static final String GLOSA_ESTADO_LIBERACION = "glosaLiberacion";
	public static final String COD_ESTADO_LIBERACION_NOK = "texto.cod.estado.liberacion.nok";
	public static final String ESTADO_LIBERACION_OK = "texto.estado.liberacion.ok";
	public static final String ESTADO_LIBERACION_NOK = "texto.estado.liberacion.nok";
	public static final String GLOSA_ESTADO_LIBERACION_OK = "texto.glosa.estado.liberacion.ok";
	public static final String GLOSA_ESTADO_LIBERACION_NOK_SISTEMA = "texto.glosa.estado.liberacion.nok.sistema";
	public static final String GLOSA_ESTADO_LIBERACION_NOK_SALDO1 = "texto.glosa.estado.liberacion.nok.saldo1";
	public static final String GLOSA_ESTADO_LIBERACION_NOK_HORA_LIMITE = "texto.glosa.estado.liberacion.nok.hora.limite";

	public static final String COD_CAMPO_RUT_LIBERADOR = "200";
	public static final String COD_CAMPO_NOM_LIBERADOR = "631";
	public static final String COD_CAMPO_FECHA_HORA_LIBERACION = "535";
	public static final String COD_CAMPO_CANAL_LIBERACION_PORTAL = "549";
	public static final String COD_CAMPO_ESTADO_PROCESO = "2";
	public static final String VAL_CAMPO_ESTADO_PROCESADA = "PROCESADA";
	public static final String VAL_CAMPO_ESTADO_NO_PROCESADA = "N°";
	public static final String GLOSA_EXEC_GFS = "EXEC_GFS";

	public static final String ACTUALIZAR_DETALLE_OPERACION_DETALLE_PROCESANDO = "2=PROCESANDO;";
	public static final String ACTUALIZAR_DETALLE_OPERACION_DETALLE_EN_PROCESO = "2=EN_PROCESO;";
	public static final String ACTUALIZAR_DETALLE_OPERACION_DETALLE_NO_PROCESANDO = "2=N°;";

	public static final String COD_CAMPO_CARGO_EN_LINEA = "46";
	public static final String COD_CAMPO_ABONO_EN_LINEA = "47";
	public static final String COD_CAMPO_CANAL_LIBERACION = "80";
	public static final String COD_CAMPO_GLOSA_ABONO_EN_LINEA = "635";

	public static final Integer NUMERIC_CERO = 0;
	public static final String CONSTANTE_CERO = "0";
	public static final String CONSTANTE_UNO = "1";
	public static final String CONSTANTE_MIL = "1000";

	public static final String CANAL_SOAP = "CANAL_SOAP";
	public static final String SUCURSAL_SOAP = "SUCURSAL_SOAP";
	public static final String MODOTRX_SOAP = "MODOTRX_SOAP";
	public static final String MODOINVOCACION_SOAP = "MODOINVOCACION_SOAP";
	public static final String RUTCLIENTE_SOAP = "RUTCLIENTE_SOAP";
	public static final String TRACENUMBER_SOAP = "TRACENUMBER_SOAP";
	public static final String FECHACONTABLE_SOAP = "FECHACONTABLE_SOAP";

	public static final String MONEDACARGO_SOAP = "MONEDACARGO_SOAP";
	public static final String BANCOCARGO_SOAP = "BANCOCARGO_SOAP";
	public static final String CUENTACARGO_SOAP = "CUENTACARGO_SOAP";
	public static final String TIPOCUENTACARGO_SOAP = "TIPOCUENTACARGO_SOAP";
	public static final String MONTOCARGO_SOAP = "MONTOCARGO_SOAP";

	public static final String RUTABONO_SOAP = "RUTABONO_SOAP";
	public static final String NOMBREABONO_SOAP = "NOMBREABONO_SOAP";
	public static final String MONEDAABONO_SOAP = "MONEDAABONO_SOAP";
	public static final String CUENTABONO_SOAP = "CUENTABONO_SOAP";
	public static final String TIPOCUENTABONO_SOAP = "TIPOCUENTABONO_SOAP";

	public static final String IDSERVICIO_SOAP = "IDSERVICIO_SOAP";
	public static final String REFERENCIA_SOAP = "REFERENCIA_SOAP";
	public static final String DOCCARGO_SOAP = "DOCCARGO_SOAP";
	public static final String DOCABONO_SOAP = "DOCABONO_SOAP";

	public static final String VALIDAR_CUENTA = "VALIDAR_CUENTA";

	public static final String RUT_CLIENTE = "rutCliente";

	public static final String CANAL = "canal";
	public static final String ID_SERVICIO_MDW = "idServicioMdw";
	public static final String MONEDA = "moneda";
	public static final String MONTO = "monto";
	public static final String RUT_CARGO = "rutCargo";
	public static final String NOM_CARGO = "nomCargo";
	public static final String CUENTA_CARGO = "cuentaCargo";
	public static final String RUT_ABONO = "rutAbono";
	public static final String CUENTA_ABONO = "cuentaAbono";
	public static final String NOM_USUARIO = "nomUsuario";
	public static final String MAIL_CARGO = "mailCargo";
	public static final String MAIL_ABONO = "mailAbono";
	public static final String GLS_TIP_TEF = "glsTipTef";
	public static final String GLS_TIPO_DISP = "glsTipoDisp";
	public static final String GLS_CLAVE_ENCRIPTADA_DISP = "glsDesafio";
	public static final String GLS_DESCRIPCION = "glsDescripcion";
	public static final String ID_SESION = "idSesion";
	public static final String GLS_TIPO_CLIENTE = "glsTipocliente";
	public static final String NOM_ABONO = "nomAbono";
	public static final String NUM_NOMINA = "numNomina";

	public static final String GLS_CONCEPTO = "glsConcepto";

	public static final String E001 = "9001";
	public static final String ERROR_MSJ_E001 = "msj-error.e1.general";

	public static final String E002 = "9002";
	public static final String ERROR_MSJ_E002_DB = "msj-error.e2.DB";

	public static final String E003 = "9003";
	public static final String ERROR_MSJ_E003_CLIENTE_TO = "msj-error.e3.TO";

	public static final String E004 = "9004";
	public static final String ERROR_MSJ_E004_CLIENTE_OPERACIONES = "msj-error.e4.cliente.operaciones";

	public static final String E005 = "9005";
	public static final String ERROR_MSJ_E005_CLIENTE_OPERACIONES = "msj-error.e5.cliente.operaciones";

	public static final String E006 = "9006";
	public static final String ERROR_MSJ_E006_CLIENTE_GFS = "msj-error.e6.cliente.gfs";
	public static final String ERROR_MSJ_E006_CLIENTE_GFS_SALDO = "msj-error.e6.cliente.gfs.saldo";

	public static final String E007 = "9007";
	public static final String ERROR_MSJ_E007 = "msj-error.e7.cliente.comprobante";

	public static final String E008 = "9008";
	public static final String ERROR_MSJ_E008_VALIDAR_DESAFIOS = "msj-error.e8.desafio";

	public static final String E009 = "9009";
	public static final String ERROR_MSJ_E009_CLIENTE_GFS = "msj-error.e9.cliente.gfs";

	public static final String RESPONSE_STATUS = "RESPONSE_STATUS";
	public static final String RESPONSE_GLOSA = "RESPONSE_GLOSA";
	public static final String RESPONSE_STATUS_INSTRUCCION = "RESPONSE_STATUS_INSTRUCCION";
	public static final String RESPONSE_GLOSA_STATUS_INSTRUCCION = "RESPONSE_GLOSA_STATUS_INSTRUCCION";

	public static final String RESPONSE_GLOSA_PROPERTIES = "RESPONSE_GLOSA_PROPERTIES";
	public static final String FLAG_REVERSA_GFS = "FLAG_REVERSA_GFS";

	public static final String BICE = "BICE";
	public static final String COD_BANCO_BICE_28 = "28";
	public static final String COD_BANCO_BICE_028 = "028";
	public static final String CANAL_INTERNET = "INTERNET";
	public static final String SUCURSAL = "001";
	public static final String COD_ESTADO_REVERSA = "50";
	public static final String COD_ESTADO_LIBERADA = "160";
	public static final String COD_ESTADO_PROCESADO = "70";
	public static final String COD_ESTADO_RECHAZADA = "80";
	public static final String COD_TIPO_CTA = "0100";
	public static final String MODOTRX_DIRECTA = "D";
	public static final String MODOTRX_REVERSA = "R";
	public static final String MONEDACLP = "CLP";
	public static final String MODO_INVOCACION_SIMPLE = "S";
	public static final String MODO_INVOCACION_MULTIPLE = "M";

	public static final String GFS_CODIGO_RETORNO_APROBACION_OK = "0";

	public static final String GFS_PROPERTY_FECHA_CONTABLE_VTRX = "gfs.vtrx";
	public static final String GFS_PROPERTY_ID_SERVICIO_CARGO = "gfs.idservicio";

	public static final String FORMAT_YYYYMMDD = "yyyyMMdd";
	public static final String FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmss.SSS";
	public static final String FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String FORMAT_YYMMDDHHMMSS = "yyMMddHHmmss";
	public static final String FORMAT_YYYYMMDDHHMM = "yyyyMMddHHmm";
	public static final String FORMAT_DD_MM_YYYY = "dd-MM-yyyy";
	public static final String FORMAT_DD_MM_YYYY2 = "yyyy/MM/dd";
	public static final String FORMAT_DD_MM_YYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
	public static final String FORMAT_DD_MM_YYYY_HHMMSS2 = "dd-MM-yyyy HH:mm:ss";

	public static final String FLAG_FECHA_CONTABLE = "flag.fechacontable";
	public static final String FLAG_FECHA_DATA = "flag.fechacontable.fecha";

	public static final String CORREO_ERR_TIPO_REVERSA_GFS = "REMESAMX-ERR-REVERSA-GFS";
	public static final String ERROR_MSJ_E004_CLIENTE_TO = "texto.errorE004.empresas.liberar.operaciones";
	public static final String ERROR_MSJ_E005_CLIENTE_GFS = "texto.errorE005.empresas.liberar.operaciones";

	// Propiedades correo
	public static final String TASAS_URI = "servicios.tasas.uri";
	public static final String TASAS_CONSULTA_TASAS_SOAP_ACTION = "servicios.tasas.consultaTasas.soapAction";
	public static final String TASAS_GET_TASAS_SOAP_ACTION = "servicios.tasas.getTasas.soapAction";
	public static final String CONSULTA_TIPO_DEPOSITOS_SOAP_ACTIION = "servicios.tasas.consultaDepositos.soapAction";
	public static final String TOMA_DEPOSITO_URI = "servicios.toma.deposito.uri";
	public static final String TOMA_DEPOSITO_ACTION = "servicios.toma.deposito.soapAction";
	public static final String REGISTRA_DEPOSITO_URI = "servicios.registra.operacion.uri";
	public static final String REGISTRA_DEPOSITO_ACTION = "servicios.registra.operacion.soapAction";
	public static final String DEPOSITOS_URI = "servicios.depositos.uri";
	public static final String DEPOSITOS_SOAP_ACTION = "servicios.deposutos.soapAction";
	public static final String MINIMO_DEPOSITO = "minimo.deposito.plazo";
	public static final String VALIDACION_CLAVE_FLAG = "validacion.clave.flag";
	public static final String VALIDACION_TOPE_DEPOSITO = "servicios.validacion.tope.deposito";
	public static final String SERVICIO_SECURITY_SERVICE = "servicio.security.service";
	public static final String URL_SERVICIO_VALIDA_CLAVE = "url.servicio.valida.clave";
	public static final String DIAS_OBTENCION_TASAS = "arreglo.dias.tasas";
	public static final String FLAG_ENVIA_CORREO = "flag.send.mail";
	public static final String SMTP_HOST = "mail.smtp.host";
	public static final String STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	public static final String SMTP_PORT = "mail.smtp.port";
	public static final String SMTP_USER = "mail.smtp.user";
	public static final String SMTP_AUTH = "mail.smtp.auth";
	public static final String FROM_EMAIL = "mail.send.from";
	public static final String TO_EMAIL = "mail.send.to";
	public static final String SMTP_TIMEOUT = "mail.smtp.timeout";
	public static final String SMTP_CONNECTION_TIMEOUT = "mail.smtp.connectiontimeout";
	public static final String VALIDACION_DIA_HABIL = "servicio.validacion.dia.habil";

	public static final String VAL_PARAMETRO = "valParametro";
	public static final String VAL_PARAMETRO2 = "valParametro2";
	public static final String VAL_PARAMETRO3 = "valParametro3";
	public static final String VAL_PARAMETRO4 = "valParametro4";

	// Estados de nomina en linea

	public static final int NOMINA_EN_LINEA_ESTADO_REGISTRADA = 0;

	public static final int NOMINA_EN_LINEA_ESTADO_CERRADA = 1;

	public static final int NOMINA_EN_LINEA_ESTADO_APROBADA_PARCIAL = 2;

	public static final int NOMINA_EN_LINEA_ESTADO_APROBADA = 3;

	public static final int NOMINA_EN_LINEA_ESTADO_LIBERADA = 4;

	public static final int NOMINA_EN_LINEA_ESTADO_ANULADA = 5;

	public static final int NOMINA_EN_LINEA_ESTADO_EN_ESPERA_CCA = 6;

	public static final int NOMINA_EN_LINEA_ESTADO_PROCESADA = 7;

	public static final int NOMINAS_EN_LINEA_REVERSAR_LIBERAR = 160;

	public static final int NOMINAS_EN_LINEA_NO_REVERSA_ESTADO = 0;

	public static final int NOMINAS_EN_LINEA_TIPO_OPERACION_CARGO = 1;

	// Inicio CODIGOS Y MENSAJES DE RESPUESTAS HTTP.
	/**
	 * Codigo OK.
	 */
	public static final int CODIGO_OK = 200;

	/**
	 * Codigo OK pero que no retorna Body.
	 */
	public static final int CODIGO_OK_SIN_CONTENIDO = 204;

	/**
	 * Codigo cuando existe error con los datos ingresados.
	 */
	public static final int CODIGO_NOK_SOLICITUD_INVALIDA = 400;

	/**
	 * Codigo No OK para el caso de que tenga el acceso prohibido.
	 */
	public static final int COD_NOK_PROHIBIDO = 403;

	/**
	 * Codigo cuando existe error con los datos ingresados.
	 */
	public static final int CODIGO_NOK_SOLICITUD_SIN_RESULTADO = 404;

	/**
	 * Codigo cuando existe error en el servidor principalmente BD.
	 */
	public static final int CODIGO_NOK_ERROR_INTERNO = 500;

	/**
	 * Codigo No OK no implementado.
	 */
	public static final int CODIGO_NOK_NO_IMPLEMENTADO = 501;

	// Termino CODIGOS Y MENSAJES DE RESPUESTAS HTTP.

	/**
	 * ENCODING.
	 */
	public static final String ENCODING = "UTF-8";

	// Inicio Constantes que se utilizan para validar el rut.

	/**
	 * Numero de la forma Rut.
	 */
	public static final int FORMULA_RUT_ONCE = 11;

	/**
	 * Codigo del char slash.
	 */
	public static final int CHAR_SLASH = 47;

	/**
	 * Numero de la forma Rut.
	 */
	public static final int FORMULAR_RUT_SEIS = 6;

	/**
	 * Numero de la forma Rut.
	 */
	public static final int FORMULA_RUT_NUEVE = 9;

	/**
	 * Codigo del char k.
	 */
	public static final int CHAR_K = 75;

	/**
	 * Largo maximo del rut.
	 */
	public static final int LARGO_RUT = 10;

	/**
	 * llave del properties que contiene la url de tarjetas de credito.
	 */
	public static final String NOTIFICACIONES_TARJETAS_URL = "servicio.tarjetas.url";

	// Monedas

	/**
	 * Codigo de moneda Pesos.
	 */
	public static final String CODIGO_MONEDA_PESOS = "000";

	/**
	 * Codigo de moneda USD.
	 */
	public static final String CODIGO_MONEDA_USD = "013";

	/**
	 * Codigo de moneda Euro.
	 */
	public static final String CODIGO_MONEDA_EURO = "142";

	/**
	 * Codigo de moneda UF.
	 */
	public static final String CODIGO_MONEDA_UF = "300";

	// Termino Constantes que se utilizan para validar el rut.

	/**
	 * Indice del corte de la tercera seccion de las tarjetas.
	 */
	public static final int TERCER_CORTE_TARJETA = 12;

	/**
	 * Indice del corte de la segunda seccion de las tarjetas.
	 */
	public static final int SEGUNDO_CORTE_TARJETA = 6;

	/**
	 * Indice del corte de la primera seccion de las tarjetas.
	 */
	public static final int PRIMER_CORTE_TARJETA = 4;

	/**
	 * Indice del inicio de la tarjeta.
	 */
	public static final int INICIO_TARJETA = 0;

	/**
	 * Codigo de la cuenta corriente.
	 */
	public static final int CODIGO_PRODUCTO_CUENTA_CORRIENTE_MN = 100;

	public static final String CANAL_MOBILE_EMPRESA = "ME";
	public static final String CANAL_PORTAL_EMPRESA = "IE";
	public static final String CANAL_PORTAL_EMPRESA_TEF_MASIVA = "IE_MS";

	public static final String SERVICIO_DETALLE_AUTORIZACION_ATRIBUTO_RESPUESTA_PDF_BASE64 = "pdfBase64";

	public static final String CODIGO_SERVICIO_LBTR = "935";

	public static final String CODIGO_SERVICIO_NOMINAS_EN_LINEA = "5117";
	/**
	 * NÃ³minas de Pago de Dividendo de Acciones
	 */
	public static final String CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_DIVIDENDO_ACCIONES = "198";
	/**
	 * NÃ³minas de Pago a Proveedores
	 */
	public static final String CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_PROVEEDORES = "199";
	/**
	 * NÃ³minas de Pago de Remuneraciones
	 */
	public static final String CODIGO_SERVICIO_NOMINAS_DIFERIDAS_PAGO_REMUNERACIONES = "200";

	public static final String CODIGO_SERVICIO_TEF_MX = "1935";

	public static final String CODIGO_SERVICIO_SPOTWEB = "2935";
	
	/**
	 * BiceComex
	 */
	public static final String CODIGO_SERVICIO_BICECOMEX = "1271";
	
	public static final String BICECOMEX_ESTADO_EN_PROCESO = "En Proceso";
	public static final String BICECOMEX_ESTADO_PENDIENTE = "Pendiente";
	public static final String BICECOMEX_ESTADO_PENDIENTE_FIRMA = "Pendiente Firma o Envio";
	public static final String BICECOMEX_ESTADO_ENVIADA = "Enviada al Banco";
	public static final String BICECOMEX_ESTADO_APROBADA = "Aprobada";
	public static final String BICECOMEX_ESTADO_LIBERADA = "Liberada";
	public static final String BICECOMEX_ESTADO_CONSULTADA = "CONSULTADA";
	public static final String BICECOMEX_ESTADO_EN_PROCESO_APROBACION = "EN_PROCESO";
	public static final String BICECOMEX_ESTADO_FIRMA_SIMULTANEA_APROBACION = "EN_PROCESO_FIRMA_POR_OTRO_APODERADO";
	
	public static final String BICECOMEX_ESTADO_CON_TODOS = "Todos";
	
	public static final String GLOSA_MONEDA_PESOS = "CLP";
	public static final String GLOSA_MONEDA_USD = "USD";
	public static final String GLOSA_MONEDA_EURO = "EUR";
	public static final String GLOSA_MONEDA_UF = "UF";
	
	public static final String CANAL_PORTAL = "IE";
	
	public static final String VALOR2 = "VALOR2";
	
	public static final String SERVICIOS_NOM_PARAM_MTO_MAX_TEF_SIN_FDA = "servicios.nom.param.monto.max.tef.sin.fda.";
	public static final String SERVICIOS_NOM_TIPO_MTO_MAX_TEF_SIN_FDA = "MONTOMINFDA";

	public static final String TRX_LOCK_SUCESS = "Transacción [{}] bloqueada correctamente (Empresa [{}])";
	public static final String TRX_LOCK_ERROR = "Error al bloquear transacción: ";
	public static final String TRX_UNLOCK_SUCESS = "Transacción [{}] desbloqueada correctamente (Empresa [{}])";
	public static final String TRX_UNLOCK_ERROR = "Error al desbloquear transacción: ";
	
	
	public static final String MS_AUTENTICACION_OPER_COD_OPERACION = "TEF";
	public static final String MS_AUTENTICACION_OPER_TIPO_OPERACION_APROBAR = "TEF_ALTO_VALOR_APROBAR";
	public static final String MS_AUTENTICACION_OPER_TIPO_OPERACION_LIBERAR = "TEF_ALTO_VALOR_LIBERAR";
 
	//Parametros TEF LBTR MN
	public static final String TEF_LBTR_ESTADOS_APROBAR = "30,40";
	public static final String TEF_LBTR_ESTADOS_LIBERAR = "50";
	public static final int TEF_LBTR_SRV_PERFIL_APROBAR = 937;
	public static final int TEF_LBTR_SRV_PERFIL_LIBERAR = 938;
	public static final String TEF_LBTR_GLS_OK_APROBAR = "Firma Completa";
	public static final String TEF_LBTR_GLS_NOK_APROBAR = "Firma no realizada";
	public static final String TEF_LBTR_GLS_OK_LIBERAR = "Liberacion OK";
	public static final String TEF_LBTR_GLS_NOK_LIBERAR = "Liberacion no realizada";
	public static final String CLAVE_PIN_BICE = "PIN_BICE";
	public static final String GLS_ESTADO_FIRMA_PENDIENTE = "Firma pendiente";
	public static final String GLS_ESTADO_FIRMA_PARCIAL = "Firmado parcialmente";
	public static final String GLS_ESTADO_FIRMADO = "Firmado";
	public static final String GLS_FIRMA_YA_REGISTRADA = "Apoderado ya registra una firma para la operacion";
	public static final int    COD_ESTADO_FIRMA_YA_REGISTRADA = -1;
	public static final int    COD_ESTADO_OPERPROG_YA_LIBERADA = -2;
	public static final int    COD_ESTADO_OPERPROG_ERROR_LIBERADA = -3;
	public static final int    COD_ESTADO_OPERPROG_ERROR_PEND_LIBERAR = -4;
	public static final int    COD_ESTADO_ERROR_EXEC_CARTOLA = -5;
	public static final int    COD_ESTADO_ERROR_EXEC_GFS = -6;
	public static final int    COD_ESTADO_ERROR_OPER_PROCESADA = -7;
	public static final int    COD_ESTADO_ERROR_UPD_DETALLE_CAMP = -8;
	public static final int    COD_ESTADO_ERROR_LIB_FECHA_VALUTA = -9;
	public static final int    COD_ESTADO_ERROR_LIB_SALDO_CTA = -10;
	public static final int    COD_ESTADO_ERROR_LIB_SALDO_CCLV = -11;
	public static final int    COD_ESTADO_ERROR_UPD_OPER_PROG = -12;
	public static final int    COD_ESTADO_ERROR_GET_PARAM_CARGO_LINEA = -13;
	public static final int    COD_ESTADO_ERROR_GFS_EJECUTANDOSE = -14;
	public static final int    COD_ESTADO_ERROR_EXEC_MASCARA = -15;
	public static final int    VALOR_CERO = 0;
	public static final String GLS_ERROR_SALDO_CTA = "ERROR_SALDO_CTA";
	public static final String GLS_OPER_PROG_YA_LIBERADA = "Operacion ya se encuentra liberada";
	public static final String GLS_OPER_PROG_ERROR_LIBERADA = "Error al obtener detalle operprog, Operacion no pudo ser liberada";
	public static final String GLS_OPER_PROG_ERROR_EST_LIBERADA = "Operacion tiene un estado incorrecto para ser liberada";
	public static final String GLS_OPER_PROG_ERROR_EST_LIBERADA_INC = "Operacion tiene datos incorrectos";
	public static final String GLS_OPER_PROG_ERROR_APROBADA = "Operacion tiene un estado incorrecto para ser aprobada";
	public static final String GLS_OPER_SIN_SFA = "operacion no registra validacion con SFA";
	public static final String GLS_OPER_SIN_PIN_BICE = "operacion no registra validacion con clave internet";
	public static final String CAMPO_NUM_OPER_PROG = "NUM_OPER_PROG";
	
	public static final String VALOR_CON_CEROS = "00000000000";
	public static final String STR_RETURN_VALUE = "RETURN_VALUE";
	public static final String STR_V_SALIDA = "v_SALIDA";
	public static final String STR_V_COD_RESULT = "v_COD_RESULT";
	public static final String STR_P_OUT_COD_RESULT = "P_OUT_COD_RESULT";
	
	//Variables para nombres de campo del SP de consulta de aprobaciones y liberaciones masivas
	public static final String NOM_CAMPO_MONTO = "Monto";
	public static final String NOM_CAMPO_ESTADO = "Estado";
	public static final String NOM_CAMPO_FECHA_VALUTA = "FechaValuta";
	public static final String NOM_CAMPO_RUT_ABONO = "RutAbono";
	public static final String NOM_CAMPO_CTA_ABONO = "NumCuentaAbono";
	public static final String NOM_CAMPO_BANCO_BENEF = "NombreBancoBeneficiario";
	public static final String NOM_CAMPO_REFERENCIA = "Referencia";
		
	

	public static final String PARAMETRO_NOM_PARAM = "v_nom_parametro";
	public static final String VALOR_NOM_PARAM = "SERVICIOSLBTR";
	public static final String COD_RESULTADO_VALIDA_OK = "0";
	public static final String GLS_RESULTADO_VALIDA_OK = "Validacion OK";
	public static final String COD_RESULTADO_VALIDA_ERROR = "-1";
	public static final String GLS_RESULTADO_VALIDA_ERROR = "Error al realizar Validacion de parametros";
	public static final String NOM_TIPO_MONTO_FDA = "MONTOMINFDA";
	public static final String COD_RESULTADO = "COD_RESULTADO";
	public static final String GLS_RESULTADO = "GLS_RESULTADO";
	public static final String SFA_FDA_ESIGN = "ESIGN";
	public static final String SFA_FDA_CERTINET = "CERTINET";
	public static final String MSG_EXCEDE_MONTO_SIN_FDA = "Para aprobar transacciones superiores a $&MONTO  es necesario disponer de Firma Digital Avanzada";
	public static final String VALOR_DEFAULT_DET_CAMP = "0000000000";
	public static final String COD_TRX_361_CARGO_LINEA_LBTR = "361";
	public static final String COD_TRX_362_CARGO_LINEA_LBTR = "362";
	public static final String CARGO_YA_EXISTE = "CARGO_YA_EXISTE";
	public static final String CARGO_NO_EXISTE = "CARGO_NO_EXISTE";
	public static final String SIN_MOVIMIENTOS = "SIN_MOVIMIENTOS";
	public static final String GLS_ERROR_GENERICO = "ERROR";
	public static final String GLS_EN_PROCESO = "EN_PROCESO";
	public static final String FIRMA_YA_EXISTE = "Apoderado ya registra una firma previa";
	public static final int    FLG_ES_APODERADO = 1;
	public static final int    FLG_ES_ADM_DELEGADO = 1;
	public static final int    RUT_SEGMENTO_EMPRESA = 50000000;
	public static final String USUARIO_NO_EXISTE = "USUARIO_NO_EXISTE";
	public static final String USUARIO_SIN_PERMISO_APROBAR = "USUARIO_SIN_PERMISO_APROBAR";
	public static final String USUARIO_CON_PERMISO_APROBAR = "USUARIO_CON_PERMISO_APROBAR";
	public static final String GLS_ERROR_SRV_FISBAN = "Error al ejecutar Servicio Fisban";
	public static final String GLS_ERROR_SRV_AUTENTICACION = "Servicio Autenticacion no disponible";
	public static final String GLS_ERROR_EXEC_SRV_FISBAN = "Error al intentar validar poderes";
	public static final String GLS_ERROR_VALIDA_HORARIO_APROB = "Error al intentar validar Horario Tope Aprobacion";
	public static final String GLS_ERROR_VALIDA_TEF_EXCLUIDA = "Tipo de Operacion no esta permitida para su aprobacion";
	
	//Variables para cartola provisoria y GFS
	public static final String TIPO_MOV_CARTOLA_PROVISORIA = "N";
	public static final String COD_RESULTADO_EXITOSO_CARTOLA_PROV = "0";
	public static final String COD_RESULTADO_EXITOSO = "0";
	public static final String MAS_PAGINAS_PENDIENTES = "Y";
	public static final String GLS_ERROR_EXEC_CARTOLA = "Error en el servicio de Cartola Provisoria";
	public static final String GLS_ERROR_EXEC_GFS = "Error en el servicio GFS";
	public static final String GLS_ERROR_OPER_PROCESADA = "Operacion no puede ser procesada ";
	public static final String GLS_ERROR_UPD_DETALLE_CAMP = "Error el intentar actualizar detalle operacion";
	public static final String GLS_ERROR_LIB_FECHA_VALUTA = "Operacion no puede ser liberada ya que excede el horario";
	public static final String GLS_ERROR_LIB_SALDO_CTA = "Error al obtener saldos Lbtr";
	public static final String GLS_ERROR_LIB_SALDO_CCLV = "Saldo Cuenta Paragua CCLV insuficiente";
	public static final String GLS_ERROR_UPD_OPER_PROG = "Error al intentar actualizar estado de operprog";
	public static final String GLS_ERROR_INS_FIRMA_APO = "Error al intentar registrar firma de apoderado";
	public static final String GLS_ESTADO_LIB_INVALIDO = "ESTADO_LIB_INVALIDO";
	public static final String GLS_ERROR_GENERICO_LIBERADA = "Operacion no pudo ser liberada";
	public static final String GLS_OPC_SI = "SI";
	public static final String GLS_OPC_NO = "NO";
	public static final String GLS_ERROR_GET_PARAM_CARGO_LINEA = "Error al obtener parametros para validar cargo en linea";
	public static final String GLS_ERROR_GET_PARAM_CAMPO_2 = "Error al obtener parametro campo 2 en tabla detalle";
	public static final String GLS_ERROR_OPER_EXEC_GFS = "Operacion no puede ser liberada ya que esta siendo procesada por otro usuario";
	public static final String GLS_ERROR_DATA_DETALLE = "Operacion no puede ser aprobada ya que presenta datos inconsistentes en el detalle (concurrencia con otro usuario)";
	public static final String GLS_ERROR_ESTADO_APROB = "Operacion no puede ser aprobada ya que tiene un estado incorrecto";
	public static final String GLS_OK = "OK";
	public static final String FLAG_ERROR_UPD_DET_CAMP = "FLAG_ERROR_UPD_DET_CAMP";
	public static final String FLAG_ERROR_UPD_OPER_PROG = "FLAG_ERROR_UPD_OPER_PROG";
	public static final String GLS_LOG_ERROR = "ERROR: [{}]";
	public static final String O_COD_RESULT = "O_COD_RESULT";
	public static final String O_MSG_RESULT = "O_MSG_RESULT";
	public static final String NOM_TIPO_HORA_LIM_INGRESO = "HORALIMITEINGRESO";
	public static final String MSG_EXCEDE_HORARIO_LIMITE_APROB = "Horario limite de aprobacion esta excedido (&HORARIO_LIMITE hrs.)";
	public static final String NOM_TIPO_CARGO_ONLINE_TODOS = "CARGO_ONLINE_TODOS";
	public static final String GLS_ERROR_SIN_PERTENENCIA = "Las operaciones indicadas no pueden ser procesadas ya que no cumplen con los criterios de pertenencia";
	public static final String GLS_ERROR_OPER_SIN_PERTENENCIA = "La operacion no cumple con los criterios de pertenencia";
	public static final String TIPO_LBTR_EXCLUIDA = "TIPO_LBTR_EXCLUIDA";
	public static final String GLS_BANCO_CENTRAL = "Banco Central De Chile";
	public static final String VALOR_NOM_PARAM_TEF_SIS = "CONSULTASSIS";
	public static final String VALOR_NOM_TIPO_RUT_CIA = "RUTCIA";
	public static final String VALOR_NOM_TIPO_TIPO_PAGO = "TIPOPAGO";
	public static final String VALOR_NOM_TIPO_NUM_CONTRATO = "NROCONTRATO";
	public static final String VALOR_NOM_PARAM_CCLV = "CCLV";
	public static final String VALOR_NOM_TIPO_RUT_CCLV = "RUTCCLV";
	public static final String PARAM_V_NUM_OPER_PROG = "V_NUM_OPER_PROG";
	
	public static final String PARAMETRO_EMPRESAS_V_DATO_FIRMA = "v_dato_firma";
	public static final String PARAMETRO_EMPRESAS_V_NOM_APODERADO = "v_nom_apoderado";
	public static final String PARAMETRO_EMPRESAS_V_RUT_APODERADO = "v_rut_apoderado";
	public static final String VAL_CAMPO_NO_BLOQUEADO = "N";
	public static final String GLS_ERROR_SRV_MASCARA = "Error al ejecutar el servicio de mascara para encriptar dato";
	
	/**
	 * Codigo que indica que usuario se encuentra excepcionado para consultar si se
	 * encuentra enrolado en ESIGN.
	 */
	public static final int CODIGO_RESP_NO_CONS_ENROLAMIENTO = 1;

	/**
	 * Codigo de error que indica que ocurrio una excepción al consultar servicio de
	 * enrolamiento en ESIGN.
	 */
	public static final int ERROR_CONSULTA_SRV_ENROLAMIENTO = 2;

	/**
	 * Codigo de error que indica que respuesta de la consulta del servicio de
	 * enrolamiento en ESIGN responde un null.
	 */
	public static final int ERROR_RESPUESTA_SRV_ENROLAMIENTO = 3;

	/**
	 * Codigo de error general en consulta de enrolamiento.
	 */
	public static final int ERROR_CONSULTA_ENROLAMIENTO = 4;

	/**
	 * Codigo que indica que usuario no valida enrolamiento en ESIGN.
	 */
	public static final int USUARIO_NO_VALIDA_ENROLAMIENTO = 5;

	/**
	 * Glosa que indica que usuario no valida enrolamiento en ESIGN.
	 */
	public static final String GLS_USUARIO_NO_VALIDA_ENROLAMIENTO = "Usuario excepcionado, no se consultara si se encuentra enrolado en ESIGN";
	
	/**
	 * Parametro input sp valida usuario excepcionado para no consultar enrolamiento
	 * en ESIGN.
	 */
	public static final String VAL_EXCEP_CONS_ENROLAMIENTO_SP_PI_RUT = "PI_RUT";

	public static final String VAL_EXCEP_CONS_ENROLAMIENTO_SP_PO_STS = "PO_STS";

	public static final String VAL_EXCEP_CONS_ENROLAMIENTO_SP_PO_MSG = "PO_MSG";
	
	/**
	 * Constructor.
	 */
	private Constantes() {
	}
}