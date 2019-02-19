<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 14.02.2019
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search flight</title>
    <style type="text/css">
        body {
            margin: 0;
        }

        #content {
            position: absolute;
        }

        #content {
            left: 500px; /* Расстояние от левого края */
            top: 0px;
        }
    </style>
</head>
<body>
<c:forEach items="${flights}" var="flight">
    <h3>
        From: <c:out value="${flight.departureAirport}"/><br/>
        To: <c:out value="${flight.arrivalAirport}"/><br/>
        Date/time of departure: <c:out value="${flight.departureDate}"/><br/>
        Date/time of arrival: <c:out value="${flight.arrivalDate}"/><br/>
    </h3>
    <form action="${pageContext.request.contextPath}/tickets/add" method="post">
        <input type="hidden" name="flightId" value="${flight.id}"/>
        <input type="submit" value="BUY!"/>

    </form>
    <c:if test="${sessionUser.isAdmin()}">
        <form action="${pageContext.request.contextPath}/flights/delete" method="post">
            <input type="hidden" name="id" value="${flight.id}"/>
            <input type="submit" value="Delete!"/>
        </form>
        <form action="${pageContext.request.contextPath}/flights" method="get">
            <input type="hidden" name="id" value="${flight.id}"/>
            <input type="submit" value="Update!"/>
        </form>

    </c:if>
</c:forEach>
<c:if test="${sessionUser.isAdmin()}">

    <form action="${pageContext.request.contextPath}/flights/add" method="get">
        <input type="submit" value="Add Flight"/>
    </form>
</c:if>

<div id="content">
    <br/>
    <br/>
    <br/>
    <h4>Search for flight.</h4>
    <h3>

        <form action="${pageContext.request.contextPath}/flights/search" method="get">
            <input type="date" required name="departureDateFrom"/> Departure Date from.<br/>
            <input type="date" required name="departureDateTo"/> Departure Date to.<br/>
            <input type="text" required name="departureAirport"/> Departure Airport. <br/>
            <input type="text" required name="arrivalAirport"/> Arrival Airport. <br/>
            <input type="submit" value="Search Flight"/>
        </form>
    </h3>
</div>
</body>
</html>
