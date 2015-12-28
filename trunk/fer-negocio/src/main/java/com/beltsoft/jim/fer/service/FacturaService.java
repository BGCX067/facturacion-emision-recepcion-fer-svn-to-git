package com.beltsoft.jim.fer.service;

import java.util.List;

import com.beltsoft.jim.fer.exception.ServiceException;
import com.beltsoft.jim.fer.map.Factura;
import com.beltsoft.jim.fer.vo.FacturaFiles;

public interface FacturaService {
	
	public void procesaFilesWeb(FacturaFiles files)throws ServiceException;
	public List<Factura> consultar(Factura factura)throws ServiceException;
	
}
