<%-- 
    Document   : payment
    Created on : Oct 18, 2020, 10:25:21 AM
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
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Payment</title>
    </head>
    <body>
        <c:set var="account" value="${sessionScope.ACCOUNT}"/>
        <c:set var="cartlist" value="${sessionScope.cart}"/>
        <c:set var="date" value="${sessionScope.DATE}"/>
        <div class="row">
            <div class="col-50">
                <div class="w3-container">


                    <c:if test="${cart != null}">        
                        <div class="card w3-display-middle" >
                            <h4>Payment: <span class="price" style="color:black">
                                    <i class="fa fa-cc-visa" style="color:navy;"></i>
                                    <i class="fa fa-cc-amex" style="color:blue;"></i>
                                    <i class="fa fa-cc-mastercard" style="color:red;"></i>
                                    <i class="fa fa-cc-discover" style="color:orange;"></i><b></b></span></h4>
                            <form action="MainController" method="POST">
                                <div class="form-group">      
                                    <c:if test="${account == null}">
                                        <label for="txtName"><b>Name:</b></label>
                                        <input type="text" name="txtName" placeholder="Enter name" required value="${account.getName()}"/>
                                        <br/>
                                        <label for="txtPhone"><b>Phone:</b></label>
                                        <input type="text" name="txtPhone" placeholder="Enter phone" required value="${account.getPhone()}"/>
                                        <br/>
                                        <label for="txtAddress"><b>Address:</b></label>
                                        <input type="text" name="txtAddress" placeholder="Enter address" required value="${account.getAddress()}"/>
                                        </br>
                                        <label for="txtPayment"><b>Payment:</b></label>
                                        <select name="txtPayment">
                                            <option value="1"> Cash </option>
                                            <option value="2"> Card </option>
                                        </select>
                                    </c:if>
                                    <c:if test="${account != null}">
                                        <label for="txtPayment"><b>Payment:</b></label>
                                        <select name="txtPayment">
                                            <option value="1"> Cash </option>
                                            <option value="2"> Card </option>
                                        </select>
                                    </c:if>
                                </div>
                                <hr>
                                <p>Total <span class="price" style="color:black"><b>${cart.getTotal()} VNĐ</b></span></p>
                                <input class="btn btn-info" type="submit" name="action" value="Finish"/>
                                <input type="hidden" name="txtTotal" value="${cart.getTotal()}"/>
                                <input type="hidden" name="txtDate" value="${sessionScope.date}"/>
                            </form>
                            <p><a href="index.jsp">Back to store</a></p>
                        </div>    
                    </c:if> 
                </div>
            </div>
        </div>
    </body>
</html>
