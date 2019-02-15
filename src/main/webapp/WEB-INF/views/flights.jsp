<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Flight DAO page Current admin is ${sessionUser.firstName}</title>
</head>
<body>
<h2> Flight in DB:</h2><br/><br/>

<a href="${pageContext.request.contextPath}/flights/all">Show all Flights!</a><br/><br/>


<c:forEach items="${flights}" var="flight">
    <c:out value="${flight.toString()}"/><br/>
</c:forEach>


<br/>
${flight}<br/> <h4>${message}</h4>


<h4>Find flight by ID.</h4>
<form action="${pageContext.request.contextPath}/flights" method="get">
    <input type="number" name="id"/>
    <input type="submit" name="Find flight."/>
</form>
<br/><br/>
<h4>Add new flight.</h4>
<form action="${pageContext.request.contextPath}/flights" method="post">
    <input type="text" name="initialPrice"/> Price.<br/>
    <input type="text" name="planeId"/> Plane id.<br/>
    <input type="text" name="departureDate"/> Departure Date.<br/>
    <input type="text" name="arrivalDate"/> Arrival Date. <br/>
    <input type="submit" value="Add flight"/>
</form>
<br/><br/>
<h4>Update flight.</h4>
<form action="${pageContext.request.contextPath}/flights/update" method="post">
    <input type="text" name="id"/> Id.<br/>
    <input type="text" name="initialPrice"/> Price.<br/>
    <input type="text" name="planeId"/> Plane id.<br/>
    <input type="text" name="departureDate"/> Departure Date.<br/>
    <input type="text" name="arrivalDate"/> Arrival Date. <br/>
    <input type="submit" value="Update Flight"/>
</form>

<br/><br/>
<h4>Delete flight.</h4>
<form action="${pageContext.request.contextPath}/flights/delete" method="post">
    <input type="text" name="id"/> Id.<br/>
    <input type="submit" value="Delete Flight"/>
</form>

</body>
</html>
