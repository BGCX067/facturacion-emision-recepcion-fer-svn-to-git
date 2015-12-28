package com.beltsoft.jim.fer.service;

import com.beltsoft.jim.fer.exception.ServiceException;
import com.beltsoft.jim.fer.vo.CorreoVo;

public interface NotificacionService {
	 void enviarNotificacion(CorreoVo correo) throws ServiceException;

}
