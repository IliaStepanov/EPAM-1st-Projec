<%@ page import="com.epam.lowcost.util.EndPoints" %>
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
            left: 1000px; /* Расстояние от левого края */
            top: 90px;
        }
    </style>
</head>
<body>

<h1>All Flights</h1>
<table border="12" width="70%" cellpadding="2">
    <tr>
        <th>Departure airport</th>
        <th>Arrival airport</th>
        <th>Departure date</th>
        <th>Arrival date</th>
        <th>Initial price</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="flight" items="${flights}">
        <tr>
            <td>${flight.departureAirport}</td>
            <td>${flight.arrivalAirport}</td>
            <td>${flight.departureDate}</td>
            <td>${flight.arrivalDate}</td>
            <td>${flight.initialPrice}</td>
            <td><form action="<%=EndPoints.FLIGHTS + EndPoints.NEW_TICKET%>" method="get">
                <input type="hidden" name="id" value="${flight.id}"/>
                <input type="submit" value="<spring:message code="lang.buy"/>"/>
            </form></td>
        </tr>
    </c:forEach>

</table>

<a href="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>/${pageId-1}">Previous</a>
<c:forEach var="page" begin="1" end="${pagesNum}">
    <a href="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>/${page}">${page}</a>
</c:forEach>
<a href="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>/${pageId+1}">Next</a>
<div>
    <form action="<%=EndPoints.FLIGHTS + EndPoints.SET_FLIGHT_BY_PAGE%>" method="get">
        <input type="hidden" name="number" value="3"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>"/>
        <input type="submit" value="Show Flights by 3"/>
    </form>
    <form action="<%=EndPoints.FLIGHTS + EndPoints.SET_FLIGHT_BY_PAGE%>" method="get">
        <input type="hidden" name="number" value="5"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>"/>
        <input type="submit" value="Show Flights by 5"/>
    </form>

</div>



<div id="content">

    <h4><spring:message code="lang.findFlight"/></h4>
    <h3>
        <form action="<%=EndPoints.FLIGHTS + EndPoints.SEARCH%>" method="get">

            <input type="date" required name="departureDateFrom"/> <spring:message code="lang.departureDateFrom"/>.<br/>
            <input type="date" required name="departureDateTo"/> <spring:message code="lang.departureDateTo"/>.<br/>
            <input type="text" required name="departureAirport"/> <spring:message code="lang.departureAirport"/>. <br/>
            <input type="text" required name="arrivalAirport"/> <spring:message code="lang.arrivalAirport"/>. <br/>
            <input type="text" hidden name="adminPage" value="false"/>
            <input type="submit" value="<spring:message code="lang.search"/>"/>

        </form>
    </h3>
</div>

</body>
</html>
