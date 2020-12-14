<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>电子支付对账</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <link rel="stylesheet" href="../css/common.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;

        layui.use(['table','form','layer','laydate'], function() {
                    var table = layui.table;
                    var laydate = layui.laydate;
                    var form=layui.form;
            //执行一个laydate实例
            laydate.render({
                elem: '#beginTime' //指定元素
            });
            laydate.render({
                elem: '#endTime' //指定元素
            });
            //第一个实例
            tableIns=table.render({
                elem: '#PaymentTable'
                ,height:600
                ,url: '../parkReport/getElectronPaymentbyPage' //数据接口
                ,method:'post'
                ,toolbar: '#ElectronicToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'createDate', title: '账单日期', width:120}
                    ,{field: 'tempOnlineTotal', title: '临时车电子支付实收', width:200}
                    ,{field: 'fixOnlineActualTotal', title: '月租车电子支付实收', width: 200}
                    ,{field: 'tempQpasspayTotal', title: '总聚合', width: 120}
                    ,{field: 'tempPrepayTotal', title: '总预缴', width: 120}
                    ,{field: 'checkFlag', title: '确认标识', width: 120,templet:'#flagTemp'}
                    ,{fixed: 'right', title: '操作', width: 200, toolbar: '#paymentBar'}
                ]]
                ,done: function(res, curr, count){
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                    // console.log(res);
                    //得到当前页码
                    // console.log(curr);
                    //得到数据总量
                    // console.log(count);
                }
            });
            //头工具栏事件
            table.on('toolbar(PaymentTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'ElectronicExcel':
                        var beginTime=$("#beginTime").val();
                        var endTime=$("#endTime").val();
                        var checkFlag=$("#checkFlag").val();
                        var data={beginTime:beginTime,endTime:endTime,checkFlag:checkFlag}
                        PaymentHandle.ElectronicExcel(data);
                        break;
                    default:
                };
            });
            //监听工具条
            table.on('tool(PaymentTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    form.val("electronicForm", data);
                   PaymentForm.detail();
                }
              /*  else if(layEvent === 'edit'){
                    data.checkFlag=1;
                    PaymentHandle.save(data);
                }else if(layEvent === 'cancelEdit'){
                    data.checkFlag=0;
                    PaymentHandle.save(data);
                }*/
            });
            //监听查询按钮
            form.on('submit(SelectPayment)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回*/
                // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                PaymentHandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            form.on('submit(electronicBtn)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回*/
                // console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                data.field.checkFlag=1;
                PaymentHandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            PaymentForm.getFlag(form);
            form.render(); //更新全部
        });
        //在页面加载完成后执行
        $(document).ready(function () {

        });
        var PaymentHandle={
            select:function (data) {
                //这里以搜索为例
                tableIns.reload({
                    where:data
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            save:function(object){
                $.ajax({
                    type: 'POST',
                    url: "../parkReport/updateElectronPayment",
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
            ElectronicExcel:function (object) {
                window.location.href="../Base/exportListforElectronic?beginTime="+object.beginTime+"&endTime="+object.endTime+"&checkFlag="+object.checkFlag;
            }
        }
        var FlagLists;
        var PaymentForm={
            //获取确认标识
            getFlag:function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getFlagLists",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            FlagLists=result.result;
                            var str=" <option value>请选择</option>";
                            FlagLists.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("#checkFlag").html(str);
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
            //电子对账单详情
            detail:function () {
                layer.open({
                    title: '电子对账单详情'
                    , type:1
                    , area: ['800px', '500px']
                    , content: $('#electronicForm')
                });
            }
        }
    </Script>
    <script type="text/html" id="ElectronicToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="ElectronicExcel">导出电子对账单信息</button>
        </div>
    </script>
    <script type="text/html" id="paymentBar">
        <a class="layui-btn layui-btn-xs" lay-event="detail">查看明细</a>
        <#--{{# if(d.checkFlag===0){ }}
        <a class="layui-btn layui-btn-xs" lay-event="edit">确认</a>
        {{# }else if(d.checkFlag===1){ }}
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="cancelEdit">取消确认</a>
        {{# } }}-->
    </script>
    <script type="text/html" id="flagTemp">
        {{# layui.each(FlagLists,function(index,item){ }}
        {{# if(item.id===d.checkFlag){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">电子支付对账 </blockquote>
<div class="layui-form layui-form-pane" style="margin: 10px">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">确认标识：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select id="checkFlag" name="checkFlag" >
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">日期：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" id="beginTime" name="beginTime">
            </div>
            <div class="layui-form-mid">至</div>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" id="endTime" name="endTime">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn" lay-submit lay-filter="SelectPayment" >查询</button>
            </div>
        </div>
    </div>
</div>
<table id="PaymentTable" lay-filter="PaymentTable"></table>
</body>
<form class="layui-form" id="electronicForm"  lay-filter="electronicForm" style="display: none">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">微信预缴</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="wechatPrepayTotal" disabled autocomplete="off" class="layui-input input_noBorder">

            </div>
            <label class="layui-form-label" style="width: 100px;">支付宝预缴</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input  type="text" name="alipayPrepayTotal" disabled autocomplete="off" class="layui-input input_noBorder">

            </div>
            <label class="layui-form-label" style="width: 100px;">第三方预缴</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input  type="text" name="thirdPrepayTotal" disabled autocomplete="off" class="layui-input input_noBorder">
            </div>

        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">月租微信支付</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input  type="text" name="monthlyWechatTotal" disabled autocomplete="off" class="layui-input input_noBorder">
            </div>
            <label class="layui-form-label" style="width: 100px;">银联无感支付</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input  type="text" name="tempUnionpayTotal" disabled autocomplete="off" class="layui-input input_noBorder">
            </div>
            <label class="layui-form-label" style="width: 100px;">银联卡支付</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input  type="text" name="tempCardTotal" disabled autocomplete="off" class="layui-input input_noBorder">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label" style="width: 100px;">银联卡权益</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input  type="text" name="tempRightsTotal" disabled autocomplete="off" class="layui-input input_noBorder">
            </div>
            <label class="layui-form-label" style="width: 100px;">微信付款码支付</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input  type="text" name="tempWechatTotal" disabled autocomplete="off" class="layui-input input_noBorder">
            </div>
            <label class="layui-form-label" style="width: 120px;">支付宝付款码支付</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input  type="text" name="tempAlipayTotal" disabled autocomplete="off" class="layui-input input_noBorder">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">少付补款</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="payLessTotal"  placeholder="少付补款" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">多付退款</label>
            <div class="layui-input-inline" style="width: 100px;">
                <input type="text" name="payMoreTotal"  placeholder="多付退款" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">对账说明</label>
        <div class="layui-input-block">
            <textarea name="reconciliationStatement" required lay-verify="required" placeholder="对账说明" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="electronicBtn">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</html>