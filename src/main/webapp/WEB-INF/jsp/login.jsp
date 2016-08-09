<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link href="/restaurant/resources/lib/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
        <script src="/restaurant/resources/lib/jquery-3.1.0.js"></script>
        <script src="/restaurant/resources/lib/bootstrap-3.3.7/js/bootstrap.js"></script>
        <script src="/restaurant/resources/js/header.js"></script>
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