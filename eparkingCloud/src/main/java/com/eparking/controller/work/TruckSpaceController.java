package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.common.entity.eparkingCloud.TTruckSpace;
import com.eparking.insideService.TTruckSpaceInsideService;
import com.eparking.service.TruckSpaceService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 15:362018-8-2
 * @Modified By:
 */
@RestController
@RequestMapping(value = "truckSpace")
public class TruckSpaceController {
    private  static final Logger logger= LoggerFactory.getLogger(TruckSpaceController.class);
    @Autowired
    private TruckSpaceService truckSpaceService;
    @Autowired
    private TTruckSpaceInsideService tTruckSpaceInsideService;

    /**
     * 查询车位信息
     * @return
     */
    @PostMapping(value = "/getTruckSpacebyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询车位信息")
    public ControllerRsp getTruckSpacebyPage(TTruckSpace tTruckSpace, Integer page, Integer limit){
        TCompanyUser user= SessionUtil.getUser();
        tTruckSpace.setCompanyId(user.getCompanyId());
        tTruckSpace.setParkId(SessionUtil.getParkId());
        return ControllerRspUtil.Success(truckSpaceService.getTruckSpacebyPage(tTruckSpace,page,limit));
//        return tTruckSpaceInsideService.getTTruckSpacebyPage(tTruckSpace,page,limit);
    }


    /**
     * 更新车位信息
     * @param tTruckSpace
     * @return
     */
    @PostMapping(value = "/updateTruckSpace")
    @HttpLog(operationType = "1",modularTypeName = "更新车位信息")
    public ActionRsp UpdateTruckSpace(TTruckSpace tTruckSpace)
    {
        TCompanyUser user=SessionUtil.getUser();
        tTruckSpace.setCompanyId(user.getCompanyId());
        tTruckSpace.setParkId(SessionUtil.getParkId());
        return ActionRspUtil.Success(truckSpaceService.UpdateTruckSpace(tTruckSpace));
//        return tTruckSpaceInsideService.UpdateTTruckSpace(tTruckSpace);
    }

    /**
     * 删除车位信息
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteTruckSpace")
    @HttpLog(operationType = "1",modularTypeName = "删除车位信息")
    public ActionRsp DeleteTruckSpace(Integer id){
        TTruckSpace tTruckSpace = new TTruckSpace();
        tTruckSpace.setId(id);
        return ActionRspUtil.Success(truckSpaceService.DeleteTruckSpace(tTruckSpace));
//        return tTruckSpaceInsideService.DeleteTTruckSpace(tTruckSpace);
    }
}
