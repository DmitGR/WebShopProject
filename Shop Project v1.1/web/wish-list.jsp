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
    <script type="text/javascript" src="/js/auth-script.js"></script>
    <script type="text/javascript" src="/js/prod-list.js"></script>






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

        <p class="h2-title" id = "prod-list-title"></p>

        <div id="devices-list">

            <%--Шаблон для товаров--%>

        </div>

    </div>
    <jsp:include page="/include/block-footer.jsp" />
</body>
</html>
