$(document).ready(function(){
	 var estadoAdsCombo = $("#formCreacionFedatario\\:estadoAdsNombre_input").val();
	 if (estadoAdsCombo == '-1'){
	     $("#formCreacionFedatario\\:estadoAdsId").val("");
	     $("#formCreacionFedatario\\:municipioAdsNombre_input").val("-1");
	     $("#formCreacionFedatario\\:municipioAdsId").val("");
	 } else {
	     $("#formCreacionFedatario\\:estadoAdsId").val(estadoAdsCombo);
	 }
	 
	 var municipioAdsCombo = $("#formCreacionFedatario\\:municipioAdsNombre_input").val();
	 if (municipioAdsCombo == '-1'){
	     $("#formCreacionFedatario\\:municipioAdsId").val("");
	 } else {
	     $("#formCreacionFedatario\\:municipioAdsAdsId").val(municipioAdsCombo);
	 }
	 
	 var estadoCombo = $("#formCreacionFedatario\\:estadoNombre_input").val();
	 if (estadoCombo == '-1'){
	     $("#formCreacionFedatario\\:estadoId").val("");
	     $("#formCreacionFedatario\\:municipioNombre_input").val("-1");
	     $("#formCreacionFedatario\\:municipioId").val("");
	 } else {
	     $("#formCreacionFedatario\\:estadoId").val(estadoCombo);
	 }
	 
	 var municipioCombo = $("#formCreacionFedatario\\:municipioNombre_input").val();
	 if (municipioCombo == '-1'){
	     $("#formCreacionFedatario\\:municipioId").val("");
	 } else {
	     $("#formCreacionFedatario\\:municipioId").val(municipioCombo);
	 }
});
 
function actualizarEstadoAdsId(){
	var valor = $("#formCreacionFedatario\\:estadoAdsNombre_input").val();
	if (valor == '-1'){
	     $("#formCreacionFedatario\\:estadoAdsId").val("");
	     $("#formCreacionFedatario\\:municipioAdsNombre_input").val("-1");
	     $("#formCreacionFedatario\\:municipioAdsId").val("");
	     
	 } else {
	     $("#formCreacionFedatario\\:estadoAdsId").val(valor);
	     $("#formCreacionFedatario\\:municipioAdsNombre_input").val("-1");
	     $("#formCreacionFedatario\\:municipioAdsId").val("");
	 }
}

function actualizarMunicipioAdsId(){
	var valor = $("#formCreacionFedatario\\:municipioAdsNombre_input").val();
	if (valor == '-1'){
	     $("#formCreacionFedatario\\:municipioAdsId").val("");
	     
	 } else {
	     $("#formCreacionFedatario\\:municipioAdsId").val(valor);
     }
}

function actualizarEstadoId(){
	var valor = $("#formCreacionFedatario\\:estadoNombre_input").val();
	if (valor == '-1'){
	     $("#formCreacionFedatario\\:estadoId").val("");
	     $("#formCreacionFedatario\\:municipioNombre_input").val("-1");
	     $("#formCreacionFedatario\\:municipioId").val("");
	     
	 } else {
	     $("#formCreacionFedatario\\:estadoId").val(valor);
	     $("#formCreacionFedatario\\:municipioNombre_input").val("-1");
	     $("#formCreacionFedatario\\:municipioId").val("");
     }
}

function actualizarMunicipioId(){
	var valor = $("#formCreacionFedatario\\:municipioNombre_input").val();
	if (valor == '-1'){
	     $("#formCreacionFedatario\\:municipioId").val("");
	     
	 } else {
	     $("#formCreacionFedatario\\:municipioId").val(valor);
     }
}

function actualizarValoresEstadoMunicipio(){
	var valorNacionalidad = $("#formCreacionFedatario\\:nacionalidad_input").val();
	var valorEstadoAdsNombre = $("#formCreacionFedatario\\:estadoAdsNombre_input").val();
	var valorEstadoNombre = $("#formCreacionFedatario\\:estadoNombre_input").val();
	var valorMunicipioAdsNombre = $("#formCreacionFedatario\\:municipioAdsNombre_input").val();
	var valorMunicipioNombre = $("#formCreacionFedatario\\:municipioNombre_input").val();
	if (valorNacionalidad == '2'){
		$("#formCreacionFedatario\\:estadoAdsId").val("");
	    $("#formCreacionFedatario\\:municipioAdsId").val("");
	    $("#formCreacionFedatario\\:estadoId").val("");
	    $("#formCreacionFedatario\\:municipioId").val("");
	 }else{
		 if(valorEstadoAdsNombre == '-1'){
			 $("#formCreacionFedatario\\:estadoAdsId").val("");
		 }
		 if(valorEstadoNombre == '-1'){
			 $("#formCreacionFedatario\\:estadoId").val("");
		 }
		 if(valorMunicipioAdsNombre == '-1'){
			 $("#formCreacionFedatario\\:municipioAdsId").val("");
		 }
		 if(valorMunicipioNombre == '-1'){
			 $("#formCreacionFedatario\\:municipioId").val("");
		 }
	 }
}