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
    <title>User Settings</title>
    <link href="webjars/bootstrap/4.3.1/css/bootstrap-grid.min.css" rel="stylesheet">
    <p align="right">Current USER in Session: ${sessionUser.firstName}  <a href="/entry/log-out"> Log Out.</a><br/></p>
</head>
<body>

Change personal information.<br/>

<form action="/user/update" method="post">
    <input type="hidden" name="id" value="${sessionUser.id}"/>
    <input type="email" name="email" /> New Email. <br/>
    <input type="hidden" name="password" value="${sessionUser.password}"/>
    <input type="hidden" name="isAdmin" value="false"/>
    <input type="text" name="firstName"/> New First name. <br/>
    <input type="text" name="lastName"/> New Last name. <br/>
    <input type="text" name="documentInfo"/> New Document. <br/>
    <input type="datetime-local" name="birthday"/> New Birthday. <br/>
    <input type="hidden" name="userUpdate" value="fromUser"/>
    <input type="submit" value="UpdateUser"/>
</form>


Change password.
<%--<form action="/user/update" method="post">--%>
    <%--<input type="hidden" name="id" value="${sessionUser.id}"/>--%>
    <%--<input type="email" name="email" /> New Email<br/>--%>
    <%--<input type="password" name="password"/> New Password <br/>--%>
    <%--<input type="hidden" name="isAdmin" value="false"/>--%>
    <%--<input type="hidden" name="firstName" value="${sessionUser.firstName}"/>--%>
    <%--<input type="hidden" name="lastName" value="${sessionUser.lastName}"/>--%>
    <%--<input type="hidden" name="documentInfo" value="${sessionUser.documentInfo}"/>--%>
    <%--<input type="hidden" name="birthday" value="${sessionUser.birthday}"/>--%>
    <%--<input type="hidden" name="userUpdate" value="fromUser"/>--%>
    <%--<input type="submit" value="UpdateUser"/>--%>
<%--</form>--%>




<script src="webjars/jquery/3.3.1-2/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
