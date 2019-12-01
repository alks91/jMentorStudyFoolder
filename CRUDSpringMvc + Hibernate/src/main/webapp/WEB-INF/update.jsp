<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=windows-1251" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<ul>
    <li>ID: ${user.id}</li>
    <li>Email: ${user.email}</li>
    <li>Name: ${user.name}</li>
    <br/>
    <hr/>

    <form action="${pageContext.request.contextPath}/update" method="post">
        <li> New Email: <input type="text" name="email"> </li>
        <li> New Name: <input type="text" name="name"> </li>
        <input type="number" hidden name="id" value="${user.id}"/>
        <input type="submit" value="Update">
    </form>


</ul>
</body>
</html>
