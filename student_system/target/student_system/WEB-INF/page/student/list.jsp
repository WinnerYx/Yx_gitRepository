<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>专业管理</title>
</head>
<body>
<div class="layuimini-container layuimini-page-anim">
    <div class="layuimini-main">
        <div style="margin: 10px">
            <form class="layui-form layui-form-pane">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" name="stuName" class="layui-input">
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
                <button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="check">
                    <i class="fa fa-pencil"></i>
                    查看详情
                </button>
                <button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="work">
                    <i class="fa fa-pencil"></i>
                    就业录入
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
                url: '${basePath}student/queryById',
                contentType:'application/json',
                method:"post",
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
                    {field: 'subjectName',title: '专业',templet: '<div>{{d.subject.subjectName}}</div>'},
                    {field: 'clazzName',title: '班级',templet: '<div>{{d.clazz.clazzName}}</div>'},
                    {field: 'clazzName',title: '任课教师',templet: '<div>{{d.teacher.teacherName}}</div>'},
                    {field: 'status',title: '状态'}
                ]],done: function(res, page, count){
                    //可以自行添加判断的条件是否选中
                    //这句才是真正选中，通过设置关键字LAY_CHECKED为true选中，这里只对第一行选中
                    res.data[0]["LAY_CHECKED"]='true';
                    //下面三句是通过更改css来实现选中的效果
                    var index= res.data[0]['LAY_TABLE_INDEX'];
                    $('tr[data-index=' + index + '] input[type="checkbox"]').prop('checked', true);
                    $('tr[data-index=' + index + '] input[type="checkbox"]').next().addClass('layui-form-checked');
                },
                skin: 'line'
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
            if (obj.event === 'check') {
                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if(data.length !=1){
                    layer.msg("请选择一行数据查看",{time:1000});
                    return;
                }
                var index = layer.open({
                    title: '查看信息',
                    type: 2,
                    shade: 0.2,
                    area: ['60%', '90%'],
                    shadeClose: false,
                    content: 'student/detail/'+data[0].id,
                    end:function(){
                        table.reload('currentTableId');
                    }
                });
            }else if (obj.event === 'work') {

                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if(data.length !=1){
                    layer.msg("请选择一行数据修改",{time:1000});
                    return;
                }
                var index = layer.open({
                    title: '就业信息',
                    type: 2,
                    shade: 0.2,
                    area: ['60%', '70%'],
                    shadeClose: false,
                    content: 'workStatus/detail/'+data[0].id,
                    end:function(){
                        table.reload('currentTableId');
                    }
                });
            }
        });
    });
</script>
</body>
</html>
