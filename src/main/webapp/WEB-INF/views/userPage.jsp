<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Aboard ${sessionUser.firstName}</title>
</head>
<body>
<h5>Here is all your tickets</h5>

<c:forEach items="${currentUserTickets}" var="ticket">
    <c:out value="${ticket.toString()}"/><br/>
</c:forEach>


</body>
</html>
