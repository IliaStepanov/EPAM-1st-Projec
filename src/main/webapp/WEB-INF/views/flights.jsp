<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.flightDAO"/></title>
</head>
<body>
<h2><spring:message code="lang.adminPage"/> <spring:message code="lang.currentUser"/> ${sessionUser.firstName}</h2>
<h2><spring:message code="lang.flights"/><br/></h2><br/><br/>

<%--<a href="${pageContext.request.contextPath}/flights/all">Show all Flights!</a><br/><br/>--%>
<a href="${pageContext.request.contextPath}/flights/all"> <spring:message code="lang.findFlight"/><br/></a>

<c:forEach items="${flights}" var="flight">
    <c:out value="${flight.toString()}"/><br/>
</c:forEach>


<br/>
${flight}<br/> <h4>${message}</h4>


<h4><spring:message code="lang.findFlightById"/><br/></h4>
<form action="${pageContext.request.contextPath}/flights" method="get">
    <input type="number" name="id"/>
    <input type="submit" name="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.addNewFlight"/><br/></h4>
<form action="${pageContext.request.contextPath}/flights" method="post">
    <input type="text" name="initialPrice"/> <spring:message code="lang.price"/><br/>
    <input type="text" name="planeId"/> <spring:message code="lang.planeId"/><br/>
    <input type="datetime-local" name="departureDate"/> <spring:message code="lang.departureDateFrom"/><br/>
    <input type="datetime-local" name="arrivalDate"/> <spring:message code="lang.arriveAt"/><br/>
    <input type="text" name="departureAirport"/> <spring:message code="lang.departureAirport"/><br/>
    <input type="text" name="arrivalAirport"/> <spring:message code="lang.arrivalAirport"/><br/> <br/>
    <input type="submit" value="OK"/>
</form>


<h4><spring:message code="lang.findFlight"/><br/></h4>
<form action="${pageContext.request.contextPath}/flights/search" method="get">
    <input type="text" name="departureDate"/> <spring:message code="lang.departureDateFrom"/><br/>
    <input type="text" name="departureAirport"/> <spring:message code="lang.departureAirport"/> <br/>
    <input type="text" name="arrivalAirport"/> <spring:message code="lang.arrivalAirport"/> <br/>
    <input type="submit" value="OK"/>
</form>


<br/><br/>
<h4><spring:message code="lang.updateFlight"/></h4>
<form action="${pageContext.request.contextPath}/flights/update" method="post">
    <input type="text" name="id"/> <spring:message code="lang.flightId"/><br/>
    <input type="text" name="initialPrice"/> <spring:message code="lang.price"/> <br/>
    <input type="text" name="planeId"/> <spring:message code="lang.planeId"/> <br/>
    <input type="text" name="departureDate"/> <spring:message code="lang.departureDateFrom"/><br/>
    <input type="text" name="arrivalDate"/> <spring:message code="lang.arriveAt"/> <br/>
    <input type="text" name="departureAirport"/> <spring:message code="lang.departureAirport"/> <br/>
    <input type="text" name="arrivalAirport"/> <spring:message code="lang.arrivalAirport"/> <br/>
    <input type="submit" value="OK"/>
</form>

<br/><br/>
<h4><spring:message code="lang.deleteFlight"/></h4>
<form action="${pageContext.request.contextPath}/flights/delete" method="post">
    <input type="text" name="id"/> <spring:message code="lang.flightId"/>.<br/>
    <input type="submit" value="OK"/>
</form>

</body>
</html>