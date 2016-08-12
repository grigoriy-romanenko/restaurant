<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error</title>
        <link href="/restaurant/resources/lib/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
        <link href="/restaurant/resources/css/common.css" rel="stylesheet">
        <script src="/restaurant/resources/lib/jquery-3.1.0.js"></script>
        <script src="/restaurant/resources/lib/bootstrap-3.3.7/js/bootstrap.js"></script>
        <script src="/restaurant/resources/js/header.js"></script>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div>${exception.message}</div>
    </body>
</html>
