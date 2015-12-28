package com.beltsoft.jim.fer.map;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Rol implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nombre;
	private Boolean disOficina;
	private Boolean disFedatario;
	private Boolean disFirma;
	private Date fechaModRol;
	private Boolean estatus;
	private List<Funcionalidad> funcionalidades;
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
	public Boolean getDisOficina() {
		return disOficina;
	}
	public void setDisOficina(Boolean disOficina) {
		this.disOficina = disOficina;
	}
	public Boolean getDisFedatario() {
		return disFedatario;
	}
	public void setDisFedatario(Boolean disFedatario) {
		this.disFedatario = disFedatario;
	}
	public Boolean getDisFirma() {
		return disFirma;
	}
	public void setDisFirma(Boolean disFirma) {
		this.disFirma = disFirma;
	}
	public Date getFechaModRol() {
		return fechaModRol;
	}
	public void setFechaModRol(Date fechaModRol) {
		this.fechaModRol = fechaModRol;
	}
	public Boolean getEstatus() {
		return estatus;
	}
	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * @return the funcionalidades
	 */
	public List<Funcionalidad> getFuncionalidades() {
		return funcionalidades;
	}

	/**
	 * @param funcionalidades the funcionalidades to set
	 */
	public void setFuncionalidades(List<Funcionalidad> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

}
