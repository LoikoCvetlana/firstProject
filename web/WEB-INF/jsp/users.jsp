<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%@include file="header.jsp" %>
<div>
    <c:forEach items="${requestScope.users}" var="users">
        <p>
            Роль:  ${users.role}<br>
        Дата регистрации: ${users.registrationDate}
        <a href="${pageContext.request.contextPath}/user-info?id=${users.id}"> ${users.name} ${users.lastname}</a><br>
        Организация: ${users.organizacion}
        </p>
    </c:forEach>

</div>
</body>
</html>