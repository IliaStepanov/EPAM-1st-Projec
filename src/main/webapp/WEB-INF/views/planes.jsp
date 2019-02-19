<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.planeDAO"/> </title>
</head>
<body>
<spring:message code="lang.adminPage"/> <spring:message code="lang.currentUser"/> ${sessionUser.firstName}
<h2><spring:message code="lang.planes"/></h2><br/><br/>

<a href="${pageContext.request.contextPath}/views/admin.jsp"><spring:message code="lang.adminPage"/></a>

<br/><br/>
<a href="${pageContext.request.contextPath}/plane/all"><spring:message code="lang.allPlanes"/></a><br/><br/>

<c:forEach items="${planes}" var="plane">
    <c:out value="${plane.toString()}"/><br/>
</c:forEach>


<br/>
${plane}<br/> <h4>${message}</h4>


<h4><spring:message code="lang.findPlaneById"/></h4>
<form action="${pageContext.request.contextPath}/plane" method="get">
    <input type="number" name="id"/>
    <input type="submit" name="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.addNewPlane"/></h4>
<form action="${pageContext.request.contextPath}/plane" method="post">
    <input type="text" name="model"/> <spring:message code="lang.model"/><br/>
    <input type="number" name="businessPlacesNumber"/><spring:message code="lang.numBusiness"/><br/>
    <input type="number" name="economPlacesNumber"/> <spring:message code="lang.numEconom"/><br/>
    <input type="submit" value="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.updatePlane"/></h4>
<form action="${pageContext.request.contextPath}/plane/update" method="post">
    <input type="text" name="id"/> <spring:message code="lang.planeId"/><br/>
    <input type="text" name="model"/> <spring:message code="lang.model"/><br/>
    <input type="number" name="businessPlacesNumber"/> <spring:message code="lang.numBusiness"/><br/>
    <input type="number" name="economPlacesNumber"/> <spring:message code="lang.numEconom"/><br/>
    <input type="submit" value="OK"/>
</form>

<h4><spring:message code="lang.deletePlane"/></h4>
<form action="${pageContext.request.contextPath}/plane/delete" method="post">
    <input type="number" name="id"/> <spring:message code="lang.planeId"/><br/>
    <input type="submit" value="OK"/>
</form>


</body>
</html>
