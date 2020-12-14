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
        layui.use(['table','form','layer'], function(){
            var form=layui.form;
            var layer=layui.layer;
            //监听更新按钮
            form.on('submit(formUpdate)', function(data){
                /* console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                 console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                 console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}*/
                CarPlatehandle.save(data.field);
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。

            });
            CarPlatehandle.getCarParkbyId(form);
            form.render(); //更新全部
        });
        //在页面加载完成后执行
        $(document).ready(function () {
            CarPlateForm.getCarPayRule();
            CarPlateForm.getComPany();


        })
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
                            form.val("carPlateUp", result.data[0]);

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
                    error: function (result) {
                        var str="<script type=\"text/javascript\">";
                        var beginNum=result.responseText.indexOf(str)+str.length;
                        var endNum=result.responseText.length-9;
                        var action=result.responseText.substring(beginNum,endNum);
                        eval(action);
                    }
                });
            }
        };
        //车场更新表单使用方法
        var CarPlateForm={
            clean:function(){
                $("#carPlateUp")[0].reset();
            }
        }
    </script>
</head>
<body>
<fieldset class="layui-elem-field">
    <legend>车位矫正</legend>
    <div class="layui-field-box">
        <form class="layui-form layui-form-pane"  id="carPlateUp" lay-filter="carPlateUp">
            <div class="layui-form-item" style="display: none">
                <label class="layui-form-label">ID</label>
                <div class="layui-input-inline">
                    <input type="text" name="id"  placeholder="ID" autocomplete="off" class="layui-input">
                </div>
            </div>
             <div class="layui-form-item" >
                 <div class="layui-inline">
                     <label class="layui-form-label" style="width: 150px">总车位数</label>
                     <div class="layui-input-inline" style="width: 150px">
                         <input type="text" name="totalParkingSpace" required  lay-verify="required|number" placeholder="总车位数" autocomplete="off" class="layui-input">
                     </div>
                 </div>

              </div>
              <div class="layui-form-item" >
                  <div class="layui-inline">
                      <label class="layui-form-label" style="width: 150px">在场个人月租车</label>
                      <div class="layui-input-inline"style="width: 150px">
                          <input type="text" name="inFixprivateCars" required  lay-verify="required|number" placeholder="在场个人月租车" autocomplete="off" class="layui-input">
                      </div>
                  </div>

              </div>
              <div class="layui-form-item" >
                  <div class="layui-inline">
                      <label class="layui-form-label" style="width: 150px">在场车场月租车</label>
                      <div class="layui-input-inline" style="width: 150px">
                          <input type="text" name="inFixlotsCars" required  lay-verify="required|number" placeholder="在场车场月租车" autocomplete="off" class="layui-input">
                      </div>
                  </div>

              </div>
              <div class="layui-form-item" >
                  <div class="layui-inline">
                      <label class="layui-form-label" style="width: 150px">在场临时车</label>
                      <div class="layui-input-inline" style="width: 150px">
                          <input type="text" name="inTempcars"  placeholder="在场临时车" autocomplete="off" class="layui-input">
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