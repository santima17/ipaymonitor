$(document).ready(function(){
  $('body').on('click', '.btn-modal.audit', function(){
    var token = localStorage.getItem("token").substring(6);
	var transactionID = $(this).attr("id");
	audit(transactionID, token);
  })
})

$("#search").click(function(){

	var token = localStorage.getItem("token").substring(6);

	var code = "";
	var dateFrom = "";
	var dateTo = "";
	var country = "all";
	var state = "all";
	var channel = "all";
	var currency = "all";
	var documento = "";
	var medioPago = "all";
	
	if($("#code-op").val() != ''){
		code = $("#code-op").val();
	}

	if($("#date-from").datepicker().val() != ''){
		dateFrom = $("#date-from").datepicker().val();
	}

	if($("#date-to").datepicker().val() != ''){
		dateTo = $("#date-to").datepicker().val();
	}

	if($("#country").val() != ''){
		country = $("#country").val();
	}

	if($("#state").val() != ''){
		state = $("#state").val();
	}

	if($("#channel").val() != ''){
		channel = $("#channel").val();
	}    

	if($("#currency").val() != ''){
		currency = $("#currency").val();
	} 

	if($("#document").val() != ''){
		documento = $("#document").val();
	} 

	if($("#medioPago").val() != ''){
		medioPago = $("#medioPago").val();
	}    
	var arrayDateFrom = dateFrom.split("/");
	var arrayDateTo = dateTo.split("/");


	
	//var dateFromJSON = new Date(arrayDateFrom[2]+'-'+arrayDateFrom[1]+'-'+arrayDateFrom[0]+'T00:00:00').getTime();
	//var dateToJSON = new Date(arrayDateTo[2]+'-'+arrayDateTo[1]+'-'+arrayDateTo[0]+'T23:59:59').getTime();
 	var dateFromJSONPRE = new Date(Date.UTC(arrayDateFrom[2], arrayDateFrom[1]-1, arrayDateFrom[0], 00, 00, 00));
    dateFromJSONPRE.setDate(dateFromJSONPRE.getDate() + 1);
    var dateFromJSON = dateFromJSONPRE.getTime();
    var dateToJSON = new Date(Date.UTC(arrayDateTo[2], arrayDateTo[1]-1, arrayDateTo[0], 23, 59, 59)).getTime();

	

	var cardsSelectedMap = new Map(JSON.parse(localStorage.getItem("cardsSelectedMapCurrentUser")));
	if(medioPago == 'all' && cardsSelectedMap.size != 4){
		medioPago = '';
		for(var [name, item] of cardsSelectedMap){
			medioPago += item.id + "#";
		}
	}

	var channelsSelectedMapCurrentUser = new Map(JSON.parse(localStorage.getItem("channelsSelectedMapCurrentUser")));
	if(channel == 'all' && channelsSelectedMapCurrentUser.size != 3){
		channel = '';
		for(var [name, item] of channelsSelectedMapCurrentUser){
			channel += item.id + "#";
		}
	}

	var countrySelectedMapCurrentUser = new Map(JSON.parse(localStorage.getItem("countrySelectedMapCurrentUser")));
	if(country == 'all' && countrySelectedMapCurrentUser.size != 18){
		country = '';
		for(var [name, item] of countrySelectedMapCurrentUser){
			country += item.id + "#";
		}
	}

	

	var jsonSearchParameters = '{"transactionID":"'+code+'","document":"'+documento+'","channel":"'+channel+'","country":"'+country+'","cardBrand":"'+medioPago+'","bank":"","currency":"'+currency+'","status":"'+state+'","dateFrom":"'+dateFromJSON+'","dateTo":"'+dateToJSON+'"}';
   
    search(JSON.parse(jsonSearchParameters), token);

});




