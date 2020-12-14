package com.common.util;

import com.common.entity.ActionRsp;

/**
 * @Author lishuhan
 * @Description:向第三方返回数据方法
 * @Date Create in 15:322018-5-9
 * @Modified By:
 */
public class ActionRspUtil {
    public static ActionRsp Success(Object result){
        ActionRsp actionRsp=new ActionRsp();
        actionRsp.setCode(0);
        actionRsp.setResult(result);
        actionRsp.setMessage("访问成功");
        return actionRsp;
    }
    public static ActionRsp Success(){
        return Success(null);
    }
    public static ActionRsp Error(Integer code,String message){
        ActionRsp actionRsp=new ActionRsp();
        actionRsp.setCode(code);
        actionRsp.setMessage(message);
        return actionRsp;
    }
}
