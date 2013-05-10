<%-- 
    Document   : Home
    Created on : Dec 26, 2012, 12:16:32 AM
    Author     : Rudrakshi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="WEB-INF/c.tld" prefix="c"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        
        <h1>Hello <c:out value="${user.uname}"/></h1>
        <jsp:include page="Header.jsp"></jsp:include>
        <p><c:if test="${empty Messages}">There are no messages for you..</c:if>
        <c:forEach var="message" items="${Messages}">
            <p> 
                <img src="<c:out value="${message.uimg}"/>" height=80 width=80 alt="Image Unavailable"/>
                <c:out value="${message.uname}"/>
            </p>
            <p>
                <c:out value="${message.content}"/>
            </p>
            <p>
                Likes: <c:out value="${message.like}"/>
            <form action="Comment" method="get">
                <input type="hidden" name="mid" value="<c:out value="${message.mid}"/>"/>
                <c:choose>
                    <c:when test = "${message.status == 'Like'}">
                        <input type="submit" name="like" value="unlike"/>
                    </c:when>
                        <c:otherwise>
                            <input type="submit" name="like" value="like"/>
                        </c:otherwise>
                </c:choose>
           </form>
            </p>
            <p>Comments:
                <c:forEach var="comment" items="${message.comment}">
                    <p>
                        <c:out value="${comment.uname}" />:&nbsp;<c:out value="${comment.comment}" /><br/>
                        <c:out value="${comment.time}" />
                    </p>
                </c:forEach>
                <form action="Comment" method="post">
                    <input type="hidden" name="mid" value="<c:out value="${message.mid}"/>"/>
                    <p><textarea name="comment" id="comment" rows="4" cols="100">Write your comment...</textarea></p>
                    <input type="submit" name="post" value="Post" onclick=""/>
                </form>    
            </p>
        </c:forEach>
        
    </body>
</html>
