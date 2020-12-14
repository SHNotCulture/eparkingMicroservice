<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>车场基础信息设置</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>

    <script type="text/javascript">
        layui.use(['table','form','layer','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            var layer=layui.layer;
            var laydate = layui.laydate;
            //时间范围
            laydate.render({
                elem: '#statisticsTime'
                ,type: 'time'
                ,range: true
            });
            //监听更新按钮
            form.on('submit(formUpdate)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                if(data.field.isWechatMonthly==null){
                    data.field.isWechatMonthly=0;
                }
                if(data.field.autoRenew==null){
                    data.field.autoRenew=0;
                }
                CarPlatehandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            CarPlatehandle.getCarParkbyId(form);
            CarPlateForm.getComPany();
            form.render(); //更新全部
        });
        //在页面加载完成后执行
        $(document).ready(function () {
            CarPlateForm.getCarPayRule();
        });
        //车场增删查改方法
        var CarPlatehandle={
            getCarParkbyId:function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarParkbyId",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if(result.code==0)
                        {
                            form.val("carPlateUp", result.result);
                            $("#statisticsTime").val(result.result.statisticsBeginTime+" - "+result.result.statisticsEndTime)
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
            },
            Update:function (type) {
                if(type==0){//新增
                    CarPlateForm.clean();
                }
                layer.open({
                    title: '更新车场'
                    , type: 1
                    ,area:['500px','600px']
                    ,content: $('#carPlateUp')
                });
            },
            save:function(object){
                $.ajax({
                    type: 'POST',
                    url: "../carPark/updateCarPark",
                    data:object,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if(result.code==0)
                        {
                            layer.closeAll();
                            layer.msg(result.result);
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                });
            }
        };
        var CarPayRuleList,ComPanyList;
        //车场更新表单使用方法
        var CarPlateForm={
            clean:function(){
                $("#carPlateUp")[0].reset();
            },
            //获取缴费规则
            getCarPayRule:function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getCarPayRuleList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            CarPayRuleList=result.result;
                            var str=" <option value>请选择计算规则</option>";
                            CarPayRuleList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("#monthRule").html(str);
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
            //获取物业公司
            getComPany:function () {
                $.ajax({
                    type: 'POST',
                    url: "../comPany/getComPany",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            ComPanyList=result.result;
/*                            var str=" <option value>请选择物业公司</option>";
                            ComPanyList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.companyName+"</option>"
                            });
                            $("select[id='companyId']").html(str);*/
                            $("#companyName").attr("value",ComPanyList[0].companyName);
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
            }
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>车场基础信息设置</legend>
    <div class="layui-field-box">
        <form class="layui-form layui-form-pane"  id="carPlateUp" lay-filter="carPlateUp">
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">ID</label>
                <div class="layui-input-block">
                    <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">物业名称</label>
                <div class="layui-input-block">
<#--                    <select id="companyId" name="companyId"  lay-verify="required" lay-filter="companyId">
                    </select>-->
                    <input type="text" name="companyName" id="companyName" required  disabled lay-verify="required" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">车场名称</label>
                <div class="layui-input-block">
                    <input type="text" name="parkName" id="parkName" required  disabled lay-verify="required" placeholder="车场名称" autocomplete="off" class="layui-input">
                </div>
            </div>
          <#--  <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">车位数</label>
                <div class="layui-input-block">
                    <input type="text" name="totalParkingSpace" required  lay-verify="required|number" placeholder="车位数" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">私家车总车位数</label>
                <div class="layui-input-block">
                    <input type="text" name="privateTotalSpace" required  lay-verify="required|number" placeholder="私家车总车位数" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">月计算规则</label>
                <div class="layui-input-block">
                    <select id="monthRule" name="monthRule"  lay-verify="required" lay-filter="monthRule">
                    </select>
                </div>
            </div>
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">开户行</label>
                <div class="layui-input-block">
                    <input type="text" name="bankName"  placeholder="开户行" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">账号</label>
                <div class="layui-input-block">
                    <input type="text" name="bankAccount" required  lay-verify="required" placeholder="账号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">银行卡号</label>
                <div class="layui-input-block">
                    <input type="text" name="bankCard" required  lay-verify="required|number" placeholder="银行卡号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-block">
                    <input type="text" name="address"  placeholder="地址" autocomplete="off" class="layui-input">
                </div>
            </div>-->
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">X坐标</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" name="positionX" placeholder="X坐标" disabled autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label class="layui-form-label">Y坐标</label>
                    <div class="layui-input-inline" style="width: 200px;">
                        <input type="text" name="positionY" placeholder="Y坐标" disabled autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 200px;">是否允许自动续缴</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="checkbox" name="autoRenew" lay-skin="switch" disabled value="1" lay-text="允许|不允许">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label" style="width: 200px;">是否允许微信月租续费</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="checkbox" name="isWechatMonthly" lay-skin="switch" disabled value="1" lay-text="允许|不允许">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                <label class="layui-form-label" style="width: 180px">统计时间范围</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" name="statisticsTime" id="statisticsTime" placeholder=" - ">
                </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <button class="layui-btn" lay-submit lay-filter="formUpdate">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</fieldset>

</body>

</html>