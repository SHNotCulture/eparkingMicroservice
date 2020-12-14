package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TIpc;
import com.eparking.insideService.TIpcInsideService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
* @Description: TIpcController类
* @author 谢轩然
* @date 2020/07/13 18:31
*/
@RestController
@RequestMapping("tIpc")
public class TIpcController {

    private  static final Logger logger= LoggerFactory.getLogger(TIpcController.class);

    @Autowired
    private TIpcInsideService tIpcInsideService;

    /**
    * 查询TIpc信息
    * @paramtIpc
    * @return
    */
    @PostMapping(value = "/getTIpc")
    @HttpLog(operationType = "1",modularTypeName = "查询TIpc")
    public ActionRsp getTIpc(TIpc tIpc){
    return ActionRspUtil.Success(tIpcInsideService.getTIpc(tIpc));
    }

    /**
    * 查询TIpc信息(分页)
    * @paramtIpc
    * @return
    */
    @PostMapping(value = "/getTIpcbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TIpc(分页)")
    public ControllerRsp getTIpcbyPage(TIpc tIpc, HttpServletRequest request, Integer page, Integer limit){
    return ControllerRspUtil.Success(tIpcInsideService.getTIpcbyPage(tIpc,page,limit));
    }

    /**
    * 更新TIpc信息
    * @paramtIpc
    * @return
    */
    @PostMapping(value = "/updateTIpc")
    @HttpLog(operationType = "1",modularTypeName = "更新TIpc信息")
    public ActionRsp UpdateTIpc(TIpc tIpc, HttpServletRequest request)
    {
        return ActionRspUtil.Success(tIpcInsideService.UpdateTIpc(tIpc));
    }

    /**
    * 删除TIpc信息
    * @param tIpc
    * @return
    */
    @PostMapping(value = "/deleteTIpc")
    @HttpLog(operationType = "1",modularTypeName = "删除TIpc信息")
    public ActionRsp DeleteTIpc(TIpc tIpc){
    return ActionRspUtil.Success(tIpcInsideService.DeleteTIpc(tIpc));
    }
}