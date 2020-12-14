<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>临停规则管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <style type="text/css">
        .layui-laydate-content>.layui-laydate-list {
            padding-bottom: 0px;
            overflow: hidden;
        }
        .layui-laydate-content>.layui-laydate-list>li{
            width:50%
        }

        .merge-box .scrollbox .merge-list {
            padding-bottom: 5px;
        }

    </style>
    <script type="text/javascript">
        $(function () {
            $("input[name='freeTime']").bind('input propertychange',function () {
                $("input[name='freeTime']").val($(this).val());
            });
            $("input[name='baseTime1']").bind('input propertychange',function () {
                $("input[name='baseTime1']").val($(this).val());
            });
            $("input[name='baseTime2']").bind('input propertychange',function () {
                $("input[name='baseTime2']").val($(this).val());
            });
            $("input[name='baseTime3']").bind('input propertychange',function () {
                $("input[name='baseTime3']").val($(this).val());
            });
            $("input[name='unitTime']").bind('input propertychange',function () {
                $("input[name='unitTime']").val($(this).val());
            });
        });
        var tableIns;
        layui.use(['table','form','layer','laydate','element'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;
            var laydate = layui.laydate;

            laydate.render({
                elem: '#timesecStart'
                , trigger: 'click'
                , type: 'time'
                , format: 'HH:mm'
                , btns: ['clear', 'confirm']
                , ready: formatminutes
            });
            laydate.render({
                elem: '#timesecEnd'
                , trigger: 'click'
                , type: 'time'
                , format: 'HH:mm'
                , btns: ['clear', 'confirm']
                , ready: formatminutes
            });
            function  formatminutes(date) {
                var aa = $(".laydate-time-list li ol")[1];
                var showtime = $($(".laydate-time-list li ol")[1]).find("li");
                for (var i = 0; i < showtime.length; i++) {
                    var t00 = showtime[i].innerText;
                    if (t00 != "00" && t00 != "20" && t00 != "30" && t00 != "40" && t00 != "50") {
                        showtime[i].remove()
                    }
                }
                $($(".laydate-time-list li ol")[2]).find("li").remove();  //清空秒
            }

            tableIns=table.render({
                elem: '#chargeRuleTable'
                ,height: 600
                ,url: '../chargeRule/getChargeRulebyPage' //数据接口
                ,method:'post'
                ,toolbar: '#toolbarChargeRule'
                ,defaultToolbar: ['filter', 'print']
                ,page: true //开启分页
                ,cols: [[ //表头
                    //{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'},
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'isHolidayUse', title: '规则类型', width:120,templet:'#IsHolidayUseTemp'}
                    ,{field: 'carTypeId', title: '车型', width: 120,templet:'#CarTypeTemp'}
                    ,{field: 'timesecStart', title: '起始自然时间', width:120}
                    ,{field: 'timesecEnd', title: '终止自然时间', width:120}
                    ,{field: 'maxDay1Fee', title: '本段最高收费', width: 120}
                    ,{field: 'isMaxSectimeFeeByAdd', title: '段内多次累计最高收费', width: 120,templet:'#isMaxSectimeFeeByAddTemp'}
                    ,{field: 'freeTime', title: '时段1', width: 120}
                    ,{field: 'baseTime1', title: '时段2', width: 120}
                    ,{field: 'baseTime2', title: '时段3', width: 120}
                    ,{field: 'baseTime3', title: '时段4', width: 120}
                    ,{field: 'unit1Time', title:'循环段1', width: 120}
                    ,{field: 'unit2Time', title:'循环段2', width: 120}
                    ,{field: 'unit3Time', title:'循环段3', width: 120}
                    ,{field: 'h24CalMode', title:'24小时后计费规则', width: 120,templet:'#h24CalModeTemp'}
                    ,{field: 'maxDay1Fee', title: '首24H最高', width: 120}
                    ,{field: 'maxDaynFee', title: '后24H最高', width: 120}
                    ,{field: 'isMax24FeeByAdd', title: '24H多次累积', width: 120}
                    //24h起点时间
                    ,{fixed: 'right', title: '操作', width: 200, toolbar: '#ChargeRuleBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(chargeRuleTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'ChargeRuleAdd':
                        var data = checkStatus.data;
                        ChargeRulehandle.Update(0);
                        break;
                    case 'holidaySet':
                        ChargeRulehandle.holidayAlter();
                                break;
                    default:
                };
            });
            //监听工具条
            table.on('tool(chargeRuleTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('是否删除', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        ChargeRulehandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("ChargeRuleForm", data);
                    // console.log(data);
                    ChargeRulehandle.Update(1);//弹出车场更新窗口
                }
            });
            //监听临停规则更新按钮
            form.on('submit(ChargeRuleUpdate)', function(data){
               /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                ChargeRulehandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });

            form.render(); //更新全部
        });
        $(document).ready(function () {
            ChargeRuleForm.getCarTypeList();
            ChargeRuleForm.getIsHolidayUseList();
            ChargeRuleForm.geth24RuleList();
            ChargeRuleForm.getisMaxSectimeFeeByAddList();
            ChargeRuleForm.geth24CalModeList();
        })
        //临停规则增删查改方法
        var  ChargeRulehandle={
            Update:function (type) {
                if(type==0){//新增
                    ChargeRuleForm.clean();
                }
                layer.open({
                    title: '更新临停规则'
                    , type: 1
                    ,area:['900px','900px']
                    ,content: $('#ChargeRuleForm')
                });
            },
            holidayAlter:function () {
                layer.open({
                    title:'节假日设置 '
                    , type: 2
                    ,area:['1000px','480px']
                    ,content: '../view/holiday'
                });
            },
            select:function (data) {
                //这里以搜索为例
                tableIns.reload({
                    where: data
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            } ,
            save:function(object){
                $.ajax({
                    type: 'POST',
                    url: "../chargeRule/updateChargeRule",
                    data:object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if(result.code==0)
                        {
                            layer.closeAll();
                            layer.msg(result.result);
                            tableIns.reload();

                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
            },
            delete:function (id) {
                $.ajax({
                    type: 'POST',
                    url: "../chargeRule/deleteChargeRule",
                    data:{'id':id},
                    dataType: "JSON",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if(result.code==0)
                        {
                            layer.msg(result.result);
                            tableIns.reload();
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
            }
        };
        var parkList,carTypeList,isHolidayUseList,h24RuleList,isMaxSectimeFeeByAddList,h24CalModeList;
        //车场更新表单使用方法
        var ChargeRuleForm={
            clean:function(){
                $("#ChargeRuleForm")[0].reset();
            },
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
                            parkList= result.data;
                            var str=" <option value>请选择停车场</option>";
                            parkList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.parkName+"</option>"
                            });
                            $("select[name='parkId']").html(str);
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
            //获取车辆类型
            getCarTypeList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getCarTypeList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            carTypeList=result.result;
                            var str=" <option value>请选择</option>";
                            carTypeList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("select[name='carTypeId']").html(str);
                        }

                    },
                    error: function (result) {
                        var str="<script type=\"text/javascript\">";
                        var beginNum=result.responseText.indexOf(str)+str.length;
                        var endNum=result.responseText.length-9;
                        var action=result.responseText.substring(beginNum,endNum);
                        eval(action);
                    }
                });

            },
            //获取假日规则
            getIsHolidayUseList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getIsHolidayUseList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            isHolidayUseList=result.result;
                            var str=" <option value>请选择</option>";
                            isHolidayUseList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("select[name='parkingSpace']").html(str);
                        }

                    },
                    error: function (result) {
                        var str="<script type=\"text/javascript\">";
                        var beginNum=result.responseText.indexOf(str)+str.length;
                        var endNum=result.responseText.length-9;
                        var action=result.responseText.substring(beginNum,endNum);
                        eval(action);
                    }
                });

            },
            geth24RuleList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getH24RuleList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            h24RuleList=result.result;
                            var str=" <option value>请选择</option>";
                            h24RuleList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("select[name='h24Rule']").html(str);
                        }

                    },
                    error: function (result) {
                        var str="<script type=\"text/javascript\">";
                        var beginNum=result.responseText.indexOf(str)+str.length;
                        var endNum=result.responseText.length-9;
                        var action=result.responseText.substring(beginNum,endNum);
                        eval(action);
                    }
                });

            },
            getisMaxSectimeFeeByAddList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getIsMaxSectimeFeeByAddList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            isMaxSectimeFeeByAddList=result.result;
                            var str=" <option value>请选择</option>";
                            isMaxSectimeFeeByAddList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                        }
                    },
                    error: function (result) {
                        var str="<script type=\"text/javascript\">";
                        var beginNum=result.responseText.indexOf(str)+str.length;
                        var endNum=result.responseText.length-9;
                        var action=result.responseText.substring(beginNum,endNum);
                        eval(action);
                    }
                });
            },
            geth24CalModeList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/geth24CalModeList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            h24CalModeList=result.result;
                            var str=" <option value>请选择</option>";
                            h24CalModeList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                        }
                    },
                    error: function (result) {
                        var str="<script type=\"text/javascript\">";
                        var beginNum=result.responseText.indexOf(str)+str.length;
                        var endNum=result.responseText.length-9;
                        var action=result.responseText.substring(beginNum,endNum);
                        eval(action);
                    }
                });
            }
        }
    </script>
    <script type="text/html" id="IsHolidayUseTemp">
        {{# layui.each(isHolidayUseList,function(index,item){ }}
        {{# if(item.id===d.isHolidayUse){ }}
        {{ item.name }}
        {{# return; }}
        {{# }  }}
        {{#  }); }}
    </script>
    <script type="text/html" id="CarTypeTemp">
        {{# layui.each(carTypeList,function(index,item){ }}
        {{# if(item.id===d.carTypeId){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="isMaxSectimeFeeByAddTemp">
        {{# layui.each(isMaxSectimeFeeByAddList,function(index,item){ }}
        {{# if(item.id===d.isMaxSectimeFeeByAdd){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="h24CalModeTemp">
        {{# layui.each(h24CalModeList,function(index,item){ }}
        {{# if(item.id===d.h24CalMode){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="toolbarChargeRule">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="ChargeRuleAdd">添加收费标准</button>
            <button class="layui-btn layui-btn-sm" lay-event="holidaySet">设置节假日</button>
        </div>
    </script>
    <script type="text/html" id="ChargeRuleBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

</head>
<body>
<blockquote class="layui-elem-quote">临停规则管理</blockquote>
<table id="chargeRuleTable" lay-filter="chargeRuleTable"></table>
</body>
<form class="layui-form layui-form-pane"  style="display: none" id="ChargeRuleForm" lay-filter="ChargeRuleForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 140px;text-align: left;margin-left: 5px;margin-top: 5px">节假日收费类型</label>
            <div class="layui-input-inline" style="margin-top: 5px">
                <input type="checkbox" name="isHolidayUse" value="1" lay-skin="switch" lay-text="是|否">
            </div>
        </div>
<#--        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">节假日收费类型</label>
            <div class="layui-input-inline" style="width: 100px;">
            <input type="checkbox" name="isHolidayUse" value="1" lay-skin="switch" lay-text="是|否">
            </div>
        </div>-->
        <div class="layui-inline">
            <label class="layui-form-label" style="margin-left: 5px;margin-top: 5px">车型</label>
            <div class="layui-input-inline" style="width: 100px;margin-top: 5px">
                <select name="carTypeId" lay-verify="required">
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 140px;text-align: left;margin-left: 5px;margin-top: 5px">自然时间段</label>
            <div class="layui-input-inline" style="width: 100px;margin-left: 5px;margin-top: 5px">
<#--                <input type="text" class="layui-input" id="timesecStart" name="timesecStart">-->
                <input type="text" class="layui-input" id="timesecStart" name="timesecStart" placeholder="HH:mm">
            </div>
            <div class="layui-form-mid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div class="layui-input-inline" style="width: 100px;margin-left: 5px;margin-top: 5px">
<#--                <input type="text" class="layui-input" id="timesecEnd" name="timesecEnd">-->
                <input type="text" class="layui-input" id="timesecEnd" name="timesecEnd" placeholder="HH:mm">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 120px;margin-left: 5px;margin-top: 5px">24小时规则</label>
                <div class="layui-input-inline" style="width: 170px;margin-top: 5px">
                    <select name="h24Rule" lay-verify="required">
                    </select>
                </div>
            </div>
    </div>
    <div class="layui-collapse">
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">基本段</h2>
        <div class="layui-colla-content layui-show">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" value="0" disabled class="layui-input" >
                    </div>
                    <div class="layui-form-mid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="freeTime"  class="layui-input" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">分钟  </div>
                    <label class="layui-form-label" style="width: 100px;">收费</label>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="freeTimeFee"  class="layui-input" lay-verify="number">
                    </div>
                    <label class="layui-form-label" style="width: 50px;">元</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"  disabled name="freeTime" class="layui-input" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="baseTime1"class="layui-input" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">分钟  </div>
                    <label class="layui-form-label" style="width: 100px;">收费</label>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"  name="baseTime1Fee" class="layui-input" lay-verify="number">
                    </div>
                    <label class="layui-form-label" style="width: 50px;">元</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" disabled name="baseTime1" class="layui-input" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"   name="baseTime2"class="layui-input" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">分钟  </div>
                    <label class="layui-form-label" style="width: 100px;">收费</label>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"  name="baseTime2Fee" class="layui-input" lay-verify="number">
                    </div>
                    <label class="layui-form-label" style="width: 50px;">元</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" disabled name="baseTime2" class="layui-input" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"   name="baseTime3"class="layui-input" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">分钟  </div>
                    <label class="layui-form-label" style="width: 100px;">收费</label>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"  name="baseTime3Fee" class="layui-input" lay-verify="number">
                    </div>
                    <label class="layui-form-label" style="width: 50px;">元</label>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" disabled name="baseTime3" class="layui-input" >
                    </div>
                    <div class="layui-form-mid">分钟过后每</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="unitTime"  class="layui-input" >
                    </div>
                    <div class="layui-form-mid">分钟  </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px;">收费</label>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="unitTimeFee" class="layui-input" >
                    </div>
                    <label class="layui-form-label" style="width: 50px;">元</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">（超过</label>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="surpassTime" class="layui-input" >
                    </div>
                    <div class="layui-form-mid">分钟按</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"  disabled name="unitTime" class="layui-input" >
                    </div>
                    <label class="layui-form-label">分钟计算）</label>
                </div>
            </div>
 <#--           <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" disabled name="baseTime2" class="layui-input" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">分钟过后每</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="unitTime"  class="layui-input" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">分钟  </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px;">收费</label>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="unitTimeFee" class="layui-input" lay-verify="number">
                    </div>
                    <label class="layui-form-label" style="width: 50px;">元</label>
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">（超过</label>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="surpassTime" class="layui-input" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">分钟按</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"  disabled name="unitTime" class="layui-input" lay-verify="number">
                    </div>
                    <label class="layui-form-label">分钟计算）</label>
                </div>
            </div>-->
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">循环段1</h2>
    <div class="layui-colla-content layui-show">
        <div class="layui-form-item">
            <div class="layui-form-mid">段总时长</div>
            <div class="layui-input-inline" style="width: 100px">
                <input type="text" class="layui-input" name="unit1Sectime" lay-verify="number">
            </div>
            <div class="layui-form-mid">分钟(0表示不限时长)</div>
        </div>
        <div class="layui-form-item">
            <div class="layui-form-mid">单位时长</div>
            <div class="layui-input-inline" style="width: 100px">
                <input type="text" class="layui-input" name="unit1Time" lay-verify="number">
            </div>
            <div class="layui-form-mid">分钟</div>
        </div>
        <div class="layui-form-item">
            <div class="layui-form-mid">单位收费</div>
            <div class="layui-input-inline" style="width: 100px">
                <input type="text" class="layui-input" name="unit1TimeFee" lay-verify="number">
            </div>
            <div class="layui-form-mid">元</div>
        </div>
        <div class="layui-form-item">
            <div class="layui-form-mid">最大收费</div>
            <div class="layui-input-inline" style="width: 100px">
                <input type="text" class="layui-input" name="unit1Maxfee" lay-verify="number">
            </div>
            <div class="layui-form-mid">元(0表示本循环段不封顶)</div>
        </div>
    </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">循环段2</h2>
        <div class="layui-colla-content layui-show">
                <div class="layui-form-item">
                    <div class="layui-form-mid">段总时长</div>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" name="unit2Sectime" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">分钟(0表示不限时长)</div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-form-mid">单位时长</div>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" name="unit2Time" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">分钟</div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-form-mid">单位收费</div>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" name="unit2TimeFee" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">元</div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-form-mid">最大收费</div>
                    <div class="layui-input-inline" style="width: 100px">
                        <input type="text" class="layui-input" name="unit2Maxfee" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">元(0表示本循环段不封顶)</div>
                </div>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">循环段3</h2>
        <div class="layui-colla-content layui-show">
            <div class="layui-form-item">
                <div class="layui-form-mid">段总时长</div>
                <div class="layui-input-inline" style="width: 100px">
                    <input type="text" class="layui-input" name="unit3Sectime" lay-verify="number">
                </div>
                <div class="layui-form-mid">分钟(0表示不限时长)</div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-mid">单位时长</div>
                <div class="layui-input-inline" style="width: 100px">
                    <input type="text" class="layui-input" name="unit3Time" lay-verify="number">
                </div>
                <div class="layui-form-mid">分钟</div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-mid">单位收费</div>
                <div class="layui-input-inline" style="width: 100px">
                    <input type="text" class="layui-input" name="unit3TimeFee" lay-verify="number">
                </div>
                <div class="layui-form-mid">元</div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-mid">最大收费</div>
                <div class="layui-input-inline" style="width: 100px">
                    <input type="text" class="layui-input" name="unit3Maxfee" lay-verify="number">
                </div>
                <div class="layui-form-mid">元(0表示本循环段不封顶)</div>
            </div>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">最大收费定义</h2>
        <div class="layui-colla-content layui-show">
            <div class="layui-form-item">
            <div class="layui-form-mid" style="width: 300px">本自然段内最高收费(起点按自然时间有效)</div>
            <div class="layui-input-inline" style="width: 100px">
                <input type="text" class="layui-input" name="maxSectimeFee" lay-verify="number">
            </div>
            <div class="layui-form-mid">元(0为无最高收费)</div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-mid" style="width: 300px">本自然段内多次累积最高收费</div>
                <div class="layui-input-inline">
                    <input type="checkbox" name="isMaxSectimeFeeByAdd" lay-skin="switch" lay-text="是|否" value="1">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-mid" style="width: 300px">首24小时最高收费(起点按自然或入场均有效)</div>
                <div class="layui-input-inline" style="width: 100px">
                    <input type="text" class="layui-input" name="maxDay1Fee" lay-verify="number">
                </div>
                <div class="layui-form-mid">元(0为无最高收费)</div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-mid" style="width: 300px">后每24小时最高收费(起点按自然或入场均有效)</div>
                <div class="layui-input-inline" style="width: 100px">
                    <input type="text" class="layui-input" name="maxDaynFee" lay-verify="number">
                </div>
                <div class="layui-form-mid">元(0为无最高收费)</div>
            </div>
            <div class="layui-form-item">
                <div class="layui-form-mid" style="width: 300px">24小时内多次累积最高收费</div>
                <div class="layui-input-inline">
                    <input type="checkbox" name="isMax24FeeByAdd" lay-skin="switch" lay-text="是|否" value="1">
                </div>
            </div>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">24小时后计费规则</h2>
        <div class="layui-colla-content layui-show">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 160px;">
                        <input type="radio" name="h24CalMode" value=1 title="按单位时间计费" checked="">
                    </div>
                    <div class="layui-form-mid">每</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" class="layui-input" name="h24UnitTime" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">分钟收费</div>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="text" class="layui-input" name="h24UnitTimeFee" lay-verify="number">
                    </div>
                    <div class="layui-form-mid">元</div>
                </div>
                <div class="layui-form-item">
                <input type="radio" name="h24CalMode" value=2 title="无免费时间重新计费" checked="">
                </div>
                <div class="layui-form-item">
                <input type="radio" name="h24CalMode" value=3 title="有免费时间重新计费" checked="">
                </div>
            </div>
        </div>
    </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px;margin-left: -100px">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="ChargeRuleUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</html>
   <#-- <fieldset class="layui-elem-field layui-field-title">
        <legend>首24小时内</legend>
        <div class="layui-field-box">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" value="0" disabled class="layui-input" >
                    </div>
                    <div class="layui-form-mid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="freeTime"  class="layui-input" >
                    </div>
                    <div class="layui-form-mid">分钟  </div>
                    <label class="layui-form-label" style="width: 100px;">收费</label>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="freeTimeFee"  class="layui-input" >
                    </div>
                    <label class="layui-form-label" style="width: 50px;">元</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"  disabled name="freeTime" class="layui-input" >
                    </div>
                    <div class="layui-form-mid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="baseTime1"class="layui-input" >
                    </div>
                    <div class="layui-form-mid">分钟  </div>
                    <label class="layui-form-label" style="width: 100px;">收费</label>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"  name="baseTime1Fee" class="layui-input" >
                    </div>
                    <label class="layui-form-label" style="width: 50px;">元</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" disabled name="baseTime1" class="layui-input" >
                    </div>
                    <div class="layui-form-mid">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"   name="baseTime2"class="layui-input" >
                    </div>
                    <div class="layui-form-mid">分钟  </div>
                    <label class="layui-form-label" style="width: 100px;">收费</label>
                </div>
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"  name="baseTime2Fee" class="layui-input" >
                    </div>
                    <label class="layui-form-label" style="width: 50px;">元</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" disabled name="baseTime2" class="layui-input" >
                    </div>
                    <div class="layui-form-mid">分钟过后每</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="unitTime"  class="layui-input" >
                    </div>
                    <div class="layui-form-mid">分钟  </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 100px;">收费</label>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="unitTimeFee" class="layui-input" >
                    </div>
                    <label class="layui-form-label" style="width: 50px;">元</label>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">（超过</label>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="surpassTime" class="layui-input" >
                    </div>
                    <div class="layui-form-mid">分钟按</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text"  disabled name="unitTime" class="layui-input" >
                    </div>
                    <label class="layui-form-label">分钟计算）</label>
                </div>
            </div>
        </div>
    </fieldset>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>24小时后</legend>
        <div class="layui-field-box">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">每</label>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="h24UnitTime" class="layui-input" >
                    </div>
                    <div class="layui-form-mid">分钟收费(为0按首24小时标准)</div>
                    <div class="layui-input-inline" style="width: 50px;">
                        <input type="text" name="h24UnitTimeFee" class="layui-input" >
                    </div>
                    <label class="layui-form-label">元</label>
                </div>
            </div>
        </div>
    </fieldset>
    <fieldset class="layui-elem-field layui-field-title">
        <legend>最高收费定义</legend>
        <div class="layui-field-box">
            <div class="layui-form-item">
               <div class="layui-inline" >
                <label class="layui-form-label" style="width: 200px;">最高收费的起始时间定义</label>
               </div>
               <div class="layui-inline" >
                <div class="layui-input-block" style="margin-left: -14px;">
                    <select name="h24Rule" lay-verify="required" >
                    </select>
                </div>
               </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 200px">段内最高收费(0位无最高收费)</label>
                <div class="layui-input-block">
                    <input type="text" name="maxSectimeFee" required  style="width: 100px" lay-verify="required" placeholder="段内最高收费" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 200px">段内多次累计最高收费</label>
                <div class="layui-input-block"  >
                    <input type="checkbox" name="isMaxSectimeFeeByAdd" lay-skin="switch" value="1" style="width: 100px">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"style="width: 200px">首24小时最高收费(0位无最高收费)</label>
                <div class="layui-input-block" >
                    <input type="text" name="maxDay1Fee" style="width: 100px" required  lay-verify="required" placeholder="首24小时最高收费" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label"style="width: 200px">后每24小时最高收费(0位无最高收费)</label>
                <div class="layui-input-block" >
                    <input type="text" name="maxDaynFee" required style="width: 100px" lay-verify="required" placeholder="后每24小时最高收费" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </fieldset>-->
