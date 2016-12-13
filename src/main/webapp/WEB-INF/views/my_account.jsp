<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="com.osm.persistences.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    	<title>My Site</title>
        <script src="<c:url value="resources/js/libs/jquery-3.1.1.min.js"/>"></script>
        <script src="<c:url value="resources/js/libs/bootstrap.min.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.min.css"/>">
        <link rel="stylesheet" type="text/css" href="resources/css/index.css">
    </head>
	<body style="font-family:'Roboto Condensed', 'Gill Sans MT', 'Century Gothic', Arial, 'Bell MT'">
    	<div class="container">
        	<div style="text-align: right; margin-bottom:5px;">
            	<button class="btn-link">Register or Login Here</button>
                  <div class="dropdown" style="text-align:left">
                      <button class="dropbtn">Hi, Anirban Sanyal <span class="glyphicon glyphicon-triangle-bottom"></span></button>
                      <div class="dropdown-content">
                        <a href="#">My Account</a>
                        <a href="#">My Orders</a>
                      </div>
                </div>
                <button class="btn" style="padding:0px 20px;">Logout</button>
            </div>
            <div class="my_header">
                <div id="title">
                	<span class="glyphicon glyphicon-shopping-cart"></span>
                    Online Shoping Site
                	<div id="sub_title">Its time to move on</div>
                </div>
            </div>
            <div class="my_path">
            	<button class="btn btn-primary">Homepage</button>
                <span class="glyphicon glyphicon-arrow-right"></span>
                <button class="btn btn-primary">Smartphone And Tablets</button>
            </div>
          	<div>
            	<form>
                	<h1>My Account</h1>
                	<div class="form-group">
                        <label for="fullname">Full Name:</label>
                        <input type="text" class="form-control" id="fullname">
                    </div>
                    
                    <div>
                        <span> Photo </span>
                        <img src="" width="100" height="100"/>
                         <input type="file"/>
                    </div>
                    
                    <div>
                        <span> Phone Number </span>
                         <input type="text"/>
                    </div>
                    
                    <div>
                        <span> Email </span>
                         <input type="text"/>
                    </div>
                    <div>
                        <span> Address</span>
                         <input type="text"/>
                    </div>
                    
                    <div>
                        <span> Username </span>
                         <input type="text"/>
                    </div>
                    
                    <div>
                        <span>Password </span>
                         <input type="text"/>
                    </div>
                    
                    <div>
                        <span>Security Question</span>
                         <input type="text"/>
                    </div>
                     <div>
                        <span> Security Answer </span>
                         <input type="text"/>
                    </div>             
                </form>
            </div>
        </div>
    </body>
</html>
</head>
<body>

</body>
</html>