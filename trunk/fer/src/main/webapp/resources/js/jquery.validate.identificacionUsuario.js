$('#identificacionUsuarioForm input').tooltipster({
	trigger : 'hover', 
	onlyOne : true, 
	position : 'top'
});


$(document).ready(function() {
	$("#identificacionUsuarioForm").validate({
		errorPlacement : function(error, element) {
			$(element).tooltipster('update', $(error).text());
			$(element).tooltipster('show');
		},
		rules : {
			'identificacionUsuarioForm:contraseniaAnt' : {
				required : function(){
					if ($("#identificacionUsuarioForm\\:repContrasenia").val() != ''
						|| $("#identificacionUsuarioForm\\:nuevaContrasenia").val() != ''){
						return true;
					}else {
						return false;
					}
				}
			},
			'identificacionUsuarioForm:nuevaContrasenia' : {
				required : function(){
					if ($("#identificacionUsuarioForm\\:contraseniaAnt").val() != ''
						|| $("#identificacionUsuarioForm\\:repContrasenia").val() != ''){
						return true;
					}else {
						return false;
					}
				}
			},
			'identificacionUsuarioForm:repContrasenia' : {
				required : function(){
					if ($("#identificacionUsuarioForm\\:contraseniaAnt").val() != ''
						|| $("#identificacionUsuarioForm\\:nuevaContrasenia").val() != ''){
						return true;
					}else {
						return false;
					}
				}
			}
		
		},
		messages : {
			'identificacionUsuarioForm:contraseniaAnt' : {
				required : "Proporcionar contraseña anterior"
			},
			'identificacionUsuarioForm:nuevaContrasenia' : {
				required : "Proporcionar nueva contraseña"
			},
			'identificacionUsuarioForm:repContrasenia' : {
				required : "Proporcionar confirmacion de contraseña"
			}
			
		}
	});
});