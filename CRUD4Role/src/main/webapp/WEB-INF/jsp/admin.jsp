<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=windows-1251" language="java" %>

<html>
<head>
    <title>Title</title>

</head>
<body>
<h1> Welcome CRUDApp Java! </h1>

<h2> Users </h2><br/>

<c:forEach items="${requestScope.use}" var="user">
<ul>
    <li>ID: <c:out value="${user.id}"/></li>
    <li>Email: <c:out value="${user.email}"/></li>
    <li>Role: <c:out value="${user.role}"/></li>

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

<form action="/logout" method="post">
    <input type="submit" value="Выйти">
</form>


</body>
</html>
