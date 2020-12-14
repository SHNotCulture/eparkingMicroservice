<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../css/bass.css">
    <link rel="stylesheet" href="../js/layui/css/layui.css">
    <link rel="stylesheet" href="../js/layui/css/admin.css">
    <script type="text/javascript" src="../js/echarts/echarts.js"></script>
    <script type="text/javascript" src="../js/layui/layui.js"></script>
    <script type="text/javascript" src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        layui.use(['table','form','layer','element'], function(){
            var form=layui.form;
            var element=layui.element;
        });
        var statistic={
            getStatistic:function () {
                $.ajax({
                    type: 'POST',
                    url: "../tParkStatistics/getTParkStatistics",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log(result);

                    },
                    error: function (result) {
                        layer.msg(result.message);
                    }
                });
            },
            getStatisticDay:function () {
                $.ajax({
                    type: 'POST',
                    url: "../tParkStatisticsDay/getTParkStatisticsDay",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log(result);

                    },
                    error: function (result) {
                        layer.msg(result.message);
                    }
                });
            }
        }
    </script>

</head>
<body layadmin-themealias="default">
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
            <#--实时数据-->
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">实时数据</div>
                    <div class="layui-card-body">
                        <div class="layui-carousel layadmin-carousel layadmin-backlog" lay-anim lay-indicator="inside" lay-arrow="none" style="width: 100%;height: 280px">
                            <div carousel-item>
                                <ul class="layui-row layui-col-space10 layui-this">
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>车场总车位</h3>
                                            <p>
                                                <cite>${main["NeedPay"]}</cite>元
                                            </p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>车场占用车位</h3>
                                            <p>
                                                <cite>${main["todayNum"]}</cite>辆
                                            </p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>车场空闲车位</h3>
                                            <p>
                                                <cite>${main["free"]}</cite>辆
                                            </p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>当日入场流量</h3>
                                            <p>
                                                <cite>${main["diff"]}</cite>元
                                            </p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>当日出场流量</h3>
                                            <p>
                                                <cite>${main["overdueNum"]}</cite>位
                                            </p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>当日交易总额</h3>
                                            <p>
                                                <cite>${main["overdueNum"]}</cite>位
                                            </p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <#--基础数据-->
            <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">基础数据</div>
                <div class="layui-card-body">
                    <div class="layui-carousel layadmin-carousel layadmin-backlog" lay-anim lay-indicator="inside" lay-arrow="none" style="width: 100%;height: 280px">
                        <div carousel-item>
                            <ul class="layui-row layui-col-space10 layui-this">
                                <li class="layui-col-xs4">
                                    <a class="layadmin-backlog-body">
                                        <h3>今日总收入</h3>
                                        <p>
                                            <cite>${main["NeedPay"]}</cite>元
                                        </p>
                                    </a>
                                </li>
                                <li class="layui-col-xs4">
                                    <a class="layadmin-backlog-body">
                                        <h3>今日车流量</h3>
                                        <p>
                                            <cite>${main["todayNum"]}</cite>辆
                                        </p>
                                    </a>
                                </li>
                                <li class="layui-col-xs4">
                                    <a class="layadmin-backlog-body">
                                        <h3>减免车辆</h3>
                                        <p>
                                            <cite>${main["free"]}</cite>辆
                                        </p>
                                    </a>
                                </li>
                                <li class="layui-col-xs4">
                                    <a class="layadmin-backlog-body">
                                        <h3>异常金额</h3>
                                        <p>
                                            <cite>${main["diff"]}</cite>元
                                        </p>
                                    </a>
                                </li>
                                <li class="layui-col-xs4">
                                    <a class="layadmin-backlog-body">
                                        <h3>超期用户</h3>
                                        <p>
                                            <cite>${main["overdueNum"]}</cite>位
                                        </p>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            <#--电子方式-->
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">电子方式</div>
                    <div class="layui-card-body">
                        <div class="layui-carousel layadmin-carousel layadmin-backlog" lay-anim lay-indicator="inside" lay-arrow="none" style="width: 100%;height: 280px">
                            <div carousel-item>
                                <ul class="layui-row layui-col-space10 layui-this">
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>微信</h3>
                                            <p>
                                                <cite>${main["wechatNums"]}</cite>次
                                            </p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>支付宝</h3>
                                            <p>
                                                <cite>${main["alipayNums"]}</cite>次
                                            </p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>优惠</h3>
                                            <p>
                                                <cite>${main["couponNums"]}</cite>次
                                            </p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>银联</h3>
                                            <p>
                                                <cite>${main["unionPayNums"]}</cite>次
                                            </p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>现金</h3>
                                            <p>
                                                <cite>${main["actualPayNums"]}</cite>次
                                            </p>
                                        </a>
                                    </li>
                                    <li class="layui-col-xs4">
                                        <a class="layadmin-backlog-body">
                                            <h3>ETC</h3>
                                            <p>
                                                <cite>${main["etcNums"]}</cite>次
                                            </p>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>
