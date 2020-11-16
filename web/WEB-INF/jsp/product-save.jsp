<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product save</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/product-save" method="post">
    <label for="name">Наименование:
        <input type="text" name="name" id="name">
    </label><br>
    <br>
    <label for="article">Артикль
        <input type="text" name="article" id="article">
    </label><br>
    <br>
    <label for="picture">Ссылка на фото
        <input type="text" name="picture" id="picture">
    </label><br>
    <br>
    <label for="value">Cтоимость за единицу:
        <input type="number" name="value" id="value">
    </label><br>
    <br>
       <select name="material" id="material">
        <c:forEach var="material" items="${requestScope.materials}">
            <option value="${material.id}">${material.name}</option>
        </c:forEach>
    </select><br>
    <br>
    <input type="submit" value="SAVE">
</form>
</body>
</html>
