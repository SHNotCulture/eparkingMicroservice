<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>登记车管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="../js/zTree/zTreeStyle.css" type="text/css">
    <script src="../js/layui/layui.js"></script>
    <script src="../js/common.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script src="../js/zTree/jquery.ztree.all-3.5.js"></script>
    <style type="text/css">
        .demo1 {
            width: 150px;
            height: 150px;
            line-height: 150px;
            text-align: center;
            color: #009688;
            border: 3px solid #009688;
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
        var tableIns,
            tableParkCode,
            tablecarplate,
            tableParkCar;
        var jsonCarPlate = new Array();
        var jsonParkCode = new Array();
        var portTreeList = new Array();
        var portIdList = "";
        var zNodes, setting, index;
        var tParkCarId, endDateOld;
        var parkList, statusList, expireSoonList, portList;
        var payRuleList=${tCarPayRuleResultList};
        var parkingSpaceList = [{id: 2, name: '子母车位'}, {id: 1, name: '机械车位'}, {id: 3, name: '普通车位'}];
        var carNatureList = [{id: 1, name: '月租车'}, {id: 2, name: '车场充值车'}, {id: 3, name: '按临停缴费车'}];
        var PortZtree = {
            getAllZtree: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../BaseDic/getPortNameListForZtree",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log("zNodes",result.data);
                        if (result.code == 0) {
                            zNodes = result.data;
                            PortZtree.createZtree();
                        }
                    },
                    error: function (result) {
                        var str = "<script type=\"text/javascript\">";
                        var beginNum = result.responseText.indexOf(str) + str.length;
                        var endNum = result.responseText.length - 9;
                        var action = result.responseText.substring(beginNum, endNum);
                        eval(action);
                    }
                })
            },
            getZtreeByParkId: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../BaseDic/getPortNameListForZtreeByParkId",
                    data: {'parkId': object},
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log("zNodes", result.data);
                        if (result.code == 0) {
                            zNodes = result.data;
                            PortZtree.createZtree();
                        }
                    },
                    error: function (result) {
                        var str = "<script type=\"text/javascript\">";
                        var beginNum = result.responseText.indexOf(str) + str.length;
                        var endNum = result.responseText.length - 9;
                        var action = result.responseText.substring(beginNum, endNum);
                        eval(action);
                    }
                })
            },
            resetZtree: function (portId) {
                if (portId != null && portId != "") {
                    var powerId = portId.split(',');
                    zNodes.forEach(function (t) {
                        var i = powerId.indexOf(t.id.toString());
                        if (i >= 0) {
                            t.checked = true;
                        } else {
                            t.checked = false;
                        }
                    });
                } else {
                    zNodes.forEach(function (t) {
                        t.checked = false;
                    });
                }
                PortZtree.createZtree();
            },
            createZtree: function () {
                setting = {
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        onCheck: PortZtree.onCheck
                    }
                };
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                $.fn.zTree.init($("#monthTreeDemo"), setting, zNodes);
                $.fn.zTree.init($("#portTreeExcel"), setting, zNodes);
            },
            onCheck: function (e, treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    mTree = $.fn.zTree.getZTreeObj("monthTreeDemo"),
                    eTree = $.fn.zTree.getZTreeObj("portTreeExcel"),
                    checkCount = zTree.getCheckedNodes(true),
                    mCheckCount = mTree.getCheckedNodes(true),
                    eCheckCount = eTree.getCheckedNodes(true);
                var portIds = "",
                    mPortIds = "",
                    ePortIds = "";
                checkCount.forEach(function (index, val) {
                    if (val == 0) {
                        portIds = index.id;
                    } else {
                        portIds = portIds + "," + index.id;
                    }
                });

                mCheckCount.forEach(function (index, val) {
                    if (val == 0) {
                        mPortIds = index.id;
                    } else {
                        mPortIds = mPortIds + "," + index.id;
                    }
                });
                eCheckCount.forEach(function (index, val) {
                    if (val == 0) {
                        ePortIds = index.id;
                    } else {
                        ePortIds = ePortIds + "," + index.id;
                    }
                });
                $("#portids").val(portIds); //reset
                $("#mportids").val(mPortIds);//edit
                $("#portIdExcel").val(ePortIds); //excelImport
            }
        };

        layui.use(['table', 'form', 'layer', 'laydate', 'tree', 'upload'], function () {
            var table = layui.table;
            tablecarplate = layui.table;
            var form = layui.form;
            var layer = layui.layer;
            var laydate = layui.laydate;
            var upload = layui.upload;
            /*BusineForm.getParkList();
            BusineForm.getStatus();
            BusineForm.getbusineType();*/
            common.getParkList(form);
            // common.getParkingSpace();
            common.getstatusList();
            Monthlyhandle.getCarPayRule(form);
            //导入执行实例
            upload.render({
                elem: '#test10' //绑定元素
                , url: '../Base/importMonthlyCar' //上传接口
                , accept: "file"
                , exts: 'xls|xlsx|xlsm|xlt|xltx|xltm'
                , auto: false
                , data: {
                    parkId: function () {
                        return $('#parkIdExcel').val();
                    },
                    payRule: function () {
                        return $('#payRuleExcel').val();
                    },
                    portId: function () {
                        return $('#portIdExcel').val();
                    }
                }
                , bindAction: '#excelFormSave'
                , done: function (res) {
                    //上传完毕回调
                    if (res.code == 0) {
                        layer.closeAll();
                        layer.msg(res.result);
                        Monthlyhandle.select();
                    }
                }
                , error: function () {
                    //请求异常回调
                    layer.msg(result.message);
                }
            });

            //第一个实例
            laydate.render({
                elem: '#endDateSelectStart'
                , type: 'date'
            });
            laydate.render({
                elem: '#endDateSelectEnd'
                , type: 'date'
            });
            laydate.render({
                elem: '#endDate'
                , type: 'date'
            });
            laydate.render({
                elem: '#paymentFormEndDate'
                , type: 'date'
            });
            laydate.render({
                elem: '#endDatenew'
                , type: 'date'
            });
            laydate.render({
                elem: '#correctFormBeginData'
                , type: 'date'
            });
            laydate.render({
                elem: '#correctFormEndData'
                , type: 'date'
            });
            tableIns = table.render({
                elem: '#parkinoutTable'
                , height: 540
                , url: '../monthlyCar/getTParkCarFuzzybyPage' //数据接口
                , method: 'post'
                , toolbar: true
                , toolbar: '#toolBarMonthly'
                , defaultToolbar: ['filter', 'print']
                , loading: true
                , title: '登记车'
                , page: true //开启分页
                , cols: [[ //表头
                    {type: 'numbers', title: '序号', width: 50, fixed: 'left'}
                    , {field: 'carPlate', title: '车牌', align: 'center', width: 110, sort: true}
                    , {field: 'carNature', title: '登记车性质', align: 'left', width: 130, templet: '#carNatureTemp'}
                    , {field: 'parkingSpace', title: '车位性质', align: 'center', width: 90, templet: '#parkingSpaceTemp'}
                    , {field: 'payRule', title: '登记车规则', align: 'left', width: 120, sort: true, templet: '#payRuleTemp'}
                    , {field: 'beginDate', title: '开始时间', align: 'center', width: 120, sort: true}
                    , {field: 'endDate', title: '到期时间', align: 'center', width: 120, sort: true}
                    , {field: 'stopTime', title: '暂停时间', align: 'center', width: 120, sort: true}
                    , {field: 'realname', title: '联系人', align: 'center', width: 90, sort: true}
                    , {field: 'phone', title: '电话', align: 'center', width: 120, sort: true}
                    , {field: 'address', title: '住宅', align: 'center', width: 80, sort: true}
                    , {field: 'status', title: '状态', align: 'center', width: 70, templet: '#statusTemp'}
                    , {field: 'bindingNum', title: '车辆数', align: 'center', width: 80}
                    , {field: 'parkingNo', title: '车位数', align: 'center', width: 80}
                    , {field: 'parkCode', title: '车位编码', align: 'center', width: 90}
                    , {field: 'walletBanance', title: '钱包余额', align: 'center', width: 90}
                    , {fixed: 'right', title: '操作', align: 'center', width: 330, toolbar: '#MonthlyBar'}
                    // ,{field: 'remarks', title: '备注', width: 120}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(parkinoutTable)', function (obj) {
                switch (obj.event) {
                    case 'MonthlyExcel':
                        var status = $('#statusSer option:selected').val();
                        var payRule = $('#payRuleform option:selected').val();
                        var parkingSpace = $('#parkingSpaceform option:selected').val();
                        var parkCode = $('#parkCode').val();
                        var endDateSelectStart = $('#endDateSelectStart').val();
                        var endDateSelectEnd = $('#endDateSelectEnd').val();
                        var carPlatemm = $('#carPlateform').val();
                        //var parkingState=$("#parkingState").val();
                        var data = {
                            status: status,
                            payRule: payRule,
                            parkingSpace: parkingSpace,
                            parkCode: parkCode,
                            endDateSelectStart: endDateSelectStart,
                            endDateSelectEnd: endDateSelectEnd,
                            carPlate: carPlatemm
                        };
                        //console.log("data: ",data);
                        Monthlyhandle.MonthlyExcel(data);
                        break;
                    default:
                }
                ;

            });

            //监听工具条
            table.on('tool(parkinoutTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                // console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if (layEvent === 'detail') { //查看
                    //do somehing
                } else if (layEvent === 'delMonthly') { //删除
                    layer.confirm('真的删除行么', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        // console.log(data);
                        Monthlyhandle.delMonthly(data);
                        //layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if (layEvent === 'walletRecharge') {
                    walletRechargeForm.clean();
                    // console.log(data);
                    /*                    if(data.walletBanance==null){
                                            data.walletBanance=0.0;
                                        }*/
                    form.val("walletRechargeForm", data);
                    walletRechargeForm.show();
                } else if (layEvent === 'edit') { //编辑
                    // console.log(data);
                    form.val("MonthlyForm", data);
                    // tParkCarId=data.id;
                    Monthlyhandle.Updateo(1);//弹出车场更新窗口
                } else if (layEvent === 'pay') { //充值
                    //form.val("MonthlyForm", data);
                    $("#paymentForm")[0].reset();
                    data.parkCarId = data.id;
                    form.val("paymentForm", data);
                    // var temporary = Monthlyhandle.getTemporaryMoney(data.carPlate, data.parkId);
                    //console.log("temporary"+temporary.toString())
                    Monthlyhandle.payform();
                } else if (layEvent === 'correct') {//冲正
                    $("#correctForm")[0].reset();
                    form.val("correctForm", data);
                    // console.log(data);
                    endDateOld = data.endDate;
                    Monthlyhandle.correctForm();
                } else if (layEvent === 'carplate') {//车牌管理
                    tParkCarId = data.id;
                    Monthlyhandle.setCarplate();
                }
            });

            //监听钱包充值/冲正单选框
            form.on('radio(walletRechargeRadio)', function (data) {
   /*             console.log(data.elem); //得到radio原始DOM对象
                console.log(data.value); //被点击的radio的value值*/
                if (data.value == "recharge") {
                    $("#walletNeedPay").html("应收: ");
                    $("#walletActualPay").html("实收: ");
                } else {
                    $("#walletNeedPay").html("冲正: ");
                    $("#walletActualPay").html("实退: ");
                }
            });

            //监听批量导入界面车场下拉列表
            form.on('select(parkId)', function (data) {
                PortZtree.getZtreeByParkId(data.value);
                Monthlyhandle.getPayRuleByParkId(data.value, form);
            });

            form.on('select(payRule)', function (data) {
                // console.log(data.value);
                var payRuleName = $("#payRuleUpdate option:checked").text();
                // console.log(payRuleName);
                $("#payRuleStr").val(payRuleName);
            });

            form.verify({
                username: function (value, item) { //value：表单的值、item：表单的DOM对象
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
                    var myDate = new Date();
                    var time = myDate.toLocaleDateString().split('/').join('-');
                    if (endDateOld <= value) {
                        return '新截止日期应小于旧截止日期，请重新填写';
                    } else if (time > value) {
                        return '新截止日期应不小于当前日期，请重新填写';
                    }
                },
                carPlateCheck: function (value, item) {
                    if (value.length > 0) {
                        if (common.carPlateCheck(value) != true) {
                            return '请输入合法车牌号';
                        }
                    } else {
                        return '请输入车牌号';
                    }
                },
                payValueCheck: function (value, item) {
                    if (common.payValueCheck(value) != true) {
                        return '保留小数点后面两位数';
                    }
                },
                parkCodeCheck: function (value, item) {
                    if (value.length > 0) {
                        if (common.parkCodeCheck(value) != true) {
                            return '请输入合法车位编号';
                        }
                    } else {
                        return '请输入车位编号';
                    }
                },
                validatePhone: function(value, item){
                    if(value.length > 0){
                        if (common.validatePhone(value) != true) {
                            return '号码格式不正确或者位数不正确';
                        }
                    }else{
                        return '请输入联系方式';
                    }
                },
                //我们既支持上述函数式的方式，也支持下述数组的形式
                //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
                pass: [
                    /^[\S]{6,12}$/
                    , '密码必须6到12位，且不能出现空格'
                ]
            });
            //监听查询按钮
            form.on('submit(SelectParkinout)', function (data) {
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log("ss:"+data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                Monthlyhandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
            form.on('submit(SelectExpireSoonParkCar)', function (data) {
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}

                tableParkCar.reload({
                    where: { //设定异步数据接口的额外参数，任意设
                        closeType: data.field.closeType,
                        payRule: data.field.payRule
                    }
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                //Monthlyhandle.batchRecharge(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });

            form.on('submit(monthlyUpdate)', function (data) {
                /*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                layer.confirm('您确认要提交吗？', {
                    btn: ['确认','取消'] //按钮
                }, function(){
                    Monthlyhandle.save(data.field);
                }, function(){
                    layer.msg('已取消..');
                    return false;
                });
            return false;//重点二！！！！  阻止提交行为！！
/*                layer.confirm('您确认要提交吗？', function (index) {
                    if (data.field.portId != "") {
                        Monthlyhandle.save(data.field);
                        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
                    } else {
                        layer.msg("请选择通道权限", {icon: 5, time: 1500});
                    }
                });*/
            });

            form.on('submit(paymentSave)', function (data) {
                // console.log(data.field);
                Monthlyhandle.savePay(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
            // 冲正保存
            form.on('submit(correctFormSave)', function (data) {
                // console.log(data.field);
                Monthlyhandle.correctFormSave(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
            /*批量充值充值按钮*/
            form.on('submit(paymentSaveBatch)', function (data) {
                //console.log(data.field);
                Monthlyhandle.savePayBatch(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
            /*重置通道权限*/
            form.on('submit(portResetSave)', function (data) {
                //console.log(data.field);
                Monthlyhandle.saveport(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });

            form.on('submit(carPlateAdd)', function () {
                var carPlateOne = $("#carPlateOne").val();
                //车牌是否已存在验证
                // Monthlyhandle.checkCarplate({'carPlate': carPlateOne});
                var data = {parkCarId: tParkCarId, carPlate: carPlateOne};
                // console.log(data);
                monthBindingCarForm.add(data);
                return false;
            });
            form.on('submit(vouchers)', function (data) {
                vouchers();
                return false;
            });
            form.on('submit(vouchersBatch)', function (data) {
                Monthlyhandle.vouchersBatchjs(data.field);
                return false;
            });

            form.on('submit(bulkRecharge)', function (data) {
                var checkStatus = table.checkStatus('showRechargeTable')
                    , data = checkStatus.data;
                //console.log("length",data.length);
                if (data.length > 0) {
                    var jsonObj = eval(JSON.stringify(data));
                    var idlist = "";
                    jsonObj.forEach(function (value, index) {
                        if (index == jsonObj.length - 1) {
                            idlist += value.id;
                        } else {
                            idlist += value.id + ",";
                        }
                    });
                    $('#ids').val(idlist);
                    layer.open({
                        title: '批量充值'
                        , type: 1
                        , area: ['400px', '500px']
                        , content: $('#paymentBatchForm')
                    });
                } else {
                    //console.log("jin");
                    layer.msg('未选中数据', {
                        icon: 1,
                        time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                    });

                }
                return false;
            });
            /*批量重置通道权限*/
            form.on('submit(bulkResetPort)', function (data) {
                var checkStatus = table.checkStatus('showRechargeTable'),
                    data = checkStatus.data;
                //console.log(JSON.stringify(data));
                if (data.length > 0) {
                    var jsonObj = eval(JSON.stringify(data));
                    var idlist = "";
                    jsonObj.forEach(function (value, index) {
                        if (index == jsonObj.length - 1) {
                            idlist += value.id;
                        } else {
                            idlist += value.id + ",";
                        }
                    });

                    $('#idsport').val(idlist);
                    layer.open({
                        title: '批量重置通道权限'
                        , type: 1
                        , area: ['500px', '600px']
                        , content: $('#portResetForm')
                    });
                    PortZtree.resetZtree();
                } else {
                    layer.msg('未选中数据', {
                        icon: 1,
                        time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                    });
                }
                return false;
            });
            form.on('submit(ParkCodeSave)', function (data) {
                var newParkCode = data.field.ParkCode;
                var index = data.field.ParkCodeid;
                if (index == "") {
                    var a = {'carPlateid': jsonParkCode.length, 'ParkCode': newParkCode};
                    jsonParkCode.push(a);
                } else {
                    jsonParkCode[index].ParkCode = newParkCode;
                }
                tableParkCode.reload({
                    data: jsonParkCode //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                layer.close(layer.index);
                return false;
            });
            //车位添加
            form.on('submit(parkCodeAdd)', function () {
                var parkCodeOne = $("#parkCodeOne").val();
                var a = {'ParkCodeid': jsonParkCode.length, 'ParkCode': parkCodeOne};
                $("#parkCodeOne").val("");
                jsonParkCode.push(a);
                tableParkCode.reload({
                    data: jsonParkCode //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                return false;
            });
            //车位保存
            form.on('submit(parkCodeToSave)', function (data) {
                var ParkCodeList;
                jsonParkCode.forEach(function (index, val) {
                    //console.log(index,val);
                    if (val == 0) {
                        ParkCodeList = index.ParkCode;
                    } else {
                        ParkCodeList = ParkCodeList + "," + index.ParkCode;
                    }
                });
                layer.close(index);
                $("#parkCodeShow").val(ParkCodeList);
                jsonParkCode = new Array();
                return false;
            });
            form.on('submit(carPlateToSave)', function (data) {
                var carPlateList;

                jsonCarPlate.forEach(function (index, val) {
                    // console.log(index, val);
                    if (val == 0) {
                        carPlateList = index.carPlate;
                    } else {
                        carPlateList = carPlateList + "," + index.carPlate;
                    }
                });
                layer.close(index);
                // console.log(carPlateList);
                $("#carPlate").val(carPlateList);
                jsonCarPlate = new Array();
                return false;
            });
            form.on('submit(ParkCodeToSave)', function (data) {
                var ParkCodeList;
                jsonParkCode.forEach(function (index, val) {
                    //console.log(index,val);
                    if (val == 0) {
                        ParkCodeList = index.ParkCode;
                    } else {
                        ParkCodeList = ParkCodeList + "," + index.ParkCode;
                    }
                });
                layer.close(4);
                // console.log("ParkCodeList: " + ParkCodeList);
                $("#parkCode").val(ParkCodeList);
                jsonParkCode = new Array();
                return false;
            });

            form.on('submit(monthlyRechargeUpdate)', function (data) {
                Monthlyhandle.walletRecharge(data.field);
                return false;
            });

            table.on('tool(carplateTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                var monthData = {id: tParkCarId,parkId: data.parkId};
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                if (layEvent === 'del') { //删除
                    layer.confirm('真的删除行么', function (index) {
                        var rowIndex =  $(tr).attr("data-index");
                        // 当只剩最后一个绑定车时警告是否删除，是删除对应的登记车信息
                        if(rowIndex==0){
                            layer.confirm('删除最后一条车牌会删除用户', function (index) {
                                Monthlyhandle.delMonthly(monthData);
                                layer.closeAll();
                            });
                        }else{
                            monthBindingCarForm.delete(data);
                            layer.close(index);
                        }
                    });
                }
            });
            table.on('tool(carSeatTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                if (layEvent === 'del') { //删除
                    layer.confirm('真的删除行么', function (index) {
                        //obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        Monthlyhandle.deleteCarSeat(data);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                }
            });
            tableParkCode = table.render({
                elem: '#carSeatTable'
                , height: 315
                , method: 'post'
                , page: true //开启分页
                , cols: [[ //表头
                    {type: 'numbers', title: '序号', width: 80, fixed: 'left'}
                    , {field: 'ParkCode', title: '车位号', width: 120}
                    , {fixed: 'right', title: '操作', width: 200, toolbar: '#parkCodeBar'}
                ]]
            });
            tableParkCar = table.render({
                elem: '#showRechargeTable'
                , height: 469
                , method: 'post'
                // , url: '../monthlyCar/getCloseParkCarbyPage' //数据接口
                , url: '../monthlyCar/getMonthlyCar' //数据接口
                , loading: true
                , page: true //开启分页
                , cols: [[ //表头
                    //{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                    {type: 'checkbox'}
                    , {type: 'numbers', title: '序号', width: 50}
                    , {field: 'carPlate', title: '车牌', width: 160}
                    , {field: 'parkingSpace', title: '车位性质', width: 120, templet: '#parkingSpaceTemp'}
                    , {field: 'payRule', title: '登记车规则', width: 120, sort: true, templet: '#payRuleTemp'}
                    , {field: 'beginDate', title: '开始时间', width: 130, sort: true}
                    , {field: 'endDate', title: '到期时间', width: 130, sort: true}
                ]]
            });

            form.render();
        });


        //在页面加载完成后执行
        $(document).ready(function () {
            var needpay = "";
            PortZtree.getAllZtree();
            Monthlyhandle.getCarNature();
            Monthlyhandle.getParkingSpace();
            /* common.getParkList();
             common.getPortName();

             common.getExpireSoonList();*/
            Array.prototype.remove = function (dx) {
                if (isNaN(dx) || dx > this.length) {
                    return false;
                }
                for (var i = 0, n = 0; i < this.length; i++) {
                    if (this[i] != this[dx]) {
                        this[n++] = this[i]
                    }
                }
                this.length -= 1
            }

        });
        //月租车增删查改方法
        var Monthlyhandle = {
            saveport: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/Resetport",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if (result.code == 0) {
                            layer.closeAll();
                            layer.msg(result.result, {icon: 6});
                            //console.log(result.result);
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            },


            /*批量充值计算*/
            vouchersBatchjs: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/vouchersBatchjs",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if (result.code == 0) {
                            $('#needPayTotal').val(result.result.needPayTotal);
                            $('#actualPayTotal').val(result.result.needPayTotal);
                            $('#data').val(result.result.dataresult);
                            //layer.closeAll();
                            //console.log(result.result);
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            },
            batchRecharge: function (object) {
                /*var form = layer.form;
                form.val("batchRecharge", object);*/
                var status = $('#statusSer option:selected').val();
                var payRule = $('#payRuleform option:selected').val();
                var parkingSpace = $('#parkingSpaceform option:selected').val();
                var parkCode = $('#parkCode').val();
                var endDateSelectStart = $('#endDateSelectStart').val();
                var endDateSelectEnd = $('#endDateSelectEnd').val();
                var carPlatemm = $('#carPlateform').val();
                var data = {
                    status: status,
                    payRule: payRule,
                    parkingSpace: parkingSpace,
                    parkCode: parkCode,
                    endDateSelectStart: endDateSelectStart,
                    endDateSelectEnd: endDateSelectEnd,
                    carPlate: carPlatemm
                };
                tableParkCar.reload({
                    where:
                    data
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                batchRechargeForm.clean();
                layer.open({
                    title: '批量操作'
                    , type: 1
                    , area: ['900px', '600px']
                    , content: $('#batchRecharge')
                });
            },
            savePay: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/voucherSave",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log(result);
                        if (result.code == 0) {
                            layer.closeAll();
                            // console.log("charge");
                            MonthlyForm.clean();
                            tableIns.reload();
                            //console.log(result.result);
                            /*                            tableIns.reload({
                                                            where: { //设定异步数据接口的额外参数，任意设
                                                            }
                                                            , page: {
                                                                curr: 1 //重新从第 1 页开始
                                                            }
                                                        });*/
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            },
            savePayBatch: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/vouchersBatchSave",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log(result);
                        if (result.code == 0) {
                            layer.closeAll();
                            //console.log(result.result);
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            },

            Updateo: function (type) {
                if (type == 0) {//新增
                    //console.log("new")
                    $("#carPlate").attr("readonly", false);
                    MonthlyForm.clean();
                    tParkCarId = 0;
                    PortZtree.resetZtree();
                    layer.open({
                        title: '新增登记车'
                        , type: 1
                        , area: ['850px', '750px']
                        , content: $('#MonthlyForm')
                    });
                } else if (type == 1) {//編輯
                    //console.log("edit");
                    var portIds = $("#mportids").val();
                    // console.log("portIds"+portIds);
                    $("#carPlate").attr("readonly", true);
                    PortZtree.resetZtree(portIds);
                    layer.open({
                        title: '更新登记车'
                        , type: 1
                        , area: ['850px', '750px']
                        , content: $('#MonthlyForm')
                    });
                }


            },


            /*Update:function (type) {
                if(type==0){//新增
                    carPlateForm.clean();
                }
                layer.open({
                    title: '更新车牌'
                    , type: 1
                    ,area:['600px','300px']
                    ,content: $('#carPlateForm')
                });
            },*/
            delete: function (data) {
                // console.log(jsonCarPlate);
                jsonCarPlate.remove(data.carPlateid);
            },
            delMonthly: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/refund",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log(result);
                        if (result.code == 0) {
                            layer.msg(result.result);
                            tableIns.reload();
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            },
            /*            insert:function(type){
                            if(type==0){
                                MonthlyForm.clean()
                            }
                            layer.open({
                                title: '月租车管理'
                                , type: 1
                                ,area:['800px','600px']
                                ,content: $('#MonthlyForm')
                            });
                        },*/
            payform: function () {
                layer.open({
                    title: '月租充值'
                    , type: 1
                    , area: ['650px', '500px']
                    , content: $('#paymentForm')
                });
            },
            correctForm: function () {
                layer.open({
                    title: '月租冲正'
                    , type: 1
                    , area: ['400px', '500px']
                    , content: $('#correctForm')
                })
            },

            save: function (object) {
                // var form = layui.form;
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/updateMonthlyCar",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log("result.code",result.code);
                        if (result.code == 0) {
                            layer.closeAll();
                            layer.msg("保存成功");
                            // console.log("edit");
                            MonthlyForm.clean();
                            tableIns.reload();
                        } else {
                            layer.msg(result.message, {
                                icon: 1,
                                time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                            });
                            //layer.closeAll();
                            MonthlyForm.clean();
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                        layer.msg(result.message, {
                            icon: 1,
                            time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                        });
                    }
                })
            },

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
            deleteCarSeat: function (data) {
                jsonParkCode.remove(data.ParkCodeid);
                tableParkCode.reload({
                    where: jsonParkCode //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            MonthlyExcel: function (object) {
                window.location.href = "../Base/exportListMonthly?status=" + object.status + "&payRule=" + object.payRule + "&parkingSpace=" + object.parkingSpace + "&carSeat=" + object.carSeat + "&endDateSelectStart=" + object.endDateSelectStart + "&endDateSelectEnd=" + object.endDateSelectEnd + "&carPlate=" + object.carPlate;
            },
            getTemporaryMoney: function (carPlate, parkId) {
                //console.log(carPlate,parkId);
                var jsonParameters = {
                    "carPlate": carPlate,
                    "parkId": parkId
                };
                // jsonParameters.a
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/getTemporaryMoney",
                    data: jsonParameters,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if (result.code == 0) {
                            layer.msg(result.result);
                            tableIns.reload();
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            },
            walletRecharge: function (object) {
                var form = layui.form;
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/monthlyWalletRecharge",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log("result.code",result.code);
                        if (result.code == 0) {
                            layer.closeAll();
                            //console.log(result.result);
                            tableIns.reload({
                                where: { //设定异步数据接口的额外参数，任意设
                                }
                                , page: {
                                    curr: 1 //重新从第 1 页开始
                                }
                            });
                        } else {
                            layer.msg('钱包充值失败', {
                                icon: 1,
                                time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                            });
                            //layer.closeAll();
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            },
            correctFormSave: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/correct",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log(result);
                        if (result.code == 0) {
                            layer.closeAll();
                            // console.log("correct");
                            MonthlyForm.clean();
                            tableIns.reload();
                            //console.log(result.result);
                            /*                            tableIns.reload({
                                                            where: { //设定异步数据接口的额外参数，任意设
                                                            }
                                                            , page: {
                                                                curr: 1 //重新从第 1 页开始
                                                            }
                                                        });*/
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            },
            downLoad: function (object) {
                /*                var curWwwPath = window.document.location.href;
                                var pathName = window.document.location.pathname;
                                var pos = curWwwPath.indexOf(pathName);
                                // var localhostPath = curWwwPath.substring(0, pos);
                                var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);*/
                var url = "../temp/owners.xls";
                window.open(url);
            },
            /*            checkCarplate: function (object) {
                            $.ajax({
                                type: 'POST',
                                url: "../monthBinding/getMonthBindingCar",
                                data: object,
                                dataType: "json",
                                contentType: "application/x-www-form-urlencoded; charset=utf-8",
                                success: function (result) {
                                    if (result.code == 0) {
                                        var carplateList = result.result;
                                        if (carplateList.length > 0) {
                                            //车牌已存在不执行保存操作
                                            $("#carPlateOne").val("");
                                            layer.msg("该车牌已存在", {icon: 5});
                                        }else{
                                            var data = {parkCarId:tParkCarId,carPlate:object.carPlate};
                                            monthBindingCarForm.add(data);
                                        }
                                    }
                                },
                                error: function (result) {
                                    //console.log(result);
                                }
                            })
                        },*/
            setCarplate: function () {
                $("#carPlateOne").val("");
                monthBindingCarForm.showTablecarplate();
                layer.open({
                    title: '添加车牌'
                    , type: 1
                    , area: ['700px', '600px']
                    , content: $('#showCarPlate')
                });
                return false;
            },
            setParkCode: function () {
                $("#parkCodeOne").val("");
                // console.log($("#parkCodeShow").val());
                if ($("#parkCodeShow").val() != null) {
                    var jsonParkCode1 = $("#parkCodeShow").val().split(',');
                }
                // console.info(jsonParkCode1);
                if (jsonParkCode1 != "" && jsonParkCode1 != null) {
                    jsonParkCode1.forEach(function (index, val) {
                        //console.info(index);
                        var a = {'ParkCodeid': val, 'ParkCode': index};
                        jsonParkCode.push(a);
                    });
                }
                // console.info(jsonParkCode);
                tableParkCode.reload({
                    where: jsonParkCode //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                index = layer.open({
                    title: '添加车位编码'
                    , type: 1
                    , area: ['700px', '600px']
                    , content: $('#showParkCode')
                    , cancel: function (index, layero) {
                        jsonParkCode = new Array();
                        layer.close(index);
                        return false;
                    }

                });
            },
            importExcel: function (object) {
                $("#excelForm")[0].reset();
                var portIds = $("#portIdExcel").val();
                PortZtree.resetZtree(portIds);
                layer.open({
                    title: '批量导入车主'
                    , type: 1
                    , area: ['800px', '650px']
                    , content: $('#excelForm')
                });
            },
            getCarPayRule: function (form) {
/*                $.ajax({
                    type: 'POST',
                    url: "../carPayRule/getPayRule",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            console.log(result.result);
                            payRuleList = result.result;
                            var str = "<option value>请选择登记车规则</option>";
                            payRuleList.forEach(function (t) {
                                str += "<option value=" + t.id + ">" + t.ruleName + "</option>"
                            });
                            $("select[name='payRule']").html(str);
                        }
                        form.render();
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })*/
                var str = "<option value>请选择登记车规则</option>";
                payRuleList.forEach(function (t) {
                    str += "<option value=" + t.id + ">" + t.ruleName + "</option>"
                });
                $("select[name='payRule']").html(str);
                form.render();
            },
            getPayRuleByParkId: function (object, form) {
                $.ajax({
                    type: 'POST',
                    url: "../carPayRule/getPayRuleByParkId",
                    data: {'parkId': object},
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            payRuleList = result.result;
                            var str = "<option value>请选择登记车规则</option>";
                            payRuleList.forEach(function (t) {
                                str += "<option value=" + t.id + ">" + t.ruleName + "</option>"
                            });
                            $("select[id='payRuleExcel']").html(str);
                        }
                        form.render();
                    },
                    error: function (result) {
                    }
                })
            },
            getCarNature: function () {
                var str = "<option value>请选择登记车性质</option>";
                carNatureList.forEach(function (t) {
                    str += "<option value=" + t.id + ">" + t.name + "</option>"
                });
                $("select[name='carNature']").html(str);
            },
            getParkingSpace: function () {
                var str = "<option value>请选择车位性质</option>";
                parkingSpaceList.forEach(function (t) {
                    str += "<option value=" + t.id + ">" + t.name + "</option>"
                });
                $("select[name='parkingSpace']").html(str);
            },
            vouchers: function () {
                var beginDate = $("#paymentFormEndDate").val();
                var payStandard = $("#payStandard").val();
                var payRule = $("#payRule").val();
                var parkingNo = $("#parkingNo").val();
                var payCount = $("#payCount").val();
                var id = $("#paymentFormParkCarId").val();
                // console.log(payStandard+"&payRule="+payRule+"&payCount="+payCount+"&id="+id+"&beginDate="+beginDate+"&parkingNo="+parkingNo);
                if (payStandard != "" && payRule != "" && payCount != "" && payCount != 0 && beginDate != "") {
                    $.post("../monthlyCar/calRule?payStandard=" + payStandard + "&payRule=" + payRule + "&payCount=" + payCount + "&id=" + id + "&beginDate=" + beginDate + "&parkingNo=" + parkingNo,//parkingNo是指1个车位
                        function (data) {
                            // console.log(data.result);
                            if (0 == data.code) {
                                var re = eval('(' + data.result + ')');
                                //console.log(re.endDate);
                                $("#endDatenew").val(re.endDate);
                                $("#needPay").val(re.needPay);
                                $("#needPay").attr("readonly", "readonly");
                                $("#actualPay").val(re.needPay);
                            } else {
                                alert("计算失败！");
                            }
                        });
                } else {
                    alert("开始日期，缴费规则，充值方式和数量必填");
                }
            }
        };
        //车场更新表单使用方法
        var MonthlyForm = {
            clean: function () {
                $("#MonthlyForm")[0].reset();
            }

        };
        var carPlateForm = {
            clean: function () {
                $("#carPlateForm")[0].reset();
            }
        };
        var batchRechargeForm = {
            clean: function () {
                $("#batchRechargeForm")[0].reset();
            }
        };

        // 钱包充值/冲正表单使用方法
        var walletRechargeForm = {
            clean: function () {
                $("#walletRechargeForm")[0].reset();
            },
            show: function () {
                layer.open({
                    title: '钱包'
                    , type: 1
                    , area: ['600px', '500px']
                    , content: $('#walletRechargeForm')
                });
            }
        };

        var monthBindingCarForm = {
            showTablecarplate: function () {
                tablecarplate.render({
                    elem: '#carplateTable'
                    , height: 315
                    , url: '../monthBinding/getMonthBindingCarbyPage' //数据接口
                    , method: 'post'
                    , where: {parkCarId: tParkCarId}
                    , loading: true
                    , page: true //开启分页
                    , cols: [[ //表头
                        //{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                        {type: 'numbers', title: '序号', width: 80, fixed: 'left'}
                        , {field: 'carPlate', title: '车牌', width: 120}
                        //, {field: 'carType', title: '车辆类型', width: 120}
                        , {fixed: 'right', title: '操作', width: 200, toolbar: '#carPlateBar'}
                    ]]
                });
            },
            reload: function (object) {
                tablecarplate.reload('carplateTable', {
                    where: { //设定异步数据接口的额外参数，任意设
                        parkCarId: object.tParkCarId
                    },
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            add: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../monthBinding/updateMonthBindingCar",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if (result.code == 0) {
                            layer.msg(result.result, {icon: 6, time: 2000});
                            monthBindingCarForm.reload(object);
                            Monthlyhandle.select();
                            // tParkCarId=0;
                        }
                    },
                    error: function (result) {
                        console.log(result.message);
                    }
                });
                $("#carPlateOne").val("");
            },
            delete: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../monthBinding/deleteMonthBindingCar",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if (result.code == 0) {
                            layer.msg(result.result, {icon: 6});
                            monthBindingCarForm.reload(object);
                            Monthlyhandle.select();
                            // tParkCarId=0;
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            }
        };


    </script>

    <script type="text/html" id="parkIdTemp">
        {{# layui.each(parkList,function(index,item){ }}
        {{# if(item.id===d.id){ }}
        {{ item.parkName }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="payRuleTemp">
        {{# layui.each(payRuleList,function(index,item){ }}
        {{# if(item.id===d.payRule){ }}
        {{ item.ruleName }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="parkingSpaceTemp">
        {{# layui.each(parkingSpaceList,function(index,item){ }}
        {{# if(item.id===d.parkingSpace){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="statusTemp">
        {{# layui.each(statusList,function(index,item){ }}
        {{# if(item.id===d.status){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="carNatureTemp">
        {{# layui.each(carNatureList,function(index,item){ }}
        {{# if(item.id===d.carNature){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="toolBarMonthly">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="Monthlyhandle.batchRecharge()">批量操作</button>
            <#--            <button class="layui-btn layui-btn-sm"  onclick="Monthlyhandle.insert(0)">月租车办理</button>-->
            <button class="layui-btn layui-btn-sm" onclick="Monthlyhandle.Updateo(0);">登记车办理</button>
            <button class="layui-btn layui-btn-sm" lay-event="MonthlyExcel">导出登记车信息</button>
            <button class="layui-btn layui-btn-sm" onclick="Monthlyhandle.importExcel()">批量导入</button>
        </div>
    </script>
    <script type="text/html" id="MonthlyBar">
        <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="walletRecharge">钱包</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs" lay-event="carplate">车牌管理</a>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="pay">充值</a>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="correct">冲正</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delMonthly">删除</a>
    </script>
    <script type="text/html" id="carPlateBar">
        <#--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>-->
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="parkCodeBar">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <style type="text/css">
        ul {
            display: flex;
            justify-content: space-around;
            overflow: hidden;
        }

        ul li {
            margin-top: 8px;
        }

        ul li .carStyle {
            width: 200px;
            height: 75px;
            border-radius: 5px;
            overflow: hidden;
        }

        .carL {
            text-align: center;
            width: 45px;
            height: 75px;
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
            overflow: hidden;
        }

    </style>
</head>
<body>
<blockquote class="layui-elem-quote">登记车管理</blockquote>
<#--<div id="carNum">
    <ul>
        <li>
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>总车辆数</span>
                </div>
                <span class="cNum">${totalCarNo}</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>总租位数</span>
                </div>
                <span class="cNum">${parkingNo}</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>总私家位</span>
                </div>
                <span class="cNum">${privateParkingNo}</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>暂停车位</span>
                </div>
                <span class="cNum">${pauseParkingNo}</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>过期车位</span>
                </div>
                <span class="cNum">${expiredParkingNo}</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>月底到期</span>
                </div>
                <span class="cNum" >${expiredParkingNo}</span>
            </div>
        </li>
    </ul>
</div>-->
<form class="layui-form" style="margin: 10px" id="BusineForm" lay-filter="BusineForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">登记车性质</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select id="carNatureSel" name="carNature" lay-filter="carNature">
                </select>
            </div>
            <label class="layui-form-label">车位性质</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="parkingSpace" id="parkingSpaceform" lay-filter="parkingSpace">
                </select>
            </div>
            <label class="layui-form-label">车位编码</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text"
                       onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4e00-\u9fa5]/g,''))"
                       onkeyup="this.value=this.value.replace(/[\u4e00-\u9fa5]/g,'')" id="parkCode" name="parkCode" placeholder="车位编码"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">登记车规则</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="payRule" id="payRuleform" lay-filter="payRule">
                </select>
            </div>
            <label class="layui-form-label">到期时间</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="endDateSelectStart" id="endDateSelectStart" placeholder="yyyy-MM-dd"
                       autocomplete="off"
                       class="layui-input">
            </div>
            <label class="layui-form-label">至</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="endDateSelectEnd" id="endDateSelectEnd" placeholder="yyyy-MM-dd"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="status" id="statusSer" lay-filter="status">
                </select>
            </div>
            <label class="layui-form-label">车牌</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" id="carPlateform" name="carPlate" placeholder="车牌" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 200px;margin-left: 110px">
                <button class="layui-btn" lay-submit lay-filter="SelectParkinout">查询</button>
            </div>
            <div class="layui-input-inline" style="width: 200px;">
            </div>
        </div>
    </div>
</form>
<table id="parkinoutTable" lay-filter="parkinoutTable"></table>
</body>

<form class="layui-form" style="display: none" id="MonthlyForm" lay-filter="MonthlyForm">
    <div class="layui-form-item" style="display: none">
        <div class="layui-inline">
            <label class="layui-form-label">ID</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="id" placeholder="ID" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <br><br>
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">登记车性质</label>
            <div class="layui-input-inline" style="width: 238px;">
                <select id="carNature" name="carNature" lay-filter="carNature" lay-verify="required">
                    <#--                    <option name="" value="">请选择车辆性质</option>
                                        <option name="" value=1>月租车</option>
                                        <option name="" value=2>车场充值车</option>
                                        <option name="" value=3>按临停缴费车</option>-->
                </select>
                <#--<input type="text" name="" required   placeholder="月租类型" autocomplete="off" class="layui-input">-->
            </div>
            <label class="layui-form-label">车牌</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="carPlate" id="carPlate" placeholder="车牌" autocomplete="off" class="layui-input"
                       lay-verify="required">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">登记车规则</label>
            <div class="layui-input-inline" style="width: 238px;">
                <select name="payRule" id="payRuleUpdate" lay-filter="payRule" lay-verify="required">
                </select>
                <input type="text" name="payruleidstr" id="payRuleStr" style="display: none" autocomplete="off"
                       class="layui-input">
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">车牌颜色</label>
                <div class="layui-input-inline" style="width: 150px;">
                    <input type="text" name="carColor" placeholder="车牌颜色" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">状态</label>
            <div class="layui-input-inline" style="width: 238px;">
                <select name="status" id="status" lay-filter="status" lay-verify="required">
                </select>
            </div>
            <label class="layui-form-label">计费规则</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="tempPayRule" id="tempPayRule" placeholder="计费规则" autocomplete="off"
                       class="layui-input" value="1" lay-verify="required">
            </div>
            <label style="font-size: 5px; color: red;line-height: 40px">*填写计费规则编码</label>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">车位编号</label>
            <div class="layui-input-inline" style="width: 150px;">
                <#--<input type="text" name="parkCode" id="parkCode" required   placeholder="车位编号" autocomplete="off" class="layui-input">-->
                <input type="text"
                       onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[\u4e00-\u9fa5]/g,''))"
                       onkeyup="this.value=this.value.replace(/[\u4e00-\u9fa5]/g,'')" name="parkCode" id="parkCodeShow"
                       placeholder="车位编号" autocomplete="off" class="layui-input" readonly>
            </div>
            <div class="layui-input-inline" style="width: 80px;">
                <#--                    <button class="layui-btn" lay-submit lay-filter="setParkCode">操作</button>-->
                <button class="layui-btn" type="button" onclick="Monthlyhandle.setParkCode()">操作</button>
            </div>
            <label class="layui-form-label">联系人</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="realname" placeholder="联系人" autocomplete="off" class="layui-input"
                       lay-verify="required">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">车位性质</label>
            <div class="layui-input-inline" style="width: 238px;">
                <select id="parkingSpace" name="parkingSpace" lay-filter="parkingSpace" lay-verify="required">
                </select>
            </div>
            <div class="layui-inline">
            <label class="layui-form-label">联系方式</label>
            <div class="layui-input-block">
                <input type="text" name="phone" placeholder="联系方式" autocomplete="off" class="layui-input"
                       lay-verify="required|validatePhone">
            </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">拥有车位数</label>
            <div class="layui-input-inline" style="width: 238px;">
                <input type="text" name="parkingNo" placeholder="" autocomplete="off" class="layui-input"
                       lay-verify="required" value="1">
            </div>
            <label class="layui-form-label">住址</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="address" placeholder="住址" autocomplete="off" class="layui-input"
                       lay-verify="required">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">允许自动续费</label>
            <div class="layui-input-inline" style="width: 238px;">
                <input type="checkbox" value="1" name="autoRenew" lay-skin="switch">
            </div>
            <label class="layui-form-label" style="width: 100px;">允许线上续费</label>
            <div class="layui-input-inline" style="width: 238px;">
                <input type="checkbox" value="1" name="isOnlinePayment" lay-skin="switch">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">备注</label>
            <div class="layui-input-inline" style="width: 350px;">
                <input type="text" name="remark" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">mportIDS</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="portId" id="mportids" placeholder="通道id集合" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px;">通道权限</label>
        <div class="layui-input-inline" style="width: 240px;">
            <ul id="monthTreeDemo" class="ztree" style="width: 230px"></ul>
        </div>
        <#--        <div class="layui-input-inline" style="display: none">
                    <input type="text" name="portId" id="portidsn" required   placeholder="车位编号" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline" style="height: 70px;overflow:auto">
                    <div id="choosePortName1" style="border:1px solid #000;"></div>
                </div>
                <div class="layui-input-inline" style="height: 70px;overflow:auto">
                    <div id="choosePortName2" style="border:1px solid #000">
                    </div>
                </div>-->
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block" style="text-align: center;left: -55px;">
            <button class="layui-btn" lay-submit lay-filter="monthlyUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<#--钱包充值/冲正-->
<form class="layui-form" style="display: none" id="walletRechargeForm" lay-filter="walletRechargeForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id" placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">车牌:</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="carPlate" required autocomplete="off" readonly class="layui-input"
                   style="background-color:transparent;border:0">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">登记车规则:</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="payruleidstr" required autocomplete="off" readonly class="layui-input"
                   style="background-color:transparent;border:0">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">充值/冲正:</label>
        <div class="layui-input-inline" style="width: 80px;">
            <input type="radio" name="rechargeType" value="recharge" title="充值" checked
                   lay-filter="walletRechargeRadio">
        </div>
        <div class="layui-input-inline" style="width: 110px;" lay-filter="walletRechargeRadio">
            <input type="radio" name="rechargeType" value="correct" title="冲正" lay-filter="walletRechargeRadio">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">当前余额:</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="walletBanance" required autocomplete="off" readonly class="layui-input"
                   style="background-color:transparent;border:0">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" id="walletNeedPay">应收: </label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="needPay" lay-verify="required|payValueCheck" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" id="walletActualPay">实收: </label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="actualPay" lay-verify="required|payValueCheck" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注: </label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="remark" required autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="monthlyRechargeUpdate">确认</button>
            <#--        <button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
        </div>
    </div>
</form>

<#--添加车牌弹窗-->
<div id="showCarPlate" style="display: none;">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width: 150px;left: 20px;margin-top:10px">
                <input type="text" name="carPlateOne" id="carPlateOne" <#--lay-verify="carPlateCheck"-->
                       placeholder="输入车牌"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 100px;left: 10px;margin-top:10px">
                <button class="layui-btn" lay-submit lay-filter="carPlateAdd">车牌添加</button>
            </div>
        </div>
    </form>
    <table class="layui-table" id="carplateTable" lay-filter="carplateTable"></table>
    <#--    <button class="layui-btn" lay-submit lay-filter="carPlateToSave" style="margin-left:160px">保存</button>-->
</div>
<#--添加车位编码弹窗-->
<div id="showParkCode" style="display: none;">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width: 150px;left: 20px;margin-top:10px">
                <input type="text" name="parkCodeOne" id="parkCodeOne" lay-verify="parkCodeCheck" placeholder="单个车位"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 100px;left: 10px;margin-top:10px">
                <button class="layui-btn" lay-submit lay-filter="parkCodeAdd">车位添加</button>
            </div>
        </div>
    </form>
    <table class="layui-table" id="carSeatTable" lay-filter="carSeatTable"></table>
    <button class="layui-btn" lay-submit lay-filter="parkCodeToSave" style="margin-left:160px">保存</button>
</div>
<#--<form class="layui-form"  style="display: none" id="carPlateForm" lay-filter="carPlateForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">车牌</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="carPlateid"  autocomplete="off" class="layui-input" style="display: none">
                <input type="text" name="carPlate" required   placeholder="车牌" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="carPlateSave">保存</button>
            &lt;#&ndash;<button type="reset" class="layui-btn layui-btn-primary">重置</button>&ndash;&gt;
        </div>
    </div>
</form>-->
<form class="layui-form" style="display: none" id="ParkCodeForm" lay-filter="ParkCodeForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">车位</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="ParkCodeid" autocomplete="off" class="layui-input" style="display: none">
                <input type="text" name="ParkCode" required placeholder="车位号" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="ParkCodeSave">保存</button>
            <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
        </div>
    </div>
</form>
<form class="layui-form" style="display: none" id="paymentForm" lay-filter="paymentForm">
    <div class="layui-form-item">
        <div class="layui-inline" style="display: none">
            <label class="layui-form-label">park_car_id</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" id="paymentFormParkCarId" name="parkCarId" required placeholder="登记车id"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">车牌</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" id="paymentFormCarplate" name="carPlate" required placeholder="车牌" readonly
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline" style="display: block">
            <label class="layui-form-label">规则</label>
            <div class="layui-input-inline" style="width: 150px">
                <select id="payRule" name="payRule" lay-filter="payRule">
                </select>
            </div>
            <label class="layui-form-label">充值方式</label>
            <div class="layui-input-inline" style="width: 150px;">
                <select id="payStandard" name="payStandard" lay-filter="parkingSpace">
                    <option name="payStandard" value="">请选择充值方式</option>
                    <option name="payStandard" value=0>按日</option>
                    <option name="payStandard" value=1>按月</option>
                    <option name="payStandard" value=2>按季度</option>
                    <option name="payStandard" value=3>按年</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">车位数</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" id="parkingNo" name="parkingNo" required placeholder="车位数" autocomplete="off"
                       class="layui-input">
            </div>
            <label class="layui-form-label">数量</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" id="payCount" name="payCount" required placeholder="数量" autocomplete="off"
                       class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 80px;">
                <button class="layui-btn" type="button" onclick="Monthlyhandle.vouchers()">计算</button>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">起始日期</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="endDate" id="paymentFormEndDate" placeholder="yyyy-MM-dd" autocomplete="off"
                       class="layui-input">
            </div>
            <label class="layui-form-label">新截止日期</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="endDatenew" id="endDatenew" placeholder="yyyy-MM-dd" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">应收</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="needPay" id="needPay" placeholder="应收" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">实收</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="actualPay" id="actualPay" lay-verify="required|payValueCheck" placeholder="实收"
                       autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="remark" placeholder="备注" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="paymentSave">充值</button>
            <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
        </div>
    </div>
</form>
<#--批量充值选择页-->
<div id="batchRecharge" style="display: none">
    <table id="showRechargeTable" lay-filter="showRechargeTable"></table>
    <form class="layui-form" id="batchRechargeForm" lay-filter="batchRechargeForm">
        <div class="layui-form-item" style="margin-top: 10px">
            <div class="layui-inline">
                <#--                <label class="layui-form-label">到期时间:</label>
                                <div class="layui-input-inline" style="width: 100px;">
                                    <select name="closeType"></select>
                                </div>
                                <label class="layui-form-label">缴费规则:</label>
                                <div class="layui-input-inline" style="width: 200px;">
                                    <select name="payRule" lay-filter="payRule">
                                    </select>
                                </div>-->
                <div class="layui-input-inline" style="width: 340px;margin-left: 60px">
                    <#--                    <button class="layui-btn" lay-submit lay-filter="SelectExpireSoonParkCar">查询</button>-->
                    <button class="layui-btn" lay-submit lay-filter="bulkRecharge">批量充值</button>
                    <button class="layui-btn" lay-submit lay-filter="bulkResetPort">批量重置通道权限</button>
                </div>
            </div>
        </div>
    </form>
</div>
<#--批量充值页面-->
<form class="layui-form" style="display: none" id="paymentBatchForm" lay-filter="paymentBatchForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">IDS</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="ids" required id="ids" placeholder="登记车id集合" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">data</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="data" required id="data" placeholder="登记车截止日期集合" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 20px;">
        <div class="layui-inline">
            <label class="layui-form-label">充值方式</label>
            <div class="layui-input-inline" style="width: 150px;">
                <select id="payStandard" name="payStandard" lay-filter="payStandard">
                    <option name="payStandard" value="">请选择充值方式</option>
                    <option name="payStandard" value=0>按日</option>
                    <option name="payStandard" value=1>按月</option>
                    <option name="payStandard" value=2>按季度</option>
                    <option name="payStandard" value=3>按年</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">数量</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" id="payCount" name="payCount" required placeholder="数量" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">现金</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="actualPay" required placeholder="现金" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="remark" placeholder="备注" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="paymentSaveBatch">保存</button>
            <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
        </div>
    </div>
</form>
<form class="layui-form" style="display: none" id="portResetForm" lay-filter="portResetForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">IDS</label>
        <div class="layui-input-inline" style="width: 100px;display: none">
            <input type="text" name="idsport" required id="idsport" placeholder="登记车id集合" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">portIDS</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="portids" required id="portids" placeholder="通道id集合" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">通道权限</label>
        <div class="layui-input-inline" style="width: 240px;">
            <ul id="treeDemo" class="ztree" style="width: 230px"></ul>
        </div>
        <#--        <div class="layui-input-inline" >
                    <div id="choosePortName11" style="border:1px solid #000"></div>
                </div>
                    <div class="layui-input-inline" >
                    <div id="choosePortName22" style="border:1px solid #000">
                    </div>-->
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">选取通道权限</label>
        <div class="layui-input-inline" style="width: 200px;">
            <#--            <input type="text" class="layui-input" id="portId" name="portId">-->
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="portResetSave">保存</button>
            <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
        </div>
    </div>
</form>
<#--冲正-->
<form class="layui-form" style="display: none" id="correctForm" lay-filter="correctForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-inline" style="width: 100px;display: none">
            <input type="text" name="id" required placeholder="登记车id" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top:10px">
        <label class="layui-form-label">车牌</label>
        <div class="layui-input-inline" style="width: 100px">
            <input type="text" name="carPlate" readonly autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开始日期</label>
        <div class="layui-input-inline" style="width: 100px">
            <#--                    <input type="text" name="beginData" required   autocomplete="off" class="layui-input">-->
            <input type="text" name="beginDate" id="correctFormBeginData" required placeholder="yyyy-MM-dd"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">截止日期</label>
        <div class="layui-input-inline" style="width: 100px">
            <input type="text" name="endDate" id="correctFormEndData" lay-verify="endDate" placeholder="yyyy-MM-dd"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">缴费方式</label>
        <input type="radio" id="cashPayType" name="payType" value="1" title="现金" checked>
        <input type="radio" id="elePayType" name="payType" value="2" title="电子">
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">冲正金额</label>
        <div class="layui-input-inline" style="width: 100px">
            <input type="text" name="needPay" lay-verify="required|payValueCheck" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline" style="width: 100px">
            <input type="text" name="remark" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="correctFormSave">保存</button>
        </div>
    </div>
</form>
<form class="layui-form" style="display: none" id="excelForm" lay-filter="excelForm">
    <div class="layui-form-item" style="margin-top: 10px">
        <div class="layui-inline">
            <label class="layui-form-label">停车场</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="parkId" id="parkIdExcel" lay-filter="parkId" lay-search lay-verify="required">
                </select>
            </div>
            <label class="layui-form-label">登记车规则</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="payRuleExcel" id="payRuleExcel" lay-filter="payRule" lay-search lay-verify="required">
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">mportExcelIDS</label>
        <div class="layui-input-inline">
            <input type="text" name="portId" id="portIdExcel" placeholder="通道id集合" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">通道权限</label>
        <div class="layui-input-inline" style="width: 240px;">
            <ul id="portTreeExcel" class="ztree" style="width: 230px"></ul>
        </div>
    </div>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
        <legend>拖拽上传</legend>
    </fieldset>
    <div class="layui-form-item" style="margin-left: 20px">
        <div class="layui-inline">
            <#--                <button id="downLoad" class="layui-btn layui-btn-normal" onclick="Monthlyhandle.downLoad()">模板下载
                            </button>-->
            <a href="../temp/owners.xls" style="text-decoration: underline ; color :dodgerblue;" download="owners.xls"
               style="margin-left: 10px">模板下载</a>
            <div class="layui-upload-drag" id="test10" style="margin-left: 30px">
                <i class="layui-icon"></i>
                <p>点击上传，或将文件拖拽到此处</p>
                <div class="layui-hide" id="uploadDemoView">
                    <hr>
                    <img src="" alt="上传成功后渲染" style="max-width: 196px">
                </div>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" id="excelFormSave" lay-submit lay-filter="excelFormSave">保存</button>
        </div>
    </div>
</form>
</html>