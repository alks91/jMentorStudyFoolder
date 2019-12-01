<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>Users list!</h2>
<style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }
    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }
</style>



<table>
    <tr>
        <th>Email</th>
        <th>Name</th>
        <th>Action</th>
    </tr>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <td>${user.email}</td>
            <td>${user.name}</td>
            <td>
            <form action="/update" method="get">
                    <input type="number" hidden name="id" value="${user.id}"/>
                    <input type="submit" value="Update"/>
            </form>
            <form action="/delete" method="get">
                <input type="number" hidden name="id" value="${user.id}"/>
                <input type="submit" value="Delete "/>
            </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<form action="/register" method="post">
 Emile <input type="email" name="email"> </br>
 Name  <input type="text" name="name"> </br>
    <input type="submit" value="Регистрация">
</form>
</body>
</html>
