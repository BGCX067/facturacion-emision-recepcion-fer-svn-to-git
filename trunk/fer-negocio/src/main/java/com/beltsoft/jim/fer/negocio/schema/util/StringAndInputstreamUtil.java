package com.beltsoft.jim.fer.negocio.schema.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;


import com.beltsoft.jim.fer.negocio.util.CadenaUtil;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

public class StringAndInputstreamUtil {

	private static final int ZIP_FILE_MAGIC_NUMBER = 0x504B0304;
	public static final int BUFFER = 2048;

	public static String convertStreamToStringUnicodeEscape(InputStream is)
			throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,
						"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return unicodeEscape(CadenaUtil.replaceUnicode(writer.toString()));
		} else {
			return "";
		}
	}

	public static String convertStreamToString(InputStream is)
			throws IOException {
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,
						"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}

	public static InputStream convertStringToStream(String text) {
		InputStream is = null;
		try {
			is = new ByteArrayInputStream(text.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return is;
	}

	private static final char[] hexChar = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String unicodeEscape(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!isUtfBom(c)) {
				if ((c >> 7) > 0) {
					sb.append("\\u");
					sb.append(hexChar[(c >> 12) & 0xF]);
					sb.append(hexChar[(c >> 8) & 0xF]);
					sb.append(hexChar[(c >> 4) & 0xF]);
					sb.append(hexChar[c & 0xF]);
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	private static boolean isUtfBom(char c) {
		boolean isBom = false;
		byte[] bom;
		try {
			bom = String.valueOf(c).getBytes("UTF-8");
			if ((bom[0] == (byte) 0xEF) && (bom[1] == (byte) 0xBB)
					&& (bom[2] == (byte) 0xBF)) {
				isBom = true;
			}
		} catch (UnsupportedEncodingException e) {
		}
		return isBom;
	}

	static enum ParseState {
		NORMAL, ESCAPE, UNICODE_ESCAPE
	}

	// convert unicode escapes back to char
	public static String convertUnicodeEscape(String s) {
		char[] out = new char[s.length()];

		ParseState state = ParseState.NORMAL;
		int j = 0, k = 0, unicode = 0;
		char c = ' ';
		for (int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			if (state == ParseState.ESCAPE) {
				if (c == 'u') {
					state = ParseState.UNICODE_ESCAPE;
					unicode = 0;
				} else { // we don't care about other escapes
					out[j++] = '\\';
					out[j++] = c;
					state = ParseState.NORMAL;
				}
			} else if (state == ParseState.UNICODE_ESCAPE) {
				if ((c >= '0') && (c <= '9')) {
					unicode = (unicode << 4) + c - '0';
				} else if ((c >= 'a') && (c <= 'f')) {
					unicode = (unicode << 4) + 10 + c - 'a';
				} else if ((c >= 'A') && (c <= 'F')) {
					unicode = (unicode << 4) + 10 + c - 'A';
				} else {
					throw new IllegalArgumentException(
							"Malformed unicode escape");
				}
				k++;

				if (k == 4) {
					out[j++] = (char) unicode;
					k = 0;
					state = ParseState.NORMAL;
				}
			} else if (c == '\\') {
				state = ParseState.ESCAPE;
			} else {
				out[j++] = c;
			}
		}

		if (state == ParseState.ESCAPE) {
			out[j++] = c;
		}

		return new String(out, 0, j);
	}
	
	public static int getInputStreamLength(InputStream is) throws IOException {

	    int len;
	    int size = 1024;
	    byte[] buf;

	    if (is instanceof ByteArrayInputStream) {
	      size = is.available();
	      buf = new byte[size];
	      len = is.read(buf, 0, size);
	    } else {
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      buf = new byte[size];
	      while ((len = is.read(buf, 0, size)) != -1)
	        bos.write(buf, 0, len);
	      buf = bos.toByteArray();
	    }
	    return buf.length;
	  }

	public static String removeUtfBom(String s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!isUtfBom(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String[] splitByLength(String s, int length) {
		String[] retorno = Iterables.toArray(
		        Splitter
	            .fixedLength(length)
	            .split(s),
	        String.class
	    );

		return retorno;
	}
	
	public static boolean isZipFile(File file) {
		boolean validZipFile = false;
		RandomAccessFile raf = null;
		long n = 0;
		try {
			raf = new RandomAccessFile(file,"r");
			n = raf.readInt();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (n == ZIP_FILE_MAGIC_NUMBER) {
			validZipFile = true;
		}

		return validZipFile;
	}

	public static String[] wordSplitByLength(String cadenaOriginal,
			int width) {
		ArrayList paragraph = new ArrayList();
		char[] chars = cadenaOriginal.toCharArray();
		boolean endOfString = false;
		int start = 0;
		int end = start;
		while(start < chars.length-1) {
		    int charCount = 0;
		    int lastSpace = 0;
		    while(charCount < width) {
		        if(chars[charCount+start] == ' ') {
		            lastSpace = charCount;
		        }
		        charCount++;
		        if(charCount+start == cadenaOriginal.length()) {
		            endOfString = true;
		            break;
		        }
		    }
		    end = endOfString ? cadenaOriginal.length()
		                      : (lastSpace > 0) ? lastSpace+start : charCount+start;
		    paragraph.add(cadenaOriginal.substring(start, end));
		    start = end+1;
		}
		
		String[] a = new String[paragraph.size()];
		return (String[]) paragraph.toArray(a);
	}

	public static void main(String[] args) {
		final String[] wordSplitByLength = wordSplitByLength("I want to split a string into multiple lines of 'x' characters without breaking a word. That is, for example, if x = 13, if the 13th character falls in the middle of a word, this word should not be considered for the first line, but must go into the next line.", 70);
		for (int i = 0; i < wordSplitByLength.length; i++) {
			System.out.println(wordSplitByLength[i]);
		}
	}
}
