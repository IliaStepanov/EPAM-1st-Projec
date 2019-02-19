<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Flight DAO page Current admin is ${sessionUser.firstName}</title>
</head>
<body>
<h2> Flight in DB:</h2><br/><br/>

<a href="${pageContext.request.contextPath}/flights/all"> Show all flights.</a>

<c:forEach items="${flights}" var="flight">
    <c:out value="${flight.toString()}"/><br/>
</c:forEach>


<br/>
${flight}<br/> <h4>${message}</h4>


<h4>Add new flight.</h4>
<form action="${pageContext.request.contextPath}/flights" method="post">
    <input type="text" required name="initialPrice"/> Price.<br/>
    <input type="text" required name="planeId"/> Plane id.<br/>
    <input type="datetime-local" required name="departureDate"/> Departure Date.<br/>
    <input type="datetime-local" required name="arrivalDate"/> Arrival Date. <br/>
    <input type="text" required name="departureAirport"/> Departure Airport. <br/>
    <input type="text" required name="arrivalAirport"/> Arrival Airport. <br/>
    <input type="submit" value="Add flight"/>
</form>




</body>
</html>