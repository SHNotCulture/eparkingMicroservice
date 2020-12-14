<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <link rel="stylesheet" href="../js/zTree/demo.css" type="text/css">
    <link rel="stylesheet" href="../js/zTree/zTreeStyle.css" type="text/css">
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script src="../js/zTree/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript">
        var zNodes,setting;
        var RoleZtree={
            getAllZtree:function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../moduleList/getModuleListForZtree",
                    data:object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if(result.code==0)
                        {
                            zNodes=result.data;
                            RoleZtree.createZtree();
                        }
                    },
                    error:function (result) {
                        var str="<script type=\"text/javascript\">";
                        var beginNum=result.responseText.indexOf(str)+str.length;
                        var endNum=result.responseText.length-9;
                        var action=result.responseText.substring(beginNum,endNum);
                        eval(action);
                    }
                })
            },
            resetZtree:function (moduleId) {
                if(moduleId!=null&&moduleId!="")
                {
                    var powerId=moduleId.split(',');
                    zNodes.forEach(function(t){
                        var i=powerId.indexOf(t.id.toString());
                        if(i>=0){
                            t.checked=true;
                        }
                        else {
                            t.checked=false;
                        }
                    });
                }
                else
                {
                    zNodes.forEach(function(t){
                        t.checked=false;
                    });
                }
                RoleZtree.createZtree();
            },
            createZtree:function(){
                setting = {
                    check: {
                        enable: true
                    },
                    data: {
                        simpleData: {
                            enable: true
                        }
                    },
                    callback: {
                        onCheck: RoleZtree.onCheck
                    }
                };
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            },
            onCheck:function(e, treeId, treeNode){
                var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                        checkCount = zTree.getCheckedNodes(true);
                //console.log(checkCount);
                var moduleId="";
                checkCount.forEach(function(index, val ){
                    //console.log(index,val);
                    if(val==0){
                        moduleId=index.id;
                    }
                    else
                    {
                        moduleId=moduleId+","+index.id;
                    }
                });
                $("#moduleId").val(moduleId);
            }
        }
        $(document).ready(function(){
            RoleZtree.getAllZtree();
        });
        var tableIns;
        layui.use(['table','form','layer'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;
            //第一个实例
            tableIns=table.render({
                elem: '#RoleTable'
                ,height: 480
                ,url: '../user/getRolePowerbyPage' //数据接口
                ,method:'post'
                ,toolbar: '#roleToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                    /*{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'},*/
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'roleName', title: '角色名称', width:300, sort: true}
                    ,{field: 'remark', title: '角色描述', width:300}
                    ,{fixed: 'right', title: '操作',align: 'center', width: 120, toolbar: '#RoleBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(RoleTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'RoleAdd':
                        var data = checkStatus.data;
                        Rolehandle.Update(0);
                        break;
                    default:
                };
            });
            //监听工具条
            table.on('tool(RoleTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('是否删除', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        Rolehandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("RoleForm", data);
                    Rolehandle.Update(1);//弹出车场更新窗口
                }
            });
            //监听更新按钮
            form.on('submit(RoleUpdate)', function(data){
              /*  console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                if (data.field.moduleId != "") {
                    Rolehandle.save(data.field);
                }else {
                    layer.msg("请选择角色权限", {icon: 5, time: 1500});
                }
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
          /*  //监听添加按钮
            form.on('submit(RoleAdd)', function(data){
                /!*console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*!/
                Rolehandle.Update(0);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });*/

            form.render(); //更新全部
        });
        //角色增删查改方法
        var  Rolehandle={
            Update:function (type) {
                if(type==0){//新增
                    RoleForm.clean();
                    RoleZtree.resetZtree();
                    layer.open({
                        title: '新增角色'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#RoleForm')
                    });
                }else{
                    var moduleId = $("#moduleId").val();
                    RoleZtree.resetZtree(moduleId);
                    layer.open({
                        title: '更新角色'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#RoleForm')
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
                    url: "../user/updateRolePower",
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
                    url: "../user/deleteRolePower",
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
        //角色更新表单使用方法
        var RoleForm={
            clean:function(){
                $("#RoleForm")[0].reset();
            }
        }
    </script>

    <script type="text/html" id="roleToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="RoleAdd">添加角色</button>
        </div>
    </script>
    <script type="text/html" id="RoleBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
</head>
<body>
<#--<form class="layui-form">
    <div class="layui-form-item">
        <button class="layui-btn" lay-submit lay-filter="RoleAdd">添加</button>
    </div>
</form>-->

<table id="RoleTable" lay-filter="RoleTable"></table>
</body>
<form class="layui-form"  style="display: none" id="RoleForm" lay-filter="RoleForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色名称</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="roleName" name="roleName" lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">角色描述</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="remark" name="remark">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">权限设置</label>
        <div class="layui-input-inline" style="width: 200px;">
            <ul id="treeDemo" class="ztree"></ul>
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">选取权限</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="moduleId" name="moduleId">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="RoleUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</html>