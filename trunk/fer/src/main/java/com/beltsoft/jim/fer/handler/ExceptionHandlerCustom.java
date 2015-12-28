package com.beltsoft.jim.fer.handler;

import java.util.Iterator;

import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;

import org.apache.log4j.Logger;

import com.beltsoft.jim.fer.dao.exception.PersistenciaException;
import com.beltsoft.jim.fer.exception.AccesoException;
import com.beltsoft.jim.fer.exception.ControladorException;
import com.beltsoft.jim.fer.exception.ServiceException;
import com.beltsoft.jim.fer.mb.BaseMB;
import com.beltsoft.jim.fer.util.Constantes;
import com.beltsoft.jim.fer.util.CustomReloadableResourceBundleMessageSource;

public class ExceptionHandlerCustom  extends ExceptionHandlerWrapper {
	private static final Logger LOG = Logger
			.getLogger(ExceptionHandlerCustom.class);
	private final ExceptionHandler wrapped;

	private static final CustomReloadableResourceBundleMessageSource MESSAGE_SOURCE;

	static {
		FacesContext context = FacesContext.getCurrentInstance();
		BaseMB bean = (BaseMB) context.getApplication().evaluateExpressionGet(context, "#{baseMB}", BaseMB.class);
		MESSAGE_SOURCE = bean.getMessageSource();
	}

	public ExceptionHandlerCustom(final ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public javax.faces.context.ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		for (final Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents()
				.iterator(); it.hasNext();) {
			final FacesContext facesContext = FacesContext.getCurrentInstance();
			final NavigationHandler nav = facesContext.getApplication()
					.getNavigationHandler();
			final UIViewRoot vr = facesContext.getViewRoot();
			boolean permanecerEnLaMismaVista = true;

			Throwable t = it.next().getContext().getException();

			LOG.error(t.getMessage());

			while ((t instanceof FacesException || t instanceof ELException)
					&& t.getCause() != null) {
				t = t.getCause();
			}
			if (t instanceof ControladorException) {
				facesContext.addMessage(Constantes.NOTIFICACION_ERROR_CONTROLADO, new FacesMessage(t.getMessage()));
			} else if (t instanceof AccesoException) {
				facesContext.addMessage(Constantes.NOTIFICACION_ERROR_CONTROLADO,new FacesMessage(t.getMessage()));
			} else if (t instanceof ServiceException) {
				facesContext.addMessage(Constantes.NOTIFICACION_ERROR_CONTROLADO,new FacesMessage(FacesMessage.SEVERITY_ERROR,MESSAGE_SOURCE.getMessage(t.getMessage()),null));
			} else if (t instanceof PersistenciaException) {
				facesContext.addMessage(Constantes.NOTIFICACION_ERROR_CONTROLADO,new FacesMessage(FacesMessage.SEVERITY_ERROR,MESSAGE_SOURCE.getMessage(t.getMessage()),null));
			} else {
				facesContext.addMessage(Constantes.NOTIFICACION_ERROR_GENERAL,new FacesMessage(FacesMessage.SEVERITY_ERROR,MESSAGE_SOURCE.getMessage("msg.error.general"),null));
			}

			if (permanecerEnLaMismaVista) {
				nav.handleNavigation(facesContext, null, vr.getViewId());
			}

			it.remove();
		}
		getWrapped().handle();
	}


}
