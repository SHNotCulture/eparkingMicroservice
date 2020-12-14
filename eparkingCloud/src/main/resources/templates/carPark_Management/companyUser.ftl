<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <link rel="stylesheet" href="../js/zTree/demo.css" type="text/css">
    <link rel="stylesheet" href="../js/zTree/zTreeStyle.css" type="text/css">
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script src="../js/common.js"></script>
    <script src="../js/zTree/jquery.ztree.all-3.5.js"></script>
    <script type="text/javascript">
        var zNodes,setting;
        var UserZtree={
            getAllZtree:function () {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarParkZtree",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if(result.code==0)
                        {
                            if(result.data!=null)
                            {
                                zNodes=result.data;
                                UserZtree.createZtree();
                            }
                            else{
                                layer.msg("请联系管理员新增车场！");
                            }

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
            resetZtree:function (ParkIds) {
                if(ParkIds!=null&&ParkIds!="")
                {
                    var powerId=ParkIds.split(',');
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
                UserZtree.createZtree();
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
                        onCheck: UserZtree.onCheck
                    }
                };
                $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            },
            onCheck:function(e, treeId, treeNode){
                var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                        checkCount = zTree.getCheckedNodes(true);
                //console.log(checkCount);
                var parkIds="";
                checkCount.forEach(function(index, val ){
                    //console.log(index,val);
                    if(val==0){
                        parkIds=index.id;
                    }
                    else
                    {
                        parkIds=parkIds+","+index.id;
                    }
                });
                $("#parkIds").val(parkIds);
            }
        }
        $(document).ready(function(){
            UserZtree.getAllZtree();
            UserForm.getRole();
        });
        var tableIns;
        layui.use(['table','form','layer'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;
            //第一个实例
            tableIns=table.render({
                elem: '#UserTable'
                ,height:480
                ,url: '../user/getCompanyUserbyPage' //数据接口
                ,method:'post'
                ,toolbar: '#userToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                   /* {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'},*/
                    {type: 'numbers', title: '序号', width:50, fixed: 'left'}
                   ,{field: 'userName', title: '姓名', width:130,align:"left", sort: true}
                    ,{field: 'phone', title: '手机', width:120,align:"left"}
                    ,{field: 'userAccout', title: '账号', width:100, sort: true}
                    ,{field: 'roleId', title: '角色', width:100, sort: true,templet:'#roleIdTemp'}
                    //,{field: 'parkIds', title: '管理停车场', width:300, sort: true}
                    ,{fixed: 'right', title: '操作', width: 300, toolbar: '#UserBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(UserTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'UserAdd':
                        var data = checkStatus.data;
                        Userhandle.Update(0);
                        break;
                    default:
                };
            });
            //监听工具条
            table.on('tool(UserTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                // console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象
                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('是否删除', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        Userhandle.delete(data.id);
                        layer.close(index);
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("UserForm", data);
                    Userhandle.Update(1);//弹出车场更新窗口
                }else if(layEvent === 'changePassword')//修改密码
                {
                    form.val("changePassword", {id:data.id});
                    UserForm.changePasswordFrom();
                } else if(layEvent === 'resetPassword')//重置密码
                {
                    layer.confirm('是否需要重置密码', function(index){
                        Userhandle.resetPassword(data.id);
                        layer.close(index);
                    });
                }
            });
            //监听更新按钮
            form.on('submit(UserUpdate)', function(data){
               /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                if (data.field.parkIds != "") {
                    Userhandle.save(data.field);
                }else {
                    layer.msg("请选择停车场权限", {icon: 5, time: 1500});
                }
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
            //监听修改密码按钮
            form.on('submit(changePassworeparkingdbtn)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                Userhandle.changePassword(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听添加按钮
          /*  form.on('submit(UserAdd)', function(data){
               /!* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*!/
                Userhandle.Update(0);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });*/
            form.verify({
                validatePhone: function(value, item){
                    if(value.length > 0){
                        if (common.validatePhone(value) != true) {
                            return '号码格式不正确或者位数不正确';
                        }
                    }else{
                        return '请输入联系方式';
                    }
                }
            });
            form.render(); //更新全部
        });
        //用户增删查改方法
        var  Userhandle={
            Update:function (type) {
                if(type==0){//新增
                    UserForm.clean();
                    UserZtree.resetZtree();
                    layer.open({
                        title: '新增用户'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#UserForm')
                    });
                }else{
                    var parkIds = $("#parkIds").val();
                    UserZtree.resetZtree(parkIds);
                    layer.open({
                        title: '更新用户'
                        , type: 1
                        ,area:['500px','600px']
                        ,content: $('#UserForm')
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
                    url: "../user/updateCompanyUser",
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
                    url: "../user/deleteCompanyUser",
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
            //修改密码
            changePassword:function (data) {
                $.ajax({
                    type: 'POST',
                    url: "../user/changePassword",
                    data:{id:data.id,password:data.password,newPassword:data.newPassword,resetPassword:data.resetPassword},
                    dataType: "JSON",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if(result.code==0)
                        {
                            layer.closeAll();
                            layer.msg(result.result);
                            tableIns.reload();
                        }
                        else
                        {
                            layer.msg(result.message);
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
            },
            //重置密码
            resetPassword:function (id) {
                $.ajax({
                    type: 'POST',
                    url: "../user/resetPassword",
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
                });
            }
        };
        //用户更新表单使用方法
        var RoleList;
        var UserForm={
            clean:function(){
                $("#UserForm")[0].reset();
            },
            changePasswordFrom:function () {
                layer.open({
                    title: '修改密码'
                    , type: 1
                    ,area:['500px','600px']
                    ,content: $('#changePassword')
                });
            },
            getRole:function () {
                $.ajax({
                    type: 'POST',
                    url: "../user/getRolePower",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            //console.log(result);
                            RoleList= result.result;
                            var str=" <option value>请选择角色</option>";
                            RoleList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.roleName+"</option>"
                            });
                            $("select[name='roleId']").html(str);
                        }

                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            }
        }
    </script>
    <script type="text/html" id="roleIdTemp">
        {{# layui.each(RoleList,function(index,item){ }}
        {{# if(item.id===d.roleId){ }}
        {{ item.roleName }}
        {{# return; }}
        {{# }if(d.roleId===1){ }}
        默认物业公司超级角色
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="UserBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs" lay-event="changePassword">修改密码</a>
        <a class="layui-btn layui-btn-xs" lay-event="resetPassword">重置密码</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="userToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="UserAdd">添加用户</button>
        </div>
    </script>
</head>
<body>
<#--<form class="layui-form">
    <div class="layui-form-item">
        <button class="layui-btn" lay-submit lay-filter="UserAdd">添加</button>
    </div>
</form>-->

<table id="UserTable" lay-filter="UserTable"></table>
</body>
<form class="layui-form"  style="display: none" id="UserForm" lay-filter="UserForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="userName" name="userName" lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="phone" name="phone" lay-verify="required|validatePhone">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">账号</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="userAccout" name="userAccout" lay-verify="required">
        </div>
    </div>
    <#--<div class="layui-form-item">
        <label class="layui-form-label">密码</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="password" name="password">
        </div>
    </div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">角色</label>
        <div class="layui-input-inline" style="width: 200px;">
            <select id="roleId" name="roleId" lay-filter="roleId" lay-verify="required">
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">停车场设置</label>
        <div class="layui-input-inline" style="width: 200px;">
            <ul id="treeDemo" class="ztree"></ul>
        </div>
    </div>
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">管理停车场</label>
        <div class="layui-input-inline" style="width: 200px;">
            <input type="text" class="layui-input" id="parkIds" name="parkIds">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="UserUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<form class="layui-form" id="changePassword" lay-filter="changePassword"  style="display: none">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">原密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" required  lay-verify="required" placeholder="原密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-block">
            <input type="password" name="newPassword" required  lay-verify="required" placeholder="请输入新密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">重复密码</label>
        <div class="layui-input-block">
            <input type="password" name="resetPassword" required  lay-verify="required" placeholder="请再次输入密码" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="changePassworeparkingdbtn">修改</button>
        </div>
    </div>
</form>
</html>