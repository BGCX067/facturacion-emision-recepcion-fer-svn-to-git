package com.beltsoft.jim.fer.negocio.util;

import java.io.File;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class FactoryUtil {

//	estas clases mandan  un erro al procesar el xml
//	http://www.coderexception.com/CbHN6zmHPWxWXXWQ/unexpected-element-unmarshalexception
//	public static Object getClass(InputStream is,Class<?> clazz) throws JAXBException{
//		JAXBContext jaxbContext;
//		jaxbContext = JAXBContext.newInstance(Class.class);
//		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//		return jaxbUnmarshaller.unmarshal(is);		
//	}
//	
//	public static Object getClassJXB(File file,Class<?> clazz) throws JAXBException{
//		JAXBContext jaxbContext;
//		jaxbContext = JAXBContext.newInstance(Class.class);
//		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//		return jaxbUnmarshaller.unmarshal(file);		
//	}
}
