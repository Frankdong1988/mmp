<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/common.jsp" %>
<%@ include file="/common/jsp/easyUi.jsp" %>
<html>
<head>
<title>登录</title>
<link rel="stylesheet" type="text/css" href="/common/css/common.css">
<style type="text/css">
#content>div{font-size:40px;margin:20px 0; color:green;letter-spacing:10px; font-weight:bold}
#content>form{margin-top:80px;text-align:center;}
#content>form>.input{margin:20px auto; background-color:#fff;border:1px #aaa solid;text-align:center;width:250px;height:48px;border-radius:21px 21px;}
#content>form>.input>input{border:0; width:-webkit-calc(100% - 20px);height:-webkit-calc(100% - 4px);outline:none;margin-top:2px; font-size:18px; line-height:-webkit-calc(100% - 4px);}
#content>form>.button{width:250px;height:48px;margin:20px auto;text-align:center;}
#content>form>.button>input{width:-webkit-calc(50% - 20px);height:-webkit-calc(100% - 5px);border:0;outline:none; text-align:center; font-size:18px; border-radius:20px 20px;cursor:pointer}
#content>form>.button>.inputl{float:left;border:1px #aaa solid;}
#content>form>.button>.inputr{float:right; background-color:green;color:#fff;}
#content>form>.backMsg{color:red;margin:0;padding:0;height:20px}
</style>
</head>
<body>
	<div style="height:100%" class="easyui-layout" >
		<!-- 顶部信息 -->
	    <div data-options="region:'north',border:false" style="height:100px;"></div>
	    <!-- 中间内容页面 -->
	    <div id="content" data-options="region:'center'" style="background:#eee;text-align:center">
	    	<div>一锅乱炖运营平台</div>
	    	
	    	<form method="post" action="/system/userLogin.do">
	    		<div class="backMsg">${map.message}</div>
			    <div class="input">   
			        <input  type="text" name="account" placeholder="请输入帐号" />
			    </div>   
			    <div class="input">   
			        <input type="password" name="psw" placeholder="请输入密码" />
			    </div>
			    <div class="button">
			    	<input class="inputl" type="reset" onclick="clearForm()" value="重置" />
			    	<input class="inputr" type="button" onclick="checkData(this)" value="登录" />
			    </div>   
			</form>
	    </div>
	    <div data-options="region:'south',border:false" style="height:80px;">
		    <%@ include file="/common/jsp/pageCompanyInfo.jsp" %>
	    </div>
    </div>
</body>
<script type="text/javascript">
function checkData(obj){
	var dataCheck = false;
	$("form").find(".input").children().each(function(index,ob){
		if(!$(ob).val()){
			dataCheck = true;
		}
	})
	if(dataCheck){
		return $.messager.alert('提示消息','用户名或密码不能为空'); 
	}
	var account = $('input[name="account"]').val();
	if(!/[0-9a-zA-Z]{3,20}/.test(account)){
		return $.messager.alert('提示消息','用户名格式不正确'); 
	}
	$("form").submit();
}
function clearForm(){
	$("form>.backMsg").text("");
}
</script>
</html>
