<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ticket DAO page Current admin is ${sessionUser.firstName}</title>
</head>
<body>
<h2>Tickets in DB:</h2><br/><br/>

<a href="${pageContext.request.contextPath}/tickets/all">List all Tickets!</a><br/><br/>


<c:forEach items="${tickets}" var="ticket">
    <c:out value="${ticket.toString()}"/><br/>
</c:forEach>


<br/>
${ticket}<br/> <h4>${message}</h4>


<h4>Find Ticket by User ID.</h4>
<form action="${pageContext.request.contextPath}/tickets" method="get">
    <input type="number" name="id"/>
    <input type="submit" name="Find Ticket."/>
</form>
<br/><br/>
<h4>Add new Ticket.</h4>
<form action="${pageContext.request.contextPath}/tickets" method="post">
    <input type="number" name="userId"/> userId.<br/>
    <input type="number" name="flightId"/> flightId.<br/>
    <input type="text" name="hasLuggage"/> hasLuggage.<br/>
    <input type="text" name="placePriority"/> placePriority. <br/>
    <input type="text" name="isBusiness"/> isBusiness. <br/>
    <input type="submit" value="AddTicket"/>
</form>
<br/><br/>
<h4>Update Ticket.</h4>
<form action="${pageContext.request.contextPath}/tickets/update" method="post">
    <input type="number" name="ticketId"/> ticketId.<br/>
    <input type="text" name="hasLuggage"/> hasLuggage.<br/>
    <input type="text" name="placePriority"/> placePriority. <br/>
    <input type="text" name="isBusiness"/> isBusiness. <br/>
    <input type="submit" value="UpdateTicket"/>
</form>

<h4>Delete Ticket by ID.</h4>
<form action="${pageContext.request.contextPath}/tickets/delete" method="post">
    <input type="number" name="id"/>
    <input type="submit" name="Delete Ticket."/>
</form>

</body>
</html>
