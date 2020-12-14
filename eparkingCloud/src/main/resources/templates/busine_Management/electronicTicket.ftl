<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>电子券管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table','form'], function(){   //,'laydate'
            var table = layui.table;
            var form=layui.form;
            /*var laydate = layui.laydate;
            var laydate1 = layui.laydate;
            laydate.render({
                elem: '#datebegin'
                ,type: 'datetime'
            });
            laydate1.render({
                elem: '#dateend'
                ,type: 'datetime'
            });*/
            //第一个实例
            tableIns=table.render({
                elem: '#eTicketTable'
                ,height: 700
                ,url: '../electronicTicket/getTElectronicTicketbyPage' //数据接口
                ,method:'post'
                ,toolbar: true
                ,toolbar: '#toolbarTicket'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,title:'电子券'
                ,page: true //开启分页
                ,cols: [[ //表头
                    /* {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}*/
                    /*,{field: 'parkId', title: '停车场', width:120,templet:'#parkIdTemp'}*/
                    {type: 'numbers', title: '序号', width:50, fixed: 'left'}
                    ,{field: 'ticketName', title: '电子券名称',align: 'center', width:200}
                    ,{field: 'ticketType', title: '电子券类型',align: 'center', width:100, templet:'#eTicketTypeTemp'}
                    ,{field: 'permissionDay', title: '时间限额',align: 'center', width:100,templet:'#permissionDayTemp'}
                    ,{field: 'ticketValue', title: '电子券价值',align: 'center', width:100,templet:'#ticketValueTemp'}
                    ,{field: 'ticketPay', title: '电子券对应现金',align: 'right', width:135}
                    ,{fixed: 'right',title: '操作',align: 'center', width: 120, toolbar: '#eTicketBar'}
                ]]
            });
            //头工具栏事件
            /*table.on('toolbar(eTicketTable)', function(obj){
                eTicketForm.BlackExcel()
            });*/
            //监听工具条
            table.on('tool(eTicketTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log("data"+data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                //var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('真的删除行么', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        eTicketHandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("eTicketForm", data);
                    eTicketHandle.Update(1);//弹出车场更新窗口

                }
            });
            form.on('select(ticketType)', function(data){
                if (data.value==0) {
                    $('#ticketValueUnit').text('元');
                }else {
                    $('#ticketValueUnit').text('分钟');
                }
            });
            //监听更新按钮
            form.on('submit(eTicketUpdate)', function(data){
               /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
               var ticketName=data.field.ticketName;
               var ticketType=data.field.ticketType;
               var ticketValue=data.field.ticketValue;
               var ticketPay=data.field.ticketPay;
               if (ticketName!="" && ticketType!="" && ticketValue!="" && ticketPay!=""){
                   eTicketHandle.save(data.field);
                   //console.log(data.field);
               }
                else{
                   layer.msg("电子券信息不能为空");
               }
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });

            form.render();

            //查询按钮
            form.on('submit(SelectSerchETicket)',function (data) {
               //console.log(data.field);
                eTicketHandle.select(data.field);
                return false;
            });
        });
        $(document).ready(function () {
            eTicketForm.getParkList();
        });
        //电子券增删查改方法
        var eTicketHandle={
            Update:function (type) {
                if(type==0){//新增
                    eTicketForm.clean();
                    layer.open({
                        title: '新增电子券'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#eTicketForm')
                    });
                }else{
                    layer.open({
                        title: '更新电子券'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#eTicketForm')
                    });
                }


            },
            select:function (obj) {
                //这里以搜索为例
                //console.log(obj);
                tableIns.reload({
                    where:  //设定异步数据接口的额外参数，任意设
                        obj
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            } ,
            save:function(object){
                $.ajax({
                    type: 'POST',
                    url: "../electronicTicket/updateTElectronicTicket",
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
/*                            if (result.result=='更新成功') {
                                layer.closeAll();
                                layer.msg(result.result, {icon: 6});
                                tableIns.reload();
                            }else if(result.result=='新建成功'){
                                layer.closeAll();
                                layer.msg(result.result);
                                tableIns.reload();
                            }*/
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
                    url: "../electronicTicket/deleteTElectronicTicket",
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

        };
        var parkList;
        //车场更新表单使用方法
        var eTicketForm={
            clean:function(){
                $("#eTicketForm")[0].reset();
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
            }
            /*BlackExcel:function () {
            window.location.href="../Base/exportListBlack";
             }*/
        };
    </script>

    <script type="text/html" id="eTicketBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

<#--    替换电子券类型 0代表金额 1代表时间-->
    <script type="text/html" id="eTicketTypeTemp">
        {{# if(d.ticketType===0){ }}
        {{ '金额' }}
        {{# }if(d.ticketType===1){ }}
        {{ '时间' }}
        {{# } }}
    </script>

    <script type="text/html" id="permissionDayTemp">
        {{# if(d.permissionDay==0){ }}
        {{ '一次性劵' }}
        {{# }else{ }}
        {{ d.permissionDay+'天有效' }}
        {{# } }}
    </script>

    <script type="text/html" id="ticketValueTemp">
        {{# if(d.ticketType===0){ }}
        {{ d.ticketValue+'元' }}
        {{# }if(d.ticketType===1){ }}
        {{ d.ticketValue+'分钟' }}
        {{# } }}
    </script>

    <script type="text/html" id="blackBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="toolbarTicket">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="eTicketHandle.Update(0)">新增电子券</button>
<#--            <button class="layui-btn layui-btn-sm" lay-event="BlackExcel">导出黑名单信息</button>-->
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">电子券管理 </blockquote>
<form class="layui-form" style="margin-top: 5px" id="serchBlackForm" lay-filter="serchBlackForm">
    <div class="layui-form-item" >
        <div class="layui-inline">
        <label class="layui-form-label">电子券名称</label>
        <div class="layui-input-inline">
            <input type="text" name="ticketName"  placeholder="可模糊搜索" autocomplete="off" class="layui-input">
        </div>
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn"  lay-submit lay-filter="SelectSerchETicket" >查询</button>
         </div>
        </div>
    </div>
</form>
<table id="eTicketTable" lay-filter="eTicketTable"></table>
</body>
<form class="layui-form"  style="display: none" id="eTicketForm" lay-filter="eTicketForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">电子券名称</label>
        <div class="layui-input-inline">
            <input type="text" id="ticketName" name="ticketName" lay-verify="required" autocomplete="off" placeholder="输入电子券名称" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">电子券类型</label>
        <div class="layui-input-inline">
            <select type="text" id="ticketType" name="ticketType" lay-filter="ticketType" >
                <option value="0">金额</option>
                <option value="1">时间</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">电子券价值</label>
        <div class="layui-input-inline">
            <input type="text" id="ticketValue" name="ticketValue" lay-verify="number" autocomplete="off" class="layui-input" lay-verify="required">
        </div>
        <label class="layui-form-label" id="ticketValueUnit" style="width: 120px;text-align: left">元</label>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">使用时间限额</label>
        <div class="layui-input-inline">
            <input type="text"  name="permissionDay" lay-verify="number|required" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label"  style="width: 120px;text-align: left">天(0为一次有效)</label>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">电子券售价</label>
        <div class="layui-input-inline">
            <input type="text" id="ticketPay" name="ticketPay" lay-verify="number" autocomplete="off" class="layui-input" lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"></label>
        <div class="layui-input-inline">
            <button class="layui-btn" lay-submit lay-filter="eTicketUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</html>