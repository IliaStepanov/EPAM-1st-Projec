<%@ page
        import="com.epam.lowcost.util.EndPoints" %><%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><spring:message code="lang.personalCabinet"/></title>
    <jsp:include page="navigationPanel.jsp"/>
</head>
<body>
<link>
<h1>All Your Tickets</h1>
<table border="12" width="90%" cellpadding="2">
    <tr>
        <th><spring:message code="lang.ticket"/></th>
        <th><spring:message code="lang.passanger"/></th>
        <th><spring:message code="lang.departureAirport"/></th>
        <th><spring:message code="lang.destination"/></th>
        <th><spring:message code="lang.hasLuggage"/></th>
        <th><spring:message code="lang.placePriority"/></th>
        <th><spring:message code="lang.date"/></th>
        <th><spring:message code="lang.price"/></th>
        <th>Actions</th>
    </tr>
    <c:forEach var="ticket" items="${currentUserTickets}">
        <tr>
            <td>${ticket.id}</td>
            <td>${ticket.user.firstName}</td>
            <td>${ticket.flight.departureAirport}</td>
            <td>${ticket.flight.arrivalAirport}</td>
            <td>${ticket.hasLuggage}</td>
            <td>${ticket.placePriority}</td>
            <td>${ticket.flight.departureDate}</td>
            <td>${ticket.price}</td>
            <td><input type="button"
                       onclick="alert('Ticket# ${ticket.id} Flight# ${ticket.flight.id} From ${ticket.flight.departureAirport} At ${ticket.flight.departureDate} To ${ticket.flight.arrivalAirport} At ${ticket.flight.arrivalDate}')"
                       value="<spring:message code="lang.details"/>"/>
                <br/>
                <form action="<%=EndPoints.TICKETS + EndPoints.CANCEL%>" method="post">
                    <input type="hidden" name="id" value="${ticket.id}"/>
                    <input type="submit" value="<spring:message code="lang.cancel"/>"/>

                </form></td>
        </tr>
    </c:forEach>

</table>



<%--<c:forEach items="${currentUserTickets}" var="ticket">--%>

    <%--<spring:message code="lang.ticket"/># <c:out value="${ticket.id}"/><br/>--%>
    <%--<spring:message code="lang.passanger"/> <c:out value="${ticket.user.firstName}"/><br/>--%>
    <%--<spring:message code="lang.departureAirport"/> <c:out value="${ticket.flight.departureAirport}"/><br/>--%>
    <%--<spring:message code="lang.destination"/> <c:out value="${ticket.flight.arrivalAirport}"/><br/>--%>
    <%--<spring:message code="lang.date"/> <c:out value="${ticket.flight.departureDate}"/><br/>--%>
    <%--<spring:message code="lang.price"/> <c:out value="${ticket.price}"/><br/>--%>
    <%--<input type="button"--%>
           <%--onclick="alert('Ticket# ${ticket.id} Flight# ${ticket.flight.id} From ${ticket.flight.departureAirport} At ${ticket.flight.departureDate} To ${ticket.flight.arrivalAirport} At ${ticket.flight.arrivalDate}')"--%>
           <%--value="<spring:message code="lang.details"/>"/>--%>
    <%--<br/>--%>
    <%--<form action="<%=EndPoints.TICKETS + EndPoints.CANCEL%>" method="post">--%>
        <%--<input type="hidden" name="id" value="${ticket.id}"/>--%>
        <%--<input type="submit" value="<spring:message code="lang.cancel"/>"/>--%>

    <%--</form>--%>

<%--</c:forEach>--%>


<a href="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT + EndPoints.FIRST_PAGE%>"><spring:message code="lang.buyMoreTickets"/></a><br/>


</body>
</html>
