<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>当班支付对账</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table','form','layer','laydate'], function() {
            var table = layui.table;
            var form =layui.form;
            var laydate = layui.laydate;
            var layer = layui.layer;

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
                elem: '#PaymentTable'
                ,height: 565
                ,url: '../cashPayment/getTParkDutyListbyPage' //数据接口
                ,method:'post'
                ,toolbar: '#CashPaymentToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,totalRow:true
                ,page: true //开启分页
                ,cols: [[ //表头
//                    {field: 'id', title: 'ID', width:'10%', sort: true, fixed: 'left',align:'center'}
//                    ,{field: 'parkId', title: '停车场名称',templet:'#forParkList',align:'center'}
                    {type: 'numbers', title: '序号', width:50, fixed: 'left'}
                    ,{field: 'dutyPerson', title: '当班员',align:'center', width:170}
                    ,{field: 'beginTime', title: '开始时间', width: 160, align:'center'}
                    ,{field: 'endTime', title: '结束时间', width: 160, align:'center'}
                    //,{field: 'lastChargeTime', title: '最后一笔收费时间', align:'center', width:'200'}
                    ,{field: 'needTotal', title: '应收总额',align:'right', width:100,totalRow: true}
                    ,{field: 'actualTotal', title: '现金总计', align:'right', width:100,totalRow: true}
                    ,{field: 'qpasspayTotal', title: '聚合总计', align:'right', width:100,totalRow: true}
                    ,{field: 'prepayTotal', title: '预缴总计', align:'right', width:100}
                    ,{field: 'behalfpayTotal', title: '代缴总计',align:'right', width:100}
                    ,{field: 'localcouponTotal', title: '减免总计', align:'right', width:100,totalRow: true}
                    ,{field: 'freePass', title: '减免次数', align:'center', width:90}
                    ,{field: 'noconfirmPass', title: '未确认次数',align:'center', width:100}
                    ,{field: 'updatecarplatesTotal', title: '修改车牌次数',align:'center', width:120}
                   /* ,{field: 'beginTime', title: '当班时间', width:'200',sort:true,align:'center'}
                    ,{field: 'endTime', title: '交班时间', align:'center', width:'200'}
                    ,{field: 'actualTotal', title: '实收金额', align:'center', width:'200'}
                    ,{field: 'payOnlineTotal', title: '线上支付总额', align:'center', width:'200'}
                    ,{field: 'localcouponTotal', title: '线下减免', align:'center', width:'200'}
                    ,{field: 'userId', title: '岗亭', align:'center', width:'200'}*/
                    ,{fixed: 'right', title: '操作',align:'center', width: 100, toolbar: '#Recoreparkingdbar'}
//                    ,{fixed: 'right', title: '操作', width: 200, toolbar: '#carPortBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(PaymentTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'cashPaymentExcel':
                        var beginTime=$("#beginTime").val();
                        var endTime=$("#endTime").val();
                        var dutyPerson=$("#dutyPerson").val();
                        var data={beginTime:beginTime,endTime:endTime,dutyPerson:dutyPerson}
                        cashPaymentForm.cashPaymentExcel(data);
                        break;
                    default:
                };
            });
            //监听工具条
            table.on('tool(PaymentTable)', function(obj){ //注：tool是工具条事件名，PaymentTable是table原始容器的属性 lay-filter="对应的值"
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

                    cashPaymentForm.detail(data.beginTime, data.endTime);
                }
            });
            //监听查询按钮
            form.on('submit(SelectCashPayment)', function(data){
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                cashPaymentForm.SelectCashPayment(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            form.render(); //更新全部
        });

        //页面加载初始化
        $(document).ready(function () {
            cashPaymentForm.getParkList();
        })
        var parkList;
        //车场更新表单使用方法
        var cashPaymentForm={
            //获取停车场集合
            getParkList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarPark",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            parkList= result.data;
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
            //当班详情
            detail:function (beginTime,endTime) {
                layer.open({
                    title: '当班详情'
                    , type: 2
                    ,area:['1000px','700px']
                    , content: '../view/cashPaymentDetail?beginTime=' + beginTime + "&endTime=" + endTime+"&type="+3
                });
            } ,
            cashPaymentExcel:function (object) {
                window.location.href="../Base/exportListforCashPayment?beginTime="+object.beginTime+"&endTime="+object.endTime+"&dutyPerson="+object.dutyPerson;
            },
            SelectCashPayment: function (obj) {
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
    </Script>
    <script type="text/html" id="CashPaymentToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="cashPaymentExcel">导出当班支付对账信息</button>
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
<blockquote class="layui-elem-quote">当班支付对账 </blockquote>
<form class="layui-form " style="margin: 10px">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">当班员：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <#--<input type="text" name="price_max" placeholder="当班员" autocomplete="off" class="layui-input">-->
                <input type="text" name="dutyPerson" id="dutyPerson" placeholder="当班员" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">当班时间：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" name="beginTime" placeholder="yyyy-MM-dd HH:mm:ss" id="beginTime">
            </div>
            <div class="layui-form-mid">至</div>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" name="endTime" placeholder="yyyy-MM-dd HH:mm:ss" id="endTime">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn" lay-submit lay-filter="SelectCashPayment" >查询</button>
            </div>
        </div>
    </div>
</form>
<table id="PaymentTable" lay-filter="PaymentTable"></table>
</body>
<div id="cashPaymentDetail" style="display: none">
<table id="parkinoutTable" lay-filter="parkinoutTable" ></table>
</div>
</html>