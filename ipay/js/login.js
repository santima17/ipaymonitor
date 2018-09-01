$(document).ready(
	function(){
		$("#login").click(function(){
			var user = $("#login-user").val();
			var password = $("#login-password").val();
			var login = true;
			// Checking for blank fields.
			if( user == ''){
				$('input[type="text"]').css("border","2px solid red");
				$('input[type="text"]').css("box-shadow","0 0 3px red");
				$('input[type="text"]').notify("Ingrese usuario", "error");
				login = false;
			}
			if( password == ''){
				$('input[type="password"]').css("border","2px solid red");
				$('input[type="password"]').css("box-shadow","0 0 3px red");
				$('input[type="password"]').notify("Ingrese contraseña", "error");
				login = false;
			}
			if (login){
		        $.ajax({
		            url: "http://138.197.150.98:8080/ipaymonitor/user/login",
		            type: "POST",
		            data:  {username:user,password:password},
		            success: function (data, textStatus, response) {
		                if(response.status == '200') {
							var token = response.getResponseHeader('Authorization');
							localStorage.setItem("token",token);
							localStorage.setItem("username",user);
							$("#tokenForm").submit();    					
						}
		            },
		            error: function(response, textStatus, errorThrown) {
		            	if(response.status == '401'){
							$('input[type="text"],input[type="password"]').css("border","2px solid red");
							$('input[type="text"],input[type="password"]').css("box-shadow","0 0 3px red");
							$.notify("Usuario o contraseña inválida", "error");
						}else{
							$.notify("Error en el sistema, contacte a soporte", "error");
						}
		            }
		        });
			}
		});
	}
);