<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>
<h1>Employees List</h1>
<table border="2" width="70%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstName}</td>
            <td>${user.email}</td>
        </tr>
    </c:forEach>
</table>
<br/>

<a href="/viewUser/${pageId-1}"><B></B>Previous</a>
<c:forEach var="page" begin="1" end="${pagesNum}">
    <a href="/viewUser/${page}">${page}</a>
</c:forEach>
    <a href="/viewUser/${pageId+1}">Next</a>
</body>
</html>
