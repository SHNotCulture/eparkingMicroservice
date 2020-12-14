var parkList, statusList, expireSoonList, portList, parkingSpaceList, payRuleList, payTypeList;
var common = {
    getParkList: function (form) {
        $.ajax({
            type: 'POST',
            url: "../carPark/getCarPark",
            dataType: "JSON",
            success: function (result) {
                if (result.code == 0) {
                    parkList = result.result;
                    var str = " <option value>请选择停车场</option>";
                    parkList.forEach(function (t) {
                        str += "<option value=" + t.id + ">" + t.parkName + "</option>"
                    });
                    $("select[name='parkId']").html(str);
                }
                form.render();
            },
            error: function (result) {
                //console.log(result);
            }
        })
    },
    getPayTypeList: function () {
        payTypeList = [{id: 1, name: '现金'}, {id: 2, name: '电子支付'}];
        var str = " <option value>请选择类型</option>";
        payTypeList.forEach(function (t) {
            str += "<option value=" + t.id + ">" + t.name + "</option>";
        });
        $("select[name='payType']").html(str);
    },
    getstatusList: function () {
        // statusList=[{id:1,name:'正常'},{id:2,name:'过期'},{id:3,name:'停用'}];
        statusList = [{id: 1, name: '启用'}, {id: 2, name: '停用'}];
        var str = " <option value>请选择状态</option>";
        statusList.forEach(function (t) {
            //console.log("<input type=\"radio\" name=\"status\" value="+t.id+" title=\""+t.name+"\">")
            // str+="<input type=\"radio\" name=\"status\" value="+t.id+" title=\""+t.name+"\">";
            str += "<option value=" + t.id + ">" + t.name + "</option>";
            // $("select[name='status']").html(str);
        });
        $("select[name='status']").html(str);
    },
    getExpireSoonList: function () {
        expireSoonList = [{id: 7, name: '7天之内'}, {id: 30, name: '30天之内'}, {id: 0, name: '已过期'}];
        var str = "<option value>请选择</option>";
        expireSoonList.forEach(function (t) {
            str += "<option value=" + t.id + ">" + t.name + "</option>";
        });
        $("select[name='closeType']").html(str);
    },
    getPortName: function () {
        $.ajax({
            type: 'POST',
            url: "../BaseDic/getPortName",
            dataType: "JSON",
            success: function (result) {
                if (result.code == 0) {
                    //console.log(result);
                    portList = result.result;
                    var str = "";
                    portList.forEach(function (t) {
                        str += "<input type='checkbox' name='portName' value='" + t.portId + "' title='" + t.portName + "' lay-skin='primary'>"
                    });
                    $("#choosePortName").html(str);
                }

            },
            error: function (result) {
                //console.log(result);
            }
        })
    },

    //获取车位性质
    getParkingSpace: function () {
        parkingSpaceList = [{id: 2, name: '子母车位'}, {id: 1, name: '机械车位'}, {id: 3, name: '普通车位'}, {id: 4, name: '车主车位'}];
        var str = " <option value>请选择</option>";
        parkingSpaceList.forEach(function (t) {
            str += "<option value=" + t.id + ">" + t.name + "</option>"
        });
        $("select[name='parkingSpace']").html(str);
    },
    getCarPayRule: function (form) {
        $.ajax({
            type: 'POST',
            url: "../carPayRule/getPayRule",
            dataType: "JSON",
            success: function (result) {
                if (result.code == 0) {
                    payRuleList = result.result;
                    // console.log(payRuleList);
                    var str = "<option value>请选择月租规则</option>";
                    payRuleList.forEach(function (t) {
                        str += "<option value=" + t.id + ">" + t.ruleName + "</option>"
                    });
                    $("select[name='payRule']").html(str);
                }
                form.render();
            },
            error: function (result) {
            }
        })
    },
    /**
     ** 加法函数，用来得到精确的加法结果
     ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
     ** 调用：accAdd(arg1,arg2)
     ** 返回值：arg1加上arg2的精确结果
     **/
    accAdd: function (arg1, arg2) {
        var r1, r2, m, c;
        try {
            r1 = arg1.toString().split(".")[1].length;
        } catch (e) {
            r1 = 0;
        }
        try {
            r2 = arg2.toString().split(".")[1].length;
        } catch (e) {
            r2 = 0;
        }
        c = Math.abs(r1 - r2);
        m = Math.pow(10, Math.max(r1, r2));
        if (c > 0) {
            var cm = Math.pow(10, c);
            if (r1 > r2) {
                arg1 = Number(arg1.toString().replace(".", ""));
                arg2 = Number(arg2.toString().replace(".", "")) * cm;
            } else {
                arg1 = Number(arg1.toString().replace(".", "")) * cm;
                arg2 = Number(arg2.toString().replace(".", ""));
            }
        } else {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", ""));
        }
        return (arg1 + arg2) / m;
    },

    /**
     ** 减法函数，用来得到精确的减法结果
     ** 说明：javascript的减法结果会有误差，在两个浮点数相减的时候会比较明显。这个函数返回较为精确的减法结果。
     ** 调用：accSub(arg1,arg2)
     ** 返回值：arg1减去arg2的精确结果
     **/
    accSub: function (arg1, arg2) {
        var r1, r2, m, n;
        try {
            r1 = arg1.toString().split(".")[1].length;
        } catch (e) {
            r1 = 0;
        }
        try {
            r2 = arg2.toString().split(".")[1].length;
        } catch (e) {
            r2 = 0;
        }
        m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
        n = (r1 >= r2) ? r1 : r2;
        return ((arg1 * m - arg2 * m) / m).toFixed(n);
    },
    /**
     ** 乘法函数，用来得到精确的乘法结果
     ** 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
     ** 调用：accMul(arg1,arg2)
     ** 返回值：arg1乘以 arg2的精确结果
     **/
    accMul: function (arg1, arg2) {
        var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
        try {
            m += s1.split(".")[1].length;
        } catch (e) {
        }
        try {
            m += s2.split(".")[1].length;
        } catch (e) {
        }
        return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
    },
    /**
     ** 除法函数，用来得到精确的除法结果
     ** 说明：javascript的除法结果会有误差，在两个浮点数相除的时候会比较明显。这个函数返回较为精确的除法结果。
     ** 调用：accDiv(arg1,arg2)
     ** 返回值：arg1除以arg2的精确结果
     **/
    accDiv: function (arg1, arg2) {
        var t1 = 0, t2 = 0, r1, r2;
        try {
            t1 = arg1.toString().split(".")[1].length;
        } catch (e) {
        }
        try {
            t2 = arg2.toString().split(".")[1].length;
        } catch (e) {
        }
        with (Math) {
            r1 = Number(arg1.toString().replace(".", ""));
            r2 = Number(arg2.toString().replace(".", ""));
            return (r1 / r2) * pow(10, t2 - t1);
        }
    },
    validatePhone: function (value, item) {
        var patt = /^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/;
        return patt.test(value);

    },
    parkCodeCheck: function (value, item) {
        var patt = /^[a-zA-Z,0-9-]{1,16}$/;
        return patt.test(value);
    },
    payValueCheck: function (value, item) {
        var patt = /^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/;
        return patt.test(value);
    },
    carPlateCheck: function (value, item) {
        var patt = /^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[A-HJ-K])|([A-HJ-K]([A-HJ-NP-Z0-9])[0-9]{4})))|([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-HJ-NP-Z0-9]{4}[A-HJ-NP-Z0-9挂学警港澳]{1})$/;
        return patt.test(value);
    }
}
