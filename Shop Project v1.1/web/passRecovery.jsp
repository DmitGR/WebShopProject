<%--
  Created by IntelliJ IDEA.
  User: SmiLeX
  Date: 24.04.2018
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Big Deal </title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">

    <link href="css/reset.css" rel = "stylesheet" type="text/css" />
    <link href="css/style.css" rel = "stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/shop-script.js"></script>
    <script type="text/javascript" src="/js/passRecovery-script.js"></script>
    <script type="text/javascript" src="/js/auth-script.js"></script>



</head>
<body>

<div id = "block-body">

    <jsp:include page="/include/block-header.jsp" />

    <div id = "block-left">
        <jsp:include page="/include/block-category.jsp" />
        <jsp:include page="/include/block-wish_list.jsp" />
    </div>
    <!-- Основной блок-->

    <div id = "block-content">

        <!-- Блок сортировки-->
        <div id="block-sorting">
            <!--Навигационные Переходы-->
            <p id ="nav-breadcrumbs">
                <a href="index.jsp"> Главная страница</a> \ <span>Все товары</span>
            </p>

        </div>

        <div id="passRecovery-content">

        </div>

    </div>
    <jsp:include page="/include/block-footer.jsp" />
</div>
</body>
</html>
