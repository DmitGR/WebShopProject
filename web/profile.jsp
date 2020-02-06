<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Big Deal </title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">

    <link href="css/reset.css" rel = "stylesheet" type="text/css" />
    <link href="css/style.css" rel = "stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/shop-script.js"></script>
    <script type="text/javascript" src="/js/profile-script.js"></script>
</head>
<body>

<div id = "block-body">

    <jsp:include page="/include/block-header.jsp" />
    <!-- Блок верхнего выбора -->

    <div id = "top-menu">

        <ul>
            <table><tr>
                <td><li><img src="/img/shop.png" /><a href="index.jsp">Главная</a></li></td>
                <td><li><img src="/img/new.png" /><a id="hotSort-new">Новинки</a></li></td>
                <td><li><img src="/img/best-seller.png" /><a id="hotSort-leader">Лидеры продаж</a></li></td>
                <td><li><img src="/img/sale.png" /><a id="hotSort-sale">Распродажа</a></li></td>
            </tr>
            </table>
        </ul>
    </div>

    <!--Сюда можно разделительную линию-->
    <hr class="horizontal-line" >
    <div id = "block-left">
        <jsp:include page="/include/block-category.jsp" />
        <jsp:include page="/include/block-parameter.jsp" />
    </div>
    <!-- Основной блок-->

    <div id = "block-content">


        <div id="profile-content">
            
        </div>

    </div>
    <jsp:include page="/include/block-footer.jsp" />
</div>
</body>
</html>
