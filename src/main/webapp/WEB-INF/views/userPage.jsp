<%@ page import="com.epam.lowcost.model.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Aboard ${sessionUser.firstName}</title>
    <jsp:include page="navigationPanel.jsp"/>
</head>
<link>
<h5>Here is all your tickets</h5>

<%
    User user = (User) session.getAttribute("sessionUser");
    if (user.isAdmin()){
        response.getWriter().write("<a href=\"/entry/admin-panel\"> Admin stuff.</a><br/>");
    }
%>

<p align="right">Current USER in Session: ${sessionUser.firstName}  <a href="/entry/log-out"> Log Out.</a><br/></p>

<c:forEach items="${currentUserTickets}" var="ticket">
  Ticket#  <c:out value="${ticket.id}"/><br/>
  Passenger  <c:out value="${ticket.user.firstName}"/><br/>
  Destination  <c:out value="${ticket.flight.arrivalAirport}"/><br/>
  Date <c:out value="${ticket.flight.departureDate}"/><br/>
    <input type="button" onclick="alert('Ticket# ${ticket.id} Flight# ${ticket.flight.id} From ${ticket.flight.departureAirport} At ${ticket.flight.departureDate} To ${ticket.flight.arrivalAirport} At ${ticket.flight.arrivalDate}')" value="Details."/>


</c:forEach>



<div align="right">

</div>


<a href="/flights/all">Buy more tickets</a><br/>

<a href="/user/settings">Change personal data.</a>

</body>
</html>
