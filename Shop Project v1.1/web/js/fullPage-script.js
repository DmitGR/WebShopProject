var id;
$(document).ready(function () {
    document.getElementById('device-full-page').innerHTML = "<p class=h2-title>Загрузка...</p>";

    var loaded = GetSession();
    while(!loaded) {
        loaded = GetSession();}
        if(loaded){
            Load();}

});

/**
 Функция для загрузки полной информации о выбранном товаре
 */
function Load()
{
    var select_id = document.location.search.slice(4);
    var str ={id:select_id};
    $.ajax({
        url: "Choose",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
            var prod_id = data.id;
            var src = data.image;
            var productName = data.name;
            var full_description = data.full_description;
            var date_time = String((data.DateTime));
            var price = data.price;
            var sellerName = data.seller.name;
            var sellerID = data.seller.id;
            var phone = data.seller.phone;
            var email = data.seller.email;
            var count = data.count;


            var buyText ="";
            // Добавление кнопки "Купить", если товар есть на складе и тип пользователя - покупатель
            if(type == 0 && count > 0) {
                buyText = "<ul><li class='bag-li-full'><p onclick='choose("+prod_id+");' class='price-full'>Купить " + price + "руб</p><img onclick='choose("+prod_id+");' class='bag-img-full' src='/img/bag.png'/></li></ul>" +
                    "<ul><li class='bag-li-full'><img onclick='AddToFavorite(" + prod_id + ");' class='wish-btn-full' src='/img/wish-btn.png'><p class=wish-text onclick='AddToFavorite(" + prod_id + ");'>Добавить в список желаний</p></li></ul>";
            }
            // Добавление кнопки "Редактировать", если авторизирован продавец этого товара

            else if(type == 1 && id == sellerID)
            {
                buyText = "<ul><li class='bag-li-full'><p onclick='editPage("+prod_id+");' class='price-full'>Редакитровать</p><img onclick='editPage("+prod_id+");' class='bag-img-full' src='/img/list-ico.png'/></li></ul>";

            }
            document.getElementById('device-full-page').innerHTML =

                "<li class='block-device-full'>" +
                "<p class='style-title-grid-full'><a href= '' >" + productName + "</a></p>" +
                "<div class='date-li-full'><img class='date-img' src='/img/date.jpg'/><div class='date_time'>" + date_time + "</div></div>" +
                "<div class='block-img-grid-full'><img class='device-img-full' src='" + src + "'/></div>" +
                buyText+
                "<div class = 'seller-info-block'>" +
                "<p>Продавец: </p>" +
                "<p class = 'seller-info-name'>" + sellerName + "</p>" +
                "<p class = 'seller-info-phone'>Телефон: " + phone + "</p>" +
                "<p class = 'seller-info-email'>E-mail: " + email + "</p>" +
                "<p class='seller-info-stock'>Количество на складе: " + count + "</pclas>" +
                "</div>" +
                "<p class='full-page-block-headler'> Описание </p>" +
                "<div class='full-description'>" + full_description + " </div>";
        }
    });
}

/**
 Функция для добавление товара в список желаний
 */
function AddToFavorite(prod_id)
{
    if(id > 0) {
        var request = {
            user_id: id,
            product_id: prod_id
        };
        $.ajax({
            url: "AddToWish",
            type: "POST",
            datatype: 'json',
            data: {"json": JSON.stringify(request)},
            success: function (data) {
                if (data != 0) {
                    alert("Успешно добавлено");
                    location.reload(true);
                }
                else
                    alert("Error");
            }
        });
    }
    else {
        alert("Необходмио авторизоваться")
    }
}

/**
 Функция для формирования ссылки на покупку
 */
function choose(id) {

    window.location.href = "buyProduct.jsp?id=" + String(id);
}

/**
 Функция для формирования ссылки на редактирование
 */
function editPage(id) {

    window.location.href = "editProduct.jsp?id=" + String(id);
}