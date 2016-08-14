<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>${menuItem.title}</title>
        <link href="/restaurant/resources/lib/jquery-ui-1.12.0/jquery-ui.css" rel="stylesheet">
        <link href="/restaurant/resources/lib/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
        <link href="/restaurant/resources/css/common.css" rel="stylesheet">
        <script src="/restaurant/resources/lib/jquery-3.1.0.js"></script>
        <script src="/restaurant/resources/lib/bootstrap-3.3.7/js/bootstrap.js"></script>
        <script src="/restaurant/resources/lib/jquery-ui-1.12.0/jquery-ui.js"></script>
        <script src="/restaurant/resources/js/header.js"></script>
        <script src="/restaurant/resources/js/menuItem.js"></script>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div>
            <b>Title: </b><span id="title">${menuItem.title}</span>
        </div>
        <div>
            <b>Price: </b><span id="price">${menuItem.price}</span>
        </div>
        <div>
            <b>Category: </b><span id="category">${menuItem.category.title}</span>
        </div>
        <div>
            <button id="editMenuItemButton" class="btn btn-default">Edit</button>
            <button id="deleteMenuItemButton" class="btn btn-default">Delete</button>
        </div>
        <div id="editMenuItemPopup" title="Edit Menu Item">
            <form id="editMenuItemForm" class="form-horizontal">
                <div class="form-group">
                    <label for="titleInput" class="control-label col-md-3">Title:</label>
                    <div class="col-md-9">
                        <input id="titleInput" type="text" class="form-control" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="priceInput" class="control-label col-md-3">Price:</label>
                    <div class="col-md-9">
                        <input id="priceInput" type="number" min="1" class="form-control" required/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="categoryInput" class="control-label col-md-3">Category:</label>
                    <div class="col-md-9">
                        <select id="categoryInput" class="form-control"></select>
                    </div>
                </div>
                <input id="submitMenuItem" type="submit" class="form-control" value="Edit"/>
            </form>
        </div>
    </body>
</html>