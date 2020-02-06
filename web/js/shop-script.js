var filterSort = "none"; //price-asc, price-desc, name-asc, name-desc, date
var manufacturersSort = new Array();  //Apple, Asus, Acer, HP, Lenovo, Dell, Samsung, Xiaomi
var typeSort = "none"; //Phone, Laptop, Tablet
var hotSortNew = false;
var hotSortLeader = false;
var hotSortSale = false;
var osSort="none"; //Windows, iOS, macOS, Linux, Android 
var startPrice = 0;
var endPrice = 300000;
var canAuth = false;
var viewGrid = true;


$(document).ready(function () {
    replaySortParameters();
    createQuery();
    AuthSession();
    //Поиск    
    $("#button-search").click(function () {

        var search = $("#input-search").val();
        // alert(search);
        if (search.length > 1) {
            Search(search);
        }
        else {
            alert("Need 2 symbols or more for search");
        }
    });

    $("#choiceOfView-grid").click(function () {
        viewGrid=true;
        Load();
    });

    $("#choiceOfView-list").click(function () {
        viewGrid=false;
        Load();
    });
    

    //Ограничение ввода   
    // 8 - backspace key, 46 - delete key

    $('#input-search').on('keyup keypress', function (e) {

        var unavailableSymbols = '!@<>""/*-+%()$&#,.;:?=|_^~`';
        return (unavailableSymbols.indexOf(String.fromCharCode(e.which)) == -1);
    });


    $('#start-price').on('keyup keypress', function (e) {
        if (e.keyCode == 8 || e.keyCode == 46) {
        }
        else {
            var availableSymbols = '1234567890';
            return (availableSymbols.indexOf(String.fromCharCode(e.which)) != -1);
        }
    });

    $('#end-price').on('keyup keypress', function (e) {
        if (e.keyCode == 8 || e.keyCode == 46) {
        }
        else {
            var availableSymbols = '1234567890';
            return (availableSymbols.indexOf(String.fromCharCode(e.which)) != -1);
        }
    });

    // Toggle Forms

    $("#select-sort").click(function () {
        $("#sorting-list").slideToggle(200);
    });

    $(".top-auth").click(function () {
        $("#block-top-auth").slideToggle(200);
    });

    $('#button-pass-show-hide').click(function() {
        var statuspass = $('#button-pass-show-hide').attr("class");

        if(statuspass == "pass-show") {
            $('#button-pass-show-hide').attr("class","pass-hide");
            var $input = $("#auth_pass");
            var change ="text";
            var rep = $("<input placeholder='Пароль' type='" + change + "' />")
                .attr("id",$input.attr("id"))
                .attr("name",$input.attr("name"))
                .attr('class',$input.attr('class'))
                .val($input.val())
                .insertBefore($input);
            $input.remove();
            $input = rep;
        } else {
            $('#button-pass-show-hide').attr("class","pass-show");
            var $input = $("#auth_pass");
            var change ="password";
            var rep = $("<input placeholder='Пароль' type='" + change + "' />")
                .attr("id",$input.attr("id"))
                .attr("name",$input.attr("name"))
                .attr('class',$input.attr('class'))
                .val($input.val())
                .insertBefore($input);
            $input.remove();
            $input = rep;
        }
    });﻿
    
    // Auth
    $("#button-auth").click(function () {
        var auth_login = $("#auth_login").val();
        var auth_pass = $("#auth_pass").val();


        if(auth_login == "" || auth_login.length > 16 || auth_login.length < 5)
        {
            $("#auth_login").css("background-color","#ff9191");
            canAuth = false;
        }
        else
        {
            $("#auth_login").css("background-color","#DBDBDB");
            canAuth = true;
        }
        
        if(auth_pass == "" || auth_pass > 15 || auth_pass < 6)
        {
            $("#auth_pass").css("background-color","#ff9191");
            canAuth = false;
        }
        else
        {
            $("#auth_pass").css("background-color","#DBDBDB");
            canAuth = true;
        }
        
        if(canAuth)
        {
            Auth(auth_login,auth_pass);    
        }
    });
    

    
    // Param Search
    $("#button-param-search").click(function () {
        startPrice = $("#start-price").val();
        endPrice =  $("#end-price").val();
        createQuery();
    });

    //изменение фильтров
    $("#head-page").click(function () {replaySortParameters(); createQuery();});

    $("#all-phones").click(function () {replaySortParameters(); typeSort="Phone"; createQuery(); $("#nav-span").innerHTML='Телефоны'});
    $("#iOS-phone").click(function () {osSort="iOS"; typeSort="Phone"; createQuery();});
    $("#Android-phone").click(function () {osSort="Android"; typeSort="Phone"; createQuery();});

    $("#all-laptops").click(function () {replaySortParameters(); typeSort="Laptop"; createQuery();});
    $("#Windows-laptop").click(function () {osSort="Windows"; typeSort="Laptop"; createQuery();});
    $("#MacOS-laptop").click(function () {osSort="MacOS"; typeSort="Laptop"; createQuery();});
    $("#Linux-laptop").click(function () {osSort="Linux"; typeSort="Laptop"; createQuery();});

    $("#all-tablets").click(function () {replaySortParameters(); typeSort="Tablet"; createQuery();});
    $("#Android-tablet").click(function () {osSort="Android"; typeSort="Tablet"; createQuery();});
    $("#iOS-tablet").click(function () {osSort="iOS"; typeSort="Tablet"; createQuery();});
    $("#Windows-tablet").click(function () {osSort="Windows"; typeSort="Tablet"; createQuery();});

    $("#priceMin").click(function () {$("#sorting-list").slideToggle(200); filterSort="price-asc"; createQuery();});
    $("#priceMax").click(function () {$("#sorting-list").slideToggle(200); filterSort="price-desc"; createQuery();});
 //   $("#popular").click(function () {filterSort="popular"; createQuery();});
    $("#new").click(function () {$("#sorting-list").slideToggle(200); filterSort="date"; createQuery();});
    $("#alphabet").click(function () {$("#sorting-list").slideToggle(200); filterSort="name-asc"; createQuery();});

    $("#hotSort-new").click(function () {replaySortParameters(); hotSortNew=true; createQuery();});
    $("#hotSort-leader").click(function () {replaySortParameters(); hotSortLeader=true; createQuery();});
    $("#hotSort-sale").click(function () {replaySortParameters(); hotSortSale=true; createQuery();});



    $('#checkApple').on('change', function(){if($('#checkApple').prop('checked')){manufacturersSort.push("Apple"); }else{removeManufactures("Apple");}});
    $('#checkAcer').on('change', function(){if($('#checkAcer').prop('checked')){manufacturersSort.push("Acer"); }else{removeManufactures("Acer"); }});
    $('#checkAsus').on('change', function(){if($('#checkAsus').prop('checked')){manufacturersSort.push("Asus"); }else{removeManufactures("Asus"); }});
    $('#checkDell').on('change', function(){if($('#checkDell').prop('checked')){manufacturersSort.push("Dell"); }else{removeManufactures("Dell"); }});
    $('#checkHP').on('change', function(){if($('#checkHP').prop('checked')){manufacturersSort.push("HP"); }else{removeManufactures("HP"); }});
    $('#checkLenovo').on('change', function(){if($('#checkLenovo').prop('checked')){manufacturersSort.push("Lenovo"); }else{removeManufactures("Lenovo"); }});
    $('#checkSamsung').on('change', function(){if($('#checkSamsung').prop('checked')){manufacturersSort.push("Samsung"); }else{removeManufactures("Samsung"); }});
    $('#checkXiaomi').on('change', function(){if($('#checkXiaomi').prop('checked')){manufacturersSort.push("Xiaomi"); }else{removeManufactures("Xiaomi"); }});

});

