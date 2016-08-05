<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>${menuItem.title}</title>
        <link href="/restaurant/resources/lib/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
        <link href="/restaurant/resources/lib/jquery-ui-1.12.0/jquery-ui.css" rel="stylesheet">
        <script src="/restaurant/resources/lib/jquery-3.1.0.js"></script>
        <script src="/restaurant/resources/lib/jquery-ui-1.12.0/jquery-ui.js"></script>
        <script src="/restaurant/resources/js/menuItem.js"></script>
    </head>
    <body>
        <%@include file="header.jsp"%>
        <div>
            <b>Title:</b><div id="title"><c:out value="${menuItem.title}"/></div>
        </div>
        <div>
            <b>Price:</b><div id="price"><c:out value="${menuItem.price}"/></div>
        </div>
        <div>
            <b>Category:</b><div id="category"><c:out value="${menuItem.category.title}"/></div>
        </div>
        <div class="col-md-1">
            <input type="button" id="editMenuItemButton" class="form-control" value="Edit"/>
        </div>
        <div id="editMenuItemPopup" title="Edit Menu Item">
            <form id="editMenuItemForm" class="form-horizontal">
                <div class="form-group">
                    <label for="titleInput" class="control-label col-md-2">Title:</label>
                    <div class="col-md-10">
                        <input id="titleInput" type="text" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="priceInput" class="control-label col-md-2">Price:</label>
                    <div class="col-md-10">
                        <input id="priceInput" type="number" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="categoryInput" class="control-label col-md-2">Category:</label>
                    <div class="col-md-10">
                        <select id="categoryInput" class="form-control"></select>
                    </div>
                </div>
                <input id="submitMenuItem" type="submit" class="form-control" value="Edit"/>
            </form>
        </div>
        <div class="col-md-1">
            <input type="button" id="deleteMenuItemButton" class="form-control" value="Delete"/>
        </div>
    </body>
</html>