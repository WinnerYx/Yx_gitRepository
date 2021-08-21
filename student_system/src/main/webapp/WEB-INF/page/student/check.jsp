<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${basePath}static/lib/layui-src/css/layui.css" media="all">
    <link rel="stylesheet" href="${basePath}static/lib/font-awesome-4.7.0/css/font-awesome.min.css" media="all">
    <link rel="stylesheet" href="${basePath}static/css/style.css" media="all">
    <script type="text/javascript">
    </script>
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main width_60">
        <form class="layui-form">
            <input type="hidden" name="id" value="${student.id}">
            <div class="layui-form-item">
                <label class="layui-form-label">所属专业</label>
                <div class="layui-input-block">
                    <select name="subjectId" lay-filter="subjectId" lay-verify="required">
                        <option value="">请选择</option>
                        <c:forEach items="${subjects}" var="subject">
                            <option value="${subject.id}" <c:if test="${student.subjectId == subject.id}">selected</c:if>>${subject.subjectName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">班级</label>
                <div class="layui-input-block">
                    <select name="clazzId" id="clazzId" lay-filter="clazzId" lay-verify="required">
                        <option value="">请选择</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">入学时间</label>
                <div class="layui-input-block">
                    <input type="text" name="joinDate" autocomplete="off" value='<fmt:formatDate value="${student.joinDate}" pattern="yyyy-MM-dd"/>' lay-verify="date"  placeholder="yyyy-MM-dd" id="joinDate" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">学号</label>
                <div class="layui-input-block">
                    <input type="text" name="stuNo" class="layui-input" value="${student.stuNo}">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="stuName" value="${student.stuName}" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">是否贫困生</label>
                <div class="layui-input-block">
                    <select name="flag" lay-verify="required">
                        <option value="是">是</option>
                        <option value="否">否</option>
                    </select>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">身份证号</label>
                <div class="layui-input-block">
                    <input type="text" name="cardNo" value="${student.cardNo}" lay-verify="identity" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <select name="gender" lay-verify="required">
                        <option value="男" <c:if test="${student.gender == '男'}">selected</c:if>>男</option>
                        <option value="女" <c:if test="${student.gender == '女'}">selected</c:if>>女</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-block">
                    <input type="text" name="phone" value="${student.phone}" lay-verify="phone" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">父母姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="pname" value="${student.pname}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">联系电话</label>
                <div class="layui-input-block">
                    <input type="text" name="telephone" value="${student.telephone}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">家庭地址</label>
                <div class="layui-input-block">
                    <input type="text" name="addr" value="${student.addr}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button id = "change" class="layui-btn layui-btn-primary layui-btn-sm data-add-btn" lay-submit lay-filter="change">
                        <i class="fa fa-refresh">请求修改</i>
                    </button>
                    <button id = "close" class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-filter="close">
                        <i id = "button1" class="fa fa-close">关闭</i>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="${basePath}static/lib/layui-src/layui.js" charset="utf-8"></script>
<script src="${basePath}static/js/lay-config.js?v=2.0.0" charset="utf-8"></script>
<script>
    layui.use(['layer','form','jquery'], function () {
        var form = layui.form,$ = layui.jquery,layer = layui.layer;
        var power = "否";
        //获取窗口索引
        var index = parent.layer.getFrameIndex(window.name);

        $("select").attr("disabled","disabled");
        form.render('select');
        $("input").attr("readonly","readonly");
        form.render('input');
        //监听提交
        $.ajax({
            url:"${basePath}teacher/queryByTeacherId",
            type:"POST",
            success:function (data){
                if(data.data.power=="1"){
                    $("input").removeAttr("readonly");
                    $("select").removeAttr("disabled");
                    $("#change").empty();
                    $("#change").append('<i id = "change" class="fa fa-refresh">修改</i>');
                    power = "1";
                }else{
                    power = "0";
                }
            }
        });

        $(document).on('click',"#close",function(){
            parent.layer.close(index);
        });

        form.on('submit(change)', function (data) {
            console.log(data);
            if(power!=1){
                $.ajax({
                    url:"${basePath}power/needPower",
                    type:"GET",
                    success:function(){
                        alert("请求已发送,等待管理员授权");
                        parent.layer.close(index);
                    }
                });
            }else{
                $.ajax({
                url:"${basePath}student/update",
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
            }
            return false;
        });

        //初始化班级列表
        initClazzList();
        function initClazzList(data){
            $('#clazzId').empty();
            var that = data;
            var subjectId;
            if(!data){
                subjectId = "${student.subjectId}";
            }else{
                subjectId = data.value;
            }
            $.ajax({
                url:"${basePath}clazz/query",
                type:"POST",
                contentType:'application/json',
                dataType:'json',
                data:JSON.stringify({"subjectId":subjectId}),
                success:function(data){
                    $('#clazzId').append('<option value="">请选择</option>');
                    data.data.forEach(function(value,index,array){
                        var selected;
                        console.log(!that);
                        if(!that){
                            if(value.id == "${student.clazzId}"){
                                selected = "selected";
                            }
                        }
                        $('#clazzId').append('<option '+selected+' value='+value.id+'>'+value.clazzName+'</option>');
                    })
                    form.render();
                }
            });
        }

        form.on('select(subjectId)', function (data) {
            initClazzList(data);
        });

    });
</script>
</body>
</html>
