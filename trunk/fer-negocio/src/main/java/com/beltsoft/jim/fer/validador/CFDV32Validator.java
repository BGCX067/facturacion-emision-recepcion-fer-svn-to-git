package com.beltsoft.jim.fer.validador;

import java.io.InputStream;

import com.beltsoft.jim.fer.exception.ServiceException;
import com.beltsoft.jim.fer.map.Factura;
import com.beltsoft.jim.fer.vo.FacturaFiles;



public interface CFDV32Validator {

	public Factura validarCfd(FacturaFiles files) throws ServiceException;
	public Factura validarCfd(InputStream is) throws ServiceException;
}
