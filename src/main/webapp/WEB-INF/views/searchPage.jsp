<%@ page import="com.epam.lowcost.util.EndPoints" %>
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">


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
<div class="container mainSerchPage">
    <div class="row">
        <div class="col-md-10">

        </div>
        <div class="col-md-2 numOfUsers">
            <form></form>
            <form action="<%=EndPoints.FLIGHTS + EndPoints.PAGE%>" method="get">
                <input type="hidden" name="number" value="3"/>

                <input type="hidden" name="fromPage" value="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>"/>
                <input type="submit" class="btn btn-link numOfUsersBtn" value="3"/>
            </form>
            <form action="<%=EndPoints.FLIGHTS + EndPoints.PAGE%>" method="get">
                <input type="hidden" name="number" value="5"/>
                <input type="hidden" name="fromPage" value="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>"/>
                <input type="submit" class="btn btn-link numOfUsersBtn" value="5"/>
            </form>
            <form action="<%=EndPoints.FLIGHTS + EndPoints.PAGE%>" method="get">
                <input type="hidden" name="number" value="10"/>
                <input type="hidden" name="fromPage" value="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>"/>
                <input type="submit" class="btn btn-link numOfUsersBtn" value="10"/>
            </form>
            <form action="<%=EndPoints.FLIGHTS + EndPoints.PAGE%>" method="get">
                <input type="hidden" name="number" value="20"/>
                <input type="hidden" name="fromPage" value="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>"/>
                <input type="submit" class="btn btn-link numOfUsersBtn" value="20"/>
            </form>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="lang.from"/></th>
                    <th scope="col"><spring:message code="lang.to"/></th>
                    <th scope="col"><spring:message code="lang.departureDateFrom"/></th>
                    <th scope="col"> <spring:message code="lang.arriveAt"/></th>
                    <th scope="col">  <spring:message code="lang.price"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>

<c:forEach items="${flights}" var="flight">

<tr>


    <td><c:out value="${flight.departureAirport.cityEng}"/></td>
    <td> <c:out value="${flight.arrivalAirport.cityEng}"/></td>
    <td>   <c:out value="${flight.departureDate}"/></td>
    <td>  <c:out value="${flight.arrivalDate}"/></td>
    <td>  <c:out value="${flight.initialPrice}"/></td>
    <td>
        <form action="<%=EndPoints.FLIGHTS + EndPoints.NEW_TICKET%>" method="get">
            <input type="hidden" name="id" value="${flight.id}"/>
            <input type="submit" value="<spring:message code="lang.buy"/>" class="btn btn-outline-primary"/>

        </form>
    </td>
</tr>
</c:forEach>
                </tbody>
            </table>
            <form action="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>/${pageId-1}">
                <input type="text" hidden name="adminPage" value="true"/>
                <input type="submit" class="btn btn-link paginationBtn" value="<spring:message code="lang.previous"/>">
            </form>
            <c:forEach var="page" begin="1" end="${pagesNum}">
                <form action="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>/${page}">

                    <input type="submit" class="btn btn-link paginationBtn" value="${page}">
                    <input type="text" hidden name="adminPage" value="true"/>
                </form>
            </c:forEach>
            <form action="<%=EndPoints.FLIGHTS + EndPoints.FLIGHT%>/${pageId+1}">
                <input type="text" hidden name="adminPage" value="true"/>
                <input type="submit" class="btn btn-link paginationBtn" value="<spring:message code="lang.next"/>">
            </form>
        </div>
    </div>

</div>


<datalist id="airport">
    <c:forEach items="${airports}" var="airport">
        <option  hidden value="${airport.code}">${airport.cityEng},${airport.countryEng} </option>
    </c:forEach>
</datalist>

</body>
</html>
