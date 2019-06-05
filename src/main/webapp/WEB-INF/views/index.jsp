<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String basePath = path + "/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>CNOOC</title>
    <link rel="stylesheet" href="js/layui/css/layui.css" media="all">
    <script src="js/jquery-2.1.1.min.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="js/layui/layui.js" charset="utf-8"></script>

</head>
<body>
<div>
    <form id="exportForm" action="<%=basePath %>exportData">
        <br>
        <span>&nbsp;&nbsp;&nbsp;&nbsp;用户名：</span>
        <input id="userName" type="text" name="userName"/>
        <span style="color: red;">（*必填）</span>
        <br><br>
        <span>&nbsp;&nbsp;&nbsp;&nbsp;关键词：</span>
        <textarea name="keyWord" style="height: 160px;width: 400px;"></textarea>
        <span style="color: red;">（提示：请将关键词用英文逗号隔开填入框内。）</span>
        <br><br>
        <span>目标网站：</span>
        <textarea name="siteList" style="height: 160px;width: 400px;"></textarea>
        <span style="color: red;">（提示：请将目标网站的主编码用英文逗号隔开填入框内。）</span>
        <br><br>
        <span>网站类别：</span>
        <input id="web" name="siteType" type="radio" checked="checked" value="0"/>外部网站
        <input id="intranet" name="siteType" type="radio" value="1"/>内部网站
        <input id="weibo" name="siteType" type="radio" value="2"/>微博
        <input id="weixin" name="siteType" type="radio" value="3"/>微信
        <br><br>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="file" id="whitelist" disabled name="file" lay-title="上传白名单" lay-type="file"
               class="layui-upload-file"/>
        <input type="button" id="export" name="export" value="导出数据" onclick="exportData()"
               class="layui-btn layui-btn-normal" style="margin-left: 40px;"/>
    </form>
    <div id="loading"  style="width: 100%;background: rgba(0, 0, 0, 0.22);height: 100%;position: fixed;top: 0px;left: 0px;display: none">
        <img title="Adapter正在运行" style="position: absolute;top: 50%;left: 50%;margin-left: -64px;margin-top: -64px;" src="<%=request.getContextPath()%>/images/loader2.gif">
    </div>
</div>

</body>
<script type="text/javascript">

    $("#userName").blur(function () {
        if (this.value != null && this.value != "" && this.value.trim() != null && this.value.trim() != "") {
            $("#whitelist").attr("disabled", false);
        } else {
            $("#whitelist").attr("disabled", true);
        }

    })

    function exportData() {
        if ($("#userName").val() != null && $("#userName").val() != "" && $("#userName").val().trim() != null && $("#userName").val().trim() != "") {
            $('#loading').show();
            $.ajax({
                type: "POST",
                url: "<%=basePath %>exportData",
                data: $("#exportForm").serialize(),
                success: function (result) {
                    if(result=="true"||result.equals("true")){
                        $('#loading').hide();
                    }else{
                        alert("导出失败！");
                    }
                }
            });
        } else {
            alert("用户名不能为空！");
        }

    }

</script>
<script>
    layui.use('upload', function () {
        layui.upload({
            url: '<%=basePath %>importWhiteList'
            , ext: 'xls|xlsx'
            , dataType: "json"
            , before: function (input) {
                //返回的参数item，即为当前的input DOM对象
                console.log('文件上传中');
            }
            , elem: '#whitelist' //指定原始元素，默认直接查找class="layui-upload-file"
            , method: 'POST' //上传接口的http类型
            , success: function (res) {
                console.log(res);
                if (res.code == 1) {
                    layer.open({
                        title: '提示'
                        , content: '导入成功'
                    });
                } else {
                    layer.open({
                        title: '提示'
                        , content: res.message
                    });
                }

            }
        });
    });

</script>