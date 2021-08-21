<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${basePath}static/lib/layui-src/css/layui.css" media="all">
    <link rel="stylesheet" href="${basePath}static/lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="${basePath}static/css/style.css" media="all">
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main width_60">
        <form class="layui-form">
            <div class="layui-form-item">
                <label class="layui-form-label">就业企业</label>
                <div class="layui-input-block">
                    <input type="text" name="company" lay-verify="required" value="${workStatus.company}" class="layui-input">
                    <input type="hidden" id = "stuId" name="stuId" value="${workStatus.stuId}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">就业时间</label>
                <div class="layui-input-block">

                    <input type="text" name="workTime" autocomplete="off" value='<fmt:formatDate value="${workStatus.workTime}" pattern="yyyy-MM-dd"/>' lay-verify="date" value="${workStatus.workTime}" id="workTime" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">薪资</label>
                <div class="layui-input-block">
                    <input type="text" name="salary" value="${workStatus.salary}" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-form-item">
                    <label class="layui-form-label">就业证明</label>
                    <div class="layui-upload-drag" id="headImg">
                        <i class="layui-icon"></i>
                        <p id="up">点击上传，或将文件拖拽到此处</p>
                        <hr>
                        <img  class="layui-upload-img" id="demo1" style="max-width: 100px">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
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
            </div>
        </form>
    </div>
</div>
<script src="${basePath}static/lib/layui-src/layui.js" charset="utf-8"></script>
<script src="${basePath}static/js/lay-config.js?v=2.0.0" charset="utf-8"></script>
<script>
    layui.use(['layer','laydate','upload','form','jquery','laydate'], function () {
        var sqlimgurl='';
        var stuId = '';
        var $ = layui.jquery,
            layer = layui.layer,
            upload = layui.upload,
            laydate=layui.laydate,
            form = layui.form;
        laydate.render({
            elem:"#workTime"
        })

        $("#up").click(function(){
            stuId = $("#stuId").attr('value');
        });

        //获取窗口索引
        var index = parent.layer.getFrameIndex(window.name);

        //拖拽上传
        upload.render({
            //elem 是你定义的div 的id，进行绑定
            elem: '#headImg',
            //url是你后端设置的RequestMapping地址
            url: '${basePath}upImage',
            //预读本地文件示例，不支持ie8
            //这个就是显示你选择图片后，前端img显示你的图片方法
            before: function(obj){
                obj.preview(function(index, file, result){
                    $('#demo1').attr('src', result); //图片链接（base64）
                });
            },
            //上传成功之后执行的方法
            done: function(res) {
                //对全局变量进行赋值，等后边表单提交的时候把值传给后台
                sqlimgurl=res.data.src
                //这个就是个消息提示方法可写 可不写
                $.post({
                    //后端RequestMapping地址
                    url: '${basePath}saveImage',
                    data:{
                        //参数对应后台接受的参数名称，把图片地址传给后台
                        'imgurl':sqlimgurl,
                        'stuId':stuId
                    },
                    //执行成功执行的方法
                    success: function (data) {
                        //ajax执行成功后提示添加成功
                        layer.msg('添加成功');
                    }
                })
                layer.msg('上传成功');
            }
        });
        //监听提交
        form.on('submit(save)', function (data) {
            $.ajax({
                url:"${basePath}workStatus/update",
                type:"POST",
                dataType:'json',
                data:data.field,
                success:function(data){
                    layer.msg(data.msg,{time:500},
                        function(){
                            parent.layer.close(index);
                    });
                }
            });
            return false;
        });

    });
</script>
</body>
</html>
