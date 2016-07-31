<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link href="/restaurant/resources/css/bootstrap.css" rel="stylesheet">
    <script src="/restaurant/resources/js/bootstrap.js"></script>
    <script src="/restaurant/resources/js/jquery-3.1.0.js"></script>
</head>
<body>
    <%@include file="header.jsp"%>
    <p>Username: <security:authentication property="principal.username"/></p>
    <p>Role: <security:authentication property="principal.authorities"/></p>
</body>
</html>
