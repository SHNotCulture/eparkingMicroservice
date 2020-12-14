<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>钱包支付报表</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        var parkList=${tCompanyParkResultList};
        layui.use(['table','form','layer','laydate'], function() {
            var table = layui.table;
            var form =layui.form;
            var laydate = layui.laydate;
            var layer = layui.layer;

            //执行一个laydate实例
            laydate.render({
                elem: '#beginTime' //指定元素
            });
            laydate.render({
                elem: '#endTime' //指定元素
            });
            //第一个实例
            tableIns=table.render({
                elem: '#CarownerPaymentTable'
                ,height: 520
                ,url: '../carWalletReport/getTCarWalletReportbyPage' //数据接口
                ,method:'post'
                ,toolbar: '#CarownerPaymentToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
//                    {field: 'id', title: 'ID', width:'10%', sort: true, fixed: 'left',align:'center'}
                    {type: 'numbers', title: '序号', width:50, fixed: 'left'}
                    ,{field: 'parkId', title: '停车场名称',templet:'#forParkList',align:'center', width:130}
                    ,{field: 'statisticsTime', title: '统计时间',align: 'center', width: 120}
                    ,{field: 'needPay', title: '车主钱包充值金额', align:'right', width:150}
                    ,{field: 'actualPay', title: '车主钱包实付金额', align:'right', width:150}
                    ,{field: 'usePay', title: '车主钱包支付金额',align:'right', width:150}
                    ,{field: 'totalWalletBanance', title: '总钱包余额', align:'right', width:105}
                    ,{field: 'createTime', title: '创建时间',align: 'center', width: 170}
//                    ,{fixed: 'right', title: '操作', width: 200, toolbar: '#carPortBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(CarownerPaymentTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                var beginTime=$("#beginTime").val();
                var endTime=$("#endTime").val();
                var data={beginTime:beginTime,endTime:endTime}
                switch(obj.event){
                    case 'CarownerPaymentExcel':
                        CarownerPaymentForm.CarownerPaymentExcel(data);
                        break;
                    case 'CarownerPaymentStatistics':
                        CarownerPaymentForm.CarownerPaymentStatistics(data);
                        break;
                    default:
                }
            });
            //监听工具条
            table.on('tool(CarownerPaymentTable)', function(obj){ //注：tool是工具条事件名，CarownerPaymentTable是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

                if(layEvent === 'detail') { //查看
                    if (data.beginTime == null || data.beginTime == "" ) {
                        layer.msg("当班时间参数缺失");
                        return;
                    }
                    else if(data.endTime == null || data.endTime == ""){
                        data.endTime=new Date();
                    }

                    CarownerPaymentForm.detail(data.beginTime, data.endTime);
                }
            });
            //监听查询按钮
            form.on('submit(SelectCarownerPayment)', function(data){
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                CarownerPaymentForm.SelectCarownerPayment(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听统计按钮
/*            form.on('submit(carownerPaymentStatistics)', function(data){
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                CarownerPaymentForm.CarownerPaymentStatistics(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });*/
            // CarownerPaymentForm.getParkList();
            form.render(); //更新全部
        });

        //页面加载初始化
        $(document).ready(function () {

        });
        var parkList;
        //车场更新表单使用方法
        var CarownerPaymentForm={
            //获取停车场集合
/*            getParkList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarPark",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            parkList= result.result;
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
            },*/
            CarownerPaymentExcel:function (object) {
                window.location.href="../Base/exportListforCarownerPayment?beginTime="+object.beginTime+"&endTime="+object.endTime;
            },
            SelectCarownerPayment: function (obj) {
                //这里以搜索为例
                tableIns.reload({
                    where:
                    obj,
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            CarownerPaymentStatistics:function (obj) {
                $.ajax({
                    type: 'POST',
                    url: "../carWalletReport/statistics",
                    data:obj,
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            layer.msg(result.result, {icon: 6});
                            CarownerPaymentForm.SelectCarownerPayment(obj);
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
    </Script>
    <script type="text/html" id="CarownerPaymentToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="CarownerPaymentExcel">导出车主钱包报表信息</button>
            <button class="layui-btn layui-btn-sm" lay-event="CarownerPaymentStatistics">手动统计</button>
        </div>
    </script>
    <script type="text/html" id="Recoreparkingdbar">
        <a  class="layui-btn layui-btn-xs" lay-event="detail">当班详情</a>
    </script>

    <script type="text/html" id="forParkList">
        {{# layui.each(parkList,function(index,item){ }}
        {{# if(item.id===d.parkId){ }}
        {{ item.parkName }}
        {{# return; }}
        {{# }  }}
        {{#  }); }}
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">钱包支付报表 </blockquote>
<form class="layui-form " style="margin: 10px">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">统计时间：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" name="beginTime" id="beginTime" placeholder="yyyy-MM-dd">
            </div>
            <div class="layui-form-mid">至</div>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input"  name="endTime" id="endTime" placeholder="yyyy-MM-dd">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn" lay-submit lay-filter="SelectCarownerPayment" >查询</button>
            </div>
<#--            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn" lay-submit lay-filter="carownerPaymentStatistics" >手动统计</button>
            </div>-->
        </div>
    </div>
</form>
<table id="CarownerPaymentTable" lay-filter="CarownerPaymentTable"></table>
</body>
<div id="CarownerPaymentDetail" style="display: none">
    <table id="parkinoutTable" lay-filter="parkinoutTable" ></table>
</div>
</html>