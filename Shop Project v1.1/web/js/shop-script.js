var sort = {
    default:0,
    nameAsc:1,
    nameDesc:2,
    priceAsc:3,
    priceDesc:4,
    dateAsc:5,
    dateDesc:6
};
var category = {
    default:0,
    Appliances : 1,
    Electronic :2,
    Transport : 3
};
var subcategory = {
    default:0,
    Dishwashers : 1,
    TV : 2,
    Fridge : 3,
    Microwave : 4,
    Washing_machine : 5,
    Vacuum_cleaner :6,
    Cars : 29,
    Motorcycles :30,
    Trucks : 31,
    Water_transport  : 32,
    Smartphones : 33,
    Tablets : 34,
    Laptops : 35,
    Cameras : 36,
    Videocamera : 37,
    Acoustics : 38,
    Player : 39
};
var filterSort = sort.dateDesc; //price-asc, price-desc, name-asc, name-desc, date-asc, date-desc
var CategorySort = category.default; // Transport,  Appliances, Electronic
var SubcategorySort= subcategory.default; // tv,  phones ,....
var page;
/*var startPrice = 0;
var endPrice = 300000;*/
/*var canAuth = false;
var viewGrid = true;*/

$(document).ready(function () {
page = document.location.search.slice(6);
if(page < 1){
    page =1;
}

    $("#back-btn").click (function () {
        if (page > 1) {
            page--;
            window.location = "index.jsp?page=" + page;
        }
    });
    $("#forward-btn").click (function () {
            page++;
            window.location = "index.jsp?page=" + page;
    });

    GetSession();
createQuery();
    //Поиск
    $("#button-search").click(function () {

        var search = $("#input-search").val();
        // alert(search);
        if (search.length > 1) {
            Search(search);
            //Load();
        }
        else {
            alert("Need 2 symbols or more for search");
        }
    });

    $('#input-search').on('keyup keypress', function (e) {

        var unavailableSymbols = '!@<>""/*-+%()$&#,.;:?=|_^~`';
        return (unavailableSymbols.indexOf(String.fromCharCode(e.which)) == -1);
    });

    // Toggle Forms

    $("#select-sort").click(function () {
        $(".sorting-list").slideToggle(200);
    });

    //Изменение фильтров

    // Сброс категорий
    $("#head-page").click(function () {resetSortParameters(); createQuery();});

    //  Добавление функций на клик

    $("#transport-all").click(function () {CategorySort=category.Transport; SubcategorySort=subcategory.default; createQuery(); });
    $("#transport-auto").click(function () {SubcategorySort=subcategory.Cars; CategorySort=category.Transport; createQuery();});
    $("#transport-motorcycle").click(function () {SubcategorySort=subcategory.Motorcycles; CategorySort=category.Transport; createQuery();});
    $("#transport-truck").click(function () {SubcategorySort=subcategory.Trucks; CategorySort=category.Transport; createQuery();});
    $("#transport-water").click(function () {SubcategorySort=subcategory.Water_transport; CategorySort=category.Transport; createQuery();});

    $("#appliances-all").click(function () {CategorySort=category.Appliances;SubcategorySort=subcategory.default; createQuery();});
    $("#appliances-dishwasher").click(function () {SubcategorySort=subcategory.Dishwashers; CategorySort=category.Appliances; createQuery();});
    $("#appliances-fridge").click(function () {SubcategorySort=subcategory.Fridge; CategorySort=category.Appliances; createQuery();});
    $("#appliances-microwave").click(function () {SubcategorySort=subcategory.Microwave; CategorySort=category.Appliances; createQuery();});
    $("#appliances-tv").click(function () {SubcategorySort=subcategory.TV; CategorySort=category.Appliances; createQuery();});
    $("#appliances-vacuum").click(function () {SubcategorySort=subcategory.Vacuum_cleaner; CategorySort=category.Appliances; createQuery();});
    $("#appliances-washer").click(function () {SubcategorySort=subcategory.Washing_machine; CategorySort=category.Appliances; createQuery();});

    $("#electronic-all").click(function () {CategorySort=category.Electronic;SubcategorySort=subcategory.default; createQuery(); });
    $("#electronic-audio").click(function () {SubcategorySort=subcategory.Acoustics; CategorySort=category.Electronic; createQuery(); });
    $("#electronic-camera").click(function () {SubcategorySort=subcategory.Cameras; CategorySort=category.Electronic; createQuery();});
    $("#electronic-phone").click(function () {SubcategorySort=subcategory.Smartphones; CategorySort=category.Electronic; createQuery();});
    $("#electronic-player").click(function () {SubcategorySort=subcategory.Player; CategorySort=category.Electronic; createQuery();});
    $("#electronic-tablet").click(function () {SubcategorySort=subcategory.Tablets; CategorySort=category.Electronic; createQuery();});
    $("#electronic-notebook").click(function () {SubcategorySort=subcategory.Laptops; CategorySort=category.Electronic; createQuery();});
    $("#electronic-video").click(function () {SubcategorySort=subcategory.Videocamera; CategorySort=category.Electronic; createQuery();});

    $("#sorting-name-Asc").click(function () {$(".sorting-list").slideToggle(200); filterSort=sort.nameAsc; createQuery();});
    $("#sorting-name-Desc").click(function () {$(".sorting-list").slideToggle(200); filterSort=sort.nameDesc; createQuery();});
    $("#sorting-price-Asc").click(function () {$(".sorting-list").slideToggle(200); filterSort=sort.priceAsc; createQuery();});
    $("#sorting-price-Desc").click(function () {$(".sorting-list").slideToggle(200); filterSort=sort.priceDesc; createQuery();});
    $("#sorting-date-Asc").click(function () {$(".sorting-list").slideToggle(200); filterSort=sort.dateAsc; createQuery();});
    $("#sorting-date-Desc").click(function () {$(".sorting-list").slideToggle(200); filterSort=sort.dateDesc; createQuery();});

});

