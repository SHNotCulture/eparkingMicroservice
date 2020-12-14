<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>配置参数管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var form;
        var layer
        //注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
        layui.use(['element','form','layer'], function(){
            var element = layui.element;
            form=layui.form;
            layer=layui.layer;
            form.on('submit(formUpdate)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
/*                if(data.field.isWechatMonthly==null){
                    data.field.isWechatMonthly=0;
                }
                if(data.field.autoRenew==null){
                    data.field.autoRenew=0;
                }*/
                GlobalInfohandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            GlobalInfohandle.select(form);
        });

        //在页面加载完成后执行
        $(document).ready(function () {
/*            ParkPortForm.getParkList();
            ParkPortForm.getParkingSpace();
            ParkPortForm.getParkingState();*/

        })

        //通道增删查改方法
        var  GlobalInfohandle={
            select:function (form) {
                //这里以搜索为例
                $.ajax({
                    type: 'POST',
                    url: "../globalInfo/getGlobalInfo",
                    // data:object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log("result: ",result.result.bindingCarEnable);
                        if(result.code==0)
                        {
                   /*         layer.closeAll();
                            layer.msg(result.result);*/
                            // result.result.speaker=51;
                            form.val("globalForm", result.result);
                            form.render();
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
            } ,
            save:function(object){
                // console.log("data: ",object)
                $.ajax({
                    type: 'POST',
                    url: "../globalInfo/updateGlobalInfo",
                    data:object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        // console.log("result: ",result);
                        if(result.code==0)
                        {
                            layer.closeAll();
                            layer.msg(result.result);
                            GlobalInfohandle.select(form);
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
            }
        }


    </script>


</head>
<body>
<blockquote class="layui-elem-quote">参数配置管理</blockquote>
<#--<table id="GlobalInfoTable" lay-filter="GlobalInfoTable"></table>-->
<table class="layui-table">
    <colgroup>
        <col width="500">
        <col width="500">
        <col>
    </colgroup>
    <thead>
    <tr>
        <th>参数名称</th>
        <th>参数值</th>
        <th>参数说明</th>
    </tr>
    </thead>
</table>
<form class="layui-form" id="globalForm" lay-filter="globalForm" style="margin-bottom: 70px">
<div class="layui-collapse">
    <div class="layui-colla-item">

        <h2 class="layui-colla-title">月租车参数配置</h2>
        <div class="layui-colla-content layui-show">
            <div class="layui-inline">
                <label class="layui-input-inline" style="width: 480px">允许车主超期进场</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                <input type="text" class="layui-input" style="width: 480px">-->
<#--                    <select id="memberOuttimeInEnable" name="memberOuttimeInEnable" lay-filter="memberOuttimeInEnable">
                        <option value=0>否</option>
                        <option value=1>是</option>
                    </select>-->
                    <input type="checkbox" name="memberOuttimeInEnable" id="memberOuttimeInEnable" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认否</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">月卡提前多少天预报过期</label>
                <div class="layui-input-inline" style="width: 500px">
                   <input type="text" class="layui-input" name="preVoicecastFixcar">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认15天</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">是否启用多车绑定功能</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="bindingCarEnable" name="bindingCarEnable" lay-filter="bindingCarEnable">-->
<#--                        <option value=1>是</option>-->
<#--                        <option value=0>否</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="bindingCarEnable" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认是</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">剩余车位为0时月租车是否允许自动入场</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="fixcarInEnalbeAsLotfull" name="fixcarInEnalbeAsLotfull" lay-filter="fixcarInEnalbeAsLotfull">-->
<#--                        <option value=1>是</option>-->
<#--                        <option value=0>否</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="fixcarInEnalbeAsLotfull" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认是</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">多车绑定车位是否允许场内换车</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="bindingcarExchangecarEnable" name="bindingcarExchangecarEnable" lay-filter="bindingcarExchangecarEnable">-->
<#--                        <option value=0>否</option>-->
<#--                        <option value=1>是</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="bindingcarExchangecarEnable" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">是=允许，否=不允许（默认）</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">月租过期车场内续费是否临停计费</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="calfeeExpiredFixcar" name="calfeeExpiredFixcar" lay-filter="calfeeExpiredFixcar">-->
<#--                        <option value=1>是</option>-->
<#--                        <option value=0>否</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="calfeeExpiredFixcar" lay-skin="switch" lay-text="是|否"   value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">是=计费（默认），否=不计费</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">月租过期车是否允许电子扣费</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="calfeeEpayExpiredMonthcar" name="calfeeEpayExpiredMonthcar" lay-filter="calfeeEpayExpiredMonthcar">-->
<#--                        <option value=0>否</option>-->
<#--                        <option value=1>是</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="calfeeEpayExpiredMonthcar" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">绑定车入场按正常月租车入场规则入场，否则按临停规则入场</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="bindingCarInAsNormalFixCar" name="bindingCarInAsNormalFixCar" lay-filter="bindingCarInAsNormalFixCar">-->
<#--                        <option value=0>否</option>-->
<#--                        <option value=1>是</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="bindingCarInAsNormalFixCar" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">月租车牌是否判断省份</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="fixCarPreciseJudge" name="fixCarPreciseJudge" lay-filter="fixCarPreciseJudge">-->
<#--                        <option value=1>是</option>-->
<#--                        <option value=0>否</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="fixCarPreciseJudge" lay-skin="switch" lay-text="是|否"   value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">本地工程参数配置</h2>
        <div class="layui-colla-content layui-show">
        <div class="layui-inline">
            <label class="layui-input-inline" style="width: 480px">出入场图片存放主目录</label>
            <div class="layui-input-inline" style="width: 500px">
                <input type="text" name="picSaveUrl" class="layui-input">
            </div>
            <label class="layui-input-inline" style="margin-left: 12px"></label>
        </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">WEB服务器IP地址</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="webServerIp" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认192.168.1.229</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">出入场记录保存天数</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="recordSaveDays" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认60天</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">出入场图片保存天数</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="picSaveDays" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认60天</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">中央收费处的电脑IP地址</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="centerPcIp" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认192.168.1.229</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">自动清理垃圾进出记录间隔时间</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="wasteInfoSaveHours" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认清理72小时前数据</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">是否启动云锁车</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="lockCarEnale" name="lockCarEnale" lay-filter="lockCarEnale">-->
<#--                        <option value=0>否</option>-->
<#--                        <option value=1>是</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="lockCarEnale" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认否</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">主控器版本</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="controllerVersion" name="controllerVersion" lay-filter="controllerVersion">
                        <option value=0>0</option>
                        <option value=1>1</option>
                        <option value=2>2</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">0=V3.x，1=V4.x，2=无控制器</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">音量</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="volume" name="volume" lay-filter="volume">
                        <option value=0>0</option>
                        <option value=1>1</option>
                        <option value=2>2</option>
                        <option value=3>3</option>
                        <option value=4>4</option>
                        <option value=5>5</option>
                        <option value=6>6</option>
                        <option value=7>7</option>
                        <option value=8>8</option>
                        <option value=9>9</option>
                        <option value=10>10</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">0=静音，10=最大</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">播音员</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="speaker" name="speaker" lay-filter="speaker">
                        <option value=3>小燕</option>
                        <option value=51>许久</option>
                        <option value=52>许多</option>
                        <option value=53>小萍</option>
                        <option value=54>唐老鸭</option>
                        <option value=55>许小宝</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">是否播报车牌</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="voiceCastCarplate" name="voiceCastCarplate" lay-filter="voiceCastCarplate">-->
<#--                        <option value=1>是</option>-->
<#--                        <option value=0>否</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="voiceCastCarplate" lay-skin="switch" lay-text="是|否"   value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认是</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">LED控制器版本</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="LEDControllerVersion" name="LEDControllerVersion" lay-filter="LEDControllerVersion">
                        <option value=0>0</option>
                        <option value=1>1</option>
                        <option value=2>2</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">0=V1.4.x，1=V1.5.x，2=V2.x</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">岗亭电脑间数据同步机制</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="sentryDataSynchronize" name="sentryDataSynchronize" lay-filter="sentryDataSynchronize">
                        <option value="云端同步">云端同步</option>
                        <option value="本地同步">本地同步</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认云端同步</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">出场界面有未放行车辆时是否允许新车辆出场</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="newCarEnterEnable" name="newCarEnterEnable" lay-filter="newCarEnterEnable">-->
<#--                        <option value=1>是</option>-->
<#--                        <option value=0>否</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="newCarEnterEnable" lay-skin="switch" lay-text="是|否"   value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认是</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">遇到跟车是否二次开闸</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="sencondOpenEnable" name="sencondOpenEnable" lay-filter="sencondOpenEnable">-->
<#--                        <option value=0>否</option>-->
<#--                        <option value=1>是</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="sencondOpenEnable" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认否</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">临停无入场记录是否查询云端</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="noInRecordQueryCloud" name="noInRecordQueryCloud" lay-filter="noInRecordQueryCloud">-->
<#--                        <option value=0>否</option>-->
<#--                        <option value=1>是</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="noInRecordQueryCloud" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认否</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">虚拟车牌是否覆盖未出场的入场记录</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="virOverwriteInRecord" name="virOverwriteInRecord" lay-filter="virOverwriteInRecord">-->
<#--                        <option value=1>是</option>-->
<#--                        <option value=0>否</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="virOverwriteInRecord" lay-skin="switch" lay-text="是|否"   value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认是：是=不严格模式，否=严格模式</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">是否上传出入场记录到云端</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="uploadRecEnable" name="uploadRecEnable" lay-filter="uploadRecEnable">-->
<#--                        <option value=1>是</option>-->
<#--                        <option value=0>否</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="uploadRecEnable" lay-skin="switch" lay-text="是|否"   value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认是，慎重选择</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">入口屏显示内容选择</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="LEDShowContext" name="LEDShowContext" lay-filter="LEDShowContext">
                        <option value=1>时间</option>
                        <option value=2>剩余车位</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">LED屏长度</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="LEDLength" name="LEDLength" lay-filter="LEDLength">
                        <option value=1>5汉字</option>
                        <option value=2>4汉字</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">隐藏界面上的当班实时统计数据</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="hideRealTimeData" name="hideRealTimeData" lay-filter="hideRealTimeData">-->
<#--                        <option value=0>否</option>-->
<#--                        <option value=1>是</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="hideRealTimeData" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">是=隐藏，否=不隐藏（默认）</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">车牌识别摄像机型号</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="cameraModels" name="cameraModels" lay-filter="cameraModels">
                        <option value=1>1</option>
                        <option value=2>2</option>
                        <option value=3>3</option>
                        <option value=4>4</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">1=标准(默认),2=VZ,3=ZK,4=HX</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">LED屏是否显示计费时长</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="LEDShowChargeTime" name="LEDShowChargeTime" lay-filter="LEDShowChargeTime">-->
<#--                        <option value=1>是</option>-->
<#--                        <option value=0>否</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="LEDShowChargeTime" lay-skin="switch" lay-text="是|否"   value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认是</label>
            </div>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">云平台工程参数配置</h2>
        <div class="layui-colla-content layui-show">
            <div class="layui-inline">
                <label class="layui-input-inline" style="width: 480px">停车场云服务ID</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="parkId" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">认证数据上传到指定物业数据中心</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">停车场云服务KEY</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="parkKey" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">认证数据上传到指定物业数据中心</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">系统运行模式</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <input type="text" class="layui-input" value="云平台">-->
                    <input type="checkbox" name="systemModel" lay-skin="switch" lay-text="云平台|否"   value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认接入云平台</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">上传url</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="uploadURL" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认是http://yun.eparking.top:8080</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">使用MQTT服务与云端数据交互</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="useMQTTChangeDataWithCloud" name="useMQTTChangeDataWithCloud" lay-filter="useMQTTChangeDataWithCloud">-->
<#--                        <option value=1>是</option>-->
<#--                        <option value=0>否</option>-->
<#--                    </select>-->
                    <input type="checkbox" name="useMQTTChangeDataWithCloud" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">是=MQTT方式，否=传统方式（默认）</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">MQTT服务器</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="mqttServer" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">exchange.eparking.top</label>
            </div>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">临时车参数配置</h2>
        <div class="layui-colla-content layui-show">
            <div class="layui-inline">
                <label class="layui-input-inline" style="width: 480px">临时车在免费时间内出场是否自动放行</label>
                <div class="layui-input-inline" style="width: 500px">
                    <#--                    <select id="useMQTTChangeDataWithCloud" name="useMQTTChangeDataWithCloud" lay-filter="useMQTTChangeDataWithCloud">-->
                    <#--                        <option value=1>是</option>-->
                    <#--                        <option value=0>否</option>-->
                    <#--                    </select>-->
                    <input type="checkbox" name="tempcarFreeAutoOut" lay-skin="switch" lay-text="是|否"   value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认是</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">临时车按F10取消出场后是否计入应收</label>
                <div class="layui-input-inline" style="width: 500px">
                    <#--                    <select id="useMQTTChangeDataWithCloud" name="useMQTTChangeDataWithCloud" lay-filter="useMQTTChangeDataWithCloud">-->
                    <#--                        <option value=1>是</option>-->
                    <#--                        <option value=0>否</option>-->
                    <#--                    </select>-->
                    <input type="checkbox" name="tempcarCancelOutIncludeReceivables" lay-skin="switch" lay-text="是|否"   value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认是</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">临时车收费规则</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="tempcarChargeRules" name="tempcarChargeRules" lay-filter="tempcarChargeRules">
                        <option value=1>1</option>
                        <option value=2>2</option>
                        <option value=3>3</option>
                        <option value=4>4</option>
                        <option value=5>5</option>
                        <option value=6>6</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认标准：1@规则一（标准），2@规则二，3@规则三，4@规则四，5@规则五，6@规则六</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">剩余车位为0时临时车是否允许自动入场</label>
                <div class="layui-input-inline" style="width: 500px">
                    <#--                    <select id="useMQTTChangeDataWithCloud" name="useMQTTChangeDataWithCloud" lay-filter="useMQTTChangeDataWithCloud">-->
                    <#--                        <option value=1>是</option>-->
                    <#--                        <option value=0>否</option>-->
                    <#--                    </select>-->
                    <input type="checkbox" name="tempcarInEnableAsLotfull" lay-skin="switch" lay-text="是|否"   value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认是</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">特殊（类）车牌入场干预，不区分月租或临停</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="inSpecialCarIntervene" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">如不限制则不填，如有则填写全车牌或部分车牌。如：鄂</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">特殊（类）车牌出场干预，不区分月租或临停</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="outSpecialCarIntervene" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">如不限制则不填，如有则填写全车牌或部分车牌。如：鄂</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">无入场记录须缴费车辆强制按固定金额收费（0.0=不启用本功能）</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="noEntryCharge" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">0=无强制收费，单位：元</label>
            </div>
        </div>
    </div>
    <div class="layui-colla-item">
        <h2 class="layui-colla-title">计费及支付参数配置</h2>
        <div class="layui-colla-content layui-show">
            <div class="layui-inline">
                <label class="layui-input-inline" style="width: 480px">预缴后场内免计费时间（分钟）</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="freeSecondsPrepay" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认15分钟</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">是否启用云闪付（博众聚合）</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="bzPayEnale" name="bzPayEnale" lay-filter="bzPayEnale">
                        <option value=0>否</option>
                        <option value=1>是</option>
                    </select>-->
                    <input type="checkbox" name="bzPayEnale" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">云闪付（博众支付）成功后是否自动开闸</label>
                <div class="layui-input-inline" style="width: 500px">
                  <#--  <select id="bzPayAutoOpenEnable" name="bzPayAutoOpenEnable" lay-filter="bzPayAutoOpenEnable">
                        <option value=1>是</option>
                        <option value=0>否</option>
                    </select>-->
                    <input type="checkbox" name="bzPayAutoOpenEnable" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">是否启用其他无感支付</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="otherPayEnable" name="otherPayEnable" lay-filter="otherPayEnable">
                        <option value=0>0</option>
                        <option value=1>1</option>
                        <option value=2>2</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">默认0（否）；1（银联）；2（优停）</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">支付Terminal的URL</label>
                <div class="layui-input-inline" style="width: 500px">
                    <input type="text" name="enableTerminalUrl" class="layui-input">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">例：本地=http://127.0.0.1:8080</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">商户优惠全免时效限制</label>
                <div class="layui-input-inline" style="width: 500px">
<#--                    <select id="limitFullCoupon" name="limitFullCoupon" lay-filter="limitFullCoupon">
                        <option value=1>有</option>
                        <option value=0>无</option>
                    </select>-->
                    <input type="checkbox" name="limitFullCoupon" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">新能源车按其他车规则计费</label>
                <div class="layui-input-inline" style="width: 500px">
  <#--                  <select id="isSpecialCarChargeRule" name="isSpecialCarChargeRule" lay-filter="isSpecialCarChargeRule">
                        <option value=0>否</option>
                        <option value=1>是</option>
                    </select>-->
                    <input type="checkbox" name="isSpecialCarChargeRule" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">是=允许，否=不允许（默认）</label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">支付Terminal接入方式</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="terminalInType" name="terminalInType" lay-filter="terminalInType">
                        <option value=1>本地（默认）</option>
                        <option value=2>云端</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">是否自动申请电子扣费</label>
                <div class="layui-input-inline" style="width: 500px">
           <#--         <select id="ePayAutoRequest" name="ePayAutoRequest" lay-filter="ePayAutoRequest">
                        <option value=1>是</option>
                        <option value=0>否</option>
                    </select>-->
                    <input type="checkbox" name="ePayAutoRequest" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">是否支持Terminal的独立通知出场指令</label>
                <div class="layui-input-inline" style="width: 500px">
           <#--         <select id="enableTerminalIndependentNoticeOut" name="enableTerminalIndependentNoticeOut" lay-filter="enableTerminalIndependentNoticeOut">
                        <option value=0>否</option>
                        <option value=1>是</option>
                    </select>-->
                    <input type="checkbox" name="enableTerminalIndependentNoticeOut" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">出场是否实时获取优惠代缴信息</label>
                <div class="layui-input-inline" style="width: 500px">
              <#--      <select id="requestRealTimeCouponInfo" name="requestRealTimeCouponInfo" lay-filter="requestRealTimeCouponInfo">
                        <option value=0>否</option>
                        <option value=1>是</option>
                    </select>-->
                    <input type="checkbox" name="requestRealTimeCouponInfo" lay-skin="switch" lay-text="是|否" value="1">
                </div>
                <label class="layui-input-inline" style="margin-left: 12px"></label>
            </div>
            <div class="layui-inline" style="margin-top: 5px">
                <label class="layui-input-inline" style="width: 480px">预缴超时出场后重新计费模式</label>
                <div class="layui-input-inline" style="width: 500px">
                    <select id="prepayOuttimeReCalMode" name="prepayOuttimeReCalMode" lay-filter="prepayOuttimeReCalMode">
                        <option value=1>模式1</option>
                        <option value=2>模式2</option>
                    </select>
                </div>
                <label class="layui-input-inline" style="margin-left: 12px">模式1（默认）=按入场时间重新计费，模式2=按预缴时间重新计费</label>
            </div>
        </div>
    </div>
</div>
    <div class="layui-form-item" style="margin-top: 10px;margin-left: -100px">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

</body>

</html>