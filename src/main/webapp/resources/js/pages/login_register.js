function validUser(username){
	var re = new RegExp("^([_a-z0-9\.-]{6,20})$");
	if (re.test(username)){
		console.log("valid username");
		return true;
	}else{
		console.log("invalid username");
		return false;
	}
}
function validPass(password){
	var re = new RegExp("^([-_@A-z0-9\.]{6,20})$");
	if (re.test(password)){
		console.log("valid password");
		return true;
	}else{
		console.log("invalid password");
		return false;
	}
}
function resendEmail(){
	console.log("resendEmail() execute...");
	var user = $("#formLogin #userName").val();
	var pass = $("#formLogin #userPass").val();
	if(validUser(user) && validPass(pass)){
		$.msgBox({
			title: "Confirm!!",
			content: "Do you want to resend the verify code to your email ?",
			type: "confirm",
			buttons: [{ value: "Yes" }, { value: "No" }],
			success: function (result) {
				switch(result){
				case 'Yes':
					$.ajax({
						url:'login/resendEmail',
						data:{
							'username' : user,
							'password' : pass,
						},
						type: 'post',
						success: function(data){
							//'0' - Email sent successfully...
							//'-1' - Username or password validation error...
							//'-2' - User already verified....
							//'-3' - Email can't send...
							switch(data){
							case '0':
									$.msgBox({
										title: "Alright !!",
										content: "We send you an link/code to your given email, \nPlease check your mail",
										type: "info",
										buttons: [{ value: "Thank You" }]
									});
								break;
							case '-1':
								$.msgBox({
									title: "Error !!",
									content: "Username or password validation error...",
									type: "error",
									buttons: [{ value: "OK" }]
								});
							break;
							case '-2':
								$.msgBox({
									title: "Ooops !!",
									content: "User already verified....",
									type: "error",
									buttons: [{ value: "OK" }]
								});
							break;
							case '-3':
								$.msgBox({
									title: "Sorry !!",
									content: "Email can't send, please try again",
									type: "error",
									buttons: [{ value: "OK" }]
								});
							break;
							default:
								$.msgBox({
									title: "Sorry !!",
									content: "We can't send email, may be server error, try again",
									type: "error",
									buttons: [{ value: "OK" }]
								});
								break;
							}
						}
					});
					break;
				default:
					break;
				}
			}
		});
	}else{
		$.msgBox({
			title: "ILLIGAL!!",
			content: "Please enter valid username and password...",
			type: "error",
			buttons: [{ value: "OK" }],
		});
	}
}

function tryLogin(user, pass){
		console.log("Try to get user...");
		$.ajax({
			type:"post",
			data: {username : user, password: pass},
			url: "login/dologin",
			success: function(data){
				switch(data){
				case '0':			//'0' - User is valid totally...
					console.log("Ready for redirect");
					var temp_redirect = getCookie("redirect_from");
					setCookie("redirect_from","",0);
					console.log("Delete preserve coockie(redirect_from)...");
					if(temp_redirect != ""){
						console.log("Redirect by cookie...");
						console.log("generated location => "+temp_redirect);
						$(location).attr('href',temp_redirect);
					}else{
						console.log("Redirect by default...");
						$(location).attr('href', './');
					}
					break;
				case '-1':			//'-1' - User not verify his/her email...
					$("#formLogin #error").html("User not verify his/her email... <button id='btnResend' type='button' class='btn btn-info'>Resend email</button>").show();
					break;
				case '-2':			//'-2' - User not exist...
					$("#formLogin #error").html("User not exist...").show();
					break;
				case '-3':			//'-3' - Username or password validation error...
					$("#formLogin #error").html("Username or password validation error...").show();
					break;
				case '-4':			//'-4' - Server error...
					$("#formLogin #error").html("Server error...").show();
					break;
				default:			//Unknown error...
					$("#formLogin #error").html("Unknown error...").show();
					break;
				}
			}
		});
}

