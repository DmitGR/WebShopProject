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
        <!-- Транспорт -->
        <li class = "category-main"><a id="transport-all"><img src = "" />Траснпорт</a></li>
        <!-- Список подкатегорий -->
        <ul class = "category-section">
            <li><a id="transport-auto">Автомобили</a></li>
            <li><a id="transport-motorcycle">Мотоциклы</a></li>
            <li><a id="transport-truck">Грузовики</a></li>
            <li><a id="transport-water">Водный транспорт</a></li>
        </ul>

        <!-- Бытовая Техника -->
        <li class = "category-main"><a id="appliances-all"><img src = "" />Бытовая Техника</a></li>
        <!-- Список подкатегорий -->
        <ul class = "category-section">
            <li><a id="appliances-tv">Телевизоры</a></li>
            <li><a id="appliances-fridge">Холодилькники</a></li>
            <li><a id="appliances-washer">Стиральные машины</a></li>
            <li><a id="appliances-microwave">Микроволновые печи</a></li>
            <li><a id="appliances-dishwasher">Посудомоечные машины</a></li>
            <li><a id="appliances-vacuum">Пылесосы</a></li>
        </ul>

        <!-- Электроника -->
        <li class = "category-main"><a id="electronic-all"><img src = "" />Электроника</a></li>
        <!-- Список подкатегорий -->
        <ul class = "category-section">
            <li><a id="electronic-phone">Смартфоны</a></li>
            <li><a id="electronic-tablet">Планшеты</a></li>
            <li><a id="electronic-notebook">Ноутбуки</a></li>
            <li><a id="electronic-camera">Фотоаппараты</a></li>
            <li><a id="electronic-video">Видеокамеры</a></li>
            <li><a id="electronic-audio">Акустика</a></li>
            <li><a id="electronic-player">Проигрыватели</a></li>
        </ul>
        
    </ul>
</div>
</html>
