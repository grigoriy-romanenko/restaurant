<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Registration</title>
        <link href="/restaurant/resources/lib/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
        <script src="/restaurant/resources/lib/jquery-3.1.0.js"></script>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <form class="form-horizontal" action="/restaurant/register" method="post">
            <div class="form-group">
                <label for="username" class="control-label col-md-2">Username:</label>
                <div class="col-md-2">
                    <input id="username" class="form-control" type="text" name="username" required/>
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="control-label col-md-2">Password:</label>
                <div class="col-md-2">
                    <input id="password" class="form-control" type="password" name="password" required/>
                </div>
            </div>
            <div class="form-group">
                <label for="repeat" class="control-label col-md-2">Repeat password:</label>
                <div class="col-md-2">
                    <input id="repeat" class="form-control" type="password" required/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-2 col-md-10">
                    <button type="submit" class="btn btn-default">Register</button>
                </div>
            </div>
        </form>
    </body>
</html>
