<%-- 
    Document   : error
    Created on : Oct 13, 2020, 1:56:56 PM
    Author     : harry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>ERROR PAGE</h1>
        <h1><font color="red">${requestScope.ERROR}</font></h1>
            <c:url var="search" value="${requestScope.URL}"></c:url>
        <!--<a href="${search}">Back to previous page</a>-->
        <a href="index.jsp">Back to main page</a>
    </body>
</html>
