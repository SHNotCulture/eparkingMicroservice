package com.eparking.insideService.impl;

import com.common.entity.eparkingCloud.TChargeRule;
import com.eparking.insideService.TChargeRuleInsideService;
import com.common.entity.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;


/**
* @Description: TChargeRuleInsideService接口熔断器
* @author 谢轩然
* @date 2020/05/15 11:00
*/
@Component
public class TChargeRuleInsideServiceImpl  implements TChargeRuleInsideService {

    private static final Logger logger = LoggerFactory.getLogger(TChargeRuleInsideServiceImpl.class);

    /**
    * 查询tChargeRule
    * @param tChargeRule
    * @return
    */
    @Override
    public List<TChargeRule> getTChargeRule(TChargeRule tChargeRule){
        String str = "查询TChargeRule失败！";
        logger.info("查询TChargeRule失败！");
        return null;
    }

    /**
    *查询(分页)tChargeRule
    * @param tChargeRule
    * @param page
    * @param limit
    * @return
    */
    @Override
    public PageBean<TChargeRule> getTChargeRulebyPage(TChargeRule tChargeRule, Integer page, Integer limit){
        String str = "查询TChargeRule分页失败！";
        logger.info("查询TChargeRule分页失败！");
        return null;
    }

    /**
    * 更新tChargeRule
    * @param tChargeRule
    * @return
    */
    @Override
    public String UpdateTChargeRule(TChargeRule tChargeRule){
        String str = "更新TChargeRule失败！";
        logger.info("更新TChargeRule失败！");
        return str;
    }

    /**
    * 删除tChargeRule
    * @param tChargeRule
    * @return
    */
    @Override
    public String DeleteTChargeRule(TChargeRule tChargeRule){
        String str = "删除TChargeRule失败！";
        logger.info("删除TChargeRule失败！");
        return str;
    }
}