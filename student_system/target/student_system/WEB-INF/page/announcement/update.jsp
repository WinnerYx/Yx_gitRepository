<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <label class="layui-form-label">公告内容</label>
                <div class="layui-input-block">
                    <input type="text" name="content" lay-verify="required" value="${announcement.content}" class="layui-input">
                    <input type="hidden" name="id" value="${announcement.id}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">公告类型</label>
                <div class="layui-input-block">
                    <select name="type" lay-verify="required" value="${announcement.type}">
                        <option value="放假通知">放假通知</option>
                        <option value="放假通知">开会通知</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">发布时间</label>
                <div class="layui-input-block">
                    <input type="text" name="publishTime"  value='<fmt:formatDate value="${announcement.publishTime}" pattern="yyyy-MM-dd"/>' autocomplete="off" lay-verify="date"  placeholder="yyyy-MM-dd" id="publishTime" class="layui-input">
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
    layui.use(['form','jquery','laydate'], function () {
        var form = layui.form,$ = layui.jquery,laydate = layui.laydate;
        //获取窗口索引
        var index = parent.layer.getFrameIndex(window.name);
        //监听提交
        laydate.render({
            elem:"#publishTime"
        })
        var id = $("#id").val();
        $("input").attr("readonly","readonly");
        $.ajax({
            url:"${basePath}announcement/update_queryById",
            type:"POST",
            data:"id="+id,
            success:function (data){
                console.log(id);
                console.log(data);
                if(data.data.status=="已审批") {
                    layer.msg("该申请已经审批，无法修改");
                    $("#bt").hide();
                    $("#bt1").hide();
                }else{
                    $("input").removeAttr("readonly");
                }
            }
        });

        form.on('submit(save)', function (data) {

            $.ajax({
                url:"${basePath}announcement/update",
                type:"POST",
                contentType: "application/json",
                dataType:'json',
                data:JSON.stringify(data.field),
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
