<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title><spring:message code="lang.updateFlight"/></title>
</head>
<body>
<jsp:include page="navigationPanel.jsp"/>
<h4>Update flight.</h4>


<form action="<%=EndPoints.FLIGHTS + EndPoints.UPDATE%>" method="post">
    <input type="hidden" name="id" value="${flight.id}"/>
    <input type="text" required name="planeId" value="${flight.plane.id}"/> <spring:message code="lang.planeId"/><br/>
    <input type="date" required name="departureDate" value="${flight.departureDate}"/> <spring:message
        code="lang.departureAt"/><br/>
    <input type="date" required name="arrivalDate" value="${flight.arrivalDate}"/> <spring:message
        code="lang.arriveAt"/>. <br/>
    <input type="text" required list="airport" name="departureAirport" value="${flight.departureAirport.code}"/>
    <spring:message
            code="lang.departureAirport"/> <br/>
    <input type="text" required list="airport" name="arrivalAirport" value="${flight.arrivalAirport.code}"/>
    <spring:message
            code="lang.arrivalAirport"/> <br/>
    <input type="text" required name="placePriorityPrice" value="${flight.placePriorityPrice}"/> <spring:message
        code="lang.placePriorityPrice"/><br/>
    <input type="text" required name="businessPrice" value="${flight.businessPrice}"/> <spring:message
        code="lang.businessPrice"/><br/>
    <input type="text" required name="luggagePrice" value="${flight.luggagePrice}"/> <spring:message
        code="lang.luggagePrice"/><br/>
    <input type="text" required name="initialPrice" value="${flight.initialPrice}"/> <spring:message code="lang.price"/>.<br/>
    <input type="submit" value="<spring:message code="lang.updateFlight"/>"/>
</form>

<datalist id="airport">
    <c:forEach items="${airports}" var="airport">
        <option hidden value="${airport.code}">${airport.cityEng},${airport.countryEng} </option>
    </c:forEach>
</datalist>

<%--<datalist id="planes">--%>

<%--</datalist>--%>
</body>
</html>
