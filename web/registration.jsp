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
    <title>Big Deal</title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">

    <link href="css/reset.css" rel = "stylesheet" type="text/css" />
    <link href="css/style.css" rel = "stylesheet" type="text/css" />



</head>
<body>

<div id = "block-body">

    <jsp:include page="/include/block-header.jsp" />
    <!-- Блок верхнего выбора -->

    <div id = "top-menu">

        <ul>
            <table><tr>
                <td><li><img src="/img/shop.png" /><a href="index.jsp">Главная</a></li></td>
                <td><li><img src="/img/new.png" /><a href="index.jsp">Новинки</a></li></td>
                <td><li><img src="/img/best-seller.png" /><a href="index.jsp">Лидеры продаж</a></li></td>
                <td><li><img src="/img/sale.png" /><a href="index.jsp">Распродажа</a></li></td>
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

    <div id="block-content">
        <h2 class="h2-title">Регистрация</h2>
        <form method="#" id="form_reg" action = "#"> <%--сделать обработчик регистрации--%>

            <p id="reg_message"></p>
            <div id="block-form-registration">
                <ul id = "form-registration">
                    <li>
                        <label>Логин</label>
                        <span class="star">*</span>
                        <input type="text" name="reg_login" id="reg_login"/>
                    </li>

                    <li>
                        <label>Пароль</label>
                        <span class="star">*</span>
                        <input type="password" name="reg_pass" id="reg_pass"/>
                      <!--  <span id="genpass">Сгенерировать</span> -->
                    </li>

                    <li>
                        <label>Фамилия</label>
                        <span class="star">*</span>
                        <input type="text" name="reg_surname" id="reg_surname"/>
                    </li>

                    <li>
                        <label>Имя</label>
                        <span class="star">*</span>
                        <input type="text" name="reg_name" id="reg_name"/>
                    </li>

                    <li>
                        <label>E-mail</label>
                        <span class="star">*</span>
                        <input type="text" name="reg_email" id="reg_email"/>
                    </li>

                    <li>
                        <label>Мобильный телефон</label>
                        <span class="star">*</span>
                        <input type="text" name="reg_phone" id="reg_phone"/>
                    </li>

                    <%--<li>--%>
                        <%--<div id="block-captcha">--%>
                            <%--<img src="/reg/regcaptcha"> &lt;%&ndash;файл генератор капчи&ndash;%&gt;--%>
                            <%--<input type="text" name="reg_captcha" id="reg_captcha"/>--%>
                            <%--<p id="reloadcaptcha">Обновить</p>--%>
                        <%--</div>--%>
                    <%--</li>--%>
                </ul>
            </div>
            <p align="right">
                <input type="submit" name="reg_submit" id="form_submit" value="Регистрация"/>
            </p>
        </form>
    </div>

    <jsp:include page="/include/block-footer.jsp" />

</div>
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.form.min.js"></script>
<script type="text/javascript" src="/js/jquery.validate.js"></script>
<script type="text/javascript" src="/js/reg.js"></script>
</body>
</html>
