package com.beltsoft.jim.fer.handler;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ExceptionHandlerFactoryCustom extends ExceptionHandlerFactory {
	private final javax.faces.context.ExceptionHandlerFactory parent;
	 
	 public ExceptionHandlerFactoryCustom(final ExceptionHandlerFactory parent) {
	  this.parent = parent;
	 }
	 
	 @Override
	 public ExceptionHandler getExceptionHandler() {
	  return new ExceptionHandlerCustom(this.parent.getExceptionHandler());
	 }

}
