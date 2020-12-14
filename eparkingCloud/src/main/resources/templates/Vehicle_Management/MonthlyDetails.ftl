<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登记车缴费记录</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns,payRuleList;
        layui.use(['table','form','layer','laydate'], function(){
            var table = layui.table;
            var form = layui.form;
            var laydate1 = layui.laydate;
            var laydate2 = layui.laydate;
            DetailsHandle.getCarPayRule(form);
            laydate1.render({
                elem: '#payTimeBegin'
                ,type: 'date'
            });
            laydate2.render({
                elem: '#payTimeEnd'
                ,type: 'date'
            });
            tableIns=table.render({
                elem: '#carPaymentTable'
                ,height: 600
                ,url: '../carPayment/getCarPayment' //数据接口
                ,method:'post'
                ,toolbar: true
                ,toolbar: '#toolBarMonthlyDetails'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,title:'登记车缴费记录'
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:50, fixed: 'left'}
                    ,{field: 'carplate', title: '车牌',align: 'center', width: 110}
                    ,{field: 'beginDate', title: '开始时间', width:120, align:'center'}
                    ,{field: 'endDate', title: '截止时间', width:120, align:'center'}
                    ,{field: 'actualPay', title: '金额', width: 80, align:'center', sort: true}
                    ,{field: 'operType', title: '支付类型', width: 105, align:'center', sort: true,templet:'#operTypeTemp'}
                    ,{field: 'payRule', title: '登记车规则',align: 'left', width: 120, sort: true,templet:'#payRuleTemp'}
                    ,{field: 'payTime', title: '支付时间', width: 160, align:'center', sort: true}
                ]]
            });
            table.on('toolbar(carPaymentTable)', function(obj){
                var payRule = $('#payRuleform option:selected').val();
                var beginData= $('#payTimeBegin').val();
                var endData= $('#payTimeEnd').val();
                var carplate = $('#carplate').val();
                var data={carplate:carplate,payRule:payRule,beginData:beginData,endData:endData};
                // console.info(data);
                DetailsHandle.MonthlyDetailsExcel(data);
            });
            form.on('submit(selectDetailsForm)',function (data) {
                DetailsHandle.select(data.field);
                return false;
            });

            form.render(); //更新全部
        });
        $(document).ready(function () {

        });

        var DetailsHandle={
            getCarPayRule:function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../carPayRule/getPayRule",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            payRuleList= result.result;
                            var str="<option value>请选择登记车规则</option>";
                            payRuleList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.ruleName+"</option>"
                            });
                            $("select[name='payRule']").html(str);
                        }
                        form.render();
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            select:function (obj) {
                //这里以搜索为例
                // console.log(obj);
                tableIns.reload({
                    where:  //设定异步数据接口的额外参数，任意设
                    obj,
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            MonthlyDetailsExcel:function (obj) {
                window.location.href="../Base/exportListMonthlyDetails?carplate="+obj.carplate+"&payRule="+obj.payRule+"&beginData="+obj.beginData+"&endData="+obj.endData+"";
            }
        }

    </script>
    <script type="text/html" id="operTypeTemp">
        {{# if(d.operType===1){ }}
        {{ '充值' }}
        {{# }if(d.operType===2){ }}
        {{ '冲正' }}
        {{# } }}
    </script>
    <script type="text/html" id="payRuleTemp">
        {{# layui.each(payRuleList,function(index,item){ }}
        {{# if(item.id===d.payRule){ }}
        {{ item.ruleName }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="toolBarMonthlyDetails">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="MonthlyDetailsExcel">导出缴费记录</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">登记车缴费记录详情</blockquote>
<form class="layui-form" style="margin: 10px" id="DetailsForm" lay-filter="DetailsForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">登记车规则</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select  name="payRule" id="payRuleform" lay-filter="payRule">
                </select>
            </div>
            <label class="layui-form-label">支付时间</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="payTimeBegin" id="payTimeBegin"  placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label"style="width: 30px">至</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="payTimeEnd" id="payTimeEnd"  placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">车牌</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" id="carplate" name="carplate" placeholder="可模糊搜索" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 200px;margin-left: 110px">
                <button class="layui-btn"  lay-submit lay-filter="selectDetailsForm" >查询</button>
            </div>
        </div>
    </div>
</form>
<table id="carPaymentTable" lay-filter="carPaymentTable"></table>
</body>
</html>