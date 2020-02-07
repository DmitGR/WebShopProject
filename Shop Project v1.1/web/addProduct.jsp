<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление товара</title>
    <meta http-equiv="content-type" content="text/html; charset = utf-8">

    <link href="css/reset.css" rel = "stylesheet" type="text/css" />
    <link href="css/style.css" rel = "stylesheet" type="text/css" />

    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="/js/ajax-fileupload.js"></script>
    <script type="text/javascript" src="/js/auth-script.js"></script>
    <script type="text/javascript" src="/js/adding-product-script.js"></script>

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
        <h2 class="h2-title">Добавлеие товара</h2>
        <form method="POST" id="form_addProd" action = "#" enctype="multipart/form-data"> <%--сделать обработчик --%>
            <div id="block-form-addProd">
                <ul id = "form-addProd">
                    <img alt = "ImgPreview" src="\img\photo.png" id ="img-preview" width="250" height="425">
                    <li>
                        <label>Категория</label>
                    <select required id="category-select">
                        <option value=2>
                            Транспорт
                        </option>
                        <option value=1>
                            Бытовая Техника
                        </option>
                        <option value=3>
                            Электроника
                        </option>
                    </select>
                        <select required id="subcategory-select">

                        </select>
                    </li>
                    <li>
                        <label>Название</label>
                        <span class="star">*</span>
                        <input type="text" maxlength="30" required name="addProd_name" id="addProd_name"/>

                    </li>
                    <li>
                        <label>Описание</label>
                        <span class="star">*</span>
                        <textarea maxlength="500" required name="addProd_description" id="addProd-description"></textarea>
                    </li>
                    <li>
                        <label>Фото</label>
                        <input  type="file" required name="datafile" id="addProd-photo" onchange="PhotoPreview();"/>
                    </li>
                    <li>
                        <label>Цена</label>
                        <span class="star">*</span>
                        <input type="number" required name="addProd-price" id="addProd-price"/>
                    </li>
                    <li>
                        <label>Количество</label>
                        <span class="star">*</span>
                        <input type="number" required name="addProd-count" value="1" id="addProd-count"/>
                    </li>
                </ul>
            </div>
            <p align="right">
                <input type="submit" name="addProd_submit" class="form_submit" id="addProd-Btn" value="Добавить"/>
            </p>
        </form>
    </div>

    <jsp:include page="/include/block-footer.jsp" />

</div>
</body>
</html>
