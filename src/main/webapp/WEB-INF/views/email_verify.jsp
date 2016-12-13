<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>My Site</title>
        <script src="<c:url value="resources/js/libs/jquery-3.1.1.min.js"/>"></script>
        <script src="<c:url value="resources/js/libs/bootstrap.min.js"/>"></script>
        <script src="<c:url value="resources/js/libs/myfunction.js"/>"></script>
        <script src="<c:url value="resources/js/pages/email_verify.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/pages/email_verify.css"/>">
</head>
<body>
	<c:choose>
  		<c:when test="${match}">
			<h2>Congratulation!</h2>
		  	<div class="panel panel-default">
		    	<div class="panel-body">
		        	Your email confirmed successfully, now you can login by your credential...
		            <br>
		            <button type="button" class="btn btn-success" data-dismiss="modal">
		            	OK
		            </button>
		        </div>
		  	</div>
  		</c:when>
  		<c:otherwise>
		    <h2>404 - Server Error!</h2>
		  	<div class="panel panel-default">
		    	<div class="panel-body">
		        	Invalid request which make server error, please goto login page or call system administrator...
		            <br>
		            <button type="button" class="btn btn-info" data-dismiss="modal">
		            	OK
		            </button>
		        </div>
		  	</div>
		 </c:otherwise>
  	</c:choose>
</body>
</html>