<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <label class="layui-form-label">工作岗位</label>
                <div class="layui-input-block">
                    <select id="name" name="name" lay-filter="jobId">
                        <option value="">请选择</option>
                        <c:forEach items="${jobs}" var="job">
                            <option value="${job.id}">${job.name}</option>
                        </c:forEach>
                    </select>
                    <input type="hidden" name="stuId" value="${stuId}">
                    <input type="hidden" name="jobStatus" value="暂未审批">
                    <input type="hidden" name="result1" value="暂未通过">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">工作地点</label>
                <div class="layui-input-block">
                    <input type="text" id="workPlace" name="workPlace" lay-verify="required" class="layui-input" readonly unselectable="on">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">工作时间</label>
                <div class="layui-input-block">
                    <input type="text" id="workTime" name="workTime" class="layui-input" readonly unselectable="on">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">薪资</label>
                <div class="layui-input-block">
                    <input type="text" id="salary" name="salary" class="layui-input" readonly unselectable="on">
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
    layui.use(['form','jquery'], function () {
        var form = layui.form,$ = layui.jquery;
        //获取窗口索引
        var index = parent.layer.getFrameIndex(window.name);

        form.on('select(jobId)', function (data) {
            console.log(data)
            $('#workPlace').empty();
            $('#workTime').empty();
            $('#salary').empty();
            if(data.value != ''){
                $.ajax({
                    url:"${basePath}job/queryJobById",
                    type:"POST",
                    contentType:'application/json',
                    dataType:'json',
                    data:JSON.stringify({"id":data.value}),
                    success:function(data){
                        console.log(data);
                        $('#workPlace').val(data.data.workPlace);
                        $('#workTime').val(data.data.workTime);
                        $('#salary').val(data.data.salary);
                    }
                });
            }else{
                $('#workPlace').val('');
                $('#workTime').val('');
                $('#salary').val('');
            }
            return false;
        });
        //监听提交
        form.on('submit(save)', function (data) {
            var json = {};
            json.name = data.field.name;
            json.workPlace = data.field.workPlace;
            json.workTime = data.field.workTime;
            json.salary = data.field.salary;
            json.jobStatus = data.field.jobStatus;
            json.result1 = data.field.result1;

            $.ajax({
                url:"${basePath}job/create",
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
