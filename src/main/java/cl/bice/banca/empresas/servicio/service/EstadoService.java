package cl.bice.banca.empresas.servicio.service;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.model.response.Estado;
import cl.bice.banca.empresas.servicio.util.EstadoEnum;

/**
 * Clase encargada de inicializar y actualizar el estado (código estado y glosa
 * estado). Con el enum EstadoEnum podemos obtener los distintos códigos de
 * estado (exito y error) y el parámetro a través del cual podemos obtener desde
 * el archivo properties el texto de la glosa del estado.
 * 
 * @author Cristian Pais (TINET)
 *
 */
@Service
public class EstadoService {

	/**
	 * Propiedades Externas.
	 */
	@Autowired
	protected Properties propiedadesExterna;

	public static final String GLOSA_ERROR_REQUEST_CAMPO_CODIGO_SERVICIO = " en el campo codigoServicio";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_CODIGO_PRODUCTO = " en el campo codigoProducto";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_NUMERO_OPERACION = " en el campo numeroOperacion";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_LISTA_OPERACIONES = " en el campo listaOperaciones";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_NOMBRE_APODERADO = " en el campo nombreApoderado";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_ID_TRANSACCION = " en el campo idTransaccion";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_CUENTA = " en el campo cuenta";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_MONEDA = " en el campo moneda";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_TIPO = " en el campo tipo";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_GLS_TIPO_DISP = " en el campo glsTipoDisp";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_GLS_DESAFIO = " en el campo glsDesafio";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_GLS_TIPO_CLIENTE = " en el campo glsTipocliente";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_MAPA_DESAFIO = " en el campo mapaDesafio";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_DISP_FIRMA = " en el campo dispositivoFirma";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_FEC_DESDE = " en el campo fechaDesde";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_FEC_HASTA = " en el campo fechaHasta";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_FEC_DESDE_VAL = ", el campo fechaDesde no puede estar vacio";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_FEC_HASTA_VAL = ", el campo fechaHasta no puede estar vacio";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_FECHAS_1 = ", el campo fechaDesde no puede ser mayor al campo fechaHasta";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_FECHAS_2 = ", el campo fechaHasta no puede ser menor al campo fechaDesde";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_FECHAS_3 = ", el campo fechaDesde debe ser numerico";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_FECHAS_4 = ", el campo fechaHasta debe ser numerico";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_FECHAS_5 = ", los campos fechaDesde y fechaHasta deben ser numericos";
	public static final String GLOSA_ERROR_REQUEST_CAMPO_FECHAS_6 = ", el campo fechaDesde no puede ser menor a la fecha actual";

	
	/**
	 * Inicializa el objeto de la clase Estado con el código de estado y la glosa de
	 * estado correspondientes a EXITO.
	 * 
	 * @return estado inicializado en EXITO.
	 */
	public Estado inicializarEstado() {
		Estado estado = new Estado();
		estado.setCodigoEstado(EstadoEnum.EXITO.getCodigoEstado());
		estado.setGlosaEstado(propiedadesExterna.getProperty(EstadoEnum.EXITO.getParametroGlosaEstado()));
		return estado;
	}

	/**
	 * Setea codigo y glosa de un determinado error al objeto de clase Estado.
	 * 
	 * @param error
	 * @param estado
	 */
	public void setErrorEstado(EstadoEnum error, Estado estado) {
		estado.setCodigoEstado(error.getCodigoEstado());
		estado.setGlosaEstado(propiedadesExterna.getProperty(error.getParametroGlosaEstado()));
	}

	/**
	 * Setea codigo error y glosa error + [texto a concatenar recibido desde el
	 * parámetro textoGlosaConcatenar] al objeto de clase Estado.
	 * 
	 * @param error
	 * @param estado
	 */
	public void setErrorEstado(EstadoEnum error, Estado estado, String textoGlosaConcatenar) {
		estado.setCodigoEstado(error.getCodigoEstado());
		estado.setGlosaEstado(propiedadesExterna.getProperty(error.getParametroGlosaEstado()) + textoGlosaConcatenar);
	}

	/**
	 * Obtiene el texto de la glosa de un determinado error.
	 * 
	 * @param error
	 * @return
	 */
	public String obtenerGlosaEstado(EstadoEnum error) {
		return propiedadesExterna.getProperty(error.getParametroGlosaEstado());
	}
	
	/**
	 * Setea codigo error y glosa error + [texto a concatenar (textoGlosaConcatenar) en la posición {0} del propertie]
	 * al objeto de clase Estado.
	 * @param error
	 * @param estado
	 */
	public void setErrorEstadoExtra(EstadoEnum error, Estado estado, String textoGlosaConcatenar) {
		estado.setCodigoEstado(error.getCodigoEstado());
		estado.setGlosaEstado(MessageFormat.format(propiedadesExterna.getProperty(error.getParametroGlosaEstado()), textoGlosaConcatenar));
	}

}
