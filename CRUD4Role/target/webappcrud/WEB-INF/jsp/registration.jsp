<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div>
<form action="/registration" method="post">
    <input type="email" required placeholder="login@mail.ru" name="email"><br>
    <input type="password" required placeholder="password" name="password"><br>
    <br>
    <input type="submit" value="Регистрация">
</form>
</div>
</body>
</html>
