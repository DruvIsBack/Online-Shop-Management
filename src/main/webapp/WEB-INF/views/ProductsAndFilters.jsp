<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="com.osm.persistences.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    	<title>My Site</title>
        <script src="../resources/js/libs/jquery-3.1.1.min.js"></script>
        <script src="../resources/js/libs/bootstrap.min.js"></script>
        <script src="../resources/js/libs/myfunction.js"></script>
        <script src="../resources/js/pages/ProductAndFilter.js"></script>
        <link rel="stylesheet" type="text/css" href="../resources/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="../resources/css/bootstrap-theme.min.css">
        <link rel="stylesheet" type="text/css" href="../resources/css/pages/index.css">
    </head>
	<body style="font-family:'Roboto Condensed', 'Gill Sans MT', 'Century Gothic', Arial, 'Bell MT'">
    	<div class="container">
        	<div style="text-align: right; margin-bottom:5px;">
        		<c:choose>
        		<c:when test="${not empty  user}">
        			<div class="dropdown" style="text-align:left">
                        <button class="dropbtn">
                        Hi, <c:out value="${user.getName()}"></c:out>(<c:out value="${user.getUserType().getName()}"></c:out>)
                        <span class="glyphicon glyphicon-triangle-bottom"></span></button>
                        <div class="dropdown-content">
                          <a href="#">My Account</a>
                          <a href="#">My Orders</a>
                        </div>
                  	</div>
                  <button class="btn" style="padding:0px 20px;" id="btn_logout">Logout</button>
                </c:when>
                <c:otherwise>
        			<button class="btn-link" id="btn_login_register">Register or Login Here</button>
        		</c:otherwise>
        		</c:choose>
            </div>
            <div class="my_header">
                <div id="title">
                	<span class="glyphicon glyphicon-shopping-cart"></span>
                    Online Shoping Site
                	<div id="sub_title">Its time to move on</div>
                </div>
            </div>
            <div class="my_path">
            	<button class="btn btn-primary" id="home_page">Homepage</button>
                <span class="glyphicon glyphicon-arrow-right"></span>
                <button class="btn btn-primary" id="prev_page"><c:out value="${productCategory.name}"></c:out></button>
                <span class="glyphicon glyphicon-arrow-right"></span>
                <button class="btn btn-primary" id="current_page"><c:out value="${productSubCategory.name}"></c:out></button>
                <span class="glyphicon glyphicon-arrow-right"></span>
            </div>
            <div class="container-fluid">
            <div class="row">
            	<div class="col-md-3" style="background-color: #E1B0B0; padding: 10px; font-size: large; text-shadow: 1px 1px 2px #878787;">
                	<div align="center" class="my_filter_module">
                    	<div>
                        	Price Min (INR.) <br>
                        	<input type="number"/>
                        </div>
                        <div>
                        	Price Max (INR.) <br>
                        	<input type="number"/>
                        </div>
                        <div>
                        	Product Vendor <br>
                        	<select>
                            	<option selected>All</option>
                            	<option>Nokia</option>
                                <option>Samsung</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-9" style="padding-top: 10px; padding-right: 5px; padding-left: 10px; padding-bottom: 15px;">
                	<div class="my_item">
                    	<div id="pic" class="img-thumbnail carousel-inner">
                        	<img src="images/product_images/xiaomi redmi note 3.jpg"/>
                            <div class="carousel-caption" id="pic_caption">5600.00 /-</div>
                        </div>
                        <div id="text">Xiaomi Redmi Note 3</div>
                    </div>
                    <div class="my_item">
                    	<div id="pic" class="img-thumbnail carousel-inner">
                        	<img src="images/product_images/ZTE Axon 7 Max.jpg"/>
                            <div class="carousel-caption" id="pic_caption">7200.00 /-</div>
                        </div>
                        <div id="text">ZTE Axon 7 Max</div>
                    </div>
                </div>
            </div>
            </div>
        </div>
    </body>
</html>