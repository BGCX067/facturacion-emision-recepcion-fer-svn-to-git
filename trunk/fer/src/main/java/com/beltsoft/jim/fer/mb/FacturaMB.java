package com.beltsoft.jim.fer.mb;

import java.io.IOException;
import java.io.Serializable;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import com.beltsoft.jim.fer.exception.ServiceException;
import com.beltsoft.jim.fer.map.Factura;
import com.beltsoft.jim.fer.service.FacturaService;
import com.beltsoft.jim.fer.vo.FacturaFiles;

@ManagedBean(name = "facturaMB")
@RequestScoped
public class FacturaMB extends BaseMB implements Serializable{

	private static final Logger logger = Logger.getLogger(FacturaMB.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1354377295342547038L;
	
	private UploadedFile xml;
	private UploadedFile pdf;
	private UploadedFile zip;

	private Date fechaInicio;
	private Date fechaFin;
	private List<Factura> facturas = new ArrayList<Factura>();
	
	@ManagedProperty(value = "#{facturaService}")
	private FacturaService service;
	
	
	
	public String inicio() {	        
        return "uploadCfdi";
    }
	
	public String lista() throws ServiceException{
		return "listaFacturaCfdi";
	}
	/**
	 * todo requeire incorporar validacion de datos
	 * @throws ServerException
	 * @throws ServiceException
	 */
	public void upload() throws ServerException, ServiceException {
		FacturaFiles files = new FacturaFiles();
		
		String mensaje="";
		try {
			if(validate(xml,mensaje)){
				files.setXmlContent(xml.getContents());
				files.setXmlFileName(xml.getFileName());
				files.setXmlIS(xml.getInputstream());
			}
			if(validate(pdf,mensaje)){
				files.setPdfContent(pdf.getContents());
				files.setPdfFileName(pdf.getFileName());
			}
			if(validate(zip,mensaje)){
				logger.info("the side of zip is "+zip.getSize());
				files.setZipContent(zip.getContents());
				files.setZipFileName(zip.getFileName());
				
				files.setZipIS(zip.getInputstream());
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("no se pudo asignar el is del zip");
		}
		String exito="";
		if(files.isWithFiles()){
			service.procesaFilesWeb(files);
			mensaje += " archivos procesados";
			exito="Succesful";
		}else{
			exito ="fail";
			mensaje = " Archivos no cargados o no se envio";
		}
		
		
		FacesMessage message = new FacesMessage(exito,
				mensaje);
		FacesContext.getCurrentInstance().addMessage(null, message);
		clean();
	}

	public void buscar() throws ServerException, ServiceException{
//		clean();
		logger.info("fechas "+fechaInicio + " , "+ fechaFin);
		setFacturas(service.consultar(null));
		
		for(Factura f:facturas){
			logger.info(f);
		}
		logger.info("facturas size "+facturas.size());
		
	}
	
	private void clean() {
		// TODO Auto-generated method stub
		xml = null;
		pdf = null;
		zip = null;
		facturas=null;
	}

	private boolean validate(UploadedFile file, String mensaje) {
		// TODO Auto-generated method stub
		if (file == null || file.getContentType()==null || file.getFileName()==null ){
			return false;
		}else{
			
			mensaje +=file.getFileName() + " is uploaded.\n";
			return true;
		}
	}

	public UploadedFile getXml() {
		return xml;
	}

	public void setXml(UploadedFile xml) {
		this.xml = xml;
	}

	
	public UploadedFile getPdf() {
		return pdf;
	}

	public void setPdf(UploadedFile pdf) {
		this.pdf = pdf;
	}

	public UploadedFile getZip() {
		return zip;
	}

	public void setZip(UploadedFile zip) {
		this.zip = zip;
	}

	public FacturaService getService() {
		return service;
	}

	public void setService(FacturaService service) {
		this.service = service;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}

	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	 
}
