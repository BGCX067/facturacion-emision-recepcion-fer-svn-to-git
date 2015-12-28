package com.beltsoft.jim.fer.negocio.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

public class NumericoUtil {
	public static boolean esNumeroEntero(String numero) {
		return PatronValidacionUtil.EXPRESION_NUMERICO_SIN_ESPACIOS.isValid(numero);
	}
		
	public static boolean esNumeroDecimal(String numero) {
		return PatronValidacionUtil.EXPRESION_NUMERO_DECIMAL.isValid(numero);
	}
	
	public static String convertirString(BigDecimal numero){
		Locale.setDefault(new Locale("es_MX"));
		String cadena         = null;
		DecimalFormat formato = new DecimalFormat("####.00");
		
		try{
			cadena = formato.format(numero);
		}catch(Exception e){ e.printStackTrace(); }
		
		return cadena;
	}
}
