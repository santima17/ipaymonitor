$("#change-password").click(function(){
    changePassword();
});

$("#delete_user").click(function(){
    alert($(this).attr('id'));
});

$( "#edit-user-form" ).click(function(){

    $("#user-name").removeAttr('style');
    $("#user-lastname").removeAttr('style');
    $("#user-username").removeAttr('style');
    $("#user-password-e").removeAttr('style');

    var infoOK = true;
    if($("#user-name").val() == ''){
        $("#user-name").css("border","2px solid red");
        $("#user-name").css("box-shadow","0 0 3px red");
        $("#user-name").notify("Ingrese nombre", "error");    
        infoOK = false;
    }

    if($("#user-lastname").val() == ''){
        $("#user-lastname").css("border","2px solid red");
        $("#user-lastname").css("box-shadow","0 0 3px red");
        $("#user-lastname").notify("Ingrese apellido", "error");    
        infoOK = false;
    }

    if($("#user-username").val() == ''){
        $("#user-username").css("border","2px solid red");
        $("#user-username").css("box-shadow","0 0 3px red");
        $("#user-username").notify("Ingrese usuario", "error");    
        infoOK = false;
    }


    if($("#user-password-e").val() == ''){
        $("#user-password-e").css("border","2px solid red");
        $("#user-password-e").css("box-shadow","0 0 3px red");
        $("#user-password-e").notify("ingrese contraseña", "error");    
        infoOK = false;
    }

    if(infoOK){
        var token = localStorage.getItem("token").substring(6);
        var user = JSON.parse(createUserInformation(false));
        sendUser(user, token);
    }
});

$( "#remove-user-form" ).click(function(){
    var token = localStorage.getItem("token").substring(6);
    var user = JSON.parse(createUserInformation(false));
    removeUser(user.id, token);
    window.location.replace("usuarios.html");
});



$( "#new-user-form" ).click(function(){

    $("#user-name").removeAttr('style');
    $("#user-lastname").removeAttr('style');
    $("#user-username").removeAttr('style');
    $("#user-password-e").removeAttr('style');

    var infoOK = true;
    if($("#user-name").val() == ''){
        $("#user-name").css("border","2px solid red");
        $("#user-name").css("box-shadow","0 0 3px red");
        $("#user-name").notify("Ingrese nombre", "error");    
        infoOK = false;
    }

    if($("#user-lastname").val() == ''){
        $("#user-lastname").css("border","2px solid red");
        $("#user-lastname").css("box-shadow","0 0 3px red");
        $("#user-lastname").notify("Ingrese apellido", "error");    
        infoOK = false;
    }

    if($("#user-username").val() == ''){
        $("#user-username").css("border","2px solid red");
        $("#user-username").css("box-shadow","0 0 3px red");
        $("#user-username").notify("Ingrese usuario", "error");    
        infoOK = false;
    }


    if($("#user-password-e").val() == ''){
        $("#user-password-e").css("border","2px solid red");
        $("#user-password-e").css("box-shadow","0 0 3px red");
        $("#user-password-e").notify("ingrese contraseña", "error");    
        infoOK = false;
    }
        
    if(infoOK){
        var token = localStorage.getItem("token").substring(6);
        var user = JSON.parse(createUserInformation(true));
        sendNewUser(user, token);
        getUserByUserNameForAddInfo(user.user, token);
        user = JSON.parse(localStorage.getItem("userForAddInfo"));  
        var countries = $("#user-countries-li").find("li.select2-selection__choice");
        for(var i = 0; i < countries.length; i++){
            var name = $(countries[i]).attr("title");
            var countriesMap = new Map(JSON.parse(localStorage.getItem("countriesMap")));
            var country = countriesMap.get(name);
            addCountryByUser(token, user.id, country.id);
        }
        $.notify("Países agregados con éxito", "success");

        var cards = $("#user-payments-li").find("li.select2-selection__choice");
        for(var i = 0; i < cards.length; i++){
            var name = $(cards[i]).attr("title");
            var cardsMap = new Map(JSON.parse(localStorage.getItem("cardsMap")));
            var card = cardsMap.get(name);
            addCardByUser(token, user.id, card.id);
        }
        $.notify("Medios de pago agregados con éxito", "success");

        var channels = $("#user-channels-li").find("li.select2-selection__choice");
        for(var i = 0; i < channels.length; i++){
            var name = $(channels[i]).attr("title");
            var channelsMap = new Map(JSON.parse(localStorage.getItem("channelsMap")));
            var channel = channelsMap.get(name);
            addChannelByUser(token, user.id, channel.id);
        }
        $.notify("Canales agregados con éxito", "success");
        window.location.replace("usuarios.html");
    }


});

function createUserInformation(sendNewUser){
    var id = $("#user-id").val();
    if(sendNewUser == true){
        id = "";
    }
    var name = $("#user-name").val();
    var lastName = $("#user-lastname").val();
    var username = $("#user-username").val();
    var password = $("#user-password-e").val();
    var isAdmin = $("#user-super:checked").length;
    var json = '{"id":"'+id+'","user":"'+username+'","password":"'+password+'","name":"'+name+'","lastName":"'+lastName+'","isAdmin":'+isAdmin+',"deleted":false,"deleteReason":""}';
    return json;
}

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
        dataType: 'html',
        contentType: 'application/json',
        success: function (data, textStatus, response) {
            if(response.status == '200') {
                $.notify("El usuario fue modificado con éxito", "success");
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
        dataType: 'html',
        processData: true,
        contentType: 'application/json',
        async: false,
        success: function (data, textStatus, response) {
            if(response.status == '200') {
                $.notify("El usuario fue creado con éxito", "success");
            }
        },
        error: function(response, textStatus, errorThrown) {
            alert(errorThrown);
            handlerError(response);
        }
    });
}

function removeUser(userID, token){
    $.ajax({
        url: "http://localhost:8080/ipaymonitor/user/" + userID,
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "DELETE",
        dataType: 'html',
        processData: true,
        async: false,
        success: function (data, textStatus, response) {
            if(response.status == '200') {
                $.notify("El usuario fue eliminado con éxito", "success");
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

function getUserByUserNameForAddInfo(userName, token){
    $.ajax({
        async: "false",
        url: "http://localhost:8080/ipaymonitor/user/username/" + userName,
        headers: {
            'Authorization': 'TOKEN:' + token,
        },
        type: "GET",
        async: false,
        success: function (data, textStatus, response) {
            if(response.status == '200') {
                localStorage.setItem("userForAddInfo", JSON.stringify(data));              
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
            $("#user-password-e").val(data.password);
            $("#user-form").append('<input type="hidden" id="user-id" value="'+userID+'">');
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

                    var linea = '<li>'+
                        '<div class="actions">';
                    if(data[i].deleted == 1){
                        linea += '<span class="left">'+data[i].user+' </span> <span style="color:red;">[Usuario Eliminado]</span>';
                    }else{
                        linea += '<span class="left">'+data[i].user+'</span>'+
                            '<a href="usuarios-editar.html?userID='+data[i].id+'" class="icon edit left tooltip">'+
                                '<small>Editar</small>'+
                            '</a>'+
                            '<a href="usuarios-eliminar.html?userID='+data[i].id+'" class="icon delete left tooltip">'+
                                '<small>Eliminar</small>'+
                            '</a>';
                    }                                                   
                    linea += '</div>'+
                        '<div>'+data[i].name+'</div>'+
                        '<div>'+data[i].lastName+'</div>'+
                        '<div>'+data[i].password+'</div>'+
                    '</li>';
                $("#users-list").append(linea);
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

