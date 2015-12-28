package com.beltsoft.jim.fer.vo;

import java.io.File;

public class FacturaVo implements Comparable<FacturaVo> {
	private File xml;
	private File pdf;
	private java.lang.String codigoEstatus;
    private java.lang.String estado;

  
	public java.lang.String getCodigoEstatus() {
		return codigoEstatus;
	}
	public void setCodigoEstatus(java.lang.String codigoEstatus) {
		this.codigoEstatus = codigoEstatus;
	}
	public java.lang.String getEstado() {
		return estado;
	}
	public void setEstado(java.lang.String estado) {
		this.estado = estado;
	}
	public File getXml() {
		if(xml== null)return new File("dummy");
		return xml;
	}
	public void setXml(File xml) {
		this.xml = xml;
	}
	public File getPdf() {
		return pdf;
	}
	public void setPdf(File pdf) {
		this.pdf = pdf;
	}
	@Override
	public int compareTo(FacturaVo o) {
		// TODO Auto-generated method stub
		return this.xml.getName().trim().compareTo(o.xml.getName().trim());
	}
 
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj instanceof FacturaVo){
			FacturaVo other = (FacturaVo) obj;
			return this.xml.getName().equals(other.xml.getName());
		}
		return false;
	}
	
}
