
function getAllCountries(token, page){
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/countries",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        async: false,
        success: function (data, textStatus, response) {
            if(page == 'user'){
                fillCountriesSelect(data);
            }else if(page == 'monitor'){
				fillCountriesMonitor(data);
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function getAllCountriesByUser(token, userID, page){
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/countries/" + userID,
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        async: false,
        success: function (data, textStatus, response) {
            if(page == 'user'){
                fillCountriesSelected(data);
            }else if(page == 'monitor'){
                fillCountriesMonitor(data);
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
            var userUl = $("#user-countries-li").find("ul")[0];
            $(userUl).empty();
            $(userUl).append('<li class="select2-search select2-search--inline"><input class="select2-search__field" tabindex="0" autocomplete="off" autocorrect="off" autocapitalize="none" spellcheck="false" role="textbox" aria-autocomplete="list" placeholder="Seleccionar países" style="width: 378px;" type="search"></li>');  
        }
    });
}

function fillCountriesSelect(countries){

    var countriesMap = new Map();

    for(var i = 0; i < countries.length; i++){
         $("#user-countries").append(
            '<option value="'+countries[i].id+'">'+countries[i].name+'</option>'
         );

         countriesMap.set(countries[i].name, countries[i]);
      
    }
    localStorage.setItem("countriesMap", JSON.stringify([...countriesMap]));
}

function fillCountriesMonitor(countries){
    var countrySelectedMapCurrentUser = new Map();
    for(var i = 0; i < countries.length; i++){
         $("#country").append(
            '<option value="'+countries[i].id+'">'+countries[i].name+'</option>'
         );
         countrySelectedMapCurrentUser.set(countries[i].name, countries[i]);
    }
    localStorage.setItem("countrySelectedMapCurrentUser", JSON.stringify([...countrySelectedMapCurrentUser]));
}


function fillCountriesSelected(countries){

    var countriesSelectedMap = new Map();

    var userLiSpan = $("#user-countries-li").find("span")[0];
    $(userLiSpan).removeClass("select2 select2-container select2-container--default select2-container--below select2-container--focus");
	$(userLiSpan).addClass("select2 select2-container select2-container--default select2-container--focus select2-container--above");
    
    var userUl = $("#user-countries-li").find("ul")[0];
    $(userUl).empty();


    for(var i = 0; i < countries.length; i++){

        $(userUl).append(
			'<li class="select2-selection__choice" title="'+countries[i].name+'" data-select2-id="'+countries[i].name+countries[i].id+'">'+
            	'<span id="'+countries[i].name+'" class="select2-selection__choice__remove removeCountry" role="presentation">×</span>'+ countries[i].name+
            '</li>'
        );

        countriesSelectedMap.set(countries[i].name, countries[i]);

    }

    $(userUl).append(
    		'<li class="select2-search select2-search--inline">'+
            	'<input class="select2-search__field" tabindex="0" autocomplete="off" autocorrect="off" autocapitalize="none" spellcheck="false" role="textbox" aria-autocomplete="list" placeholder="" style="width: 0.75em;" type="search">'+
            '</li>'
        );
    
    localStorage.setItem("countriesSelectedMap", JSON.stringify([...countriesSelectedMap]));
}

function completeCountriesSelected(){
    var countriesSelectedMap = new Map(JSON.parse(localStorage.getItem("countriesSelectedMap")));
    var userUl = $("#user-countries-li").find("ul")[0];
    for(var [name, item] of countriesSelectedMap){
        $(userUl).append(
            '<li class="select2-selection__choice" title="'+name+'" data-select2-id="'+name+item.id+'">'+
                '<span id="'+name+'" class="select2-selection__choice__remove removeCountry" role="presentation">×</span>'+name+
            '</li>'
        );
    }
    var firstLi = $(userUl).find(".select2-search.select2-search--inline")[0];
    $(firstLi).remove();
    $(userUl).append(
            '<li class="select2-search select2-search--inline">'+
                '<input class="select2-search__field" tabindex="0" autocomplete="off" autocorrect="off" autocapitalize="none" spellcheck="false" role="textbox" aria-autocomplete="list" placeholder="" style="width: 0.75em;" type="search">'+
            '</li>'
        );
}

function getAllCards(token, page){
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/cards",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {
            if(page == 'user'){
                fillCardsSelect(data);
            }else if(page == 'monitor'){
				fillCardsMonitor(data);
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function getAllCardsByUser(token, userID, page){
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/cards/" + userID,
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {
             
             if(page == 'user'){
                fillCardsSelected(data);
            }else if(page == 'monitor'){
                fillCardsMonitor(data);
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
            var userUl = $("#user-payments-li").find("ul")[0];
            $(userUl).empty();
            $(userUl).append('<li class="select2-search select2-search--inline"><input class="select2-search__field" tabindex="0" autocomplete="off" autocorrect="off" autocapitalize="none" spellcheck="false" role="textbox" aria-autocomplete="list" placeholder="Seleccionar medios" style="width: 378px;" type="search"></li>');
        }
    });
}

function fillCardsSelect(cards){

	var cardsMap = new Map();

    for(var i = 0; i < cards.length; i++){
         $("#user-payments").append(
            '<option value="'+cards[i].id+'">'+cards[i].name+'</option>'
         );

         cardsMap.set(cards[i].name, cards[i]);
    }

    localStorage.setItem("cardsMap", JSON.stringify([...cardsMap]));
}

function fillCardsMonitor(cards){

    var cardsSelectedMapCurrentUser = new Map();
    for(var i = 0; i < cards.length; i++){

        $("#medioPago").append(
            '<option value="'+cards[i].id+'">'+cards[i].name+'</option>'
        );
        cardsSelectedMapCurrentUser.set(cards[i].name, cards[i]);
    }
    localStorage.setItem("cardsSelectedMapCurrentUser", JSON.stringify([...cardsSelectedMapCurrentUser]));
}


function fillCardsSelected(cards){

	var cardsSelectedMap = new Map();

    var userLiSpan = $("#user-payments-li").find("span")[0];
    $(userLiSpan).removeClass("select2 select2-container select2-container--default select2-container--below select2-container--focus");
	$(userLiSpan).addClass("select2 select2-container select2-container--default select2-container--focus select2-container--above");
    
    var userUl = $("#user-payments-li").find("ul")[0];
    $(userUl).empty();


    for(var i = 0; i < cards.length; i++){

        $(userUl).append(
			'<li class="select2-selection__choice" title="'+cards[i].name+'" data-select2-id="'+cards[i].name+cards[i].id+'">'+
            	'<span id="'+cards[i].name+'" class="select2-selection__choice__remove removeCard" role="presentation">×</span>'+ cards[i].name+
            '</li>'
        );

        cardsSelectedMap.set(cards[i].name, cards[i]);

    }

    $(userUl).append(
    		'<li class="select2-search select2-search--inline">'+
            	'<input class="select2-search__field" tabindex="0" autocomplete="off" autocorrect="off" autocapitalize="none" spellcheck="false" role="textbox" aria-autocomplete="list" placeholder="" style="width: 0.75em;" type="search">'+
            '</li>'
        );
    
    localStorage.setItem("cardsSelectedMap", JSON.stringify([...cardsSelectedMap]));

}

function completeCardsSelected(cards){

    var cardsSelectedMap = new Map(JSON.parse(localStorage.getItem("cardsSelectedMap")));
    var userUl = $("#user-payments-li").find("ul")[0];
    for(var [name, item] of cardsSelectedMap){

        $(userUl).append(
            '<li class="select2-selection__choice" title="'+name+'" data-select2-id="'+name+item.id+'">'+
                '<span id="'+name+'" class="select2-selection__choice__remove removeCard" role="presentation">×</span>'+ name+
            '</li>'
        );

    }

    var firstLi = $(userUl).find(".select2-search.select2-search--inline")[0];
    $(firstLi).remove();
    $(userUl).append(
            '<li class="select2-search select2-search--inline">'+
                '<input class="select2-search__field" tabindex="0" autocomplete="off" autocorrect="off" autocapitalize="none" spellcheck="false" role="textbox" aria-autocomplete="list" placeholder="" style="width: 0.75em;" type="search">'+
            '</li>'
        );

}

function getAllChannels(token, page){
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/channels",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {
            if(page == 'user'){
                fillChannelsSelect(data);
            }else if(page == 'monitor'){
            	fillChannelsMonitor(data);
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function getAllChannelsByUser(token, userID, page){
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/channels/" + userID,
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {
        	
            if(page == 'user'){
                fillChannelsSelected(data);
            }else if(page == 'monitor'){
                fillChannelsMonitor(data);
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
            var userUl = $("#user-channels-li").find("ul")[0];
            $(userUl).empty();
            $(userUl).append('<li class="select2-search select2-search--inline"><input class="select2-search__field" tabindex="0" autocomplete="off" autocorrect="off" autocapitalize="none" spellcheck="false" role="textbox" aria-autocomplete="list" placeholder="Seleccionar canales" style="width: 378px;" type="search"></li>');
        }
    });
}

function fillChannelsSelect(channels){

	var channelsMap = new Map();

    for(var i = 0; i < channels.length; i++){
         $("#user-channels").append(
            '<option value="'+channels[i].id+'">'+channels[i].name+'</option>'
         );
         channelsMap.set(channels[i].name, channels[i]);
    }

    localStorage.setItem("channelsMap", JSON.stringify([...channelsMap]));
}

function fillChannelsMonitor(channels){

    var channelsSelectedMapCurrentUser = new Map();
    for(var i = 0; i < channels.length; i++){
        $("#channel").append(
            '<option value="'+channels[i].id+'">'+channels[i].name+'</option>'
        );
        channelsSelectedMapCurrentUser.set(channels[i].name, channels[i]);
    }
    localStorage.setItem("channelsSelectedMapCurrentUser", JSON.stringify([...channelsSelectedMapCurrentUser]));

}

function fillChannelsSelected(channels){

	var channelsSelectedMap = new Map();

    var userLiSpan = $("#user-channels-li").find("span")[0];
    $(userLiSpan).removeClass("select2 select2-container select2-container--default select2-container--below select2-container--focus");
	$(userLiSpan).addClass("select2 select2-container select2-container--default select2-container--focus select2-container--above");
    
    var userUl = $("#user-channels-li").find("ul")[0];
    $(userUl).empty();


    for(var i = 0; i < channels.length; i++){

        $(userUl).append(
			'<li class="select2-selection__choice" title="'+channels[i].name+'" data-select2-id="'+channels[i].name+channels[i].id+'">'+
            	'<span id="'+channels[i].name+'" class="select2-selection__choice__remove removeChannel" role="presentation">×</span>'+ channels[i].name+
            '</li>'
        );

        channelsSelectedMap.set(channels[i].name, channels[i]);

    }

    $(userUl).append(
    		'<li class="select2-search select2-search--inline">'+
            	'<input class="select2-search__field" tabindex="0" autocomplete="off" autocorrect="off" autocapitalize="none" spellcheck="false" role="textbox" aria-autocomplete="list" placeholder="" style="width: 0.75em;" type="search">'+
            '</li>'
        );
    

    localStorage.setItem("channelsSelectedMap", JSON.stringify([...channelsSelectedMap]));
    
}

function completeChannelsSelected(){

    var channelsSelectedMap = new Map(JSON.parse(localStorage.getItem("channelsSelectedMap")));
    
    var userUl = $("#user-channels-li").find("ul")[0];
    for(var [name, item] of channelsSelectedMap){

        $(userUl).append(
            '<li class="select2-selection__choice" title="'+name+'" data-select2-id="'+name+item.id+'">'+
                '<span id="'+name+'" class="select2-selection__choice__remove removeChannel" role="presentation">×</span>'+ name+
            '</li>'
        );

    
    }

    var firstLi = $(userUl).find(".select2-search.select2-search--inline")[0];
    $(firstLi).remove();
    $(userUl).append(
            '<li class="select2-search select2-search--inline">'+
                '<input class="select2-search__field" tabindex="0" autocomplete="off" autocorrect="off" autocapitalize="none" spellcheck="false" role="textbox" aria-autocomplete="list" placeholder="" style="width: 0.75em;" type="search">'+
            '</li>'
        );
    
}


function isSelected(itemName, itemType){

    var userUl = $("#user-channels-li").find("ul")[0];
    if(itemType == 'card'){
        userUl = $("#user-payments-li").find("ul")[0];
    }
    if(itemType == 'country'){
        userUl = $("#user-countries-li").find("ul")[0];
    }
    var list = $(userUl).find(".select2-selection__choice");
    for(var i = 0; i < list.length; i++){
        if($(list[i]).attr('title') == itemName){
            return 'yes';
        }
    }
    return 'no';
}

function serchName(itemID, itemType){
    var map = new Map(JSON.parse(localStorage.getItem("channelsMap")));
    if(itemType == 'card'){
        map = new Map(JSON.parse(localStorage.getItem("cardsMap")));
    }
    if(itemType == 'country'){
        map = new Map(JSON.parse(localStorage.getItem("countriesMap")));
    }
    for(var [name, item] of map){
        if(item.id == itemID){
            return name;
        }
    }
}

function deleteItem(itemName, itemType){
    var userUl = $("#user-channels-li").find("ul")[0];
    if(itemType == 'card'){
        userUl = $("#user-payments-li").find("ul")[0];
    }
    if(itemType == 'country'){
        userUl = $("#user-countries-li").find("ul")[0];
    }
    var list = $(userUl).find('[title='+itemName+']');
    if(list.length > 1){
        var dataID = $(list[0]).attr('data-select2-id');
        $(list[1]).attr('data-select2-id',dataID);
        $(list[0]).remove();
    }

}

function addCountryByUser(token, userID, countryID){
	var data = createUpdateInformation(userID, countryID);
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/addCountry",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "POST",
        data: data,
        dataType: 'html',
        processData: true,
        async: false,
        contentType: 'application/json',
        success: function (data, textStatus, response) {
        	
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function addCardByUser(token, userID, cardID){
	var data = createUpdateInformation(userID, cardID);
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/addCard",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "POST",
        data: data,
        dataType: 'html',
        processData: true,
        async: false,
        contentType: 'application/json',
        success: function (data, textStatus, response) {
        	
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function addChannelByUser(token, userID, channelID){
	var data = createUpdateInformation(userID, channelID);
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/addChannel",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "POST",
        data: data,
        dataType: 'html',
        processData: true,
        async: false,
        contentType: 'application/json',
        success: function (data, textStatus, response) {
        	
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function removeCountryByUser(token, userID, countryID){
	var data = createUpdateInformation(userID, countryID);
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/removeCountry",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "DELETE",
        data: data,
        dataType: 'html',
        processData: true,
        contentType: 'application/json',
        success: function (data, textStatus, response) {
        	
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function removeCardByUser(token, userID, cardID){
	var data = createUpdateInformation(userID, cardID);
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/removeCard",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "DELETE",
        data: data,
        dataType: 'html',
        processData: true,
        contentType: 'application/json',
        success: function (data, textStatus, response) {
        	
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function removeChannelByUser(token, userID, channelID){
	var data = createUpdateInformation(userID, channelID);
    $.ajax({
        url: "http://138.197.150.98:8080/ipaymonitor/system/removeChannel",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "DELETE",
        data: data,
        dataType: 'html',
        processData: true,
        contentType: 'application/json',
        success: function (data, textStatus, response) {
        	
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function createUpdateInformation(userID, itemID){
	return '{"userID":"' + userID + '", "itemID":"' + itemID + '"}';
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