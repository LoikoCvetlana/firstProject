<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<%@include file="header.jsp" %>
<div>
    <c:forEach items="${requestScope.products}" var="product">
        <br>
        <img src="${product.picture}" width="120"><br>
        <a href="${pageContext.request.contextPath}/product-info?id=${product.id}"> ${product.name} ${product.article}</a><br>
        <br>
    </c:forEach>

</div>
</body>
</html>