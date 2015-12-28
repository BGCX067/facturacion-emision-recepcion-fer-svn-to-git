package com.beltsoft.jim.fer.service;

import java.util.List;

import com.beltsoft.jim.fer.exception.ServiceException;
import com.beltsoft.jim.fer.map.Rol;
import com.beltsoft.jim.fer.map.Usuario;

public interface UsuarioService {
	/**
	 * Método para consultar usuarios
	 * @param usuario
	 */
	List<Usuario> consultarUsuario(String usuario);
	/**
	 * Método para dar de alta a un usuario
	 * @param usuario, List<Rol>, List<Grupo>, oficina, fedatario
	 *  List<Grupo> grupos, CtOficina oficina, Fedatario fedatario
	 */
	void crear(Usuario usuario, List<Rol> roles);
	/**
	 * Método que actualiza la información del usuario
	 * @param usuario, List<Rol>, List<Grupo>, oficina, fedatario
	 * , List<Grupo> grupos, CtOficina oficina, Fedatario fedatario
	 */
	void actualizar(Usuario usuario, List<Rol> roles);
	/**
	 * Método que valida y modifica la contraseña del usuario, junto con la imagen y huso Horario
	 * @param usuario
	 */
	void modificarIdentificacion(Usuario usuario);
	
	/**
	 * Método que realiza la validación del acceso del usuario
	 * 
	 * @param usuario del cual se está realizando la validación de su acceso
	 * @exception ServiceException para notificación de reglas incumplidas
	 */
	Usuario login(Usuario usuario)throws ServiceException ;
	
	/**
	 * Método que obtiene los usuarios disponibles en el sistema
	 * 
	 * @return Lista de usuarios
	 */
	List<Usuario> consulta();
	/**
	 * Método que obtiene los usuarios asignados a una oficina
	 * 
	 * @return Lista de usuarios
	 */
	List<Usuario> consultaUsuariosOficina(List<Usuario> usuarios);
	/**
	 * Método que obtiene los usuarios asignados a un fedatario
	 * 
	 * @return Lista de usuarios
	 */
	List<Usuario> consultaUsuariosFedatario(List<Usuario> usuarios);
	/**
	 * Metodo que se utiliza para la creacion de nuevos usuarios de autoservicio
	 * @param usuario
	 */
	void crearUsuarioAutoservicio(Usuario usuario) throws ServiceException;
	
	/**
	 * Metodo que se utiliza para la activacion del estatus de los usuarios de autoservicio 
	 * @param usuario
	 */
	int activaEstatusUsuarioAutoservicio(Integer usuario);
	
	/**
	 * Metodo que obtiene las oficinas por un usuario dado.
	 * 
	 * @param idUsuario
	 * @return
	 */
	
	/*
	 * Metodo que devuenle el usuario que reviso una solicitud*
	 */
	Usuario obtieneUsuarioRevisor(String nci);
	
	Usuario obtenerOficinasPorUsuario(Integer idUsuario);
	//CUO01
	Integer usuarioOficina(String usr);
	//CUO01
//	List<Usuario> consultaUsuarioPorOficina(CtOficina oficina);
	
}
