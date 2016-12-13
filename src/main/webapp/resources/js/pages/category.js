$(document).ready(function(){
	console.log("Category page loaded successfully...");
	
	//Home button click event...
	$(".my_path #home_page").click(function(){
		$(location).attr('href', './');
	});
	
	//Category click event...
	$(".my_module").click(function(e){
		var categoryId = $(this).attr('id');
		$(location).attr('href', './'+categoryId);
	});
	
	//Login/register button click event...
	$("#btn_login_register").click(function(){
		console.log("LoginRegister button clicked...");
		console.log("Redirect from => "+$(location).attr('pathname')); 
		setCookie("redirect_from", $(location).attr('pathname'), "1");
		$(location).attr('href', './login');
	});
	
	//Logout button click event...
	$("#btn_logout").click(function(){
		console.log("Logout clicked...");
		setCookie("username", "", "0");
		setCookie("password", "", 0);
		$.ajax({
			type: 'post',
			url: 'login/clearSession',
			success: function(data){
				if(data == "1"){
					setCookie("JSESSIONID", "", 0);
					console.log("Clear alls...");
				}else{
					console.log("Something ERROR occured...");
				}
				location.reload();
			}
		});
	});
});