package com.eparking.service;

import com.common.entity.PageBean;
import com.common.entity.eparkingCloud.TChargeRule;


import java.util.List;

/**
 * @Author lishuhan
 * @Description:
 * @Date Create in 18:072018-9-14
 * @Modified By:
 */
public interface ChargeRuleService {
    PageBean<TChargeRule> getChargeRulebyPage(TChargeRule tChargeRule, Integer page, Integer limit);
    List<TChargeRule> getChargeRule(TChargeRule tChargeRule);
    String UpdateChargeRule(TChargeRule tChargeRule);
    String DeleteChargeRule(Integer id);


}
