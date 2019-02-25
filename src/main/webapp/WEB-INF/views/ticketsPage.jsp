<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket DAO page </title>
    <jsp:include page="navigationPanel.jsp"/>
</head>
<body>
<spring:message code="lang.adminPage"/>
<h2><spring:message code="lang.tickets"/></h2><br/><br/>

<a href="<%=EndPoints.TICKETS + EndPoints.ALL + EndPoints.FIRST_PAGE%>"><spring:message code="lang.allTickets"/></a><br/><br/>


<div>
    <form action="<%=EndPoints.TICKETS + EndPoints.PAGE%>" method="get">
        <input type="hidden" name="number" value="1"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.TICKETS + EndPoints.ALL%>"/>
        <input type="submit" value="Show Tickets by 1"/>
    </form>
    <form action="<%=EndPoints.TICKETS + EndPoints.PAGE%>" method="get">
        <input type="hidden" name="number" value="3"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.TICKETS + EndPoints.ALL%>"/>
        <input type="submit" value="Show Tickets by 3"/>
    </form>
    <form action="<%=EndPoints.TICKETS + EndPoints.PAGE%>" method="get">
        <input type="hidden" name="number" value="5"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.TICKETS + EndPoints.ALL%>"/>
        <input type="submit" value="Show Tickets by 5"/>
    </form>
    <form action="<%=EndPoints.TICKETS + EndPoints.PAGE%>" method="get">
        <input type="hidden" name="number" value="20"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.TICKETS + EndPoints.ALL%>"/>
        <input type="submit" value="Show Tickets by 20"/>
    </form>
</div>

<h1>All Tickets</h1>
<table border="12" width="70%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Passenger</th>
        <th>Flight</th>
        <th>Class</th>
        <th>Luggage</th>
        <th>Priority place</th>
        <th>Price</th>
        <th>Purchase date</th>
        <th>Service</th>
    </tr>
    <c:forEach var="ticket" items="${tickets}">
        <tr>
            <td>${ticket.id}</td>
            <td>${ticket.user.firstName} </td>
            <td>From ${ticket.flight.departureAirport} to ${ticket.flight.arrivalAirport} at ${ticket.flight.departureDate}</td>
            <td>${ticket.business}</td>
            <td>${ticket.hasLuggage}</td>
            <td>${ticket.placePriority}</td>
            <td>${ticket.price}</td>
            <td>${ticket.purchaseDate}</td>
            <td><form action="<%=EndPoints.TICKETS + EndPoints.DELETE%>" method="post">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" value="<spring:message code="lang.deleteTicket"/>"/>
            </form></td>
        </tr>
    </c:forEach>

</table>
<br/>

<a href="<%=EndPoints.TICKETS + EndPoints.ALL%>/${pageId-1}">Previous</a>
<c:forEach var="page" begin="1" end="${pagesNum}">
    <a href="<%=EndPoints.TICKETS + EndPoints.ALL%>/${page}">${page}</a>
</c:forEach>
<a href="<%=EndPoints.TICKETS + EndPoints.ALL%>/${pageId+1}">Next</a>







<br/>
${ticket}<br/> <h4>${message}</h4>


<h4><spring:message code="lang.findTicketById"/></h4>
<form action="<%=EndPoints.TICKETS%>" method="get">
    <input type="number" name="id"/>
    <input type="submit" name="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.addNewTicket"/></h4>
<form action="<%=EndPoints.TICKETS%>" method="post">
    <input type="number" name="userId"/> <spring:message code="lang.userID"/><br/>
    <input type="number" name="flightId"/> <spring:message code="lang.flightId"/><br/>
    <input type="text" name="hasLuggage"/> <spring:message code="lang.hasLuggage"/><br/>
    <input type="text" name="placePriority"/> <spring:message code="lang.placePriority"/> <br/>
    <input type="text" name="isBusiness"/> <spring:message code="lang.isBusiness"/> <br/>
    <input type="submit" value="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.updateTicket"/></h4>
<form action="<%=EndPoints.TICKETS + EndPoints.UPDATE%>" method="post">
    <input type="number" name="ticketId"/> <spring:message code="lang.ticketId"/><br/>
    <input type="text" name="hasLuggage"/> <spring:message code="lang.hasLuggage"/><br/>
    <input type="text" name="placePriority"/> <spring:message code="lang.placePriority"/> <br/>
    <input type="text" name="isBusiness"/> <spring:message code="lang.isBusiness"/> <br/>
    <input type="submit" value="OK"/>
</form>

<h4><spring:message code="lang.deleteTicket"/></h4>
<form action="<%=EndPoints.TICKETS + EndPoints.DELETE%>" method="post">
    <input type="number" name="id"/><spring:message code="lang.ticketId"/><br/>
    <input type="submit" name="OK"/>
</form>

</body>
</html>
