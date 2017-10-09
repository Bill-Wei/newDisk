<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>网盘</title>
<!--引入关于JavaScript和css文件  -->
<script type="text/javascript" src="javascript/prototype.js"></script>
<script type="text/javascript" src="javascript/common.js"></script>
<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
	<body>
		<!-- 调用上传文件页面的form -->
		<form action="upload_page.action" method="post" name="uploadForm">
			<input type="hidden" value="" name="uploadPath"/>
		</form>
		<!-- 用于下载文件的iframe -->
		<iframe src="" id="downloadFrame" style="visibility: hidden;height: 0px;width: 0px;">
		</iframe>
		<table width="100%">
			<tr>
				<td>
					<div style="height: 30px;margin-top: 10px;margin-left: 10px;">
						<!-- 在隐藏表单中保存当前路径 -->
						<c:choose>
							<c:when test="${param.current_path==null}">
								<input type="hidden" value="/" name="txt_path"/>
							</c:when>
							<c:otherwise>
								<input type="hidden" value="${param.current_path}" name="txt_path">
							</c:otherwise>
						</c:choose>
						<!-- 下面是主页面上的各种按钮的表单域，每个按钮调用了一个JavaScript函数 -->
						<input id="btn_previous" value="返回上一级目录" type="button"
							onclick="previous()"/> &nbsp;&nbsp;
						<input id="btn_upload" value="上传" type="button" onclick="goUpload()"/>
						&nbsp;&nbsp;
						<input id="btn_download" value="下载" type="button" onclick="downloadMoreFile()"/>
						&nbsp;&nbsp;
						<input id="btn_create_dir" value="新建文件夹" type="button" 
						onclick="showCreateDorDialog()"/>&nbsp;&nbsp;
						<input id="btn_delete" value="删除" type="button" onclick="deletePath()"/> 
						&nbsp;&nbsp;
						<input id="btn_delete" value="已使用空间" type="button" onclick="getUsedSize()"/>
						&nbsp;&nbsp;
						<input id="btn_relogin" value="重新登录" type="button" onclick="relogin()"/>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div id="div_current_path" style="height: 30px;margin-top: 10px;margin-left: 10px;">
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<table id="tbl_list" width="700" order="0" cellspacing="0" cellpadding="0">
						<!-- 显示文件和目录头 -->
						<tr bgcolor="#DDDDDD" style="font: bold;">
							<td width="50%">
								<input type="checkbox" id="checkbox_head" onclick="checkAll(this)"/>
								&nbsp;目录</td>
							<td width="30%">上传时间</td>
							<td style="text-align: right;">大小</td>
						</tr>
					</table>
				</td>
			</tr>	
		</table>
	<script type="text/javascript">
		//向tbl_list表中添加当前目录的子目录和问件
		jsonLoadDirAndFile();
		//在主页面上显示当前路径
		showCurrentPath();
	</script>
	</body>
</html>