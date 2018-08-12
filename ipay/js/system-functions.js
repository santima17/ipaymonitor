function getAllCountries(token, page){
    $.ajax({
        url: "http://localhost:8080/ipaymonitor/system/countries",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {
            if(page == 'user'){
                fillCountriesSelect(data);
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function getAllCountriesByUser(token, userID, page){
    $.ajax({
        url: "http://localhost:8080/ipaymonitor/system/countries/" + userID,
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {

        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function fillCountriesSelect(countries){
    for(var i = 0; i < countries.length; i++){
         $("#user-countries").append(
            '<option value="'+countries[i].id+'">'+countries[i].name+'</option>'
         );
    }
}

function getAllCards(token, page){
    $.ajax({
        url: "http://localhost:8080/ipaymonitor/system/cards",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {
             if(page == 'user'){
                fillCardsSelect(data);
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function getAllCardsByUser(token, userID, page){
    $.ajax({
        url: "http://localhost:8080/ipaymonitor/system/cards/" + userID,
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {
             
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function fillCardsSelect(countries){
    for(var i = 0; i < countries.length; i++){
         $("#user-payments").append(
            '<option value="'+countries[i].id+'">'+countries[i].name+'</option>'
         );
    }
}

function getAllChannels(token, page){
    $.ajax({
        url: "http://localhost:8080/ipaymonitor/system/channels",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {
             if(page == 'user'){
                fillChannelsSelect(data);
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function getAllChannelsByUser(token, userID, page){
    $.ajax({
        url: "http://localhost:8080/ipaymonitor/system/channels/" + userID,
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function fillChannelsSelect(countries){
    for(var i = 0; i < countries.length; i++){
         $("#user-channels").append(
            '<option value="'+countries[i].id+'">'+countries[i].name+'</option>'
         );
    }
}

function handlerError(response){
    if(response.status == '403') {
        alert("Error de seguridad, debe ingresar nuevamente");
        window.location = "../index.html";
    }
    if(response.status == '500') {
        alert("La session ha caducado");
        window.location = "../index.html";
    }
}