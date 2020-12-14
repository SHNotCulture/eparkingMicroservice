package com.eparking.insideService;

import com.common.entity.eparkingCloud.TChargeRule;
import com.eparking.insideService.impl.TChargeRuleInsideServiceImpl;
import com.common.util.Common;
import com.common.entity.PageBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @Description: TChargeRuleInsideService接口
* @author 谢轩然
* @date 2020/05/15 11:00
*/
@FeignClient(value = "eparkingCloudData",fallback = TChargeRuleInsideServiceImpl.class)
public interface TChargeRuleInsideService {
    String client= Common.Feign_eparkingCloudData;
    /**
    * 查询tChargeRule
    * @param tChargeRule
    * @return
    */
    @PostMapping(value = client+"/tChargeRule/getTChargeRule")
    List<TChargeRule> getTChargeRule(@RequestBody TChargeRule tChargeRule);

    /**
    *查询(分页)tChargeRule
    * @param tChargeRule
    * @param page
    * @param limit
    * @return
    */
    @PostMapping(value = client+"/tChargeRule/getTChargeRulebyPage")
    PageBean<TChargeRule> getTChargeRulebyPage(@RequestBody TChargeRule tChargeRule, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);

    /**
    * 更新tChargeRule
    * @param tChargeRule
    * @return
    */
    @PostMapping(value = client+"/tChargeRule/updateTChargeRule")
    String UpdateTChargeRule(@RequestBody TChargeRule tChargeRule);

    /**
    * 删除tChargeRule
    * @param tChargeRule
    * @return
    */
    @PostMapping(value = client+"/tChargeRule/deleteTChargeRule")
    String DeleteTChargeRule(@RequestBody TChargeRule tChargeRule);
}