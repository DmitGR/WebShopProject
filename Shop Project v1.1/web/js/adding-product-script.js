var addProdForm;
var target;
var sublist = [[],["Телевизоры","Холодильники","Стиральные машины","Микроволновые печи","Посудомоечные машины","Пылесосы"],
    ["Автомобили","Мотоциклы","Грузовики","Водный транспорт"],
    ["Смартфоны", "Планшеты","Ноутбуки","Фотоаппараты","Видеокамеры","Акустика","Проигрыватели"]];
var subs = [[],[2,3,5,4,1,6],[29,30,31,32],[33,34,35,36,37,38,39]];
var maxCount = 1000000;
var maxPrice = 1000000000000;
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
        AddProduct();
    });
});

/**
 Функция для загрузки подкатегорий
 */
function Load() {
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
    var cid = $("#category-select").val();
    var appendStr = "";
    for (var i = 0; i < sublist[cid].length; i++) {
        appendStr += "<option value=" + subs[cid][i] + ">" + sublist[cid][i] + "</option>"
    }
    document.getElementById('subcategory-select').innerHTML = appendStr;
    $("#addProd-count").bind('keyup mouseup', function () {
        var checkCount = $(this).val();
        if (checkCount > maxCount)
            $(this).val(maxCount);
        else if (checkCount <= 0)
            $(this).val(1);
        else {        $(this).val(parseInt(checkCount));
        }
    });

    $("#addProd-price").bind('keyup mouseup', function () {
        var checkCount = $(this).val();
        if (checkCount <= 0)
            $(this).val(1);
        else if (checkCount > maxPrice)
            $(this).val(maxPrice);
        else {        $(this).val(parseInt(checkCount));
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
function AddProduct() {

    var str = {
        subcateg_id:$('#subcategory-select').val(),
        user_id:id,
        price: $('#addProd-price').val(),
        name: $('#addProd_name').val(),
        description: $('#addProd-description').val(),
        img: $('#addProd-photo').val().slice(12),
        count: $('#addProd-count').val()
    };
    $.ajax({
        url: "AddProduct",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
            alert("Успешно "+data)
        }
    });
}

