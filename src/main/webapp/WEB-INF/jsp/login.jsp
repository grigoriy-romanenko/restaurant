<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div>
            <form name="login" method="post" action="/restaurant/login">
                <p>
                    <input type="text" name="username" placeholder="username" required>
                </p>
                <p>
                    <input type="password" name="password" placeholder="password" required>
                </p>
                <p>
                    <input type="submit" value="Login"/>
                </p>
            </form>
        </div>
    </body>
</html>