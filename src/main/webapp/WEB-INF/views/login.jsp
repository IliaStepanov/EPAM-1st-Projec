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
    <title>Welcome to Login page.</title>
</head>
<body>

<h4>${message}</h4><a href="/login/registration">Sign in?</a>
<br/>

<h3>Enter login and password!</h3>
<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="email" placeholder="Login"/>
    <input type="text" name="password" placeholder="password"/>
    <input type="submit" name="Log In!"/>
</form>

</body>
</html>
