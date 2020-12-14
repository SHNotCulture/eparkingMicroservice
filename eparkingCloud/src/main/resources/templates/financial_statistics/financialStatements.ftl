<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>财务报表统计</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <style type="text/css">
        #carNum{
            height:80px;
            border-radius: 5px;
        }
        ul{
            display: flex;
            justify-content:space-around;
            overflow: hidden;
        }
        ul li{
            margin-top:8px;
        }
        ul li .carStyle{
            width:180px;
            height:65px;
            border-radius: 5px;
        }
        .carL{
            text-align: center;
            width:45px;
            height:80px;
            display:inline-block;
        }
        .carL span{
            display: block;
            margin:4px 0 0 10px;
            width:16px;
            word-wrap: break-word;
            font-size:5px;
            font-weight: bold;
            color:white;
        }
        .cNum{
            font-size:30px;
            font-weight: bold;
            text-align: center;
            margin-left:40px;
        }
    </style>
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
                elem: '#parkReportTable'
                ,height: 565
                ,url: '../parkReport/getParkReport' //数据接口
                ,method:'post'
                ,toolbar: '#parkReportToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,totalRow: true
                ,title:'财务报表'
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:50, fixed: 'left'}
                    ,{field: 'createDate', title: '账单日期',align: 'center', width: 120}
                    ,{field: 'tempNeedpayTotal', title: '总应收',align:'right', width:80,totalRow: true}
                    ,{field: 'tempCashActualTotal', title: '总现金',align:'right', width:80,totalRow: true}
                    ,{field: 'tempBehalfpayTotal', title: '总代缴',align:'right', width:80}
                    ,{field: 'tempQpasspayTotal', title: '总聚合',align:'right', width:80,totalRow: true}
                    ,{field: 'tempPrepayTotal', title: '总预缴',align:'right', width:80}
                    // ,{field: 'tempCashNeedTotal', title: '临时车现金应收', width:200}
                    // ,{field: 'tempOnlineNeedTotal', title: '临时车电子支付应收', width: 200}
                    // ,{field: 'tempOnlineTotal', title: '临时车电子支付实收', width: 200}
                    ,{field: 'fixAdd', title: '新增登记车辆',align:'center', width: 115}
                    ,{field: 'fixCashNeedTotal', title: '登记车现金应收',align:'right', width:130}
                    ,{field: 'fixCashActualTotal', title: '登记车现金实收',align:'right', width:130}
                    ,{field: 'fixOnlineNeedTotal', title: '登记车电子支付应收',align:'right', width:160}
                    ,{field: 'fixOnlineActualTotal', title: '登记车电子支付实收',align:'right', width:160}
                    // ,{field: 'centerChargeTotal', title: '中央收费总额', width: 200}
                    ,{field: 'businessCircleTotal', title: '商圈收费总额',align:'right', width:130}
                    ,{field: 'freeTotal', title: '免费车数量',align:'center', width: 100}
                    ,{field: 'tempPayDiff', title: '临停差额',align:'right', width: 100,totalRow: true}
                ]]
                ,done: function(res, curr, count){
                    //如果是异步请求数据方式，res即为你接口返回的信息。
                    //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                  /*  console.log(res);

                    //得到当前页码
                    console.log(curr);

                    //得到数据总量
                    console.log(count);*/
                }
            });
            //头工具栏事件
            table.on('toolbar(parkReportTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                var beginTime=$("#beginTime").val();
                var endTime=$("#endTime").val();
                var type=$('input[name="type"]:checked ').val();
                var data={beginTime:beginTime,endTime:endTime,type:type}
                switch(obj.event){
                    case 'parkReportExcel':
                        //console.log(data);
                        ParkReportHandle.ParkReportExcel(data);
                        break;
                    case 'parkReportStatistics':
                        if(beginTime!=null && endTime!=null && beginTime!="" && endTime!=""){
                            ParkReportHandle.parkReportStatistics(data);
                        }else if(beginTime==null || beginTime==""){
                            layer.msg("请输入开始时间");
                        }else{
                            layer.msg("请输入结束时间");
                        }
                        break;
                    default:
                };
            });
            //监听查询按钮
            form.on('submit(SelectParkReport)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                ParkReportHandle.select(data.field);
                // ParkReportHandle.selectTotal(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            form.render(); //更新全部
        });
      var  ParkReportHandle={
            select:function (data) {
                //这里以搜索为例
                tableIns.reload({
                    where:data
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
          ParkReportExcel:function (object) {
              window.location.href="../Base/exportListforParkReport?beginTime="+object.beginTime+"&endTime="+object.endTime+"&type="+object.type;
          },
          parkReportStatistics:function (object) {
              $.ajax({
                  type: 'POST',
                  url: "../parkReport/parkReportStatistics",
                  data: object,
                  dataType: "JSON",
                  success: function (result) {
                      if(result.code==0)
                      {
                          layer.msg(result.result);
                          ParkReportHandle.select();
                      }
                  },
                  error: function (result) {
                      console.log(result);
                  }
              })
          }
/*          selectTotal:function (data) {
              $.ajax({
                  type: 'POST',
                  url: "../parkReport/getParkReportSum",
                  dataType: "JSON",
                  data:data,
                  success: function (result) {
                      if(result.code==0)
                      {
                          document.getElementById("tempBehalfpayTotal").innerHTML=result.result.tempBehalfpayTotal;
                          document.getElementById("tempCashActualTotal").innerHTML=result.result.tempCashActualTotal;
                          document.getElementById("tempNeedpayTotal").innerHTML=result.result.tempNeedpayTotal;
                          document.getElementById("tempOnlineTotal").innerHTML=result.result.tempOnlineTotal;
                          document.getElementById("tempPrepayTotal").innerHTML=result.result.tempPrepayTotal;
                          document.getElementById("tempQpasspayTotal").innerHTML=result.result.tempQpasspayTotal;
                          console.info(result.result);
                      }

                  },
                  error: function (result) {
                      console.log(result);
                  }
              })
          }*/
        }

    </Script>
    <script type="text/html" id="parkReportToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="parkReportExcel">导出财务报表信息</button>
            <button class="layui-btn layui-btn-sm" lay-event="parkReportStatistics">报表统计</button>
        </div>
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">财务报表</blockquote>
<div class="layui-form layui-form-pane" style="margin: 10px">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">报表类型：</label>
            <div class="layui-input-inline" style="width: 300px;">
                <input type="radio" name="type" value=0 title="日报" checked="">
                <input type="radio" name="type"  value=1 title="月报">
                <input type="radio" name="type"  value=2 title="年报">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">账单日期：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" id="beginTime" name="beginTime" placeholder="yyyy-MM-dd">
            </div>
            <div class="layui-form-mid">至</div>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" class="layui-input" id="endTime" name="endTime" placeholder="yyyy-MM-dd">
            </div>
        </div>
        <div class="layui-inline">
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn" lay-submit lay-filter="SelectParkReport" >查询</button>
            </div>
        </div>
    </div>
</div>
<#--<div  id="carNum">
    <ul>
        <li>
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>临停现金</span>
                </div>
                <span class="cNum" id="tempCashActualTotal">0</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #FBC88C;">
                <div class="carL" style="background:#FBC88C;">
                    <span>临停电子</span>
                </div>
                <span class="cNum" id="tempOnlineTotal">0</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #FA5D8C;">
                <div class="carL" style="background:#FA5D8C;">
                    <span>总应收</span>
                </div>
                <span class="cNum" id="tempNeedpayTotal">0</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>总代缴</span>
                </div>
                <span class="cNum" id="tempBehalfpayTotal">0</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>总闪付</span>
                </div>
                <span class="cNum" id="tempQpasspayTotal">0</span>
            </div>
        </li>
        <li>
            <div class="carStyle" style="border:2px solid #90DAF2;">
                <div class="carL" style="background:#90DAF2;">
                    <span>总预缴</span>
                </div>
                <span class="cNum" id="tempPrepayTotal">0</span>
            </div>
        </li>
    </ul>
</div>-->
<table id="parkReportTable" lay-filter="parkReportTable"></table>
</body>
</html>