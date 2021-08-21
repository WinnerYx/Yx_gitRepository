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
            <div class="layui-form-item">
                <label class="layui-form-label">公告内容</label>
                <div class="layui-input-block">
                    <input type="text" name="content" lay-verify="required" class="layui-input">
                    <input type="hidden" name="status" value="暂未审批">
                    <input type="hidden" name="result1" value="暂未通过">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">公告类型</label>
                <div class="layui-input-block">
                    <select name="type" lay-verify="required">
                        <option value="">请选择公告类型</option>
                        <option value="放假通知">放假通知</option>
                        <option value="开会通知">开会通知</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">发布时间</label>
                <div class="layui-input-block">
                    <input type="text" name="publishTime" autocomplete="off" lay-verify="date"  placeholder="yyyy-MM-dd" id="publishTime" class="layui-input">
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
        var form = layui.form,$ = layui.jquery,laydate=layui.laydate;
        //获取窗口索引
        var index = parent.layer.getFrameIndex(window.name);

        laydate.render({
            elem:"#publishTime"
        })
        //监听提交
        form.on('submit(save)', function (data) {
            var json = {};
            json.content = data.field.content;
            json.publishTime = data.field.publishTime;
            json.type = data.field.type;
            json.status = data.field.status;
            json.result1 = data.field.result1;
            $.ajax({
                url:"${basePath}announcement/create",
                type:"POST",
                contentType:'application/json',
                dataType:'json',
                data:JSON.stringify(json),
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
