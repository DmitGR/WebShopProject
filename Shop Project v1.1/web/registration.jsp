<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">

    <link href="css/reset.css" rel = "stylesheet" type="text/css" />
    <link href="css/style.css" rel = "stylesheet" type="text/css" />


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
        <h2 class="h2-title">Регистрация</h2>
        <form method="#" id="form_reg" action = "#"> <%--сделать обработчик регистрации--%>
            <p id="reg_message"></p>
            <div id="block-form-registration">
                <ul id = "form-registration">
                    <li>
                        <select id="type-user-select">
                            <option value= 0>
                                Покупатель
                            </option>
                            <option value= 1>
                                Продавец
                            </option>
                        </select>
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

<%--                    <li>
                        <label>Отчество</label>
                        <input type="text" name="reg_patronymic" id="reg_patronymic"/>
                    </li>--%>
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
                        <label>Повтроите пароль</label>
                        <span class="star">*</span>
                        <input type="password" name="reg_repeat_pass" id="reg_repeat_pass"/>
                    </li>

                    <li>
                        <label>E-mail</label>
                        <span class="star">*</span>
                        <input type="email" name="reg_email" id="reg_email"/>
                    </li>

                    <li>
                        <label>Мобильный телефон</label>
                        <span class="star">*</span>
                        <input type="tel" name="reg_phone" id="reg_phone"/>
                    </li>

                </ul>
            </div>
            <p align="right">
                <input type="submit" name="reg_submit" class="form_submit" value="Регистрация"/>
            </p>
        </form>
    </div>

    <jsp:include page="/include/block-footer.jsp" />

</div>

<!-- Скрипты для регистрации-->
<script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="/js/jquery.validate.js"></script>
<script type="text/javascript" src="/js/reg.js"></script>

</body>
</html>
