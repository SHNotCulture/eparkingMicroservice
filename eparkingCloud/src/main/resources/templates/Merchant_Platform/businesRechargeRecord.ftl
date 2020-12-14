<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>充值记录</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <style type="text/css">
    /*    #carNum{
            height:80px;
            border-radius: 5px;
    }*/
        ul{
            display: flex;
            justify-content:space-around;
            overflow: hidden;
        }
    ul li{
        margin-top:8px;
    }
    /*ul li .carStyle{
        width:180px;
        height:65px;
        border-radius: 5px;
    }
    .carL{
        text-align: center;
        width:45px;
        height:80px;
        display:inline-block;
    }*/
    .carL span{
        display: block;
        margin:4px 0 0 10px;
        width:16px;
        word-wrap: break-word;
        font-size:5px;
        font-weight: bold;
        color:white;
    }
    /*.cNum{
        font-size:30px;
        font-weight: bold;
        text-align: center;
        margin-left:40px;
    }
    .Btn{
        display: flex;
        justify-content:space-around;
        overflow: hidden;
    }*/
    .Btn button{
        width:100px;
        height:30px;
        margin:10px 15px 10px 15px;
        text-align: center;
        color:white;
        font-weight: bold;
        font-size:18px;
        border-radius: 3px;
        border:none;
        cursor: pointer;
    }
    /*.carStyle{*/
            /*display: flex;*/
            /*justify-content:space-around;*/
        /*}*/
    </style>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table','form','layer','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;
            var laydate = layui.laydate;
            var laydate1 = layui.laydate;
            laydate.render({
                elem: '#payTimeBegin'
                ,type: 'datetime'
            });
            laydate1.render({
                elem: '#payTimeEnd'
                ,type: 'datetime'
            });

            //第一个实例
            tableIns=table.render({
                elem: '#MerchantRechargeRecordsTable'
                ,height: 650
                ,url: '../businesRechargeRecord/getTBusinePaybyPage' //数据接口
                ,method:'post'
                // ,toolbar: true
                ,toolbar: '#MerchantRechargeRecordsToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,title:'充值记录查询'
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'parkName', title: '所属停车场', width:170, align:'center'}
                    ,{field: 'orderNumber', title: '订单号', width:270, align:'center'}
                    ,{field: 'needPay', title: '充值金额', width:170, align:'center'}
                    ,{field: 'actualPay', title: '实收金额', width:170, align:'center'}
                    ,{field: 'payTime', title: '充值时间', width:170, align:'center', sort: true}
                    ,{field: 'balance', title: '账户余额', width:170, align:'center'}
                    ,{field: 'remark', title: '备注', width:170, align:'center'}

                ]]
            });

            //监听查询按钮
            form.on('submit(SelectMerchantRechargeRecords)', function(data){
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                MerchantRechargeRecordshandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });

            form.render(); //更新全部
        });
        //获取圆圈中的数据

        var MerchantRechargeRecordshandle= {
            select: function (obj) {
                //这里以搜索为例
                // console.log(obj);
                tableIns.reload({
                    where:
                    obj,
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            }
        }

    </script>


</head>
<body>
<blockquote class="layui-elem-quote">充值记录</blockquote>

<form class="layui-form" style="margin: 10px" id="MerchantRechargeRecordsForm" lay-filter="MerchantRechargeRecordsForm">


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">充值日期</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="payTimeBegin" id="payTimeBegin"  placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label" style="width: 30px">至</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="payTimeEnd" id="payTimeEnd"  placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn"  lay-submit lay-filter="SelectMerchantRechargeRecords" id="SelectMerchantRechargeRecords">搜索</button>
            </div>
        </div>
    </div>
</form>
<table id="MerchantRechargeRecordsTable" lay-filter="MerchantRechargeRecordsTable"></table>
<#--<div id="showp" style="display: none;border:1px ;width:500px;height:380px;position:absolute;left:50%;margin-left:-250px;top:50%; z-index:999;margin-top:-190px" onclick="hidePhoto()" >-->
</div>

</body>
</html>