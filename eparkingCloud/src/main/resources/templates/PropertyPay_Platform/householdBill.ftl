<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>住户账单管理</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css" media="all"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var tableIns;
        var companyList,parkList,paidList,payTypeList,pushedList,billItemNameList;
        layui.use(['table','form','laydate'], function(){
            var table = layui.table;
            var form=layui.form;
            //第一个实例
            tableIns=table.render({
                elem: '#householeparkingdbillTable'
                ,height: 300
                ,url: '../householeparkingdbill/getHouseholeparkingdbillList' //数据接口
                ,method:'post'
                ,toolbar: true
                ,toolbar: '#toolbarBill'
                ,defaultToolbar: ['filter', 'print']
                ,loading:true
                ,page: true //开启分页
                ,cols: [[ //表头
                    {type:'checkbox'}
                    ,{field: 'companyId', title: '物业公司', width:170, align:'center',templet:'#companyTemp'}
                    ,{field: 'parkId', title: '车场名称', width:170, align:'center',templet:'#parkIdTemp'}
                    ,{field: 'name', title: '住户名称', width: 140, align:'center', sort: true}
                    ,{field: 'billItemName', title: '项目名称', width: 140, align:'center', sort: true}
                    ,{field: 'checkoutDate', title: '账单日', width:140, align:'center', sort: true}
                    ,{field: 'billPushDate', title: '账单推送日', width:170, align:'center'}
                    ,{field: 'amout', title: '账单金额', width:170, align:'center'}
                    ,{field: 'actualPay', title: '实付金额', width:170, align:'center'}
                    ,{field: 'paid', title: '支付标识', width:170, align:'center',templet:'#paidTemp'}
                    ,{field: 'payType', title: '支付类型', width:170, align:'center',templet:'#paidTypeTemp'}
                    ,{field: 'updateTime', title: '支付时间', width:170, align:'center'}
                    ,{field: 'createTime', title: '创建时间', width:170, align:'center', sort: true}
                    ,{field: 'pushed', title: '推送标识', width:170, align:'center',templet:'#pushedTemp'}
                    ,{field: 'pushTime', title: '推送时间', width:170, align:'center', sort: true}
                    ,{fixed: 'right',title: '操作', width: 200, toolbar: '#householeparkingdbar'}
                ]]
            });

            //监听工具条
            table.on('tool(householdAutobillTable)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
                var data = obj.data; //获得当前行数据
                //console.log(data);
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                var tr = obj.tr; //获得当前行 tr 的DOM对象

                if(layEvent === 'add'){ //查看
                    //do somehing
                } else if(layEvent === 'push'){ //推送
                    // blackHandle.Update(1);//弹出车场更新窗口
                }
            });

            //监听批量推送按钮
            table.on('toolbar(householeparkingdbillTable)', function(obj){
                var checkStatus = table.checkStatus(obj.config.id);
                switch(obj.event){
                    case 'selectPost':

                        var data = checkStatus.data;
/*                        var memberIds=null;
                        for(var i=0;i<data.length;i++){
                            if(memberIds!=null){
                                memberIds=memberIds+','+data[i].memberId;
                            }else{
                                memberIds=data[i].memberId;
                            }
                        }*/

                        // console.log("memberIds: ",JSON.stringify(memberIds));
/*                        console.log("memberIds: ",memberIds);
                        console.log("noticeIds: ",noticeIds);*/
                        layer.alert(JSON.stringify(data));
/*                        $.ajax({
                            type: 'POST',
                            url: "../companyNotice/postNotice",
                            data:{'noticeIds':noticeIds,'memberIds':memberIds},
                            dataType: "json",
                            contentType: "application/x-www-form-urlencoded; charset=utf-8",
                            success: function (result) {
                                if(result.code==0)
                                {
                                    if (result.result=='推送成功') {
                                        layer.closeAll();
                                        layer.msg(result.result, {icon: 6});
                                        tableHousehold.reload();
                                    }
                                }
                            },
                            error:function (result) {
                                console.log(result);
                            }
                        });*/
                        break;
                };
            });

            //查询按钮
            form.on('submit(selectBill)',function (data) {
                householeparkingdbillHandle.select(data.field);
                return false;
            });

            //监听新增按钮
            form.on('submit(billUpdate)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                console.log(data.field);
                householeparkingdbillHandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
            householeparkingdbillHandle.getBillName(form);
            form.render();
        });
        $(document).ready(function () {     //DOM完成加载之后立即加载，并且在页面内容加载之前
            householeparkingdbillHandle.getCompany();
            householeparkingdbillHandle.getParkList();
            householeparkingdbillHandle.getPaid();
            householeparkingdbillHandle.getPayType();
            householeparkingdbillHandle.getPushed();
        });
        //住户账单增删查改方法
        var householeparkingdbillHandle={
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
                    url: "../householeparkingdbill/updateHouseholeparkingdbillList",
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
                $("#billEditForm")[0].reset();
            },
            update:function (type) {
                if(type==0){//新增
                    householeparkingdbillHandle.clean();
                    layer.open({
                        title: '新增住户账单'
                        , type: 1
                        ,area:['500px','500px']
                        ,content: $('#billEditForm')
                    });
                }else{
                    layer.open({
                        title: '更新住户账单'
                        , type: 1
                        ,area:['500px','500px']
                        ,content: $('#billEditForm')
                    });
                }
            },
            delete:function (id) {
                $.ajax({
                    type: 'POST',
                    url: "..//",
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
            },
            //获取支付标识
            getPaid:function () {
                paidList=[{id:0,name:'未支付'},{id:1,name:'已支付'}];
                var str=" <option value>请选择</option>";
                paidList.forEach(function (t) {
                    str+="<option value="+t.id+">"+t.name+"</option>"
                });
                $("select[name='paid']").html(str);
            },
            //获取支付类型
            getPayType:function () {
                payTypeList=[{id:1,name:'微信'},{id:2,name:'支付宝'},{id:3,name:'云闪付'}];
                var str=" <option value>请选择</option>";
                payTypeList.forEach(function (t) {
                    str+="<option value="+t.id+">"+t.name+"</option>"
                });
                $("select[name='payType']").html(str);
            },
            //获取推送标识
            getPushed:function () {
                pushedList=[{id:0,name:'未推送'},{id:1,name:'已推送'},{id:2,name:'推送失败'}];
                var str=" <option value>请选择</option>";
                pushedList.forEach(function (t) {
                    str+="<option value="+t.id+">"+t.name+"</option>"
                });
                $("select[name='pushed']").html(str);
            },
            getBillName:function (form) {
                $.ajax({
                    type: 'POST',
                    url: "../propertybillitems/getPropertybillitemsList",
                    dataType: "JSON",
                    success: function (result) {
                        if(result.code==0)
                        {
                            // console.log("result: ",result);
                            billItemNameList= result.result;
                            var str="<option value>请选择缴费项目</option>";
                            billItemNameList.forEach(function (t) {
                                str+="<option value="+t.id+">"+t.billName+"</option>"
                            });
                            $("select[name='billItemId']").html(str);
                        }
                        form.render();
                    },
                    error: function (result) {
                        //console.log(result);
                    }
                })
            }
        }
    </script>
    <script type="text/html" id="companyTemp">
        {{# layui.each(companyList,function(index,item){ }}
        {{# if(item.id===d.companyId){ }}
        {{ item.companyName }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="parkIdTemp">
        {{# layui.each(parkList,function(index,item){ }}
        {{# if(item.id===d.parkId){ }}
        {{ item.parkName }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="paidTemp">
        {{# layui.each(paidList,function(index,item){ }}
        {{# if(item.id===d.paid){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="payTypeTemp">
        {{# layui.each(payTypeList,function(index,item){ }}
        {{# if(item.id===d.payType){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="pushedTemp">
        {{# layui.each(pushedList,function(index,item){ }}
        {{# if(item.id===d.pushed){ }}
        {{ item.name }}
        {{# return; }}
        {{# } }}
        {{#  }); }}
    </script>
    <script type="text/html" id="householeparkingdbar">
        <a class="layui-btn layui-btn-xs" lay-event="push">推送</a>
    </script>

    <script type="text/html" id="toolbarBill">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" onclick="householeparkingdbillHandle.update(0)">新增住户账单</button>
            <button class="layui-btn layui-btn-sm" lay-event="selectPost"">批量推送</button>
        </div>
    </script>

</head>
<body>
<blockquote class="layui-elem-quote">住户账单管理首页 </blockquote>

<#--住户账单查询条件-->
<form class="layui-form" style="margin: 10px" id="HouseholdForm" lay-filter="HouseholdForm">
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">住户名称</label>
            <div class="layui-input-inline">
                <input type="text" name="name"  placeholder="可模糊搜索" autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label">项目名称</label>
            <div class="layui-input-inline">
                <#--<input type="text" name="billItemName"  placeholder="可模糊搜索" autocomplete="off" class="layui-input">-->
                <select  name="billItemId" id="billItemId" lay-filter="billItemId">
                </select>
            </div>
            <label class="layui-form-label">账单金额</label>
            <div class="layui-input-inline">
                <input type="text" name="amout" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">支付标识</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select  name="paid" id="paid" lay-filter="paid">
                </select>
            </div>
            <label class="layui-form-label">支付类型</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select  name="payType" id="payType" lay-filter="payType">
                </select>
            </div>
            <label class="layui-form-label">推送标识</label>
            <div class="layui-input-inline" style="width: 200px;">
                <select  name="pushed" id="pushed" lay-filter="pushed">
                </select>
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <div class="layui-input-inline" style="width: 200px;">
                <button class="layui-btn"  lay-submit lay-filter="selectBill" >查询</button>
            </div>
        </div>
    </div>
</form>

<#--住户账单管理显示窗口-->
<table id="householeparkingdbillTable" lay-filter="householeparkingdbillTable"></table>
</body>
<form class="layui-form"  style="display: none" id="billEditForm" lay-filter="billEditForm">
    <div class="layui-form-item" style="display: none">
        <label class="layui-form-label">ID</label>
        <div class="layui-input-block">
            <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">缴费项目</label>
        <div class="layui-input-inline">
            <#--<input type="text" id="billItemName" name="billItemName" autocomplete="off" class="layui-input">-->
            <select  name="billItemId" id="billItemId" lay-filter="billItemId">
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">住户</label>
        <div class="layui-input-inline">
            <input type="text" id="name" name="name" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="width: 100px">账单金额</label>
        <div class="layui-input-inline">
            <input type="text" id="amout" name="amout" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-inline">
            <input type="hidden" name="paid" id="editPaid" value="0" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline">
            <input type="hidden" name="pushed" id="editPushed" value="0" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-input-inline" style="width: 650px; position: relative; left:25%;">
            <button class="layui-btn" lay-submit lay-filter="billUpdate">保存</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>
</html>