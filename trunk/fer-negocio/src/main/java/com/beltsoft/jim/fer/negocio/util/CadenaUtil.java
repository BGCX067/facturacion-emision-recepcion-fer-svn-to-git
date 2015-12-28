package com.beltsoft.jim.fer.negocio.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CadenaUtil {
	/**
	 * Metodo para generar Claves aleatorias, utilizadas para el registro de
	 * usuario o cuando se solicita un cambio de password
	 * 
	 * @return
	 */
	
	public static final String Ñ = "Ñ";
	public static final String Á = "Á";
	public static final String É = "É";
	public static final String Í = "Í";
	public static final String Ó = "Ó";
	public static final String Ú = "Ú";
	
	public static final String ñ = "ñ";
	public static final String á = "á";
	public static final String é = "é";
	public static final String í = "í";
	public static final String ó = "ó";
	public static final String ú = "ú";
	
	public static final String Ñ_UNICODE = "\u00D1";
	public static final String Á_UNICODE = "\u00C1";
	public static final String É_UNICODE = "\u00C9";
	public static final String Í_UNICODE = "\u00CD";
	public static final String Ó_UNICODE = "\u00D3";
	public static final String Ú_UNICODE = "u\00DA";
	
	public static final String ñ_UNICODE = "\u00F1";
	public static final String á_UNICODE = "\u00E1";
	public static final String é_UNICODE = "\u00E9";
	public static final String í_UNICODE = "\u00ED";
	public static final String ó_UNICODE = "\u00F3";
	public static final String ú_UNICODE = "\u00FA";
	
	public static final String COMA = ",";
	
	public static String generaClave() {
		StringBuilder clave = new StringBuilder();

		for (int i = 0; i < 5; i++) {
			int numAleat = ((int) (Math.random() * 122)) + 1;
			if (numAleat >= 97 && numAleat <= 122)
				clave.append((char) numAleat);
			else if (numAleat >= 65 && numAleat <= 90)
				clave.append((char) numAleat);
			else
				clave.append(numAleat);
		}

		return clave.toString();
	}

	/**
	 * Valida si el valor de un RFC tiene el formato correcto
	 * 
	 * @param rfc
	 * @return
	 */
	public static boolean rfcValido(String rfc) {
		return rfcFisicaValido(rfc) || rfcMoralValido(rfc);
	}

	/**
	 * Valida si el valor de un RFC tiene el formato correcto para personas
	 * Fisicas
	 * 
	 * FORMATO: 4 caracteres alafanumericos o con el simbolos aaceptados 2
	 * digitos para el anio 2 digitos para el mes 2 digitos para el dia 3
	 * caracteres alfanumericos
	 * 
	 * XXXX991231XXX
	 * 
	 * @param rfc
	 * @return
	 */
	public static boolean rfcFisicaValido(String rfc) {
		rfc = rfc.toUpperCase();
		boolean banderaValidar;

		if (rfc.matches("[A-Z&]{4}[0-9]{2}[0,1][1-9]{1}[0-9]{2}[A-Z0-9]{3}")) {
			banderaValidar = true;
		} else {
			banderaValidar = false;
		}

		return banderaValidar;
	}

	/**
	 * Valida si el valor de un RFC tiene el formato correcto para personas
	 * Morales
	 * 
	 * FORMATO: 3 caracteres alafanumericos o con el simbolos aaceptados 2
	 * digitos para el anio 2 digitos para el mes 2 digitos para el dia 3
	 * caracteres alfanumericos
	 * 
	 * XXX991231XXX
	 * 
	 * @param rfc
	 * @return
	 */
	public static boolean rfcMoralValido(String rfc) {
		rfc = rfc.toUpperCase();
		boolean banderaValidar;

		if (rfc.matches("[A-Z&]{3}[0-9]{2}[0,1][1-9]{1}[0-9]{2}[A-Z0-9]{3}")) {
			banderaValidar = true;
		} else {
			banderaValidar = false;
		}

		return banderaValidar;
	}

	/**
	 * Valida si un correo electronico tiene el formato estandar valido
	 * 
	 * Ejemplos: apodo_nombre@dominio.com apodo-nombre@dominio.org
	 * apodo.nombre@dominio.org.mx
	 * 
	 * @param mail
	 * @return
	 */
	public static boolean emailValido(final String mail) {
		String email = mail.toUpperCase();
		boolean banderaValidar;

		if ((email.matches("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}"))) {
			banderaValidar = true;
		} else {
			banderaValidar = false;
		}

		return banderaValidar;
	}

	public static String getEmail(String email) {
		try {
			if (email.contains("<") && email.contains(">")) {
				return email.substring(email.indexOf("<") + 1,
						email.indexOf(">"));
			} else if (emailValido(email)) {
				return email;
			} else {
				return "";
			}
		} catch (Exception e) {
			// deberian de notificarse por correo las excepciones a fin de dar
			// seguimiento
			e.printStackTrace();
			return "";
		}
	}

	public static byte[] stringToBytesUTFCustom(String str) throws UnsupportedEncodingException {
		byte[] b = new byte[str.length() << 1];
		for (int i = 0; i < str.length(); i++) {
			char strChar = str.charAt(i);
			int bpos = i << 1;
			b[bpos] = (byte) ((strChar & 0xFF00) >> 8);
			b[bpos + 1] = (byte) (strChar & 0x00FF);
		}
		return b;
//		if (getEnCODING(STR).EQUALS("UTF8")) {			
//			RETURN STR.GETBYTES("UTF-8");
//		} ELSE {
//			BYTE[] B = NEW BYTE[STR.LENGTH() << 1];
//			FOR (INT I = 0; I < STR.LENGTH(); I++) {
//				CHAR STRCHAR = STR.CHARAT(I);
//				INT BPOS = I << 1;
//				B[BPOS] = (BYTE) ((STRCHAR & 0XFF00) >> 8);
//				B[BPOS + 1] = (BYTE) (STRCHAR & 0X00FF);
//			}
//			RETURN B;
//		}
	}

	public static String getEncoding(String string) {
		
		String encoding = System.getProperty("file.encoding");

		BufferedReader bufferedReader = null;

		try {
			InputStream is = new ByteArrayInputStream(string.getBytes("UTF-8"));
			// In order to read files with non-default encoding, specify an
			// encoding in the FileInputStream constructor.
			bufferedReader = new BufferedReader(new InputStreamReader(is));

			char buffer[] = new char[3];
			int length = bufferedReader.read(buffer);

			if (length >= 2) {
				if ((buffer[0] == (char) 0xff && buffer[1] == (char) 0xfe) /*
																			 * UTF-
																			 * 16
																			 * ,
																			 * little
																			 * endian
																			 */
						|| (buffer[0] == (char) 0xfe && buffer[1] == (char) 0xff) /*
																				 * UTF
																				 * -
																				 * 16
																				 * ,
																				 * big
																				 * endian
																				 */) {
					encoding = "UTF16";
				}
			}
			if (length >= 3) {
				if (buffer[0] == (char) 0xef && buffer[1] == (char) 0xbb
						&& buffer[2] == (char) 0xbf) /* UTF-8 */{
					encoding = "UTF8";
				}
			}
		} catch (IOException ex) {
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
				}
			}
		}

		return encoding;
	}

	public static String replaceUnicode(String xml) {
		System.out.println("Modificando la cadena "+xml);
		System.setProperty("file.encoding", "UTF-8");
		
		Pattern pattern = Pattern.compile(Ñ_UNICODE);
		Matcher matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes Ñ");
			xml.replaceAll(Ñ_UNICODE, Ñ);
		}
		pattern = Pattern.compile(Á_UNICODE);
		matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes Á");
			xml.replaceAll(Á_UNICODE, Á);
		}
		pattern = Pattern.compile(É_UNICODE);
		matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes É");
			xml.replaceAll(É_UNICODE, É);
		}
		pattern = Pattern.compile(Í_UNICODE);
		matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes Í");
			xml.replaceAll(Í_UNICODE, Í);
		}
		pattern = Pattern.compile(Ó_UNICODE);
		matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes Ó");
			xml.replaceAll(Ó_UNICODE, Ó);
		}
		pattern = Pattern.compile(Ú_UNICODE);
		matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes Ú");
			xml.replaceAll(Ú_UNICODE, Ú);
		}
		
		pattern = Pattern.compile(ñ_UNICODE);
		matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes ñ");
			xml.replaceAll(ñ_UNICODE, ñ);
		}
		
		pattern = Pattern.compile(á_UNICODE);
		matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes á");
			xml.replaceAll(á_UNICODE,á);
		}
		pattern = Pattern.compile(é_UNICODE);
		matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes é");
			xml.replaceAll(é_UNICODE,é);
		}
		pattern = Pattern.compile(í_UNICODE);
		matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes í");
			xml.replaceAll(í_UNICODE,í);
		}
		
		pattern = Pattern.compile(ó_UNICODE);
		matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes ó");
			xml.replaceAll(ó_UNICODE,ó);
		}
		
		pattern = Pattern.compile(ú_UNICODE);
		matcher = pattern.matcher(xml);
		while(matcher.find()){
			System.out.println("Se reemplazan los caracetes ú");
			xml.replaceAll(ú_UNICODE,ú);
		}
		System.out.println("finaliza modificación "+xml);
		return xml;
	}
	
	public static String[] dividirCadenaPorComa(String cadena) {
		return dividirCadena(cadena, COMA);
	}
	
	public static String[] dividirCadena(String cadena, String patron) {
		String []arreglo = null;
		
		if(cadena != null && !cadena.trim().equals("") && patron != null && !patron.trim().isEmpty()){
			arreglo = cadena.split(patron);
		}
		
		return arreglo;
	}

	public static boolean esAlfanumericoTamanioMaxTres(String valor) {
		return PatronValidacionUtil.EXPRESION_ALFANUMERICO_SIN_ESPACIOS_MAX_3_CHAR.isValid(valor);
	}
}
