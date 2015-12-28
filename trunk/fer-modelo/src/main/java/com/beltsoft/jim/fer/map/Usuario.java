package com.beltsoft.jim.fer.map;

import java.util.Date;
import java.util.List;

import com.beltsoft.jim.fer.vo.HusoHorario;

public class Usuario {
	 private static final long serialVersionUID = 1L;
	    
	    private Integer id;
	    private int estado;
	    private String nombre;
	    private String apellidoPaterno;
	    private String apellidoMaterno;
	    private String correo;
	    private String rfc;
	    private String telefono;
	    private String fea;
	    private String curp;
	    private String telContacto;
	    private String justificacion;
	    private String domicilio;
	    private Date fechaModificacion;
	    private Boolean esFedatario;
	    private Boolean activo;
	    private Boolean inactivo;
	    private Boolean bloqueado;
	    private Date fechaReactivacion;
	    private List<Rol> roles;
	    private String contrasenia;
	    private String nuevaContrasenia;
	    private String repContrasenia;
	    private byte[] foto;
	    private HusoHorario husoHorario;
	    private Integer intentosLogin;
	    private Date ultimoIntentoLogin;
	    private String idConfirmacion;
	    private int idGeneral;
		private int husoHorarioDefecto;
	    private Date fechaModContrasenia;
	    private String mimeTypeFoto;
	    private EstatusUsuario estatus;
	    public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public int getEstado() {
			return estado;
		}
		public void setEstado(int estado) {
			this.estado = estado;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getApellidoPaterno() {
			return apellidoPaterno;
		}
		public void setApellidoPaterno(String apellidoPaterno) {
			this.apellidoPaterno = apellidoPaterno;
		}
		public String getApellidoMaterno() {
			return apellidoMaterno;
		}
		public void setApellidoMaterno(String apellidoMaterno) {
			this.apellidoMaterno = apellidoMaterno;
		}
		public String getCorreo() {
			return correo;
		}
		public void setCorreo(String correo) {
			this.correo = correo;
		}
		public String getRfc() {
			return rfc;
		}
		public void setRfc(String rfc) {
			this.rfc = rfc;
		}
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		public String getFea() {
			return fea;
		}
		public void setFea(String fea) {
			this.fea = fea;
		}
		public String getCurp() {
			return curp;
		}
		public void setCurp(String curp) {
			this.curp = curp;
		}
		public String getTelContacto() {
			return telContacto;
		}
		public void setTelContacto(String telContacto) {
			this.telContacto = telContacto;
		}
		public String getJustificacion() {
			return justificacion;
		}
		public void setJustificacion(String justificacion) {
			this.justificacion = justificacion;
		}
		public String getDomicilio() {
			return domicilio;
		}
		public void setDomicilio(String domicilio) {
			this.domicilio = domicilio;
		}
		public Date getFechaModificacion() {
			return fechaModificacion;
		}
		public void setFechaModificacion(Date fechaModificacion) {
			this.fechaModificacion = fechaModificacion;
		}
		public Boolean getEsFedatario() {
			return esFedatario;
		}
		public void setEsFedatario(Boolean esFedatario) {
			this.esFedatario = esFedatario;
		}
		public Boolean getActivo() {
			return activo;
		}
		public void setActivo(Boolean activo) {
			this.activo = activo;
		}
		public Boolean getInactivo() {
			return inactivo;
		}
		public void setInactivo(Boolean inactivo) {
			this.inactivo = inactivo;
		}
		public Boolean getBloqueado() {
			return bloqueado;
		}
		public void setBloqueado(Boolean bloqueado) {
			this.bloqueado = bloqueado;
		}
		public Date getFechaReactivacion() {
			return fechaReactivacion;
		}
		public void setFechaReactivacion(Date fechaReactivacion) {
			this.fechaReactivacion = fechaReactivacion;
		}
		public List<Rol> getRoles() {
			return roles;
		}
		public void setRoles(List<Rol> roles) {
			this.roles = roles;
		}
		public String getContrasenia() {
			return contrasenia;
		}
		public void setContrasenia(String contrasenia) {
			this.contrasenia = contrasenia;
		}
		public String getNuevaContrasenia() {
			return nuevaContrasenia;
		}
		public void setNuevaContrasenia(String nuevaContrasenia) {
			this.nuevaContrasenia = nuevaContrasenia;
		}
		public String getRepContrasenia() {
			return repContrasenia;
		}
		public void setRepContrasenia(String repContrasenia) {
			this.repContrasenia = repContrasenia;
		}
		public byte[] getFoto() {
			return foto;
		}
		public void setFoto(byte[] foto) {
			this.foto = foto;
		}
		public Date getFechaModContrasenia() {
			return fechaModContrasenia;
		}
		public void setFechaModContrasenia(Date fechaModContrasenia) {
			this.fechaModContrasenia = fechaModContrasenia;
		}
		public String getMimeTypeFoto() {
			return mimeTypeFoto;
		}
		public void setMimeTypeFoto(String mimeTypeFoto) {
			this.mimeTypeFoto = mimeTypeFoto;
		}
		public EstatusUsuario getEstatus() {
			return estatus;
		}
		public void setEstatus(EstatusUsuario estatus) {
			this.estatus = estatus;
		}
		public Integer getIntentosLogin() {
			return intentosLogin;
		}
		public void setIntentosLogin(Integer intentosLogin) {
			this.intentosLogin = intentosLogin;
		}
		public Date getUltimoIntentoLogin() {
			return ultimoIntentoLogin;
		}
		public void setUltimoIntentoLogin(Date ultimoIntentoLogin) {
			this.ultimoIntentoLogin = ultimoIntentoLogin;
		}
		public String getIdConfirmacion() {
			return idConfirmacion;
		}
		public void setIdConfirmacion(String idConfirmacion) {
			this.idConfirmacion = idConfirmacion;
		}
		public int getIdGeneral() {
			return idGeneral;
		}
		public void setIdGeneral(int idGeneral) {
			this.idGeneral = idGeneral;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
	    
	    
	    public HusoHorario getHusoHorario() {
			return husoHorario;
		}
		public void setHusoHorario(HusoHorario husoHorario) {
			this.husoHorario = husoHorario;
		}
		public int getHusoHorarioDefecto() {
			return husoHorarioDefecto;
		}
		public void setHusoHorarioDefecto(int husoHorarioDefecto) {
			this.husoHorarioDefecto = husoHorarioDefecto;
		}

}
