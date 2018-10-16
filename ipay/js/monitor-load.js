$(document).ready(function(){
	var token = localStorage.getItem("token").substring(6);
	var userName = localStorage.getItem("username");

    getUserByUserName(userName, token);

	var f = new Date();
	var valueDate = f.getDate() + "/" + (f.getMonth() +1) + "/" + f.getFullYear();
	$('#date-from').val(valueDate);
	$('#date-to').val(valueDate);
});
