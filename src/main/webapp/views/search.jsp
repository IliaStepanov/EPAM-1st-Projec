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
        body { margin: 0; }
        #content { position: absolute; }
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
<form action="${pageContext.request.contextPath}/tickets/buy" method="get">
    <input type="hidden" name="id" value="${flight.id}" />
    <input type="submit"  value="BUY!" />
</form>

</c:forEach>

<div id="content">
<h4>Search for flight.</h4>
    <h3>
<form action="${pageContext.request.contextPath}/flights/search" method="get">
    <input type="date" name="departureDateFrom"/> Departure Date from.<br/>
    <input type="date" name="departureDateTo"/> Departure Date to.<br/>
    <input type="text" name="departureAirport"/> Departure Airport. <br/>
    <input type="text" name="arrivalAirport"/> Arrival Airport. <br/>
    <input type="submit" value="Search Flight"/>
</form></h3></div>
</body>
</html>
