<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../css/bass.css">
    <link rel="stylesheet" href="../js/layui/css/layui.css">
    <script type="text/javascript" src="../js/echarts/echarts.js"></script>
    <script type="text/javascript" src="../js/layui/layui.js"></script>
    <script type="text/javascript" src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>


</head>
<body style="background:url(../img/main2.jpg) no-repeat;background-size:cover;">
<div class="layui-row">
    <div id="e1" class="layui-col-xs4">
        <div class="bss">
            <span>『</span>
            <span>基础数据</span>
        </div>
        <div class="i1 data">
            <ul>
                    <li>
                        <div class="today">今日总收入</div>
                        <div>
                            <span class="countN">${main["NeedPay"]}</span>
                            <span class="count">元</span>
                        </div>
                    </li>
                    <li>
                        <div class="today">今日车流量</div>
                        <div>
                            <span class="countN">${main["todayNum"]}</span>
                            <span class="count">次</span>
                        </div>
                    </li>
                </ul>
            <ul>
                    <li>
                        <div class="today">减免车辆</div>
                        <div>
                            <span class="countN">${main["free"]}</span>
                            <span class="count">辆</span>
                        </div>
                    </li>

                    <li>
                        <div class="today">异常金额</div>
                        <div>
                            <span class="countN">${main["diff"]}</span>
                            <span class="count">元</span>
                        </div>
                    </li>
                    <li style="padding-right:12px;">
                        <div class="today">超期用户</div>
                        <div>
                            <span class="countN">${main["overdueNum"]}</span>
                            <span class="count">位</span>
                        </div>
                    </li>
            </ul>
            <#--车位对比-->
            <div id="container" style="height: 150px">
            </div>
        </div>
    </div>
    <div id="pei" class="layui-col-xs4" >
        <div id="containerPei1" class="layui-col-md6" style="height: 160px"></div>
        <div id="containerPei2" class="layui-col-md6" style="height: 160px"></div>
        <div  class="layui-col-md6" style="height: 10px;text-align: center;">
                <span style="color:#00C6EA;font-weight: bold;font-size:14px;">当天缴费方式对比</span>
        </div>
        <div  class="layui-col-md6" style="height: 10px;text-align: center;">
           <span style="color:#00C6EA;font-weight: bold;font-size:14px;">当天月租车收费对比</span>
            </div>
        <div id="containerPei3" class="layui-col-md6" style="height: 160px"></div>
        <div id="containerPei4" class="layui-col-md6" style="height: 160px"></div>
        <div  class="layui-col-md6" style="height: 10px;text-align: center;">
            <span style="color:#00C6EA;font-weight: bold;font-size:14px;">车位周转率</span>
        </div>
        <div  class="layui-col-md6" style="height: 10px;text-align: center;">
            <span style="color:#00C6EA;font-weight: bold;font-size:14px;">车位使用率</span>
        </div>
    </div>
    <div id="e2" class="layui-col-xs4">
            <div class="bss">
                <span>『</span>
                <span>电子方式</span>
            <div class="i4 data1">
                <ul>
                    <li>
                        <div class="today">微信</div>
                        <div>
                            <span class="countN">${main["wechatNums"]}</span>
                            <span class="count">次</span>
                        </div>
                    </li>
                    <li>
                        <div class="today">支付宝</div>
                        <div>
                            <span class="countN">${main["alipayNums"]}</span>
                            <span class="count">次</span>
                        </div>
                    </li>
                    <li>
                        <div class="today">优惠</div>
                        <div>
                            <span class="countN">${main["couponNums"]}</span>
                            <span class="count">次</span>
                        </div>
                    </li>
                </ul>
                <ul>
                    <li >
                        <div class="today">银联</div>
                        <div>
                            <span class="countN">${main["unionPayNums"]}</span>
                            <span class="count">次</span>
                        </div>

                    </li>
                    <li>
                        <div class="today">现金</div>
                        <div>
                            <span class="countN">${main["actualPayNums"]}</span>
                            <span class="count">次</span>
                        </div>
                    </li>
                    <li>
                        <div class="today">etc</div>
                        <div>
                            <span class="countN">${main["etcNums"]}</span>
                            <span class="count">次</span>
                        </div>
                    </li>
                </ul>
            </div>
            <div id="carflow" class="i2" style="height: 100px"></div>
        </div>
    </div>
