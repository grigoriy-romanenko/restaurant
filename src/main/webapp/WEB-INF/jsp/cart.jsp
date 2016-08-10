<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Cart</title>
        <link href="/restaurant/resources/lib/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">
        <script src="/restaurant/resources/lib/jquery-3.1.0.js"></script>
        <script src="/restaurant/resources/lib/bootstrap-3.3.7/js/bootstrap.js"></script>
        <script src="/restaurant/resources/js/header.js"></script>
        <script src="/restaurant/resources/js/cart.js"></script>
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
                <c:forEach var="menuItem" items="${cart.menuItems}">
                    <tr>
                        <td><a href="/restaurant/menu/items/${menuItem.id}">${menuItem.title}</a></td>
                        <td>${menuItem.price}</td>
                        <td>
                            <button data-menu-item="${menuItem.id}" data-username="${pageContext.request.userPrincipal.name}" class="btn btn-default delete-from-cart-btn">Delete</button>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${not empty cart.menuItems}">
                    <tr>
                        <td>Overall price: ${cart.overallPrice}</td>
                    </tr>
                </c:if>
            </table>
        </div>
    </body>
</html>
