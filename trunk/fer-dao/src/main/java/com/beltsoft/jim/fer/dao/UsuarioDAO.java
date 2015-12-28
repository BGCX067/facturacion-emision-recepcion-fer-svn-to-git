package com.beltsoft.jim.fer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.beltsoft.jim.fer.map.Rol;
import com.beltsoft.jim.fer.map.Usuario;

public interface UsuarioDAO { 
	List<Usuario> consultarUsuario(String usuario);
	
	void crear(Usuario usuario);
	void crearUsuarioAtoservicio(Usuario usuario);
	void crearUsuarioRol(Map<String, Integer> map);
	void crearUsuarioGrupo(Map<String, Integer> map);
	void crearUsuarioOficina(Map<String, Integer> map);
	
	void eliminarUsuarioRoles(Usuario usuario);
	void eliminarUsuarioGrupos(Usuario usuario);
	void eliminarUsuarioOficina(Usuario usuario);

	void actualizar(Usuario usuario);

	
	List<Rol> consultarRoles(Usuario usuario);
	/**
	 * Metodo que valida si la contrasenia ingresada es la misma a la de la BD y regresa true 
	 * si se cumple 
	 * @param usuario
	 * @return
	 */
	Boolean validarContrasenia(Usuario usuario);
	/**
	 * Metodo que actualiza la informacion del usuario segun los cambios que haya echo
	 * @param usuario
	 */
	void actualizarIdentificacion(Usuario usuario);
	/**
	 * Metodo que valida que el usuario este registrado en el sistema, si este se
	 * encuentra registrado, entonces, se obtine la informacion del usuario
	 * 
	 * @param usuario del cual se esta realizando la validacion
	 * @return la informacion del usuario, encaso de que exista registro en la Base de Datos
	 */
	Usuario validarAcceso(Usuario usuario);
	/**
	 * Metodo que obtiene los usuarios disponibles en el sistema
	 * 
	 * @return Lista de usuarios
	 */
	List<Usuario> consulta();
	/**
	 * Metodo que obtiene los usuarios asignados a una oficina
	 * 
	 * @return Lista de usuarios
	 */
	List<Usuario> consultaUsuariosOficina(List<Usuario> usuarios);
	/**
	 * Metodo que obtiene los usuarios asignados a un fedatario
	 * 
	 * @return Lista de usuarios
	 */
	List<Usuario> consultaUsuariosFedatario(List<Usuario> usuarios);
	/**
	 * Metodo que valida que los usuarios esten registrados a una oficina
	 * @param usuarios
	 * @return Numero de usuarios validos
	 */
	Integer validacionUsuariosOficina(List<Usuario> usuarios);
	/**
	 * Metodo que valida que los usuarios esten registrados a un fedatario
	 * @param usuarios
	 * @return Numero de usuarios validos
	 */
	Integer validacionUsuariosFedatario(List<Usuario> usuarios);
	
	
	/**
	 * Metodo que realiza la modificacion del estatus de un determinado usuario
	 * 
	 * @param usuario usuario al cual se le actualizara el estatus
	 */
	void actualizarEstatus(Usuario usuario);
	
	/**
	 * Metodo que registra el intento fallido de login de un determinado usuario
	 * 
	 * @param usuario del cual se registrara el intento de login
	 */
	int registrarIntentoLogin(Usuario usuario);
	
	/**
	 * Metodo que obtiene informacion del usuario segun correo proporcionado 
	 * 
	 * @param correo mediante el cual se realizara la consulta
	 * @return informacion de usuario en caso de que este exista
	 */
	Usuario consultarPorCorreo(String correo);
	
	/**
	 * Metodo que actualiza el contador de intentos fallidos de login
	 * 
	 * @param usuario al cual se le reiniciara el conteo de login
	 */
	void reiniciarIntentosLogin(Usuario usuario);
	
	/**
	 * Metodo para actualizar la fecha de ultimo acceso al sistema del usuario en cuestion
	 * 
	 * @param usuario al que se le actualizara la fecha de ultimo login
	 */
	void actualizarFeachaLogin(Usuario usuario);
	
	/**
	 * Metodo que se utiliza para saber si el usuario que se crea ya existe en la BD 
	 * @param curp
	 * @return
	 */
	Usuario consultarPorCurp(String curp);
	
	/**
	 * Metodo que se utiliza para el cambio del estatus de los usuarios de autoservicio
	 * @param usuario
	 * @return registros actualizados
	 */
	int activarUsuarioAutoservicio(Integer usuario);
	
	Long obtenerSiguienteNoConfirmacion();
	
	/**
	 * Metodo que obtiene las oficinas por un usuario dado.
	 * 
	 * @param idUsuario
	 * @return
	 */
	Usuario obtenerOficinasPorUsuario(@Param("idUsuario") Integer idUsuario);
    
	/**
	 * Metodo que valida si el usuario es un administrador nacional o no
	 * @param usuario
	 * @return
	 */
	Boolean validarAdmonNacional(Usuario usuario);
	
	/**
	 * Metodo que devuele el usuario que reviso la solicitud
	 * **/
	public Usuario obtieneUsuarioRevisor(String nci);
	
	//CUO01
	Integer usuarioOficina(String usr);
	//CUO01
	

//List<Usuario> consultaUsuarioPorOficina(CtOficina oficina);
	
	
}
