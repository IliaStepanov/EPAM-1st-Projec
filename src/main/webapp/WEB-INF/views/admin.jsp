<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilia_Stepanov
  Date: 13-Feb-19
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.adminPage"/>${sessionUser.firstName}</title>
</head>
<body>

<p align="right"><spring:message code="lang.currentUser"/> ${sessionUser.firstName}  <a href="/entry/log-out"> <spring:message code="lang.logOut"/></a><br/></p>


<a href="/user/all"> <spring:message code="lang.userDAO"/></a><br/>
<a href="/plane/all"> <spring:message code="lang.planeDAO"/></a><br/>
<a href="/flights/all"> <spring:message code="lang.flightDAO"/></a><br/>
<a href="/tickets/all"> <spring:message code="lang.ticketDAO"/></a><br/>



</body>
</html>
