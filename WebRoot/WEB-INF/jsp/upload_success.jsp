<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<form action="main_page.action" method="post" name="mainForm">
		<input type="hidden" value="<s:property value='uploadPath'/>" name="current_path"/>
	</form>
	
	<script type="text/javascript">
		alert("上传成功");
		mainForm.submit();
	</script>
</body>
</html>