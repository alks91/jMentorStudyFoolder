<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=windows-1251" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<ul>
    <li>ID: <c:out value="${requestScope.user.id}"/></li>

    <form action="${pageContext.request.contextPath}/update" method="post">
        <li> New Name: <input type="text" name="email" value="<c:out value="${requestScope.user.email}"/>"> </li>
        <input type="number" hidden name="id" value="${user.id}"/>
        <input type="submit" value="Update">
    </form>


</ul>
</body>
</html>