<#--    <div class="layui-row layui-col-space15">
            &lt;#&ndash;车位对比&ndash;&gt;
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">车位对比</div>
                    <div class="layui-card-body">
                        <div class="layui-carousel layadmin-carousel layadmin-backlog" lay-anim lay-indicator="inside" lay-arrow="none" style="width: 100%;height: 280px">
                            <div carousel-item>
                                &lt;#&ndash;车位对比&ndash;&gt;
                                <div id="container" style="height: 150px">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            &lt;#&ndash;电子支付对比&ndash;&gt;
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">电子支付对比</div>
                    <div class="layui-card-body">
                        <div class="layui-carousel layadmin-carousel layadmin-backlog" lay-anim lay-indicator="inside" lay-arrow="none" style="width: 100%;height: 280px">
                            <div carousel-item>
                                <div id="carflow" class="i2" style="height: 100px"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            &lt;#&ndash;当天缴费方式对比&ndash;&gt;
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">当天缴费方式对比</div>
                    <div class="layui-card-body">
                        <div class="layui-carousel layadmin-carousel layadmin-backlog" lay-anim lay-indicator="inside" lay-arrow="none" style="width: 100%;height: 280px">
                            <div carousel-item>
                                &lt;#&ndash;车位对比&ndash;&gt;
                                <div id="containerPei1" class="layui-col-md6" style="height: 160px"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    </div>
    <div class="layui-row layui-col-space15">
            &lt;#&ndash;当天月租车收费对比&ndash;&gt;
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">当天月租车收费对比</div>
                    <div class="layui-card-body">
                        <div class="layui-carousel layadmin-carousel layadmin-backlog" lay-anim lay-indicator="inside" lay-arrow="none" style="width: 100%;height: 280px">
                            <div carousel-item>
                                <div id="containerPei2" class="layui-col-md6" style="height: 160px"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            &lt;#&ndash;当天缴费方式对比&ndash;&gt;
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">车位周转率</div>
                    <div class="layui-card-body">
                        <div class="layui-carousel layadmin-carousel layadmin-backlog" lay-anim lay-indicator="inside" lay-arrow="none" style="width: 100%;height: 280px">
                            <div carousel-item>
                                &lt;#&ndash;车位对比&ndash;&gt;
                                <div id="containerPei3" class="layui-col-md6" style="height: 160px"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            &lt;#&ndash;当天月租车收费对比&ndash;&gt;
            <div class="layui-col-md4">
                <div class="layui-card">
                    <div class="layui-card-header">车位使用率</div>
                    <div class="layui-card-body">
                        <div class="layui-carousel layadmin-carousel layadmin-backlog" lay-anim lay-indicator="inside" lay-arrow="none" style="width: 100%;height: 280px">
                            <div carousel-item>
                                <div id="containerPei4" class="layui-col-md6" style="height: 160px"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

    </div>-->
</div>

<#--<div class="clearfix"></div>-->
<div class="i3">
    <div class="layui-row  footer">
        <div id="container5" class="layui-col-md12 footer-box" style="height: 300px">
        </div>
    </div>
</div>


</body>
</html>