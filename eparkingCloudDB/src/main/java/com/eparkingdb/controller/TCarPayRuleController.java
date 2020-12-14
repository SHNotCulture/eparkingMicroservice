package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarPayRule;
import com.common.entity.eparkingCloud.TParkCar;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TCarPayRuleService;
import com.common.util.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TCarPayRuleController类
* @author 谢轩然
* @date 2020/05/14 18:07
*/
@RestController
@RequestMapping("/tCarPayRule")
public class TCarPayRuleController {

    private  static final Logger logger= LoggerFactory.getLogger(TCarPayRuleController.class);

    @Autowired
    private TCarPayRuleService tCarPayRuleService;

    /**
    * 查询TCarPayRule信息
    * @paramtCarPayRule
    * @return
    */
    @PostMapping(value = "/getTCarPayRule")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarPayRule")
    public List<TCarPayRule> getTCarPayRule(@RequestBody TCarPayRule tCarPayRule){
    return tCarPayRuleService.getTCarPayRule(tCarPayRule);
    }

    /**
    * 查询TCarPayRule信息(分页)
    * @paramtCarPayRule
    * @return
    */
    @PostMapping(value = "/getTCarPayRulebyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TCarPayRule(分页)")
    public PageBean<TCarPayRule> getTCarPayRulebyPage(@RequestBody TCarPayRule tCarPayRule, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tCarPayRuleService.getTCarPayRulebyPage(tCarPayRule,page,limit);
    }

    /**
    * 更新TCarPayRule信息
    * @paramtCarPayRule
    * @return
    */
    @PostMapping(value = "/updateTCarPayRule")
    @HttpLog(operationType = "1",modularTypeName = "更新TCarPayRule信息")
    public String UpdateTCarPayRule(@RequestBody TCarPayRule tCarPayRule)
    {
        return tCarPayRuleService.UpdateTCarPayRule(tCarPayRule);
    }

    /**
    * 删除TCarPayRule信息
    * @param tCarPayRule
    * @return
    */
    @PostMapping(value = "/deleteTCarPayRule")
    @HttpLog(operationType = "1",modularTypeName = "删除TCarPayRule信息")
    public String DeleteTCarPayRule(@RequestBody TCarPayRule tCarPayRule){
    return tCarPayRuleService.DeleteTCarPayRule(tCarPayRule);
    }

    /**
     * 根据id查询TCarPayRule信息
     * @param id
     * @return
     */
    @PostMapping(value = "/getTCarPayRuleByID")
    @HttpLog(operationType = "1",modularTypeName = "删除TCarPayRule信息")
    public TCarPayRule getTCarPayRuleByID(@RequestParam("id") Integer id){
        return tCarPayRuleService.getTCarPayRuleByID(id);
    }

    /**
     * 获取月租规则id
     * @param
     * @return
     */
    @PostMapping(value = "/getCarPayRuleId")
    @HttpLog(operationType = "0",modularTypeName = "获取月租规则id")
    public Integer getCarPayRuleId(@RequestParam("parkId") Integer parkId,@RequestParam("ruleName") String ruleName){
        return tCarPayRuleService.getCarPayRuleId(parkId,ruleName);
    }

    @PostMapping(value = "/getNeedPay")
    @HttpLog(operationType = "0",modularTypeName = "查询应收费")
    public JSONObject getNeedPay(@RequestParam("payStandard") Integer payStandard, @RequestParam("payCount") Integer payCount, @RequestBody TParkCar tParkCar){
        return tCarPayRuleService.getNeedPay(payStandard,payCount,tParkCar);
    }
}