package com.beltsoft.jim.fer.negocio.schema.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public final class SATDateTimeBinder {
	public static Date parseDateTime(String s) {
		DateFormat formatter = null;
		try {
			formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			return formatter.parse(s);
		} catch (ParseException e) {
			formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
			try {
				return formatter.parse(s);
			} catch (ParseException e1) {
				formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
				try {
					return formatter.parse(s);
				} catch (ParseException e2) {
					throw new RuntimeException(e2);
				}
			}
		}
	}

	public static String printDateTime(Date dt) {
		if (dt != null) {
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(dt);
			DateFormat formatter = null;
			int mili = cal.get(Calendar.MILLISECOND);
			if (mili != 0) {
				formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
			} else {
				formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			}
			return formatter.format(dt);
		}
		return null;
	}
	
	public static Date parseDateTimeXML(String s) {
		XMLGregorianCalendar fecha = null;

		try {
			fecha = DatatypeFactory.newInstance().newXMLGregorianCalendar(s);
			
			return new Date(fecha.toGregorianCalendar().getTimeInMillis());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
