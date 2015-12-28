package com.beltsoft.jim.fer.negocio.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechaUtil {
	private static final SimpleDateFormat FORMATO = new SimpleDateFormat();
	
	public static String formato(Date fecha, String patron){
		FORMATO.applyPattern(patron);
		return FORMATO.format(fecha);
	}
	
	public static String formatoSimpleEstandar(long fecha){
		return formatoSimpleEstandar(new Date(fecha));
	}
	
	public static String formatoEstandar(long fecha){
		return formatoEstandar(new Date(fecha));
	}
	
	public static String formatoSimpleEstandar(Date fecha){
		return formato(fecha, "dd/MM/yyyy");
	}
	
	public static String formatoEstandar(Date fecha){
		return formato(fecha, "dd/MM/yyyy HH:mm:ss");
	}
	
	public static Date parseo(String fecha, String patron){
		Date resultado = null;
		
		try{
			FORMATO.applyPattern(patron);
			resultado = FORMATO.parse(fecha);
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public static Date parseoSimpleEstandar(String fecha){
		return parseo(fecha, "dd/MM/yyyy");
	}
	
	public static Date parseoEstandar(String fecha){
		return parseo(fecha, "dd/MM/yyyy HH:mm:ss");
	}
}
