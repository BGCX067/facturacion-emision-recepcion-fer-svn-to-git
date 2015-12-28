$(document).ready(function(){
	 var estadoAdsCombo = $("#formModificacionFedatario\\:estadoAdsNombre_input").val();
	 if (estadoAdsCombo == '-1'){
	     $("#formModificacionFedatario\\:estadoAdsId").val("");
	     $("#formModificacionFedatario\\:municipioAdsNombre_input").val("-1");
	     $("#formModificacionFedatario\\:municipioAdsId").val("");
	 } else {
	     $("#formModificacionFedatario\\:estadoAdsId").val(estadoAdsCombo);
	 }
	 
	 var municipioAdsCombo = $("#formModificacionFedatario\\:municipioAdsNombre_input").val();
	 if (municipioAdsCombo == '-1'){
	     $("#formModificacionFedatario\\:municipioAdsId").val("");
	 } else {
	     $("#formModificacionFedatario\\:municipioAdsAdsId").val(municipioAdsCombo);
	 }
	 
	 var estadoCombo = $("#formModificacionFedatario\\:estadoNombre_input").val();
	 if (estadoCombo == '-1'){
	     $("#formModificacionFedatario\\:estadoId").val("");
	     $("#formModificacionFedatario\\:municipioNombre_input").val("-1");
	     $("#formModificacionFedatario\\:municipioId").val("");
	 } else {
	     $("#formModificacionFedatario\\:estadoId").val(estadoCombo);
	 }
	 
	 var municipioCombo = $("#formModificacionFedatario\\:municipioNombre_input").val();
	 if (municipioCombo == '-1'){
	     $("#formModificacionFedatario\\:municipioId").val("");
	 } else {
	     $("#formModificacionFedatario\\:municipioId").val(municipioCombo);
	 }
});
 
function actualizarEstadoAdsId(){
	var valor = $("#formModificacionFedatario\\:estadoAdsNombre_input").val();
	if (valor == '-1'){
	     $("#formModificacionFedatario\\:estadoAdsId").val("");
	     $("#formModificacionFedatario\\:municipioAdsNombre_input").val("-1");
	     $("#formModificacionFedatario\\:municipioAdsId").val("");
	     
	 } else {
	     $("#formModificacionFedatario\\:estadoAdsId").val(valor);
	     $("#formModificacionFedatario\\:municipioAdsNombre_input").val("-1");
	     $("#formModificacionFedatario\\:municipioAdsId").val("");
	 }
}

function actualizarMunicipioAdsId(){
	var valor = $("#formModificacionFedatario\\:municipioAdsNombre_input").val();
	if (valor == '-1'){
	     $("#formModificacionFedatario\\:municipioAdsId").val("");
	     
	 } else {
	     $("#formModificacionFedatario\\:municipioAdsId").val(valor);
	 }
}

function actualizarEstadoId(){
	var valor = $("#formModificacionFedatario\\:estadoNombre_input").val();
if (valor == '-1'){
     $("#formModificacionFedatario\\:estadoId").val("");
     $("#formModificacionFedatario\\:municipioNombre_input").val("-1");
     $("#formModificacionFedatario\\:municipioId").val("");
     
 } else {
     $("#formModificacionFedatario\\:estadoId").val(valor);
     $("#formModificacionFedatario\\:municipioNombre_input").val("-1");
     $("#formModificacionFedatario\\:municipioId").val("");
     }
}

function actualizarMunicipioId(){
	var valor = $("#formModificacionFedatario\\:municipioNombre_input").val();
	if (valor == '-1'){
	     $("#formModificacionFedatario\\:municipioId").val("");
	     
	 } else {
	     $("#formModificacionFedatario\\:municipioId").val(valor);
	 }
}

function actualizarValoresEstadoMunicipio(){
	var valorNacionalidad = $("#formModificacionFedatario\\:nacionalidad_input").val();
	var valorEstadoAdsNombre = $("#formModificacionFedatario\\:estadoAdsNombre_input").val();
	var valorEstadoNombre = $("#formModificacionFedatario\\:estadoNombre_input").val();
	var valorMunicipioAdsNombre = $("#formModificacionFedatario\\:municipioAdsNombre_input").val();
	var valorMunicipioNombre = $("#formModificacionFedatario\\:municipioNombre_input").val();
	if (valorNacionalidad == '2'){
		$("#formModificacionFedatario\\:estadoAdsId").val("");
	    $("#formModificacionFedatario\\:municipioAdsId").val("");
	    $("#formModificacionFedatario\\:estadoId").val("");
	    $("#formModificacionFedatario\\:municipioId").val("");
	 }else{
		 if(valorEstadoAdsNombre == '-1'){
			 $("#formModificacionFedatario\\:estadoAdsId").val("");
		 }
		 if(valorEstadoNombre == '-1'){
			 $("#formModificacionFedatario\\:estadoId").val("");
		 }
		 if(valorMunicipioAdsNombre == '-1'){
			 $("#formModificacionFedatario\\:municipioAdsId").val("");
		 }
		 if(valorMunicipioNombre == '-1'){
			 $("#formModificacionFedatario\\:municipioId").val("");
		 }
	 }
}