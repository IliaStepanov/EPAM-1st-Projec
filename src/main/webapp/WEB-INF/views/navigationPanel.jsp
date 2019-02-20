<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--<%@ taglib prefix="spring" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="webjars/bootstrap/4.3.1/css/bootstrap-grid.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #000540;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: #248baf;
        }

        .topnav a.active {
            background-color: #39af82;
            color: white;
        }
    </style>
</head>
<body>

<div class="topnav">
    <a class="active" href="/tickets/self"><spring:message code="lang.ticket"/></a>
    <a href="/user/settings"><spring:message code="lang.userSettings"/></a>
    <c:if test="${sessionUser.isAdmin()}"> <a href="/user/all"> <spring:message code="lang.userDAO"/></a></c:if>
    <c:if test="${sessionUser.isAdmin()}"> <a href="/plane/all"> <spring:message code="lang.planeDAO"/></a></c:if>
    <c:if test="${sessionUser.isAdmin()}"> <a href="/flights/all"> <spring:message code="lang.flightDAO"/></a></c:if>
    <c:if test="${sessionUser.isAdmin()}"> <a href="/tickets/all"> <spring:message code="lang.ticketDAO"/></a></c:if>
    <a href="/entry/log-out"><spring:message code="lang.logOut"/></a>
</div>

</body>
</html>

