/**
 * Created by SmiLeX on 07.06.2018.
 */ $('#reg_pass').on('keyup keypress', function (e) {
    if (e.keyCode == 8 || e.keyCode == 46) {
    }
    else {
        var availableSymbols = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_';
        return (availableSymbols.indexOf(String.fromCharCode(e.which)) != -1);  
    }
});
$('#reg_login').on('keyup keypress', function (e) {
    if (e.keyCode == 8 || e.keyCode == 46) {
    }
    else {
        var availableSymbols = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_';
        return (availableSymbols.indexOf(String.fromCharCode(e.which)) != -1);
    }
});
 
$('#form_reg').validate({
    // Проверка
    rules: {
        reg_login: {
            required: true,
            minlength: 5,
            maxlength: 15,
            remote: {
                type: "POST",
                dataType   : "json",
                url: "checklogin",
                data: {"json": function(){var str = {login:$("#reg_login").val()}; return JSON.stringify(str)}},
                dataFilter : function (data) {
                   // alert(data);
                    return data == 0;
                }
            }
        },
        reg_pass: {
            required: true,
            minlength: 6,
            maxlength: 20
        },
        reg_surname: {
            required: true,
            minlength: 3,
            maxlength: 15
        },
        reg_name: {
            required: true,
            minlength: 3,
            maxlength: 15
        },
        reg_email: {
            required: true,
            email: true,
            remote: {
                type: "POST",
                dataType   : "json",
                url: "checklogin",
                data: {"json": function(){var str = {login:$("#reg_email").val()}; return JSON.stringify(str)}},
                dataFilter : function (data) {
                 //   alert(data);
                    return data == 0;
                }
            }
        },
        reg_phone: {
            required: true,
            maxlength: 12,
            digits: true
        }
    },
    messages: {
        reg_login: {
            required: "Укажите логин",
            minlength: "от 5 до 15 символов",
            maxlength: "от 5 до 15 символов",
            remote: "Логин занят"
        },
        reg_pass: {
            required: "Укажите Пароль",
            minlength: "от 6 до 20 символов",
            maxlength: "от 6 до 20 символов"
        },
        reg_surname: {
            required: "Укажите Фамилию",
            minlength: "от 3 до 15 символов",
            maxlength: "от 3 до 15 символов",
        },
        reg_name: {
            required: "Укажите Имя",
            minlength: "от 3 до 15 символов",
            maxlength: "от 3 до 15 символов"
        },
        reg_email: {
            required: "Укажите e-mail",
            email: "Некорректный e-mail",
            remote: "Пользователь с таки e-mail уже существует!"
        },
        reg_phone: {
            required: "Укажите телефон",
            digits: "Некорректыйн ввод"
        }
    },
    submitHandler: function (form) {
        registration();
//alert("na")
       // form.submit();
    }
});


function registration() {
    var str = {
        login: $("#reg_login").val(),
        password: $("#reg_pass").val(),
        surname: $("#reg_surname").val(),
        name: $("#reg_name").val(),
        phone: $("#reg_phone").val(),
        email: $("#reg_email").val() 
    };

    $.ajax({
        url: "Registration",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
           // alert(data);

            if (data != 0) {
                $("#block-form-registration").fadeOut(300, function () {

                    $("#reg_message").addClass("reg_message_good").fadeIn(400).html("Вы успешно зарегистрированы!");
                    $("#form_submit").hide();

                });
            }
            else {
                $("#reg_message").addClass("reg_message_error").fadeIn(400).html(data);
            }
            
        }
    });
}