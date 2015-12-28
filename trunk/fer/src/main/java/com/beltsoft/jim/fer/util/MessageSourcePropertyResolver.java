package com.beltsoft.jim.fer.util;

import javax.el.ELContext;
import javax.el.ELException;

import org.springframework.context.MessageSource;
import org.springframework.web.jsf.el.SpringBeanFacesELResolver;

public class MessageSourcePropertyResolver extends SpringBeanFacesELResolver {
	public Object getValue(ELContext elContext, Object base, Object property)
			throws ELException {
		if (base instanceof MessageSource && property instanceof String) {
			String mensaje = ((MessageSource) base).getMessage(
								(String) property, null, Constantes.SIGER_LOCALE);
			
			if (null != mensaje) {
				elContext.setPropertyResolved(true);
			}
			return mensaje;
		}
		return super.getValue(elContext, base, property);
	}

}
