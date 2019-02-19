<%@ page import="com.epam.lowcost.model.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.personalCabinet"/></title>
</head>

<div><spring:message code="lang.loginPageEntry"/><br/> ${sessionUser.firstName}</div>
<link>
<h5><spring:message code="lang.hereAllTickets"/></h5>

<%
    User user = (User) session.getAttribute("sessionUser");
    if (user.isAdmin()){
        response.getWriter().write("<a href=\"/entry/admin-panel\"> <spring:message code=\"lang.adminPage\"/></a><br/>");
    }
%>

<p align="right"><spring:message code="lang.currentUser"/> ${sessionUser.firstName}  <a href="/entry/log-out"> <spring:message code="lang.logOut"/></a><br/></p>

<c:forEach items="${currentUserTickets}" var="ticket">
  <spring:message code="lang.ticket"/>#  <c:out value="${ticket.id}"/><br/>
  <spring:message code="lang.passanger"/>  <c:out value="${ticket.user.firstName}"/><br/>
  <spring:message code="lang.destination"/>  <c:out value="${ticket.flight.arrivalAirport}"/><br/>
  <spring:message code="lang.date"/> <c:out value="${ticket.flight.departureDate}"/><br/>
    <input type="button" onclick="alert('Ticket# ${ticket.id} Flight# ${ticket.flight.id} From ${ticket.flight.departureAirport} At ${ticket.flight.departureDate} To ${ticket.flight.arrivalAirport} At ${ticket.flight.arrivalDate}')" value="<spring:message code="lang.details"/>"/>


</c:forEach>



<div align="right">

</div>


<a href="/flights/all"><spring:message code="lang.buyMoreTickets"/></a><br/>

<a href="/user/settings"><spring:message code="lang.changePersonalData"/></a>

</body>
</html>
