<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>车主反馈信息</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        var companyList,parkList;
        layui.use(['table','form','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            //第一个实例
            tableIns=table.render({
                elem: '#householdFeeeparkingdbackTable'
                ,height: 300
                ,url: '../householdFeeeparkingdback/geTHouseholdFeedbackList' //数据接口
                ,method:'post'
                ,toolbar: true
                ,toolbar: '#toolbarFeeeparkingdback'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'companyId', title: '物业公司', width:170, align:'center',templet:'#companyTemp'}
                    ,{field: 'parkId', title: '车场名称', width:170, align:'center',templet:'#parkIdTemp'}
                    ,{field: 'name', title: '车主名称', width:170, align:'center'}
                    ,{field: 'sex', title: '性别', width:170, align:'center'}
                    ,{field: 'phoneNumber', title: '联系电话', width:170, align:'center'}
                    ,{field: 'content', title: '反馈内容', width:170, align:'center'}
                    ,{field: 'createTime', title: '反馈时间', width:170, align:'center', sort: true}
                    ,{field: 'replyContent', title: '回复内容', width:170, align:'center'}
                    ,{field: 'replyTime', title: '回复时间', width:170, align:'center', sort: true}
                    ,{field: 'replyUser', title: '回复人', width:170, align:'center'}
                    ,{fixed: 'right',title: '操作', width: 200, toolbar: '#householeparkingdbar'}
                ]]
            });

            //监听工具条
            table.on('tool(householdFeeeparkingdbackTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'reply'){ //回复
                    //do somehing
                }
            });

        });
        $(document).ready(function () {     //DOM完成加载之后立即加载，并且在页面内容加载之前
            feeeparkingdbackHandle.getCompany();
            feeeparkingdbackHandle.getParkList();
        });
        //反馈内容增删查改方法
        var feeeparkingdbackHandle={
            clean:function(){
                $("#feeeparkingdbackForm")[0].reset();
            },
            update:function (type) {
                if(type==0){//新增
                    feeeparkingdbackHandle.clean();
                    layer.open({
                        title: '新增反馈信息'
                        , type: 1
                        ,area:['500px','500px']
                        ,content: $('#feeeparkingdbackForm')
                    });
                }
            },
            //获取物业公司
            getCompany:function () {
                $.ajax({
                    type: 'POST',
                    url: "../comPany/getComPany",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            companyList=result.data;
                        }
                    },
                    error: function (result) {
                        var str="<script type=\"text/javascript\">";
                        var beginNum=result.responseText.indexOf(str)+str.length;
                        var endNum=result.responseText.length-9;
                        var action=result.responseText.substring(beginNum,endNum);
                        eval(action);
                    }
                })
            },
            //获取车场
            getParkList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarPark",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            parkList=result.data;
                        }
                    },
                    error: function (result) {
                        var str="<script type=\"text/javascript\">";
                        var beginNum=result.responseText.indexOf(str)+str.length;
                        var endNum=result.responseText.length-9;
                        var action=result.responseText.substring(beginNum,endNum);
                        eval(action);
                    }
                })
            }
        }
    </script>
    <script type="text/html" id="companyTemp">
        {{# layui.each(companyList,function(index,item){ }}
        {{# if(item.id===d.companyId){ }}
        {{ item.companyName }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="parkIdTemp">
        {{# layui.each(parkList,function(index,item){ }}
        {{# if(item.id===d.parkId){ }}
        {{ item.parkName }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="householeparkingdbar">
        <a class="layui-btn layui-btn-xs" lay-event="reply">回复</a>
    </script>
    <script type="text/html" id="toolbarNotice">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="noticeHandle.update(0)">新增公告</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">车主反馈信息首页 </blockquote>


<#--车主反馈信息查询显示窗口-->
<table id="householdFeeeparkingdbackTable" lay-filter="householdFeeeparkingdbackTable"></table>
</body>
<form class="layui-form"  style="display: none" id="feeeparkingdbackForm" lay-filter="feeeparkingdbackForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">回复内容</label>
        <div class="layui-input-inline">
            <input maxlength="200" type="text" id="replyContent" name="replyContent" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-inline" style="width: 650px; position: relative; left:25%;">
            <button class="layui-btn" lay-submit lay-filter="replyUpdate">回复</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>



</html>