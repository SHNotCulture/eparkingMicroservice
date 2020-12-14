<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>停车缴费记录</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table','form','layer','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;
            var laydate = layui.laydate;
            var laydate1 = layui.laydate;
           /* ParkingForm.getParkList();
            ParkingForm.getStatus();
            ParkingForm.getbusineType();
            ParkingForm.getPortName();*/
            laydate.render({
                elem: '#date'
                ,type: 'datetime'
                ,value: '${beginTime}'
            });
            laydate1.render({
                elem: '#dateEnd'
                ,type: 'datetime'
                ,value: '${endTime}'
            });
            //第一个实例
            tableIns=table.render({
                elem: '#parkinoutTable'
                ,height: 500
                ,width:'full-200'
                ,url: '../parkingRecord/getCashPayment' //数据接口
                ,where: {
                    inTime:'${beginTime}',
                    outTime:'${endTime}',
                    chargeType:'${type}'
                    }
                ,method:'post'
                ,loading:true
                ,title:'停车记录'
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:50, fixed: 'left'}
                    ,{field: 'inCarPlate', title: '进场车牌',align: 'center', width: 110}
                    ,{field: 'outCarPlate', title: '出场车牌',align: 'center', width: 110}
                    ,{field: 'carNatureDesc', title: '车辆性质',align: 'left', width: 110}
                    ,{field: 'inTime', title: '进场时间*出场时间',align: 'center', width: 300,templet:'#timeTemp'}
                    ,{field: 'stopTime', title: '停车时长',align: 'center', width: 150}
                    ,{field: 'needPay', title: '应收',align: 'right', width: 60}
                    ,{field: 'actualPay', title: '现金',align: 'right', width: 60}
                    ,{field: 'qpassPay', title: '聚合',align: 'right', width: 60}
                    ,{field: 'coupon', title: '代缴',align: 'right', width: 60}
                    ,{field: 'beforePay', title: '预缴',align: 'right', width: 60}
                    ,{field: 'localcoupon', title: '减免',align: 'right', width: 60}
                    ,{field: 'outType', title: '出场方式',align: 'left', width: 150,align:'center',templet:'#outTypeTemp'}
                    ,{field: 'dutyPerson', title: '当班员', width: 85, align: 'center'}
                    ,{fixed: 'right', title: '操作', align: 'center', width: 100, toolbar: '#Recoreparkingdbar'}
                ]]
            });
            //监听工具条
            table.on('tool(parkinoutTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                    form.val("RecordForm", data);
                    //ParkingForm.showCarNature();
                    $("#inPicPath").attr('src',"http://eparking2.oss-cn-shenzhen.aliyuncs.com"+data.inPicPath);
                    $("#outPicPath").attr('src',"http://eparking2.oss-cn-shenzhen.aliyuncs.com"+data.outPicPath);
                    Recordhandle.detail();
                }
            });

            form.render(); //更新全部
        });
        //获取圆圈中的数据


        //在页面加载完成后执行
        $(document).ready(function () {
            var needpay="";


            //ParkingForm.getPortName();
            //ParkingForm.getOutType();
            //ParkingForm.getParkList();
        })
        //车辆记录增删查改方法
        var Recordhandle={
            select:function (obj) {
                //这里以搜索为例
                tableIns.reload({
                    where:  //设定异步数据接口的额外参数，任意设
                    obj,

                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            } ,
            detail:function () {
                layer.open({
                    title: '查看详情'
                    , type: 1
                    ,area:['800px','600px']
                    ,content: $('#RecordForm')
                });
            }

        };
        var parkList,busineStatusList,busineTypeList;
        var carNatureList=[{id:1,name:'会员临停'},{id:2,name:'月租车'},{id:3,name:'临时车'},{id:4,name:'月租绑定车'},{id:5,name:'月租暂停车'},{id:6,name:'月租过期车'},{id:7,name:'月租无通道权限'}];
        var ePayTypeList=[{id:0,name:'无'},{id:1,name:'银联权益'},{id:2,name:'银联卡'},{id:3,name:'微信'},{id:4,name:'支付宝'},{id:5,name:'无感支付'},{id:6,name:'云闪付'},{id:7,name:'etc'},{id:8,name:'无'}];
        var outTypeList=[{id:1,name:'月租车放行'},{id:2,name:'缴费放行'},{id:3,name:'缴费放行'},{id:4,name:'缴费放行'},{id:5,name:'缴费放行'},{id:6,name:'缴费放行'},{id:7,name:'缴费放行'},{id:13,name:'减免放行'},{id:9 ,name:'未确认放行'},{id:12,name:'无入场记录放行'}]
        //车场更新表单使用方法
        var ParkingForm={
            clean:function(){
                $("#ParkingForm")[0].reset();
            },
           /* //显示车主性质中文
            showCarNature:function () {
                var carn=$("#carNature").val();
                carNatureList.forEach(function (value, index) {
                    if (carn==value.id){
                        $("#showCarNature").val(value.name);
                    }
                })

            },*/
            //获取停车场
            getParkList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarPark",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            //console.log(result);
                            parkList= result.result;
                            var str=" <option value>请选择停车场</option>";
                            parkList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.parkName+"</option>"
                            });
                            $("select[name='parkId']").html(str);
                        }

                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            //获取通道
            getPortName:function () {
                $.ajax({
                    type: 'POST',
                    url: "../BaseDic/getPortName?portType=1",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            //console.log(result);
                            parkList= result.result;
                            var str=" <option value>请选择通道名称</option>";
                            parkList.forEach(function (t) {
                                str+="<option value="+t.portId+">"+t.portName+"</option>"
                            });
                            $("select[id='outPortName']").html(str);
                        }

                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            getOutType:function () {
                $.ajax({
                    type: 'POST',
                    url: "../BaseDic/getOutType",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            //console.log(result);
                            parkList= result.result;
                            var str=" <option value>请选择出场方式</option>";
                            parkList.forEach(function (t) {
                                str+="<option value="+t.outtypeId+">"+t.outtypeName+"</option>"
                            });
                            $("select[id='outType']").html(str);
                        }

                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            //获取状态
            getStatus:function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getBusineStatusList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            busineStatusList=result.result;
                            var str;
                            busineStatusList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("#busineStatus").html(str);
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
            getbusineType:function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getBusineTypeList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            busineTypeList=result.result;
                            var str;
                            busineTypeList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("#busineType").html(str);
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

    <script type="text/html" id="carNatureTemp">
        {{# layui.each(carNatureList,function(index,item){ }}
        {{# if(item.id===d.carNature){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="ePayTypeTemp">
        {{# layui.each(ePayTypeList,function(index,item){ }}
        {{# if(item.id===d.epaytype){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="cashTemp">
        {{# if(d.chargeType===3){ }}
        {{ d.actualPay }}
        {{# }else{ }}
        {{ 0 }}
        {{# } }}
    </script>
    <script type="text/html" id="electronicTemp">
        {{# if(d.chargeType===1){ }}
        {{ d.actualPay }}
        {{# }else{ }}
        {{ 0 }}
        {{# } }}
    </script>
    <script type="text/html" id="outTypeTemp">
        {{# layui.each(outTypeList,function(index,item){ }}
        {{# if(item.id===d.outType){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="timeTemp">
        {{#if(d.inTime===null){}}
        {{d.inTime=" "}}
        {{#}}}
        {{#if(d.outTime===null){}}
        {{d.outTime=" "}}
        {{#}}}
        {{d.inTime+'*'+d.outTime}}
    </script>
    <script type="text/html" id="Recoreparkingdbar">
        <a class="layui-btn layui-btn-xs" lay-event="detail">查看详情</a>
    </script>
    <style type="text/css">
        ul li .carStyle{
            width:180px;
            height:65px;
            border-radius: 5px;
        }
        .carL span{
            display: block;
            margin:4px 0 0 10px;
            width:16px;
            word-wrap: break-word;
            font-size:5px;
            font-weight: bold;
            color:white;
        }
        .input{
            border: 0px;outline:none;cursor: default;
        }
    </style>
</head>
<body>
<#--<div  id="ringNum" style="margin-top: 50px;">-->

    <table id="parkinoutTable" lay-filter="parkinoutTable"></table>
            </body>
            <form class="layui-form"  style="display: none" id="RecordForm" lay-filter="RecordForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">车牌</label>
                        <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="inCarPlate"  autocomplete="off" class="layui-input input" readonly>
            </div>
            <label class="layui-form-label">车牌颜色</label>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="inCarPlateColor" autocomplete="off" class="layui-input input" readonly>
            </div>
            <label class="layui-form-label">车辆性质</label>
            <div class="layui-input-inline" style="width: 120px">
                <input type="text" id="carNatureDesc" name="carNatureDesc" autocomplete="off" class="layui-input input" readonly>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">现金</label>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="actualPay" autocomplete="off"  class="layui-input input" readonly>
            </div>
            <label class="layui-form-label">预缴</label>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="beforePay" autocomplete="off"  class="layui-input input" readonly>
            </div>
            <label class="layui-form-label">聚合</label>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="qpassPay" autocomplete="off"  class="layui-input input" readonly>
            </div>

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">代缴</label>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="coupon" autocomplete="off"  class="layui-input input" readonly>
            </div>
            <label class="layui-form-label">班次</label>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="dutyPerson" autocomplete="off"  class="layui-input input" readonly>
            </div>
            <label class="layui-form-label">记录说明</label>
            <div class="layui-input-inline" style="width: 140px;">
                <input type="text" name="remarks" autocomplete="off"  class="layui-input input" readonly>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">进场通道</label>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="inPortName" autocomplete="off"  class="layui-input input" readonly>
            </div>
            <label class="layui-form-label">出场通道</label>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="outPortName" autocomplete="off"  class="layui-input input" readonly>
            </div>
            <label class="layui-form-label">出场方式</label>
            <div class="layui-input-inline" style="width: 140px;">
                <input type="text" name="outTypeDesc" autocomplete="off"  class="layui-input input" readonly>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">进场时间</label>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="inTime" autocomplete="off"  class="layui-input input" readonly>
            </div>
            <label class="layui-form-label">出场时间</label>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="outTime" autocomplete="off"  class="layui-input input" readonly>
            </div>
            <label class="layui-form-label">停车时长</label>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="text" name="stopTime" autocomplete="off"  class="layui-input input" readonly>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px">进场车辆图片</label>
            <div class="layui-input-inline" style="width: 200px;">
                <img id="inPicPath" style="width: 200px;height: 200px">
            </div>
            <label class="layui-form-label" style="width: 150px">出场车辆图片</label>
            <div class="layui-input-inline" style="width: 200px;">
                <img id="outPicPath" style="width: 200px;height: 200px">
            </div>
        </div>
    </div>
</form>
</html>