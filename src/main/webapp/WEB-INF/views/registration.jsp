<%--
  Created by IntelliJ IDEA.
  User: Ilia_Stepanov
  Date: 13-Feb-19
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Page.</title>
</head>
<body>
<h5>Fill form to register.</h5>

<form action="${pageContext.request.contextPath}/user" method="post">
    <input type="text" name="email"/> Email.<br/>
    <input type="text" name="password"/> Password.<br/>
    <input type="hidden" name="isAdmin" value="false"/>
    <input type="text" name="firstName"/> First name. <br/>
    <input type="text" name="lastName"/> Last name. <br/>
    <input type="text" name="documentInfo"/> Document. <br/>
    <input type="text" name="birthday"/> Birthday. <br/>
    <input type="submit" value="AddUser"/>
</form>


</body>
</html>
