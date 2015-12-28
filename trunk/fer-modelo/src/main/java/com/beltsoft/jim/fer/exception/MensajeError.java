package com.beltsoft.jim.fer.exception;

import java.util.List;

public class MensajeError {
	private List<Error> errores;
	private Boolean iraInicio;
	public List<Error> getErrores() {
		return errores;
	}
	public void setErrores(List<Error> errores) {
		this.errores = errores;
	}
	public Boolean getIraInicio() {
		return iraInicio;
	}
	public void setIraInicio(Boolean iraInicio) {
		this.iraInicio = iraInicio;
	}
}

class Error{
	private String mensaje;
	private String codigo;
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
