<%--
  Created by IntelliJ IDEA.
  User: SmiLeX
  Date: 24.04.2018
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!--Основной верхний БЛОК-->
<div id = "block-header">
    <!--Верхний блок с навигацией-->
    <div id = "header-top-block">
        <!--Список навигации-->
        <ul id ="header-top-menu">
            <li>Ваш город - <span>Москва</span></li>
            <li><a href="aboutUs.jsp">О нас</a></li>
            <li><a href="contacts.jsp">Контакты</a></li>
        </ul>

        <!--Вход и Регистрация-->
        
        <p id = "reg-auth-title" align="right">
            <a class = "top-auth">Вход</a>
            <a href="registration.jsp">Регистрация</a>
        </p>
        
        <div id="block-top-auth">
            <form method="post">
                <ul id="input-email-pass">
                    <h3>Вход</h3>
                    <p id="message-auth">Неверный логин и (или) пароль</p>
                    <div id = "message-auth-center" > 
                        <li>
                            <input type="text" id="auth_login" placeholder="Логин или E-mail"/>
                        </li>
                        <li>
                            <input type="password" id="auth_pass" placeholder="Пароль"/>
                            <span id="button-pass-show-hide" class="pass-show"></span>
                        </li>
                    </div>
                    <ul id="list-auth">
                          <li>
                            <a id="remindpass" href="/passRecovery.jsp">Забыли пароль?</a>
                        </li>
                    </ul>
                    <p align="right" id="button-auth"><a>Вход</a></p>
                </ul>
            </form>
        </div>
        
        
    </div>
    
    <!--Сюда можно разделительную линию-->
    <hr class="horizontal-line" >

    <div id = "block-user">
        <ul>
            <li><img src="/img/userMINI.png"/> <a href='profile.jsp'>Профиль</a></li>
            <li><img src="/img/exit.png"/> <a id = "profile-exit" href='index.jsp'>Выход</a></li>

        </ul>
    </div>
    
    <!--Лого-->
    <img id = "img-logo" src="/img/BidDeal.png"/>
    
    <!--Информационный блок-->
    <div id = "info">
        <h3 align="right">8 (800) 555-35-35</h3>
        <p align="right">(с 8:00 до 22:00)</p>
        <img  src="/img/phone-call.png"/>

        <p align="right">Режим работы:</p>
        <p align="right">Будние дни: c 9:00 до 20:00</p>
        <p align="right">Суббота, Воскресенье - выходной</p>
        <img src="/img/daily-graphic.png">
    </div>
    
    <!--Блок Поиска-->

    <div id = "block-search">
        
        <!--Настроить метод поиска-->
            
            <input type="text" id = "input-search" autocomplete=false placeholder="Поиск среди лучших товаров">
            <input type="image" id = "button-search"  src="/img/search-button.png" />
        
    </div>
    
   
    
</div>
</html>
