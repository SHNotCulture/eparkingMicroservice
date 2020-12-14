<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>电子券使用记录查询</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <style type="text/css">
      /*  #carNum{
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
   /* .cNum{
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

    </style>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table','form','layer','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;
            var laydate = layui.laydate;
            var laydate1 = layui.laydate;
            var laydate2 = layui.laydate;
            var laydate3 = layui.laydate;
            laydate.render({
                elem: '#inTimeBegin'
                ,type: 'datetime'
            });
            laydate1.render({
                elem: '#inTimeEnd'
                ,type: 'datetime'
            });
            laydate2.render({
                elem: '#outTimeBegin'
                ,type: 'datetime'
            });
            laydate3.render({
                elem: '#outTimeEnd'
                ,type: 'datetime'
            });
            //第一个实例
            tableIns=table.render({
                elem: '#TicketCouponRecordsTable'
                ,height: 650
                ,url: '../ticketCoupon/getTElectronicTicketbyPage' //数据接口  ElectronicCouponRecordsController 查询电子优惠券记录
                ,method:'post'
                // ,toolbar: true
                ,toolbar: '#TicketCouponRecordsToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,title:'电子券使用记录查询'
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'carplate', title: '车牌', width:100, align:'center'}
                    ,{field: 'inTime', title: '进场时间', width: 165, align:'center', sort: true}
                    ,{field: 'outTime', title: '出场时间', width: 165, align:'center', sort: true}
                    ,{field: 'couponCode', title: '电子券编码', width:170, align:'center'}
                    ,{field: 'couponType', title: '优惠类型', width:90, align:'center',templet:'#couponTypeTemp'}
                    ,{field: 'couponPay', title: '优惠金额', width:90, align:'center'}
                    ,{field: 'busineName', title: '所属商户名称', width:120, align:'center'}
                    ,{field: 'parkName', title: '优惠车场名称', width:170, align:'center'}
                    ,{field: 'ticketName', title: '电子券名称', width:200, align:'center'}
                    ,{field: 'isUse', title: '是否被使用', width:105, align:'center',templet:'#isUseTemp'}
                    ,{field: 'permissionDate', title: '电子券使用时间限制', width:160, align:'center'}
                    ,{field: 'remark', title: '说明', width:170, align:'center'}
                ]]
            });

            //监听查询按钮
            form.on('submit(SelectTicketCouponRecord)', function(data){
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                TicketCouponRecordhandle.SelectTicketCouponRecord(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });

            form.render(); //更新全部
        });

        //电子优惠券被使用状态 (0/未使用 1/已使用 2/正在使用 3/已作废)
        var isUseList=[{id:0,name:'未使用'},{id:1,name:'已使用'},{id:2,name:'正在使用'},{id:3,name:'已作废'}];

        var TicketCouponRecordhandle= {
            SelectTicketCouponRecord: function (obj) {
                //这里以搜索为例
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

    <script type="text/html" id="showPhoto">
        <#--<a class="layui-btn layui-btn-xs" lay-event="show">查看</a>-->
        <a class="layui-btn layui-btn-xs" lay-event="detail">查看详情</a>
    </script>

<#--    电子优惠券优惠类型(0/金额 1/时间)-->
    <script type="text/html" id="couponTypeTemp">
        {{# if(d.couponType===0){ }}
        {{ '金额' }}
        {{# }if(d.couponType===1){ }}
        {{ '时间' }}
        {{# } }}
    </script>

<#--    电子优惠券被使用状态-->
    <script type="text/html" id="isUseTemp">
        {{# layui.each(isUseList,function(index,item){ }}
        {{# if(item.id===d.isUse){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>


    <script type="text/html" id="TicketCouponRecords">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="TicketCouponRecordsExcel">导出电子券使用记录</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">电子券使用记录查询</blockquote>

<form class="layui-form" style="margin: 10px" id="TicketCouponRecordsForm" lay-filter="TicketCouponRecordsForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">进场时间</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="inTimeBegin" id="inTimeBegin"  placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">至</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="inTimeEnd" id="inTimeEnd"  placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">出场时间</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="outTimeBegin" id="outTimeBegin"  placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">至</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="outTimeEnd" id="outTimeEnd"  placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">车牌</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" id="carplate" name="carplate" placeholder="可模糊搜索" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">车场名称</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" autocomplete="off" id="parkName" name="parkName" class="layui-input">
            </div>
            <label class="layui-form-label">电子券名称</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" autocomplete="off" id="ticketName" name="ticketName" class="layui-input">
            </div>
            <label class="layui-form-label">电子券编码</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" autocomplete="off" id="couponCode" name="couponCode" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 200px;margin-left: 20px">
                <button class="layui-btn"  lay-submit lay-filter="SelectTicketCouponRecord" id="SelectTicketCouponRecord">查询</button>
            </div>
        </div>
    </div>
</form>
<table id="TicketCouponRecordsTable" lay-filter="TicketCouponRecordsTable"></table>
<#--<div id="showp" style="display: none;border:1px ;width:500px;height:380px;position:absolute;left:50%;margin-left:-250px;top:50%; z-index:999;margin-top:-190px" onclick="hidePhoto()" >-->
</div>

</body>
</html>