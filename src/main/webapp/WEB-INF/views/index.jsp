<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String basePath = path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
	<meta charset="utf-8" />
	<title>0529页面</title>
	<link rel="stylesheet" href="layui/css/layui.css" media="all">
	<script src="jquery-2.1.1.min.js"></script>
	<script src="jquery.min.js"></script>
	<script src="layui/layui.js" charset="utf-8"></script>
	
</head>
<body>
	<div>
		<form id="exportForm" action="">
			<span>用户名：</span>
			<input type="text" name="username" />
			<input type="file" id="keyword" name="file" lay-title="上传关键词" lay-type="file" class="layui-upload-file" />
			<input type="file" id="sitelist" name="file" lay-title="上传目标网站" lay-type="file" class="layui-upload-file" />
			<input type="file" id="whitelist" name="file" lay-title="上传白名单" lay-type="file" class="layui-upload-file" />
			<br><br>
			<input id="web" name="leibie" type="radio" checked="checked" style="margin-left: 270px" />网站
			<input id="weibo" name="leibie" type="radio"/>微博
			<input id="weixin" name="leibie" type="radio"/>微信
			<input type="button" id="export" name="export" value="导出数据" onclick="exportData()" />
		</form>
	</div>
</body>
<script type="text/javascript">
	
	function exportData(){
		$("#exportForm").submit();
	}

</script>
<script>
	layui.use('upload', function(){
		layui.upload({
		url: ''
		,ext: 'txt'
		, dataType: "json"
		,before: function(input){
			//返回的参数item，即为当前的input DOM对象
			console.log('文件上传中');
		}
		,elem: '#keyword' //指定原始元素，默认直接查找class="layui-upload-file"
		,method: 'POST' //上传接口的http类型
		,success: function(res){
			if(res.code ==1){
				layer.open({
						title: '提示'
						,content: '导入成功'
					});
				documentinfoinit();
			}else{
				layer.open({
						title: '提示'
						,content: res.message
					});
				documentinfoinit();
				}
	
			}
		});
	});
	
</script>
<script>
	layui.use('upload', function(){
		layui.upload({
		url: ''
		,ext: 'xls|xlsx'
		, dataType: "json"
		,before: function(input){
			//返回的参数item，即为当前的input DOM对象
			console.log('文件上传中');
		}
		,elem: '#sitelist' //指定原始元素，默认直接查找class="layui-upload-file"
		,method: 'POST' //上传接口的http类型
		,success: function(res){
			if(res.code ==1){
				layer.open({
						title: '提示'
						,content: '导入成功'
					});
				documentinfoinit();
			}else{
				layer.open({
						title: '提示'
						,content: res.message
					});
				documentinfoinit();
				}
	
			}
		});
	});
	
</script>
<script>
	layui.use('upload', function(){
		layui.upload({
		url: ''
		,ext: 'xls|xlsx'
		, dataType: "json"
		,before: function(input){
			//返回的参数item，即为当前的input DOM对象
			console.log('文件上传中');
		}
		,elem: '#whitelist' //指定原始元素，默认直接查找class="layui-upload-file"
		,method: 'POST' //上传接口的http类型
		,success: function(res){
			if(res.code ==1){
				layer.open({
						title: '提示'
						,content: '导入成功'
					});
				documentinfoinit();
			}else{
				layer.open({
						title: '提示'
						,content: res.message
					});
				documentinfoinit();
				}
	
			}
		});
	});
	
</script>