<%-- 
    Document   : insertCake
    Created on : Oct 17, 2020, 1:49:37 PM
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
        <title>Add Cake</title>
    </head>
    <body>
        <div class="container">
            <div class="card-detail">
                <h1 style="text-align: center">Add new Cake</h1>
                <form action="MainController">
                    <div class="form-group">
                        <label>Name:</label>
                        <input class="form-control" name="txtName" type="text" required placeholder="Cake name.."/>
                        <label>Image:</label>
                        <input class="form-control" name="txtImage" type="file" required/>
                        <label>Price:</label>
                        <input class="form-control" name="txtPrice" type="text" required/>
                        <label>Quantity:</label>
                        <input class="form-control" name="txtQuantity" type="text" required/>
                        <label>Category:</label>
                        <select name = "listCake">
                            <option value ="1">Bánh nướng</option>
                            <option value ="2">Bánh dẻo</option>
                            <option value ="3">Bánh chay</option>
                        </select> <br/>
                        <label>Create Date</label>
                        <input class="form-control" name="txtCreDate" type="date" id="datepicker" required/>
                        <label>Expired Date</label>
                        <input class="form-control" name="txtExpDate" type="date" id="datepicker" required/>
                        <input type="hidden" name="action" value="AddCake">
                        <input class="form-control btn btn-info" type="submit" name="add" value="Add Cake">
                    </div>
                    <a href="index.jsp">Back to home page</a>
                </form>
            </div>
        </div>
        
    <script>
        $(function() {
        $("#datepicker").datepicker();
        });
    </script>
</body>
</html>
