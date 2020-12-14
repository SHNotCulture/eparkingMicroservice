<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>在场车辆</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <style type="text/css">
        #carNum {
            height: 80px;
            border-radius: 5px;
        }

        ul {
            display: flex;
            justify-content: space-around;
            overflow: hidden;
        }

        ul li {
            margin-top: 8px;
        }

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

        .Btn {
            display: flex;
            justify-content: space-around;
            overflow: hidden;
        }

        .Btn button {
            width: 100px;
            height: 30px;
            margin: 10px 15px 10px 15px;
            text-align: center;
            color: white;
            font-weight: bold;
            font-size: 18px;
            border-radius: 3px;
            border: none;
            cursor: pointer;
        }

        /*.carStyle{*/
        /*display: flex;*/
        /*justify-content:space-around;*/
        /*}*/
    </style>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table', 'form', 'layer', 'laydate'], function () {
            var table = layui.table;
            var form = layui.form;
            var layer = layui.layer;
            var laydate = layui.laydate;
            var laydate1 = layui.laydate;
            var isUpdateCarplate='off';

            laydate.render({
                elem: '#date'
                , type: 'datetime'
            });
            laydate1.render({
                elem: '#dateEnd'
                , type: 'datetime'
            });
            //第一个实例
            tableIns = table.render({
                elem: '#parkinoutTable'
                , height: 520
                , url: '../Present/getPresentCarbyPage' //数据接口
                , method: 'post'
                , toolbar: '#PresentCarToolbar'
                , defaultToolbar: ['filter', 'print']
                , loading: true
                , title: '在场车辆信息'
                , page: true //开启分页
                , cols: [[ //表头
                    {type: 'numbers', title: '序号', width: 50, fixed: 'left'}
                    , {field: 'inCarPlate', title: '车牌',align: 'center', width: 110}
                    , {field: 'carNatureDesc', title: '车辆性质',align: 'left', width: 130}
                    , {field: 'inPortName', title: '进入通道', width: 110, align: 'center'}
                    , {field: 'inTime', title: '进场时间', width: 165, align: 'center', sort: true}
                    , {field: 'stopTime', title: '停车时长', width: 165, align: 'center', sort: true}
                    , {fixed: 'right', title: '操作', align: 'center', width: 90, toolbar: '#showPhoto'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(parkinoutTable)', function (obj) {
                switch (obj.event) {
                    case 'PresentCarExcel':
/*                        var radios = document.getElementsByName("carNature");
                        var value = 0;
                        for (var i = 0; i < radios.length; i++) {
                            if (radios[i].checked == true) {
                                value = radios[i].value;
                            }
                        }

                        var carNature = value;*/
                        var radiosn = document.getElementsByName("timeType");
                        var valuen = 0;
                        for (var i = 0; i < radiosn.length; i++) {
                            if (radiosn[i].checked == true) {
                                valuen = radiosn[i].value;
                            }
                        }
                        var timeType = valuen;
                        var inPortId = $('#PortName option:selected').val();
                        var inCarPlate = $('#inCarPlate').val();
                        var inTime = $('#date').val();
                        var outTime = $('#dateEnd').val();
                        // var isUpdateCarplate = $('#isUpdateCarplate').val();
                        // console.log("q" + isUpdateCarplate);
                        //var parkingState=$("#parkingState").val();
                        var data = {
                            // carNature: carNature,
                            timeType: timeType,
                            inPortId: inPortId,
                            inCarPlate: inCarPlate,
                            inTime: inTime,
                            outTime: outTime,
                            isUpdateCarplate: isUpdateCarplate
                        };
                        PresentCarForm.PresentCarExcel(data);
                        break;
                    default:
                }

            });
            //监听开关
            form.on('switch(isUpdateCarplate)', function(data){
                if(data.elem.checked==false){
                    data.value="off";
                }
                isUpdateCarplate=data.value;
/*                console.log(data.elem); //得到checkbox原始DOM对象
                console.log(data.elem.checked); //开关是否开启，true或者false
                console.log(data.value); //开关value值，也可以通过data.elem.value得到
                console.log(data.othis); //得到美化后的DOM对象*/
            });

            //监听工具条
            table.on('tool(parkinoutTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                /*console.log(data);*/
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if (layEvent === 'show') { //查看
                    /*showPhotohandle.Show(data.inPicPath);*/
                } else if (layEvent === 'detail') { //查看详情
                    if (data.updatedincarplate === null) {
                        data.updatedincarplate = "无修改记录";
                    }
                    form.val("RecordForm", data);
                    $("#inPicPath").attr('src', "http://eparking2.oss-cn-shenzhen.aliyuncs.com" + data.inPicPath);
                    //showPhotohandle.showCarNature();
                    showPhotohandle.ShowDetail();//弹出查看详情
                }
            });

            //监听查询按钮
            form.on('submit(SelectParkinout)', function (data) {
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                PresentCarhandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听临时车快速查询
            form.on('submit(SelectTemporary)', function () {
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                PresentCarhandle.selectTemporary();
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听月租车快速查询
            form.on('submit(SelectMonthly)', function () {
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                PresentCarhandle.selectMonthly();
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听私家车快速查询
            form.on('submit(SelectPrivate)', function () {
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                PresentCarhandle.selectPrivate();
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            // PresentCarForm.getPortName();
            PresentCarForm.getStatus();
            PresentCarForm.getbusineType();
            PresentCarForm.getPortName(form);
            PresentCarForm.getCarPayRule(form);
            form.render(); //更新全部
            // form.render('checkbox');
        });
        //获取圆圈中的数据

        var carNatureList = [{id: 1, name: '会员临停'}, {id: 2, name: '月租车'}, {id: 3, name: '临时车'}, {
            id: 4,
            name: '月租绑定车'
        }, {id: 5, name: '月租暂停车'}, {id: 6, name: '月租过期车'}, {id: 7, name: '月租无通道权限'}];
        var showPhotohandle = {
            //显示车主性质中文
            showCarNature: function () {
                var carn = $("#carNaturen").val();
                carNatureList.forEach(function (value, index) {
                    if (carn == value.id) {
                        $("#showCarNature").val(value.name);
                    }
                })

            },
            ShowDetail: function () {
                layer.open({
                    title: '查看详情'
                    , type: 1
                    , area: ['800px', '600px']
                    , content: $('#RecordForm')
                    , resize: false
                });
            },
            /*Show:function(inPicPath){
                document.getElementById("showp").style.display="block";
                document.getElementById("showp").style.background="url(http://eparking.oss-cn-shenzhen.aliyuncs.com"+inPicPath+") no-repeat";
            }*/
        };

        function hidePhoto() {
            document.getElementById("showp").style.display = "none";
        };

        //在页面加载完成后执行
        $(document).ready(function () {

        })
        //车场增删查改方法
        var PresentCarhandle = {

            select: function (obj) {
                //这里以搜索为例
                tableIns.reload({
                    where:
                    obj,
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            selectTemporary: function () {
                //这里以搜索为例
                tableIns.reload({
                    where:
                        { //设定异步数据接口的额外参数，任意设
                            CarNature: 3
                        }
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            selectMonthly: function () {
                //这里以搜索为例
                tableIns.reload({
                    where:
                        { //设定异步数据接口的额外参数，任意设
                            CarNature: 2
                        }
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            selectPrivate: function () {
                //这里以搜索为例
                tableIns.reload({
                    where:
                        { //设定异步数据接口的额外参数，任意设
                            CarNature: 1
                        }
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },

        };
        var busineStatusList, busineTypeList;
        //车场更新表单使用方法
        var PresentCarForm = {
            clean: function () {
                $("#PresentCarForm")[0].reset();
            },
            //获取通道
            getPortName: function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../BaseDic/getPortName?portType=0",
                    dataType: "JSON",
                    success: function (result) {
                        if (result.code == 0) {
                            /*console.log(result);*/
                            portList = result.result;
                            var str = " <option value>请选择通道名称</option>";
                            portList.forEach(function (t) {
                                str += "<option value=" + t.portId + ">" + t.portName + "</option>"
                            });
                            $("select[id='PortName']").html(str);
                        }
                        form.render();
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
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
            PresentCarExcel: function (object) {
                // console.log(object);
                window.location.href = "../Base/exportListPresentCar?timeType=" + object.timeType  + "&inCarPlate=" + object.inCarPlate + "&inPortId=" + object.inPortId + "&inTime=" + object.inTime + "&outTime=" + object.outTime + "&isUpdateCarplate=" + object.isUpdateCarplate;
            },
            getCarPayRule:function (form) {
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
            }
        };

    </script>

    <script type="text/html" id="showPhoto">
        <#--<a class="layui-btn layui-btn-xs" lay-event="show">查看</a>-->
        <a class="layui-btn layui-btn-xs" lay-event="detail">查看详情</a>
    </script>

    <script type="text/html" id="busineTypeTemp">
        {{# layui.each(busineTypeList,function(index,item){ }}
        {{# if(item.id===d.busineType){ }}
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
    <script type="text/html" id="carNatureTemp">
        {{# if(d.carNature===1){ }}
        {{ '月租车' }}
        {{# }if(d.carNature===2){ }}
        {{ '车场充值车' }}
        {{# }if(d.carNature===3){ }}
        {{ '按临停缴费车' }}
        {{# }if(d.carNature===4){ }}
        {{ '黑名单车' }}
        {{# }if(d.carNature===100){ }}
        {{ '临时车' }}
        {{# }if(d.carNature===101){ }}
        {{ '特殊车' }}
        {{# } }}
    </script>
    <script type="text/html" id="PresentCarToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="PresentCarExcel">导出在场车辆信息</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">在场车辆</blockquote>
<#--<div id="carNum">
    <ul>
        <li>
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>临时车</span>
                </div>
                <span class="cNum">${cNumTemporary}</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #FBC88C;">
                <div class="carL" style="background:#FBC88C;">
                    <span>月租车</span>
                </div>
                <span class="cNum">${cNumMonthly}</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #FA5D8C;">
                <div class="carL" style="background:#FA5D8C;">
                    <span>私家车</span>
                </div>
                <span class="cNum">${cNumPrivate}</span>
            </div>
        </li>
        &lt;#&ndash;<li>
            <div class="carStyle" style="border:2px solid #02AF9E;">
                <div class="carL" style="background:#02AF9E;">
                    <span style="margin:2px 0 0 8px;">剩余车位</span>
                </div>
                <span class="cNum" style="position:relative;bottom:15px;">${cNumRemaining}</span>
            </div>
        </li>&ndash;&gt;
    </ul>
</div>-->
<form class="layui-form" style="margin: 10px" id="PresentCarForm" lay-filter="PresentCarForm">
    <div class="layui-form-item">
        <div class="layui-inline" id="timeType">
            <label class="layui-form-label">停车时长</label>
            <div class="layui-input-inline" style="width: 80px;">
                <input type="radio" name="timeType" value="all" title="全部" checked>
            </div>
            <div class="layui-input-inline" style="width: 110px;">
                <input type="radio" name="timeType" value="stageOne" title="0-30分钟">
            </div>
            <div class="layui-input-inline" style="width: 135px;">
                <input type="radio" name="timeType" value="stageTwo" title="30分钟-1小时">
            </div>
            <div class="layui-input-inline" style="width: 120px;">
                <input type="radio" name="timeType" value="stageThree" title="1小时-1天">
            </div>
            <div class="layui-input-inline" style="width: 110px;">
                <input type="radio" name="timeType" value="stageFour" title="1天-7天">
            </div>
            <div class="layui-input-inline" style="width: 110px;">
                <input type="radio" name="timeType" value="stageFive" title="大于7天">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">通道</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select id="PortName" name="inPortId" lay-filter="PortName">
                </select>
            </div>
            <label class="layui-form-label">进场时间</label>
            <div class="layui-input-inline"  style="width: 200px">
                <input type="text" name="inTime" id="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off"
                       class="layui-input">
            </div>
            <label class="layui-form-label" style="width: 30px">至</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="outTime" id="dateEnd" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off"
                       class="layui-input">
            </div>

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">车牌</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" id="inCarPlate" name="inCarPlate" placeholder="可模糊搜索" autocomplete="off"
                       class="layui-input">
            </div>
            <label class="layui-form-label">车辆性质</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" id="carNatureDesc" name="carNatureDesc" placeholder="车辆性质" autocomplete="off" class="layui-input">
<#--                <select id="carNatureDesc" name="carNatureDesc"  lay-filter="carNatureDesc" lay-search>
                </select>-->
            </div>
            <label class="layui-form-label" style="width: 120px">是否修改过车牌</label>
            <div class="layui-input-inline" style="width: 50px;">
                <input type="checkbox" id="isUpdateCarplate" name="isUpdateCarplate" lay-filter="isUpdateCarplate" lay-skin="switch" lay-text="是|否"
                       value="on">
            </div>
            <div class="layui-input-inline" style="width: 200px;margin-left: 100px">
                <button class="layui-btn" lay-submit lay-filter="SelectParkinout" id="SelectParkinout">查询</button>
            </div>
        </div>
    </div>
</form>
<table id="parkinoutTable" lay-filter="parkinoutTable"></table>
<#--<div id="showp" style="display: none;border:1px ;width:500px;height:380px;position:absolute;left:50%;margin-left:-250px;top:50%; z-index:999;margin-top:-190px" onclick="hidePhoto()" >-->
</div>
<form class="layui-form " style="display: none" id="RecordForm" lay-filter="RecordForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">车牌：</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="inCarPlate" disabled
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">车牌颜色：</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="inCarPlateColor" disabled
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">车辆性质：</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text"    id="carNatureDesc" name="carNatureDesc"  autocomplete="off" class="layui-input" disabled>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">车牌修改记录：</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="updatedincarplate"
                       autocomplete="off" class="layui-input" readonly>
            </div>
            <label class="layui-form-label">进场通道：</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="inPortName" disabled
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">进场时间：</label>
            <div class="layui-input-inline" style="width: 150px;">
                <input style="border: 0px;outline:none;cursor: default;" type="text" name="inTime" disabled
                       autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>入场车辆图片</legend>
        <div class="layui-field-box">
            <img id="inPicPath" onerror="this.src='../img/error.png'"
                 style="width: 384px;height: 300px;margin-left: 100px;repeat;background-size:contain">
        </div>
    </fieldset>

</form>
</body>
</html>