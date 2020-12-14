package com.eparking.controller.work;

import com.common.annotation.HttpLog;
import com.common.entity.ActionRsp;
import com.common.entity.ControllerRsp;
import com.common.entity.eparkingCloud.TCarPayRule;
import com.common.entity.eparkingCloud.TCompanyUser;
import com.eparking.insideService.TCarPayRuleInsideService;
import com.eparking.service.CarPayRuleService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author lishuhan
 * @Description:登记车规则Controller
 * @Date Create in 16:492018-7-16
 * @Modified By:
 */
@RestController
@RequestMapping(value = "carPayRule")
public class CarPayRuleController {
    private  static final Logger logger= LoggerFactory.getLogger(CarPayRuleController.class);
    @Autowired
    private CarPayRuleService carPayRuleService;
    @Autowired
    private TCarPayRuleInsideService tCarPayRuleInsideService;

    /**
     * 查询登记车缴费信息
     * @return
     */
    @PostMapping(value = "/getPayRule")
    @HttpLog(operationType = "0",modularTypeName = "查询登记车规则")
    public ActionRsp getPayRule(TCarPayRule tCarPayRule){
        TCompanyUser user= SessionUtil.getUser();
        //admin账号登录可以根据parkId查询规则
//        tCarPayRule.setCompanyId(user.getCompanyId());
        tCarPayRule.setParkId(SessionUtil.getParkId());
        return ActionRspUtil.Success(carPayRuleService.getCarPayRule(tCarPayRule));
//        return ActionRspUtil.Success(tCarPayRuleInsideService.getTCarPayRule(tCarPayRule));
    }

    /**
     * 查询登记车缴费信息
     * @return
     */
    @PostMapping(value = "/getPayRuleByParkId")
    @HttpLog(operationType = "0",modularTypeName = "根据车场ID查询登记车规则")
    public ActionRsp getPayRuleByParkId(String parkId){
        TCarPayRule tCarPayRule = new TCarPayRule();
        TCompanyUser user= SessionUtil.getUser();
        tCarPayRule.setCompanyId(user.getCompanyId());
        tCarPayRule.setParkId(Integer.valueOf(parkId));
        List<TCarPayRule> tCarPayRuleList = carPayRuleService.getCarPayRule(tCarPayRule);
         return ActionRspUtil.Success(tCarPayRuleList);
//        return ActionRspUtil.Success(tCarPayRuleInsideService.getTCarPayRule(tCarPayRule));
    }

    /**
     * 查询登记车缴费信息(分页)
     * @return
     */
    @PostMapping(value = "/getPayRulebyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询登记车缴费信息(分页)")
    public ControllerRsp getPayRulebyPage(TCarPayRule tCarPayRule, HttpServletRequest request, Integer page, Integer limit){
        TCompanyUser user=SessionUtil.getUser();
        tCarPayRule.setCompanyId(user.getCompanyId());
        tCarPayRule.setParkId(SessionUtil.getParkId());
        return ControllerRspUtil.Success(carPayRuleService.getCarPayRulebyPage(tCarPayRule,page,limit));
//        return tCarPayRuleInsideService.getTCarPayRulebyPage(tCarPayRule,page,limit);
    }
    /**
     * 更新登记车规则
     * @param tCarPayRule
     * @return
     */
    @PostMapping(value = "/updatePayRule")
    @HttpLog(operationType = "1",modularTypeName = "更新登记车规则")
    public ActionRsp UpdatePayRule(TCarPayRule tCarPayRule, HttpServletRequest request)
    {
        String effectiveTime = request.getParameter("effectiveTime");
        String dailyStartTime = effectiveTime.substring(0,8);
        String dailyEndTime = effectiveTime.substring(11);
//        System.out.println("dailyStartTime:"+dailyStartTime+",dailyEndTime:"+dailyEndTime);
        TCompanyUser user= SessionUtil.getUser();
        tCarPayRule.setDailyStartTime(dailyStartTime);
        tCarPayRule.setDailyEndTime(dailyEndTime);
        tCarPayRule.setCompanyId(user.getCompanyId());
        tCarPayRule.setParkId(SessionUtil.getParkId());
        return ActionRspUtil.Success(carPayRuleService.UpdateCarPayRule(tCarPayRule));
//        return tCarPayRuleInsideService.UpdateTCarPayRule(tCarPayRule);
    }

    /**
     * 删除登记车规则
     * @param id
     * @return
     */
    @PostMapping(value = "/deletePayRule")
    @HttpLog(operationType = "1",modularTypeName = "删除登记车规则")
    public ActionRsp DeletePayRule(Integer id){
        TCarPayRule tCarPayRule = new TCarPayRule();
        tCarPayRule.setId(id);
        return ActionRspUtil.Success(carPayRuleService.DeleteCarPayRule(tCarPayRule));
/*        TCarPayRule tCarPayRule = new TCarPayRule();
        tCarPayRule.setId(id);
        return tCarPayRuleInsideService.DeleteTCarPayRule(tCarPayRule);*/
    }
}
