<%--
  Created by IntelliJ IDEA.
  User: SmiLeX
  Date: 26.04.2018
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div id = "block-category">
    <p class="header-title">
        Категории товаров
    </p>
    <!-- Список с категриями -->
    <ul>
        <!-- Телефоны -->
        <li class = "category-main"><a id="all-phones"><img src = "" />Телефоны</a></li>
        <!-- Список подкатегорий -->
        <ul class = "category-section">
            <li><a id="iOS-phone">iOS</a></li>
            <li><a id="Android-phone">Android</a></li>
        </ul>

        <!-- Ноутбуки -->
        <li class = "category-main"><a id="all-laptops"><img src = "" />Ноутбуки</a></li>
        <!-- Список подкатегорий -->
        <ul class = "category-section">
            <li><a id="Windows-laptop">Windows</a></li>
            <li><a id="MacOS-laptop">MacOS</a></li>
            <li><a id="Linux-laptop">Linux</a></li>
        </ul>

        <!--  -->
        <li class = "category-main"><a id="all-tablets"><img src = "" />Планшеты</a></li>

        <!-- Список подкатегорий -->
        <ul class = "category-section">
            <li><a id="Android-tablet">Android</a></li>
            <li><a id="iOS-tablet">iOS</a></li>
            <li><a id="Windows-tablet">Windows</a></li>
            
        </ul>
        
    </ul>
</div>
</html>
