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
<div style="margin-left: 60px;">
    <form id="exportForm" action="<%=basePath %>exportData">
        <br>
        <span style="width: 20px;">用户名：</span>
        <input id="userName" type="text" name="userName"/>
        <span style="color: red;">（*导出数据必填）</span>
        <br><br>
        <span style="width: 20px;">关键词：</span>
        <textarea id="keyWord" name="keyWord" style="height: 160px;width: 400px;"></textarea>
        <span style="color: red;">（提示：请将关键词用英文逗号隔开填入框内。）</span>
        <br><br>
        <span style="width: 20px;">目标网站：</span>
        <textarea id="siteList" name="siteList" style="height: 160px;width: 400px;"></textarea>
        <span style="color: red;">（提示：请将目标网站的主编码用英文逗号隔开填入框内。）</span>
        <br><br>
        <span style="width: 20px;">网站类别：</span>
        <input id="web" name="siteType" type="radio" checked="checked" value="0"/>外部网站
        <input id="intranet" name="siteType" type="radio" value="1"/>内部网站
        <input id="weibo" name="siteType" type="radio" value="2"/>微博
        <input id="weixin" name="siteType" type="radio" value="3"/>微信
        <br/><br/>
        <div style="display: inline-block;margin-left: 40px;width: 2px;"></div>
        <input type="file" id="whitelist" name="file" lay-title="上传白名单" lay-type="file"
               class="layui-upload-file"/>
        <input type="button" id="export" name="export" value="导出数据" onclick="exportData()"
               class="layui-btn layui-btn-normal" style="margin-left: 40px;"/>
    </form>
    <div id="loading"  style="width: 100%;background: rgba(0, 0, 0, 0.22);height: 100%;position: fixed;top: 0px;left: 0px;display: none">
        <img title="Adapter正在运行" style="position: absolute;top: 50%;left: 50%;margin-left: -64px;margin-top: -64px;" src="<%=basePath %>/images/loader2.gif">
    </div>
</div>

</body>
<script type="text/javascript">

    /*$("#userName").blur(function () {
        if (this.value != null && this.value != "" && this.value.trim() != null && this.value.trim() != "") {
            $("#whitelist").attr("disabled", false);
        } else {
            $("#whitelist").attr("disabled", true);
        }

    })*/

    function exportData() {
        var username = $("#userName").val();
        var keyword = $("#keyWord").val();
        var siteList = $("#siteList").val();
        if (username != null && username != "" && username.trim() != null && username.trim() != "") {
            if (username=="cnoocadmin"){
                var ifexport = confirm("是否导出数据？(数据量较大，请耐心等待。)")
                if (ifexport == true) {
                    $("#exportForm").submit();
                }else{

                }
            }else{
                if(keyword == null || keyword == "" || keyword.trim() == null || keyword.trim() == ""){
                    alert("关键词不能为空！");
                }/*else if (siteList == null || siteList == "" || siteList.trim() == null || siteList.trim() == "") {
                    alert("目标网站不能为空！");
                }*/else{
                    var ifexport = confirm("是否导出数据？(数据量较大，请耐心等待。)")
                    if (ifexport == true) {
                        $("#exportForm").submit();
                    }else{

                    }
                }
            }

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