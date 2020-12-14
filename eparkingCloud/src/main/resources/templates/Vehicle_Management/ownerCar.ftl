<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>车主车位管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="../js/zTree/zTreeStyle.css" type="text/css">
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script src="../js/zTree/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript">
        var tableIns;
        var tableParkCode,
            tablecarplate,
            payRuleList,
            tableParkCar;
        var carNatureList=[{id:1,name:'月租车'},{id:2,name:'车场充值车'},{id:3,name:'按临停缴费车'}];
        var jsonCarPlate = new Array();
        var jsonParkCode = new Array();
        var zNodes, setting, index;
        var tParkCarId;
        var PortZtree = {
            getAllZtree: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../BaseDic/getPortNameListForZtree",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log("zNodes",result.data);
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
                $.fn.zTree.init($("#ownerCarTreeDemo"), setting, zNodes);
            },
            onCheck: function (e, treeId, treeNode) {
                var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    mTree = $.fn.zTree.getZTreeObj("ownerCarTreeDemo"),
                    checkCount = zTree.getCheckedNodes(true),
                    mCheckCount = mTree.getCheckedNodes(true);
                //console.log(checkCount);
                var portIds = "",
                    mPortIds = "";
                checkCount.forEach(function (index, val) {
                    //console.log(index,val);
                    if (val == 0) {
                        portIds = index.id;
                    } else {
                        portIds = portIds + "," + index.id;
                    }
                });
                mCheckCount.forEach(function (index, val) {
                    //console.log(index,val);
                    if (val == 0) {
                        mPortIds = index.id;
                    } else {
                        mPortIds = mPortIds + "," + index.id;
                    }
                });
                //console.log("portIds",portIds);
                $("#portids").val(portIds);
                $("#mportids").val(mPortIds);

            }
        };
        layui.use(['table', 'form'], function () {
            var table = layui.table;
            tablecarplate = layui.table;
            var form = layui.form;
            tableIns = table.render({
                elem: '#parkinoutTable'
                , height: 470
                , url: '../monthlyCar/getOwnnerCarbyPage' //数据接口
                , method: 'post'
                , toolbar: true
                , toolbar: '#toolBarOwnerCar'
                , defaultToolbar: ['filter', 'print']
                , loading: true
                , title: '车主车'
                , page: true //开启分页
                , cols: [[ //表头
                    {type: 'numbers', title: '序号', width: 50, fixed: 'left'}
                    , {field: 'carPlate', title: '车牌',align: 'center', width: 110, sort: true}
                    , {field: 'carNature', title: '登记车性质',align: 'left', width: 130, templet: '#carNatureTemp'}
                    , {field: 'parkingSpace', title: '车位性质',align: 'center', width: 90, templet: '#parkingSpaceTemp'}
                    , {field: 'payRule', title: '登记车规则',align: 'left', width: 120, sort: true, templet: '#payRuleTemp'}
                    , {field: 'beginDate', title: '开始时间',align: 'center', width: 120, sort: true}
                    , {field: 'endDate', title: '到期时间',align: 'center', width: 120, sort: true}
                    , {field: 'stopTime', title: '暂停时间',align: 'center', width: 120, sort: true}
                    , {field: 'realname', title: '联系人',align: 'center', width: 90, sort: true}
                    , {field: 'phone', title: '电话',align: 'center', width: 120, sort: true}
                    , {field: 'address', title: '住宅',align: 'center', width: 80, sort: true}
                    , {field: 'status', title: '状态',align: 'center', width: 70, templet: '#statusTemp'}
                    , {field: 'parkingNo', title: '车位数',align: 'center', width: 80}
                    , {field: 'parkCode', title: '车位编码',align: 'center', width: 90}
                    , {field: 'walletBanance', title: '钱包余额',align: 'center', width: 90}
                    , {fixed: 'right', title: '操作',align: 'center', width: 190, toolbar: '#ownerCarBar'}
                    // ,{field: 'remarks', title: '备注', width: 120}
                ]]
            });

            //头工具栏事件
            table.on('toolbar(parkinoutTable)', function (obj) {
                var status = $('#statusSer option:selected').val();
                var payRule = $('#payRuleform option:selected').val();
                // var parkingSpace = $('#parkingSpaceform option:selected').val();
                // var parkingSpace = $('#parkingSpaceform').val();
                var parkCode = $('#carSeatform').val();
                var carPlatemm = $('#carPlateform').val();
                //var parkingState=$("#parkingState").val();
                var data = {
                    status: status,
                    payRule: payRule,
                    // parkingSpace: parkingSpace,
                    parkCode: parkCode,
                    carPlate: carPlatemm
                };
                //console.log("data: ",data);
                ownerCarHandle.ownerExcel(data);
            });

            //监听工具条
            table.on('tool(parkinoutTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if (layEvent === 'detail') { //查看
                    //do somehing
                } else if (layEvent === 'delMonthly') { //删除
                    layer.confirm('真的删除行么', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        ownerCarHandle.delMonthly(data);
                        //layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if (layEvent === 'edit') { //编辑
                    form.val("ownerCarForm", data);
                    tParkCarId=data.id;
                    // console.log(data);
                    ownerCarHandle.Updateo(1);//弹出车场更新窗口
                } else if(layEvent === 'carplate'){ //车牌管理
                    tParkCarId=data.id;
                    ownerCarHandle.setCarplate();
                }
            });
            //操作车牌
/*            form.on('submit(setCarPlate)', function (data) {
                $("#carPlateOne").val("");
                var jsonCarPlateOld = data.field.carPlate.split(',');
                //console.info(jsonCarPlateOld);
                if (jsonCarPlateOld != "" && jsonCarPlateOld != null) {
                    jsonCarPlateOld.forEach(function (index, val) {
                        //console.info(index);
                        var a = {'carPlateid': val, 'carPlate': index};
                        jsonCarPlate.push(a);
                    });
                }
                //console.info(jsonCarPlate);
                tablecarplate.reload({
                    data: jsonCarPlate //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                index = layer.open({
                    title: '添加车牌'
                    , type: 1
                    , area: ['700px', '600px']
                    , content: $('#showCarPlate')
                    , cancel: function (index, layero) {
                        jsonCarPlate = new Array();
                        layer.close(index)
                        return false;
                    }
                });
                return false;
            });*/
            //添加车牌
            form.on('submit(carPlateAdd)', function () {
                var carPlateOne = $("#carPlateOne").val();
                // ownerCarHandle.checkCarplate({'carPlate':carPlateOne});
/*                var a = {'carPlateid': jsonCarPlate.length, 'carPlate': carPlateOne};
                $("#carPlateOne").val("");
                jsonCarPlate.push(a);
                tablecarplate.reload({
                    data: jsonCarPlate //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });*/
                var data = {parkCarId:tParkCarId,carPlate:carPlateOne};
                // console.log(data);
                monthBindingCarForm.add(data);
                return false;
            });
            // 表单验证
            form.verify({
                carPlateCheck: function (value, item) {
                    if (value.length > 0) {
                        if (common.carPlateCheck(value) != true) {
                            return '请输入合法车牌号';
                        }
                    } else {
                        return '请输入车牌号';
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
                }
            });
            //保存车牌
            form.on('submit(carPlateToSave)', function () {
                var carPlateList;
                jsonCarPlate.forEach(function (index, val) {
                    //console.log(index,val);
                    if (val == 0) {
                        carPlateList = index.carPlate;
                    } else {
                        carPlateList = carPlateList + "," + index.carPlate;
                    }
                });
                layer.close(index);
                // console.log("close");
                $("#formCarPlate").val(carPlateList);
                jsonCarPlate = new Array();
                return false;
            });
            //车位编号操作
            form.on('submit(setCarSeat)', function (data) {
                var jsonParkCode1 = data.field.parkCode.split(',');
                //console.info(jsonParkCode1);
                if (jsonParkCode1 != "" && jsonParkCode1 != null) {
                    jsonParkCode1.forEach(function (index, val) {
                        //console.info(index);
                        var a = {'ParkCodeid': val, 'ParkCode': index};
                        jsonParkCode.push(a);
                    });
                }
                //console.info(jsonParkCode);
                tableParkCode.reload({
                    data: jsonParkCode //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                index = layer.open({
                    title: '添加车位编码'
                    , type: 1
                    , area: ['700px', '600px']
                    , content: $('#showCarSeat')
                    , cancel: function (index, layero) {
                        jsonParkCode = new Array();
                        layer.close(index)
                        return false;
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
                $("#parkCode").val(ParkCodeList);
                jsonParkCode = new Array();
                return false;
            });

            //车位添加
            form.on('submit(parkCodeAdd)', function () {
                var carSeatOne = $("#carSeatOne").val();
                var a = {'ParkCodeid': jsonParkCode.length, 'ParkCode': carSeatOne};
                $("#carSeatOne").val("");
                jsonParkCode.push(a);
                tableParkCode.reload({
                    data: jsonParkCode //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                return false;
            });

            form.on('submit(SelectParkinout)', function (data) {
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log("ss:"+data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                ownerCarHandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });

            form.on('submit(ownerCarImport)', function (data) {
                return false;
            });
            //车位编辑更新
            form.on('submit(ownerCarUpdate)', function (data) {
                /*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                // console.log(data.field);
                if(data.field.portId!=""){
                    ownerCarHandle.save(data.field);
                }else{
                    layer.msg("请选择通道权限", {icon: 5,time:1500});
                }
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });

            table.on('tool(carplateTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                if (layEvent === 'del') { //删除
                    layer.confirm('真的删除行么', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        monthBindingCarForm.delete(data);
                        layer.close(index);
                        //向服务端发送删除指令
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
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        ownerCarHandle.deleteCarSeat(data);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                }
            });

/*            tablecarplate = table.render({
                elem: '#carplateTable'
                , height: 315
                , method: 'post'
                , page: true //开启分页
                , cols: [[ //表头
                    //{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                    {type: 'numbers', title: '序号', width: 80, fixed: 'left'}
                    , {field: 'carPlate', edit: 'text', title: '车牌', width: 120}
                    , {fixed: 'right', title: '操作', width: 200, toolbar: '#carPlateBar'}
                ]]
            });*/

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
            ownerCarForm.getCarPayRule(form);
            form.render();
        });
        //在页面加载完成后执行
        $(document).ready(function () {
            PortZtree.getAllZtree();
            ownerCarForm.getstatusList();
            ownerCarForm.getCarNature();
            ownerCarForm.getParkingSpace();
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

        //车主车位增删查改方法
        var ownerCarHandle = {
            select: function (obj) {
                //这里以搜索为例
                tableIns.reload({
                    where:  //设定异步数据接口的额外参数，任意设
                    obj,
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                })
            },

            Updateo: function (type) {
                if (type == 0) {//新增
                    //console.log("new");
                    $("#carPlate").attr("readonly",false);
                    ownerCarForm.clean();
                    PortZtree.resetZtree();
                    layer.open({
                        title: '新增车主车位'
                        , type: 1
                        , area: ['850px', '600px']
                        , content: $('#ownerCarForm')
                    });
                } else if (type == 1) {//編輯
                    //console.log("edit");
                    var portIds = $("#mportids").val();
                    $("#carPlate").attr("readonly",true);
                    PortZtree.resetZtree(portIds);
                    layer.open({
                        title: '更新车主车位'
                        , type: 1
                        , area: ['850px', '600px']
                        , content: $('#ownerCarForm')
                    });
                }

            },
            delete: function (data) {
                jsonCarPlate.remove(data.carPlateid);
/*                tablecarplate.reload({
                    data: jsonCarPlate //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });*/
            },
            deleteCarSeat: function (data) {
                jsonParkCode.remove(data.ParkCodeid);
                tableParkCode.reload({
                    data: jsonParkCode //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            delMonthly: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/refund",
                    data: object,
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
            save: function (object) {
                var form = layui.form;
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/updateMonthlyCar",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log("result.code", result.code);
                        if (result.code == 0) {
                            layer.closeAll();
                            ownerCarForm.clean();
                            tableIns.reload();
                        } else {
                            layer.msg(result.message, {
                                icon: 1,
                                time: 1500 //1.5秒关闭（如果不配置，默认是3秒）
                            });
                            //layer.closeAll();
                            ownerCarForm.clean();
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
            ownerExcel: function (object) {
                // console.log(object);
                window.location.href = "../Base/exportListOwner?status=" + object.status + "&payRule=" + object.payRule  + "&parkCode=" + object.parkCode + "&carPlate=" + object.carPlate;
            },
            checkCarplate: function (object) {
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
            },
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
            setParkCode:function () {
                $("#carSeatOne").val("");
                if($("#parkCode").val()!=null){
                    var jsonParkCode1 = $("#parkCode").val().split(',');
                }
                //console.info(jsonParkCode1);
                if (jsonParkCode1 != "" && jsonParkCode1 != null) {
                    jsonParkCode1.forEach(function (index, val) {
                        //console.info(index);
                        var a = {'ParkCodeid': val, 'ParkCode': index};
                        jsonParkCode.push(a);
                    });
                }
                //console.info(jsonParkCode);
                tableParkCode.reload({
                    data: jsonParkCode //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
                index = layer.open({
                    title: '添加车位编码'
                    , type: 1
                    , area: ['700px', '600px']
                    , content: $('#showCarSeat')
                    , cancel: function (index, layero) {
                        jsonParkCode = new Array();
                        layer.close(index)
                        return false;
                    }

                });
            }
        };

        //车位更新表单使用方法
        var ownerCarForm = {
            clean: function () {
                $("#ownerCarForm")[0].reset();
            },
            //获取车位状态
            getstatusList: function () {
                statusList = [{id: 1, name: '启用'}, {id: 2, name: '停用'}];
                var str = " <option value>请选择状态</option>";
                statusList.forEach(function (t) {
                    str += "<option value=" + t.id + ">" + t.name + "</option>";
                });
                $("select[name='status']").html(str);
            },
            //获取车位性质
            getParkingSpace: function () {
                parkingSpaceList = [{id: 4, name: '车主车位'}];
                var str = " <option value>请选择</option>";
                parkingSpaceList.forEach(function (t) {
                    str += "<option value=" + t.id + ">" + t.name + "</option>"
                });
                $("select[name='parkingSpace']").html(str);
            },
            //获取月租规则
            getCarPayRule: function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../carPayRule/getPayRule",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            // console.log(result.result);
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
                        //console.log(result);
                    }
                })
            },
            getCarNature: function () {
                var str = "<option value>请选择登记车性质</option>";
                carNatureList.forEach(function (t) {
                    str += "<option value=" + t.id + ">" + t.name + "</option>"
                });
                $("select[name='carNature']").html(str);
            }
        };

        var monthBindingCarForm = {
            showTablecarplate:function() {
                // console.log(tParkCarId);
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
                        , {fixed: 'right', title: '操作', width: 200, toolbar: '#carPlateBar'}
                    ]]
                });
            },
            reload :function(object){
                tablecarplate.reload('carplateTable',{
                    where: { //设定异步数据接口的额外参数，任意设
                        parkCarId:object.tParkCarId
                    },
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            add:function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../monthBinding/updateMonthBindingCar",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if (result.code == 0) {
                            layer.msg(result.result, {icon: 6,time: 2000});
                            monthBindingCarForm.reload(object);
                            ownerCarHandle.select();
                            // tParkCarId=0;
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            },
            delete:function (object) {
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
                            ownerCarHandle.select();
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            }
        };
    </script>
    <script type="text/html" id="parkingSpaceTemp">
        {{# layui.each(parkingSpaceList,function(index,item){ }}
        {{# if(item.id===d.parkingSpace){ }}
        {{ item.name }}
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

    <script type="text/html" id="toolBarOwnerCar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="ownerCarHandle.Updateo(0);">车主车位办理</button>
            <button class="layui-btn layui-btn-sm" lay-event="ownerExcel">导出车主车位信息</button>
        </div>
    </script>
    <script type="text/html" id="ownerCarBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs" lay-event="carplate">车牌管理</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="delMonthly">删除</a>
    </script>
    <script type="text/html" id="carPlateBar">
        <#--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>-->
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="parkCodeBar">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">车主车位管理</blockquote>
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
<form class="layui-form" style="margin: 10px" id="queryForm" lay-filter="queryForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="status" id="statusSer" lay-filter="parkingSpace">
                </select>
            </div>
            <label class="layui-form-label">登记车规则</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select name="payRule" id="payRuleform" lay-filter="payRule">
                </select>
            </div>
            <label class="layui-form-label">车位编码</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" id="carSeatform" name="parkCode" placeholder="车位编码" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">车牌</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" id="carPlateform" name="carPlate" placeholder="车牌" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 200px;margin-left: 110px">
                <button class="layui-btn" lay-submit lay-filter="SelectParkinout">查询</button>
            </div>
        </div>
    </div>
</form>
<form class="layui-form" style="display: none" id="ownerCarForm" lay-filter="ownerCarForm">
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
                </select>
                <#--<input type="text" name="" required   placeholder="月租类型" autocomplete="off" class="layui-input">-->
            </div>
            <label class="layui-form-label">车牌</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="carPlate" id="carPlate" placeholder="车牌" autocomplete="off" class="layui-input"
                       readonly lay-verify="required">
            </div>
            <div class="layui-input-inline" style="width: 70px;">
<#--                <button class="layui-btn" lay-submit lay-filter="setCarPlate">操作</button>-->
<#--                <button class="layui-btn" type="button" onclick="ownerCarHandle.setCarplate()">操作</button>-->
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">

            <div class="layui-inline">
                <label class="layui-form-label" style="width: 100px;">车位编号</label>
                <div class="layui-input-inline" style="width: 150px;">
                    <input type="text" name="parkCode" id="parkCode" required placeholder="车位编号" autocomplete="off"
                           class="layui-input" readonly>
                </div>
                <div class="layui-input-inline" style="width: 150px;">
<#--                    <button class="layui-btn" lay-submit lay-filter="setCarSeat">操作</button>-->
                    <button class="layui-btn" type="button" onclick="ownerCarHandle.setParkCode()">操作</button>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">登记车规则</label>
            <div class="layui-input-inline" style="width: 238px;">
                <select name="payRule" lay-filter="payRule" lay-verify="required">
                </select>
                <#--<input type="text" name="payRule" required   placeholder="缴费规则" autocomplete="off" class="layui-input">-->
            </div>
            <label class="layui-form-label">联系人</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="realname" lay-verify="required" placeholder="联系人" autocomplete="off" class="layui-input">
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
            <label class="layui-form-label">联系方式</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="phone" lay-verify="required" placeholder="联系方式" autocomplete="off" class="layui-input"
                       lay-verify="required|validatePhone">
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
            <label class="layui-form-label">住址</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="address" required placeholder="住址" autocomplete="off" class="layui-input" lay-verify="required">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">车位数</label>
            <div class="layui-input-inline" style="width: 238px;">
                <input type="text" name="parkingNo" required placeholder="" autocomplete="off" class="layui-input" lay-verify="required" value="1">
            </div>
            <label class="layui-form-label">车牌颜色</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="carColor" required placeholder="车牌颜色" autocomplete="off" class="layui-input">
                <input type="text" style="display: none" name="memberId" required autocomplete="off"
                       class="layui-input">
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
            <div class="layui-input-inline" style="width: 238px;">
                <input type="text" name="remark" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">mportIDS</label>
        <div class="layui-input-inline" style="width: 100px;">
            <input type="text" name="portId" required id="mportids" placeholder="通道id集合" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px;">通道权限</label>
        <div class="layui-input-inline" style="width: 240px;">
            <ul id="ownerCarTreeDemo" class="ztree" style="width: 230px"></ul>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block" style="text-align: center;left: -55px;">
            <button class="layui-btn" lay-submit lay-filter="ownerCarUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<#--添加车牌弹窗-->
<div id="showCarPlate" style="display: none;">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width: 150px;left: 20px;margin-top:10px">
                <input type="text" name="carPlateOne" id="carPlateOne" placeholder="单个车牌" lay-verify="carPlateCheck"
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
<div id="showCarSeat" style="display: none;">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width: 150px;left: 20px;margin-top:10px">
                <input type="text" name="carSeatOne" id="carSeatOne" lay-verify="parkCodeCheck" placeholder="单个车位"
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
<table id="parkinoutTable" lay-filter="parkinoutTable"></table>
</body>
</html>