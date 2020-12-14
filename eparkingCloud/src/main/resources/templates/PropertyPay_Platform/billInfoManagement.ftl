<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>缴费项目管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        var companyList,parkList;
        layui.use(['table','form','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            var laydate = layui.laydate;
            var laydate1 = layui.laydate;
            laydate.render({
                elem: '#checkoutDate'
                ,format: 'dd'
            });
            laydate1.render({
                elem: '#autobillPushDate'
                ,format: 'dd'
            });
            //第一个实例
            tableIns=table.render({
                elem: '#billInfoTable'
                ,height: 300
                ,url: '../propertybillitems/getPropertybillitemsListbyPage' //数据接口
                ,method:'post'
                ,toolbar: true
                ,toolbar: '#toolbarBillitems'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'companyId', title: '物业公司名称', width:170, align:'center',templet:'#companyTemp'}
                    ,{field: 'parkId', title: '车场名称', width:170, align:'center',templet:'#parkIdTemp'}
                    ,{field: 'billName', title: '项目名称', width: 140, align:'center', sort: true}
                    ,{field: 'itemsType', title: '项目类型', width:140, align:'center', sort: true,templet:'#itemsTypeTemp'}
                    ,{field: 'itemsAmount', title: '计算基数', width:140, align:'center', sort: true}
                    ,{field: 'createTime', title: '创建时间', width:170, align:'center', sort: true}
                    ,{field: 'updateTime', title: '更新时间', width:170, align:'center', sort: true}
                    ,{fixed: 'right',title: '操作', width: 200, toolbar: '#householeparkingdbar'}
                ]]
            });

            //监听更新按钮
            form.on('submit(billitemsUpdate)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                console.log("field",data.field);
                billitemsHandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });



            //监听工具条
            table.on('tool(billInfoTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'add'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('真的删除行么', function(index){
                        billitemsHandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("billitemsEditForm", data);
                    billitemsHandle.update(1);
                }
            });
            form.render();
        });
        $(document).ready(function () {     //DOM完成加载之后立即加载，并且在页面内容加载之前
            billitemsHandle.getCompany();
            billitemsHandle.getParkList();
        });
        //缴费项目增删查改方法
        var billitemsHandle={

            clean:function(){
                $("#billitemsEditForm")[0].reset();
            },
            update:function (type) {
                if(type==0){//新增
                    billitemsHandle.clean();
                    layer.open({
                        title: '新增缴费项目'
                        , type: 1
                        ,area:['500px','500px']
                        ,content: $('#billitemsEditForm')
                    });
                }else{
                    layer.open({
                        title: '更新缴费项目'
                        , type: 1
                        ,area:['500px','500px']
                        ,content: $('#billitemsEditForm')
                    });
                }
            },
            save:function(obj){
                $.ajax({
                    type: 'POST',
                    url: "../propertybillitems/updatePropertybillitemsList",
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
                                // tableIns.reload();
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
            delete:function (id) {
                $.ajax({
                    type: 'POST',
                    url: "../propertybillitems/deletePropertybillitemsList",
                    data:{'id':id},
                    dataType: "JSON",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if(result.code==0)
                        {
                            layer.msg(result.result);
                            tableIns.reload();
                        }else{
                            layer.msg(result.message);
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
    <script type="text/html" id="itemsTypeTemp">
        {{# if(d.itemsType===0){ }}
        {{ '固定金额' }}
        {{# }if(d.itemsType===1){ }}
        {{ '物业费' }}
        {{# } }}
    </script>
    <script type="text/html" id="householeparkingdbar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="toolbarBillitems">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="billitemsHandle.update(0)">新增缴费项目</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">缴费信息管理首页 </blockquote>

<#--缴费项目信息显示窗口-->
<table id="billInfoTable" lay-filter="billInfoTable"></table>
<form class="layui-form"  style="display: none" id="billitemsEditForm" lay-filter="billitemsEditForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">项目名称</label>
        <div class="layui-input-inline">
            <input maxlength="200" type="text" id="billName" name="billName" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
    <label class="layui-form-label" style="width: 100px">项目类型</label>
    <div class="layui-input-inline" style="width: 200px;">
        <select  name="itemsType" id="itemsType" lay-filter="itemsType">
            <option value="">请选择</option>
            <option value="0">固定金额</option>
            <option value="1" >物业费</option>
        </select>
    </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">计算基数</label>
        <div class="layui-input-inline">
            <input maxlength="200" type="text" id="itemsAmount" name="itemsAmount" autocomplete="off" class="layui-input">
        </div>
    </div>
<#--    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">账单日</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="checkoutDate" id="checkoutDate" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">账单推送日</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="autobillPushDate" id="autobillPushDate" autocomplete="off" class="layui-input">
        </div>
    </div>-->
    <div class="layui-form-item">
        <div class="layui-input-inline" style="width: 650px; position: relative; left:25%;">
            <button class="layui-btn" lay-submit lay-filter="billitemsUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</html>