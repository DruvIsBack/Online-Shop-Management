$(document).ready(function(e){
	var time_left = 5;
	var interval = setInterval(function(){
		$("#timer_text").html("Please wait "+time_left+"sec");
		if(time_left==0){
			clearInterval(interval);
			window.location.href = "/osm";
		}else{
			time_left=time_left-1;
		}
		},1200);
});