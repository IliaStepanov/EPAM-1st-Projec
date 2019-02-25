<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.lowcost.util.EndPoints" %><%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 20.02.2019
  Time: 16:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.addNewFlight"/> </title>
</head>
<body>
<jsp:include page="navigationPanel.jsp"/>
<h4><spring:message code="lang.addNewFlight"/><br/></h4>
<form action="<%=EndPoints.FLIGHTS%>" method="post">
    <input type="text" required name="initialPrice"/> <spring:message code="lang.price"/><br/>

    <input type="text" required name="planeId"/> <spring:message code="lang.planeId"/><br/>
    <input type="date" required name="departureDate"/> <spring:message code="lang.departureDateFrom"/><br/>
    <input type="date" required name="arrivalDate"/> <spring:message code="lang.arriveAt"/><br/>
    <input type="text" required list="airport" name="departureAirport"/> <spring:message code="lang.departureAirport"/><br/>
    <input type="text" required list="airport" name="arrivalAirport"/> <spring:message code="lang.arrivalAirport"/><br/> <br/>
    <input type="text" required name="placePriorityPrice"/> <spring:message code="lang.placePriorityPrice"/>.<br/>
    <input type="text" required name="businessPrice"/> <spring:message code="lang.businessPrice"/><br/>
    <input type="text" required name="luggagePrice"/> <spring:message code="lang.luggagePrice"/><br/>

    <input type="submit" value="OK"/>
</form>

<datalist id="airport">
    <c:forEach items="${airports}" var="airport">
        <option  hidden value="${airport.code}">${airport.cityEng},${airport.countryEng} </option>
    </c:forEach>
</datalist>

</body>
</html>
