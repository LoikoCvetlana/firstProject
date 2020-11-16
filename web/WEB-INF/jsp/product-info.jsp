<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product info</title>
</head>
<body>
<div>
    <%@include file="header.jsp"%>
    Наименование: ${requestScope.product.name} ${requestScope.product.article} <br>
    Стоимость за единицу: ${requestScope.product.value}<br>
    Материал изготовления: ${requestScope.product.materialName}<br>
    Фото: <img src="${requestScope.product.picture}" width="350">
</div>
</body>
</html>
