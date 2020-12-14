package com.eparkingdb.controller;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TChargeRule;
import com.common.annotation.HttpLog;
import com.eparkingdb.service.TChargeRuleService;
import com.common.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @Description: TChargeRuleController类
* @author 谢轩然
* @date 2020/05/15 11:00
*/
@RestController
@RequestMapping("/tChargeRule")
public class TChargeRuleController {

    private  static final Logger logger= LoggerFactory.getLogger(TChargeRuleController.class);

    @Autowired
    private TChargeRuleService tChargeRuleService;

    /**
    * 查询TChargeRule信息
    * @paramtChargeRule
    * @return
    */
    @PostMapping(value = "/getTChargeRule")
    @HttpLog(operationType = "0",modularTypeName = "查询TChargeRule")
    public List<TChargeRule> getTChargeRule(@RequestBody TChargeRule tChargeRule){
    return tChargeRuleService.getTChargeRule(tChargeRule);
    }

    /**
    * 查询TChargeRule信息(分页)
    * @paramtChargeRule
    * @return
    */
    @PostMapping(value = "/getTChargeRulebyPage")
    @HttpLog(operationType = "0",modularTypeName = "查询TChargeRule(分页)")
    public PageBean<TChargeRule> getTChargeRulebyPage(@RequestBody TChargeRule tChargeRule, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
    return tChargeRuleService.getTChargeRulebyPage(tChargeRule,page,limit);
    }

    /**
    * 更新TChargeRule信息
    * @paramtChargeRule
    * @return
    */
    @PostMapping(value = "/updateTChargeRule")
    @HttpLog(operationType = "1",modularTypeName = "更新TChargeRule信息")
    public String UpdateTChargeRule(@RequestBody TChargeRule tChargeRule)
    {
        return tChargeRuleService.UpdateTChargeRule(tChargeRule);
    }

    /**
    * 删除TChargeRule信息
    * @param tChargeRule
    * @return
    */
    @PostMapping(value = "/deleteTChargeRule")
    @HttpLog(operationType = "1",modularTypeName = "删除TChargeRule信息")
    public String DeleteTChargeRule(@RequestBody TChargeRule tChargeRule){
    return tChargeRuleService.DeleteTChargeRule(tChargeRule);
    }
}