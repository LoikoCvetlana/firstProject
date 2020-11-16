<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<%@include file="header.jsp"%>
<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="e-mail">Username:
        <input id="e-mail" type="text" name="e-mail" value="${param.username}">
    </label><br>
    <label for="password">Password:
        <input id="password" type="password" name="password">
    </label><br>
    <input type="submit" value="Login">
</form>
</body>
</html>