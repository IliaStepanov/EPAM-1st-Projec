<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 15.02.2019
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>
    <form action="${pageContext.request.contextPath}/tickets/add" method="post">
        First Name:
        <output name="firstName">${sessionUser.firstName}</output>
        <br/>
        Last Name:
        <output name="lastName">${sessionUser.lastName}</output>
        <br/>
        Passport:
        <output name="Passport">${sessionUser.documentInfo}</output>
        <br/>
        Birthdate:
        <output name="Birthdate">${sessionUser.birthday}</output>
        <br/>
        From:
        <output name="from">${flight.departureAirport}</output>
        <br/>
        TO:
        <output name="arrivaAirport">${flight.arrivalAirport}</output>
        <br/>
        Departure Date:
        <output name="departureDate">${flight.departureDate}</output>
        <br/>
        Arrival Date:
        <output name="arrivalDate">${flight.arrivalDate}</output>
        <br/>
        Business class: <input type="checkbox" value="true" name="isBusiness"/> + <c:out value="${flight.businessPrice}"/> <br/>
        Luggage: <input type="checkbox" name="hasLuggage" value="true"/>+ <c:out value="${flight.placePriorityPrice}"/><br/>
        Place choice: <input type="checkbox" name="placePriority" value="true"/> + <c:out value="${flight.luggagePrice}"/><br/>
        <input type="hidden" name="flightId" value="${flight.id}"/>
        </br> <input type="submit" value="Buy ticket"/>

</form>
    <form action="${pageContext.request.contextPath}/flights/return" method="get">
        </br> <input type="submit" value="Cancel"/>
    </form>

</h3>
</body>
</html>
