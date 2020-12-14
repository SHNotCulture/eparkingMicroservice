<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>车位管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table','form','layer','upload'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;
            var upload = layui.upload;

            //执行实例
            var uploadInst = upload.render({
                elem: '#portImport' //绑定元素
                ,url: '/Base/import' //上传接口
                ,accept:'file'
                ,acceptMime: 'file/xlsx, file/xls'
                ,done: function(res){
                    //上传完毕回调
                    if(res.code==0)
                    {
                        layer.closeAll();
                        layer.msg(res.result);
                        tableIns.reload();

                    }
                }
                ,error: function(){
                    //请求异常回调
                    layer.msg(result.message);
                }
            });
            //第一个实例
            tableIns=table.render({
                elem: '#TruckSpaceTable'
                ,height: 600
                ,url: '../truckSpace/getTruckSpacebyPage' //数据接口
                ,method:'post'
                ,toolbar: '#truckSpaceToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,title:'车位信息'
                ,page: true //开启分页
                ,cols: [[ //表头
                    //{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'},
                   /* {field: 'parkId', title: '所属车场', width:120,templet:'#parkIdTemp'}*/
                    {type: 'numbers', title: '序号', width:50, fixed: 'left'}
                   ,{field: 'parkingState', title: '车位状态',align: 'center', width:100,templet:'#parkingStateTemp'}
                    ,{field: 'parkingSpace', title: '车位性质',align: 'center', width:100,templet:'#parkingSpaceTemp'}
                    ,{field: 'parkCode', title: '车位编码',align: 'center', width: 100}
                    ,{fixed: 'right',align: 'center', title: '操作', width: 120, toolbar: '#truckSpaceBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(TruckSpaceTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'TruckSpaceAdd':
                        var data = checkStatus.data;
                        TruckSpacehandle.Update(0);
                        break;
                    case 'TruckSpaceExcel':
                        var parkingSpace=$("#parkingSpace").val();
                        var parkingState=$("#parkingState").val();
                        var data={parkingSpace:parkingSpace,parkingState:parkingState}
                        TruckSpacehandle.TruckSpaceExcel(data);
                        break;
                    default:
                };
            });
            //监听工具条
            table.on('tool(TruckSpaceTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('是否删除', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        TruckSpacehandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("TruckSpaceForm", data);
                    TruckSpacehandle.Update(1);//弹出车场更新窗口
                }
            });
            //监听更新按钮
            form.on('submit(TruckSpaceUpdate)', function(data){
               /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                TruckSpacehandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听查询按钮
            form.on('submit(SelectTruckSpace)', function(data){
                /*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                TruckSpacehandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            form.on('submit(TruckSpaceImport)', function(data){
                return false;
            });
            TruckSpaceForm.getParkingSpace(form);
            TruckSpaceForm.getParkingState(form);
            form.render(); //更新全部
        });
        //在页面加载完成后执行
        $(document).ready(function () {
            TruckSpaceForm.getParkList();

        })

        //车位增删查改方法
        var  TruckSpacehandle={
            Update:function (type) {
                if(type==0){//新增
                    TruckSpaceForm.clean();
                    layer.open({
                        title: '新增车位'
                        , type: 1
                        ,area:['600px','300px']
                        ,content: $('#TruckSpaceForm')
                    });
                }else{
                    layer.open({
                        title: '更新车位'
                        , type: 1
                        ,area:['600px','300px']
                        ,content: $('#TruckSpaceForm')
                    });
                }
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
                    url: "../truckSpace/updateTruckSpace",
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
                    url: "../truckSpace/deleteTruckSpace",
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
            TruckSpaceExcel:function (object) {
                window.location.href="../Base/exportListforTTruckSpace?parkingSpace="+object.parkingSpace+"&parkingState="+object.parkingState;
            }
        };
        var parkList,parkingStateList,parkingSpaceList;
        //车位更新表单使用方法
        var TruckSpaceForm={
            clean:function(){
                $("#TruckSpaceForm")[0].reset();
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
                           /* var str=" <option value>请选择停车场</option>";
                            parkList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.parkName+"</option>"
                            });
                            $("select[name='parkId']").html(str);*/
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
            //获取车位状态
            getParkingState:function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getParkingStateList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            parkingStateList=result.result;
                            var str=" <option value>请选择</option>";
                            parkingStateList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("select[name='parkingState']").html(str);
                        }
                        form.render();
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
            //获取车位性质
            getParkingSpace:function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getParkingSpaceList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            parkingSpaceList=result.result;
                            // console.log("parkingSpaceList: ",parkingSpaceList);
                            var str=" <option value>请选择</option>";
                            parkingSpaceList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("select[name='parkingSpace']").html(str);
                        }
                        form.render();
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
    <script type="text/html" id="parkingStateTemp">
        {{# layui.each(parkingStateList,function(index,item){ }}
        {{# if(item.id===d.parkingState){ }}
        {{ item.name }}
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
    <script type="text/html" id="truckSpaceBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="truckSpaceToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="TruckSpaceAdd">添加车位</button>
            <button class="layui-btn layui-btn-sm" lay-event="TruckSpaceExcel">导出车位信息</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">车位管理</blockquote>
<div class="layui-form layui-form-pane" style="margin: 10px" id="SelectTruckSpace">
    <div class="layui-form-item">
        <div class="layui-inline">
           <#-- <label class="layui-form-label">所属车场：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select id="parkId" name="parkId" lay-filter="parkId">
                </select>
            </div>-->
            <label class="layui-form-label">车位性质：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select id="parkingSpace" name="parkingSpace" lay-filter="parkingSpace">
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">车位状态：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select id="parkingState" name="parkingState" lay-filter="parkingState">
                </select>
            </div>
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn" lay-submit lay-filter="SelectTruckSpace" >查询</button>
<#--                <button class="layui-btn"  id="portImport" lay-submit lay-filter="TruckSpaceImport"  ><i class="layui-icon">&#xe67c;</i>车位导入</button>-->
            </div>
        </div>
    </div>
</div>
<table id="TruckSpaceTable" lay-filter="TruckSpaceTable"></table>
</body>
<form class="layui-form"  style="display: none" id="TruckSpaceForm" lay-filter="TruckSpaceForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <#--<div class="layui-form-item">
        <label class="layui-form-label">停车场</label>
        <div class="layui-input-inline" style="width: 200px;">
            <select id="parkId" name="parkId" lay-filter="parkId">
            </select>
        </div>
    </div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">车位状态</label>
        <div class="layui-input-inline" style="width: 200px;">
            <select id="parkingState" name="parkingState" lay-filter="parkingState" lay-verify="required">
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">车位性质</label>
        <div class="layui-input-inline" style="width: 200px;">
            <select id="parkingSpace" name="parkingSpace" lay-filter="parkingSpace" lay-verify="required">
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">车位编码</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" name="parkCode"  lay-verify="required" placeholder="车位编码" autocomplete="off" class="layui-input">
        </div>
        <label class="layui-form-label">*多个用','号隔开</label>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="TruckSpaceUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</html>