<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Error</title>
        <link href="/restaurant/resources/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
        <link href="/restaurant/resources/css/common.css" rel="stylesheet">
        <script src="/restaurant/resources/jquery/3.1.0/jquery.js"></script>
        <script src="/restaurant/resources/bootstrap/3.3.7/js/bootstrap.js"></script>
        <script src="/restaurant/resources/js/header.js"></script>
        <script src="/restaurant/resources/js/cart.js"></script>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div>Error message: ${exception.message}</div>
    </body>
</html>
