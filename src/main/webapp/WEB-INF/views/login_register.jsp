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
        <script src="<c:url value="resources/js/libs/jquery.msgBox.js"/>"></script>
        <script src="<c:url value="resources/js/libs/myfunction.js"/>"></script>
        <script src="<c:url value="resources/js/pages/login_register.js"/>"></script>
        <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/bootstrap-theme.min.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/msgBoxLight.css"/>">
        <link rel="stylesheet" type="text/css" href="<c:url value="resources/css/pages/index.css"/>">
    </head>
	<body style="font-family:'Roboto Condensed', 'Gill Sans MT', 'Century Gothic', Arial, 'Bell MT'">
    	<div class="container">
        	<div style="text-align: right; margin-bottom:5px; min-height: 25px">
            	
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
            <div class="container-fluid">
            <div class="row">
            	<div class="col-md-12" style="padding: 10px; font-size: medium; border-width: thin; border-style: dotted;">
            		<div>LOGIN HERE</div>
                	<form id="formLogin" class="panel panel-default" style="padding:10px 20px;">
                    	<div class="text-danger error" id="error">
                        	<span class="glyphicon glyphicon-arrow-right"></span> 
                            Username and Password not valid
                        </div>
                    	<div class="form-group">
    						<label for="userName" class="control-label">Username</label>
    						<input type="text" class="form-control" id="userName" placeholder="ex. user123"/>
                            <div class="help-block">Minimum of 6 characters</div>
  						</div>
                        <div class="form-group">
    						<label for="userPass" class="control-label">Password</label>
    						<input type="password" class="form-control" id="userPass" placeholder="ex. 123@abc" maxlength="15" data-minlength="6" required/>
                            <div class="help-block">Minimum of 6 characters</div>
  						</div>
                         <div class="form-group">
                         	<button type="reset" class="btn btn-default">Reset</button>
    						<button id="btn_submit" type="button" class="btn btn-primary">Login</button>
  						</div>
                    </form>
                    
                    
                    
                    <div align="center" style="font-size: xx-large;">OR</div>
        
                    
                    
                    
                    
                    <div>REGISTRATION HERE (<i>All fields are mandatory</i>)</div>
                    <form id="form_register" data-toggle="validator" role="form" class="panel panel-default" style="padding:10px 20px;">
                    	<div class="text-danger error" id="error">
                        	<span class="glyphicon glyphicon-arrow-right"></span> 
                            Registration Error - Possibly reason
                            <div id="reason">
                            </div>
                        </div>
                        <!-- UserPhoto -->
                        <div class="form-group">
    						<label for="photo" class="control-label">My Photo*</label>
    						<div style="padding: 10px;"><img id="photopreview" width="100" height="100" src="<c:url value="resources/images/user_photo/default.png"/>"/></div>
    						<span><input type="file" class="form-control" id="photoupload" required/></span>
                            <div class="help-block">Your current photo</div>
  						</div>
  						<!-- FullName -->
                    	<div class="form-group">
    						<label for="fullname" class="control-label">Full Name*</label>
    						<input type="text" class="form-control" id="fullname" placeholder="ex. 'Anirban Sanyal'" required/>
                            <div class="help-block">Full Name must maintain capitalize letter like 'Md. Sayed Ali'</div>
  						</div>
  						<!-- Phone No -->
  						<div class="form-group">
    						<label for="phoneNumber" class="control-label">Phone Number*</label>
    						<input type="text" class="form-control" id="phoneNumber" placeholder="ex. '0123456789'" required/>
                            <div class="help-block">Only numerical digits are allow</div>
  						</div>
  						<!-- Email ID -->
  						<div class="form-group">
    						<label for="email" class="control-label">Email Id*</label>
    						<input type="text" class="form-control" id="email" placeholder="ex. 'your@email.com'" required/>
                            <div class="help-block">Full email like "abc123@xyz.com"</div>
  						</div>
  						<!-- Address -->
  						<div class="form-group">
    						<label for="address" class="control-label">Current Address*</label>
    						<textarea rows="4" style="min-width: 100%;" class="form-control" id="address" placeholder="ex. 'Your Home Address...'" required></textarea>
                            <div class="help-block">Your Home Address (Line 1: House No/Building No,Lane/Street) (Line 2: Post Office,District,Pin Code)</div>
  						</div>
  						<!-- Username -->
  						<div class="form-group">
    						<label for="username" class="control-label">New Username*</label>
    						<input type="text" class="form-control" id="username" placeholder="ex. 'user123'" required />
                            <div class="help-block">Minimum length is 6 and only some characters allow to choose your username ("a-z","0-9",".","-") <br>and no blank spaces</div>
  						</div>
  						<!-- New Password -->
  						<div class="form-group">
    						<label for="password1" class="control-label">New Password*</label>
    						<input type="password" class="form-control" id="password1" placeholder="ex. 'user@?123'" required />
                            <div class="help-block">Minimum length is 6 and only some characters allow to choose your username ("A-Z","a-z","0-9",".","@","_","-","?") <br>and no blank spaces</div>
  						</div>
  						<!-- Verify Password -->
  						<div class="form-group">
    						<label for="passwor2" class="control-label">Verify Password*</label>
    						<input type="password" class="form-control" id="password2" placeholder="ex. 'user@?123'" required />
                            <div class="help-block">Enter your password again</div>
  						</div>
  						<!-- Security Question -->
  						<div class="form-group">
    						<label for="secques" class="control-label">Security Question*</label>
    						<input type="text" class="form-control" id="secques" placeholder="ex. 'What is my name ?'" required />
                            <div class="help-block">Make a question properly</div>
  						</div>
  						<!-- Security Answer -->
  						<div class="form-group">
    						<label for="secans" class="control-label">Security Answer*</label>
    						<input type="text" class="form-control" id="secans" placeholder="ex. 'user@?123'" required />
                            <div class="help-block">Enter your dedicated answer of above question, don't forget it, it will help to recover your account again</div>
  						</div>
  						<!-- Button Group -->
  						<div class="form-group">
                         	<button type="reset" class="btn btn-default">Reset</button>
    						<button id="btn_submit" type="button" class="btn btn-primary">Register</button>
  						</div>
                    </form>
            	</div>
            </div>
        </div>
    	</div>
    </body>
</html>