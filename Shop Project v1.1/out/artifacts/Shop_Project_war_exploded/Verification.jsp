<%--
  Created by IntelliJ IDEA.
  User: SmiLeX
  Date: 27.06.2018
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Подтверждение почты</title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">

    <link href="../css/reset.css" rel = "stylesheet" type="text/css" />
    <link href="../css/style.css" rel = "stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/Verification.js"></script>
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
                <a href="index.jsp"> Главная страница</a> \ <a href="index.jsp" id = nav-span>Все товары</a>
            </p>

            <ul class ="options-list" >
                <!--Сортировка-->
                <li><a id = "select-sort">Сортировка</a>
                    <ul class ="sorting-list">
                        <li><a id="sorting-name-Asc">По названию (А-Я)</a></li>
                        <li><a id="sorting-name-Desc">По названию (Я-А)</a></li>
                        <li><a id="sorting-price-Asc">Дороже</a></li>
                        <li><a id="sorting-price-Desc">Дешевле</a></li>
                        <li><a id="sorting-date-Asc">Новее</a></li>
                        <li><a id="sorting-date-Desc">Старее</a></li>

                    </ul>
                </li>
            </ul>
        </div>
        <p id="verification_message"></p>
    </div>
    <jsp:include page="/include/block-footer.jsp" />
</div>
</body>
</html>
