package com.beltsoft.jim.fer.dao;

import java.util.List;

import com.beltsoft.jim.fer.map.Factura;

public interface FacturaDAO {
	void insertar(Factura factura);
	List<Factura> selectFilter(Factura factura);
}
