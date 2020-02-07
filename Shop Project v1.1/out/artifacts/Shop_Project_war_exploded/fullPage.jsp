<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>◄Web Shop► </title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">

    <link href="css/reset.css" rel = "stylesheet" type="text/css" />
    <link href="css/style.css" rel = "stylesheet" type="text/css" />
    <link href="css/grid-view.css" rel = "stylesheet" type="text/css" />
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/auth-script.js"></script>
    <script type="text/javascript" src="/js/fullPage-script.js"></script>

</head>
<body>

<div id = "block-body">

    <jsp:include page="/include/block-header.jsp" />

    <div id = "block-left">
        <jsp:include page="/include/block-category.jsp" />
        <jsp:include page="/include/block-wish_list.jsp" />
    </div>
    <!-- Основной блок-->

        <div id="device-full-page">

            <%--Шаблон для одного продукта--%>

        </div>
    </div>
    <jsp:include page="/include/block-footer.jsp" />
</div>
</body>
</html>
