package com.beltsoft.jim.fer.util;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.ServletContextResourceLoader;

public class CustomReloadableResourceBundleMessageSource extends
	ReloadableResourceBundleMessageSource implements ServletContextAware {

	private static final Logger LOG = Logger.getLogger(CustomReloadableResourceBundleMessageSource.class);
	private int cacheSeconds;
	private String customMessageSources;
	
	private ResourceLoader cargadorRecursos=null;

	
	public void setCustomMessageSources(String customMessageSources) {
		this.customMessageSources = customMessageSources;
		if(cargadorRecursos!=null){
			cargarMensajes();
		}
	}
	public String getCustomMessageSources() {		
		return customMessageSources;
	}


	private void cargarMensajes() {
		Resource resource = this.cargadorRecursos.getResource(customMessageSources.substring(0,customMessageSources.lastIndexOf("/")));
		
		File recurso=null;
		try {
			recurso = resource.getFile();
		} catch (IOException e) {
			LOG.error(e.getMessage(),e);
		}
		
		
		
		Set<String> listaFicheros=new HashSet<String>();
		if(customMessageSources.lastIndexOf("/")==customMessageSources.length()-1){
			customMessageSources=customMessageSources.substring(0, customMessageSources.length()-1);
		}			
		String[] cadenaProperty = customMessageSources.split("/");
		String raiz=customMessageSources.substring(0, customMessageSources.lastIndexOf("/")+1);
		if("*".equals(cadenaProperty[cadenaProperty.length-1])){						
			recuperarFicherosProperties(raiz,recurso.listFiles(),listaFicheros);
			
		}else{
			if(("**").equals(cadenaProperty[cadenaProperty.length-1])){	
				recuperarFicherosProperties(raiz,recurso.listFiles(),listaFicheros);
				recuperarFicherosPropertiesPorRecursividad(raiz,recurso.listFiles(),listaFicheros);
			}else{
				super.setBasename(customMessageSources);
			}
		}		
		
		if(listaFicheros.size()==1){	
			super.setBasename(listaFicheros.toArray()[0].toString());
		}
		if(listaFicheros.size()>1){		
			super.setBasenames(listaFicheros.toArray(new String[listaFicheros.size()]));   
		}		
		
	}
	public void setFichero(String raiz,File fichero,Set<String> listaFicheros){
		int posComienzoProperties=fichero.getName().indexOf("_");

		if(posComienzoProperties!=-1){
			listaFicheros.add(raiz+fichero.getName().substring(0, posComienzoProperties));			
		}
	}
	
	public void recuperarFicherosProperties(String raiz,File ficheros[],Set<String> listaFicheros){
		for (File fichero: ficheros){
			if(fichero.isFile()){			
				setFichero(raiz,fichero,listaFicheros);
			}
		}
	}
	
	public  void recuperarFicherosPropertiesPorRecursividad(String raiz,File ficheros[],Set<String> listaFicheros){         	                         
         for (File fichero: ficheros){                  
        	 if (fichero.isDirectory()){        		 
        		 recuperarFicherosPropertiesPorRecursividad(raiz+fichero.getName()+"/",fichero.listFiles(),listaFicheros);        		 
        	 }else{
        		 setFichero(raiz,fichero, listaFicheros);
        	 }
         }       
	 }

	public int getCacheSeconds() {
		return cacheSeconds;
	}

	public void setCacheSeconds(int cacheSeconds) {
		super.setCacheSeconds(cacheSeconds);
		this.cacheSeconds = cacheSeconds;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.setResourceLoader(new ServletContextResourceLoader(servletContext));
		this.cargadorRecursos=new ServletContextResourceLoader(servletContext);
		if(customMessageSources!=null){
			cargarMensajes();
		}
		
	}
	
	/**
	 * Método para obtener mensaje de archivos properties 
	 * 
	 * @param keyMessage llave del mensaje que se desea obtener
	 * @return retorna el valor que está asociado a la llave del mensaje proporcionado
	 */
	public String getMessage(String keyMessage){
			return this.getMessage(keyMessage, null, new Locale("es", "MX"));
	}

}
