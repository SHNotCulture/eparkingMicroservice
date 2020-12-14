<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>物业信息</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        layui.use(['table','form'], function(){
            var table = layui.table;
            var form=layui.form;
            //第一个实例
            tableIns=table.render({
                elem: '#companyTable'
                ,height: 315
                ,url: '../comPany/getCompanybyPage' //数据接口
                ,method:'post'
                ,toolbar: '#companyToolbar'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                   // {field: 'id', title: 'ID', width:80, sort: true, fixed: 'left'},
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'companyName', title: '物业公司', width:120}
                    ,{field: 'account', title: '账号', width:120}
                    ,{field: 'contact', title: '联系人', width:120}
                    ,{field: 'contactTel', title: '联系人方式', width: 120}
                    ,{field: 'address', title: '地址', width: 120, sort: true}
                    ,{field: 'companyType', title: '物业类型', width: 120, sort: true,templet:'#companyTypeTemp'}
                    ,{field: 'status', title: '状态', width: 120, sort: true,templet:'#statusTemp'}
                    ,{fixed: 'right', title: '操作', width: 200, toolbar: '#comPanyBar'}
                ]]
            });
            //头工具栏事件
            table.on('toolbar(companyTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'add':
                        var data = checkStatus.data;
                        ComPanyhandle.Update(0)
                        break;
                };
            });
            //监听工具条
            table.on('tool(companyTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'detail'){ //查看
                    //do somehing
                } else if(layEvent === 'del'){ //删除
                    layer.confirm('是否删除', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        ComPanyhandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    //console.log(data);
                    form.val("companyForm", data);
                    ComPanyhandle.Update(1);//弹出车场更新窗口
                }
            });
            //监听更新按钮
            form.on('submit(formUpdate)', function(data){
               /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                ComPanyhandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            //监听查询按钮
            form.on('submit(SelectCompany)', function(data){
              /*  console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                ComPanyhandle.select(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });

            form.render();
        });
        $(document).ready(function () {
            ComPanyForm.getCompanyType();
            ComPanyForm.getStatus();
            ComPanyForm.getIsManyPreferential();
        });
        //车场增删查改方法
        var ComPanyhandle={
            Update:function (type) {
                if(type==0){//新增
                    ComPanyForm.clean();
                    $("#password").show();
                }
                else
                {
                    $("#password").hide();
                }
                layer.open({
                    title: '更新物业公司'
                    , type: 1
                    ,area:['500px','600px']
                    ,content: $('#companyForm')
                });
            },
            select:function (data) {
                //这里以搜索为例
                tableIns.reload({
                    where:data
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            } ,
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
            },
            delete:function (id) {
                $.ajax({
                    type: 'POST',
                    url: "../comPany/deleteComPany",
                    data:{'id':id},
                    dataType: "JSON",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        //console.log(result);
                        if(result.code==0)
                        {
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
                $.ajax({
                    type: 'POST',
                    url: "../Base/getCompanyTypeList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            companyTypeList=result.result;
                            var str=" <option value>请选择</option>";
                            //console.log(companyTypeList);
                            companyTypeList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("select[name='companyType']").html(str);
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
            //获取状态
            getStatus:function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getCompanyStatusList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            statusList=result.result;
                            var str=" <option value>请选择</option>";
                            statusList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("select[name='status']").html(str);
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
            //获取代缴类型
            getIsManyPreferential:function () {
                $.ajax({
                    type: 'POST',
                    url: "../Base/getIsManyPreferentialList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            IsManyPreferentialList=result.result;
                            var str=" <option value>请选择</option>";
                            IsManyPreferentialList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.name+"</option>"
                            });
                            $("select[name='isManyPreferential']").html(str);
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
    <script type="text/html" id="companyToolbar">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add">新增</button>
        </div>
    </script>
    <script type="text/html" id="comPanyBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script type="text/html" id="companyTypeTemp">
        {{# layui.each(companyTypeList,function(index,item){ }}
        {{# if(item.id===d.companyType){ }}
        {{ item.name }}
        {{# return; }}
        {{# }  }}
        {{#  }); }}
    </script>
    <script type="text/html" id="statusTemp">
        {{# layui.each(statusList,function(index,item){ }}
        {{# if(item.id===d.status){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}

        {{#  }); }}
    </script>
</head>
<body>
<blockquote class="layui-elem-quote">物业信息 </blockquote>
<div class="layui-form" style="margin: 10px">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">关键字：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <input type="text" name="companyName"  placeholder="物业公司" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">状态：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select id="status" name="status" lay-filter="status">
                </select>
            </div>

        </div>
        <div class="layui-inline">
            <label class="layui-form-label">物业类型：</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select id="companyType" name="companyType" lay-filter="companyType">
                </select>
            </div>
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn" lay-submit lay-filter="SelectCompany">查询</button>
            </div>
        </div>
    </div>
</div>
<table id="companyTable" lay-filter="companyTable"></table>
</body>
<form class="layui-form"  style="display: none" id="companyForm" lay-filter="companyForm">
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
    <div class="layui-form-item" id="password">
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
</html>