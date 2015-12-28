package com.beltsoft.jim.fer.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.beltsoft.jim.fer.exception.ServiceException;
import com.beltsoft.jim.fer.service.MailAttachServiceService;
import com.beltsoft.jim.fer.service.NotificacionService;
import com.beltsoft.jim.fer.vo.CorreoVo;

@Service(value = "notificacionService")
public class NotificacionServiceImpl implements NotificacionService {
	  private static final Logger LOG = Logger.getLogger(NotificacionService.class);
	    
	    private static final String MAIL_PROPERTIES = "/mail.params.properties";
	    private static final String TIPO_CORREO_KEY = "tipo.correo";
	    private static final String MAIL_ACCOUNT_KEY = "mail.account";
	    private static final String PASSWORD_SE_KEY = "password.se";
	    private static final String ESPACIO_FINAL = "\\n\\n";
	    
	    private static int idTipoCorreo = 1;
	    private static int idMailAccount = 26;
	    private static String passwordSe = "No inicializado";
	    private static boolean propsInitialized = false;
	    
	    private boolean envioActivo = false;
	    private MailAttachServiceService service;
	    
//	    @Autowired
//	    private ValorGeneralDAO valorGeneralDAO;
	    
	    private static boolean loadProperties(){
	        Properties p = new Properties();        
	        InputStream in =null;// ValorGeneralDAO.class.getResourceAsStream(MAIL_PROPERTIES);
	        try{
	            p.load(in);
	            idTipoCorreo = Integer.parseInt((String)p.get(TIPO_CORREO_KEY));
	            idMailAccount = Integer.parseInt((String)p.get(MAIL_ACCOUNT_KEY));
	            passwordSe = (String)p.get(PASSWORD_SE_KEY); 
	            propsInitialized=true;           
	        }catch(IOException e){
	            LOG.error("No se encuentra el archivo " + MAIL_PROPERTIES + " : " + e.getMessage(), e);
	            propsInitialized=false;
	        }catch(NumberFormatException nfe){
	            LOG.error("Error al leer propiedades de mail " + MAIL_PROPERTIES + " : " + nfe.getMessage(), nfe);
	            propsInitialized=false;
	        }finally{
	            try{
	                if(in!=null){
	                    in.close();
	                }
	            }catch(IOException e2){
	                LOG.debug("Error al intentar cerrar el archivo " + MAIL_PROPERTIES + " : " + e2.getMessage(), e2);
	            }
	        }    
	        return propsInitialized;
	        
	    }   
	      
	    /**
	     * Envia el mensaje de notificación vía correo electrónico mediante el webservice de la SE
	     * @param correo el mensaje de notificación a enviar.
	     * @throws NotificacionException cuando encuentra algún error para realizar el envío.
	     */
	    @Override
	    public void enviarNotificacion(CorreoVo correo)
	            throws ServiceException {        
	        LOG.info("Solicitando notificación: ");
	        LOG.info("Destinatario: " + correo.getDestinatario());
	        LOG.info("Asunto: " + correo.getCorreo().getAsunto());
	        LOG.info("Mensaje: " + correo.getCorreo().getMensaje());
	        if(propsInitialized==false && loadProperties()==false){
	            throw new ServiceException("Error en envio de notificación: No se pudieron cargar las propiedades de configuración.");
	        }
//	        envioActivo = valorGeneralDAO.consultar().getEnviarMail();        
//	        if(envioActivo){
//	            try{
//	                service = new MailAttachServiceService();            
//	                SendMailAttachment.Mail wsmail = new SendMailAttachment.Mail();
//	                wsmail.setDestinatario(correo.getDestinatario());
//	                wsmail.setAsunto(correo.getCorreo().getAsunto());
//	                wsmail.setMensaje(correo.getCorreo().getMensaje()+ESPACIO_FINAL);
//	                wsmail.setIdTipoCorreo(idTipoCorreo);
//	                wsmail.setIdMailAccount(idMailAccount);
//	                wsmail.setPasswordSe(passwordSe);            
//	                MsMailServiceFile p = service.getMsMailServiceFilePort();
//	                p.sendMailAttachment(wsmail);
//	            }catch(Exception e){
//	                throw new NotificacionException("Error enviando la notificación por correo: " + e.getMessage(), e);
//	            }
//	        }else{
//	            LOG.info("Envío inactivo, el mensaje no se envió.");
//	        }
	    }
}
