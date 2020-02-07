var text;
var maxConnection = 2;
var TimeOut = 640;
counterOfconnection = 0;
$(document).ready(function () {
    text = "Загрузка...";

    var GetSession = function(){
        AuthSession();
        if(id > 0){
            switch (type) {
                case 0:
                    text = "Список Желаний";
                    break;
                case 1:
                    text = "Список Товаров на продаже";

                    break;
            }
            Load(id);
        }
        else if(counterOfconnection < maxConnection)
        {
            setTimeout(GetSession, TimeOut); // GetSession again in a second
            counterOfconnection++;
        }
        else
        {
            text = "Необходимо Авторизоваться";
        }
        document.getElementById('prod-list-title').innerHTML = text;
    };
    GetSession();

    $(".category-main").click(function () { document.location.href="index.jsp"; });
    $(".category-section").click(function () { document.location.href="index.jsp"; });


});

/**
 Функция для загрузки списка товаров
 */
function Load(id) {
    var request = {
        user_id: id
    };
    $.ajax({
        url: "GetProductList",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(request)},
        success: function (data) {
            addToPage(data);
        }
    });
}

function addToPage(data) {
    var appendDeviceStr = "";
    for (var i = 0; i < data.length; i++) {
        var prod_id = data[i].id;
        var src = data[i].image;
        var name = data[i].name;
        var mini_description = data[i].mini_description;
        var price = data[i].price;
        var date_time = String((data[i].DateTime));
        document.getElementById('devices-list').innerHTML = "<link href='css/grid-view.css' rel = 'stylesheet' type='text/css' />";


        appendDeviceStr += "<li class='block-device'>"+
            "<div class='block-img-grid'><img class='device-img' src='"+src+"'/></div>"+
            "<a class='btn-close-ico' onclick='remove("+prod_id+");'>"+"<img class='close-ico' src='"+"/img/close.png"+"'/></div>"+
            "<p class='style-title-grid'><a href= "+"javascript:choose("+prod_id+")>"+ name+"</a></p>"+
            "<ul class='reviews-grid-comment'>"+
            "<li class='date-li'><img class='date-img' src='/img/date.jpg'/><div class='date_time'>"+date_time+"</div></li></ul>"+
            "<ul class='reviews-grid-price'>"+
            "<li class='bag-li'><img class='bag-img' src='/img/cost.png'/><p class='price'>"+price+" руб.</p></li></ul>"+
        "<div class='mini-features'>"+mini_description+"</div>"+
        "</li>";

        document.getElementById('devices-list').innerHTML = appendDeviceStr;
    }}

function choose(id) {

    window.location.href = "fullPage.jsp?id=" + String(id);
}

/**
 Функция для удаления товара из списка
 */
function remove(prod_id) {
    var request = {
        user_id: id,
        product_id: prod_id
    };
    $.ajax({
        url: "RemoveProduct",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(request)},
        success: function (data) {
            if(data != 0) {
                alert("Успешно удалено");
                location.reload(true);
            }
            else
                alert("Error");
        }
    });
}
