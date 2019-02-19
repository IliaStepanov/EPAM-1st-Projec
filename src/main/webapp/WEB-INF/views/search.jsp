<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <title><spring:message code="lang.findFlight"/></title>
</head>
<body>
<c:forEach items="${flights}" var="flight">

  <spring:message code="lang.flight"/> № <c:out value="${flight.id}"/><br/>
  <spring:message code="lang.from"/>: <c:out value="${flight.departureAirport}"/><br/>
  <spring:message code="lang.to"/>: <c:out value="${flight.arrivalAirport}"/><br/>
  <spring:message code="lang.at"/>: <c:out value="${flight.departureDate.toString()}"/><br/>
  <spring:message code="lang.arriveAt"/>:  <c:out value="${flight.arrivalDate.toString()}"/><br/>
    <input type="button" id="${flight.id}" value="<spring:message code="lang.buy"/>"/> <br>

</c:forEach>


<h4><spring:message code="lang.findFlight"/></h4>
<form action="${pageContext.request.contextPath}/flights/search" method="get">
    <input type="date" name="departureDateFrom"/> <spring:message code="lang.departureDateFrom"/>.<br/>
    <input type="date" name="departureDateTo"/> <spring:message code="lang.departureDateTo"/>.<br/>
    <input type="text" name="departureAirport"/> <spring:message code="lang.departureAirport"/>. <br/>
    <input type="text" name="arrivalAirport"/> <spring:message code="lang.arrivalAirport"/>. <br/>
    <input type="submit" value="<spring:message code="lang.search"/>"/>
</form>
</body>
</html>
