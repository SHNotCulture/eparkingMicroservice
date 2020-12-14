<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>车场信息</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>

    <script type="text/javascript">
        var tableIns,formIns;
        layui.use(['table','form','layer'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;

            //第一个实例
            tableIns=table.render({
                elem: '#carPlateTable'
                ,height:600
                ,url: '../carPark/getCarParkbyPage' //数据接口
                ,method:'post'
                ,toolbar: '#toolbarPark'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,title:'停车场信息'
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'id', title: 'ID', width:80, sort: true}
                    ,{field: 'companyId', title: '物业公司', width:120,templet:'#companyTemp'}
                    ,{field: 'parkName', title: '车场名称', width:120}
                    ,{field: 'totalParkingSpace', title: '总车位数', width:120}
                    ,{field: 'privateTotalSpace', title: '私家车总车位数', width:120}
                    ,{field: 'monthRule', title: '月卡规则', width: 120,templet:'#monthRuleTemp'}
                    ,{field: 'address', title: '地址', width: 120, sort: true}
                    ,{field: 'apiKey', title: 'key', width: 300}
                    ,{fixed: 'right', title: '操作', width: 200, toolbar: '#carPlateBar'}
                ]]
                , done: function(res, curr, count){
                    // console.log(res);
                }
            });
            //头工具栏事件
            table.on('toolbar(carPlateTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'add':
                        var data = checkStatus.data;
                        CarPlatehandle.Update(0);
                        break;
                };
            });
            //监听工具条
            table.on('tool(carPlateTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('是否删除', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        CarPlatehandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("carPlateUp", data);
                    CarPlatehandle.Update(1);//弹出车场更新窗口
                }
            });
            //监听更新按钮
            form.on('submit(formUpdate)', function(data){
               /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
               if(data.field.isWechatMonthly==null){
                   data.field.isWechatMonthly=0;
               }
                if(data.field.autoRenew==null){
                    data.field.autoRenew=0;
                }
                CarPlatehandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听查询按钮
            form.on('submit(SelectParkName)', function(data){
               /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                CarPlatehandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            form.render(); //更新全部
        });
        //在页面加载完成后执行
        $(document).ready(function () {
            CarPlateForm.getCarPayRule();
            CarPlateForm.getComPany();

        })
        //车场增删查改方法
        var CarPlatehandle={
            Update:function (type) {
                if(type==0){//新增
                    CarPlateForm.clean();
                    layer.open({
                        title: '新增车场'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#carPlateUp')
                    });
                }else{
                    layer.open({
                        title: '更新车场'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#carPlateUp')
                    });
                }

            },
               select:function (data) {
                   //这里以搜索为例
                   tableIns.reload({
                       where:data
                       ,page: {
                           curr: 1 //重新从第 1 页开始
                       }
                   });
               } ,
            save:function(object){
                $.ajax({
                    type: 'POST',
                    url: "../carPark/updateCarPark",
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
                            if(result.result=="新增成功"){
                                //跳转到全局参数配置管理页面
                                //获取父页面
                                var body = window.parent.document.getElementById("body");
                                //父页面设置一个iframe
                                body.html("<iframe src=\"../view/globalManage\" style=\"height:100%;width:98%\"/>");

                            }
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
                    url: "../carPark/deleteCarPark",
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
        var CarPayRuleList,ComPanyList;
        //车场更新表单使用方法
        var CarPlateForm={
            clean:function(){
                $("#carPlateUp")[0].reset();
            },
            //获取缴费规则
            getCarPayRule:function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getCarPayRuleList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            CarPayRuleList=result.result;
                            var str=" <option value>请选择计算规则</option>";
                            CarPayRuleList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("#monthRule").html(str);
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
            //获取物业公司
            getComPany:function () {
                $.ajax({
                    type: 'POST',
                    url: "../comPany/getComPany",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            //console.log(result);
                            ComPanyList=result.result;
                            // console.log(result.result);
                            var str=" <option value>请选择物业公司</option>";
                            ComPanyList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.companyName+"</option>"
                            });
                            $("#companyId").html(str);
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
        }
    </script>
    <script type="text/html" id="companyTemp">
        {{# layui.each(ComPanyList,function(index,item){ }}
        {{# if(item.id===d.companyId){ }}
        {{ item.companyName }}
        {{# return; }}
        {{# }  }}
        {{#  }); }}
    </script>
    <script type="text/html" id="monthRuleTemp">
        {{# layui.each(CarPayRuleList,function(index,item){ }}
        {{# if(item.id===d.monthRule){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}

        {{#  }); }}
    </script>
    <script type="text/html" id="carPlateBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="toolbarPark">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">车场信息</blockquote>
<form class="layui-form" style="margin: 10px">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">车场名称</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="parkName" placeholder="车场名称" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn"  lay-submit lay-filter="SelectParkName" >查询</button>
            </div>
        </div>
    </div>
</form>
<table id="carPlateTable" lay-filter="carPlateTable"></table>
</body>
    <form class="layui-form layui-form-pane"  style="display: none" id="carPlateUp" lay-filter="carPlateUp">
        <div class="layui-form-item" style="display: none">
            <label class="layui-form-label">ID</label>
            <div class="layui-input-block">
                <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">物业名称</label>
            <div class="layui-input-block">
                <select id="companyId" name="companyId"  lay-verify="required" lay-filter="companyId">
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">车场名称</label>
            <div class="layui-input-block">
                <input type="text" name="parkName" required  lay-verify="required" placeholder="车场名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">车位数</label>
            <div class="layui-input-block">
                <input type="text" name="totalParkingSpace" required  lay-verify="required|number" placeholder="车位数" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">私家车总车位数</label>
            <div class="layui-input-block">
                <input type="text" name="privateTotalSpace" required  lay-verify="required|number" placeholder="私家车总车位数" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">月计算规则</label>
            <div class="layui-input-block">
                <select id="monthRule" name="monthRule"  lay-verify="required" lay-filter="monthRule">
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开户行</label>
            <div class="layui-input-block">
                <input type="text" name="bankName"  placeholder="开户行" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">账号</label>
            <div class="layui-input-block">
                <input type="text" name="bankAccount" required  lay-verify="required" placeholder="账号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">银行卡号</label>
            <div class="layui-input-block">
                <input type="text" name="bankCard" required  lay-verify="required|number" placeholder="银行卡号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <input type="text" name="address"  placeholder="地址" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">X坐标</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="positionX" placeholder="X坐标" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">Y坐标</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="positionY" placeholder="Y坐标" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 200px;">是否允许自动续缴</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="checkbox" name="autoRenew" lay-skin="switch" value="1" lay-text="允许|不允许">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 200px;">是否允许微信月租续费</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="checkbox" name="isWechatMonthly" lay-skin="switch" value="1" lay-text="允许|不允许">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formUpdate">保存</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</html>