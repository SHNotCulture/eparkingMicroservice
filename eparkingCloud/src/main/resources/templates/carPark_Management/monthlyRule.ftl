<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登记车规则管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns,form;
        layui.use(['table','laydate','form'], function(){
            var table = layui.table;
            var laydate = layui.laydate;
            laydate.render({
                elem: '#effectiveTime'
                ,type: 'time'
                ,value: '00:00:00 - 23:59:59'
                ,range: true
            });
             form=layui.form;
            //第一个实例
            tableIns=table.render({
                elem: '#monthlyRuleTable'
                ,height: 600
                ,url: '../carPayRule/getPayRulebyPage' //数据接口
                ,method:'post'
                ,toolbar: '#monthlyRuleToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:50, fixed: 'left'}
                    ,{field: 'ruleName', title: '规则设置',align: 'center', width:100}
                    ,{field: 'dayFee', title: '每天收费',align: 'right', width:90}
                    ,{field: 'monthFee', title: '每月收费',align: 'right', width: 90}
                    ,{field: 'seasonFee', title: '每季收费',align: 'right', width: 90}
                    ,{field: 'yearFee', title: '每年收费',align: 'right', width: 90}
                    ,{field: 'remark', title: '备注',align: 'center', width: 150}
                    ,{title: '操作',align: 'center', width: 120, toolbar: '#monthlyRuleBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(monthlyRuleTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'add':
                        var data = checkStatus.data;
                        monthlyRulehandle.Update(0)
                        break;
                };
            });
            //监听工具条
            table.on('tool(monthlyRuleTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('是否删除', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        monthlyRulehandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("monthlyRuleForm", data);
                    monthlyRulehandle.Update(1);//弹出车场更新窗口
                }
            });
            //监听更新按钮
            form.on('submit(formUpdate)', function(data){
                /*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                monthlyRulehandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });

            form.render();
        });
        //在页面加载完成后执行
        $(document).ready(function () {
            // monthlyRuleForm.getParkList();
        })

        //车场增删查改方法
        var monthlyRulehandle={
            Update:function (type) {
                if(type==0){//新增
                    monthlyRuleForm.clean();
                    layer.open({
                        title: '新增登记车规则'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#monthlyRuleForm')
                    });
                }else{
                    layer.open({
                        title: '更新登记车规则'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#monthlyRuleForm')
                    });
                }
            },
            select:function (name) {
                //这里以搜索为例
                tableIns.reload({
                    where: { //设定异步数据接口的额外参数，任意设
                        name:name
                    }
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            } ,
            save:function(object){
                $.ajax({
                    type: 'POST',
                    url: "../carPayRule/updatePayRule",
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
                    url: "../carPayRule/deletePayRule",
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
        var parkList;
        //车场更新表单使用方法
        var monthlyRuleForm={
            clean:function(){
                $("#monthlyRuleForm")[0].reset();
            }
            //获取停车场
/*            getParkList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarPark",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            //console.log(result);
                            parkList= result.data;
                           /!* var str=" <option value>请选择停车场</option>";
                            parkList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.parkName+"</option>"
                            });
                            $("#parkId").html(str);*!/
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
            }*/
        }
    </script>
<#--    <script type="text/html" id="parkIdTemp">
        {{# layui.each(parkList,function(index,item){ }}
        {{# if(item.id===d.parkId){ }}
        {{ item.parkName }}
        {{# return; }}
        {{# }  }}
        {{#  }); }}
    </script>-->
    <script type="text/html" id="monthlyRuleBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="monthlyRuleToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add">新增登记车规则</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">登记车规则管理</blockquote>
<table id="monthlyRuleTable" lay-filter="monthlyRuleTable"></table>
</body>
<form class="layui-form layui-form-pane"  style="display: none" id="monthlyRuleForm" lay-filter="monthlyRuleForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <#--<div class="layui-form-item">
        <label class="layui-form-label">停车场</label>
        <div class="layui-input-block">
            <select id="parkId" name="parkId" lay-filter="parkId">
            </select>
        </div>
    </div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">规则设置</label>
        <div class="layui-input-inline">
            <input type="text" name="ruleName" required  lay-verify="required" placeholder="规则设置" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">每天收费</label>
        <div class="layui-input-inline">
            <input type="text" name="dayFee" required  lay-verify="required" placeholder="每天收费" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">每月收费</label>
        <div class="layui-input-inline">
            <input type="text" name="monthFee" required  lay-verify="required" placeholder="每月收费" autocomplete="off" class="layui-input">

        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">每季收费</label>
        <div class="layui-input-inline">
            <input type="text" name="seasonFee" required  lay-verify="required" placeholder="每季收费" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">每年收费</label>
        <div class="layui-input-inline">
            <input type="text" name="yearFee" required  lay-verify="required" placeholder="每年收费" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">有效时间</label>
            <div class="layui-input-inline">
              <input type="text" name="effectiveTime" class="layui-input" id="effectiveTime" placeholder="00:00:00 - 23:59:59">
            </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注</label>
        <div class="layui-input-inline">
            <input type="text" name="remark" required  placeholder="备注" autocomplete="off" class="layui-input">
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