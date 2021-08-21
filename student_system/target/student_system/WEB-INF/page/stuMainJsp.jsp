<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <style>
        .layui-card {border:1px solid #f2f2f2;border-radius:5px;}
        .icon {margin-right:10px;color:#1aa094;}
        .icon-cray {color:#ffb800!important;}
        .icon-blue {color:#1e9fff!important;}
        .icon-tip {color:#ff5722!important;}
        .layuimini-qiuck-module {text-align:center;margin-top: 10px}
        .layuimini-qiuck-module a i {display:inline-block;width:100%;height:60px;line-height:60px;text-align:center;border-radius:2px;font-size:30px;background-color:#F8F8F8;color:#333;transition:all .3s;-webkit-transition:all .3s;}
        .layuimini-qiuck-module a cite {position:relative;top:2px;display:block;color:#666;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;font-size:14px;}
        .welcome-module {width:100%;height:210px;}
        .panel {background-color:#fff;border:1px solid transparent;border-radius:3px;-webkit-box-shadow:0 1px 1px rgba(0,0,0,.05);box-shadow:0 1px 1px rgba(0,0,0,.05)}
        .panel-body {padding:10px}
        .panel-title {margin-top:0;margin-bottom:0;font-size:12px;color:inherit}
        .label {display:inline;padding:.2em .6em .3em;font-size:75%;font-weight:700;line-height:1;color:#fff;text-align:center;white-space:nowrap;vertical-align:baseline;border-radius:.25em;margin-top: .3em;}
        .layui-red {color:red}
        .main_btn > p {height:40px;}
        .layui-bg-number {background-color:#F8F8F8;}
        .layuimini-notice:hover {background:#f6f6f6;}
        .layuimini-notice {padding:7px 16px;clear:both;font-size:12px !important;cursor:pointer;position:relative;transition:background 0.2s ease-in-out;}
        .layuimini-notice-title,.layuimini-notice-label {
            padding-right: 70px !important;text-overflow:ellipsis!important;overflow:hidden!important;white-space:nowrap!important;}
        .layuimini-notice-title {line-height:28px;font-size:14px;}
        .layuimini-notice-extra {position:absolute;top:50%;margin-top:-8px;right:16px;display:inline-block;height:16px;color:#999;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-row layui-col-space15">
            <div class="layui-col-md8">
                <div class="layui-row layui-col-space15">
                    <div class="layui-col-md6">
                        <div class="layui-card">
                            <div class="layui-card-header"><i class="fa fa-warning icon"></i>数据统计</div>
                            <div class="layui-card-body">
                                <div class="welcome-module">
                                    <div class="layui-row layui-col-space10">

                                        <div class="layui-col-xs6">
                                            <div class="panel layui-bg-number">
                                                <div class="panel-body">
                                                    <div class="panel-title">
                                                        <span class="label pull-right layui-bg-blue">实时</span>
                                                        <h5>未审批请假数量</h5>
                                                        <small>当前未审批请假总数</small>
                                                    </div>
                                                    <div class="panel-content">
                                                        <h1 class="number" id="request"></h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-col-xs6">
                                            <div class="panel layui-bg-number">
                                                <div class="panel-body">
                                                    <div class="panel-title">
                                                        <span class="label pull-right layui-bg-cyan">实时</span>
                                                        <h5>未审批休退学数量</h5>
                                                        <small>当前未审批休退学总数</small>
                                                    </div>
                                                    <div class="panel-content">
                                                        <h1 class="number" id="studyStatus"></h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-col-xs6">
                                            <div class="panel layui-bg-number">
                                                <div class="panel-body">
                                                    <div class="panel-title">
                                                        <span class="label pull-right layui-bg-cyan">实时</span>
                                                        <h5>未审批学生活动</h5>
                                                        <small>当前未审批学生活动总数</small>
                                                    </div>
                                                    <div class="panel-content">
                                                        <h1 class="number" id="studentAction"></h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-col-xs6">
                                            <div class="panel layui-bg-number">
                                                <div class="panel-body">
                                                    <div class="panel-title">
                                                        <span class="label pull-right layui-bg-green">实时</span>
                                                        <h5>未审工岗位总数</h5>
                                                        <small>当前未审批勤工岗位总数</small>
                                                    </div>
                                                    <div class="panel-content">
                                                        <h1 class="number" id="job"></h1>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="layui-col-md6">
                        <div class="layui-card">
                            <div class="layui-card-header"><i class="fa fa-credit-card icon icon-blue"></i>快捷入口</div>
                            <div class="layui-card-body">
                                <div class="welcome-module">
                                    <div class="layui-row layui-col-space10 layuimini-qiuck">
                                        <div class="layui-col-xs3 layuimini-qiuck-module">
                                            <a href="javascript:;" layuimini-content-href="student/list" data-title="本人信息查询" data-icon="fa fa-window-maximize">
                                                <i class="fa fa-window-maximize"></i>
                                                <cite>本人信息查询</cite>
                                            </a>
                                        </div>
                                        <div class="layui-col-xs3 layuimini-qiuck-module">
                                            <a href="javascript:;" layuimini-content-href="workStatus/list" data-title="就业信息查询" data-icon="fa fa-gears">
                                                <i class="fa fa-gears"></i>
                                                <cite>本人就业信息查询</cite>
                                            </a>
                                        </div>
                                        <div class="layui-col-xs3 layuimini-qiuck-module">
                                            <a href="javascript:;" layuimini-content-href="request/stu_list" data-title="请假申请" data-icon="fa fa-file-text">
                                                <i class="fa fa-file-text"></i>
                                                <cite>请假申请</cite>
                                            </a>
                                        </div>
                                        <div class="layui-col-xs3 layuimini-qiuck-module">
                                            <a href="javascript:;" layuimini-content-href="study/stu_list" data-title="休退学申请" data-icon="fa fa-dot-circle-o">
                                                <i class="fa fa-dot-circle-o"></i>
                                                <cite>休退学申请</cite>
                                            </a>
                                        </div>
                                        <div class="layui-col-xs3 layuimini-qiuck-module">
                                            <a href="javascript:;" layuimini-content-href="studentAction/stu_list" data-title="学生活动申请" data-icon="fa fa-calendar">
                                                <i class="fa fa-calendar"></i>
                                                <cite>学生活动申请</cite>
                                            </a>
                                        </div>
                                        <div class="layui-col-xs3 layuimini-qiuck-module">
                                            <a href="javascript:;" layuimini-content-href="job/stu_list" data-title="勤工岗位申请" data-icon="fa fa-calendar">
                                                <i class="fa fa-calendar"></i>
                                                <cite>勤工岗位申请</cite>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header"><i class="fa fa-bullhorn icon icon-tip"></i>放假公告</div>
                    <div class="layui-card-body layui-text" id="announcement">
                        <div class="layuimini-notice" id="announcement1">
                            <div class="layuimini-notice-title" id="content1"></div>
                            <div class="layuimini-notice-extra" id="announcementTime1"></div>
                            <div class="layuimini-notice-content layui-hide" id="announcementContent1"></div>
                        </div>
                    </div>
                </div>
                <div class="layui-card">
                    <div class="layui-card-header"><i class="fa fa-bullhorn icon icon-tip"></i>开会公告</div>
                    <div class="layui-card-body layui-text" id="announcement2">
                        <div class="layuimini-notice" id="announcement22">
                            <div class="layuimini-notice-title"></div>
                            <div class="layuimini-notice-extra"></div>
                            <div class="layuimini-notice-content layui-hide"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.use(['echarts','jquery'], function () {
        var echarts = layui.echarts,$ = layui.jquery;

        $.ajax({
            url:"${basePath}sum",
            type:"POST",
            dataType:'json',
            success:function(data){
                console.log(data);
                $('#request').html(''+data.data.requestCnt);
                $('#studyStatus').html(''+data.data.studyStatusCnt);
                $('#studentAction').html(''+data.data.studentActionCnt);
                $('#job').html(''+data.data.jobCnt);
                if(data.data.announcements.length==0){
                    $('#announcement1').hide();
                }else{
                    data.data.announcements.forEach(function(value,index,array){
                        if(array[index].type=='放假通知'){
                            $('#announcement1').hide();
                            $('#announcement').append('<div class="layuimini-notice">'+'<div class="layuimini-notice-title">'+data.data.announcements[index].content.substring(0.3)+'....'+'</div>'+
                                '<div class="layuimini-notice-extra">'+data.data.announcements[index].publishTime+'</div>'+
                                '<div class="layuimini-notice-content layui-hide">'+data.data.announcements[index].content+'</div>'+'</div>');
                        }else{
                            $('#announcement22').hide();
                            $('#announcement2').append('<div class="layuimini-notice">'+'<div class="layuimini-notice-title">'+data.data.announcements[index].content.substring(0.3)+'....'+'</div>'+
                                '<div class="layuimini-notice-extra">'+data.data.announcements[index].publishTime+'</div>'+
                                '<div class="layuimini-notice-content layui-hide">'+data.data.announcements[index].content+'</div>'+'</div>');
                        }
                    })
                }
            }
        });

        $('body').on('click','.layuimini-notice',function() {
            var title = $(this).children('.layuimini-notice-title').text(),
                noticeTime = $(this).children('.layuimini-notice-extra').text(),
                content = $(this).children('.layuimini-notice-content').html();
            var html = '<div style="padding:15px 20px; text-align:justify; line-height: 22px;border-bottom:1px solid #e2e2e2;background-color: #2f4056;color: #ffffff">\n' +
                '<div style="text-align: center;margin-bottom: 20px;font-weight: bold;border-bottom:1px solid #718fb5;padding-bottom: 5px"><h4 class="text-danger">' + title + '</h4></div>\n' +
                '<div style="font-size: 12px">' + content + '</div>\n' +
                '</div>\n';
            parent.layer.open({
                type: 1,
                title: '放假公告'+'<span style="float: right;right: 1px;font-size: 12px;color: #b1b3b9;margin-top: 1px">'+noticeTime+'</span>',
                area: '300px;',
                shade: 0.8,
                id: 'layuimini-notice',
                btn: [ '关闭'],
                btnAlign: 'c',
                moveType: 1,
                content:html,
                success: function (layero) {
                    var btn = layero.find('.layui-layer-btn');
                    btn.find('.layui-layer-btn0').attr({
                        target: '_blank'
                    });
                }
            });
        });

    });
</script>
</body>
</html>
