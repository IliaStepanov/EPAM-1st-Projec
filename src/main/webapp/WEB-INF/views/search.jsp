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
            left: 500px; /* Расстояние от левого края */
            top: 0px;
        }
    </style>
</head>
<body>
<c:forEach items="${flights}" var="flight">
    <h3>


         <spring:message code="lang.from"/>: <c:out value="${flight.departureAirport}"/><br/>
        <spring:message code="lang.to"/>: <c:out value="${flight.arrivalAirport}"/><br/>
        <spring:message code="lang.departureDateFrom"/>: <c:out value="${flight.departureDate}"/><br/>
        <spring:message code="lang.arriveAt"/>: <c:out value="${flight.arrivalDate}"/><br/>
        Initial price <c:out value="${flight.initialPrice}"/><br/>


    </h3>
    <form action="${pageContext.request.contextPath}/flights/new-ticket" method="get">
        <input type="hidden" name="id" value="${flight.id}"/>
        <input type="submit" value="BUY!"/>

    </form>



    </form>
    <c:if test="${sessionUser.isAdmin()}">
        <form action="${pageContext.request.contextPath}/flights/delete" method="post">
            <input type="hidden" name="id" value="${flight.id}"/>
            <input type="submit" value="Delete!"/>
        </form>
        <form action="${pageContext.request.contextPath}/flights" method="get">
            <input type="hidden" name="id" value="${flight.id}"/>
            <input type="submit" value="Update!"/>
        </form>


    </c:if>
</c:forEach>
<c:if test="${sessionUser.isAdmin()}">

    <form action="${pageContext.request.contextPath}/flights/add" method="get">
        <input type="submit" value="Add Flight"/>
    </form>
</c:if>


<div id="content">

    <h4><spring:message code="lang.findFlight"/></h4>
    <h3>
        <form action="<%=EndPoints.FLIGHTS + EndPoints.SEARCH%>" method="get">

            <input type="date" required name="departureDateFrom"/> <spring:message code="lang.departureDateFrom"/>.<br/>
            <input type="date" required name="departureDateTo"/> <spring:message code="lang.departureDateTo"/>.<br/>
            <input type="text" required name="departureAirport"/> <spring:message code="lang.departureAirport"/>. <br/>
            <input type="text" required name="arrivalAirport"/> <spring:message code="lang.arrivalAirport"/>. <br/>

            <input type="submit" value="<spring:message code="lang.search"/>"/>

        </form>
    </h3>
</div>

</body>
</html>
