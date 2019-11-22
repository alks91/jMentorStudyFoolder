<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>

  Hello <c:out value="${email}"/> <br>

<form action="/logout" method="post">
    <input type="submit" value="Выйти">
</form>
</body>
</html>