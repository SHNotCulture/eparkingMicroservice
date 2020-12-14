package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TChargeRule;

import java.util.List;

/**
* @Description: TChargeRuleService接口
* @author 谢轩然
* @date 2020/05/15 11:00
*/
public interface TChargeRuleService {
    /**
    *查询(分页)tChargeRule
    * @param tChargeRule
    * @param page
    * @param limit
    * @return
    */
    PageBean<TChargeRule> getTChargeRulebyPage(TChargeRule tChargeRule, Integer page, Integer limit);

    /**
    * 查询tChargeRule
    * @param tChargeRule
    * @return
    */
    List<TChargeRule> getTChargeRule(TChargeRule tChargeRule);

    /**
    * 更新tChargeRule
    * @param tChargeRule
    * @return
    */
    String UpdateTChargeRule(TChargeRule tChargeRule);

    /**
    * 删除tChargeRule
    * @param tChargeRule
    * @return
    */
    String DeleteTChargeRule(TChargeRule tChargeRule);

    /**
    * 根据ID查询tChargeRule
    * @param id
    * @return
    */
    TChargeRule getTChargeRuleByID(Integer id);

}