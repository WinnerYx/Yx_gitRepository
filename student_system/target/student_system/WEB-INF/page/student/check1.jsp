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
                <div class="layui-form-item">
                    <label class="layui-form-label">就业证明</label>
                    <div class="layui-upload-drag" id="headImg">
                        <i class="layui-icon"></i>
                        <hr>
                        <div onclick="photograph(this);"><img id="myImg"  src="" width="" height="" style="max-width: 100px"></div>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-submit lay-filter="close">
                        <i class="fa fa-remove"></i>
                        关闭
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="${basePath}static/lib/layui-src/layui.js" charset="utf-8"></script>
<script src="${basePath}static/js/lay-config.js?v=2.0.0" charset="utf-8"></script>
<script>
    layui.use(['layer','form','jquery','laydate'], function () {
        var form = layui.form,$ = layui.jquery,layer=layui.layer;
        var index = parent.layer.getFrameIndex(window.name);
        var stuId = ${workStatus.stuId};
        //监听提交
        $.ajax({
            url:"${basePath}queryImg",
            type:"POST",
            data:{
                "stuId":stuId,
            },
            success:function(data){
                console.log(data.src);
                if(data.code==1001){
                    layer.msg("该生暂未上传就业证明，请联系上传后查看");
                }else{
                    layer.msg("点击图片查看");

                    $("#myImg").attr('src',data.src);
                }
            }
        });

        photograph = function(t) {
            var t = $(t).find("img");
            if(t == null||t == ''){
                return;
            }
            layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['auto', 'auto'], //宽高
                shadeClose: true, //开启遮罩关闭
                end: function (index, layero) {
                    return false;
                },
                content: '<div style="text-align:center"><img src="' + $(t).attr('src') + '" /></div>'
            });
        }

        form.on('submit(close)', function (data) {
            parent.layer.close(index);
            return false;
        });

    });
</script>
</body>
</html>
