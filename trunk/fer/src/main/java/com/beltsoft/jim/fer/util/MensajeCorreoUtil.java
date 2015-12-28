package com.beltsoft.jim.fer.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.beltsoft.jim.fer.map.Usuario;
import com.beltsoft.jim.fer.vo.Correo;
import com.beltsoft.jim.fer.vo.CorreoVo;

public class MensajeCorreoUtil {
	private static final Logger LOGGER = Logger.getLogger(MensajeCorreoUtil.class);
	public static final Properties MENSAJES_PROPERTIES;
	
	private MensajeCorreoUtil() {
		
	}

	static {
		MENSAJES_PROPERTIES = new Properties();
			try {
				MENSAJES_PROPERTIES.load( MensajeCorreoUtil.class.getClassLoader().getResourceAsStream("mensajesCorreo.properties"));
			} catch (IOException e) {
				LOGGER.error(e.getMessage(),e);
			}
		
	}

	/**
	 * Metodo que se utiliza para complementar la creacion del correo de confirmacion para activar la cuenta creada
	 * @param usuario
	 * @return 
	 */
	public static CorreoVo crearNotificacionRegistrAuto(Usuario usuario) {
		Correo correo = new Correo();
		CorreoVo correoVO = new CorreoVo();
		correoVO.setDestinatario(usuario.getCorreo());
				
		String elMensaje = MessageFormat.format(MENSAJES_PROPERTIES.getProperty("usuarioAutoservicio.confirmacion.mensaje"), usuario.getId(), usuario.getIdConfirmacion(), usuario.getCorreo(), usuario.getContrasenia());
		correo.setAsunto(MENSAJES_PROPERTIES.getProperty("usuarioAutoservicio.confirmacion.asunto"));
		correo.setMensaje(elMensaje);
		correoVO.setCorreo(correo);
		return correoVO;
	}
}
