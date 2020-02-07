var addProdForm;
var prod_id;
var photo;
var target;
var maxCount = 1000000;
var maxPrice = 1000000000;
var sublist = [[],["Телевизоры","Холодильники","Стиральные машины","Микроволновые печи","Посудомоечные машины","Пылесосы"],
    ["Автомобили","Мотоциклы","Грузовики","Водный транспорт"],
    ["Смартфоны", "Планшеты","Ноутбуки","Фотоаппараты","Видеокамеры","Акустика","Проигрыватели"]];
var subs = [[],[2,3,5,4,1,6],[29,30,31,32],[33,34,35,36,37,38,39]];
$(document).ready(function () {

    addProdForm = document.getElementById('block-content').innerHTML;
    document.getElementById('block-content').innerHTML = "<p class=h2-title>Загрузка...</p>";

    // Получение текущей сессии
    var loaded = GetSession();
    while (!loaded) {
        loaded = GetSession();
    }
    if (loaded) {
        Load();
    }

    //Добавление событий на кнопки
    UploadFile();
    $('#form_addProd').on('submit', function () {
        EditProduct();
    });
    $("#editProd-count").bind('keyup mouseup', function () {

        var checkCount = $(this).val();
        if (checkCount <= 0)
            $(this).val(1);
        else if(checkCount > maxCount)
            $(this).val(maxCount);
        else {        $(this).val(parseInt(checkCount));
        }
    });

    $("#editProd-price").bind('keyup mouseup', function () {
        var checkCount = $(this).val();
        if (checkCount <= 0)
            $(this).val(1);
        else if(checkCount > maxPrice)
            $(this).val(maxPrice);
        else {        $(this).val(parseInt(checkCount));
        }

    });
});

/**
 Функция для загрузки подкатегорий
 */
function Load() {
    prod_id = document.location.search.slice(4);

    document.getElementById('block-content').innerHTML = addProdForm;
    PreLoad();


    $("#category-select").change(function () {
        var cid = $("#category-select").val();
       // alert("Select ID: " + cid);
        var appendStr = "";
        for (var i = 0; i < sublist[cid].length; i++) {
            appendStr += "<option value=" + subs[cid][i] + ">" + sublist[cid][i] + "</option>"
        }
        document.getElementById('subcategory-select').innerHTML = appendStr;
    });
}

/**
 Функция для загрузки категорий
 */
function PreLoad() {

    var str ={id:prod_id};
    $.ajax({
        url: "Choose",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
            var src = data.image;
            photo = src;
            var productName = data.name;
            var full_description = data.full_description;
            var price = data.price;
            var sellerID = data.seller.id;
            var count = data.count;
            var categID = data.category;
            var subcID = data.subcategory;

            if(categID == 2)
                categID=3;
            else if(categID == 3)
                categID =2;

            // Проверка продовца на владение товаром
            if(type > 0 && id == sellerID) {
                $("#category-select").val(categID);
                $("#editProd_name").val(productName);
                $("#editProd-description").val(full_description);
                $("#editProd-price").val(price);
                $("#editProd-count").val(count);

                $("#img-preview").attr("src",src);

                var appendStr = "";
                for (var i = 0; i < sublist[categID].length; i++) {
                    appendStr += "<option value=" + subs[categID][i] + ">" + sublist[categID][i] + "</option>"
                }
                document.getElementById('subcategory-select').innerHTML = appendStr;
                $("#subcategory-select").val(subcID);

            }
            else {
                document.getElementById('block-content').innerHTML = "<p class=h2-title>У вас не достаточно прав</p>";
            }
        }
    });
}

/**
 Функция отображения фото
 */
function PhotoPreview() {
    target = document.getElementById("img-preview");
    var file = document.querySelector("input[type=file]").files[0];

    var reader = new FileReader();

    reader.onloadend = function () {
        target.src = reader.result;
    };
    if(file)
    {
        reader.readAsDataURL(file);
    }
    else
    {
        target.src="";
    }
}

/**
 Функция для загрузки файла на сервер
 */
function UploadFile() {

    $('#addProd-photo').ajaxfileupload({
        'action': 'UploadFile',
        'onComplete': function (response) {
            $('#upload').hide();
         //   alert("File SAVED!! " +response);
        },
        'onStart': function () {
            $('#upload').show();
        }
    });
}

/**
 Функция для отправки данных о новом товаре
 */
function EditProduct() {

    var sendingPhoto = $('#editProd-photo').val().slice(12);
    if(sendingPhoto.localeCompare("") == 0)
        sendingPhoto = photo.slice(16);
    var str = {
        prod_id:prod_id,
        subcateg_id:$('#subcategory-select').val(),
        price: $('#editProd-price').val(),
        name: $('#editProd_name').val(),
        description: $('#editProd-description').val(),
        img: sendingPhoto,
        count: $("#editProd-count").val()
    };
    $.ajax({
        url: "EditProduct",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
            alert("Успешно "+data)
        }
    });
}

