<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Plane DAO page</title>
</head>
<body>
<h2>Planes in DB:</h2><br/><br/>

<a href="${pageContext.request.contextPath}/plane/all">List all Planes!</a><br/><br/>


<c:forEach items="${planes}" var="plane">
    <c:out value="${plane.toString()}"/><br/>
</c:forEach>


<br/>
${plane}<br/> <h4>${message}</h4>


<h4>Find Plane by ID.</h4>
<form action="${pageContext.request.contextPath}/plane" method="get">
    <input type="number" name="id"/>
    <input type="submit" name="Find Plane."/>
</form>
<br/><br/>
<h4>Add new Plane.</h4>
<form action="${pageContext.request.contextPath}/plane" method="post">
    <input type="text" name="model"/>  Model.<br/>
    <input type="number" name="businessPlacesNumber"/> Number of business places.<br/>
    <input type="number" name="economPlacesNumber"/> Number of econom places.<br/>
    <input type="submit" value="AddPlane"/>
</form>
<br/><br/>
<h4>Update Plane.</h4>
<form action="${pageContext.request.contextPath}/plane/update" method="post">
    <input type="text" name="id"/> Plane ID.<br/>
    <input type="text" name="model"/> New Model.<br/>
    <input type="number" name="businessPlacesNumber"/> New Number of business places.<br/>
    <input type="number" name="economPlacesNumber"/> New Number of econom places.<br/>
    <input type="submit" value="UpdatePlane"/>
</form>

<h4>Delete Plane by ID.</h4>
<form action="${pageContext.request.contextPath}/plane/delete" method="post">
    <input type="number" name="id"/>
    <input type="submit" name="Delete User."/>
</form>


</body>
</html>
