var id;
var type;
var maxConnection = 3;
var TimeOut = 720;
var balance;
var types={
    admin:2,
    buyer:0,
    seller:0

};
counterOfconnection = 0;


$(document).ready(function () {

    $(".top-auth").click(function () {
        $("#block-top-auth").slideToggle(200);
    });

    $('#button-pass-show-hide').click(function () {
        var statuspass = $('#button-pass-show-hide').attr("class");
        if (statuspass == "pass-show") {
            $('#button-pass-show-hide').attr("class", "pass-hide");
            var $input = $("#auth_pass");
            var change = "text";
            var rep = $("<input placeholder='Пароль' type='" + change + "' />")
                .attr("id", $input.attr("id"))
                .attr("name", $input.attr("name"))
                .attr('class', $input.attr('class'))
                .val($input.val())
                .insertBefore($input);
            $input.remove();
            $input = rep;
        } else {
            $('#button-pass-show-hide').attr("class", "pass-show");
            var $input = $("#auth_pass");
            var change = "password";
            var rep = $("<input placeholder='Пароль' type='" + change + "' />")
                .attr("id", $input.attr("id"))
                .attr("name", $input.attr("name"))
                .attr('class', $input.attr('class'))
                .val($input.val())
                .insertBefore($input);
            $input.remove();
            $input = rep;
        }
    });

// Auth
    $("#button-auth").click(function () {
        var auth_login = $("#auth_login").val();
        var auth_pass = $("#auth_pass").val();


        if(auth_login == "" || auth_login.length > 16 || auth_login.length < 5)
        {
            $("#auth_login").css("background-color","#ff9191");
            canAuth = false;
        }
        else
        {
            $("#auth_login").css("background-color","#DBDBDB");
            canAuth = true;
        }

        if(auth_pass == "" || auth_pass.length > 15 || auth_pass.length < 5)
        {
            $("#auth_pass").css("background-color","#ff9191");
            canAuth = false;
        }
        else
        {
            $("#auth_pass").css("background-color","#DBDBDB");
            canAuth = true;
        }

        if(canAuth)
        {
            Authorization(auth_login,auth_pass);
        }
    });

});

/**
 * @return {boolean}
 */
var GetSession = function() {
    AuthSession();
    if (id > 0 && type >= 0) {
        return true;
    }
    if (counterOfconnection < maxConnection) {
        setTimeout(GetSession, TimeOut); // GetSession again in a second
        counterOfconnection++;
        return false;
    }
    else{
        return true;
    }
};


function Authorization(login, pass) {
    var str = {
        login:login,
        pass:pass
    };
    $.ajax({
        url: "Authorisation",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
            var message =   $("#message-auth");
            if(data.verified == 0)
            {
                message.text("Ваша почта не подтверждена");
                message.slideDown(420);

            }
            else if(data != 0)
            {
                alert("Добро Пожаловать!");
                window.location.href="/index.jsp";
            }
            else
            {
                message.slideDown(420);
            }
        }
    });
}
/**
 Функция создания сессии
 */
function AuthSession() {
    $.ajax({
        url: "Authorisation",
        type: "POST",
        datatype: 'json',
        data: {"initial_load": "none"},
        success: function (data) {
            if (data != null && data != "") {
                id = data.id;
                type = data.type;
                balance = data.balance;
                if ( type < 2) {
                    prepareWishList();
                }
                if(type == 2)
                {
                    $("#profile-link").attr("href", "/admin-panel/users-list.jsp");
                }
                    //    alert('From AuthSession id = '+data.id);
                    //  $("#reg-auth-title").css("display","none");
                    document.getElementById('reg-auth-title').innerHTML = "<p id =user-info align=right><img src=/img/user.png> Здравствуйте," + data.name + "</p>";
                    $("#user-info").hover(function () {
                            $(this).css("cursor", "pointer");
                        },
                        function () {
                            $(this).css("cursor", "pointer");
                        });
                    $("#user-info").click(
                        function () {
                            if ($("#block-user").css("display") == "none") {
                                $("#block-user").css("display", 'block');
                            }
                            else $("#block-user").css("display", "none");
                        }
                    );
                    $("#profile-exit").click(function () {

                        $.ajax({
                            url: "Authorisation",
                            type: "POST",
                            data: {logout: 1},
                            success: function (data) {
                                window.location.href = "/index.jsp";
                            }
                        });
                    });
                }
            }
    });
}