<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User info</title>
</head>
<body>
<div>
    <%@include file="header.jsp" %>

    <h1>${requestScope.user.name} ${requestScope.user.lastname}</h1> <br>
    Дата регистрации: ${requestScope.user.registrationDate}<br>
    Организация: ${requestScope.user.organization}<br>
    E-mail: ${requestScope.user.e_mail}<br>
    О себе: ${requestScope.user.otherInformation}
</div>
</body>
</html>
