<%--
  Created by IntelliJ IDEA.
  User: mp3dr
  Date: 29.05.2018
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <div id = "block-parameter"></div>
    <p class="header-title">Поиск по параметрам</p>
    <p class="title-filter">Стоимость</p>
        <div id="block-input-price">
            <ul>
                <li><p>От</p></li>
                <li><input type="text" id="start-price" name="start_price" value="1000"/></li>
                <li><p>до</p></li>
                <li><input type="text" id="end-price" name="end_price" value="300000"/></li>
                <li><p>руб</p></li>
            </ul>
        </div>
        
        <div id="blocktrackbar"></div> <!--создать блок-->
        <p class="title-filter">Производители</p>
        <ul class="checkbox-brand">
            <li>                
                <input type="checkbox" id="checkApple"/>
                <label for="checkApple">Apple</label>
            </li>
            <li>
                <input type="checkbox" id="checkAcer"/>
                <label for="checkAcer">Acer</label>
            </li>
            <li>
                <input type="checkbox" id="checkAsus"/>
                <label for="checkAsus">Asus</label>
            </li>
            <li>
                <input type="checkbox" id="checkDell"/>
                <label for="checkDell">Dell</label>
            </li>
            <li>
                <input type="checkbox" id="checkHP"/>
                <label for="checkHP">HP</label>
            </li>
            <li>
                <input type="checkbox" id="checkLenovo"/>
                <label for="checkLenovo">Lenovo</label>
            </li>
            <li>
                <input type="checkbox" id="checkSamsung"/>
                <label for="checkSamsung">Samsung</label>
            </li>
            <li>
                <input type="checkbox" id="checkXiaomi"/>
                <label for="checkXiaomi">Xiaomi</label>
            </li>
        </ul>
        
        <div class="button-param-search"><input type="submit" name="submit" id="button-param-search" value="Найти"/></div>
</html>
