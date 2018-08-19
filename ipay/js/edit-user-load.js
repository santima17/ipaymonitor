$(document).ready(function(){
	
	var token = localStorage.getItem("token").substring(6);
	var userName = localStorage.getItem("username");
    getUserByUserName(userName, token);

	var urlParams = new URLSearchParams(window.location.search);
	var userID = urlParams.get('userID');
    getUserByUserID(userID, token);


    getAllCountries(token, 'user');
    getAllCountriesByUser(token, userID, 'user');
	getAllChannels(token, 'user');
	getAllChannelsByUser(token, userID, 'user');
	getAllCards(token, 'user');
	getAllCardsByUser(token, userID, 'user');
    
});

$(document).ready(function(){
  $('body').on('click', '.removeCard', function(){
    var token = localStorage.getItem("token").substring(6);
    var cardName = $(this).attr("id");
    var cardsSelectedMap = new Map(JSON.parse(localStorage.getItem("cardsSelectedMap")));
    var cardObject = cardsSelectedMap.get(cardName);
    var urlParams = new URLSearchParams(window.location.search);
    var userID = urlParams.get('userID');
    removeCardByUser(token, userID, cardObject.id);
    cardsSelectedMap.delete(cardName);
    localStorage.setItem("cardsSelectedMap", JSON.stringify([...cardsSelectedMap]));
    getAllCardsByUser(token, userID, 'user');

  }),

  $('body').on('click', '.removeChannel', function(){
    var token = localStorage.getItem("token").substring(6);
    var channelName = $(this).attr("id");
    var channelsSelectedMap = new Map(JSON.parse(localStorage.getItem("channelsSelectedMap")));
    var channelObject = channelsSelectedMap.get(channelName);
    var urlParams = new URLSearchParams(window.location.search);
    var userID = urlParams.get('userID');
    removeChannelByUser(token, userID, channelObject.id);
    channelsSelectedMap.delete(channelName);
    localStorage.setItem("channelsSelectedMap", JSON.stringify([...channelsSelectedMap]));
    getAllChannelsByUser(token, userID, 'user');

  }),

  $('body').on('click', '.removeCountry', function(){
    var token = localStorage.getItem("token").substring(6);
    var countryName = $(this).attr("id");
    var countriesSelectedMap = new Map(JSON.parse(localStorage.getItem("countriesSelectedMap")));
    var countryObject = countriesSelectedMap.get(countryName);
    var urlParams = new URLSearchParams(window.location.search);
    var userID = urlParams.get('userID');
    removeCountryByUser(token, userID, countryObject.id);
    countriesSelectedMap.delete(countryName);
    localStorage.setItem("countriesSelectedMap", JSON.stringify([...countriesSelectedMap]));
    getAllCountriesByUser(token, userID, 'user');

  }),

  $('body').on('change', '#user-countries', function(){

  	var itemVal = $(this).val();
  	var itemName = serchName(itemVal, 'country');
  	var exist = isSelected(itemName, 'country');
  	var page = $('#edit-user-page').val();
	if(page != null && page == 'ok'){
	   	completeCountriesSelected();
	}
	deleteItem(itemName, 'country');

  }),

  $('body').on('change', '#user-payments', function(){
  	var itemVal = $(this).val();
  	var itemName = serchName(itemVal, 'card');
  	var exist = isSelected(itemName, 'card');
  	var page = $('#edit-user-page').val();
    if(page != null && page == 'ok'){
    	completeCardsSelected();
    }
	deleteItem(itemName, 'card');
  }),

  $('body').on('change', '#user-channels', function(){
  	var itemVal = $(this).val();
  	var itemName = serchName(itemVal, 'channel');
  	var exist = isSelected(itemName, 'channel');
  	var page = $('#edit-user-page').val();
    if(page != null && page == 'ok'){
    	completeChannelsSelected();
    }
	deleteItem(itemName, 'channel');
  })
})


    