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
<form action="${pageContext.request.contextPath}/plane/id" method="post">
    <input type="number" name="id"/>
    <input type="submit" name="Find Plane."/>
</form>
<br/><br/>
<h4>Add new Plane.</h4>
<form action="${pageContext.request.contextPath}/plane/add" method="post">
    <input type="text" name="model"/>  Model.<br/>
    <input type="number" name="businessPlacesNumber"/> Number of business places.<br/>
    <input type="number" name="economPlacesNumber"/> Number of econom places.<br/>
    <input type="submit" value="AddPlane"/>
</form>
<br/><br/>
<%--<h4>Update User.</h4>--%>
<%--<form action="${pageContext.request.contextPath}/user/update" method="post">--%>
    <%--<input type="text" name="id"/> User ID.<br/>--%>
    <%--<input type="text" name="email"/> New Email.<br/>--%>
    <%--<input type="text" name="password"/> New Password.<br/>--%>
    <%--<input type="text" name="isAdmin"/> New Admin role.<br/>--%>
    <%--<input type="text" name="firstName"/> New First name. <br/>--%>
    <%--<input type="text" name="lastName"/> New Last name. <br/>--%>
    <%--<input type="text" name="documentInfo"/> New Document. <br/>--%>
    <%--<input type="text" name="birthday"/> New Birthday. <br/>--%>
    <%--<input type="submit" value="UpdateUser"/>--%>
<%--</form>--%>


</body>
</html>
