<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商户充值记录</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>

    <script type="text/javascript">
        var tableIns,form;
        var busineList;
        layui.use(['table','form','layer','laydate'], function(){
            var table = layui.table;
            form=layui.form;
            var layer=layui.layer;
            var laydate = layui.laydate;
            //执行一个laydate实例
            laydate.render({
                elem: '#payTimeBegin' //指定元素
                ,type: 'datetime'
            });
            laydate.render({
                elem: '#payTimeEnd' //指定元素
                ,type: 'datetime'
            });
            //第一个实例
            tableIns=table.render({
                elem: '#businePayTable'
                ,height:600
                ,url: '../busine/getBusinePaybyPage' //数据接口
                ,method:'post'
                ,toolbar: true
                ,toolbar: '#BusinePayToolbar'
                ,totalRow: true
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,title:'商户充值记录'
                ,page: true //开启分页
                ,cols: [[ //表头
                    /* {field: 'id', title: 'ID', width:80, sort: true, hide:true}*/
                    {type: 'numbers', title: '序号', width:80, fixed: 'left',  totalRowText: '合计'}
                    ,{field: 'busineName', title: '商户名称', width:120}
                    ,{field: 'needPay', title: '应收', width: 75,align: 'right', sort: true,totalRow: true}
                    ,{field: 'actualPay', title: '实收', width: 75,align: 'right', sort: true,totalRow: true}
                    ,{field: 'payTime', title: '充值时间', width: 200, sort: true}
                    ,{field: 'orderNumber', title: '订单号', width: 300, sort: true}
                    ,{field: 'balance', title: '账户余额', width: 102,align: 'right', sort: true,totalRow: true}
                    ,{field: 'remark', title: '备注', width: 120}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(businePayTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'BusinePayExcel':
                        var payTimeBegin=$("#payTimeBegin").val();
                        var payTimeEnd=$("#payTimeEnd").val();
                        var busineId=$("#busineId").val();
                        var data={payTimeBegin:payTimeBegin,payTimeEnd:payTimeEnd,busineId:busineId}
                        BusinePayHandle.BusinePayExcel(data);
                        break;
                    default:
                };
            });
            //监听查询按钮
            form.on('submit(SelectBusinePay)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回*/
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                // console.log(data.field);
                BusinePayHandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
        });
        //在页面加载完成后执行
        $(document).ready(function () {
            //BusinePayForm.getParkList();
            BusinePayForm.getBusineId();
        });
        var BusinePayHandle={
            select:function (data) {
                //这里以搜索为例
                tableIns.reload({
                    where: data
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            BusinePayExcel:function (object) {
                window.location.href="../Base/exportListforBusinePay?payTimeBegin="+object.payTimeBegin+"&payTimeEnd="+object.payTimeEnd+"&busineId="+object.busineId;
            }
        };


        //商户更新表单使用方法
        var BusinePayForm={
            getBusineId:function () {
                $.ajax({
                    type: 'POST',
                    url: "../busine/getTBusine",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            // console.log(result);
                            busineList= result.result;
                            // console.log("busineList: ",busineList);
                             var str=" <option value>请选择商户</option>";
                            busineList.forEach(function (t) {
                                 str+="<option value="+t.id+">"+t.busineName+"</option>"
                             });
                            $("select[name='busineId']").html(str);
                        }
                        form.render();
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
    <script type="text/html" id="BusinePayToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="BusinePayExcel">导出商户充值记录</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">商户充值记录</blockquote>
<form class="layui-form" style="margin: 10px">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">操作时间：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" id="payTimeBegin" name="payTimeBegin" placeholder="yyyy-MM-dd HH:mm:ss">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" id="payTimeEnd" name="payTimeEnd" placeholder="yyyy-MM-dd HH:mm:ss">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">选择商户</label>
            <div class="layui-input-inline">
                <select id="busineId" name="busineId" lay-filter="busineId">
                </select>
            </div>
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn"  lay-submit lay-filter="SelectBusinePay" >查询</button>
            </div>
        </div>
    </div>
</form>
<table id="businePayTable" lay-filter="businePayTable"></table>
</body>
</html>