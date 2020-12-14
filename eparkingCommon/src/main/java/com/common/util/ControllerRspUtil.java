package com.common.util;


import com.common.entity.ControllerRsp;
import com.common.entity.PageBean;

import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 16:552018-7-23
 * @Modified By:
 */
public class ControllerRspUtil {
    public static ControllerRsp Success(List<?> result){
        ControllerRsp controllerRsp=new ControllerRsp();
        controllerRsp.setCode(0);
        controllerRsp.setCount(result.size());
        controllerRsp.setData(result);
        controllerRsp.setMsg("数据获取成功");
        return controllerRsp;
    }

    /**
     * 分页用返回方法
     * @param pageData
     * @return
     */
    public static ControllerRsp Success(PageBean<?> pageData){
        ControllerRsp controllerRsp=new ControllerRsp();
        controllerRsp.setCode(0);
        controllerRsp.setCount(pageData.getTotalNum());
        controllerRsp.setData(pageData.getItems());
        controllerRsp.setMsg("数据获取成功");
        return controllerRsp;
    }
    public static ControllerRsp Success(){
        return Success();
    }

    public static ControllerRsp Error(Integer code,String message){
        ControllerRsp controllerRsp=new ControllerRsp();
        controllerRsp.setCode(code);
        controllerRsp.setMsg(message);
        return controllerRsp;
    }
}