function replaySortParameters() {
    filterSort = "none"; 
    manufacturersSort = new Array();  
    typeSort = "none";
    hotSortNew = false;
    hotSortLeader = false;
    hotSortSale = false;
    osSort="none"; 
    startPrice = 0;
    endPrice = 300000;
}

function removeManufactures(str) {
    for (var i = 0; i <= manufacturersSort.length; i++){
        if(manufacturersSort[i]==str){
            manufacturersSort.splice(i,1);
            i--;
        }
    }
}

function createQuery() {
    var str = {
        filterSort: filterSort,
        manufacturersSort: manufacturersSort,
        typeSort: typeSort,
        hotSortNew: hotSortNew,
        hotSortLeader: hotSortLeader,
        hotSortSale: hotSortSale,
        osSort: osSort,
        min_price:startPrice,
        max_price:endPrice
    };

    $.ajax({
        url: "Load",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
            if(data == ""){ document.getElementById('devices-list').innerHTML = "<h1 class=search-error>Ничего не найдено! <br> <img src=/img/search-error.jpg></h1>"};
            addToPage(data);
        }
    });
}

function Search(search) {
    document.title = "Big Deal Поиск - "+search;
    var str ={search:search};
    $.ajax({
        url: "Search",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
            if(data == ""){ document.getElementById('devices-list').innerHTML = "<h1 class=search-error>Ничего не найдено! <br> <img src=/img/search-error.jpg></h1>"};
            addToPage(data);
          //  alert(data);
        }
    });
}