function search(searchParameters, token){
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/search",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "POST",
        data: JSON.stringify(searchParameters),
        processData: true,
        dataType: 'json',
        async: "false",
        contentType: 'application/json',
        success: function (data, textStatus, response) {
            if(response.status == '200') {
                fillMonitor(data);
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function audit(transactionID, token){
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/search/audit/"+transactionID,
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        processData: true,
        dataType: 'json',
        async: "false",
        contentType: 'application/json',
        success: function (data, textStatus, response) {
            if(response.status == '200') {
                fillAudit(data);
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}


function fillMonitor(transactions){

	var ul = $(".table.fs-s").find("ul")[0];
	$(ul).empty();
	$(ul).append(	'<li class="header fw-bold">'+
						'<h4>Operación</h4>'+
						'<h4>Fecha</h4>'+
						'<h4>Estado</h4>'+
						'<h4>Tarjeta</h4>'+
						'<h4>Número</h4>'+
						'<h4>Canal</h4>'+
						'<h4>País</h4>'+
						'<h4>Monto</h4>'+
						'<h4>Moneda</h4>'+
					'</li>');

	$(".fw-bold.trans").empty();
	$(".fw-bold.trans").append(transactions.length + ' transacciones');

	for(var i = 0; i < transactions.length; i++){
	    var date = new Date(transactions[i].fecha);
	    var dd = date.getDate(); 
	    var mm = date.getMonth()+1; 
	    var yyyy = date.getFullYear();
	    dateString = dd + '/' + mm + '/' + yyyy;

	 	var medioPago = 'AMEX';
	 	if(transactions[i].medioPago == '01'){
	 	 	medioPago = 'MasterCard';
	 	}else if(transactions[i].medioPago == '10'){
	 	 	medioPago = 'VISA';
	 	}else if(transactions[i].medioPago == '11'){
	 	 	medioPago = 'Discovery';
	 	}


	 	var channel = 'Web';
	 	if(transactions[i].canal == 'C'){
	 	 	channel = 'CallCenter';
	 	}else if(transactions[i].canal == 'S'){
	 	 	channel = 'Sucursal';
	 	}

	 	var currency = "USD";
	 	if(transactions[i].moneda == '840'){
	 		currency = "USD";
	 	}

	 	var amountWitouthComa = transactions[i].monto;
	 	var amountPrefix = amountWitouthComa.substring(0, amountWitouthComa.length - 2);
	 	var amountSufix = amountWitouthComa.substring(amountWitouthComa.length - 2, amountWitouthComa.length);


	 	var status = "Transacción Autorizada";
	 	var statusClass = "close";
	 	var isError = "";

	 	var estadoCentinela = transactions[i].estado.split("@")[0];


        if (estadoCentinela == "000") {
        	status = "Transacción autorizada";
        	statusClass = "success";
        } else if (estadoCentinela == "D") {
        	status = "Devuelto";
        } else if (estadoCentinela == "C") {
        	status = "Cancelado"
        } else if (estadoCentinela == "001") {
        	status = "Denegado: Limite de Credito";
        } else if (estadoCentinela == "PPP") {
        	status = "Chequear Manualmente";
        	statusClass = "error";
        	isError = 'class="error"';
        } else if (estadoCentinela == "114") {
            status = "Denegado: Operación no autorizada por medio de pago";
        } else if (estadoCentinela == "010") {
        	status = "Denegado: Tarjeta Extraviada";
        } else if (estadoCentinela == "011") {
        	status = "Denegado: Tarjeta Inválida o Info Incompleta";
        } else if (estadoCentinela == "100") {
        	status = "Denegado: Error de Seguridad";
        } else if (estadoCentinela == "110") {
        	status = "Denegado: Order Duplicada";
        } else if (estadoCentinela == "111") {
        	status = "Error de comunicacion";
        } else if (estadoCentinela == "112") {
        	status = "Cancelado por usuario";
        } else if (estadoCentinela == "CHK") {
        	status = "Chequear Manualmente";
        	statusClass = "error";
        	isError = 'class="error"';
        } else {
        	status = "Denegado: Por medio de pago";
        }

		var tarjetaStr = transactions[i].tarjeta;
      	var tar1 = "";
      	var tar2 = "";
      	if (tarjetaStr.length >= 16) {
        	if ( tarjetaStr!="null" && tarjetaStr.includes("******") == false) {
          		tar1 = tarjetaStr.substring(0, 6);
          		tar2 = tarjetaStr.substring(12, 16);
          		tarjetaStr = tar1 + "******" + tar2;
        	} 
		}	
        
        $(ul).append('<li id="'+transactions[i].codigoRes+'" class="btn-modal audit" data-modal="transfer-info"><div><div class="actions"><span class="left">'+transactions[i].codCard+'</span><span class="icon info left tooltip audit"><small>Ver detalle</small></span></div></div><div>'+dateString+'</div><div '+ isError +'><span class="icon '+statusClass+' small"></span>'+status+'</div><div>'+medioPago+'</div><div>'+tarjetaStr+'</div><div>'+channel+'</div><div>'+transactions[i].pais+'</div><div>'+amountPrefix+','+amountSufix+'</div><div>'+currency+'</div></li>');
      
    }

}


function fillAudit(registros){

	$(".fw-bold.codigo.transaction").empty();

	$(".fw-bold.codigo.transaction").append(registros[0].nroTransaccion);

	$(".audit.panel").empty();

	$(".audit.panel").append('<li class="header fw-bold">' +
						'<h4>Tramo</h4>' +
						'<h4>Vía comunicación</h4>' +
						'<h4>Fecha</h4>' +
						'<h4>Estado</h4>' +
					'</li>');

	for(var i = 0; i < registros.length; i++){

		var date = new Date(registros[i].fecha);
	    var dd = date.getDate(); 
	    var mm = date.getMonth()+1; 
	    var yyyy = date.getFullYear();
	    var hr = date.getHours();
	    var min = date.getMinutes();
	    var sec = date.getSeconds();

	    dateString = dd + '/' + mm + '/' + yyyy + ' ' + hr + ':' + min + ':' + sec;


		var estadoCentinela = registros[i].estado.split("@")[0];


		var status = "Transacción Autorizada";
	 	var statusClass = "close"

	    if (estadoCentinela == "000") {
	    	status = "Transacción autorizada";
	    	statusClass = "success";
	    } else if (estadoCentinela == "D") {
	    	status = "Devuelto";
	    } else if (estadoCentinela == "C") {
	    	status = "Cancelado"
	    } else if (estadoCentinela == "001") {
	    	status = "Denegado: Limite de Credito";
	    } else if (estadoCentinela == "PPP") {
	    	status = "Pendiente de Respuesta";
	    } else if (estadoCentinela == "114") {
	        status = "Denegado: Operación no autorizada por medio de pago";
	    } else if (estadoCentinela == "010") {
	    	status = "Denegado: Tarjeta Extraviada";
	    } else if (estadoCentinela == "011") {
	    	status = "Denegado: Tarjeta Inválida o Info Incompleta";
	    } else if (estadoCentinela == "100") {
	    	status = "Denegado: Error de Seguridad";
	    } else if (estadoCentinela == "110") {
	    	status = "Denegado: Order Duplicada";
	    } else if (estadoCentinela == "111") {
	    	status = "Error de comunicacion";
	    } else if (estadoCentinela == "112") {
	    	status = "Cancelado por usuario";
	    } else if (estadoCentinela == "CHK") {
	    	status = "Chequear Manualmente";
	    	statusClass = "error";
	    	isError = 'class="error"';
	    } else {
	    	status = "Denegado: Por medio de pago";
	    }

	    var accion = registros[i].accion.split(":");
	    if(accion.length == 1){
	    	accion[1] = '';
	    }
	    if(accion.length == 3){
	    	accion[1] = accion[1] + ":" + accion[2];
	    }

		$(".audit.panel").append(
					'<li>' +
						'<div>'+accion[0]+'</div>' +
						'<div>'+accion[1]+'</div>' +
						'<div>'+dateString+'</div>' +
						'<div class="icon-wrapper"><span class="icon '+statusClass+' small"></span>'+status+'</div>' +
					'</li>'
					);

	}

}



					

