<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>车易泊云平台</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <link rel="stylesheet" href="../public/icons/iconfont.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        layui.use(['table','form','layer'], function(){
            var form=layui.form;
            //监听登录按钮
            form.on('submit(formLogin)', function(data){
              /*  console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                Loginhandle.login(data.field.userAccout,data.field.password,true,data.field.code);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
           /* var code="'"++"'";
            $("#code").html(code);*/
        });
        $(document).ready(function () {
            Loginhandle.changeCode();
        })
        var Loginhandle={
            login:function(userName,passWord,rememberMe,code) {
                $.ajax({
                    type: 'POST',
                    url: "../login/checkPassword",
                    data: {userName:userName,passWord:passWord,rememberMe:rememberMe,code:code},
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log(result);
                        if (result.code == 0) {
                            layer.msg(result.message);
                            window.location.href="../view/getIndex";
                        }
                        else {
                            layer.msg(result.message);
                            Loginhandle.changeCode();
                        }
                        Loginhandle.clean();
                    },
                    error: function (result) {
                        layer.msg(result.message);
                    }
                });
            },
            clean:function(){
                $("#LoginForm")[0].reset();
            },
            changeCode:function () {
                $.ajax({
                    type: 'POST',
                    url: "../login/changeCode",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if (result.code == 0) {
                            //layer.msg(result.result);
                            $("#code").html(result.result);
                        }
                        else {
                            layer.msg(result.message);
                        }
                    },
                    error: function (result) {
                        layer.msg(result.message);
                    }
                });
            }
        }
    </script>
    <style type="text/css">
        * { margin: 0; padding: 0; }
        html { height: 100%; }
        body { height: 100%;background: #fff url(../public/img/timg.jpg) no-repeat center center fixed; background-size: cover;}

        #LoginForm{
            color: white;
            font-size: 20px;
            font-family: '微软雅黑';
        }
        .login_from{
            box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
            opacity: 1;
            transition-property: transform,opacity,box-shadow,top,left;
            transition-duration: .5s;
            transform-origin: 161px 100%;
            transform: rotateX(0deg);
            position: absolute;
            left: 5%;
            right: 0;
            margin: auto;
            top: 0;
            bottom: 0;
            padding: 100px 40px 40px 40px;
            background: linear-gradient(230deg, rgba(53, 57, 74, 0) 0%, rgb(0, 0, 0) 100%);
            width:600px;
            height: 300px;
          /*  background-color: #fff9;
            border:1px solid black;
            border-radius: 5px;
            table-layout: fixed;
            padding: 0 20px;
            width:600px;
            position: absolute;
            left: 57%;
            top: 25%;*/
        }
        #LoginForm{
            top: 45%;
        }
        .login_from-title{
            font-size: 30px;
            width: 300px;
            margin-left:150px;
            margin-bottom: 50px;
            text-align: center;
        }
        .login_from_item {margin-left: 10%;}
        .login_form_code{
            width: 100px;
            text-align: center;
            padding: 9px 0px;
            border:1px solid black;
            background-color: #67ffae;
        }
    </style>
</head>
<body>
<div class="login_from">
    <div class="layui-row ">
        <form class="layui-form" id="LoginForm">
            <div  class="login_from-title">
                <label >云停车管理平台</label>
            </div>
        <div class="layui-form-item login_from_item">
            <label class="layui-form-label">  <i class="layui-icon layui-icon-username"></i></label>
            <div class="layui-input-inline">
                <input type="text" name="userAccout" required  lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item login_from_item">
            <label class="layui-form-label">  <i class="layui-icon layui-icon-password"></i></label>
            <div class="layui-input-inline">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item login_from_item">
            <div class="layui-inline">
                <label class="layui-form-label"> <i class="layui-icon  layui-icon-vercode"></i></label>
                <div class="layui-input-inline">
                   <input type="text" name="code" required lay-verify="required" placeholder="验证码" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-input-inline">
                    <label class="layui-form-label login_form_code" id="code" onclick="Loginhandle.changeCode()"></label>
                </div>
            </div>
        </div>
        <div class="layui-form-item login_from_item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formLogin">登录</button>
                <button type="reset" class="layui-btn layui-btn-primary" style="margin-left: 50px">重置</button>
            </div>
        </div>
    </form></div>
</div>


</body>