<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main">
        <script type="text/html" id="toolbar">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add">
                    <i class="fa fa-plus"></i>
                    添加
                </button>
                <button id="audit" class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="update">
                    <i class="fa fa-pencil"></i>
                    修改
                </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete">
                    <i class="fa fa-remove"></i>
                    撤回
                </button>
            </div>
        </script>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
    </div>
</div>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;
        table.render({
            elem: '#currentTableId',
            url: '${basePath}request/stu_list',
            contentType:'application/json',
            method:"post",
            toolbar: '#toolbar',
            defaultToolbar: ['filter', 'exports', 'print'],
            page: true,
            cols: [[
                {type: "checkbox", width: 50},
                /*{field: 'id', width: 80, title: 'ID'},*/
                {field: 'stuName',  title: '学生姓名',templet: '<div>{{d.student.stuName}}</div>'},
                {field: 'reason',  title: '请假原因'},
                {field: 'days',  title: '请假天数'},
                {field: 'status',  title: '审批状态'},
                {field: 'result1',  title: '审批结果'}
            ]],
            skin: 'line'
        });


        // 监听搜索操作
        form.on('submit(search-btn)', function (data) {
            //执行搜索重载
            console.log(data.field);
            table.reload('currentTableId', {
                contentType:'application/json',
                where: data.field
            }, 'data');
            return false;
        });
        form.on('submit(audit-btn)', function (data) {
            console.log(data);
        });

        /**
         * toolbar事件监听
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {   // 监听添加操作
                var index = layer.open({
                    title: '新增请假',
                    type: 2,
                    shade: 0.2,
                    shadeClose: false,
                    area: ['50%', '50%'],
                    content: 'request/stuRequestAdd',
                    end:function(){
                        table.reload('currentTableId');
                    }
                });
            } else if (obj.event === 'update') {  // 监听修改操作
                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if(data.length !=1){
                    layer.msg("请选择一行请假数据修改",{time:1000});
                    return;
                }
                var index = layer.open({
                    title: '修改请假',
                    type: 2,
                    shade: 0.2,
                    area: ['50%', '50%'],
                    shadeClose: false,
                    content: 'request/detail/'+data[0].id,
                    end:function(){
                        table.reload('currentTableId');
                    }
                });
            }else if (obj.event === 'delete') { // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if(data.length ==0){
                    layer.msg("请选择行请假数据撤回",{time:1000});
                    return;
                }
                var arr = [];
                for(index in data){
                    arr.push(data[index].id);
                }
                layer.confirm('确认撤回该条记录？', function (index) {
                    $.ajax({
                        url:"${basePath}request/delete",
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
