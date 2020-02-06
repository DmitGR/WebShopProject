/**
 * Created by mp3dr on 28.06.2018.
 */
$(document).ready(function () {
    loadContent();


    function loadContent() {
        document.getElementById('passRecovery-content').innerHTML = "<li class='block-passRec'>" +
            "<div id='block-passRec-grid'><img id = 'recPass-img' src='/img/recPass.jpg'/>" +
            "<p class='recPass-head-info'>Восстановление пароля</p>" +
            "<p class='recPass-info'>*Введите Логин или E-mail от своей учетной записи,<br> и мы вышлем вам на почту новый пароль.<br> " +
            "Вы сможете сменить его в любой момент в личном кабинете.</p>" +
            "<p class='profile-input'><input type='text' class='newPass' id='recPass-login' placeholder=' Введите Логин или E-mail'/></p>" +
            "<input type='button' class='PassButtons' id = 'recPass-button' value='Получить пароль'/></div></li>";
    
        $("#recPass-button").click(function () {
            enterNewPass();
        });

    }

    function enterNewPass() {
        var login = $("#recPass-login").val();
        //тут наверное отправить логин, если такой есть вернуть тру
        //дата пришла там булевая
        var check = false;
        $.ajax({
            type: "POST",
            dataType: "json",
            url: "checklogin",
            data: {
                "json": function () {
                    var str = {login: $("#recPass-login").val()};
                    return JSON.stringify(str)
                }
            },
            success: function (data) {
                check = data != 0;
                alert(check);
                if (check) {
                    sendPass($("#recPass-login").val());
                    alert("Новый пароль отправлен на вашу почту!");
                    window.location.href = "index.jsp";
                }
                else {
                    alert("Такого логина нет в нашей базе, проверьте данные.");
                }
            }
        });
    }
    
    function sendPass(login) {
        $.ajax({
            type:"POST",
            dataType:"json",
            url:"ForgotPass",
            data: {"json": function(){var str = {login:$("#recPass-login").val()}; return JSON.stringify(str)}},
            success: function (data) {
                alert(data);
            }
        })
    }
});