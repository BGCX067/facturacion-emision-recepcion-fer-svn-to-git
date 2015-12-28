package com.beltsoft.jim.fer.mb;

import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import com.beltsoft.jim.fer.util.CustomReloadableResourceBundleMessageSource;

@ManagedBean(name="baseMB")
public class BaseMB {
	protected static final Logger logger = Logger.getLogger(BaseMB.class);
	
	@ManagedProperty(value = "#{messageSource}")
	protected CustomReloadableResourceBundleMessageSource messageSource;
	
    public String getErrorMessage(String clientId) {
        FacesContext fc = FacesContext.getCurrentInstance();
        Iterator<FacesMessage> iter = fc.getMessages(clientId);
        if (iter.hasNext()) {
        	FacesMessage fm= iter.next();
        	fm.rendered();
            return fm.getDetail();
        }

        return "";
    }
    
    /**
	 * @return the messageSource
	 */
	public CustomReloadableResourceBundleMessageSource getMessageSource() {
		return messageSource;
	}

	/**
	 * @param messageSource the messageSource to set
	 */
	public void setMessageSource(
			CustomReloadableResourceBundleMessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	/**
	 * Método que evalua si un campo es valido
	 * @param componente, cadena de texto con la forma ":form:IdCampo"
	 * @return valor boolean que determina si es valido el elemento
	 */
	public boolean evaluarCampoValido(String componente){
		FacesContext context = FacesContext.getCurrentInstance();
	    UIInput input = (UIInput)context.getViewRoot().findComponent(componente);
	    return input.isValid();
	}
}
