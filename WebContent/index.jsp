<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/jsp/common.jsp" %>
<html>
<head>
<title>welcome</title>
<script src="${path}/common/js/jquery-1.3.2.min.js"></script>
<style type="text/css">
body center .line{ margin-top:20px; border-bottom:2px red solid}
</style>
</head>
<body>
	<center>
		<div class="line">
			<form action="${path}/system/testMapper.do" method="get">
				<input type="text" name="user.name" value="hahahha" />
				<input type="file" value="上传文件" />
				<input type="submit" value="提交表单" />	
			</form>
		</div>
		<div class="line">
			<div onclick="ajaxRequset()">异步请求</div>
		</div>
		<div class="line">
			<div onclick="ajaxTrue()">异步请求2</div>
		</div>
		<div class="line">
			<div onclick="ajaxFalse()">同步请求</div>
		</div>
	</center>
</body>
<script type="text/javascript">
function ajaxRequset(){
	$.post("${path}/system/testMapper.do",{"user.name":123},function(data,sta){
	})
}

function ajaxTrue(){
	var xmlhttp;
	if (window.XMLHttpRequest)
	{
	    //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
	    xmlhttp=new XMLHttpRequest();
	}
	else
	{
	    // IE6, IE5 浏览器执行代码
	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.open("GET","${path}/system/testMapper.do",true);
	xmlhttp.send();
}
function ajaxFalse(){
	var xmlhttp;
	if (window.XMLHttpRequest)
	{
	    //  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
	    xmlhttp=new XMLHttpRequest();
	}
	else
	{
	    // IE6, IE5 浏览器执行代码
	    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.open("GET","${path}/system/testMapper.do",false);
	xmlhttp.send();
}
</script>
</html>
