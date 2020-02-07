<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль</title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">

    <link href="css/reset.css" rel = "stylesheet" type="text/css" />
    <link href="css/style.css" rel = "stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/auth-script.js"></script>
<%--
    <script type="text/javascript" src="/js/shop-script.js"></script>
--%>
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

        <div id="profile-content">

          <li class='block-profile'>
          <div id='block-user-grid'><img class = 'profile-img' src='/img/userBIG.jpg'/>
          <p class='profile-info'>Имя</p>
           <p class='user-type-info'>Тип</p>
              <p class='user-type-info'>Баланс: 0 руб</p>
              <p class='profile-phone'>Почта</p>
          <p class='profile-phone'>Телефон</p>
          <input type='button' class='PassButtons' id = 'changePassButton' value='Сменить пароль'/></div></li>

        </div>

    </div>
    <jsp:include page="/include/block-footer.jsp" />

    <script type="text/javascript" src="/js/profile-script.js"></script>

</div>
</body>
</html>
