package com.beltsoft.jim.fer.vo;

import java.io.InputStream;


public class FacturaFiles {
	
	
	private byte[]xmlContent;
	private byte[]pdfContent;
	private byte[]zipContent;
	private InputStream zipIS;
	private InputStream xmlIS;
	private String xmlFileName;
	private String pdfFileName;
	private String zipFileName;
	
	public byte[] getXmlContent() {
		return xmlContent;
	}
	public void setXmlContent(byte[] xmlContent) {
		if(xmlContent== null){
			
		}else{
			this.xmlContent = (byte[])xmlContent.clone();
		}
	}
	public byte[] getPdfContent() {
		return pdfContent;
	}
	public void setPdfContent(byte[] pdfContent) {
		this.pdfContent = pdfContent;
	}
	public byte[] getZipContent() {
		return zipContent;
	}
	public void setZipContent(byte[] zipContent) {
		this.zipContent = zipContent;
	}
	public String getXmlFileName() {
		return xmlFileName;
	}
	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
	}
	public String getPdfFileName() {
		return pdfFileName;
	}
	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}
	public String getZipFileName() {
		return zipFileName;
	}
	public void setZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
	}

	public boolean isWithFiles(){
		return (this.pdfFileName!=null || this.xmlFileName!=null || this.zipFileName!=null);
	}
	public InputStream getZipIS() {
		return zipIS;
	}
	public void setZipIS(InputStream zipIS) {
		this.zipIS = zipIS;
	}
	public InputStream getXmlIS() {
		return xmlIS;
	}
	public void setXmlIS(InputStream xmlIS) {
		this.xmlIS = xmlIS;
	}
}
