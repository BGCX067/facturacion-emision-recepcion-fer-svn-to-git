package com.beltsoft.jim.fer.negocio.schema.util;

public final class SATStringDecimalBinder {
	public static String parseStringDecimal(String s) {
		try {
			if(s != null){
				return s.trim();
			}
		} catch (Exception e) {
			throw new RuntimeException("No se pudo generar el número a partir de la cadena " + s,e);
		}
		return null;
	}

	public static String printStringDecimal(String bd) {
		if(bd != null){
			return bd.trim();
		}
		return null;
	}
}
