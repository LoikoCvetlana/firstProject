<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product save</title>
</head>
<body>
<%@include file="header.jsp" %>
<form action="${pageContext.request.contextPath}/rewiew-save" method="post">

    <label for="text">
        <textarea style="width:500px; height:150px;" name="text" id="text">Оставьте свой отзыв о нашей продукции тут</textarea>
    </label><br>
    <br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
