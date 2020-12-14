package com.eparking.controller.work;

import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TChargeRule;
import com.common.entity.eparkingCloud.TDayOff;
import com.common.entity.eparkingCloud.THoliday;
import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.eparking.insideService.TChargeRuleInsideService;
import com.eparking.service.ChargeRuleService;
import com.eparking.service.DayOffService;
import com.eparking.service.HolidayService;
import com.common.util.ActionRspUtil;
import com.common.util.Common;
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
 * @Author lishuhan
 * @Description:临停缴费Controller
 * @Date Create in 20:362018-8-8
 * @Modified By:
 */
@RestController
@RequestMapping(value = "chargeRule")
public class ChargeRuleController {
    private  static final Logger logger= LoggerFactory.getLogger(ChargeRuleController.class);
    @Autowired
    private ChargeRuleService chargeRuleService;
    @Autowired
    private HolidayService holidayService;
    @Autowired
    private DayOffService dayOffService;
    @Autowired
    private TChargeRuleInsideService tChargeRuleInsideService;
    /**
     * 查询临停缴费信息
     * @return
     */
    @PostMapping(value = "/getChargeRulebyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询临停缴费信息")
    public ControllerRsp getChargeRulebyPage(TChargeRule tChargeRule, Integer page, Integer limit){
        tChargeRule.setParkId(SessionUtil.getParkId());
        return ControllerRspUtil.Success(chargeRuleService.getChargeRulebyPage(tChargeRule,page,limit));
//        return tChargeRuleInsideService.getTChargeRulebyPage(tChargeRule,page,limit);
    }


    /**
     * 更新临停缴费信息
     * @param tChargeRule
     * @return
     */
    @PostMapping(value = "/updateChargeRule")
    @HttpLog(operationType = "1",modularTypeName = "更新临停缴费信息")
    public ActionRsp UpdateChargeRule(TChargeRule tChargeRule, HttpServletRequest request)
    {
        if(tChargeRule.getIsMaxSectimeFeeByAdd()==null){
            tChargeRule.setIsMaxSectimeFeeByAdd(0);
        }
        if(tChargeRule.getIsHolidayUse()==null){
            tChargeRule.setIsHolidayUse(0);
        }
        tChargeRule.setParkId(SessionUtil.getParkId());
        return ActionRspUtil.Success(chargeRuleService.UpdateChargeRule(tChargeRule));
//        return tChargeRuleInsideService.UpdateTChargeRule(tChargeRule);
    }

    /**
     * 删除临停缴费信息
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteChargeRule")
    @HttpLog(operationType = "1",modularTypeName = "删除临停缴费信息")
    public ActionRsp DeleteChargeRule(Integer id){
        return ActionRspUtil.Success(chargeRuleService.DeleteChargeRule(id));
/*        TChargeRule tChargeRule = new TChargeRule();
        tChargeRule.setId(id);
        return tChargeRuleInsideService.DeleteTChargeRule(tChargeRule);*/
    }
    /**
     * 查询节假日信息
     * @return
     */
    @PostMapping(value = "/getHoliday")
    @HttpLog(operationType = "0",modularTypeName = "查询节假日信息")
    public ControllerRsp getHoliday(THoliday tHoliday, HttpServletRequest request, Integer page, Integer limit){
        tHoliday.setParkId(Integer.valueOf(request.getSession().getAttribute(Common.ParkId).toString()));
        return ControllerRspUtil.Success(holidayService.getHolidaybyPage(tHoliday,page,limit));
    }


    /**
     * 更新节假日信息
     * @param tHoliday
     * @return
     */
    @PostMapping(value = "/updateHoliday")
    @HttpLog(operationType = "1",modularTypeName = "更新节假日信息")
    public ActionRsp UpdateHoliday(THoliday tHoliday, HttpServletRequest request)
    {
        tHoliday.setParkId(Integer.valueOf(request.getSession().getAttribute(Common.ParkId).toString()));
        tHoliday.setBeginDay(Integer.valueOf(tHoliday.getBeginTime().substring(3, 4)));
        tHoliday.setBeginMonth(Integer.valueOf(tHoliday.getBeginTime().substring(0, 1)));
        return ActionRspUtil.Success(holidayService.UpdateHoliday(tHoliday));
    }

    /**
     * 删除节假日信息
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteHoliday")
    @HttpLog(operationType = "1",modularTypeName = "删除节假日信息")
    public ActionRsp DeleteHoliday(Integer id){
        return ActionRspUtil.Success(holidayService.DeleteHoliday(id));
    }
    /**
     * 查询调休日信息
     * @return
     */
    @PostMapping(value = "/getDayOff")
    @HttpLog(operationType = "0",modularTypeName = "查询调休日信息")
    public ControllerRsp getDayOff(TDayOff tDayOff, HttpServletRequest request, Integer page, Integer limit){
        tDayOff.setParkId(Integer.valueOf(request.getSession().getAttribute(Common.ParkId).toString()));
        return ControllerRspUtil.Success(dayOffService.getDayOffbyPage(tDayOff,page,limit));
    }


    /**
     * 更新调休日信息
     * @param tDayOff
     * @return
     */
    @PostMapping(value = "/updateDayOff")
    @HttpLog(operationType = "1",modularTypeName = "更新调休日信息")
    public ActionRsp UpdateDayOff(TDayOff tDayOff, HttpServletRequest request)
    {
        tDayOff.setParkId(Integer.valueOf(request.getSession().getAttribute(Common.ParkId).toString()));
        return ActionRspUtil.Success(dayOffService.UpdateDayOff(tDayOff));
    }

    /**
     * 删除调休日信息
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteDayOff")
    @HttpLog(operationType = "1",modularTypeName = "删除调休日信息")
    public ActionRsp DeleteDayoff(Integer id){
        return ActionRspUtil.Success(dayOffService.DeleteDayoff(id));
    }
}
