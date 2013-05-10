<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body><c:choose>
            <c:when test="${empty user}"></c:when>
            <c:otherwise>
                <a href="ChangeImg.jsp"><img src="${user.uimg}" height="200" width="200" alt="Image Unavailable"/></a>
                <p><a href="Home">Home</a>
        <a href="CreateMsg.jsp">Create Message</a>
        <a href="Follow">Follow User</a>
        <a href="Login">Logout</a>
            </c:otherwise>
        </c:choose>
    
