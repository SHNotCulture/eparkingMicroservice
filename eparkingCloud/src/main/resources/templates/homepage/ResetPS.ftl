<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>重置密码</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        layui.use(['table','form','layer'], function() {
            var table = layui.table;
            var form = layui.form;
            var layer = layui.layer;
            form.on('submit(resetPassworeparkingdbtn)', function(data){
                /*  console.log(data.elem); //得到select原始DOM对象
                  console.log(data.value); //得到被选中的值
                  console.log(data.othis); //得到美化后的DOM对象*/
                SysIndex.resetPassword(data.field);
                return false;
            });
        });
        var SysIndex={
            resetPassword:function(data){
                $.ajax({
                    type: 'POST',
                    url: "../login/resetPassword",
                    data:{password:data.password,newPassword:data.newPassword,resetPassword:data.resetPassword},
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        layer.msg(result.result);
                        top.window.location.href="/view/getLogin";
                    },
                    error:function (result) {
                        console.log(result.message);
                    }
                })
            }
        }
    </script>
</head>
<body>
<form class="layui-form" id="resetPassword" lay-filter="resetPassword">
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
            <button class="layui-btn" lay-submit lay-filter="resetPassworeparkingdbtn">重置</button>
        </div>
    </div>
</form>
</body>