package com.beltsoft.jim.fer.negocio.util;

public class FeConstantes {
	public static enum Rol {
		None, Administrador, Administrador_Proveedor, Administrador_PyG, Usuario_Proveedor, Usuario_PyG, Soporte, Usuario_HP, Proveedor_Terceros
	};

	public static enum Estatus_Usuario {
		Inactivo, Activo, Pwd_Provisional, Bloqueado
	};

	public static enum Estatus_Bloqueo_Proveedor {
		Sin_bloqueo, Bloqueado, Bloqueo_temporal
	};

	public static enum Tipo_Correo {
		Empty, Recuperacion_Pwd, Registro_Usr, Validacion, Validacion_correo, Validacion_correo_error, Solo_Validacion_correo
	};

//	public static final String PROPIEDAD_DOMINIO_APROBADOR_EMAIL = "CATALOGOS_DOMINIO_APROBADOR_EMAIL";

	public static final String PROPIEDAD_CORREOS_NOTIFICACION_USUARIOS_NUEVOS = "MAIL_NEW_USERS_DISTRIBUTION_LIST";
	
	public static final String PROPIEDAD_MSG_ENVIO_NO_PERMITIDO = "SYS_AVISO_BLOQUEO";
	
	public static final String PROPIEDAD_SYS_ENABLE_TESTS = "SYS_ENABLE_TESTS";

	public static final int DEFAULT_RESGUARDO = 3;

	public static final String APPLICATION_RESOURCES= "mx.com.sfe.struts.ApplicationResources";
	
	public static enum Tipo_Escenario {
		None, Propios, Servicios, Consignacion
	};
	
	public static enum Codigo_Trans_Sap {
		None, COC, FIN, ABN, CSG
	}
}
