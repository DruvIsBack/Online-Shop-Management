<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page import="com.osm.persistences.User"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    	<title>My Site</title>
        <script src="<c:url value="./resources/js/libs/jquery-3.1.1.min.js"/>"></script>
        <script src="<c:url value="./resources/js/libs/bootstrap.min.js"/>"></script>
        <script src="<c:url value="./resources/js/libs/myfunction.js"/>"></script>
        <script src="<c:url value="./resources/js/pages/subcategory.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/bootstrap-theme.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="./resources/css/pages/index.css"/>">
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
                <button class="btn btn-primary" id="current_page"><c:out value="${productCategory.name}"></c:out></button>
                <span class="glyphicon glyphicon-arrow-right"></span>
            </div>
            <c:if test="${listProductSubCategory == null}">
            	<div align="center">No sub-category listed</div>
            </c:if>
            <c:forEach items="${listProductSubCategory}" var="productSubCategory">
		            <div class="my_module" id="<c:out value="${productSubCategory.id}"/>">
		            	<div id="pic">
		           	  		<img src="./uploads/productSubCategories/<c:out value="${productSubCategory.id}"/>"/> 
		              	</div>
		                <div id="title">
		                	<c:out value="${productSubCategory.name}"/>
		                </div>
		          </div>
            </c:forEach>
        </div>
    </body>
</html>