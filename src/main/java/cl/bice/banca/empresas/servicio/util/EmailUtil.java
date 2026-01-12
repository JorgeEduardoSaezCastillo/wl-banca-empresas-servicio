package cl.bice.banca.empresas.servicio.util;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.bice.banca.empresas.servicio.exception.BancaEmpresasException;
import cl.bice.banca.empresas.servicio.exception.ErrorStoredProcedureException;
import cl.bice.banca.empresas.servicio.exception.NoEncontradoException;
import cl.bice.banca.empresas.servicio.model.common.Constantes;
import cl.bice.banca.empresas.servicio.repository.PortalOrawRepository;

/**
 * Utilitario para envio de correo.
 *
 * @author Luis Basso s. (TINet)
 * @version 1.0
 */
@Service
public final class EmailUtil {
	
	/**
	 * Propiedades Externas.
	 */
    @Autowired
    protected Properties propiedadesExterna;
    
	/**
	 * Repositorio oraw.
	 */
    @Autowired
    PortalOrawRepository portalOrawRepository;
    
    public static final String NRO_NOMINA = "Nro nómina";
    
    public static final String MTO_CARGO = "Monto cargo";
    
    public static final String CTA_CARGO = "Cuenta cargo";
    
    public static final String MSJE = "Mensaje";
    
    public static final String ENVIADO_A = "enviado a";
    
	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
	
	/**
	 * Constructor por defecto.
	 */
	private EmailUtil() {
		super();
	}

	/**
	 * Chequea que una dirección de email sea válida
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isValidEmailAddress(String email) {
		if (null == email) {
			return false;
		} else {
			String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
			return email.matches(regex);
		}
	}

	/**
	 * Mail informa cargo identficado por la Consulta Cartola Provisoria TCMW0025.
	 * @param rutCliente
	 * @param nomEmpresa
	 * @param rutApo
	 * @param nomApo
	 * @param ref
	 * @param mtoTotalNom
	 * @param fecPago
	 * @param tipNomina
	 * @param mtoExistente
	 * @param tipAbono
	 * @param lineaCartola
	 * @param listaDistribucion
	 */
	public void sendMailCargoDuplicado(String rutCliente, String nomEmpresa,String rutApo, String nomApo, String ref, String mtoTotalNom, String fecPago, String tipNomina, 
			String mtoExistente, String tipAbono, String lineaCartola, String listaDistribucion, String remitente){
		try{
			String asunto = "Nómina Diferida - Problema al Aprobar - "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+" - "+nomEmpresa;
			String body="";
			body+="	Se requiere revisar Nómina diferida que apoderado intenta Aprobar, la cual registra cargo ya aplicado.\n";
			body+="Apoderado no podrá Aprobar mientras no se regularice cargo que ya existe de esta nómina.\n";
			body+="Empresa : "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+"  "+nomEmpresa+"\n";
			body+="Apoderado que intenta Aprobar : "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutApo))+"  "+nomApo+"\n";
			body+="Referencia nómina: "+ref+"\n";
			body+="Monto Total Nómina $: "+mtoTotalNom+"\n";
			body+="Fecha Pago Nómina: "+fecPago+"\n";
	        body+="Tipo de Nómina : "+tipNomina+"\n";
	        body+="Monto del cargo existente: $ "+mtoExistente+"\n";
	        body+="Tipo de cargo en línea : "+tipAbono+"\n";
	        body+="Detalle de cartola con evidencia de cargo que existe:\n";
	        body+="\n"+lineaCartola+"\n";
	        
	        LOGGER.info("EmailUtil.sendMailCargoDuplicado " + asunto +"\n " + MSJE + ": \n " + body + " \n " + ENVIADO_A + " : " + listaDistribucion);
	        
