<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp" %>
<%@ include file="/common/jsp/easyUi.jsp" %>
<html>
<head>
<title>首页</title>
<link rel="stylesheet" type="text/css" href="/common/css/common.css">
<style type="text/css">
</style>
</head>
<body class="easyui-layout" >
	<!-- 顶部信息 -->
    <div class="easyui-layout" data-options="region:'north',border:false" style="height:50px;">
    	<div data-options="region:'east',iconCls:'icon-reload',border:false" style="width:200px;height:100%"></div>
    	<div data-options="region:'west',border:false" style="width:200px;height:100%;"></div>
    	<div data-options="region:'center',border:false" style="width:200px;height:100%;"></div>
    </div>
    <!-- 左侧菜单列表 -->   
    <div data-options="region:'west',title:'菜单列表',split:true" style="width:200px;">
    	<div class="easyui-accordion" style="width:-webkit-calc(100% - 20px);height:100%;">   
		    <div title="Title1" data-options="iconCls:'icon-save',selected:true" style="overflow:auto;padding:10px;">   
		        <h3 style="color:#0099FF;">Accordion for jQuery</h3>   
		        <p>Accordion is a part of easyui framework for jQuery.     
		        It lets you define your accordion component on web page more easily.</p>   
		    </div>   
		</div>
    </div>
    <!-- 中间内容页面 -->   
    <div data-options="region:'center'" style="padding:5px;background:#eee;"></div>   
</body>
<script type="text/javascript">
</script>
</html>