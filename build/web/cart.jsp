<%-- 
    Document   : cart
    Created on : Oct 17, 2020, 9:36:28 PM
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
        <title>View Cart</title>
    </head>
    <body>

        <c:set var="cartlist" value="${sessionScope.cart}"/>
        <div class="row">
            <div class="col-50">
                <c:if test="${cart == null}">
                    <div class="card w3-display-middle">
                        <h2>Cart is empty!</h2>    
                        <p><a href="index.jsp">Back to store</a></p>
                    </div>
                </c:if>
                <div class="w3-container">


                    <c:if test="${cart != null}">        
                        <div class="card w3-display-middle" >
                            <h4>Cart Items: <span class="price" style="color:black"><i class="fa fa-shopping-cart"></i> <b></b></span></h4>
                            <form action="MainController" method="POST">
                                <c:forEach var="item" items="${sessionScope.cart.getCart().values()}">

                                    <div class="form-inline">
                                        <p>${item.getName()} 
                                            <c:choose>
                                                <c:when test="${item.getStock() <= item.getQuantity()}">
                                                    <input type="text" name="txtQuantity" value="${item.getStock()}"/> 
                                                    <span class="price">${item.getPrice() * item.getStock()} VNĐ </span> 
                                                    <c:set var="temp" value="1"/>
                                                </c:when>
                                                <c:when test="${item.getStock() > item.getQuantity()}">
                                                    <input type="text" name="txtQuantity" value="Out of stock!!!"/> 
                                                    <c:set var="temp" value="2"/>
                                                    <span class="price">N/A</span>
                                                </c:when>
                                            </c:choose>

                                            <input type="hidden" name="txtName" value="${item.getName()}"/>
                                            <input class="btn btn-info" type="submit" name="action" value="Remove" onclick="return confirm('Want to delete?')"/>
                                            <input class="btn btn-info" type="submit" name="action" value="Update Cake"/>
                                        </p>
                                    </div>

                                </c:forEach>

                                <hr>
                                    <c:choose>
                                        <c:when test="${temp == '1'}">
                                            <p>Total <span class="price" style="color:black"><b>${cart.getTotal()} VNĐ</b></span></p>
                                            <input class="btn btn-info" type="submit" name="action" value="Next Step"/>
                                            <input type="hidden" name="txtTotal" value="${cart.getTotal()}"/>
                                        </c:when>
                                        <c:when test="${temp == '2'}">
                                            <p>Total <span class="price" style="color:black"><b>N/A</b></span></p>
                                        </c:when>
                                    </c:choose>


                            </form>

                            <p><a href="index.jsp">Back to store</a></p>
                        </div>    
                    </c:if> 
                </div>
            </div>
        </div>
    </body>
</html>
