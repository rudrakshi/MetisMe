

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>User Login</h1>
        <form action="Login" method="post">
        Username:&nbsp;<input type="text" name="unm" id="unm" size="50" onchange=""/><br/>
        Password:&nbsp;<input type="password" name="pwd" id="pwd" size="100"/><br/>
        <input type="submit" name="Login" value="Login" onclick=""/>&nbsp;<a href="index.jsp"/>New Registration</a>
        </form>
    </body>
</html>
