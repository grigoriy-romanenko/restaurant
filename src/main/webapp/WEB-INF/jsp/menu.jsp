<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link href="/restaurant/resources/css/bootstrap.css" rel="stylesheet">
    <script src="/restaurant/resources/js/bootstrap.js"></script>
    <script src="/restaurant/resources/js/jquery-3.1.0.js"></script>
    <script src="/restaurant/resources/js/menu.js"></script>
</head>
<body>
    <%@include file="header.jsp"%>
    <form class="form-horizontal">
        <div class="form-group">
            <label for="categories" class="control-label col-md-2">Category</label>
            <div class="col-md-2">
                <select id="categories" class="form-control"></select>
            </div>
        </div>
    </form>
    <div class="container col-md-6">
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody id="items"></tbody>
        </table>
    </div>
</body>
</html>
