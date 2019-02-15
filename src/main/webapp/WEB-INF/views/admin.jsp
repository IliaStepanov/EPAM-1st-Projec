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
    <title>Admin Page Current admin is ${sessionUser.firstName}</title>
</head>
<body>

<p align="right">Current USER in Session: ${sessionUser.firstName}  <a href="/login/log-out"> Log Out.</a><br/></p>

<a href="/user/all"> User CRUD page.</a><br/>
<a href="/plane/all"> Plane CRUD page.</a><br/>
<a href="/flights/all"> Flights CRUD page.</a><br/>
<a href="/tickets/all"> Tickets CRUD page.</a><br/>



</body>
</html>
