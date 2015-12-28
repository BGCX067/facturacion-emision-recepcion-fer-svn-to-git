package com.beltsoft.jim.fer.map;

import java.io.Serializable;
import java.util.Date;

public class Factura implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1876492167972859095L;

	
	private int idFactura;
	private String rfcEmisor;
	private String rfcReceptor;
	private String uuid;
	private String acuseEstado;
	private String acuseCodigo;
	private Date fechaEmision;
	private Date fechaCreacion;
	private Date fechaTimbrado;
	private byte[] factura;
	private byte[] xml;
	
	public int getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}
	public String getRfcEmisor() {
		return rfcEmisor;
	}
	public void setRfcEmisor(String rfcEmisor) {
		this.rfcEmisor = rfcEmisor;
	}
	public String getRfcReceptor() {
		return rfcReceptor;
	}
	public void setRfcReceptor(String rfcReceptor) {
		this.rfcReceptor = rfcReceptor;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Date getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getFechaTimbrado() {
		return fechaTimbrado;
	}
	public void setFechaTimbrado(Date fechaTimbrado) {
		this.fechaTimbrado = fechaTimbrado;
	}
	public byte[] getFactura() {
		return factura;
	}
	public void setFactura(byte[] factura) {
		this.factura = factura;
	}
	public byte[] getXml() {
		return xml;
	}
	public void setXml(byte[] xml) {
		this.xml = xml;
	}
	public String getAcuseEstado() {
		return acuseEstado;
	}
	public void setAcuseEstado(String acuseEstado) {
		this.acuseEstado = acuseEstado;
	}
	public String getAcuseCodigo() {
		return acuseCodigo;
	}
	public void setAcuseCodigo(String acuseCodigo) {
		this.acuseCodigo = acuseCodigo;
	}
	
	public String toString(){
		return rfcEmisor + " " +rfcReceptor + " "+ acuseEstado+" "+uuid;
	}
}
