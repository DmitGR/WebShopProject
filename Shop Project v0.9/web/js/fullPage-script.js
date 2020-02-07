/**
 * Created by mp3dr on 14.06.2018.
 */
$(document).ready(function () {
    var id = document.location.search.slice(4);
    var str ={id:id};
    $.ajax({
        url: "Choose",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {

            var src = "/img/upload_img/" + data.image + "";
            var name = data.name;
            var full_description = data.full_description;
            var features = data.features;
            var date_time = String((data.DateTime));
            var price = data.price;

            document.getElementById('device-full').innerHTML = "<li class='block-device-full'>" +
                "<p class='style-title-grid-full'><a href= '' >" + name + "</a></p>" +
                "<div class='date-li-full'><img class='date-img' src='/img/date.jpg'/><div class='date_time'>" + date_time + "</div></div>" +
                "<ul><li class='bag-li-full'><img class='bag-img-full' src='/img/bag.png'/><p class='price-full'>" + price + " руб.</p></li></ul>" +

                "<div class='block-img-grid-full'><img class='device-img-full' src='" + src + "'/></div>" +
                "<p class='full-page-block-headler'> Описание </p>"+            
                "<div class='full-description'>" + full_description + "</div>"+
                "<p class='full-page-block-headler'> Основные характеристики </p>"+
                "<div class='features'>" + features + "</div></li>";
        }
    }); 
});