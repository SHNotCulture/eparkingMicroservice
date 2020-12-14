<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>黑名单管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        var tablecarplate;
        var jsonCarPlate = new Array();
        layui.use(['table','form','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer = layui.layer;
            var laydate = layui.laydate;
            var laydate1 = layui.laydate;
            laydate.render({
                elem: '#datebegin'
                ,type: 'datetime'
            });
            laydate1.render({
                elem: '#dateend'
                ,type: 'datetime'
            });
            //第一个实例
            tableIns=table.render({
                elem: '#blackTable'
                ,height: 700
                ,url: '../blackList/getBlackList' //数据接口
                ,method:'post'
                ,toolbar: true
                ,toolbar: '#toolbarBlack'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,title:'黑名单'
                ,page: true //开启分页
                ,cols: [[ //表头
                    /* {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}*/
                    /*,{field: 'parkId', title: '停车场', width:120,templet:'#parkIdTemp'}*/
                    {type: 'numbers', title: '序号', width:50, fixed: 'left'}
                    ,{field: 'carPlate', title: '车牌', align: 'center', width: 110}
                    ,{field: 'createTime', title: '修改时间', align: 'center', width:170}
                    ,{field: 'createPerson', title: '修改人', width:80}
                    ,{field: 'beginTime', title: '起始时间', align: 'center', width:170}
                    ,{field: 'endTime', title: '终止时间', align: 'center', width:170}
                    ,{fixed: 'right',title: '操作', align: 'center', width: 120, toolbar: '#blackBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(blackTable)', function(obj){
                var carplate = $('#blackCarplate').val();
                var data = {
                    carplate: carplate
                };
                blackForm.BlackExcel(data)
            });
            //监听工具条
            table.on('tool(blackTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                // console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('真的删除行么', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        blackHandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("blackForm", data);
                    blackHandle.Update(1);//弹出车场更新窗口
                }
            });
            table.on('tool(carplateTable)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                if (layEvent === 'del') { //删除
                    layer.confirm('真的删除行么', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        blackHandle.deleteCarplate(data);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                }
            });
            form.verify({
                carPlateCheck: function (value, item) {
                    var patt = /^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[A-HJ-K])|([A-HJ-K]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})$/;
                    if (value.length > 0) {
                        if (patt.test(value) != true) {
                            return '请输入合法车牌号';
                        }
                    } else {
                        return '请输入车牌号';
                    }
                }

            });

            form.on('submit(carPlateAdd)', function (data) {
                var carPlateOne = $("#carPlateOne").val();
                //车牌是否已存在验证
                blackHandle.checkCarplate({'carplate':carPlateOne});
                return false;
            });
            form.on('submit(carPlateToSave)', function (data) {
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
                $("#carPlate").val(carPlateList);
                jsonCarPlate = new Array();
                return false;
            });
            //监听更新按钮
            form.on('submit(blackUpdate)', function(data){
               /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                blackHandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            tablecarplate = table.render({
                elem: '#carplateTable'
                , height: 315
                , method: 'post'
                , loading: true
                , page: true //开启分页
                , cols: [[ //表头
                    //{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                    {type: 'numbers', title: '序号', width: 80, fixed: 'left'}
                    , {field: 'carPlate', title: '车牌', width: 120}
                    , {fixed: 'right', title: '操作', width: 200, toolbar: '#carPlateBar'}
                ]]
            });
            form.render();

            //查询按钮
            form.on('submit(SelectSerchBlackForm)',function (data) {
                // console.log(data.field);
                blackHandle.select(data.field);
                return false;
            });
        });
        $(document).ready(function () {
            blackForm.getParkList();
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
        //黑名单增删查改方法
        var blackHandle={
            Update:function (type) {
                if(type==0){//新增
                    blackForm.clean();
                    layer.open({
                        title: '新增黑名单'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#blackForm')
                    });
                }else{
                    layer.open({
                        title: '更新黑名单'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#blackForm')
                    });
                }
            },
            select:function (name) {
                //这里以搜索为例
                tableIns.reload({
                    where:  //设定异步数据接口的额外参数，任意设
                        name
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            } ,
            save:function(object){
                var carplateList = object.carPlate.split(',');
                carplateList.forEach(function(blackCarplate) {
                    $.ajax({
                        type: 'POST',
                        url: "../blackList/updateBlackList",
                        data:{'id':object.id,'carPlate':blackCarplate,'beginTime':object.beginTime,'endTime':object.endTime},
                        dataType: "json",
                        contentType: "application/x-www-form-urlencoded; charset=utf-8",
                        success: function (result) {
                            // console.log(result);
                            if(result.code==0)
                            {
                                if (result.result=='该车牌已存在') {
                                    layer.msg(result.result, {icon: 5});
                                }else {
                                    layer.closeAll();
                                    layer.msg(result.result);
                                    tableIns.reload();
                                }
                            }
                        },
                        error:function (result) {
                            console.log(result);
                        }
                    })
                });
            },
            delete:function (id) {
                $.ajax({
                    type: 'POST',
                    url: "../blackList/deleteBlackList",
                    data:{'id':id},
                    dataType: "JSON",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log(result);
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
            },
            deleteCarplate: function (data) {
                jsonCarPlate.remove(data.carPlateid);
                tablecarplate.reload({
                    data: jsonCarPlate //设定异步数据接口的额外参数，任意设
                    , page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            checkCarplate: function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../monthlyCar/getTParkCarByCarplate",
                    data: object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if (result.code == 0) {
                            var carplateList = result.result;
                            if(carplateList.length>0){
                                //车牌已存在不执行保存操作
                                $("#carPlateOne").val("");
                                layer.msg("该车牌已存在", {icon: 5});
                            }else{
                                var flag=0;
                                jsonCarPlate.forEach(function (index, val) {
                                    if(index.carPlate.indexOf(object.carplate) > -1 ){
                                        flag=1;
                                    }
                                });
                                if(flag==0){
                                    var a = {'carPlateid': jsonCarPlate.length, 'carPlate': object.carplate};
                                    $("#carPlateOne").val("");
                                    jsonCarPlate.push(a);
                                    tablecarplate.reload({
                                        data: jsonCarPlate //设定异步数据接口的额外参数，任意设
                                        , page: {
                                            curr: 1 //重新从第 1 页开始
                                        }
                                    });
                                }else{
                                    $("#carPlateOne").val("");
                                    layer.msg("该车牌已输入", {icon: 5});
                                }
                            }
                        }
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            },
            setCarplate:function () {
                $("#carPlateOne").val("");
                if($("#carPlate").val()!=null){
                    var jsonCarPlateOld=$("#carPlate").val().split(',');
                }
                if (jsonCarPlateOld != "" && jsonCarPlateOld != null) {
                    jsonCarPlateOld.forEach(function (index, val) {
                        var a = {'carPlateid': val, 'carPlate': index};
                        jsonCarPlate.push(a);
                    });
                }
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
            }

        };
        var parkList;
        //车场更新表单使用方法
        var blackForm={
            clean:function(){
                $("#blackForm")[0].reset();
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
                            parkList= result.result;
                            var str=" <option value>请选择停车场</option>";
                            parkList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.parkName+"</option>"
                            });
                            $("#parkId").html(str);
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
            BlackExcel:function (object) {
                // console.log(object);
            window.location.href="../Base/exportListBlack?carplate="+ object.carplate;
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
    <script type="text/html" id="blackBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="toolbarBlack">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="blackHandle.Update(0)">新增黑名单</button>
            <button class="layui-btn layui-btn-sm" lay-event="BlackExcel">导出黑名单信息</button>
        </div>
    </script>
    <script type="text/html" id="carPlateBar">
        <#--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>-->
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">黑名单管理 </blockquote>
<form class="layui-form" style="margin-top: 5px" id="serchBlackForm" lay-filter="serchBlackForm">
    <div class="layui-form-item" >
        <div class="layui-inline">
        <label class="layui-form-label">车牌</label>
        <div class="layui-input-inline">
            <input type="text" name="carPlate" id="blackCarplate" placeholder="可模糊搜索" autocomplete="off" class="layui-input">
        </div>
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn"  lay-submit lay-filter="SelectSerchBlackForm" >查询</button>
         </div>
        </div>
    </div>
</form>
<#--测试二维码-->
    <#--<form id="enter" action="/qr/open" method="post" class="form-horizontal form-label-left">
        <div class="form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">票编号</label>
            <div class="col-md-9 col-sm-9 col-xs-12">
                <input name="qrid1" id="qrid1" type="text" class="form-control" placeholder="请输入有效的票编号"required>
                <span style="color: red" id="warning"></span>
            </div>
            <div class="modal-footer">
                <button type="submit"   class="btn btn-primary">提交</button>
            </div>
        </div>-->
        <#--<img src="../couponQrcode/qrcode?content=1"/>-->
<#--测试二维码-->

<table id="blackTable" lay-filter="blackTable"></table>
</body>
<form class="layui-form"  style="display: none" id="blackForm" lay-filter="blackForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 10px">
        <label class="layui-form-label">车牌</label>
        <div class="layui-input-inline">
            <input type="text" name="carPlate" id="carPlate" placeholder="车牌" lay-verify="required" readonly autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline" style="width: 70px;">
            <button class="layui-btn" type="button" onclick="blackHandle.setCarplate()">操作</button>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">起始时间*</label>
        <div class="layui-input-inline">
            <input type="text" name="beginTime" id="datebegin"  placeholder="yyyy-MM-dd HH:mm:ss" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">终止时间*</label>
        <div class="layui-input-inline">
            <input type="text" name="endTime" id="dateend"  placeholder="yyyy-MM-dd HH:mm:ss" lay-verify="required" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"></label>
        <div class="layui-input-inline">
            <button class="layui-btn" lay-submit lay-filter="blackUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<#--添加车牌弹窗-->
<div id="showCarPlate" style="display: none;">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <div class="layui-input-inline" style="width: 150px;left: 20px;margin-top:10px">
                <input type="text" name="carPlateOne" id="carPlateOne" lay-verify="carPlateCheck" placeholder="单个车牌"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 100px;left: 10px;margin-top:10px">
                <button class="layui-btn" lay-submit lay-filter="carPlateAdd">车牌添加</button>
            </div>
        </div>
    </form>
    <table class="layui-table" id="carplateTable" lay-filter="carplateTable"></table>
    <button class="layui-btn" lay-submit lay-filter="carPlateToSave" style="margin-left:160px">保存</button>
</div>
</html>