<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>车主钱包充值记录</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/common.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        var payTypeList;
        var valueTypeList=[{id:1,name:'应付'},{id:2,name:'实付'},{id:3,name:'钱包余额'}];
        var parkList;
        var operTypeList=[{id:1,name:'充值'},{id:2,name:'冲正'}];
        layui.use(['table','form','layer','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;
            var laydate = layui.laydate;
            var laydate1 = layui.laydate;
            laydate.render({
                elem: '#beginDate'
                ,type: 'datetime'
            });
            laydate1.render({
                elem: '#endDate'
                ,type: 'datetime'
            });
            //第一个实例
            tableIns=table.render({
                elem: '#walletRechargeRecordTable'
                ,height: 560
                ,width:'full-200'
                ,url: '../tCarownerPayment/getTCarownerPaymentbyPage' //数据接口
                ,method:'post'
                ,toolbar: '#RecordToolbar'
                ,defaultToolbar: ['filter',  'print']
                ,loading:true
                ,totalRow: true
                ,title:'钱包充值记录'
                ,page: true //开启分页
                ,cols: [[ //表头
                    //{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'}
                     {type: 'numbers', title: '序号', width:50, fixed: 'left',  totalRowText: '合计'}
                    ,{field: 'carplate', title: '车牌',align: 'center', width: 110}
                    ,{field: 'needPay', title: '应付',align: 'right', width: 100,totalRow: true}
                    ,{field: 'actualPay', title: '实付',align: 'right', width: 100,totalRow: true}
                    ,{field: 'difference', title: '差额',align: 'right', width: 100,templet:function (d) {
                            return common.accSub(d.needPay,d.actualPay);
                        },totalRow: true}
                    ,{field: 'walletBanance', title: '钱包余额',align: 'right', width: 100,totalRow: true}
                    ,{field: 'operType', title: '操作类型',align: 'center', width:90,templet:'#operTypeTemp'}
                    ,{field: 'payTime', title: '支付时间', width: 160, align:'center'}
                    ,{field: 'payType', title: '充值方式',align: 'center', width:90,templet:'#payTypeTemp'}
                    ,{field: 'operator', title: '操作员',align: 'center', width:90}
                    ,{field: 'remark', title: '备注',align: 'center', width:80}
                ]]
                ,done:function (res) {
                    var differenceTotal =0;
                    layui.each(res.data,function(index,d){
                        differenceTotal = common.accAdd(differenceTotal,common.accSub(d.needPay,d.actualPay))
                    });
                    // console.log("differenceTotal: ",differenceTotal);
                    this.elem.next().find('.layui-table-total td[data-field="difference"] .layui-table-cell').text(differenceTotal);
                }
            });
            //头工具栏事件
            table.on('toolbar(walletRechargeRecordTable)', function(obj){
                switch(obj.event){
                    case 'RecordExcel':
                        var beginDate= $('#beginDate').val();
                        var endDate= $('#endDate').val();
                        var data={beginDate:beginDate,endDate:endDate};
                        rechargeForm.RecordExcel(data);
                        break;
                    default:
                };

            });
            //监听工具条
            table.on('tool(walletRechargeRecordTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

            });

            //监听查询按钮
            form.on('submit(selectRecord)', function(data){
/*                console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                Recordhandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            Recordhandle.getValueTypeList();
            form.render(); //更新全部
        });
        //获取圆圈中的数据


        //在页面加载完成后执行
        $(document).ready(function () {
            common.getPayTypeList();
        })
        var Recordhandle={
            select:function (obj) {
                // console.log(obj);
                //这里以搜索为例
                tableIns.reload({
                    where:  //设定异步数据接口的额外参数，任意设
                        obj,
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            getValueTypeList:function(){
                var str=" <option value>选择金额</option>";
                valueTypeList.forEach(function (t) {
                    str+="<option value="+t.id+">"+t.name+"</option>";
                });
                $("select[name='valueType']").html(str);
            }
            };

        var rechargeForm={
            clean:function(){
                $("#rechargeForm")[0].reset();
            },
            RecordExcel:function (object) {
            window.location.href="../Base/exportListWalletRechargeRecord?beginDate="+object.beginDate+"&endDate="+object.endDate+"";
        }
        };

    </script>

    <script type="text/html" id="operTypeTemp">
        {{# layui.each(operTypeList,function(index,item){ }}
        {{# if(item.id===d.operType){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="payTypeTemp">
        {{# layui.each(payTypeList,function(index,item){ }}
        {{# if(item.id===d.payType){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="RecordToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="RecordExcel">导出钱包充值记录</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">钱包充值记录</blockquote>
<form class="layui-form" style="margin: 10px" id="rechargeForm" lay-filter="rechargeForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">充值方式</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select id="payTypeSelect" name="payType" lay-filter="payType">
                </select>
            </div>
            <label class="layui-form-label" style="text-align: left;width: 80px">支付时间</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="payTimeMin" id="beginDate" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label" style="width: 80px">至</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="payTimeMax" id="endDate" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
           <label class="layui-form-label">车牌</label>
           <div class="layui-input-inline" style="width: 200px;">
               <input type="text" id="carplate" name="carplate" placeholder="车牌" autocomplete="off" class="layui-input">
           </div>
            <div class="layui-input-inline" style="width: 100px;text-align: left">
                <select id="valueType" name="valueType" lay-filter="valueType">
                </select>
            </div>
            <div class="layui-input-inline" style="width: 90px;">
                <input type="text" name="priceMin" placeholder="￥" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid">-</div>
            <div class="layui-input-inline" style="width: 90px;">
                <input type="text" name="priceMax" placeholder="￥" autocomplete="off" class="layui-input">
            </div>
           <div class="layui-input-inline" style="width: 200px;margin-left: 110px">
               <button class="layui-btn"  lay-submit lay-filter="selectRecord" >查询</button>
           </div>
        </div>
    </div>
</form>
<table id="walletRechargeRecordTable" lay-filter="walletRechargeRecordTable"></table>
</body>

</html>