function resetSortParameters() {
    filterSort = sort.dateDesc;
    CategorySort = category.default;
    SubcategorySort=subcategory.default;
}

function createQuery() {
    var navCat;
    var navSubc;
    switch (CategorySort){
        case category.Appliances:  navCat = "Бытовая техника";
            break;
        case category.Electronic: navCat = "Электргоника";
            break;
        case category.Transport: navCat = "Транспорт";
        break;
    }
    $("#nav-span").text(navCat);
    var request = {
        filterSort: filterSort,
        category: CategorySort,
        subcategory: SubcategorySort,
        page:page
    };

    $.ajax({
        url: "Load",
        type: "POST",
        datatype: 'json',
        data: {"json": JSON.stringify(request)},
        success: function (data) {
            if(data == ""){ document.getElementById('devices-list').innerHTML = "<h1 class=search-error>Ничего не найдено! <br> <img src=/img/search-error.jpg></h1>"};
            addToPage(data);
        }
    });
}

function Search(search) {
    document.title = "Поиск - "+search;
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
    resetSortParameters();
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
    for (var i = 0; i < data.length; i++) {
        var id = data[i].id;
        var src = data[i].image;
        var href = "index.jsp";
        var name = data[i].name;
        var mini_description = data[i].mini_description;
        var price = data[i].price;
        var date_time = String((data[i].DateTime));


       // document.getElementById('css-view').innerHTML = "<link href='css/grid-view.css' rel = 'stylesheet' type='text/css' />";


    appendDeviceStr += "<li class='block-device'>"+
    "<div class='block-img-grid'><img class='device-img' src='"+src+"'/></div>"+
    "<p class='style-title-grid'><a href= "+"javascript:choose("+id+")> " + name + "</a></p>"+
    "<ul class='reviews-grid-comment'>"+
    "<li class='date-li'><img class='date-img' src='/img/date.jpg'/><div class='date_time'>" + date_time + "</div></li></ul>" +
    "<ul class='reviews-grid-price'>"+
    "<li class='bag-li'><img class='bag-img' src='/img/cost.png'/><p class='price'>" + price + " руб.</p></li></ul>"+
    "<div class='mini-features'>" + mini_description + "</div></li>";

    document.getElementById('devices-list').innerHTML = appendDeviceStr;
    }
}



    


    

    
