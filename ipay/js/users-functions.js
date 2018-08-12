$("#change-password").click(function(){
    changePassword();
});

$("#delete_user").click(function(){
    alert($(this).attr('id'));
});

function changePassword(){
    var changePassword = true;
    var passwordOld = '';
    var passwordNew = '';
    var rePasswordNew = '';
    $("#password-old").removeAttr('style');
    $("#password-new").removeAttr('style');
    $("#repassword-new").removeAttr('style');
    
    // Checking for blank fields.
    if( $("#password-old").val() == ''){
        $("#password-old").css("border","2px solid red");
        $("#password-old").css("box-shadow","0 0 3px red");
        $("#password-old").notify("Ingrese contraseña actual", "error");
        changePassword = false;
    }else{
        passwordOld = $("#password-old").val();
    }
    if( $("#password-new").val() == ''){
        $("#password-new").css("border","2px solid red");
        $("#password-new").css("box-shadow","0 0 3px red");
        $("#password-new").notify("Ingrese nueva contraseña", "error");
        changePassword = false;
    }else{
        passwordNew = $("#password-new").val();
    }
    if( $("#repassword-new").val() == ''){
        $("#repassword-new").css("border","2px solid red");
        $("#repassword-new").css("box-shadow","0 0 3px red");
        $("#repassword-new").notify("Repita nueva contraseña", "error");
        changePassword = false;
    }else{
        rePasswordNew = $("#repassword-new").val();
    }
    if(changePassword){
        var currentUser = JSON.parse(localStorage.getItem("currentUser"));
        if(currentUser.password != passwordOld){
            $("#password-old").notify("La contraseña actual no es correcta", "error");
            $("#password-old").css("border","2px solid red");
            $("#password-old").css("box-shadow","0 0 3px red");
        }else if(passwordNew != rePasswordNew){
            $("#password-new").notify("Las contraseñas no coinciden", "error");
            $("#password-new").css("border","2px solid red");
            $("#password-new").css("box-shadow","0 0 3px red");
            $("#repassword-new").css("border","2px solid red");
            $("#repassword-new").css("box-shadow","0 0 3px red");
        }else{
            currentUser.password = passwordNew;
            var token = localStorage.getItem("token").substring(6);
            sendUser(currentUser, token);
            modalClose();
            $.notify("Su contraseña ha sido cambiada con éxito", "success");     
        }
    }        
}

function sendUser(currentUser, token){
    $.ajax({
        url: "http://localhost:8080/ipaymonitor/user/",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "PUT",
        data: JSON.stringify(currentUser),
        processData: true,
        dataType: 'json',
        contentType: 'application/json',
        success: function (data, textStatus, response) {
            if(response.status == '200') {
            }
        },
        error: function(response, textStatus, errorThrown) {
            alert(errorThrown);
            handlerError(response);
        }
    });
}

function sendNewUser(currentUser, token){
    $.ajax({
        url: "http://localhost:8080/ipaymonitor/user/",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "POST",
        data: JSON.stringify(currentUser),
        processData: true,
        dataType: 'json',
        contentType: 'application/json',
        success: function (data, textStatus, response) {
            if(response.status == '200') {
            }
        },
        error: function(response, textStatus, errorThrown) {
            alert(errorThrown);
            handlerError(response);
        }
    });
}

function getUserByUserName(userName, token){
    $.ajax({
        async: "false",
        url: "http://localhost:8080/ipaymonitor/user/username/" + userName,
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {
            if(response.status == '200') {
                $("#user-info").html(data.name + ' ' + data.lastName);              
            }
            localStorage.setItem("currentUser", JSON.stringify(data));
            if(data.isAdmin == '1'){
                $("#main-menu").append('<li><a href="usuarios.html" class="icon-wrapper"><span class="icon user"></span>Gestión de usuarios</a></li>');
                $("#main-menu").append('<li><a href="estadisticas.html" class="icon-wrapper"><span class="icon graph"></span>Estadísticas</a></li>');
            }   
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function getUserByUserID(userID, token){
    $.ajax({
        async: "false",
        url: "http://localhost:8080/ipaymonitor/user/" + userID,
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {

            $("#user-name").val(data.name);
            $("#user-lastname").val(data.lastName);
            $("#user-username").val(data.user);
            $("#user-password").val(data.password);
            if(data.isAdmin == 1){
                $("#user-super").prop('checked', true);
            }
            
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function getAllUsers(token){
    $.ajax({
        url: "http://localhost:8080/ipaymonitor/user",
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        success: function (data, textStatus, response) {
            for(var i = 0; i < data.length; i++){
                $("#users-list").append(

                    '<li>'+
                        '<div class="actions">'+
                            '<span class="left">'+data[i].user+'</span>'+
                            '<a href="usuarios-editar.html?userID='+data[i].id+'" class="icon edit left tooltip">'+
                                '<small>Editar</small>'+
                            '</a>'+
                            '<a href="usuarios-editar.html?userID='+data[i].id+'" class="icon delete left tooltip">'+
                                '<small>Eliminar</small>'+
                            '</a>'+                                                     
                        '</div>'+
                        '<div>'+data[i].name+'</div>'+
                        '<div>'+data[i].lastName+'</div>'+
                        '<div>'+data[i].password+'</div>'+
                    '</li>'
                );
            }
        },
        error: function(response, textStatus, errorThrown) {
            handlerError(response);
        }
    });
}

function modalClose(){
    $("#password-old").val('');
    $("#password-new").val('');
    $("#repassword-new").val('');
    $(".overlay").fadeOut(200);
    $(".modal").fadeOut(50);
    setTimeout(function(){
        $(".modal").removeClass("on");
        $("body").removeClass("modalOpen");
        $(".overlay").removeClass("black");
    }, 300);            
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