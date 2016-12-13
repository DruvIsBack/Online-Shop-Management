$(document).ready(function(){
	console.log("Sub-category page loaded successfully...");
	
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
	
	//Login/register button click event...
	$("#btn_login_register").click(function(){
		console.log("LoginRegister button clicked...");
		console.log("Redirect from => "+$(location).attr('pathname')); 
		setCookie("redirect_from", $(location).attr('pathname'), "1");
		$(location).attr('href', './login');
	});
	
	//Home button click event...
	$(".my_path #home_page").click(function(){
		$(location).attr('href', './');
	});
	
	//Subcategory button click event...
	$(".my_path #current_page").click(function(){
		location.reload();
	});
	
	//Sub-category click event...
	$(".my_module").click(function(e){
		var categoryId = $(this).attr('id');
		var path = './'+$(location).attr('pathname').replace("/osm/","")+"/";
		$(location).attr('href', path + categoryId);
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