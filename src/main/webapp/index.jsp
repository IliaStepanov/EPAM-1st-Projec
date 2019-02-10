

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
  <head>
    <title>Title of web app!</title>
  </head>
  <body>
  <h1>Hello World!</h1>



  <form action="${pageContext.request.contextPath}/users/getById" method="POST">
    <input type="number" name="id"/> Enter UserID to show.
    <input type="submit" name="Search">
  </form>

  </body>
</html>
