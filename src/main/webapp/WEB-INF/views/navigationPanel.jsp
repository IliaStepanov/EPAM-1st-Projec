<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="webjars/bootstrap/4.3.1/css/bootstrap-grid.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>

<header class="languageAndAccaunt">
<div align="right" class="language" >
    <a href="?lang=en">Eng |</a><a href="?lang=ru">Rus</a>

</div>
    <div align="right" class="language" >
    <a href="<%=EndPoints.USER + EndPoints.SETTINGS%>"><spring:message code="lang.userSettings"/>  ${sessionUser.firstName} |</a>
    <a href="<%=EndPoints.ENTRY + EndPoints.LOG_OUT%>"><spring:message code="lang.logOut"/></a>
    </div>
    <div class="topnav">
        <a class=" navbarLink " href="<%=EndPoints.TICKETS + EndPoints.SELF%>"><spring:message code="lang.personalCabinet"/></a>

        <c:if test="${sessionUser.isAdmin()}"> |<a href="<%=EndPoints.USER + EndPoints.ALL + EndPoints.FIRST_PAGE%>" class="navbarLink"> <spring:message code="lang.users"/></a>|</c:if>
        <c:if test="${sessionUser.isAdmin()}"> <a href="<%=EndPoints.PLANE + EndPoints.ALL + EndPoints.FIRST_PAGE%>" class="navbarLink"> <spring:message code="lang.planes" /></a>|</c:if>
        <c:if test="${sessionUser.isAdmin()}"> <a href="<%=EndPoints.FLIGHTS + EndPoints.ALL + EndPoints.FIRST_PAGE%>" class="navbarLink"> <spring:message code="lang.flights"/></a>|</c:if>
        <c:if test="${sessionUser.isAdmin()}"> <a href="<%=EndPoints.TICKETS + EndPoints.ALL + EndPoints.FIRST_PAGE%>" class="navbarLink"> <spring:message code="lang.tickets"/></a>|</c:if>
        <c:if test="${sessionUser.isAdmin()}"> <a href="<%=EndPoints.AIRPORT + EndPoints.ALL + EndPoints.FIRST_PAGE%>" class="navbarLink"> <spring:message code="lang.airports"/></a>|</c:if>

    </div>
</header>



</body>
</html>

