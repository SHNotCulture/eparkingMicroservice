package com.eparkingdb.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TCarPayRule;
import com.common.entity.eparkingCloud.TParkCar;
import net.sf.json.JSONObject;

import java.util.List;

/**
* @Description: TCarPayRuleService接口
* @author 谢轩然
* @date 2020/05/14 18:07
*/
public interface TCarPayRuleService {
    /**
    *查询(分页)tCarPayRule
    * @param tCarPayRule
    * @param page
    * @param limit
    * @return
    */
    PageBean<TCarPayRule> getTCarPayRulebyPage(TCarPayRule tCarPayRule, Integer page, Integer limit);

    /**
    * 查询tCarPayRule
    * @param tCarPayRule
    * @return
    */
    List<TCarPayRule> getTCarPayRule(TCarPayRule tCarPayRule);

    /**
    * 更新tCarPayRule
    * @param tCarPayRule
    * @return
    */
    String UpdateTCarPayRule(TCarPayRule tCarPayRule);

    /**
    * 删除tCarPayRule
    * @param tCarPayRule
    * @return
    */
    String DeleteTCarPayRule(TCarPayRule tCarPayRule);

    /**
    * 根据ID查询tCarPayRule
    * @param id
    * @return
    */
    TCarPayRule getTCarPayRuleByID(Integer id);

    Integer getCarPayRuleId(Integer parkid, String ruleName);

    JSONObject getNeedPay(Integer payStandard, Integer payCount, TParkCar tParkCar);
}