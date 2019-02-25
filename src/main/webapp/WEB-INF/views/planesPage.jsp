<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.planeDAO"/></title>
    <spring:url value="/resources/css/main.css" var="main_css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">
    <link href="${main_css}" rel="stylesheet">
</head>
<body>
<div class="container">
    <br/><br/>
    <a href="<%=EndPoints.PLANE + EndPoints.ALL%>"><spring:message code="lang.allPlanes"/></a><br/><br/>

    <div class="row">
        <div class="col-md-12">


            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="lang.planeId"/></th>
                    <th scope="col"><spring:message code="lang.model"/></th>
                    <th scope="col"><spring:message code="lang.numBusiness"/></th>
                    <th scope="col"><spring:message code="lang.numEconom"/></th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${planes}" var="plane">
                    <tr>
                        <td><c:out value="${plane.id}"/></td>
                        <td><c:out value="${plane.model}"/></td>
                        <td><c:out value="${plane.businessPlacesNumber}"/></td>
                        <td><c:out value="${plane.economPlacesNumber}"/></td>

                    </tr>
                </c:forEach>
                </tbody>

            </table>

        </div>
    </div>
    <br/>
    ${plane}<br/> <h4>${message}</h4>

    <div class="row">
        <div class="col-md-3 findPlaneBtn">


            <h4><spring:message code="lang.findPlaneById"/></h4>
            <form action="<%=EndPoints.PLANE%>" method="get">
                <input type="number" class="form-control input" name="id"/>
                <input type="submit" class="btn btn-outline-primary findPlaneBtn" name="OK"/>
            </form>
        </div>
    </div>

    <br/><br/>
    <div class="row">
        <div class="col-md-3">


            <h4><spring:message code="lang.addNewPlane"/></h4>
            <form action="<%=EndPoints.PLANE%>" method="post">
                <spring:message code="lang.model"/><br/> <input type="text" class="form-control input" name="model"/>
                <spring:message code="lang.numBusiness"/><br/> <input type="number" class="form-control input"
                                                                      name="businessPlacesNumber"/>
                <spring:message code="lang.numEconom"/><br/><input type="number" class="form-control input"
                                                                   name="economPlacesNumber"/>
                <input type="submit" class="btn btn-outline-primary addPlaneBtn" value="OK"/>
            </form>

        </div>
    </div>
    <br/><br/>

    <div class="row">
        <div class="col-md-3">


            <h4><spring:message code="lang.updatePlane"/></h4>
            <form action="<%=EndPoints.PLANE + EndPoints.UPDATE%>" method="post">
                <spring:message code="lang.planeId"/><br/><input type="text" class="form-control input" name="id"/>
                <spring:message code="lang.model"/><br/><input type="text" class="form-control input" name="model"/>
                <spring:message code="lang.numBusiness"/><br/> <input type="number" class="form-control input"
                                                                      name="businessPlacesNumber"/>
                <spring:message code="lang.numEconom"/><br/> <input type="number" class="form-control input"
                                                                    name="economPlacesNumber"/>
                <input type="submit" class="btn btn-outline-primary updatePlaneBtn" value="OK"/>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-md-3">
            <h4><spring:message code="lang.deletePlane"/></h4>
            <form action="<%=EndPoints.PLANE + EndPoints.DELETE%>" method="post">
                <spring:message code="lang.planeId"/><br/><input type="number" class="form-control input" name="id"/>
                <input type="submit" class="btn btn-outline-primary deletePlaneBtn" value="OK"/>
            </form>
        </div>
    </div>
</div>

</body>
</html>
