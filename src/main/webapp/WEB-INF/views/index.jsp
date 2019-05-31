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
	<title>CONNC</title>
	<link rel="stylesheet" href="js/layui/css/layui.css" media="all">
	<script src="js/jquery-2.1.1.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/layui/layui.js" charset="utf-8"></script>

</head>
<body>
<div>
	<form id="exportForm" action="">
		<br>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;用户名：</span>
		<input type="text" name="userName" />
		<br><br>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;关键词：</span>
		<textarea name="keyWord" style="height: 160px;width: 400px;"></textarea>
		<br><br>
		<span>目标网站：</span>
		<textarea name="siteList" style="height: 160px;width: 400px;"></textarea>
		<br><br>
		<span>网站类别：</span>
		<input id="web" name="siteType" type="radio" checked="checked" value="1" />普通网站
		<input id="weibo" name="siteType" type="radio" value="2" />微博
		<input id="weixin" name="siteType" type="radio" value="3" />微信
		<br><br>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="file" id="whitelist" name="file" lay-title="上传白名单" lay-type="file" class="layui-upload-file" />
		<input type="button" id="export" name="export" value="导出数据" onclick="exportData()" class="layui-btn layui-btn-normal" style="margin-left: 40px;" />
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
			url: '<%=basePath %>importWhiteList'
			,ext: 'zip'
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