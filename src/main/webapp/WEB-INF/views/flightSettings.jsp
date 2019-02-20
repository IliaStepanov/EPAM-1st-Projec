<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 19.02.2019
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h4>Update flight.</h4>
<form action="${pageContext.request.contextPath}/flights/update" method="post">
    <input type="hidden" name="id" value="${flight.id}"/>
    <input type="text" required name="planeId" value="${flight.plane.id}"/> Plane id.<br/>
    <input type="text" required name="departureDate" value="${flight.departureDate}"/> Departure Date.<br/>
    <input type="text" required name="arrivalDate" value="${flight.arrivalDate}"/> Arrival Date. <br/>
    <input type="text" required name="departureAirport"value="${flight.departureAirport}" /> Departure Airport. <br/>
    <input type="text" required name="arrivalAirport"value="${flight.arrivalAirport}"/> Arrival Airport. <br/>
    <input type="text" required name="placePriorityPrice"value="${flight.placePriorityPrice}"/> place priority Price.<br/>
    <input type="text" required name="businessPrice"value="${flight.businessPrice}"/> Business class Price.<br/>
    <input type="text" required name="luggagePrice"value="${flight.luggagePrice}"/> Luggage Price.<br/>
    <input type="text" required name="initialPrice"value="${flight.initialPrice}"/> Price.<br/>
    <input type="submit" value="Update Flight"/>
</form>

<%--<datalist id="planes">--%>

<%--</datalist>--%>
</body>
</html>
