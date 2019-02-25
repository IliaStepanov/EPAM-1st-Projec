<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket DAO page </title>
    <jsp:include page="navigationPanel.jsp"/>
    <spring:url value="/resources/css/main.css" var="main_css" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>
<spring:message code="lang.adminPage"/>
<h2><spring:message code="lang.tickets"/></h2><br/><br/>

<a href="<%=EndPoints.TICKETS + EndPoints.ALL%>"><spring:message code="lang.allTickets"/></a><br/><br/>


<c:forEach items="${tickets}" var="ticket">
    <c:out value="${ticket.toString()}"/><br/>
</c:forEach>


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
