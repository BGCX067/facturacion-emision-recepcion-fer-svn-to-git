package com.beltsoft.jim.fer.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionControl {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ex.printStackTrace();
		ModelAndView model = new ModelAndView("error");
		model.addObject("errMsg", "Ha ocurrido un error, favor de intentarlo más tarde");
		return model;
	}
	
	
}