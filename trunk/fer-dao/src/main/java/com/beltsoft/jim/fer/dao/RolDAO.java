package com.beltsoft.jim.fer.dao;

import java.util.List;
import java.util.Map;

import com.beltsoft.jim.fer.map.Funcionalidad;
import com.beltsoft.jim.fer.map.Rol;
import com.beltsoft.jim.fer.map.Usuario;

public interface RolDAO { 
	List<Rol> consultarPorNombre(Rol rol);

	List<Rol> consultarTodosRoles();
	
	List<Rol> consultarUsuarioRoles(Usuario usuario);
	
	List<Rol> consultarRolesOficina();
	
	List<Rol> consultarRolesFedatario();
	
	List<Rol> consultarRolesGenerales();

	List<Rol> consultarRoles(Usuario usuario);

	Boolean consultarSiExisteNombreRol(Rol rol);

	List<Funcionalidad> consultarFuncionalidadPorRol(Rol rol);

	List<Usuario> consultarUsuariosPorRol(Rol rol);

	List<Funcionalidad> consultarFuncionalidadDisponiblesPorRol(Rol rol);

	List<Usuario> consultarUsuariosDisponiblesPorRol(Rol rol);

	void eliminarFuncionalidadesPorRol(Map<String, Object> map);

	void insertarFuncionalidadesPorRol(Map<String, Object> map);

	void eliminarUsuariosPorRol(Map<String, Object> map);

	void insertarUsuariosPorRol(Map<String, Object> map);
	
	void modificar(Rol rol);
	
	void crear(Rol rol);

	
	
	List<Rol> consultarPorUsuario(Usuario usuario);
}
