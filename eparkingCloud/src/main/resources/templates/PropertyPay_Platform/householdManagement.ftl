<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>用户信息管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        var companyList,parkList;
        layui.use(['table','form','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            //第一个实例
            tableIns=table.render({
                elem: '#householdInfoTable'
                ,height: 300
                ,url: '../householdManagement/getHouseholdList' //数据接口
                ,where:{isAuditing:1}
                ,method:'post'
                ,toolbar: true
                ,toolbar: '#toolbarhousehold'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type: 'numbers', title: '序号', width:80, fixed: 'left'}
                    ,{field: 'companyId', title: '物业公司名称', width:170, align:'center',templet:'#companyTemp'}
                    ,{field: 'parkId', title: '车场名称', width:170, align:'center',templet:'#parkIdTemp'}
                    ,{field: 'villageName', title: '小区名称', width:170, align:'center'}
                    ,{field: 'name', title: '住户姓名', width: 140, align:'center', sort: true}
                    ,{field: 'sex', title: '性别', width: 140, align:'center', sort: true,templet:'#sexTemp'}
                    ,{field: 'phoneNumber', title: '联系电话', width:140, align:'center', sort: true}
                    ,{field: 'address', title: '家庭地址', width:170, align:'center', sort: true}
                    ,{field: 'houseArea', title: '住房面积', width:170, align:'center', sort: true}
                    ,{field: 'isAuditing', title: '审核标识', width:170, align:'center', sort: true,templet:'#isAuditingTemp'}
                    ,{field: 'createTime', title: '创建时间', width:170, align:'center', sort: true}
                    ,{fixed: 'right',title: '操作', width: 200, toolbar: '#householeparkingdbar'}
                ]]
            });

            //监听工具条
            table.on('tool(householdInfoTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'del'){ //删除
                    layer.confirm('真的删除行么', function(index){
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        householdHandle.delete(data.id);
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                } else if(layEvent === 'edit'){ //编辑
                    form.val("infoEditForm", data);
                    householdHandle.update(1);
                }
            });


            //查询按钮
            form.on('submit(selectHousehold)',function (data) {
                console.log(data.field);
                householdHandle.select(data.field);
                return false;
            });

            //监听更新按钮
            form.on('submit(infoUpdate)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                householdHandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });

            form.render();

            //监听单元格编辑
            /*            table.on('edit(householdInfoTable)', function(obj){
                            var value = obj.value //得到修改后的值
                                ,data = obj.data //得到所在行所有键值
                                ,field = obj.field; //得到字段
                            layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);
                            householdHandle.save(obj);
                        });*/
        });
        $(document).ready(function () {     //DOM完成加载之后立即加载，并且在页面内容加载之前
            householdHandle.getCompany();
            householdHandle.getParkList();
        });

        //住户信息增删查改方法
        var householdHandle={
            select:function (obj) {
                //这里以搜索为例
                tableIns.reload({
                    where:  //设定异步数据接口的额外参数，任意设
                    obj
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    }
                });
            },
            save:function(obj){
                $.ajax({
                    type: 'POST',
                    url: "../householdManagement/updateHouseholdList",
                    data:obj,
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if(result.code==0)
                        {
                            if (result.result=='编辑成功') {
                                layer.closeAll();
                                layer.msg(result.result, {icon: 6});
                                tableIns.reload();
                            }else if(result.result=='成功添加'){
                                layer.closeAll();
                                layer.msg(result.result);
                                tableIns.reload();
                            }
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                })
            },
            clean:function(){
                $("#infoEditForm")[0].reset();
            },
            update:function (type) {
                if(type==0){//新增
                    householdHandle.clean();
                    layer.open({
                        title: '新增住户信息'
                        , type: 1
                        ,area:['500px','500px']
                        ,content: $('#infoEditForm')
                    });
                }else{
                    layer.open({
                        title: '更新住户信息'
                        , type: 1
                        ,area:['500px','500px']
                        ,content: $('#infoEditForm')
                    });
                }
            },
            delete:function (id) {
                $.ajax({
                    type: 'POST',
                    url: "../householdManagement/deleteInfoList",
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
            },
            //获取物业公司
            getCompany:function () {
                $.ajax({
                    type: 'POST',
                    url: "../comPany/getComPany",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            companyList=result.data;
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
            //获取车场
            getParkList:function () {
                $.ajax({
                    type: 'POST',
                    url: "../carPark/getCarPark",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            parkList=result.data;
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
    <script type="text/html" id="isAuditingTemp">
        {{# if(d.isAuditing===0){ }}
        {{ '未审核' }}
        {{# }if(d.isAuditing===1){ }}
        {{ '已审核' }}
        {{# } }}
    </script>
    <script type="text/html" id="sexTemp">
        {{# if(d.sex===0){ }}
        {{ '女' }}
        {{# }if(d.sex===1){ }}
        {{ '男' }}
        {{# } }}
    </script>
    <script type="text/html" id="companyTemp">
        {{# layui.each(companyList,function(index,item){ }}
        {{# if(item.id===d.companyId){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="parkIdTemp">
        {{# layui.each(parkList,function(index,item){ }}
        {{# if(item.id===d.parkId){ }}
        {{ item.parkName }}
        {{# return; }}
        {{# }  }}
        {{#  }); }}
    </script>
    <script type="text/html" id="householeparkingdbar">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs" lay-event="del">删除</a>
    </script>

    <script type="text/html" id="toolbarhousehold">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="householdHandle.update(0)">新增住户</button>
        </div>
    </script>

</head>
<body>
<blockquote class="layui-elem-quote">住户信息管理首页 </blockquote>

<#--住户信息显示窗口-->
<form class="layui-form" style="margin: 10px" id="householdInfoForm" lay-filter="householdInfoForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">住户姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="name"  placeholder="可模糊搜索" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">小区名称</label>
            <div class="layui-input-inline">
                <input type="text" name="villageName"  placeholder="可模糊搜索" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">家庭住址</label>
            <div class="layui-input-inline">
                <input type="text" name="address"  placeholder="可模糊搜索" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">联系电话</label>
            <div class="layui-input-inline">
                <input type="text" name="phoneNumber"  placeholder="可模糊搜索" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="hidden" name="isAuditing" value="1" type="hidden" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn"  lay-submit lay-filter="selectHousehold" >查询</button>
            </div>
        </div>
    </div>
</form>

<#--住户信息查询显示窗口-->
<table class="layui-table" id="householdInfoTable" lay-filter="householdInfoTable"></table>
</body>
<form class="layui-form"  style="display: none" id="infoEditForm" lay-filter="infoEditForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">住户姓名</label>
        <div class="layui-input-inline">
            <input type="text" id="name" name="name" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">性别</label>
        <div class="layui-input-inline">
            <select type="text" id="sex" name="sex" lay-filter="sex" >
                <option value="0">女</option>
                <option value="1">男</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">联系电话</label>
        <div class="layui-input-inline">
            <input type="text" id="phoneNumber" name="phoneNumber" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">小区名称</label>
        <div class="layui-input-inline">
            <input type="text" id="villageName" name="villageName" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">家庭地址</label>
        <div class="layui-input-inline">
            <input type="text" id="address" name="address" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">住房面积</label>
        <div class="layui-input-inline">
            <input type="text" id="houseArea" name="houseArea" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-inline">
            <input type="hidden" name="isAuditing" id="editAuditing" value="1" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline" style="width: 650px; position: relative; left:25%;">
            <button class="layui-btn" lay-submit lay-filter="infoUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>



</html>