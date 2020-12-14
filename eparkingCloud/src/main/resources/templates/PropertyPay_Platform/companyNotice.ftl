<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>物业公告</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        var noticeIds;
        var tableHousehold;
        var companyList,parkList;
        layui.use(['table','form','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            //第一个实例
            tableIns=table.render({
                elem: '#companyNoticeTable'
                ,height: 300
                ,url: '../companyNotice/getCompanyNoticeList' //数据接口
                ,method:'post'
                ,toolbar: true
                ,toolbar: '#toolbarNotice'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type:'checkbox'}
                    ,{field: 'companyId', title: '物业公司', width:170, align:'center',templet:'#companyTemp'}
                    ,{field: 'parkId', title: '车场名称', width:170, align:'center',templet:'#parkIdTemp'}
                    ,{field: 'title', title: '公告标题', width:170, align:'center'}
                    ,{field: 'content', title: '公告内容', width:170, align:'center'}
                    ,{field: 'createTime', title: '创建时间', width:170, align:'center', sort: true}
                    ,{field: 'updateTime', title: '更新时间', width:170, align:'center', sort: true}
                    ,{field: 'userId', title: '操作人', width:170, align:'center'}
                    ,{fixed: 'right',title: '操作', width: 200, toolbar: '#noticeBar'}
                ]]
            });

            tableHousehold=table.render({
                elem: '#householdTable'
                ,height: 500
                ,url: '../householdManagement/getHouseholdList' //数据接口
                ,where:{isAuditing:1}
                ,method:'post'
                ,toolbar: true
                ,toolbar: '#toolbarhousehold'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type:'checkbox'}
                    ,{field: 'companyId', title: '物业公司名称', width:170, align:'center',templet:'#companyTemp'}
                    ,{field: 'parkId', title: '车场名称', width:170, align:'center',templet:'#parkIdTemp'}
                    ,{field: 'name', title: '住户姓名', width: 140, align:'center', sort: true}
                    ,{field: 'sex', title: '性别', width: 140, align:'center', sort: true,templet:'#sexTemp'}
                    ,{field: 'phoneNumber', title: '联系电话', width:140, align:'center', sort: true}
                    ,{field: 'villageName', title: '小区名称', width:170, align:'center'}
                    ,{field: 'address', title: '家庭地址', width:170, align:'center', sort: true}
                    ,{field: 'isAuditing', title: '审核标识', width:170, align:'center', sort: true,templet:'#isAuditingTemp'}
                    ,{field: 'createTime', title: '创建时间', width:170, align:'center', sort: true}
                    ,{field: 'auditingTime', title: '审核时间', width:170, align:'center', sort: true}
                    ,{field: 'updateTime', title: '更新时间', width:170, align:'center', sort: true}
                ]]
            });
            //监听工具条
            table.on('tool(companyNoticeTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据

                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'push'){ //推送
                    var memberId = '0';     //单条公告推送给所有住户默认id为0
                    $.ajax({
                        type: 'POST',
                        url: "../companyNotice/postNotice",
                        data:{'noticeIds':data.id,'memberIds':memberId},
                        dataType: "json",
                        contentType: "application/x-www-form-urlencoded; charset=utf-8",
                        success: function (result) {
                            if(result.code==0)
                            {
                                if (result.result=='推送成功') {
                                    layer.closeAll();
                                    layer.msg(result.result, {icon: 6});
                                    tableHousehold.reload();
                                }
                            }
                        },
                        error:function (result) {
                            console.log(result);
                        }
                    });
                    } else if(layEvent === 'edit'){ //编辑
                    form.val("noticeEditForm", data);
                    noticeHandle.update(1);
                }
            });

            //监听住户推送按钮
            table.on('toolbar(companyNoticeTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                noticeIds=null;
                for(var i=0;i<checkStatus.data.length;i++){
                   if(noticeIds!=null) {
                       noticeIds=noticeIds+','+checkStatus.data[i].id;
                   }else{
                       noticeIds=checkStatus.data[i].id;
                   }
                }
                tableHousehold.reload({
                    where: { //设定异步数据接口的额外参数，任意设
                    }
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                layer.open({
                    title: '推送住户选择'
                    , type: 1
                    ,area:['1000px','600px']
                    ,content: $('#noticePost')
                });
            });

            //批量推送按钮
            table.on('toolbar(householdTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'selectPost':

                        var data = checkStatus.data;
                        var memberIds=null;
                        for(var i=0;i<data.length;i++){
                            if(memberIds!=null){
                                memberIds=memberIds+','+data[i].memberId;
                            }else{
                                memberIds=data[i].memberId;
                            }
                        }

                        // console.log("memberIds: ",JSON.stringify(memberIds));
                        console.log("memberIds: ",memberIds);
                        console.log("noticeIds: ",noticeIds);
                        layer.alert(JSON.stringify(data));
                        $.ajax({
                            type: 'POST',
                            url: "../companyNotice/postNotice",
                            data:{'noticeIds':noticeIds,'memberIds':memberIds},
                            dataType: "json",
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            success: function (result) {
                                if(result.code==0)
                                {
                                    if (result.result=='推送成功') {
                                        layer.closeAll();
                                        layer.msg(result.result, {icon: 6});
                                        tableHousehold.reload();
                                    }
                                }
                            },
                            error:function (result) {
                                console.log(result);
                            }
                        });
                        break;
                };
            });

            //监听更新按钮
            form.on('submit(noticeUpdate)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                console.log("field",data.field);
                noticeHandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });

            form.render();

        });
        $(document).ready(function () {     //DOM完成加载之后立即加载，并且在页面内容加载之前
            noticeHandle.getCompany();
            noticeHandle.getParkList();
        });

        //物业公告增删查改方法
        var noticeHandle={

            clean:function(){
                $("#noticeEditForm")[0].reset();
            },
            update:function (type) {
                if(type==0){//新增
                    noticeHandle.clean();
                    layer.open({
                        title: '新增公告信息'
                        , type: 1
                        ,area:['500px','500px']
                        ,content: $('#noticeEditForm')
                    });
                }else{
                    layer.open({
                        title: '更新公告信息'
                        , type: 1
                        ,area:['500px','500px']
                        ,content: $('#noticeEditForm')
                    });
                }
            },
            save:function(obj){
                $.ajax({
                    type: 'POST',
                    url: "../companyNotice/updateCompanyNoticeList",
                    data:obj,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if(result.code==0)
                        {
                            if (result.result=='编辑成功') {
                                layer.closeAll();
                                layer.msg(result.result, {icon: 6});
                                tableIns.reload();
                            }else if(result.result=='成功添加'){
                                layer.closeAll();
                                layer.msg(result.result);
                                tableIns.reload();
                            }
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
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
    <script type="text/html" id="isAuditingTemp">
        {{# if(d.isAuditing===0){ }}
        {{ '未审核' }}
        {{# }if(d.isAuditing===1){ }}
        {{ '已审核' }}
        {{# } }}
    </script>
    <script type="text/html" id="sexTemp">
        {{# if(d.sex===0){ }}
        {{ '女' }}
        {{# }if(d.sex===1){ }}
        {{ '男' }}
        {{# } }}
    </script>
    <script type="text/html" id="noticeBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs" lay-event="push">推送</a>
    </script>
    <script type="text/html" id="toolbarNotice">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="noticeHandle.update(0)">新增公告</button>
            <button class="layui-btn layui-btn-sm" lay-event="selectHousehold">住户推送</button>
        </div>
    </script>
    <script type="text/html" id="toolbarhousehold">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="selectPost">批量推送</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">物业公告首页 </blockquote>


<#--物业公告查询显示窗口-->
<table id="companyNoticeTable" lay-filter="companyNoticeTable"></table>
</body>
<form class="layui-form"  style="display: none" id="noticeEditForm" lay-filter="noticeEditForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">公告标题</label>
        <div class="layui-input-inline">
            <input maxlength="100" type="text" id="title" name="title" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">公告内容</label>
        <div class="layui-input-inline">
            <textarea maxlength="200" type="text/plain" id="content" name="content" autocomplete="off" class="layui-input "style="width:99%;height:250px">
            </textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-inline" style="width: 650px; position: relative; left:25%;">
            <button class="layui-btn" lay-submit lay-filter="noticeUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<div id="noticePost" style="display: none">
    <table id="householdTable" lay-filter="householdTable"></table>
</div>

</html>