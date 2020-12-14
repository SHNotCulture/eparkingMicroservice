package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TParkInOut;
import com.common.entity.eparkingCloud.TParkInOutCriteria;
import com.eparking.insideService.TBusinesCouponInsideService;
import com.eparking.insideService.TParkingRecordInsideService;
import com.eparking.service.BusineService;
import com.eparking.service.BusinesCouponService;
import com.eparking.service.ParkingRecordService;
import com.common.util.ActionRspUtil;
import com.common.util.ControllerRspUtil;
import com.common.entity.PageBean;
import com.common.util.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CarParkingRecord
 * @Author jin
 * @Date 2018/7/31 16:25
 **/
@RestController
@RequestMapping(value = "parkingRecord")
public class ParkingRecordController {
    private static final Logger logger = LoggerFactory.getLogger(ParkingRecordController.class);

    @Autowired
    private ParkingRecordService parkingRecordService;
    @Autowired
    private BusinesCouponService businesCouponService;
    @Autowired
    private BusineService busineService;
    @Autowired
    private TParkingRecordInsideService tParkingRecordInsideService;
    @Autowired
    private TBusinesCouponInsideService tBusinesCouponInsideService;

    @PostMapping(value = "/getParkinout")
    @HttpLog(operationType = "0",modularTypeName = "查询车辆进出场记录")
    public ControllerRsp getParkinout(TParkInOut parkInOut, Integer page, Integer limit, HttpServletRequest request) {
        String isUpdateCarplate = request.getParameter("isUpdateCarplate");
        String settleDateBegin = request.getParameter("settleDateBegin");
        String settleDateEnd = "@"+request.getParameter("settleDateEnd");
        String outTimeBegin =(request.getParameter("outTimeBegin")==null)?"":(request.getParameter("outTimeBegin"));
        String outTimeEnd =(request.getParameter("outTimeEnd")==null)?"":(request.getParameter("outTimeEnd"));
        if(parkInOut.getParkId()==null){
            parkInOut.setParkId(SessionUtil.getParkId());
        }
//        parkInOut.setParkId(SessionUtil.getParkId());
        if ((settleDateBegin+settleDateEnd).length()>9){
                parkInOut.setSettleDate(settleDateBegin+settleDateEnd);
        }
        if (isUpdateCarplate!=null){
            parkInOut.setUpdatedincarplate("havaupdate");
        }
        return ControllerRspUtil.Success(parkingRecordService.getParkinoutbyPage(parkInOut,outTimeBegin,outTimeEnd,page,limit));
//        return tParkingRecordInsideService.getParkinoutbyPage(parkInOut,page,limit);
    }

    @PostMapping(value = "/getParkInOutbyUmp")
    @HttpLog(operationType = "0",modularTypeName = "查询车辆进出场记录(云值守)")
    public ControllerRsp getParkInOutbyUmp( String inPortName,String outPortName,Integer parkId,Integer page,Integer limit) {
        TParkInOut parkInOut =new TParkInOut();
        parkInOut.setParkId(parkId);
        parkInOut.setInPortName(inPortName);
        parkInOut.setOutPortName(outPortName);
       /* String isUpdateCarplate = request.getParameter("isUpdateCarplate");
        String settleDateBegin = request.getParameter("settleDateBegin");
        String settleDateEnd = "@"+request.getParameter("settleDateEnd");*/
        if(parkInOut.getParkId()==null){
            parkInOut.setParkId(SessionUtil.getParkId());
        }
        String outTimeBegin="";
        String outTimeEnd="";
       /* if ((settleDateBegin+settleDateEnd).length()>9){
            parkInOut.setSettleDate(settleDateBegin+settleDateEnd);
        }
        if (isUpdateCarplate!=null){
            parkInOut.setUpdatedincarplate("havaupdate");
        }*/
        PageBean<TParkInOut> tParkInOutPageBean=parkingRecordService.getParkinoutbyPage(parkInOut,outTimeBegin,outTimeEnd,page,limit);
        return ControllerRspUtil.Success(tParkInOutPageBean);
    }


