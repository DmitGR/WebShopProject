var copy;
var maxBalance= 1000000000;

$(document).ready(function () {
    copy = document.getElementById('block-adminPanel').innerHTML;
    document.getElementById('block-adminPanel').innerHTML = "<p class=h2-title>Загрузка...</p>";
   var loaded = GetSession();
   while(!loaded) {
       loaded = GetSession();}
    if(loaded ){
        Load();}


});
function Load()
{
    var user_id = document.location.search.slice(4);
    var str ={user:user_id};
    $.ajax({
        url: "UserInfo",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
            var name = data.name;
            var surname = data.surname;
            var patronymic = data.patronymic;
            var balance = data.balance;
            var email = data.email;
            var phone = data.phone;
            if (type == 2) {
                document.getElementById('block-adminPanel').innerHTML = copy;
                document.getElementById('block-user-info').innerHTML =
                    "<li><input type='text' maxlength='45' id = 'user-surname' value=" + surname + " /></li>" +
                    "<li><input type='text' maxlength='45' id = 'user-name' value=" + name + " /></li>" +
                    "<li><input type='text' maxlength='45' id = 'user-patronymic' value=" + patronymic + " /></li>" +
                    "<li><input type='number' maxlength='20' id = 'user-balance' value=" + balance + " /> <label>руб </label></li>" +
                    "<li><input type='email' maxlength='45' id = 'user-mail' value=" + email + " /></li>" +
                    "<li><input type='tel' maxlength='45' id = 'user-phone' value=" + phone + " /></li>" +
                    "<li><input type='button' class='PassButtons' onclick='SaveChanges(" + user_id + ");' id = 'changeUserData' value='Сохранить изменения'/></li>";

                GetSales(user_id);

                $("#user-balance").bind('keyup mouseup', function () {
                    var checkCount = $(this).val();
                    if (checkCount < 0)
                        $(this).val(0);
                    else if(checkCount > maxBalance)
                        $(this).val(maxBalance);
                    else {$(this).val(parseInt(checkCount));
                    }

                });
            }
            else {
                document.getElementById('block-adminPanel').innerHTML = "<p class ='h2-title'>У вас нет прав</p>";
            }

        }
    });
}

function SaveChanges(user_id) {
    var str = {
        id:user_id,
        name: $("#user-name").val(),
        surname: $("#user-surname").val(),
        patronymic: $("#user-patronymic").val(),
        balance: $("#user-balance").val(),
        email: $("#user-mail").val() ,
        phone: $("#user-phone").val()
    };

    $.ajax({
        url: "ChangeUser",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
            alert("Status: "+data);
            location.reload();
        }
        });
}

function GetSales(user) {
        var str = {
            id:user
        };

        $.ajax({
            url: "GetSales",
            type: "POST",
            datatype: 'json',
            data: {"json": JSON.stringify(str)},
            success: function (data) {
           //     alert("Status: "+data);
                addToPage(data);

            }
        });
}

function addToPage(data) {

    var appendDeviceStr = "";
    for (var i = 0; i < data.length; i++) {
        var sale_id = data[i].id;
        var product_name = data[i].product.name;
        var product_price = data[i].product.price;
        var buyer = data[i].buyer.surname + " "+data[i].buyer.name;
        var seller = data[i].seller.surname + " "+data[i].seller.name;
        var address = data[i].address;
        var date = data[i].date;

        appendDeviceStr += "<li class='sale'>Заказ № <label id = 'sale-id'> "+sale_id+" </label>"+
            "Продавец: <label id = 'sale-seller'> "+seller+" </label>"+
            "Покупатель: <label id = 'sale-buyer'> "+buyer+" </label>"+
            "Название товара:  <label id = 'sale-product'> "+product_name+" </label>"+
            "Цена товара:  <label id = 'sale-product-price'> "+product_price+" руб </label>"+
            "Дата:  <label id = 'sale-date'> "+date+" </label>"+
            "Адрес доставки:  <label id = 'sale-address'> "+address+" </label></li>";

        document.getElementById('block-user-sales').innerHTML = appendDeviceStr;
    }
}