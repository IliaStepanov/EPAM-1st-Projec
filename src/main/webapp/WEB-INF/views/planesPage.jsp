<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.planeDAO"/></title>
</head>
<body>
<h2><spring:message code="lang.planes"/></h2><br/><br/>

<br/><br/>
<a href="<%=EndPoints.PLANE + EndPoints.ALL+ EndPoints.FIRST_PAGE%>"><spring:message code="lang.allPlanes"/></a><br/><br/>
<div>
    <form action="<%=EndPoints.PLANE + EndPoints.PAGE%>" method="get">
        <input type="hidden" name="number" value="1"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.PLANE + EndPoints.ALL%>"/>
        <input type="submit" value="Show Planes by 1"/>
    </form>
    <form action="<%=EndPoints.PLANE + EndPoints.PAGE%>" method="get">
        <input type="hidden" name="number" value="3"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.PLANE + EndPoints.ALL%>"/>
        <input type="submit" value="Show Planes by 3"/>
    </form>
    <form action="<%=EndPoints.PLANE + EndPoints.PAGE%>" method="get">
        <input type="hidden" name="number" value="5"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.PLANE + EndPoints.ALL%>"/>
        <input type="submit" value="Show Planes by 5"/>
    </form>
    <form action="<%=EndPoints.PLANE + EndPoints.PAGE%>" method="get">
        <input type="hidden" name="number" value="20"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.PLANE + EndPoints.ALL%>"/>
        <input type="submit" value="Show Planes by 20"/>
    </form>
</div>
<h1>All Planes</h1>
<table border="12" width="70%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Model</th>
        <th>Business Places</th>
        <th>Econom Places</th>
        <th>Service</th>
    </tr>
    <c:forEach var="plane" items="${planes}">
        <tr>
            <td>${plane.id}</td>
            <td>${plane.model} </td>
            <td>${plane.businessPlacesNumber}</td>
            <td>${plane.economPlacesNumber}</td>
            <td><form action="<%=EndPoints.PLANE + EndPoints.DELETE%>" method="post">
                <input type="hidden" name="id" value="${plane.id}"/>
                <input type="submit" value="<spring:message code="lang.deletePlane"/>"/>
            </form></td>
        </tr>
    </c:forEach>

</table>
<a href="<%=EndPoints.PLANE + EndPoints.ALL%>/${pageId-1}">Previous</a>
<c:forEach var="page" begin="1" end="${pagesNum}">
    <a href="<%=EndPoints.PLANE + EndPoints.ALL%>/${page}">${page}</a>
</c:forEach>
<a href="<%=EndPoints.PLANE + EndPoints.ALL%>/${pageId+1}">Next</a>


<br/>
${plane}<br/> <h4>${message}</h4>


<h4><spring:message code="lang.findPlaneById"/></h4>
<form action="<%=EndPoints.PLANE%>" method="get">
    <input type="number" name="id"/>
    <input type="submit" name="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.addNewPlane"/></h4>
<form action="<%=EndPoints.PLANE%>" method="post">
    <input type="text" name="model"/> <spring:message code="lang.model"/><br/>
    <input type="number" name="businessPlacesNumber"/><spring:message code="lang.numBusiness"/><br/>
    <input type="number" name="economPlacesNumber"/> <spring:message code="lang.numEconom"/><br/>
    <input type="submit" value="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.updatePlane"/></h4>
<form action="<%=EndPoints.PLANE + EndPoints.UPDATE%>" method="post">
    <input type="text" name="id"/> <spring:message code="lang.planeId"/><br/>
    <input type="text" name="model"/> <spring:message code="lang.model"/><br/>
    <input type="number" name="businessPlacesNumber"/> <spring:message code="lang.numBusiness"/><br/>
    <input type="number" name="economPlacesNumber"/> <spring:message code="lang.numEconom"/><br/>
    <input type="submit" value="OK"/>
</form>

<h4><spring:message code="lang.deletePlane"/></h4>
<form action="<%=EndPoints.PLANE + EndPoints.DELETE%>" method="post">
    <input type="number" name="id"/> <spring:message code="lang.planeId"/><br/>
    <input type="submit" value="OK"/>
</form>


</body>
</html>
