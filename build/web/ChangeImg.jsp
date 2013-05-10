<%-- 
    Document   : ChangeImg
    Created on : Dec 26, 2012, 5:31:01 PM
    Author     : Rudrakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload Image</title>
    </head>
    <body>
        <jsp:include page="Header.jsp"></jsp:include>
        <form action="Change" method="post" enctype="multipart/form-data">
            <input type="file" name="img"/>
            <input type="submit" value="Upload"/>
        </form>
    </body>
</html>
