<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生管理</title>
</head>
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main">
        <div style="margin: 10px">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">学号</label>
                        <div class="layui-input-inline">
                            <input type="text" id="stuNo" name="stuNo" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">专业</label>
                        <div class="layui-input-inline">
                            <select id="subjectId" name="subjectId" lay-filter="subjectId">
                                <option value="">请选择</option>
                                <c:forEach items="${subjects}" var="subject">
                                    <option value="${subject.id}">${subject.subjectName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">班级</label>
                        <div class="layui-input-inline">
                            <select id="clazzId" name="clazzId" lay-filter="clazzId">
                                <option value="">请选择</option>
                                <c:forEach items="${clazzes}" var="clazz">
                                    <option value="${clazz.id}">${clazz.clazzName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-primary"  lay-submit lay-filter="search-btn"><i class="layui-icon"></i> 搜 索</button>
                    </div>
                </div>
            </form>
        </div>
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add">
                    <i class="fa fa-plus"></i>
                    添加
                </button>
                <button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="update">
                    <i class="fa fa-pencil"></i>
                    修改
                </button>
                <button class="layui-btn layui-btn-warm layui-btn-sm" lay-event="uploadExcel">
                    <i class="layui-icon"></i>导入Excel
                </button>
                <%--<button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete">
                    <i class="fa fa-remove"></i>
                    删除
                </button>--%>
                <%--<button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="job">
                    <i class="fa fa-pencil"></i>
                    就业录入
                </button>--%>
            </div>
        </script>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
    </div>
</div>
<script>
    layui.use(['form', 'table','layer',"element", "laypage", "upload"], function () {
        var form = layui.form
            , table = layui.table
            , layer = layui.layer
            , $ = layui.jquery;
        form.render();
        table.render({
            elem: '#currentTableId',
            url: '${basePath}student/teacher_student',
            contentType:'application/json',
            method:"post",
            where:{
                teacherId:${teacherId}
            },
            toolbar: '#toolbar',
            defaultToolbar: ['filter', 'exports', 'print'],
            page: true,
            cols: [[
                {type: "checkbox", width: 50},
                /*{field: 'id', width: 80, title: 'ID'},*/
                {field: 'stuNo',  title: '学号'},
                {field: 'stuName', title: '姓名'},
                {field: 'cardNo',title: '身份证号'},
                {field: 'gender',title: '性别'},
                {field: 'phone',title: '手机号码'},
                {field: 'joinDate',title: '入学时间'},
                {field: 'subjectId',title: '专业',templet: '<div>{{d.subject.subjectName}}</div>'},
                {field: 'clazzId',title: '班级',templet: '<div>{{d.clazz.clazzName}}</div>'},
                {field: 'flag',title: '是否为贫困生'},
                {field: 'status',title: '状态'}
            ]],
            skin: 'line'
        });

        // 监听搜索操作
        form.on('submit(search-btn)', function (data) {
            console.log(data);

            //执行搜索重载
            table.reload('currentTableId', {
                contentType:'application/json',
                where: data.field
            }, 'data');
            return false;
        });
        /**
         * toolbar事件监听
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if(obj.event === 'uploadExcel'){

                layer.open({
                    type: 2,
                    title: 'Excel上传',
                    shadeClose: false,
                    shade: 0.8,
                    area: ['500px', '50%'],
                    content: 'upload',
                    /*btn: ['确定', '取消'],*/
                    end: function (index) {
                        table.reload('currentTableId');
                    }
                })
            }
            else if (obj.event === 'add') {   // 监听添加操作
                var index = layer.open({
                    title: '添加学生',
                    type: 2,
                    shade: 0.2,
                    shadeClose: false,
                    area: ['60%', '90%'],
                    content: 'student/add',
                    end:function(){
                        table.reload('currentTableId');
                    }
                });
            } else if (obj.event === 'update') {  // 监听修改操作
                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if(data.length !=1){
                    layer.msg("请选择一行数据修改",{time:1000});
                    return;
                }
                var index = layer.open({
                    title: '修改学生信息',
                    type: 2,
                    shade: 0.2,
                    area: ['60%', '90%'],
                    shadeClose: false,
                    content: 'student/detail/'+data[0].id,
                    end:function(){
                        table.reload('currentTableId');
                    }
                });
            }else if (obj.event === 'delete') { // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if(data.length ==0){
                    layer.msg("请选择行数据删除",{time:1000});
                    return;
                }
                var arr = [];
                for(index in data){
                    arr.push(data[index].id);
                }
                layer.confirm('确认删除学生记录？', function (index) {
                    $.ajax({
                        url:"${basePath}student/delete",
                        type:"POST",
                        dataType:'json',
                        data:"ids="+arr.join(","),
                        success:function(data){
                            layer.msg(data.msg,{time:500},
                                function(){
                                    table.reload("currentTableId");
                                });
                        }
                    });
                });
            }
        });
    });
</script>
</body>
</html>
