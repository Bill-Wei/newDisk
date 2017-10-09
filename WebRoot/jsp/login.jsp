<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/style.css">
<title>用户登录</title>
<script type="text/javascript">
	function refresh(){
		var img = document.getElementById("img_validation_code");
		img.src = "validate_code.action?"+Math.random();
	}
</script>
</head>
<body>
	<center>
		<div style="margin-top: 20px;margin-left: 20px;font-size: 20px;height: 50px;">
			用户登录页面
		</div>
		如果您还没有注册，单机此处<a href="register_page.action">注册</a>
		<font color="red"><s:actionerror/></font>
		<s:form action="login">
			<s:textfield label="用户名" cssClass="input_list" name="user"/>
			<s:password label="密码" name="password" cssClass="input_list"/>
			<s:textfield label="验证码" name="validationCode" cssClass="input_list"/>
			<s:submit value="登录"/>
		</s:form>
		
		用户验证码：<img id="img_validation_code" src="validate_code.action"/>
		<a href="#" onclick="refresh()">重新获取验证码</a>
	</center>
	
</body>
</html>