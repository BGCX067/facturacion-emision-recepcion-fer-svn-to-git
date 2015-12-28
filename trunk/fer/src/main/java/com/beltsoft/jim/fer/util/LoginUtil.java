package com.beltsoft.jim.fer.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.beltsoft.jim.fer.map.Funcionalidad;
import com.beltsoft.jim.fer.map.Rol;
import com.beltsoft.jim.fer.seguridad.RoleSecurity;

public class LoginUtil {
private LoginUtil(){
		
	}
	/**
	 * Método que pasa roles de usuario a permisos de spring security
	 * 
	 * @param roles que se pasarán a permisos spring
	 * @return permisos según roles proporcionados
	 */
	public static List<GrantedAuthority> obtenerGrantedAuthorityDeFuncionalidad(List<Rol> roles){
		final List<GrantedAuthority> permisos = new ArrayList<GrantedAuthority>();
		RoleSecurity rolSecurity = null;
		if(roles!=null){
			for(Rol rol : roles){		
				for (Funcionalidad funcionalidad : rol.getFuncionalidades() ) {
					rolSecurity = new RoleSecurity();
					rolSecurity.setNombre(funcionalidad.getCodigo());
					permisos.add(rolSecurity);
				}
			}
		}
		return permisos;
	}
}
