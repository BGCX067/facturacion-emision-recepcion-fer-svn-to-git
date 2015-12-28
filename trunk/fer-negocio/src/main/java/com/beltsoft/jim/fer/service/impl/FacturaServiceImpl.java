package com.beltsoft.jim.fer.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.beltsoft.jim.fer.dao.FacturaDAO;
import com.beltsoft.jim.fer.exception.ServiceException;
import com.beltsoft.jim.fer.map.Factura;
import com.beltsoft.jim.fer.service.FacturaService;
import com.beltsoft.jim.fer.util.FilesUtil;
import com.beltsoft.jim.fer.util.Unzip;
import com.beltsoft.jim.fer.validador.CFDV32Validator;
import com.beltsoft.jim.fer.vo.FacturaFiles;
import com.beltsoft.jim.fer.vo.FacturaVo;

@Service(value="facturaService")
public class FacturaServiceImpl implements FacturaService {

	private static final Logger logger = Logger.getLogger(FacturaServiceImpl.class);
	
	@Autowired
	@Qualifier("validatorCfdService")
	private CFDV32Validator validatorCfdService;
	
	@Autowired
	private FacturaDAO facturaDao;
	
	@Override
	public void procesaFilesWeb(FacturaFiles files) throws ServiceException {
		// TODO Auto-generated method stub
		logger.info("procesando archivos a validar");
//		logger.info(StringUtil.decodeUTF8( files.getXmlContent()));
		
		if(files.getXmlFileName()==null || files.getXmlIS()==null){
			logger.info("no hay xml a procesar");
			throw new ServiceException("No fue posible leer el archivo XMl y su contenido");
		}else{
			Factura factura = validatorCfdService.validarCfd( files);
			/**
			 * @todo guardar factura y pdf si hay
			 */
			factura.setFactura(files.getPdfContent());
			logger.info(" pdf " + factura.getFactura());
			factura.setXml(files.getXmlContent());
			logger.info("xml "+ factura.getXml());
			facturaDao.insertar(factura);
			
			logger.info("se inserto la factura de shit "+factura.getIdFactura());
			
			
		}
		
		
		logger.info("paso el validator");
		if(files.getZipContent()==null){
			logger.info("no hay archivo zip");
		}else{
			Unzip unZip = new Unzip();
			String filedir="";
	    	unZip.unZipIt(files.getZipIS(), filedir = files.getZipFileName());
	    	
		}
		
	}

	@Override
	public List<Factura> consultar(Factura factura) throws ServiceException {
		// TODO Auto-generated method stub
		return facturaDao.selectFilter(factura);
		
	}
	/**
	 * se encarga de ejecutarse cada 10 min y procesar todos los directorios que hay en el directorio zip
	 */
//	se jecuta cada cinco miunutos
//	@Scheduled(fixedDelay=300000)
//	 @Scheduled(cron="0 0 0 * * ?")
	public  void procesaZipScheduler(){
		long initTime = Calendar.getInstance().getTimeInMillis();
		logger.info("inicia ejecución del proceso de zip "+ new Date(initTime));
		final String rootDir = Unzip.OUTPUT_FOLDER;
		Collection<File> all = new ArrayList<File>();
	    FilesUtil.addTree(new File(rootDir), all);
	    TreeSet<FacturaVo> facturasList = new TreeSet<FacturaVo>();
	    for(File f : all){
	    	
	    	if(f.isFile() && f.getName().endsWith(".xml")){
	    		FacturaVo vo = new FacturaVo();
	    		vo.setXml(f);
	    		facturasList.add(vo);
//	    		System.out.println("xml "+ f.getName());
	    	}else if(f.isFile() && f.getName().endsWith(".pdf")){
	    		// se debe 
//	    		System.out.println("pdf "+ f.getName());
	    	}else{
//	    		logger.info("es directorio " + f.getName());
	    	}
	    }
	    System.out.println(facturasList.size());
	    for(FacturaVo vo : facturasList){
	    	try {
				validatorCfdService.validarCfd(new FileInputStream(vo.getXml()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("Error al procesar el archivo "+vo.getXml().getName(),e);
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("Error al procesar el archivo "+vo.getXml().getName(),e);
			}
	    }
	    long endTime = Calendar.getInstance().getTimeInMillis();
		logger.info("inicia ejecución del proceso de zip "+ new Date(endTime));
		double dif = (endTime -initTime)*1.0/(1000.0*60.0);
		logger.info("Tiempo transcurrido en minutos "+dif);
	 
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	public CFDV32Validator getValidatorCfdService() {
		return validatorCfdService;
	}

	public void setValidatorCfdService(CFDV32Validator validatorCfdService) {
		this.validatorCfdService = validatorCfdService;
	}

	public static void main(String args[]){
	}

	public FacturaDAO getFacturaDao() {
		return facturaDao;
	}

	public void setFacturaDao(FacturaDAO facturaDao) {
		this.facturaDao = facturaDao;
	}
}
