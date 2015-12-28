package com.beltsoft.jim.fer.dao.exception;

import org.springframework.dao.DataAccessException;

public class PersistenciaException extends DataAccessException{

	public PersistenciaException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
