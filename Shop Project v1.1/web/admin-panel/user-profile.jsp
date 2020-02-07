<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Админ панель</title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">

    <link href="../css/reset.css" rel = "stylesheet" type="text/css" />
    <link href="../css/admin-panel.css" rel = "stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/auth-script.js"></script>
    <script type="text/javascript" src="/js/user-info-script.js"></script>

</head>
<body>

<div id = "block-body">
    <!-- Основной блок-->
    <!--Лого-->

    <a href="/admin-panel/users-list.jsp" ><img id = "img-logo" src="/img/logo.png"/></a>
   <a href="/admin-panel/users-list.jsp" ><img id = "img-logo2" src="/img/admin-logo.png"/></a>


    <div id = "block-adminPanel">
        <label class="h2-title">Данные пользователя</label>

        <div id="profile-content">
            <img class = 'profile-img' src='/img/userBIG.jpg'/>
                <ul id="block-user-info">
                <li><input type="text" maxlength="45" name = "user-surname" value="Фамилия" /></li>
                <li><input type="text" maxlength="45" name = "user-name" value="Имя" /></li>
                <li><input type="text" maxlength="45" name = "user-patronymic" value="Отчество" /></li>
                <li><input type="number" maxlength="20" name = "user-balance" value="Баланс" /> <label>руб </label></li>
                <li><input type="email" maxlength="45" name = "user-mail" value="Почта" /></li>
                <li><input type="tel" maxlength="45" name = "user-phone" value="Номер Телефона" /></li>
                <li><input type='button' class='PassButtons' id = 'changeUserData' value='Сохранить изменения'/></li>
                </ul>

            <p class="h2-title2">Заказы пользователя</p>
            <ul id = "block-user-sales">
            </ul>
        </div>

    </div>
</div>

</body>
</html>
