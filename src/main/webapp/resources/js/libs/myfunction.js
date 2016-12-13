$(function () {
    $(window).scroll(function () {
        var top = $(window).scrollTop();
        $("div.social").css({ position: (top > 260 ? "fixed" : "absolute"), top: top > 260 ? 10 : 260 });
    });
});
function setCodeView(mainCode) {
    var code = mainCode.split('');
    var temp = "";
    var spanRed = "<span style=\"color:red;\">";
    var spanGreen = "<span style=\"color:green;\">";
    var spanClose = "</span>";
    var isOpen = false;
    var lastIndex = 0;
    for (var i = 0; i < code.length; i++) {
        if (code[i] == '"' || code[i] == '\'') {
            temp += (isOpen ? code[i] + spanClose : spanRed + code[i]);
            lastIndex = i;
            isOpen = !isOpen;
        }
        else if (code[i] == ',' || code[i] == ':' || code[i] == '{' || code[i] == '}') {
            temp += spanGreen + code[i] + spanClose;
        }
        else if (code[i] == '<') {
            temp += "&#060";
        }
        else if (code[i] == '>') {
            temp += "&#062";
        }
        else if (code[i] == '\n') {
            temp += i == 0 ? "" : (i == code.length - 1 ? "" : "<br/>");
        }
        else if (code[i] == ' ') {
            temp += ' ';
        }
        else {
            temp += code[i];
        }
    }
    return temp;
}

function SetCodeBlocks() {
    $("div.codeBlock>code").each(function (index, domEle) {
        var code = setCodeView($(this).html());
        $(this).html(code);
    });
}
$(function () {
    SetCodeBlocks();
});
function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length,c.length);
        }
    }
    return "";
}
function deleteAllCookies(){
	var cookies = document.cookie.split(";");
	for(var i=0; i < cookies.length; i++) {
	    var equals = cookies[i].indexOf("=");
	    var name = equals > -1 ? cookies[i].substr(0, equals) : cookies[i];
	    document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
	}
}