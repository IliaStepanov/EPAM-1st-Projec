<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.flightDAO"/></title>
</head>
<body>
<h2><spring:message code="lang.flights"/><br/></h2><br/><br/>


<a href="<%=EndPoints.FLIGHTS + EndPoints.ALL%>"> <spring:message code="lang.findFlight"/><br/></a>


<c:forEach items="${flights}" var="flight">
    <c:out value="${flight.toString()}"/><br/>
</c:forEach>


<br/>
${flight}<br/> <h4>${message}</h4>


<h4><spring:message code="lang.findFlightById"/><br/></h4>
<form action="<%=EndPoints.FLIGHTS%>" method="get">
    <input type="number" name="id"/>
    <input type="submit" name="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.addNewFlight"/><br/></h4>
<form action="<%=EndPoints.FLIGHTS%>" method="post">
    <input type="text" required name="initialPrice"/> <spring:message code="lang.price"/><br/>

    <input type="text" required name="planeId"/> <spring:message code="lang.planeId"/><br/>
    <input type="datetime-local" required name="departureDate"/> <spring:message code="lang.departureDateFrom"/><br/>
    <input type="datetime-local" required name="arrivalDate"/> <spring:message code="lang.arriveAt"/><br/>
    <input type="text" required name="departureAirport"/> <spring:message code="lang.departureAirport"/><br/>
    <input type="text" required name="arrivalAirport"/> <spring:message code="lang.arrivalAirport"/><br/> <br/>
    <input type="text" required name="placePriorityPrice"/> <spring:message code="lang.placePriorityPrice"/>.<br/>
    <input type="text" required name="businessPrice"/> <spring:message code="lang.businessPrice"/><br/>
    <input type="text" required name="luggagePrice"/> <spring:message code="lang.luggagePrice"/><br/>

    <input type="submit" value="OK"/>
</form>


<h4><spring:message code="lang.findFlight"/><br/></h4>
<form action="<%=EndPoints.FLIGHTS + EndPoints.SEARCH%>" method="get">
    <input type="text" name="departureDate"/> <spring:message code="lang.departureDateFrom"/><br/>
    <input type="text" name="departureAirport"/> <spring:message code="lang.departureAirport"/> <br/>
    <input type="text" name="arrivalAirport"/> <spring:message code="lang.arrivalAirport"/> <br/>

    <input type="submit" value="OK"/>
</form>


<br/><br/>
<h4><spring:message code="lang.updateFlight"/></h4>
<form action="<%=EndPoints.FLIGHTS + EndPoints.UPDATE%>" method="post">
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
<form action="<%=EndPoints.FLIGHTS + EndPoints.DELETE%>" method="post">
    <input type="text" name="id"/> <spring:message code="lang.flightId"/>.<br/>
    <input type="submit" value="OK"/>
</form>


</body>
</html>