<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${basePath}static/lib/layui-src/css/layui.css" media="all">
    <link rel="stylesheet" href="${basePath}static/lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="${basePath}static/css/style.css" media="all">
</head>
<body class="layui-layout-body">
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main width_60">
        <form class="layui-form">
            <div class="layui-form-item">
                <div class="layui-form-item">
                    <div class="layui-upload-drag" id="excel">
                        <i class="layui-icon"></i>
                        <p>点击上传，或将文件拖拽到此处</p>
                        <hr>
                    </div>
                </div>
            </div>
            <%--<div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-primary layui-btn-sm data-add-btn">
                        <i class="fa fa-refresh"></i>
                        重置
                    </button>
                    <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-submit lay-filter="save">
                        <i class="fa fa-save"></i>
                        保存
                    </button>
                </div>
            </div>--%>
        </form>
    </div>
</div>
<script src="${basePath}static/lib/layui-src/layui.js" charset="utf-8"></script>
<script src="${basePath}static/js/lay-config.js?v=2.0.0" charset="utf-8"></script>
<script>
    layui.use(['layer','laydate','upload','form','jquery'], function () {
        var index = parent.layer.getFrameIndex(window.name);
        var $ = layui.jquery,
            layer = layui.layer,
            upload = layui.upload,
            form = layui.form;

        layer.msg("选择文件后自动上传",{time:3000},
            function(){
            });

        upload.render({
            //elem 是你定义的div 的id，进行绑定
            elem: '#excel',
            //url是你后端设置的RequestMapping地址
            url: '${basePath}uploadExcel',
            accept: 'file', //普通文件
            exts: 'xls|xlsx', //导入表格
            /*auto: false,*/  //选择文件后不自动上传
            //预读本地文件示例，不支持ie8

            before: function (obj) {
                layer.load(); //上传loading
            },
            error : function(){
                setTimeout(function () {
                    layer.msg("上传失败！", {
                        icon : 1
                    });
                    //关闭所有弹出层
                    layer.closeAll(); //疯狂模式，关闭所有层
                },1000);
            },
            //上传成功之后执行的方法
            done: function(data) {
                layer.msg(data.msg,{time:500},
                    function(){
                        parent.layer.close(index);
                    });
            }
        });
    });
</script>
</body>
</html>
</html>
