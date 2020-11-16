<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Materials</title>
</head>
<body>
<%@include file="header.jsp" %>
<div>
    <c:forEach items="${requestScope.rewiews}" var="rewiew">
        <p>
        <h5><a href="${pageContext.request.contextPath}/user-info?id=${user.userId}">
                ${requestScope.rewiew.userName} ${requestScope.rewiew.userLastname}</a><br>
           ${requestScope.rewiew.organization} </h5>
        ${requestScope.rewiew.text}</a><br>
        </p>
    </c:forEach>

</div>
</body>
</html>