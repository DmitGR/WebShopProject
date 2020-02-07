/**
 * @return {string}
 */
function GetType (type) {

    switch (type)
    {
        case 0: return "Покупатель";
        break;
        case 1: return "Продавец";
            break;
        case 2: return "Администратор";
        break;
    }

}

var body;
var SearchAll;

$(document).ready(function () {
body =  document.getElementById("block-body").innerHTML;

// Получение сессии
    var loaded = GetSession();
    while(!loaded) {
        loaded = GetSession();}
    if(loaded ){
        document.getElementById("block-body").innerHTML = body;
        //События для поиска
        $("#search-btn").click(function () {
            SearchAll = false;
            var search = $("#admin-search").val();
            //  alert(search);
            if (search.length < 1) {
                SearchAll = true;
            }
            Search(search);
        });
        $("#all-users").click(function () {
            SearchAll = true;
            Search("");
        });

        }
        else {
        document.getElementById("block-body").innerHTML = "<p class ='h2-title'>У вас нет прав</p>";

    }
    // Проверка ввода
    $('#admin-search').on('keyup keypress', function (e) {

        var unavailableSymbols = '!@<>""/*-+%()$&#,.;:?=|_^~`';
        return (unavailableSymbols.indexOf(String.fromCharCode(e.which)) == -1);
    });
});

/**
 Функция для отправки поискового запроса на сервер
 */
function Search(search) {
    if (type == types.admin) {
        document.title = "Поиск - " + search;
        var str = {
            search: search,
            All: SearchAll
        };
        $.ajax({
            url: "UsersSearch",
            type: "POST",
            datatype: 'json',
            data: {"json": JSON.stringify(str)},
            success: function (data) {
                if (data == "") {
                    document.getElementById('users-list').innerHTML = "<p class ='h2-title2'>Ничего не найдено!</p><h1 class=search-error><br><img src=/img/search-error.jpg></h1>";
                }
                addToPage(data);
            }
        });
    }
    else {
        document.getElementById('users-list').innerHTML = "<p class ='h2-title2'>У вас нет прав</p><h1 class=search-error><br> <img src=/img/search-error.jpg></h1>";

    }
}
/**
 Формирование ссылки к выбранному пользователю
 */
function choose(id) {

    window.location.href = "/admin-panel/user-profile.jsp?id=" + String(id);
}
/**
 Функция для вывода данных на страницу
 */
function addToPage(data) {

    var appendDeviceStr = "";
    for (var i = 0; i < data.length; i++) {
        var userid = data[i].id;
        var surname = data[i].surname;
        var type = GetType(data[i].type);
        var name = data[i].name;
        var balance = data[i].balance;

        appendDeviceStr += "<li>"+
            " <button onclick='javascript:choose("+userid+");'>Просмотр</button>"+
            "<label>Имя:<label class='user-info' >"+surname+" "+name+"</label></label>"+
            "<label>Тип: <label class='user-info' >"+type+"</label></label>"+
            "<label>Баланс: <label class='user-info' >"+balance+"</label></label>";

        document.getElementById('users-list').innerHTML = appendDeviceStr;
    }
}









