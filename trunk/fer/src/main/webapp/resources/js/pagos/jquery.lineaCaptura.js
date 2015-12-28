$('#formLineaCaptura input[type="text"]').tooltipster({
	trigger : 'hover', 
	onlyOne : true, 
	position : 'top'
});

$(document).ready(function() {
	$("#formLineaCaptura").validate({
		errorPlacement : function(error, element) {
			$(element).tooltipster('update', $(error).text());
			$(element).tooltipster('show');
		},
		rules : {
			'formLineaCaptura:prueba' : {
				required : true
			},
			'formLineaCaptura:lineaCapturaTabla:linCap' : {
				required : true
			},
			'formLineaCaptura:prueba2' : {
				required : true
			}
		},
		messages : {
			'formLineaCaptura:prueba' : {
				required : "Proporcionar fecha de pago"
			},
			'formLineaCaptura:lineaCapturaTabla:linCap' : {
				required : "Proporcionar línea de captura"
			},
			'formLineaCaptura:prueba2' : {
				required : "Proporcionar prueba!"
			}
		}
	});
});

function validarCampos(){
	//alert ("Validar campos");
	$("#formLineaCaptura").valid();
	return false;
}