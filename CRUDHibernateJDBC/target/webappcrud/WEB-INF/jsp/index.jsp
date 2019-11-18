<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=windows-1251" language="java" %>

<html>
<head>
    <title>Title</title>

</head>
<body>
<h1> Welcome CRUDApp Java! </h1>

<h2> Users </h2><br />

<c:forEach var="user" items="${requestScope.use}">
<ul>
    <li>ID: <c:out value="${user.id}"/></li>
    <li>Name: <c:out value="${user.name}"/></li>
    <li>Email: <c:out value="${user.email}"/></li>

    <form action="${pageContext.request.contextPath}/delete" method="post">
            <input type="number" hidden name="id" value="${user.id}"/>
            <input type="submit" value="Delete"/>
    </form>
    <form action="${pageContext.request.contextPath}/update" method="get">
        <input type="number" hidden name="id" value="${user.id}"/>
        <input type="submit" value="Update"/>
    </form>

</ul>
    <hr />
</c:forEach>





<form action="/" method="post">
    Name <input type="text" name="name">
    Email <input type="email" name="email">
    <input type="submit" name="Registration">
</form>

</body>
</html>
