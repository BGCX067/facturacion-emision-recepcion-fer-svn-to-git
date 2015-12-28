$(document).ready(function(){
	alert ("estoy aqui??");
	
     $("#formLineaCaptura").validate({
         rules: {
      'formLineaCaptura:lineaCapturaTabla:linea': {
    	  required: true
          }
      }
     ,messages: {
    	 "linea": {
    	 required: true,
		 maxlength: 20
    	 }
     }
  });
     });