<%-- 
    Document   : editCake
    Created on : Oct 17, 2020, 2:33:37 PM
    Author     : harry
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <Link  rel = "stylesheet"  href = "//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" >
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/mycss.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Cake</title>
    </head>
    <body>

        <c:set var="cake" value="${sessionScope.CAKE}"/>
        <div class="container">
            <div class="card-detail">
                <h1 style="text-align: center">Edit Cake</h1>
                <form action="MainController" method="POST">
                    <div class="form-group">
                        <label>Name:</label>
                        <input class="form-control" name="txtName" type="text"  value="${cake.getName()}"/>
                        <label>Image:</label>
                        <input class="form-control" name="txtImage" type="file" value="${cake.getImage()}"/>
                        <label>Price:</label>
                        <input class="form-control" name="txtPrice" type="text" value="${cake.getPrice()}"/>
                        <label>Quantity:</label>
                        <input class="form-control" name="txtQuantity" type="text" value="${cake.getQuantity()}"/>
                        <label>Category:</label>
                        <select name = "listCake" >
                            <option value ="1">Bánh nướng</option>
                            <option value ="2">Bánh dẻo</option>
                            <option value ="3">Bánh chay</option>
                        </select> <br/>
                        <label>Category:</label>
                        <select name = "listStatus" >
                            <option value ="1">Khả dụng</option>
                            <option value ="2">Không khả dụng</option>
                        </select> <br/>
                        <label>Create Date</label>
                        <input class="form-control" name="txtCreDate" type="date" id="datepicker" value="${cake.getCreDate()}"/>
                        <label>Expired Date</label>
                        <input class="form-control" name="txtExpDate" type="date" id="datepicker" value="${cake.getExpDate()}"/>
                        <input class="form-control btn btn-info" type="submit" name="action" value="Edit">
                        <input type="hidden" name="Id" value="${cake.getId()}"/>
                    </div>
                    <a href="index.jsp">Back to home page</a>
                </form>
            </div>
        </div>
                    
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>                  
    </body>
</html>
