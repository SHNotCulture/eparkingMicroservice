<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>住户自动账单配置管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        var companyList,parkList,billNameList;
        layui.use(['table','form','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            var laydate = layui.laydate;
            var laydate1 = layui.laydate;
            var laydate2 = layui.laydate;
            var laydate3 = layui.laydate;
            laydate.render({
                elem: '#checkoutDate'
                ,format: 'dd'
            });
            laydate1.render({
                elem: '#autobillPushDate'
                ,format: 'dd'
            });
            laydate2.render({
                elem: '#checkoutDateUpdate'
                ,format: 'dd'
            });
            laydate3.render({
                elem: '#autobillPushDateUpdate'
                ,format: 'dd'
            });
            //第一个实例
            tableIns=table.render({
                elem: '#householdAutobillTable'
                ,height: 300
                ,url: '../propertyAutobill/getPropertyAutobillList' //数据接口
                ,method:'post'
                ,toolbar: true
                ,toolbar: '#toolbarAutobill'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'companyId', title: '物业公司名称', width:170, align:'center',templet:'#companyTemp'}
                    ,{field: 'parkId', title: '车场名称', width:170, align:'center',templet:'#parkIdTemp'}
                    ,{field: 'billName', title: '项目名称', width: 140, align:'center', sort: true}
                    ,{field: 'itemsType', title: '项目类型', width:140, align:'center', sort: true,templet:'#itemsTypeTemp'}
                    ,{field: 'checkoutDate', title: '账单日', width:140, align:'center', sort: true}
                    ,{field: 'autobillPushDate', title: '账单推送日', width:170, align:'center'}
                    ,{field: 'amount', title: '账单金额', width:170, align:'center'}
                    ,{field: 'createTime', title: '创建时间', width:170, align:'center', sort: true}
                    ,{field: 'updateTime', title: '更新时间', width:170, align:'center', sort: true}
                    ,{fixed: 'right',title: '操作', width: 200, toolbar: '#autobillBar'}
                ]]
            });

            //监听工具条
            table.on('tool(householdAutobillTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据

                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'del'){ //删除
                    layer.confirm('真的删除行么', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        autobillHandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("billEditForm", data);
                    autobillHandle.update(1);
                }
            });

            //查询按钮
            form.on('submit(selectAutobill)',function (data) {
                //console.log(data.field);
                autobillHandle.select(data.field);
                return false;
            });

            //监听更新按钮
            form.on('submit(autobillUpdate)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                var billName = $("#billItemId").find("option:selected").text();
                data.field.billName= billName;
                // console.log("data.field: ",data.field);
                autobillHandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
            autobillHandle.getBillName(form);
            form.render();
        });
        $(document).ready(function () {     //DOM完成加载之后立即加载，并且在页面内容加载之前
            autobillHandle.getCompany();
            autobillHandle.getParkList();
        });

        //住户信息增删查改方法
        var autobillHandle={
            select:function (obj) {
                //这里以搜索为例
                tableIns.reload({
                    where:  //设定异步数据接口的额外参数，任意设
                    obj
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            clean:function(){
                $("#billEditForm")[0].reset();
            },
            update:function (type) {
                if(type==0){//新增
                    autobillHandle.clean();
                    layer.open({
                        title: '新增账单信息'
                        , type: 1
                        ,area:['500px','500px']
                        ,content: $('#billEditForm')
                    });
                }else{
                    layer.open({
                        title: '更新账单信息'
                        , type: 1
                        ,area:['500px','500px']
                        ,content: $('#billEditForm')
                    });
                }
            },
            save:function(obj){
                $.ajax({
                    type: 'POST',
                    url: "../propertyAutobill/updateHouseholdPropertyAutobillList",
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

            delete:function (id) {
                $.ajax({
                    type: 'POST',
                    url: "../propertyAutobill/deleteHouseholdPropertyAutobillList",
                    data:{'id':id},
                    dataType: "JSON",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if(result.code==0)
                        {
                            layer.msg(result.result);
                            tableIns.reload();
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
            },
            getBillName:function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../propertybillitems/getPropertybillitemsList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                             // console.log("result: ",result);
                            billNameList= result.result;
                            // console.log("billNameList: ",billNameList);
                            var str="<option value>请选择缴费项目</option>";
                            billNameList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.billName+"</option>"
                            });
                            $("select[name='billItemId']").html(str);
                        }
                        form.render();
                    },
                    error: function (result) {
                        //console.log(result);
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
<#--    <script type="text/html" id="billNameTemp">
        {{# layui.each(billNameList,function(index,item){ }}
        {{# if(item.id===d.billName){ }}
        {{ item.billName }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>-->
    <script type="text/html" id="autobillBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
    </script>

    <script type="text/html" id="toolbarAutobill">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="autobillHandle.update(0)">新增</button>
        </div>
    </script>

</head>
<body>
<blockquote class="layui-elem-quote">住户自动账单配置管理首页 </blockquote>

<#--住户自动账单配置查询条件-->
<form class="layui-form" style="margin: 10px" id="autobillForm" lay-filter="autobillForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">项目名称</label>
            <div class="layui-input-inline">
                <#--<input type="text" name="billName"  placeholder="可模糊搜索" autocomplete="off" class="layui-input">-->
                <select  name="billItemId"  lay-filter="billItemId">
                </select>
            </div>
            <label class="layui-form-label" style="width: 100px">账单日</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="checkoutDate" id="checkoutDate" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label" style="width: 100px">账单推送日</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="autobillPushDate" id="autobillPushDate" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn"  lay-submit lay-filter="selectAutobill" >查询</button>
            </div>
        </div>
    </div>
</form>

<#--住户自动账单配置管理显示窗口-->
<table id="householdAutobillTable" lay-filter="householdAutobillTable"></table>
</body>

<form class="layui-form"  style="display: none" id="billEditForm" lay-filter="billEditForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">缴费项目</label>
        <div class="layui-input-inline">
            <#--<input type="text" id="billName" name="billName" autocomplete="off" class="layui-input">-->
            <select  name="billItemId" id="billItemId" lay-filter="billItemId">
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">账单日</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="checkoutDate" id="checkoutDateUpdate" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">账单推送日</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="autobillPushDate" id="autobillPushDateUpdate" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">账单金额</label>
        <div class="layui-input-inline">
            <input type="text" id="amount" name="amount" autocomplete="off" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-input-inline" style="width: 650px; position: relative; left:25%;">
            <button class="layui-btn" lay-submit lay-filter="autobillUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</html>