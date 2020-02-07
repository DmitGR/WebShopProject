<%--
  Created by IntelliJ IDEA.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>◄Web Shop►</title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">
    
    <link href="css/reset.css" rel = "stylesheet" type="text/css" />   
    <link href="css/style.css" rel = "stylesheet" type="text/css" />

    <div id="css-view">

      <%--выбор css в зависимости от вида (список/сетка)--%>

        <link href='css/grid-view.css' rel = 'stylesheet' type='text/css' />
    </div>

    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/js/auth-script.js"></script>
    <script type="text/javascript" src="/js/shop-script.js"></script>



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

      <!-- Блок сортировки-->
      <div id="block-sorting">
        <!--Навигационные Переходы-->
        <p id ="nav-breadcrumbs">
          <a href="index.jsp"> Главная страница</a> \ <a href="index.jsp" id = nav-span>Все товары</a>
        </p>

        <ul class ="options-list" >
          <!--Сортировка-->
          <li><a id = "select-sort">Сортировка</a>
            <ul class ="sorting-list">
              <li><a id="sorting-name-Asc">По названию (А-Я)</a></li>
              <li><a id="sorting-name-Desc">По названию (Я-А)</a></li>
              <li><a id="sorting-price-Asc">Дороже</a></li>
              <li><a id="sorting-price-Desc">Дешевле</a></li>
              <li><a id="sorting-date-Asc">Новее</a></li>
              <li><a id="sorting-date-Desc">Старее</a></li>

            </ul>
          </li>
        </ul>
      </div>

        <div id="devices-list">

        <%--Шаблон для товаров--%>

        </div>

      <div id = 'pages'>

      <button id ='back-btn' class="form_submit">Назад</button>

      <button id ='forward-btn' class="form_submit">Вперёд</button>

      </div>



  </div>
      <jsp:include page="/include/block-footer.jsp" />
  </body>
</html>
