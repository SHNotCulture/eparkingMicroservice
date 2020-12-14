<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>通道管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        var ipcTypeList;
        layui.use(['table','form','layer','upload'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;


            //第一个实例
            tableIns=table.render({
                elem: '#PortTable'
                ,height: 600
                ,url: '../parkPort/getParkPortbyPage' //数据接口
                ,method:'post'
                ,toolbar: '#parkPortToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,title:'通道信息'
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                   ,{field: 'portId', title: '通道编码', width:120}
                    ,{field: 'portName', title: '通道名称', width:120}
                    ,{field: 'portType', title: '通道类型', width: 120,templet:'#portTypeTemp'}
                    ,{field: 'computerIndex', title: '通道电脑', width: 120}
                    ,{fixed: 'right', title: '操作', width: 200, toolbar: '#parkPortBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(PortTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'parkPortAdd':
                        var data = checkStatus.data;
                        ParkPorthandle.Update(0);
                        break;
                    default:
                };
            });
            //监听工具条
            table.on('tool(PortTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('是否删除', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        ParkPorthandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    // console.log("bjdata: ",data);
                    form.val("ParkPortForm", data);
                    if(data.portType==0){

                        $("#formInPortType").prop("checked", true);
                        $("#formOutPortType").removeAttr("checked");
                    }else{
                        $("#formOutPortType").prop("checked", true);
                        $("#formInPortType").removeAttr("checked");
                    }

               /*     $("#formPortId").val(data.portId);
                    $("#formPortName").val(data.portName);
                    $("#formComputerIndex").val(data.computerIndex);
                    $("input[name=PortType][value=0]").attr("checked", data.computerIndex == 0 ? true : false);
                    $("input[name=PortType][value=1]").attr("checked", data.computerIndex == 1 ? true : false);*/
                    form.render(); //更新全部

                    ParkPorthandle.Update(1);//弹出车场更新窗口
                }
            });
            //监听更新按钮
            form.on('submit(parkPortUpdate)', function(data){
               /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                // console.log("saveData: ",data.field);
                if(data.field.intervene==null){
                    data.field.intervene=0;
                }
                if(data.field.actived==null){
                    data.field.actived=0;
                }
                if(data.field.isInCarUpdate==null){
                    data.field.isInCarUpdate=0;
                }
                ParkPorthandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听查询按钮
            form.on('submit(SelectParkPort)', function(data){
                /*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                ParkPorthandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            ParkPorthandle.getIpcType(form);
            form.render(); //更新全部
        });
        //在页面加载完成后执行
        $(document).ready(function () {
/*            ParkPortForm.getParkList();
            ParkPortForm.getParkingSpace();
            ParkPortForm.getParkingState();*/
        })

        //通道增删查改方法
        var  ParkPorthandle={
            Update:function (type) {
                if(type==0){//新增
                    ParkPortForm.clean();
                }
                layer.open({
                    title: '更新通道'
                    , type: 1
                    ,area:['600px','600px']
                    ,content: $('#ParkPortForm')
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
                // console.log("data: ",object)
                $.ajax({
                    type: 'POST',
                    url: "../parkPort/updateParkPort",
                    data:object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log("result: ",result);
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
                    url: "../parkPort/deleteParkPort",
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
            },
            getIpcType:function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../tIpc/getTIpc",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                             // console.log("Typeresult: ",result);
                            ipcTypeList= result.result;
                            var str="<option value>识别相机类型编号</option>";
                            ipcTypeList.forEach(function (t) {
                                str+="<option value="+t.ipcType+">"+t.ipcType+"</option>"
                            });
                            $("select[name='ipcType']").html(str);
                        }
                        form.render();
                    },
                    error: function (result) {
                        // console.log(result);
                    }
                })
            }
        };
        var parkList;
        //车位更新表单使用方法
        var ParkPortForm={
            clean:function(){
                $("#ParkPortForm")[0].reset();
            }
        }
    </script>

    <#--    通道类型(0/入口 1/出口)-->
    <script type="text/html" id="portTypeTemp">
        {{# if(d.portType===0){ }}
        {{ '入口' }}
        {{# }if(d.portType===1){ }}
        {{ '出口' }}
        {{# } }}
    </script>

    <script type="text/html" id="parkPortBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="parkPortToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="parkPortAdd">添加通道</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">通道管理</blockquote>
<div class="layui-form layui-form-pane" style="margin: 10px" id="ParkPortManage">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">通道编号</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="portId" id="portId" required   placeholder="通道编号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">通道名称</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="portName" id="portName"    placeholder="通道名称" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">通道电脑</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="computerIndex" id="computerIndex"    placeholder="通道电脑" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
            <label class="layui-form-label">通道类型</label>
            <input type="radio" name="portType" value="0" title="入口">
            <input type="radio" name="portType" value="1" title="出口">
        </div>

            <button class="layui-btn" lay-submit lay-filter="SelectParkPort" >查询</button>
        </div>

    </div>
</div>
<table id="PortTable" lay-filter="PortTable"></table>
</body>
<form class="layui-form"  style="display: none" id="ParkPortForm" lay-filter="ParkPortForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">parkId</label>
        <div class="layui-input-block">
            <input type="text" name="parkId"  placeholder="parkId" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item" style="margin-top: 5px">
        <label class="layui-form-label" style="width: 160px;text-align: left">通道编号</label>
        <div class="layui-input-inline">
            <input type="text" name="portId"  id="formPortId"   placeholder="通道编号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 160px;text-align: left">通道名称</label>
        <div class="layui-input-inline">
            <input type="text" name="portName" id="formPortName"  placeholder="通道名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 160px;text-align: left">通道电脑</label>
        <div class="layui-input-inline">
            <input type="text" name="computerIndex"  id="formComputerIndex"  placeholder="通道电脑" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 160px;text-align: left">通道类型</label>
        <input type="radio" id="formInPortType" name="portType" value="0" title="入口" >
        <input type="radio" id="formOutPortType" name="portType" value="1" title="出口">
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">车场名称</label>
        <div class="layui-input-inline">
            <input type="text" name="parkName"  id="parkName"  placeholder="车场名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">通道控制器的序号</label>
        <div class="layui-input-inline">
            <input type="text" name="laneControlSn"  id="laneControlSn"  placeholder="通道控制器的序号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">主相机局域网IP</label>
        <div class="layui-input-inline">
            <input type="text" name="ipcIp"  id="ipcIp"  placeholder="主相机局域网IP" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">主摄像机序列号</label>
        <div class="layui-input-inline">
            <input type="text" name="ipcSn"  id="ipcSn"  placeholder="主摄像机序列号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">副相机1序号</label>
        <div class="layui-input-inline">
            <input type="text" name="auxIpc1Sn"  id="auxIpc1Sn"  placeholder="副相机1序号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">副相机2序号</label>
        <div class="layui-input-inline">
            <input type="text" name="auxIpc2Sn"  id="auxIpc2Sn"  placeholder="副相机2序号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">区域编码</label>
        <div class="layui-input-inline">
            <input type="text" name="areaNumber"  id="areaNumber"  placeholder="区域编码" autocomplete="off" class="layui-input"lay-verify="number">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">入场是否干预</label>
<#--        传回后台时如果选择了否，返回的是null，需要做赋值0处理-->
    <div class="layui-input-inline">
        <input type="checkbox" name="intervene" lay-skin="switch" lay-text="是|否" value="1">
    </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">是否激活</label>
<#--        传回后台时如果选择了否，返回的是null，需要做赋值0处理-->
        <div class="layui-input-inline">
            <input type="checkbox" name="actived" lay-skin="switch" lay-text="是|否" value="1">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">外置显示屏IP</label>
        <div class="layui-input-inline">
            <input type="text" name="lotsLedIp"  id="lotsLedIp"  placeholder="外置显示屏IP" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">外置车位屏首行显示内容</label>
        <div class="layui-input-inline">
            <input type="text" name="lotsLedStr"  id="lotsLedStr"  placeholder="外置车位屏首行显示内容" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">入场修改车型为"其他车”</label>
        <#--        传回后台时如果选择了否，返回的是null，需要做赋值0处理-->
        <div class="layui-input-inline">
            <input type="checkbox" name="isInCarUpdate" lay-skin="switch" lay-text="允许|不允许" value="1">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">相机线下接收模式</label>
        <div class="layui-input-inline">
            <select id="laneRxMode" name="laneRxMode" lay-filter="laneRxMode">
                <option value=1>相机http</option>
                <option value=2>相机MQTT</option>
                <option value=3 selected="selected">通道机MQTT</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">语音播放模式</label>
        <div class="layui-input-inline">
            <select id="voiceMode" name="voiceMode" lay-filter="voiceMode">
                <option value=1>硬件万能语音</option>
                <option value=2>相机TTS</option>
                <option value=3 selected="selected">通道机TTS</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"style="width: 160px;text-align: left">音量大小</label>
        <div class="layui-input-inline">
            <select id="voiceVolume" name="voiceVolume" lay-filter="voiceVolume">
                <option value=0>0</option>
                <option value=1>1</option>
                <option value=2>2</option>
                <option value=3>3</option>
                <option value=4>4</option>
                <option value=5>5</option>
                <option value=6>6</option>
                <option value=7>7</option>
                <option value=8 selected="selected">8</option>
                <option value=9>9</option>
                <option value=10>10</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
    <label class="layui-form-label" style="width: 160px;text-align: left">识别相机类型编号</label>
    <div class="layui-input-inline">
        <select  name="ipcType" id="ipcType" lay-filter="ipcType" lay-verify="required">
        </select>
    </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="parkPortUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</html>