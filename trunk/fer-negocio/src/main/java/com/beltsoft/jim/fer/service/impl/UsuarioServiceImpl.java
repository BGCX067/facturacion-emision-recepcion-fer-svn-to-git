package com.beltsoft.jim.fer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beltsoft.jim.fer.dao.RolDAO;
import com.beltsoft.jim.fer.dao.UsuarioDAO;
import com.beltsoft.jim.fer.dao.exception.PersistenciaException;
import com.beltsoft.jim.fer.exception.ServiceException;
import com.beltsoft.jim.fer.map.EstatusUsuario;
import com.beltsoft.jim.fer.map.Rol;
import com.beltsoft.jim.fer.map.Usuario;
import com.beltsoft.jim.fer.service.NotificacionService;
import com.beltsoft.jim.fer.service.UsuarioService;
import com.beltsoft.jim.fer.util.Constantes;
import com.beltsoft.jim.fer.util.MensajeCorreoUtil;
import com.beltsoft.jim.fer.vo.CorreoVo;

@Service(value="usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	private static final Logger logger = Logger.getLogger(UsuarioService.class);
	private Usuario usuarioid;

	@Autowired 
	private UsuarioDAO usuarioDao;
	
//	@Autowired
//	private AutoservicioUtil autoservicioUtil;
	
	@Autowired
	private RolDAO rolDAO;
	
	//envio de mails por implementar
	@Autowired
	private NotificacionService notificacion;
	
	@Override
	public List<Usuario> consultarUsuario(String usuario) {
		List<Usuario> listaUsuarios = usuarioDao.consultarUsuario(usuario);
		return listaUsuarios;
	}
	
	@Transactional
	public void crear(Usuario usuario, List<Rol> roles) {
		usuario = asignarHusoHorario(usuario);
		usuarioDao.crear(usuario);
		logger.debug("El id del usuario creado es...." + usuario.getId());
		logger.debug("-------------------Comenzando el proceso de guardado de oficina--------------");
//			altaOficina(usuario, oficina);
		logger.debug("-------------------Comenzando el proceso de guardado de roles----------------");
			altaRoles(roles, usuario);
		logger.debug("-------------------Comenzando el proceso de guardado de grupos----------------");
//			altaGrupos(grupos, usuario);
		
	}
	
	@Transactional
	public void actualizar(Usuario usuario, List<Rol> roles){
		logger.debug("------------------Comenzando el proceso de borrado de roles---------------------");
		usuarioDao.eliminarUsuarioRoles(usuario);
		logger.debug("------------------Comenzando el proceso de borrado de grupos--------------------");
		usuarioDao.eliminarUsuarioGrupos(usuario);
		logger.debug("------------------Comenzando el proceso de borrado de oficina-------------------");
		usuarioDao.eliminarUsuarioOficina(usuario);
		logger.debug("------------------Comenzando el proceso de actualización de datos---------------");
		usuario = asignarHusoHorario(usuario);
		usuarioDao.actualizar(usuario);
		logger.debug("------------------Comenzando el proceso de guardado de oficina------------------");
//		altaOficina(usuario, oficina);
		logger.debug("------------------Comenzando el proceso de guardado de roles--------------------");
		altaRoles(roles, usuario);
		logger.debug("------------------Comenzando el proceso de guardado de grupos-------------------");
//		altaGrupos(grupos, usuario);		
	}
	
	private Usuario asignarHusoHorario(Usuario usuario){
		logger.debug("Añadiendo huso horario del centro por defecto................................");
		usuario.setHusoHorarioDefecto(1);
		return usuario;
	}
	
	private Map<String, Integer> altaRoles(List<Rol> roles, Usuario usuario){
		Map<String, Integer> listaRoles = new HashMap<String, Integer>();
		if(roles != null){
			listaRoles.put("Usuario", usuario.getId());
			for(Rol rol : roles){
				logger.debug("rol..............."         +rol.getNombre() + 		" guardado al usuario " +usuario.getNombre());
				listaRoles.put("Rol", rol.getId());
				usuarioDao.crearUsuarioRol(listaRoles);
			}
		}
		return listaRoles;
	}
	
//	private Map<String, Integer> altaGrupos(List<Grupo> grupos, Usuario usuario){
//		Map<String, Integer> listaGrupos = new HashMap<String, Integer>();
//		if(grupos != null){
//			listaGrupos.put("Usuario", usuario.getId());
//			for(Grupo grupo: grupos){
//				logger.debug("grupo............."		+grupo.getNombreGrupo() + 	" guardado al usuario " +usuario.getNombre());
//				listaGrupos.put("Grupo", grupo.getId());
//				usuarioDao.crearUsuarioGrupo(listaGrupos);
//			}
//		}
//		return listaGrupos;
//	}
//	
//	private Map<String, Integer> altaOficina(Usuario usuario, CtOficina oficina){
//
//		Map<String, Integer> ofc = new HashMap<String, Integer>();
//		if(oficina != null){
//			ofc.put("Usuario", usuario.getId());
//			ofc.put("Oficina", oficina.getId().intValue());
//			usuarioDao.crearUsuarioOficina(ofc);
//		}
//		return ofc;
//	}	

	/**
	 * 
	 */
	@Override
	public void modificarIdentificacion(Usuario usuario)  {
		if(usuario.getContrasenia()!=null && !usuario.getContrasenia().trim().equals("")){
			usuario.setContrasenia(DigestUtils.md5Hex(usuario.getContrasenia()));
		}
		usuario.setFechaModContrasenia(new Date());
		if(usuarioDao.validarContrasenia(usuario))
		{
			usuario.setNuevaContrasenia(DigestUtils.md5Hex(usuario.getNuevaContrasenia()));
			usuarioDao.actualizarIdentificacion(usuario);
		}else
		{	
			usuario.setContrasenia("");
			usuarioDao.actualizarIdentificacion(usuario);
		}	
		
	}
	/* (non-Javadoc)
	 * @see mx.gob.se.siger2.service.usuario.UsuarioService#login(mx.gob.se.siger2.modelo.mappers.Usuario)
	 */
	@Transactional
	@Override	
	public Usuario login(Usuario usuario) throws ServiceException {
		final int intentosMaximoDeLogin 		= 3;
		final int minutosParaReseteoDeIntentos	= 30;
		String correo = usuario.getCorreo();
		
		usuario.setContrasenia(DigestUtils.md5Hex(usuario.getContrasenia()));
		logger.info(":::::::::DASDSDASDASDASAS::::::::::::::PASOOSOSOSOSOSOSO");
		Usuario usuarioEnSistema = usuarioDao.validarAcceso(usuario);
		logger.info("::::::::::::::::::::::::PASOOSOSOSOSOSOSO");
		if (usuarioEnSistema != null){
			if(usuarioEnSistema.getEstatus().getId().equals(Constantes.ACTIVO)){
				usuarioEnSistema.setRoles(rolDAO.consultarPorUsuario(usuarioEnSistema));
				usuarioEnSistema.setUltimoIntentoLogin(new Date());
				//reiniciamos el conteo de intentos fallidos y actualizamos fecha de último login
				usuarioDao.actualizarFeachaLogin(usuarioEnSistema);
			} else {
				identificarEstatusUsuarioNoPermitido(usuarioEnSistema.getEstatus().getId());
			}
			
		} else {//no existe usuario por correo y contraseña, ahora validamos si existe solo con el correo
			Usuario usuarioNoPermitido = usuarioDao.consultarPorCorreo(correo);
			/*
			 * Para determinar si el usuario que intenta loguearse existe registrado con el correo proporcionado,
			 * si éste existe, entonces se registra intento fallido
			 */
			if (usuarioNoPermitido!=null){
				identificarEstatusUsuarioNoPermitido(usuarioNoPermitido.getEstatus().getId());
				//Antes de registrar el intento fallido, calculamos cuánto tiempo ha transcurrido desde el último intento 
				int minutosTranscurridosUltimoIntetoLogin = calcularMinutosTranscurridosDesdeAActual(usuarioNoPermitido.getUltimoIntentoLogin());
				int intentosLogin = 0;
				
				usuarioNoPermitido.setUltimoIntentoLogin(new Date());
				usuarioDao.registrarIntentoLogin(usuarioNoPermitido);
				if(usuarioNoPermitido.getIntentosLogin()!=null){
					//registramos el incremento
					intentosLogin = usuarioNoPermitido.getIntentosLogin()+1;
				}
				//agregamos el intento registrado
				usuarioNoPermitido.setIntentosLogin(intentosLogin);

				//si el tiempo transcurrido es mayor al tiempo necesario para el reseteo de intentos fallidos
				//entonces reseteamos los intentos a 1
				if(minutosTranscurridosUltimoIntetoLogin > minutosParaReseteoDeIntentos){
					usuarioDao.reiniciarIntentosLogin(usuarioNoPermitido);
					usuarioNoPermitido.setIntentosLogin(1);//reseteamos intentos a 1
				} 
				
				/*
				 * bloqueamos el usuario si ha excedido en el número de intentos permitídos 
				 */
				if(usuarioNoPermitido.getIntentosLogin().equals(intentosMaximoDeLogin)){
					EstatusUsuario estatus = new EstatusUsuario();
					estatus.setId(Constantes.BLOQUEADO);
					usuarioNoPermitido.setEstatus(estatus);
					usuarioDao.actualizarEstatus(usuarioNoPermitido);
					throw new ServiceException("login.ingreso.msg.usuarioBloqueado");
				} 
			}
			throw new ServiceException("login.ingreso.msg.usuarioNoRegistrado");
		}
		return usuarioEnSistema;
	}
	/* (non-Javadoc)
	 * @see mx.gob.se.siger2.service.usuario.UsuarioService#consultar()
	 */
	public List<Usuario> consulta(){
		return usuarioDao.consulta();
	}
	/* (non-Javadoc)
	 * @see mx.gob.se.siger2.service.usuario.UsuarioService#consultaUsuariosOficina(java.util.List)
	 */
	@Override
	public List<Usuario> consultaUsuariosOficina(List<Usuario> usuarios) {
		return usuarioDao.consultaUsuariosOficina(usuarios);
	}

	/* (non-Javadoc)
	 * @see mx.gob.se.siger2.service.usuario.UsuarioService#consultaUsuariosFedatario(java.util.List)
	 */
	public List<Usuario> consultaUsuariosFedatario(List<Usuario> usuarios){
		return usuarioDao.consultaUsuariosFedatario(usuarios);
	}
	/**
	 * Método que identificar el estatus del usuario al que no se le permitirá el acceso
	 * 
	 * @param estatus a evaluar
	 * @throws ServiceException para notificar la razón por la cual no se permite el acceso
	 */
	private void identificarEstatusUsuarioNoPermitido(Integer estatus)throws ServiceException{
		if(estatus.equals(Constantes.INACTIVO)){
			throw new ServiceException("login.ingreso.msg.usuarioInactivo");
		} else if(estatus.equals(Constantes.BLOQUEADO)){
			throw new ServiceException("login.ingreso.msg.usuarioBloqueado");
		} 
	}
	
	/**
	 * Método que obtiene los minutos transcurridos del último acceso con la fecha y hora actual
	 * 
	 * @param fechaDesde fecha inicial desde la cual se realizará el cálculo de minutos transcurridos
	 * @return minutos transcurridos 
	 */
	private int calcularMinutosTranscurridosDesdeAActual(Date fechaDesde){
		DateTime fechaInicioJod = new DateTime(fechaDesde);
		DateTime fechaFinJod = new DateTime(new Date());
		
		int minutosTranscurridos = Minutes.minutesBetween(fechaInicioJod, fechaFinJod).getMinutes();
		return minutosTranscurridos;
	}
	
	/**
	 * Metodo que inicia la validacion de los usuarios existentes por medio de su curp y de su correo
	 * para la creacion del nuevo usuario, al igual envia la notificacion de confirmacion de registro
	 */
	@Override
	@Transactional
	public void crearUsuarioAutoservicio(Usuario usuario) throws ServiceException {
		String contraseniaSinMD5 = usuario.getContrasenia();
		try{
			String curpUsu = usuario.getCurp();
			String correoUsu = usuario.getCorreo();	
			
			Usuario usuarioCurp = usuarioDao.consultarPorCurp(curpUsu);
			Usuario usuarioCorreo = usuarioDao.consultarPorCorreo(correoUsu);
			
			if( usuarioCorreo != null || usuarioCurp != null){
				throw new ServiceException("usuarioAutoservicio.errormsg.usuarioExistente");
			}else{
//				usuario.setIdConfirmacion(autoservicioUtil.crearNoConfirmacion());
				//encriptamos la contraseña con hash
				usuario.setContrasenia(DigestUtils.md5Hex(usuario.getContrasenia()));
				
				usuarioDao.crearUsuarioAtoservicio(usuario);
			}
		}catch (DataAccessException e) {
			//hacemos persistenciaException cuando el usuario sufrio problemas al conectar con la BD
		    logger.info(e.getMessage(),e);
			throw new PersistenciaException("usuarioAutoservicio.errormsg.almacnNoexitoso");
		}
		
		try {
			//le regresamos a la contraseña sin hash
			usuario.setContrasenia(contraseniaSinMD5);
			CorreoVo correo =  MensajeCorreoUtil.crearNotificacionRegistrAuto(usuario);
			this.notificacion.enviarNotificacion(correo);
		} catch (ServiceException e) {
			//hacemos rollback a la transacción, pues la notificación no se llevó a acabo y no se debe de permitir el registro de usuario
			throw new PersistenciaException("usuarioAutoservicio.errormsg.correoError");
		}
		
	}
	/**
	 * Metodo que se utiliza para la activacion de los usuarios de autoservicio por medio del correo de confirmacion
	 */
	@Override
	public int activaEstatusUsuarioAutoservicio(Integer usuario) {
		return usuarioDao.activarUsuarioAutoservicio(usuario);
	}
//	@Override
//	public List<Usuario> consultaUsuarioPorOficina(CtOficina oficina) {
//		
//		return usuarioDao.consultaUsuarioPorOficina(oficina);
//	}

	public Usuario getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(Usuario usuarioid) {
		this.usuarioid = usuarioid;
	}
	/**
	 * @return the usuarioDao
	 */
	public UsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}

	/**
	 * @param usuarioDao the usuarioDao to set
	 */
	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	/**
	 * @return the rolDAO
	 */
	public RolDAO getRolDAO() {
		return rolDAO;
	}

	/**
	 * @param rolDAO the rolDAO to set
	 */
	public void setRolDAO(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}
	
	/**
	 * Metodo que obtiene las oficinas por un usuario dado.
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Usuario obtenerOficinasPorUsuario(Integer idUsuario) {
		return usuarioDao.obtenerOficinasPorUsuario(idUsuario);
	}

	//metodo que devuelve el usuario que reviso silicitud
	public Usuario obtieneUsuarioRevisor(String nci){
		return usuarioDao.obtieneUsuarioRevisor(nci);
		
	}
	
	//CUO01
	@Override
	public Integer usuarioOficina(String usr) {
		return usuarioDao.usuarioOficina(usr);
	}


}
