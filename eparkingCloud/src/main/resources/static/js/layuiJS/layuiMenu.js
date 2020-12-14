/**
 项目JS主入口
 以依赖Layui的layer和form模块为例
 **/
layui.define(['layer', 'form','element'], function(exports){
    exports('layuiMenu', {
        Init:function (LayuiMenuData)
        {
            //console.log(LayuiMenuData);
            //建立初始字符串
            var str="";
            //判断Menu类型
            if(LayuiMenuData.type!=undefined)
            {
                switch (LayuiMenuData.type) {
                    case 'vertical':
                        str+=" <ul  class='layui-nav layui-nav-tree'>";
                        break;
                    case 'left':
                        str+=" <ul  class='layui-nav layui-layout-left'>";
                        break;
                    case 'right':
                        str+=" <ul  class='layui-nav layui-layout-right'>";
                        break;

                    default:
                        break;
                }
            }
            //str+=" <ul  class='layui-nav layui-nav-tree '>";
            //判断是否存在Data属性
            if(LayuiMenuData.data.length>0 && LayuiMenuData.data!=undefined)
            {
                for(var i=0;i<LayuiMenuData.data.length;i++)
                {
                    str+="<li class='layui-nav-item layui-nav-itemed'>";
                    if(LayuiMenuData.data[i].xrc!=""&& LayuiMenuData.data[i].xrc!=undefined)
                    {
                        //添加页面路径
                        str+="<a xrc='"+LayuiMenuData.data[i].xrc+"'>";
                    }
                    else
                    {
                        str+="<a>";
                    }
                    if(LayuiMenuData.data[i].iconCls!=null&&LayuiMenuData.data[i].iconCls!=undefined)
                    {
                        str+="<i class='iconfont' >"+icon(LayuiMenuData.data[i].iconCls)+"</i>&nbsp;";
                    }
                    if(LayuiMenuData.data[i].Name!=""&&LayuiMenuData.data[i].Name!=undefined)
                    {
                        str+=LayuiMenuData.data[i].Name;
                    }
                    str+="</a>";
                    //判断是否存在子级菜单
                    if(LayuiMenuData.data[i].children!=undefined)
                    {
                        if(LayuiMenuData.data[i].children.length>0)
                        {
                            //生成子级菜单头
                            str+="<dl class='layui-nav-child'>";
                            for(var j=0;j<LayuiMenuData.data[i].children.length;j++)
                            {
                                str+="<dd id="+LayuiMenuData.data[i].children[j].ID+"> ";
                                if(LayuiMenuData.data[i].children[j].xrc!=""&& LayuiMenuData.data[i].children[j].xrc!=undefined)
                                {
                                    //添加页面路径
                                    str+="<a xrc='"+LayuiMenuData.data[i].children[j].xrc+"'>";
                                    //添加图标
                                    if(LayuiMenuData.data[i].children[j].iconCls!=null&&LayuiMenuData.data[i].children[j].iconCls!=undefined)
                                    {
                                        str+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='iconfont'>"+icon(LayuiMenuData.data[i].children[j].iconCls)+"</i>&nbsp;";
                                        //str+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-";
                                    }
                                    //添加名称
                                    if(LayuiMenuData.data[i].children[j].Name!=""&&LayuiMenuData.data[i].children[j].Name!=undefined)
                                    {
                                        //str+="<cite>"+LayuiMenuData.data[i].children[j].Name+"</cite>";
                                        str+=LayuiMenuData.data[i].children[j].Name;
                                    }
                                    str+="</a>";
                                }
                                str+="</dd>";
                            }
                            str+="</dl>";
                        }
                    }
                    str+="</li>";
                }
            }
            str+="</ul>";
            //在目标实例化Menu
            $('#'+LayuiMenuData.id).html(str);

        },
        MeunClick:function(LayuiMenuData)
        {
            var element = layui.element;
            //得到Nav对象
            var $nav =$('#'+LayuiMenuData.id).find('li.layui-nav-item');
            $nav.each(function (i, e)
            {
                //给子菜单绑定点击事件
                $(e).bind("click", function (e) {
                    var Name = this.innerText.substring(1, this.innerText.length);
                    var src = $(this).find('a').attr('xrc');
                    if(src!=undefined){
                        var str = "<iframe src='" + src + "' style='height:100%;width:98%'/>";
                        //console.log(str);
                        $("#body").html(str);
                    }

                });
                //得到子菜单对象
                var $child=$(e).find('dl.layui-nav-child')
                $child.find('dd').each(function (j, a){
                    //给子菜单绑定点击事件
                    $(a).bind("click", function (e) {
                        var Meunid=$(this).attr('id');
                        var Name=this.innerText.substring(1,this.innerText.length);
                        var src=$(this).find('a').attr('xrc');
                        var flog=true;
                        var str="<iframe src='" + src + "' style='height:100%;width:98%'/>";
                        $("#body").html(str);
                        //得到Title对象
                        /*var $title = $('ul.layui-tab-title');
                        $title.find('li').each(function (b, k) {
                            //得到已生成的Lay-id
                            var id=$(k).attr('lay-id');
                            //与即将生成的Tab进行比对
                            if(id==Meunid)
                            {
                                flog=false;
                            }
                        });
                        //如果之前没有这个Tab产生
                        if(flog==true)
                        {
                            if(src!=""&& src!=undefined)
                            {
                                //新增一个Tab项
                                element.tabAdd('Tab', {
                                    title: Name+"<i class='layui-icon layui-unselect layui-tab-close'>ဆ</i>",
                                    content:"<div style='height:842px;width:98% '><iframe src='" + src + "' style='height:100%;width:98%'/></div>",
                                    id: Meunid //实际使用一般是规定好的id，这里以时间戳模拟下
                                });

                            }
                            else
                            {
                                //新增一个Tab项
                                element.tabAdd('Tab', {
                                    title: Name+"<i class='layui-icon layui-unselect layui-tab-close'>ဆ</i>",
                                    content:"<div style='height:842px;width:98% '><iframe src='404.html' style='height:100%;width:98%'/></div>",
                                    id: Meunid //实际使用一般是规定好的id，这里以时间戳模拟下
                                });
                            }
                            //绑定删除点击事件
                            initIClick($title,element, Meunid);
                        }
                        //跳转至新增的Tab项
                        element.tabChange('Tab', Meunid);
*/

                    });
                });
            });
            element.init();
        }

    }); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});
//绑定TAB删除事件
function initIClick(title, element,ID) {
    //为最后一个i绑定click语句
    var i = title.find('li:last').find('i');
    $(i).bind("click", function () {
        //删除指定Tab项
        element.tabDelete('Tab', ID);
    });
}
function icon(icon)
{
    switch (icon) {
        case 'add':
            icon="&#xe61f";
            break;
        case 'edit':
            icon="&#xe642";
            break;
        case 'Delete':
            icon="&#xe640";
            break;
        case 'System':
            icon="&#xe622;";
            break;
        case 'Menu':
            icon="&#xe63c";
            break;
        case 'Index':
            icon="&#xe66f;";
            break;
        case 'User':
            icon="&#xe668;";
            break;
        case 'Manager':
            icon="&#xe61e;";
            break;
        case 'Car':
            icon="&#xe6b0;";
            break;
        case 'Park':
            icon="&#xe60f;";
            break;
        case 'Money':
            icon="&#xe8f5;";
            break;
        case 'Close':
            icon="&#x1006";
            break;
        default:
            break;
    }
    return icon;
}