function Load() {
    replaySortParameters();
    $.ajax({
        url: "Load",
        type: "POST",
        datatype: 'json',
        data: {"initial_load": "none"},
        success: function (data) {
            addToPage(data);
        }
    });
}


function choose(id) {

    window.location.href = "fullPage.jsp?id=" + String(id);
}


function addToPage(data) {
    var appendDeviceStr = "";
    for (var i = 0; i <= data.length; i++) {
        var src = "/img/upload_img/" + data[i].image + "";
        var href = "www.apple.com";
        var name = data[i].name;
        var mini_description = data[i].mini_description;
        var price = data[i].price;
        var date_time = String((data[i].DateTime));
      //  var date_time = data[i].datetime;
        if(viewGrid){
            document.getElementById('css-view').innerHTML = "<link href='css/grid-view.css' rel = 'stylesheet' type='text/css' />";
        }   
        else {
            document.getElementById('css-view').innerHTML = "<link href='css/list-view.css' rel = 'stylesheet' type='text/css' />";
        }
        
        appendDeviceStr += "<li class='block-device'>" +
            "<div class='block-img-grid'><img class='device-img' src='" + src + "'/></div>" +
            "<p class='style-title-grid'><a href= "+"javascript:choose("+data[i].id+")>" + name + "</a></p>" +
            "<ul class='reviews-grid-comment'>" +
            "<li class='date-li'><img class='date-img' src='/img/date.jpg'/><div class='date_time'>" + date_time + "</div></li></ul>" +
            "<ul class='reviews-grid-price'>" +
            "<li class='bag-li'><img class='bag-img' src='/img/bag.png'/><p class='price'>" + price + " руб.</p></li></ul>" +
            "<div class='mini-features'>" + mini_description + "</div></li>";
        document.getElementById('devices-list').innerHTML = appendDeviceStr;
    }
}


function Auth(login, pass) {
    var str = {
            login:login,
            pass:pass
    };
    $.ajax({
        url: "Authorisation",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(str)},
        success: function (data) {
            var message =   $("#message-auth");
            if(data.verified == 0)
            {
                message.text("Ваша почта не подтверждена");
                message.slideDown(420);

            }
            else if(data != 0)
            {
                alert("Добро Пожаловать!");
                window.location.href="/index.jsp";
            }
            else
            {
                message.slideDown(420);
            }
        }
    });
}
function AuthSession() {
    $.ajax({
        url: "Authorisation",
        type: "POST",
        datatype: 'json',
        data: {"initial_load": "none"},
        success: function (data) {
//            alert(data);
            if (data != null && data != "") {
                //  $("#reg-auth-title").css("display","none");
                document.getElementById('reg-auth-title').innerHTML = "<p id =user-info align=right><img src=/img/user.png> Здравствуйте," + data.name + "</p>";
                $("#user-info").hover(function () {
                        $(this).css("cursor", "pointer");
                    },
                    function () {
                        $(this).css("cursor", "pointer");
                    });
                $("#user-info").click(
                    function () {
                        if ($("#block-user").css("display") == "none") {
                            $("#block-user").css("display", 'block');
                        }
                        else $("#block-user").css("display", "none");
                    }
                );
                $("#profile-exit").click(function () {

                    $.ajax({
                        url: "Authorisation",
                        type: "POST",
                        data: {logout: 1},
                        success: function (data) {
                            window.location.href = "/index.jsp";
                        }
                    });
                });
            }
        }
    });
}

    


    

    
