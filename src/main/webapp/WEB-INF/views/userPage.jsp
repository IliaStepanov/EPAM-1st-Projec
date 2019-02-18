<%@ page import="com.epam.lowcost.model.User" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Aboard ${sessionUser.firstName}</title>
</head>
<body>
<h5>Here is all your tickets</h5>

<%
    User user = (User) session.getAttribute("sessionUser");
    if (user.isAdmin()){
        response.getWriter().write("<a href=\"/entry/admin-panel\"> Admin stuff.</a><br/>");
    }
%>



<p align="right">Current USER in Session: ${sessionUser.firstName}  <a href="/entry/log-out"> Log Out.</a><br/></p>

<c:forEach items="${currentUserTickets}" var="ticket">
    <c:out value="${ticket.toString()}"/><br/>
</c:forEach>


<a href="/flights/all">Buy more tickets</a>

</body>
</html>
