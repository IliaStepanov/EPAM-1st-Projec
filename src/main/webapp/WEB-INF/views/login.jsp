<%@ page import="static com.epam.lowcost.util.EndPoints.ENTRY" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Ilia_Stepanov
  Date: 13-Feb-19
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lang.loginPageEntry"/></title>
    <link href="webjars/bootstrap/4.3.1/css/bootstrap-grid.min.css" rel="stylesheet">
</head>
<body>
<div class="container">

    <h4>${message}</h4><br/><a href="/entry/registration"><spring:message code="lang.signIn"/></a>
    <br/>

    <h3><spring:message code="lang.loginIntroduction"/></h3>
    <form  action= "/entry"method="post">
        <input type="email" name="email" placeholder="<spring:message code="lang.login"/>"/>
        <input type="password" name="password" placeholder="<spring:message code="lang.password"/>"/>
        <input type="submit" value="<spring:message code="lang.logIn"/>"/>
    </form>


</div>
<script src="webjars/jquery/3.3.1-2/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
