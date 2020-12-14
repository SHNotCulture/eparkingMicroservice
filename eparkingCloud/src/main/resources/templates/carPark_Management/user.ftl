<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>操作员管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        layui.use(['table','form','element'], function() {
            var table = layui.table;
            var form = layui.form;
            var element = layui.element;
            //一些事件监听
            element.on('tab(tabUser)', function (data) {
                // console.log(data);
            });
        });
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">操作员管理</blockquote>
<div class="layui-tab" lay-filter="tabUser">
    <ul class="layui-tab-title">
        <li class="layui-this">角色</li>
        <li>用户</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show"><iframe src="../view/role" style="height:650px;width:98%"></iframe></div>
        <div class="layui-tab-item"><iframe src="../view/companyUser" style="height:650px;width:98%"></iframe></div>
    </div>
</div>
</body>
</html>