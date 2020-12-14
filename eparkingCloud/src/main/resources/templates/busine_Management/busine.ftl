<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>商户信息</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>

    <script type="text/javascript">
        var tableIns, form, tableEle;
        var busineIdParameter, companyIdParameter, busineNameParameter;
        var discountTypeList = [{id: 0, name: '限制金额'}, {id: 1, name: '时间'}, {id: 2, name: '固定金额'}, {
            id: 3,
            name: '电子券'
        }];
        layui.use(['table', 'form', 'layer', 'laydate'], function () {
            var table = layui.table;
            tableEle = layui.table;
            form = layui.form;
            var laydate = layui.laydate;
            laydate.render({
                elem: '#expiryDate'
                , type: 'datetime'
            });
            var layer = layui.layer;
            //第一个实例
            tableIns = table.render({
                elem: '#busineTable'
                , height: 680
                , url: '../busine/getTBusinebyPage' //数据接口
                , method: 'post'
                , toolbar: '#busineToolbar'
                , defaultToolbar: ['filter', 'print']
                , page: true //开启分页
                , loading: true
                , cols: [[ //表头
                    /* {field: 'id', title: 'ID', width:80, sort: true, hide:true}*/
                    {type: 'numbers', title: '序号', width: 50, fixed: 'left', sort: true}
                    , {field: 'account', title: '账户名称',align:'center', width: 100}
                    // ,{field: 'parkId', title: '所属停车场', width:150,templet:'#parkIdTemp'}
                    , {field: 'busineName', title: '商户名称',align:'center', width: 120}
                    , {field: 'principal', title: '负责人',align:'center', width: 90}
                    , {field: 'phone', title: '商户电话', width: 120, sort: true}
                    , {field: 'mobile', title: '手机',align: 'center', width: 120, sort: true}
                    , {field: 'discountType', title: '优惠类型',align: 'center', width: 105, sort: true, templet: '#discountTypeTemp'}
                    // ,{field: 'busineType', title: '商户类别', width: 100, sort: true,templet:'#busineTypeTemp'}
                    , {field: 'address', title: '商户地址',align: 'center', width: 150, sort: true}
                    , {field: 'balance', title: '账户余额',align: 'right', width: 110, sort: true}
                    , {field: 'discountAmount', title: '最大优惠金额',align: 'right', width: 130, sort: true}
                    , {field: 'busineStatus', title: '状态',align: 'center', width: 80, sort: true, templet: '#busineStatusTemp'}
                    , {fixed: 'right', title: '操作',align: 'center', width: 450, toolbar: '#busineBar'}
                ]]
                , done: function (res, curr, count) {
                    //console.log(res);
                }
            });
            //头工具栏事件
            table.on('toolbar(busineTable)', function (obj) {
                var checkStatus = table.checkStatus(obj.config.id);
                switch (obj.event) {
                    case 'busineAdd':
                        var data = checkStatus.data;
                        Businehandle.Update(0);
                        break;
                    default:
                }
                ;
            });
            //监听工具条
            table.on('tool(busineTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                // console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if (layEvent === 'detail') { //查看
                    //do somehing
                } else if (layEvent === 'del') { //删除
                    layer.confirm('是否删除', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        Businehandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if (layEvent === 'edit') { //编辑
                    form.val("BusineForm", data);
                    // $('#initialAmount').val(data.initialAmount);
                    Businehandle.Update(1);//弹出车场更新窗口
                    // console.log(data);
                    if (data.discountType == 2) {
                        /*                        $('#initialAmount').attr('disabled',false);
                                                $('#initialAmount').css("background","");*/
                        $('#fixedAmount').attr('disabled', false);
                        $('#fixedAmount').css("background", "");
                        // $('#isAuto').attr('disabled',false);
                        $('#fixedAmount').val(data.fixedAmount);
                    } else {
                        /*                        $('#initialAmount').attr('disabled',true);
                                                $('#initialAmount').css("background","#CCCCCC");*/
                        $('#fixedAmount').attr('disabled', true);
                        $('#fixedAmount').css("background", "#CCCCCC");
                        // $('#isAuto').attr('disabled',true);
                        $('#fixedAmount').val();
                    }
                } else if (layEvent === 'recharge') {
                    RechargeForm.clean();
                    form.val("RechargeForm", data);
                    RechargeForm.show();
                } else if (layEvent === 'changePassword')//修改密码
                {
                    BusineForm.changePasswordFrom();
                    // console.log(data);
                    form.val("changePassword", {"idChange": data.id});
                    // console.log($("input[name=idChange]").val());

                } else if (layEvent === 'resetPassword') {
                    layer.confirm('是否需要重置密码', function (index) {
                        Businehandle.resetPassword(data.id);
                        layer.close(index);
                    });
                } else if (layEvent === 'eleAuthorize') {
                    //仅允许电子券商户使用授权电子券批次功能
                    if (data.discountType == 3) {
                        busineIdParameter = data.id;
                        companyIdParameter = data.companyId;
                        busineNameParameter = data.busineName;
                        $("#eleBuyForm")[0].reset();
                        BusineForm.showEleTicketTable();
                        layer.open({
                            title: '授权电子券批次'
                            , type: 1
                            , area: ['710px', '600px']
                            , content: $('#eleBuyForm')
                        });
                    } else {
                        layer.msg("该商户不是电子券商户");
                    }
                }
            });

            function completeDate(value) {
                return value < 10 ? "0" + value : value;
            }

            form.verify({
                checkDate: function (value, item) {
                    var nowDate = new Date();
                    var colon = ":";
                    var h = nowDate.getHours();
                    var m = nowDate.getMinutes();
                    var s = nowDate.getSeconds();
                    var nowDateTime = Businehandle.getNowFormatDay(nowDate) + " " + Businehandle.completeDate(h) + colon + Businehandle.completeDate(m) + colon + Businehandle.completeDate(s);
                    if (new Date($("#expiryDate").val().replace("-", "/").replace("-", "/")) < new Date(nowDateTime.replace("-", "/").replace("-", "/"))) {
                        return '到期日期应大于当前日期，请重新填写';
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
            //监听更新按钮
            form.on('submit(busineUpdate)', function (data) {
                /*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                // console.log(data.field);
                Businehandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听查询按钮
            form.on('submit(SelectBusine)', function (data) {
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回*/
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                Businehandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听充值按钮
            form.on('submit(busineRecharge)', function (data) {
                /*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                var type = 1;//冲值
                Businehandle.Recharge(data.field, type);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听充正按钮
            form.on('submit(busineCorrect)', function (data) {
                /*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                var type = 0;//冲正
                Businehandle.Recharge(data.field, type);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
            //监听修改密码按钮
            form.on('submit(changePassworeparkingdbtn)', function (data) {
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                // console.log($("input[name=idChange]").val());
                Businehandle.changePassword(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听购买电子券按钮
            form.on('submit(eleBuyBtn)', function (data) {
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                var checkStatus = tableEle.checkStatus('showEletichet');
                var tableData = checkStatus.data;
                if (tableData.length > 0) {
                    data.field.ticketId = tableData[0].id;
                    data.field.ticketName = tableData[0].ticketName;
                    data.field.busineId = busineIdParameter;
                    data.field.busineName = busineNameParameter;
                    data.field.companyId = companyIdParameter;
                    Businehandle.eleBuy(data.field);
                } else {
                    layer.msg("请选择需要购买的电子券");
                }

                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });

            form.on('switch(isfree)', function (data) {
                if (data.elem.checked) {
                    $('#discountAmount').val(999);
                } else {
                    $('#discountAmount').val(0);
                }
            });

            //监听优惠规则下拉选择框
            form.on('select(discountType)', function (data) {
                //console.log(data.elem); //得到select原始DOM对象
                //console.log(data.value); //得到被选中的值
                //console.log(data.othis); //得到美化后的DOM对象
                if (data.value == 2) {
                    /*                    $('#initialAmount').attr('disabled',false);
                                        $('#initialAmount').css("background","");*/
                    $('#fixedAmount').attr('disabled', false);
                    $('#fixedAmount').css("background", "");
                    // $('#isAuto').attr('disabled',false);
                } else {
                    /*                    $('#initialAmount').attr('disabled',true);
                                        $('#initialAmount').css("background","#CCCCCC");*/
                    $('#fixedAmount').attr('disabled', true);
                    $('#fixedAmount').css("background", "#CCCCCC");
                    // $('#isAuto').attr('disabled',true);

                }
            });
            //固定金额整除控制
            form.verify({
                chu: function (value, item) {
                    var bchu = $('#initialAmount').val();
                    var discountType = $('#discountType option:selected').val();//选中的值
                    if (discountType == 2) {
                        if (bchu % value != 0) {
                            return '不整除';
                        }
                    }

                }
            });
            form.render(); //更新全部
        });
        //在页面加载完成后执行
        $(document).ready(function () {
            BusineForm.getParkList();
            BusineForm.getStatus();
            // BusineForm.getbusineType();

            $("input[name='needPay']").bind('input propertychange', function () {
                $("input[name='actualPay']").val($(this).val());
            });
            $("input[name='ticketNum']").bind('input propertychange', function () {
                var checkStatus = tableEle.checkStatus('showEletichet');
                var tableData = checkStatus.data;
                if (tableData.length > 0) {
                    $("input[name='needPay']").val($(this).val() * tableData[0].ticketPay);
                } else {
                    layer.msg("请选择需要购买的电子券");
                    $("input[name='ticketNum']").val(0);
                }

            });
        })
        //车场增删查改方法
        var Businehandle = {
            //修改密码
            changePassword: function (data) {
                $.ajax({
                    type: 'POST',
                    url: "../busine/changePassword",
                    data: {
                        id: data.idChange,
                        password: data.password,
                        newPassword: data.newPassword,
                        resetPassword: data.resetPassword
                    },
                    dataType: "JSON",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if (result.code == 0) {
                            layer.closeAll();
                            layer.msg(result.result);
                            tableIns.reload();
                        } else {
                            layer.msg(result.message);
                        }
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            //重置密码
            resetPassword: function (id) {
                $.ajax({
                    type: 'POST',
                    url: "../busine/resetPassword",
                    data: {'id': id},
                    dataType: "JSON",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if (result.code == 0) {
                            layer.msg(result.result);
                            tableIns.reload();
                        }
                    },
                    error: function (result) {
                        console.log(result);
                    }
                });
            },
            Update: function (type) {
                if (type == 0) {//新增
                    BusineForm.clean();
                    layer.open({
                        title: '新增商户'
                        , type: 1
                        , area: ['600px', '700px']
                        , content: $('#BusineForm')
                    });
                }else{
                    layer.open({
                        title: '更新商户'
                        , type: 1
                        , area: ['600px', '700px']
                        , content: $('#BusineForm')
                    });
                }
            },
            Recharge: function (object, type) {
                var tBusinePay = {
                    busineId: object.id,
                    busineName: object.busineName,
                    //parkId:object.parkId,
                    needPay: object.needPay,
                    actualPay: object.actualPay,
                    type: type
                }
                $.ajax({
                    type: 'POST',
                    url: "../busine/busineRecharge",
                    data: tBusinePay,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if (result.code == 0) {
                            layer.closeAll();
                            layer.msg(result.result);
                            tableIns.reload();
                        }
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            select: function (data) {
                //这里以搜索为例
                tableIns.reload({
                    where: data
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            save: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../busine/updateBusine",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if (result.code == 0) {
                            layer.closeAll();
                            layer.msg(result.result);
                            tableIns.reload();

                        }
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            delete: function (id) {
                $.ajax({
                    type: 'POST',
                    url: "../busine/deleteBusine",
                    data: {'id': id},
                    dataType: "JSON",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if (result.code == 0) {
                            layer.msg(result.result);
                            tableIns.reload();
                        }
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            eleBuy: function (data) {
                $.ajax({
                    type: 'POST',
                    url: "../busine/eleAuthorization",
                    data: data,
                    dataType: "JSON",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if (result.code == 0) {
                            layer.closeAll();
                            layer.msg(result.result);
                            tableIns.reload();
                        } else {
                            layer.msg(result.message, {icon: 5});
                        }
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            completeDate: function (value) {
                return value < 10 ? "0" + value : value;
            },
            getNowFormatDay: function (nowDate) {
                var char = "-";
                if (nowDate == null) {
                    nowDate = new Date();
                }
                var day = nowDate.getDate();
                var month = nowDate.getMonth() + 1;//注意月份需要+1
                var year = nowDate.getFullYear();
                //补全0，并拼接
                return year + char + Businehandle.completeDate(month) + char + Businehandle.completeDate(day);
            }
        };
        var parkList, busineStatusList;
        //商户更新表单使用方法
        var BusineForm = {
            changePasswordFrom: function () {
                layer.open({
                    title: '修改密码'
                    , type: 1
                    , area: ['500px', '600px']
                    , content: $('#changePassword')
                });
                $("#changePassword")[0].reset();
            },
            clean: function () {
                $("#BusineForm")[0].reset();
            },
            //获取停车场
            getParkList: function () {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarPark",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            //console.log(result);
                            parkList = result.data;
                            /* var str=" <option value>请选择停车场</option>";
                             parkList.forEach(function (t) {
                                 str+="<option value="+t.id+">"+t.parkName+"</option>"
                             });
                             $("select[name='parkId']").html(str);*/
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
            //获取状态
            getStatus: function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getBusineStatusList",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            busineStatusList = result.result;
                            var str;
                            busineStatusList.forEach(function (t) {
                                str += "<option value=" + t.id + ">" + t.name + "</option>"
                            });
                            $("#busineStatus").html(str);
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
            /*            getbusineType:function () {
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

                        },*/
            showEleTicketTable: function () {
                tableEle.render({
                    elem: '#showEletichet'
                    , height: 300
                    , url: '../electronicTicket/getTElectronicTicketbyPage' //数据接口
                    , method: 'post'
                    /*,toolbar: true
                    ,toolbar: '#toolbarBlack'*/
                    //,defaultToolbar: ['filter', 'print']
                    , loading: true
                    , page: true //开启分页
                    , cols: [[ //表头
                        /* {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}*/
                        /*,{field: 'parkId', title: '停车场', width:120,templet:'#parkIdTemp'}*/
                        {type: 'radio'}
                        , {type: 'numbers', title: '序号', width: 50}
                        , {field: 'ticketName', title: '电子卷名称', width: 200 , align:'center'}
                        , {field: 'ticketType', title: '类型', width: 80, templet: '#ticketTypeTemp'}
                        , {field: 'permissionDay', title: '时间限额', width: 100, templet: '#permissionDayTemp'}
                        , {field: 'ticketValue', title: '价值(元/分钟)', width: 120, templet: '#ticketValueTemp'}
                        , {field: 'ticketPay', title: '单价(元)', width: 100}
                    ]]
                });
            },
        }
        //商户充值表单使用方法
        var RechargeForm = {
            clean: function () {
                $("#RechargeForm")[0].reset();
            },
            show: function () {
                layer.open({
                    title: '商户充值-冲正'
                    , type: 1
                    , area: ['600px', '400px']
                    , content: $('#RechargeForm')
                });
            }
        }

    </script>
    <script type="text/html" id="parkIdTemp">
        {{# layui.each(parkList,function(index,item){ }}
        {{# if(item.id===d.parkId){ }}
        {{ item.parkName }}
        {{# return; }}
        {{# }  }}
        {{#  }); }}
    </script>
    <script type="text/html" id="busineStatusTemp">
        {{# layui.each(busineStatusList,function(index,item){ }}
        {{# if(item.id===d.busineStatus){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="discountTypeTemp">
        {{# layui.each(discountTypeList,function(index,item){ }}
        {{# if(item.id===d.discountType){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <#--    替换电子券类型 0代表金额 1代表时间-->
    <script type="text/html" id="ticketTypeTemp">
        {{# if(d.ticketType===0){ }}
        {{ '金额' }}
        {{# }if(d.ticketType===1){ }}
        {{ '时间' }}
        {{# } }}
    </script>
    <#--    <script type="text/html" id="busineTypeTemp">
            {{# layui.each(busineTypeList,function(index,item){ }}
            {{# if(item.id===d.busineType){ }}
            {{ item.name }}
            {{# return; }}
            {{# } }}
            {{#  }); }}
        </script>-->
    <script type="text/html" id="permissionDayTemp">
        {{# if(d.permissionDay==0){ }}
        {{ '一次性劵' }}
        {{# }else{ }}
        {{ d.permissionDay+'天有效' }}
        {{# } }}
    </script>
    <script type="text/html" id="ticketValueTemp">
        {{# if(d.ticketType==0){ }}
        {{ d.ticketValue+'元' }}
        {{# }else if(d.ticketType==1){ }}
        {{ d.ticketValue+'分钟' }}
        {{# } }}
    </script>
    <script type="text/html" id="busineToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="busineAdd">添加商户</button>
        </div>
    </script>
    <script type="text/html" id="busineBar">
        <a class="layui-btn layui-btn-xs layui-btn-normal" lay-event="recharge">充值-冲正</a>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="eleAuthorize">授权电子券批次</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="changePassword">修改密码</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="resetPassword">密码重置</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    </script>
</head>
<body>
<blockquote class="layui-elem-quote">商户管理</blockquote>
<form class="layui-form" style="margin: 10px">
    <div class="layui-form-item">
        <div class="layui-inline">
            <#-- <label class="layui-form-label">所属车场</label>
             <div class="layui-input-inline" style="width: 200px;">
                 <select id="parkIdSelect" name="parkId" lay-filter="parkId">
                 </select>
             </div>-->
            <label class="layui-form-label">商户名称</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="busineName" placeholder="商户名称" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn" lay-submit lay-filter="SelectBusine">查询</button>
            </div>
        </div>
    </div>
</form>
<table id="busineTable" lay-filter="busineTable"></table>
</body>
<form class="layui-form" style="display: none" id="BusineForm" lay-filter="BusineForm">
    <div class="layui-form-item" style="display: none">
        <div class="layui-inline">
            <label class="layui-form-label">ID</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="id" placeholder="ID" autocomplete="off" class="layui-input">
                <input type="text" name="password" placeholder="password" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商户名称</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="busineName" lay-verify="required" placeholder="商户名称" autocomplete="off"
                       class="layui-input">
            </div>
            <label class="layui-form-label">商户电话</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="phone" placeholder="商户电话" autocomplete="off"
                       class="layui-input" lay-verify="required|validatePhone">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">账户名称</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="account" lay-verify="required" placeholder="账户名称" autocomplete="off"
                       class="layui-input">
            </div>
            <label class="layui-form-label">商户地址</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="address" lay-verify="required" placeholder="商户地址" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="mobile" placeholder="手机" autocomplete="off"
                       class="layui-input" lay-verify="required|validatePhone">
            </div>
            <#--            <label class="layui-form-label">商户类别</label>
                        <div class="layui-input-inline" style="width: 150px;">
                            <select id="busineType" name="busineType" lay-filter="busineType">
                            </select>
                        </div>-->
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">最高优惠金额</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="discountAmount" id="discountAmount" lay-verify="required" placeholder="最高优惠金额"
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">是否全免</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="checkbox" id="isfree" name="isfree" lay-filter="isfree" lay-skin="switch" lay-text="是|否"
                       value="1">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">负责人</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="principal" lay-verify="required" placeholder="负责人" autocomplete="off"
                       class="layui-input">
            </div>
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline" style="width: 150px;">
                <select id="busineStatus" name="busineStatus" lay-filter="busineStatus">
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">优惠方式</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select id="discountType" name="discountType" lay-filter="discountType">
                    <option value="0">限制金额</option>
                    <option value="1">时间</option>
                    <option value="2">固定金额</option>
                    <option value="3">电子券</option>
                </select>
            </div>
            <label class="layui-form-label">月初始金额</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input type="text" name="initialAmount" id="initialAmount" required placeholder="月初始金额"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">固定金额</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="fixedAmount" id="fixedAmount" disabled="disabled" lay-verify="chu"
                       style="background-color: #cccccc" required placeholder="固定金额" autocomplete="off"
                       class="layui-input">
            </div>
            <label class="layui-form-label" style="width: 120px">是否月初充值</label>
            <div class="layui-input-inline" style="width: 50px;">
                <input type="checkbox" id="isAuto" name="isAuto" lay-filter="isAuto" lay-skin="switch" lay-text="是|否"
                       value="1">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="busineUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<form class="layui-form" style="display: none" id="RechargeForm" lay-filter="RechargeForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">商户ID</label>
        <div class="layui-input-block">
            <input type="text" name="id" placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <#-- <div class="layui-form-item">
         <label class="layui-form-label">所属停车场</label>
         <div class="layui-input-block">
             <select id="parkId" name="parkId" lay-filter="parkId">
             </select>
         </div>
     </div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">商户名称</label>
        <div class="layui-input-block">
            <input type="text" name="busineName" required lay-verify="required" placeholder="商户名称" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">充值金额</label>
        <div class="layui-input-block">
            <input type="text" name="needPay" required lay-verify="required" placeholder="充值金额" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">实收金额</label>
        <div class="layui-input-block">
            <input type="text" name="actualPay" required disabled lay-verify="required" placeholder="实收金额"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="busineCorrect">充正</button>
            <button class="layui-btn" lay-submit lay-filter="busineRecharge">充值</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<form class="layui-form" id="changePassword" lay-filter="changePassword" style="display: none">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="idChange" placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">原密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" required lay-verify="required" placeholder="原密码" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-block">
            <input type="password" name="newPassword" required lay-verify="required" placeholder="请输入新密码"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">重复密码</label>
        <div class="layui-input-block">
            <input type="password" name="resetPassword" required lay-verify="required" placeholder="请再次输入密码"
                   autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="changePassworeparkingdbtn">修改</button>
        </div>
    </div>
</form>
<#--购买电子券-->
<form class="layui-form" id="eleBuyForm" lay-filter="eleBuyForm" style="display: none">
    <table id="showEletichet" lay-filter="showEletichet"></table>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">每日使用限额</label>
        <div class="layui-input-inline">
            <input type="text" name="limitDay" lay-verify="number" autocomplete="off" class="layui-input" value="0">
        </div>
        <label class="layui-form-label" style="width: 100px">每月使用限额</label>
        <div class="layui-input-inline">
            <input type="text" name="limitMonth" lay-verify="number" autocomplete="off" class="layui-input" value="0">
        </div>

    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">每年使用限额</label>
        <div class="layui-input-inline">
            <input type="text" name="limitYear" lay-verify="number" autocomplete="off" class="layui-input" value="0">
        </div>
        <label class="layui-form-label" style="width: 100px">电子券到期时间</label>
        <div class="layui-input-inline">
            <input type="text" name="expiryDate" id="expiryDate" autocomplete="off" class="layui-input"
                   lay-verify="required|checkDate">
        </div>
    </div>

    <#--<div class="layui-form-item" >
        <label class="layui-form-label"style="width: 100px">购买数量</label>
        <div class="layui-input-inline">
            <input type="text" name="ticketNum"  lay-verify="number"  autocomplete="off" class="layui-input" value="0">
        </div>
        <label class="layui-form-label">应付</label>
        <div class="layui-input-inline">
            <input type="text" name="needPay" disabled autocomplete="off" class="layui-input">
        </div>
    </div>-->
    <label class="layui-form-label" style="width: 150px">是否允许商户自购</label>
    <div class="layui-input-block">
        <input type="radio" name="isPay" value="1" title="允许">
        <input type="radio" name="isPay" value="0" title="不允许" checked="">
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="eleBuyBtn">确认授权</button>
        </div>
    </div>
</form>
</html>