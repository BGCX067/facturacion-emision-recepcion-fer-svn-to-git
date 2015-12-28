package com.beltsoft.jim.fer.primefaces;

import java.util.Arrays;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.primefaces.model.DefaultUploadedFile;
@FacesValidator("uploadValidator")
public class UploadValidator implements Validator {
	/**
	 * Metodo que se utiliza para la validacion del tipo de archivo 
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, Object value)
			throws ValidatorException {
		 DefaultUploadedFile file = (DefaultUploadedFile)value;
		String tipo = (String)component.getAttributes().get("tipo");
	      if (file == null) {
	         return;
	      }
//	      else if ( "rptdesign".equals(tipo) ){
//	    	  if (!"application/octet-stream".equals(file.getContentType()) || !"text/plain".equals(file.getContentType() ) ) {
//		    	  if(!(file.getFileName().toLowerCase().endsWith(".rptdesign"))){
//				         FacesMessage msg = new FacesMessage(file.getFileName(), "Tipo de archivo no permitido");
//				         msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//				         
//				         throw new ValidatorException(msg);	    		  
//		    	  		}
//		          }
//	      }else if ( "image".equals(tipo)){
//	    	  if (!("image/png".equals(file.getContentType()) || "image/jpeg".equals(file.getContentType()) || "image/gif".equals(file.getContentType())) ) {
//	    		  if(!(file.getFileName().toLowerCase().endsWith(".jpg") 
//	    				  || file.getFileName().toLowerCase().endsWith(".png") 
//	    				  || file.getFileName().toLowerCase().endsWith(".gif")
//	    			)){
//				         FacesMessage msg = new FacesMessage(file.getFileName(),"Tipo de archivo no permitido");
//				         msg.setSeverity(FacesMessage.SEVERITY_ERROR);
//			         
//			         throw new ValidatorException(msg);	    		  
//	    	  	}
//	    	  }		          
//	      }
	      else if( "pdf".equals(tipo) ){
	    	  if(validateFile(Arrays.asList("application/pdf"), Arrays.asList(".pdf"), file)){	    		  
	    	  }else{
	    		 throwMessage(file);	
	    	  }	    	  
	      }else if( "xml".equals(tipo) ){
	    	  if(validateFile(Arrays.asList("text/xml","application/xml"), Arrays.asList(".xml"), file)){	    		  
	    	  }else{
	    		 throwMessage(file);	
	    	  }
	      }else if( "zip".equals(tipo) ){
	    	  if(validateFile(Arrays.asList("application/zip"), Arrays.asList(".zip"), file)){	    		  
	    	  }else{
	    		 throwMessage(file);	
	    	  }
	      }	      
	}

	
	private void throwMessage( DefaultUploadedFile file) {
		// TODO Auto-generated method stub
		 FacesMessage msg = new FacesMessage(file.getFileName(),"Tipo de archivo no permitido");
         msg.setSeverity(FacesMessage.SEVERITY_ERROR);
     
     throw new ValidatorException(msg);
	}


	public static boolean validateFile( List<String> types,List<String> extensions, DefaultUploadedFile file){
		boolean fail=false;
		for(String t:types){			
			if(t.equals(file.getContentType())){
				fail = false;
				break;
			}else{
				fail = true;
			}
		}
		if(fail)return false;
		for(String t:extensions){
			if(file.getFileName().toLowerCase().endsWith(t.toLowerCase())){
				fail = false;
				break;
			}else{
				fail = true;
			}
		}
		if(fail)return false;
		return true;
	}


}
