<%@ page import="com.epam.lowcost.util.EndPoints" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="navigationPanel.jsp"/>
    <title><spring:message code="lang.userDAO"/></title>

</head>
<body>

<spring:message code="lang.adminPage"/>
<h2><spring:message code="lang.usersInDB"/></h2><br/><br/>


<br/><br/>
<a href="<%=EndPoints.USER + EndPoints.ALL + EndPoints.FIRST_PAGE%>"><spring:message code="lang.allUsers"/></a><br/><br/>

<div>
    <form action="<%=EndPoints.USER + EndPoints.SET_USERS_BY_PAGE%>" method="get">
        <input type="hidden" name="number" value="1"/>
        <input type="submit" value="Show Users by 1"/>
    </form>
    <form action="<%=EndPoints.USER + EndPoints.SET_USERS_BY_PAGE%>" method="get">
        <input type="hidden" name="number" value="3"/>
        <input type="submit" value="Show Users by 3"/>
    </form>
    <form action="<%=EndPoints.USER + EndPoints.SET_USERS_BY_PAGE%>" method="get">
        <input type="hidden" name="number" value="5"/>
        <input type="submit" value="Show Users by 5"/>
    </form>
    <form action="<%=EndPoints.USER + EndPoints.SET_USERS_BY_PAGE%>" method="get">
        <input type="hidden" name="number" value="20"/>
        <input type="submit" value="Show Users by 20"/>
    </form>
</div>

<h1>All Users</h1>
<table border="12" width="70%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Birthday</th>
        <th>Document</th>
        <th>Service</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName} </td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>${user.birthday}</td>
            <td>${user.documentInfo}</td>
            <td><form action="<%=EndPoints.USER + EndPoints.DELETE%>" method="post">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="submit" value="<spring:message code="lang.deleteUser"/>"/>
            </form></td>
        </tr>
    </c:forEach>

</table>
<br/>

<a href="<%=EndPoints.USER + EndPoints.ALL%>/${pageId-1}">Previous</a>
<c:forEach var="page" begin="1" end="${pagesNum}">
    <a href="<%=EndPoints.USER + EndPoints.ALL%>/${page}">${page}</a>
</c:forEach>
<a href="<%=EndPoints.USER + EndPoints.ALL%>/${pageId+1}">Next</a>


<br/>
<h4>${message}</h4>


<h4><spring:message code="lang.findUserById"/></h4>
<form action="<%=EndPoints.USER%>" method="get">
    <input type="number" name="id"/><spring:message code="lang.userID"/>
    <input type="submit" value="Find"/>
</form>
<br/><br/>
<%--<h4><spring:message code="lang.addNewUser"/></h4>--%>
<%--<form action="<%=EndPoints.USER%>" method="post">--%>
    <%--<input type="email" name="email"/> <spring:message code="lang.email"/><br/>--%>
    <%--<input type="password" name="password"/> <spring:message code="lang.password"/><br/>--%>
    <%--<input type="text" name="isAdmin"/> <spring:message code="lang.isAdmin"/><br/>--%>
    <%--<input type="text" name="firstName"/> <spring:message code="lang.firstName"/> <br/>--%>
    <%--<input type="text" name="lastName"/> <spring:message code="lang.lastName"/> <br/>--%>
    <%--<input type="text" name="documentInfo"/> <spring:message code="lang.document"/> <br/>--%>
    <%--<input type="datetime-local" name="birthday"/> <spring:message code="lang.birthday"/> <br/>--%>
    <%--<input type="submit" value="OK"/>--%>
<%--</form>--%>
<%--<br/><br/>--%>
<%--<h4><spring:message code="lang.updateUser"/></h4>--%>
<%--<form action="<%=EndPoints.USER + EndPoints.UPDATE%>" method="post">--%>
    <%--<input type="text" name="id"/> <spring:message code="lang.userID"/><br/>--%>
    <%--<input type="email" name="email"/> <spring:message code="lang.email"/><br/><br/>--%>
    <%--<input type="password" name="password"/> <spring:message code="lang.password"/><br/>--%>
    <%--<input type="text" name="isAdmin"/> <spring:message code="lang.isAdmin"/><br/>--%>
    <%--<input type="text" name="firstName"/> <spring:message code="lang.firstName"/> <br/>--%>
    <%--<input type="text" name="lastName"/> <spring:message code="lang.lastName"/> <br/>--%>
    <%--<input type="text" name="documentInfo"/> <spring:message code="lang.document"/> <br/>--%>
    <%--<input type="datetime-local" name="birthday"/> <spring:message code="lang.birthday"/> <br/>--%>
    <%--<input type="submit" value="OK"/>--%>
<%--</form>--%>

<%--<h4><spring:message code="lang.deleteUser"/> <br/></h4>--%>
<%--<form action="<%=EndPoints.USER + EndPoints.DELETE%>" method="post">--%>
    <%--<input type="number" name="id"/><spring:message code="lang.userID"/><br/>--%>
    <%--<input type="submit" name="OK"/>--%>
<%--</form>--%>

</body>
</html>
