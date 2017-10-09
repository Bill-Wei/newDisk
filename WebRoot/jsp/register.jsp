<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-dojo-tags" prefix="sx"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/style.css">

<title>用户注册</title>

<sx:head/>
<script type="text/javascript">
	function refresh(){
		var img = document.getElementById("img_validation_code");
		img.src = "validation_code.action?"+Math.random();
	}
</script>
</head>
<body>
	<center>
		<div style="margin-top: 20px;margin-left: 20px;font-size: 20px;height: 50px;">
			用户注册界面
		</div>
		单机此处<a href="login_page.action">登录</a>
		<font color="red"> <s:actionerror/> </font>
		<s:form action="register" validate="true">
			<s:textfield label="用户名" cssClass="input_list" name="user" required="true" value=""/>
			<s:password label="密码" name="password" cssClass="input_list" required="true"/>
			<s:password label="请再次输入密码" name="repassword" cssClass="input_list" required="true"/>
			<s:textfield label="姓名" cssClass="input_list" name="xm" value=""/>
			<s:textfield label="邮箱地址" name="email" cssClass="input_list" value=""/>
			<s:textfield label="电话" name="phone" cssClass="input_list" value=""/>
			<s:textfield label="QQ" cssClass="input_list" name="qq" value=""/>
			<s:textfield label="验证码" name="validationCode" cssClass="input_list"/>
			<s:submit value="注册"/>
		</s:form>
		
		用户验证码：<img id="img_validation_code" src="calidation_code.action"/>
		<a href="#" onclick="refresh()">重新获取验证码</a>
	</center>
	
</body>
</html>