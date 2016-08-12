<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
    <head>
        <title>${category.title}</title>
        <link href="/restaurant/resources/lib/jquery-ui-1.12.0/jquery-ui.css" rel="stylesheet">
        <link href="/restaurant/resources/lib/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
        <link href="/restaurant/resources/css/common.css" rel="stylesheet">
        <script src="/restaurant/resources/lib/jquery-3.1.0.js"></script>
        <script src="/restaurant/resources/lib/bootstrap-3.3.7/js/bootstrap.js"></script>
        <script src="/restaurant/resources/lib/jquery-ui-1.12.0/jquery-ui.js"></script>
        <script src="/restaurant/resources/js/header.js"></script>
        <script src="/restaurant/resources/js/category.js"></script>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container col-md-4">
            <table class="table">
                <tr>
                    <th>Title</th>
                    <th>Price</th>
                    <th></th>
                </tr>
                <c:forEach var="menuItem" items="${category.menuItems}">
                    <tr>
                        <td><a href="/restaurant/menu/items/${menuItem.id}">${menuItem.title}</a></td>
                        <td>${menuItem.price}</td>
                        <td>
                            <security:authorize access="isAuthenticated()">
                                <button data-menu-item="${menuItem.id}"
                                        data-username="${pageContext.request.userPrincipal.name}"
                                        class="btn btn-default add-to-cart-btn">Add to cart</button>
                            </security:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <security:authorize access="hasAuthority('admin')">
            <div>
                <button id="createMenuItemButton" class="btn btn-default">Create</button>
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
        </security:authorize>
    </body>
</html>
