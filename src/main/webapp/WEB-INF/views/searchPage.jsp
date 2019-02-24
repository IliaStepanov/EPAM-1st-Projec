<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@page import="java.time.LocalDateTime" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Anastasia
  Date: 14.02.2019
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.findFlight"/></title>
    <spring:url value="/resources/css/main.css" var="main_css" />
    <link href="${main_css}" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">

</head>
<body>
<div class="container">
    <div class="row BlockBachground">
        <div class="col-md-8">

            <p class="labelSeatchFlight"><spring:message code="lang.findFlight"/></p>

                <form action="<%=EndPoints.FLIGHTS + EndPoints.SEARCH%>" method="get">
                    <div class="leftBlockSerch">
                        <label for="inpSerc"><spring:message code="lang.departureDateFrom"/>:</label>
                        <input type="date" id="inpSerc" required name="departureDateFrom" class="form-control searchInput"/> <br/>
                        <label for="inpSerc2"><spring:message code="lang.departureDateTo"/>: </label>
                        <input type="date" id="inpSerc2" name="departureDateTo" class="form-control searchInput"/><br/>
                    </div>
                    <div class="leftBlockSerchRight">
                    <label for="inpSerc3"><spring:message code="lang.departureAirport"/>: </label>
                    <input type="text" id="inpSerc3" required list="airport" name="departureAirport" class="form-control searchInput"/> <br/>
                    <label for="inpSerc4"><spring:message code="lang.arrivalAirport"/>: </label>
                    <input type="text" id="inpSerc4" required list="airport" name="arrivalAirport" class="form-control searchInput"/>  <br/>
                    <input type="text" hidden name="adminPage" value="false"/>
                    </div>

        </div>
        <div class="col-md-4">
            <input type="submit" value="<spring:message code="lang.search"/>" class="btn btn-outline-warning btnSeach"/>

            </form>
        </div>
    </div>
</div>
<c:forEach items="${flights}" var="flight">
    <h3>



        <spring:message code="lang.from"/>: <c:out value="${flight.departureAirport.cityEng}"/><br/>
        <spring:message code="lang.to"/>: <c:out value="${flight.arrivalAirport.cityEng}"/><br/>
        <spring:message code="lang.departureDateFrom"/>: <c:out value="${flight.departureDate}"/><br/>
        <spring:message code="lang.arriveAt"/>: <c:out value="${flight.arrivalDate}"/><br/>
        <spring:message code="lang.price"/> <c:out value="${flight.initialPrice}"/><br/>



    </h3>
    <form action="<%=EndPoints.FLIGHTS + EndPoints.NEW_TICKET%>" method="get">
        <input type="hidden" name="id" value="${flight.id}"/>
        <input type="submit" value="<spring:message code="lang.buy"/>"/>

    </form>
</c:forEach>

<div id="content">

</div>

<datalist id="airport">
    <c:forEach items="${airports}" var="airport">
        <option  hidden value="${airport.code}">${airport.cityEng},${airport.countryEng} </option>
    </c:forEach>
</datalist>

</body>
</html>
