package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TParkInOut;
import com.eparking.insideService.TPresentCarInsideService;
import com.eparking.service.PresentCarService;
import com.common.util.ControllerRspUtil;
import com.common.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PresentCarController
 * @Author jin
 * @Date 2018/9/18 10:49
 **/
@RestController
@RequestMapping(value = "Present")
public class PresentCarController {
    private  static final Logger logger= LoggerFactory.getLogger(PresentCarController.class);
    @Autowired
    private PresentCarService presentCarService;
    @Autowired
    private TPresentCarInsideService tPresentCarInsideService;

    /**
     * 表单搜索查询
     * @param tParkInOut
     * @return
     */
    @PostMapping(value = "/getPresentCarbyPage")
    @HttpLog(operationType = "0",modularTypeName = "表单搜索查询")
    public ControllerRsp getPresentCarbyPage(TParkInOut tParkInOut,Integer page, Integer limit,HttpServletRequest request){
        String timeType = request.getParameter("timeType");
        String isUpdateCarplate = request.getParameter("isUpdateCarplate");
        String parkId = SessionUtil.getParkId().toString();
        if (timeType==null){
            timeType="all";
        }
        if (isUpdateCarplate!=null){
            tParkInOut.setUpdatedincarplate("havaupdate");
        }
        return ControllerRspUtil.Success(presentCarService.getPresentCarbyPage(tParkInOut,parkId,timeType, page,limit));
//        return tPresentCarInsideService.getTPresentCarbyPage(tParkInOut,parkId,timeType, page,limit);
    }


}
