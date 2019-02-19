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
    <form action="${pageContext.request.contextPath}/tickets/update" method="post">
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
        <output name="from">${ticket.flight.departureAirport}</output>
        <br/>
        TO:
        <output name="arrivaAirport">${ticket.flight.arrivalAirport}</output>
        <br/>
        Departure Date:
        <output name="departureDate">${ticket.flight.departureDate}</output>
        <br/>
        Arrival Date:
        <output name="arrivalDate">${ticket.flight.arrivalDate}</output>
        <br/>
        Business class: <input type="checkbox" value="true" name="isBusiness"/><br/>
        Luggage: <input type="checkbox" name="hasLuggage" value="true"/><br/>
        Place choice: <input type="checkbox" name="placePriority" value="true"/><br/>
        <input type="hidden" name="ticketId" value="${ticket.id}"/>
        </br> <input type="submit" value="Buy ticket"/>

</form>
    <form action="${pageContext.request.contextPath}/tickets/delete" method="post">
        <input type="hidden" name="id" value="${ticket.id}"/>
        </br> <input type="submit" value="Cancel"/>
    </form>

</h3>
</body>
</html>
