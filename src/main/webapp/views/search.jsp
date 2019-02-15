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
</head>
<body>
<c:forEach items="${flights}" var="flight">
    <c:out value="${flight.toString()}"/><br/>
    <input type="button" id="${flight.id}" value="BUY!"/> <br>

</c:forEach>


<h4>Search for flight.</h4>
<form action="${pageContext.request.contextPath}/flights/search" method="get">
    <input type="date" name="departureDate"/> Departure Date from.<br/>
    <input type="date" name="arrivalDate"/> Arrival Date to.<br/>
    <input type="text" name="departureAirport"/> Departure Airport. <br/>
    <input type="text" name="arrivalAirport"/> Arrival Airport. <br/>
    <input type="submit" value="Search Flight"/>
</form>
</body>
</html>
