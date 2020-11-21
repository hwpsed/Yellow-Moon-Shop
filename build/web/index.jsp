<%-- 
    Document   : index
    Created on : Oct 12, 2020, 11:42:35 AM
    Author     : harry
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet"  href ="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <link rel="stylesheet" href="resource/mycss.css"/>       

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">     
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Home Page</title>
    </head>
    <body>

        <c:set var="user" value="${sessionScope.USER}"/>
        <!--<nav class="navbar navbar-expand-lg navbar-white" data-spy="affix" data-offset-top="0">-->
        <nav class="navbar navbar-expand-lg navbar-dark static-top">
            <a class="navbar-item toleft" href="#"><image src="images/YM.png" style="width:70px;"></a>

            <div class="collapse navbar-collapse">
                <c:if test="${sessionScope.ROLE eq 'admin'}">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="MainController?action=AddCake&add=goto">Add Cake</a>
                        </li>
                    </ul>
                </c:if>
            </div>

            <form action="MainController" method="GET">

                <div class="navbar navbar-topnav">
<!--                    <select name="listSearch" >
                        <option value="1">Words</option>
                        <option value="2">Price</option>
                    </select>-->

                    <%--<c:if test="${param.listSearch == '1'}">--%>
                        <div class="form-inline" style="padding-bottom: 30px;">
                            <input class="form-control" type="text" name="txtSearch" placeholder="Search..." value="<c:out value="${param.txtSearch}"/>"/>
                            <button class="btn btn-primary" type="submit" name="action" value="Search"><i class="fa fa-search"></i></button> 
                            <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                        </div>
                    <%--</c:if>--%>
                    <%--<c:if test="${param.listSearch == '2'}">--%>
                        <div class="form-inline" style="margin-left: -545px; margin-top: 50px; display: inline-block;">
                            <input class="form-control" type="text" name="txtPrice1" >
                            - <input class="form-control" type="text" name="txtPrice2" />
                            <!--<button class="btn btn-primary" type="submit" name="action" value="Search"><i class="fa fa-search"></i></button>-->
                            <input type="hidden" name="txtPrice1" value="<c:out value="${param.txtPrice1}"/>"/>
                            <input type="hidden" name="txtPrice2" value="<c:out value="${param.txtPrice2}"/>"/>
                        </div>
                    <%--</c:if>--%>
                    <input type="hidden" name="txtRole" value="${sessionScope.ROLE}"/>
                    
                    <br/>        
                </div>
            </form> 

            <div class="nav-item toright" style="padding-right: 70px; padding-bottom: 10px">
                <c:if test="${sessionScope.ROLE ne 'admin'}">
                    <a href="cart.jsp" class="form-control btn btn-info"><i class="fa fa-shopping-cart" ></i></a>
                    </c:if>
            </div>

            <div class="navbar-text toright">
                <c:choose>
                    <c:when test="${not empty sessionScope.ROLE}">
                        Welcome <strong>${sessionScope.FULLNAME}</strong><br/>
                        <c:if test="${not empty sessionScope.ROLE}">
                            <a class="pull-right" href="MainController?action=Logout"> Logout</a>
                        </c:if>
                    </c:when> 
                    <c:when test="${empty requestScope.ROLE}">
                        <a href="login.jsp">Login</a>
                        <br/>
                    </c:when>
                </c:choose>
                </li>
            </div>
        </nav>

        <!--<div class="container" style="width: 100%;">--> 
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>


            <!-- Wrapper for slides -->
            <div class="carousel-inner">

                <div class="item active">
                    <img src="images/img1.png" style="width:100%;">
                </div>

                <div class="item">
                    <img src="images/img2.png" style="width:100%;">
                </div>

                <div class="item">
                    <img src="images/img3.png" style="width:100%;">
                </div>
            </div>
            <label class="center carousel-indicators"><h1 class="title">Yellow Moon Shop</h1></label>
            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <!--</div>--> 

        <div class="grid-container">
            <div class="menu"> 
                <div class="tab">
                    <button class="tablinks" onclick="openCity(event, 'BanhNuong')">Bánh Nướng</button><br/>
                    <button class="tablinks" onclick="openCity(event, 'BanhDeo')">Bánh Dẻo</button><br/>
                    <button class="tablinks" onclick="openCity(event, 'BanhChay')">Bánh Chay</button><br/>
                </div>
            </div>  
            <div class="grid-container-inner">

                <c:if test="${requestScope.INFO != null}">
                    <c:if test="${not empty requestScope.INFO}">
                        <c:forEach var="cake" items="${requestScope.INFO}">
                            <div class="grid-item">
                                <div class="product-small box">
                                    <div class="box-image">
                                        <img src="${cake.getImage()}" style="width: 200px; height: 200px"/>
                                    </div>
                                    <div class="box-text">
                                        <div class="name product-title">
                                            <h4>${cake.getName()}</h4>
                                        </div>
                                        <div class="price-wrapper">
                                            <span class="price">
                                                <span class="amount">
                                                    ${cake.getPrice()} VNĐ
                                                </span>
                                            </span>
                                        </div>
                                        <form action="MainController" method="POST">
                                            <c:choose>
                                                <c:when test="${sessionScope.ROLE eq 'admin'}">
                                                    <div class="add-to-cart-button">
                                                        <input class="form-control btn btn-info" type="submit" name="action" value="Edit Cake"/>
                                                        <input type="hidden" name="txtId" value="${cake.getId()}"/>
<!--                                                        <input type="submit" name="action" value="Delete Cake" 
                                                               class="btn btn-secondary btn-sm " onclick="return confirm('Want to delete?')">-->
                                                        <!--<i class="fa fa-trash" ></i>-->

                                                        </input>
                                                    </div>
                                                </c:when>
                                                <c:when test="${sessionScope.ROLE ne 'admin'}">
                                                    <div class="add-to-cart-button">
                                                        <input class="form-control btn btn-info" type="submit" name="action" value="Add to Cart"/>
                                                        <input type="hidden" name="txtId" value="${cake.getId()}"/>
                                                    </div>
                                                </c:when>          
                                            </c:choose>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>

                    <c:if test="${empty requestScope.INFO}">
                        <div class="card col-40">
                            No cake found 
                        </div>                     
                    </c:if> 
                </div>
                <div></div>
                <div></div>
                <div></div>
                <form action="MainController" method="GET">
                    <input type="hidden" name="txtSearch" value="${param.txtSearch}"/>
                    <input type="hidden" name="action" value="Search"/>
                    <div class="pagination" style="padding-left: 400px">
                        <c:if test="${requestScope.INDEX >= 2}">
                            <input type="submit" class="page-item page-link" name="btnAction"  value="Previous"/>
                        </c:if>
                        <span class="page-item page-link">${requestScope.INDEX}</span>
                        <input type="hidden" name="page" value="${requestScope.INDEX}"/>
                        <c:if test="${requestScope.INDEX < requestScope.SIZE}">
                            <input type="submit" class="page-item page-link" name="btnAction"  value="Next"/>
                        </c:if>
                    </div>
                </form>




            </c:if>  


            <!--<script src="resource/js/indexjs.js"></script>-->

    </body>
</html>
