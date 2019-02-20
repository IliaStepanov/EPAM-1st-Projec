<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <jsp:include page="navigationPanel.jsp"/>
</head>
<body>
<h3>

    <form action="<%=EndPoints.TICKETS + EndPoints.ADD%>" method="post">

        <spring:message code="lang.firstName"/>
        <output name="firstName">${sessionUser.firstName}</output>
        <br/>
        <spring:message code="lang.lastName"/>
        <output name="lastName">${sessionUser.lastName}</output>
        <br/>
        <spring:message code="lang.document"/>
        <output name="Passport">${sessionUser.documentInfo}</output>
        <br/>
        <spring:message code="lang.birthday"/>
        <output name="Birthdate">${sessionUser.birthday}</output>
        <br/>
        <spring:message code="lang.from"/>
        <output name="from">${flight.departureAirport}</output>
        <br/>
        <spring:message code="lang.to"/>
        <output name="arrivaAirport">${flight.arrivalAirport}</output>
        <br/>
        <spring:message code="lang.departureAt"/>
        <output name="departureDate">${flight.departureDate}</output>
        <br/>
        <spring:message code="lang.arriveAt"/>
        <output name="arrivalDate">${flight.arrivalDate}</output>
        <br/>
        <spring:message code="lang.isBusiness"/>: <input type="checkbox" value="true" name="isBusiness"/> +
        <c:out value="${flight.businessPrice}"/> <br/>
        <spring:message code="lang.hasLuggage"/> <input type="checkbox" name="hasLuggage" value="true"/>+ <c:out
            value="${flight.placePriorityPrice}"/><br/>
        <spring:message code="lang.placePriority"/> <input type="checkbox" name="placePriority" value="true"/> + <c:out
            value="${flight.luggagePrice}"/><br/>
        <input type="hidden" name="flightId" value="${flight.id}"/>
        </br> <input type="submit" value="<spring:message code="lang.buy"/>"/>
    </form>


    <form action="<%=EndPoints.FLIGHTS + EndPoints.RETURN%>" method="get">
        </br> <input type="submit" value="<spring:message code="lang.cancel"/>"/>
    </form>


</h3>
</body>
</html>
