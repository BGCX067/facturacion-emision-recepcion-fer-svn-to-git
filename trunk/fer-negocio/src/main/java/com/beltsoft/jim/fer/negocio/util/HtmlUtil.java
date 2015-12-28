package com.beltsoft.jim.fer.negocio.util;

import java.util.List;

public class HtmlUtil {
	private static final int TAM_RENGLON         = 35;
	private static final int TAM_RENGLON_PADDING = 190;
	private static final int TAM_RENGLON_VACIO   = 30;
	
	public static int obtenerAlturaTablaHtml(List<?> lista){
		if(lista == null || lista.size() == 0){
			return TAM_RENGLON;
		}else if(lista.size() == 1){
			return lista.size()*TAM_RENGLON+TAM_RENGLON_PADDING-65;
		}
		else{
			return lista.size()*TAM_RENGLON+TAM_RENGLON_PADDING;
		}
	}
	
	public static int obtenerAlturaTablaHtml(List<?> lista, int numRenglonesPagina){
		int altura       = TAM_RENGLON_VACIO;
		int numRenglones = 0;
		
		if(lista == null || lista.size() == 0){
			altura = TAM_RENGLON_VACIO;
		}else if(lista.size() == 1){
			altura = TAM_RENGLON + TAM_RENGLON_PADDING - TAM_RENGLON_VACIO;
		}else{
			numRenglones = lista.size();
			numRenglones = numRenglones > numRenglonesPagina ? numRenglonesPagina : numRenglones;
			altura       = (++numRenglones*TAM_RENGLON)+TAM_RENGLON_VACIO+TAM_RENGLON_PADDING;
		}
		
		return altura;
	}
}
