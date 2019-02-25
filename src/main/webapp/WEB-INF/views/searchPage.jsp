<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@page import="java.time.LocalDateTime" %>
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
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.findFlight"/></title>
    <style type="text/css">
        body {
            margin: 0;
        }

        #content {
            position: absolute;
        }

        #content {
            left: 500px; /* Расстояние от левого края */
            top: 100px;
        }
    </style>
</head>
<body>
<c:forEach items="${flights}" var="flight">
    <h3>



        <spring:message code="lang.from"/>: <c:out value="${flight.departureAirport.cityEng}"/><br/>
        <spring:message code="lang.to"/>: <c:out value="${flight.arrivalAirport.cityEng}"/><br/>
        <spring:message code="lang.departureDateFrom"/>: <c:out value="${flight.departureDate}"/><br/>
        <spring:message code="lang.arriveAt"/>: <c:out value="${flight.arrivalDate}"/><br/>
        <spring:message code="lang.price"/> <c:out value="${flight.initialPrice}"/><br/>
        <spring:message code="lang.price"/> <c:out value="${flight.plane.economPlacesNumber}"/><br/>
        <spring:message code="lang.price"/> <c:out value="${flight.plane.businessPlacesNumber}"/><br/>


    </h3>
    <form action="<%=EndPoints.FLIGHTS + EndPoints.NEW_TICKET%>" method="get">
        <input type="hidden" name="id" value="${flight.id}"/>
        <input type="submit" value="<spring:message code="lang.buy"/>"/>

    </form>
</c:forEach>

<div id="content">

    <h4><spring:message code="lang.findFlight"/></h4>
    <h3>
        <form action="<%=EndPoints.FLIGHTS + EndPoints.SEARCH%>" method="get">

            <input type="date" required name="departureDateFrom"/> <spring:message code="lang.departureDateFrom"/>.<br/>
            <input type="date"  name="departureDateTo"/> <spring:message code="lang.departureDateTo"/>.<br/>
            <input type="text" required list="airport" name="departureAirport"/> <spring:message code="lang.departureAirport"/>. <br/>
            <input type="text" required list="airport" name="arrivalAirport"/> <spring:message code="lang.arrivalAirport"/>. <br/>
            <input type="text" hidden name="adminPage" value="false"/>
            <input type="submit" value="<spring:message code="lang.search"/>"/>

        </form>
    </h3>
</div>

<datalist id="airport">
    <c:forEach items="${airports}" var="airport">
        <option  hidden value="${airport.code}">${airport.cityEng},${airport.countryEng} </option>
    </c:forEach>
</datalist>

</body>
</html>
