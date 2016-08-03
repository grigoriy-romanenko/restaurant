<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu</title>
    <link href="/restaurant/resources/lib/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
    <link href="/restaurant/resources/lib/jquery-ui-1.12.0/jquery-ui.css" rel="stylesheet">
    <script src="/restaurant/resources/lib/jquery-3.1.0.js"></script>
    <script src="/restaurant/resources/lib/jquery-ui-1.12.0/jquery-ui.js"></script>
    <script src="/restaurant/resources/js/menu.js"></script>
</head>
<body>
    <%@include file="header.jsp"%>
    <form class="form-horizontal">
        <div class="form-group">
            <label for="categories" class="control-label col-md-1">Category:</label>
            <div class="col-md-2">
                <select id="categories" class="form-control"></select>
            </div>
            <div class="col-md-1">
                <input type="button" id="createMenuItemButton" class="form-control" value="Create"/>
            </div>
            <div id="createMenuItemPopup" title="Create Menu Item">
                <form id="createMenuItemForm" class="form-horizontal">
                    <div class="form-group">
                        <label for="title" class="control-label col-md-2">Title:</label>
                        <div class="col-md-10">
                            <input id="title" type="text" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price" class="control-label col-md-2">Price:</label>
                        <div class="col-md-10">
                            <input id="price" type="number" class="form-control"/>
                        </div>
                    </div>
                    <input id="submitMenuItem" type="submit" class="form-control" value="Create"/>
                </form>
            </div>
        </div>
    </form>
    <div class="container col-md-4">
        <table class="table">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Price</th>
                    <th></th>
                </tr>
            </thead>
            <tbody id="items"></tbody>
        </table>
    </div>
</body>
</html>
