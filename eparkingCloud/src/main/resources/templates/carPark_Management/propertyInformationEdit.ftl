<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>物业基础信息设置</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table','form'], function(){
            var table = layui.table;
            var form=layui.form;
            //监听更新按钮
            form.on('submit(formUpdate)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                ComPanyhandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            ComPanyhandle.getComPanybyId(form);
            form.render();
        });
        $(document).ready(function () {
            ComPanyForm.getCompanyType();
            ComPanyForm.getStatus();
            ComPanyForm.getIsManyPreferential();

        });
        //车场增删查改方法
        var ComPanyhandle={
            getComPanybyId:function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../comPany/getComPanybyId",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if(result.code==0)
                        {
                            form.val("companyForm", result.data[0]);

                        }
                    },
                    error:function (result) {
                        var str="<script type=\"text/javascript\">";
                        var beginNum=result.responseText.indexOf(str)+str.length;
                        var endNum=result.responseText.length-9;
                        var action=result.responseText.substring(beginNum,endNum);
                        eval(action);
                    }
                })
            },
            save:function(object){
                $.ajax({
                    type: 'POST',
                    url: "../comPany/updateComPany",
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
            }
        };
        var companyTypeList,statusList,IsManyPreferentialList;
        //车场更新表单使用方法
        var ComPanyForm={
            clean:function(){
                $("#companyForm")[0].reset();
            },
            //获取物业类型
            getCompanyType:function () {
                companyTypeList= [{id:1,name:'物业公司'},{id:2,name:'地产商'},{id:3,name:'政府部门'},{id:4,name:'商广场'}];
                var str=" <option value>请选择</option>";
                //console.log(companyTypeList);
                companyTypeList.forEach(function (t) {
                    str+="<option value="+t.id+">"+t.name+"</option>"
                });
                $("select[name='companyType']").html(str);
            },
            //获取状态
            getStatus:function () {
                statusList=[{id:0,name:'停用'},{id:1,name:'启用'}];
                var str=" <option value>请选择</option>";
                statusList.forEach(function (t) {
                    str+="<option value="+t.id+">"+t.name+"</option>"
                });
                $("select[name='status']").html(str);
            },
            //获取代缴类型
            getIsManyPreferential:function () {
                IsManyPreferentialList=[{id:0,name:'单次代缴'},{id:1,name:'多次代缴'},{id:2,name:'覆盖代缴'}];
                var str=" <option value>请选择</option>";
                IsManyPreferentialList.forEach(function (t) {
                    str+="<option value="+t.id+">"+t.name+"</option>"
                });
                $("select[name='isManyPreferential']").html(str);
            }
        }
    </script>

</head>
<body>
<fieldset class="layui-elem-field">
    <legend>物业基础信息设置</legend>
    <div class="layui-field-box">
        <form class="layui-form layui-form-pane"  id="companyForm" lay-filter="companyForm">
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">ID</label>
                <div class="layui-input-block">
                    <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">物业公司</label>
                <div class="layui-input-block">
                    <input type="text" name="companyName" required  lay-verify="required" placeholder="物业公司" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">账号</label>
                <div class="layui-input-block">
                    <input type="text" name="account" required  lay-verify="required" placeholder="账号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">物业类型</label>
                <div class="layui-input-block">
                    <select id="companyType" name="companyType" lay-filter="companyType">
                    </select>

                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-block">
                    <input type="text" name="address" required  lay-verify="required" placeholder="地址" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">是否启用</label>
                <div class="layui-input-block">
                    <select id="status" name="status" lay-filter="status">
                    </select>
                </div>
            </div>
            <div class="layui-form-item" id="password" style="display: none">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="text" name="password" required  lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">联系人</label>
                <div class="layui-input-block">
                    <input type="text" name="contact" required  lay-verify="required" placeholder="联系人" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">联系方式</label>
                <div class="layui-input-block">
                    <input type="text" name="contactTel" required  lay-verify="required" placeholder="联系方式" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">是否允许自动续缴</label>
                    <div class="layui-input-inline" style="width: 100px;">
                        <input type="checkbox" name="autoRenew" lay-skin="switch" value="1" lay-text="允许|不允许">
                    </div>
                </div>

            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">代缴类型</label>
                    <div class="layui-input-block">
                        <select id="isManyPreferential" name="isManyPreferential" lay-filter="isManyPreferential">
                        </select>
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formUpdate">保存</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </form>
    </div>
</fieldset>

</body>

</html>