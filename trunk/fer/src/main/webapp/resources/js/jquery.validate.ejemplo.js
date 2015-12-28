$('#ejemploFormulario input[type="text"]').tooltipster({
	trigger : 'hover', 
	onlyOne : true, 
	position : 'top'
});

$(document).ready(function() {
	$("#ejemploFormulario").validate({
		errorPlacement : function(error, element) {
			$(element).tooltipster('update', $(error).text());
			$(element).tooltipster('show');
		},
		rules : {
			'ejemploFormulario:nombreUsuario' : {
				required : true
			},
			'ejemploFormulario:apellidoPaterno' : {
				required : true
			}
		},
		messages : {
			'ejemploFormulario:nombreUsuario' : {
				required : "Proporcionar nombre"
			},
			'ejemploFormulario:apellidoPaterno' : {
				required : "Proporcionar apellido paterno"
			}
		}
	});
});