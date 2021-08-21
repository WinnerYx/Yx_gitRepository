<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生就业信息</title>
</head>
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main">
        <div style="margin: 10px">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">审批状态</label>
                        <div class="layui-input-inline">
                            <input type="text" name="status" class="layui-input">
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
                <%--<button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add">
                    <i class="fa fa-plus"></i>
                    添加
                </button>--%>
                <%--<button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="update">
                    <i class="fa fa-pencil"></i>
                    修改
                </button>--%>
                <button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="update">
                    <i class="fa fa-pencil"></i>
                    审批
                </button>
            </div>
        </script>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
    </div>
</div>
<script>
    layui.use(['form', 'table','jquery'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;
            form.render();
            table.render({
                elem: '#currentTableId',
                url: '${basePath}announcement/query',
                contentType:'application/json',
                method:"post",
                toolbar: '#toolbar',
                defaultToolbar: ['filter', 'exports', 'print'],
                page: true,
                cols: [[
                    {type: "checkbox", width: 50},
                    /*{field: 'id', width: 80, title: 'ID'},*/
                    {field: 'type',title: '公告类型'},
                    {field: 'content',title: '公告内容'},
                    {field: 'publishTime',title: '发布时间'},
                    {field: 'status',title: '审批状态'},
                    {field: 'result1',title: '审批结果'}
                ]],
                skin: 'line'
            });

        // 监听搜索操作
        form.on('submit(search-btn)', function (data) {
            console.log(data.field);
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
            if (obj.event === 'add') {   // 监听添加操作
                var index = layer.open({
                    title: '审批公告',
                    type: 2,
                    shade: 0.2,
                    shadeClose: false,
                    area: ['70%', '70%'],
                    content: '${basePath}announcement/add',
                    end:function(){
                        table.reload('currentTableId');
                    }
                });
            }else if (obj.event === 'update1') {  // 监听修改操作
                    var checkStatus = table.checkStatus('currentTableId');
                    var data = checkStatus.data;
                    if(data.length !=1){
                        layer.msg("请选择一行数据修改",{time:1000});
                        return;
                    }
                    var index = layer.open({
                        title: '审批公告',
                        type: 2,
                        shade: 0.2,
                        area: ['70%', '70%'],
                        shadeClose: false,
                        content: 'announcement/detail/'+data[0].id,
                        end:function(){
                            table.reload('currentTableId');
                        }
                    });
            }else if (obj.event === 'update') { // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if(data.length ==0){
                    layer.msg("请选择一行数据审批",{time:1000});
                    return;
                }
                var arr = [];
                for(index in data){
                    arr.push(data[index].id);
                }
                layer.confirm('确认审批公告？', function (index) {
                    $.ajax({
                        url:"${basePath}announcement/update",
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
