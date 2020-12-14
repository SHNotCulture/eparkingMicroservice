<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>缴费记录</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <style type="text/css">
        .demo1 {
            width: 150px;
            height: 150px;
            line-height: 150px;
            text-align: center;
            background-color: #009688;
            color: #fff;
            border-radius: 50%;
        }

        .ring {
            display: flex;
            justify-content: space-around;
            overflow: hidden;
        }

        .ring li {
            display: flex;
            flex-direction: column;
        }

        .demoNum {
            margin-left: 45px;
            margin-top: 15px;
            font-weight: bold;
        }
    </style>
    <script type="text/javascript">
        var tableIns;
        var tablePayment;
        var tableBusineCoupon;
        var carStatusList =[{id:1,name:'正常'},{id:2,name:'过期'},{id:3,name:'暂停'},{id:4,name:'无通道权限'},{id:5,name:'绑定车'}];
        var busineCouponTypeList = [{id: 0, name: '限制金额'}, {id: 1, name: '时间'}, {id: 2, name: '固定金额'}, {id: 3,name: '电子劵'}, {id: 4, name: '会员优惠'}];
        var parkList, busineStatusList, busineTypeList,epaytypeList,paymentMethodList,acquiringChannelList,paymentChannelList;
        var carNatureList = [{id: 1, name: '会员临停'}, {id: 2, name: '月租车'}, {id: 3, name: '临时车'}, {
            id: 4,
            name: '月租绑定车'
        }, {id: 5, name: '月租暂停车'}, {id: 6, name: '月租过期车'}, {id: 7, name: '月租无通道权限'}];
        var outTypeList = [{id: 1, name: '月租车放行'}, {id: 2, name: '出口现金缴费放行'}, {
            id: 4,
            name: '聚合支付自动放行'
        },  { id: 9, name: '保安未确认' }, {id: 12, name: '无入场记录放行'}, {id: 13, name: '减免放行'},{id: 14, name: '无入场记录强制收费放行'},{id: 15, name: '车主钱包支付'},{id: 16, name: '无费用放行'}];

        layui.use(['table', 'form', 'layer', 'laydate'], function () {
            var table = layui.table;
            tablePayment = layui.table;
            tableBusineCoupon = layui.table;
            var form = layui.form;
            var layer = layui.layer;
            var laydate = layui.laydate;
            var laydate1 = layui.laydate;
            var laydate2 = layui.laydate;
            var laydate3 = layui.laydate;

            laydate.render({
                elem: '#date'
                , type: 'datetime'
            });
            laydate1.render({
                elem: '#dateEnd'
                , type: 'datetime'
            });
            laydate2.render({
                elem: '#settleDateBegin'
                , type: 'datetime'
            });
            laydate3.render({
                elem: '#settleDateEnd'
                , type: 'datetime'
            });
            //第一个实例
            tableIns = table.render({
                elem: '#parkinoutTable'
                , height: 580
                , width: 'full-200'
                , url: '../parkingRecord/getParkinout' //数据接口
                , method: 'post'
                , toolbar: '#RecordToolbar'
                , defaultToolbar: ['filter', 'print']
                , loading: true
                , totalRow: true
                , title: '停车记录'
                , page: true //开启分页
                , cols: [[ //表头
                    //{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                    {type: 'numbers', title: '序号', width: 50, fixed: 'left'}
                    , {field: 'carNatureDesc', title: '车辆性质',align: 'left', width: 130}
                    //,{field: 'inPortName', title: '进出通道', width:150}
                    , {field: 'carStatus', title: '车辆状态',align: 'left', width: 110, templet: '#carStatusTemp'}
                    , {field: 'chargeTypeDesc', title: '计费规则',align: 'left', width: 100}
                    , {field: 'outType', title: '出场方式',align: 'left', width: 150, templet: '#outTypeTemp'}
                    ,{field: 'epaytype', title: '支付方式',align: 'left', width: 100,templet:'#epaytypeTemp'}
                    , {field: 'inCarPlate', title: '车牌',align: 'center', width: 110, templet: '#carPlateTemp'}
                    , {field: 'inPortName', title: '入口',align: 'center', width: 100}
                    , {field: 'outPortName', title: '出口',align: 'center', width: 100}
                    , {field: 'inTime', title: '进场时间*出场时间',align: 'center', width: 300, templet: '#timeTemp'}
                    , {field: 'stopTime', title: '停车时长',align: 'center', width: 150}
                    , {field: 'needPay', title: '应收',align: 'right', width: 60,totalRow: true}
                    , {field: 'actualPay', title: '现金',align: 'right', width: 60,totalRow: true}
                    , {field: 'qpassPay', title: '聚合',align: 'right', width: 60,totalRow: true}
                    //,{field: 'electronic', title: '电子', width: 80 ,templet:'#electronicTemp'}
                    //,{field: 'merchantId', title: '代缴商户', width: 100}
                    /*,{field: 'prepaytype', title: '预缴类型', width: 100}
                    ,{field: 'beforePay', title: '预缴', width: 70}*/
                    , {field: 'coupon', title: '代缴',align: 'right', width: 60,totalRow: true}
                    , {field: 'beforePay', title: '预缴',align: 'right', width: 60,totalRow: true}
                    , {field: 'localcoupon', title: '减免',align: 'right', width: 60,totalRow: true}
                    // ,{field: 'coupon', title: '聚合类型', width: 70}
                    // ,{field: 'coupon', title: '减免金额', width: 70}
                    , {field: 'localcouponName', title: '减免类型', width: 90, align: 'center'}
                    //,{field: 'dutyPerson', title: '值班', width: 70}
                    , {field: 'dutyPerson', title: '当班员', width: 85, align: 'center'}
                    //,{field: 'remarks', title: '备注', width: 70}
                    , {fixed: 'right', title: '操作',align: 'center', width: 165, toolbar: '#Recoreparkingdbar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(parkinoutTable)', function (obj) {
                switch (obj.event) {
                    case 'RecordExcel':
                        var outPortId = $('#outPortName option:selected').val();
                        var outType = $('#outType option:selected').val();
                        var inCardCode = $('#prepaytype option:selected').val();
                        var inCarPlate = $('#inCarPlate').val();
                        var outTimeBegin = $('#date').val();
                        var outTimeEnd = $('#dateEnd').val();
                        var dutyPerson = $('#dutyPerson').val();
                        //var parkingState=$("#parkingState").val();
                        var data = {
                            outPortId: outPortId,
                            outType: outType,
                            inCardCode: inCardCode,
                            inCarPlate: inCarPlate,
                            outTimeBegin: outTimeBegin,
                            outTimeEnd: outTimeEnd,
                            dutyPerson: dutyPerson
                        };
                        // console.log(data);
                        ParkingForm.RecordExcel(data);
                        break;
                    default:
                }
                ;

            });
            //监听工具条
            table.on('tool(parkinoutTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if (layEvent === 'detail') { //查看
                    //do somehing
                    // console.log(data);
                    form.val("RecordForm", data);
                    // ParkingForm.showCarNature();
                    $("#inPicPath").attr('src', "http://eparking2.oss-cn-shenzhen.aliyuncs.com" + data.inPicPath);
                    $("#outPicPath").attr('src', "http://eparking2.oss-cn-shenzhen.aliyuncs.com" + data.outPicPath);
                    Recordhandle.detail();
                }/* else if (layEvent === 'busineCouponDetail') {//查看优惠详情
                    ParkingForm.showBusineCouponDetail(data);
                }*/ else if (layEvent === 'paymentDetail') {//查看缴费详情
                    ParkingForm.showPaymentDetail(data.orderId);
                    layer.open({
                        title: '缴费详情'
                        , type: 1
                        ,area:['1128px','700px']
                        ,content: $('#paymentForm')
                    });
                }
            });

            //监听查询按钮
            form.on('submit(SelectParkinout)', function (data) {
/*                console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                Recordhandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });

            //监听缴费明细查询按钮
/*            form.on('submit(selectPayment)', function (data) {
                paymentHandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });*/
            ParkingForm.getStatus();
            ParkingForm.getbusineType();
            ParkingForm.getPortName(form);
            // ParkingForm.getCarPayRule(form);
            form.render(); //更新全部
        });
        //获取圆圈中的数据


        //在页面加载完成后执行
        $(document).ready(function () {
            var needpay = "";
            ParkingForm.getOutType();
            ParkingForm.getepaytype();
            ParkingForm.getPaymentMethod();
            ParkingForm.getAcquiringChannel();
            ParkingForm.getPaymentChannel();

            //ParkingForm.getPayType();
            //document.getElementById("needpay").innerHTML=aaaaa;
            /* CarPlateForm.getCarPayRule();
             CarPlateForm.getComPany();*/

        })
        //车辆记录增删查改方法
        var Recordhandle = {
            select: function (obj) {
                //这里以搜索为例
                tableIns.reload({
                    where:  //设定异步数据接口的额外参数，任意设
                    obj,
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            detail: function () {
                layer.open({
                    title: '查看详情'
                    , type: 1
                    , area: ['870px', '800px']
                    , content: $('#RecordForm')
                    , resize: false
                });
            }

        };
        var paymentHandle = {
            select: function (obj) {
                //这里以搜索为例
                tablePayment.reload({
                    where:  //设定异步数据接口的额外参数，任意设
                    obj,
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            }}
        //车场更新表单使用方法
        var ParkingForm = {
            clean: function () {
                $("#ParkingForm")[0].reset();
            },
            //显示车主性质中文
            showCarNature: function () {
                var carn = $("#carNature").val();
                carNatureList.forEach(function (value, index) {
                    if (carn == value.id) {
                        $("#showCarNature").val(value.name);
                    }
                })

            },
            //显示优惠详情
            showBusineCouponDetail: function (data) {
                $.ajax({
                    type: 'POST',
                    url: "../parkingRecord/getBusineCoupon",
                    dataType: "JSON",
                    data: data,
                    success: function (result) {
                        if (result.code == 0) {
                            // console.log(result);
                            if (result.result.length > 0) {
                                /*console.log(result.data);
                                console.log(result.result);
                                console.info("表格" + result.data);*/
                                ParkingForm.showbusineCouponDetailTable(result.result);
                                layer.open({
                                    title: '查看优惠详情'
                                    , type: 1
                                    , area: ['870px', '400px']
                                    , content: $('#showBusineCouponDetailDiv')
                                    , resize: false
                                });
                            } else {
                                layer.msg('未使用优惠');
                            }
                        }

                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            showPaymentDetail: function (obj) {
                tablePayment.render({
                    elem: '#paymentDetail'
                    ,height: 600
                    ,url: '../tPaymentDetails/getTPaymentDetailsbyPage' //数据接口
                    ,method:'post'
                    ,data:{orderId:obj}
                    ,loading:true
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        {type: 'numbers', title: '序号', width:80, fixed: 'left',sort: true}
                        ,{field: 'carPlate', title: '车牌', width: 100}
                        //,{field: 'orderId', title: 'cloud订单号', width:110}
                        ,{field: 'amount', title: '金额',width:100}
                        ,{field: 'paymentMethod', title: '支付方式', width:100, templet: '#paymentMethodTemp'}
                        ,{field: 'acquiringChannel', title: '收单渠道',width:100, templet: '#acquiringChannelTemp'}
                        ,{field: 'paymentChannel', title: '支付渠道',width:100, templet: '#paymentChannelTemp'}
                        ,{field: 'paymentOrderId', title: '支付流水号',width:100}
                        ,{field: 'inTime', title: '进场时间',width:160}
                        ,{field: 'outTime', title: '出场时间',width:160}
                    ]]
                });
            },
            /*getBusine:function(id){
                $.ajax({
                    type: 'POST',
                    url: "../parkingRecord/getBusineById",
                    dataType: "JSON",
                    data:{id:id},
                    success: function (result) {
                        if(result.code==0)
                        {
                        }

                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },*/
            showbusineCouponDetailTable: function (data) {
                tableBusineCoupon.render({
                    elem: '#busineCouponDetail'
                    , height: 300
                    , data: data
                    //,url: '../Merchant/getBusineTicket' //数据接口
                    //,method:'post'
                    /*,toolbar: true
                    ,toolbar: '#toolbarBlack'*/
                    //,defaultToolbar: ['filter', 'print']
                    , loading: true
                    , page: true //开启分页
                    , cols: [[ //表头
                        /* {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}*/
                        /*,{field: 'parkId', title: '停车场', width:120,templet:'#parkIdTemp'}*/
                        //{type:'radio'}
                        {type: 'numbers', title: '序号', width: 50}
                        , {field: 'busineType', title: '优惠类型', width: 100, templet: '#busineTypeTemp'}
                        , {field: 'busineName', title: '商家名称', width: 100}
                        , {field: 'couponPay', title: '优惠价值', width: 100}
                        , {field: 'createDate', title: '优惠时间', width: 170}
                        , {field: 'status', title: '优惠状态', width: 100}
                    ]]
                });
            },
            //获取停车场
            getParkList: function () {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarPark",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            parkList = result.data;
                            var str = " <option value>请选择停车场</option>";
                            parkList.forEach(function (t) {
                                str += "<option value=" + t.id + ">" + t.parkName + "</option>"
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
            getPortName: function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../BaseDic/getPortName?portType=1",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            parkList = result.result;
                            var str = " <option value>请选择通道名称</option>";
                            parkList.forEach(function (t) {
                                str += "<option value=" + t.portId + ">" + t.portName + "</option>"
                            });
                            $("select[id='outPortName']").html(str);
                        }
                        form.render();
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            getOutType: function () {
                var str = " <option value>请选择出场方式</option>";
                outTypeList.forEach(function (t) {
                    str += "<option value=" + t.id + ">" + t.name + "</option>"
                });
                $("select[name='outType']").html(str);
/*                $.ajax({
                    type: 'POST',
                    url: "../BaseDic/getOutType",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            console.log(result.result);
                            parkList = result.result;
                            var str = " <option value>请选择出场方式</option>";
                            parkList.forEach(function (t) {
                                str += "<option value=" + t.outtypeId + ">" + t.outtypeName + "</option>"
                            });
                            $("select[name='outType']").html(str);
                        }

                    },
                    error: function (result) {
                        console.log(result);
                    }
                })*/
            },
            /*getPayType:function () {
                $.ajax({
                    type: 'POST',
                    url: "../BaseDic/getPayType",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            console.log(result);
                            parkList= result.data;
                            var str=" <option value>请选择缴费方式</option>";
                            parkList.forEach(function (t) {
                                str+="<option value="+t.paytypeId+">"+t.payname+"</option>"
                            });
                            $("select[id='prepaytype']").html(str);
                        }

                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },*/
            //获取状态
            getStatus: function () {
                busineStatusList = [{id: 0, name: '休业'}, {id: 1, name: '营业'}];
                var str = " <option value>请选择</option>";
                busineStatusList.forEach(function (t) {
                    str += "<option value=" + t.id + ">" + t.name + "</option>"
                });
                $("#busineStatus").html(str);
            },
            getbusineType: function () {
                busineTypeList = [{id: 0, name: '类别1'}, {id: 1, name: '类别2'}];
                var str = " <option value>请选择</option>";
                busineTypeList.forEach(function (t) {
                    str += "<option value=" + t.id + ">" + t.name + "</option>"
                });
                $("#busineType").html(str);
            },
            RecordExcel: function (object) {
                window.location.href = "../Base/exportPayFeeRecord?outPortId=" + object.outPortId + "&outType=" + object.outType + "&inCarPlate=" + object.inCarPlate + "&inCardCode=" + object.inCardCode + "&outTimeBegin=" + object.outTimeBegin+ "&outTimeEnd=" + object.outTimeEnd + "&dutyPerson=" + object.dutyPerson;
            },
            getepaytype: function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getEPayType",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            epaytypeList = result.result;
                            var str = " <option value>请选择支付方式</option>";
                            epaytypeList.forEach(function (t) {
                                str += "<option value=" + t.id + ">" + t.name + "</option>"
                            });
                            $("select[name='epaytype']").html(str);
                        }

                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            getPaymentMethod: function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getPaymentMethod",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            paymentMethodList = result.result;
                        }
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            getAcquiringChannel: function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getAcquiringChannel",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            acquiringChannelList = result.result;
                        }
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            getPaymentChannel: function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getPaymentChannel",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            paymentChannelList = result.result;
                        }
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            }
/*            getCarPayRule:function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../carPayRule/getPayRule",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            payRuleList= result.result;
                            var str="<option value>请选择车辆性质</option>";
                            payRuleList.forEach(function (t) {
                                str+="<option value="+t.ruleName+">"+t.ruleName+"</option>"
                            });
                            $("select[name='carNatureDesc']").html(str);
                        }
                        form.render();
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            }*/
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
    <script type="text/html" id="carPlateTemp">
        {{#if(d.inCarPlate===null){}}
        {{d.outCarPlate}}
        {{#}else{}}
        {{d.inCarPlate}}
        {{#}}}
    </script>
    <script type="text/html" id="epaytypeTemp">
        {{# layui.each(epaytypeList,function(index,item){ }}
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
    <script type="text/html" id="busineTypeTemp">
        {{# layui.each(busineCouponTypeList,function(index,item){ }}
        {{# if(item.id===d.busineType){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="couponTypeTemp">
        {{# layui.each(busineCouponTypeList,function(index,item){ }}
        {{# if(item.id===d.couponType){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="paymentMethodTemp">
        {{# layui.each(paymentMethodList,function(index,item){ }}
        {{# if(item.id===d.paymentMethod){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="acquiringChannelTemp">
        {{# layui.each(acquiringChannelList,function(index,item){ }}
        {{# if(item.id===d.acquiringChannel){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="paymentChannelTemp">
        {{# layui.each(paymentChannelList,function(index,item){ }}
        {{# if(item.id===d.paymentChannel){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="carStatusTemp">
        {{# layui.each(carStatusList,function(index,item){ }}
        {{# if(item.id===d.carStatus){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="Recoreparkingdbar">
        <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="detail">查看详情</a>
<#--        <a class="layui-btn layui-btn-xs" lay-event="busineCouponDetail">优惠详情</a>-->
        <a class="layui-btn layui-btn-xs" lay-event="paymentDetail">缴费详情</a>
    </script>
    <script type="text/html" id="RecordToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="RecordExcel">导出缴费记录信息</button>
        </div>
    </script>
    <style type="text/css">
        ul li .carStyle {
            width: 180px;
            height: 65px;
            border-radius: 5px;
        }

        .carL {
            text-align: center;
            width: 45px;
            height: 80px;
            display: inline-block;
        }

        .carL span {
            display: block;
            margin: 4px 0 0 10px;
            width: 16px;
            word-wrap: break-word;
            font-size: 5px;
            font-weight: bold;
            color: white;
        }

        .cNum {
            font-size: 30px;
            font-weight: bold;
            text-align: center;
            margin-left: 40px;
        }
    </style>
</head>
<body>
<blockquote class="layui-elem-quote">缴费记录</blockquote>
<div id="ringNum">
    <#--<blockquote class="layui-elem-quote">临停车 </blockquote>-->
    <#--<div  id="ringNum">
    <ul class="ring" >
        <li>
            <div class="demo1 ring_one" id="needpay" style="font-size: 50px">${needPay}</div>
            <div class="demoNum">临停应收</div>
        </li>
        <li>
            <div class="demo1 ring_two" style="font-size: 50px">${cashPay}</div>
            <div class="demoNum">临停现金</div>
        </li>
        <li>
            <div class="demo1 ring_two" style="font-size: 50px">${beforePay}</div>
            <div class="demoNum">临停预缴</div>
        </li>
        <li>
            <div class="demo1 ring_two" style="font-size: 50px">${coupon}</div>
            <div class="demoNum">临停代缴</div>
        </li>
        <li>
            <div class="demo1 ring_two" style="font-size: 50px">${polymerization}</div>
            <div class="demoNum">临停聚合</div>
        </li>
        <li>
            <div class="demo1 ring_two" style="font-size: 50px">${relief}</div>
            <div class="demoNum">临停减免</div>
        </li>
    </ul>
    </div>-->
    <#--<div class="layui-row layui-col-space5">
        <div class="layui-col-md2">
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>临停应收</span>
                </div>
                <span class="cNum">${needPay}</span>
            </div>
        </div>
        <div class="layui-col-md2">
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>临停现金</span>
                </div>
                <span class="cNum">${cashPay}</span>
            </div>
        </div>
        <div class="layui-col-md2">
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>临停预缴</span>
                </div>
                <span class="cNum">${beforePay}</span>
            </div>
        </div>
        <div class="layui-col-md2">
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>临停代缴</span>
                </div>
                <span class="cNum">${coupon}</span>
            </div>
        </div>
        <div class="layui-col-md2">
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>临停聚合</span>
                </div>
                <span class="cNum">${polymerization}</span>
            </div>
        </div>
        <div class="layui-col-md2">
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>临停减免</span>
                </div>
                <span class="cNum">${relief}</span>
            </div>
        </div>
    </div>-->
    <#--<div class="layui-row layui-col-space10">

    </div>-->
    <form class="layui-form" style="margin: 10px" id="ParkingForm" lay-filter="ParkingForm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">出场通道</label>
                <div class="layui-input-inline" style="width: 200px;">
                    <select id="outPortName" name="outPortId" lay-filter="outPortId">
                    </select>
                </div>
                <label class="layui-form-label">出场方式</label>
                <div class="layui-input-inline" style="width: 200px;">
                    <select id="outType" name="outType" lay-filter="outType">
                    </select>
                </div>
                <label class="layui-form-label">缴费类型</label>
                <div class="layui-input-inline" style="width: 200px;">
                    <select id="prepaytype" name="inCardCode" lay-filter="prepaytype">
                        <option value>请选择缴费方式</option>
                        <option value="actual_pay">现金</option>
                        <option value="qpass_pay">聚合</option>
                        <option value="coupon">代缴</option>
                        <option value="before_pay">预缴</option>
                        <option value="localcoupon">线下减免</option>
                    </select>
                </div>
                <label class="layui-form-label">支付方式</label>
                <div class="layui-input-inline" style="width: 200px;">
                    <select id="epaytype" name="epaytype" lay-filter="epaytype">
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">计费规则</label>
                <div class="layui-input-inline" style="width: 200px;">
                    <input type="text" id="chargeTypeDesc" name="chargeTypeDesc" placeholder="计费规则" autocomplete="off" class="layui-input">
                </div>
                <label class="layui-form-label">出场时间</label>
<#--                <div class="layui-input-inline" style="width: 115px;">
                    <select id="timeType" name="timeType" lay-filter="timeType" style="width: 100px">
                        <option value>请选择时间</option>
                        <option value="1">进场时间</option>
                        <option value="2">出场时间</option>
                        <option value="3">记录时间</option>
                    </select>
                </div>-->
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" name="outTimeBegin" id="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off"
                               class="layui-input">
                    </div>
                    <label class="layui-form-label">至</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" name="outTimeEnd" id="dateEnd" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off"
                               class="layui-input">
                    </div>
                    <label class="layui-form-label">当班员</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" id="dutyPerson" name="dutyPerson" placeholder="当班员" autocomplete="off" class="layui-input">
                    </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">车牌</label>
                <div class="layui-input-inline" style="width: 200px;">
                    <input type="text" id="inCarPlate" name="inCarPlate" placeholder="车牌" autocomplete="off" class="layui-input">
                </div>
                <label class="layui-form-label">车辆性质</label>
                <div class="layui-input-inline" style="width: 200px;">
                    <input type="text" id="carNatureDesc" name="carNatureDesc" placeholder="车辆性质" autocomplete="off" class="layui-input">
                </div>
                <label class="layui-form-label" style="width: 120px">是否修改过车牌</label>
                <div class="layui-input-inline" style="width: 50px;">
                    <input type="checkbox" name="isUpdateCarplate" lay-skin="switch" lay-text="是|否">
                </div>
                <div class="layui-input-inline" style="width: 200px;margin-left: 220px">
                    <button class="layui-btn" lay-submit lay-filter="SelectParkinout">查询</button>
                </div>
            </div>
        </div>
    </form>
    <table id="parkinoutTable" lay-filter="parkinoutTable"></table>
</body>
<form class="layui-form " style="display: none" id="RecordForm" lay-filter="RecordForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 120px;">车牌:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="inCarPlate" disabled
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">车牌颜色:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="inCarPlateColor" disabled
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">班次:</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="dutyPerson" disabled
                       autocomplete="off" class="layui-input">
            </div>
            <#--  <label class="layui-form-label">进场方式</label>
              <div class="layui-input-inline" style="width: 100px;">
                  <input type="text" name="inType" disabled autocomplete="off" class="layui-input">
              </div>-->
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 120px;">现金:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="actualPay" disabled
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">车辆性质:</label>
            <div class="layui-input-inline" style="width: 100px">
                <input style="border: 0px;outline:none;cursor: default;" type="text" id="carNatureDescRecord" disabled
                       name="carNatureDesc" autocomplete="off" class="layui-input">
            </div>
<#--            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" id="showCarNature" disabled
                       autocomplete="off" class="layui-input">
            </div>-->
            <label class="layui-form-label">出场方式:</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="outTypeDesc" disabled
                       autocomplete="off" class="layui-input">
            </div>

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 120px;">代缴:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="coupon" disabled
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">进场通道:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="inPortName" disabled
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">进场时间:</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="inTime" disabled
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 120px;">预缴:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="beforePay" disabled
                       autocomplete="off" class="layui-input">
            </div>

            <label class="layui-form-label">出场通道:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="outPortName" disabled
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">出场时间:</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="outTime" disabled
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 120px;">聚合:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="qpassPay" disabled
                       autocomplete="off" class="layui-input">
            </div>

            <label class="layui-form-label">停车时长:</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="stopTime" disabled
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
<#--            <label class="layui-form-label" style="width: 120px;">进场车牌修改记录:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="updatedincarplate" disabled
                       autocomplete="off" class="layui-input">
            </div>

            <label class="layui-form-label" style="width: 120px;">出场车牌修改记录:</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="updatedoutcarplate" disabled
                       autocomplete="off" class="layui-input">
            </div>-->
            <label class="layui-form-label" style="width: 120px;">记录说明:</label>
            <div class="layui-input-inline" style="width: 600px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="remarks" disabled
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

<#--    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 120px;">cloud订单号:</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="cloudOrderId" disabled
                       autocomplete="off" class="layui-input">
            </div>

            <label class="layui-form-label" style="width: 120px;">订单时间:</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="settleDate" disabled
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>-->

    <#--<div class="layui-form-item">
        <div class="layui-inline" >
           &lt;#&ndash; <label class="layui-form-label" style="width: 150px">进场车辆图片</label>&ndash;&gt;
            <div class="layui-input-inline" style="width: 384px;" >
                <img id="inPicPath" style="width: 384px;height: 300px;repeat;background-size:contain">
            </div>
            <div class="layui-input-inline" >
                <img id="outPicPath" style="width: 384px;height: 300px;repeat;background-size:contain">
            </div>
        </div>
    </div>-->

    <fieldset class="layui-elem-field layui-field-title">
        <legend>出入场车辆图片</legend>
        <div class="layui-input-inline">
            <img id="inPicPath" onerror="this.src='../img/error.png'"
                 style="width: 384px;height: 300px;margin-left: 10px;repeat;background-size:contain">
        </div>
        <div class="layui-input-inline">
            <img id="outPicPath" onerror="this.src='../img/error.png'"
                 style="width: 384px;height: 300px;margin-left: 10px;repeat;background-size:contain">
        </div>
        </div>
    </fieldset>

</form>
<#--显示优惠详情弹出层-->
<div id="showBusineCouponDetailDiv" style="display: none">
    <table id="busineCouponDetail"></table>
</div>
<form class="layui-form " style="display: none" id="paymentForm" lay-filter="paymentForm">
<#--    <div class="layui-form-item" style="margin-top: 10px">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">车牌</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="inCarPlate" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">cloud订单号</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="orderId" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label" style="width: 100px;">支付方式</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="paymentMethod" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">收单渠道</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="acquiringChannel" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">支付渠道</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="paymentChannel" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">支付流水号</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="paymentOrderId" autocomplete="off" class="layui-input">
            </div>-->
<#--            <button class="layui-btn" style="margin-left: 60px" lay-submit lay-filter="selectPayment">查询</button>-->
<#--            <label class="layui-form-label" style="width: 100px;">进场时间</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="inTime" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">出场时间</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="outTime" autocomplete="off" class="layui-input">
            </div>-->
        </div>
    </div>
    <table id="paymentDetail"></table>
</form>
<#--<div id="showPaymentDetailDiv" style="display: none">

</div>-->
</html>