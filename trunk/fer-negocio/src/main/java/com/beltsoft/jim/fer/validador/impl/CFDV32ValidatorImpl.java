package com.beltsoft.jim.fer.validador.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.apache.xerces.dom.ElementNSImpl;
import org.datacontract.schemas._2004._07.Sat_Cfdi_Negocio_ConsultaCfdi_Servicio.Acuse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.tempuri.ConsultaCFDIServiceLocator;

import com.beltsoft.jim.fer.exception.ServiceException;
import com.beltsoft.jim.fer.map.Factura;
import com.beltsoft.jim.fer.sat.cfdv32.Comprobante;
import com.beltsoft.jim.fer.sat.cfdv32.Comprobante.Complemento;
import com.beltsoft.jim.fer.sat.timbrefiscaldigital.TimbreFiscalDigital;
import com.beltsoft.jim.fer.validador.CFDV32Validator;
import com.beltsoft.jim.fer.vo.FacturaFiles;

@Component
@Service(value="validatorCfdService")
public class CFDV32ValidatorImpl implements CFDV32Validator{

	private static final Logger logger = Logger.getLogger(CFDV32ValidatorImpl.class);
	
	

	
	@Override
	public Factura validarCfd(FacturaFiles files) throws ServiceException {
		// TODO Auto-generated method stub
		Factura factura = new Factura();
		Acuse acuse = validarSat(files.getXmlIS(),factura);
		
		return factura;
		
	}
	
	@Override
	public Factura validarCfd(InputStream is) throws ServiceException {
		Factura factura = new Factura();
		Acuse acuse = validarSat(is,factura);
		
		return factura;
	}
	
	
	private Acuse validarSat(InputStream is,Factura factura) throws ServiceException{
		logger.info("se crea :::::: el comprbando cfd version 32 folio fiscal original ");
		Comprobante comprobante = null;
		try {
			JAXBContext jaxbContext;

			jaxbContext = JAXBContext.newInstance(Comprobante.class);
						Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			comprobante = (Comprobante)jaxbUnmarshaller.unmarshal(is);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServiceException("No se pudo generar el Comprobante desde el XML");
		}
		
		if(comprobante.getVersion().equals("3.2")){
			Complemento complemento = comprobante.getComplemento();
			TimbreFiscalDigital tfd =null;
			for(Object any : complemento.getAny()){
				ElementNSImpl element= (ElementNSImpl) any;				
				if(element.getLocalName().equals("TimbreFiscalDigital")	&&
					element.getNamespaceURI().equals("http://www.sat.gob.mx/TimbreFiscalDigital")){
					try {
						JAXBContext jaxbContext;

						jaxbContext = JAXBContext.newInstance(TimbreFiscalDigital.class);
									Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
						tfd = (TimbreFiscalDigital)jaxbUnmarshaller.unmarshal(element);
						break;
					} catch (JAXBException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						throw new ServiceException("No se pudo generar el TimbreFiscalDigital desde el Comprobante");
					}					
				}
			}
				
			ConsultaCFDIServiceLocator locator = new ConsultaCFDIServiceLocator();
			Acuse acuse = null;
			try {
				Comprobante.Emisor emisor = comprobante.getEmisor();
				Comprobante.Receptor receptor = comprobante.getReceptor();
				String re = emisor.getRfc();
				String rr = receptor.getRfc();
				
				
				
				String cadena = "?re=" +re+
						"&rr=" +rr+
						"&tt=" +comprobante.getTotal()+
						"&id="+tfd.getUUID();
				logger.info("cadena de validacion sat " + cadena);
				acuse = locator.getBasicHttpBinding_IConsultaCFDIService().
						consulta(
								cadena
								);
				logger.info("--- " +acuse.getCodigoEstatus() + " --- " +acuse.getEstado() + "--- " );
				
				//set de datos para dao
				factura.setRfcEmisor(re);
				factura.setRfcReceptor(rr);
				factura.setUuid(tfd.getUUID());
				factura.setFechaCreacion(new Date());
				factura.setFechaEmision(comprobante.getFecha().toGregorianCalendar().getTime());
				factura.setFechaTimbrado(tfd.getFechaTimbrado().toGregorianCalendar().getTime());			
				factura.setAcuseCodigo(acuse.getCodigoEstatus());
				factura.setAcuseEstado(acuse.getEstado());
				
				return acuse;
			} catch (RemoteException e) { 
				e.printStackTrace();
				throw new ServiceException("No se pudo conectar al Sat por RemoteException "+e.getMessage());
			} catch (javax.xml.rpc.ServiceException e) {
				e.printStackTrace();
				throw new ServiceException("No se pudo conectar al Sat por ServiceException "+e.getMessage());
			}
		}else{
			throw new ServiceException("La versión de la factura no corresponde con la 3.2");
		}
	}
	
	public static void main(String args[]){
		JAXBContext jaxbContext;
		try {
			File file = new File("C:\\CME9701212I4F0000004351.xml");
			jaxbContext = JAXBContext.newInstance(Comprobante.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			InputStream is = new FileInputStream(file);
			Comprobante comprobante = (Comprobante)jaxbUnmarshaller.unmarshal(is);
			Complemento complemento = comprobante.getComplemento();
			for(Object any : complemento.getAny()){
				ElementNSImpl element= (ElementNSImpl) any;
				
				if(element.getLocalName().equals("TimbreFiscalDigital")&&
						element.getNamespaceURI().equals("http://www.sat.gob.mx/TimbreFiscalDigital")){
					jaxbContext = JAXBContext.newInstance(TimbreFiscalDigital.class);
					jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					
					TimbreFiscalDigital tfd = (TimbreFiscalDigital) jaxbUnmarshaller.unmarshal(element);
					System.out.println(tfd.getUUID());
				}
				

			}
			
			System.out.println(comprobante.getCertificado());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}
	
}
