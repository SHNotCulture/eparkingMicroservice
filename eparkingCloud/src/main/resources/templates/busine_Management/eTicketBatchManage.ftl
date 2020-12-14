<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>电子券批次管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns,form;
        var busineList;
        layui.use(['table','form'], function(){   //,'laydate'
            var table = layui.table;
            form=layui.form;
            //第一个实例
            tableIns=table.render({
                elem: '#eTicketTable'
                ,height: 700
                ,url: '../busineTicket/getBusineTicketListbyPage' //数据接口
                ,method:'post'
                /*,toolbar: true
                ,toolbar: '#toolbarTicket'*/
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,title:'电子券'
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:50, fixed: 'left'}
                    ,{field: 'busineName', title: '商户名称',align: 'center', width:135}
                    ,{field: 'ticketName', title: '电子券名称',align: 'center', width:200}
                    ,{field: 'limitDay', title: '每日限额',align: 'right', width: 105, sort: true}
                    ,{field: 'limitMonth', title: '每月限额',align: 'right', width: 105, sort: true}
                    ,{field: 'limitYear', title: '每年限额',align: 'right', width:105, sort: true}
                   /* ,{field: 'ticketInitialNum', title: '购买数量', width:170, align:'center'}*/
                    ,{field: 'ticketNum', title: '剩余数量',align: 'right', width:105, sort: true}
                    ,{field: 'expiryDate', title: '到期时间',align: 'center', width:160, sort: true}
                    ,{fixed: 'right',title: '操作',align: 'center', width: 140, toolbar: '#eTicketBar'}
                ]]
            });
            //头工具栏事件
            /*table.on('toolbar(eTicketTable)', function(obj){
                eTicketForm.BlackExcel()
            });*/
            //监听工具条
            table.on('tool(eTicketTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log("data"+data.id+data.ticketId);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                //var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'eBuy'){ //购买
                    //验证到期时间是否小于当前时间
                    var d = new Date(Date.parse(data.expiryDate.replace(/-/g,"/")));
                    var curDate = new Date();
                    if(d <=curDate){
                        layer.msg("该券过期,请重新给商户授权电子券");
                    }else{
                        form.val("eTicketBuyForm", data);
                        eTicketBatchHandle.Update(0);
                        $('#ticketNumNew').val("");
                    }
                } else if(layEvent === 'changeRule'){ //更改权限
                    busineTicketIsPayForm.clean();
                    form.val("busineTicketIsPayForm", data);
                    if(data.isPay==1){
                        // $('#idPayOk').checked;
                        $('#idPayOk').prop("checked", true);
                        $('#idPayNo').prop("checked", false);
                    }else{
                        // $('#idPayNo').checked;
                        $('#idPayOk').prop("checked", false);
                        $('#idPayNo').prop("checked", true);
                    }
                    eTicketBatchHandle.changeRule();
                }
            });
            form.on('submit(eTicketBuy)', function(data){
                // console.info(data.field);
                eTicketBatchHandle.saveBuy(data.field);
                return false;
            });
            form.on('submit(busineTicketIsPayBtn)', function(data){
                eTicketBatchHandle.changeIsPay(data.field);
                return false;
            });

            form.render();

            //查询按钮
            form.on('submit(SelectBusineTicket)',function (data) {
               //console.log(data.field);
                eTicketBatchHandle.select(data.field);
                return false;
            });
        });
        $(document).ready(function () {
            eTicketBatchHandle.getBusine();
        });
        //电子券增删查改方法
        var eTicketBatchHandle={
            Update:function (type) {
                if(type==0){//新增
                    //eTicketBuyForm.clean();
                    layer.open({
                        title: '购买电子券'
                        , type: 1
                        ,area:['500px','300px']
                        ,content: $('#eTicketBuyForm')
                    });
                }
            },
            changeRule:function (type) {
                    layer.open({
                        title: '修改批次权限'
                        , type: 1
                        ,area:['500px','250px']
                        ,content: $('#busineTicketIsPayForm')
                    });
            },
            select:function (obj) {
                //这里以搜索为例
                // console.log(obj);
                tableIns.reload({
                    where:  //设定异步数据接口的额外参数，任意设
                        obj
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            } ,
            saveBuy:function(object){
                $.ajax({
                    type: 'POST',
                    url: "../busineTicket/buyBusineTicket",
                    data:object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        layer.closeAll();
                        if (result.code==0){
                            layer.msg(result.result, {icon: 6});
                        } else {
                            layer.msg(result.message, {icon: 5});
                        }
                        tableIns.reload();
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
            },
            changeIsPay:function(object){
            $.ajax({
                type: 'POST',
                url: "../busineTicket/changeIsPay",
                data:object,
                dataType: "json",
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success: function (result) {
                    //console.log(result);
                    if (result.code==0){
                        layer.closeAll();
                        layer.msg(result.result, {icon: 6});
                        tableIns.reload();
                    }

                },
                error:function (result) {
                    console.log(result);
                }
            })
        },
            getBusine:function () {
                $.ajax({
                    type: 'POST',
                    url: "../busine/getTBusine",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if (result.code==0){
                            busineList= result.result;
                            var str=" <option value>请选择商户名称</option>";
                            busineList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.busineName+"</option>"
                            });
                            $("select[name='busineId']").html(str);
                        }
                        form.render();
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
            }

        };
        var parkList;
        //车场更新表单使用方法
        var eTicketBuyForm={
            clean:function(){
                $("#eTicketBuyForm")[0].reset();
            },
            //获取停车场
            /*getParkList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarPark",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            //console.log(result);
                            parkList= result.data;
                            var str=" <option value>请选择停车场</option>";
                            parkList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.parkName+"</option>"
                            });
                            $("#parkId").html(str);
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
            }*/

        };
        var busineTicketIsPayForm={
            clean:function(){
                $("#busineTicketIsPayForm")[0].reset();
            }
        };
    </script>

    <script type="text/html" id="eTicketBar">
        <a class="layui-btn layui-btn-xs" lay-event="eBuy">购买</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="changeRule">更改权限</a>
    </script>

</head>
<body>
<blockquote class="layui-elem-quote">电子券批次管理 </blockquote>
<form class="layui-form" style="margin-top: 5px" id="serchBlackForm" lay-filter="serchBlackForm">
    <div class="layui-form-item" >
        <div class="layui-inline">
        <label class="layui-form-label">电子券名称</label>
        <div class="layui-input-inline">
            <input type="text" name="ticketName"  placeholder="可模糊搜索" autocomplete="off" class="layui-input">
        </div>
            <label class="layui-form-label">商户名称</label>
            <div class="layui-input-inline">
                <select id="busineId" name="busineId" lay-filter="busineId">

                </select>
                <#--<input type="text" name="busineName"  placeholder="可模糊搜索" autocomplete="off" class="layui-input">-->
            </div>
            <label class="layui-form-label">权限类型</label>
            <div class="layui-input-inline">
                <select name="isPay" lay-filter="isPay">
                    <option value="">请选择</option>
                    <option value="0">不允许</option>
                    <option value="1" >允许</option>
                </select>
            </div>
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn"  lay-submit lay-filter="SelectBusineTicket" >查询</button>
         </div>
        </div>
    </div>
</form>
<table id="eTicketTable" lay-filter="eTicketTable"></table>
</body>
<form class="layui-form"  style="display: none" id="eTicketBuyForm" lay-filter="eTicketBuyForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label" style="width: 100px">ID</label>
        <div class="layui-input-inline">
            <input type="text"  name="id" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label" style="width: 100px">ticketID</label>
        <div class="layui-input-inline">
            <input type="text"  name="ticketId" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px">
        <label class="layui-form-label" style="width: 100px">电子券名称</label>
        <div class="layui-input-inline">
            <input type="text"  name="ticketName" autocomplete="off" class="layui-input" disabled="true">
        </div>
    </div>
    <#--<div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">单价</label>
        <div class="layui-input-inline">
            <input type="text" id="ticketPay" name="ticketPay" autocomplete="off" class="layui-input" >
        </div>
    </div>-->
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">购买数量</label>
        <div class="layui-input-inline">
            <input type="text"  name="ticketNumNew" id="ticketNumNew" autocomplete="off" class="layui-input">
        </div>
    </div>
    <#--<div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">应收</label>
        <div class="layui-input-inline">
            <input type="text" id="needPay" name="needPay" autocomplete="off"  class="layui-input">
        </div>
    </div>-->
    <div class="layui-input-inline" style="margin-left: 130px">
        <button class="layui-btn" lay-submit lay-filter="eTicketBuy">确认</button>
    </div>

</form>
<form class="layui-form"  style="display: none" id="busineTicketIsPayForm" lay-filter="busineTicketIsPayForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label" style="width: 100px">ID</label>
        <div class="layui-input-inline">
            <input type="text"  name="id" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px">
        <label class="layui-form-label" style="width: 120px">是否允许商户自购</label>
        <div class="layui-input-block">
            <input type="radio" name="isPay" id="idPayOk" value="1" title="允许">
            <input type="radio" name="isPay" id="idPayNo" value="0" title="不允许">
        </div>
    </div>
<#--    <label class="layui-form-label" style="width: 150px;margin-top: 20px">是否允许商户自购</label>
    <div class="layui-input-block" style="margin-top: 20px">
        <input type="radio" name="isPay" id="idPayOk" value="1" title="允许">
        <input type="radio" name="isPay" id="idPayNo" value="0" title="不允许">
    </div>-->
    <div class="layui-form-item" style="margin-top: 20px;margin-left: 40px">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="busineTicketIsPayBtn">确认修改</button>
        </div>
    </div>
</form>
</html>