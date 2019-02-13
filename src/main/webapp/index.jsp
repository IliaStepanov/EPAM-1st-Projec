<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Title of web app!</title>
</head>
<body>
<h1>Login page.</h1>


<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="login" placeholder="Login"/>
    <input type="text" name="password" placeholder="password"/>
    <input type="submit" name="Log In!"/>
</form>
</body>
</html>
