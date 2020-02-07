<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список желаний</title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">

    <link href="css/reset.css" rel = "stylesheet" type="text/css" />
    <link href="css/style.css" rel = "stylesheet" type="text/css" />

    <div id="css-view">

        <%--выбор css в зависимости от вида (список/сетка)--%>

        <link href='css/list-view.css' rel = 'stylesheet' type='text/css' />
    </div>
    <%--<link href="css/grid-view.css" rel = "stylesheet" type="text/css" />--%>

    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/shop-script.js"></script>

</head>
<body>

<img id = "back" src="/img/Blue.png">
<div id = "block-body">
    <jsp:include page="/include/block-header.jsp" />

    <div id = "block-left">
        <jsp:include page="/include/block-category.jsp" />
        <jsp:include page="/include/block-wish_list.jsp" />
    </div>
    <!-- Основной блок-->
    <div id = "block-content">

        <p class="h2-title" > Список Товаров на продаже</p>

        <div id="devices-list">

            <%--Шаблон для товаров--%>


            <li class='block-device'>
                <div class='block-img-grid'><img class='device-img' src="/img/upload_img/4.jpg"/></div>
                <div class="btn-close-ico"><img class='close-ico' src="/img/close.png"/></div>

                <p class='style-title-grid'><a href= ""> Холодильник САРАТОВ 550</a></p>
                <ul class='reviews-grid-comment'>
                    <li class='date-li'><img class='date-img' src='/img/date.jpg'/><div class='date_time'>03-апр-2019</div></li></ul>
                <ul class='reviews-grid-price'>
                    <li class='bag-li'><img class='bag-img' src='/img/cost.png'/><p class='price'>19000 руб.</p></li></ul>
                <div class='mini-features'>количество камер: однокамерный; расположение морозильной камеры- морозильная камера отсутствует</div>
               <ul class="prod-info">
                  <li><p>Кол-во на складе: <label class="stock-count">5</label></p></li>
                   <li><p>Кол-во продаж: <label class="stock-count">1</label></p></li>
               </ul>
            </li>

                <li class='block-device'>
                    <div class='block-img-grid'><img class='device-img' src="/img/upload_img/2.jpg"/></div>
                    <div class="btn-close-ico"><img class='close-ico' src="/img/close.png"/></div>
                    <p class='style-title-grid'><a href= "">Яндекс Смартфон новый</a></p>
                    <ul class='reviews-grid-comment'>
                        <li class='date-li'><img class='date-img' src='/img/date.jpg'/><div class='date_time'>10-мар-2019</div></li></ul>
                    <ul class='reviews-grid-price'>
                        <li class='bag-li'><img class='bag-img' src='/img/cost.png'/><p class='price'>17500 руб.</p></li></ul>
                    <div class='mini-features'>ОС Android 8.1, экран: 5.65", IPS, 2160×1080, процессор: Qualcomm Snapdragon 630, 2200МГц, 8-ми ядерный, камера: 16Мп, GPS, ГЛОНАСС, время работы в режиме разговора, до: 20ч, в режиме ожидания, до: 580ч, оперативная память: 4Гб, встроенная память: 64Гб</div>
                    <ul class="prod-info">
                        <li><p >Кол-во на складе: <label class="stock-count">15</label></p></li>
                        <li><p >Кол-во продаж: <label class="stock-count">3</label></p></li>
                    </ul>
                </li>


        </div>



    </div>
    <jsp:include page="/include/block-footer.jsp" />
</body>
</html>