	        //Envio mail
	        enviaCorreo(listaDistribucion, asunto, body, propiedadesExterna, remitente);
	        
		}catch(Exception e){
			LOGGER.error("EmailUtil.sendMailCargoDuplicado Exception : " + e.getMessage());
		}
	}
	
	
	/**
	 * @param rutCliente
	 * @param numNomina
	 * @param rutApo
	 * @param fecPago
	 * @param mtoTotalNom
	 * @param mtoTotalCargoL
	 * @param mtoCargo1
	 * @param mtoCargo2
	 * @param tipNomina
	 * @param tipAbonos
	 * @param listaDistribucion
	 */
	public void sendMailErrorCartolaProv(String rutCliente, String  numNomina, String rutApo, String fecPago, String mtoTotalNom, 
			String mtoTotalCargoL,String mtoCargo1, String mtoCargo2, String tipNomina, String tipAbonos, String listaDistribucion, String nomEmpresa, String remitente){
		try{
			String asunto = "Error Consulta Cartola Nóminas Diferidas - "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+" - "+nomEmpresa;
			String body="";
			body+="	Se informa que existe cliente intentando Aprobar nómina diferida.\n";
			body+="Dado que no está respondiendo la Consulta de cartola de la cuenta del cliente, cliente no puede aprobar la nómina.\n";
			body+="Detalle de la Nómina:\n";
			body+=NRO_NOMINA + ": " +numNomina+"\n";
			body+="Monto Nómina: $ "+mtoTotalNom+"\n";
			body+="Tipo Nómina: Pago "+tipNomina+"\n";
			body+="Fecha Pago Nómina: "+fecPago+"\n"; 
			body+="Tipos de abonos de la nómina : "+tipAbonos+"\n";
			body+="Total de Cargos en línea : $ "+mtoTotalCargoL+"\n";
			body+="Monto Cargo en línea 1: $ "+mtoCargo1+"\n";
			body+="Monto Cargo en línea 2: $ "+mtoCargo2+"\n";
			body+="Rut Cliente: "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+"\n";
			body+="Rut Apoderado que aprueba: "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutApo))+"\n";

	        LOGGER.info("EmailUtil.sendMailErrorCartolaProv " + asunto +"\n " + MSJE + ": \n " + body + " \n " + ENVIADO_A + " : " + listaDistribucion);
	        
	        //Envio mail
	        enviaCorreo(listaDistribucion, asunto, body, propiedadesExterna, remitente);
	        
		}catch(Exception e){
			LOGGER.error("EmailUtil.sendMailErrorCartolaProv Exception : " + e.getMessage());
		}
	}
	
	/**
	 * @param rutCliente
	 * @param numCtaCargo
	 * @param mtoReversa
	 * @param ref
	 * @param mtoTotalNom
	 * @param fecPago
	 * @param tipNomina
	 * @param tipAbono
	 * @param listaDistribucion
	 */
	public void sendMailErrorReversaGFS(String rutCliente, String numCtaCargo, String mtoReversa, String ref, String mtoTotalNom, String fecPago, 
			String tipNomina, String tipAbono, String listaDistribucion, String nomEmpresa, String remitente){
		try{
			String asunto = "Nómina Diferida - Problema al Aprobar - "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+" - "+nomEmpresa;
			String body="";			
			body+="	Se informa que se recibe error al solicitar reversa de siguiente cargo por nómina diferida:\n";
			body+="Rut Cliente : "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+"\n"; 
			body+=CTA_CARGO + " :"+numCtaCargo+"\n";
			body+="Monto : $ "+mtoReversa+"\n";
			body+="Tipo de Cargo : Cargos por Abonos "+tipAbono+"\n";
			body+="Referencia Nómina : "+ref+"\n";
			body+="Tipo Nómina : "+tipNomina+"\n";
			body+="Fecha Pago Nómina : "+fecPago+"\n";
			body+="Tipos de abonos de la nómina : "+tipAbono+"\n";
	        
	        LOGGER.info("EmailUtil.sendMailErrorReversaGFS " + asunto +"\n " + MSJE + ": \n " + body + " \n " + ENVIADO_A + " : " + listaDistribucion);
	        
	        //Envio mail
	        enviaCorreo(listaDistribucion, asunto, body, propiedadesExterna, remitente);
	        
		}catch(Exception e){
			LOGGER.error("EmailUtil.sendMailErrorReversaGFS Exception : " + e.getMessage());
		}
	}
	
	
	/**
	 * @param rutCliente
	 * @param numCtaCargo
	 * @param numNomina
	 * @param mtoTotalNom
	 * @param listaDistribucion
	 */
	public void sendMailErrorEDTI(String rutCliente, String numCtaCargo, String numNomina, String mtoTotalNom, String listaDistribucion, String nomEmpresa, String remitente){
		try{
			String asunto = "Nómina Diferida - Transaccion duplicada - "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+" - "+nomEmpresa;
			String body="";			
			body+="	Se solicita revisar detalle de nómina diferida siguente::\n";
			body+="Rut Cliente : "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+"\n";
			body+=CTA_CARGO + " :"+numCtaCargo+"\n";
			body+=MTO_CARGO + " : $ "+mtoTotalNom+"\n";
			body+=NRO_NOMINA + " : "+numNomina+"\n";
	        
	        LOGGER.info("EmailUtil.sendMailErrorEDTI " + asunto +"\n " + MSJE + ": \n " + body + " \n " + ENVIADO_A + " : " + listaDistribucion);
	        
	        //Envio mail
	        enviaCorreo(listaDistribucion, asunto, body, propiedadesExterna, remitente);
	        
		}catch(Exception e){
			LOGGER.error("EmailUtil.sendMailErrorEDTI Exception : " + e.getMessage());
		}
	}
	
	/**
	 * @param rutCliente
	 * @param numCtaCargo
	 * @param numNomina
	 * @param mtoTotalNom
	 * @param listaDistribucion
	 */
	public void sendMailErrorDETUD037(String rutCliente, String numCtaCargo, String numNomina, String mtoTotalNom, String listaDistribucion, String nomEmpresa, String remitente){
		try{
			String asunto = "Nómina Diferida - Transaccion ya existe - "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+" - "+nomEmpresa;
			String body="";			
			body+="	Se solicita revisar detalle de nómina diferida siguente:\n";
			body+="Rut Cliente : "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+"\n"; 
			body+=CTA_CARGO + " :"+numCtaCargo+"\n";
			body+=MTO_CARGO + " : $ "+mtoTotalNom+"\n";
			body+=NRO_NOMINA + " : "+numNomina+"\n";
			
	        LOGGER.info("EmailUtil.sendMailErrorDETUD037 " + asunto +"\n " + MSJE + ": \n " + body + " \n " + ENVIADO_A + " : " + listaDistribucion);
	        
	        //Envio mail
	        enviaCorreo(listaDistribucion, asunto, body, propiedadesExterna, remitente);
	        
		}catch(Exception e){
			LOGGER.error("EmailUtil.sendMailErrorDETUD037 Exception : " + e.getMessage());
		}
	}

	/**
	 * @param rutCliente
	 * @param numCtaCargo
	 * @param numNomina
	 * @param mtoTotalNom
	 */
	public void sendMailErrorACVAC07(String rutCliente, String numCtaCargo, String numNomina, String mtoTotalNom, String nomEmpresa, String remitente){
		try{
			String asunto = "Nómina Diferida - Cuenta no acepta cargos - "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+" - "+nomEmpresa;
			String body="";			
			body+="	Estimado ejecutivo: se requiere revisar estado de la cuenta siguiente, su cliente intenta Cerrar una nomina de pago con cargo a esta cuenta,\n";
			body+="la cual no permite cargos:\n";
			body+="Rut Cliente : "+FormateadorUtil.formatoRut(FormateadorUtil.rutSinCero(rutCliente))+"\n";
			body+=CTA_CARGO + " :"+numCtaCargo+"\n";
			body+=MTO_CARGO + " : $ "+mtoTotalNom+"\n";
			body+=NRO_NOMINA + " : "+numNomina+"\n";  
			
			String mailejecutivo = getEmailEjecutivo(rutCliente);
			
	        LOGGER.info("EmailUtil.sendMailErrorACVAC07 " + asunto +"\n " + MSJE + ": \n " + body + " \n " + ENVIADO_A + " ejecutivo: " + mailejecutivo);
	        
	        //Envio mail
	        enviaCorreo(mailejecutivo, asunto, body, propiedadesExterna, remitente);
	        
		}catch(Exception e){
			LOGGER.error("EmailUtil.sendMailErrorACVAC07 Exception : " + e.getMessage());
		}
	}
	
	/**
	 * Metodo que envia mail recibido por parametro
	 * 
	 * @param destinatario
	 * @param asunto
	 * @param mensaje
	 * @param propiedadesExterna
	 */
	public void enviaCorreo(String destinatario, String asunto, String mensaje, Properties propiedadesExterna, String remitente) {
		enviaCorreo(destinatario, asunto, mensaje, propiedadesExterna, remitente, null);
	}
	
	/**
	 * Metodo que envia mail recibido por parametro
	 * 
	 * @param destinatario
	 * @param asunto
	 * @param mensaje
	 * @param propiedadesExterna
	 * @param remitente
	 * @param toName
	 */
	public void enviaCorreo(String destinatario, String asunto, String mensaje, Properties propiedadesExterna, String remitente, String toName) {
		LOGGER.info("seteando propiedades");
		Properties props = new Properties();
		LOGGER.info("mail.smtp.host =" + propiedadesExterna.getProperty(Constantes.SMTP_HOST));
		LOGGER.info("mail.smtp.starttls.enable ="
				+ propiedadesExterna.getProperty(Constantes.STARTTLS_ENABLE));
		LOGGER.info("mail.smtp.port =" + propiedadesExterna.getProperty(Constantes.SMTP_PORT));
		LOGGER.info("mail.smtp.user =" + propiedadesExterna.getProperty(Constantes.SMTP_USER));
		LOGGER.info("mail.smtp.auth =" + propiedadesExterna.getProperty(Constantes.SMTP_AUTH));
		LOGGER.info("From  =" + (!remitente.isEmpty() ? remitente : propiedadesExterna.getProperty(Constantes.FROM_EMAIL)));
		props.setProperty("mail.smtp.host", propiedadesExterna.getProperty(Constantes.SMTP_HOST));
		props.setProperty("mail.smtp.starttls.enable",
				propiedadesExterna.getProperty(Constantes.STARTTLS_ENABLE));
		props.setProperty("mail.smtp.port", propiedadesExterna.getProperty(Constantes.SMTP_PORT));
//	    props.setProperty("mail.smtp.user", propiedadesExterna.getProperty(Constantes.SMTP_USER));
//		props.setProperty("mail.smtp.auth", propiedadesExterna.getProperty(Constantes.SMTP_AUTH));
		props.put("mail.smtp.timeout", propiedadesExterna.getProperty(Constantes.SMTP_TIMEOUT));
		props.put("mail.smtp.connectiontimeout",
				propiedadesExterna.getProperty(Constantes.SMTP_CONNECTION_TIMEOUT));
		try {
			// Preparamos la sesion
			LOGGER.info("preparando sesion");
			Session session = Session.getDefaultInstance(props);
			LOGGER.info("Construyendo mensaje");
			// Construimos el mensaje
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(!remitente.isEmpty() ? remitente : propiedadesExterna.getProperty(Constantes.FROM_EMAIL),
					"Banco BICE"));
			// message.addRecipient(Message.RecipientType.TO,new
			// InternetAddress(destinatario));
			if(null == null)
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			else
				message.setRecipients(Message.RecipientType.TO, new InternetAddress(destinatario, toName).getAddress());
			message.setSubject(asunto);
			message.setText(mensaje);
			LOGGER.info("enviando mensaje");
			// Transport.send(message);
			// Lo enviamos.
			Transport t = session.getTransport("smtp");
			t.connect(Constantes.SMTP_USER, "");
			t.sendMessage(message, message.getAllRecipients());
			// Cierre.
			t.close();
			LOGGER.info("mensaje enviado");
		} catch (Exception e) {
			LOGGER.error("error al enviar email", e);
		}
	}

	/**
	 * Construccion de mensaje de correo de error en reversa
	 * 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @param 
	 * @return
	 */
	public String mensajeCorreoReversa(String nombreDestinatario, String servicio, String rutCliente, String estado, String glosa, Object parametros, String numeroOperacion) {
		StringBuffer msgBody = new StringBuffer(200);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
		String fechaFormateada = formateador.format(cal.getTime());

		msgBody.append("Estimado (a) " + nombreDestinatario + ": \n\n");
		msgBody.append("Comunico a Usted que se ha producido un error al realizar la reversa de la compra de acciones con cargo a cuenta caja en servicio " + servicio + ".\n\n");
		msgBody.append("El detalle de la operacion es la siguiente: \n\n");
		msgBody.append("Servicio: app-servicios-acciones \n");
		msgBody.append("Metodo: ingresarOrden \n");
		msgBody.append("Rut cliente : " + rutCliente + "\n");
		msgBody.append("Estado : " + estado + "\n");
		msgBody.append("Glosa error : " + glosa + "\n");
		msgBody.append("Parametros : " + parametros + "\n");
		msgBody.append("Numero de la Operacion : " + numeroOperacion + " \n");
		msgBody.append("Fecha : " + fechaFormateada + "\n\n");
		msgBody.append("Atentamente, \n");
		msgBody.append("Banco BICE \n\n");
		msgBody.append("Nota Importante: \n");
		msgBody.append("Este mail es generado de manera automatica, por favor no responda a este mensaje. Ante cualquier duda, contactese con nuestro CENTRO DE ATENCION INTERNET al fono 600 400 1010.\n");
		msgBody.append("P.D.: Los tildes han sido omitidos en forma intencional.");
		return msgBody.toString();
	}

	/**
	 * Metodo que econstruye mensaje y envia mail por parámetros
	 * 
	 * @param destinatario
	 * @param asunto
	 * @param mensaje
	 * @param propiedadesExterna
	 */
	public void enviaCorreoNominas(String servicio, String rutCliente, String estado, String glosa, Object parametros, String numeroOperacion, Properties propiedadesExterna) {
		EmailUtil email = new EmailUtil();
		String nombreDestinatario = "";
		String mensaje = email.mensajeCorreoReversa(nombreDestinatario, servicio, rutCliente, estado, glosa, parametros, numeroOperacion);
		String destinatario = propiedadesExterna.getProperty(Constantes.TO_EMAIL);
		String asunto = "Servicio Banca digital empresas - Error al generar reversa de cargo a cuenta caja en servicio " + servicio;
		email.enviaCorreo(destinatario, asunto, mensaje, propiedadesExterna, "");
	}
	
	/**
	 * Metodo que obtiene correo de ejecutivo desde base de datos
	 * 
	 * @param rutEmpresa
	 * @return
	 * @throws BancaEmpresasException
	 */
	public String getEmailEjecutivo(String rutEmpresa) throws BancaEmpresasException {
		LOGGER.info("EmailUtil getEmailEjecutivo: rutEmpresa[{}]", rutEmpresa);

		String salida;

		Map<String, Object> params = new HashMap<>();
		params.put("V_RUT_CLI", rutEmpresa);

		try {
			portalOrawRepository.obtenerEmailEjecutivo(params);
			salida = (String) params.get("RESULT");
		} catch (SQLException e) {
			throw new ErrorStoredProcedureException(e);
		}

		if (salida== null || salida.equals("")) {
			throw new NoEncontradoException();
		}
		
		return salida;
	}
	
	/**
	 * @param idSesion
	 * @param descripcion
	 * @return
	 */
	public Map<String, String> preparaMsgMail(String idSesion,String descripcion){
		
		Map<String, String> dat = new HashMap<>();
		LOGGER.info("["+idSesion+"]EmailUtil.preparaMsgMail descripcion [" +descripcion + "]");
		try{
			
			String[] text = descripcion.split("\\|");
			String msj = text[0];
			String ref="";
			if(msj.indexOf(',') > 0){
				ref = msj.substring(0, msj.indexOf(','));
			}else{
				ref = msj;
			}
			
			if(msj.startsWith("Pago")){
				dat.put("ref", ref.trim());
				dat.put("tipNom",((String[])msj.split(" "))[1].trim());
				if(msj.indexOf('$') > 0){
					dat.put("mtoCargo", msj.substring(msj.indexOf('$')+1,msj.lastIndexOf('.')).trim());
				}				
				dat.put("tipAbono", ref.substring(ref.indexOf('-')+1, ref.length()).trim());
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOGGER.error("["+idSesion+"]EmailUtil.preparaMsgMail Exception " + e.getMessage());
		}		
		return dat;
	}
}
