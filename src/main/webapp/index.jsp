<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Title of web app!</title>
</head>
<body>

<h1>Login page.</h1>

<c:redirect url="/entry"/>

<form action="/entry" method="post">
    <input type="text" name="email" placeholder="Login"/>
    <input type="password" name="password" placeholder="password"/>
    <input type="submit" name="Log In!"/>
</form>

</body>
</html>
