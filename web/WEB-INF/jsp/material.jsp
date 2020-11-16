<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Materials</title>
</head>
<body>
<%@include file="header.jsp" %>
<div>
    <c:forEach items="${requestScope.materials}" var="material">
      <p>
       <h5>${material.name}</h5>
        ${material.description}</a><br>
      </p>
    </c:forEach>

</div>
</body>
</html>