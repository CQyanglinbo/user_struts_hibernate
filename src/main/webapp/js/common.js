$(document).ready(function() {
	resizeWindow();
	$(window).resize(function() {
		resizeWindow();
	});
})
function resizeWindow() {
	var contentHeight=$(window).height()-80;
	$("#left").height(contentHeight);
	$("#right").height(contentHeight);
}

//注册界面的验证
function click1() {
	var name=document.getElementById("username").value;
	var pwd=document.getElementById("pwd").value;
	var rpwd=document.getElementById("pwd1").value;
	var pwd1=pwd.split("");
	var rpwd1=pwd.split("");
	if(name.length<2||name.length>20) {
		alert("用户名必须在2-20个字符之间");
		return false;
	}
	if(pwd1.length<6||pwd1.length>20||rpwd1.length<6||rpwd1.length>20) {
		alert("密码或重复密码必须在6-20个字符之间");
		return false;
	}
	if(pwd!=rpwd) {
		alert("密码与重复密码必须一致！");
		return false;
	}
	return true;
}