<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
    <head>
        <title>Orders</title>
        <link href="/restaurant/resources/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet">
        <link href="/restaurant/resources/css/common.css" rel="stylesheet">
        <script src="/restaurant/resources/jquery/3.1.0/jquery.js"></script>
        <script src="/restaurant/resources/bootstrap/3.3.7/js/bootstrap.js"></script>
        <script src="/restaurant/resources/js/header.js"></script>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container col-md-4">
            <table class="table">
                <tr>
                    <th>User</th>
                    <th>Date</th>
                    <th>Items</th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.user.username}</td>
                        <td><fmt:formatDate value="${order.date}" type="both" dateStyle="short" timeStyle="short"/></td>
                        <td>
                            <c:forEach var="orderItem" items="${order.orderItems}">
                                <p>${orderItem.menuItem.title}</p>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
