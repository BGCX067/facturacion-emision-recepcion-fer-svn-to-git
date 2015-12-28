package com.beltsoft.jim.fer.util;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener {

	 public static final Logger LOGGER = Logger.getLogger(SessionListener.class);
	    
	    public void sessionCreated(HttpSessionEvent se) {  
	        HttpSession sesion = se.getSession();  
	        String idSesion = sesion.getId();  
	        LOGGER.info("Adding session -->"+idSesion);
	        //Almacenamos la sesi�n
	        Sesiones.SESIONES_MAP.put(idSesion, sesion);
	    }  
	   
	    public void sessionDestroyed(HttpSessionEvent se) {  
	        LOGGER.info("destroying session-->"+se.getSession().getId());
	        HttpSession sess = se.getSession();  
	        String id = sess.getId();  
	        //removemos la sesi�n
	        Sesiones.SESIONES_MAP.remove(id);
	    }  

}
