<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>主页</title>
  <link rel="stylesheet" href="../js/layui/css/layui.css">
  <link rel="stylesheet" href="../css/my.css" />
  <script type="text/javascript" src="../js/echarts/echarts.js"></script>
    <script type="text/javascript" src="../js/echarts/theme/halloween.js"></script>
    <script type="text/javascript" src="../js/layui/layui.js"></script>
    <script type="text/javascript" src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <style type="text/css">
        body{
           height: 1100px
        }
        .top{
           /* margin: 10px;*/
            height: 20%;
            background-color: rgba(13,1,1,0.96);
           /* border: 1px solid #c0c0c0;*/
        }
        .topbox{
            height: 100%;
            border-radius: 5px;
            word-wrap: break-word;
            color:rgba(255,175,81,1);
            border: 1px solid #c0c0c0;
        }
        .topbox-top{
            height: 50%;
            font-size: 35px;
            text-align: center;
            line-height:90px;
            /*background:#90DAF2;*/
        }
        .topbox-bom{
            height: 50%;
            font-size: 15px;
            text-align: center;
            /*border: 1px solid #90DAF2;*/
        }
        .topbox-bom-left{
            height: 100%;
            margin-top: 10px;
            text-align:center;
            line-height:90px;
            /*border: 1px solid #c0c0c0;*/
        }
        .topbox-bom-center{
            height: 100%;
            margin-top: 10px;
            text-align:center;
            line-height:90px;
            /*border: 1px solid #c0c0c0;*/
        }
        .topbox-bom-right{
            height: 100%;
            margin-top: 10px;
            text-align:center;
            line-height:90px;
           /* border: 1px solid #c0c0c0;*/
        }
        .center{
            height: 30%;
        }
        .center-pie{
            height: 100%;
        }
        .footer{
            height: 45%;
        }
        .footer-box{
            height:100%;
        }
    </style>
    <script type="text/javascript">
        layui.use(['table','form','layer'], function() {
            var table = layui.table;
            var form = layui.form;
            var layer = layui.layer;
        });
        function changeMsg(obj) {
            var id=$(obj).attr("id");
            var msg=$(obj).text();
            $.ajax({
                type:"post",
                url:"../homepage/changeMsg",
                data:{
                    "id":id,
                    "msg":msg
                },
                dataType:"html",
                success:function (data) {
                    if (data==1){
                        layer.msg("修改成功！");
                        location.reload(true);
                    }else{
                        layer.msg("修改失败！");
                    }
                },
                error:function () {
                    layer.msg("系统繁忙，请稍后重试！");
                }
            })

        }
        //在页面加载完成后执行
        $(document).ready(function () {
            charts.charts1();
            charts.charts2();
            charts.charts3();
            charts.charts4();
        });
        var charts={
            charts1:function(){
                var dom = document.getElementById("container1");
                var myChart = echarts.init(dom,'halloween');
                var app = {};
                option = null;
                app.title = '环形图';
                option = {
                    title : {
                        text: '当天月租车收费对比',
                        x:'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        type: 'scroll',
                        orient: 'vertical',
                        right: 10,
                        top: 20,
                        bottom: 20,
                        data:['电子收费','现金收费']
                    },
                    series: [
                        {
                            name:'当天月租车收费对比',
                            type:'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'center'
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: {
                                        fontSize: '30',
                                        fontWeight: 'bold'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data:[
                                {value:${monEle}, name:'电子收费'},
                                {value:${monCash}, name:'现金收费'},
                            ]
                        }
                    ]
                };
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            },
            charts2:function(){
                var dom = document.getElementById("container2");
                var myChart = echarts.init(dom,'halloween');
                var app = {};
                option = null;
                app.title = '环形图';

                option = {
                    title : {
                        text: '当天临停车收费对比',
                        x:'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        type: 'scroll',
                        orient: 'vertical',
                        right: 10,
                        top: 20,
                        bottom: 20,
                        data:['电子收费','现金收费']
                    },
                    series: [
                        {
                            name:'当天临停车收费对比',
                            type:'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'center'
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: {
                                        fontSize: '30',
                                        fontWeight: 'bold'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data:[
                                {value:${temporaryEle}, name:'电子收费'},
                                {value:${temporaryCash}, name:'现金收费'}
                            ]
                        }
                    ]
                };
                ;
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            },
            charts3:function () {
                var dom = document.getElementById("container3");
                var myChart = echarts.init(dom,'halloween');
                var app = {};
                option = null;
                app.title = '环形图';

                option = {
                    title : {
                        text: '本月收费对比',
                        x:'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: "{a} <br/>{b}: {c} ({d}%)"
                    },
                    legend: {
                        type: 'scroll',
                        orient: 'vertical',
                        right: 10,
                        top: 20,
                        bottom: 20,
                        data:['电子收费','现金收费']
                    },
                    series: [
                        {
                            name:'本月收费对比',
                            type:'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                normal: {
                                    show: false,
                                    position: 'center'
                                },
                                emphasis: {
                                    show: true,
                                    textStyle: {
                                        fontSize: '30',
                                        fontWeight: 'bold'
                                    }
                                }
                            },
                            labelLine: {
                                normal: {
                                    show: false
                                }
                            },
                            data:[
                                {value:${yueEle}, name:'电子收费'},
                                {value:${yueCash}, name:'现金收费'},

                            ]
                        }
                    ]
                };
                ;
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            },
            charts4:function () {
                var dom = document.getElementById("container4");
                var myChart = echarts.init(dom,'halloween');
                var app = {};
                option = null;
                app.title = '堆叠柱状图';

                option = {
                    title : {
                        text: '年收费报表',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        type: 'scroll',
                        orient: 'vertical',
                        right: 10,
                        top: 20,
                        bottom: 20,
                        data:['电子收费','现金收费','免付费']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis : [
                        {
                            type : 'category',
                            data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
                            name:'电子收费',
                            type:'bar',
                            data:[${mapEle["1"]},${mapEle["2"]},${mapEle["3"]},${mapEle["4"]},${mapEle["5"]},${mapEle["6"]},${mapEle["7"]},${mapEle["8"]},${mapEle["9"]},${mapEle["10"]},${mapEle["11"]},${mapEle["12"]}]
                        },
                        {
                            name:'现金收费',
                            type:'bar',
                            /*stack: '广告',*/
                            data:[${mapCash["1"]},${mapCash["2"]},${mapCash["3"]},${mapCash["4"]},${mapCash["5"]},${mapCash["6"]},${mapCash["7"]},${mapCash["8"]},${mapCash["9"]},${mapCash["10"]},${mapCash["11"]},${mapCash["12"]}]
                        },
                        {
                            name:'免付费',
                            type:'bar',
                            /*  stack: '广告',*/
                            data:[${mapFree["1"]},${mapFree["2"]},${mapFree["3"]},${mapFree["4"]},${mapFree["5"]},${mapFree["6"]},${mapFree["7"]},${mapFree["8"]},${mapFree["9"]},${mapFree["10"]},${mapFree["11"]},${mapFree["12"]}]
                        }

                        /* {
                             name:'搜索引擎',
                             type:'bar',
                             data:[862, 1018, 964, 1026, 1679, 1600, 1570],
                             markLine : {
                                 lineStyle: {
                                     normal: {
                                         type: 'dashed'
                                     }
                                 },
                                 data : [
                                     [{type : 'min'}, {type : 'max'}]
                                 ]
                             }
                         },*/

                    ]
                };
                if (option && typeof option === "object") {
                    myChart.setOption(option, true);
                }
            }
        }
        //var chart = echarts.init(document.getElementById('container1'), 'dark');

    </script>
</head>
<body>

<#--<div class="all">
<!-- 内容主体区域 &ndash;&gt;
  <div class="body">-->
      <div class="layui-row top">
          <div class="layui-col-md4 topbox">
              <div class="layui-row  topbox-top">当前总空位数：${mapTop["totalVacancySpace"]}</div>
              <div class=" layui-row  topbox-bom">
                  <div class="layui-col-md6 topbox-bom-left">
                      车场空位：<span contentEditable id="park_vacancy_space" onblur="changeMsg(this)">${mapTop["parkVacancySpace"]}</span>
                  </div>
                 <div class="layui-col-md6 topbox-bom-right">
                     私家空位：<span contentEditable id="private_vacancy_space" onblur="changeMsg(this)">${mapTop["privateVacancySpace"]}</span>
                 </div>
              </div>
          </div>
          <div class="layui-col-md4 topbox">
              <div class="layui-row  topbox-top">当前在场车辆数：${mapTop["inTotle"]}</div>
              <div class="layui-row  topbox-bom">
                  <div class="layui-col-md4 topbox-bom-left">
                      私家车：<span id="in_fixprivate_cars" contentEditable onblur="changeMsg(this)">${mapTop["inFixprivate"]}</span>
                  </div>
                  <div class="layui-col-md4 topbox-bom-center">
                      月租车：<span contentEditable id="in_fixlots_cars" onblur="changeMsg(this)">${mapTop["inFixlots"]}</span>
                  </div>
                  <div class="layui-col-md4 topbox-bom-right">
                      临停车：<span contentEditable id="in_tempcars" onblur="changeMsg(this)">${mapTop["inTemp"]}</span>
                  </div>
              </div>
          </div>
          <div class="layui-col-md4 topbox">
              <div class="layui-row  topbox-top">今日进场车辆数：${mapTop["inCars"]}</div>
              <div class="layui-row  topbox-bom">
                  <div class="layui-col-md6 topbox-bom-left">
                      今日出场车辆数：${mapTop["outCars"]}
                  </div>
                  <div class="layui-col-md6 topbox-bom-right">
                      今日未确认出场车数：${mapTop["noconfirmCar"]}
                  </div>
              </div>
          </div>
      </div>
      <#--<div class="layui-row">
          <div class="layui-col-md4" style="background-image: url(../img/imadata1.png);no-repeat;height: 200px">
              <span class="n">当前总空位数：${mapTop["totalVacancySpace"]}</span>
              <span class="n">车场空位：<span contentEditable id="park_vacancy_space" onblur="changeMsg(this)">${mapTop["parkVacancySpace"]}</span></span>
              <span class="n">私家空位：<span contentEditable id="private_vacancy_space" onblur="changeMsg(this)">${mapTop["privateVacancySpace"]}</span></span>
          </div>
          <div class="layui-col-md4" style="background-image: url(../img/imadata1.png);no-repeat;height: 200px">
              <span class="Num Num1">当前在场车辆数：${mapTop["inTotle"]}</span>
              <span class="Num Num2">私家车：<span id="in_fixprivate_cars" contentEditable onblur="changeMsg(this)">${mapTop["inFixprivate"]}</span></span>
              <span class="Num Num3">月租车：<span contentEditable id="in_fixlots_cars" onblur="changeMsg(this)">${mapTop["inFixlots"]}</span></span>
              <span class="Num Num4">临停车：<span contentEditable id="in_tempcars" onblur="changeMsg(this)">${mapTop["inTemp"]}</span></span>
          </div>
          <div class="layui-col-md4" style="background-image: url(../img/imadata1.png);no-repeat;height: 200px">
              <span class="Num Num1">今日进场车辆数：${mapTop["inCars"]}</span>
              <span class="Num Num2">今日出场车辆数：${mapTop["outCars"]}</span>
              <span class="Num Num3">今日未确认出场车数：${mapTop["noconfirmCar"]}</span>
          </div>
      </div>-->
    <#--<div class="myDate">
	    	<div class="data1" >
	    		<img src="../img/imadata1.png" class="imG">
		    	<span class="Num Num1">当前总空位数：${mapTop["totalVacancySpace"]}</span>
		    	<span class="Num Num2">车场空位：<span contentEditable id="park_vacancy_space" onblur="changeMsg(this)">${mapTop["parkVacancySpace"]}</span></span>
		    	<span class="Num Num3">私家空位：<span contentEditable id="private_vacancy_space" onblur="changeMsg(this)">${mapTop["privateVacancySpace"]}</span></span>
	    	</div>


	    	<div class="data1"><img src="../img/imadata1.png" class="imG">
		    	<span class="Num Num1">当前在场车辆数：${mapTop["inTotle"]}</span>
		    	<span class="Num Num2">私家车：<span id="in_fixprivate_cars" contentEditable onblur="changeMsg(this)">${mapTop["inFixprivate"]}</span></span>
		    	<span class="Num Num3">月租车：<span contentEditable id="in_fixlots_cars" onblur="changeMsg(this)">${mapTop["inFixlots"]}</span></span>
                <span class="Num Num4">临停车：<span contentEditable id="in_tempcars" onblur="changeMsg(this)">${mapTop["inTemp"]}</span></span>
	    	</div>
	    	<div class="data1"><img src="../img/imadata1.png" class="imG">
		    	<span class="Num Num1">今日进场车辆数：${mapTop["inCars"]}</span>
		    	<span class="Num Num2">今日出场车辆数：${mapTop["outCars"]}</span>
		    	<span class="Num Num3">今日未确认出场车数：${mapTop["noconfirmCar"]}</span>
	    	</div>
    </div>-->


      <div class="layui-row  center">
      <div id="container1" class="layui-col-md4 center-pie"></div>
      <div id="container2" class="layui-col-md4 center-pie"></div>
      <div id="container3" class="layui-col-md4 center-pie"></div>
      </div>


    <div class="layui-row  footer">
    	<div id="container4" class="layui-col-md12 footer-box">
    	</div>
    </div>

    <!--<div style="padding: 15px;">内容主体区域</div>-->
<#--  </div>


</div>-->

<#--<script src="../js/echarts.min.js"></script>-->
</body>

</html>