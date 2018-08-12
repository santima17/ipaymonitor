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
    