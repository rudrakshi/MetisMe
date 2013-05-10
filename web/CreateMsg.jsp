<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Message</title>
    </head>
    <body>
         <jsp:include page="Header.jsp"></jsp:include>
        <form action="Home" method="post">
            Message:
            <p><textarea name="msg" id="msg" rows="10" cols="100">Write your message...</textarea></p>
            <input type="submit" name="post" value="Post" onclick=""/>
        </form>
    </body>
</html>
