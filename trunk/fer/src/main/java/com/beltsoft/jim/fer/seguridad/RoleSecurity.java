package com.beltsoft.jim.fer.seguridad;

import org.springframework.security.core.GrantedAuthority;

public class RoleSecurity implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
	private String nombre;
	
	
	public String getAuthority() {
		return this.nombre;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}