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
    <div class="row">
        <div class="col-md-3 planeTitle">
            <spring:message code="lang.planes"/>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 addPlaneBtn">

            <form action="<%=EndPoints.PLANE + EndPoints.ADD%>" method="get">
                <input type="submit" class="btn btn-outline-primary addPlaneBtn" value="<spring:message code="lang.addNewPlane"/> "/>
            </form>

        </div>
    </div>
    <div class="row">
    <div class="col-md-10">

    </div>
    <div class="col-md-2 numOfUsers">
        <form></form>
        <form action="<%=EndPoints.PLANE + EndPoints.PAGE%>" method="get">
            <input type="hidden" name="number" value="3"/>

            <input type="hidden" name="fromPage" value="<%=EndPoints.PLANE + EndPoints.ALL%>"/>
            <input type="submit" class="btn btn-link numOfUsersBtn" value="3"/>
        </form>
        <form action="<%=EndPoints.PLANE + EndPoints.PAGE%>" method="get">
            <input type="hidden" name="number" value="5"/>
            <input type="hidden" name="fromPage" value="<%=EndPoints.PLANE + EndPoints.ALL%>"/>
            <input type="submit" class="btn btn-link numOfUsersBtn" value="5"/>
        </form>
        <form action="<%=EndPoints.PLANE+ EndPoints.PAGE%>" method="get">
            <input type="hidden" name="number" value="10"/>
            <input type="hidden" name="fromPage" value="<%=EndPoints.PLANE + EndPoints.ALL%>"/>
            <input type="submit" class="btn btn-link numOfUsersBtn" value="10"/>
        </form>
        <form action="<%=EndPoints.PLANE + EndPoints.PAGE%>" method="get">
            <input type="hidden" name="number" value="20"/>
            <input type="hidden" name="fromPage" value="<%=EndPoints.PLANE + EndPoints.ALL%>"/>
            <input type="submit" class="btn btn-link numOfUsersBtn" value="20"/>
        </form>
    </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="lang.model"/></th>
                    <th scope="col"><spring:message code="lang.numBusiness"/></th>
                    <th scope="col"><spring:message code="lang.numEconom"/></th>
                </tr>

                </thead>
                <tbody>
                <c:forEach items="${planes}" var="plane">
                    <tr>

                        <td><c:out value="${plane.model}"/></td>
                        <td><c:out value="${plane.businessPlacesNumber}"/></td>
                        <td><c:out value="${plane.economPlacesNumber}"/></td>


                    <td>
                    <c:if test="${sessionUser.isAdmin()}">
                    <form action="<%=EndPoints.PLANE%>" method="get">
                    <input type="hidden" name="id" value="${plane.id}"/>
                    <input type="submit" value="<spring:message code="lang.updatePlane"/>" class="btn btn-outline-primary updatePlaneBtn"/>
                    </form>
                    <form action="<%=EndPoints.PLANE + EndPoints.DELETE%>" method="post">
                    <input type="hidden" name="id" value="${plane.id}"/>
                    <input type="submit" value="<spring:message code="lang.deletePlane"/>" class="btn btn-outline-danger deletePlaneBtn"/>
                    </form>



                    </c:if>

                    </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
            <form action="<%=EndPoints.PLANE + EndPoints.ALL%>/${pageId-1}">
                <input type="submit" class="btn btn-link paginationBtn" value="<spring:message code="lang.previous"/>">
            </form>
            <c:forEach var="page" begin="1" end="${pagesNum}">
                <form action="<%=EndPoints.PLANE + EndPoints.ALL%>/${page}">
                    <input type="submit" class="btn btn-link paginationBtn" value="${page}">
                </form>
            </c:forEach>
            <form action="<%=EndPoints.PLANE + EndPoints.ALL%>/${pageId+1}">
                <input type="submit" class="btn btn-link paginationBtn" value="<spring:message code="lang.next"/>">
            </form>

        </div>
    </div>
    <br/>
    <br/> <h4>${message}</h4>


    <%--<div class="row">--%>
        <%--<div class="col-md-3">--%>




        <%--</div>--%>
    <%--</div>--%>
    <%--<br/><br/>--%>

    <%--<div class="row">--%>
        <%--<div class="col-md-3">--%>


            <%--<h4><spring:message code="lang.updatePlane"/></h4>--%>
            <%--<form action="<%=EndPoints.PLANE + EndPoints.UPDATE%>" method="post">--%>
                <%--<spring:message code="lang.planeId"/><br/><input type="text" class="form-control input" name="id"/>--%>
                <%--<spring:message code="lang.model"/><br/><input type="text" class="form-control input" name="model"/>--%>
                <%--<spring:message code="lang.numBusiness"/><br/> <input type="number" class="form-control input"--%>
                                                                      <%--name="businessPlacesNumber"/>--%>
                <%--<spring:message code="lang.numEconom"/><br/> <input type="number" class="form-control input"--%>
                                                                    <%--name="economPlacesNumber"/>--%>
                <%--<input type="submit" class="btn btn-outline-primary updatePlaneBtn" value="OK"/>--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<div class="row">--%>
        <%--<div class="col-md-3">--%>
            <%--<h4><spring:message code="lang.deletePlane"/></h4>--%>
            <%--<form action="<%=EndPoints.PLANE + EndPoints.DELETE%>" method="post">--%>
                <%--<spring:message code="lang.planeId"/><br/><input type="number" class="form-control input" name="id"/>--%>
                <%--<input type="submit" class="btn btn-outline-primary deletePlaneBtn" value="OK"/>--%>
            <%--</form>--%>
        <%--</div>--%>
    <%--</div>--%>
</div>

</body>
</html>
