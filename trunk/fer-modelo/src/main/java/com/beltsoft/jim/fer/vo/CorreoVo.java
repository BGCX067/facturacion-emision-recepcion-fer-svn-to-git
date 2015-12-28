package com.beltsoft.jim.fer.vo;

public class CorreoVo {
	private String destinatario;
	private String remitente;
	
	Correo correo;
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public Correo getCorreo() {
		return correo;
	}
	public void setCorreo(Correo correo) {
		this.correo = correo;
	}
	public String getRemitente() {
		return remitente;
	}
	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}
}