$(document).ready(function(){
	window.scrollTo(0, 0);
	var correct_color = "rgb(198, 255, 176)", wrong_color = "rgb(255, 230, 217)";
	
	$("#formLogin #error").hide();
	$("#form_register #reason").parent().hide();
	console.log("Login page loaded successfully...");
	$("#formLogin #btn_submit").click(function(e){
		$("#formLogin #error").hide();
		var user = $("#formLogin #userName").val();
		var pass = $("#formLogin #userPass").val();
		var boolGetCommand = false;
		$.msgBox({
			title: "Let Us Know",
			content: "Remember your credential ?",
			type: "confirm",
			buttons: [{ value: "Yes" }, { value: "No" }],
			success: function (result) {
				if (result == "Yes") {
					console.log("YES command clicked...");
					setCookie("username",user,15);
					setCookie("password",pass,15);
					console.log("User data save in cookies for 15 days...");
				}else if(result == "No"){
					console.log("NO command clicked...");
					console.log("User data not save in cookies...");
				}else{
					console.log("Select none of given commands...");
				}
				console.log("Try login...");
				tryLogin(user,pass);
				console.log("Login tried...");
			}
		});
		
	});
	
	var boolPhoto=false,boolName=false,boolPhone=false,boolEmail=false,boolAddress=false,boolUser=false,boolPass1=false,boolPass2=false,boolQues=false,boolAns=false;
	
	//Registration Submit
	$("#form_register #btn_submit").click(function(e){
		console.log("Register form btn submit clicked...");
		if(!boolPhoto){
			RegErrorShow("Photo is not valid...");
		}else if(!boolName){
			RegErrorShow("Name isn't valid...");
		}else if(!boolPhone){
			RegErrorShow("Phone isn't valid...");
		}else if(!boolEmail){
			RegErrorShow("Email isn't valid...");
		}else if(!boolAddress){
			RegErrorShow("Address isn't valid...");
		}else if(!boolUser){
			RegErrorShow("Username isn't valid...");
		}else if(!boolPass1){
			RegErrorShow("Password1 isn't valid...");
		}else if(!boolPass2){
			RegErrorShow("Password2 isn't valid...");
		}else if(!boolQues){
			RegErrorShow("SecQuestion isn't valid...");
		}else if(!boolAns){
			RegErrorShow("SecAnswer isn't valid...");
		}else{
			$("#form_register #btn_submit").css("cursor", "progress");
			$("#form_register #btn_submit").prop('disabled', true);
			console.log("Form Valid...");
			var formdata = new FormData($(this)[0]);
			formdata.append("photo_data",$('#photoupload')[0].files[0]);
			formdata.append("name",$("#form_register #fullname").val());
			formdata.append("phone",$("#form_register #phoneNumber").val());
			formdata.append("email",$("#form_register #email").val());
			formdata.append("address",$("#form_register #address").val());
			formdata.append("username",$("#form_register #username").val());
			formdata.append("password",$("#form_register #password2").val());
			formdata.append("secques",$("#form_register #secques").val());
			formdata.append("secans",$("#form_register #secans").val());
			console.log("/setUser mapping call by ajax...");
			$.ajax({
				url:'login/saveUser',
				data: formdata,
				cache: false,
				contentType: false,
				processData: false,
				type:'post',
				success: function(data){
					console.log("Get response => "+data);
					$("#form_register #btn_submit").prop('disabled', false);
					$("#form_register #btn_submit").css("cursor", "default");
					// '0' - Successfully save user and sent email verification to user mail...
					 // '-1' - Same username existed already....
					 // '-2' - Server problem to create a user...
					 // '-3' - Error occurred due to server file upload reason...
					 // '-4' - User saved and confirmation code generated, but email can't send yet...
					 // '-5' - Can't set/fetch/send email verification Code...
					 // '-6' - Same email already exist...
					switch(data){
						case '0':		// '0' - Successfully save user and sent email verification to user mail...
							var email = $("#form_register #email").val();
							$("body").html(
									"<h1>User saved successfully, please check&amp;verify your email("+email+")...</h1>"+
									"Please wait 5 sec we will redirect you..."
							);
							window.setTimeout( function(){window.location.reload();}, 5000 );
							break;
						case '-1':		// '-1' - Same username existed already....
							$.msgBox({
								title: "Username Conflict !!",
								content: "Your choosen username already existed",
								type: "error",
								buttons: [{value: "OK"}],
								success: function(){
									$("#form_register #username").css("background-color",wrong_color);
									boolUser=false;
									RegErrorShow("Same username already exist...");
								}
							});
							break;
						case '-2':		// '-2' - Server problem to create a user...
							$.msgBox({
								title: "Server Error !!",
								content: "Server problem to create a user...",
								type: "error",
								buttons: [{value: "OK"}]
							});
							break;
						case '-3':		// '-3' - Error occurred due to server file upload reason...
							$.msgBox({
								title: "Server Upload Error !!",
								content: "Your choosen username already existed",
								type: "error",
								buttons: [{value: "OK"}]
							});
							break;
						case '-4':		// '-4' - User saved and confirmation code generated, but email can't send yet...
							$.msgBox({
								title: "Server Mail Error",
								content: "Server registration completed but email not send yet to confirm your account",
								type: "alert",
								buttons: [{value: "OK"}]
							});
							break;
						case '-5':		// '-5' - Can't set/fetch/send email verification Code...
							$.msgBox({
								title: "Server Mail Error",
								content: "User saved but server can't set/fetch or send email verification Code...",
								type: "error",
								buttons: [{value: "OK"}]
							});
							break;
						case '-6':		// '-6' - Same email already exist...
							$.msgBox({
								title: "Email Conflict !!",
								content: "Your choosen email already existed",
								type: "error",
								buttons: [{value: "OK"}],
								success: function(){
									$("#form_register #email").css("background-color",wrong_color);
									boolEmail=false;
									RegErrorShow("Same email already exist...");
								}
							});
							break;
					}
				}
			});
		}
	});
	
	//Name Validation
	$("#form_register #fullname").change(function(e){
		console.log("Fullname changed...");
		var name = $("#form_register #fullname").val();
		var re = new RegExp("^([A-Z][a-z]+[\.]?( [A-Z][a-z]+)+)$");
		if (re.test(name)){
			console.log("Phone Number is valid...");
			$("#form_register #fullname").css("background-color",correct_color);
			boolName=true;
		}else{
			$("#form_register #fullname").css("background-color",wrong_color);
			//console.log("Phone Number is not valid...");
			boolName=false;
		}
	});
	//PhotoPreview Method and Validation
	$("#form_register #photoupload").change(function(e){
		var ext = $('#photoupload').val().split('.').pop().toLowerCase();
		if($.inArray(ext, ['bmp','jpg','jpeg']) == -1){
			$("#photoupload").css("background-color",wrong_color);
			$("#photopreview").attr("src","./resources/images/user_photo/wrong.png");
			boolPhoto = false;
		}else{
			$("#photoupload").css("background-color",correct_color);
			boolPhoto = true;
			var reader = new FileReader();
		    reader.onload = function (e) {
		    	$("#photopreview").attr("src",e.target.result);
		    };
		    reader.readAsDataURL(this.files[0]);
		}
	});
	//PhoneNo validation
	$("#form_register #phoneNumber").change(function(e){
		console.log("Phone_no changed...");
		var phone = $("#form_register #phoneNumber").val();
		var re = new RegExp("^([0-9]{10})$");
		if (re.test(phone)){
			$("#form_register #phoneNumber").css("background-color",correct_color);
			console.log("Phone number is valid...");
			boolPhone=true;
		}else{
			$("#form_register #phoneNumber").css("background-color",wrong_color);
			//console.log("Phone number is not valid...");
			boolPhone=false;
		}
	});
	//Email validation
	$("#form_register #email").change(function(e){
		console.log("Email changed...");
		var email = $("#form_register #email").val();
		var re = new RegExp("^([A-z0-9]{3,}@[A-z0-9]{3,}[\.][A-z]{2,})$");
		if (re.test(email)){
			$("#form_register #email").css("background-color",correct_color);
			console.log("Email is valid...");
			boolEmail=true;
		}else{
			$("#form_register #email").css("background-color",wrong_color);
			//console.log("Email is not valid...");
			boolEmail=false;
		}
	});
	//Address validation
	$("#form_register #address").change(function(e){
		//console.log("Address changed...");
		var address = $("#form_register #address").val();
		var re = new RegExp("^([-A-z0-9\n\.\+\/ ]+)$");
		if (re.test(address)){
			$("#form_register #address").css("background-color",correct_color);
			console.log("Address is valid...");
			boolAddress=true;
		}else{
			$("#form_register #address").css("background-color",wrong_color);
			//console.log("Address is not valid...");
			boolAddress=false;
		}
	});
	//Username validation
	$("#form_register #username").change(function(e){
		console.log("Username changed...");
		var username = $("#form_register #username").val();
		var re = new RegExp("^([_a-z0-9\.-]{6,20})$");
		if (re.test(username)){
			$("#form_register #username").css("background-color",correct_color);
			console.log("Username is valid...");
			boolUser=true;
		}else{
			$("#form_register #username").css("background-color",wrong_color);
			//console.log("Username is not valid...");
			boolUser=false;
		}
	});
	
	//Password1 validation
	$("#form_register #password1").change(function(e){
		console.log("Password1 changed...");
		$("#form_register #password2").val("");
		$("#form_register #password2").css("background-color","WHITE");
		boolPass2=false;
		var password1 = $("#form_register #password1").val();
		var re = new RegExp("^([-_@A-z0-9\.]{6,20})$");
		if (re.test(password1)){
			$("#form_register #password1").css("background-color",correct_color);
			console.log("Password1 is valid...");
			boolPass1=true;
		}else{
			$("#form_register #password1").css("background-color",wrong_color);
			console.log("Password1 is not valid...");
			boolPass1=false;
		}
	});
	
	//Password2 validation
	$("#form_register #password2").change(function(e){
		console.log("Password2 changed...");
		var password2 = $("#form_register #password2").val();
		var re = new RegExp("^([-_@A-z0-9\.]{6,20})$");
		if (re.test(password2) && ($("#form_register #password1").val() == password2)){
			$("#form_register #password2").css("background-color",correct_color);
			console.log("Password2 is valid...");
			boolPass2=true;
		}else{
			$("#form_register #password2").css("background-color",wrong_color);
			console.log("Password2 is not valid...");
			boolPass2=false;
		}
	});
	
	//SecQues validation
	$("#form_register #secques").change(function(e){
		console.log("Secques changed...");
		$("#form_register #secans").val("");
		$("#form_register #secans").css("background-color","WHITE");
		boolAns=false;
		var secques = $("#form_register #secques").val();
		var re = new RegExp("^([A-z0-9 ]+[\?])$");
		if (re.test(secques)){
			$("#form_register #secques").css("background-color",correct_color);
			console.log("Secques is valid...");
			boolQues=true;
		}else{
			$("#form_register #secques").css("background-color",wrong_color);
			console.log("Secques is not valid...");
			boolQues=false;
		}
	});
	
	//SecAns validation
	$("#form_register #secans").change(function(e){
		console.log("Secans changed...");
		var secques = $("#form_register #secans").val();
		var re = new RegExp("^([A-z0-9 ]+)$");
		if (re.test(secans)){
			$("#form_register #secans").css("background-color",correct_color);
			console.log("Secans is valid...");
			boolAns=true;
		}else{
			$("#form_register #secans").css("background-color",wrong_color);
			console.log("Secans is not valid...");
			boolAns=false;
		}
	});
	$("#formLogin #error").on('click', '#btnResend', function(e){
		console.log("Resend button clicked...");
		resendEmail();
	});
});
function RegErrorShow(text){
	console.log(text);
	$("#form_register #reason").parent().show();
	$("#form_register #reason").html(text);
};

