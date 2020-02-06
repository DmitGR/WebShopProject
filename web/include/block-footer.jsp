<%--
  Created by IntelliJ IDEA.
  User: SmiLeX
  Date: 24.04.2018
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!--  -->


    <!-- Нижняя часть  -->

<div id ="block-footer">
    
    <!-- Телефон и график поддержки -->
    
    <div id = "footer-phone">
        
        <h4> Служба поддержки </h4>
        <h3> 8 (800) 555-35-35 </h3>
        <p>Режим работы: <br/>
            Будние дни: c 9:00 до 20:00<br/>
            Суббота, Воскресенье - выходной</p>
    </div>

    <!-- Сервис и помощь -->

    <div class = "footer-list"> 
    <p>Сервис и помощь</p>
        
        <ul>
            <li><a href="">Как сделать заказ</a></li>
            <li><a href>Способ оплаты</a></li>
            <li><a href>Возврат товара</a></li>
            <li><a href>Публичная оферта</a></li>


        </ul>
        
    </div>

    <!-- О Комании -->

    <div class = "footer-list">
        <p>О Комании</p>

        <ul>
            <li><a href="">О нас</a></li>
            <li><a href="">Вакансии</a></li>
            <li><a href="">Партнёрам</a></li>
            <li><a href="">Контакты</a></li>


        </ul>

    </div>

    <!-- Навигация -->

    <div class = "footer-list">
        <p>Навигация</p>

        <ul>
            <li><a href="index.jsp" id="head-page">Главная страница</a></li>
            <li><a href="">Обратная связь</a></li>
            <br/>
            <p>Рассказать о сайте</p>

            <script type="text/javascript">(function() {
                if (window.pluso)if (typeof window.pluso.start == "function") return;
                if (window.ifpluso==undefined) { window.ifpluso = 1;
                    var d = document, s = d.createElement('script'), g = 'getElementsByTagName';
                    s.type = 'text/javascript'; s.charset='UTF-8'; s.async = true;
                    s.src = ('https:' == window.location.protocol ? 'https' : 'http')  + '://share.pluso.ru/pluso-like.js';
                    var h=d[g]('body')[0];
                    h.appendChild(s);
                }})();</script>
            <div class="pluso" data-background="#ebebeb" data-options="medium,square,line,horizontal,counter,theme=04" data-services="vkontakte,odnoklassniki,facebook,twitter,google,email"></div>
        </ul>

    </div>
    
</div>

</html>
