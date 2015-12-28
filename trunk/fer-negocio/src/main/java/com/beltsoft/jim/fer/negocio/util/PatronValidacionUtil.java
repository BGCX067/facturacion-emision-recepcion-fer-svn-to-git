package com.beltsoft.jim.fer.negocio.util;

public enum PatronValidacionUtil {
	EXPRESION_NUMERICO_SIN_ESPACIOS("\\d+"),
	EXPRESION_NUMERO_DECIMAL("([0-9]{1,13}(\\.[0-9]{1,2})?)|([0-9]{1,13})"),
	EXPRESION_ALFABETICO_SIN_ESPACIOS_3_CHAR("[a-zA-ZÑñ]{3}"),
	EXPRESION_ALFABETICO_CON_ESPACIOS("([a-zA-ZñÑ]+\\s{0,1})+(\\s)*"),
	EXPRESION_CUALQUIER_COSA (".*"),
	EXPRESION_ALFANUMERICO_SIN_ESPACIOS_MAX_3_CHAR("[a-zA-Z0-9]{1,3}"),;
	
	private String expresion;
	
	private PatronValidacionUtil(String cadena){
		expresion = cadena;
	}
	
	public boolean isValid( String cadena){		
		return cadena != null && cadena.matches( expresion );		
	}
}
