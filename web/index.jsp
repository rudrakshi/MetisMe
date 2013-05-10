<%-- 
    Document   : index
    Created on : 25 Dec, 2012, 4:39:19 PM
    Author     : Rudrakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <!--script ></script-->
    </head>
    <body>
        <h1>User Registration</h1>
        
        <form method="post" action="Register">
            Name:&nbsp;<input type="text" name="uname" id="uname" size="50" onchange=""/><br/>
            Occupation:&nbsp;<input type="text" name="occ" id="occ" size="3" onchange=""/><br/>
            Location:&nbsp;<input type="text" name="loc" id="loc" size="50" onchange=""/><br/>
            Email address:&nbsp;<input type="text" name="email" id="email" size="75" onchange=""/><br/>
            Username:&nbsp;<input type="text" name="unm" id="unm" size="50" onchange=""/><br/>
            Password:&nbsp;<input type="password" name="pwd" id="pwd" size="100"/><br/>
            Confirm password:&nbsp;<input type="password" name="cpwd" id="cpwd" size="100"/><br/>
            <input type="submit" name="Register" value="Register" onclick=""/>
        </form>
        <a href="Login.jsp">Login</a>
    </body>
</html>
