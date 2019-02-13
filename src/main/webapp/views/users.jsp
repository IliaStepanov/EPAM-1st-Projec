<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User DAO page</title>
</head>
<body>
<h2>Users in DB:</h2><br/><br/>
<a  href="${pageContext.request.contextPath}/views/admin.jsp">Admin page.</a>


<br/><br/>
<a href="${pageContext.request.contextPath}/user/all">List all Users!</a><br/><br/>

<c:forEach items="${users}" var="user">
    <c:out value="${user.toString()}"/><br/>
</c:forEach>


<br/>
${user}<br/> <h4>${message}</h4>


<h4>Find User by ID.</h4>
<form action="${pageContext.request.contextPath}/user" method="get">
    <input type="number" name="id"/>
    <input type="submit" name="Find User."/>
</form>
<br/><br/>
<h4>Add new User.</h4>
<form action="${pageContext.request.contextPath}/user" method="post">
    <input type="text" name="email"/> Email.<br/>
    <input type="text" name="password"/> Password.<br/>
    <input type="text" name="isAdmin"/> Admin role.<br/>
    <input type="text" name="firstName"/> First name. <br/>
    <input type="text" name="lastName"/> Last name. <br/>
    <input type="text" name="documentInfo"/> Document. <br/>
    <input type="text" name="birthday"/> Birthday. <br/>
    <input type="submit" value="AddUser"/>
</form>
<br/><br/>
<h4>Update User.</h4>
<form action="${pageContext.request.contextPath}/user/update" method="post">
    <input type="text" name="id"/> User ID.<br/>
    <input type="text" name="email"/> New Email.<br/>
    <input type="text" name="password"/> New Password.<br/>
    <input type="text" name="isAdmin"/> New Admin role.<br/>
    <input type="text" name="firstName"/> New First name. <br/>
    <input type="text" name="lastName"/> New Last name. <br/>
    <input type="text" name="documentInfo"/> New Document. <br/>
    <input type="text" name="birthday"/> New Birthday. <br/>
    <input type="submit" value="UpdateUser"/>
</form>

<h4>Delete User by ID.</h4>
<form action="${pageContext.request.contextPath}/user/delete" method="post">
    <input type="number" name="id"/>
    <input type="submit" name="Delete User."/>
</form>


</body>
</html>
