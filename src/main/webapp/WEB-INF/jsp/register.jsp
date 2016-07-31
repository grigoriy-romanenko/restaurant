<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link href="/restaurant/resources/css/bootstrap.css" rel="stylesheet">
    <script src="/restaurant/resources/js/bootstrap.js"></script>
    <script src="/restaurant/resources/js/jquery-3.1.0.js"></script>
</head>
<body>
    <%@include file="header.jsp"%>
    <form action="/restaurant/register" method="post">
        <label for="username">Username: </label>
        <input id="username" type="text" name="username" required/>
        <label for="password">Password: </label>
        <input id="password" type="password" name="password" required/>
        <label for="repeat">Repeat password: </label>
        <input id="repeat" type="password" required/>
        <input type="submit"/>
    </form>
</body>
</html>
