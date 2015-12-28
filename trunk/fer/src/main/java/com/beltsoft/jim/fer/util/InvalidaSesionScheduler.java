package com.beltsoft.jim.fer.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

public class InvalidaSesionScheduler {
	 public static final Logger LOGGER = Logger.getLogger(InvalidaSesionScheduler.class);
	   
	    /**
	     * Método que finaliza las sesiones a una determinada hora
	     */
	    @Scheduled(cron="0 0 0 * * ?")
	    public void invalidarSesiones(){
	        LOGGER.info("Ejecutando cron, invalidando sesiones ");
	        
	         Set<String> idSesiones = Sesiones.SESIONES_MAP.keySet();
	         List<HttpSession> sesiones = new ArrayList<HttpSession>();
	         
	         //Pasamos las sesiones a una lista para evitar accesos concurrentes
	         for(String idSesion : idSesiones){
	             sesiones.add(Sesiones.SESIONES_MAP.get(idSesion));
	         }
	         
	         //invalidamos sesiones
	         for (HttpSession sesion : sesiones){
	             sesion.invalidate();
	         }
	    }

}
