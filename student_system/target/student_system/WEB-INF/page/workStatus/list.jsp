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
                        <label class="layui-form-label">姓名</label>
                        <div class="layui-input-inline">
                            <input type="text" id = "stuName" name="stuName" class="layui-input">
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
                <button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="update">
                    <i class="fa fa-pencil"></i>
                    修改
                </button>
                <button class="layui-btn layui-btn-sm layui-btn-normal data-delete-btn" lay-event="checkImg">
                    查看就业信息
                </button>
                    <%--<button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete">
                        <i class="fa fa-remove"></i>
                        删除
                    </button>--%>

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
            form.render();
            table.render({
                elem: '#currentTableId',
                url: '${basePath}workStatus/studentWorkStatus',
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
                    {field: 'gender',title: '性别'},
                    {field: 'phone',title: '手机号码'},
                    {field: 'company',title: '就业公司',templet: '<div>{{d.workStatus.company}}</div>'},
                    {field: 'salary',title: '薪资',templet: '<div>{{d.workStatus.salary}}</div>'},
                    {field: 'workTime',title: '就业时间',templet: '<div>{{d.workStatus.workTime}}</div>'},
                    {field: 'status',title: '状态'}
                ]],
                done: function(res, page, count){
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
            var stuName = $('#stuName').val();
            //执行搜索重载
            table.reload('currentTableId', {
                where: {
                    stuName:stuName,
                    teacherId:${teacherId}
                }
            }, 'data');
            return false;
        });


        /**
         * toolbar事件监听
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if(obj.event === 'checkImg'){
                var checkStatus = table.checkStatus('currentTableId');
                var data = checkStatus.data;
                if(data.length !=1){
                    layer.msg("请选择一行数据查看",{time:1000});
                    return;
                }
                var index = layer.open({
                    title: '就业证明',
                    type: 2,
                    shade: 0.2,
                    area:['60%','90%'],
                    shadeClose: false,
                    content: 'detail/'+data[0].id,
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
                        title: '修改就业情况',
                        type: 2,
                        shade: 0.2,
                        area: ['60%', '90%'],
                        shadeClose: false,
                        content: 'workStatus/detail/'+data[0].id,
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
                    layer.confirm('确认删除该学生就业记录？', function (index) {
                        $.ajax({
                            url:"${basePath}workStatus/delete",
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
