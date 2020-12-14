<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    <title>车易泊云平台</title>
    <link rel="stylesheet" href="../js/layui/css/layui.css"/>
    <link rel="stylesheet" href="../public/icons/iconfont.css"/>
    <script src="../js/layui/layui.js"></script>
    <script src="../js/jQuery1.9.1/jquery-1.9.1.js"></script>
    <script type="text/javascript">
        var user= ${user};
        var layuiMenuList=${layuiMenuList};
        $(function () {
            SysMeun.RequsetMenu();
            SysIndex.ComPanyParkInit();
            SysIndex.initMain();
            $("#userName").text(user.userName);
        })
        //菜单对象
        var SysMeun = {
            //请求菜单数据
            RequsetMenu: function () {
                var Menu = {
                    id: 'LayuiMenu',
                    type: 'vertical',
                    data:layuiMenuList
                        /*[   {"ID":1,"Name":"物管主页","iconCls":"Index","xrc":"/view/main"},

                            {"ID":1,"Name":"停车管理","iconCls":"User","children":[
                                {"ID":2,"Name":"在场车辆","iconCls":"System","xrc":"/view/PresentCar"},
                                {"ID":2,"Name":"停车记录","iconCls":"System","xrc":"/view/parkingRecord"} ]},
                            {"ID":1,"Name":"车辆管理","iconCls":"Car","children":[
                                {"ID":2,"Name":"月租车管理","iconCls":"System","xrc":"/view/MonthlyCar"},
                                    {"ID":2,"Name":"月租车缴费记录","iconCls":"System","xrc":"/view/MonthlyPayRecord"},
                                {"ID":2,"Name":"黑名单管理","iconCls":"System","xrc":"/view/Blacklist"}
                            ]},
                            {"ID":1,"Name":"商户管理","iconCls":"Manager","children":[
                                {"ID":2,"Name":"商户管理","iconCls":"System","xrc":"/view/busine"}
                                ]},
                            {"ID":1,"Name":"车场管理","iconCls":"Park","children":[
                                {"ID":2,"Name":"车场信息","iconCls":"System","xrc":"/view/carPark"},
                                {"ID":2,"Name":"物业信息","iconCls":"System","xrc":"/view/propertyInformation"},
                                {"ID":2,"Name":"月租规则管理","iconCls":"System","xrc":"/view/monthlyRule"},
                                {"ID":2,"Name":"临停规则管理","iconCls":"System","xrc":"/view/temporaryRule"},
                                {"ID":2,"Name":"车位管理","iconCls":"System","xrc":"/view/carPort"},
                                {"ID":2,"Name":"操作员管理","iconCls":"System","xrc":"/view/user"}
                                ]},
                            {"ID":1,"Name":"财务统计","iconCls":"Money","children":[
                                {"ID":2,"Name":"电子支付对账","iconCls":"System","xrc":"/view/electronicPayment"},
                                {"ID":2,"Name":"现金支付对账","iconCls":"System","xrc":"/view/cashPayment"},
                                {"ID":2,"Name":"财务报表统计","iconCls":"System","xrc":"/view/financialStatements"}
                                ]}
                        ]*/
                };
                //console.log(Menu);
                SysMeun.MeunInit(Menu);
            }
            ,
            //加载菜单
            MeunInit: function (LayuiMenuData) {
                layui.config({
                    base: '../js/layuiJS/' //你的模块目录
                }).use('layuiMenu', function () {
                    var LayuiMenu = layui.layuiMenu;
                    var element = layui.element;
                    var layer = layui.layer;
                    var form = layui.form;
                    LayuiMenu.Init(LayuiMenuData);
                    LayuiMenu.MeunClick(LayuiMenuData);

                    form.on('select(parkId)', function(data){
                      /*  console.log(data.elem); //得到select原始DOM对象
                        console.log(data.value); //得到被选中的值
                        console.log(data.othis); //得到美化后的DOM对象*/
                        SysIndex.saveParkID(data.value);
                    });

                    form.render();

                }); //加载入口
            }


        }
        //主页其他空间窗体对象
        var SysIndex={
                //加载车场
                ComPanyParkInit:function () {
                var parkList= ${tCompanyParkList};
                var str=" <option value='0'>请选择停车场</option>";
                parkList.forEach(function (t,index) {
                    if(index==0){
                        str+="<option value="+t.id+" selected>"+t.parkName+"</option>"
                    }else{
                        str+="<option value="+t.id+">"+t.parkName+"</option>"
                    }

                });
                $("#parkId").html(str);
            },
            //保存更改后的停车场ID
            saveParkID:function (id) {
                $.ajax({
                    type: 'POST',
                    url: "../login/saveParkId",
                    data:{id:id},
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if(result.code==0)
                        {
                            layer.msg(result.result);
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                });
            }
            ,resetForm:function () {
                layer.open({
                    title: '密码重置'
                    , type:2
                    ,area:['300px','300px']
                    // , content: 'http://sentsin.com'
                    ,content:'/view/ResetPS'
                });
            },
            exit:function () {
                $.ajax({
                    type: 'POST',
                    url: "../login/logOut",
                    dataType: "json",
                    contentType: "application/x-www-form-urlencoded; charset=utf-8",
                    success: function (result) {
                        if(result.code==0)
                        {
                            layer.msg(result.result);
                            top.window.location.href="../view/getLogin";
                        }
                    },
                    error:function (result) {
                        console.log(result);
                    }
                });

            },
            initMain:function () {
                      $("#body").html("<iframe src=\"../view/main\" style=\"height:100%;width:98%\"/>")

            }
        }

    </script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header" id="header">
        <div class="layui-logo" style="width: 300px"> ${tCompanyList.companyName}</div>



     <div class="layui-form " style="float:right;margin:10px">
            <div class="layui-form-item">

                <div class="layui-inline">
                    <div class="layui-input-inline" style="width: 200px;">
                        <select id="parkId" name="parkId" lay-filter="parkId" lay-search>
                        </select>
                    </div>
                    <div class="layui-form-mid">-</div>
                    <div class="layui-input-inline" style="width: 200px;margin-top: -10px" >
                        <ul class="layui-nav layui-layout-right">
                            <li class="layui-nav-item">
                                <a href="javascript:;">
                                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                                <label id="userName"></label>
                                </a>
                                <dl class="layui-nav-child">
                                    <dd><a onclick="SysIndex.resetForm()">修改密码</a></dd>
                                    <dd><a onclick="SysIndex.exit()">安全退出</a></dd>
                                </dl>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <div class="layui-side layui-bg-black" >
        <div class="layui-side-scroll">
            <div id="LayuiMenu"></div>
        </div>
    </div>

    <div class="layui-body" style="height:100%;width:88%" id="body">

    </div>

   <#-- <div class="layui-footer">
        <!-- 底部固定区域 &ndash;&gt;
        © eparking@2018 - 车易泊云平台
    </div>-->
</div>
</body>
</html>