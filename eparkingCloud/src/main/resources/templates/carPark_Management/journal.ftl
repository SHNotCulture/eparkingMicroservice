<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>操作日志记录</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>

    <script type="text/javascript">
        var tableIns,form;
        layui.use(['table','form','layer','laydate'], function(){
            var table = layui.table;
            form=layui.form;
            var layer=layui.layer;
            var laydate = layui.laydate;
            //执行一个laydate实例
            laydate.render({
                elem: '#beginTime' //指定元素
                ,type: 'datetime'
            });
            laydate.render({
                elem: '#endTime' //指定元素
                ,type: 'datetime'
            });
            //第一个实例
            tableIns=table.render({
                elem: '#journalTable'
                ,height:560
                ,url: '../journal/getJournal' //数据接口
                ,method:'post'
                ,title:'操作日志'
                ,toolbar: true
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,cols: [[ //表头
                    /* {field: 'id', title: 'ID', width:80, sort: true, hide:true}*/
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'companyUser', title: '操作员', width:120}
                    ,{field: 'operatingType', title: '事件类型', width:120,templet:'#operatingTypeTemp'}
                    ,{field: 'operationContent', title: '操作内容', width: 800,align:'center'}
                    ,{field: 'ip', title: '电脑IP', width: 140, sort: true}
                    ,{field: 'date', title: '时间', width: 200, sort: true}
                    ,{field: 'status', title: '状态', width: 100, sort: true,templet:'#statusTemp'}
                ]]
                ,page: true //开启分页
            });
            //监听查询按钮
            form.on('submit(SelectJournal)', function(data){
                // console.log(data.field);
                Journalhandle.select(data.field.companyUserId,data.field.operatingType,data.field.beginTime,data.field.endTime);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            JournalForm.getCompanyUserList(form);
            form.render(); //更新全部
        });
        //日志的增删查改
        var  Journalhandle={
            select:function (companyUserId,operatingType,beginTime,endTime) {
                //这里以搜索为例
                tableIns.reload({
                    where: {companyUserId:companyUserId,operatingType:operatingType,beginTime:beginTime,endTime:endTime}
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            }
        }
        //在页面加载完成后执行
        $(document).ready(function () {
            JournalForm.getOperatingTypeList();
            JournalForm.getStatusList();
        })

        var CompanyUserList,operatingTypeList,statusList;
        //商户更新表单使用方法
        var JournalForm={
            getCompanyUserList:function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../user/getCompanyUser",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            CompanyUserList= result.result;
                             var str=" <option value>请选择用户</option>";
                            CompanyUserList.forEach(function (t) {
                                 str+="<option value="+t.id+">"+t.userName+"</option>"
                             });
                             $("select[name='companyUserId']").html(str);
                            form.render();
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
            getOperatingTypeList:function () {
                operatingTypeList=[{id:1,name:'操作日志'},{id:2,name:'事件日志'}];
                             var str=" <option value>请选择</option>";
                             operatingTypeList.forEach(function (t) {
                                 str+="<option value="+t.id+">"+t.name+"</option>"
                             });
                             $("select[name='operatingType']").html(str);

            },
            getStatusList:function () {
                statusList=[{id:1,name:'成功'},{id:2,name:'失败'}];
            }
        }
    </script>
    <script type="text/html" id="operatingTypeTemp">
        {{# layui.each(operatingTypeList,function(index,item){ }}
        {{# if(item.id===d.operatingType){ }}
        {{ item.name }}
        {{# return; }}
        {{# }  }}
        {{#  }); }}
    </script>
    <script type="text/html" id="statusTemp">
        {{# layui.each(statusList,function(index,item){ }}
        {{# if(item.id===d.status){ }}
        {{ item.name }}
        {{# return; }}
        {{# }  }}
        {{#  }); }}
    </script>

</head>
<body>
<blockquote class="layui-elem-quote">操作日志 </blockquote>
<div class="layui-form" style="margin: 10px">
    <div class="layui-form-item">
        <div class="layui-inline">
         <label class="layui-form-label">操作员：</label>
         <div class="layui-input-inline" style="width: 200px;">
             <select id="companyUserId" name="companyUserId" lay-filter="companyUserId">
             </select>
         </div>
            <label class="layui-form-label">操作时间：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" id="beginTime" name="beginTime" placeholder="yyyy-MM-dd HH:mm:ss">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" id="endTime" name="endTime" placeholder="yyyy-MM-dd HH:mm:ss">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">事件类型：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select id="operatingType" name="operatingType" lay-filter="operatingType">
                </select>
            </div>
            <label class="layui-form-label">关键字：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" id="operationContent" name="operationContent" placeholder="关键字" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 200px;margin-left: 15px">
                <button class="layui-btn" lay-submit lay-filter="SelectJournal" >查询</button>
            </div>
        </div>
    </div>
</div>
<table id="journalTable" lay-filter="journalTable"></table>
</body>
</html>