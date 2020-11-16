<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

<div>
    <table border="1" bordercolor="E0FFFF" width="850" height="250"
           cellpadding="10">
        <tr>
            <th background="https://naviny.by/media/2014.01_w4/download/nok/08_NOK_press.JPG">
                <h1><span style="color: black; font-family: Monotype Corsiva; ">
                    ORAZ: одежда для спорта и фитнеса</span>
                </h1>
            </th>
        </tr>
    </table>
</div>
<div>
    <c:if test="${not empty sessionScope.currentUser}">
    </c:if>
    <c:if test="${sessionScope.currentUser.role eq 'ADMIN'}">

    </c:if>
    <a href="${pageContext.request.contextPath}/change-locale?language=ru_RU"
       role="button">Русский</a>
    <a href="${pageContext.request.contextPath}/change-locale?language=en_US"
       role="button">English</a>
    <br>
    <br>
    <p><a href="${pageContext.request.contextPath}/products?language=en_US" class="btn btn-outline-secondary" role="button">Продукция</a>
        <a href="${pageContext.request.contextPath}/material?language=en_US" class="btn btn-outline-secondary" role="button">Материалы</a>
        <a href="${pageContext.request.contextPath}/users?language=en_US" class="btn btn-outline-secondary" role="button">Пользователи</a>

    </p>
    <br>
</div>