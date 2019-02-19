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


<a href="${pageContext.request.contextPath}/flights/all"> <spring:message code="lang.findFlight"/><br/></a>


<c:forEach items="${flights}" var="flight">
    <c:out value="${flight.toString()}"/><br/>
</c:forEach>


<br/>
${flight}<br/> <h4>${message}</h4>


<h4><spring:message code="lang.addNewFlight"/><br/></h4>
<form action="${pageContext.request.contextPath}/flights" method="post">
    <input type="text" required name="initialPrice"/> <spring:message code="lang.price"/><br/>
    <input type="text" required name="planeId"/> <spring:message code="lang.planeId"/><br/>
    <input type="datetime-local" required name="departureDate"/> <spring:message code="lang.departureDateFrom"/><br/>
    <input type="datetime-local" required name="arrivalDate"/> <spring:message code="lang.arriveAt"/><br/>
    <input type="text" required name="departureAirport"/> <spring:message code="lang.departureAirport"/><br/>
    <input type="text" required name="arrivalAirport"/> <spring:message code="lang.arrivalAirport"/><br/> <br/>
    <input type="submit" value="OK"/>
</form>



</body>
</html>