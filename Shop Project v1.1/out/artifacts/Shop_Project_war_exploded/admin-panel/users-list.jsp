<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Админ панель</title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">

    <link href="../css/reset.css" rel = "stylesheet" type="text/css" />
    <link href="../css/admin-panel.css" rel = "stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/auth-script.js"></script>

<%--
    <script type="text/javascript" src="/js/auth-script.js"></script>
--%>
    <script type="text/javascript" src="/js/adminPanel-list.js"></script>
</head>
<body>

<div id = "block-body">
    <!-- Основной блок-->
    <!--Лого-->

    <a href="/index.jsp"><img id = "img-logo" src="/img/logo.png"/></a>
    <a href="/index.jsp"><img id = "img-logo2" src="/img/admin-logo.png"/></a>
    <div id = "block-adminPanel">
            <label for="admin-search">Поиск пользователей</label><input type="text" id = "admin-search" />
        <button id="search-btn">Поиск</button>

    </div>

    <div id = "block-users-list">
    <label class = "h2-title" id ="all-users">Список Пользователей</label>
        <ul id = "users-list">

<%--            <li>
                <button>Просмотр</button>
                <label>Имя:<label class="user-info" >Лысенко Фёдор</label> </label>
                <label>Тип: <label class="user-info" >Покупатель</label></label>
                <label>Баланс: <label class="user-info" >100 руб</label></label>
            </li>--%>

        </ul>
    </div>

    </div>
</div>
</body>
</html>
