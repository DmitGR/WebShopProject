var copy;
var maxCount = 1;

/**
 * @return {string}
 */
var GetStatus= function (status) {
   switch (status){
       case true: return "Успешно";
        break;
        case  false: return"Безуспешно";
        break;
   }
};

$(document).ready(function () {
    copy = document.getElementById('block-form-buyProd').innerHTML;
    document.getElementById('block-form-buyProd').innerHTML = "<p class=h2-title>Загрузка...</p>";

    // Получение сессии

    var loaded = GetSession();
    while (!loaded) {
        loaded = GetSession();
    }
    if (loaded) {
        Load();
    }
    Load();


});


/**
 Функция для загрузки данных о покупаемом товаре
 */
function Load() {
    //alert("id:"+id);
        var load_id = document.location.search.slice(4);
        var str = {id: load_id};
        $.ajax({
            url: "Choose",
            type: "POST",
            datatype: 'json',
            data: {"json": JSON.stringify(str)},
            success: function (data) {

                var prod_id = data.id;
                var src = data.image;
                var productName = data.name;
                var price = data.price;
                var seller_id = data.seller.id;
                maxCount = data.count;
                var totalSum = price;


                    // alert("Count: " + maxCount);

                document.getElementById('block-form-buyProd').innerHTML = copy;

                document.getElementById('form-buyProd').innerHTML =
                    "<img alt = 'ImgPreview' src='" + src + "' id ='img-preview' width='150' height='260'/>" +
                    "<li>" +
                    "<label>Название</label>" +
                    "<p id='buyProd_name'>" + productName + "</p>" +
                    "</li>" +
                    "<li>" +
                    "<label>Цена</label>" +
                    "<p id='buyProd-price'>" + price + " руб" + "</p>" +
                    "</li>" +
                    "<li>" +
                    "<label class='buyLabel'>Адрес Доставки</label>" +
                    "<span class='star2'>*</span>" +
                    "<textarea maxlength='500' required name='buyProd_address' id='buyProd_address'></textarea>" +
                    "</li>" +
                    "<li>" +
                    "<label class='buyLabel'>Количество: </label>" +
                    "<span class='star2'>*</span>" +
                    "<input type='number' id ='buyProd_count' value='1'></li>";


                // Проверка на количество
                $("#buyProd_count").bind('keyup mouseup', function () {
                   // alert("21");
                    var checkCount = $(this).val();
                    if (checkCount > maxCount)
                        $(this).val(maxCount);
                    if (checkCount <= 0)
                        $(this).val(1);
                    totalSum = $("#buyProd_count").val()*price;
                    $("#buyProd-price").text( totalSum + " руб");

                });
                // Проверка баланса перед покупкой
                document.getElementById('buyProd-Btn').onclick = function () {

                    if (balance > totalSum) {
                        BuyProduct(prod_id, seller_id, id, price);
                    }
                    else {
                        alert("Недостаточно средств");
                        document.getElementById('block-form-buyProd').innerHTML = "Недостаточно средств";

                    }
                };

            }
        });
}

/**
 Функция для отправки заказа на сервер
 */
function BuyProduct(prod_id, seller_id, buyer_id, price) {
    if(type == 0) {

        var address = $("#buyProd_address").val();
        var str = {
            prod_id: prod_id,
            seller_id: seller_id,
            buyer_id: buyer_id,
            address: address,
            price: price
        };

        $.ajax({
            url: "BuyProduct",
            type: "POST",
            datatype: 'json',
            data: {"json": JSON.stringify(str)},
            success: function (data) {
                alert("Статус: " + GetStatus(data));
                window.location = "/index.jsp";
            }
        });
    }
    else {
        document.getElementById('block-form-buyProd').innerHTML = "<p class=h2-title>Необходимо авторизоваться...</p>";

    }
}