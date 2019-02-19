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
    <link href="webjars/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <p align="right">Current USER in Session: ${sessionUser.firstName} <a href="/entry/log-out"> Log Out.</a><br/></p>
</head>
<body>

<a href="/self">Back to my page.</a><br/>

Change personal information.<br/>

<form action="/user/update" method="post">
    <input type="hidden" name="id" value="${sessionUser.id}"/>
    <input type="email" placeholder="${sessionUser.email}" name="email"/> New Email. <br/>
    <input type="hidden" name="password" value="${sessionUser.password}"/>
    <input type="hidden" name="isAdmin" value="false"/>
    <input type="text" name="firstName" placeholder="${sessionUser.firstName}"/> New First name. <br/>
    <input type="text" name="lastName" placeholder="${sessionUser.lastName}"/> New Last name. <br/>
    <input type="text" name="documentInfo" placeholder="${sessionUser.documentInfo}"/> New Document. <br/>
    <input type="datetime-local" name="birthday" placeholder="${sessionUser.birthday}"/> New Birthday. <br/>
    <input type="hidden" name="userUpdate" value="fromUser"/>
    <input type="submit" value="UpdateUser"/>
</form>


Change password.
<form action="/user/change-password" method="post">
    <label>
        <input required type="password" name="oldPassword">
    </label> Old password <br/>
    <label>
        <input required type="password" name="newPassword">
    </label> New password <br/>
    <label>
        <input required type="password" name="newPassword2">
    </label> Repeat new password.<br/>
    <input type="submit" value="Submit">
</form>

${message}

<script src="webjars/jquery/3.3.1-2/jquery.min.js"></script>
<script src="webjars/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
