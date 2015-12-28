package com.beltsoft.jim.fer.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.apache.commons.codec.digest.DigestUtils;

import com.beltsoft.jim.fer.exception.ServiceException;
import com.beltsoft.jim.fer.map.Usuario;
import com.beltsoft.jim.fer.service.UsuarioService;
import com.beltsoft.jim.fer.util.Constantes;
import com.beltsoft.jim.fer.util.LoginUtil;

@ManagedBean(name="loginMB")
@SessionScoped
public class LoginMB extends BaseMB implements Serializable {
private static final Logger LOG = Logger.getLogger(LoginMB.class);
	
	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioLogin = new Usuario();
	
	@ManagedProperty(value = "#{usuarioService}")
	private UsuarioService usuarioService;
	
	
	private FacesContext facesContext = null;
	
	/**
	 * Método que realiza el login de usuarios en el sistema, consideraciones:
	 * 	1.- Si el usuario genera más de 3 intentos fallidos, la cuenta es bloqueda
	 *  2.- Se valida que el usuario esté activo para permitirle el acceso
	 *  3.- En caso de que el usuario esté activo se cargan sus permisos y se permite el acceso al sistema
	 * 
	 * @return
	 * @throws ServiceException 
	 */
	public String login() throws ServiceException{
		facesContext = FacesContext.getCurrentInstance();
		
		//Usamos un objeto distinto, pues la consulta podría retornar el mismo objeto en nulo
		Usuario usuarioAValidar = new Usuario();
		
		usuarioAValidar.setCorreo(usuarioLogin.getCorreo());
		usuarioAValidar.setContrasenia(usuarioLogin.getContrasenia());
		usuarioAValidar = usuarioService.login(usuarioAValidar);
		
		facesContext.getExternalContext().getSessionMap().put("usuario", usuarioAValidar);
		
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				usuarioAValidar.getCorreo(), usuarioAValidar.getContrasenia(),
				LoginUtil.obtenerGrantedAuthorityDeFuncionalidad(usuarioAValidar.getRoles()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		facesContext.getExternalContext().getSessionMap().put("recienLoggeado", true);
		
		return Constantes.CASO_NAVEGACION_INICIO;
	}
	
	/**
	 * @return the usuarioLogin
	 */
	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}

	/**
	 * @param usuarioLogin the usuarioLogin to set
	 */
	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	/**
	 * @return the usuarioService
	 */
	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	/**
	 * @param usuarioService the usuarioService to set
	 */
	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}



	public static void main(String[] args) {
		LOG.info(DigestUtils.md5Hex("admin"));
	}
	
}
