
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <html>
        <head>
            <title>Покупка товара</title>
            <meta http-equiv="content-type" content="text/html; charset = utf-8">

            <link href="css/reset.css" rel = "stylesheet" type="text/css" />
            <link href="css/style.css" rel = "stylesheet" type="text/css" />
            <link href="css/buyProd.css" rel = "stylesheet" type="text/css" />

            <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
            <script type="text/javascript" src="/js/auth-script.js"></script>
            <script type="text/javascript" src="/js/buy-script.js"></script>

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

    <div id="block-content">
        <h2 class="h2-title">Покупка товара</h2>
        <form method="POST" id="form_buyProd" action = "#" enctype="multipart/form-data"> <%--сделать обработчик --%>
            <div id="block-form-buyProd">
                <ul id = "form-buyProd">
               <img alt = "ImgPreview" src="\img\photo.png" id ="img-preview" width="150" height="225"/>
                    <li>
<%--                        <label>Название</label>--%>
                        <p id="buyProd_name"></p>
                    </li>

                    <li>
<%--
                        <label>Цена</label>
--%>
                        <p id="buyProd-price"></p>
                    </li>
                    <li><%--
                        <label>Адрес Доставки</label>
                        <span class="star2">*</span>--%>
                        <textarea maxlength="500" required name="buyProd_address" id="buyProd_address"></textarea>
                    </li>

                    <li>
<%--                        <label>Количество: </label>
                        <span class="star2">*</span>--%>
                        <input type="number" id = "buyProd_count" value="1">

                    </li>

                </ul>
            </div>
            <p align="right">
                <input type="submit" name="buyProd_submit" class="form_submit" id="buyProd-Btn" value="Купить"/>
            </p>
        </form>
    </div>

    <jsp:include page="/include/block-footer.jsp" />

</div>
</body>
</html>
</title>
</head>
<body>

</body>
</html>
