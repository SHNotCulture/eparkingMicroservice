<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>节假日管理</title>
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
                elem: '#beginTime' //指定元素
                ,format: 'MM-dd' //可任意组合
            });
            //第一个实例
            tableIns=table.render({
                elem: '#HolidayTable'
                ,height: 315
                ,url: '../chargeRule/getHoliday' //数据接口
                ,method:'post'
                ,toolbar: '#toolbarHoliday'
                ,defaultToolbar: ['filter', 'print']
                ,page: true //开启分页
                ,loading:true
                ,cols: [[ //表头
                    //{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'},
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'holidayName', title: '假日名称', width:120}
                    ,{field: 'beginTime', title: '开始日期', width:120,}
                    ,{field: 'dayCount', title: '放假天数', width:120,}
                    ,{fixed: 'right', title: '操作', width: 200, toolbar: '#HolidayBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(HolidayTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'holidayAdd':
                        var data = checkStatus.data;
                        Holidayhandle.Update(0);
                        break;
                    case 'dayOffSet':
                        Holidayhandle.dayOffAlter();
                        break;
                    default:
                };
            });
            //监听工具条
            table.on('tool(HolidayTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('是否删除', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        Holidayhandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("HolidayForm", data);
                    Holidayhandle.Update(1);//弹出车场更新窗口
                }
            });
            //监听更新按钮
            form.on('submit(holidayUpdate)', function(data){
               /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                Holidayhandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });

            form.render(); //更新全部
        });
        //节假日增删查改方法
        var  Holidayhandle={
            Update:function (type) {
                if(type==0){//新增
                    HolidayForm.clean();
                }
                layer.open({
                    title: '更新节假日'
                    , type: 1
                    ,area:['400px','300px']
                    ,content: $('#HolidayForm')
                });
            },
            dayOffAlter:function () {
                layer.open({
                    title:'调休设置 '
                    , type: 2
                    ,area:['700px','400px']
                    ,content: '../view/dayOff'
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
                    url: "../chargeRule/updateHoliday",
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
                    url: "../chargeRule/deleteHoliday",
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
        //节假日更新表单使用方法
        var HolidayForm={
            clean:function(){
                $("#HolidayForm")[0].reset();
            }
        }
    </script>
    <script type="text/html" id="toolbarHoliday">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="holidayAdd">添加节假日</button>
            <button class="layui-btn layui-btn-sm" lay-event="dayOffSet">设置调休日</button>
        </div>
    </script>
    <script type="text/html" id="HolidayBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</head>
<body>
<table id="HolidayTable" lay-filter="HolidayTable"></table>
</body>
<form class="layui-form"  style="display: none" id="HolidayForm" lay-filter="HolidayForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">节假日名称</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="holidayName" name="holidayName">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开始时间</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="beginTime" name="beginTime">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">放假天数</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="dayCount" name="dayCount">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="holidayUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</html>