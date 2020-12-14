<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>商户减免管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script src="../js/jQuery1.9.1/jquery.min.js"></script>
    <script src="../js/jQuery1.9.1/qrcode.js"></script>
    <script type="text/javascript" src="..\js\echarts\echarts.js"></script>
    <script type="text/javascript">
        var dates=[];    //日期数组（实际用来盛放X轴坐标值）
        var value=[];    //支出数组（实际用来盛放Y坐标值）
        var option = {
            title : {
                subtext: '商家消费年支出',
                x:'center',
                subtextStyle: {
                    "fontSize": 14,
                    "fontWeight": 'bold',
                    "color":'#00C6EA'
                }
            },
            color: ['#FF1231'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                right: 10,
                top: 20,
                bottom: 20,
                data:[ '支出']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    //fontSize : 10px
                    data : []
                    /*axisTick: {
                        alignWithLabel: true
                    }*/
                }
            ],
            yAxis : [
                {
                    type : 'value'
                    //fontSize : 10px
                }
            ],
            series : [
                {
                    name:'直接访问',
                    type:'bar',
                    barWidth: '60%',
                    label: {
                        normal: {
                            show: true,
                            position: 'inside'
                        }
                    },
                    data:[]
                }
            ]
        };
        //console.log("Init");
    </script>
    <script type="text/javascript">
        var tableIns;
        var form;
        var couponType=0;
        var inTime='';
        var fixedAmount=0.0;
        var discountAmount=0.0;
        var myChart;
        layui.use(['table','form','laydate'], function(){
            tableIns = layui.table;
            var laydate = layui.laydate;
            laydate.render({
                elem: '#validTime'
                ,type: 'date'
            });
             form=layui.form;
            /*tableIns=table.render({
                elem: '.busineTicketTableShow'
                ,height: 300
                ,url: '../electronicBusineTicket/getBusineTicket' //数据接口
                ,method:'post'
                /!*,toolbar: true
                ,toolbar: '#toolbarBlack'*!/
                //,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                    /!* {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}*!/
                    /!*,{field: 'parkId', title: '停车场', width:120,templet:'#parkIdTemp'}*!/
                    {type:'radio'}
                    ,{type: 'numbers', title: '序号', width:50}
                    ,{field: 'ticketName', title: '电子卷名称', width:130}
                    ,{field: 'permissionDate', title: '使用时间限制',width:120,templet:'#permissionDateTemp'}
                    ,{field: 'expiryDate', title: '到期时间', width:120}
                    ,{field: 'ticketNum', title: '剩余数量', width:100}
                ]]
            });*/
            form.verify({
                checkCouponPay :function (value, item) {
                    if(discountAmount<value){
                        return '减免金额超过该商户的单笔最大消费金额';
                    }
                }
/*                username: function (value, item) { //value：表单的值、item：表单的DOM对象
                    if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                        return '用户名不能有特殊字符';
                    }
                    if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                        return '用户名首尾不能出现下划线\'_\'';
                    }
                    if (/^\d+\d+\d$/.test(value)) {
                        return '用户名不能全为数字';
                    }
                },
                endDate: function (value, item) {
                    if ($("#correctFormBeginData").val() > value) {
                        return '开始日期应小于截止日期，请重新填写';
                    }
                },
                carPlateCheck: function (value, item) {
                    var patt = /^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[A-HJ-K])|([A-HJ-K]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})$/;
                    if (value.length > 0) {
                        if (patt.test(value) != true) {
                            return '请输入合法车牌号';
                        }
                    } else {
                        return '请输入车牌号';
                    }
                },
                parkCodeCheck: function (value, item) {
                    var patt = /^[a-zA-Z,0-9-]{1,16}$/;
                    if (value.length > 0) {
                        if (patt.test(value) != true) {
                            return '请输入合法车位编号';
                        }
                    } else {
                        return '请输入车位编号';
                    }
                },
                pass: [
                    /^[\S]{6,12}$/
                    , '密码必须6到12位，且不能出现空格'
                ]*/
            });
            //监听填写输入二维码减免金额/时间
            form.on('submit(inputQrcode)', function(data) {

                var couponPay=data.field.couponPay;

                if(couponPay!=""){
                    couponHandle.inputQrcode();
                }
                else
                {
                    if(couponPay==""){
                        if($('#tipUnit').text()=="(元)"){
                            layer.msg("减免金额不能为空");  //动态替换。。
                        }else{
                            layer.msg("减免时间不能为空");
                        }
                    }
                }

                return false;
            });

            //监听生成二维码
            form.on('submit(createQrcode)', function(data) {
                var couponPay=data.field.couponPay;
                var qrcodeName=data.field.qrcodeName;
                var triesLimit=data.field.triesLimit;
                data.field.couponType=couponType;
                if(couponPay!="" && qrcodeName!="" && triesLimit!=""){
                    couponHandle.createQrcode(data.field,"qrcode");
                }
                else
                {
                    if (qrcodeName==""){
                        layer.msg("二维码名称不能为空");
                    }else if(triesLimit==""){
                        layer.msg("限制次数不能为0");
                    }else{
                        layer.msg("减免数量不能为0");
                    }

                }

                return false;
            });
            //监听电子券生成二维码
            form.on('submit(createEleQrcode)', function(data) {
                var qrcodeName=data.field.qrcodeName;
                var triesLimit=data.field.triesLimit;
                var checkStatus = tableIns.checkStatus('eleBusineTicketTable'),
                    tableData = checkStatus.data;
                //var jsonTable = JSON.stringify(tableData);
                //console.info("table:"+jsonTable);
                //console.info("table:"+tableData[0].id);
                data.field.couponType=couponType;
                data.field.ticketId=tableData[0].id;
                data.field.couponPay = tableData[0].ticketValue;
                var couponPay=data.field.couponPay;
                if(couponPay!="" && qrcodeName!="" && triesLimit!=""){
                    couponHandle.createQrcode(data.field,"eleQrcode");
                }
                else
                {
                    if (qrcodeName==""){
                        layer.msg("二维码名称不能为空");
                    }else if(triesLimit==""){
                        layer.msg("限制次数不能为0");
                    }
                }

                return false;
            });
            form.on('submit(updateEle)', function(data){
                var checkStatus = tableIns.checkStatus('busineTicketTable'),
                    tableData = checkStatus.data;
                /*var jsonTable = JSON.stringify(tableData);
                console.info("table:"+jsonTable);
                console.info("data:"+data.field);*/
                var result=data.field;
                result.ticketId=tableData[0].id;
                result.couponType=3;
                result.couponPay=tableData[0].ticketValue;
                couponHandle.transferBusineCoupon(result);
                return false;
            });
            form.on('submit(blackUpdate)', function(data){
                data.field.couponType=couponType;
                //console.info("data:"+data.field);
                couponHandle.transferBusineCoupon(data.field);
                return false;
            });

            //监听查询入场时间
/*            form.on('submit(couponSelect)', function(data) {
                var carPlate=data.field.carPlate;
                if(carPlate!=""){
                    carplateHandle.selectCarIntime();
                }
                else
                {
                    layer.msg("车牌不能为空");
                }

                return false;
            });*/
            //监听优惠规则下拉选择框
            form.on('select(carPlateSearch)', function(data){
                /*console.log(data.elem); //得到select原始DOM对象
                console.log(data.value); //得到被选中的值
                console.log(data.othis); //得到美化后的DOM对象*/
                $("#carPlate").val(data.value);         //对车牌输入栏input赋值
                $("#carPlateSearchDiv").hide();         //隐藏下拉列表
            });
            form.on('select(carPlateSearchEle)', function(data){
                /*console.log(data.elem); //得到select原始DOM对象
                console.log(data.value); //得到被选中的值
                console.log(data.othis); //得到美化后的DOM对象*/
                $("#carPlateEle").val(data.value);         //对车牌输入栏input赋值
                $("#carPlateSearchEleDiv").hide();         //隐藏下拉列表
            });

        });
        $(document).ready(function () {     //DOM完成加载之后立即加载，并且在页面内容加载之前
            couponHandle.selectCouponType();
            couponHandle.selectSumPay();
            $("#carPlate").bind('input propertychange',function () {  //绑定监听事件 车牌输入栏input 调用模糊搜索方法
                carplateHandle.carplateSelectLike();
            });
            $("#carPlateEle").bind('input propertychange',function () {  //绑定监听事件 车牌输入栏input 调用模糊搜索方法
                carplateHandle.carplateSelectLike();
            });
            $("input[name='triesLimit']").bind('input propertychange',function () {

                $("input[name='useLimit']").val($(this).val().replace(/[^0-9.]/g,''));
            });
            myChart = echarts.init(document.getElementById("barChartYear"));
            couponHandle.couponYear();
        });
        //减免窗口弹出方法
        var couponHandle={

            //查询消费累计数据
            selectSumPay:function(){
                $.ajax({
                    type: 'POST',
                    url: "../Merchant/selectSumPay",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result.result);
                        if (result.code==0) {
                            //console.log(result.result.couponBalance);
                            $("#couponNeedPay").html(result.result.couponNeedPay);
                            $("#couponSumPay").html(result.result.couponSumPay);
                            $("#couponBalance").html(result.result.couponBalance);
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
            },

            //生成二维码图片方法

            createQrcodeDetail:function(url,domId){
                    var qrcode = new QRCode(domId, {
                        text: url,
                        width: 128,
                        height: 128,
                        colorDark : '#000000',
                        colorLight : '#ffffff',
                        correctLevel : QRCode.CorrectLevel.H
                    });
                },

            couponMonth:function(){
              /*  $("#barChartYear").hide();
                $("#barChartMonth").show();*/
                $.ajax({
                    type: 'POST',
                    url: "../Merchant/indexMonth",  //按日查询支出 MerchantController
                    dataType: "json",
                    data:{},
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success : function(result) {
                        //请求成功时执行该函数内容，result即为服务器返回的json对象
                        //console.log(result.result);

                        if (result.code==0) {

                            for(var i=0;i<result.result.length;i++){
                                dates.push((result.result[i].date));    //挨个取出日期并填入日期数组
                                value.push(result.result[i].value);    //挨个取出支出金额并填入支出数组
                            }
                            //console.log(dates);
                            //console.log(value);

                            //option.hideLoading();    //隐藏加载动画
                            //console.log(option.xAxis);
                            option.title.subtext= '商家消费月支出';
                            option.xAxis[0].data=dates;

                            //console.log(option.xAxis[0].data);
                            option.series=[{
                                type: 'bar',
                                // 根据名字对应到相应的系列
                                name: '支出',
                                data: value
                            }];

                            if (option && typeof option === "object") {
                                myChart.hideLoading();    //隐藏加载动画
                                myChart.setOption(option, true);
                                dates=[];
                                value=[];
                            }
                        }

                    },
                    error : function(errorMsg) {
                        //请求失败时执行该函数
                        alert("图表请求数据失败!");
                        myChart.hideLoading();
                    }
                })
            },

            couponYear:function(){
                //console.log("func");
/*                $("#barChartYear").show();
                $("#barChartMonth").hide();*/
                $.ajax({
                type: 'POST',
                url: "../Merchant/indexYear",  //按月查询支出 MerchantController
                dataType: "json",
                data:{},
                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                success : function(result) {
                    //请求成功时执行该函数内容，result即为服务器返回的json对象
                    //console.log(result.result);

                    if (result.code==0) {
                        // var myChart = echarts.init(document.getElementById("barChartYear"));
                        for(var i=0;i<result.result.length;i++){
                            dates.push((result.result[i].date));    //挨个取出日期并填入日期数组
                            value.push(result.result[i].value);    //挨个取出支出金额并填入支出数组
                        }
                        //console.log(dates);
                        //console.log(value);

                        //option.hideLoading();    //隐藏加载动画
                        //console.log(option.xAxis);
                        option.title.subtext= '商家消费年支出';
                        option.xAxis[0].data=dates;

                        //console.log(option.xAxis[0].data);
                        option.series=[{
                            type: 'bar',
                            // 根据名字对应到相应的系列
                            name: '支出',
                            data: value
                        }];

                        if (option && typeof option === "object") {
                            myChart.hideLoading();    //隐藏加载动画
                            myChart.setOption(option, true);
                             dates=[];
                             value=[];
                        }
                    }

                },
                error : function(errorMsg) {
                    //请求失败时执行该函数
                    alert("图表请求数据失败!");
                    myChart.hideLoading();
                }
            })
            },

            get:function () {
                //console.log(couponType);
                $('#couponType').text(couponType);
                switch(couponType)
                {
                    case 0:
                        //$('#butCouponType').text("按限定金额减免");
                        $('#tipMethod').text("限定金额减免");
                        $('#tipAmount').text("*减免金额:");
                        $('#tipUnit').text("(元)");
                        couponHandle.layerOpenCouponForm();
                        break;
                    case 1:
                        //$('#butCouponType').text("按时间减免");
                        $('#tipMethod').text("按时间减免");
                        $('#tipAmount').text("*减免时间:");
                        $('#tipUnit').text("(分钟)");
                        couponHandle.layerOpenCouponForm();
                        break;
                    case 2:
                        //$('#butCouponType').text("固定金额减免");
                        $('#tipMethod').text("固定金额减免");
                        $('#tipAmount').text("*减免金额:");
                        $('#tipUnit').text("(元)");
                        couponHandle.layerOpenCouponForm();
                        $('#couponPay').val(fixedAmount);
                        $('#couponPay').attr('disabled',true);
                        $('#couponPay').css("background","#CCCCCC");
                        break;
                    case 3:
                        //电子券
                        couponHandle.layerOpenCouponEleForm();
                        break;
                    default:
                        break;
                };
            },
            layerOpenCouponForm:function(){
                couponHandle.clean();
                layer.open({
                    title: '优惠减免'
                    , type: 1
                    ,area:['650px','500px']
                    ,content: $('#couponForm')
                });
            },
            layerOpenCouponEleForm:function(){
                couponHandle.cleanEle();
                couponHandle.showEleBusineTable("busineTicketTable");
                layer.open({
                    title: '电子券优惠减免'
                    , type: 1
                    ,area:['750px','700px']
                    ,content: $('#couponEleForm')
                });
            },
            /*qrcode:function(){*/

            create:function () {
                $('#couponType').text(couponType);
                switch(couponType)
                {
                    case 0:
                        //$('#butCouponType').text("按限定金额减免");
                        $('#inputTipMethod').text("限定金额减免");
                        $('#inputTipAmount').text("减免金额");
                        $('#inputTipUnit').text("(元)");
                        couponHandle.layerOpenQrcode();
                        break;
                    case 1:
                        //$('#butCouponType').text("按时间减免");
                        $('#inputTipMethod').text("按时间减免");
                        $('#inputTipAmount').text("减免时间");
                        $('#inputTipUnit').text("(分钟)");
                        couponHandle.layerOpenQrcode();
                        break;
                    case 2:
                        //$('#butCouponType').text("固定金额减免");
                        $('#inoutTipMethod').text("固定金额减免");
                        $('#inputTipAmount').text("减免金额");
                        $('#inputTipUnit').text("(元)");
                        couponHandle.layerOpenQrcode();
                        $('#inputCouponPay').val(fixedAmount);
                        $('#inputCouponPay').attr('disabled',true);
                        $('#inputCouponPay').css("background","#CCCCCC");
                        break;
                    case 3:
                        couponHandle.layerOpenEleQrcode();
                        break;
                    default:
                        break;
                };

            },
            layerOpenQrcode:function(){
                layer.open({
                    title: '生成二维码'
                    , type: 1
                    ,area:['620px','500px']
                    ,content: $('#inputQrcodeForm')
                });
                $("#inputQrcodeForm")[0].reset();
                $("#qrcode").html('');
            },
            layerOpenEleQrcode:function(){
                couponHandle.showEleBusineTable("eleBusineTicketTable");
                layer.open({
                    title: '生成二维码'
                    , type: 1
                    ,area:['1000px','520px']
                    ,content: $('#eleQrcodeForm')
                });
                $("#eleQrcodeForm")[0].reset();
                $("#eleQrcode").html('');
            },
            showEleBusineTable:function(domId){
                tableIns.render({
                    elem: '#'+domId+''
                    ,height: 300
                    ,url: '../Merchant/getBusineTicket' //数据接口
                    ,method:'post'
                    /*,toolbar: true
                    ,toolbar: '#toolbarBlack'*/
                    //,defaultToolbar: ['filter', 'print']
                    ,loading:true
                    ,page: true //开启分页
                    ,cols: [[ //表头
                        /* {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}*/
                        /*,{field: 'parkId', title: '停车场', width:120,templet:'#parkIdTemp'}*/
                         {type:'radio'}
                        ,{type: 'numbers', title: '序号', width:50}
                        ,{field: 'ticketName', title: '电子卷名称', width:200}
                        ,{field: 'ticketType', title: '类型', width:80,templet:'#ticketTypeTemp'}
                        ,{field: 'ticketValue', title: '价值',width:120,templet:'#ticketValueTemp'}
                    ]]
                });
            },
            createQrcode:function(data,domId){
                $.ajax({
                    type: 'POST',
                    url: "../couponQrcodeRecords/createCouponQrcode",  //CouponQrcodeRecordsController 生成优惠信息二维码记录
                    dataType: "json",
                    data:data,
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if(result.code==0) {
                            //console.log(result);
                            $("#qrcode").html('');
                            couponHandle.createQrcodeDetail(result.result,domId);
                                $("#qrcode").show();
                                $("#qrcode").attr("title",""); //去掉二维码图片的悬停显示
                        }
                        if (result.code==15){
                        layer.msg(result.message);
                        $("#inputQrcodeForm")[0].reset();
                    }
                    if(result.code==16){
                    layer.msg(result.message);
                    $("#inputQrcodeForm")[0].reset();
                    }},
                    error:function (result) {
                        console.log(result);
                    }
                })
            },
            transferBusineCoupon:function(data){
                $.ajax({
                    type: 'POST',
                    url: "../Merchant/transferBusineCoupon",
                    data:data,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        console.log(result);
                        if (result.code==0) {
                            layer.msg(result.result);
                        }
                    },
                    error:function (result) {
                        console.log(result.info);
                    }
                })
            },

            selectCouponType:function(){
                $.ajax({
                    type: 'POST',
                    url: "../Merchant/getTBusine",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if (result.code==0) {
                            fixedAmount = result.result.fixedAmount;
                             couponType = result.result.discountType;
                            discountAmount = result.result.discountAmount;
                            switch(couponType)
                            {
                                case 0:
                                    $('#butCouponType').text("按限定金额减免");
                                    break;
                                case 1:
                                    $('#butCouponType').text("按时间减免");
                                    break;
                                case 2:
                                    $('#butCouponType').text("固定金额减免");
                                    break;
                                case 3:
                                    $('#butCouponType').text("电子券减免");
                                    break;
                                default:
                                    break;
                            }
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
            },
            clean:function(){
                $("#couponForm")[0].reset();
            },
            cleanEle:function () {
                $("#couponEleForm")[0].reset();
            },
            //离开input输入栏时检测是否含有数字和小数点以外的字符
            check:function (e) {
            var re = /^\d+(?=\.{0,1}\d+$|$)/
            if (e.value != "") {
                if (!re.test(e.value)) {
                    alert("请输入正确的数字");
                    e.value = "";
                    e.focus();
                }
            }
        }
        };

        var carplateHandle={
            selectCarIntime:function(){
                var carPlate;
                if($("#carPlate").val().length>0){
                    carPlate=$("#carPlate").val();
                }else if($("#carPlateEle").val().length>0){
                    carPlate=$("#carPlateEle").val();
                }else{
                    layer.msg('车牌号不能为空');
                }
                $.ajax({
                    type: 'POST',
                    url: "../Merchant/getCarIntime",
                    data:{'carPlate':carPlate},
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if (result.code==0) {
                           // couponType = result.result;
                            inTime =result.result;
                            //console.log(result);
                            $("#showInTime").val(inTime);
                            $("#showInTimeEle").val(inTime);
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                });
            },
            //车牌模糊搜索
            carplateSelectLike: function (){
                var carPlate;
                if($("#carPlate").val().length>0){
                    carPlate = $("#carPlate").val();
                }else if($("#carPlateEle").val().length>3){
                    carPlate = $("#carPlateEle").val();
                }
            $.ajax({
                dataType :'json',
                type:'post',
                url:'../Merchant/getCarPlateFuzzy',
                data:{"carPlate":carPlate},
                success : function(result) {
                    //console.log(result);
                    if(result.code == 0){
                        if(result.result!=null){
                            //console.log(result.result);
                            var carPlateList=result.result;
                            var str="";
                            if (carPlateList!=null){
                                carPlateList.forEach(function (t) {
                                    str+="<option>"+t+"</option>"
                                });
                                $("#carPlateSearch").html(str);   //下拉框赋值
                                $("#carPlateSearchDiv").show();     //下拉框显示
                                $("#carPlateSearchEle").html(str);   //下拉框赋值
                                $("#carPlateSearchEleDiv").show();     //下拉框显示
                                form.render();                      //动态加载元素时要对form进行渲染
                            }
                        }
                    }else{
                        // layer.msg(result.message);
                        layer.msg("请输入至少4位车牌号");
                    }
                },
                error:function(result){
                    layer.msg("操作失败");
                }
            });
        }
        };
    </script>
    <script type="text/html" id="ticketValueTemp">
        {{# if(d.ticketType==0){ }}
        {{ d.ticketValue+'元'  }}
        {{# }else{ }}
        {{ d.ticketValue+'分钟' }}
        {{# } }}
    </script>
    <script type="text/html" id="ticketTypeTemp">
        {{# if(d.ticketType==0){ }}
        {{ '金额'  }}
        {{# }else{ }}
        {{ '时间' }}
        {{# } }}
    </script>

</head>
<body>
<blockquote class="layui-elem-quote">商户管理首页 </blockquote>
<div class="layui-btn-container" style="float: left">
    <button class="layui-btn layui-btn-sm" id="butCouponType" onclick="couponHandle.get()" style="margin-left: 30px"></button>
    <button class="layui-btn layui-btn-sm" id="butCreateCouponQrcode" onclick="couponHandle.create()">生成二维码</button>
    <hr class="layui-bg-red">
    <label class="layui-form-label" style="width: 100px;font-size:10px;">消费支出列表</label>
    <button class="layui-btn layui-btn-sm" id="butCouponDay" style="margin-left: 500px" onclick="couponHandle.couponMonth()">按日</button>
    <button class="layui-btn layui-btn-sm" id="butCouponMonth" onclick="couponHandle.couponYear()">按月</button>
    <div id="barChartYear" style="height: 400px;width: 800px;"></div>
</div>
<div  style="margin-left: 1000px;">
    <div style="height: 100px;width: 200px;background-color: #FFB951;text-align: center;margin-bottom: 20px;line-height: 50px">
        <strong class="f-ib" id="sumRechargeText" >累计充值金额</strong>
        <div class="mrtb5" style="border-top: 1px solid #333;"></div>
        <span id="couponNeedPay" style="font-size: 30px;color: #0C0C0C"></span>
    </div>
    <div style="height: 100px;width: 200px;background-color: #FFB951;text-align: center;margin-bottom: 20px;line-height: 50px">
        <strong id="sumPayText">累计消费金额</strong>
        <div class="mrtb5" style="border-top: 1px solid #333;"></div>
        <span id="couponSumPay" style="font-size: 30px;color: #0C0C0C;"></span>
    </div>
    <div style="height: 100px;width: 200px;background-color: #FFB951;text-align: center;line-height: 50px">
        <strong id="balanceText">账户余额</strong>
        <div class="mrtb5" style="border-top: 1px solid #333;"></div>
        <span id="couponBalance" style="font-size: 30px;color: #0C0C0C;"></span>
    </div>
</div>

<#--<div id="barChartMonth" style="height: 400px;width: 800px;display: none"></div>-->

</body>

<#--电子券下发窗口-->
<form class="layui-form" id="couponEleForm" lay-filter="couponEleForm" style="display: none">
    <div class="layui-form-item">
        <label class="layui-form-label">车牌号:</label>
        <div class="layui-input-inline" >
            <input type="text" class="layui-input" id="carPlateEle" name="carPlate" style="width:150px;" lay-verify="required">
        </div>
        <div class="layui-input-inline"  style="margin-left: -50px">
            <label class="layui-form-label" style="width: 120px">（可不输入汉字）</label>
        </div>
<#--        <button class="layui-btn" lay-submit lay-filter="couponSelect">查询</button>-->
        <button class="layui-btn" type="button" onclick="carplateHandle.selectCarIntime()">查询</button>
    </div>
    <div class="layui-form-item" id="carPlateSearchEleDiv" style="display: none">
        <label class="layui-form-label">历史记录:</label>
        <div class="layui-input-inline" >
            <select id="carPlateSearchEle"  lay-search="" lay-filter="carPlateSearchEle" style="width: 150px"></select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">*入场时间:</label>
        <input type="text" class="layui-input"  id="showInTimeEle" name="inTime" style="width:150px;border: 0px" readonly="readonly" lay-verify="required">
    </div>
    <table id="busineTicketTable" lay-filter="busineTicketTable"></table>
    <div class="layui-form-item">
        <label class="layui-form-label">下发张数:</label>
        <div class="layui-input-inline">
            <input type="text" name="couponNum"  autocomplete="off" class="layui-input" value="1">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注:</label>
        <div class="layui-input-inline">
            <input type="text" name="remark"  autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"></label>
        <div class="layui-input-inline" style="width: 90px">
            <button class="layui-btn" lay-submit lay-filter="updateEle">确定</button>
        </div>
    </div>
</form>
<#--非电子卷下发窗口-->
<form class="layui-form" id="couponForm" style="display: none" lay-filter="couponForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">couponType</label>
        <div class="layui-input-block">
            <input type="text" name="couponType" id="couponType" placeholder="couponType" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">车牌号:</label>
        <div class="layui-input-inline" >
<#--            车牌填写输入栏-->
            <input type="text" class="layui-input" id="carPlate" name="carPlate" style="width:150px;" lay-verify="required">
        </div>
        <div class="layui-input-inline"  style="margin-left: -50px">
        <label class="layui-form-label" style="width: 120px">（可不输入汉字）</label>
        </div>
<#--        <button class="layui-btn" lay-submit lay-filter="couponSelect">查询</button>-->
        <button class="layui-btn" type="button" onclick="carplateHandle.selectCarIntime()">查询</button>
    </div>
    <#-- 下拉列表选择-->
    <div class="layui-form-item" id="carPlateSearchDiv" style="display: none">
            <label class="layui-form-label">历史记录:</label>
            <div class="layui-input-inline" >
                <select id="carPlateSearch"  lay-search="" lay-filter="carPlateSearch" style="width: 150px"></select>
            </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">*入场时间:</label>
        <input type="text" class="layui-input"  id="showInTime" name="inTime" style="width:150px;border: 0px" readonly="readonly" lay-verify="required">
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">减免方法:</label>
        <label class="layui-form-label" id="tipMethod"  style="width:120px;text-align: left;padding-left: unset" ></label>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label" id="tipAmount"></label>
        <div class="layui-input-inline">
        <input type="text" name="couponPay" id="couponPay" autocomplete="off" class="layui-input" onblur="couponHandle.check(this)" lay-verify="required|checkCouponPay" onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')">
        </div>
        <label class="layui-form-label" id="tipUnit" style="text-align: left"></label>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">备注:</label>
        <div class="layui-input-inline">
        <input type="text" name="remark"  autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label"></label>
        <div class="layui-input-inline" style="width: 90px">
        <button class="layui-btn" lay-submit lay-filter="blackUpdate">确定</button>
        </div>
<#--        <div class="layui-input-inline">
        <button class="layui-btn"  lay-submit lay-filter="inputQrcode">生成二维码</button>
        </div>-->
    </div>
</form>
<#--非电子券二维码-->
<form class="layui-form" id="inputQrcodeForm" style="display: none" lay-filter="inputQrcodeForm">
    <div style="float: left">
        <div class="layui-form-item">
            <label class="layui-form-label" id="inputTipAmount" style="width: 100px"></label>
            <div class="layui-input-inline">
                <input type="text" name="couponPay" id="inputCouponPay" autocomplete="off" class="layui-input" onblur="couponHandle.check(this)"  onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')">
            </div>
            <label class="layui-form-label" id="inputTipUnit" style="text-align: left;width: 40px"></label>
        </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">二维码名称</label>
        <div class="layui-input-inline">
            <input type="text" id="qrcodeName" name="qrcodeName" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">限制次数</label>
        <div class="layui-input-inline">
            <input type="text" id="triesLimit" onblur="couponHandle.check(this)"  onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')" name="triesLimit" autocomplete="off" class="layui-input" >
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">剩余次数</label>
        <div class="layui-input-inline">
            <input type="text" id="useLimit" name="useLimit" disabled="disabled" autocomplete="off" class="layui-input">
        </div>
    </div>
        <label class="layui-form-label" style="width: 100px">有效时间</label>
        <div class="layui-input-inline"><input type="text" name="validTime" id="validTime" placeholder="yyyy-MM-dd" readonly="" autocomplete="off" class="layui-input">

        </div>
    <div class="layui-form-item">
        <button class="layui-btn" lay-submit lay-filter="createQrcode" style="margin-left:50px;margin-top:10px">生成二维码</button>
    </div>
    </div>
    <div id="qrcode" style="margin-top:10px;float: right;margin-right: 50px;"></div>
</form>
<#--电子券二维码-->
<form class="layui-form" id="eleQrcodeForm" style="display: none" lay-filter="eleQrcodeForm">
    <div style="float: left">
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">二维码名称</label>
            <div class="layui-input-inline">
                <input type="text" id="qrcodeNameEle" name="qrcodeName" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">限制次数</label>
            <div class="layui-input-inline">
                <input type="text" id="triesLimitEle" onblur="couponHandle.check(this)"  onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')" name="triesLimit" autocomplete="off" class="layui-input" >
            </div>
        </div>
        <table id="eleBusineTicketTable"  lay-filter="eleBusineTicketTable"></table>
        <div class="layui-form-item">
            <button class="layui-btn" lay-submit lay-filter="createEleQrcode" style="margin-left:50px;margin-top:10px">生成二维码</button>
        </div>
    </div>
    <div id="eleQrcode" style="margin-top:10px;float: right;margin-right: 50px;"></div>
</form>
</html>