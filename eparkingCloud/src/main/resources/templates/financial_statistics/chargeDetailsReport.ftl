<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>分类统计报表</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script src="../js/common.js"></script>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table','form','layer','laydate'], function() {
            var table = layui.table;
            var form =layui.form;
            var laydate = layui.laydate;
            var layer = layui.layer;

            //执行一个laydate实例
            laydate.render({
                elem: '#beginDate' //指定元素
            });
            laydate.render({
                elem: '#endDate' //指定元素
            });
            //第一个实例
            tableIns=table.render({
                elem: '#chargeReportTable'
                ,height: 600
                ,url: '../tChargeDetailsReport/getTChargeDetailsReportbyPage' //数据接口
                ,method:'post'
                ,toolbar: '#chargeReportToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,totalRow: true
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:50, fixed: 'left',  totalRowText: '合计'}
                    ,{field: 'parkId', title: '停车场名称',templet:'#forParkList',align:'center', width:130}
                    ,{field: 'chargeDate', title: '日期', width: 160, align:'center'}
                    ,{field: 'carNatureDesc', title: '车辆性质', width: 160, align:'center'}
                    ,{field: 'chargeAmount', title: '次数',align:'right', width:80,totalRow: true}
                    ,{field: 'needpayTotal', title: '总应收', align:'right', width:100,totalRow: true}
                    ,{field: 'actualpayTotal', title: '总现金', align:'right', width:100,totalRow: true}
                    ,{field: 'qpasspayTotal', title: '总聚合', align:'right', width:100,templet:function (d) {
                            return Number(d.qpasspayTotal) - Number(d.walletpayTotal);
                        },totalRow: true}
                    ,{field: 'walletpayTotal', title: '钱包支付', align:'right', width:100,totalRow: true}
                    ,{field: 'inaccesspaidTotal', title: '支付成功不开杆', align:'right', width:130,totalRow: true}
                    ,{field: 'prepayTotal', title: '总预缴', align:'right', width:80,totalRow: true}
                    ,{field: 'behalfpayTotal', title: '总代缴',align:'right', width:80,totalRow: true}
                    ,{field: 'couponTotal', title: '总减免', align:'right', width:100,totalRow: true}
                    ,{field: 'difference', title: '总差额',align: 'right', width: 100,templet:function (d) {
                            return chargeReportForm.numSub(d.needpayTotal,d.actualpayTotal,d.qpasspayTotal,d.prepayTotal,d.behalfpayTotal,d.couponTotal);
                            // return Number(d.needpayTotal) - Number(d.actualpayTotal) - Number(d.qpasspayTotal) - Number(d.prepayTotal) - Number(d.behalfpayTotal) - Number(d.couponTotal);
                        },totalRow: true}
                    ,{field: 'createTime', title: '创建时间',align:'center', width:160}
/*                    ,{field: 'outPortId', title: '出口岗亭',align:'center', width:120}
                    ,{field: 'dutyPerson', title: '当班员',align:'center', width:80}*/
                ]]
                ,done:function (res) {
                    var differenceTotal =0;
                    var qpasspayTotal = 0;
                    layui.each(res.data,function(index,d){
                        // differenceTotal+=Number(d.needpayTotal) - Number(d.actualpayTotal) - Number(d.qpasspayTotal) - Number(d.prepayTotal) - Number(d.behalfpayTotal) - Number(d.couponTotal)
                        differenceTotal = common.accAdd(differenceTotal,chargeReportForm.numSub(d.needpayTotal,d.actualpayTotal,d.qpasspayTotal,d.prepayTotal,d.behalfpayTotal,d.couponTotal));
                        qpasspayTotal += Number(d.qpasspayTotal) - Number(d.walletpayTotal);
                    });
                    // console.log("differenceTotal: ",differenceTotal);
                    this.elem.next().find('.layui-table-total td[data-field="difference"] .layui-table-cell').text(differenceTotal);
                    this.elem.next().find('.layui-table-total td[data-field="qpasspayTotal"] .layui-table-cell').text(qpasspayTotal);
                }
            });
            //头工具栏事件
            table.on('toolbar(chargeReportTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                var beginDate=$("#beginDate").val();
                var endDate=$("#endDate").val();
                var data={beginDate:beginDate,endDate:endDate};
                // console.log(data);
                switch(obj.event){
                    case 'chargeReportExcel':
                        chargeReportForm.chargeReportExcel(data);
                        break;
                    case 'chargeReportStatistics':
                        if(beginDate!=null && endDate!=null && beginDate!="" && endDate!=""){
                            chargeReportForm.chargeReportStatistics(data);
                        }else if(beginDate==null || beginDate==""){
                            layer.msg("请输入开始时间");
                        }else{
                            layer.msg("请输入结束时间");
                        }
                        break;
                    default:
                }
            });

            //监听查询按钮
            form.on('submit(selectRechargeReport)', function(data){
                //console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                //console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                chargeReportForm.selectRechargeReport(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            form.render(); //更新全部
        });

        //页面加载初始化
        $(document).ready(function () {
            chargeReportForm.getParkList();
        })
        var parkList;
        //车场更新表单使用方法
        var chargeReportForm={
            //获取停车场集合
            getParkList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarPark",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            parkList= result.result;
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
            },
            chargeReportExcel:function (object) {
                window.location.href="../Base/exportListforChargeReport?beginDate="+object.beginDate+"&endDate="+object.endDate;
            },
            selectRechargeReport: function (obj) {
                //这里以搜索为例
                tableIns.reload({
                    where:
                    obj,
                    page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            chargeReportStatistics:function (object) {
                $.ajax({
                    type: 'POST',
                    url: "../tChargeDetailsReport/chargeReportStatistics",
                    data: object,
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            layer.msg(result.result);
                            chargeReportForm.selectRechargeReport();
                        }
                    },
                    error: function (result) {
                        console.log(result);
                    }
                })
            },
            numSub :function (num1, num2, num3, num4, num5, num6) {
                var baseNum, baseNum1, baseNum2, baseNum3, baseNum4, baseNum5, baseNum6;
                var precision;// 精度
                try {
                    baseNum1 = num1.toString().split(".")[1].length;
                } catch (e) {
                    baseNum1 = 0;
                }
                try {
                    baseNum2 = num2.toString().split(".")[1].length;
                } catch (e) {
                    baseNum2 = 0;
                }
                try {
                    baseNum3 = num3.toString().split(".")[1].length;
                } catch (e) {
                    baseNum3 = 0;
                }
                try {
                    baseNum4 = num4.toString().split(".")[1].length;
                } catch (e) {
                    baseNum4 = 0;
                }
                try {
                    baseNum5 = num5.toString().split(".")[1].length;
                } catch (e) {
                    baseNum5 = 0;
                }
                try {
                    baseNum6 = num6.toString().split(".")[1].length;
                } catch (e) {
                    baseNum6 = 0;
                }
                baseNum = Math.pow(10, Math.max(baseNum1, baseNum2, baseNum3, baseNum4, baseNum5, baseNum6));
                // precision = (baseNum1 >= baseNum2) ? baseNum1 : baseNum2;
                precision = 2;
                return ((num1 * baseNum - num2 * baseNum - num3 * baseNum - num4 * baseNum - num5 * baseNum - num6 * baseNum) / baseNum)
                    .toFixed(precision);
            }
        }
    </Script>
    <script type="text/html" id="chargeReportToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="chargeReportExcel">导出分类统计报表</button>
            <button class="layui-btn layui-btn-sm" lay-event="chargeReportStatistics">报表统计</button>
        </div>
    </script>

    <script type="text/html" id="forParkList">
        {{# layui.each(parkList,function(index,item){ }}
        {{# if(item.id===d.parkId){ }}
        {{ item.parkName }}
        {{# return; }}
        {{# }  }}
        {{#  }); }}
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">分类统计报表</blockquote>
<form class="layui-form " style="margin: 10px">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">出场时间：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" name="beginDate" id="beginDate" placeholder="yyyy-MM-dd">
            </div>
            <div class="layui-form-mid">至</div>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" name="endDate" id="endDate" placeholder="yyyy-MM-dd">
            </div>
            <button class="layui-btn" lay-submit lay-filter="selectRechargeReport" >查询</button>
<#--            <label class="layui-form-label">当班员：</label>
            <div class="layui-input-inline" style="width: 200px;">
                &lt;#&ndash;<input type="text" name="price_max" placeholder="当班员" autocomplete="off" class="layui-input">&ndash;&gt;
                <input type="text" name="dutyPerson" id="dutyPerson" placeholder="当班员" autocomplete="off" class="layui-input">
            </div>-->
        </div>
    </div>
<#--    <div class="layui-form-item">-->
<#--        <div class="layui-inline">-->
<#--            <label class="layui-form-label">出场通道</label>-->
<#--            <div class="layui-input-inline" style="width: 200px;">-->
<#--                <select id="outPortName" name="outPortId" lay-filter="outPortId">-->
<#--                </select>-->
<#--            </div>-->
<#--            <div class="layui-input-inline" style="width: 200px;margin-left: 345px">-->
<#--                -->
<#--            </div>-->
<#--        </div>-->
<#--    </div>-->

</form>
<table id="chargeReportTable" lay-filter="chargeReportTable"></table>
</body>
</html>