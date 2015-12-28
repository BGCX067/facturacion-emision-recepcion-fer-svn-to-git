package com.beltsoft.jim.fer.util;

import java.util.Locale;

public interface Constantes {
	
	//usuario
    public static final int ACTIVO = 1;
    public static final int INACTIVO = 2;
    public static final int BLOQUEADO = 3;
    
  //Identificadores para reglas de navegación
  	public static final String CASO_NAVEGACION_INICIO	=	"inicio";
  	public static final String CASO_NAVEGACION_LOGIN	=	"login";
  	
  	public static final Locale SIGER_LOCALE = new Locale("es", "MX");
	public static final String NOTIFICACION_EXITO = "exito";
	public static final String NOTIFICACION_ERROR_CONTROLADO = "errorControlado";
	public static final String NOTIFICACION_ERROR_GENERAL = "errorGeneral";
  	
}
