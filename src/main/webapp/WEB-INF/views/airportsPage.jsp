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
    <spring:url value="/resources/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-3 airportTitle">
        <spring:message code="lang.airports"/>
            <br/>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3 findAirportBtn">
        <spring:message code="lang.findAirportByCode"/>
        <form action="<%=EndPoints.AIRPORT%>" method="get">
            <input class="form-control input codeInputAirports" type="text" name="code"/>
            <input type="submit" class="btn btn-outline-primary okBtnAirports" name="OK"/>
        </form>
        </div>

    </div>
    <div class="row">
        <div class="col-md-12  mainContentAirport">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="lang.airportCode"/></th>
                    <th scope="col"><spring:message code="lang.cityEng"/></th>
                    <th scope="col"><spring:message code="lang.cityRus"/></th>
                    <th scope="col"><spring:message code="lang.nameEng"/></th>
                    <th scope="col"><spring:message code="lang.nameRus"/></th>
                    <th scope="col"><spring:message code="lang.countryEng"/></th>
                    <th scope="col"><spring:message code="lang.countryRus"/></th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${airports}" var="airport">
                    <tr>
                        <td><c:out value="${airport.code}"/></td>
                        <td><c:out value="${airport.cityEng}"/></td>
                        <td><c:out value="${airport.cityRus}"/></td>
                        <td><c:out value="${airport.nameEng}"/></td>
                        <td><c:out value="${airport.nameRus}"/></td>
                        <td><c:out value="${airport.countryEng}"/></td>
                        <td><c:out value="${airport.countryRus}"/></td>


                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </div>

    <br/>
    ${airport}<br/> <h4>${message}</h4>

    <%--<br/><br/>--%>
    <%--<div class="row">--%>
        <%--<div class="col-md-3 addAirport">--%>
            <%--<h4><spring:message code="lang.addNewAirport"/></h4><br/>--%>
            <%--<form action="<%=EndPoints.AIRPORT + EndPoints.ADD%>" method="post">--%>
                <%--<spring:message code="lang.airportCode"/><br/> <input type="text" class="form-control input" required--%>
                                                                      <%--name="code"/>--%>
                <%--<spring:message code="lang.cityEng"/><br/><input type="text" class="form-control input" required--%>
                                                                 <%--name="cityEng"/>--%>
                <%--<spring:message code="lang.cityRus"/><br/><input type="text" class="form-control input" required--%>
                                                                 <%--name="cityRus"/>--%>
                <%--<spring:message code="lang.nameEng"/><br/><input type="text" class="form-control input" required--%>
                                                                 <%--name="nameEng"/>--%>
                <%--<spring:message code="lang.nameRus"/><br/><input type="text" class="form-control input" required--%>
                                                                 <%--name="nameRus"/>--%>
                <%--<spring:message code="lang.countryEng"/><br/><input type="text" class="form-control input" required--%>
                                                                    <%--name="countryEng"/>--%>
                <%--<spring:message code="lang.countryRus"/><br/><input type="text" class="form-control input" required--%>
                                                                    <%--name="countryRus"/>--%>

                <%--<input type="submit" class="btn btn-outline-success addAirportBtn" value="OK"/>--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<br/><br/>--%>
    <%--<div class="row">--%>
        <%--<div class="col-md-3 updateAirportBtn">--%>
            <%--<h4><spring:message code="lang.updateAirport"/></h4>--%>
            <%--<form action="<%=EndPoints.AIRPORT + EndPoints.UPDATE%>" method="post">--%>
                <%--<spring:message code="lang.airportCode"/><br/><input type="text" class="form-control input" name="code" value="${airport.code}"/>--%>
                <%--<spring:message code="lang.cityEng"/><br/><input type="text" class="form-control input" required name="cityEng" value="${airport.cityEng}"/>--%>
                <%--<spring:message code="lang.cityRus"/><br/> <input type="text" class="form-control input" required name="cityRus" value="${airport.cityRus}"/>--%>
                <%--<spring:message code="lang.nameEng"/><br/><input type="text" class="form-control input" required name="nameEng" value="${airport.nameEng}"/>--%>
                <%--<spring:message code="lang.nameRus"/><br/><input type="text" class="form-control input" required name="nameRus" value="${airport.nameRus}"/>--%>
                <%--<spring:message code="lang.countryEng"/><br/><input type="text" class="form-control input" required name="countryEng" value="${airport.countryEng}"/>--%>
                <%--<spring:message code="lang.countryRus"/><br/><input type="text" class="form-control input" required name="countryRus" value="${airport.countryRus}"/>--%>
                <%--<input type="submit" class="btn btn-outline-primary updateAirportBtn" value="OK"/>--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>


</div>
</body>
</html>
