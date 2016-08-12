<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<header>
    <div class="navbar navbar-default">
        <div class="btn-group">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Menu <span class="caret"></span>
            </button>
            <ul class="dropdown-menu"></ul>
        </div>
        <security:authorize access="hasAuthority('admin')">
            <a class="btn btn-default navbar-btn" href="/restaurant/orders">Orders</a>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
            <a class="btn btn-default navbar-btn" href="/restaurant/profile">Profile</a>
            <a class="btn btn-default navbar-btn" href="/restaurant/users/${pageContext.request.userPrincipal.name}/cart">Cart</a>
            <a class="btn btn-default navbar-btn" href="/restaurant/logout">Logout</a>
        </security:authorize>
        <security:authorize access="isAnonymous()">
            <a class="btn btn-default navbar-btn" href="/restaurant/login">Login</a>
            <a class="btn btn-default navbar-btn" href="/restaurant/register">Register</a>
        </security:authorize>
    </div>
</header>