</div>
    <#--<div class="clearfix"></div>-->
    <div class="i3">
        <div class="layui-row  footer">
            <div id="container5" class="layui-col-md12 footer-box" style="height: 300px">
            </div>
        </div>
    </div>
    <#--车位对比-->
    <script type="text/javascript">
        var BehalfPay=${main["BehalfPay"]};
        // console.log(BehalfPay);
    // var dom = document.getElementById("container");
    // var myChart = echarts.init(dom);
    // var app = {};
    // option = null;
    // app.title = '世界人口总量 - 条形图';
    var myChart = echarts.init(document.getElementById('container'));
    var option = {
        title: {
            subtext: '『 车位对比',
            subtextStyle: {
                "fontSize": 14,
                "fontWeight": 'bold',
                "color": "#00C6EA"
            }
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        // legend: {
        //     data: ['车位数']
        // },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'value',
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff',
                    fontSize:'10'
                }
            },
            boundaryGap: [0, 0.001]
        },
        yAxis: {
            type: 'category',
            data: ['空车位','总车位'],
            axisLabel: {
                show: true,
                textStyle: {
                    color: '#fff'
                }
            }
        },
        series: [
            {
                type: 'bar',
                barMaxWidth:'5',
                data: ['${main["surplus"]}','${main["total"]}']
            }
        ]
    };
    ;
    //if (option && typeof option === "object") {
        myChart.setOption(option);
    //}
</script>
    <#--预交代缴聚合现金对比-->
    <script type="text/javascript">

    var dom = document.getElementById("containerPei1");
    var myChart = echarts.init(dom,'halloween');
    var app = {};
    option = null;
    option = {
        /*title : {
            text: '当天缴费方式对比',
            x:'center',
            textStyle: {
                fontWeight: "normal",
                color: "#fff",
                fontSize: 14
            }
        },*/
        tooltip: {
            trigger: 'item',
            //formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        // legend: {
        //     type: 'scroll',
        //     orient: 'vertical',
        //     right: 10,
        //     top: 20,
        //     bottom: 20,
        //     data:['电子收费','现金收费']
        // },
        series: [
            {
                //name:'当天缴费方式对比',
                type:'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: false,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold'
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: true
                    }
                },
                data:[
                    {value:${main["BehalfPay"]}, name:'代缴'},
                    {value:${main["PrePay"]}, name:'预交'},
                    {value:${main["CashPay"]}, name:'现金'},
                    {value:${main["qPassPay"]}, name:'聚合'},
                ]
            }
        ]
    };
        myChart.setOption(option);

