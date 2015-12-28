package com.beltsoft.jim.fer.exception;

public class ServiceException extends Exception{
	private static final long serialVersionUID = 1L;
		
		public ServiceException(String mensaje){
			super(mensaje);
		}
		
		public ServiceException(String mensaje, Throwable t){
	        super(mensaje,t);
	    }
}
