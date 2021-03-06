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
    <title>Big Deal </title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">
    
    <link href="css/reset.css" rel = "stylesheet" type="text/css" />   
    <link href="css/style.css" rel = "stylesheet" type="text/css" />

    <div id="css-view">
      <%--выбор css в зависимости от вида (список/сетка)--%>
    </div>
    <%--<link href="css/grid-view.css" rel = "stylesheet" type="text/css" />--%>
    
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/shop-script.js"></script>

  </head>
  <body>
  
  <div id = "block-body">
    
    <jsp:include page="/include/block-header.jsp" />
    <!-- Блок верхнего выбора -->

    <div id = "top-menu">

      <ul>
        <table><tr>
          <td><li><img src="/img/shop.png" /><a href="index.jsp">Главная</a></li></td>
          <td><li><img src="/img/new.png" /><a id="hotSort-new">Новинки</a></li></td>
          <td><li><img src="/img/best-seller.png" /><a id="hotSort-leader">Лидеры продаж</a></li></td>
          <td><li><img src="/img/sale.png" /><a id="hotSort-sale">Распродажа</a></li></td>
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
    
    <div id = "block-content">

      <!-- Блок сортировки-->
      <div id="block-sorting">
        <!--Навигационные Переходы-->
        <p id ="nav-breadcrumbs">
          <a href="index.jsp"> Главная страница</a> \ <span id = nav-span>Все товары</span>
        </p>
        <ul id ="options-list" >
          <li>Вид:</li>
          <li><input type="image" id = "choiceOfView-grid" src="/img/grid-ico.png" /></li>
          <li><input type="image" id = "choiceOfView-list" src="/img/list-ico.png" /></li>
               
               <!--Сортировка-->   
               
               <li>Сортировка:</li>
               <li><a id = "select-sort">Сортировка</a>
                 <ul id="sorting-list">
                   <li><a id="priceMin">Сначала дешёвые</a></li>
                   <li><a id="priceMax">Сначала дорогие</a></li>
                   <li><a id="new">Новинки</a></li>
                   <li><a id="alphabet">От А до Я</a></li>
                 </ul>
               </li>
        </ul>
      </div>
      
      <div id="devices-list">
        <%--Шаблон для товаров--%>
          
      </div>
      
    </div>
    <jsp:include page="/include/block-footer.jsp" />
  </div>
  </body>
</html>
