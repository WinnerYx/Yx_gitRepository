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
                        <label class="layui-form-label">公告结果</label>
                        <div class="layui-input-inline">
                            <input type="text" name="result1" class="layui-input">
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
                <%--<button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="update">
                    <i class="fa fa-pencil"></i>
                    修改
                </button>--%>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete">
                    <i class="fa fa-remove"></i>
                    撤回
                </button>
            </div>
        </script>
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>
    </div>
</div>
<%--<script type="text/html" id="switchTpl">
    <input type="checkbox" name="checkflag" json={{ JSON.stringify(d) }} value="{{ d.checkflag }}" lay-skin="switch" lay-text="查看|查看" lay-filter="checkflagDemo" {{ d.useflag == true ? 'checked' : '' }}>
</script>--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">查看详情</a>
</script>
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
                    {field: 'result1',title: '审批结果'},
                    {fixed: 'right', width:150, align:'center', toolbar: '#barDemo'}
                    /*{field: 'checkflag',title: '查看详情',templet:'#switchTpl'}*/
                ]],
                skin: 'line'
            });


        $(document).on('click','#check1',function(){

        });

        /*form.on('switch(checkflagDemo)', function () {
            console.log(this.getAttribute("json"));
        });*/

        table.on('tool(currentTableFilter)', function(obj){ //注：tool 是工具条事件名，table-layer-Filter是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

            if(layEvent=== 'detail'){
                var title = "公告详情",
                    noticeTime = data.publishTime,
                    content = data.content;
                var html = '<div style="padding:15px 20px; text-align:justify; line-height: 22px;border-bottom:1px solid #e2e2e2;background-color: #2f4056;color: #ffffff">\n' +
                    '<div style="text-align: center;margin-bottom: 20px;font-weight: bold;border-bottom:1px solid #718fb5;padding-bottom: 5px"><h4 class="text-danger">' + title + '</h4></div>\n' +
                    '<div style="font-size: 12px">' + content + '</div>\n' +
                    '</div>\n';
                parent.layer.open({
                    type: 1,
                    title: '公告' + '<span style="float: right;right: 1px;font-size: 12px;color: #b1b3b9;margin-top: 1px">' + noticeTime + '</span>',
                    area: '300px;',
                    shade: 0.8,
                    id: 'layuimini-notice',
                    btn: [ '关闭'],
                    btnAlign: 'c',
                    moveType: 1,
                    content: html,
                    success: function (layero) {
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').attr({
                            target: '_blank'
                        });
                    }
                });
            }

        });

        // 监听搜索操作
        form.on('submit(search-btn)', function (data) {
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
                    title: '添加公告',
                    type: 2,
                    shade: 0.2,
                    shadeClose: false,
                    area: ['70%', '70%'],
                    content: '${basePath}announcement/add',
                    end:function(){
                        table.reload('currentTableId');
                    }
                });
            }else if (obj.event === 'update') {  // 监听修改操作
                    var checkStatus = table.checkStatus('currentTableId');
                    var data = checkStatus.data;
                    if(data.length !=1){
                        layer.msg("请选择一行数据修改",{time:1000});
                        return;
                    }
                    var index = layer.open({
                        title: '公告修改',
                        type: 2,
                        shade: 0.2,
                        area: ['70%', '70%'],
                        shadeClose: false,
                        content: 'announcement/detail/'+data[0].id,
                        end:function(){
                            table.reload('currentTableId');
                        }
                    });
            }else if (obj.event === 'delete') { // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if(data.length ==0){
                    layer.msg("请选择行数据撤回",{time:1000});
                    return;
                }
                var arr = [];
                for(index in data){
                    arr.push(data[index].id);
                }
                layer.confirm('确认撤回该公告？', function (index) {
                    $.ajax({
                        url:"${basePath}announcement/delete",
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
