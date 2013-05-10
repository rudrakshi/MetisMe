<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Follow User</title>
    </head>
    <body>
         <jsp:include page="Header.jsp"></jsp:include>
         <c:forEach var="user" items="${Users}">
                    <p>
                        <img src=' <c:out value="${user.uimg}"/> ' height=80 width=80 alt="Image Unavailable"/>
                <c:out value="${user.uname}"/>
                    </p>
                    <p>
                    <c:if test="${user.status == 'Not Followed'}">
                        <form action="Follow" method="post">
                            <input type="hidden" name="uid" value="<c:out value="${user.uid}"/>">
                            <input type="submit" value="Follow"/>
                        </form>
                    </c:if>
                </c:forEach>
    </body>
</html>
