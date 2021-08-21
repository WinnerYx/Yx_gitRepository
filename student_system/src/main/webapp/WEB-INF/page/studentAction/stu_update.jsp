<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <input type="hidden" value="${studentAction.id}" name="id" id="id">
            <div class="layui-form-item">
                <label class="layui-form-label">学生活动</label>
                <div class="layui-input-block">
                    <input type="text" name="reason" lay-verify="required" value="${studentAction.reason}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">时间</label>
                <div class="layui-input-block">
                    <input type="text" name="days" lay-verify="required" value="${studentAction.days}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button id="bt" class="layui-btn layui-btn-primary layui-btn-sm data-add-btn">
                        <i class="fa fa-refresh"></i>
                        重置
                    </button>
                    <button id="bt1" class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-submit lay-filter="save">
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
    layui.use(['form','jquery'], function () {
        var form = layui.form,$ = layui.jquery;
        //获取窗口索引
        var index = parent.layer.getFrameIndex(window.name);
        //监听提交

        var id = $("#id").val();
        $("input").attr("readonly","readonly");
        $.ajax({
            url:"${basePath}studentAction/update_queryById",
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
                url:"${basePath}studentAction/update",
                type:"POST",
                contentType:'application/json',
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