    @GetMapping (value = "/getTodayMsg")
    public ControllerRsp getTodayMsg(){
        List listltmsg = new ArrayList();
        TParkInOutCriteria tParkInOutCriteria = new TParkInOutCriteria();
        TParkInOutCriteria.Criteria criteria = tParkInOutCriteria.createCriteria();
        criteria.andInCarPlateLike("A222");
        //Double needpay = tParkInOutMapper.sumNeedpayToday(tParkInOutCriteria);
        //listltmsg.add(needpay);
//        System.out.println(listltmsg);
        return ControllerRspUtil.Success(listltmsg);
    }


    @PostMapping(value = "/getCashPayment")
    @HttpLog(operationType = "0",modularTypeName = "查询车辆进出场记录")
    public ControllerRsp getCashPayment(TParkInOut parkInOut, HttpServletRequest request,Integer page, Integer limit) {
        /*String type = request.getParameter("type");
        System.out.println(type);*/
        String parkId = SessionUtil.getParkId().toString();

       /* TParkInOutCriteria tParkInOutCriteria = new TParkInOutCriteria();
        if (parkInOut != null) {
            TParkInOutCriteria.Criteria criteria = tParkInOutCriteria.createCriteria();
            if (parkInOut.getInTime() != null && parkInOut.getInTime().length()>0) {
                criteria.andInTimeGreaterThanOrEqualTo(parkInOut.getInTime());
            }
            if (parkInOut.getOutTime()!=null && parkInOut.getOutTime().length()>0){
                criteria.andInTimeLessThanOrEqualTo(parkInOut.getOutTime());
            }
            if(parkInOut.getChargeType()!=null && !parkInOut.getChargeType().equals("")){
                criteria.andChargeTypeEqualTo(parkInOut.getChargeType());
            }
        }
        List<TParkInOut> tParkInOutList = tParkInOutMapper.selectByExample(tParkInOutCriteria);*/
        return ControllerRspUtil.Success(parkingRecordService.getParkinoutDetailbyPage(parkInOut,parkId,page,limit));
    }

/*    @PostMapping(value = "/getBusineCoupon")
    @HttpLog(operationType = "0",modularTypeName = "查询车辆优惠详情")
    public ControllerRsp getBusineCoupon(TParkInOut parkInOut){
        List list = businesCouponService.getBusinesCouponByCarplate(parkInOut);
        return ControllerRspUtil.Success(list);
    }*/
    @PostMapping(value = "/getBusineCoupon")
    @HttpLog(operationType = "0",modularTypeName = "查询车辆优惠详情")
    public ActionRsp getBusineCoupon(TParkInOut parkInOut){
        return ActionRspUtil.Success(businesCouponService.getBusinesCouponByCarplate(parkInOut));
//        return ActionRspUtil.Success(tBusinesCouponInsideService.getBusinesCouponByCarplate(parkInOut));
    }

    @PostMapping(value = "/advanceNotice")
    @HttpLog(operationType = "1",modularTypeName = "预缴通知")
    public String advanceNotice(@RequestParam("parkId") Integer parkId, @RequestParam("inCarPlate") String inCarPlate, @RequestParam("cloudOrderId") String cloudOrderId, @RequestParam("needPay") Double needPay, @RequestParam("actualPay") Double actualPay, @RequestParam("ePayType") Integer ePayType){
        return parkingRecordService.advanceNotice(parkId, inCarPlate, cloudOrderId, needPay, actualPay, ePayType);
    }

    @PostMapping(value = "/getParkinoutUnlimitByPage")
    @HttpLog(operationType = "0",modularTypeName = "查询车辆进出场记录(无限制)")
    public ControllerRsp getParkinoutUnlimitByPage(TParkInOut parkInOut, Integer page, Integer limit, HttpServletRequest request) {
        String isUpdateCarplate = request.getParameter("isUpdateCarplate");
        if(parkInOut.getParkId()==null){
            parkInOut.setParkId(SessionUtil.getParkId());
        }
        if (isUpdateCarplate!=null){
            parkInOut.setUpdatedincarplate("havaupdate");
        }
        return ControllerRspUtil.Success(parkingRecordService.getParkinoutUnlimitByPage(parkInOut,page,limit));
    }
}

