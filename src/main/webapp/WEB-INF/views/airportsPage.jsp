<%--
  Created by IntelliJ IDEA.
  User: Anastasia_Berlina
  Date: 2/22/2019
  Time: 1:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.airportDAO"/></title>
</head>
<body>
<h2><spring:message code="lang.airports"/></h2><br/><br/>

<br/><br/>
<a href="<%=EndPoints.AIRPORT + EndPoints.ALL%>"><spring:message code="lang.allAirports"/></a><br/><br/>


<a href="<%=EndPoints.AIRPORT + EndPoints.ALL + EndPoints.FIRST_PAGE%>"><spring:message code="lang.allPlanes"/></a><br/><br/>
<div>

    </form>
    <form action="<%=EndPoints.AIRPORT + EndPoints.PAGE%>" method="get">
        <input type="hidden" name="number" value="10"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.AIRPORT + EndPoints.ALL%>"/>
        <input type="submit" value="Show Airports by 10"/>
    </form>
    <form action="<%=EndPoints.AIRPORT + EndPoints.PAGE%>" method="get">
        <input type="hidden" name="number" value="20"/>
        <input type="hidden" name="fromPage" value="<%=EndPoints.AIRPORT + EndPoints.ALL%>"/>
        <input type="submit" value="Show Airports by 20"/>
    </form>
</div>
<h1>All Planes</h1>
<table border="12" width="70%" cellpadding="2">
    <tr>
        <th>Iata Code</th>
        <th>City Eng / City Rus</th>
        <th>Name Eng / Name Rus</th>
        <th>Country Eng / Country Rus</th>

    </tr>
    <c:forEach var="airport" items="${airports}">
        <tr>
            <td>${airport.code}</td>
            <td>${airport.cityEng} / ${airport.cityRus}</td>
            <td>${airport.nameEng} / ${airport.nameRus}</td>
            <td>${airport.countryEng} / ${airport.countryRus}</td>

        </tr>
    </c:forEach>

</table>
<a href="<%=EndPoints.AIRPORT + EndPoints.ALL%>/${pageId-1}">Previous</a>
<c:forEach var="page" begin="1" end="${pagesNum}">
    <a href="<%=EndPoints.AIRPORT + EndPoints.ALL%>/${page}">${page}</a>
</c:forEach>
<a href="<%=EndPoints.AIRPORT + EndPoints.ALL%>/${pageId+1}">Next</a>


<br/>
${airport}<br/> <h4>${message}</h4>


<h4><spring:message code="lang.findAirportByCode"/></h4>
<form action="<%=EndPoints.AIRPORT%>" method="get">
    <input type="text" name="code"/>
    <input type="submit" name="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.addNewAirport"/></h4>
<form action="<%=EndPoints.AIRPORT + EndPoints.ADD%>" method="post">
    <input type="text" required name="code"/> <spring:message code="lang.airportCode"/><br/>
    <input type="text" required name="cityEng"/> <spring:message code="lang.cityEng"/><br/>
    <input type="text" required name="cityRus"/> <spring:message code="lang.cityRus"/><br/>
    <input type="text" required name="nameEng"/> <spring:message code="lang.nameEng"/><br/>
    <input type="text" required name="nameRus"/> <spring:message code="lang.nameRus"/><br/>
    <input type="text" required name="countryEng"/> <spring:message code="lang.countryEng"/><br/>
    <input type="text" required name="countryRus"/> <spring:message code="lang.countryRus"/><br/>

    <input type="submit" value="OK"/>
</form>
<br/><br/>
<h4><spring:message code="lang.updateAirport"/></h4>
<form action="<%=EndPoints.AIRPORT + EndPoints.UPDATE%>" method="post">
    <input type="text"  name="code" value="${airport.code}"/> <spring:message code="lang.airportCode"/><br/>
    <input type="text" required name="cityEng" value="${airport.cityEng}"/> <spring:message code="lang.cityEng"/><br/>
    <input type="text" required name="cityRus" value="${airport.cityRus}"/> <spring:message code="lang.cityRus"/><br/>
    <input type="text" required name="nameEng" value="${airport.nameEng}"/> <spring:message code="lang.nameEng"/><br/>
    <input type="text" required name="nameRus" value="${airport.nameRus}"/> <spring:message code="lang.nameRus"/><br/>
    <input type="text" required name="countryEng" value="${airport.countryEng}"/> <spring:message code="lang.countryEng"/><br/>
    <input type="text" required name="countryRus" value="${airport.countryRus}"/> <spring:message code="lang.countryRus"/><br/>
    <input type="submit" value="OK"/>
</form>



</body>
</html>
