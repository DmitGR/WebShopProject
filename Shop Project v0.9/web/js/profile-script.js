var name = "";
var surename = "";
var mobilenumber = "";
var login= "";


$(document).ready(function () {
    //забать id пользователя когда он зашёл, вернуть его данные из бд
        $.ajax({
            url: "Authorisation",
            type: "POST",
            datatype: 'json',
            data: {"initial_load": "none"},
            success: function (data) {
             //  alert(data);
                if(data != null && data != "")
                {
                      loadProfile(data);
                      login = data.login;
                }
            }
        });

    function loadProfile(data) {
        name = data.name;
        surename = data.name;
        mobilenumber = data.phone;
        document.getElementById('profile-content').innerHTML = "<li class='block-profile'>" +
            "<div id='block-user-grid'><img id = 'profile-img' src='/img/userBIG.jpg'/>" +
            "<p class='profile-info'>" + name + " " + surename + "</p>" +
            "<p class='profile-phone'>" + mobilenumber + "</p>" +
            "<input type='button' class='PassButtons' id = 'changePassButton' value='Сменить пароль'/></div></li>"

        $("#changePassButton").click(function () {
            newPassword();
        });

    }


    function newPassword() {
        document.getElementById('profile-content').innerHTML = "<li class='block-profile'>" +
            "<div id='block-user-grid'><img id = 'profile-img' src='/img/userBIG.jpg'/>" +
            "<p class='profile-info'>" + name + " " + surename + "</p>" +
            "<p class='profile-phone'>Телефон: " + mobilenumber + "</p>" +
            "<p class='profile-input'><input type='password' class='newPass' id='newPass1' placeholder=' Новый пароль'/></p>" +
            "<p class='profile-input'><input type='password' class='newPass' id='newPass2' placeholder=' Повторите пароль'/></p>" +
            "<input type='button' class='PassButtons' id = 'changePassBack' value='Назад'/>" +
            "<input type='button' class='PassButtons' id = 'changePassApply' value='Применить'/></div></li>";

        $("#newPass1").on('keyup keypress', function (e) {

            var unavailableSymbols = '!@<>""/*-+%()$&#,.;:?=|_^~`';
            return (unavailableSymbols.indexOf(String.fromCharCode(e.which)) == -1);
        });

        $("#newPass2").on('keyup keypress', function (e) {

            var unavailableSymbols = '!@<>""/*-+%()$&#,.;:?=|_^~`';
            return (unavailableSymbols.indexOf(String.fromCharCode(e.which)) == -1);
        });


        $("#changePassBack").click(function () {
            loadProfile();
        });

        $("#changePassApply").click(function () {
            var pass1 = $("#newPass1").val();
            var pass2 = $("#newPass2").val();
            if(pass1 != pass2 || pass1=="" || pass2==""){
                alert("Ввдены неверные данные");
            }
            else if(pass1.length > 20 || pass1.length < 6)
            {
                alert("Пароль от 6 до 20 символов");
            }
            else{  
                var str = {login:login,
                pass:pass1};
                $.ajax({
                    url: "ChangePass",
                    type: "POST",
                    datatype: 'json',
                    data: {"json" : JSON.stringify(str)},
                    success: function (data) {
                        alert(data);
                        alert("Пароль успешно изменён!");
                        loadProfile();
                    }
                });
            

            }
        });
    }
});