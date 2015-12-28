package com.beltsoft.jim.fer.map;

import java.util.Date;

public class Funcionalidad {
	private Integer id;
	private String nombre;
	private Date fechaModfuncionalidad;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFechaModfuncionalidad() {
		return fechaModfuncionalidad;
	}
	public void setFechaModfuncionalidad(Date fechaModfuncionalidad) {
		this.fechaModfuncionalidad = fechaModfuncionalidad;
	}
	public Boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	private Boolean estatus;
	private String codigo;
}
