<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>调休管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table','form','layer','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;
            var laydate = layui.laydate;
            //执行一个laydate实例
            laydate.render({
                elem: '#dayOff' //指定元素
                ,type: 'date'
            });
            //第一个实例
            tableIns=table.render({
                elem: '#DayOffTable'
                ,height: 315
                ,url: '../chargeRule/getDayOff' //数据接口
                ,method:'post'
                ,toolbar: '#toolbarDayOff'
                ,defaultToolbar: ['filter', 'print']
                ,page: true //开启分页
                ,loading:true
                ,cols: [[ //表头
                    //{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'},
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'dayOff', title: '调休日期', width:120}
                    ,{fixed: 'right', title: '操作', width: 200, toolbar: '#DayOffBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(DayOffTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'DayOffAdd':
                        var data = checkStatus.data;
                        DayOffhandle.Update(0);
                        break;
                    default:
                };
            });
            //监听工具条
            table.on('tool(DayOffTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('是否删除', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        DayOffhandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("DayOffForm", data);
                    DayOffhandle.Update(1);//弹出车场更新窗口
                }
            });
            //监听更新按钮
            form.on('submit(DayOffUpdate)', function(data){
                /*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                DayOffhandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });

            form.render(); //更新全部
        });
        //调休日增删查改方法
        var  DayOffhandle={
            Update:function (type) {
                if(type==0){//新增
                    DayOffForm.clean();
                }
                layer.open({
                    title: '更新节假日'
                    , type: 1
                    ,area:['400px','200px']
                    ,content: $('#DayOffForm')
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
                    url: "../chargeRule/updateDayOff",
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
                    url: "../chargeRule/deleteDayOff",
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
        //调休更新表单使用方法
        var DayOffForm={
            clean:function(){
                $("#DayOffForm")[0].reset();
            }
        }
    </script>
    <script type="text/html" id="toolbarDayOff">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="DayOffAdd">添加调休日</button>
        </div>
    </script>
    <script type="text/html" id="DayOffBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</head>
<body>

<table id="DayOffTable" lay-filter="DayOffTable"></table>
</body>
<form class="layui-form"  style="display: none" id="DayOffForm" lay-filter="DayOffForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">调休日期</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="dayOff" name="dayOff">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="DayOffUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</html>