<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilia_Stepanov
  Date: 18-Feb-19
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.userSettings"/></title>
    <link href="webjars/bootstrap/4.3.1/css/bootstrap-grid.min.css" rel="stylesheet">
    <p align="right"><spring:message code="lang.currentUser"/>  ${sessionUser.firstName}  <a href="/entry/log-out"> <spring:message code="lang.logOut"/></a><br/></p>
</head>
<body>

<a href="/tickets/self"><spring:message code="lang.backToCabinet"/></a><br/>

<spring:message code="lang.changePersonalData"/><br/>

<form action="/user/update" method="post">
    <input type="hidden" name="id" value="${sessionUser.id}"/>
    <input type="email" placeholder="${sessionUser.email}" name="email" /> <spring:message code="lang.newEmail"/> <br/>
    <input type="hidden" name="password" value="${sessionUser.password}"/>
    <input type="hidden" name="isAdmin" value="false"/>
    <input type="text" name="firstName" placeholder="${sessionUser.firstName}"/> <spring:message code="lang.newFirstName"/> <br/>
    <input type="text" name="lastName" placeholder="${sessionUser.lastName}"/> <spring:message code="lang.newLastName"/> <br/>
    <input type="text" name="documentInfo" placeholder="${sessionUser.documentInfo}"/> <spring:message code="lang.newDocument"/> <br/>
    <input type="datetime-local" name="birthday" placeholder="${sessionUser.birthday}"/> <spring:message code="lang.newDateBirth"/><br/>
    <input type="hidden" name="userUpdate" value="fromUser"/>
    <input type="submit" value="ОК"/>
</form>


Change password.
<form action="/user/change-password" method="post">
    <input type="password" name="oldPassword"> <spring:message code="lang.oldPassword"/> <br/>
    <input type="password" name="newPassword"> <spring:message code="lang.newPassword"/><br/>
    <input type="password" name="newPassword2"> <spring:message code="lang.repeatPassword"/><br/>
    <input type="submit" value="OK">
</form>




<script src="webjars/jquery/3.3.1-2/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
