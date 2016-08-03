<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link href="/restaurant/resources/css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div>
            <form name="login" method="post" action="/restaurant/login">
                <div class="form-group col-md-2">
                    <input class="form-control" type="text" name="username" placeholder="username" required>
                </div>
                <div class="form-group col-md-2">
                    <input class="form-control" type="password" name="password" placeholder="password" required>
                </div>
                <button type="submit" class="btn btn-default">Login</button>
            </form>
        </div>
    </body>
</html>