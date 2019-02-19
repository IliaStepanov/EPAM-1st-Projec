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
    <jsp:include page="navigationPanel.jsp"/>
    <title>Search flight</title>
</head>
<body>
<c:forEach items="${flights}" var="flight">

   Flight № <c:out value="${flight.id}"/><br/>
  From:  <c:out value="${flight.departureAirport}"/><br/>
  To: <c:out value="${flight.arrivalAirport}"/><br/>
   At: <c:out value="${flight.departureDate.toString()}"/><br/>
  Arrive at:  <c:out value="${flight.arrivalDate.toString()}"/><br/>
    <input type="button" id="${flight.id}" value="BUY!"/> <br>

</c:forEach>


<h4>Search for flight.</h4>
<form action="${pageContext.request.contextPath}/flights/search" method="get">
    <input type="date" name="departureDateFrom"/> Departure Date From.<br/>
    <input type="date" name="departureDateTo"/> Departure Date To.<br/>
    <input type="text" name="departureAirport"/> Departure Airport. <br/>
    <input type="text" name="arrivalAirport"/> Arrival Airport. <br/>
    <input type="submit" value="Search Flight"/>
</form>
</body>
</html>
