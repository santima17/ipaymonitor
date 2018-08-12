$(document).ready(function(){
	var token = localStorage.getItem("token").substring(6);
	var userName = localStorage.getItem("username");
    getUserByUserName(userName, token);
});
