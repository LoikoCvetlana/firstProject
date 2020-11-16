<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product save</title>
</head>
<body>
<%@include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/material-save" method="post">
    <label for="name">Название:
        <input type="text" name="name" id="name">
    </label><br>
    <br>
    <label for="description">
        <textarea style="width:500px; height:150px;" name="description" id="description">Описание</textarea>
    </label><br>
    <br>
    <input type="submit" value="SAVE">
</form>
</body>
</html>
