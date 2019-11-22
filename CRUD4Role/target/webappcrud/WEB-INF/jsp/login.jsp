
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<div>
<form action="/" method="post">
    <input type="email" required placeholder="login@mail.ru" name="email"><br>
    <input type="password" required placeholder="password" name="password"><br>
    <br>
    <input type="submit" value="Войти">
</form>
</div>

    <tr>
        <td style="border: 1px solid black; text-align: center">
            <a href="/registration">Registration</a>
        </td>
    </tr>


</body>
</html>