</script>
    <script type="text/javascript">
        var dom = document.getElementById("containerPei2");
        var myChart = echarts.init(dom,'halloween');
        var app = {};
        option = null;
        option = {
            /*title : {
                text: '当天月租车收费对比',
                x:'center',
                textStyle: {
                    color:"#00C6EA",
                    fontWeight: "bold",
                    fontSize:"14px"
                }
            },*/
            tooltip: {
                trigger: 'item',
                //formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            // legend: {
            //     type: 'scroll',
            //     orient: 'vertical',
            //     right: 10,
            //     top: 20,
            //     bottom: 20,
            //     data:['电子收费','现金收费']
            // },
            series: [
                {
                    //name:'当天月租车收费对比',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: false,
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
                        {value:${main["fixOline"]}, name:'电子收费'},
                        {value:${main["fixNeed"]}, name:'现金收费'},
                    ]
                }
            ]
        };
        myChart.setOption(option);

    </script>
    <script type="text/javascript">
        var dom = document.getElementById("carflow");
        var myChart = echarts.init(dom);
        var app = {};
        option = null;
        option = {
            title: {
                subtext: '『 近七日车流量',
                subtextStyle: {
                    "fontSize": 14,
                    "fontWeight": 'bold',
                    "color": "#00C6EA"
                }
            },
            tooltip : {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            xAxis: {
                type: 'category',
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff',
                        fontSize:'12'
                    }
                },
                boundaryGap: false,
                data: [${mapdate["1"]},${mapdate["2"]},${mapdate["3"]},${mapdate["4"]},${mapdate["5"]},${mapdate["6"]},${mapdate["7"]}]
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff',
                        fontSize:'12'
                    }
                },
                splitLine: {
                    show: false,
                    //  改变轴线颜色
                    lineStyle: {
                        // 使用深浅的间隔色
                        color: ['red']
                    }
                }
            },
            series: [{
                data: [${mapnum["1"]}, ${mapnum["2"]}, ${mapnum["3"]}, ${mapnum["4"]}, ${mapnum["5"]}, ${mapnum["6"]}, ${mapnum["7"]}],
                type: 'line',
                areaStyle: {}
            }]
        };
        ;
        if (option && typeof option === "object") {
            myChart.setOption(option, true);
        }
    </script>
    <script type="text/javascript">
        var dom = document.getElementById("containerPei3");
        var myChart = echarts.init(dom,'halloween');
        var app = {};
        option = null;
        option = {
           /* title : {
                text: '车位周转率',
                x:'center',
                textStyle: {
                    fontWeight: "normal",
                    color: "#fff",
                    fontSize: 14
                }
            },*/
            tooltip: {
                trigger: 'item',
                //formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            // legend: {
            //     type: 'scroll',
            //     orient: 'vertical',
            //     right: 10,
            //     top: 20,
            //     bottom: 20,
            //     data:['电子收费','现金收费']
            // },
            series: [
                {
                    //name:'当天月租车收费对比',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: false,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: true
                        }
                    },
                    data:[
                        {value:${main["turnover"]}, name:'周转率'},
                        {value:1-${main["turnover"]}, name:''},
                    ]
                }
            ]
        };
        myChart.setOption(option);

    </script>
    <script type="text/javascript">
        var dom = document.getElementById("containerPei4");
        var myChart = echarts.init(dom,'halloween');
        var app = {};
        option = null;
        option = {
            /*title : {
                text: '车位使用率',
                x:'center',
                textStyle: {
                    fontWeight: "normal",
                    color: "#fff",
                    fontSize: 14
                }
            },*/
            tooltip: {
                trigger: 'item',
                //formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            // legend: {
            //     type: 'scroll',
            //     orient: 'vertical',
            //     right: 10,
            //     top: 20,
            //     bottom: 20,
            //     data:['电子收费','现金收费']
            // },
            series: [
                {
                    //name:'当天月租车收费对比',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: false,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: true
                        }
                    },
                    data:[
                        {value:${main["syl"]}, name:'使用率'},
                        {value:${main["kcl"]},name:'空车率'},
                    ]
                }
            ]
        };
        myChart.setOption(option);

    </script>
    <script type="text/javascript">
    var dom = document.getElementById("container5");
    var myChart = echarts.init(dom,'halloween');
    var app = {};
    option = null;
    app.title = '堆叠柱状图';

    option = {
        title : {
            subtext: '年收费报表',
            // text: '年收费报表',
             x:'center',
            subtextStyle: {
                "fontSize": 14,
                "fontWeight": 'bold',
                "color":'#00C6EA'
            }
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
            textStyle:{
                fontSize: 15,//字体大小
                color: '#ffffff'//字体颜色
            },
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
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff',
                        fontSize:'10'
                    }
                },
                data : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
            }
        ],
        yAxis : [
            {
                type : 'value',
                splitLine:{
                    show:false
                },
                axisLabel: {
                    show: true,
                    textStyle: {
                        color: '#fff',
                        fontSize:'10'
                    }
                },
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


        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
</html>