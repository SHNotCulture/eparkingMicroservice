<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>二维码优惠查询</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script src="../js/jQuery1.9.1/jquery.min.js"></script>
    <script src="../js/jQuery1.9.1/qrcode.js"></script>
    <style type="text/css">
       /* #carNum{
            height:80px;
            border-radius: 5px;
    }*/
        ul{
            display: flex;
            justify-content:space-around;
            overflow: hidden;
        }
    ul li{
        margin-top:8px;
    }
    /*ul li .carStyle{
        width:180px;
        height:65px;
        border-radius: 5px;
    }*/
    /*.carL{
        text-align: center;
        width:45px;
        height:80px;
        display:inline-block;
    }*/
    .carL span{
        display: block;
        margin:4px 0 0 10px;
        width:16px;
        word-wrap: break-word;
        font-size:5px;
        font-weight: bold;
        color:white;
    }
   /* .cNum{
        font-size:30px;
        font-weight: bold;
        text-align: center;
        margin-left:40px;
    }
    .Btn{
        display: flex;
        justify-content:space-around;
        overflow: hidden;
    }*/
    .Btn button{
        width:100px;
        height:30px;
        margin:10px 15px 10px 15px;
        text-align: center;
        color:white;
        font-weight: bold;
        font-size:18px;
        border-radius: 3px;
        border:none;
        cursor: pointer;
    }

    </style>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table','form','layer','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;
            var laydate = layui.laydate;
            laydate.render({
                elem: '#validTime'
                ,type: 'date'
            });
            //第一个实例
            tableIns=table.render({
                elem: '#CouponQrcodeRecordsTable'
                ,height: 650
                ,url: '../couponQrcodeRecords/getCouponQrcodeList' //数据接口
                ,method:'post'
                // ,toolbar: true
                ,toolbar: '#CouponQrcodeRecordsToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,title:'二维码优惠查询'
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'qrcodeName', title: '二维码名称', width:170, align:'center'}
                    ,{field: 'couponType', title: '优惠类型', width: 190, align:'center',templet:'#couponTypeTemp'}
                    ,{field: 'couponPay', title: '优惠数量', width: 190, align:'center'}
                    ,{field: 'triesLimit', title: '限制次数', width: 190, align:'center'}
                    ,{field: 'useLimit', title: '剩余次数', width:140, align:'center'}
                    ,{field: 'createTime', title: '创建时间', width:170, align:'center'}
                    ,{fixed: 'right',title: '操作', width: 200, toolbar: '#couponQrcodeBar'}
                ]]
            });

            //监听工具条
            table.on('tool(CouponQrcodeRecordsTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log("data"+data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                //var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    $("#qrcodeDetail").html('');
                    CouponQrcodeRecordshandle.createQrcodeDetail(data.qrcodeUrl,'qrcodeDetail');
                    $("#qrcodeDetail").attr("title",""); //去掉二维码图片的悬停显示
                    layer.open({
                        title: '查看优惠二维码'
                        ,type: 1
                        ,area:['350px','350px']
                        ,content: $('#qrcodeDetail')
                    });

                } else if(layEvent === 'del'){ //删除
                    layer.confirm('真的删除行么', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        CouponQrcodeRecordshandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑

                    switch(obj.data.couponType)
                    {
                        case 0:case 2:
                            $('#tipAmount').text("减免金额");
                            $('#tipUnit').text("(元)");
                            break;
                        case 1:
                            $('#tipAmount').text("减免时间");
                            $('#tipUnit').text("(分钟)");
                            break;

                        default:
                            break;
                    };
                    form.val("couponQrcodeForm", data);
                    CouponQrcodeRecordshandle.showEditForm(1);//弹出车场更新窗口
                }
            });



            //监听更新按钮
            form.on('submit(conponQrcodeUpdate)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                var qrcodeUrl=data.field.qrcodeUrl;
                var triesLimit=data.field.triesLimit;
                var useLimit=data.field.useLimit;
                if (qrcodeUrl!="" && triesLimit!="" && useLimit!=""){
                    CouponQrcodeRecordshandle.conponQrcodeUpdate(data.field);
                    //console.log(data.field);
                }
                else{
                    layer.msg("二维码信息不能为空");
                }
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });

            //监听查询按钮
            form.on('submit(SelectCouponQrcodeRecords)', function(data){
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                CouponQrcodeRecordshandle.SelectCouponQrcodeRecords(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });

            form.render(); //更新全部
        });

        //在页面加载完成后执行
        $(document).ready(function () {
            $("input[name='triesLimit']").bind('input propertychange',function () {
                $("input[name='useLimit']").val($(this).val());
            });
        })


        var CouponQrcodeRecordshandle= {
            //生成二维码图片方法
            createQrcodeDetail:function(url,dom){
                var qrcode = new QRCode(dom, {
                    text: url,
                    width: 128,
                    height: 128,
                    colorDark : '#000000',
                    colorLight : '#ffffff',
                    correctLevel : QRCode.CorrectLevel.H
                });
            },

            //限制输入栏仅能输入数字和小数点
            check:function (e) {
                var re = /^\d+(?=\.{0,1}\d+$|$)/
                if (e.value != "") {
                    if (!re.test(e.value)) {
                        alert("请输入正确的数字");
                        e.value = "";
                        e.focus();
                    }
                }
            },
            showEditForm:function (type) {
                if(type==0){//新增
                    null;
                }else{
                    layer.open({
                        title: '更新优惠二维码'
                        , type: 1
                        ,area:['400px','400px']
                        ,content: $('#couponQrcodeForm')
                    });
                }
            },
            SelectCouponQrcodeRecords: function (obj) {
                //这里以搜索为例
                tableIns.reload({
                    where:
                    obj,
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            conponQrcodeUpdate:function(object){
                $.ajax({
                    type: 'POST',
                    url: "../couponQrcodeRecords/updateCouponQrcode",
                    data:object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if(result.code==0)
                        {
                            if (result.result=='更新成功') {
                                layer.closeAll();
                                layer.msg(result.result, {icon: 6});
                                tableIns.reload();
                            }else if(result.result=='新增成功'){null;
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
                    url: "../couponQrcodeRecords/deleteCouponQrcode",
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
        }

    </script>

    <script type="text/html" id="couponQrcodeBar">
        <a class="layui-btn layui-btn-xs" lay-event="detail">查看二维码</a>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>

    <#--    电子优惠券优惠类型(0/金额 1/时间)-->
    <script type="text/html" id="couponTypeTemp">
        {{# if(d.couponType===0){ }}
        {{ '限定金额' }}
        {{# }if(d.couponType===1){ }}
        {{ '时间' }}
        {{# }if(d.couponType===2){ }}
        {{ '固定金额' }}
        {{# }if(d.couponType===3){ }}
        {{ '电子券' }}
        {{# } }}
    </script>

</head>
<body>
<blockquote class="layui-elem-quote">二维码管理</blockquote>

<form class="layui-form" style="margin: 10px" id="CouponQrcodeRecordsForm" lay-filter="CouponQrcodeRecordsForm">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">二维码名称</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" id="qrCode" name="qrcodeName" placeholder="可模糊搜索" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn"  lay-submit lay-filter="SelectCouponQrcodeRecords" id="SelectCouponQrcodeRecords">查询</button>
            </div>
        </div>
    </div>
</form>
<table id="CouponQrcodeRecordsTable" lay-filter="CouponQrcodeRecordsTable"></table>
</body>
<form class="layui-form"  style="display: none" id="couponQrcodeForm" lay-filter="couponQrcodeForm">

    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" id="tipAmount" style="width: 100px">优惠数量</label>
        <div class="layui-input-inline">
            <input type="text" name="couponPay" id="couponPay" autocomplete="off" class="layui-input" onblur="CouponQrcodeRecordshandle.check(this)"  onkeyup="this.value=this.value.replace(/[^0-9.]/g,'')">
        </div>
        <label class="layui-form-label" id="tipUnit" style="text-align: left;width: 40px"></label>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">二维码名称</label>
        <div class="layui-input-inline">
            <input type="text" id="qrcodeName" name="qrcodeName" autocomplete="off" placeholder="输入电子券名称" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">限制次数</label>
        <div class="layui-input-inline">
            <input type="text" id="triesLimit" name="triesLimit" autocomplete="off" class="layui-input" value="0">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">剩余次数</label>
        <div class="layui-input-inline">
            <input type="text" id="useLimit" name="useLimit" disabled="disabled" autocomplete="off" class="layui-input" value="0">
        </div>
    </div>
    <label class="layui-form-label" style="width: 100px">有效时间</label>
    <div class="layui-input-inline">
        <input type="text" name="validTime" id="validTime"  placeholder="yyyy-MM-dd" readonly="" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label"></label>
        <div class="layui-input-inline">
            <button class="layui-btn" lay-submit lay-filter="conponQrcodeUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
<div id="qrcodeDetail" style="margin-left: 100px;margin-top: 50px;display: none;"></div>
</html>