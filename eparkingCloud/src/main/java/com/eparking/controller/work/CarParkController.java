package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCompanyPark;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparking.insideService.TCompanyParkInsideService;
import com.eparking.service.CarParkService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author lishuhan
 * @Description:停车场Controller
 * @Date Create in 16:172018-7-13
 * @Modified By:
 */
@RestController
@RequestMapping(value = "carPark")
public class CarParkController {

    private  static final Logger logger= LoggerFactory.getLogger(CarParkController.class);
    @Autowired
    private CarParkService carParkService;
    @Autowired
    private TCompanyParkInsideService tCompanyParkInsideService;

    /**
     * 查询车场信息
     * @return
     */
    @PostMapping(value = "/getCarPark")
    @HttpLog(operationType = "0",modularTypeName = "查询车场信息")
    public ActionRsp getCarPark(TCompanyPark tCompanyPark){
        TCompanyUser user= SessionUtil.getUser();
//        tCompanyPark.setCompanyId(user.getCompanyId());
        return ActionRspUtil.Success(carParkService.getCarParkbyIDIn(user.getParkIds()));
//        return ActionRspUtil.Success(carParkService.getCarPark(tCompanyPark));
//        return tCompanyParkInsideService.getTCompanyPark(tCompanyPark);
    }
    /**
     * 查询车场信息(分页)
     * @return
     */
    @PostMapping(value = "/getCarParkbyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询车场信息(分页)")
    public ControllerRsp getCarParkbyPage(TCompanyPark tCompanyPark, HttpServletRequest request, Integer page, Integer limit){
        TCompanyUser user= SessionUtil.getUser();
        //tCompanyPark.setCompanyId(user.getCompanyId());
        return ControllerRspUtil.Success(carParkService.getCarParkbyPage(tCompanyPark,page,limit));
//        return tCompanyParkInsideService.getTCompanyParkbyPage(tCompanyPark, page, limit);
    }
    /**
     * 通过ID查询车场信息
     * @return
     */
    @PostMapping(value = "/getCarParkbyId")
    @HttpLog(operationType = "0",modularTypeName = "通过id查询车场信息")
    public ActionRsp getCarParkbyId(){
//        TCompanyUser user= SessionUtil.getUser();
        TCompanyPark tCompanyPark=new TCompanyPark();
        tCompanyPark.setId(SessionUtil.getParkId());
//        tCompanyPark.setCompanyId(user.getCompanyId());
        return ActionRspUtil.Success(carParkService.getCarParkbyId(tCompanyPark));
//        return tCompanyParkInsideService.getTCompanyParkByID(tCompanyPark);
    }
    /**
     * 查询车场信息Ztree
     * @return
     */
    @PostMapping(value = "/getCarParkZtree")
    @HttpLog(operationType = "0",modularTypeName = "查询车场信息Ztree信息")
    public ControllerRsp getCarParkZtree(HttpServletRequest request){
        TCompanyUser user= SessionUtil.getUser();
        TCompanyPark tCompanyPark=new TCompanyPark();
        tCompanyPark.setCompanyId(user.getCompanyId());
        return ControllerRspUtil.Success(carParkService.getCarParkZtree(tCompanyPark));
    }


    /**
     * 更新车场信息
     * @param tCompanyPark
     * @return
     */
    @PostMapping(value = "/updateCarPark")
    @HttpLog(operationType = "1",modularTypeName = "更新车场信息")
    public ActionRsp UpdateCarPark(TCompanyPark tCompanyPark, HttpServletRequest request)
    {
        TCompanyUser user= SessionUtil.getUser();
        if(tCompanyPark.getAutoRenew()==null){
            tCompanyPark.setAutoRenew(0);
        }
        if(tCompanyPark.getIsWechatMonthly()==null){
            tCompanyPark.setIsWechatMonthly(1);
        }
        String statisticsTime = request.getParameter("statisticsTime");
        if(statisticsTime==null){
            tCompanyPark.setStatisticsBeginTime("");
            tCompanyPark.setStatisticsEndTime("");
        }else{
            if(!statisticsTime.equals("")){
                tCompanyPark.setStatisticsBeginTime(statisticsTime.substring(0,8));
                tCompanyPark.setStatisticsEndTime(statisticsTime.substring(11));
            }
        }
//        tCompanyPark.setCompanyId(user.getCompanyId());
//        System.out.println("tCompanyPark: "+JsonUtil.beanToJson(tCompanyPark));
        return ActionRspUtil.Success(carParkService.UpdateCarPark(tCompanyPark));
//        return tCompanyParkInsideService.UpdateTCompanyPark(tCompanyPark);
    }

    /**
     * 删除车场信息
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteCarPark")
    @HttpLog(operationType = "1",modularTypeName = "删除车场信息")
    public ActionRsp DeleteCarPark(Integer id){
        return ActionRspUtil.Success(carParkService.DeleteCarPark(id));
//        return tCompanyParkInsideService.DeleteTCompanyPark(tCompanyPark);
    }
}
