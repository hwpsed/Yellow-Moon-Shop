<%-- 
    Document   : login
    Created on : Oct 13, 2020, 1:57:15 PM
    Author     : harry
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <Link  rel = "stylesheet"  href = "//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" >
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/mycss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>  
        <div class ="login">    
            <h1 style="text-align: center">Login</h1>
            <div class="form-group">      
                <form action="MainController" method="POST">
                    <label for="txtUsername"><b>Username:</b></label>
                    <input type="text" name="txtUsername" placeholder="Enter username"/>
                    <font color="red">
                    ${requestScope.INVALID.usernameError}
                    </font>
                    <br/>
                    <label for="txtPassword"><b>Password:</b></label>
                    <input type="password" name="txtPassword" placeholder="Enter password"/>
                    <font color="red">
                    ${requestScope.INVALID.passwordError}
                    </font>
                    <input class="form-control btn btn-info" type ="submit" name="action" value="Login"/>
                </form>
            </div>
        </div>
    </body>
</html>
